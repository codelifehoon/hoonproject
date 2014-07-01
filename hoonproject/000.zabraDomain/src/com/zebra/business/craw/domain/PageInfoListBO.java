package com.zebra.business.craw.domain;

import java.util.Date;

import lombok.Data;

import com.zebra.common.domain.BaseBO;
import com.zebra.common.util.DateTime;

@Data
public class PageInfoListBO extends BaseBO {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4258097598996496580L;
	
	private String  pageInfoListSeq;
	private String  siteConfigSeq;
	private String  goodsNo;
	private String  goodsNm;
	private String  goodsPrice;
	private String  goodsUrl;
	private String  goodsImg;
	private String  statCd;
	private String  createNo;
	private Date  createDt;
	private Date  updateDt;
	private String  updateNo;
	

	public String getCreateDtStr()
	{
		return DateTime.format(getCreateDt(), "yyyy-MM-dd HH:mm:ss");
	}

	public String getUpdateDtStr()
	{
		return DateTime.format(getUpdateDt(), "yyyy-MM-dd HH:mm:ss");
	}
	
	
}
