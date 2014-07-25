package com.zebra.process.crawler;


import java.util.List;




import org.springframework.stereotype.Service;
import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.business.craw.domain.CrawlerDataCombBO;
import com.zebra.business.craw.domain.WebPageInfoBO;


public interface CrawlerJob {

	public void initCrawler(CrawConfigBO crawConfigBO) throws Exception;

	public List<WebPageInfoBO> validCrawlerPrdInfo(CrawlerDataCombBO	crawlerDataCombBO) throws Exception;

	public CrawlerDataCombBO validCrawlSeedURLInfo(CrawlerDataCombBO crawlerDataCombBO) throws Exception;

	/**
	 * @param crawlerDataCombBO
	 * @return
	 * @throws Exception
	 */
	public CrawlerDataCombBO startController(CrawlerDataCombBO crawlerDataCombBO)
			throws Exception;


}