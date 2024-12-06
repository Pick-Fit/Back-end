package com.pickfit.pickfit.multipartupload.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="user_images")
public class UploadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadDate;

    public UploadEntity() {

    }

    public UploadEntity(String email, String fileName, String url, LocalDateTime uploadDate) {
        this.email = email;
        this.fileName = fileName;
        this.url = url;
        this.uploadDate = uploadDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "UploadEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", uploadDate=" + uploadDate +
                '}';
    }
}
