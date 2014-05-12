package com.zebra.process.crawler.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zebra.process.parser.domain.ExpPattenBO;



public class CrawlerDataCombBO {

	private CrawConfigBO crawConfigBO ;
	private PageConfigBO	pageConfigBo;
	private HashMap<String, WebPageInfoBO>  webPageInfoBOMap  = new HashMap<String, WebPageInfoBO>();
	private List<WebPageInfoBO>  webPageInfoBOlist = new ArrayList<WebPageInfoBO>();
	private List<VisiteTargetBO>  visiteTargetBOlist = new ArrayList<VisiteTargetBO>();
	
	
	HashMap<String, ExpPattenBO[]> pattenMap;	// pattenMap을 넘겨서 공통코드를 사용하지 않고 직접 셋팅해서 사용할떄 필요
	
	
	private String preViewYn;				// 링크 수집시 저장을 할지 말지에 대한 판단을 하기 위한 flag
	
	
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
	
	public PageConfigBO getPageConfigBO() {
		return pageConfigBo;
	}
	public void setPageConfigBO(PageConfigBO pageConfigBo) {
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
	public String getPreViewYn() {
		return preViewYn;
	}
	public void setPreViewYn(String preViewYn) {
		this.preViewYn = preViewYn;
	}
	public HashMap<String, ExpPattenBO[]> getPattenMap() {
		return pattenMap;
	}
	public void setPattenMap(HashMap<String, ExpPattenBO[]> pattenMap) {
		this.pattenMap = pattenMap;
	}
	public List<VisiteTargetBO> getVisiteTargetBOlist() {
		return visiteTargetBOlist;
	}
	public void setVisiteTargetBOlist(List<VisiteTargetBO> visiteTargetBOlist) {
		this.visiteTargetBOlist = visiteTargetBOlist;
	}

		
}
