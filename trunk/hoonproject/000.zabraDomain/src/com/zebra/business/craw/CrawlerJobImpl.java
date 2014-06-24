package com.zebra.business.craw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.zebra.business.craw.dao.CrawConfigDAO;
import com.zebra.business.craw.dao.PageInfoDAO;
import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.business.craw.domain.CrawlerDataCombBO;
import com.zebra.business.craw.domain.PageConfigBO;
import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.BaseFactory;
import com.zebra.common.BaseConstants;
import com.zebra.common.util.ConverterUtil;
import com.zebra.common.util.DebugUtil;
import com.zebra.common.util.PattenUtil;
import com.zebra.process.parser.DomParser;


import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

@Service
public class CrawlerJobImpl implements CrawlerJob {

	protected static final Logger log = Logger.getLogger(CrawlerJob.class.getName());
	
	@Autowired	CrawConfigDAO	crawConfigDAO;
	@Autowired	PageInfoDAO		pageInfoDAO;
	@Autowired 	DomParser		domParser;
	
	
	/* (non-Javadoc)
	 * @see com.zebra.process.crawler.CrawlerJob#doCrawler(com.zebra.process.crawler.domain.CrawlerDataCombBO)
	 */
	public CrawlerDataCombBO doCrawler(CrawlerDataCombBO crawlerDataCombBO) throws Exception 
	{
		log.debug("##### doCrawler crawlerDataCombBO::" + DebugUtil.debugBo(crawlerDataCombBO));
		
		CommCrawlController commCrawlController = null;
		CrawlConfig crawlConfig = new CrawlConfig();
		crawlConfig.setCrawlStorageFolder(BaseConstants.CRAWL_STORAGE_FOLDER + this.getClass().getName() + "\\" + crawlerDataCombBO.getCrawConfigBO().getSiteNm());
		crawlConfig.setUserAgentString(crawlerDataCombBO.getCrawConfigBO().getCrawlAgent());
		//crawlConfig.setResumableCrawling(false);
		if (crawlerDataCombBO.getCrawConfigBO().getCrawlDepth() > 0) crawlConfig.setMaxPagesToFetch(crawlerDataCombBO.getCrawConfigBO().getCrawlDepth());
		
		PageFetcher 		pageFetcher = new PageFetcher(crawlConfig);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		robotstxtConfig.setEnabled(false);
		RobotstxtServer robotstxtServer  = new RobotstxtServer(robotstxtConfig, pageFetcher);
		
		try {
			commCrawlController = new CommCrawlController(crawlConfig, pageFetcher, robotstxtServer) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		
		commCrawlController.setCrawlerDataCombBO(crawlerDataCombBO);
		//commCrawlController.startNonBlocking(_c, numberOfCrawlers)
		for (String seedURL : crawlerDataCombBO.getCrawConfigBO().getSeedURL()) 
			{
				log.debug("seedURL:" + seedURL);
				commCrawlController.addSeed(seedURL);		
			}
		

		
		commCrawlController.start(URLCrawler.class, crawlerDataCombBO.getCrawConfigBO().getCrawlThreadCount());

		return crawlerDataCombBO ;
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
	
	
	@Override
	public List<WebPageInfoBO> validCrawlerPrdInfo( CrawlerDataCombBO	crawlerDataCombBO) 
	{

		WebPageInfoBO		webPageInfoBO 		=  BaseFactory.create(WebPageInfoBO.class);
		List<WebPageInfoBO>	retList			=  new ArrayList<WebPageInfoBO>();
		
		
		Document doc;
		String htmlString ="";
		String[] URLs = crawlerDataCombBO.getCrawConfigBO().getSeedURL();
		
		for (String URL : URLs)
		{
			CrawlerDataCombBO cdcBo = BaseFactory.create(CrawlerDataCombBO.class);
			
			try {
				doc = Jsoup.connect(URL).userAgent(crawlerDataCombBO.getCrawConfigBO().getCrawlAgent()).get();
				htmlString = doc.toString();
				log.debug("########### html:"+ crawlerDataCombBO.getCrawConfigBO().getCrawlAgent() + "###"+ htmlString);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			WebPageInfoBO webPageInfoBONew = domParser.doParsing(htmlString,webPageInfoBO, crawlerDataCombBO.getPattenMap());



			
			retList.add(webPageInfoBONew);
		}
		
		return retList;
	
		
		
	}


	@Override
	public CrawlerDataCombBO validCrawlSeedURLInfo( CrawlerDataCombBO crawlerDataCombBO) throws Exception {
		return doCrawler(crawlerDataCombBO);
		
		
	}
	
}
