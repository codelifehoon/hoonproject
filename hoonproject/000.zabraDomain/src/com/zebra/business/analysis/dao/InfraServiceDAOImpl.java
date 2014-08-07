/**
 * @FileName  : InfraServiceDAOImpl.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 6. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.business.analysis.dao;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.log4j.Log4j;

import org.springframework.stereotype.Repository;

import com.zebra.business.analysis.domain.GoodsPriceTrendBO;
import com.zebra.business.analysis.domain.ProcedureParamBO;
import com.zebra.business.analysis.domain.SearchCombBO;
import com.zebra.common.BaseFactory;
import com.zebra.common.dao.CommonDAO;

@Log4j
@Repository
public class InfraServiceDAOImpl extends CommonDAO implements InfraServiceDAO {

	/* (non-Javadoc)
	 * @see com.zebra.business.analysis.dao.InfraServiceDAO#majorMallGoodsSequanceGen(com.zebra.business.analysis.domain.ProcedureParamBO)
	 */
	@Override
	public ProcedureParamBO majorMallGoodsSequanceGen(
			ProcedureParamBO procedureParamBO) {
		
		ProcedureParamBO resultBO = sqlSession.selectOne("query.analysis.majorMallGoodsSequanceGen",procedureParamBO);
		return resultBO;
		
	}

	/* (non-Javadoc)
	 * @see com.zebra.business.analysis.dao.InfraServiceDAO#CleanGarbageData(com.zebra.business.analysis.domain.ProcedureParamBO)
	 */
	@Override
	public ProcedureParamBO cleanGarbageData(ProcedureParamBO procedureParamBO) {

		ProcedureParamBO resultBO = sqlSession.selectOne("query.analysis.cleanGarbageData",procedureParamBO);
		return resultBO;
	}

	/* (non-Javadoc)
	 * @see com.zebra.business.analysis.dao.InfraServiceDAO#doGoodsSearch(com.zebra.business.analysis.domain.SearchCombineBO)
	 */
	@Override
	public List<SearchCombBO> doGoodsSearch(SearchCombBO searchCombineBO) {
		
		List<SearchCombBO> searchCombineBOList =  sqlSession.selectList("query.analysis.doGoodsSearch",searchCombineBO);
		
		for (SearchCombBO searchBO : searchCombineBOList)
		{
			String[] priceCodeArr = searchBO.getGoodsTrendStr().split(",");

			for (String priceDataStr : priceCodeArr)
			{	
				
				GoodsPriceTrendBO goodsPriceTrendBO = BaseFactory.create(GoodsPriceTrendBO.class);
				String[] datas = priceDataStr.split("#");
				
				goodsPriceTrendBO.setGoodsPrice(datas[0]);
				goodsPriceTrendBO.setPriceCode(datas[1]);
				
				searchBO.getGoodsPriceTrendBOList().add(goodsPriceTrendBO);
			}
		}
		
		return searchCombineBOList;
	}

}
