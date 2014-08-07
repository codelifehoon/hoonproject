/**
 * @FileName  : InfraServiceDAO.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 6. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.business.analysis.dao;

import java.util.List;

import com.zebra.business.analysis.domain.ProcedureParamBO;
import com.zebra.business.analysis.domain.SearchCombBO;

public interface InfraServiceDAO {

	/**
	 * @param procedureParamBO
	 * @return
	 */
	ProcedureParamBO majorMallGoodsSequanceGen(ProcedureParamBO procedureParamBO);

	/**
	 * @param procedureParamBO
	 * @return
	 */
	ProcedureParamBO cleanGarbageData(ProcedureParamBO procedureParamBO);

	/**
	 * @param searchCombineBO
	 * @return
	 */
	List<SearchCombBO> doGoodsSearch(SearchCombBO searchCombineBO);

}
