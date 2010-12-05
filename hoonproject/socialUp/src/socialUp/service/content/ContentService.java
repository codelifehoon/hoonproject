package socialUp.service.content;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import socialUp.service.content.dto.ContentSourceTblDTO;
import socialUp.service.content.dto.ContentTitleTblDTO;

public interface ContentService {




	/**
	 * 회원의 등록된 컨텐츠 소스를 가져온다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentSourceTblDTO> selectContentSourceTbl(ContentSourceTblDTO contentSourceParam) throws Exception;

	
	/**
	 * 회원의 등록된 타이틀목록을  가져온다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentTitleTblDTO> selectContentTitleList(ContentTitleTblDTO contentTitleListParam) throws Exception;
	
	
	/**
	 * 회원의 최초 고리정보를 생성한다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public String  addMemContent(List<ContentSourceTblDTO> contentSourceArr ,ContentTitleTblDTO contentTitleListParam) throws Exception;
	
	
	/**
	 * 회원의 고리정보를 update 한다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public String  updtaeMemContent(List<ContentSourceTblDTO> contentSourceArr ,ContentTitleTblDTO contentTitleListParam) throws Exception;
	
		
}
