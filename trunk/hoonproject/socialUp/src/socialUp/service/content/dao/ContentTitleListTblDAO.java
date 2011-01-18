package socialUp.service.content.dao;



import java.util.List;
import org.apache.ibatis.session.SqlSession;

import socialUp.service.content.dto.ContentJoinMemDTO;
import socialUp.service.content.dto.ContentTitleTblDTO;

public interface ContentTitleListTblDAO 
{

	
	public void setSqlSesstion(SqlSession session)  throws Exception ;
	
	/**
	 * 컨텐츠 테이블 정보 입력
	 * @param param
	 * @throws Exception
	 */
	public int insertContentTitleListTbl(ContentTitleTblDTO conTitleParam) throws Exception ;
	
	
	/**
	 * 컨텐츠 컨텐츠타이틀 참여회원등록 
	 * @param param
	 * @throws Exception
	 */
	public int insertContentTitleListTbl(ContentJoinMemDTO contentJoinMemDTO)	throws Exception;
	
	
	/**
	 * 컨텐츠 수정
	 * 
	 * @param param
	 * @throws Exception
	 */
	public int updateContentTitle(ContentTitleTblDTO conTitleParam) throws Exception ;
	
	
	/**
	 * 컨텐츠정보 조회
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentTitleTblDTO> selectContentTitleListTbl(ContentTitleTblDTO conTitleParam) throws Exception ;
	

	/**
	 * 컨텐츠타이틀 참여회원등록 
	 * 
	 * @param contentJoinMem
	 * @return
	 * @throws Exception
	 */
	public int insertContentJoinMem(ContentJoinMemDTO contentJoinMem) throws Exception ;
	
	
	
	/**
	 * 컨텐츠타이틀 참여회원수정
	 * 
	 * @param contentJoinMem
	 * @return
	 * @throws Exception
	 */
	public int updateContentJoinMem(ContentJoinMemDTO contentJoinMem) throws Exception ;

	
	/**
	 * 컨텐츠타이틀 참여회원조회
	 * 
	 * @param contentJoinMem
	 * @return
	 * @throws Exception
	 */
	public List<ContentJoinMemDTO> selectContentJoinMemList(ContentJoinMemDTO contentJoinMem) throws Exception ;
	
	
}