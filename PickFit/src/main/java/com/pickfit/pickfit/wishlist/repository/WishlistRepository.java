package com.pickfit.pickfit.wishlist.repository;

import com.pickfit.pickfit.wishlist.entity.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistEntity, Integer> {  // Long -> Integer로 변경

    // 특정 사용자의 위시리스트 조회
    List<WishlistEntity> findByUserEmail(String userEmail);  // userId -> userEmail로 변경
}
