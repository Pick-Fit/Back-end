package com.pickfit.pickfit.oauth2.model.dto;

public class UserDTO {

    private String email; // 이메일
    private String name;  // 사용자 이름
    private String phoneNum; // 폰 번호
    private String profile;

    public UserDTO() {
    }

    public UserDTO(String email, String name, String phoneNum, String profile) {
        this.email = email;
        this.name = name;
        this.phoneNum = phoneNum;
        this.profile = profile;
    }

    public UserDTO(String email, String name) {
        this.email = email;
        this.name = name;
        this.phoneNum = null;
        this.profile = null;
    }

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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "profile='" + profile + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
