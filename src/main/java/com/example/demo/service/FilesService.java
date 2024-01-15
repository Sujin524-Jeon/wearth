package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import com.example.demo.vo.BoardVO;
import com.example.demo.vo.FilesVO;

public interface FilesService {
	
	//게시글별 파일 불러오기 위한 메소드 - MB
	public FilesVO findFile();
	
	//파일 등록시 다음 fileno 부여하기 위한 fileno 번호 불러오기 - JPA
	public int getNextNo();

	//실천하기 게시판 파일 insert
	public void insertInBoard(FilesVO f);
	
	//실천하기 게시판 파일 delete하기 위해 파일 유무 확인
	public List<FilesVO> findByBoardno(int boardno);
	
	//실천하기 게시판 파일 delete
	public void deleteInBoard(int boardno);
	
	//봉사하기 게시판 파일 insert
	public void insertInVolunteer(FilesVO f);
}
