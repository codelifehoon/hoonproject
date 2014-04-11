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
