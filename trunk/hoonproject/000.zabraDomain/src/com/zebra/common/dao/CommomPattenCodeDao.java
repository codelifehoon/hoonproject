package com.zebra.common.dao;

import java.util.List;

import com.zebra.process.parser.domain.ExpPattenBO;

public interface CommomPattenCodeDao {

	public List<ExpPattenBO> selectPattenCodeList(String siteConfigSeq, String pattenKind);

	public ExpPattenBO[] selectPattenCodeArray(String siteConfigSeq , String pattenKind) ;
	
}