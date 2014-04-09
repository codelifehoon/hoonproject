package com.zebra.process.crawler.domain;

import com.zebra.common.domain.BaseBO;
import com.zebra.process.parser.domain.ExpPattenBO;

public class PageConfigBo extends BaseBO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6257747544308455213L;

	private String HtmlString; 

	public String getHtmlString() {
		return HtmlString;
	}
	public void setHtmlString(String htmlString) {
		HtmlString = htmlString;
	}
	
}
