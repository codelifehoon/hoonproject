package com.zebra.process.action.officetool;

import java.util.ArrayList;


import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.NotNull;

import lombok.extern.log4j.Log4j;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.zebra.process.crawler.CrawlerJob;
import com.zebra.process.crawregister.CrawMngService;
import com.zebra.business.craw.dao.CrawConfigDAO;
import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.business.craw.domain.CrawlerDataCombBO;
import com.zebra.business.craw.domain.ExpPattenBO;
import com.zebra.business.craw.domain.PageConfigBO;
import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.BaseFactory;
import com.zebra.common.BaseConstants;
import com.zebra.common.util.CmnUtil;
import com.zebra.common.util.DateTime;
import com.zebra.common.util.DebugUtil;
import com.zebra.common.util.PattenUtil;
import com.zebra.process.action.AuthAction;
import com.zebra.process.action.BaseAction;


import org.json.JSONObject;

@Controller @Log4j
public class SiteMngAction extends AuthAction {

	//private static final  Logger log =  Logger.getLogger(SiteMngAction.class.getName());
	@Autowired private CrawlerJob crawlerJob;
	@Autowired private CrawConfigDAO crawConfigDAO;
	@Autowired private CrawMngService crawMngService;

	
	@RequestMapping("/siteMng/regCrawlInfo")
	public ModelAndView regCrawlInfo(@RequestParam HashMap<String,Object> paramMap , HttpServletRequest request, HttpServletResponse response )
	//public ModelAndView regCrawlInfo(@ModelAttribute CrawConfigBO CrawConfigBO , BindingResult result  )
	{ 
		
		log.debug("##### regCrawlInfo");
		ModelAndView mv = new ModelAndView();
		
		JSONObject a = new JSONObject();
		
		mv.addObject("CrawConfigList",crawConfigDAO.selectCrawConfigList(null));
		mv.setViewName("/sitemng/RegCrawlInfo");
		return  mv;
	}
	
	@RequestMapping("/siteMng/regGoodsInfoPopup")
	public ModelAndView regGoodsInfoPopup(@RequestParam HashMap<String,Object> paramMap , HttpServletRequest request, HttpServletResponse response ) throws Exception
	{
		log.debug("##### regGoodsInfoPopup");
		ModelAndView mv = new ModelAndView();
		Date currentDt = new Date();
		
		
		CrawlerDataCombBO crawlerDataCombBO = BaseFactory.create(CrawlerDataCombBO.class);
		crawlerDataCombBO.setCrawConfigBO((CrawConfigBO)BaseFactory.create(CrawConfigBO.class));
		crawlerDataCombBO.setPageConfigBO((PageConfigBO)BaseFactory.create(PageConfigBO.class));
		
		crawlerDataCombBO.setPreViewYn("Y");
		crawlerDataCombBO.getCrawConfigBO().setSeedStrURL((String)paramMap.get("goodsUrl"));
		crawlerDataCombBO.getCrawConfigBO().setCrawlAgent((String)paramMap.get("crawlAgent"));
		
		crawlerDataCombBO.getCrawConfigBO().setUseYn("Y");
		crawlerDataCombBO.getCrawConfigBO().setCreateNo("999");
		crawlerDataCombBO.getCrawConfigBO().setCreateDt(currentDt);


		
		crawlerDataCombBO.setPattenMap(PattenUtil.getPagePatten(paramMap, crawlerDataCombBO));
		
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
		mv.setViewName("/popupprefix/sitemng/RegGoodsInfoPopup");
		return  mv;
	}
	
