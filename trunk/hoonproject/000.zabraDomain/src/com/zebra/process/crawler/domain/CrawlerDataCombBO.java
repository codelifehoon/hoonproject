package com.zebra.process.crawler.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class CrawlerDataCombBO {

	private CrawConfigBO crawConfigBO ;
	private PageConfigBo	pageConfigBo;
	private HashMap<String, WebPageInfoBO>  webPageInfoBOMap  = new HashMap<String, WebPageInfoBO>();
	private List<WebPageInfoBO>  webPageInfoBOlist = new ArrayList<WebPageInfoBO>();
	
	
	public CrawConfigBO getCrawConfigBO() {
		return crawConfigBO;
	}
	public void setCrawConfigBO(CrawConfigBO crawConfigBO) {
		this.crawConfigBO = crawConfigBO;
	}
	public HashMap<String, WebPageInfoBO> getWebPageInfoBOMap() {
		return webPageInfoBOMap;
	}
	public void setWebPageInfoBOMap(HashMap<String, WebPageInfoBO> webPageInfoBOMap) {

		this.webPageInfoBOMap = webPageInfoBOMap;
	}
	public List<WebPageInfoBO> getWebPageInfoBOlist() {
		return webPageInfoBOlist;
	}
	public void setWebPageInfoBOlist(List<WebPageInfoBO> webPageInfoBOlist) {
		this.webPageInfoBOlist = webPageInfoBOlist;
	}
	
	public PageConfigBo getPageConfigBo() {
		return pageConfigBo;
	}
	public void setPageConfigBo(PageConfigBo pageConfigBo) {
		this.pageConfigBo = pageConfigBo;
	}
	
		
	public synchronized long addCrawWebPageinfo(WebPageInfoBO webPageInfoBO)
	{
		
		if (webPageInfoBOMap.get(webPageInfoBO.getGoodsUrl()) == null)
		{
			webPageInfoBOMap.put(webPageInfoBO.getGoodsUrl(), webPageInfoBO);
			webPageInfoBOlist.add(webPageInfoBO);
		}
		
		return webPageInfoBOlist.size();
	}
	
	public long compareListMapSize()
	{
		if ( webPageInfoBOMap.size() == webPageInfoBOlist.size()) return webPageInfoBOMap.size();
		return -1;
	}
		
}
