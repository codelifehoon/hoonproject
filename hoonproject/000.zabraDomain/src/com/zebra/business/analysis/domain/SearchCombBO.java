/**
 * @FileName  : SearchCombineBO.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 6. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.business.analysis.domain;

import java.util.ArrayList;

import java.util.List;

import lombok.Data;

import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.BaseFactory;
import com.zebra.common.domain.BaseBO;

@Data
public class SearchCombBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8552498204844078167L;
	CrawConfigBO crawConfigBO 			= BaseFactory.create(CrawConfigBO.class);
	WebPageInfoBO webPageInfoBO			= BaseFactory.create(WebPageInfoBO.class);
	GoodsPriceTrendArrBO	goodsPriceTrendArrBO = BaseFactory.create(GoodsPriceTrendArrBO.class);

	String searchWord;
	String goodsTrendStr;
	

	
	public List<GoodsPriceTrendBO> getGoodsPriceTrendBOList() {
		return goodsPriceTrendArrBO.getGoodsPriceTrendBOList();
	}

	public void setGoodsPriceTrendBOList(
			List<GoodsPriceTrendBO> goodsPriceTrendBOList) {
		this.goodsPriceTrendArrBO.setGoodsPriceTrendBOList(goodsPriceTrendBOList);
	}
	
	
}
