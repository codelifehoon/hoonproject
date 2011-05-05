package socialUp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import socialUp.action.main.BaseActionSupport;
import socialUp.common.AuthInfo;
import socialUp.common.AuthService;
import socialUp.common.ServiceFactory;
import socialUp.common.util.CmnUtil;
import socialUp.common.util.CookieUtil;
import socialUp.common.util.DateTime;
import socialUp.common.util.DebugUtil;
import socialUp.common.util.NumUtil;
import socialUp.service.content.ContentService;
import socialUp.service.content.ContentServiceImpl;
import socialUp.service.content.dto.ContentBranchDTO;
import socialUp.service.content.dto.ContentSourceTblDTO;
import socialUp.service.content.dto.ContentDtlTblDTO;
import socialUp.service.content.dto.ContentTitleTblDTO;
import socialUp.service.content.dto.SearchDTO;
import socialUp.service.member.MemberService;
import socialUp.service.member.MemberServiceImpl;
import socialUp.service.member.dto.MemTblDTO;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.jmx.remote.util.Service;
import com.sun.xml.internal.ws.util.ServiceFinder;

public class SearchAction extends BaseActionSupport 
{

	
	
	private static final long serialVersionUID = -4670002927249892181L;
	
	public Logger log = Logger.getLogger(this.getClass());
	
	List<ContentDtlTblDTO> contentDtlList;
	SearchDTO			   search;				
	
	
	public List<ContentDtlTblDTO> getContentDtlList() {
		return contentDtlList;
	}

	public void setContentDtlList(List<ContentDtlTblDTO> contentDtlList) {
		this.contentDtlList = contentDtlList;
	}

	public SearchDTO getSearch() {
		return search;
	}

	public void setSearch(SearchDTO search) {
		this.search = search;
	}
	
	
	/**
	 * 최초  컨텐츠를 등록하기 위한 화면을 호출
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ResultList() throws Exception 
	{
		String returnVal = "DEFAULT";
		
		log.debug("DoResult 시작");
		
		//회원테이블 조회용 객체 생성
		ContentService contentService = (ContentService)ServiceFactory.createService(ContentServiceImpl.class);
		SearchDTO	  searchParam = new SearchDTO();
		
		AuthInfo authInfo =  AuthService.getAuthInfo(this.getRequest(), this.getResponse());
		String searchStr 	= CmnUtil.nvl(this.getRequest().getParameter("searchStr"));
		
		if (searchStr.trim().length() < 2) throw new Exception("검색어는 2자 이상이어야 합니다.");
		searchParam.setSearchStr(searchStr);
		searchParam.setSearchType("00");	// 컨텐츠 타이틀 상세 모두 검색
		this.contentDtlList = contentService.SearchContentDtl(searchParam);
		this.search 		= searchParam;
			
		return  returnVal;
	
	}

}
