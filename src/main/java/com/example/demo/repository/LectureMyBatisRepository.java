package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.db.SchoolDBManager;
import com.example.demo.vo.EducationVO;
import com.example.demo.vo.LectureVO;

@Repository
public class LectureMyBatisRepository {
	// 강의 전체목록 반환 
	public List<LectureVO> findAllLecture() {
		return SchoolDBManager.findAllLecture();
	}
	
	public LectureVO findByNoLecture(int lecNO){
		return SchoolDBManager.findByNoLecture(lecNO);
	}
	
	public int insertLecture(LectureVO l) {
		return SchoolDBManager.insertLecture(l);
	}
	
}
