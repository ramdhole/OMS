package com.oms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oms.entity.Cart;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Integer> {

	public Optional<Cart> findByCustId(int custId);
	
}
