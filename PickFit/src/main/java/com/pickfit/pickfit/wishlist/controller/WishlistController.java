package com.pickfit.pickfit.wishlist.controller;

import com.pickfit.pickfit.wishlist.dto.WishlistDto;
import com.pickfit.pickfit.wishlist.entity.WishlistEntity;
import com.pickfit.pickfit.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<Map<String, Object>> getWishlist(@PathVariable String userEmail) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<WishlistEntity> wishlist = wishlistService.getWishlist(userEmail);
            response.put("data", wishlist);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "위시리스트를 불러오는 데 실패했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addToWishlist(@RequestBody WishlistDto request) {
        Map<String, String> response = new HashMap<>();
        try {
            if (request.getUserEmail() == null || request.getProductId() == null ||
                    request.getImageUrl() == null || request.getTitle() == null) {
                response.put("error", "필수 값이 누락되었습니다.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            wishlistService.addToWishlist(request);
            response.put("message", "상품이 위시리스트에 추가되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "위시리스트에 상품을 추가하는 데 실패했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{wishlistId}")
    public ResponseEntity<Map<String, String>> removeFromWishlist(@PathVariable Integer wishlistId) {
        Map<String, String> response = new HashMap<>();
        try {
            wishlistService.removeFromWishlist(wishlistId);
            response.put("message", "상품이 위시리스트에서 삭제되었습니다.");
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            response.put("error", "위시리스트에서 상품을 삭제하는 데 실패했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
