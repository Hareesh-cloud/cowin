package com.hcl.cowin.response;

import com.hcl.cowin.constant.ResponseMsg;

public class BaseResponse {

	private ResponseMsg responseMsg;
	private long responseCode;
	
	public ResponseMsg getResponseMsg() {
		return responseMsg;
	}
	public void setResponseMsg(ResponseMsg responseMsg) {
		this.responseMsg = responseMsg;
	}
	public long getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(long responseCode) {
		this.responseCode = responseCode;
	}
}
