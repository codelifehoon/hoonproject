package test.tmall.process.action;

import java.util.HashMap;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zebra.process.action.BaseAction;

@Controller
public class TestAction extends BaseAction{
	 
	private Log log = LogFactory.getLog(TestAction.class);
	

	/**
	 * ACTION 테스트 
	 * @param paramMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/test/getTest")
	public String getTest(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		request.setAttribute("message", "[tiles : 변수 binding test]");
		return "/test/test";
	}
	
	/**
	 * DIV API 테스트 페이지 
	 * @param paramMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/test/getDivComponentTestList")
	public String getDivComponentTestList(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		
		
		
		
		
		return "/test/divComponentTestList";
	}	
	
	/**
	 * ModelAndView Action 테스트
	 * @param paramMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/test/getModelAndView")
	public ModelAndView getModelAndView(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		return new ModelAndView("/test/test", "message", "[tiles : 변수 binding test]");
	}
	
	/**
	 * Template Tiles Action 테스트
	 * @param paramMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/test/testTemplateStd")
	public ModelAndView testPage(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("/template/std1");
		mnv.addObject("innerHtml", "[템플릿 tiles : 변수 binding test]");
		return mnv;
	}
	
	/**
	 * FreeMarker 샘플 Action 테스트
	 * @param paramMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/freemarker/integrateTiles")
	public ModelAndView testApiServer(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("templateId", "TEST_TEMP");
		
		Map<String, Object> apiResult = null;
		
		LinkedHashMap result = (LinkedHashMap) apiResult.get("result");
		String templateSource = (String)result.get("templateSource");
    	
		log.debug("api rest response : " +templateSource);
		
		
		return new ModelAndView("/template/std1", "innerHtml", templateSource);
	}
	

		
	/**
	 * omp_site_info 정보 전체 가져오는 기능
	 * @param paramMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getOmpSiteInfoList")
	public ModelAndView getOmpSiteInfoList(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		
		HashMap<String, String> param = new HashMap<String, String>();

		Map<String, Object> apiResult = null;
		
		log.debug("api rest response : " + apiResult.get("result"));
		
		return new ModelAndView("/template/std1", "innerHtml", apiResult.get("result"));
	}
	
	/**
	 * omp_site_info 정보 가져오는 기능
	 * @param paramMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getGetOmpSiteInfo")
	public ModelAndView getGetOmpSiteInfo(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response ) throws Exception {
	
		HashMap<String, String> param = new HashMap<String, String>();
		LiteOmpSiteInfoBO ompSiteBO = null;
		param.put("OMP_CONSUMER_KEY", ompSiteBO.getOmpConsumerKey());
		
		Map<String, Object> apiResult = ompRestClient.requestData(PropertiesManager.getProperty("api.context.url")+"/rest/liteomp/conf/ompsiteinfo/getOmpSiteInfo.json", param);
		log.debug("api rest response : " + apiResult.get("result"));
		
		return new ModelAndView("/template/std1", "innerHtml", apiResult.get("result"));
	}
		/**
	 * FreeMarker Template 가져오는 기능
	 * @param paramMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/freemarker/getTemplate")
	public ModelAndView getTemplate(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("templateId", "TWO_DEPTH_JS_TEMP");
		
		Map<String, Object> apiResult = ompRestClient.requestData(PropertiesManager.getProperty("api.context.url")+"/rest/liteomp/conf/template/getTemplate.json", param);
		
		LinkedHashMap result = (LinkedHashMap) apiResult.get("result");
		String templateSource = (String)result.get("templateSource");
    	
		log.debug("api rest response : " +templateSource); 
		
		
		return new ModelAndView("/template/std1", "innerHtml", templateSource);
	}
	
	/**
	 * FreeMarker Template reload 기능
	 * @param paramMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/freemarker/reloadTemplate")
	public ModelAndView reloadTemplate(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		
		HashMap<String, String> param = new HashMap<String, String>();
		
		Map<String, Object> apiResult = ompRestClient.requestData(PropertiesManager.getProperty("api.context.url")+"/rest/liteomp/conf/template/reloadTemplate.json", param);
		
		LinkedHashMap result = (LinkedHashMap) apiResult.get("result");
		String returnCd = (String)result.get("returnCd");
    	
		log.debug("api rest response : " +returnCd); 
		
		
		return new ModelAndView("/template/std1", "innerHtml", returnCd);
	}
	
	
	@Autowired
	private OmpRestClient ompRestClientImpl;
	
	/**
	 * 기능명 : getPage Action
	 * 메소드명 : getComponent 
	 * 작성자 : ghkim 
	 * 작성일 : 2011. 11. 26. 
	 * 파라메터설명 :
	 * 리턴항목 :
	 */
	@RequestMapping(value = "/test/getBillboardBnrTest")
	public ModelAndView getBillboardBnrTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LiteOmpSiteInfoBO liteOmpSiteInfoBO = LiteOmpSiteInfoUtil.getLiteOmpSiteInfoDomain(request.getServerName());
		LoSiteInfoBO loSiteInfoBO = (LoSiteInfoBO)liteOmpSiteInfoBO.getLoSiteInfoMap().get(request.getServerName());
		
