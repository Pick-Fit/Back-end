package com.pickfit.pickfit.mypage.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "")
public class MypageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   @Column(name = "MyPage_email")
    private String email;
    private String nickname;
    private String userName;
    private String phoneNumber;
    private String address;
    private String role;

    public MypageEntity() {}

    public MypageEntity(String email, String nickname, String userName, String phoneNumber, String address, String role) {
        this.email = email;
        this.nickname = nickname;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    public java.lang.Integer getId() {
        return id;
    }

    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    public java.lang.String getNickname() {
        return nickname;
    }

    public void setNickname(java.lang.String nickname) {
        this.nickname = nickname;
    }

    public java.lang.String getUserName() {
        return userName;
    }

    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    public java.lang.String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(java.lang.String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public java.lang.String getAddress() {
        return address;
    }

    public void setAddress(java.lang.String address) {
        this.address = address;
    }

    public java.lang.String getRole() {
        return role;
    }

    public void setRole(java.lang.String role) {
        this.role = role;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "MypageEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
