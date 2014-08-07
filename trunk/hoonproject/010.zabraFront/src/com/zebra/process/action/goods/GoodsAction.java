/**
 * @FileName  : GoodsAction.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 7. 25. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.process.action.goods;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.BaseFactory;
import com.zebra.process.action.BaseAction;
import com.zebra.process.goods.GoodsService;


@Controller @Log4j
public class GoodsAction extends BaseAction {

	@Autowired GoodsService goodsService ;

	@RequestMapping("/goods/goodsDetail")
	public  ModelAndView goodsDeatil(@RequestParam final HashMap<String, Object> paramMap ,final HttpServletRequest reuqest) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		final  ModelAndView mav = new ModelAndView();
		WebPageInfoBO webPageInfoBO = BaseFactory.create(WebPageInfoBO.class);
		
		webPageInfoBO.setPageInfoListSeq("0");
		
		BeanUtils.populate(webPageInfoBO, paramMap);

		HashMap<String,Object> goodsDetailMap = goodsService.goodsDetail(webPageInfoBO);
		
		mav.addObject("goodsDetailMap", goodsDetailMap);
		mav.setViewName("/goods/goodsDetail");
		return mav;
	}
}
