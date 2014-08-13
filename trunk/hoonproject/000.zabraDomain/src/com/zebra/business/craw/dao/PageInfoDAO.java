package com.zebra.business.craw.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zebra.business.analysis.domain.GoodsPriceChangeBO;
import com.zebra.business.craw.domain.CrawlerDataCombBO;
import com.zebra.business.craw.domain.WebPageInfoBO;



public interface PageInfoDAO {

	public  void insertPageInfo(List<WebPageInfoBO> webPageInfoBOlist);

	public HashMap<String, WebPageInfoBO>  selectReNewPageInfoMap(WebPageInfoBO webPageInfoBO);

	public HashMap<String, WebPageInfoBO>  selectBulkPageInfoMap(WebPageInfoBO webPageInfoBO);
	
	public  void updateReNewPageInfoList(List<WebPageInfoBO> webPageInfoBOList);

	public  HashMap<String, WebPageInfoBO> selectPageInfoMap(WebPageInfoBO webPageInfoBO);
	

	
	public  WebPageInfoBO selectPageInfo(WebPageInfoBO webPageInfoBO);
	
	public  void asyncCall(String data);


}