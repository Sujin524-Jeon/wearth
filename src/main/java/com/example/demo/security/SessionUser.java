package com.example.demo.security;

import java.io.Serializable;
import java.time.LocalDate;

import com.example.demo.vo.UsersVO;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class SessionUser implements Serializable {
	private int userno;
	private String id;
	private String pwd;
	private String u_name;
	private LocalDate date_birth;
	private String gender;
	private String email;
	private String nickname;
	private LocalDate date_reg;
	private String residence;
	private String phone;
	private int point;
	private String u_status;
	@Enumerated(EnumType.STRING)
	@Column(name="role")
	private Role role;
	
	public SessionUser(UsersVO user) {
		this.id = user.getId();
		this.pwd = user.getPwd();
		this.u_name = user.getU_name();
		this.date_birth = user.getDate_birth();
		this.gender = user.getGender();
		this.email = user.getEmail();
		this.nickname = user.getNickname();
		this.date_reg = user.getDate_reg();
		this.residence = user.getResidence();
		this.phone = user.getPhone();
		this.point = user.getPoint();
		this.u_status = user.getU_status();
		this.role = user.getRole();
	}
}
