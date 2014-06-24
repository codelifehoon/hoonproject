package test.junit;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Mockito.*;

import com.zebra.common.BaseFactory;
import com.zebra.common.BaseConstants;
import com.zebra.common.SampleInterface;
import com.zebra.common.SpringBeanFactory;
import com.zebra.common.dao.CommonPattenCodeDao;
import com.zebra.common.util.CmnUtil;
import com.zebra.common.util.DateTime;
import com.zebra.common.util.DebugUtil;
import com.zebra.common.util.PattenUtil;
import com.zebra.process.crawler.CommCrawlController;
import com.zebra.process.crawler.CrawlerJob;
import com.zebra.process.crawler.CrawlerJobImpl;
import com.zebra.process.crawler.dao.CrawConfigDAO;
import com.zebra.process.crawler.dao.PageInfoDAO;
import com.zebra.process.crawler.domain.CrawConfigBO;
import com.zebra.process.crawler.domain.CrawlerDataCombBO;
import com.zebra.process.crawler.domain.PageConfigBO;
import com.zebra.process.crawler.domain.WebPageInfoBO;
import com.zebra.process.parser.DomParser;
import com.zebra.process.parser.DomParserImpl;
import com.zebra.process.parser.domain.ExpPattenBO;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import com.zebra.process.renew.ReNewJob;

@RunWith(SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations="classpath:com/zebra/batch/resource/spring-context-common.xml")
public class CrawlerTest {

	protected static final  Logger log = Logger.getLogger(CrawlerTest.class.getName());
	@Autowired CrawlerJob 	crawlerJob ;
	@Autowired DomParser	domParser;
	@Autowired ReNewJob	reNewJob;
	@Autowired PageInfoDAO	pageInfoDAO;
	@Autowired(required=false) SampleInterface  sampleInterface;
	
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
 
	@Test
	public void InterfaceTest()
	{
		
		sampleInterface.doEtc("","");
		
	}
	//@Test
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
			CrawlerDataCombBO retVal = crawlerJob.doCrawler(crawlerDataCombBO);
			
			retVal.getVisiteTargetBOlist();
			retVal.getWebPageInfoBOlist();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	//@Test
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
	
	
	
	 @Test
	public void ParsingTest() {
		
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

		
		HashMap<String, ExpPattenBO[]> pattenMap = getPagePatten();
		WebPageInfoBO webPageInfoBONew = domParser.doParsing(htmlString,webPageInfoBO, pattenMap);
		
	
		log.debug("#########################");
		log.debug("INFO:" + DebugUtil.debugBo(webPageInfoBONew));
		log.debug("INFO PK_VISIT_SITE_PATTEN:" + PattenUtil.pattenMaches(pattenMap.get(BaseConstants.PK_VISIT_SITE_PATTEN), LowURL) );
		log.debug("INFO PK_VISIT_URL_PATTEN:" + PattenUtil.pattenMaches(pattenMap.get(BaseConstants.PK_VISIT_URL_PATTEN), LowURL));
		log.debug("INFO PK_GOODS_URL_PATTEN :" + PattenUtil.pattenMaches(pattenMap.get(BaseConstants.PK_GOODS_URL_PATTEN), LowURL));
		log.debug("INFO PK_GOODS_NO_PATTEN:" + PattenUtil.pattenString(pattenMap.get(BaseConstants.PK_GOODS_NO_PATTEN), LowURL));
	
		
		}

	@Test
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

	@Test
	public void ReNewTest() throws Exception 
	{
	
		long reNewCnt=0;
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
	
		reNewCnt = reNewJob.doReNew(crawlerDataCombBO);
		reNewJob.applyReNewInfo(crawlerDataCombBO);
		log.debug("##### renew cnt:" + reNewCnt);
		
	}
		
	
	@Test
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
	
		reNewJob.initReNew(crawConfigBO);
		
		log.debug("##### initReNewTest end:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
	}
		
