package com.zebra.common.dao;

import java.util.List;

import com.zebra.business.craw.domain.ExpPattenBO;

public interface CommonPattenCodeDao {

	public List<ExpPattenBO> selectPattenCodeList(String siteConfigSeq, String pattenKind);

	public ExpPattenBO[] selectPattenCodeArray(String siteConfigSeq , String pattenKind) ;
	
}