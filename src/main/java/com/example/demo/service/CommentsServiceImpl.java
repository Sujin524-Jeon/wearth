package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.CommentsJpaRepository;
import com.example.demo.repository.CommentsMyBatisRepository;
import com.example.demo.vo.CommentsVO;

import lombok.Setter;

@Service
@Setter
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentsJpaRepository cJPA;
	
	@Autowired
	private CommentsMyBatisRepository cMB;

	@Override
	public List<CommentsVO> findAll(int boardno) {
		System.out.println("process : CommentsServiceImp--------------------------");
		return cMB.findAll(boardno);
	}

	@Override
	public int getNextNo() {
		return cJPA.getNextNo();
	}

	@Override
	public void insert(CommentsVO c) {
		System.out.println("CommentsServiceImpl-------------------------");
		cJPA.insert(c);
	}
	

}
