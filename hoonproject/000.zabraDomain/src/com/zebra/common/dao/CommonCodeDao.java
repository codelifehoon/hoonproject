/**
 * @FileName  : CommonCodeDao.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 1. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.common.dao;

import com.zebra.common.domain.CommonCodeBO;

public interface CommonCodeDao {

	/**
	 * @param commonCodeBO
	 * @return
	 */
	CommonCodeBO selectCommonCode(CommonCodeBO commonCodeBO);

}
