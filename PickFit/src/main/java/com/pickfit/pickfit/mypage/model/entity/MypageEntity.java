package com.pickfit.pickfit.mypage.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "")
public class MypageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "MyPage_email")
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
}
