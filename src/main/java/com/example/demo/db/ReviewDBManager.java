package com.example.demo.db;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.example.demo.vo.OpinionVO;
 
public class ReviewDBManager extends DBManager {
	public static int insertReview(OpinionVO review) {
		int re = -1 ;
		System.out.println("reviewDBManager op : "+review);
		SqlSession session = sqlSessionFactory.openSession(true);
		re = session.insert("review.insertReview", review);
		session.close();
		return re;
	}

	// 강연, 교육 후기(review)
		//후기 조회
		public static List<OpinionVO> findAllEducationReview (int eduNO){
			System.out.println("교육리뷰조회 eduNO 파라미터값 ( dbManager) :"+ eduNO);
			SqlSession session = sqlSessionFactory.openSession();
			List<OpinionVO> list = session.selectList("review.findAllEducationReview",eduNO);
			session.close();
			return list;
		}
		public static List<OpinionVO> findAllLectureReview (int lecNO){
			SqlSession session = sqlSessionFactory.openSession();
			List<OpinionVO> list = session.selectList("review.findAllLectureReview",lecNO);
			session.close();
			return list;
		}
		//후기 등록
		public static int insertEducationReview( HashMap<String, Object> map) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession(true);
			re = session.insert("review.insertEducationReview",map);
			session.close();
			return re;
		}
		public static int insertLectureReview( HashMap<String, Object> map) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession(true);
			re = session.insert("review.insertLectureReview",map);
			session.close();
			return re;
		}

}
