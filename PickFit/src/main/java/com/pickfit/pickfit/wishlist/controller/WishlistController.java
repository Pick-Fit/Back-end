package com.pickfit.pickfit.wishlist.controller;

import com.pickfit.pickfit.wishlist.dto.WishlistDto;  // Wishlist 관련 DTO 클래스
import com.pickfit.pickfit.wishlist.entity.WishlistEntity;  // Wishlist Entity 클래스
import com.pickfit.pickfit.wishlist.service.WishlistService;  // Wishlist 관련 비즈니스 로직을 처리하는 서비스 클래스
import org.springframework.beans.factory.annotation.Autowired;  // Spring에서 의존성 주입을 위한 애너테이션
import org.springframework.http.ResponseEntity;  // HTTP 응답을 생성하기 위한 클래스
import org.springframework.web.bind.annotation.*;  // REST API에서 사용할 애너테이션 (GET, POST, DELETE 등)
import org.springframework.http.HttpStatus;  // HTTP 상태 코드를 관리하는 클래스
import java.util.List;
import java.util.HashMap;  // HashMap을 사용하여 응답 메시지 및 데이터를 저장
import java.util.Map;  // Map 인터페이스를 사용하여 키-값 쌍으로 데이터를 저장

@RestController  // 이 클래스가 RESTful 웹 서비스를 위한 컨트롤러임을 나타냄
@RequestMapping("/api/wishlist")  // 기본 URL을 "/api/wishlist"로 설정
public class WishlistController {

    private final WishlistService wishlistService;  // WishlistService를 사용하기 위한 필드 선언

    @Autowired  // Spring에서 WishlistService를 자동으로 주입
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;  // 생성자에서 wishlistService를 초기화
    }

    // 특정 사용자의 위시리스트 조회
    @GetMapping("/{userEmail}")  // HTTP GET 요청을 처리. URL은 "/api/wishlist/{userEmail}"
    public ResponseEntity<Map<String, Object>> getWishlist(@PathVariable String userEmail) {  // userId -> userEmail로 변경
        Map<String, Object> response = new HashMap<>();  // 응답 데이터를 저장할 Map 생성
        try {
            // 사용자 이메일로 위시리스트를 조회
            List<WishlistEntity> wishlist = wishlistService.getWishlist(userEmail);
            response.put("data", wishlist);  // 응답에 "data"라는 키로 위시리스트 데이터를 추가
            return ResponseEntity.ok(response);  // 200 OK 응답을 반환하고, 데이터 포함
        } catch (Exception e) {
            response.put("error", "위시리스트를 불러오는 데 실패했습니다.");  // 예외 발생 시 오류 메시지 추가
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);  // 500 서버 오류와 함께 응답
        }
    }

    // 위시리스트에 상품 추가
    @PostMapping  // HTTP POST 요청을 처리. URL은 "/api/wishlist"
    public ResponseEntity<Map<String, String>> addToWishlist(@RequestBody WishlistDto request) {  // DTO로 요청받음
        Map<String, String> response = new HashMap<>();  // 응답 데이터를 저장할 Map 생성
        try {
            // 요청에서 userEmail과 productId가 누락되지 않았는지 체크
            if (request.getUserEmail() == null || request.getProductId() == null) {
                response.put("error", "필수 값이 누락되었습니다.");  // 필수 값이 없으면 오류 메시지 추가
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);  // 400 Bad Request 반환
            }

            // 위시리스트에 상품을 추가
            WishlistEntity wishlist = wishlistService.addToWishlist(request.getUserEmail(), request.getProductId());
            response.put("message", "상품이 위시리스트에 추가되었습니다.");  // 성공 메시지 추가
            return ResponseEntity.ok(response);  // 200 OK 응답을 반환하고, 메시지 포함
        } catch (Exception e) {
            response.put("error", "위시리스트에 상품을 추가하는 데 실패했습니다.");  // 예외 발생 시 오류 메시지 추가
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);  // 500 서버 오류 반환
        }
    }

    // 위시리스트에서 상품 삭제
    @DeleteMapping("/{wishlistId}")  // HTTP DELETE 요청을 처리. URL은 "/api/wishlist/{wishlistId}"
    public ResponseEntity<Map<String, String>> removeFromWishlist(@PathVariable Integer wishlistId) {  // wishlistId로 상품 삭제
        Map<String, String> response = new HashMap<>();  // 응답 데이터를 저장할 Map 생성
        try {
            // 위시리스트에서 상품 삭제
            wishlistService.removeFromWishlist(wishlistId);  // wishlistId를 사용하여 위시리스트 항목 삭제
            response.put("message", "상품이 위시리스트에서 삭제되었습니다.");  // 성공 메시지 추가
            return ResponseEntity.noContent().build();  // 204 No Content 응답을 반환 (본문 없이)
        } catch (Exception e) {
            response.put("error", "위시리스트에서 상품을 삭제하는 데 실패했습니다.");  // 예외 발생 시 오류 메시지 추가
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);  // 500 서버 오류 반환
        }
    }
}
