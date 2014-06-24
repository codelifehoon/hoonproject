package com.zebra.process.action.officeTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zebra.common.BaseFactory;
import com.zebra.common.BaseConstants;
import com.zebra.common.util.DateTime;
import com.zebra.common.util.DebugUtil;
import com.zebra.common.util.PattenUtil;
import com.zebra.process.action.AuthAction;
import com.zebra.process.action.BaseAction;
import com.zebra.process.crawler.CrawlerJob;
import com.zebra.process.crawler.domain.CrawConfigBO;
import com.zebra.process.crawler.domain.CrawlerDataCombBO;
import com.zebra.process.crawler.domain.PageConfigBO;
import com.zebra.process.crawler.domain.WebPageInfoBO;
import com.zebra.process.parser.domain.ExpPattenBO;

@Controller @Log4j
public class SiteMngAction extends AuthAction {

	//private static final  Logger log =  Logger.getLogger(SiteMngAction.class.getName());
	@Autowired private CrawlerJob crawlerJob;

	
	@RequestMapping("/SiteMng/RegCrawlInfo")
	public ModelAndView RegCrawlInfo(@RequestParam HashMap<String,Object> paramMap , HttpServletRequest request, HttpServletResponse response )
	{
		log.debug("##### RegCrawlInfo");
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/SiteMng/RegCrawlInfo");
		return  mv;
	}
	
	@RequestMapping("/SiteMng/RegGoodsInfoPopup")
	public ModelAndView RegGoodsInfoPopup(@RequestParam HashMap<String,Object> paramMap , HttpServletRequest request, HttpServletResponse response )
	{
		log.debug("##### RegGoodsInfoPopup");
		ModelAndView mv = new ModelAndView();
		
		
		CrawlerDataCombBO crawlerDataCombBO = BaseFactory.create(CrawlerDataCombBO.class);
		crawlerDataCombBO.setCrawConfigBO((CrawConfigBO)BaseFactory.create(CrawConfigBO.class));
		crawlerDataCombBO.setPageConfigBO((PageConfigBO)BaseFactory.create(PageConfigBO.class));
		crawlerDataCombBO.setPreViewYn("Y");
		crawlerDataCombBO.setPattenMap(getPagePatten(paramMap ,request, response));
		crawlerDataCombBO.getCrawConfigBO().setSeedStrURL((String)paramMap.get("goodsUrl"));
		crawlerDataCombBO.getCrawConfigBO().setCrawlAgent((String)paramMap.get("crawlAgent"));
		
		List<WebPageInfoBO> webPageInfoList = crawlerJob.validCrawlerPrdInfo(crawlerDataCombBO);

		/*
		log.debug("#########################");
		log.debug("INFO PK_VISIT_SITE_PATTEN:" + PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_VISIT_SITE_PATTEN), URL.toLowerCase()) );
		log.debug("INFO PK_VISIT_URL_PATTEN:" + PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_VISIT_URL_PATTEN), URL.toLowerCase()));
		log.debug("INFO PK_GOODS_URL_PATTEN :" + PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_GOODS_URL_PATTEN), URL.toLowerCase()));
		log.debug("INFO PK_GOODS_NO_PATTEN:" + PattenUtil.pattenString(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_GOODS_NO_PATTEN), URL.toLowerCase()));
		*/
		log.debug("INFO:" + DebugUtil.debugBoList(webPageInfoList));
		
		
		
		mv.addObject("webPageInfoList", webPageInfoList);
		mv.setViewName("/PopupPrefix/SiteMng/RegGoodsInfoPopup");
		return  mv;
	}
	
	@RequestMapping("/SiteMng/ajax/validCrawlSeedURL")
	public ModelAndView  validCrawlSeedURL(@RequestParam HashMap<String,Object> paramMap , HttpServletRequest request, HttpServletResponse response )
	{
		log.debug("##### validCrawlSeedURL");
		log.debug(paramMap);
		ModelAndView mv = new ModelAndView();
		List<CrawConfigBO> urlValidList = new ArrayList<CrawConfigBO>();
		

		CrawlerDataCombBO crawlerDataCombBO = BaseFactory.create(CrawlerDataCombBO.class);
		crawlerDataCombBO.setCrawConfigBO((CrawConfigBO)BaseFactory.create(CrawConfigBO.class));
		crawlerDataCombBO.setPageConfigBO((PageConfigBO)BaseFactory.create(PageConfigBO.class));
		
		
		
		crawlerDataCombBO.setPreViewYn("Y");
		crawlerDataCombBO.getCrawConfigBO().setCrawlThreadCount(1);
		// crawler 에서 사용하는 수집관리를 위해서 사용하는데  was에서 1회 실행 후 동일 이름으로 재 싱행시 기존의 싷행결과이 이어서 실행이 되기 때문에
		// 같은 실행결과가 나오지 않는다. 그래서 항상 새로운 이름으로 요청해서 새로운 겨과를 받도록 하는데
		// 차후 미리보기에 의해서 생성 할 경우  기존 db생성을 무시하도록 코드 수정 필요하다 	
		crawlerDataCombBO.getCrawConfigBO().setSiteNm("valid_" + request.getSession().getId() + DateTime.getFormatString("yyyyMMddHHmmss"));
		//crawlerDataCombBO.getCrawConfigBO().setSiteNm("valid");
		crawlerDataCombBO.getCrawConfigBO().setSeedStrURL((String)paramMap.get("seedURL"));
		crawlerDataCombBO.getCrawConfigBO().setCrawlDepth(1);
		crawlerDataCombBO.getCrawConfigBO().setCrawlAgent((String)paramMap.get("crawlAgent"));
		crawlerDataCombBO.setPattenMap(getPagePatten(paramMap ,request, response));
		
		for (String url : crawlerDataCombBO.getCrawConfigBO().getSeedURL())
		{
			log.debug("##### url:" + url);
			log.debug("PK_VISIT_SITE_PATTEN:" + crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_VISIT_SITE_PATTEN)[0] );
			log.debug("PK_VISIT_URL_PATTEN:" + crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_VISIT_URL_PATTEN)[0] );
			
