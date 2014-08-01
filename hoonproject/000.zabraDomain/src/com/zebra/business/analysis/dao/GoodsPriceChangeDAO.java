/**
 * @FileName  : GoodsPriceChangeDAO.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 7. 29. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.business.analysis.dao;

import java.util.List;

import com.zebra.business.analysis.domain.GoodsPriceChange;
import com.zebra.business.analysis.domain.GoodsPriceTrend;
import com.zebra.business.craw.domain.WebPageInfoBO;

public interface GoodsPriceChangeDAO {

	/**
	 * @param goodsPriceChange
	 * @return
	 */
	List<WebPageInfoBO> selectGoodsPriceChangeList(
			GoodsPriceChange goodsPriceChange);

}
