package com.zebra.process.crawler.domain;

import java.util.List;

import lombok.Data;

import com.zebra.common.BaseConstants;
import com.zebra.common.domain.BaseBO;
import com.zebra.common.util.PattenUtil;
import com.zebra.process.parser.domain.ExpPattenBO;

@Data
public class CrawConfigBO extends BaseBO 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2472773675572611279L;
	private String siteConfigSeq;
	private String siteNm;
	private String seedStrURL;
	private String[] seedURL;
	private String useYn;
	
	private int	crawlThreadCount=0;
	private int crawlDepth = 0;
	
	// 접근대상이 되는 사이트,페이지인지 판단 flag
	private String 	visitSiteYn;			// 접근대상  사이트 여부
	private String visitUrlYn;				// 접근대상 url 여부
	private String goodsUrlYn;				//	접근가능 상품URL 여부

	private String crawlAgent = "";				//	접속 브라우저 browser agent
	private List<ExpPattenBO> expPattenBOList;		// 해당 craw정보에 속한patten 정보
	

	public void setSeedStrURL(String seedStrURL) {
		
		this.seedStrURL = seedStrURL;
		if (seedStrURL != null) this.seedURL = seedStrURL.split("@");
	}
	
	public String getCrawlAgent()
	{
		if ("".equals(crawlAgent)) return BaseConstants.CRAWL_AGENT;
		else return crawlAgent;
	}
	
}
