package com.pickfit.pickfit.oauth2.model.dto;

public class UserDTO {

    private String email; // 이메일
    private String name;  // 사용자 이름
    private String phoneNum; // 폰 번호

    public UserDTO(String email, String name, String phoneNum) {
        this.email = email;
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public UserDTO(String email, String name) {
        this.email = email;
        this.name = name;
        this.phoneNum = null;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
