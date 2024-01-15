package com.example.demo.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.BoardDBManager;
import com.example.demo.db.CommentsDBManager;
import com.example.demo.db.DBManager;
import com.example.demo.db.SchoolDBManager;
import com.example.demo.vo.BoardVO;
import com.example.demo.vo.CommentsVO;
import com.example.demo.vo.EducationVO;

@Repository
public class CommentsMyBatisRepository {


	public List<CommentsVO> findAll(int boardno){
		return CommentsDBManager.findAll(boardno);
	}

}
