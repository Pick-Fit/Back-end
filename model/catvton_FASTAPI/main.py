from fastapi import FastAPI, UploadFile, File, HTTPException
from contextlib import asynccontextmanager
import base64
import json
from io import BytesIO
from PIL import Image, ImageFilter
import os
from datetime import datetime
import torch
import numpy as np
from huggingface_hub import snapshot_download
from model.cloth_masker import AutoMasker
from model.pipeline import CatVTONPipeline

app = FastAPI()

person_image_dir = "trenbe/test/images/"
clothing_image_dir = "trenbe/test/cloth/"
agnostic_mask_dir = "trenbe/test/agnostic_masks/"
output_image_dir = "output/"
seed = 555

@asynccontextmanager
async def lifespan(app: FastAPI):
    print("Initializing application resources...")
    repo_path = snapshot_download(repo_id="zhengchong/CatVTON")

    automasker = AutoMasker(
        densepose_ckpt=os.path.join(repo_path, "DensePose"),
        schp_ckpt=os.path.join(repo_path, "SCHP"),
        device="cuda"
    )
    # CatVTONPipeline 초기화
    @torch.no_grad()
    def load_catvton_pipeline():
        return CatVTONPipeline(
            attn_ckpt_version="mix",
            attn_ckpt="zhengchong/CatVTON",
            base_ckpt="booksforcharlie/stable-diffusion-inpainting",
            weight_dtype=torch.float32,
            device="cuda",
            skip_safety_check=True,
            use_tf32=True,
            compile=False
        )
    catvton_pipeline = load_catvton_pipeline()
    app.state.automasker = automasker
    app.state.catvton_pipeline = catvton_pipeline
    yield
    print("Cleaning up application resources...")


app = FastAPI(lifespan=lifespan)
# Helper functions
def save_image_from_base64(base64_data: str, file_path: str):
    """Base64 이미지를 디코딩하여 저장"""
    image_data = base64.b64decode(base64_data)
    image = Image.open(BytesIO(image_data))
    image.save(file_path)

def generate_and_save_mask(automasker: AutoMasker, person_image_path: str, mask_path: str):
    """마스크 생성 및 저장"""
    mask = automasker(person_image_path)['mask']
    mask.save(mask_path)

def repaint(person, mask, result):
    """리페인팅 기능"""
    _, h = result.size
    kernal_size = h // 100
    if kernal_size % 2 == 0:
        kernal_size += 1
    mask = mask.filter(ImageFilter.GaussianBlur(kernal_size))
    mask = mask.resize(person.size, Image.BILINEAR)
    result = result.resize(person.size, Image.BILINEAR)

    person_np = np.array(person)
    result_np = np.array(result)
    mask_np = np.array(mask) / 255
    mask_np = np.expand_dims(mask_np, axis=-1)
    mask_np = np.repeat(mask_np, 3, axis=-1)

    repaint_result = person_np * (1 - mask_np) + result_np * mask_np
    return Image.fromarray(repaint_result.astype(np.uint8))

def apply_virtual_tryon(catvton_pipeline, person_image_path: str, clothing_image_path: str, mask_path: str, output_image_dir: str):
    """Virtual Try-On 적용"""
    person_image = Image.open(person_image_path)
    clothing_image = Image.open(clothing_image_path)
    mask_image = Image.open(mask_path)

    generator = torch.Generator(device="cuda").manual_seed(seed)
    results = catvton_pipeline(
        person_image,
        clothing_image,
        mask_image,
        num_inference_steps=50,
        guidance_scale=2.5,
        height=1024,
        width=768,
        generator=generator,
        eta=1.0
    )

    output_image_path = os.path.join(output_image_dir, "output_image.jpg")
    repaint_result = repaint(person_image, mask_image, results[0])
    repaint_result.save(output_image_path)

# FastAPI Endpoint
@app.post("/upload/")
async def upload_images(file: UploadFile = File(...)):
    contents = await file.read()
    data = json.loads(contents)
    person_base64 = data.get("person_base64")
    cloth_base64 = data.get("cloth_base64")

    timestamp = datetime.now().strftime("%Y%m%d%H%M%S")
    person_image_name = f"{timestamp}_person.jpg"
    clothing_image_name = f"{timestamp}_cloth.jpg"
    mask_name = f"{timestamp}_mask.png"

    person_image_path = os.path.join(person_image_dir, person_image_name)
    clothing_image_path = os.path.join(clothing_image_dir, clothing_image_name)
    mask_path = os.path.join(agnostic_mask_dir, mask_name)

    save_image_from_base64(person_base64, person_image_path)
    save_image_from_base64(cloth_base64, clothing_image_path)

    generate_and_save_mask(app.state.automasker, person_image_path, mask_path)

    apply_virtual_tryon(app.state.catvton_pipeline, person_image_path, clothing_image_path, mask_path, output_image_dir)

    output_image_name = f"{timestamp}_output.jpg"
    return {"message": "Images processed successfully", "output_image": f"{timestamp}_output.jpg"}