package com.pickfit.pickfit.wishlist.service;

import com.pickfit.pickfit.wishlist.dto.WishlistDto;
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

    public List<WishlistEntity> getWishlist(String userEmail) {
        try {
            return wishlistRepository.findByUserEmail(userEmail);
        } catch (Exception e) {
            throw new RuntimeException("위시리스트를 가져오는 중 오류가 발생했습니다.");
        }
    }

    public WishlistEntity addToWishlist(WishlistDto wishlistDto) {
        try {
            WishlistEntity wishlist = new WishlistEntity();
            wishlist.setUserEmail(wishlistDto.getUserEmail());
            wishlist.setImageUrl(wishlistDto.getImageUrl());
            wishlist.setUserName(wishlistDto.getUserName());
            wishlist.setPrice(wishlistDto.getPrice());
            wishlist.setProductId(wishlistDto.getProductId());
            wishlist.setTitle(wishlistDto.getTitle());

            return wishlistRepository.save(wishlist);
        } catch (Exception e) {
            throw new RuntimeException("위시리스트에 상품을 추가하는 중 오류가 발생했습니다.");
        }
    }

    public void removeFromWishlist(Integer wishlistId) {
        try {
            wishlistRepository.deleteById(wishlistId);
        } catch (Exception e) {
            throw new RuntimeException("위시리스트에서 상품을 삭제하는 중 오류가 발생했습니다.");
        }
    }
}
