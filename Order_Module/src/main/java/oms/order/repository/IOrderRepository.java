package com.orders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orders.entity.Orders;


@Repository
public interface IOrderRepository extends JpaRepository<Orders, Integer> {
	
	public List<Orders> findOrderByCustId(int customerId);
	

}

