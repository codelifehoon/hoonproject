package com.zebra.batch.collect;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.zebra.common.BaseFactory;
import com.zebra.common.SpringBeanFactory;
import com.zebra.common.util.ConverterUtil;
import com.zebra.common.util.DateTime;
import com.zebra.process.crawler.CrawlerJob;
import com.zebra.process.crawler.dao.PageInfoDAO;
import com.zebra.process.crawler.domain.CrawConfigBO;
import com.zebra.process.crawler.domain.CrawlerDataCombBO;
import com.zebra.process.crawler.domain.PageConfigBo;
import com.zebra.process.crawler.domain.WebPageInfoBO;
import com.zebra.process.parser.domain.ExpPattenBO;
import com.zebra.process.renew.ReNewJob;

public class ReNewBatch extends BaseDaemon {
	public Logger log = Logger.getLogger(this.getClass());


	
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
		batch_no = 1002;
    	batchID = "ReNewBatchDaemon";
    	batchName = "URL 정보갱신 배치";
    	

		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        System.out.println( simpleDateFormat.format(new Date(System.currentTimeMillis())));
        ReNewJob  reNewJob =  SpringBeanFactory.getBean(ReNewJob.class);
   
		try {CrawConfigBO		crawConfigBO		= BaseFactory.create(CrawConfigBO.class);
		//crawConfigBO.setSiteConfigSeq("100000");
		crawConfigBO.setSiteNm("reNew");
		crawConfigBO.setRowCnt(1000);
		crawConfigBO.setCrawlThreadCount(2);
		
		log.debug("##### initReNewTest start:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
	
		reNewJob.initReNew(crawConfigBO);
		
		log.debug("##### initReNewTest end:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

	
	




}
