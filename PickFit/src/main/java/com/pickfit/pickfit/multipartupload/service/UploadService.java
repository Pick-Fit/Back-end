package com.pickfit.pickfit.multipartupload.service;

import com.amazonaws.services.s3.AmazonS3;
import com.pickfit.pickfit.multipartupload.entity.UploadEntity;
import com.pickfit.pickfit.oauth2.model.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;
import com.pickfit.pickfit.multipartupload.repository.UploadRepository;
import com.pickfit.pickfit.oauth2.model.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.util.Optional;

@Service
public class UploadService {

    private final AmazonS3 amazonS3;
    private final String bucketName = "pickfit"; // 버킷 이름 설정
    private final UploadRepository uploadRepository;

    public UploadService(AmazonS3 amazonS3, UploadRepository uploadRepository){
        this.amazonS3 = amazonS3;
        this.uploadRepository = uploadRepository;
    }

    public String uploadFile(String userEmail, MultipartFile file) {
        try {

            String keyName = "userimages/" + file.getOriginalFilename(); // 파일을 S3에 저장할 경로 지정

            // 파일 메타데이터 설정
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            // S3에 파일 업로드
            amazonS3.putObject(new PutObjectRequest(bucketName, keyName, file.getInputStream(), null));

            String fileUrl = amazonS3.getUrl(bucketName, keyName).toString();

            // 이미지 정보 DB에 저장
            UploadEntity uploadEntity = new UploadEntity(userEmail, file.getOriginalFilename(), fileUrl, LocalDateTime.now());

            uploadRepository.save(uploadEntity); // 여기서 데이터베이스에 저장

            return fileUrl; // 업로드된 파일의 URL 반환

        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
        }
    }
}
