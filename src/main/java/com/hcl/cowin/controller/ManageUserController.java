package com.hcl.cowin.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping("/createuser")
	public UserResponse createUsers(@RequestBody UserDto user) {

		/*
		 * UserDto user = new UserDto(); user.setDistrict("test");
		 * user.setEmail("deepak@gmail.com"); user.setPayment("free");
		 * user.setType("any"); user.setDate("05/14/2021");
		 */
		return userService.createUser(user);

		
	}
	@GetMapping("/test1")
	public UserResponse getAllUserData() {

		userService.getAllNotifiableUsers();
		return new UserResponse();
	}
}
