/**
 * @FileName  : AnalysisInfoServiceImpl.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 6. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.process.analysis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebra.business.analysis.dao.GoodsPriceChangeDAO;
import com.zebra.business.analysis.dao.GoodsPriceTrendDAO;
import com.zebra.business.analysis.dao.InfraServiceDAO;
import com.zebra.business.analysis.domain.ProcedureParamBO;
import com.zebra.business.analysis.domain.SearchCombBO;

@Service
public class AnalysisInfoServiceImpl implements AnalysisInfoService {

	@Autowired GoodsPriceChangeDAO 	goodsPriceChangeDAO;
	@Autowired GoodsPriceTrendDAO	goodsPriceTrendDAO;
	@Autowired InfraServiceDAO		infraServiceDAO; 
	/* (non-Javadoc)
	 * @see com.zebra.process.analysis.AnalysisInfoService#PriceFlowAnalysis(com.zebra.business.analysis.domain.ProcedureParamBO)
	 */
	@Override
	public void priceFlowAnalysis(ProcedureParamBO procedureParamBO) {

		ProcedureParamBO resultBO;
		
		resultBO = goodsPriceChangeDAO.procGoodsPriceChangeCalc(procedureParamBO);
		resultBO = goodsPriceTrendDAO.procGoodsPriceTrendCalc(procedureParamBO);
		
	}
	/* (non-Javadoc)
	 * @see com.zebra.process.analysis.AnalysisInfoService#majorMallGoodsSequanceGen(com.zebra.business.analysis.domain.ProcedureParamBO)
	 */
	@Override
	public void majorMallGoodsSequanceGen(ProcedureParamBO procedureParamBO) {
		// TODO Auto-generated method stub
		ProcedureParamBO resultBO;
		
		resultBO = infraServiceDAO.majorMallGoodsSequanceGen(procedureParamBO);
	}
	/* (non-Javadoc)
	 * @see com.zebra.process.analysis.AnalysisInfoService#CleanGarbageData(com.zebra.business.analysis.domain.ProcedureParamBO)
	 */
	@Override
	public void cleanGarbageData(ProcedureParamBO procedureParamBO) {
		
		ProcedureParamBO resultBO;
		
		resultBO = infraServiceDAO.cleanGarbageData(procedureParamBO);
		
	}
	/* (non-Javadoc)
	 * @see com.zebra.process.analysis.AnalysisInfoService#doGoodsSearch(com.zebra.business.analysis.domain.SearchCombineBO)
	 */
	@Override
	public List<SearchCombBO> doGoodsSearch(SearchCombBO searchCombineBO) {
		
		return  infraServiceDAO.doGoodsSearch(searchCombineBO);
	}

}
