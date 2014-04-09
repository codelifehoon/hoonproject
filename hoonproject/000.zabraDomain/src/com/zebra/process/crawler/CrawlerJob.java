package com.zebra.process.crawler;

import org.springframework.stereotype.Service;

import com.zebra.process.crawler.domain.CrawConfigBO;
import com.zebra.process.crawler.domain.CrawlerDataCombBO;


public interface CrawlerJob {

	public void doCrawler(CrawlerDataCombBO crawlerDataCombBO) throws Exception;

	public void initCrawler(CrawConfigBO crawConfigBO) throws Exception;


}