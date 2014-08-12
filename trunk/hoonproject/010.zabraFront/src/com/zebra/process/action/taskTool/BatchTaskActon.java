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
import java.util.Calendar;
import java.util.Date;

import lombok.extern.log4j.Log4j;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.zebra.business.analysis.domain.ProcedureParamBO;
import com.zebra.business.craw.dao.CrawConfigDAO;
import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.common.BaseFactory;
import com.zebra.common.util.DateTime;
import com.zebra.process.action.AuthAction;
import com.zebra.process.analysis.AnalysisInfoService;
import com.zebra.process.renew.ReNewJob;


@Log4j
@Controller
public class BatchTaskActon extends AuthAction {

	@Autowired private  ReNewJob  		reNewJob;
	@Autowired private  CrawConfigDAO	crawConfigDAO;
	@Autowired private  AnalysisInfoService analysisInfoService;
	private  static final	int	DEALY_JOB	= 1000*60*10 ;

	

	/**
	 * @param string
	 */
	private void endLog(String string) {
		// TODO Auto-generated method stub
		log.error("##### " +string+ " endLog:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * @param string
	 */
	private void startLog(String string) {
		// TODO Auto-generated method stub
		log.error("##### " +string+ " startLog:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
	}
	
	@Scheduled(fixedDelay = DEALY_JOB)
	public void st11Update()
	{
		
		startLog("st11Update");
		CrawConfigBO		crawConfigBO	= BaseFactory.create(CrawConfigBO.class);
		crawConfigBO.setSiteConfigSeq("100027");
		
		RunCrawUpdateTask(crawConfigBO);
		endLog("st11Update");
		
	}
	
	

	@Scheduled(fixedDelay =DEALY_JOB)
	public void AUpdate()
	{
		startLog("AUpdate");
		
		CrawConfigBO		crawConfigBO	= BaseFactory.create(CrawConfigBO.class);
		crawConfigBO.setSiteConfigSeq("100028");
		
		RunCrawUpdateTask(crawConfigBO);
		endLog("AUpdate");
		
	}
	
	@Scheduled(fixedDelay =DEALY_JOB)
	public void GUpdate()
	{
		startLog("GUpdate");
		
		CrawConfigBO		crawConfigBO	= BaseFactory.create(CrawConfigBO.class);
		crawConfigBO.setSiteConfigSeq("100031");
		
		RunCrawUpdateTask(crawConfigBO);
		endLog("GUpdate");
		
	}
	
	// 초,분,시
	//@Scheduled(cron="0 0 1/24 * * *")
	public void priceFlowAnalysis()
	{	
		startLog("priceFlowAnalysis");
		
		ProcedureParamBO procedureParamBO = BaseFactory.create(ProcedureParamBO.class); 
		Date currentDt = new Date();

		currentDt = DateUtils.truncate(currentDt, Calendar.DATE);
	
		procedureParamBO.setStartDt(currentDt);
		procedureParamBO.setEndDt(DateUtils.addDays(currentDt,1));
		
		analysisInfoService.priceFlowAnalysis(procedureParamBO);
		endLog("priceFlowAnalysis");
		
		 
	}
	
	// 초,분,시
	//@Scheduled(cron="0 0 1/24 * * *")
	public void majorMallGoodsSequanceGen()
	{
		startLog("majorMallGoodsSequanceGen");
		
		ProcedureParamBO procedureParamBO = BaseFactory.create(ProcedureParamBO.class); 
		
		analysisInfoService.majorMallGoodsSequanceGen(procedureParamBO);
		endLog("majorMallGoodsSequanceGen");
		 
	}
	
	// 초,분,시
	//@Scheduled(cron="0 0 1/24 * * *")
	public void cleanGarbageData()
	{
		startLog("cleanGarbageData");
		
		ProcedureParamBO procedureParamBO = BaseFactory.create(ProcedureParamBO.class); 
		
		analysisInfoService.cleanGarbageData(procedureParamBO);
		endLog("cleanGarbageData");

	}
		
	

	/**
	 * @param crawConfigBO
	 */
	private void RunCrawUpdateTask(CrawConfigBO crawConfigBO) {
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		log.debug( simpleDateFormat.format(new Date(System.currentTimeMillis())));
		log.debug("##### TaskScheduleTest:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
		
		
		crawConfigBO =  crawConfigDAO.selectCrawConfigList(crawConfigBO).get(0);
		
		crawConfigBO.setRowCnt(10);
		crawConfigBO.setCrawlThreadCount(2);
		//crawConfigBO.setCrawlDepth(1);
	
		reNewJob.initCrawler(crawConfigBO);
		
	}
	
	
	
	
}
