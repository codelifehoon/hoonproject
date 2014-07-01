/**
 * @FileName  : PageCodeListDAO.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 6. 27. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.business.craw.dao;

import java.util.HashMap;

import com.zebra.business.craw.domain.ExpPattenBO;

public interface PageCodeListDAO {

	/**
	 * @param siteConfigSeq
	 * @param pattenMap
	 * @return TODO
	 */
	int addPageCodeList(String siteConfigSeq,
			HashMap<String, ExpPattenBO[]> pattenMap);

}
