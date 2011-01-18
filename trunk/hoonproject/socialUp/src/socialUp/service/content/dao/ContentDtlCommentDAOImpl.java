package socialUp.service.content.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.util.NumUtil;
import socialUp.service.content.dto.ContentDtlCommentDTO;

public class ContentDtlCommentDAOImpl implements ContentDtlCommentDAO {
	

	public Logger log = Logger.getLogger(this.getClass());
	
	private SqlSession sqlMap ;
	
	public void setSqlSesstion(SqlSession session)  throws Exception 
	{
		
		this.sqlMap = session;
	}

	@Override
	public long insertContentDtlComment(ContentDtlCommentDTO ContentDtlCommentParam) throws Exception 
	{
		 
		this.sqlMap.insert("socialUp.service.content.mapper.insertContentDtlComment", ContentDtlCommentParam);
		
		// 등록후 시퀀스를 넘겨준다.
		return NumUtil.toInt(ContentDtlCommentParam.getCdc_no());
	}

	@Override
	public int updateContentDtlComment(ContentDtlCommentDTO ContentDtlCommentParam) throws Exception 
	{
		return  this.sqlMap.update("socialUp.service.content.mapper.updateContentDtlComment", ContentDtlCommentParam);
		
	}

	@Override
	public List<ContentDtlCommentDTO> selectContentDtlComment(ContentDtlCommentDTO ContentDtlCommentParam) throws Exception 
	{

		return  this.sqlMap.selectList("socialUp.service.content.mapper.selectContentDtlCommentList", ContentDtlCommentParam);
	}

}