package socialUp.service.content;

import java.util.HashMap;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.DaoFactory;
import socialUp.common.mybatis.MyBatisManager;
import socialUp.common.util.CookieUtil;
import socialUp.service.content.dao.ContentSourceTblDAOImpl;
import socialUp.service.content.dao.ContentSourceTblDAO;
import socialUp.service.content.dao.ContentTitleListTblDAO;
import socialUp.service.content.dao.ContentTitleListTblDAOImpl;
import socialUp.service.content.dto.ContentSourceTblDTO;
import socialUp.service.content.dto.ContentTitleListTblDTO;
import socialUp.service.member.dao.MemTblDAO;
import socialUp.service.member.dao.MemTblDAOImpl;
import socialUp.service.member.dto.MemTblDTO;

public class ContentServiceImpl implements ContentService 
{
	public Logger log = Logger.getLogger(this.getClass());
	
	
	/**
	 * 회원의 등록된 컨텐츠 소스를 가져온다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentSourceTblDTO> selectContentSourceTbl(ContentSourceTblDTO contentSourceParam) throws Exception
	{

		log.debug("selectContentSourceTbl 시작");
		
		List<ContentSourceTblDTO> resultList = null;
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		try{
		
		
		// 회원테이블 조회용 객체 생성
		ContentSourceTblDAO contentSourceTblDAO = (ContentSourceTblDAO)DaoFactory.createDAO(ContentSourceTblDAOImpl.class);
		contentSourceTblDAO.setSqlSesstion(sqlMap);			// sql session 설정
		
		// 회원정보조회
		resultList = contentSourceTblDAO.selectContentSourceTbl(contentSourceParam);
		
		sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
		
		
		
		return resultList;
	}
	
	/**
	 * 회원의 최초 고리정보를 생성한다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public String addMemGoreContent(List<ContentSourceTblDTO> contentSourceArr ,ContentTitleListTblDTO contentTitleListParam) throws Exception
	{
		String resultVal = "";
		
		
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		int tt_No = 0;			// 컨텐츠 타이틀 일련번호
		
		try
		{

		ContentTitleListTblDAO 	contentTitleListTblDAO = (ContentTitleListTblDAO)DaoFactory.createDAO(ContentTitleListTblDAOImpl.class);
		ContentSourceTblDAO 	contentSourceTblDAO = (ContentSourceTblDAO)DaoFactory.createDAO(ContentSourceTblDAOImpl.class);
		
		contentTitleListTblDAO.setSqlSesstion(sqlMap);
		contentSourceTblDAO.setSqlSesstion(sqlMap);

		// content_title_list_tbl 생성
		tt_No = contentTitleListTblDAO.insertContentTitleListTbl(contentTitleListParam);
		
		// content_source_tbl 생성
		for (int i=0;i<contentSourceArr.size();i++)
		{
			ContentSourceTblDTO contentSource = contentSourceArr.get(i);
			contentSource.setTt_no(String.valueOf(tt_No));
			
			contentSourceTblDAO.insertContentSourceTbl(contentSource);
		}
		
		
		sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
		
		
		
		return resultVal;
	}
	
	

	
	}
