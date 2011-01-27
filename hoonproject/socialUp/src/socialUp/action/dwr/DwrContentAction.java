package socialUp.action.dwr;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import socialUp.common.AuthInfo;
import socialUp.common.AuthService;
import socialUp.common.DaoFactory;
import socialUp.common.ServiceFactory;
import socialUp.common.mybatis.MyBatisManager;
import socialUp.common.util.CookieUtil;
import socialUp.common.util.DateTime;
import socialUp.common.util.DebugUtil;
import socialUp.common.util.FileUploadListener;
import socialUp.service.content.ContentService;
import socialUp.service.content.ContentServiceImpl;
import socialUp.service.content.dto.ContentDtlCommentDTO;
import socialUp.service.member.MemberService;
import socialUp.service.member.MemberServiceImpl;
import socialUp.service.member.dao.MemTblDAO;
import socialUp.service.member.dao.MemTblDAOImpl;
import socialUp.service.member.dto.MemTblDTO;

import com.opensymphony.xwork2.ActionSupport;

public class DwrContentAction extends BaseDwrAction 
{


	public Logger log = Logger.getLogger(this.getClass());
	
	
	
	/**
	 * 댓글 목록을 가져온다.
	 * 
	 * @param memtbldto
	 * @throws Exception 
	 */
	public HashMap selectContentDtlCommentList(ContentDtlCommentDTO contentDtlCommentParam) throws Exception
	{
		log.debug("selectContentDtlCommentList 시작");
		
		HashMap resultMap = new HashMap();
		String sCurrentDate = DateTime.getFormatString("yyyyMMddHHmmss"); // 현재날짜
		
		resultMap.put("result_code","00");
		resultMap.put("result_msg","성공");
		
		
		try{
		// 컨텐츠 서비스 객체 생성
		ContentService contentService = (ContentService)ServiceFactory.createService(ContentServiceImpl.class);
		 List<ContentDtlCommentDTO>	contentDtlCommentList = null;  
				
		
		 contentDtlCommentList = contentService.selectContentDtlCommentList(contentDtlCommentParam);
		 
		

		 
/*	
 * 		 직접 array를 넘길경우 client에서 eval를 써서 배열로 텍스트 문구를 배열로 다시 만들어야된다.
		 JSONArray jsonArray = JSONArray.fromObject(contentDtlCommentList);
		 resultMap.put("beanlist",jsonArray.toString());
*/
		 
		 
		 Map<String, Object> map = new HashMap<String, Object>();
		 JSONArray jsonArray = JSONArray.fromObject(contentDtlCommentList);
		 map.put("commentList", jsonArray);			// beanList 라는 배열의 묶음으로처리
		  
		 JSONObject jsonObject = JSONObject.fromObject(map);
		 resultMap.put("retValList", jsonObject); 	// beanList 묶음을 넘긴다.
		 
	 

		} catch(Exception e)
		{
			e.printStackTrace();
			resultMap.put("result_code","-1");
			resultMap.put("result_msg",e.getMessage());
		}
		  
		
		return resultMap;
	}
	
