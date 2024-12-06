package com.pickfit.pickfit.wishlist.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wishlist")  // "wishlist" 테이블과 매핑
public class WishlistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ID를 자동 생성
    private Integer id;  // id를 Integer로 변경

    @Column(name = "user_email", nullable = false)  // "user_email" 컬럼
    private String userEmail;  // 사용자 이메일

    @Column(name = "product_id", nullable = false)  // "product_id" 컬럼
    private Long productId;  // 상품 ID

    // 기본 생성자
    public WishlistEntity() {
    }

    // 모든 필드를 포함하는 생성자
    public WishlistEntity(Integer id, String userEmail, Long productId) {
        this.id = id;
        this.userEmail = userEmail;
        this.productId = productId;
    }

    // Getter & Setter
    public Integer getId() {
        return id;  // Integer로 변경된 ID 반환
    }

    public void setId(Integer id) {
        this.id = id;  // Integer로 변경된 ID 설정
    }

    public String getUserEmail() {
        return userEmail;  // 사용자 이메일 반환
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;  // 사용자 이메일 설정
    }

    public Long getProductId() {
        return productId;  // 상품 ID 반환
    }

    public void setProductId(Long productId) {
        this.productId = productId;  // 상품 ID 설정
    }

    // toString 메서드
    @Override
    public String toString() {
        return "WishlistEntity{" +
                "id=" + id +
                ", userEmail='" + userEmail + '\'' +
                ", productId=" + productId +
                '}';
    }
}
