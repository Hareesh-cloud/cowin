package com.hcl.cowin.constant;

public enum PaymentType {

	PAID("Paid"), FREE("Free"), ANY("Any");

	private String value;

	PaymentType(String value) {
		this.value = value;
	}

	public String getValue() {

		return value;
	}
}
