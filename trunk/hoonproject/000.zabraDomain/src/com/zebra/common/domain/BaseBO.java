package com.zebra.common.domain;

import java.io.Serializable;
import lombok.Data;


public class BaseBO  implements Serializable  {


	private String retCode;
	private String retMsg;
	private Integer	   rowCnt;
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
	public Integer getRowCnt() {
		return rowCnt;
	}
	public void setRowCnt(Integer rowCnt) {
		this.rowCnt = rowCnt;
	}
	
	
}
