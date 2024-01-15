package com.example.demo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.CommentsService;
import com.example.demo.vo.CommentsVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@Setter
public class CommentsController {
	
	@Autowired
	private CommentsService cs;
	
	@RequestMapping("/comments/insert")
	@ResponseBody
	public String insert(String boardno, String userno, String c_content) {
		System.out.println("insert Comment Controller---------------------------------");
		CommentsVO c = new CommentsVO(); 
		c.setCommentno(cs.getNextNo());
		c.setBoardno(Integer.parseInt(boardno)); 
		c.setUserno(Integer.parseInt(userno));
		System.out.println("c_content1: "+c_content);
		
		c.setC_content(c_content);
		System.out.println("c_content2: "+c.getC_content());
		cs.insert(c);
		
		System.out.println("댓글 insert 완료");
		return c.getCommentno()+"번 댓글 삽입 완료";
	}
	
	@PostMapping("/comments/list")
	@ResponseBody
	public List<CommentsVO> list(String boardno) {
		System.out.println("list Comment Controller---------------------------------");
		System.out.println(boardno+"번 게시글의 댓글리스트:"+cs.findAll(Integer.parseInt(boardno)));
		return cs.findAll(Integer.parseInt(boardno));
	}
	
	
}