			CrawConfigBO val = BaseFactory.create(CrawConfigBO.class); 
			val.setSeedStrURL(url);
			val.setVisitSiteYn(String.valueOf(PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_VISIT_SITE_PATTEN), url)));
			val.setVisitUrlYn(String.valueOf(PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_VISIT_URL_PATTEN), url)));
			
			urlValidList.add(val);
			
		}
		
		try {
			 crawlerJob.validCrawlSeedURLInfo(crawlerDataCombBO);
			log.debug("########## getVisiteTargetBOlist");
			log.debug(DebugUtil.debugBoList(crawlerDataCombBO.getVisiteTargetBOlist()));
			log.debug("########## getWebPageInfoBOlist");
			log.debug(DebugUtil.debugBoList(crawlerDataCombBO.getWebPageInfoBOlist()));
			log.debug("########## urlValidList");
			log.debug(DebugUtil.debugBoList(urlValidList));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		mv.addObject("visiteTargetList", crawlerDataCombBO.getVisiteTargetBOlist());
		mv.addObject("webPageInfoList", crawlerDataCombBO.getWebPageInfoBOlist());
		mv.addObject("urlValidList",urlValidList);
		
		//mav.setViewName("/SiteMng/RegCrawlInfo");
		return  mv;
	}
	
	
	
	private HashMap<String, ExpPattenBO[]> getPagePatten(HashMap<String,Object> paramMap , HttpServletRequest request, HttpServletResponse response)
	{
		HashMap<String, ExpPattenBO[]>  pattenMap = new HashMap<String, ExpPattenBO[]> ();


		
		pattenMap.put( BaseConstants.PK_GOODS_NAME_PATTEN 
				,BaseFactory.createExpPattenBO((String)paramMap.get("goodsNamePatten")
				,BaseConstants.SPLIT_REG,BaseConstants.PK_GOODS_NAME_PATTEN) );
		
		pattenMap.put( BaseConstants.PK_GOODS_PRICE_PATTEN 
				,BaseFactory.createExpPattenBO((String)paramMap.get("goodsPricePatten")
				,BaseConstants.SPLIT_REG,BaseConstants.PK_GOODS_PRICE_PATTEN) );
		
		pattenMap.put( BaseConstants.PK_GOODS_IMG_PATTEN 
				,BaseFactory.createExpPattenBO((String)paramMap.get("goodsImgPatten")
				,BaseConstants.SPLIT_REG,BaseConstants.PK_GOODS_IMG_PATTEN) );
		
		pattenMap.put( BaseConstants.PK_CATE1_PATTEN 
				,BaseFactory.createExpPattenBO((String)paramMap.get("cate1Patten")
				,BaseConstants.SPLIT_REG,BaseConstants.PK_CATE1_PATTEN) );
		
		pattenMap.put( BaseConstants.PK_CATE2_PATTEN 
				,BaseFactory.createExpPattenBO((String)paramMap.get("cate2Patten")
				,BaseConstants.SPLIT_REG,BaseConstants.PK_CATE2_PATTEN) );
		
		pattenMap.put( BaseConstants.PK_CATE3_PATTEN 
				,BaseFactory.createExpPattenBO((String)paramMap.get("cate3Patten")
				,BaseConstants.SPLIT_REG,BaseConstants.PK_CATE3_PATTEN) );
		
		pattenMap.put( BaseConstants.PK_VISIT_URL_PATTEN 
				,BaseFactory.createExpPattenBO((String)paramMap.get("visitUrlPatten")
				,BaseConstants.SPLIT_REG,BaseConstants.PK_VISIT_URL_PATTEN) );
		
		pattenMap.put( BaseConstants.PK_VISIT_URL_PATTEN 
				,BaseFactory.createExpPattenBO((String)paramMap.get("visitSitePatten")
				,BaseConstants.SPLIT_REG,BaseConstants.PK_VISIT_URL_PATTEN) );
		
		pattenMap.put( BaseConstants.PK_VISIT_SITE_PATTEN 
				,BaseFactory.createExpPattenBO((String)paramMap.get("visitSitePatten")
				,BaseConstants.SPLIT_REG,BaseConstants.PK_VISIT_SITE_PATTEN) );
		
		pattenMap.put( BaseConstants.PK_GOODS_URL_PATTEN 
				,BaseFactory.createExpPattenBO((String)paramMap.get("goodsUrlPatten")
				,BaseConstants.SPLIT_REG,BaseConstants.PK_GOODS_URL_PATTEN) );
		
		pattenMap.put( BaseConstants.PK_GOODS_NO_PATTEN 
				,BaseFactory.createExpPattenBO((String)paramMap.get("goodsNoPatten")
				,BaseConstants.SPLIT_REG,BaseConstants.PK_GOODS_NO_PATTEN) );
		
		pattenMap.put( BaseConstants.PK_GOODS_DISC_PATTEN 
				,BaseFactory.createExpPattenBO((String)paramMap.get("goodsDisc")
				,BaseConstants.SPLIT_REG,BaseConstants.PK_GOODS_DISC_PATTEN) );
		
		
		
		
		return pattenMap;
	}
	
}
