package com.example.demo.vo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "orders")
@ToString
public class OrdersVO {

	@Id
	private int ordersNo;
	private int userNo;
	private int addrNo;
	private int ordersPrice;
	private Date ordersDate;
	private String ordersStatus;
	private String ordersContent;
	private int ordersCnt;

	private int shipPrice;

}
