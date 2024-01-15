package com.example.demo.vo;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "files")
public class FilesVO {

	@Id
	private int fileno;
	private int boardno;
	private int volunteerno;
	private int goodsno;
	private int opinionno;
	private int eduno;
	private int lecno;
	private String fname;
	
	@Transient	//테이블 매핑에서는 제외
	private MultipartFile uploadFile;

	
	public int getFileno() {
		return fileno;
	}

	public void setFileno(int fileno) {
		this.fileno = fileno;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public int getVolunteerno() {
		return volunteerno;
	}

	public void setVolunteerno(int volunteerno) {
		this.volunteerno = volunteerno;
	}

	public int getGoodsno() {
		return goodsno;
	}

	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}

	public int getOpinionno() {
		return opinionno;
	}

	public void setOpinionno(int opinionno) {
		this.opinionno = opinionno;
	}

	public int getEduno() {
		return eduno;
	}

	public void setEduno(int eduno) {
		this.eduno = eduno;
	}

	public int getLecno() {
		return lecno;
	}

	public void setLecno(int lecno) {
		this.lecno = lecno;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	
	
}
