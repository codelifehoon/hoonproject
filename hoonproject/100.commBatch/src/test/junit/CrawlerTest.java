package test.junit;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zebra.business.craw.dao.PageInfoDAO;
import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.business.craw.domain.CrawlerDataCombBO;
import com.zebra.business.craw.domain.ExpPattenBO;
import com.zebra.business.craw.domain.PageConfigBO;
import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.BaseFactory;
import com.zebra.common.BaseConstants;
import com.zebra.common.SampleInterface;
import com.zebra.common.util.DateTime;
import com.zebra.common.util.DebugUtil;
import com.zebra.common.util.PattenUtil;
import com.zebra.process.crawler.CommCrawlController;
import com.zebra.process.crawler.CrawlerJob;
import com.zebra.process.crawregister.CrawMngService;
import com.zebra.process.parser.DomParser;
import com.zebra.process.renew.ReNewJob;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

@RunWith(SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations="classpath:com/zebra/batch/resource/spring-context-common.xml")
public class CrawlerTest {

	protected static final  Logger log = Logger.getLogger(CrawlerTest.class.getName());
	@Autowired CrawlerJob 	crawlerJob ;
	@Autowired DomParser	domParser;
	@Autowired ReNewJob	reNewJob;
	@Autowired PageInfoDAO	pageInfoDAO;
	@Autowired CrawMngService crawMngService ;
	@Autowired(required=false) SampleInterface  sampleInterface;
	
	
 
	 @Test
	public void InterfaceTest()
	{
		
		log.debug("##### InterfaceTest");
		
	}
	 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String log4jConfigPath = "D:/workspace/100.commBatch/src/com/zebra/batch/config/batch_log4j.xml";
								  
