/**
 * @FileName  : GoodsPriceTrendDAOImpl.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 7. 29. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.business.analysis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zebra.business.analysis.domain.GoodsPriceTrend;
import com.zebra.common.dao.CommonDAO;

@Repository
public class GoodsPriceTrendDAOImpl extends CommonDAO  implements GoodsPriceTrendDAO {

	/* (non-Javadoc)
	 * @see com.zebra.business.analysis.dao.GoodsPriceTrendDAO#selectGoodsPriceTrend(com.zebra.business.analysis.domain.GoodsPriceTrend)
	 */
	@Override
	public List<GoodsPriceTrend> selectGoodsPriceTrend(GoodsPriceTrend goodsPriceTrend) {

		List<GoodsPriceTrend> goodsPriceTrendList = sqlSession.selectList("query.analysis.selectGoodsPriceTrendList", goodsPriceTrend);
		
		return goodsPriceTrendList;
	}

}
