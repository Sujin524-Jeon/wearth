package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.vo.OrdersDetailVO;

public interface OrdersDetailJpaRepository extends JpaRepository<OrdersDetailVO, Integer> {
	
	public List<OrdersDetailVO> findByOrdersNo(int ordersno);

}
