package com.zebra.process.crawler.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zebra.process.crawler.domain.CrawlerDataCombBO;
import com.zebra.process.crawler.domain.WebPageInfoBO;



public interface PageInfoDAO {

	public  void insertPageInfo(List<WebPageInfoBO> webPageInfoBOlist);

	public HashMap<String, WebPageInfoBO>  selectReNewPageInfoMap(WebPageInfoBO webPageInfoBO);

	public  void updateReNewPageInfoList(List<WebPageInfoBO> webPageInfoBOList);

	public  HashMap<String, WebPageInfoBO> selectPageInfoMap(WebPageInfoBO webPageInfoBO);

}