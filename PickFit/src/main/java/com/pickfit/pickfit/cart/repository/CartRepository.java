package com.pickfit.pickfit.cart.repository;

import com.pickfit.pickfit.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository <CartItem, Long>{
}
