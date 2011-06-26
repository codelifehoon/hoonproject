package socialUp.service.content.dao;



import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

import socialUp.common.util.ConvertHashMap;
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
	
	
	public void initSql();
	
	
	/**
	 * 컨텐츠타이틀 참여회원조회
	 * 
	 * @param contentJoinMem
	 * @return
	 * @throws Exception
	 */
	public List<ConvertHashMap> selectMyGoreeList(ContentTitleTblDTO conTitleParam) throws Exception ;

	
	
	/**
	 * 컨텐츠타이틀 참여회원조회
	 * 
	 * @param contentJoinMem
	 * @return
	 * @throws Exception
	 */
	public List<ConvertHashMap> selectMyInList(ContentJoinMemDTO contentJoinMem) throws Exception;
	

	/**
	 * 고리 기보정보 관리>> 고리현황(참여고리 정보(고리 등록/수정),고리참여자 정보 ,고리 홍보현황,고리전파현황)
	 * 
	 * @param contentJoinMem
	 * @return
	 * @throws Exception
	 */
	public List<ConvertHashMap> selectGoreeeEtcCnt(ContentTitleTblDTO conTitleParam) throws Exception ;
}