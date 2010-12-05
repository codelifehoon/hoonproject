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
import socialUp.service.content.dto.ContentDtlTblDTO;
import socialUp.service.content.dto.ContentTitleTblDTO;
import socialUp.service.member.MemberService;
import socialUp.service.member.MemberServiceImpl;
import socialUp.service.member.dto.MemTblDTO;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.jmx.remote.util.Service;
import com.sun.xml.internal.ws.util.ServiceFinder;

public class ContentAction extends BaseActionSupport 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4531298069661381976L;


	/**
	 * 
	 */

	public Logger log = Logger.getLogger(this.getClass());

	
	private List<ContentTitleTblDTO> contentTitleList = null;
	private  List<ContentSourceTblDTO> contentSourceList = null;
	 
	
	public List<ContentSourceTblDTO> getContentSourceList() {
		return contentSourceList;
	}

	public void setContentSourceList(List<ContentSourceTblDTO> contentSourceList) {
		this.contentSourceList = contentSourceList;
	}
	
	

	 			
	

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
		ContentTitleTblDTO 	contentTitleListParam = new ContentTitleTblDTO();		// 타이틀 컨텐츠 정보
		
		
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
		contentService.addMemContent(contentSourceParamArr, contentTitleListParam);
		
		
		
		
		return  returnVal;
	
	}

	/**
	 * 컨텐츠를 수정할 화면
	 * 
	 * @return
	 * @throws Exception
	 */
	public String EditForm	 () throws Exception 
	{
		String returnVal = "DEFAULT";

		
		log.debug("contentEditForm 시작");
		
		AuthInfo authInfo =  AuthService.getAuthInfo(this.request, this.response);
		if (!authInfo.isAuth()) throw new Exception("로그인한 사용자가 아닙니다.");

		String tt_no = this.request.getParameter("tt_no");
		
		// 회원테이블 조회용 객체 생성
		ContentTitleTblDTO	contentTitleList		= new ContentTitleTblDTO();
		ContentSourceTblDTO	contentSource			= new ContentSourceTblDTO();
		ContentService 		contentService 			= (ContentService)ServiceFactory.createService(ContentServiceImpl.class);
		
		log.debug("tt_no:" +tt_no);
		contentTitleList.setTt_no(tt_no);
		contentTitleList.setMt_no(authInfo.getMt_no());
		
		contentSource.setTt_no(tt_no);
		contentSource.setMt_no(authInfo.getMt_no());
		
		
		// 수정할 타이틀의 내용
		this.contentTitleList = contentService.selectContentTitleList(contentTitleList);
		
		// 수정할 의 내용
		this.contentSourceList = contentService.selectContentSourceTbl(contentSource);
		
		
		
		return  returnVal;
	
	}
	
	/**
	 * 컨텐츠를 수정처리
	 * 
	 * @return
	 * @throws Exception
	 */
	public String EditFinish() throws Exception {
		String returnVal = "DEFAULT";
		
		log.debug("contentEditFinish 시작");
		
		AuthInfo authInfo =  AuthService.getAuthInfo(this.request, this.response);
		if (!authInfo.isAuth()) throw new Exception("로그인한 사용자가 아닙니다.");

		String sCurrentDate = DateTime.getFormatString("yyyyMMddHHmmss"); // 현재날짜
		String tt_no 					= this.request.getParameter("tt_no");
		String validatecontentTitle		= this.request.getParameter("validatecontentTitle");
		String title_name				= this.request.getParameter("title_name");	
		String order_mem_open_yn		= this.request.getParameter("order_mem_open_yn");
		String branch_conf_yn			= this.request.getParameter("branch_conf_yn");
		String order_mem_join_yn		= this.request.getParameter("order_mem_join_yn");
		String order_mem_join_passwd	= this.request.getParameter("order_mem_join_passwd");
		String validatecontentTitleChk 	= "";

		String[] cs_no_arr				= this.request.getParameter("cs_no_arr").split(",");

		// 넘어온 타이틀 값들로 인증문장생성
		validatecontentTitleChk  = title_name 
									+ order_mem_open_yn
									+ branch_conf_yn
									+ order_mem_join_yn
									+ order_mem_join_passwd;
		
		
		// 업무처리할 객체 생성
		ContentService 		contentService 			= (ContentService)ServiceFactory.createService(ContentServiceImpl.class);
		ContentTitleTblDTO contentTitle = null;					// 수정될 컨텐츠 타이틀 정보
		List<ContentSourceTblDTO> contentSourceList = new ArrayList<ContentSourceTblDTO>(); 	// 수정될 컨텐츠 소스 정보
		
		
		if (log.isDebugEnabled())
		{
			log.debug("validatecontentTitle:"+ validatecontentTitle);
			log.debug("validatecontentTitleChk:" + validatecontentTitleChk);
			
			
		}
		
		//  컨텐츠 타이틀에 변동사항이 있다면 
		if (!validatecontentTitle.equals(validatecontentTitleChk))
		{
			log.debug("-- contenttitle 변경내용 반영 --  tt_no:" + tt_no);
			
			contentTitle  = new ContentTitleTblDTO();
			
			contentTitle.setTt_no(tt_no);
			contentTitle.setTitle_name(title_name);	
			contentTitle.setOrder_mem_open_yn(order_mem_open_yn);
			contentTitle.setBranch_conf_yn(branch_conf_yn);
			contentTitle.setOrder_mem_join_yn(order_mem_join_yn);
			contentTitle.setOrder_mem_join_passwd(order_mem_join_passwd);
			contentTitle.setUpdate_dt(sCurrentDate);
			contentTitle.setUpdate_no(authInfo.getMt_no());
		}
		
		for (int i=0;i<cs_no_arr.length;i++)
		{
			// 공백으로 들어올수있기 때문에 (ex 1,2,3,4,) 
			if (!"".equals(cs_no_arr[i]))
			{
				String cs_no  = cs_no_arr[i];
				String source_dtl_kind		= this.request.getParameter("source_dtl_kind" + cs_no);
				String source_login_id		= this.request.getParameter("source_login_id" + cs_no);
				String rss2_url				= this.request.getParameter("rss2_url" + cs_no);
				String use_yn				= this.request.getParameter("use_yn" + cs_no);
				String source_owner_kind	= this.request.getParameter("source_owner_kind" + cs_no);
				String validateEditDate		= this.request.getParameter("validateEditDate" + cs_no);
				String validateEditDateChk  = "";
				
				validateEditDateChk = source_dtl_kind
							 			+ source_login_id 
								 		+ rss2_url
								 		+ use_yn;
			 		
				
				//  컨텐츠 소스에 변동사항이 있다면
				if (log.isDebugEnabled()) log.debug("cs_no:" + cs_no);
				if (!validateEditDate.equals(validateEditDateChk))
				{
					log.debug("-- contentSource 변경내용 반영 --  cs_no,use_yn:" + cs_no  + ","  + use_yn);
					ContentSourceTblDTO contentSource  = new ContentSourceTblDTO();
					
					contentSource.setCs_no(cs_no);
					contentSource.setSource_dtl_kind(source_dtl_kind);
					contentSource.setSource_login_id(source_login_id);
					contentSource.setRss2_url(rss2_url);
					contentSource.setUse_yn(use_yn);
					contentSource.setUpdate_dt(sCurrentDate);
					contentSource.setUpdate_no(authInfo.getMt_no());
					 
					contentSourceList.add(contentSource);
				}
			}
			
		}
		
		contentService.updtaeMemContent(contentSourceList, contentTitle);
		
		
		return  returnVal;
	
	}

	public List<ContentTitleTblDTO> getContentTitleList() {
		return contentTitleList;
	}

	public void setContentTitleList(List<ContentTitleTblDTO> contentTitleList) {
		this.contentTitleList = contentTitleList;
	}

		
}
