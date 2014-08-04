/**
 * @FileName  : DataInitClass.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 6. 27. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package test.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;

import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.business.craw.domain.ExpPattenBO;
import com.zebra.common.BaseConstants;
import com.zebra.common.BaseFactory;
import com.zebra.common.util.DateTime;

public class DataInitClass {
	

	public static  HashMap<String, ExpPattenBO[]> getDefaultPagePatten(String initType) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
		{
			HashMap<String, ExpPattenBO[]>  pattenMap = new HashMap<String, ExpPattenBO[]> ();
	
			ExpPattenBO[] visitSitePatten  	= new ExpPattenBO[1];
			ExpPattenBO[] visitUrlPatten  = new ExpPattenBO[1];
			ExpPattenBO[] goodsUrlPatten  = new ExpPattenBO[1];
			ExpPattenBO[] goodsNoPatten  = new ExpPattenBO[1];

			ExpPattenBO[] goodsNmPatten  	= new ExpPattenBO[1];
			ExpPattenBO[] goodsPricePatten  = new ExpPattenBO[1];
			ExpPattenBO[] goodsImgPatten  	= new ExpPattenBO[1];
			ExpPattenBO[] goodsDisc  	= new ExpPattenBO[1];
			ExpPattenBO[] cate1Patten  	= new ExpPattenBO[1];
			ExpPattenBO[] cate2Patten  	= new ExpPattenBO[1];
			ExpPattenBO[] cate3Patten  	= new ExpPattenBO[1];
			ExpPattenBO[] goodsIsBuyPatten  	= new ExpPattenBO[1];
			
			
			
			

			
			
		
			//DataInitClass dataInitClass1 = new DataInitClass();
			
			//Method method1 = DataInitClass.class.getMethod("testMethod",String[].class);
			//Object retobj1 = method1.invoke(dataInitClass1, new Object[] {new String[] {"AAA","BBB"} });
			
			//if (1==1) return null;
			
			DataInitClass dataInitClass = new DataInitClass();
			
			Method method = DataInitClass.class.getMethod( "get" + initType + "Data",new Class[] {ExpPattenBO[].class,ExpPattenBO[].class,ExpPattenBO[].class,ExpPattenBO[].class,ExpPattenBO[].class
																								,ExpPattenBO[].class,ExpPattenBO[].class,ExpPattenBO[].class,ExpPattenBO[].class,ExpPattenBO[].class
																								,ExpPattenBO[].class,ExpPattenBO[].class});
			Object retobj = method.invoke(dataInitClass, new Object[] {visitSitePatten,visitUrlPatten,goodsUrlPatten,goodsNoPatten,goodsNmPatten
																	,goodsPricePatten,goodsImgPatten,goodsDisc,cate1Patten,cate2Patten,cate3Patten,goodsIsBuyPatten} );
			
		
			
			pattenMap.put(BaseConstants.PK_GOODS_NAME_PATTEN, goodsNmPatten);
			pattenMap.put(BaseConstants.PK_GOODS_PRICE_PATTEN, goodsPricePatten);
			pattenMap.put(BaseConstants.PK_GOODS_IMG_PATTEN, goodsImgPatten);
			
			
			pattenMap.put(BaseConstants.PK_GOODS_DISC_PATTEN, goodsDisc);
			pattenMap.put(BaseConstants.PK_CATE1_PATTEN, cate1Patten);
			pattenMap.put(BaseConstants.PK_CATE2_PATTEN, cate2Patten);
			pattenMap.put(BaseConstants.PK_CATE3_PATTEN, cate3Patten);
			
			pattenMap.put(BaseConstants.PK_VISIT_URL_PATTEN, visitUrlPatten);
			pattenMap.put(BaseConstants.PK_VISIT_SITE_PATTEN,visitSitePatten );
			pattenMap.put(BaseConstants.PK_GOODS_URL_PATTEN, goodsUrlPatten);
			pattenMap.put(BaseConstants.PK_GOODS_NO_PATTEN,goodsNoPatten );
			pattenMap.put(BaseConstants.PK_GOODS_ISBUY_PATTEN,goodsIsBuyPatten );
			
			
			
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


	public static  int testMethod(String[] str)
	{
		System.out.println("#### call testMethod:" + str.length);
		return 0;
	}
	
	public static  CrawConfigBO get11stCrawConfig()
	{
		CrawConfigBO crawConfigBO = BaseFactory.create(CrawConfigBO.class);
	
		Date currentDt = new Date();
		
		
		crawConfigBO.setCrawlThreadCount(1);
		crawConfigBO.setSiteNm("11st");
		crawConfigBO.setSeedURL(new String[]{"http://m.11st.co.kr/MW/Product/productBasicInfo.tmall?prdNo=954321212"});
		crawConfigBO.setCrawlDepth(1);
		crawConfigBO.setUseYn("Y");
		crawConfigBO.setCrawlAgent("Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
		crawConfigBO.setCreateNo("999");
		crawConfigBO.setCreateDt(currentDt);
	
		
		return crawConfigBO;
	}
	
	public static  CrawConfigBO getAuctionCrawConfig()
	{
		CrawConfigBO crawConfigBO = BaseFactory.create(CrawConfigBO.class);
	
		Date currentDt = new Date();
		
	
		crawConfigBO.setCrawlThreadCount(1);
		crawConfigBO.setSiteNm("auctionMobile");
		crawConfigBO.setSeedURL(new String[]{"http://mobile.auction.co.kr"});
		crawConfigBO.setCrawlDepth(1);
		crawConfigBO.setUseYn("Y");
		crawConfigBO.setCrawlAgent("Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
		crawConfigBO.setCreateNo("999");
		crawConfigBO.setCreateDt(currentDt);
	
		
		return crawConfigBO;
	}
	
	

	/**
	 * @param visitSitePatten
	 * @param visitUrlPatten
	 * @param goodsUrlPatten
	 * @param goodsNoPatten
	 * @param goodsNmPatten
	 * @param goodsPricePatten
	 * @param goodsImgPatten
	 * @param goodsDisc TODO
	 * @param cate1Patten
	 * @param cate2Patten
	 * @param cate3Patten
	 */
	public static void get11stData(ExpPattenBO[] visitSitePatten,
			ExpPattenBO[] visitUrlPatten, ExpPattenBO[] goodsUrlPatten,
			ExpPattenBO[] goodsNoPatten, ExpPattenBO[] goodsNmPatten,
			ExpPattenBO[] goodsPricePatten, ExpPattenBO[] goodsImgPatten,
			ExpPattenBO[] goodsDisc, ExpPattenBO[] cate1Patten,
			ExpPattenBO[] cate2Patten, ExpPattenBO[] cate3Patten,ExpPattenBO[] goodsIsBuyPatten) 
	{
		System.out.println("#### call testMethod:get11stData");
		
		goodsNmPatten[0]		= new ExpPattenBO("#cts section.base div.dtl_heading",BaseConstants.PK_GOODS_NAME_PATTEN, "");
		goodsPricePatten[0]		= new ExpPattenBO("#cts section.base form div.dtl_info div.total div.prc strong",BaseConstants.PK_GOODS_PRICE_PATTEN,"");
		goodsImgPatten[0]		= new ExpPattenBO("#mwDev_imageWrap",BaseConstants.PK_GOODS_IMG_PATTEN, "");
		goodsDisc[0]				= new ExpPattenBO("",BaseConstants.PK_GOODS_DISC_PATTEN, "");	
		cate1Patten[0]				= new ExpPattenBO("",BaseConstants.PK_CATE1_PATTEN, "");
		cate2Patten[0]				= new ExpPattenBO("",BaseConstants.PK_CATE2_PATTEN, "");
		cate3Patten[0]				= new ExpPattenBO("",BaseConstants.PK_CATE3_PATTEN, "");
		visitUrlPatten[0]			= new ExpPattenBO(".*(\\.(html|tmall)).*",BaseConstants.PK_VISIT_URL_PATTEN, "");
		visitSitePatten[0]			= new ExpPattenBO(".*(11st.co.kr).*",BaseConstants.PK_VISIT_SITE_PATTEN, "");
		goodsUrlPatten[0]			= new ExpPattenBO(".*(productBasicInfo.tmall?).*",BaseConstants.PK_GOODS_URL_PATTEN, "");
		goodsNoPatten[0]			= new ExpPattenBO("prdno=\\d*",BaseConstants.PK_GOODS_NO_PATTEN, "");
		goodsIsBuyPatten[0]			= new ExpPattenBO("#cts section.base form div.dtl_info div.btn_set",BaseConstants.PK_GOODS_ISBUY_PATTEN, "");
		
	}
	
	/**
	 * @param visitSitePatten
	 * @param visitUrlPatten
	 * @param goodsUrlPatten
	 * @param goodsNoPatten
	 * @param goodsNmPatten
	 * @param goodsPricePatten
	 * @param goodsImgPatten
	 * @param goodsDisc TODO
	 * @param cate1Patten
	 * @param cate2Patten
	 * @param cate3Patten
	 */
	public static void getAuctionData(ExpPattenBO[] visitSitePatten,
			ExpPattenBO[] visitUrlPatten, ExpPattenBO[] goodsUrlPatten,
			ExpPattenBO[] goodsNoPatten, ExpPattenBO[] goodsNmPatten,
			ExpPattenBO[] goodsPricePatten, ExpPattenBO[] goodsImgPatten,
			ExpPattenBO[] goodsDisc, ExpPattenBO[] cate1Patten,
			ExpPattenBO[] cate2Patten, ExpPattenBO[] cate3Patten,ExpPattenBO[] goodsIsBuyPatten) 
	{
		goodsNmPatten[0]		= new ExpPattenBO("#itemInfoForMain div.product-info_header",BaseConstants.PK_GOODS_NAME_PATTEN, "");
		goodsPricePatten[0]		= new ExpPattenBO("#itemInfoForMain div.product-info_group--main div.product-info_block div.data-group.product-info_offer div.data-group_content strong.product-info_offer_price",BaseConstants.PK_GOODS_PRICE_PATTEN ,"");
		goodsImgPatten[0]		= new ExpPattenBO("#image_wrapper li.content-slider_item.active a",BaseConstants.PK_GOODS_IMG_PATTEN, "");
		goodsDisc[0]				= new ExpPattenBO("#itemInfoForMain div.product-info_group--main div.product-info_block div.data-group.product-info_offer div.data-group_content ul.product-info_offer_highlight li.product-info_offer_highlight_item div.offer--discount",BaseConstants.PK_GOODS_DISC_PATTEN , "");
		cate1Patten[0]				= new ExpPattenBO("",BaseConstants.PK_CATE1_PATTEN , "");
		cate2Patten[0]				= new ExpPattenBO("",BaseConstants.PK_CATE2_PATTEN , "");
		cate3Patten[0]				= new ExpPattenBO("",BaseConstants.PK_CATE3_PATTEN , "");
		visitUrlPatten[0]			= new ExpPattenBO(".*(\\.aspx).*",BaseConstants.PK_VISIT_URL_PATTEN , "");
		visitSitePatten[0]			= new ExpPattenBO(".*(auction.co.kr).*",BaseConstants.PK_VISIT_SITE_PATTEN , "");
		goodsUrlPatten[0]			= new ExpPattenBO(".*(ViewItem.aspx?).*",BaseConstants.PK_GOODS_URL_PATTEN , "");
		goodsNoPatten[0]			= new ExpPattenBO("ItemNo=\\d*",BaseConstants.PK_GOODS_NO_PATTEN , "");
		goodsIsBuyPatten[0]			= new ExpPattenBO("#buy",BaseConstants.PK_GOODS_ISBUY_PATTEN, "");
	}

	
	/**
	 * @param visitSitePatten
	 * @param visitUrlPatten
	 * @param goodsUrlPatten
	 * @param goodsNoPatten
	 * @param goodsNmPatten
	 * @param goodsPricePatten
	 * @param goodsImgPatten
	 * @param goodsDisc TODO
	 * @param cate1Patten
	 * @param cate2Patten
	 * @param cate3Patten
	 */
	public static void getGmarketData(ExpPattenBO[] visitSitePatten,
			ExpPattenBO[] visitUrlPatten, ExpPattenBO[] goodsUrlPatten,
			ExpPattenBO[] goodsNoPatten, ExpPattenBO[] goodsNmPatten,
			ExpPattenBO[] goodsPricePatten, ExpPattenBO[] goodsImgPatten,
			ExpPattenBO[] goodsDisc, ExpPattenBO[] cate1Patten,
			ExpPattenBO[] cate2Patten, ExpPattenBO[] cate3Patten,ExpPattenBO[] goodsIsBuyPatten) 
	{
		System.out.println("#### call testMethod:getGmarketData");
		
		goodsNmPatten[0]		= new ExpPattenBO(".prod_tit > h3:nth-child(2) > span:nth-child(1)",BaseConstants.PK_GOODS_NAME_PATTEN, "");
		goodsPricePatten[0]		= new ExpPattenBO(".pri1",BaseConstants.PK_GOODS_PRICE_PATTEN,"");
		goodsImgPatten[0]		= new ExpPattenBO("#content div.goods div.prod_img a",BaseConstants.PK_GOODS_IMG_PATTEN, "");
		goodsDisc[0]				= new ExpPattenBO("#GoodsPrice ul.sub_tit_grp p.disct",BaseConstants.PK_GOODS_DISC_PATTEN, "");	
		cate1Patten[0]				= new ExpPattenBO("div#ctgr_list>p:eq(0)>span:eq(1)>a:eq(0)",BaseConstants.PK_CATE1_PATTEN, "");
		cate2Patten[0]				= new ExpPattenBO("div#ctgr_list>p:eq(0)>span:eq(2)>a:eq(0)",BaseConstants.PK_CATE2_PATTEN, "");
		cate3Patten[0]				= new ExpPattenBO("div#ctgr_list>p:eq(0)>span:eq(3)>a:eq(0)",BaseConstants.PK_CATE3_PATTEN, "");
		visitUrlPatten[0]			= new ExpPattenBO(".*(Item).*",BaseConstants.PK_VISIT_URL_PATTEN, "");
		visitSitePatten[0]			= new ExpPattenBO(".*(gmarket.co.kr).*",BaseConstants.PK_VISIT_SITE_PATTEN, "");
		goodsUrlPatten[0]			= new ExpPattenBO(".*(Item?).*",BaseConstants.PK_GOODS_URL_PATTEN, "");
		goodsNoPatten[0]			= new ExpPattenBO("goodsCode=\\d*",BaseConstants.PK_GOODS_NO_PATTEN, "");
		goodsIsBuyPatten[0]			= new ExpPattenBO(".immt",BaseConstants.PK_GOODS_ISBUY_PATTEN, "");
		
	}
	
	
}
