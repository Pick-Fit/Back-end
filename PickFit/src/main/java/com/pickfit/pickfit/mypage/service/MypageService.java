package com.pickfit.pickfit.mypage.service;

import com.pickfit.pickfit.mypage.model.dto.MyPageDTO;
import com.pickfit.pickfit.mypage.model.entity.MyPageEntity;
import com.pickfit.pickfit.mypage.respository.MyPageRepository;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyPageService {

    @Autowired
    private MyPageRepository myPageRepository;

    public void createMyPage(MyPageDTO myPageDTO) {
        MyPageEntity entity = new MyPageEntity(myPageDTO.getEmail(), myPageDTO.getNickname(),
                myPageDTO.getUserName(), myPageDTO.getPhoneNum(), myPageDTO.getAddress(),
                myPageDTO.getRole());
        myPageRepository.save(entity);
    }
    @Transactional
    public void updateMyPage(Integer id, MyPageDTO myPageDTO) {
        Optional<MyPageEntity> optional = myPageRepository.findById(id);
        if (optional.isPresent()) {
            MyPageEntity entity = optional.get();
            entity.setEmail(myPageDTO.getEmail());
            entity.setNickname(myPageDTO.getNickname());
            entity.setUserName(myPageDTO.getUserName());
            entity.setPhoneNumber(myPageDTO.getPhoneNum());
            entity.setAddress(myPageDTO.getAddress());
            entity.setRole(myPageDTO.getRole());
            myPageRepository.save(entity);
        }else {
            throw new RuntimeException("아이디를 찾을 수 없습니다" + id);
        }
    }

    public void deleteMyPage(Integer id) {
        myPageRepository.deleteById(id);
    }



}