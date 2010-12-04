package socialUp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import socialUp.action.main.BaseActionSupport;
import socialUp.common.AuthInfo;
import socialUp.common.AuthService;
import socialUp.common.ServiceFactory;
import socialUp.common.util.CookieUtil;
import socialUp.common.util.DateTime;
import socialUp.common.util.DebugUtil;
import socialUp.service.content.ContentService;
import socialUp.service.content.ContentServiceImpl;
import socialUp.service.content.dto.ContentSourceTblDTO;
import socialUp.service.content.dto.ContentTitleListTblDTO;
import socialUp.service.member.MemberService;
import socialUp.service.member.MemberServiceImpl;
import socialUp.service.member.dto.MemTblDTO;

import com.opensymphony.xwork2.ActionSupport;

public class GoriContentAction extends BaseActionSupport 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4531298069661381976L;


	/**
	 * 
	 */

	public Logger log = Logger.getLogger(this.getClass());
	
	

	/**
	 * 최초  컨텐츠를 등록하기 위한 화면을 호출
	 * 
	 * @return
	 * @throws Exception
	 */
	public String AddForm () throws Exception 
	{
		String returnVal = "DEFAULT";
		
		log.debug("contentFirstAddForm 시작");
		
		// 회원테이블 조회용 객체 생성
		ContentService contentService = (ContentService)ServiceFactory.createService(ContentServiceImpl.class);
		ContentSourceTblDTO contentSourceTblDTO = new ContentSourceTblDTO();
		
		AuthInfo authInfo =  AuthService.getAuthInfo(this.request, this.response);
		
		
		if (!authInfo.isAuth()) throw new Exception("로그인한 사용자가 아닙니다.");
		
		
		contentSourceTblDTO.setMt_no(authInfo.getMt_no());

		 
		// 수집 컨텐츠 등록정보
		List<ContentSourceTblDTO> resultList = contentService.selectContentSourceTbl(contentSourceTblDTO);
			
		
		
		return  returnVal;
	
	} 
	
	/**
	 * 최초 컨텐츠를 실제 등록처리
	 * 
	 * @return
	 * @throws Exception
	 */
	public String AddFinish() throws Exception 
	{
		String returnVal = "DEFAULT";
		
		log.debug("contentFirstAddProc 시작");
		
		
		
		AuthInfo authInfo =  AuthService.getAuthInfo(this.request, this.response);
		String sCurrentDate = DateTime.getFormatString("yyyyMMddHHmmss"); // 현재날짜
		
		
		if (!authInfo.isAuth()) throw new Exception("로그인한 사용자가 아닙니다.");
		
		// 회원테이블 조회용 객체 생성
		ContentService contentService = (ContentService)ServiceFactory.createService(ContentServiceImpl.class);
		 
		
		String source_dtl_kind 		= this.request.getParameter("source_dtl_kind");			// 소속사이트
		String source_login_id 		= this.request.getParameter("source_login_id");
		String rss2_url 			= this.request.getParameter("rss2_url");				// 자기 컨텐츠 주소(블로그등등)
		String source_kind 			= this.request.getParameter("source_kind");				// 소스종료
		String favoriteContent 		= this.request.getParameter("favoriteContent");			// 자기가 자주가는 블로그,트위터등등
		String order_mem_open_yn 	= this.request.getParameter("order_mem_open_yn");		// 자기가 등록한 고리에 대한 공개여부
		String branch_conf_yn	 	= this.request.getParameter("branch_conf_yn");			// 가지치기 가능여부
		String order_mem_join_yn	= this.request.getParameter("order_mem_join_yn");
		String order_mem_join_passwd= this.request.getParameter("order_mem_join_passwd");
		
		String[] favoriteContentArr  	= null; 												// 즐겨찾는 고리에 대해 배열로 분리
		
		// 즐겨찾는 고리 배열생성
		favoriteContent = favoriteContent.replaceAll("\r\n", ",");
		favoriteContentArr = favoriteContent.split(",");
		
		
		List<ContentSourceTblDTO> contentSourceParamArr = new ArrayList<ContentSourceTblDTO>();	// 타이틀 테이블에 들어갈정보 배열
		ContentTitleListTblDTO 	contentTitleListParam = new ContentTitleListTblDTO();		// 타이틀 컨텐츠 정보
		
		
		contentTitleListParam.setMt_no(authInfo.getMt_no());
		contentTitleListParam.setTitle_name("나의 기본 글모음");
		contentTitleListParam.setTitle_kind("00");
		contentTitleListParam.setBranch_conf_yn(branch_conf_yn);
		contentTitleListParam.setOrder_mem_open_yn(order_mem_open_yn);
		contentTitleListParam.setOrder_mem_join_yn(order_mem_join_yn);
		contentTitleListParam.setOrder_mem_join_passwd(order_mem_join_passwd);
		contentTitleListParam.setCreate_no(authInfo.getMt_no());
		contentTitleListParam.setCreate_dt(sCurrentDate);
		contentTitleListParam.setUpdate_no(authInfo.getMt_no());
		contentTitleListParam.setUpdate_dt(sCurrentDate);
		
		
		// 자신의 컨텐츠 정보 생성
		ContentSourceTblDTO contentSourceTblDTO = new ContentSourceTblDTO(); 
		
		
		contentSourceTblDTO.setMt_no(authInfo.getMt_no());
		contentSourceTblDTO.setRss2_url(rss2_url);
		contentSourceTblDTO.setSource_kind(source_kind);
		contentSourceTblDTO.setSource_dtl_kind(source_dtl_kind);
		contentSourceTblDTO.setSource_owner_kind("01");				// 본인이 직접등록
		contentSourceTblDTO.setSource_login_id(source_login_id);
		contentSourceTblDTO.setReg_stat("02");
		contentSourceTblDTO.setCreate_dt(sCurrentDate);
		contentSourceTblDTO.setCreate_no(authInfo.getMt_no());
		contentSourceTblDTO.setUpdate_dt(sCurrentDate);
		contentSourceTblDTO.setUpdate_no(authInfo.getMt_no());
		
		
		
		contentSourceParamArr.add(contentSourceTblDTO);
		
		// 자신의 관심 컨텐츠 등록
		for (int i=0;i<favoriteContentArr.length;i++)
		{
			// 관심컨텐츠가 공백이 아니라면 등록한다.
			if (favoriteContentArr[i] != null
					&& !"".equals(favoriteContentArr[i].trim())
				)
			{
				// 자신의 컨텐츠 정보 생성
				ContentSourceTblDTO favoritContentSource = new ContentSourceTblDTO(); 
				
				
				favoritContentSource.setMt_no(authInfo.getMt_no());
				favoritContentSource.setRss2_url(favoriteContentArr[i]);
				favoritContentSource.setSource_kind(source_kind);
				favoritContentSource.setSource_dtl_kind("");
				favoritContentSource.setSource_owner_kind("03");				// 본인이 직접등록
				favoritContentSource.setSource_login_id("");
				favoritContentSource.setReg_stat("02");
				favoritContentSource.setCreate_dt(sCurrentDate);
				favoritContentSource.setCreate_no(authInfo.getMt_no());
				favoritContentSource.setUpdate_dt(sCurrentDate);
				favoritContentSource.setUpdate_no(authInfo.getMt_no());
				
				
				contentSourceParamArr.add(favoritContentSource);
				
			}
		}
		
		
		// 최초 자신의 고리 개설
		contentService.addMemGoreContent(contentSourceParamArr, contentTitleListParam);
		
		
		
		
		return  returnVal;
	
	}

	/**
	 * 컨텐츠를 수정할 화면
	 * 
	 * @return
	 * @throws Exception
	 */
	public String EditForm	 () throws Exception {
		String returnVal = "DEFAULT";
		
		
		
		return  returnVal;
	
	}
	
	/**
	 * 컨텐츠를 수정처리
	 * 
	 * @return
	 * @throws Exception
	 */
	public String EditFinish	 () throws Exception {
		String returnVal = "DEFAULT";
		
		
		
		return  returnVal;
	
	}
	
	

	 			
		
}
