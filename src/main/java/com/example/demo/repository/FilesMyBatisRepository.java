package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.FilesDBManager;
import com.example.demo.vo.FilesVO;

@Repository
public class FilesMyBatisRepository {

	public FilesVO findFile() {
		return FilesDBManager.findFile();
	}	
	
	public List<FilesVO> findByBoardno(int boardno){
		return FilesDBManager.findByBoardno(boardno);
	}
}
