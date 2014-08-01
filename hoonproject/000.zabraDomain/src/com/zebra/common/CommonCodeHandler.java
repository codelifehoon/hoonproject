/**
 * @FileName  : CommonCodeHandler.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 1. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.zebra.common.domain.CommonCodeBO;
import com.zebra.process.common.CommonCodeService;

public class CommonCodeHandler {



	
	/**
	 * @param cdMaster
	 * @param cdDetail
	 * @return 
	 */
	public static CommonCodeBO selectCommonCode(String cdMaster, String cdDetail) {


		CommonCodeService commonCodeService = SpringBeanFactory.getBean(CommonCodeService.class);
		return commonCodeService.selectCommonCode(cdMaster, cdDetail);
		
	}

	

}
