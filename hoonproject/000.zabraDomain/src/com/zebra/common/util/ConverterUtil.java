package com.zebra.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zebra.process.crawler.domain.WebPageInfoBO;

public class ConverterUtil {

	public static HashMap<String, WebPageInfoBO>  webPageInfoList2Map(String key, List<WebPageInfoBO>  webPageInfoBOlist )
	{

		 HashMap<String, WebPageInfoBO> map = new HashMap<String, WebPageInfoBO>();
		

		for (WebPageInfoBO webPageInfoBO :  webPageInfoBOlist )	
		{			
			map.put(webPageInfoBO.getGoodsNo(), webPageInfoBO);		
		}
		return map;
	}
	
	public static List<WebPageInfoBO>  webPageInfoMap2List(HashMap<String, WebPageInfoBO>   webPageInfoBOMap)
	{
		
		 List<WebPageInfoBO>  webPageInfoBOlist = new ArrayList<WebPageInfoBO>();
		webPageInfoBOlist = new ArrayList<WebPageInfoBO>( webPageInfoBOMap.values());

		return webPageInfoBOlist;
	}
	
}
