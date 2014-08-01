/**
 * @FileName  : GoodsPriceChangeDAOImpl.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 7. 29. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.business.analysis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zebra.business.analysis.domain.GoodsPriceChange;
import com.zebra.business.analysis.domain.GoodsPriceTrend;
import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.dao.CommonDAO;

@Repository
public class GoodsPriceChangeDAOImpl extends CommonDAO implements GoodsPriceChangeDAO {

	/* (non-Javadoc)
	 * @see com.zebra.business.analysis.dao.GoodsPriceChangeDAO#selectGoodsPriceChangeList(com.zebra.business.analysis.domain.GoodsPriceChange)
	 */
	@Override
	public List<WebPageInfoBO> selectGoodsPriceChangeList(
			GoodsPriceChange goodsPriceChange) {
		
		 List<WebPageInfoBO>  webPageInfoBOList = sqlSession.selectList("query.analysis.selectGoodsPriceChangeList",goodsPriceChange);
		
		return webPageInfoBOList;
		
	}



}
