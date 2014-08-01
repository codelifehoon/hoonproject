/**
 * @FileName  : CommonCodeService.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 1. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.process.common;

import com.zebra.common.domain.CommonCodeBO;

public interface CommonCodeService {

	/**
	 * @param cdMaster
	 * @param cdDetail
	 * @return
	 */
	CommonCodeBO selectCommonCode(String cdMaster, String cdDetail);

}
