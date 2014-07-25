package com.zebra.batch.collect;

import static org.junit.Assert.assertTrue;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.zebra.business.craw.dao.CrawConfigDAO;
import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.business.craw.domain.ExpPattenBO;
import com.zebra.common.BaseFactory;
import com.zebra.common.SpringBeanFactory;
import com.zebra.common.util.ConverterUtil;
import com.zebra.common.util.DateTime;
import com.zebra.process.crawler.CrawlerJob;

import com.zebra.process.renew.ReNewJob;

public class ReNewBatch extends BaseDaemon {
	public static final 	Logger log 		= Logger.getLogger(ReNewBatch.class.getName());
	private  ReNewJob  		reNewJob 		= SpringBeanFactory.getBean(ReNewJob.class);
	private  CrawConfigDAO	crawConfigDAO	= SpringBeanFactory.getBean(CrawConfigDAO.class);
	private final int		SLEEP_TIME = 1000*60; // 
	
/*
 * 메인 시작
 * */
	public static void main( String[] args )
	{
		
		try {
		ReNewBatch batch = new ReNewBatch();
		batch.run(args);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
    }


	public void run(String[] args) throws Exception
	{
		batchNo = 1002;
    	batchID = "ReNewBatchDaemon";
    	batchName = "URL 정보갱신 배치";
    	

    	
    	/*TO-DO*/
    	//agent 를 table에서 가져오는 방식으로 변경이 필요함.
    	// 변경이후 작업할것
    	 
    	 
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		log.debug( simpleDateFormat.format(new Date(System.currentTimeMillis())));
       
   
		try {

		CrawConfigBO		crawConfigBO	= BaseFactory.create(CrawConfigBO.class);
		crawConfigBO.setSiteConfigSeq("100031");
		crawConfigBO =  crawConfigDAO.selectCrawConfigList(crawConfigBO).get(0);
		
		crawConfigBO.setRowCnt(1000);
		crawConfigBO.setCrawlThreadCount(1);
		//crawConfigBO.setCrawlDepth(1);
	
		//while (true)
		{
		log.error("##### initReNewTest start:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
		reNewJob.initCrawler(crawConfigBO);
		log.error("##### initReNewTest end:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
		Thread.sleep(SLEEP_TIME);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

	
	




}
