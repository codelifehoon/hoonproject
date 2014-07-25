/**
 * @FileName  : TaskActon.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 7. 18. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.process.action.taskTool;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.zebra.business.craw.dao.CrawConfigDAO;
import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.common.BaseFactory;
import com.zebra.common.util.DateTime;
import com.zebra.process.action.AuthAction;
import com.zebra.process.renew.ReNewJob;


@Log4j
@Controller
public class TaskActon extends AuthAction {

	@Autowired private  ReNewJob  		reNewJob;
	@Autowired private  CrawConfigDAO	crawConfigDAO;
	private  static final	int	DEALY_JOB	= 1000*60*10 ;

	
	//@Scheduled(cron="*/10 * * * * *")
	@Scheduled(fixedDelay = DEALY_JOB)
	public void Task_st11_Update()
	{
		log.error("##### Task_st11_Update start:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
		
		CrawConfigBO		crawConfigBO	= BaseFactory.create(CrawConfigBO.class);
		crawConfigBO.setSiteConfigSeq("100027");
		
		RunCrawUpdateTask(crawConfigBO);
	
	}
	
	@Scheduled(fixedDelay =DEALY_JOB)
	public void Task_A_Update()
	{
		log.error("##### Task_A_Update start:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
		
		CrawConfigBO		crawConfigBO	= BaseFactory.create(CrawConfigBO.class);
		crawConfigBO.setSiteConfigSeq("100028");
		
		RunCrawUpdateTask(crawConfigBO);
		
	}
	
	@Scheduled(fixedDelay =DEALY_JOB)
	public void Task_G_Update()
	{
		log.error("##### Task_G_Update start:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
		
		CrawConfigBO		crawConfigBO	= BaseFactory.create(CrawConfigBO.class);
		crawConfigBO.setSiteConfigSeq("100031");
		
		RunCrawUpdateTask(crawConfigBO);
		
	}
	

	/**
	 * @param crawConfigBO
	 */
	private void RunCrawUpdateTask(CrawConfigBO crawConfigBO) {
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		log.debug( simpleDateFormat.format(new Date(System.currentTimeMillis())));
		log.debug("##### TaskScheduleTest:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
		
		
		crawConfigBO =  crawConfigDAO.selectCrawConfigList(crawConfigBO).get(0);
		
		crawConfigBO.setRowCnt(1000);
		crawConfigBO.setCrawlThreadCount(50);
		//crawConfigBO.setCrawlDepth(1);
	
		reNewJob.initCrawler(crawConfigBO);
		
	}
	
	
	
	
}
