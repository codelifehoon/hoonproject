package com.zebra.process.crawler.domain;

import com.zebra.common.domain.BaseBO;
import com.zebra.process.parser.domain.ExpPattenBO;

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
	
	private int	crawlThreadCount;
	
	
	public String[] getSeedURL() {
		return seedURL;
	}
	public void setSeedURL(String[] siteURL) {
		this.seedURL = siteURL;
	}
	public String getSiteNm() {
		return siteNm;
	}
	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}
	public int getCrawlThreadCount() {
		return crawlThreadCount;
	}
	public void setCrawlThreadCount(int crawlThreadCount) {
		this.crawlThreadCount = crawlThreadCount;
	}

	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getSiteConfigSeq() {
		return siteConfigSeq;
	}
	public void setSiteConfigSeq(String siteConfigSeq) {
		this.siteConfigSeq = siteConfigSeq;
	}
	public String getSeedStrURL() {
		return seedStrURL;
	}
	public void setSeedStrURL(String seedStrURL) {
		
		this.seedStrURL = seedStrURL;
		this.seedURL = seedStrURL.split("@");
	}
	
	
}
