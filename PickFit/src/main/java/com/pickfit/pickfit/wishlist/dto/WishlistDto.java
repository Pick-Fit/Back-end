package com.pickfit.pickfit.wishlist.dto;

public class WishlistDto {

    private String userEmail;  // 사용자 이메일
    private Long productId;    // 상품 ID

    // 기본 생성자
    public WishlistDto() {
    }

    // 모든 필드를 포함하는 생성자
    public WishlistDto(String userEmail, Long productId) {
        this.userEmail = userEmail;
        this.productId = productId;
    }

    // Getter & Setter
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
        return "WishlistDto{" +
                "userEmail='" + userEmail + '\'' +
                ", productId=" + productId +
                '}';
    }
}
