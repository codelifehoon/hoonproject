/**
 * @FileName  : JobBase.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 7. 16. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.process.crawler;

import com.zebra.business.craw.domain.CrawConfigBO;

import com.zebra.business.craw.domain.CrawlerDataCombBO;
import com.zebra.common.BaseConstants;
import com.zebra.common.exception.BaseException;
import com.zebra.common.util.DateTime;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public abstract class JobBase {

	/**
	 * 
	 */
	public JobBase() {
		super();
	}

	/**
	 * @param crawlerDataCombBO
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	protected CommCrawlController initCrawController(CrawlerDataCombBO crawlerDataCombBO) throws Exception,
			BaseException {
				
				CommCrawlController crawlControlle = null;
				CrawlConfig crawlConfig = new CrawlConfig();
				crawlConfig.setCrawlStorageFolder(BaseConstants.CRAWL_STORAGE_FOLDER + this.getClass().getName() + "\\"+ DateTime.getFormatString("yyyyMMdd-HHmmss")  + crawlerDataCombBO.getCrawConfigBO().getSiteNm());
				crawlConfig.setUserAgentString(crawlerDataCombBO.getCrawConfigBO().getCrawlAgent());
				crawlConfig.setFollowRedirects(false);
				crawlConfig.setConnectionTimeout(1500);
				crawlConfig.setSocketTimeout(1000);
				crawlConfig.setPolitenessDelay(1);
				
				//crawlConfig.setResumableCrawling(false);
				if (crawlerDataCombBO.getCrawConfigBO().getCrawlDepth() > 0) crawlConfig.setMaxPagesToFetch(crawlerDataCombBO.getCrawConfigBO().getCrawlDepth());
				
				PageFetcher 		pageFetcher = new PageFetcher(crawlConfig);
				RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
				robotstxtConfig.setEnabled(false);
				RobotstxtServer robotstxtServer  = new RobotstxtServer(robotstxtConfig, pageFetcher);
				
				crawlControlle = new CommCrawlController(crawlConfig, pageFetcher, robotstxtServer) ;
				
				crawlControlle.setCrawlerDataCombBO(crawlerDataCombBO);
				return crawlControlle;
			}
	
}