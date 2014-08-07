/**
 * @FileName  : GoodsPriceChange.java
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
public class GoodsPriceChangeBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8966919445998281014L;
	private String goodsPriceChangeSeq; 
	private String pageInfoListSeq; 
	private String pageInfoListHistSeq; 
	private String statCd; 
	private String createNo; 
	private Date createDt; 
	private Date updateDt; 
	private String updateNo; 
	
}
