from diffusers import StableDiffusionPipeline
from PIL import Image
import torch

def main():
    try:
        # GPU 사용 가능 여부 체크
        if not torch.cuda.is_available():
            print("GPU를 찾을 수 없습니다! 이 모델은 효율적인 실행을 위해 GPU가 필요합니다.")
            return
            
        print(f"사용 중인 GPU: {torch.cuda.get_device_name(0)}")
        print("파이프라인 로딩 중...")
        
        pipe = StableDiffusionPipeline.from_pretrained(
            "camenduru/IDM-VTON-F16",
            torch_dtype=torch.float16,  # GPU 메모리 절약을 위해 float16 사용
        ).to("cuda")  # GPU 사용
        
        print("이미지 로딩 중...")
        person_image = Image.open("person.jpg").resize((768, 1024))
        clothes_image = Image.open("dress.jpg").resize((768, 1024))
        
        print("이미지 처리 중...")
        result = pipe(
            person_image=person_image,
            clothes_image=clothes_image
        ).images[0]
        
        print("결과 저장 중...")
        result.save("result.png")
        print("완료!")
        
    except Exception as e:
        print(f"오류가 발생했습니다: {str(e)}")
        print(f"오류 유형: {type(e)}")
        
if __name__ == "__main__":
    main()

