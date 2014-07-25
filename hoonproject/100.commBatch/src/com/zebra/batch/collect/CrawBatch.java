package com.zebra.batch.collect;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.common.BaseFactory;
import com.zebra.common.SpringBeanFactory;
import com.zebra.process.crawler.CrawlerJob;




public class CrawBatch extends BaseDaemon {
	protected static final Logger log    = Logger.getLogger(BaseDaemon.class.getName());


	
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
		batchNo = 1001;
    	batchID = "CrawBatchDaemon";
    	batchName = "신규 URL 수집 데몬";


		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        log.debug( simpleDateFormat.format(new Date(System.currentTimeMillis())));
        
   
		CrawlerJob  crawlerJob =  SpringBeanFactory.getBean(CrawlerJob.class);
		try {
			CrawConfigBO crawConfigBO  = BaseFactory.create(CrawConfigBO.class);
			crawConfigBO.setCrawlThreadCount(5);
			
			if (args.length == 1) crawConfigBO.setSiteConfigSeq(args[0]);

			crawlerJob.initCrawler(crawConfigBO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

	
	


}
