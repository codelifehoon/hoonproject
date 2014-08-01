/**
 * @FileName  : GoodsPriceTrendDAO.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 7. 29. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.business.analysis.dao;

import java.util.List;

import com.zebra.business.analysis.domain.GoodsPriceTrend;

public interface GoodsPriceTrendDAO {

	/**
	 * @param goodsPriceTrend
	 * @return
	 */
	List<GoodsPriceTrend> selectGoodsPriceTrend(GoodsPriceTrend goodsPriceTrend);

}
