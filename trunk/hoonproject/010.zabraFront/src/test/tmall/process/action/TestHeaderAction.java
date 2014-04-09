package test.tmall.process.action;

import java.util.HashMap;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.zebra.process.action.BaseAction;
@Controller
public class TestHeaderAction extends BaseAction {
	private Log log = LogFactory.getLog(TestAction.class);
	
	/**
	 * 
	 * @param paramMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/test/getHeaderTest")
	public String getHeaderTest(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		System.out.println("================ HEADER TEST =============================");
		String frameTemplate = "ft_body";		//TEMPLATE 정보를 셋팅
		String returnJsp = "headerJs";					//return jsp page정보 셋팅
		String returnId = "/"+frameTemplate+"/"+returnJsp;
		return returnId;
	}
}
