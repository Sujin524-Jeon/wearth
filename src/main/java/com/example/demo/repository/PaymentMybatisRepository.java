package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.MypageDBManager;
import com.example.demo.db.PaymentDBManager;
import com.example.demo.vo.OrdersDetailGoodsVO;
import com.example.demo.vo.OrdersDetailVO;
import com.example.demo.vo.OrdersVO;
import com.example.demo.vo.PaymentVO;
@Repository
public class PaymentMybatisRepository {

	// 결제내역 조회
	public static PaymentVO findByOrdersNo(int ordersno) {
		return PaymentDBManager.findByOrdersNo(ordersno); 
	}
}
