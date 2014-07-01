/**
 * @FileName  : DataInitClass.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 6. 27. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package test.junit;

import java.util.Date;
import java.util.HashMap;

import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.business.craw.domain.ExpPattenBO;
import com.zebra.common.BaseConstants;
import com.zebra.common.BaseFactory;
import com.zebra.common.util.DateTime;

public class DataInitClass {

	public static  HashMap<String, ExpPattenBO[]> getDefaultPagePatten()
		{
			HashMap<String, ExpPattenBO[]>  pattenMap = new HashMap<String, ExpPattenBO[]> ();
	
	
			ExpPattenBO[] visitSitePatten  	= new ExpPattenBO[1];
			ExpPattenBO[] visitUrlPatten  = new ExpPattenBO[1];
			ExpPattenBO[] goodsUrlPatten  = new ExpPattenBO[1];
			ExpPattenBO[] goodsNoPatten  = new ExpPattenBO[1];
			
			
	
			ExpPattenBO[] goodsNmPatten  	= new ExpPattenBO[1];
			ExpPattenBO[] goodsPricePatten  = new ExpPattenBO[1];
			ExpPattenBO[] goodsImgPatten  	= new ExpPattenBO[1];
			ExpPattenBO[] cate1Patten  	= new ExpPattenBO[1];
			ExpPattenBO[] cate2Patten  	= new ExpPattenBO[1];
			ExpPattenBO[] cate3Patten  	= new ExpPattenBO[1];
			
			
			
			goodsNmPatten[0]		= new ExpPattenBO("div#layBodyWrap div#layBody div.prdc_wrap div#productInfoMain.prdc_top_wrap  div.prdc_top_left div.prdc_heading_v2 h2","");
			goodsPricePatten[0]		= new ExpPattenBO("div#layBodyWrap div#layBody div.prdc_wrap div#productInfoMain.prdc_top_wrap div.prdc_top_left div#prdcInfoColumn2.prdc_info_column2 div.prdc_default_info div.prdc_price_info ul li strong.prdc_price em");
			goodsImgPatten[0]		= new ExpPattenBO("","");
			cate1Patten[0]				= new ExpPattenBO("div#location_boxid_1 button#headSel_1","");
			cate2Patten[0]				= new ExpPattenBO("div#location_boxid_2 button#headSel_2","");
			
			cate3Patten[0]				= new ExpPattenBO("div#location_boxid_3 button#headSel_3","");
			visitUrlPatten[0]			= new ExpPattenBO(".*(\\.(html|tmall)).*","");
			visitSitePatten[0]			= new ExpPattenBO(".*(11st.co.kr).*","");
			goodsUrlPatten[0]			= new ExpPattenBO(".*(sellerproductdetail.tmall?).*","");
			goodsNoPatten[0]			= new ExpPattenBO("prdno=\\d*","");
		
			
			
			pattenMap.put(BaseConstants.PK_GOODS_NAME_PATTEN, goodsNmPatten);
			pattenMap.put(BaseConstants.PK_GOODS_PRICE_PATTEN, goodsPricePatten);
			pattenMap.put(BaseConstants.PK_GOODS_IMG_PATTEN, goodsImgPatten);
			pattenMap.put(BaseConstants.PK_CATE1_PATTEN, cate1Patten);
			pattenMap.put(BaseConstants.PK_CATE2_PATTEN, cate2Patten);
			pattenMap.put(BaseConstants.PK_CATE3_PATTEN, cate3Patten);
			
			pattenMap.put(BaseConstants.PK_VISIT_URL_PATTEN, visitUrlPatten);
			pattenMap.put(BaseConstants.PK_VISIT_SITE_PATTEN,visitSitePatten );
			pattenMap.put(BaseConstants.PK_GOODS_URL_PATTEN, goodsUrlPatten);
			pattenMap.put(BaseConstants.PK_GOODS_NO_PATTEN,goodsNoPatten );
			
			
			
			
	/*
			pageConfigBo.setGoodsNmPatten(goodsNmPatten);
			pageConfigBo.setGoodsPricePatten(goodsPricePatten);
			pageConfigBo.setGoodsImgPatten(goodsImgPatten);
			pageConfigBo.setCate1Patten(cate1Patten);
			pageConfigBo.setCate2Patten(cate2Patten);
			pageConfigBo.setCate3Patten(cate3Patten);
	*/		
			
			return pattenMap;
		}

	public static  CrawConfigBO getDefaultCrawConfig()
	{
		CrawConfigBO crawConfigBO = BaseFactory.create(CrawConfigBO.class);
	
		Date currentDt = new Date();
		
		crawConfigBO.setSiteNm("testSiteNm");
		crawConfigBO.setSeedStrURL("seedUrl");
		crawConfigBO.setUseYn("Y");
		crawConfigBO.setCrawlAgent("agentNnm");
		
		crawConfigBO.setCreateNo("999");
		crawConfigBO.setCreateDt(currentDt);
	
		
		return crawConfigBO;
	}

}
