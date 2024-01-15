package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.MypageDBManager;
import com.example.demo.vo.GoodsVO;
import com.example.demo.vo.OrdersDetailGoodsVO;
import com.example.demo.vo.OrdersDetailVO;
import com.example.demo.vo.OrdersVO;
@Repository
public class MypageMybatisRepository {
	
	// 주문 목록 조회
	public List<OrdersVO> findByUserNo(int userno) {
		return MypageDBManager.findByUserNo(userno);
	}
	// 주문 조회
	public OrdersVO findOrderByOrdersNo(int ordersno) {
		return MypageDBManager.findOrdersByOrdersNo(ordersno); 
	}
	// 주문 상세 목록 조회
	public List<OrdersDetailGoodsVO> findOrdersDetailGoodsByOrdersNo(int ordersno) {
		return MypageDBManager.findListOrdersDetailGoodsByOrdersNo(ordersno);
	}
	
	// 주문취소
	public int deleteOrder(int ordersno) {
		return MypageDBManager.deleteOrder(ordersno);
	}
	
	// 관심 상품 조회
	public List<GoodsVO> findLikedGoodsByUserno(int usersno){
		return MypageDBManager.findLikedGoodsByUserno(usersno);
	}
}
