package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.EducationJpaRepository;
import com.example.demo.repository.EducationMyBatisRepository;
import com.example.demo.repository.LikedMybatisRepository;
import com.example.demo.repository.ReviewMyBatisRepository;
import com.example.demo.vo.EducationVO;
import com.example.demo.vo.LikedVO;
import com.example.demo.vo.OpinionVO;

import lombok.Setter;

@Service
@Setter
public class EducationService  {

	@Autowired
	private EducationJpaRepository dao_JPA;
	
	@Autowired
	private EducationMyBatisRepository dao_MB;
	
	@Autowired
	private LikedMybatisRepository liked_MB;
	
	@Autowired
	private ReviewMyBatisRepository review_MB;

//MB (조회)
	// 기본 래코드수
	public int getTotalRecordEducation(HashMap<String, Object> map) {
		return dao_MB.getTotalRecordEducation(map);
	}
	// 기본 findAll
	public List<EducationVO> findAllEducation(HashMap<String, Object> map){
		System.out.println("map: "+map);
		return dao_MB.findAllEducation(map);
	}
	
	public EducationVO findByNoEducation(int eduNO) {
		return dao_MB.findByNoEducation(eduNO);
	}
	
// 좋아요	
	//교육 좋아요 추가
	public int insertEducationLiked(HashMap<String, Object> map) { 
		return liked_MB.insertEducationLiked(map);
	}
	// 교육좋아요 취소
	public int deleteEducationLiked(HashMap<String, Object> map) {
		return liked_MB.deleteEducationLiked(map);
	}
	// 교육 좋아요목록 조회
	public List<EducationVO> findLikedEducationByUserNo (int userno) {
		return LikedMybatisRepository.findLikedEducationByUserNo(userno);
	}
	// 좋아요한 버튼 세션 유지를 위한 조회
	public List<Integer> findLikedEducationNos(int userno) {
		return liked_MB.findLikedEducationNos(userno);
	}
// 리뷰
	// 교육 리뷰 조회
	public List<OpinionVO> findAllEducationReview (int eduNO){
		return review_MB.findAllEducationReview(eduNO);
	}
	public List<OpinionVO> findAllLectureReview (int lecNO){
		return review_MB.findAllLectureReview(lecNO);
	}
	// 교육 리뷰 등록
	public int insertEducationReview(HashMap<String, Object> map) { 
		return review_MB.insertEducationReview(map);
	}
	public int insertLectureReview(HashMap<String, Object> map) { 
		return review_MB.insertLectureReview(map);
	}
	
//JPA (추가, 수정, 삭제)
	
	public int getNextNoEducation() {
		return (int)dao_JPA.getNextNoEducation();
	}
	
	public void insertEducation(EducationVO e) {
		dao_JPA.save(e);
	}
	
	public void updateEducation(EducationVO e) {
		dao_JPA.save(e);
	}
	
	public void deleteEducation(int eduNO) {
		dao_JPA.deleteById(eduNO);
	}
	
	
}
