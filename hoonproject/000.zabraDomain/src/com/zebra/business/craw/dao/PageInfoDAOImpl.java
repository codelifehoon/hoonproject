package com.zebra.business.craw.dao;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.log4j.Log4j;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.zebra.business.craw.domain.CrawlerDataCombBO;
import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.BaseFactory;
import com.zebra.common.SpringBeanFactory;
import com.zebra.common.dao.CommonDAO;
import com.zebra.common.util.DateTime;
import com.zebra.common.util.NumUtil;


import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

@Repository
@Log4j
public class PageInfoDAOImpl  extends CommonDAO  implements PageInfoDAO{


	
		/* (non-Javadoc)
		 * @see com.zebra.process.crawler.dao.PageInfoDAO#insertPageInfo(java.util.List)
		 */
	
		public void insertPageInfo(List<WebPageInfoBO>  webPageInfoBOlist )
		{
			log.error("#####insertPageInfo:"+ webPageInfoBOlist.size());
			
			// batch 데이터 처리시 session 을 별도로 열어야 동작을 하네..ExecutorType.BATCH
		    SqlSession session = sqlSessionBatch.getSqlSessionFactory().openSession(ExecutorType.BATCH);
		     try
		    {
		    	for(WebPageInfoBO webPageInfoBO :webPageInfoBOlist )
				{
		    		session.insert("query.crawler.insertPageInfoList", webPageInfoBO);
				}
		    }
		     catch(Exception e) { e.printStackTrace();  }
		     finally { session.flushStatements(); //session.close(); 
		     } 
		}
		

		public HashMap<String, WebPageInfoBO>  selectReNewPageInfoMap(WebPageInfoBO webPageInfoBO)
		{


			Map<String, WebPageInfoBO>  result  = sqlSession.selectMap("query.crawler.selectReNewPageInfoMap", webPageInfoBO,"goodsUrl");
			HashMap<String, WebPageInfoBO> map = new HashMap<String, WebPageInfoBO>(result) ;
			return map;
			
		
		}
		
	
		public HashMap<String, WebPageInfoBO>  selectPageInfoMap(WebPageInfoBO webPageInfoBO)
		{

			Map<String, WebPageInfoBO>  result = sqlSession.selectMap("query.crawler.selectPageInfoMap", webPageInfoBO, "goodsUrl");
			HashMap<String, WebPageInfoBO> map = new HashMap<String, WebPageInfoBO>(result) ;
			
			return  map;
			
		
		}
	
		
		

		/* (non-Javadoc)
		 * @see com.zebra.process.crawler.dao.PageInfoDAO#selectPageInfoListAll()
		 */
		//@Async
		public void  updateReNewPageInfoList(List<WebPageInfoBO> webPageInfoBOList )
		{
			
			
			SqlSession session = sqlSessionBatch.getSqlSessionFactory().openSession(ExecutorType.BATCH);
		   
		    try
		    {
		    	for(WebPageInfoBO webPageInfoBO : webPageInfoBOList)
				{

		    		webPageInfoBO.setUpdateDt(new Date());
		    		webPageInfoBO.setStatCd("02");
	
		    		if ("Y".equals(webPageInfoBO.getFailYn()))
					{
		    			log.debug("##### 수집실패");
		    			// 실패건에 대해서는 실채cnt 와 updatedt만 올린다.
		    			WebPageInfoBO tempBo = BaseFactory.create(WebPageInfoBO.class);
		    			
		    			tempBo.setPageInfoListSeq(webPageInfoBO.getPageInfoListSeq());
		    			tempBo.setFailCnt(webPageInfoBO.getFailCnt()+1);
		    			tempBo.setUpdateDt(webPageInfoBO.getUpdateDt());
		    			tempBo.setStatCd(webPageInfoBO.getStatCd());
		    			
		    					
		    			session.insert("query.crawler.insertPageInfoListHistCopy", tempBo);
		    			session.update("query.crawler.updatePageInfoList", tempBo);
					}
		    		else 
		    		{
		    			webPageInfoBO.setFailCnt(0);
		    			session.insert("query.crawler.insertPageInfoListHistCopy", webPageInfoBO);
		    			session.update("query.crawler.updatePageInfoList", webPageInfoBO);
						
		    		}
				}
		   }
		  catch(Exception e) { e.printStackTrace();  }
		  finally { session.flushStatements(); //session.close();
			  } 
		   }

		/* (non-Javadoc)
		 * @see com.zebra.business.craw.dao.PageInfoDAO#selectPageInfo(com.zebra.business.craw.domain.WebPageInfoBO)
		 */
		@Override
		public WebPageInfoBO selectPageInfo(WebPageInfoBO webPageInfoBO) {

			WebPageInfoBO result = sqlSession.selectOne("query.crawler.selectPageInfoMap", webPageInfoBO);

			return result;
		}


		/* (non-Javadoc)
		 * @see com.zebra.business.craw.dao.PageInfoDAO#asyncCall(java.lang.String)
		 */
		@Override
		@Async
		public void asyncCall(String data) {
			// TODO Auto-generated method stub
			log.debug("시작:" + data);
			
				for (int i=0;i<1000000;i++) 
					{
						double j = 3.23*1.1/i;
					}
			
			log.debug("종료:" + data);
			
		}
		    
		

		
}
