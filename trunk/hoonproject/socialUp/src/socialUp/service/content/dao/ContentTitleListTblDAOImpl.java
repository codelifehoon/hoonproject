package socialUp.service.content.dao;

import java.util.List;



import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.util.NumUtil;
import socialUp.service.content.dto.ContentJoinMemDTO;
import socialUp.service.content.dto.ContentTitleTblDTO;
import socialUp.service.member.dto.MemTblDTO;

public class ContentTitleListTblDAOImpl implements ContentTitleListTblDAO {
	

	public Logger log = Logger.getLogger(this.getClass());
	
	private SqlSession sqlMap ;
	
	public void setSqlSesstion(SqlSession session)  throws Exception 
	{
		
		this.sqlMap = session;
	}

	@Override
	public int insertContentTitleListTbl(ContentTitleTblDTO conTitleParam)
			throws Exception {
		 
		this.sqlMap.insert("socialUp.service.content.mapper.insertContentTitleListTbl", conTitleParam);
		
		// 회원가입후 가입일련번호를 넘겨준다.
		return NumUtil.toInt(conTitleParam.getTt_no());

	}
	
	@Override
	public int insertContentTitleListTbl(ContentJoinMemDTO contentJoinMemDTO)
			throws Exception {
		 
		return this.sqlMap.insert("socialUp.service.content.mapper.insertContentJoinMem", contentJoinMemDTO);
		
	}
	
	
	
	@Override
	public int updateContentTitle(ContentTitleTblDTO conTitleParam)
			throws Exception {
		return  this.sqlMap.update("socialUp.service.content.mapper.updateContentTitle", conTitleParam);
		
	}

	@Override
	public List<ContentTitleTblDTO> selectContentTitleListTbl(ContentTitleTblDTO conTitleParam) throws Exception {

		return  this.sqlMap.selectList("socialUp.service.content.mapper.selectContentTitleListTblList", conTitleParam);
	}

	
	/**
	 * 컨텐츠타이틀 참여회원등록 
	 * 
	 * @param contentJoinMem
	 * @return
	 * @throws Exception
	 */
	public int insertContentJoinMem(ContentJoinMemDTO contentJoinMem) throws Exception {
		 
		return this.sqlMap.insert("socialUp.service.content.mapper.insertContentJoinMem", contentJoinMem);
		
	}
	
	
	
	/**
	 * 컨텐츠타이틀 참여회원수정
	 * 
	 * @param contentJoinMem
	 * @return
	 * @throws Exception
	 */
	public int updateContentJoinMem(ContentJoinMemDTO contentJoinMem) throws Exception {
		return  this.sqlMap.update("socialUp.service.content.mapper.updateContentJoinMem", contentJoinMem);
		
	}

	
	/**
	 * 컨텐츠타이틀 참여회원조회
	 * 
	 * @param contentJoinMem
	 * @return
	 * @throws Exception
	 */
	public List<ContentJoinMemDTO> selectContentJoinMemList(ContentJoinMemDTO contentJoinMem) throws Exception {

		return  this.sqlMap.selectList("socialUp.service.content.mapper.selectContentJoinMemList", contentJoinMem);
	}
	
	
	
}