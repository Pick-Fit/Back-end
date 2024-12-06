package com.pickfit.pickfit.wishlist.service;

import com.pickfit.pickfit.wishlist.entity.WishlistEntity;
import com.pickfit.pickfit.wishlist.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    // 특정 사용자의 위시리스트 조회
    public List<WishlistEntity> getWishlist(String userEmail) {
        try {
            // userEmail을 이용해 위시리스트 데이터를 찾기
            return wishlistRepository.findByUserEmail(userEmail);  // userEmail로 위시리스트 조회
        } catch (Exception e) {
            throw new RuntimeException("위시리스트를 가져오는 중 오류가 발생했습니다.");  // 예외 처리 (한글 메시지)
        }
    }

    // 위시리스트에 상품 추가
    public WishlistEntity addToWishlist(String userEmail, Long productId) {
        try {
            // 새로운 WishlistEntity 객체를 생성하여 사용자 이메일과 상품 ID 설정
            WishlistEntity wishlist = new WishlistEntity();
            wishlist.setUserEmail(userEmail);
            wishlist.setProductId(productId);

            // 데이터베이스에 새로운 위시리스트를 저장
            return wishlistRepository.save(wishlist);
        } catch (Exception e) {
            throw new RuntimeException("위시리스트에 상품을 추가하는 중 오류가 발생했습니다.");
        }
    }

    // 위시리스트에서 상품 삭제
    public void removeFromWishlist(Integer wishlistId) {  // wishlistId는 Integer 타입으로 변경
        try {
            // 주어진 wishlistId로 위시리스트 항목을 삭제
            wishlistRepository.deleteById(wishlistId);
        } catch (Exception e) {
            throw new RuntimeException("위시리스트에서 상품을 삭제하는 중 오류가 발생했습니다.");
        }
    }
}
