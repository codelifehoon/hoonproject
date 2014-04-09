package com.zebra.process.crawler.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.zebra.common.dao.CommonDAO;
import com.zebra.process.crawler.domain.CrawlerDataCombBO;
import com.zebra.process.crawler.domain.WebPageInfoBO;


import org.apache.log4j.Logger ;

@Repository
public class PageInfoDAOImpl  extends CommonDAO  implements PageInfoDAO{

	
	protected Logger log = Logger.getLogger(this.getClass());
	
		/* (non-Javadoc)
		 * @see com.zebra.process.crawler.dao.PageInfoDAO#insertPageInfo(java.util.List)
		 */
		public void insertPageInfo(List<WebPageInfoBO>  webPageInfoBOlist )
		{
			log.error("#####insertPageInfo:"+ webPageInfoBOlist.size());
		
				for(WebPageInfoBO webPageInfoBO :webPageInfoBOlist )
				{
					sqlSessionBatch.insert("query.crawler.insertPageInfoList", webPageInfoBO);
				}
				
			//List<BatchResult> results = sqlSessionBatch.flushStatements();
		}
		
		/* (non-Javadoc)
		 * @see com.zebra.process.crawler.dao.PageInfoDAO#selectPageInfoListAll()
		 */
		public List<WebPageInfoBO>  selectPageInfoList(WebPageInfoBO webPageInfoBO)
		{

			log.debug("sqlSession:" + sqlSession);
			log.debug("sqlSession.getExecutorType():" + sqlSession.getExecutorType());
			log.debug("sqlSessionBatch.getExecutorType():" + sqlSessionBatch.getExecutorType());
			
			List<WebPageInfoBO> list = sqlSession.selectList("query.crawler.selectPageInfoList", webPageInfoBO);
			
			return list;
			
		
		}
		
	
		public HashMap<String, WebPageInfoBO>  selectPageInfoMap(WebPageInfoBO webPageInfoBO)
		{

			Map<String, WebPageInfoBO>  a = sqlSession.selectMap("query.crawler.selectPageInfoMap", webPageInfoBO, "goodsNo");
			HashMap<String, WebPageInfoBO> h = new HashMap<String, WebPageInfoBO>(a) ;
			
			return  h;
			
		
		}
		
		
		
		/* (non-Javadoc)
		 * @see com.zebra.process.crawler.dao.PageInfoDAO#selectPageInfoListAll()
		 */
		public void  updatePageInfoList(List<WebPageInfoBO> webPageInfoBOList )
		{
			log.error("##### updatePageInfoList");
			for (WebPageInfoBO webPageInfoBO : webPageInfoBOList )
			{
				log.error("webPageInfoBO.getGoodsNo()" + webPageInfoBO.getGoodsNo());
				log.error("webPageInfoBO.getGoodsImg()" + webPageInfoBO.getGoodsImg());
				log.error("webPageInfoBO.getGoodsNm()" + webPageInfoBO.getGoodsNm());			
				log.error("webPageInfoBO.getGoodsPrice()" + webPageInfoBO.getGoodsPrice());
				log.error("webPageInfoBO.getGoodsUrl()" + webPageInfoBO.getGoodsUrl());
			}
				
		}

		
}
