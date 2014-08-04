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
@Service("DomParserService4MGmarketImpl")
public class DomParserService4MGmarketImpl extends DomParser implements DomParserService 
{
	@Override
	protected String getPattenData(String retVal, ExpPattenBO expPattenBO) {
		if (BaseConstants.PK_GOODS_IMG_PATTEN.equals(expPattenBO.getPattenKind()))
		{
			Document localDoc;
		 	Element  localElement;
		 	String   localQuery = "img";
		 	
		 	localDoc = Jsoup.parse(retVal);
		 	localElement = localDoc.select(localQuery).first();
		 	retVal  = localElement.attr("src").trim();
		}
		else if (BaseConstants.PK_GOODS_ISBUY_PATTEN.equals(expPattenBO.getPattenKind()))
		{
		 	if ("즉시구매".equals(retVal)) retVal = "Y";
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
