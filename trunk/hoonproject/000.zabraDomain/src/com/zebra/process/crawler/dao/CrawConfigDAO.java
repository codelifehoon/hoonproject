package com.zebra.process.crawler.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.zebra.process.crawler.domain.CrawConfigBO;


public interface CrawConfigDAO {

	public List<CrawConfigBO> selectCrawConfigList(CrawConfigBO crawConfigBO);

}