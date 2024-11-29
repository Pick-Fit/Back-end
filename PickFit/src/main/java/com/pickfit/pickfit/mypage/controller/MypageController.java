package com.pickfit.pickfit.mypage.controller;

import com.pickfit.pickfit.mypage.model.dto.MyPageDTO;
import com.pickfit.pickfit.mypage.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/user")
public class MyPageController {

    @Autowired
    private MyPageService myPageService;

    @PostMapping("/create")
    public String createUser(@RequestBody MyPageDTO myPageDTO) {
        myPageService.createMyPage( myPageDTO );
        return "success";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable Integer id, @RequestBody MyPageDTO myPageDTO) {
        try {
            myPageService.updateMyPage(id, myPageDTO);
            return "update success";
        } catch ( RuntimeException e) {
            return "update fail";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        try {
            myPageService.deleteMyPage(id);
            return "delete success";
        } catch ( RuntimeException e) {
            return "delete fail";
        }

    }
}