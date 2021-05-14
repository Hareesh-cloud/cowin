package com.hcl.cowin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.cowin.constant.PaymentType;
import com.hcl.cowin.constant.UserGroupType;
import com.hcl.cowin.service.ManageUsersService;
import com.hcl.dto.UserDto;

@RestController
public class ManageUserController {

	@Autowired
	private ManageUsersService userService;

	@GetMapping("/test")
	public String createUsers() {

		UserDto user = new UserDto();
		user.setDistrict("test");
		user.setEmail("deepak@gmail.com");
	//	user.setPayment(PaymentType.ANY);
		//user.setType(UserGroupType.Eighteen_Plus);
		user.setDate("05/14/2021");
		userService.createUser(user);

		return "SuccessFully return";
	}
}