	@RequestMapping("/siteMng/ajax/validCrawlSeedURL")
	public ModelAndView  validCrawlSeedURL(@RequestParam HashMap<String,Object> paramMap , HttpServletRequest request, HttpServletResponse response ) throws Exception
	{
		log.debug("##### validCrawlSeedURL");
		log.debug(paramMap);
		
		
		ModelAndView mv = new ModelAndView();
		Date currentDt = new Date();
		List<CrawConfigBO> urlValidList = new ArrayList<CrawConfigBO>();
		
		log.debug("#####" + (String)paramMap.get("cate1Patten"));

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
		
		crawlerDataCombBO.getCrawConfigBO().setUseYn("Y");
		crawlerDataCombBO.getCrawConfigBO().setCreateNo("999");
		crawlerDataCombBO.getCrawConfigBO().setCreateDt(currentDt);
		
		crawlerDataCombBO.setPattenMap(PattenUtil.getPagePatten(paramMap, crawlerDataCombBO));
		
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
	
	
	@RequestMapping("/siteMng/ajax/crawlConfigInfo")
	public ModelAndView crawlConfigInfo(@RequestParam HashMap<String,Object> paramMap , HttpServletRequest request, HttpServletResponse response )
	{
		log.debug("##### CrawlConfigInfo");
		ModelAndView mv = new ModelAndView();
		CrawConfigBO crawConfigBO = BaseFactory.create(CrawConfigBO.class);
		
		crawConfigBO.setSiteConfigSeq((String)paramMap.get("siteConfigSeq"));

		mv.addObject("crawConfigBO",crawConfigDAO.selectCrawConfigList(crawConfigBO));

		return  mv;
	}
	
	@RequestMapping("/siteMng/ajax/regCrawConfig")
	public ModelAndView regCrawConfig(@RequestParam HashMap<String,Object> paramMap , HttpServletRequest request, HttpServletResponse response )
	{
		log.debug("##### regCrawConfig");
		ModelAndView mv = new ModelAndView();
		CrawConfigBO crawConfigBO = BaseFactory.create(CrawConfigBO.class);
		
		crawConfigBO.setSiteConfigSeq((String)paramMap.get("siteConfigSeq"));

		
		
		mv.addObject("crawConfigBO",crawConfigDAO.selectCrawConfigList(crawConfigBO));

		return  mv;
	}
	
	@RequestMapping("/siteMng/addCrawlInfo")
	public ModelAndView addCrawlInfo(@RequestParam HashMap<String,Object> paramMap , HttpServletRequest request, HttpServletResponse response ) throws Exception
	//public ModelAndView regCrawlInfo(@ModelAttribute CrawConfigBO CrawConfigBO , BindingResult result  )
	{ 
		log.debug("##### addCrawlInfo");
		log.debug(paramMap);
		ModelAndView mv = new ModelAndView();
		List<CrawConfigBO> urlValidList = new ArrayList<CrawConfigBO>();
		Date currentDt = new Date();
		
		

		CrawlerDataCombBO crawlerDataCombBO = BaseFactory.create(CrawlerDataCombBO.class);
		crawlerDataCombBO.setCrawConfigBO((CrawConfigBO)BaseFactory.create(CrawConfigBO.class));
		
		crawlerDataCombBO.getCrawConfigBO().setSiteNm((String)paramMap.get("siteNm"));
		crawlerDataCombBO.getCrawConfigBO().setSeedStrURL((String)paramMap.get("seedURL"));
		crawlerDataCombBO.getCrawConfigBO().setUseYn("Y");
		crawlerDataCombBO.getCrawConfigBO().setCrawlAgent((String)paramMap.get("crawlAgent"));
		
		crawlerDataCombBO.getCrawConfigBO().setCreateNo("999");
		crawlerDataCombBO.getCrawConfigBO().setCreateDt(currentDt);

		crawlerDataCombBO.setPattenMap(PattenUtil.getPagePatten(paramMap, crawlerDataCombBO));
		
		
		crawMngService.addCrawInfo(crawlerDataCombBO);
		
		//initPattenMap(paramMap, currentDt, crawlerDataCombBO);
		
		
		RedirectView rv = new RedirectView("/siteMng/regCrawlInfo.do");
		rv.setExposeModelAttributes(false);
		return  new ModelAndView(rv);
	}

	
	@RequestMapping("/siteMng/editCrawConfig")
	public ModelAndView  editCrawConfig(@RequestParam HashMap<String,Object> paramMap , HttpServletRequest request, HttpServletResponse response )
	{  
		
		return null; 
	}
	
	
	
}
