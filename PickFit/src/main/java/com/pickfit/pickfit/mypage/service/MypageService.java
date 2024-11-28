package com.pickfit.pickfit.mypage.service;

import com.pickfit.pickfit.mypage.model.dto.MypageDTO;
import com.pickfit.pickfit.mypage.respository.MypageRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MypageService {

    @Autowired
    private MypageRespository mypageRespository;

    public MypageDTO getUserByEmail(String email) {

    }

}
