package com.zebra.business.craw.domain;

import lombok.Data;

import com.zebra.common.domain.BaseBO;

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
	private String  createDt;
	private String  updateDt;
	private String  updateNo;
	

	
}
