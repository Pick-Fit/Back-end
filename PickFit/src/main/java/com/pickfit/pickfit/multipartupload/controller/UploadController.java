package com.pickfit.pickfit.multipartupload.controller;

import com.pickfit.pickfit.multipartupload.dto.UploadDTO;
import com.pickfit.pickfit.multipartupload.service.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> initiateUpload(@RequestBody UploadDTO uploadDTO) {
        logger.info("Received POST /api/initiate request with fileName: {}", uploadDTO.getFileName());
        // 업로드 시작 요청 처리
        String uploadId = uploadService.initiateMultipartUpload(uploadDTO.getFileName());
        logger.info("Generated Upload ID: {}", uploadId);
        return ResponseEntity.ok(uploadId);
    }

    @GetMapping("/url")
    public ResponseEntity<List<String>> getPresignedUrls(
            @RequestParam String uploadId,
            @RequestParam String fileName,
            @RequestParam int partCount) {
        // Presigned URL 요청 처리
        List<String> urls = uploadService.generatePresignedUrls(uploadId, fileName, partCount);
        return ResponseEntity.ok(urls);
    }

    @PostMapping("/complete")
    public ResponseEntity<String> completeUpload(@RequestBody UploadDTO.CompleteRequest completeRequest) {
        // 멀티파트 업로드 완료 처리
        String fileUrl = uploadService.completeMultipartUpload(completeRequest);
        return ResponseEntity.ok(fileUrl);
    }
}
