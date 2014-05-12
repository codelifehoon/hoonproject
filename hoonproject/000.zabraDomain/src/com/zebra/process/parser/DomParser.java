package com.zebra.process.parser;

import java.util.HashMap;

import com.zebra.process.crawler.domain.PageConfigBO;
import com.zebra.process.crawler.domain.WebPageInfoBO;
import com.zebra.process.parser.domain.ExpPattenBO;

public interface DomParser {

	public String compareWebPageInfo(WebPageInfoBO source, WebPageInfoBO target);

	public WebPageInfoBO doParsing(String htmlString, WebPageInfoBO webPageInfoBO, HashMap<String, ExpPattenBO[]> pattenMap);

}