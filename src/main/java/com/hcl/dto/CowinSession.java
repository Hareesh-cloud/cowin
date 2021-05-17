package com.hcl.dto;

import lombok.Data;

@Data
public class CowinSession {
	private String session_id;
	private String date;
	private String available_capacity;
	private String min_age_limit;
	private String vaccine;
	
}
