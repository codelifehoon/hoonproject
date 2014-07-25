package com.zebra.process.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.zebra.business.craw.domain.ExpPattenBO;
import com.zebra.business.craw.domain.PageConfigBO;
import com.zebra.business.craw.domain.WebPageInfoBO;

public interface DomParser {

	public String compareWebPageInfo(WebPageInfoBO source, WebPageInfoBO target);

	public WebPageInfoBO doParsing(String htmlString, WebPageInfoBO webPageInfoBO, HashMap<String, ExpPattenBO[]> pattenMap) throws Exception ;

}