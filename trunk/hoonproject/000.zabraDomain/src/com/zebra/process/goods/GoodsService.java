/**
 * @FileName  : GoodsService.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 7. 28. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.process.goods;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.zebra.business.craw.domain.WebPageInfoBO;

public interface GoodsService {

	/**
	 * @param webPageInfoBO
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	HashMap<String, Object> goodsDetail(WebPageInfoBO webPageInfoBO) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;

}
