package com.zebra.process.renew;

import org.springframework.stereotype.Service;

import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.business.craw.domain.CrawlerDataCombBO;


public interface ReNewJob {

	public long doReNew(CrawlerDataCombBO crawlerDataCombBO) throws Exception;
	
	public long applyReNewInfo(CrawlerDataCombBO crawlerDataCombBO) throws Exception;

	public void initReNew(CrawConfigBO  crawConfigBO);

	
	
}