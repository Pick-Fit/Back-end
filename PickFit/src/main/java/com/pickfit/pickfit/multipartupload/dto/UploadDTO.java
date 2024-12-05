package com.pickfit.pickfit.multipartupload.dto;

import com.amazonaws.services.s3.model.PartETag;

import java.util.List;

public class UploadDTO {

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public static class CompleteRequest {
        private String uploadId;
        private String fileName;
        private List<PartETag> parts;

        public String getUploadId() {
            return uploadId;
        }

        public void setUploadId(String uploadId) {
            this.uploadId = uploadId;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public List<PartETag> getParts() {
            return parts;
        }

        public void setParts(List<PartETag> parts) {
            this.parts = parts;
        }

        @Override
        public String toString() {
            return "CompleteRequest{" +
                    "uploadId='" + uploadId + '\'' +
                    ", fileName='" + fileName + '\'' +
                    ", parts=" + parts +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UploadDTO{" +
                "fileName='" + fileName + '\'' +
                '}';
    }
}
