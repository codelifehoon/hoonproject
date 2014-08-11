/**
 * @FileName  : ListingAction.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 7. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.process.action.listing;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zebra.business.analysis.domain.SearchCombBO;
import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.BaseFactory;
import com.zebra.common.util.DateTime;
import com.zebra.common.util.NumUtil;
import com.zebra.process.action.BaseAction;
import com.zebra.process.analysis.AnalysisInfoService;


@Controller @Log4j
public class ListingAction extends BaseAction {

	@Autowired	AnalysisInfoService analysisInfoService;

	@RequestMapping("/listing/searchGoodsMain")
	public  ModelAndView searchGoods(@RequestParam  HashMap<String, Object> paramMap , HttpServletRequest reuqest) 
	{
		final ModelAndView mav = new ModelAndView();
		
	
		mav.setViewName("/listing/searchGoodsMain"); 
		return mav;
	}
	
	@RequestMapping("/listing/ajax/searchGoods")
	public  ModelAndView searchGoodsAjax(@RequestParam  HashMap<String, Object> paramMap , HttpServletRequest reuqest) throws IllegalAccessException, InvocationTargetException 
	{
		final ModelAndView mav = new ModelAndView();
		
		log.error("##### searchGoodsAjax start:" + DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
		
		SearchCombBO searchCombBO = BaseFactory.create(SearchCombBO.class); 
		

		BeanUtils.populate(searchCombBO, paramMap);
		BeanUtils.populate(searchCombBO.getWebPageInfoBO(), paramMap);
		
	
		if (log.isDebugEnabled()) log.debug("searchCombBO:" + searchCombBO.toString());
		
		List<SearchCombBO> searchResultList = analysisInfoService.doGoodsSearch(searchCombBO);
		
		log.debug("searchResultList:" + searchResultList.size());
		
		mav.addObject("searchResultList", searchResultList);
		mav.addObject("paramMap",paramMap); 
		mav.setViewName("/ajaxprefix/listing/ajax/searchGoods"); 
		
		return mav;
	}
	
}


