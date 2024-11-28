package com.pickfit.pickfit.mypage.model.dto;

public class MypageDTO {

    private String email;
    private String nickname;
    private String userName;
    private String phoneNum;
    private String address;
    private String role;

    public MypageDTO() {}

    public MypageDTO(String email, String nickname, String userName, String phoneNum, String address, String role) {
        this.email = email;
        this.nickname = nickname;
        this.userName = userName;
        this.phoneNum = phoneNum;
        this.address = address;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "MypageDTO{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", address='" + address + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
