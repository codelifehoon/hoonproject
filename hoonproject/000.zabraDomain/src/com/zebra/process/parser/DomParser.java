/**
 * @FileName  : DomParser.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 1. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.process.parser;

import java.util.HashMap;

import lombok.extern.log4j.Log4j;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebra.business.craw.domain.ExpPattenBO;
import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.BaseConstants;
import com.zebra.common.dao.CommonPattenCodeDao;
import com.zebra.common.util.CmnUtil;

@Log4j
@Service
public class DomParser {


	@Autowired
	private CommonPattenCodeDao commomPattenCodeDao;

	/**
	 * 
	 */
	public DomParser() {
		super();
	}

	protected String compareWebPageInfo(WebPageInfoBO source, WebPageInfoBO  target) {
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

	protected HashMap<String,ExpPattenBO[]> getPageInfoPatten(WebPageInfoBO webPageInfoBO, HashMap<String,ExpPattenBO[]> pattenMap) {
		if (pattenMap != null) return pattenMap;
		
		
		HashMap<String,ExpPattenBO[]> retPattenMap = new HashMap<String,ExpPattenBO[]>();
		
		retPattenMap.put(BaseConstants.PK_GOODS_IMG_PATTEN, commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , BaseConstants.PK_GOODS_IMG_PATTEN));
		retPattenMap.put(BaseConstants.PK_GOODS_NAME_PATTEN, commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , BaseConstants.PK_GOODS_NAME_PATTEN));
		retPattenMap.put(BaseConstants.PK_GOODS_PRICE_PATTEN, commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , BaseConstants.PK_GOODS_PRICE_PATTEN));
		retPattenMap.put(BaseConstants.PK_GOODS_DISC_PATTEN, commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , BaseConstants.PK_GOODS_DISC_PATTEN));
		retPattenMap.put(BaseConstants.PK_CATE1_PATTEN, commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , BaseConstants.PK_CATE1_PATTEN));
		retPattenMap.put(BaseConstants.PK_CATE2_PATTEN, commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , BaseConstants.PK_CATE2_PATTEN));
		retPattenMap.put(BaseConstants.PK_CATE3_PATTEN, commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , BaseConstants.PK_CATE3_PATTEN));
		retPattenMap.put(BaseConstants.PK_GOODS_ISBUY_PATTEN, commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , BaseConstants.PK_GOODS_ISBUY_PATTEN));
	
		
		
		return retPattenMap;
		
		
	}

	protected String selectPattenInfo(ExpPattenBO[] expPattenBOs, Document doc) {
		String retVal = "";
	
		try
		{
			if (expPattenBOs == null) return retVal;
			for (ExpPattenBO expPattenBO : expPattenBOs )
			{
				if ("".equals(expPattenBO.getPattenStr())) continue;
				Element element = doc.select(expPattenBO.getPattenStr()).first();
			
				retVal =  element.html().trim();
				
				if (!BaseConstants.PK_GOODS_IMG_PATTEN.equals(expPattenBO.getPattenKind())
						&&  !BaseConstants.PK_GOODS_ISBUY_PATTEN.equals(expPattenBO.getPattenKind()))
				{
					retVal = CmnUtil.removeFullHtmlTag(retVal);
				}
				
				if (BaseConstants.PK_GOODS_PRICE_PATTEN.equals(expPattenBO.getPattenKind())
						|| BaseConstants.PK_GOODS_DISC_PATTEN.equals(expPattenBO.getPattenKind()) ) 
				{
					retVal = CmnUtil.getOnlyNumberString(retVal);
				}
	
				
				log.debug("expPattenBO.getPattenKind():" + expPattenBO.getPattenKind());
				log.debug("retVal:" + retVal);
				log.debug("element.html():" + element.html().trim());
				
				
			}
		} catch (Exception e)
		{
			e.toString();
		}
		return retVal;
	}

}