	@Test
	public void validCrawlSeedURLInfoTest() 
	{
		 
		CrawlerDataCombBO crawlerDataCombBO = BaseFactory.create(CrawlerDataCombBO.class);
		crawlerDataCombBO.setCrawConfigBO((CrawConfigBO)BaseFactory.create(CrawConfigBO.class));
		crawlerDataCombBO.setPageConfigBO((PageConfigBO)BaseFactory.create(PageConfigBO.class));
		
		crawlerDataCombBO.setPreViewYn("Y");
		crawlerDataCombBO.getCrawConfigBO().setCrawlThreadCount(1);
		crawlerDataCombBO.getCrawConfigBO().setSiteNm("11st");
		crawlerDataCombBO.getCrawConfigBO().setSeedURL(new String[]{"http://m.11st.co.kr/MW/html/ranking/rankingMain.html"});
		crawlerDataCombBO.getCrawConfigBO().setCrawlDepth(1);
		crawlerDataCombBO.setPattenMap(getPagePatten());
		
		
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
		
	@Test
	public void validCrawlerPrdInfoTest()
	{
		
		
		String URL = "http://deal.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1029870614&trTypeCd=34";
		CrawlerDataCombBO crawlerDataCombBO = BaseFactory.create(CrawlerDataCombBO.class);
		crawlerDataCombBO.setCrawConfigBO((CrawConfigBO)BaseFactory.create(CrawConfigBO.class));
		crawlerDataCombBO.setPageConfigBO((PageConfigBO)BaseFactory.create(PageConfigBO.class));
		crawlerDataCombBO.setPreViewYn("Y");
		crawlerDataCombBO.setPattenMap(getPagePatten());
		crawlerDataCombBO.getCrawConfigBO().setSeedStrURL(URL);
		
	
		List<WebPageInfoBO> retList = crawlerJob.validCrawlerPrdInfo(crawlerDataCombBO);

		
		log.debug("#########################");
		log.debug("INFO PK_VISIT_SITE_PATTEN:" + PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_VISIT_SITE_PATTEN), URL.toLowerCase()) );
		log.debug("INFO PK_VISIT_URL_PATTEN:" + PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_VISIT_URL_PATTEN), URL.toLowerCase()));
		log.debug("INFO PK_GOODS_URL_PATTEN :" + PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_GOODS_URL_PATTEN), URL.toLowerCase()));
		log.debug("INFO PK_GOODS_NO_PATTEN:" + PattenUtil.pattenString(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_GOODS_NO_PATTEN), URL.toLowerCase()));
		log.debug("INFO:" + DebugUtil.debugBoList(retList));
	}
	
	

	private HashMap<String, ExpPattenBO[]> getPagePatten()
	{
		HashMap<String, ExpPattenBO[]>  pattenMap = new HashMap<String, ExpPattenBO[]> ();


		ExpPattenBO[] visitSitePatten  	= new ExpPattenBO[1];
		ExpPattenBO[] visitUrlPatten  = new ExpPattenBO[1];
		ExpPattenBO[] goodsUrlPatten  = new ExpPattenBO[1];
		ExpPattenBO[] goodsNoPatten  = new ExpPattenBO[1];
		
		

		ExpPattenBO[] goodsNmPatten  	= new ExpPattenBO[1];
		ExpPattenBO[] goodsPricePatten  = new ExpPattenBO[1];
		ExpPattenBO[] goodsImgPatten  	= new ExpPattenBO[1];
		ExpPattenBO[] cate1Patten  	= new ExpPattenBO[1];
		ExpPattenBO[] cate2Patten  	= new ExpPattenBO[1];
		ExpPattenBO[] cate3Patten  	= new ExpPattenBO[1];
		
		
		
		goodsNmPatten[0]		= new ExpPattenBO("div#layBodyWrap div#layBody div.prdc_wrap div#productInfoMain.prdc_top_wrap  div.prdc_top_left div.prdc_heading_v2 h2","");
		goodsPricePatten[0]		= new ExpPattenBO("div#layBodyWrap div#layBody div.prdc_wrap div#productInfoMain.prdc_top_wrap div.prdc_top_left div#prdcInfoColumn2.prdc_info_column2 div.prdc_default_info div.prdc_price_info ul li strong.prdc_price em");
		goodsImgPatten[0]		= new ExpPattenBO("","");
		cate1Patten[0]				= new ExpPattenBO("div#location_boxid_1 button#headSel_1","");
		cate2Patten[0]				= new ExpPattenBO("div#location_boxid_2 button#headSel_2","");
		
		cate3Patten[0]				= new ExpPattenBO("div#location_boxid_3 button#headSel_3","");
		visitUrlPatten[0]			= new ExpPattenBO(".*(\\.(html|tmall)).*","");
		visitSitePatten[0]			= new ExpPattenBO(".*(11st.co.kr).*","");
		goodsUrlPatten[0]			= new ExpPattenBO(".*(sellerproductdetail.tmall?).*","");
		goodsNoPatten[0]			= new ExpPattenBO("prdno=\\d*","");
	
		
		
		pattenMap.put(BaseConstants.PK_GOODS_NAME_PATTEN, goodsNmPatten);
		pattenMap.put(BaseConstants.PK_GOODS_PRICE_PATTEN, goodsPricePatten);
		pattenMap.put(BaseConstants.PK_GOODS_IMG_PATTEN, goodsImgPatten);
		pattenMap.put(BaseConstants.PK_CATE1_PATTEN, cate1Patten);
		pattenMap.put(BaseConstants.PK_CATE2_PATTEN, cate2Patten);
		pattenMap.put(BaseConstants.PK_CATE3_PATTEN, cate3Patten);
		
		pattenMap.put(BaseConstants.PK_VISIT_URL_PATTEN, visitUrlPatten);
		pattenMap.put(BaseConstants.PK_VISIT_SITE_PATTEN,visitSitePatten );
		pattenMap.put(BaseConstants.PK_GOODS_URL_PATTEN, goodsUrlPatten);
		pattenMap.put(BaseConstants.PK_GOODS_NO_PATTEN,goodsNoPatten );
		
		
		
		
/*
		pageConfigBo.setGoodsNmPatten(goodsNmPatten);
		pageConfigBo.setGoodsPricePatten(goodsPricePatten);
		pageConfigBo.setGoodsImgPatten(goodsImgPatten);
		pageConfigBo.setCate1Patten(cate1Patten);
		pageConfigBo.setCate2Patten(cate2Patten);
		pageConfigBo.setCate3Patten(cate3Patten);
*/		
		
		return pattenMap;
	}
	

	
	
	}


