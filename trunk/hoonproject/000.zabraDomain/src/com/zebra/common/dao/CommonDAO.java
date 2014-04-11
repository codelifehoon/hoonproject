package com.zebra.common.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDAO  {

	@Autowired @Qualifier("sqlSession")
	protected SqlSessionTemplate sqlSession;
	
	@Autowired @Qualifier("sqlSessionBatch")
	protected SqlSessionTemplate sqlSessionBatch;
	
	public CommonDAO()
	{
		// log4j를 이용해 mybtis query mapping log출력을 위해서 설정 
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}


}
