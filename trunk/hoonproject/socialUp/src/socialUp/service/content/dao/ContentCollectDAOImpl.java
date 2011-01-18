package socialUp.service.content.dao;

import java.util.List;



import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.util.NumUtil;
import socialUp.service.content.dto.ContentCollectDTO;
import socialUp.service.content.dto.ContentSourceTblDTO;
import socialUp.service.member.dto.MemTblDTO;

public class ContentCollectDAOImpl implements ContentCollectDAO {
	

	public Logger log = Logger.getLogger(this.getClass());
	
	private SqlSession sqlMap ;
	
	public void setSqlSesstion(SqlSession session)  throws Exception 
	{
		
		this.sqlMap = session;
	}

	@Override
	public long insertContentCollect(ContentCollectDTO ContentCollectParam) throws Exception
	{
		 
		this.sqlMap.insert("socialUp.service.content.mapper.insertContentCollectListTbl", ContentCollectParam);
		
		// 회원가입후 가입일련번호를 넘겨준다.
		return NumUtil.toInt(ContentCollectParam.getColl_no());

	}

	@Override
	public int updateContentCollect(ContentCollectDTO ContentCollectParam)	throws Exception 
	{
		return  this.sqlMap.update("socialUp.service.content.mapper.updateContentCollect", ContentCollectParam);
		
	}

	@Override
	public List<ContentCollectDTO> selectContentCollect(ContentCollectDTO ContentCollectParam) throws Exception  
	{

		return  this.sqlMap.selectList("socialUp.service.content.mapper.selectContentCollectList", ContentCollectParam);
	}


	/**
	 * 수집대상이 되는 컨텐츠의 리스트
	 * 
	 * @param contentSource 컨텐츠 소스정보
	 * @return
	 * @throws Exception
	 */
	public List<ContentSourceTblDTO>  selectContentCollectWaitList() throws Exception
	{
		return  this.sqlMap.selectList("socialUp.service.content.mapper.selectContentCollectWaitList");
	}
	
	
	
	
}