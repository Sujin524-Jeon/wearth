package com.example.demo.vo;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "lecture")
public class LectureVO {
	
	@Id
	private int lecNO;
	private String lecName;
	
	private String lecContent;
	private String lecStatus;
	private String lecDate;
	private String lecAddr;
	private String lecturer;
	private String lecApp;
	private String lecPhone;
	private int lecPrice;
	
	private String lecFile;
	private String lecDetailFile;
	private Date lecSysdate;
	
	private boolean liked;
	
	// 좋아요 상태 설정 세션유지를 위한 메소드
   public void setLiked(boolean liked) {
       this.liked = liked;
   }
	// lectureVO 클래스에 isLiked 메소드 추가
	public boolean isLiked(List<Integer> likedLecNOs) {
	    return likedLecNOs.contains(this.lecNO);
	}
}
