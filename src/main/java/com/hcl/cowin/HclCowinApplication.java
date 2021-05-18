package com.hcl.cowin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HclCowinApplication {

	public static void main(String[] args) {
		SpringApplication.run(HclCowinApplication.class, args);
	}

}
