package com.zebra.business.craw.domain;

import lombok.Data;
import com.zebra.common.domain.BaseBO;

@Data
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
	
}
