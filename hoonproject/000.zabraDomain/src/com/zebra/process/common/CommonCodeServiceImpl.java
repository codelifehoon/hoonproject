/**
 * @FileName  : CommonCodeServiceImpl.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 1. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.process.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebra.common.BaseFactory;
import com.zebra.common.dao.CommonCodeDao;
import com.zebra.common.dao.CommonDAO;
import com.zebra.common.domain.CommonCodeBO;

@Service
public class CommonCodeServiceImpl implements CommonCodeService {

		@Autowired CommonCodeDao commonCodeDao;
	/* (non-Javadoc)
	 * @see com.zebra.process.common.CommonCodeService#selectCommonCode(java.lang.String, java.lang.String)
	 */
	@Override
	public CommonCodeBO selectCommonCode(String cdMaster, String cdDetail) {
		
		CommonCodeBO commonCodeBO = BaseFactory.create(CommonCodeBO.class);
		commonCodeBO.setCdMaster(cdMaster);
		commonCodeBO.setCdDetail(cdDetail);
		
		return commonCodeDao.selectCommonCode(commonCodeBO);
		
	}

}
