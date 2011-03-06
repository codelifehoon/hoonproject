package socialUp.service.content.dao;

import java.util.HashMap;
import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.properties.PropertiesManager;
import socialUp.common.util.CmnUtil;
import socialUp.common.util.DebugUtil;
import socialUp.common.util.NumUtil;
import socialUp.service.content.dto.ContentDtlCommentDTO;
import socialUp.service.content.dto.ContentDtlImgDTO;
import socialUp.service.content.dto.ContentDtlTblDTO;
import socialUp.service.content.dto.UploadFilesDTO;
import java.util.Vector;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Element;

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

	
	// 업로드 파일 테이블 관련
	
	@Override
	public long insertUploadFiles(UploadFilesDTO uploadFilesParam) throws Exception 
	{
		 
		this.sqlMap.insert("socialUp.service.content.mapper.insertUploadFiles", uploadFilesParam);
		
		// 등록후 시퀀스를 넘겨준다.
		return NumUtil.toInt(uploadFilesParam.getUf_id());
	}

	@Override
	public int updateUploadFiles(UploadFilesDTO uploadFilesParam) throws Exception 
	{
		return  this.sqlMap.update("socialUp.service.content.mapper.updateUploadFiles", uploadFilesParam);
		
	}

	@Override
	public List<UploadFilesDTO> selectUploadFiles(UploadFilesDTO uploadFilesParam) throws Exception  
	{
		 List<UploadFilesDTO> uploadFilesList = null;
		 
		  uploadFilesList =   this.sqlMap.selectList("socialUp.service.content.mapper.selectUploadFilesList", uploadFilesParam);
		 Long tt  		=   (Long)this.sqlMap.selectOne("socialUp.service.common.mapper.selectFoundRows");
		 
		 for(int i=0;i<uploadFilesList.size();i++) uploadFilesList.get(i).setAllRowNum(tt.intValue());
			
		 
		 return uploadFilesList;
		
		
		
	}

	/**
	 * 글상세 이미지 목록 테이블 정보 입력
	 * @param param
	 * @throws Exception
	 */
	public long updateContentDtlImg(ContentDtlImgDTO contentDtlImgParam) throws Exception 
	{

		// 필수값 chk
		if ( "".equals(CmnUtil.nvl(contentDtlImgParam.getCd_no())) 
				&& "".equals(CmnUtil.nvl(contentDtlImgParam.getCdi_no())) 
			) throw new Exception("updateContentDtlImg 필수값이 없습니다");


		return  this.sqlMap.update("socialUp.service.content.mapper.updateContentDtlImg", contentDtlImgParam);

	}
	
	/**
	 * 글상세 이미지 목록 정보 수정
	 * 
	 * @param param
	 * @throws Exception
	 */
	public int insertContentDtlImg(ContentDtlImgDTO contentDtlImgParam) throws Exception 
	{
		 
		this.sqlMap.insert("socialUp.service.content.mapper.insertContentDtlImg", contentDtlImgParam);
		
		// 등록후 시퀀스를 넘겨준다.
		return NumUtil.toInt(contentDtlImgParam.getCdi_no());
	}
	
	/**
	 * 글상세 이미지 목록 정보 조회
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentDtlImgDTO> selectContentDtlImgList(ContentDtlImgDTO contentDtlImgParam) throws Exception
	{
		 List<ContentDtlImgDTO> contentDtlImgList = null;
		 
		 contentDtlImgList =   this.sqlMap.selectList("socialUp.service.content.mapper.selectContentDtlImgList", contentDtlImgParam);
		
		 
		 return contentDtlImgList;
	}
	
	
	/**
	 * html 본문에서  이미지를 태그를  뽑아내어 thumbnail을 생성한다.
	 * 본문에 있는 간략한 이미지를 뽑아내기 위해서 사용한다.
	 * @param 	contentDtlParam.getCd_no()				등록일련번호
	 * 			contentDtlParam.getContent_desc()		내용
	 * @return
	 */
	public int extractRegContentDtlThumbNail(ContentDtlTblDTO contentDtlParam) throws Exception
	{
		log.debug("extractRegContentDtlThumbNail 시작");
		
		int retVal = 0;
		CmnUtil	cmnUtil = new CmnUtil(); 
		
		// 유효한 img 태그 추출
		Vector tagNames = new Vector();
        tagNames.add(HTMLElementName.IMG);
        
        HashMap elementsMap =  CmnUtil.getHttpElementsMapFromStream(contentDtlParam.getContent_desc(),tagNames);

        List<Element> elementList = (List<Element>) elementsMap.get(HTMLElementName.IMG);
        
        String imgUrl = null;
        if( elementList != null ) 
        {

        	// 기존에 등록된 썸네일은 모두 삭제처리
        	ContentDtlImgDTO contentDtlImgParam = new ContentDtlImgDTO();
        	
        	contentDtlImgParam.setCd_no(contentDtlParam.getCd_no());
        	contentDtlImgParam.setUse_yn("N");
        	contentDtlImgParam.setCreate_dt(contentDtlParam.getCreate_dt());
    		contentDtlImgParam.setCreate_no(contentDtlParam.getCreate_no());
    		updateContentDtlImg(contentDtlImgParam);
    		
        	
        	
        	for(int i=0;i<elementList.size();i++) 
        	{
        		Element element = elementList.get(i);
        		
        		// 문서내의 이미지 다운로드 & 축소 이미지 생성
        		
        		String filePath =  PropertiesManager.getProperty("file.defpath");
        		String thumbNailFileName = "";
        		
        		// 파일경 생성
        		filePath += "/" + contentDtlParam.getCreate_dt().substring(0, 8) + "/thumb"; 
        		
				// 원본파일경로에서 파일명만 가져오기
        		thumbNailFileName = "cdno_" + contentDtlParam.getCd_no() + "_seq" + String.valueOf(i)  + "_" + cmnUtil.getFileName(element.getAttributeValue("src"),"/");
				
        		//CmnUtil.httpFileDownload(element.getAttributeValue("src"),filePath + "/" + fileName);
        		// 원본다운로드및 축소 이미지 생성은 배치에서 관리
        		// 축소이미지 등록        		
        		contentDtlImgParam = new ContentDtlImgDTO();
        		
        		contentDtlImgParam.setCd_no(contentDtlParam.getCd_no());
        		contentDtlImgParam.setImg_kind("01");		 				// 일반이미지
        		contentDtlImgParam.setImg_url(element.getAttributeValue("src"));
        		contentDtlImgParam.setUse_yn("Y");
        		contentDtlImgParam.setProc_stat("00");
        		contentDtlImgParam.setCreate_dt(contentDtlParam.getCreate_dt());
        		contentDtlImgParam.setCreate_no(contentDtlParam.getCreate_no());
        		contentDtlImgParam.setThumbnail_url(filePath + "/" + thumbNailFileName);
        		insertContentDtlImg(contentDtlImgParam);
        		
        		retVal++;
            }
        	

        }
		
		return retVal;
		
	}
	

	/**
	 * 썸네일 대상 이미지 목록
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentDtlImgDTO> selectContentDtlImgWaitList(ContentDtlImgDTO contentDtlImgParam) throws Exception
	{
		 List<ContentDtlImgDTO> contentDtlImgList = null;
		 
		 contentDtlImgList =   this.sqlMap.selectList("socialUp.service.content.mapper.selectContentDtlImgWaitList", contentDtlImgParam);

		 return contentDtlImgList;
	}
	
}