/*
 * Project 421.aspOrderProject
 */
package test.tmall.process.action;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zebra.process.action.BaseAction;

/**  
 *
 * @author CP91984
 * @date 2012. 02. 18.
 *  apiTestPage.jsp 카피 파일
	기본 파라미터 하드코딩으로 실제 페이지만 바로 확인하기 위해 수정하여 생성.
 */
@Controller
public class Dup_LiteOmpAPIAction extends BaseAction {

	private Log log = LogFactory.getLog(this.getClass());
	@Autowired
	private LiteOmpRestClientImpl liteOmpRestClientImpl;
	
	@RequestMapping(value="/sample/LiteOmpAPIAction/Dup_APITest")
	public String APITest(@RequestParam HashMap<String, Object> dataBox, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> motherRequestMap = new HashMap<String, Object>();		
		Map<String, Object> componentResultMap = new HashMap<String, Object>();
		HashMap<String, String> componentParamMap = new HashMap<String, String>();
		HashMap<String, String> tmpCompParamMap = new HashMap<String, String>();
		
		
		// 필수 파라미터 하드 코딩 부분... 
		String key = "ExhibitionDtlList";
		String cpntNo = "244";
		String divId = "css";
		String styleClassName = "css";
		String dispSpceNo1 = "";
		String dispSpceNo2 = "";
		String dispSpceNo3 = "";
		String paramList = "plnDispNo:340740";
		
		System.out.println("key===>> "+ key);
		System.out.println("cpntNo===>> "+ cpntNo);
		
		// 파라메터 셋팅
//		String key = (String)dataBox.get("key");
//		String cpntNo = (String)dataBox.get("cpntNo");
//		String divId = (String)dataBox.get("divId");
//		String styleClassName = (String)dataBox.get("styleClassName");
//		String dispSpceNo1 = (String)dataBox.get("dispSpceNo1");
//		String dispSpceNo2 = (String)dataBox.get("dispSpceNo2");
//		String dispSpceNo3 = (String)dataBox.get("dispSpceNo3");
//		String paramList = (String)dataBox.get("paramList");
		 

		
		// 필수 키 값이 존재 할때만 처리
		if (key != null && cpntNo != null) {
			componentParamMap.put("key", key);
			componentParamMap.put("cpntNo", cpntNo);
			componentParamMap.put("divId", divId);
			componentParamMap.put("styleClassName", styleClassName);
			componentParamMap.put("dispSpceNo1", dispSpceNo1);
			componentParamMap.put("dispSpceNo2", dispSpceNo2);
			componentParamMap.put("dispSpceNo3", dispSpceNo3);
			
			// 컴포넌트 파라메터 추가
			String paramKey = null;
			String paramValue = null;
			String[] paramSet = null;
			if(paramList != null && !paramList.equals("")){
				for (String element : paramList.split(",")) {
					paramSet = element.trim().split(":");
					paramKey = paramSet[0].trim();
					paramValue = paramSet[1].trim();
					tmpCompParamMap.put(paramKey, paramValue);
				}
	
				for (String tmpKey : tmpCompParamMap.keySet()) {
					componentParamMap.put(tmpKey, tmpCompParamMap.get(tmpKey));
				}
			}

			motherRequestMap.put(key, componentParamMap);

			Map<String, Object> apiResponseMap = liteOmpRestClientImpl.requestLiteOmpMultiData(PropertiesManager.getProperty("api.context.url")+ "/rest/liteomp/multi/getLOComponentDatas.do",motherRequestMap);

			setResultMap(motherRequestMap, apiResponseMap, componentResultMap);
		}
		request.setAttribute("result", componentResultMap);		
		return "/test/Dup_apiTestPage";
	}
	
	private void setResultMap(Map<String, Object> motherRequestMap, Map<String, Object> apiResponseMap, Map<String, Object> componentResultMap) throws Exception {
		if(apiResponseMap != null) {
			if(apiResponseMap.containsKey("code") && apiResponseMap.get("code").equals("200")) {
				Map<String, Object> resultMap = (Map<String, Object>) apiResponseMap.get("result");
				String tmpResult = "";
				motherRequestMap.remove("OMP_COMMON_PARAM");
				for (String key : motherRequestMap.keySet()) {
					tmpResult = (String)resultMap.get(key);
					// 값이 없으면 div 태그로 구성해줌
					if(tmpResult == null || tmpResult.equals("null") || tmpResult.equals("")){
						tmpResult = "<div></div>";
					}
					componentResultMap.put(key, tmpResult);
				}
			} else {
				throw new Exception("PageTemplate API CALL ERROR : ");
			}
		}
	}
}
