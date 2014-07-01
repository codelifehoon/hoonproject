/**
 * @FileName  : PageCodeListDAOImpl.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 6. 27. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.business.craw.dao;

import java.util.HashMap;
import java.util.Iterator;

import org.springframework.stereotype.Repository;

import com.zebra.business.craw.domain.ExpPattenBO;
import com.zebra.common.BaseConstants;
import com.zebra.common.BaseFactory;
import com.zebra.common.dao.CommonDAO;
import com.zebra.common.util.CmnUtil;
import com.zebra.common.util.PattenUtil;

@Repository
public class PageCodeListDAOImpl  extends CommonDAO  implements PageCodeListDAO {

	/* (non-Javadoc)
	 * @see com.zebra.business.craw.dao.PageCodeListDAO#addPageCodeList(long, java.util.HashMap)
	 */
	@Override
	public int addPageCodeList(String siteConfigSeq,
			HashMap<String, ExpPattenBO[]> pattenMap)
	{
		int rowCnt = 0;
		Iterator<String> itr = pattenMap.keySet().iterator();
		while(itr.hasNext())
	    {
	        String pName = itr.next();
	        ExpPattenBO[] expPattenBOArray =  pattenMap.get(pName);
	        	
    		for (ExpPattenBO expPattenBO : expPattenBOArray)
    		{
    			expPattenBO.setSiteConfigSeq(siteConfigSeq);
    			
    			rowCnt += sqlSession.insert("query.crawler.insertCommonPatten", expPattenBO); 
    		} 
	
		 }
		
		return rowCnt;
	}
	
		
}
