package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.vo.CouponVO;

public interface CouponJpaRepository extends JpaRepository<CouponVO, Integer> {
		
	public List<CouponVO> findByUserNo(int userno);

}
