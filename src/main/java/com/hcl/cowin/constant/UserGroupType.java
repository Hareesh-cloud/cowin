package com.hcl.cowin.constant;

public enum UserGroupType {


	Eighteen_Plus("18+"), FourtyFive_Plus("45+");

	private String value;

	UserGroupType(String value) {
		this.value = value;
	}

	public String getValue() {

		return value;
	}
}
