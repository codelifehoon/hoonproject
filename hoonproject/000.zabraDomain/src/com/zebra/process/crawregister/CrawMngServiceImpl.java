/**
 * @FileName  : CrawMngServiceImpl.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 6. 27. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.process.crawregister;


//import static org.hamcrest.CoreMatchers.*;
//import static org.junit.Assert.assertThat;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebra.business.craw.dao.CrawConfigDAO;
import com.zebra.business.craw.dao.PageCodeListDAO;
import com.zebra.business.craw.domain.CrawlerDataCombBO;

@Log4j
@Service
public class CrawMngServiceImpl implements CrawMngService {

	@Autowired private CrawConfigDAO crawConfigDao;
	@Autowired private PageCodeListDAO pageCodeListDao;
	
	/* (non-Javadoc)
	 * @see com.zebra.process.crawregister.CrawMngService#addCrawInfo(com.zebra.business.craw.domain.CrawlerDataCombBO)
	 */
	@Override
	public void addCrawInfo(CrawlerDataCombBO crawlerDataCombBO) {

		crawlerDataCombBO = null;
		assert (crawlerDataCombBO != null && crawlerDataCombBO.getCrawConfigBO() != null) : this.toString() + "  expect not null.";
		//assertThat(crawlerDataCombBO, is(notNullValue())) ;
		//assertThat(crawlerDataCombBO.getCrawConfigBO(), is(notNullValue())) ;

		crawConfigDao.addCrawConfig(crawlerDataCombBO.getCrawConfigBO());
		log.debug("addCrawInfo-siteConfigSeq:" + crawlerDataCombBO.getCrawConfigBO().getSiteConfigSeq());
		
		int cnt = pageCodeListDao.addPageCodeList(crawlerDataCombBO.getCrawConfigBO().getSiteConfigSeq() ,crawlerDataCombBO.getPattenMap());
		log.debug("addCrawInfo-addPageCodeList:" + cnt);
		
	}

}
