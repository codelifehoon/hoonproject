package com.zebra.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.log4j.Log4j;


import com.zebra.business.craw.domain.CrawlerDataCombBO;
import com.zebra.business.craw.domain.ExpPattenBO;
import com.zebra.common.BaseConstants;
import com.zebra.common.BaseFactory;

import edu.uci.ics.crawler4j.crawler.WebCrawler;

@Log4j
public class PattenUtil {

	
	public static boolean pattenMaches(ExpPattenBO[] pattens,String inputStr)
	{
		boolean flag= false;
		for (ExpPattenBO patten : pattens )
		{       
				Pattern FILTERS = Pattern.compile(patten.getPattenStr());
	      		flag = FILTERS.matcher(inputStr).matches();
	      		if (flag) break;
		}
		
		return flag;
	}
	
	public static String pattenString(ExpPattenBO[] pattens,String inputStr)
	{
		String retVal= "";
		for (ExpPattenBO patten : pattens )
		{           		
				Pattern FILTERS = Pattern.compile(patten.getPattenStr());
				Matcher matcher = FILTERS.matcher(inputStr);
	      						
				if (matcher.find()) retVal = matcher.group(); 
	      		if (!retVal.equals("")) break;
		}
		
		return retVal;
	}

	public static String pattenName2Code(String name)
	{
		if ("goodsNamePatten".equals(name)) return  BaseConstants.PK_GOODS_NAME_PATTEN ;
		if ("goodsPricePatten".equals(name)) return  BaseConstants.PK_GOODS_PRICE_PATTEN ;
		if ("goodsImgPatten".equals(name)) return  BaseConstants.PK_GOODS_IMG_PATTEN ;
		if ("cate1Patten".equals(name)) return  BaseConstants.PK_CATE1_PATTEN ;
		if ("cate2Patten".equals(name)) return  BaseConstants.PK_CATE2_PATTEN ;
		if ("cate3Patten".equals(name)) return  BaseConstants.PK_CATE3_PATTEN ;
		if ("visitUrlPatten".equals(name)) return  BaseConstants.PK_VISIT_URL_PATTEN ;
		if ("visitSitePatten".equals(name)) return  BaseConstants.PK_VISIT_SITE_PATTEN ;
		if ("goodsUrlPatten".equals(name)) return  BaseConstants.PK_GOODS_URL_PATTEN ;
		if ("goodsNoPatten".equals(name)) return  BaseConstants.PK_GOODS_NO_PATTEN ;
		if ("goodsDisc".equals(name)) return  BaseConstants.PK_GOODS_DISC_PATTEN ;
		if ("goodsIsbuyPatten".equals(name)) return  BaseConstants.PK_GOODS_ISBUY_PATTEN ;
		
		return "";
	}

