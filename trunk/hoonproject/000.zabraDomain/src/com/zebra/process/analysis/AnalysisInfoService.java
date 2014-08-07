/**
 * @FileName  : AnalysisInfoService.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 5. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.process.analysis;

import java.util.List;

import com.zebra.business.analysis.domain.ProcedureParamBO;
import com.zebra.business.analysis.domain.SearchCombBO;

public interface AnalysisInfoService {

	/**
	 * @param procedureParamBO
	 */
	void priceFlowAnalysis(ProcedureParamBO procedureParamBO);

	/**
	 * @param procedureParamBO
	 */
	void majorMallGoodsSequanceGen(ProcedureParamBO procedureParamBO);

	/**
	 * @param procedureParamBO
	 */
	void cleanGarbageData(ProcedureParamBO procedureParamBO);

	/**
	 * @param searchCombineBO
	 * @return
	 */
	List<com.zebra.business.analysis.domain.SearchCombBO> doGoodsSearch(
			SearchCombBO searchCombineBO);

}
