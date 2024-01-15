package com.example.demo.db;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.example.demo.vo.OrdersDetailGoodsVO;
import com.example.demo.vo.OrdersDetailVO;
import com.example.demo.vo.OrdersVO;
import com.example.demo.vo.PaymentVO;

public class PaymentDBManager extends DBManager {
	public static PaymentVO findByOrdersNo(int ordersno) {
		SqlSession session = sqlSessionFactory.openSession();
		PaymentVO payment = session.selectOne("payment.findPayment", ordersno);
		session.close();
		return payment;
	}


}