	public static String pattenCode2Name(String code)
	{
		if (BaseConstants.PK_GOODS_NAME_PATTEN.equals(code)) return  "goodsNamePatten" ;
		if (BaseConstants.PK_GOODS_PRICE_PATTEN.equals(code)) return  "goodsPricePatten" ;
		if (BaseConstants.PK_GOODS_IMG_PATTEN.equals(code)) return "goodsImgPatten"  ;
		if (BaseConstants.PK_CATE1_PATTEN.equals(code)) return "cate1Patten"  ;
		if (BaseConstants.PK_CATE2_PATTEN.equals(code)) return "cate2Patten"  ;
		if (BaseConstants.PK_CATE3_PATTEN.equals(code)) return  "cate3Patten" ;
		if (BaseConstants.PK_VISIT_URL_PATTEN.equals(code)) return "visitUrlPatten"  ;
		if (BaseConstants.PK_VISIT_SITE_PATTEN.equals(code)) return "visitSitePatten"  ;
		if (BaseConstants.PK_GOODS_URL_PATTEN.equals(code)) return  "goodsUrlPatten" ;
		if (BaseConstants.PK_GOODS_NO_PATTEN.equals(code)) return   "goodsNoPatten";
		if (BaseConstants.PK_GOODS_DISC_PATTEN .equals(code)) return "goodsDisc"  ;
		if (BaseConstants.PK_GOODS_ISBUY_PATTEN .equals(code)) return "goodsIsbuyPatten"  ;
		return "";
	}

	
	public static String pattenCode2Type(String code)
	{
		if (BaseConstants.PK_GOODS_NAME_PATTEN.equals(code)) return  "02" ;
		if (BaseConstants.PK_GOODS_PRICE_PATTEN.equals(code)) return  "02" ;
		if (BaseConstants.PK_GOODS_IMG_PATTEN.equals(code)) return "02"  ;
		if (BaseConstants.PK_CATE1_PATTEN.equals(code)) return "02"  ;
		if (BaseConstants.PK_CATE2_PATTEN.equals(code)) return "02"  ;
		if (BaseConstants.PK_CATE3_PATTEN.equals(code)) return  "02" ;
		if (BaseConstants.PK_VISIT_URL_PATTEN.equals(code)) return "01"  ;
		if (BaseConstants.PK_VISIT_SITE_PATTEN.equals(code)) return "01"  ;
		if (BaseConstants.PK_GOODS_URL_PATTEN.equals(code)) return  "01" ;
		if (BaseConstants.PK_GOODS_NO_PATTEN.equals(code)) return   "01";
		if (BaseConstants.PK_GOODS_DISC_PATTEN .equals(code)) return "02"  ;
		if (BaseConstants.PK_GOODS_ISBUY_PATTEN .equals(code)) return "02"  ;
		
	
		return "";
	}

	
	public static HashMap<String, ExpPattenBO[]> getPagePatten(HashMap<String,Object> paramMap, CrawlerDataCombBO crawlerDataCombBO) throws Exception
	{
		HashMap<String, ExpPattenBO[]>  pattenMap = new HashMap<String, ExpPattenBO[]> ();
	
		
		Iterator<String> itr = paramMap.keySet().iterator();
		while(itr.hasNext())
	    {
	        String pName = itr.next();
	        String pValue = CmnUtil.nvl((String)paramMap.get(pName));
	        
	      
	        if (!"".equals(PattenUtil.pattenName2Code(pName)))
			  {

	        	String vals[] = pValue.split(BaseConstants.SPLIT_REG);
	    		int size = vals.length;
	    		ExpPattenBO[] expBos = new ExpPattenBO[size];
	    		
	    		
	    		for (int i=0;i<size;i++)
	    		{
	    			expBos[i] = BaseFactory.create(ExpPattenBO.class);
	    			expBos[i].setPattenStr(vals[i]);
	    			expBos[i].setPattenKind(PattenUtil.pattenName2Code(pName));
	    			expBos[i].setPattenType(PattenUtil.pattenCode2Type(expBos[i].getPattenKind()));
	    			expBos[i].setSiteConfigSeq(crawlerDataCombBO.getCrawConfigBO().getSiteConfigSeq());
	    			expBos[i].setUseYn(crawlerDataCombBO.getCrawConfigBO().getUseYn());
	    			expBos[i].setCreateNo(crawlerDataCombBO.getCrawConfigBO().getCreateNo());
	    			expBos[i].setCreateDt(crawlerDataCombBO.getCrawConfigBO().getCreateDt());
	    			expBos[i].setUpdateNo(crawlerDataCombBO.getCrawConfigBO().getCreateNo());
	    			expBos[i].setUpdateDt(crawlerDataCombBO.getCrawConfigBO().getCreateDt());
	    			
	    		}
	    
	    	
	    		if (log.isDebugEnabled())
	    		{
	    			log.debug("pName :" + PattenUtil.pattenName2Code(pName));
	    			for (ExpPattenBO BO : expBos) log.debug("expBo :" + DebugUtil.debugBo(BO));
	    		}
	    		
	    		pattenMap.put(PattenUtil.pattenName2Code(pName), expBos);
	    		
			  }
	    }
		
		
		 /*Enumeration eParam = request.getParameterNames();
		 while (eParam.hasMoreElements()) 
		 {
		  String pName = (String)eParam.nextElement();
		  String pValue = request.getParameter(pName);
	
		  if (!"".equals(PattenUtil.pattenName2Code(pName)))
			  {
		  		pattenMap.put( PattenUtil.pattenName2Code(pName)
		  						,BaseFactory.createExpPattenBO(pValue,BaseConstants.SPLIT_REG,PattenUtil.pattenName2Code(pName)))  ;
	
			  }
		  }*/
		
		
		return pattenMap;
	}
	

	
	
	
}
