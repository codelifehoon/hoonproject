package com.zebra.process.crawler.domain;

import com.zebra.common.domain.BaseBO;

public class WebPageInfoBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1046937489186477694L;
	
	private String  pageInfoListSeq;
	private String 	siteConfigSeq = "";
	private String 	webPageInfoSeq ="";
	private String 	goodsNo 	="";
	private String 	goodsNm 	="";
	private String 	goodsPrice 	="";
	private String 	goodsUrl 	="";
	private String  goodsImg 	="";
	private String  reNewFlag="N";
	
	private String cate1 ="";
	private String cate2 ="";
	private String cate3 ="";
	
	private String statCd="";
	private String updateDt="";
	private String updateNo="";
	private String createDt="";
	private String createNo="";

	
	
	
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getGoodsNm() {
		return goodsNm;
	}
	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getGoodsUrl() {
		return goodsUrl;
	}
	public void setGoodsUrl(String goodsUrl) {
		this.goodsUrl = goodsUrl;
	}
	public String getGoodsImg() {
		return goodsImg;
	}
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	public String getWebPageInfoSeq() {
		return webPageInfoSeq;
	}
	public void setWebPageInfoSeq(String webPageInfoSeq) {
		this.webPageInfoSeq = webPageInfoSeq;
	}
	public String getCate1() {
		return cate1;
	}
	public void setCate1(String cate1) {
		this.cate1 = cate1;
	}
	public String getCate2() {
		return cate2;
	}
	public void setCate2(String cate2) {
		this.cate2 = cate2;
	}
	public String getCate3() {
		return cate3;
	}
	public void setCate3(String cate3) {
		this.cate3 = cate3;
	}
	
	public String getReNewFlag() {
		return reNewFlag;
	}
	public void setReNewFlag(String reNewFlag) {
		this.reNewFlag = reNewFlag;
	}
	public String getSiteConfigSeq() {
		return siteConfigSeq;
	}
	public void setSiteConfigSeq(String siteConfigSeq) {
		this.siteConfigSeq = siteConfigSeq;
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
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	public String getCreateNo() {
		return createNo;
	}
	public void setCreateNo(String createNo) {
		this.createNo = createNo;
	}
	public String getStatCd() {
		return statCd;
	}
	public void setStatCd(String statCd) {
		this.statCd = statCd;
	}
	public String getPageInfoListSeq() {
		return pageInfoListSeq;
	}
	public void setPageInfoListSeq(String pageInfoListSeq) {
		this.pageInfoListSeq = pageInfoListSeq;
	}
	

}
