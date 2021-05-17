package com.hcl.dto;

import java.util.List;

import lombok.Data;

@Data
public class CowinObjectDto {

	private String center_id;
	private String name;
	private String address;
	private String state_name;
	private String district_name;
	private String block_name;
	private String pincode;
	private String from;
	private String to;
	private String fee_type;
	private List<CowinSession> sessions;
	
}
