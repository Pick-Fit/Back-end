package com.pickfit.pickfit.wishlist.repository;

import com.pickfit.pickfit.wishlist.entity.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistEntity, Integer> {
    List<WishlistEntity> findByUserEmail(String userEmail);
}
