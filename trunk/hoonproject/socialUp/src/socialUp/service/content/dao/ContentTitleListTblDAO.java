package socialUp.service.content.dao;



import java.util.List;
import org.apache.ibatis.session.SqlSession;
import socialUp.service.content.dto.ContentTitleListTblDTO;

public interface ContentTitleListTblDAO 
{

	
	public void setSqlSesstion(SqlSession session)  throws Exception ;
	
	/**
	 * 회원 테이블 정보 입력
	 * @param param
	 * @throws Exception
	 */
	public int insertContentTitleListTbl(ContentTitleListTblDTO conTitleParam) throws Exception ;
	
	/**
	 * 회원정보 수정
	 * 
	 * @param param
	 * @throws Exception
	 */
	public int updateContentTitleListTbl(ContentTitleListTblDTO conTitleParam) throws Exception ;
	
	
	/**
	 * 회원정보 조회
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentTitleListTblDTO> selectContentTitleListTbl(ContentTitleListTblDTO conTitleParam) throws Exception ;
	


	
	
}