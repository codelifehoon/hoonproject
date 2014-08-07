/**
 * @FileName  : GoodsPriceTrend.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 7. 29. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.business.analysis.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import com.zebra.common.BaseFactory;
import com.zebra.common.domain.BaseBO;

@Data
public class GoodsPriceTrendArrBO extends BaseBO {

	List<GoodsPriceTrendBO> goodsPriceTrendBOList = new ArrayList<GoodsPriceTrendBO>();
	
	public GoodsPriceTrendBO getGoodsPriceTrendByPriceCode(String priceCode)
	{
		for (GoodsPriceTrendBO goodsPriceTrendBO :  goodsPriceTrendBOList )
		{
			if (goodsPriceTrendBO.getPriceCode().equals(priceCode)) return goodsPriceTrendBO;
		}
		
		// not null return
		return BaseFactory.create(GoodsPriceTrendBO.class);
	}
	
}
