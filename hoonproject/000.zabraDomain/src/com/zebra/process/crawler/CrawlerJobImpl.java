package com.zebra.process.crawler;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.zebra.common.BaseFactory;
import com.zebra.common.CommonConstants;
import com.zebra.common.util.ConverterUtil;
import com.zebra.process.crawler.dao.CrawCofigDAO;
import com.zebra.process.crawler.dao.PageInfoDAO;
import com.zebra.process.crawler.domain.CrawConfigBO;
import com.zebra.process.crawler.domain.CrawlerDataCombBO;
import com.zebra.process.crawler.domain.WebPageInfoBO;


import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

@Service
public class CrawlerJobImpl implements CrawlerJob {

	protected Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	CrawCofigDAO	crawConfigDAO;
	
	@Autowired
	PageInfoDAO		pageInfoDAO;
	
	
	/* (non-Javadoc)
	 * @see com.zebra.process.crawler.CrawlerJob#doCrawler(com.zebra.process.crawler.domain.CrawlerDataCombBO)
	 */
	public void doCrawler(CrawlerDataCombBO crawlerDataCombBO) throws Exception 
	{
		CommCrawlController commCrawlController = null;
		CrawlConfig crawlConfig = new CrawlConfig();
		crawlConfig.setCrawlStorageFolder(CommonConstants.CRAWL_STORAGE_FOLDER + this.getClass().getName() + "\\" + crawlerDataCombBO.getCrawConfigBO().getSiteNm());
		crawlConfig.setUserAgentString(CommonConstants.CRAWL_AGENT);
		
		PageFetcher 		pageFetcher = new PageFetcher(crawlConfig);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer  = new RobotstxtServer(robotstxtConfig, pageFetcher);
		
		try {
			commCrawlController = new CommCrawlController(crawlConfig, pageFetcher, robotstxtServer) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		commCrawlController.setCrawlerDataCombBO(crawlerDataCombBO);
		for (String seedURL : crawlerDataCombBO.getCrawConfigBO().getSeedURL()) 
			{
				log.debug("seedURL:" + seedURL);
				commCrawlController.addSeed(seedURL);		
			}
		
		commCrawlController.start(URLCrawler.class, crawlerDataCombBO.getCrawConfigBO().getCrawlThreadCount());
		
	}
	

	public void initCrawler(CrawConfigBO crawConfigBO) throws Exception 
	{
		log.debug("#### initCrawler");
		List<CrawConfigBO> crawConfigBOList = crawConfigDAO.selectCrawConfigList(crawConfigBO);
		
		for (CrawConfigBO bo :crawConfigBOList )
		{
			log.info("##### siteCraw Start #####");
			log.info("SiteNm:" + bo.getSiteNm());
			log.info("SeedStrURL:" + bo.getSeedStrURL());
			
			
			CrawlerDataCombBO crawlerDataCombBO = BaseFactory.create(CrawlerDataCombBO.class);
			WebPageInfoBO webPageInfoBO			= BaseFactory.create(WebPageInfoBO.class);
			HashMap<String, WebPageInfoBO>		webPageInfoBOMap;
			
			webPageInfoBO.setSiteConfigSeq(bo.getSiteConfigSeq());

			webPageInfoBOMap = pageInfoDAO.selectPageInfoMap(webPageInfoBO);
			

			bo.setCrawlThreadCount(crawConfigBO.getCrawlThreadCount());
			crawlerDataCombBO.setCrawConfigBO(bo);
			crawlerDataCombBO.setWebPageInfoBOMap(webPageInfoBOMap);			// 기 수집된 내역은 제외 하기 위해서 set 
			
			//crawlerDataCombBO.setWebPageInfoBOComp(null);
			doCrawler(crawlerDataCombBO);
			
			log.info("##### siteCraw end #####");
			
		}
		
	}
	
}
