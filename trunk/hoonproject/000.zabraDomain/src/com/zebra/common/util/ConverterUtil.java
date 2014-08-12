package com.zebra.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.zebra.business.craw.domain.WebPageInfoBO;

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

	/**
	 * @param <T>
	 * @param webPageInfoBOMap
	 * @return
	 */
	public static String getMaxMapKey(
			HashMap<String, WebPageInfoBO> map) {
		

		Entry<String,WebPageInfoBO> maxEntry = null;

		for(Entry<String, WebPageInfoBO> entry : map.entrySet()) {
		    if (maxEntry == null || NumUtil.toLong(entry.getValue().getPageInfoListSeq()) > NumUtil.toLong(maxEntry.getValue().getPageInfoListSeq())) 
		    {
		        maxEntry = entry;
		    }
		}
		
		if (maxEntry == null)
		{
			return "0";
		}
		
		
		return maxEntry.getValue().getPageInfoListSeq();
	}

	
}
