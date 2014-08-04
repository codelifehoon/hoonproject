package com.zebra.business.craw.domain;

import java.util.Date;

import lombok.Data;
import com.zebra.common.domain.BaseBO;
import com.zebra.common.util.DateTime;

@Data
public class ExpPattenBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4984631287652950803L;

	private String pattenCodeListSeq;
	private String siteConfigSeq;
	private String pattenKind;
	private String pattenType;						
	private String pattenStr;
	private String useYn;
	private String createNo;
	private Date createDt;
	private Date updateDt;
	private String updateNo;

	public ExpPattenBO(String pattenStr,String pattenKind, String pattenType )
	{
			this.pattenStr 	= pattenStr;
			this.pattenKind = pattenKind;
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
	

	public String getCreateDtStr()
	{
		return DateTime.format(getCreateDt(), "yyyy-MM-dd HH:mm:ss");
	}

	public String getUpdateDtStr()
	{
		return DateTime.format(getUpdateDt(), "yyyy-MM-dd HH:mm:ss");
	}
	
}
