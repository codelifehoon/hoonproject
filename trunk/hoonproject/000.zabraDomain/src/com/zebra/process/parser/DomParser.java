package com.zebra.process.parser;

import com.zebra.process.crawler.domain.PageConfigBo;
import com.zebra.process.crawler.domain.WebPageInfoBO;

public interface DomParser {

	public String compareWebPageInfo(WebPageInfoBO source, WebPageInfoBO target);

	public WebPageInfoBO doParsing(PageConfigBo pageConfigBo,
			WebPageInfoBO webPageInfoBO);

}