package socialUp.service.content;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.sun.syndication.feed.synd.SyndFeed;

import socialUp.service.content.dto.ContentBranchDTO;
import socialUp.service.content.dto.ContentDtlCommentDTO;
import socialUp.service.content.dto.ContentDtlImgDTO;
import socialUp.service.content.dto.ContentDtlTblDTO;
import socialUp.service.content.dto.ContentJoinMemDTO;
import socialUp.service.content.dto.ContentSourceTblDTO;
import socialUp.service.content.dto.ContentTitleTblDTO;
import socialUp.service.content.dto.SearchDTO;

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
	public String  addMemContent(List<ContentSourceTblDTO> contentSourceArr ,ContentTitleTblDTO contentTitleListParam , String branchTtno) throws Exception;
	
	
	/**
	 * 회원의 고리정보를 update 한다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public String  updtaeMemContent(List<ContentSourceTblDTO> contentSourceArr ,ContentTitleTblDTO contentTitleListParam) throws Exception;
	
	
	
	/**
	 * 컨텐츠 소스정보를 이용해서 자료를 수집하고 등록한다.
	 * 
	 * @param contentSource 컨텐츠 소스정보
	 * @return
	 * @throws Exception
	 */
	public String  addContentCollect(ContentSourceTblDTO contentSource) throws Exception;
	
	/**
	 * 수집대상이 되는 컨텐츠의 리스트
	 * 
	 * @param contentSource 컨텐츠 소스정보
	 * @return
	 * @throws Exception
	 */
	public List<ContentSourceTblDTO>  selectContentCollectWaitList() throws Exception;
	
	
	/*
	 * 게시물 조회목록
	 * 
	 * @param contentSource 컨텐츠 소스정보
	 * @return
	 * @throws Exception
	 */
	public ContentTitleTblDTO  selectContentDtlPageList(ContentDtlTblDTO contentDtl) throws Exception;
	
	/**
	 * 컨텐츠 상세정보를 볼수 있는 권한이 있는지 확인
	 * 
	 * @param contentSource 컨텐츠 소스정보
	 * 		contentDtl.cd_no
	 * 		contentDtl.mt_no
	 * @return
	 * @throws Exception
	 */
	public boolean  isContentDtlViewAuth(ContentDtlTblDTO contentDtl) throws Exception;
	

	/**
	 * 게시물 상세내용
	 * 
	 * @param ContentDtlTblDTO 컨텐츠 상세정보
	 * 			contentDtl.getCd_no();
	 * 			contentDtl.getMt_no();
	 * @return
	 * @throws Exception
	 */
	public ContentTitleTblDTO  selectContentDtlView(ContentDtlTblDTO contentDtlParam) throws Exception;

	/**
	 * 사이트에서 신규컨텐츠 상세를 직업 등록한다.
	 * 
	 * @param contentDtlParam 컨텐츠 소스정보
	 * 		사용되는필드
	 * 		contentDtlTblDTO.setTt_no
			contentDtlTblDTO.setMt_no
			contentDtlTblDTO.setContent_title
			contentDtlTblDTO.setContent_desc
			contentDtlTblDTO.setSource_kind
			contentDtlTblDTO.setUse_yn
			contentDtlTblDTO.setCreate_dt
			contentDtlTblDTO.setCreate_no
	 * 		
	 * @return
	 * @throws Exception
	 */
	public String  insertContentDtl(ContentDtlTblDTO contentDtlParam) throws Exception;

	
	/**
	 * 사이트에서 신규컨텐츠 상세를 직업 등록한다.
	 * 
	 * @param contentDtlParam 컨텐츠 소스정보
	 * 		사용되는필드
	 * 		contentDtlTblDTO.setCd_no
			contentDtlTblDTO.setMt_no
			contentDtlTblDTO.setContent_title
			contentDtlTblDTO.setContent_desc
			contentDtlTblDTO.setUse_yn
			contentDtlTblDTO.setCreate_dt
			contentDtlTblDTO.setCreate_no
	 * 		
	 * @return
	 * @throws Exception
	 */
	public String  updateContentDtl(ContentDtlTblDTO contentDtlParam) throws Exception;

	
	/**
	 * 컨텐츠 상세의 댓글을 등록한다.
	 * 
	 * @param param
	 * 
	 * @throws Exception
	 */
	public void insertContentDtlCommentList(ContentDtlCommentDTO contentDtlCommentParam) throws Exception;

	/**
	 * 컨텐츠 상세의 댓글을 가져온다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentDtlCommentDTO> selectContentDtlCommentList(ContentDtlCommentDTO contentDtlCommentParam) throws Exception;

	

	/**
	 * 컨텐츠 상세의 댓글을 수정한다.
	 * 
	 * @param param
	 * @return TODO
	 * @throws Exception
	 */
	public int updateContentDtlComment(ContentDtlCommentDTO contentDtlCommentParam) throws Exception;
	

	/**
	 * 타이틀 컨텐츠가 가지고있는 브렌치 정보를 가져온다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public ContentTitleTblDTO selectContentBranch(ContentTitleTblDTO contentTitleParam) throws Exception;
	
	/**
	 * 컨텐츠 타이틀에 브랜치 정보를 추가한다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public ContentBranchDTO insertContentNewBranch(ContentBranchDTO contentBranchParam) throws Exception;

	/**
	 * 컨텐츠 상세 검색 결과를 리턴한다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentDtlTblDTO> SearchContentDtl(SearchDTO searchParam) throws Exception;

	/**
	 * 컨텐츠타이틀 참여회원조회
	 * 
	 * @param contentJoinMem
	 * @return
	 * @throws Exception
	 */
	public List<ContentJoinMemDTO> selectContentJoinMemList(ContentJoinMemDTO contentJoinMemParam) throws Exception;

	/**
	 * 컨텐츠타이틀  다른 회원 참여처리
	 * 
	 * @param contentJoinMem
	 * @return
	 * @throws Exception
	 */
	public ContentTitleTblDTO addContentJoin(ContentTitleTblDTO contentTitleParam) throws Exception; 

	/**
	 * 나의 컨텐츠가 내가 참여를 원하는 퀀텐츠 목록에 브랜치로 등록되어 있는지확인한다.
	 * 
	 * @param contentJoinMem
	 * tt_no :  참여를 원하는 퀀텐츠 목록 번호 
 	   mt_no :  tt_no에 소속되어 있는 컨텐츠중 mt_no에 속한 컨텐츠 목록을 뽑아온다.
	 * @return
	 * @throws Exception
	 */
	public List<ContentBranchDTO> selectContentBranchSelfJoinList(ContentBranchDTO contentBranchParam) throws Exception;
	
	/**
	 * 나의 컨텐츠를 다른사람의 컨텐츠 타이틀 브랜치 정보에 스스로 참여시킨다.
	 * 
	 * addContentBranchParam : 참여원하는 브랜치에 추가 대상 컨텐츠타이틀
	 * delContentBranchParam : 브랜치에 삭제 대상 컨텐츠타이틀
	 * @throws Exception
	 */
	public void insertContentBranchSelfJoin(ContentBranchDTO addContentBranchParam,ContentBranchDTO delContentBranchParam) throws Exception;
	

	/**
	 * 게시물 조회목록
	 * 
	 * @param contentSource 컨텐츠 소스정보
	 * @return
	 * @throws Exception
	 */
	public SyndFeed  makeFeedRss(ContentDtlTblDTO contentDtlParam) throws Exception;
	
}
