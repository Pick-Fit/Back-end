package com.pickfit.pickfit.wishlist.service;

import com.pickfit.pickfit.wishlist.dto.WishlistDto;
import com.pickfit.pickfit.wishlist.entity.WishlistEntity;
import com.pickfit.pickfit.wishlist.repository.WishlistRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<WishlistEntity> getWishlist(String userEmail) {
        if (userEmail == null || userEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 사용자 이메일입니다.");
        }

        return wishlistRepository.findByUserEmail(userEmail);
    }

    public WishlistEntity addToWishlist(WishlistDto wishlistDto) {
        if (wishlistDto == null) {
            throw new IllegalArgumentException("위시리스트 요청 데이터가 비어 있습니다.");
        }

        try {
            // 유효성 검사
            if (wishlistDto.getUserEmail() == null || wishlistDto.getUserEmail().isEmpty()) {
                throw new IllegalArgumentException("유효하지 않은 이메일입니다.");
            }
            if (wishlistDto.getProductId() == null) {
                throw new IllegalArgumentException("상품 ID가 누락되었습니다.");
            }

            // 위시리스트 생성 및 저장
            WishlistEntity wishlist = new WishlistEntity();
            wishlist.setUserEmail(wishlistDto.getUserEmail());
            wishlist.setImageUrl(wishlistDto.getImageUrl());
            wishlist.setUserName(wishlistDto.getUserName());
            wishlist.setPrice(wishlistDto.getPrice());
            wishlist.setProductId(wishlistDto.getProductId());
            wishlist.setTitle(wishlistDto.getTitle());

            return wishlistRepository.save(wishlist);
        } catch (DataIntegrityViolationException e) {
            // 데이터베이스 제약 조건 위반 (예: 중복된 항목)
            throw new IllegalStateException("위시리스트 데이터베이스 제약 조건 위반: " + e.getMessage(), e);
        } catch (Exception e) {
            // 그 외의 오류
            throw new RuntimeException("위시리스트에 상품을 추가하는 중 시스템 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }


    public boolean softDeleteProduct(String userEmail, Long productId) {
        Optional<WishlistEntity> optionalProduct = wishlistRepository.findByProductIdAndUserEmail(productId, userEmail);

        if (optionalProduct.isEmpty()) {
            return false; // 제품이 존재하지 않음
        }

        WishlistEntity wishlistEntity = optionalProduct.get();

        // 상태 확인
        if (wishlistEntity.isDeleted()) {
            return false; // 이미 삭제된 데이터
        }

        // 상태 변경
        wishlistEntity.setDeleted(true);
        wishlistRepository.save(wishlistEntity);

        return true;
    }


}
