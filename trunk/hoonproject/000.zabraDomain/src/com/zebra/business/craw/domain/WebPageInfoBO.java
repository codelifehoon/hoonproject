package com.zebra.business.craw.domain;

import java.util.Date;

import lombok.Data;

import com.zebra.common.domain.BaseBO;
import com.zebra.common.util.DateTime;

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
	private String  goodsPrice	="";
	private String 	goodsUrl 	="";
	private String  goodsImg 	="";
	private String  goodsDisc	="";
	private String  goodsIsbuyPatten	="";
	
	private String  reNewFlag="N";
	
	private String cate1 ="";
	private String cate2 ="";
	private String cate3 ="";
	
	private String statCd="";
	private Integer failCnt=0;
	private Date updateDt ;
	private String updateNo="";
	private Date createDt ;
	private String createNo="";

	private String failYn="";
	
	public String getCreateDtStr()
	{
		return DateTime.format(getCreateDt(), "yyyy-MM-dd HH:mm:ss");
	}

	public String getUpdateDtStr()
	{
		return DateTime.format(getUpdateDt(), "yyyy-MM-dd HH:mm:ss");
	}


}
