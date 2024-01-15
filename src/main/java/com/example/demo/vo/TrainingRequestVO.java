package com.example.demo.vo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "trainingRequest")
public class TrainingRequestVO {
	
	@Id
	private int reqNo;
	private String reqTitle;
	
//	@ManyToOne
//	@JoinColumn(name = "id", insertable = true, updatable = true)
//	private UsersVO users;
	
	private String reqContent;
	private String reqStatus;
	private String reqApp;
	private String reqAddr;
	private String reqDate;
	private String reqCompany;
	private String reqPhone;
	private Date reqSysDate;
	private int userNo;
}
