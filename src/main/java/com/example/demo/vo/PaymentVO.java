package com.example.demo.vo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="payment")
public class PaymentVO {
	
	@Id
	private int payno;
	private int userno;
	private int ordersno;
	private Date payDate;
	private String payment_method;
}
