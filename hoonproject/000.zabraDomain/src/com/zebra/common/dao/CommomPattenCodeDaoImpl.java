package com.zebra.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zebra.common.BaseFactory;
import com.zebra.process.parser.domain.ExpPattenBO;

@Repository
public class CommomPattenCodeDaoImpl extends CommonDAO implements CommomPattenCodeDao {
	
	
	/* (non-Javadoc)
	 * @see com.zebra.common.dao.CommomPattenCodeDao#getPattenCode(java.lang.String, java.lang.String)
	 */
	public List<ExpPattenBO> selectPattenCodeList(String siteConfigSeq , String pattenKind) 
	{
		
	
		ExpPattenBO expPattenBO= null;
		List<ExpPattenBO> expPattenList = null;
		try {
		
		expPattenBO = BaseFactory.create(ExpPattenBO.class);
		
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
		
		
		expPattenList =  sqlSession.selectList("query.commonPatten.selectCommonPatten", expPattenBO);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return (ExpPattenBO[])expPattenList.toArray(new ExpPattenBO [expPattenList.size()]);
	}
	

}
