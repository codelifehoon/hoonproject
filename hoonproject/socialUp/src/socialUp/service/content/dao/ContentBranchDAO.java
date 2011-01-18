package socialUp.service.content.dao;



import java.util.List;

import org.apache.ibatis.session.SqlSession;

import socialUp.service.content.dto.ContentBranchDTO;


public interface ContentBranchDAO 
{
	


	
	public void setSqlSesstion(SqlSession session)  throws Exception ;
	
	
	/**
	 * 테이블 정보 입력
	 * @param param
	 * @throws Exception
	 */
	public long insertContentBranch(ContentBranchDTO contentBranchParam) throws Exception ;
	
	/**
	 * 정보 수정
	 * 
	 * @param param
	 * @throws Exception
	 */
	public int updateContentBranch(ContentBranchDTO contentBranchParam) throws Exception ;
	
	
	/**
	 * 정보 조회
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentBranchDTO> selectContentBranch(ContentBranchDTO contentBranchParam) throws Exception ;
	

	/**
	 * 브랜치를 생성해서 컨텐츠 타이틀에 등록
	 * 
	 * @param contentBranchParam
	 */
	public  void insertContentNewBranch(ContentBranchDTO contentBranchParam) throws Exception ;
	

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
	public List<ContentBranchDTO> selectContentBranchSelfJoinList(ContentBranchDTO contentBranchParam) throws Exception;

	
	/**
	 * 컨텐츠 타이틀 참여된 브랜치 정보를  삭제한다.
		1. 삭제시에 삭제 대상 컨텐츠타이틀에  삭제를 원하는 나의 컨텐츠타이틀의 branch_no 와 같은 값이 존재하면 삭제한다.
		
	 * @param contentBranchParam
	 * @return
	 * @throws Exception
	 */
	public int deleteContentBranchSelfJoins(ContentBranchDTO contentBranchParam) throws Exception;	

	/**
	 * 여러개의 컨텐츠 타이틀을 브랜치에 참여시킨다. 여러건.
	 * 
	 * @param contentBranchParam
	 */
	public  void insertContentNewBranchs(ContentBranchDTO contentBranchParam) throws Exception;
	
}