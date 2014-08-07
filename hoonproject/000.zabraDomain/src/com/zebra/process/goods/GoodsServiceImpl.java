/**
 * @FileName  : GoodsServiceImpl.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 7. 28. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.process.goods;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import lombok.extern.log4j.Log4j;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebra.business.analysis.dao.GoodsPriceChangeDAO;
import com.zebra.business.analysis.dao.GoodsPriceTrendDAO;
import com.zebra.business.analysis.domain.GoodsPriceChangeBO;
import com.zebra.business.analysis.domain.GoodsPriceTrendBO;
import com.zebra.business.craw.dao.CrawConfigDAO;
import com.zebra.business.craw.dao.PageInfoDAO;
import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.BaseFactory;

@Service @Log4j
public class GoodsServiceImpl implements GoodsService {

		@Autowired	PageInfoDAO pageInfoDAO;
		@Autowired	GoodsPriceChangeDAO goodsPriceChangeDAO;
		@Autowired	GoodsPriceTrendDAO goodsPriceTrendDAO;
		@Autowired	CrawConfigDAO crawConfigDAO;
		
	/* (non-Javadoc)
	 * @see com.zebra.process.goods.GoodsService#goodsDetail(com.zebra.business.craw.domain.WebPageInfoBO)
	 */
	public HashMap<String, Object> goodsDetail(WebPageInfoBO webPageInfoBO) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		
		GoodsPriceChangeBO	goodsPriceChange = BaseFactory.create(GoodsPriceChangeBO.class);
		GoodsPriceTrendBO		goodsPriceTrend = BaseFactory.create(GoodsPriceTrendBO.class);
		CrawConfigBO		crawConfigBO = BaseFactory.create(CrawConfigBO.class);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		goodsPriceChange.setPageInfoListSeq(webPageInfoBO.getPageInfoListSeq());
		goodsPriceTrend.setPageInfoListSeq(webPageInfoBO.getPageInfoListSeq());
		
		
		WebPageInfoBO 			webPageInfoBOResult = pageInfoDAO.selectPageInfo (webPageInfoBO);
		
		crawConfigBO.setSiteConfigSeq(webPageInfoBOResult.getSiteConfigSeq());
		crawConfigBO = crawConfigDAO.selectCrawConfigList(crawConfigBO).get(0);
		List<WebPageInfoBO> 	priceChangeList = goodsPriceChangeDAO.selectGoodsPriceChangeList(goodsPriceChange);
		List<GoodsPriceTrendBO> 	priceTrendList = goodsPriceTrendDAO.selectGoodsPriceTrend (goodsPriceTrend);


		if (log.isDebugEnabled())
		{
			log.debug("webPageInfoBOResult:" + BeanUtils.describe(webPageInfoBOResult).toString() );
			log.debug("crawConfigBO:" + BeanUtils.describe(crawConfigBO).toString() );
			log.debug("priceChangeList:" + priceChangeList.size() );
			log.debug("priceTrendList:" + priceChangeList.size() );
		}
		
		resultMap.put("webPageInfoBO", webPageInfoBOResult);
		resultMap.put("priceChangeList", priceChangeList);
		resultMap.put("priceTrendList", priceTrendList);
		resultMap.put("crawConfigBO", crawConfigBO);
		
		
		
		
		
		return resultMap;
	}

}
