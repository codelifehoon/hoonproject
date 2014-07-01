/**
 * @FileName  : crawMngService.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 6. 27. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.process.crawregister;

import com.zebra.business.craw.domain.CrawlerDataCombBO;

public interface CrawMngService {

	/**
	 * @param crawlerDataCombBO
	 */
	void addCrawInfo(CrawlerDataCombBO crawlerDataCombBO);

}
