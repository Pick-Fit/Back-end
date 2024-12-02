package com.pickfit.pickfit.mypage.service;

import com.pickfit.pickfit.mypage.model.dto.MyPageDTO;
import com.pickfit.pickfit.mypage.model.entity.MyPageEntity;
import com.pickfit.pickfit.mypage.repository.MyPageRepository;
import org.springframework.stereotype.Service;

@Service
public class MyPageService {

    private final MyPageRepository myPageRepository;

    public MyPageService(MyPageRepository myPageRepository) {
        this.myPageRepository = myPageRepository;
    }

    public void createMyPage(MyPageDTO myPageDTO) {
        MyPageEntity entity = convertToEntity(myPageDTO);
        myPageRepository.save(entity);
    }

    public void updateMyPage(Integer id, MyPageDTO myPageDTO) {
        // ID로 엔터티 조회
        MyPageEntity entity = myPageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + id));

        // Null-safe 필드 업데이트
        if (myPageDTO.getEmail() != null) {
            entity.setEmail(myPageDTO.getEmail());
        }
        if (myPageDTO.getNickname() != null) {
            entity.setNickname(myPageDTO.getNickname());
        }
        if (myPageDTO.getUserName() != null) {
            entity.setUserName(myPageDTO.getUserName());
        }
        if (myPageDTO.getPhoneNum() != null) {
            entity.setPhoneNum(myPageDTO.getPhoneNum());
        }
        if (myPageDTO.getAddress() != null) {
            entity.setAddress(myPageDTO.getAddress());
        }
        if (myPageDTO.getRole() != null) {
            entity.setRole(myPageDTO.getRole());
        }

        // 엔터티 저장
        myPageRepository.save(entity);
    }

    public void deleteMyPage(Integer id) {
        if (!myPageRepository.existsById(id)) {
            throw new RuntimeException("사용자를 찾을 수 없습니다: " + id);
        }
        myPageRepository.deleteById(id);
    }

    private MyPageEntity convertToEntity(MyPageDTO myPageDTO) {
        MyPageEntity entity = new MyPageEntity();
        entity.setEmail(myPageDTO.getEmail());
        entity.setNickname(myPageDTO.getNickname());
        entity.setUserName(myPageDTO.getUserName());
        entity.setPhoneNum(myPageDTO.getPhoneNum());
        entity.setAddress(myPageDTO.getAddress());
        entity.setRole(myPageDTO.getRole());
        return entity;
    }
}
