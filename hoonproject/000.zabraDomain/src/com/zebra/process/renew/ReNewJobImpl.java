package com.zebra.process.renew;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebra.common.BaseFactory;
import com.zebra.common.BaseConstants;
import com.zebra.common.util.ConverterUtil;
import com.zebra.common.util.DebugUtil;
import com.zebra.process.crawler.CommCrawlController;
import com.zebra.process.crawler.URLCrawler;
import com.zebra.process.crawler.dao.PageInfoDAO;
import com.zebra.process.crawler.dao.PageInfoDAOImpl;
import com.zebra.process.crawler.domain.CrawConfigBO;
import com.zebra.process.crawler.domain.CrawlerDataCombBO;
import com.zebra.process.crawler.domain.WebPageInfoBO;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;


@Service
public class ReNewJobImpl implements ReNewJob {

	protected static final  Logger log = Logger.getLogger(ReNewJobImpl.class.getName());
	@Autowired PageInfoDAO pageInfoDAO;
	/* (non-Javadoc)
	 * @see com.zebra.process.renew.ReNewJob#doReNew(com.zebra.process.crawler.domain.CrawlerDataCombBO)
	 */
	public long doReNew(CrawlerDataCombBO crawlerDataCombBO) throws Exception
	{
		
		log.debug("##### doReNew crawlerDataCombBO::" + DebugUtil.debugBo(crawlerDataCombBO));
		
		long crawCount = 0;
		

		CommCrawlController crawlControlle = null;
		CrawlConfig crawlConfig = new CrawlConfig();
		crawlConfig.setCrawlStorageFolder(BaseConstants.CRAWL_STORAGE_FOLDER + this.getClass().getName() +  "\\" + crawlerDataCombBO.getCrawConfigBO().getSiteNm());
		crawlConfig.setUserAgentString(crawlerDataCombBO.getCrawConfigBO().getCrawlAgent());
		
		PageFetcher 		pageFetcher = new PageFetcher(crawlConfig);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		robotstxtConfig.setEnabled(false);
		RobotstxtServer robotstxtServer  = new RobotstxtServer(robotstxtConfig, pageFetcher);
		List<WebPageInfoBO> webPageInfoBOList;
		
		
		try {
			crawlControlle = new CommCrawlController(crawlConfig, pageFetcher, robotstxtServer) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		webPageInfoBOList = ConverterUtil.webPageInfoMap2List(crawlerDataCombBO.getWebPageInfoBOMap());
		crawlControlle.setCrawlerDataCombBO(crawlerDataCombBO);
				
		for (WebPageInfoBO  webPageInfoBO : webPageInfoBOList ) crawlControlle.addSeed(webPageInfoBO.getGoodsUrl());
		
		crawlControlle.start(PageCrawler.class, crawlerDataCombBO.getCrawConfigBO().getCrawlThreadCount());
			
		
		return crawCount;
	}

	public long applyReNewInfo(CrawlerDataCombBO crawlerDataCombBO)
			throws Exception {
		
		List<WebPageInfoBO> webPageInfoBOList =  ConverterUtil.webPageInfoMap2List(crawlerDataCombBO.getWebPageInfoBOMap()) ;
		pageInfoDAO.updateReNewPageInfoList(webPageInfoBOList);
		
		return webPageInfoBOList.size();
	}
	
	
	public void initReNew(CrawConfigBO  crawConfigBO)
	{
	
		long reNewCnt=1 ;
		WebPageInfoBO webPageInfoBO = BaseFactory.create(WebPageInfoBO.class);
		
		webPageInfoBO.setSiteConfigSeq(crawConfigBO.getSiteConfigSeq());
		webPageInfoBO.setRowCnt(crawConfigBO.getRowCnt());
		
		while (reNewCnt > 0)
		{
			CrawlerDataCombBO crawlerDataCombBO = BaseFactory.create(CrawlerDataCombBO.class);
			
			HashMap<String, WebPageInfoBO> webPageInfoBOMap = pageInfoDAO.selectReNewPageInfoMap(webPageInfoBO);
			
			reNewCnt = webPageInfoBOMap.size();
			crawlerDataCombBO.setWebPageInfoBOMap(webPageInfoBOMap);		// 데이터의 빠른 갱신을 위해서 조회를 map으로 처리한다.(key: prdNo)
			crawlerDataCombBO.setCrawConfigBO(crawConfigBO);
			
			try {
				doReNew(crawlerDataCombBO);
				reNewCnt = applyReNewInfo(crawlerDataCombBO);
				log.debug("##### renew cnt:" + reNewCnt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	}
	
	
	
}