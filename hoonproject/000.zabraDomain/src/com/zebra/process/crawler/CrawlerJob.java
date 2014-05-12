package com.zebra.process.crawler;


import java.util.List;

import org.springframework.stereotype.Service;
import com.zebra.process.crawler.domain.CrawConfigBO;
import com.zebra.process.crawler.domain.CrawlerDataCombBO;
import com.zebra.process.crawler.domain.WebPageInfoBO;


public interface CrawlerJob {

	public CrawlerDataCombBO doCrawler(CrawlerDataCombBO crawlerDataCombBO) throws Exception;

	public void initCrawler(CrawConfigBO crawConfigBO) throws Exception;

	public abstract List<WebPageInfoBO> validCrawlerPrdInfo(CrawlerDataCombBO	crawlerDataCombBO);

	public CrawlerDataCombBO validCrawlSeedURLInfo(CrawlerDataCombBO crawlerDataCombBO) throws Exception;


}