	/**
	 * 댓글을 등록한다.
	 * 
	 * @param memtbldto
	 * contentDtlCommentParam.cd_no
	 * contentDtlCommentParam.comment
	 * 
	 * @throws Exception 
	 */
	public HashMap insertContentDtlComment(ContentDtlCommentDTO contentDtlCommentParam) throws Exception
	{
		log.debug("insertContentDtlComment 시작");
		
		HashMap resultMap = new HashMap();
		try 
		{
		log.debug("this.request:" + this.getRequest());
		log.debug("this.response:" + this.getResponse());
		
		String sCurrentDate = DateTime.getFormatString("yyyyMMddHHmmss"); // 현재날짜
		AuthInfo authInfo =  AuthService.getAuthInfo(this.getRequest(), this.getResponse());
		
			
		if (!authInfo.isAuth()) throw new Exception("로그인한 사용자가 아닙니다.");
		
		resultMap.put("result_code","00");
		resultMap.put("result_msg","성공");
		
		
		// 컨텐츠 서비스 객체 생성
		ContentService contentService = (ContentService)ServiceFactory.createService(ContentServiceImpl.class);
		
		contentDtlCommentParam.setMt_no(authInfo.getMt_no());
		contentDtlCommentParam.setCreate_dt(sCurrentDate);
		contentDtlCommentParam.setCreate_no(authInfo.getMt_no());
		contentDtlCommentParam.setUse_yn("Y");
   	    contentService.insertContentDtlCommentList(contentDtlCommentParam);


		}
		catch(Exception e)
		{
			log.debug("4");
			e.printStackTrace();
			resultMap.put("result_code","-1");
			resultMap.put("result_msg",e.getMessage());
		}
		  
		
		return resultMap;
	}
	
	
	/**
	 * 댓글을 수정한다.
	 * 
	 * @param memtbldto
	 * contentDtlCommentParam.cd_no
	 * contentDtlCommentParam.comment
	 * 
	 * @throws Exception 
	 */
	public HashMap updateContentDtlComment(ContentDtlCommentDTO contentDtlCommentParam) throws Exception
	{
		log.debug("updateContentDtlComment 시작");
		
		HashMap resultMap = new HashMap();
		try 
		{
		
		String sCurrentDate = DateTime.getFormatString("yyyyMMddHHmmss"); // 현재날짜
		AuthInfo authInfo =  AuthService.getAuthInfo(this.getRequest(), this.getResponse());
		
		
			
		if (!authInfo.isAuth()) throw new Exception("로그인한 사용자가 아닙니다.");
		
		resultMap.put("result_code","00");
		resultMap.put("result_msg","성공");
		
		
		// 컨텐츠 서비스 객체 생성
		ContentService contentService = (ContentService)ServiceFactory.createService(ContentServiceImpl.class);
		
		// 댓글을 삭제할떄
		if ("D".equals(contentDtlCommentParam.getNewFlag()))
		{
			contentDtlCommentParam.setMt_no(authInfo.getMt_no());
			contentDtlCommentParam.setCdc_no(contentDtlCommentParam.getCdc_no());
			contentDtlCommentParam.setCreate_dt(sCurrentDate);
			contentDtlCommentParam.setCreate_no(authInfo.getMt_no());
			contentDtlCommentParam.setUse_yn("N");
	   	    contentService.updateContentDtlComment(contentDtlCommentParam);
		}
		// 댓글을 수정할때
		else
		{
			contentDtlCommentParam.setMt_no(authInfo.getMt_no());
			contentDtlCommentParam.setCdc_no(contentDtlCommentParam.getCdc_no());
			contentDtlCommentParam.setComment(contentDtlCommentParam.getComment());
			contentDtlCommentParam.setCreate_dt(sCurrentDate);
			contentDtlCommentParam.setCreate_no(authInfo.getMt_no());
	   	    contentService.updateContentDtlComment(contentDtlCommentParam);
		}


		}
		catch(Exception e)
		{
			e.printStackTrace();
			resultMap.put("result_code","-1");
			resultMap.put("result_msg",e.getMessage());
		}
		  
		
		return resultMap;
	}
	
	
	
	/**
	 * 파일업로드 상태를 실시간으로 확인한다.
	 * 
	 * @param memtbldto
	 * contentDtlCommentParam.cd_no
	 * contentDtlCommentParam.comment
	 * 
	 * @throws Exception 
	 */
	public HashMap fileUplaodStst(HashMap mapParam) throws Exception
	{
		//log.debug("fileUplaodStst 시작");
		
		HashMap resultMap = new HashMap();
		long	bytesRead = 0,
				contentLength = 0,
				percentComplete = 0;
		String  finished = "no";
		
		try 
		{
			
			String sCurrentDate = DateTime.getFormatString("yyyyMMddHHmmss"); // 현재날짜
			AuthInfo authInfo =  AuthService.getAuthInfo(this.getRequest(), this.getResponse());
			
			if (!authInfo.isAuth()) throw new Exception("로그인한 사용자가 아닙니다.");
			
			resultMap.put("result_code","00");
			resultMap.put("result_msg","성공");
		
			
			
			String fileName =  (String)mapParam.get("fileName");

			//log.debug("fileName:" + fileName);

			// 파일 업로드 상태 확인 시작
	    	HttpSession session = this.getRequest().getSession();
	    	FileUploadListener listener = null; 
		    StringBuffer buffy = new StringBuffer();
		    
	    	
	    	// Make sure the session has started
	    	if (session == null)
	    	{
	    		// 파일 업로드가 없음
				bytesRead 		= -1;
				contentLength 	= -1;
	    	}
	    	else if (session != null)
	    	{
	    		// Check to see if we've created the listener object yet
	    		listener = (FileUploadListener)session.getAttribute("LISTENER");
	    		
	    		if (listener == null)
	    		{
	    			// 파일 업로드가 없음
	    			bytesRead 		= -1;
	    			contentLength 	= -1;
	    		}
	    		else
	    		{
	    	    	bytesRead = listener.getBytesRead();
	    			contentLength = listener.getContentLength();
	    			percentComplete = ((100 * bytesRead) / contentLength);
	    			
	    			if (bytesRead == contentLength) finished = "yes";
	    				 
	    		}
	    	}
	    	
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resultMap.put("result_code","-1");
			resultMap.put("result_msg",e.getMessage());
		}
		
		resultMap.put("bytesRead",bytesRead);
		resultMap.put("contentLength",contentLength);
		resultMap.put("percentComplete",percentComplete);
		resultMap.put("finished",finished);
		
		
		
		return resultMap;
	}
	
	

}
