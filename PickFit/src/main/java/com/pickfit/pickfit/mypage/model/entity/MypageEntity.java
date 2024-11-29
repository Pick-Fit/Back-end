package com.pickfit.pickfit.mypage.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mypage")
public class MyPageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "MyPage_email")
    private String email;
    @Column(nullable = true)
    private String nickname;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(nullable = true)
    private String phoneNum;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, length = 20)
    private String role;

    public MyPageEntity() {}

    public MyPageEntity(String email, String nickname, String userName, String phoneNum, String address, String role) {
        this.email = email;
        this.nickname = nickname;
        this.userName = userName;
        this.phoneNum = phoneNum;
        this.address = address;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "MyPageEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", address='" + address + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}