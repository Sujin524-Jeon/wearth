package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.module.FindException;
import java.util.HashMap;

import org.apache.ibatis.io.ResolverUtil.IsA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.naming.SelfNaming;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.BoardService;
import com.example.demo.service.CommentsService;
import com.example.demo.service.FilesService;
import com.example.demo.service.LikedService;
import com.example.demo.vo.BoardVO;
import com.example.demo.vo.FilesVO;
import com.example.demo.vo.LikedVO;
import com.example.demo.vo.UsersVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class LikedController {
	
	@Autowired
	private LikedService ls;
	
	//좋아요 추가
	@GetMapping("/liked/insertOrDelete/{boardno}")
	@ResponseBody
	public String insertOrDeleteLiked(@PathVariable("boardno") int boardno, HttpSession session) {
		System.out.println("LikedInsert Controller---------------------------------");
		System.out.println("좋아요 누른 게시글 번호: "+boardno);
		//현재 로그인 한 사용자 정보
		UsersVO u = (UsersVO) session.getAttribute("u");
		int userno = u.getUserno();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("boardno", boardno); map.put("userno", userno);
		int cnt = ls.checkLiked(map);
		System.out.println("checkLiked:"+cnt);
		String result = "";
		if(cnt==0) {
			System.out.println("현재 좋아요 상태: false");
			LikedVO l = new LikedVO();
			l.setLikedNo(ls.getNextNo()); l.setUserNo(userno); l.setBoardNo(boardno);
			ls.insert(l);
			System.out.println(userno+"님이"+boardno+"게시글 좋아요 등록 완료 ");
			result = "true";
		}else {
			System.out.println("현재 좋아요 상태: true");
			ls.delete(boardno, userno);
			System.out.println("좋아요 취소 성공");
			result = "false";
		}
		return result;
	}
	
}
