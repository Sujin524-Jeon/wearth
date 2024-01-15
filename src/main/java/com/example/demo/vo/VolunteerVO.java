package com.example.demo.vo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "volunteer")
public class VolunteerVO {
		
	@Id
	private int volunteerno;
	private String v_title;
	private String v_content;
	private Date v_date;
	private Date v_start;
	private Date v_end;
	private String v_loc;
	private String v_addr;
	private String v_time;
	private int v_personnel;
	private String v_agency;
	private String v_manager;
	private String v_phone;
	private Date v_recruitDate;
	private String v_email;
	public int getVolunteerno() {
		return volunteerno;
	}
	public void setVolunteerno(int volunteerno) {
		this.volunteerno = volunteerno;
	}
	public String getV_title() {
		return v_title;
	}
	public void setV_title(String v_title) {
		this.v_title = v_title;
	}
	public String getV_content() {
		return v_content;
	}
	public void setV_content(String v_content) {
		this.v_content = v_content;
	}
	public Date getV_date() {
		return v_date;
	}
	public void setV_date(Date v_date) {
		this.v_date = v_date;
	}
	public Date getV_start() {
		return v_start;
	}
	public void setV_start(Date v_start) {
		this.v_start = v_start;
	}
	public Date getV_end() {
		return v_end;
	}
	public void setV_end(Date v_end) {
		this.v_end = v_end;
	}
	public String getV_loc() {
		return v_loc;
	}
	public void setV_loc(String v_loc) {
		this.v_loc = v_loc;
	}
	public String getV_addr() {
		return v_addr;
	}
	public void setV_addr(String v_addr) {
		this.v_addr = v_addr;
	}
	public String getV_time() {
		return v_time;
	}
	public void setV_time(String v_time) {
		this.v_time = v_time;
	}
	public int getV_personnel() {
		return v_personnel;
	}
	public void setV_personnel(int v_personnel) {
		this.v_personnel = v_personnel;
	}
	public String getV_agency() {
		return v_agency;
	}
	public void setV_agency(String v_agency) {
		this.v_agency = v_agency;
	}
	public String getV_manager() {
		return v_manager;
	}
	public void setV_manager(String v_manager) {
		this.v_manager = v_manager;
	}
	public String getV_phone() {
		return v_phone;
	}
	public void setV_phone(String v_phone) {
		this.v_phone = v_phone;
	}
	public Date getV_recruitDate() {
		return v_recruitDate;
	}
	public void setV_recruitDate(Date v_recruitDate) {
		this.v_recruitDate = v_recruitDate;
	}
	public String getV_email() {
		return v_email;
	}
	public void setV_email(String v_email) {
		this.v_email = v_email;
	}

	
	
	
}
