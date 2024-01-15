package com.example.demo.controller;

import java.security.Security;
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

import com.example.demo.repository.EducationMyBatisRepository;
import com.example.demo.service.EducationService;
import com.example.demo.service.UserInfoService;
import com.example.demo.service.lectureService;
import com.example.demo.vo.EducationVO;
import com.example.demo.vo.LectureVO;
import com.example.demo.vo.LikedVO;
import com.example.demo.vo.OpinionVO;
import com.example.demo.vo.UsersVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class EducationController {
	
	@Autowired
	private EducationService es;
	//EducationMyBatisRepository, EducationJpaRepository
	
	@Autowired
	private UserInfoService us;

	@Autowired
	private lectureService ls;
	
	// list
	@GetMapping(value={ "/school/education/list/{pageNUM}", "/school/education/list/{category}/{search}/{pageNUM}"})
	public ModelAndView list( 
			@PathVariable(name = "category", required = false) String category, 
	        @PathVariable(name = "search", required = false) String search, 
	        @PathVariable(name = "pageNUM") int pageNUM,
	         HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView("/school/education/list");
	    int start = (pageNUM - 1) * EducationMyBatisRepository.pageSize + 1;
	    int end = start + EducationMyBatisRepository.pageSize - 1;
	    System.out.println("현재페이지:"+start);
	    System.out.println("마지막페이지:"+end);
	    HashMap<String, Object> map = new HashMap<>();
	    map.put("category", category);
	    map.put("search", search);
	    map.put("start", start);
	    map.put("end", end);
	    
	    // 목록
	    HttpSession session = request.getSession();
	    UsersVO u = (UsersVO)session.getAttribute("u");
		System.out.println("u : "+u);
		
		//(만약 로그인 상태라면)
   if (u != null) {
	        int userno = u.getUserno();
	        System.out.println("userno: " + userno);
	        session.setAttribute("userno", u.getUserno());
	        
	        // 좋아요한 항목 정보 가져오기 
		    List<Integer> likedEduNOs = new ArrayList();
	        // 좋아요한 항목 정보 가져와서 리스트에 저장
	        likedEduNOs = es.findLikedEducationNos(userno);
	        
	        List<EducationVO> educationList = es.findAllEducation(map);
		    
	        // 좋아요한 항목인지 확인하여 상태 설정
	   	    for (EducationVO edu : educationList) {
	   	    	if (likedEduNOs.contains(edu.getEduNO())) {
	   	    		 edu.setLiked(edu.isLiked(likedEduNOs));
	   	        }
	   	    }
	   	  mav.addObject("list", educationList);
	    }else {
	    	mav.addObject("list", es.findAllEducation(map));
	    }	 
	    mav.addObject("totalPage", EducationMyBatisRepository.totalPage);
	    
	    return mav;
	}
	
	// insert에 파일올리기 추가하기
	@GetMapping("/school/education/insert")
	public void insert() {}
	@PostMapping("/school/education/insert")
	public String insert(EducationVO e) {
		return "redirect:/school/education/list";
	}
	
	// update에 파일올리기 추가하기
	@GetMapping("/school/education/update/{eduNO}")
	public String update(Model model, @PathVariable("eduNO")  int eduNO) {
		model.addAttribute("e",es.findByNoEducation(eduNO));
		return "/school/education/update";
	}
	@PostMapping("/school/education/update")
	public String update(EducationVO e) {
		es.updateEducation(e);
		return "redirect:/school/education/list";
	}
	
	//detail
	@GetMapping("/school/education/detail/{eduNO}")
	public ModelAndView detail(@PathVariable("eduNO") int eduNO, HttpServletRequest request) {
		System.out.println("교육컨트롤러(detail) 글번호 : "+ eduNO);
		ModelAndView mav = new ModelAndView("/school/education/detail");
		
		HttpSession session = request.getSession();
	    UsersVO u = (UsersVO)session.getAttribute("u");
		System.out.println("Detail u : "+u);
	
		//(만약 로그인 상태라면)
	    if (u != null) {
	        int userno = u.getUserno();
	        System.out.println("userno: " + userno);
	        session.setAttribute("userno", u.getUserno());
	        
	        // 좋아요한 항목 정보 가져오기 
		    List<Integer> likedEduNOs = new ArrayList();
	        // 좋아요한 항목 정보 가져와서 리스트에 저장
	        likedEduNOs = es.findLikedEducationNos(userno);
	        // 좋아요한 항목인지 확인하여 상태 설정
	        EducationVO educationDetail = es.findByNoEducation(eduNO);
	        if (likedEduNOs.contains(educationDetail.getEduNO())) {
	            educationDetail.setLiked(true);
	            mav.addObject("e", educationDetail);
	        }
		}else {
			mav.addObject("e", es.findByNoEducation(eduNO));
		}
	    return mav;
	}
	
// 좋아요기능
	// 좋아요 추가만 처리하는 컨트롤러 메서드
	@PostMapping("/school/education/liked/insert")
	@ResponseBody
	public ResponseEntity<String> like(@RequestParam int eduNO, HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    UsersVO u = (UsersVO) session.getAttribute("u");
	    int userno = u.getUserno();

	    System.out.println("좋아요에 eduno 파라미터 : "+ eduNO);
	    HashMap<String, Object> map = new HashMap<>();
	    map.put("eduNO", eduNO);
	    map.put("userno", userno);
	  
    es.insertEducationLiked(map);
    return ResponseEntity.ok("좋아요를 추가했습니다.");
	   
	}
	// 좋아요 취소
	@PostMapping("/school/education/liked/delete")
	@ResponseBody
	public ResponseEntity<String> unlike(@RequestParam int eduNO, HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    UsersVO u = (UsersVO) session.getAttribute("u");

	    int userno = u.getUserno();
	    System.out.println("userno : " + userno);
	    HashMap<String, Object> map = new HashMap<>();
	    map.put("eduNO", eduNO);
	    map.put("userno", userno);

	        es.deleteEducationLiked(map);
	        return ResponseEntity.ok("좋아요를 취소했습니다.");
	   
	}

	// 좋아요 목록
	@GetMapping("/school/education/likedList")
    public ModelAndView listLiked(Model model, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/school/education/likedList");
        HttpSession session = request.getSession();
        UsersVO u = (UsersVO) session.getAttribute("u");//(만약 로그인 상태라면)
	    if (u != null) {
	        int userno = u.getUserno();
	        System.out.println("userno: " + userno);
	        session.setAttribute("userno", u.getUserno());
	        // 교육 좋아요 목록
	        List<EducationVO> edulikedList = es.findLikedEducationByUserNo(userno);
	        System.out.println("edulikedList" + edulikedList);
	        mav.addObject("edulikedList", edulikedList);
	        
	        List<LectureVO> leclikedList = ls.findLikedLectureByUserNo(userno);
	        System.out.println("likedList" + leclikedList);
	        mav.addObject("leclikedList", leclikedList);
	    }else {
	    	mav.setViewName("redirect:/userinfo/login");
	    }
        return mav;
    }
//리뷰 
	
	//교육리뷰 조회
	@GetMapping("/school/education/review/list/{eduNO}")
	@ResponseBody
	public List<OpinionVO> findAllEducationReview(Model model,@PathVariable(name = "eduNO") int eduNO) {
		System.out.println("교육리뷰조회 eduNO 파라미터값 :"+ eduNO);
	   return es.findAllEducationReview(eduNO);
	}

	//교육리뷰 추가
	@PostMapping("/school/education/review/insert")
	@ResponseBody
	public int insertEducationReview(@RequestParam int eduNO, @RequestParam String opinionContent, HttpServletRequest request) {
	    int re = 0; // 초기값을 0으로 설정
	    HttpSession session = request.getSession();
	    UsersVO u = (UsersVO) session.getAttribute("u");
	    
	    if (u != null) {
	        String ID = u.getNickname();
	        System.out.println("eduNO : " + eduNO);
	        System.out.println("id : " + ID);
	        System.out.println("opinionContent : " + opinionContent);

	        HashMap<String, Object> map = new HashMap<>();
	        map.put("eduNO", eduNO);
	        map.put("ID", ID);
	        map.put("opinionContent", opinionContent);
	        System.out.println("map : " + map);

	        re = es.insertEducationReview(map); // re 값을 실제 처리 결과로 설정
	    }
	    
	    return re;
	}

}
