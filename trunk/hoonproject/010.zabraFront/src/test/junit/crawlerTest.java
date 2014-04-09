package test.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.zebra.process.crawler.CommCrawlController;
import com.zebra.process.crawler.CrawlerJob;
import com.zebra.process.crawler.CrawlerJobImpl;
import com.zebra.process.crawler.domain.CrawConfigBO;
import com.zebra.process.crawler.domain.CrawlerDataCombBO;
import com.zebra.process.crawler.domain.PageConfigBo;
import com.zebra.process.crawler.domain.WebPageInfoBO;
import com.zebra.process.parser.DomParser;
import com.zebra.process.parser.DomParserImpl;
import com.zebra.process.parser.domain.ExpPattenBO;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class crawlerTest {

	protected static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(crawlerTest.class.getName());
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String log4jConfigPath = "D:/workspace/010.zabraFront/webapps/WEB-INF/properties/local_log4j.xml";
								  
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
	public void CrawlerTest() {
		
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        System.out.println( simpleDateFormat.format(new Date(System.currentTimeMillis())));
        
        
		CrawlerJob  crawlerJob = new CrawlerJobImpl();
		CrawlerDataCombBO crawlerDataCombBO = new CrawlerDataCombBO();
		CrawConfigBO crawConfigBO ;

		
		
		crawConfigBO =  getCrawConfigInit();

		
		
		crawlerDataCombBO.setCrawConfigBO(crawConfigBO);
		try {
			crawlerJob.doCrawler(crawlerDataCombBO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	//@Test
	public void ParsingTest() {
		DomParser  	domParserImpl = new DomParserImpl();
		PageConfigBo	pageConfigBo ;
		WebPageInfoBO		webPageInfoBO = new WebPageInfoBO();
		
		Document doc;
		String htmlString ="";
		String URL = "http://www.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=521537407";
		
		try {
			doc = Jsoup.connect(URL).get();
			htmlString = doc.toString();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pageConfigBo =  getPageConfigInit();
		pageConfigBo.setHtmlString(htmlString);
		

		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

		 
		WebPageInfoBO webPageInfoBONew = domParserImpl.doParsing(pageConfigBo,webPageInfoBO);
		
		assertEquals(webPageInfoBONew.getGoodsNm(),"아도르 미니홈클리닉세트 (산성샴푸+단백질PPT+LPP+퍼펙트헤어필러세트)/헤어팩트리트먼트헤어에센스");
		assertEquals(webPageInfoBONew.getGoodsPrice(),"13,320");
		assertTrue(!webPageInfoBONew.getCate1().equals(""));
		assertTrue(!webPageInfoBONew.getCate2().equals(""));
		assertTrue(!webPageInfoBONew.getCate3().equals(""));
		
		 
		System.out.println(webPageInfoBONew.getGoodsNm());
		System.out.println(webPageInfoBONew.getGoodsPrice());
		System.out.println(webPageInfoBONew.getGoodsImg());
		System.out.println(webPageInfoBONew.getCate1());
		System.out.println(webPageInfoBONew.getCate2());
		System.out.println(webPageInfoBONew.getCate3());
		System.out.println(webPageInfoBONew.getReNewFlag());
		
			 
		}

	//@Test
	public void dataCombTest() throws Exception 
	{
		CommCrawlController crawlControlle = null;
		CrawlConfig crawlConfig = new CrawlConfig();
		crawlConfig.setCrawlStorageFolder("d:\\crawlStorage\\AAA");
		//crawlConfig.setUserAgentString(userAgentString);
		System.out.println(crawlConfig.toString());
		PageFetcher 		pageFetcher = new PageFetcher(crawlConfig);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer  = new RobotstxtServer(robotstxtConfig, pageFetcher);
		
		try {
			crawlControlle = new CommCrawlController(crawlConfig, pageFetcher, robotstxtServer) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		crawlControlle.setCrawlerDataCombBO(new CrawlerDataCombBO());
		
		
		for (int i=0;i<100000;i++)
		{
			WebPageInfoBO webPageInfoBO = new WebPageInfoBO();
			webPageInfoBO.setGoodsNo(String.valueOf(i));
			webPageInfoBO.setGoodsUrl(String.valueOf(i) + "상품명");
			crawlControlle.addWebPageinfoList(webPageInfoBO);
		}
		
		System.out.println("dataCombTest 종료");
	}
/*
	@Test
	public void ReNewTest() throws Exception 
	{
		ReNewJob reNewJobImpl = new ReNewJobImpl();
		CrawlerDataCombBO crawlerDataCombBO  = new CrawlerDataCombBO();
		CrawConfigBO	crawConfigBO = new CrawConfigBO();
		PageConfigBo	PageConfigBo = new PageConfigBo();
		
		PageInfoDAO pageInfoDAO = new PageInfoDAOImpl(); 
		
		crawConfigBO = getCrawConfigInit();
		crawConfigBO.setSiteNm("11st_update");
		crawConfigBO.setCrawlThreadCount(10);
		
		
		crawlerDataCombBO.setCrawConfigBO(crawConfigBO);
		crawlerDataCombBO.setPageConfigBo(getPageConfigInit());
		crawlerDataCombBO.setWebPageInfoBOlist(pageInfoDAO.selectPageInfoListAll());
		crawlerDataCombBO.setWebPageInfoBOMap(ConverterUtil.webPageInfoList2Map( "GoodsNo" , crawlerDataCombBO.getWebPageInfoBOlist()));
		assertTrue(crawlerDataCombBO.compareListMapSize()>=0);
		
		reNewJobImpl.doReNew(crawlerDataCombBO);
		System.out.println("doReNew 종료");
		reNewJobImpl.applyReNewInfo(crawlerDataCombBO);
		
		System.out.println(crawlerDataCombBO.getWebPageInfoBOlist().size());
		System.out.println("ReNewTest 종료");
		
	}

	
	
	
	*/
	

	public CrawConfigBO getCrawConfigInit()
	{
		CrawConfigBO	crawConfigBO = new CrawConfigBO();
		String[] seedURL  = new String[]{"http://www.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=819315530"
				,"http://www.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=563434627"
				};

		crawConfigBO.setSiteConfigSeq("100000");
		crawConfigBO.setSiteNm("11st");
		crawConfigBO.setCrawlThreadCount(1);
		crawConfigBO.setSeedURL(seedURL);
		
		
		return crawConfigBO;
	}

	public PageConfigBo getPageConfigInit()
	{
		PageConfigBo	pageConfigBo = new PageConfigBo();


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

		
/*
		pageConfigBo.setGoodsNmPatten(goodsNmPatten);
		pageConfigBo.setGoodsPricePatten(goodsPricePatten);
		pageConfigBo.setGoodsImgPatten(goodsImgPatten);
		pageConfigBo.setCate1Patten(cate1Patten);
		pageConfigBo.setCate2Patten(cate2Patten);
		pageConfigBo.setCate3Patten(cate3Patten);
*/		
		
		return pageConfigBo;
	}
	
	
	}



