package com.zebra.process.crawler;

import java.io.IOException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.extern.log4j.Log4j;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.zebra.business.craw.dao.CrawConfigDAO;
import com.zebra.business.craw.dao.PageInfoDAO;
import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.business.craw.domain.CrawlerDataCombBO;
import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.BaseFactory;
import com.zebra.common.util.ConverterUtil;
import com.zebra.common.util.DebugUtil;
import com.zebra.common.util.PattenUtil;

import com.zebra.process.parser.DomParser;



@Service
@Log4j
public class CrawlerJobImpl extends JobBase implements CrawlerJob {

	@Autowired	CrawConfigDAO	crawConfigDAO;
	@Autowired	PageInfoDAO		pageInfoDAO;
	@Autowired 	DomParser		domParser;
	
	
	/* (non-Javadoc)
	 * @see com.zebra.process.crawler.CrawlerJob#doCrawler(com.zebra.process.crawler.domain.CrawlerDataCombBO)
	 */
	@Override
	public CrawlerDataCombBO startController(CrawlerDataCombBO crawlerDataCombBO) throws Exception 
	{
		
		CommCrawlController crawlControlle = super.initCrawController(crawlerDataCombBO);
		
		///////////////////////////////////
		//commCrawlController.startNonBlocking(_c, numberOfCrawlers)
		for (String seedURL : crawlerDataCombBO.getCrawConfigBO().getSeedURL()) 
			{
				log.debug("seedURL:" + seedURL);
				crawlControlle.addSeed(seedURL);		
			}
		

		
		crawlControlle.start(URLCrawler.class, crawlerDataCombBO.getCrawConfigBO().getCrawlThreadCount());

		return crawlerDataCombBO ;
	}

	@Override
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
			startController(crawlerDataCombBO);
			
			log.info("##### siteCraw end #####");
			
		}
		
	}
	
	
	@Override
	public List<WebPageInfoBO> validCrawlerPrdInfo(CrawlerDataCombBO crawlerDataCombBO) throws Exception 
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
				// doc = Jsoup.connect(URL).userAgent(crawlerDataCombBO.getCrawConfigBO().getCrawlAgent()).get();
				// htmlString = doc.toString();
				
				HttpClient 	httpClient = new HttpClient();
				PostMethod	httpGet	= new PostMethod(URL);
				
				httpClient.executeMethod(httpGet);
				htmlString = httpGet.getResponseBodyAsString();
				
				
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
		return startController(crawlerDataCombBO);
		
		
	}

}