		Map<String, Object> motherRequestMap = new HashMap<String, Object>();		//통합요청 request Param MAP
		Map<String, Object> componentResultMap = new HashMap<String, Object>();		//통합 요청 결과에 대한 DIV값이 HashMap 형태로 담긴다.
		
		String moduleCpntNo = request.getParameter("moduleCpntNo"); 
		String divId = request.getParameter("divId"); 
		
		// API에 요청할 component list 정보 구성 START//
		HashMap<String, String> componentParamMap = new HashMap<String, String>();
		componentParamMap.put("divId", divId);
		//실제 multi 호출시에는 아래 parameter로 호출하게 된다
		//componentParamMap.put("divId", "P"+pageTypeCd+"_S"+sectionNo+"_M"+dispPrrtRnk+"_C"+compntDispPrrtRnk);
		//motherRequestMap.put(moduleCpntNo, componentParamMap);
		
		//component TEST를 위한 변수 세팅
		motherRequestMap.put(moduleCpntNo, componentParamMap);
		// API에 요청할 component list 정보 구성 END//	
		
		// API에 요청 START//
		Map<String, Object> apiResponseMap = liteOmpRestClientImpl.requestLiteOmpMultiData(PropertiesManager.getProperty("api.context.url")+"/rest/liteomp/multi/getPageComponentDatas.do",motherRequestMap);
		if(apiResponseMap != null) {
			if(apiResponseMap.containsKey("code") && apiResponseMap.get("code").equals("200")) {
				Map<String, Object> resultMap = (Map<String, Object>) apiResponseMap.get("result");
				String tmpResult = "";
				for (String key : motherRequestMap.keySet()) {
					tmpResult = (String)resultMap.get(key);
//					if(tmpResult == null) tmpResult = defaultValues.get(key);
					componentResultMap.put(key, tmpResult);
					System.out.println("key == ["+key+"] tmpResult == ["+tmpResult+"]");
				}
			} else {
				throw new Exception("PageTemplate API CALL ERROR : ");
			}
		}
		componentResultMap.put("moduleCpntNo", moduleCpntNo);
		
		System.out.println("componentResultMap == ["+componentResultMap+"]");
		
		// API에 요청 END//
		

		return new ModelAndView("/test/componentApiResult","componentResultMap",componentResultMap);
	}
	
	/**
	 * 기능명 : getPage Action
	 * 메소드명 : getComponent 
	 * 작성자 : ghkim 
	 * 작성일 : 2011. 11. 26. 
	 * 파라메터설명 :
	 * 리턴항목 :
	 */
	@RequestMapping(value = "/test/getRollPrdTest")
	public ModelAndView getRollPrdTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> motherRequestMap = new HashMap<String, Object>();		//통합요청 request Param MAP		
		Map<String, String> defaultValues = new HashMap<String, String>();			//return 데이터 없을경우 default 화면 구성 map
		/** parameter setting START**/
		HashMap<String, String> componentParamMap = new HashMap<String, String>();
		componentParamMap.put("dispSpceId", request.getParameter("dispSpceId"));
		componentParamMap.put("divId", request.getParameter("divId"));
		componentParamMap.put("divClass", request.getParameter("divClass"));
		componentParamMap.put("OMP_API_URL", request.getParameter("apiURL"));
		defaultValues.put(request.getParameter("divId"),"<div class=\""+componentParamMap.get("divClass")+"\"></div>");
		/** parameter setting END**/
		motherRequestMap.put(request.getParameter("divId"),componentParamMap);

		Map<String, Object> apiResponseMap = ompRestClientImpl.requestDivMultiData(PropertiesManager.getProperty("api.context.url") + "/rest/div/multi/getMultiDatas.do", motherRequestMap);
		if(apiResponseMap != null) {
			if(apiResponseMap.containsKey("code") && apiResponseMap.get("code").equals("200")) {
				Map<String, Object> resultMap = (Map<String, Object>) apiResponseMap.get("result");
				String tmpResult = "";
				for (String key : motherRequestMap.keySet()) {
					tmpResult = (String)resultMap.get(key);
					if(tmpResult == null) tmpResult = defaultValues.get(key);
					request.setAttribute(key, tmpResult);
				}
			} else {
				throw new Exception("getRollPrdTest Action ERROR : ");
			}
		}		
		
		//ACTION TEST 변수
		request.setAttribute("testAPIKey", request.getParameter("divId"));

		return new ModelAndView("/test/componentApiResult");
	}	
	
	
	/**
	 * IntroPageTest Page..
	 * @param paramMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/test/getIntroPageTest")
	public String getIntroPageTest(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		
		
		return "/test/introPageTest";
	}	
	/**
	 * IntroPageTest Page..
	 * @param paramMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
}
