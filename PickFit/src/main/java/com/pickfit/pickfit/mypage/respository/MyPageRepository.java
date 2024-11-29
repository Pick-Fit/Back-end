package com.pickfit.pickfit.mypage.respository;

import com.pickfit.pickfit.mypage.model.entity.MypageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyPageRepository extends JpaRepository<MyPageEntity, Integer> {
}
