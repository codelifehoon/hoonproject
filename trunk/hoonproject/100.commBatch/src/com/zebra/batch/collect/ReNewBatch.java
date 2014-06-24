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
import com.zebra.process.crawler.domain.PageConfigBO;
import com.zebra.process.crawler.domain.WebPageInfoBO;
import com.zebra.process.parser.domain.ExpPattenBO;
import com.zebra.process.renew.ReNewJob;

public class ReNewBatch extends BaseDaemon {
	public static final Logger log = Logger.getLogger(ReNewBatch.class.getName());


	
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
    	agent 를 table에서 가져오는 방식으로 변경이 필요함.
    	 변경이후 작업할것
    	 
    	 
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		log.debug( simpleDateFormat.format(new Date(System.currentTimeMillis())));
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
