package com.zebra.process.renew;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zebra.common.util.ConverterUtil;
import com.zebra.process.crawler.CommCrawlController;
import com.zebra.process.crawler.URLCrawler;
import com.zebra.process.crawler.dao.PageInfoDAO;
import com.zebra.process.crawler.dao.PageInfoDAOImpl;
import com.zebra.process.crawler.domain.CrawlerDataCombBO;
import com.zebra.process.crawler.domain.WebPageInfoBO;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;


@Service
public class ReNewJobImpl implements ReNewJob {

	/* (non-Javadoc)
	 * @see com.zebra.process.renew.ReNewJob#doReNew(com.zebra.process.crawler.domain.CrawlerDataCombBO)
	 */
	public long doReNew(CrawlerDataCombBO crawlerDataCombBO) throws Exception
	{
		long crawCount = 0;
		

		CommCrawlController crawlControlle = null;
		CrawlConfig crawlConfig = new CrawlConfig();
		crawlConfig.setCrawlStorageFolder("d:\\crawlStorage\\" + crawlerDataCombBO.getCrawConfigBO().getSiteNm());
		//crawlConfig.setUserAgentString(userAgentString);
		
		PageFetcher 		pageFetcher = new PageFetcher(crawlConfig);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer  = new RobotstxtServer(robotstxtConfig, pageFetcher);

		
		
		try {
			crawlControlle = new CommCrawlController(crawlConfig, pageFetcher, robotstxtServer) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		crawlControlle.setCrawlerDataCombBO(crawlerDataCombBO);
				
		for (WebPageInfoBO  webPageInfoBO : crawlerDataCombBO.getWebPageInfoBOlist()) crawlControlle.addSeed(webPageInfoBO.getGoodsUrl());
		
		crawlControlle.start(PageCrawler.class, crawlerDataCombBO.getCrawConfigBO().getCrawlThreadCount());
			
		
		return crawCount;
	}

	public long applyReNewInfo(CrawlerDataCombBO crawlerDataCombBO)
			throws Exception {
		
		 List<WebPageInfoBO> webPageInfoBOApplyList =  new ArrayList<WebPageInfoBO>();
		 PageInfoDAO pageInfoDAO = new PageInfoDAOImpl();
		 
		 List<WebPageInfoBO> webPageInfoBOList =  ConverterUtil.webPageInfoMap2List(crawlerDataCombBO.getWebPageInfoBOMap()) ;
		 
		for (WebPageInfoBO webPageInfoBO : webPageInfoBOList)
			{
					if (webPageInfoBO.getReNewFlag().equals("Y")) webPageInfoBOApplyList.add(webPageInfoBO);
			}
		
		pageInfoDAO.updatePageInfoList(webPageInfoBOApplyList);
		
		return webPageInfoBOApplyList.size();
	}
	
}
