/**
 * @FileName  : ProcedureParamBO.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 5. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.business.analysis.domain;

import java.util.Date;

import lombok.Data;

import com.zebra.common.domain.BaseBO;

@Data
public class ProcedureParamBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1151326360705667315L;
	private Integer siteConfigSeq;
	private String goodsUrl;
	private Integer startNum ;
	private Integer endNum ;
	private Date 	regDt ;
	private Date 	startDt ;
	private Date 	endDt ;

	private String	procResult1;
	private String	procResult2;
	private String	procResult3;
	private String	procResult4;
	private String	procResult5;
	
	
}
