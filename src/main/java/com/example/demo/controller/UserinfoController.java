package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.apache.catalina.User;
import org.apache.ibatis.javassist.Loader.Simple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.security.KakaoLoginService;
import com.example.demo.service.UserInfoService;
import com.example.demo.utils.CustomMailSender;
import com.example.demo.vo.UsersVO;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class UserinfoController {

	@Autowired
	private UserInfoService us;

	@Autowired
	private UserDetailsService uds;
	
	@Autowired
	private AuthenticationManager am;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private KakaoLoginService kus;

	@PostMapping("userinfo/signup")
	public String signup(UsersVO u) {
		String view = "redirect:/";
		u.setPwd(encoder.encode(u.getPwd()));
		us.join(u);
		return view;
	}

	@GetMapping("userinfo/login")
	public void login(@RequestParam(value="error", required = false)String error, Model model) {
		model.addAttribute("error", error);
	}

	@GetMapping("userinfo/signup")
	public void signupForm() {
		System.out.println("회원가입 폼 실행");
	}
	
	@GetMapping("userinfo/order")
	public void order() {
	}

	@GetMapping("userinfo/findId")
	public void find(String email, String phone) {

	}

	@GetMapping("userinfo/checkId")
	@ResponseBody
	public String checkId(String id) {
		String result = "T";
		if (us.findById(id).isPresent()) {
			result = "F";
		}
		return result;
	}

	@GetMapping("userinfo/checkNickname")
	@ResponseBody
	public String checkNickname(String nickname) {
		String result = "T";
		if (us.findByNickname(nickname).isPresent()) {
			result = "F";
		}
		return result;
	}

	@GetMapping("userinfo/checkEmail")
	@ResponseBody
	public String checkEmail(String email) {
		String msg = null;
		if (us.findByEmail(email).isPresent()) {
			msg = "존재하는 이메일입니다.\n다른 이메일을 입력해주세요.";
		}
		return msg;
	}

	@GetMapping("userinfo/checkPhone")
	@ResponseBody
	public String checkPhone(String phone) {
		String result = "T";
		if (us.findByPhone(phone).isPresent()) {
			result = "F";
		}
		return result;
	}

	@GetMapping("userinfo/isVaildEmail")
	@ResponseBody
	public String code(String email, HttpSession session) {
		HashMap<String, String> mail = CustomMailSender.makeCode();
		String code = mail.get("code");
		CustomMailSender.sendEmail(email, mail.get("subject"), mail.get("text"));
		session.setAttribute("code", code);
		session.setMaxInactiveInterval(180);
		return code;

	}

	@PostMapping("userinfo/findIdByEmail")
	@ResponseBody
	public String findIdByEmail(String email) {
		Optional<UsersVO> u = us.findByEmail(email);
		return u.isPresent() ? u.get().getId() : null;
	}

	@PostMapping("userinfo/findIdByPhone")
	@ResponseBody
	public String findIdByPhone(String phone) {
		Optional<UsersVO> u = us.findByPhone(phone);
		return u.isPresent() ? u.get().getId() : null;
	}
	
	@PostMapping("userinfo/sendEmailCode")
	@ResponseBody
	public String findPwdByEmail(String email) {	
		Optional<UsersVO> u = us.findByEmail(email);
		if(!u.isPresent()) return null;
		return null;
	}




	
}