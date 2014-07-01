package com.zebra.business.craw.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.common.dao.CommonDAO;
import com.zebra.common.dao.CommonPattenCodeDao;
@Repository
public class CrawConfigDAOImpl  extends CommonDAO implements CrawConfigDAO {

	protected static final  Logger logger =  Logger.getLogger(CrawConfigDAOImpl.class.getName());
	@Autowired CommonPattenCodeDao commonPattenCodeDao;
	
	/* (non-Javadoc)
	 * @see com.zebra.process.crawler.dao.SiteCofigDAO#selectSiteConfig()
	 */
	public List<CrawConfigBO> selectCrawConfigList(CrawConfigBO crawConfigBO)
	{
		
		List<CrawConfigBO> crawConfigBOList = sqlSession.selectList("query.crawler.selectCrawConfigList", crawConfigBO);
	
		for (CrawConfigBO bo : crawConfigBOList)
		{
			/*
			 * selectPattenCodeList  의  parameterType을 hashmap으로 하지 않아서 collection 불가능해 별도의 
			  java로 분리
			  */
			bo.setExpPattenBOList(commonPattenCodeDao.selectPattenCodeList(bo.getSiteConfigSeq(), null));
		}
		return crawConfigBOList;
		
	}

	/* (non-Javadoc)
	 * @see com.zebra.business.craw.dao.CrawConfigDAO#addCrawConfig(com.zebra.business.craw.domain.CrawConfigBO)
	 */
	@Override
	public long addCrawConfig(CrawConfigBO crawConfigBO) {
		return sqlSession.insert("query.crawler.inserCrawConfig", crawConfigBO); 
	}
}
