package com.zebra.common.dao;

import java.util.List;

import lombok.extern.log4j.Log4j;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.zebra.common.BaseException;
import com.zebra.common.BaseFactory;
import com.zebra.process.parser.domain.ExpPattenBO;

@Repository @Log4j
public class CommonPattenCodeDaoImpl extends CommonDAO implements CommonPattenCodeDao {
	
		
	/* (non-Javadoc)
	 * @see com.zebra.common.dao.CommomPattenCodeDao#getPattenCode(java.lang.String, java.lang.String)
	 */
	public List<ExpPattenBO> selectPattenCodeList(String siteConfigSeq , String pattenKind) 
	{
		
	
		ExpPattenBO expPattenBO= null;
		List<ExpPattenBO> expPattenList = null;
		try {
		
		expPattenBO = BaseFactory.create(ExpPattenBO.class);
		
		if ("".equals(siteConfigSeq) || siteConfigSeq == null  ) throw new BaseException ("패턴코드 조회오류.");
		expPattenBO.setSiteConfigSeq(siteConfigSeq);
		expPattenBO.setPattenKind(pattenKind);
		
		
		expPattenList =  sqlSession.selectList("query.commonPatten.selectCommonPatten", expPattenBO);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return expPattenList;
	}
	
	/* (non-Javadoc)
	 * @see com.zebra.common.dao.CommomPattenCodeDao#getPattenCode(java.lang.String, java.lang.String)
	 */
	public ExpPattenBO[] selectPattenCodeArray(String siteConfigSeq , String pattenKind) 
	{
		
	
		ExpPattenBO expPattenBO= null;
		List<ExpPattenBO> expPattenList = null;
		try {
		
		expPattenBO = BaseFactory.create(ExpPattenBO.class);
		
		expPattenBO.setSiteConfigSeq(siteConfigSeq);
		expPattenBO.setPattenKind(pattenKind);
		if ("".equals(siteConfigSeq) || siteConfigSeq == null || "".equals(pattenKind) || pattenKind == null ) throw new Exception ("패턴코드 조회오류.");
		
		
		expPattenList =  sqlSession.selectList("query.commonPatten.selectCommonPatten", expPattenBO);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
		if (expPattenList == null) return null;
		
		return (ExpPattenBO[])expPattenList.toArray(new ExpPattenBO [expPattenList.size()]);
	}
	

}
