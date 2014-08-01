/**
 * @FileName  : CommonCodeBO.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 1. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.common.domain;

import lombok.Data;

@Data
public class CommonCodeBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3112178470502148582L;
	
	private String cdMaster;
	private String cdDetail;
	private String val1;
	private String val2;
	private String val3;
	private String useYn;
	private String createNo;
	private String createDt;
	private String updateNo;
	private String updateDt;
	
}
