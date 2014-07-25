/**
 * @FileName  : crawMngService.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 6. 27. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.process.crawregister;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.zebra.business.craw.domain.CrawlerDataCombBO;

 
@Validated
public interface CrawMngService {

	/**
	 * @param crawlerDataCombBO
	 */
	void addCrawInfo(@NotNull CrawlerDataCombBO crawlerDataCombBO);

	/**
	 * @param crawlerDataCombBO
	 */
	void editCrawInfo(@NotNull CrawlerDataCombBO crawlerDataCombBO);

}