		if (new java.io.File(log4jConfigPath).exists() == false)
		{
			throw new java.io.FileNotFoundException("log4j config file is not found ["+log4jConfigPath+"]");
		}
		org.apache.log4j.xml.DOMConfigurator.configure(log4jConfigPath); 
	}
      
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
		
		
	////@Test
	public void CrawlerTest() 
	{
		 
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		log.debug( simpleDateFormat.format(new Date(System.currentTimeMillis())));
        
        CrawlerDataCombBO crawlerDataCombBO = new CrawlerDataCombBO();
		CrawConfigBO crawConfigBO  = BaseFactory.create(CrawConfigBO.class);

		
		crawConfigBO.setCrawlThreadCount(4);
		crawConfigBO.setSiteConfigSeq("100000");
		crawConfigBO.setSiteNm("11st");
		crawConfigBO.setCrawlDepth(1);
		crawConfigBO.setSeedURL(new String[]{"http://deal.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1016796431&trTypeCd=34"});
		
		crawlerDataCombBO.setCrawConfigBO(crawConfigBO);
		
		try {
			CrawlerDataCombBO retVal = crawlerJob.startController(crawlerDataCombBO);
			
			retVal.getVisiteTargetBOlist();
			retVal.getWebPageInfoBOlist();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	////@Test
	public void initCrawlerTest() 
	{
		 
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		log.debug( simpleDateFormat.format(new Date(System.currentTimeMillis())));
        
		CrawConfigBO crawConfigBO  = BaseFactory.create(CrawConfigBO.class);

		
		crawConfigBO.setCrawlThreadCount(5);
		crawConfigBO.setCrawlDepth(1);
		//crawConfigBO.setSiteConfigSeq("100000");
		//crawConfigBO.setSiteNm("11st");
		//crawConfigBO.setSeedURL(new String[]{"http://deal.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1016796431&trTypeCd=34"});
		

		try {
			crawlerJob.initCrawler(crawConfigBO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	
	 //@Test
	public void ParsingTest() throws Exception {
		
		WebPageInfoBO		webPageInfoBO =  BaseFactory.create(WebPageInfoBO.class);
		
		Document doc;
		String htmlString ="";
		String URL = "http://www.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=521537407";
		String LowURL = URL;
		
		try {
			doc = Jsoup.connect(URL).userAgent(BaseConstants.CRAWL_AGENT).get();
			htmlString = doc.toString();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		HashMap<String, ExpPattenBO[]> pattenMap = DataInitClass.getDefaultPagePatten("11st");
		WebPageInfoBO webPageInfoBONew = domParser.doParsing(htmlString,webPageInfoBO, pattenMap);
		
	
		log.debug("#########################");
		log.debug("INFO:" + DebugUtil.debugBo(webPageInfoBONew));
		log.debug("INFO PK_VISIT_SITE_PATTEN:" + PattenUtil.pattenMaches(pattenMap.get(BaseConstants.PK_VISIT_SITE_PATTEN), LowURL) );
		log.debug("INFO PK_VISIT_URL_PATTEN:" + PattenUtil.pattenMaches(pattenMap.get(BaseConstants.PK_VISIT_URL_PATTEN), LowURL));
		log.debug("INFO PK_GOODS_URL_PATTEN :" + PattenUtil.pattenMaches(pattenMap.get(BaseConstants.PK_GOODS_URL_PATTEN), LowURL));
		log.debug("INFO PK_GOODS_NO_PATTEN:" + PattenUtil.pattenString(pattenMap.get(BaseConstants.PK_GOODS_NO_PATTEN), LowURL));
	
		
		}

	//@Test
	public void dataCombTest() throws Exception 
	{
		CommCrawlController crawlControlle = null;
		CrawlConfig crawlConfig = new CrawlConfig();
		crawlConfig.setCrawlStorageFolder(BaseConstants.CRAWL_STORAGE_FOLDER + this.getClass().getName() );
		crawlConfig.setUserAgentString(BaseConstants.CRAWL_AGENT);
		//crawlConfig.setUserAgentString(userAgentString);

		PageFetcher 		pageFetcher = new PageFetcher(crawlConfig);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		robotstxtConfig.setEnabled(false);
		RobotstxtServer robotstxtServer  = new RobotstxtServer(robotstxtConfig, pageFetcher);
		
		try {
			crawlControlle = new CommCrawlController(crawlConfig, pageFetcher, robotstxtServer) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		crawlControlle.setCrawlerDataCombBO(new CrawlerDataCombBO());
		
		
		for (int i=0;i<200;i++)
		{
			WebPageInfoBO webPageInfoBO = new WebPageInfoBO();
			webPageInfoBO.setSiteConfigSeq("100000");
			webPageInfoBO.setGoodsNo(String.valueOf(i));
			webPageInfoBO.setGoodsUrl(String.valueOf(i) + "상품TEST");
			crawlControlle.addWebPageinfoList(webPageInfoBO);
		}
		
		log.debug("dataCombTest 종료");
	}

	//@Test
	public void ReNewTest() throws Exception 
	{
	
		WebPageInfoBO webPageInfoBO = BaseFactory.create(WebPageInfoBO.class);
		
		webPageInfoBO.setSiteConfigSeq("100000");
		webPageInfoBO.setRowCnt(10);
		
		CrawlerDataCombBO 	crawlerDataCombBO 	= BaseFactory.create(CrawlerDataCombBO.class);
		CrawConfigBO		crawConfigBO		= BaseFactory.create(CrawConfigBO.class);
		
		HashMap<String, WebPageInfoBO> webPageInfoBOMap = pageInfoDAO.selectReNewPageInfoMap(webPageInfoBO);
		
		crawConfigBO.setSiteConfigSeq("100000");
		crawConfigBO.setSiteNm("11st");
		crawConfigBO.setCrawlThreadCount(2);
		
		crawlerDataCombBO.setWebPageInfoBOMap(webPageInfoBOMap);		// 데이터의 빠른 갱신을 위해서 조회를 map으로 처리한다.(key: prdNo)
		crawlerDataCombBO.setCrawConfigBO(crawConfigBO);
	
		reNewJob.startController(crawlerDataCombBO);
		reNewJob.applyReNewInfo(crawlerDataCombBO);

		
	}
		
	
	//@Test
	public void initReNewTest() throws Exception 
	{
	
		//2014-04-11 14:04:13,462 DEBUG [test.junit.crawlerTest] ##### initReNewTest start:2014-04-11 14:04:13
		//2014-04-11 14:06:15,083 DEBUG [test.junit.crawlerTest] ##### initReNewTest end:2014-04-11 14:06:15
		
		CrawConfigBO		crawConfigBO		= BaseFactory.create(CrawConfigBO.class);
		//crawConfigBO.setSiteConfigSeq("100000");
		crawConfigBO.setSiteNm("reNew");
		crawConfigBO.setRowCnt(1000);
		crawConfigBO.setCrawlThreadCount(4);
		
		log.debug("##### initReNewTest start:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
	
		reNewJob.initCrawler(crawConfigBO);
		
		log.debug("##### initReNewTest end:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
	}
		
	//@Test
	public void validCrawlSeedURLInfoTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
	{
		 
		CrawlerDataCombBO crawlerDataCombBO = BaseFactory.create(CrawlerDataCombBO.class);
		crawlerDataCombBO.setCrawConfigBO((CrawConfigBO)BaseFactory.create(CrawConfigBO.class));
		crawlerDataCombBO.setPageConfigBO((PageConfigBO)BaseFactory.create(PageConfigBO.class));
		
		crawlerDataCombBO.setPreViewYn("Y");
		crawlerDataCombBO.setCrawConfigBO(DataInitClass.getAuctionCrawConfig());
		crawlerDataCombBO.setPattenMap(DataInitClass.getDefaultPagePatten("Auction"));
		
		
		try {
			 crawlerJob.validCrawlSeedURLInfo(crawlerDataCombBO);
			log.debug("########## getVisiteTargetBOlist");
			log.debug(DebugUtil.debugBoList(crawlerDataCombBO.getVisiteTargetBOlist()));
			log.debug("########## getWebPageInfoBOlist");
			log.debug(DebugUtil.debugBoList(crawlerDataCombBO.getWebPageInfoBOlist()));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
		
	//@Test
	public void validCrawlerPrdInfoTest() throws Exception
	{
		
		
		String URL = "http://deal.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1029870614&trTypeCd=34";
		CrawlerDataCombBO crawlerDataCombBO = BaseFactory.create(CrawlerDataCombBO.class);
		crawlerDataCombBO.setCrawConfigBO((CrawConfigBO)BaseFactory.create(CrawConfigBO.class));
		crawlerDataCombBO.setPageConfigBO((PageConfigBO)BaseFactory.create(PageConfigBO.class));
		crawlerDataCombBO.setPreViewYn("Y");
		crawlerDataCombBO.setPattenMap(DataInitClass.getDefaultPagePatten("11st"));
		crawlerDataCombBO.getCrawConfigBO().setSeedStrURL(URL);
		
		List<WebPageInfoBO> retList = crawlerJob.validCrawlerPrdInfo(crawlerDataCombBO);

		log.debug("#########################");
		log.debug("INFO PK_VISIT_SITE_PATTEN:" + PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_VISIT_SITE_PATTEN), URL.toLowerCase()) );
		log.debug("INFO PK_VISIT_URL_PATTEN:" + PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_VISIT_URL_PATTEN), URL.toLowerCase()));
		log.debug("INFO PK_GOODS_URL_PATTEN :" + PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_GOODS_URL_PATTEN), URL.toLowerCase()));
		log.debug("INFO PK_GOODS_NO_PATTEN:" + PattenUtil.pattenString(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_GOODS_NO_PATTEN), URL.toLowerCase()));
		log.debug("INFO:" + DebugUtil.debugBoList(retList));
	}
	
	//@Test
	public void addCrawConfigTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		CrawlerDataCombBO crawlerDataCombBO = BaseFactory.create(CrawlerDataCombBO.class);
		crawlerDataCombBO.setCrawConfigBO((CrawConfigBO)BaseFactory.create(CrawConfigBO.class));
		
		crawlerDataCombBO.setCrawConfigBO(DataInitClass.get11stCrawConfig());
		crawlerDataCombBO.setPattenMap(DataInitClass.getDefaultPagePatten("11st"));
		
		crawMngService.addCrawInfo(crawlerDataCombBO);
		
		
	}
	
	 @Test
	public void getHtmlTest() throws Exception {
			
			WebPageInfoBO		webPageInfoBO =  BaseFactory.create(WebPageInfoBO.class);
			
			Document doc;
			String htmlString ="";
			String url = "http://mobile.auction.co.kr/Item/ViewItem.aspx?ItemNo=A582441053";
			String LowURL = url;
			
			try {
				
				//doc = Jsoup.connect(url).userAgent(BaseConstants.CRAWL_AGENT).get();
				//htmlString = doc.toString();
				
				HttpClient 	httpClient = new HttpClient();
				PostMethod	httpGet	= new PostMethod(url);
				
				
				httpClient.executeMethod(httpGet);
				htmlString = httpGet.getResponseBodyAsString();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			HashMap<String, ExpPattenBO[]> pattenMap = DataInitClass.getDefaultPagePatten("11st");
			WebPageInfoBO webPageInfoBONew = domParser.doParsing(htmlString,webPageInfoBO, pattenMap);
			
		
			log.debug("#########################");
			log.debug("INFO:" + DebugUtil.debugBo(webPageInfoBONew));
			log.debug("INFO PK_VISIT_SITE_PATTEN:" + PattenUtil.pattenMaches(pattenMap.get(BaseConstants.PK_VISIT_SITE_PATTEN), LowURL) );
			log.debug("INFO PK_VISIT_URL_PATTEN:" + PattenUtil.pattenMaches(pattenMap.get(BaseConstants.PK_VISIT_URL_PATTEN), LowURL));
			log.debug("INFO PK_GOODS_URL_PATTEN :" + PattenUtil.pattenMaches(pattenMap.get(BaseConstants.PK_GOODS_URL_PATTEN), LowURL));
			log.debug("INFO PK_GOODS_NO_PATTEN:" + PattenUtil.pattenString(pattenMap.get(BaseConstants.PK_GOODS_NO_PATTEN), LowURL));
			log.debug(htmlString);
			
			}
		
		
	}



