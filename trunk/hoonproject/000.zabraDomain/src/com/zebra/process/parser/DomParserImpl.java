package com.zebra.process.parser;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.zebra.common.CommonConstants;
import com.zebra.common.dao.CommomPattenCodeDao;
import com.zebra.common.util.PattenUtil;
import com.zebra.process.crawler.domain.PageConfigBo;
import com.zebra.process.crawler.domain.WebPageInfoBO;
import com.zebra.process.parser.domain.ExpPattenBO;

public class DomParserImpl implements DomParser 
{
	
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
	public WebPageInfoBO doParsing(PageConfigBo pageConfigBo, WebPageInfoBO webPageInfoBO)
	{
		
		
		WebPageInfoBO webPageInfoBONew = null;
		Document doc = Jsoup.parse(pageConfigBo.getHtmlString());
		
		try {
			webPageInfoBONew = (WebPageInfoBO)BeanUtils.cloneBean(webPageInfoBO);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		webPageInfoBONew.setGoodsImg(selectPattenInfo(commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , CommonConstants.PK_GOODS_IMG_PATTEN)
									, doc));
		webPageInfoBONew.setGoodsNm(selectPattenInfo(commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , CommonConstants.PK_GOODS_NAME_PATTEN)
									, doc));
		webPageInfoBONew.setGoodsPrice(selectPattenInfo(commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , CommonConstants.PK_GOODS_PRICE_PATTEN)
									, doc));
		webPageInfoBONew.setCate1(selectPattenInfo(commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , CommonConstants.PK_CATE1_PATTEN)
									, doc));
		webPageInfoBONew.setCate2(selectPattenInfo(commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , CommonConstants.PK_CATE2_PATTEN)
									, doc));
		webPageInfoBONew.setCate3(selectPattenInfo(commomPattenCodeDao.selectPattenCodeArray(webPageInfoBO.getSiteConfigSeq() , CommonConstants.PK_CATE3_PATTEN)
									, doc));
		webPageInfoBONew.setReNewFlag(this.compareWebPageInfo(webPageInfoBO, webPageInfoBONew));
		
		return webPageInfoBONew;
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
				retVal = element.html() ;	
			}
		} catch (Exception e)
		{
			e.toString();
		}
		return retVal;
	}
}
