package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.CommentsVO;

import jakarta.transaction.Transactional;

@Repository
public interface CommentsJpaRepository extends JpaRepository<CommentsVO, Integer> {
	
	@Query("select nvl(max(commentno), 0)+1 from CommentsVO")
	public int getNextNo();
	
	@Modifying
	@Query(value = "insert into Comments c(c.commentno, c.userno, c.boardno, c_content, c_Date) values(:#{#c.commentno},:#{#c.userno},:#{#c.boardno},:#{#c.c_content},sysdate)", nativeQuery = true)
	@Transactional
	public void insert(CommentsVO c);

}
