package com.zebra.business.craw;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zebra.business.craw.dao.PageInfoDAO;
import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.business.craw.domain.CrawlerDataCombBO;
import com.zebra.business.craw.domain.ExpPattenBO;
import com.zebra.business.craw.domain.VisiteTargetBO;
import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.BaseFactory;
import com.zebra.common.BaseConstants;
import com.zebra.common.SpringBeanFactory;
import com.zebra.common.dao.CommonPattenCodeDao;
import com.zebra.common.util.DateTime;
import com.zebra.common.util.PattenUtil;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class URLCrawler extends WebCrawler {

	protected static final Logger log = Logger.getLogger(URLCrawler.class.getName());

	
	private CommonPattenCodeDao commomPattenCodeDao = SpringBeanFactory.getBean(CommonPattenCodeDao.class);
	
    /**
     * You should implement this function to specify whether
     * the given url should be crawled or not (based on your
     * crawling logic).
     */
    @Override
    public boolean shouldVisit(WebURL url) {
    		

    		boolean visitSiteflag = false;
    		boolean visitURLflag = false;
    		boolean visitGoodsflag = false;
    		String goodsNo = "";
    		
    		
    		CrawlerDataCombBO 	crawlerDataCombBO 	= ((CommCrawlController)getMyController()).getCrawlerDataCombBO();
    		CrawConfigBO		crawConfigBO		= crawlerDataCombBO.getCrawConfigBO();
    		
    	
    		String domain = url.getDomain();
            String href = url.getURL();

            if (crawlerDataCombBO.getPattenMap() != null)
            {
            	visitSiteflag = PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_VISIT_SITE_PATTEN), domain);
				visitURLflag = PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_VISIT_URL_PATTEN), href);
				visitGoodsflag = PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_GOODS_URL_PATTEN), href);
				goodsNo = PattenUtil.pattenString(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_GOODS_NO_PATTEN), href);
            }
            else
            {
	            visitSiteflag = PattenUtil.pattenMaches(commomPattenCodeDao.selectPattenCodeArray(crawConfigBO.getSiteConfigSeq() , BaseConstants.PK_VISIT_SITE_PATTEN), domain);
	            visitURLflag = PattenUtil.pattenMaches(commomPattenCodeDao.selectPattenCodeArray(crawConfigBO.getSiteConfigSeq() , BaseConstants.PK_VISIT_URL_PATTEN), href);
	            visitGoodsflag = PattenUtil.pattenMaches(commomPattenCodeDao.selectPattenCodeArray(crawConfigBO.getSiteConfigSeq() , BaseConstants.PK_GOODS_URL_PATTEN), href);
	            goodsNo = PattenUtil.pattenString(commomPattenCodeDao.selectPattenCodeArray(crawConfigBO.getSiteConfigSeq() , BaseConstants.PK_GOODS_NO_PATTEN), href);
            }
            
            if ( visitSiteflag && visitURLflag && visitGoodsflag && !goodsNo.equals(""))
            	{
            		WebPageInfoBO webPageInfoBO = BaseFactory.create(WebPageInfoBO.class);
            		webPageInfoBO.setSiteConfigSeq(crawConfigBO.getSiteConfigSeq());
            		webPageInfoBO.setGoodsUrl(url.getURL());
            		webPageInfoBO.setGoodsNo(goodsNo);
            		webPageInfoBO.setStatCd(BaseConstants.ST_FIRST);
            		webPageInfoBO.setUpdateNo("999");
            		webPageInfoBO.setUpdateDt(DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
            		webPageInfoBO.setCreateNo("999");
            		webPageInfoBO.setCreateDt(DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
            		
            		((CommCrawlController)getMyController()).addWebPageinfoList(webPageInfoBO);
            		log.debug("##### addWebPageinfoList : " + href );
            	}
            if ( visitSiteflag && visitURLflag && "Y".equals(crawlerDataCombBO.getPreViewYn()))
	            {
            		//지금은 미리보기를 위해서만 사용하는데 차후에는 방문대상 URL도 별도로 저장할 필요는 있을듯 
            		log.debug("#### 방문대상URL:" + url);
            		VisiteTargetBO visiteTargetBO = BaseFactory.create(VisiteTargetBO.class);            		
            		visiteTargetBO.setURL(url.getURL());

            		crawlerDataCombBO.getVisiteTargetBOlist().add(visiteTargetBO);
	            }
           

            return visitSiteflag && visitURLflag;
            
    }

    /**
     * This function is called when a page is fetched and ready 
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
    	
            String url = page.getWebURL().getURL();

            if (page.getParseData() instanceof HtmlParseData) {
                    HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                    String text = htmlParseData.getText();
                    String html = htmlParseData.getHtml();
                    List<WebURL> links = htmlParseData.getOutgoingUrls();
                    /*
                    log.debug("html:" +html);
                   
                    ((URLCrawlController)getMyController()).plusCount();
                    if (((URLCrawlController)getMyController()).getCrawCount() % 100 == 0 )
                    {
	                    SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	                    System.out.println( "Time: " + simpleDateFormat.format(new Date(System.currentTimeMillis())));
	                    System.out.println("Text length: " + text.length());
	                    System.out.println("Html length: " + html.length());
	                    System.out.println("Number of outgoing links: " + links.size());
                    }  */
            }
  }


}
