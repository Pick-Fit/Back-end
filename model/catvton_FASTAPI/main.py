from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import base64
from PIL import Image, ImageOps
from io import BytesIO
import os
import subprocess

app = FastAPI()

# 디렉토리 설정
PERSON_DIR = "trenbe/test/images"
CLOTH_DIR = "trenbe/test/cloth"
TEST_PAIR_FILE = "trenbe/test/test_pairs_paired.txt"

class Base64Images(BaseModel):
    person_base64: str
    cloth_base64: str

def resize_with_aspect_ratio(image, target_width=384, target_height=512):    
    aspect_ratio = image.width / image.height
    target_aspect_ratio = target_width / target_height
    if aspect_ratio > target_aspect_ratio:
        new_width = target_width
        new_height = int(target_width / aspect_ratio)
    else:
        new_height = target_height
        new_width = int(target_height * aspect_ratio)
    resized_image = image.resize((new_width, new_height), Image.LANCZOS)
    padded_image = ImageOps.pad(resized_image, (target_width, target_height), color=(0, 0, 0))
    return padded_image

def decode_base64_to_image(base64_string, output_path, target_width=384, target_height=512):
    image_data = base64.b64decode(base64_string)
    image = Image.open(BytesIO(image_data))    
    processed_image = resize_with_aspect_ratio(image, target_width, target_height)    
    processed_image.save(output_path)
    return output_path

def append_to_test_pairs(person_image_name, cloth_image_name):
    with open(TEST_PAIR_FILE, "a") as file:
        file.write(f"{person_image_name} {cloth_image_name}\n")

def run_virtual_tryon(person_image_path, cloth_image_path):
    repo_path_local = "zhengchong/CatVTON"
    data_root_path = r'C:\Users\epdgn\Downloads\catvton_FASTAPI\trenbe'
    output_dir = r'C:\Users\epdgn\Downloads\catvton_FASTAPI\output'

    preprocess_command = [
        "python", "c:/Users/epdgn/Downloads/catvton_FASTAPI/preprocess_agnostic_mask.py", 
        "--data_root_path", data_root_path,
        "--repo_path", repo_path_local
    ]    
    subprocess.run(preprocess_command, check=True)
    
    inference_command = [
        "python", "-u", "c:/Users/epdgn/Downloads/catvton_FASTAPI/inference.py",  
        "--dataset", "trenbe",
        "--data_root_path", data_root_path,
        "--output_dir", output_dir,
        "--dataloader_num_workers", "4",
        "--repaint",
        "--batch_size", "1",
        "--seed", "555"
    ]    
    subprocess.run(inference_command, check=True)
    return "Virtual try-on completed successfully!"

@app.post("/decode-image/")
async def decode_image(image_data: Base64Images):    
    person_image_name = "decoded_image.jpg"
    person_output_path = os.path.join(PERSON_DIR, person_image_name)
    decoded_person_image_path = decode_base64_to_image(image_data.person_base64, person_output_path)    
    
    cloth_image_name = "decoded_image.jpg"
    cloth_output_path = os.path.join(CLOTH_DIR, cloth_image_name)
    decoded_cloth_image_path = decode_base64_to_image(image_data.cloth_base64, cloth_output_path)
    
    append_to_test_pairs(person_image_name, cloth_image_name)

    result = run_virtual_tryon(decoded_person_image_path, decoded_cloth_image_path)
    return {
        "message": "Virtual Try-On completed successfully",
        "result": result  
    }
