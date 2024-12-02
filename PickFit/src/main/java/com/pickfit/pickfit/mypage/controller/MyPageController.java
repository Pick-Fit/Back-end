package com.pickfit.pickfit.mypage.controller;

import com.pickfit.pickfit.mypage.model.dto.MyPageDTO;
import com.pickfit.pickfit.mypage.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class MyPageController {

    private final MyPageService myPageService;

    @Autowired
    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @PostMapping("/users")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody MyPageDTO myPageDTO) {
        myPageService.createMyPage(myPageDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "success");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Map<String, String>> updateUser(@PathVariable Integer id, @RequestBody MyPageDTO myPageDTO) {
        try {
            myPageService.updateMyPage(id, myPageDTO);

            // JSON 응답 생성
            Map<String, String> response = new HashMap<>();
            response.put("message", "update success");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다: " + id);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Integer id) {
        try {
            myPageService.deleteMyPage(id);

            // JSON 응답 생성
            Map<String, String> response = new HashMap<>();
            response.put("message", "delete success");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다: " + id);
        }
    }
}
