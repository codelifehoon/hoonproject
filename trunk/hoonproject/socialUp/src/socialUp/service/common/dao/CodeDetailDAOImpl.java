package socialUp.service.common.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import socialUp.common.DaoFactory;
import socialUp.common.mybatis.MyBatisManager;
import socialUp.service.common.dto.CodeDetailDTO;
import socialUp.service.content.dao.ContentSourceTblDAO;
import socialUp.service.content.dao.ContentSourceTblDAOImpl;



public class CodeDetailDAOImpl
{

	/*
	 * 그룹코드에 해당하는 상세코드를 가져온다.
	 * 
	 * @param grp_Cd	그룹코드
	 * @return
	 * @throws Exception
	 */
	public static  List<CodeDetailDTO>  selectCodeGroup(String grp_cd) throws Exception
	{
		List<CodeDetailDTO> resultList = null;
		
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		try{
		
		
			CodeDetailDTO codeDetailDTO = new CodeDetailDTO();
			codeDetailDTO.setGrp_cd(grp_cd);
			
			// 회원테이블 조회용 객체 생성
			resultList =  (List<CodeDetailDTO>)sqlMap.selectList("socialUp.service.common.mapper.cache.selectCodeGroup", codeDetailDTO);
			
			
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
	 * 그룹코드,상세코드에 해당하는 값을 리턴한다.
	 * 
	 * @param grp_Cd
	 * @param detail_Cd
	 * @return
	 * @throws Exception
	 */
	public static  CodeDetailDTO selectCodeGroupDetail(String grp_cd,String detail_cd) throws Exception
	{
		CodeDetailDTO resultList = null;
		
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		try{
		
			CodeDetailDTO codeDetailDTO = new CodeDetailDTO();
			codeDetailDTO.setGrp_cd(grp_cd);
			codeDetailDTO.setDetail_cd(detail_cd);
			
			// 회원테이블 조회용 객체 생성
			resultList =  (CodeDetailDTO)sqlMap.selectOne("socialUp.service.common.mapper.selectCodeGroup", codeDetailDTO);
			
			
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
	 * 그룹코드에 해당하는 코드를 option 문자열로 리턴한다.
	 * 
	 * @param grp_Cd
	 * @param detail_Cd
	 * @param defaultNm		// 디폴트로 표시하고싶은값이
	 * @return
	 * @throws Exception
	 */
	public static  String selectCodeGroupOptionString(String grp_Cd,String defaultVal) throws Exception
	{
		StringBuffer resultString = new StringBuffer();
		
		
		List<CodeDetailDTO> CodeDetailList  =  CodeDetailDAOImpl.selectCodeGroup(grp_Cd);
		
		for (int i=0;i<CodeDetailList.size();i++)
		{
			resultString.append("<option value='");
			resultString.append(CodeDetailList.get(i).getDetail_cd());
			
			// option 기본값 설정
			if (defaultVal.equals(CodeDetailList.get(i).getDetail_cd()))
			{
				resultString.append("' selected>");
			}
			else
			{
				resultString.append("'>");
			}
			
			
			resultString.append(CodeDetailList.get(i).getDetail_nm());
			resultString.append("</option>");
		}
		
		return resultString.toString();
	}
	
}