package com.example.demo.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.BoardDBManager;
import com.example.demo.db.VolunteerDBManager;
import com.example.demo.vo.VolunteerVO;

@Repository
public class VolunteerMyBatisRepository {
	
	public List<VolunteerVO> findAll(HashMap<String, Object> map){
		return VolunteerDBManager.findAll(map);
	}
	
	public int getTotalRecord() {
		return VolunteerDBManager.getTotalRecord();
	}

	public VolunteerVO findByVolunteerNo(int volunteerno) {
		return VolunteerDBManager.findByVolunteerNo(volunteerno);
	}
}
