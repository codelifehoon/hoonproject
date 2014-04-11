package com.zebra.process.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebra.common.CommonConstants;
import com.zebra.common.dao.CommomPattenCodeDao;
import com.zebra.common.util.PattenUtil;
import com.zebra.process.crawler.domain.PageConfigBo;
import com.zebra.process.crawler.domain.WebPageInfoBO;
import com.zebra.process.parser.domain.ExpPattenBO;

@Service
public class DomParserImpl implements DomParser 
{
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private CommomPattenCodeDao commomPattenCodeDao;
	

	/* (non-Javadoc)
	 * @see com.zebra.process.parser.DomParser#compareWebPageInfo(com.zebra.process.crawler.domain.WebPageInfoBO, com.zebra.process.crawler.domain.WebPageInfoBO)
	 */
	public String  compareWebPageInfo(WebPageInfoBO source , WebPageInfoBO  target)
	{
		String reNewFlag = "Y";
		if (source.getCate1().equals(target.getCate1())
				&& source.getCate2().equals(target.getCate2())
				&& source.getCate3().equals(target.getCate3())
				&& source.getGoodsImg().equals(target.getGoodsImg())
				&& source.getGoodsNm().equals(target.getGoodsNm())
				&& source.getGoodsNo().equals(target.getGoodsNo())
				&& source.getGoodsPrice().equals(target.getGoodsPrice())
				&& source.getGoodsUrl().equals(target.getGoodsUrl())
				) reNewFlag = "N";
		
		return reNewFlag;
	}
	
	/* (non-Javadoc)
	 * @see com.zebra.process.parser.DomParser#doParsing(com.zebra.process.crawler.domain.PageConfigBo, com.zebra.process.crawler.domain.WebPageInfoBO)
	 */
	public WebPageInfoBO doParsing(String htmlString, WebPageInfoBO webPageInfoBO, HashMap<String, ExpPattenBO[]> pattenMap)
	{
		
		
		WebPageInfoBO webPageInfoBONew = null;
		Document doc = Jsoup.parse(htmlString);
		
		try {
			webPageInfoBONew = (WebPageInfoBO)BeanUtils.cloneBean(webPageInfoBO);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pattenMap = getPageInfoPatten(webPageInfoBO , pattenMap );
		
		webPageInfoBONew.setGoodsImg(selectPattenInfo(pattenMap.get(CommonConstants.PK_GOODS_IMG_PATTEN), doc));
		webPageInfoBONew.setGoodsNm(selectPattenInfo(pattenMap.get(CommonConstants.PK_GOODS_NAME_PATTEN), doc));
		webPageInfoBONew.setGoodsPrice(selectPattenInfo(pattenMap.get(CommonConstants.PK_GOODS_PRICE_PATTEN), doc));
		webPageInfoBONew.setCate1(selectPattenInfo(pattenMap.get(CommonConstants.PK_CATE1_PATTEN), doc));
		webPageInfoBONew.setCate2(selectPattenInfo(pattenMap.get(CommonConstants.PK_CATE2_PATTEN), doc));
		webPageInfoBONew.setCate3(selectPattenInfo(pattenMap.get(CommonConstants.PK_CATE3_PATTEN), doc));
		
		
		webPageInfoBONew.setReNewFlag(this.compareWebPageInfo(webPageInfoBO, webPageInfoBONew));
		if (!"".equals(webPageInfoBONew.getGoodsNm()) 
				&& !"".equals(webPageInfoBONew.getGoodsPrice())  ) webPageInfoBONew.setFailYn("N");
		
	
		if ("Y".equals(webPageInfoBONew.getFailYn()))
		{
			log.info("##### 수집실패:" + com.zebra.common.util.DebugUtil.DebugBo(webPageInfoBONew) );
		}
		
		return webPageInfoBONew;
	}
	
	

	private HashMap<String,ExpPattenBO[]> getPageInfoPatten(WebPageInfoBO webPageInfoBO , HashMap<String,ExpPattenBO[]> pattenMap )
	{
		if (pattenMap != null) return pattenMap;
		
		
		pattenMap = new HashMap<String,ExpPattenBO[]>();
		
		pattenMap.put(CommonConstants.PK_GOODS_IMG_PATTEN, commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , CommonConstants.PK_GOODS_IMG_PATTEN));
		pattenMap.put(CommonConstants.PK_GOODS_NAME_PATTEN, commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , CommonConstants.PK_GOODS_NAME_PATTEN));
		pattenMap.put(CommonConstants.PK_GOODS_PRICE_PATTEN, commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , CommonConstants.PK_GOODS_PRICE_PATTEN));
		pattenMap.put(CommonConstants.PK_CATE1_PATTEN, commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , CommonConstants.PK_CATE1_PATTEN));
		pattenMap.put(CommonConstants.PK_CATE2_PATTEN, commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , CommonConstants.PK_CATE2_PATTEN));
		pattenMap.put(CommonConstants.PK_CATE3_PATTEN, commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , CommonConstants.PK_CATE3_PATTEN));
		
		
		return pattenMap;
		
		
	}
	
	private String selectPattenInfo(ExpPattenBO[] expPattenBOs,Document doc) {
		String retVal = "";
		try
		{
			if (expPattenBOs == null) return retVal;
			for (ExpPattenBO expPattenBO : expPattenBOs )
			{
				if (expPattenBO.getPattenStr().equals("")) continue;
				Element element = doc.select(expPattenBO.getPattenStr()).first();
				retVal = element.html().trim() ;	
			}
		} catch (Exception e)
		{
			e.toString();
		}
		return retVal;
	}
}
