package com.zebra.process.action;

import java.util.HashMap;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TestAction extends BaseAction {

	private static final Logger log =  Logger.getLogger(TestAction.class.getName());
	
	@RequestMapping("/test/testView")
	public ModelAndView testMethod(@RequestParam HashMap<String,Object> paramMap , HttpServletRequest request, HttpServletResponse response )
	{
		log.debug("##### testView 시작");

		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/test/testView");
		mav.addObject("message","msg 입니다.!");
		
		
		return  mav;
	}
}
