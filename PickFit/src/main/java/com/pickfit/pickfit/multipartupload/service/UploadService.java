package com.pickfit.pickfit.multipartupload.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.pickfit.pickfit.multipartupload.dto.UploadDTO;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UploadService {

    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);

    private final AmazonS3 amazonS3;
    private final String bucketName = "pickfit";

    public UploadService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String initiateMultipartUpload(String fileName) {
        // AWS S3 멀티파트 업로드 초기화
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, fileName);
        InitiateMultipartUploadResult result = amazonS3.initiateMultipartUpload(request);
        logger.info("1단계 id 발급 -----------------------------------------Multipart upload initiated. UploadId: {}", result.getUploadId());
        return result.getUploadId();
    }

    public List<String> generatePresignedUrls(String uploadId, String fileName, int partCount) {
        logger.info("Generating presigned URLs for UploadId: {}, FileName: {}, PartCount: {}", uploadId, fileName, partCount);
        List<String> presignedUrls = new ArrayList<>();
        for (int partNumber = 1; partNumber <= partCount; partNumber++) {
            GeneratePresignedUrlRequest presignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileName)
                    .withMethod(HttpMethod.PUT) // HTTP 메서드 지정
                    .withExpiration(new Date(System.currentTimeMillis() + 3600000));// URL 유효 기간 설정

            presignedUrlRequest.addRequestParameter("Content-Type", "image/jpeg");
            URL presignedUrl = amazonS3.generatePresignedUrl(presignedUrlRequest);
            presignedUrls.add(presignedUrl.toString());
        }
        return presignedUrls;
    }

    public String completeMultipartUpload(UploadDTO.CompleteRequest completeRequest) {
        logger.info("Completing multipart upload for UploadId: {}, FileName: {}", completeRequest.getUploadId(), completeRequest.getFileName());
        // AWS SDK의 CompleteMultipartUploadRequest 생성자에 적합한 데이터 전달
        CompleteMultipartUploadRequest request = new CompleteMultipartUploadRequest(
                bucketName,                      // S3 버킷 이름
                completeRequest.getFileName(),   // S3 객체 키 (파일 이름)
                completeRequest.getUploadId(),  // 업로드 ID
                completeRequest.getParts()      // PartETag 리스트
        );

        // S3에 멀티파트 업로드 완료 요청
        CompleteMultipartUploadResult result = amazonS3.completeMultipartUpload(request);
        logger.info("Multipart upload completed. File URL: {}", result.getLocation());
        return result.getLocation(); // 업로드된 파일의 URL 반환
    }

}
