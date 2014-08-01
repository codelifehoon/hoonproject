package com.zebra.process.parser;

import java.util.HashMap;

import lombok.extern.log4j.Log4j;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.zebra.business.craw.domain.ExpPattenBO;
import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.BaseConstants;

@Log4j
@Service
public class DomParserImpl extends DomParser implements DomParserService 
{

	
	/* (non-Javadoc)
	 * @see com.zebra.process.parser.DomParser#doParsing(com.zebra.process.crawler.domain.PageConfigBo, com.zebra.process.crawler.domain.WebPageInfoBO)
	 */
	public WebPageInfoBO doParsing(String htmlString, WebPageInfoBO webPageInfoBO, HashMap<String, ExpPattenBO[]> pattenParamMap) throws Exception
	{
		
		WebPageInfoBO webPageInfoBONew = null;
		Document doc = Jsoup.parse(htmlString);
		HashMap<String, ExpPattenBO[]> pattenMap;
		
		
		try {
			webPageInfoBONew = (WebPageInfoBO)BeanUtils.cloneBean(webPageInfoBO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		
		pattenMap = getPageInfoPatten(webPageInfoBO , pattenParamMap );
		
		webPageInfoBONew.setGoodsImg(selectPattenInfo(pattenMap.get(BaseConstants.PK_GOODS_IMG_PATTEN), doc));
		webPageInfoBONew.setGoodsNm(selectPattenInfo(pattenMap.get(BaseConstants.PK_GOODS_NAME_PATTEN), doc));
		webPageInfoBONew.setGoodsPrice(selectPattenInfo(pattenMap.get(BaseConstants.PK_GOODS_PRICE_PATTEN), doc));
		webPageInfoBONew.setGoodsDisc(selectPattenInfo(pattenMap.get(BaseConstants.PK_GOODS_DISC_PATTEN), doc));
		webPageInfoBONew.setCate1(selectPattenInfo(pattenMap.get(BaseConstants.PK_CATE1_PATTEN), doc));
		webPageInfoBONew.setCate2(selectPattenInfo(pattenMap.get(BaseConstants.PK_CATE2_PATTEN), doc));
		webPageInfoBONew.setCate3(selectPattenInfo(pattenMap.get(BaseConstants.PK_CATE3_PATTEN), doc));
		webPageInfoBONew.setGoodsIsbuyPatten(selectPattenInfo(pattenMap.get(BaseConstants.PK_GOODS_ISBUY_PATTEN), doc));
		
		
		webPageInfoBONew.setReNewFlag(this.compareWebPageInfo(webPageInfoBO, webPageInfoBONew));
		if (!"".equals(webPageInfoBONew.getGoodsNm()) 
				&& !"".equals(webPageInfoBONew.getGoodsPrice())  ) webPageInfoBONew.setFailYn("N");
		
	
		if ("Y".equals(webPageInfoBONew.getFailYn()))
		{
			log.info("##### 수집실패:" + com.zebra.common.util.DebugUtil.debugBo(webPageInfoBONew) );
		}
		
		return webPageInfoBONew;
	}
}
