package com.zebra.process.action.officetool;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.zebra.business.craw.dao.CrawConfigDAO;
import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.business.craw.domain.CrawlerDataCombBO;
import com.zebra.business.craw.domain.PageConfigBO;
import com.zebra.business.craw.domain.WebPageInfoBO;
import com.zebra.common.BaseConstants;
import com.zebra.common.BaseFactory;
import com.zebra.common.util.DateTime;
import com.zebra.common.util.DebugUtil;
import com.zebra.common.util.PattenUtil;
import com.zebra.process.action.AuthAction;
import com.zebra.process.crawler.CrawlerJob;
import com.zebra.process.crawregister.CrawMngService;

@Controller @Log4j
public class SiteMngAction extends AuthAction {

	//private static final  Logger log =  Logger.getLogger(SiteMngAction.class.getName());
	@Autowired private  CrawlerJob crawlerJob;
	@Autowired private  CrawConfigDAO crawConfigDAO;
	@Autowired private  CrawMngService crawMngService;

	
	@RequestMapping("/siteMng/regCrawlInfo")
	public ModelAndView regCrawlInfo(@RequestParam final HashMap<String,Object> paramMap , final HttpServletRequest request ) throws Exception
	//public ModelAndView regCrawlInfo(@ModelAttribute CrawConfigBO CrawConfigBO , BindingResult result  )
	{ 
		log.debug("##### regCrawlInfo");
		
		final List<CrawConfigBO>  crawConfigBOList =  crawConfigDAO.selectCrawConfigList(null);

		final ModelAndView mav = new ModelAndView();
		mav.addObject("CrawConfigList",crawConfigBOList);
		mav.setViewName("/sitemng/regCrawlInfo");
		return  mav;
	}
	
	@RequestMapping("/siteMng/regGoodsInfoPopup")
	public ModelAndView regGoodsInfoPopup(@RequestParam HashMap<String,Object> paramMap , final HttpServletRequest request ) throws Exception
	{
		log.debug("##### regGoodsInfoPopup");

		final Date currentDt = new Date();
		
		
		final CrawlerDataCombBO crawlerDataCombBO = BaseFactory.create(CrawlerDataCombBO.class);
		crawlerDataCombBO.setCrawConfigBO((CrawConfigBO)BaseFactory.create(CrawConfigBO.class));
		crawlerDataCombBO.setPageConfigBO((PageConfigBO)BaseFactory.create(PageConfigBO.class));
		
		crawlerDataCombBO.setPreViewYn("Y");
		crawlerDataCombBO.getCrawConfigBO().setSeedStrURL((String)paramMap.get("goodsUrl"));
		crawlerDataCombBO.getCrawConfigBO().setCrawlAgent((String)paramMap.get("crawlAgent"));
		
		crawlerDataCombBO.getCrawConfigBO().setUseYn("Y");
		crawlerDataCombBO.getCrawConfigBO().setCreateNo("999");
		crawlerDataCombBO.getCrawConfigBO().setCreateDt(currentDt);


		
		crawlerDataCombBO.setPattenMap(PattenUtil.getPagePatten(paramMap, crawlerDataCombBO));
		
		final List<WebPageInfoBO> webPageInfoList = crawlerJob.validCrawlerPrdInfo(crawlerDataCombBO);

		/*
		log.debug("#########################");
		log.debug("INFO PK_VISIT_SITE_PATTEN:" + PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_VISIT_SITE_PATTEN), URL.toLowerCase()) );
		log.debug("INFO PK_VISIT_URL_PATTEN:" + PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_VISIT_URL_PATTEN), URL.toLowerCase()));
		log.debug("INFO PK_GOODS_URL_PATTEN :" + PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_GOODS_URL_PATTEN), URL.toLowerCase()));
		log.debug("INFO PK_GOODS_NO_PATTEN:" + PattenUtil.pattenString(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_GOODS_NO_PATTEN), URL.toLowerCase()));
		*/
		log.debug("INFO:" + DebugUtil.debugBoList(webPageInfoList));
		
		
		final ModelAndView mav = new ModelAndView();
		mav.addObject("webPageInfoList", webPageInfoList);
		mav.setViewName("/popupprefix/sitemng/regGoodsInfoPopup");
		return  mav;
	}
	
	@RequestMapping("/siteMng/ajax/validCrawlSeedURL")
	public ModelAndView  validCrawlSeedURL(@RequestParam HashMap<String,Object> paramMap , final HttpServletRequest request ) throws Exception
	{
		log.debug("##### validCrawlSeedURL");
		log.debug(paramMap);
		
		
		
		final Date currentDt = new Date();
		final List<CrawConfigBO> urlValidList = new ArrayList<CrawConfigBO>();
		
		log.debug("#####" + (String)paramMap.get("cate1Patten"));

		final CrawlerDataCombBO crawlerDataCombBO = BaseFactory.create(CrawlerDataCombBO.class);
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
			
			final CrawConfigBO val = BaseFactory.create(CrawConfigBO.class); 
			val.setSeedStrURL(url);
			//val.setVisitSiteYn(String.valueOf(PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_VISIT_SITE_PATTEN), url)));
			//val.setVisitUrlYn(String.valueOf(PattenUtil.pattenMaches(crawlerDataCombBO.getPattenMap().get(BaseConstants.PK_VISIT_URL_PATTEN), url)));
			
			urlValidList.add(val);
			
		}
		
		
		
