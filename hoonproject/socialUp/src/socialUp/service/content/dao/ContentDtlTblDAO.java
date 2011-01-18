package socialUp.service.content.dao;



import java.sql.SQLException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import socialUp.service.content.dto.ContentDtlTblDTO;
import socialUp.service.content.dto.ContentSourceTblDTO;
import socialUp.service.content.dto.SearchDTO;

public interface ContentDtlTblDAO 
{
	


	
	public void setSqlSesstion(SqlSession session)  throws Exception ;
	
	/**
	 * 컨텐츠 상세정보 추가
	 * @param param
	 * @throws Exception
	 */
	public long insertContentDtl(ContentDtlTblDTO contentDtl) throws Exception ;
	
	/**
	 * 컨텐츠 상세정보  수정
	 * 
	 * @param param
	 * @throws Exception
	 */
	public int updateContentDtl(ContentDtlTblDTO contentDtl) throws Exception ;
	
	
	/**
	 * 컨텐츠 상세정보  조회
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentDtlTblDTO> selectContentDtl(ContentDtlTblDTO contentDtl) throws Exception ;
	
	
	/**
	 * 컨텐츠 상세정보 추가(기존에 등록된 정보가있는지 확인하고 중복이 없다면 등록한다.
	 * 	field : content_title,org_create_dt 가 같은 값이 있는지 확인함.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public long compareInsertContentDtl(ContentDtlTblDTO contentDtl) throws Exception ;
	
	/**
	 * 컨텐츠 상세정보조회(게시물조회)
	 * 	field : content_title,org_create_dt 가 같은 값이 있는지 확인함.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public  List<ContentDtlTblDTO>   selectContentDtlPageList(ContentDtlTblDTO contentDtl) throws Exception;

	/**
	 * 컨텐츠 상세정보를 볼수 있는 권한이 있는지 확인
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List isContentDtlView(ContentDtlTblDTO contentDtl) throws Exception;

	
	/**
	 * 컨텐츠 상세 검색 결과를 리턴한다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentDtlTblDTO> SearchContentDtl(SearchDTO searchParam) throws Exception;
	
}