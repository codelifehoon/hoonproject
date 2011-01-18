package socialUp.service.content.dao;

import java.util.List;



import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.util.NumUtil;
import socialUp.service.content.dto.ContentBranchDTO;
import socialUp.service.content.dto.ContentTitleTblDTO;
import socialUp.service.member.dto.MemTblDTO;

public class ContentBranchDAOImpl implements ContentBranchDAO {
	

	public Logger log = Logger.getLogger(this.getClass());
	
	private SqlSession sqlMap ;
	
	public void setSqlSesstion(SqlSession session)  throws Exception 
	{
		
		this.sqlMap = session;
	}

	@Override
	public long insertContentBranch(ContentBranchDTO contentBranchParam) throws Exception 
	{
		 
		this.sqlMap.insert("socialUp.service.content.mapper.insertContentBranch", contentBranchParam);
		
		// 회원가입후 가입일련번호를 넘겨준다.
		return NumUtil.toInt(contentBranchParam.getTt_no());

	}

	@Override
	public int updateContentBranch(ContentBranchDTO contentBranchParam) throws Exception 
	{
		return  this.sqlMap.update("socialUp.service.content.mapper.updateContentBranch", contentBranchParam);
		
	}

	@Override
	public List<ContentBranchDTO> selectContentBranch(ContentBranchDTO contentBranchParam) throws Exception 
	{

		return  this.sqlMap.selectList("socialUp.service.content.mapper.selectContentBranchList", contentBranchParam);
	}

	
	/**
	 * 브랜치를 생성해서 컨텐츠 타이틀에 등록 1건
	 * 
	 * @param contentBranchParam
	 */
	public  void insertContentNewBranch(ContentBranchDTO contentBranchParam) throws Exception 
	{
		
		this.sqlMap.insert("socialUp.service.content.mapper.insertContentNewBranch", contentBranchParam);
	}
	
	/**
	 * 나의 컨텐츠가 내가 참여를 원하는 퀀텐츠 목록에 브랜치로 등록되어 있는지확인한다.
			 존재가능성 1. 참여를 원하는 컨텐츠가 나의 컨텐츠를 브랜치 따 갔을경우
			          2. 내가 직접 참여 시킨경우
			 등록은 1,2중 하나라도 있을경우 불가능
			 삭제는 2번만 가능.
	 * @param contentBranchParam
	 * @return
	 * @throws Exception
	 */
	public List<ContentBranchDTO> selectContentBranchSelfJoinList(ContentBranchDTO contentBranchParam) throws Exception 
	{

		return  this.sqlMap.selectList("socialUp.service.content.mapper.selectContentBranchSelfJoinList", contentBranchParam);
	}

	/**
	 * 컨텐츠 타이틀 참여된 브랜치 정보를  삭제한다.
		1. 삭제시에 삭제 대상 컨텐츠타이틀에  삭제를 원하는 나의 컨텐츠타이틀의 branch_no 와 같은 값이 존재하면 삭제한다.
		
	 * @param contentBranchParam
	 * @return
	 * @throws Exception
	 */
	public int deleteContentBranchSelfJoins(ContentBranchDTO contentBranchParam) throws Exception 
	{

		return  this.sqlMap.update("socialUp.service.content.mapper.deleteContentBranchSelfJoins", contentBranchParam);
	}
	

	/**
	 * 여러개의 컨텐츠 타이틀을 브랜치에 참여시킨다. 여러건.
	 * 
	 * @param contentBranchParam
	 */
	public  void insertContentNewBranchs(ContentBranchDTO contentBranchParam) throws Exception 
	{
		
		this.sqlMap.insert("socialUp.service.content.mapper.insertContentNewBranchs", contentBranchParam);
	}
	
	
	
}