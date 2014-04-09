package com.zebra.common.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


public class CommonDAO  {

	@Autowired
	protected SqlSessionTemplate sqlSession;
	@Autowired
	protected SqlSessionTemplate sqlSessionBatch;
	
	public CommonDAO()
	{
		// log4j를 이용해 mybtis query mapping log출력을 위해서 설정 
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	public void setSqlSessionBatch(SqlSessionTemplate sqlSessionBatch) {
		this.sqlSessionBatch = sqlSessionBatch;
	}

	

}
