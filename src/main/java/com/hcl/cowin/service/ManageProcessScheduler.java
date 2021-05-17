package com.hcl.cowin.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ManageProcessScheduler {
	
	@Autowired
	private ManageUsersService manageUserService;
	
	@Scheduled(fixedDelay = 5000)
	public void process() {
		System.out.println("Method executed at every 5 seconds. Current time is :: " + new Date());
		
	//	manageUserService.updateUserDetail();
		
	}
}
