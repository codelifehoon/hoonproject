package com.zebra.batch.collect;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.apache.ibatis.executor.loader.ProxyFactory;
import org.apache.log4j.Logger;

import com.zebra.common.BaseFactory;
import com.zebra.common.SpringBeanFactory;
import com.zebra.common.util.ConverterUtil;
import com.zebra.process.crawler.CrawlerJob;
import com.zebra.process.crawler.CrawlerJobImpl;
import com.zebra.process.crawler.dao.PageInfoDAO;
import com.zebra.process.crawler.domain.CrawConfigBO;
import com.zebra.process.crawler.domain.CrawlerDataCombBO;
import com.zebra.process.crawler.domain.PageConfigBo;
import com.zebra.process.crawler.domain.WebPageInfoBO;
import com.zebra.process.parser.domain.ExpPattenBO;
import com.zebra.process.renew.ReNewJob;
import com.zebra.process.renew.ReNewJobImpl;




public class CrawBatch extends BaseDaemon {
	public Logger log = Logger.getLogger(this.getClass());


	
/*
 * 메인 시작
 * */
	public static void main( String[] args )
	{
		
		try {
        CrawBatch batch = new CrawBatch();
        batch.run(args);
        //batch.ReNewTest();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
    }


	public void run(String[] args) throws Exception
	{
		batch_no = 1001;
    	batchID = "CrawBatchDaemon";
    	batchName = "신규 URL 수집 데몬";
    	

		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        System.out.println( simpleDateFormat.format(new Date(System.currentTimeMillis())));
        
   
		CrawlerJob  crawlerJob =  SpringBeanFactory.getBean(CrawlerJob.class);
		try {
			crawlerJob.initCrawler(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

	
	public void ReNewTest() throws Exception 
	{
		ReNewJob reNewJobImpl = SpringBeanFactory.getBean(ReNewJob.class); 
		PageInfoDAO pageInfoDAO = SpringBeanFactory.getBean(PageInfoDAO.class); 
		
		CrawlerDataCombBO crawlerDataCombBO  = BaseFactory.create(CrawlerDataCombBO.class);
		CrawConfigBO	crawConfigBO = BaseFactory.create(CrawConfigBO.class);
		PageConfigBo	pageConfigBo = BaseFactory.create(PageConfigBo.class);
		WebPageInfoBO	webPageInfoBO= BaseFactory.create(WebPageInfoBO.class);
		
		
		
		
		crawConfigBO = getCrawConfigInit();
		crawConfigBO.setSiteNm("11st_update");
		crawConfigBO.setCrawlThreadCount(10);
		
		
		crawlerDataCombBO.setCrawConfigBO(crawConfigBO);
		crawlerDataCombBO.setPageConfigBo(getPageConfigInit());
		crawlerDataCombBO.setWebPageInfoBOlist(pageInfoDAO.selectPageInfoList(webPageInfoBO));
		crawlerDataCombBO.setWebPageInfoBOMap(ConverterUtil.webPageInfoList2Map( "GoodsNo" , crawlerDataCombBO.getWebPageInfoBOlist()));
		assertTrue(crawlerDataCombBO.compareListMapSize()>=0);
		
		//reNewJobImpl.doReNew(crawlerDataCombBO);
		System.out.println("doReNew 종료");
		//reNewJobImpl.applyReNewInfo(crawlerDataCombBO);
		
		System.out.println(crawlerDataCombBO.getWebPageInfoBOlist().size());
		System.out.println("ReNewTest 종료");
		
	}


	

	public CrawConfigBO getCrawConfigInit()
	{
		CrawConfigBO	crawConfigBO = new CrawConfigBO();

		ExpPattenBO[] expVisitSitePatten  	= new ExpPattenBO[1];
		ExpPattenBO[] expVisitURLPatten  	= new ExpPattenBO[1];
		ExpPattenBO[] expVisitGoodsPatten  	= new ExpPattenBO[1];
		
		ExpPattenBO[] expGoodsURLPatten  = new ExpPattenBO[1];
		ExpPattenBO[] expGoodsNoPatten  	= new ExpPattenBO[1];
		
		
		expVisitSitePatten[0]			= new ExpPattenBO(".*(11st.co.kr).*");
		expVisitURLPatten[0]		= new ExpPattenBO(".*(\\.(html|tmall)).*");
		expGoodsURLPatten[0]		= new ExpPattenBO(".*(sellerproductdetail.tmall?).*");
		expGoodsNoPatten[0]		= new ExpPattenBO("prdno=\\d*");
		
		

		crawConfigBO.setSiteNm("11st");
		crawConfigBO.setCrawlThreadCount(1);
		
		
		return crawConfigBO;
	}

	public PageConfigBo getPageConfigInit()
	{
		PageConfigBo	pageConfigBo = new PageConfigBo();

		ExpPattenBO[] goodsNoPatten  	= new ExpPattenBO[1];
		ExpPattenBO[] goodsNmPatten  	= new ExpPattenBO[1];
		ExpPattenBO[] goodsPricePatten  = new ExpPattenBO[1];
		ExpPattenBO[] goodsURLPatten  	= new ExpPattenBO[1];
		ExpPattenBO[] goodsImgPatten  	= new ExpPattenBO[1];
		ExpPattenBO[] cate1Patten  	= new ExpPattenBO[1];
		ExpPattenBO[] cate2Patten  	= new ExpPattenBO[1];
		ExpPattenBO[] cate3Patten  	= new ExpPattenBO[1];
		
		goodsNoPatten[0]			= new ExpPattenBO("","");
		goodsNmPatten[0]		= new ExpPattenBO("div#layBodyWrap div#layBody div.prdc_wrap div#productInfoMain.prdc_top_wrap  div.prdc_top_left div.prdc_heading_v2 h2","");
		goodsPricePatten[0]		= new ExpPattenBO("div#layBodyWrap div#layBody div.prdc_wrap div#productInfoMain.prdc_top_wrap div.prdc_top_left div#prdcInfoColumn2.prdc_info_column2 div.prdc_default_info div.prdc_price_info ul li strong.prdc_price em");
		goodsURLPatten[0]		= new ExpPattenBO("","");
		goodsImgPatten[0]		= new ExpPattenBO("","");
		cate1Patten[0]				= new ExpPattenBO("div#location_boxid_1 button#headSel_1","");
		cate2Patten[0]				= new ExpPattenBO("div#location_boxid_2 button#headSel_2","");
		cate3Patten[0]				= new ExpPattenBO("div#location_boxid_3 button#headSel_3","");


		
		
		return pageConfigBo;
	}
	
	


}
