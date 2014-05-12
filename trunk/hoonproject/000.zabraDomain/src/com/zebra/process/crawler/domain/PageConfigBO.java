package com.zebra.process.crawler.domain;

import lombok.Data;

import com.zebra.common.domain.BaseBO;
import com.zebra.process.parser.domain.ExpPattenBO;

@Data
public class PageConfigBO extends BaseBO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6257747544308455213L;

	private String htmlString; 

	
}
