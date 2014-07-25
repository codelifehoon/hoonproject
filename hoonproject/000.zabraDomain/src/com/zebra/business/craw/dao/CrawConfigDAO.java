package com.zebra.business.craw.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.zebra.business.craw.domain.CrawConfigBO;


public interface CrawConfigDAO {

	public List<CrawConfigBO> selectCrawConfigList(CrawConfigBO crawConfigBO);

	/**
	 * @param crawConfigBO
	 * @return
	 */
	public long addCrawConfig(CrawConfigBO crawConfigBO);

	/**
	 * @param crawConfigBO
	 * @return TODO
	 */
	public int updateCrawConfig(CrawConfigBO crawConfigBO);

}