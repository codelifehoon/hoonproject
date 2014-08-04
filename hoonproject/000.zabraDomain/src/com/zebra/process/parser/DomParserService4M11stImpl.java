package com.zebra.process.parser;


import lombok.extern.log4j.Log4j;


import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.zebra.business.craw.domain.ExpPattenBO;
import com.zebra.common.BaseConstants;
import com.zebra.common.util.CmnUtil;

@Log4j
@Service("DomParserService4M11stImpl")
public class DomParserService4M11stImpl extends DomParser implements DomParserService 
{

	
	/**
	 * @param retVal
	 * @param expPattenBO
	 * @return
	 */
	
	@Override
	protected String getPattenData(String retVal, ExpPattenBO expPattenBO) {
		if (BaseConstants.PK_GOODS_IMG_PATTEN.equals(expPattenBO.getPattenKind()))
		{
			Document localDoc;
		 	Element  localElement;
		 	String   localQuery = ".swipe-view > img:eq(0)";
		 	
		 	localDoc = Jsoup.parse(retVal);
		 	localElement = localDoc.select(localQuery).first();
		 	retVal  = localElement.attr("src").trim();
		}
		if (BaseConstants.PK_GOODS_ISBUY_PATTEN.equals(expPattenBO.getPattenKind()))
		{
			Document localDoc;
		 	Element  localElement;
		 	String   localQuery = ".b1";
		 	
		 	localDoc = Jsoup.parse(retVal);
		 	localElement = localDoc.select(localQuery).first();
		 	retVal  = localElement.attr("value").trim();
		 	
		 	if ("구매하기".equals(retVal)) retVal = "Y";
		}
		else if (BaseConstants.PK_GOODS_PRICE_PATTEN.equals(expPattenBO.getPattenKind())
				|| BaseConstants.PK_GOODS_DISC_PATTEN.equals(expPattenBO.getPattenKind()) ) 
		{
			retVal = CmnUtil.getOnlyNumberString(retVal);
		}
		else 
		{
			retVal = CmnUtil.removeFullHtmlTag(retVal);
		}
		return retVal;
	}
}
