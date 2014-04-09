package com.zebra.process.crawler.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.zebra.common.dao.CommonDAO;
import com.zebra.process.crawler.domain.CrawConfigBO;
@Repository
public class CrawConfigDAOImpl  extends CommonDAO implements CrawCofigDAO {

	protected static final  Logger logger =  Logger.getLogger(CrawConfigDAOImpl.class.getName());
	
	
	/* (non-Javadoc)
	 * @see com.zebra.process.crawler.dao.SiteCofigDAO#selectSiteConfig()
	 */
	public List<CrawConfigBO> selectCrawConfigList(CrawConfigBO crawConfigBO)
	{
		
		List<CrawConfigBO> crawConfigBOList = sqlSession.selectList("query.crawler.selectCrawConfigList", crawConfigBO);
	
		
		return crawConfigBOList;
		
	}
}
