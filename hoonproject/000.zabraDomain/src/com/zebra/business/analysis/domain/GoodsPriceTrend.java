/**
 * @FileName  : GoodsPriceTrend.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 7. 29. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.business.analysis.domain;

import java.util.Date;

import lombok.Data;

import com.zebra.common.domain.BaseBO;

@Data
public class GoodsPriceTrend extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2264011545711190593L;
	private String goodsPriceTrendSeq;
	private String pageInfoListHistSeq;
	private String pageInfoListSeq; 
	private String goodsPrice; 
	private String priceCode; 
	private String statCd; 
	private String createNo; 
	private Date createDt; 
	private Date updateDt; 
	private String updateNo; 
	
}
