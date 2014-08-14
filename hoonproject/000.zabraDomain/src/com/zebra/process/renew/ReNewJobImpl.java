package com.zebra.process.renew;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import lombok.extern.log4j.Log4j;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebra.business.craw.dao.PageInfoDAO;
import com.zebra.business.craw.dao.PageInfoDAOImpl;
import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.business.craw.domain.CrawlerDataCombBO;
import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.BaseFactory;
import com.zebra.common.BaseConstants;
import com.zebra.common.exception.BaseException;
import com.zebra.common.util.ConverterUtil;
import com.zebra.common.util.DateTime;
import com.zebra.common.util.DebugUtil;
import com.zebra.process.crawler.CommCrawlController;
import com.zebra.process.crawler.JobBase;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;


@Service
@Log4j
public class ReNewJobImpl extends JobBase  implements ReNewJob {


	@Autowired PageInfoDAO pageInfoDAO;
	/* (non-Javadoc)
	 * @see com.zebra.process.renew.ReNewJob#doReNew(com.zebra.process.crawler.domain.CrawlerDataCombBO)
	 */
	public CrawlerDataCombBO startController(CrawlerDataCombBO crawlerDataCombBO) throws Exception
	{
		

		CommCrawlController crawlControlle = super.initCrawController(crawlerDataCombBO);
		
		//////////////////////////
		List<WebPageInfoBO> webPageInfoBOList;
		webPageInfoBOList = ConverterUtil.webPageInfoMap2List(crawlerDataCombBO.getWebPageInfoBOMap());
		
		log.debug("##### webPageInfoBOList:" +  webPageInfoBOList.size() + ArrayUtils.toString(webPageInfoBOList));
		for (WebPageInfoBO  webPageInfoBO : webPageInfoBOList ) crawlControlle.addSeed(webPageInfoBO.getGoodsUrl());
		
		crawlControlle.start(PageCrawler.class, crawlerDataCombBO.getCrawConfigBO().getCrawlThreadCount());
			
		return crawlerDataCombBO;
	}

	
	public long applyReNewInfo(CrawlerDataCombBO crawlerDataCombBO)
			throws Exception {
		
		
		
		
		List<WebPageInfoBO> webPageInfoBOList =  ConverterUtil.webPageInfoMap2List(crawlerDataCombBO.getWebPageInfoBOMap()) ;
		
		log.error("applyReNewInfo: 시작" + DateTime.getShortTimeString() + ">>" + webPageInfoBOList.get(0).getPageInfoListSeq());
			
		pageInfoDAO.updateReNewPageInfoList(webPageInfoBOList);
		
		log.error("applyReNewInfo: 죵로" + DateTime.getShortTimeString() + ">>" + webPageInfoBOList.get(0).getPageInfoListSeq());
		
		return webPageInfoBOList.size();
	}
	
	
	public void initCrawler(CrawConfigBO  crawConfigBO)
	{
	
		boolean loopFlag = true;
		long applyCnt = 0;
		//String maxPageInfoListSeqNext = "0";
		
		
		while (loopFlag)
		{
			{
				try {
					
					WebPageInfoBO webPageInfoBO = BaseFactory.create(WebPageInfoBO.class);
					CrawlerDataCombBO crawlerDataCombBO = BaseFactory.create(CrawlerDataCombBO.class);
					
					webPageInfoBO.setSiteConfigSeq(crawConfigBO.getSiteConfigSeq());
					webPageInfoBO.setRowCnt(crawConfigBO.getRowCnt());
					//webPageInfoBO.setMaxPageInfoListSeq(maxPageInfoListSeqNext);
					
					
					HashMap<String, WebPageInfoBO> webPageInfoBOMap = pageInfoDAO.selectBulkPageInfoMap(webPageInfoBO);
					if ( webPageInfoBOMap.size() > 0)
					{
						//maxPageInfoListSeqNext =  ConverterUtil.getMaxMapKey(webPageInfoBOMap);	// 다음조회시 최종 조회된 번호 이후로 조회하기 위해서 조건 추가
						//log.error("##### maxPageInfoListSeqNext:" + maxPageInfoListSeqNext); 
						crawlerDataCombBO.setWebPageInfoBOMap(webPageInfoBOMap);		// 데이터의 빠른 갱신을 위해서 조회를 map으로 처리한다.(key: prdNo)
						crawlerDataCombBO.setCrawConfigBO(crawConfigBO);
						
						
						startController(crawlerDataCombBO);
						applyCnt = applyReNewInfo(crawlerDataCombBO);
						log.error("##### renew ##### cnt:" + applyCnt);
					}
					else 
					{
						loopFlag =false;
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	
	}
	
	
	
}
