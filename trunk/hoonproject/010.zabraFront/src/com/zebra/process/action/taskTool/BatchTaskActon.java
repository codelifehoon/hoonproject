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

	

	// @Scheduled(fixedDelay = DEALY_JOB)
	public void st11Update()
	{
		log.error("##### task_st11_Update start:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
		
		CrawConfigBO		crawConfigBO	= BaseFactory.create(CrawConfigBO.class);
		crawConfigBO.setSiteConfigSeq("100027");
		
		RunCrawUpdateTask(crawConfigBO);
	
	}
	
	// @Scheduled(fixedDelay =DEALY_JOB)
	public void AUpdate()
	{
		log.error("##### task_A_Update start:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
		
		CrawConfigBO		crawConfigBO	= BaseFactory.create(CrawConfigBO.class);
		crawConfigBO.setSiteConfigSeq("100028");
		
		RunCrawUpdateTask(crawConfigBO);
		
	}
	
	// @Scheduled(fixedDelay =DEALY_JOB)
	public void GUpdate()
	{
		log.error("##### task_G_Update start:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
		
		CrawConfigBO		crawConfigBO	= BaseFactory.create(CrawConfigBO.class);
		crawConfigBO.setSiteConfigSeq("100031");
		
		RunCrawUpdateTask(crawConfigBO);
		
	}
	
	// 초,분,시
	//// @Scheduled(cron="0 0 3 * * *")
	public void priceFlowAnalysis()
	{	
		log.error("##### task_PriceFlowAnalysis start:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
		ProcedureParamBO procedureParamBO = BaseFactory.create(ProcedureParamBO.class); 
		Date currentDt = new Date();

		currentDt = DateUtils.truncate(currentDt, Calendar.DATE);
	
		procedureParamBO.setStartDt(currentDt);
		procedureParamBO.setEndDt(DateUtils.addDays(currentDt,1));
		
		analysisInfoService.priceFlowAnalysis(procedureParamBO);
		
		 
	}
	
	// 초,분,시
	//// @Scheduled(cron="0 0 3 * * *")
	public void majorMallGoodsSequanceGen()
	{
		log.error("##### task_GenGoodsSequance start:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
		ProcedureParamBO procedureParamBO = BaseFactory.create(ProcedureParamBO.class); 
		
		analysisInfoService.majorMallGoodsSequanceGen(procedureParamBO);
		 
	}
	
	// 초,분,시
	//// @Scheduled(cron="0 0 3 * * *")
	public void cleanGarbageData()
	{
		log.error("##### CleanGarbageData start:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
		ProcedureParamBO procedureParamBO = BaseFactory.create(ProcedureParamBO.class); 
		
		analysisInfoService.cleanGarbageData(procedureParamBO);

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
