package com.zebra.process.crawler.domain;

import lombok.Data;

import com.zebra.common.domain.BaseBO;

@Data
public class WebPageInfoBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1046937489186477694L;
	
	private String  pageInfoListSeq;
	private String 	siteConfigSeq = "";
	private String 	goodsNo 	="";
	private String 	goodsNm 	="";
	private String 	goodsPrice 	="";
	private String 	goodsUrl 	="";
	private String  goodsImg 	="";
	private String  goodsDisc	="";
	
	private String  reNewFlag="N";
	
	private String cate1 ="";
	private String cate2 ="";
	private String cate3 ="";
	
	private String statCd="";
	private Integer failCnt=0;
	private String updateDt="";
	private String updateNo="";
	private String createDt="";
	private String createNo="";

	private String failYn="";
	
	

}
