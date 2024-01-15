package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.UserInfoService;
import com.example.demo.service.lectureService;
import com.example.demo.vo.EducationVO;
import com.example.demo.vo.LectureVO;
import com.example.demo.vo.UsersVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LectureController {
	
	@Autowired
	private lectureService ls;
	//LectureMyBatisRepository, LectureJpaRepository
	
	@Autowired
	private UserInfoService us;
	
	//list
	@GetMapping(value={"/school/lecture/list", "/school/lecture/list/{id}"})
	public void list(Model model,
			HttpServletRequest request
			) {
		HttpSession session = request.getSession();
	      UsersVO u = (UsersVO)session.getAttribute("u");
	      System.out.println("u : "+u);
		
		if(u != null) {
			
			int userno = u.getUserno();
			session.setAttribute("userno", u.getUserno());
			session.setAttribute("userName", u.getU_name());
			System.out.println("userno :" +userno);
		}
			model.addAttribute("list",ls.findAllLecture());
	}
	
	
	//insert
	@GetMapping("/school/lecture/insert")
	public void insert() {}
	@PostMapping("/school/lecture/insert")
	public String insert(LectureVO l) {
		return "redirect:/school/lecture/list";
	}
	
	//update
	@GetMapping("/school/lecture/update/{lecNO}")
	public String update(Model model, @PathVariable("lecNO")int lecNO) {
		model.addAttribute("l",ls.findByNoLecture(lecNO));
		return "/school/lecture/update";
	}
	@PostMapping("/school/lecture/update")
	public String update(LectureVO l) {
		ls.updateLecture(l);
		return "redirect:/school/lecture/list";
	}
	
	//detail
	@GetMapping("/school/lecture/detail/{lecNO}")
	public ModelAndView detail(@PathVariable("lecNO")int lecNO, HttpServletRequest request) {
		System.out.println("controller) findBYNO: "+lecNO);
		ModelAndView mav = new ModelAndView("school/lecture/detail");
		HttpSession session = request.getSession();
	    UsersVO u = (UsersVO)session.getAttribute("u");
		System.out.println("u : "+u);
		//(만약 로그인 상태라면)
		   if (u != null) {
		        int userno = u.getUserno();
		        System.out.println("userno: " + userno);
		        session.setAttribute("userno", u.getUserno());
		        
		        // 좋아요한 항목 정보 가져오기 
			    List<Integer> likedlecNOs = new ArrayList();
		        // 좋아요한 항목 정보 가져와서 리스트에 저장
			    likedlecNOs = ls.findLikedLectureNos(userno);
			    // 좋아요한 항목인지 확인하여 상태 설정
		        LectureVO lectureDetail = ls.findByNoLecture(lecNO);
		        if (likedlecNOs.contains(lectureDetail.getLecNO())) {
		        	lectureDetail.setLiked(true);
		        }
		        mav.addObject("l", lectureDetail);
			}else {
				mav.addObject("l",ls.findByNoLecture(lecNO));
			}
		return mav;
	}
	
	// 좋아요기능
		// 좋아요 추가만 처리하는 컨트롤러 메서드
		@PostMapping("/school/lecture/liked/insert")
		@ResponseBody
		public ResponseEntity<String> like(@RequestParam int lecNO, HttpServletRequest request) {
		    HttpSession session = request.getSession();
		    UsersVO u = (UsersVO) session.getAttribute("u");
		    int userno = u.getUserno();

		    System.out.println("좋아요 lecno : " + lecNO);
		    System.out.println("좋아요 userno : " + userno);
		    HashMap<String, Object> map = new HashMap<>();
		    map.put("lecNO", lecNO);
		    map.put("userno", userno);
		  
	    ls.insertLectureLiked(map);
	    return ResponseEntity.ok("좋아요를 추가했습니다.");
		   
		}
		// 좋아요 취소
		@PostMapping("/school/lecture/liked/delete")
		@ResponseBody
		public ResponseEntity<String> unlike(@RequestParam int lecNO, HttpServletRequest request) {
		    HttpSession session = request.getSession();
		    UsersVO u = (UsersVO) session.getAttribute("u");

		    int userno = u.getUserno();
		    
		    HashMap<String, Object> map = new HashMap<>();
		    map.put("lecNO", lecNO);
		    map.put("userno", userno);

		        ls.deleteLectureLiked(map);
		        return ResponseEntity.ok("좋아요를 취소했습니다.");
		   
		}

//		// 좋아요 목록
//		@GetMapping("/school/education/likedList")
//	    public ModelAndView listLiked(Model model, HttpServletRequest request){
//			ModelAndView mav = new ModelAndView("/school/education/likedList");
//	        HttpSession session = request.getSession();
//	        UsersVO u = (UsersVO) session.getAttribute("u");//(만약 로그인 상태라면)
//		    if (u != null) {
//		        int userno = u.getUserno();
//		        System.out.println("userno: " + userno);
//		        session.setAttribute("userno", u.getUserno());
//		        List<LectureVO> likedList = ls.findLikedLectureByUserNo(userno);
//		        System.out.println("likedList" + likedList);
//		        mav.addObject("leclikedList", likedList);
//		    }else {
//		    	mav.setViewName("redirect:/userinfo/login");
//		    }
//	        return mav;
//	    }
	
	
}
