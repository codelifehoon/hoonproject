package com.zebra.common.domain;

import java.io.Serializable;

public class BaseBO  implements Serializable  {


	private String retCode;
	private String retMsg;
	
	
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	
}
