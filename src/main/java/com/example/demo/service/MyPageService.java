package com.example.demo.service;

import java.util.List;
import com.example.demo.db.MypageDBManager;
import com.example.demo.vo.AddrVO;
import com.example.demo.vo.GoodsVO;
import com.example.demo.vo.OpinionVO;
import com.example.demo.vo.OrdersDetailGoodsVO;
import com.example.demo.vo.OrdersVO;
import com.example.demo.vo.PaymentVO;
import com.example.demo.vo.UsersVO;

public interface MyPageService {

	// 회원정보 수정
	public void update(UsersVO u);
	
	// 회원 탈퇴
	public void withdraw(UsersVO u);
	
	// 주문 목록 조회
	public List<OrdersVO> findOrdersByUserno(int userno);

	// 주문 조회
	public OrdersVO findOrderByOrdersNo(int ordersno);

	// 주문 상세 목록 조회
	public List<OrdersDetailGoodsVO> findOrdersDetailGoodsByOrdersNo(int ordersno);

	// 리뷰 작성
	public int insertReview(OpinionVO o);

	// 결제내역 조회
	public PaymentVO findPaymentByOrdersNo(int ordersno);

	// 쇼핑 취소
	public int deleteOrder(int ordersno);
	
	// 관심 상품 조회
	public List<GoodsVO> findLikedGoodsByUserno(int usersno);
	
	// 배송지 주소록 가져오기
	public List<AddrVO> findAddrByUserno(int userno);
}
