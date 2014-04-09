package com.zebra.process.renew;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.zebra.common.CommonConstants;
import com.zebra.common.dao.CommomPattenCodeDao;
import com.zebra.common.util.PattenUtil;
import com.zebra.process.crawler.CommCrawlController;
import com.zebra.process.crawler.domain.CrawConfigBO;
import com.zebra.process.crawler.domain.CrawlerDataCombBO;
import com.zebra.process.crawler.domain.PageConfigBo;
import com.zebra.process.crawler.domain.WebPageInfoBO;
import com.zebra.process.parser.DomParser;
import com.zebra.process.parser.DomParserImpl;
import com.zebra.process.parser.domain.ExpPattenBO;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class PageCrawler extends WebCrawler {


	protected static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(WebCrawler.class.getName());
	@Autowired
	private CommomPattenCodeDao commomPattenCodeDao;
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
    	
    	
            String url = page.getWebURL().getURL().toLowerCase();
            CrawlerDataCombBO crawlerDataCombBO = ((CommCrawlController)getMyController()).getCrawlerDataCombBO();
            CrawConfigBO		crawConfigBO		= crawlerDataCombBO.getCrawConfigBO();
            PageConfigBo pageConfigBo = crawlerDataCombBO.getPageConfigBo();

            logger.error("url:" + url);
            
            if (page.getParseData() instanceof HtmlParseData) 
            {
            	DomParser  domParser = new DomParserImpl();
            	
            	String goodsNo = PattenUtil.pattenString(commomPattenCodeDao.selectPattenCodeArray(crawConfigBO.getSiteConfigSeq() , CommonConstants.PK_GOODS_NO_PATTEN)
            											, url);
            											
            	
            	WebPageInfoBO webPageInfoBONew;
                WebPageInfoBO webPageInfoBOOld = crawlerDataCombBO.getWebPageInfoBOMap().get(goodsNo);
                
                if (webPageInfoBOOld == null)
                {
                	logger.error("visit webpage info is  null:" + goodsNo);
                	return;
                }
                
                HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                pageConfigBo.setHtmlString(htmlParseData.getHtml());
                
                webPageInfoBONew = domParser.doParsing(pageConfigBo, webPageInfoBOOld);
                webPageInfoBONew.setGoodsUrl(webPageInfoBOOld.getGoodsUrl());
                webPageInfoBONew.setGoodsNo(webPageInfoBOOld.getGoodsNo());
        		
                

                if (webPageInfoBONew.getReNewFlag().equals("Y")) 
                {	
                	crawlerDataCombBO.getWebPageInfoBOMap().put(goodsNo,webPageInfoBONew); 
                }
            }
    }
}
