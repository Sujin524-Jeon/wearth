package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.vo.OrdersVO;

public interface OrderJpaRepository extends JpaRepository<OrdersVO, Integer> {
	
	public List<OrdersVO> findByUserNo(int userno);

}
