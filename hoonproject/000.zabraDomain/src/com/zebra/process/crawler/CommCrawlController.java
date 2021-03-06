package com.zebra.process.crawler;

import org.springframework.beans.factory.annotation.Autowired;


import com.zebra.business.craw.dao.PageInfoDAO;
import com.zebra.business.craw.domain.CrawlerDataCombBO;
import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.SpringBeanFactory;
import com.zebra.common.dao.CommonPattenCodeDao;
import com.zebra.common.exception.BaseException;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class CommCrawlController extends CrawlController {

	private CrawlerDataCombBO crawlerDataCombBO = null;
	
	private PageInfoDAO pageInfoDao = SpringBeanFactory.getBean(PageInfoDAO.class);;
	
	public CommCrawlController(CrawlConfig config, PageFetcher pageFetcher,
			RobotstxtServer robotstxtServer) throws Exception {
		super(config, pageFetcher, robotstxtServer);
		// TODO Auto-generated constructor stub
	}
	
	public CrawlerDataCombBO getCrawlerDataCombBO() {
		return crawlerDataCombBO;
	}

	public void setCrawlerDataCombBO(CrawlerDataCombBO crawlerDataCombBO) throws BaseException {
		if (this.crawlerDataCombBO != null) throw new BaseException("crawlerDataCombBO is not null");
		this.crawlerDataCombBO = crawlerDataCombBO;
	}
	
	public synchronized void addWebPageinfoList(WebPageInfoBO webPageInfoBO)
	{
		
		if ("Y".equals(crawlerDataCombBO.getPreViewYn()) )
		{
			crawlerDataCombBO.addCrawWebPageinfo(webPageInfoBO);
		}
		else
		{
			if (crawlerDataCombBO.addCrawWebPageinfo(webPageInfoBO) >= 100)
			{
				pageInfoDao.insertPageInfo(crawlerDataCombBO.getWebPageInfoBOlist());
				crawlerDataCombBO.getWebPageInfoBOlist().clear();
			}
		}
		
	}

}
