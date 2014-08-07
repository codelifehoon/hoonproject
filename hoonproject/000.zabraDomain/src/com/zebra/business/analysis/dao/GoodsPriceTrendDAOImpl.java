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

import com.zebra.business.analysis.domain.GoodsPriceTrendBO;
import com.zebra.business.analysis.domain.ProcedureParamBO;
import com.zebra.common.dao.CommonDAO;

@Repository
public class GoodsPriceTrendDAOImpl extends CommonDAO  implements GoodsPriceTrendDAO {

	/* (non-Javadoc)
	 * @see com.zebra.business.analysis.dao.GoodsPriceTrendDAO#selectGoodsPriceTrend(com.zebra.business.analysis.domain.GoodsPriceTrend)
	 */
	@Override
	public List<GoodsPriceTrendBO> selectGoodsPriceTrend(GoodsPriceTrendBO goodsPriceTrend) {

		List<GoodsPriceTrendBO> goodsPriceTrendList = sqlSession.selectList("query.analysis.selectGoodsPriceTrendList", goodsPriceTrend);
		
		return goodsPriceTrendList;
	}

	/* (non-Javadoc)
	 * @see com.zebra.business.analysis.dao.GoodsPriceTrendDAO#procGoodsPriceTrendCalc(com.zebra.business.analysis.domain.ProcedureParamBO)
	 */
	@Override
	public ProcedureParamBO procGoodsPriceTrendCalc(ProcedureParamBO procedureParamBO) {
		ProcedureParamBO resultBO = sqlSession.selectOne("query.analysis.procGoodsPriceTrendCalc",procedureParamBO);
		return resultBO;
		
	}

}
