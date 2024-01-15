package com.example.demo.service;

import java.util.List;

import com.example.demo.vo.CommentsVO;

public interface CommentsService {

	//Board 댓글 목록 불러오기 - MB
	public List<CommentsVO> findAll(int boardno);
	
	//-----------------------------------------------------
	
	//댓글 등록 위한 다음 댓글 번호 불러오기 - JPA
	public int getNextNo();

	//댓글 등록
	public void insert(CommentsVO c);
}
