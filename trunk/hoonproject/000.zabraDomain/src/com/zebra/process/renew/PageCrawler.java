package com.zebra.process.renew;

import java.text.SimpleDateFormat;



import java.util.Date;
import java.util.List;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.business.craw.domain.CrawlerDataCombBO;
import com.zebra.business.craw.domain.ExpPattenBO;
import com.zebra.business.craw.domain.PageConfigBO;
import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.BaseConstants;
import com.zebra.common.SpringBeanFactory;
import com.zebra.common.dao.CommonPattenCodeDao;
import com.zebra.common.util.PattenUtil;
import com.zebra.process.crawler.CommCrawlController;
import com.zebra.process.parser.DomParser;
import com.zebra.process.parser.DomParserImpl;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class PageCrawler extends WebCrawler {


	protected  static final  Logger log =  Logger.getLogger(PageCrawler.class.getName());
	
	 private CommonPattenCodeDao commomPattenCodeDao = SpringBeanFactory.getBean(CommonPattenCodeDao.class);
	 private DomParser domParser = SpringBeanFactory.getBean(DomParser.class);
	
    /**
     * You should implement this function to specify whether
     * the given url should be crawled or not (based on your
     * crawling logic).
     */
    @Override
    public boolean shouldVisit(WebURL url) 
    {
    		return false;
	}

    /**
     * This function is called when a page is fetched and ready 
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
    	
    	
    		String ordUrl = page.getWebURL().getURL();
            String url = page.getWebURL().getURL();
            CrawlerDataCombBO crawlerDataCombBO = ((CommCrawlController)getMyController()).getCrawlerDataCombBO();
            CrawConfigBO		crawConfigBO	= crawlerDataCombBO.getCrawConfigBO();
           

            log.debug("url:" + url);
 
            if (page.getParseData() instanceof HtmlParseData) 
            {
            	
 
            	WebPageInfoBO webPageInfoBONew;
                WebPageInfoBO webPageInfoBOOld = crawlerDataCombBO.getWebPageInfoBOMap().get(ordUrl);
                
                if (webPageInfoBOOld == null)
                {
                	log.error("visit webpage info is  null:" + ordUrl);
                	return;
                }
                
                HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                
                webPageInfoBONew = domParser.doParsing( htmlParseData.getHtml(), webPageInfoBOOld,crawlerDataCombBO.getPattenMap() );
                webPageInfoBONew.setGoodsUrl(webPageInfoBOOld.getGoodsUrl());
                webPageInfoBONew.setGoodsNo(webPageInfoBOOld.getGoodsNo());
        		
                crawlerDataCombBO.getWebPageInfoBOMap().put(ordUrl,webPageInfoBONew); 
                
            }
    }
}
