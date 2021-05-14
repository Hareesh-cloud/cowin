package com.hcl.dto;

import com.hcl.cowin.constant.PaymentType;
import com.hcl.cowin.constant.UserGroupType;


public class UserDto {

	private String email;
	private String 	district;
	//private PaymentType payment;
//	private UserGroupType type;
	private String date;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}

}
