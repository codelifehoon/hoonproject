package socialUp.service.content.dao;

import java.util.List;



import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.util.NumUtil;
import socialUp.service.content.dto.ContentTitleListTblDTO;
import socialUp.service.member.dto.MemTblDTO;

public class ContentTitleListTblDAOImpl implements ContentTitleListTblDAO {
	

	public Logger log = Logger.getLogger(this.getClass());
	
	private SqlSession sqlMap ;
	
	public void setSqlSesstion(SqlSession session)  throws Exception 
	{
		
		this.sqlMap = session;
	}

	@Override
	public int insertContentTitleListTbl(ContentTitleListTblDTO conTitleParam)
			throws Exception {
		 
		this.sqlMap.insert("socialUp.service.content.mapper.insertContentTitleListTbl", conTitleParam);
		
		// 회원가입후 가입일련번호를 넘겨준다.
		return NumUtil.toInt(conTitleParam.getTt_no());

	}

	@Override
	public int updateContentTitleListTbl(ContentTitleListTblDTO conTitleParam)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ContentTitleListTblDTO> selectContentTitleListTbl(ContentTitleListTblDTO conTitleParam) throws Exception {

		return  this.sqlMap.selectList("socialUp.service.content.mapper.selectContentTitleListTblList", conTitleParam);
	}

	
	
}