		 crawlerJob.validCrawlSeedURLInfo(crawlerDataCombBO);
		log.debug("########## getVisiteTargetBOlist");
		log.debug(DebugUtil.debugBoList(crawlerDataCombBO.getVisiteTargetBOlist()));
		log.debug("########## getWebPageInfoBOlist");
		log.debug(DebugUtil.debugBoList(crawlerDataCombBO.getWebPageInfoBOlist()));
		log.debug("########## urlValidList");
		log.debug(DebugUtil.debugBoList(urlValidList));
			
		
		
	
		final ModelAndView mav = new ModelAndView();
		mav.addObject("visiteTargetList", crawlerDataCombBO.getVisiteTargetBOlist());
		mav.addObject("webPageInfoList", crawlerDataCombBO.getWebPageInfoBOlist());
		mav.addObject("urlValidList",urlValidList);
		
		//mav.setViewName("/SiteMng/RegCrawlInfo");
		return  mav;
	}
	
	
	@RequestMapping("/siteMng/ajax/crawlConfigInfo")
	public ModelAndView crawlConfigInfo(@RequestParam HashMap<String,Object> paramMap , final HttpServletRequest request )
	{
		log.debug("##### CrawlConfigInfo");
		
		final CrawConfigBO crawConfigBO = BaseFactory.create(CrawConfigBO.class);
		
		crawConfigBO.setSiteConfigSeq((String)paramMap.get("siteConfigSeq"));

		final ModelAndView mav = new ModelAndView();
		mav.addObject("crawConfigBO",crawConfigDAO.selectCrawConfigList(crawConfigBO));
		return  mav;
	}
	
	@RequestMapping("/siteMng/ajax/regCrawConfig")
	public ModelAndView regCrawConfig(@RequestParam HashMap<String,Object> paramMap , final HttpServletRequest request )
	{
		log.debug("##### regCrawConfig");
		
		final CrawConfigBO crawConfigBO = BaseFactory.create(CrawConfigBO.class);
		
		crawConfigBO.setSiteConfigSeq((String)paramMap.get("siteConfigSeq"));

		
		final ModelAndView mav = new ModelAndView();
		mav.addObject("crawConfigBO",crawConfigDAO.selectCrawConfigList(crawConfigBO));
		return  mav;
	}
	
	@RequestMapping("/siteMng/addCrawlInfo")
	public ModelAndView addCrawlInfo(@RequestParam HashMap<String,Object> paramMap , final HttpServletRequest request ) throws Exception
	//public ModelAndView regCrawlInfo(@ModelAttribute CrawConfigBO CrawConfigBO , BindingResult result  )
	{ 
		log.debug("##### addCrawlInfo");
		log.debug(paramMap);
		final Date currentDt = new Date();
		final CrawlerDataCombBO crawlerDataCombBO = BaseFactory.create(CrawlerDataCombBO.class);
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
		
		
		RedirectView rdv = new RedirectView("/siteMng/regCrawlInfo.do");
		rdv.setExposeModelAttributes(false);
		return  new ModelAndView(rdv);
	}

	
	@RequestMapping("/siteMng/editCrawlInfo")
	public ModelAndView  editCrawConfig(@RequestParam HashMap<String,Object> paramMap , final HttpServletRequest request ) throws Exception
	{ 
		log.debug("##### editCrawlInfo");
		log.debug(paramMap);
		final Date currentDt = new Date();
		final CrawlerDataCombBO crawlerDataCombBO = BaseFactory.create(CrawlerDataCombBO.class);
		

		crawlerDataCombBO.setCrawConfigBO((CrawConfigBO)BaseFactory.create(CrawConfigBO.class));
		
		crawlerDataCombBO.getCrawConfigBO().setSiteConfigSeq((String)paramMap.get("siteConfigSeq"));
		crawlerDataCombBO.getCrawConfigBO().setSiteNm((String)paramMap.get("siteNm"));
		crawlerDataCombBO.getCrawConfigBO().setSeedStrURL((String)paramMap.get("seedURL"));
		crawlerDataCombBO.getCrawConfigBO().setUseYn("Y");
		crawlerDataCombBO.getCrawConfigBO().setCrawlAgent((String)paramMap.get("crawlAgent"));
		
		crawlerDataCombBO.getCrawConfigBO().setCreateNo("999");
		crawlerDataCombBO.getCrawConfigBO().setCreateDt(currentDt);

		crawlerDataCombBO.setPattenMap(PattenUtil.getPagePatten(paramMap, crawlerDataCombBO));
		

		crawMngService.editCrawInfo(crawlerDataCombBO);
		
		//initPattenMap(paramMap, currentDt, crawlerDataCombBO);
		
		RedirectView rdv = new RedirectView("/siteMng/regCrawlInfo.do");
		rdv.setExposeModelAttributes(false);
		return  new ModelAndView(rdv);
	}
	

	
}
