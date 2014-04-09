package com.zebra.process.parser.domain;

import com.zebra.common.domain.BaseBO;

public class ExpPattenBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4984631287652950803L;

	private String siteConfigSeq;
	private String pattenKind;
	private String pattenType;						
	private String pattenStr;
	private String useYn;
	private String createNo;
	private String createDt;
	private String updateDt;
	private String updateNo;



	public ExpPattenBO(String pattenStr,String pattenType )
	{
			this.pattenStr 	= pattenStr;
		this.pattenType	= pattenType;
	}
	public ExpPattenBO(String pattenStr)
	{
			this.pattenStr 	= pattenStr;
			this.pattenType	= "";
	}
	public ExpPattenBO()
	{
	}
	
	
	public String getPattenType() {
		return pattenType;
	}
	public void setPattenType(String pattenType) {
		this.pattenType = pattenType;
	}
	public String getPattenStr() {
		return pattenStr;
	}
	public void setPattenStr(String pattenNm) {
		this.pattenStr = pattenNm;
	}
	public String getPattenKind() {
		return pattenKind;
	}
	public void setPattenKind(String pattenKind) {
		this.pattenKind = pattenKind;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getCreateNo() {
		return createNo;
	}
	public void setCreateNo(String createNo) {
		this.createNo = createNo;
	}
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	public String getUpdateDt() {
		return updateDt;
	}
	public void setUpdateDt(String updateDt) {
		this.updateDt = updateDt;
	}
	public String getUpdateNo() {
		return updateNo;
	}
	public void setUpdateNo(String updateNo) {
		this.updateNo = updateNo;
	}
	public String getSiteConfigSeq() {
		return siteConfigSeq;
	}
	public void setSiteConfigSeq(String siteConfigSeq) {
		this.siteConfigSeq = siteConfigSeq;
	}
}
