package com.pickfit.pickfit.mypage.repository;

import com.pickfit.pickfit.mypage.model.entity.MyPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyPageRepository extends JpaRepository<MyPageEntity, Integer> {
}
