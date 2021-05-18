package com.hcl.cowin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.cowin.response.UserResponse;
import com.hcl.cowin.service.ManageUsersService;
import com.hcl.dto.UserDto;

@RestController
public class ManageUserController {

	@Autowired
	private ManageUsersService userService;

	@CrossOrigin
	@PostMapping("/createuser")
	public UserResponse createUsers(@RequestBody UserDto user) {

		/*
		 * UserDto user = new UserDto(); user.setDistrict("670");
		 * user.setEmail("deepak@gmail.com"); user.setPayment("Free");
		 * user.setType("45"); user.setDate("14/5/2021");
		 */
		 
		return userService.createUser(user);

		
	}
	@GetMapping("/scheduleUser")
	public String getAllUserData() {
		userService.updateUserDetail();
		return "process completed";
	}
}
