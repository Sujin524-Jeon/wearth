package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.AddrJpaRepository;
import com.example.demo.repository.MypageMybatisRepository;
import com.example.demo.repository.PaymentMybatisRepository;
import com.example.demo.repository.ReviewMyBatisRepository;
import com.example.demo.vo.AddrVO;
import com.example.demo.vo.GoodsVO;
import com.example.demo.vo.OpinionVO;
import com.example.demo.vo.OrdersDetailGoodsVO;
import com.example.demo.vo.OrdersVO;
import com.example.demo.vo.PaymentVO;
import com.example.demo.vo.UsersVO;

import lombok.Setter;


@Service
@Setter
public class MyPageServiceImpl implements MyPageService {
	@Autowired
	private MypageMybatisRepository ordersRepository;
	@Autowired
	private PaymentMybatisRepository paymentRepository;
	@Autowired
	private ReviewMyBatisRepository reviewRepository;
	@Autowired
	private AddrJpaRepository addrRepository;
	
	// 회원 정보 수정
	@Override
	public void update(UsersVO u) {
		
	}

	// 회원 탈퇴
	@Override
	public void withdraw(UsersVO u) {

	}
	// 주문 취소
	@Override
	public int deleteOrder(int ordersno) {
		return ordersRepository.deleteOrder(ordersno);
	}

	@Override
	public List<OrdersVO> findOrdersByUserno(int userno) {
		return ordersRepository.findByUserNo(userno);
	}

	@Override
	public OrdersVO findOrderByOrdersNo(int ordersno) {
		return ordersRepository.findOrderByOrdersNo(ordersno);
	}

	@Override
	public List<OrdersDetailGoodsVO> findOrdersDetailGoodsByOrdersNo(int ordersno) {
		return ordersRepository.findOrdersDetailGoodsByOrdersNo(ordersno);
	}

	@Override
	public int insertReview(OpinionVO o) {
		return reviewRepository.insertReview(o);
	}

	@Override
	public PaymentVO findPaymentByOrdersNo(int ordersno) {
		return paymentRepository.findByOrdersNo(ordersno);
	}

	@Override
	public List<GoodsVO> findLikedGoodsByUserno(int usersno) {
		List<GoodsVO> list = ordersRepository.findLikedGoodsByUserno(usersno);
		System.out.println(list);
		return list;
	}

	@Override
	public List<AddrVO> findAddrByUserno(int userno) {
		return addrRepository.findByUserNo(userno);
	}

}
