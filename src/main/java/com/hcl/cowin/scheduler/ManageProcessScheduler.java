package com.hcl.cowin.scheduler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hcl.cowin.service.ManageUsersService;

@Component
public class ManageProcessScheduler {
	
	@Autowired
	private ManageUsersService manageUserService;
	
	@Scheduled(fixedDelay = 7200000)
	public void process() {
		System.out.println("Method executed at every 5 seconds. Current time is :: " + new Date());
		manageUserService.updateUserDetail();
		
	}
}
