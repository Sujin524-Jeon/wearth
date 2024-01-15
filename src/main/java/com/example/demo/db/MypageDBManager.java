package com.example.demo.db;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.example.demo.vo.GoodsVO;
import com.example.demo.vo.OrdersDetailGoodsVO;
import com.example.demo.vo.OrdersDetailVO;
import com.example.demo.vo.OrdersVO;

public class MypageDBManager extends DBManager {
	public static List<OrdersVO> findByUserNo(int userno) {
		SqlSession session = sqlSessionFactory.openSession();
		List<OrdersVO> list = session.selectList("orders.findListOrders", userno);
		session.close();
		return list;
	}
	public static OrdersVO findOrdersByOrdersNo(int ordersno) {
		SqlSession session = sqlSessionFactory.openSession();
		OrdersVO order = session.selectOne("orders.findOrdersByOrdersNo", ordersno);
		session.close();
		return order;
	}

	public static List<OrdersDetailGoodsVO> findListOrdersDetailGoodsByOrdersNo(int ordersno) {
		SqlSession session = sqlSessionFactory.openSession();
		List<OrdersDetailGoodsVO> list = session.selectList("orders.findListOrdersDetailGoods", ordersno);
		session.close();
		return list;
	}
	
	public static List<GoodsVO> findLikedGoodsByUserno(int usersno){
		SqlSession session = sqlSessionFactory.openSession();
		List<GoodsVO> list = session.selectList("goods.likedGoods", usersno);
		session.close();
		return list;
	}
	
	public static int deleteOrder(int ordersno) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = session.update("orders.deleteOrder", ordersno);
		session.close();
		return re;
	}

}