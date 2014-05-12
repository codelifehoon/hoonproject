package com.zebra.process.crawler.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zebra.common.BaseFactory;
import com.zebra.common.SpringBeanFactory;
import com.zebra.common.dao.CommonDAO;
import com.zebra.common.util.DateTime;
import com.zebra.process.crawler.domain.CrawlerDataCombBO;
import com.zebra.process.crawler.domain.WebPageInfoBO;


import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger ;

@Repository
public class PageInfoDAOImpl  extends CommonDAO  implements PageInfoDAO{

	
	protected static final Logger log = Logger.getLogger(PageInfoDAOImpl.class.getName());
	
		/* (non-Javadoc)
		 * @see com.zebra.process.crawler.dao.PageInfoDAO#insertPageInfo(java.util.List)
		 */
	
		public void insertPageInfo(List<WebPageInfoBO>  webPageInfoBOlist )
		{
			log.error("#####insertPageInfo:"+ webPageInfoBOlist.size());
			
			// batch 데이터 처리시 session 을 별도로 열어야 동작을 하네..ExecutorType.BATCH
		    SqlSession session = sqlSessionBatch.getSqlSessionFactory().openSession();
		    try
		    {
		    	for(WebPageInfoBO webPageInfoBO :webPageInfoBOlist )
				{
					session.insert("query.crawler.insertPageInfoList", webPageInfoBO);
				}
		    }
		    finally { session.close(); } 
		}
		
		/* (non-Javadoc)
		 * @see com.zebra.process.crawler.dao.PageInfoDAO#selectPageInfoListAll()
		 */
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
		public void  updateReNewPageInfoList(List<WebPageInfoBO> webPageInfoBOList )
		{
			
			
		  SqlSession session = sqlSessionBatch.getSqlSessionFactory().openSession();
		   try
		    {
		    	for(WebPageInfoBO webPageInfoBO : webPageInfoBOList)
				{

		    		webPageInfoBO.setUpdateDt(DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
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
		  finally { session.close(); } 
		    
		}

		
}
