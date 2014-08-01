/**
 * @FileName  : CommonCodeDaoImpl.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 1. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.common.dao;

import org.springframework.stereotype.Repository;

import com.zebra.common.domain.CommonCodeBO;

@Repository
public class CommonCodeDaoImpl extends CommonDAO implements CommonCodeDao {

	/* (non-Javadoc)
	 * @see com.zebra.common.dao.CommonCodeDao#selectCommonCode(com.zebra.common.domain.CommonCodeBO)
	 */
	@Override
	public CommonCodeBO selectCommonCode(CommonCodeBO commonCodeBO) {
		
		return sqlSession.selectOne("query.commonCode.selectCommonCode", commonCodeBO);

	}

}
