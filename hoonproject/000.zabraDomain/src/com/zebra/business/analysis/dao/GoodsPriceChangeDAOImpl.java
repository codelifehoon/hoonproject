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

import lombok.extern.log4j.Log4j;

import org.springframework.stereotype.Repository;

import com.zebra.business.analysis.domain.GoodsPriceChangeBO;
import com.zebra.business.analysis.domain.GoodsPriceTrendBO;
import com.zebra.business.analysis.domain.ProcedureParamBO;
import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.dao.CommonDAO;

@Repository
@Log4j
public class GoodsPriceChangeDAOImpl extends CommonDAO implements GoodsPriceChangeDAO {

	/* (non-Javadoc)
	 * @see com.zebra.business.analysis.dao.GoodsPriceChangeDAO#selectGoodsPriceChangeList(com.zebra.business.analysis.domain.GoodsPriceChange)
	 */
	@Override
	public List<WebPageInfoBO> selectGoodsPriceChangeList(
			GoodsPriceChangeBO goodsPriceChange) {
		
		 List<WebPageInfoBO>  webPageInfoBOList = sqlSession.selectList("query.analysis.selectGoodsPriceChangeList",goodsPriceChange);
		
		return webPageInfoBOList;
		
	}

	/* (non-Javadoc)
	 * @see com.zebra.business.analysis.dao.GoodsPriceChangeDAO#procGoodsPriceChangeCalc(com.zebra.business.analysis.domain.ProcedureParamBO)
	 */
	@Override
	public ProcedureParamBO procGoodsPriceChangeCalc(ProcedureParamBO procedureParamBO) {
		ProcedureParamBO resultBO = sqlSession.selectOne("query.analysis.procGoodsPriceChangeCalc",procedureParamBO);
		return resultBO;
	}



}
