package socialUp.service.common.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import socialUp.common.DaoFactory;
import socialUp.common.mybatis.MyBatisManager;
import socialUp.service.common.dto.CodeDetailDTO;
import socialUp.service.content.dao.ContentSourceTblDAO;
import socialUp.service.content.dao.ContentSourceTblDAOImpl;
import socialUp.service.content.dto.ContentDtlTblDTO;
import socialUp.service.content.dto.ContentSourceTblDTO;



public interface ReadContentDAO
{

	
	/**
	 * 컨텐츠소스 정보를 이용해서 컨텐츠를 읽어온
	 * 
	 * @param contentSourceParam
	 * @throws Exception
	 */
	public List<ContentDtlTblDTO>   readContent(ContentSourceTblDTO contentSourceParam) throws Exception;
		
}