# import os
# import subprocess

# # # 경로 설정
# # repo_path_local = "zhengchong/CatVTON"
# # data_root_path = r'C:\Users\epdgn\Downloads\catvton_FASTAPI\trenbe'
# # output_dir = r'C:\Users\epdgn\Downloads\catvton_FASTAPI\output'

# # 1. Preprocess Agnostic Mask
# print("Starting preprocess_agnostic_mask.py...", flush=True)
# preprocess_command = [
#     "python", "preprocess_agnostic_mask.py",
#     "--data_root_path", data_root_path,
#     "--repo_path", repo_path_local
# ]

# try:
#     subprocess.run(preprocess_command, check=True)
#     print("Preprocessing completed successfully!", flush=True)
# except subprocess.CalledProcessError as e:
#     print(f"Error during preprocessing: {e}", flush=True)
#     exit(1)

# # 2. Run Inference
# print("Starting inference.py...", flush=True)
# inference_command = [
#     "python", "-u", "inference.py",
#     "--dataset", "trenbe",
#     "--data_root_path", data_root_path,
#     "--output_dir", output_dir,
#     "--dataloader_num_workers", "0",
#     "--repaint",
#     "--batch_size", "1",
#     "--seed", "555"
# ]

# try:
#     subprocess.run(inference_command, check=True)
#     print("Inference completed successfully!", flush=True)
# except subprocess.CalledProcessError as e:
#     print(f"Error during inference: {e}", flush=True)
#     exit(1)
import argparse
import subprocess
import os

# 경로 설정 (FastAPI에서 제공되는 경로 사용)
def main(person_image, cloth_image):
    # 경로 설정
    output_dir = r'C:\Users\epdgn\Downloads\catvton_FASTAPI\output'
    data_root_path = r'C:\Users\epdgn\Downloads\catvton_FASTAPI'  # 데이터 루트 경로


    # 1. Preprocess Agnostic Mask
    print("Starting preprocess_agnostic_mask.py...", flush=True)
    preprocess_command = [
        "python", "preprocess_agnostic_mask.py",
        "--person_image", person_image,  # FastAPI에서 넘겨주는 경로 사용
        "--cloth_image", cloth_image,    # FastAPI에서 넘겨주는 경로 사용
        "--data_root_path", data_root_path,  # 필수 인자 추가

    ]

    try:
        subprocess.run(preprocess_command, check=True)
        print("Preprocessing completed successfully!", flush=True)
    except subprocess.CalledProcessError as e:
        print(f"Error during preprocessing: {e}", flush=True)
        exit(1)

    # 2. Run Inference
    print("Starting inference.py...", flush=True)
    inference_command = [
        "python", "-u", "inference.py",
        "--person_image", person_image,  # FastAPI에서 넘겨주는 경로 사용
        "--cloth_image", cloth_image,    # FastAPI에서 넘겨주는 경로 사용
        "--output_dir", output_dir,
        "--dataloader_num_workers", "0",
        "--repaint",
        "--batch_size", "1",
        "--seed", "555"
    ]

    try:
        subprocess.run(inference_command, check=True)
        print("Inference completed successfully!", flush=True)
    except subprocess.CalledProcessError as e:
        print(f"Error during inference: {e}", flush=True)
        exit(1)

if __name__ == '__main__':
    import sys
    # FastAPI에서 전달받은 이미지를 인자로 받음
    if len(sys.argv) != 3:
        print("Usage: python run.py <person_image_path> <cloth_image_path>")
        exit(1)
    
    person_image = sys.argv[1]  # FastAPI에서 받은 이미지 경로
    cloth_image = sys.argv[2]   # FastAPI에서 받은 이미지 경로
    
    main(person_image, cloth_image)
