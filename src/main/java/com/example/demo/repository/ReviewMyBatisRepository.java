package com.example.demo.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.ReviewDBManager;
import com.example.demo.vo.OpinionVO;

@Repository
public class ReviewMyBatisRepository {

	public int insertReview(OpinionVO o) {
		return ReviewDBManager.insertReview(o);
	}
	
// 강연, 교육 후기(review)	
	//후기 조회
	public List<OpinionVO> findAllEducationReview(int eduNO){
		return ReviewDBManager.findAllEducationReview(eduNO);
	}
	public List<OpinionVO> findAllLectureReview(int lecNO){
		return ReviewDBManager.findAllLectureReview(lecNO);
	}
	//후기 등록
	public int insertEducationReview( HashMap<String, Object> map) {
		return ReviewDBManager.insertEducationReview(map);
	}
	public int insertLectureReview( HashMap<String, Object> map) {
		return ReviewDBManager.insertLectureReview(map);
	}
}

