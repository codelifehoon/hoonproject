package socialUp.service.common.dao;

import java.util.ArrayList;
import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.jdom.Document;

import socialUp.common.DaoFactory;
import socialUp.common.mybatis.MyBatisManager;
import socialUp.common.util.CmnUtil;
import socialUp.common.util.DateTime;
import socialUp.service.common.dto.CodeDetailDTO;
import socialUp.service.content.dao.ContentSourceTblDAO;
import socialUp.service.content.dao.ContentSourceTblDAOImpl;
import socialUp.service.content.dto.ContentDtlTblDTO;
import socialUp.service.content.dto.ContentSourceTblDTO;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;



/**
 * 
 * RSS, ATOM 관련 컨텐츠를 읽어오는 객체
 * 
 * @author 장재훈
 *
 */
public class ReadRssContentDAOImpl implements  ReadContentDAO
{

	public Logger log = Logger.getLogger(this.getClass());
	
	
	
	
	/**
	 * 컨텐츠소스 정보를 이용해서 컨텐츠를 읽어온
	 * 
	 * @param contentSourceParam
	 * @throws Exception
	 */
	public List<ContentDtlTblDTO>  readContent(ContentSourceTblDTO contentSourceParam)  throws Exception
	{
		List<ContentDtlTblDTO> contentDtlResult = new ArrayList<ContentDtlTblDTO>();
		
		 
		try
		{
			// String contents  = "";
			// InputStream input = new ByteArrayInputStream(contents.getBytes("UTF-8"));
			
			URL feedUrl = null;
			
			try
			{
				feedUrl = new URL(contentSourceParam.getRss2_url());
			}
			// 전달URL의 값이 URL 폼에 맞지않을떄 발생(잘못된 URL 전달시)
			catch (java.net.MalformedURLException e)
			{
				return null;	
			}
			
	        SyndFeedInput input = new SyndFeedInput();
	        SyndFeed syndFeed  = null;
	        try
			{
			      syndFeed = input.build(new XmlReader(feedUrl));
			}
	        // 	분석가능한 xml 형식이 아닐때(RSS,ATOM이 아님)
			catch (com.sun.syndication.io.ParsingFeedException e)
			{
				return null;
			}
	        
	        /*RSS*/
	        log.debug("### getFeedType 			[" + syndFeed.getFeedType() +"]");
	        log.debug("### getLanguage 			[" + syndFeed.getLanguage() +"]");
	        log.debug("### getTitle 			[" + syndFeed.getTitle() +"]");
	        log.debug("### getPublishedDate 	[" + syndFeed.getPublishedDate() +"]");
	
	        List<SyndEntry> entries = syndFeed.getEntries();        
	        SyndEntry entry = null;
	        
	        /*발행정보*/
	        for(int i=0, j=entries.size(); i<j ; i++) 
	        {
	        	
	        	entry = entries.get(i);
	        	
	        	
	        	ContentDtlTblDTO contentDtlTblDTO = new ContentDtlTblDTO();
	        	String[] categoryList = new String[entry.getCategories().size()];
	        	
	        	
	            
	            /*
		            log.debug("### 글타이틀 		[" + entry.getTitle() +"]");
		            log.debug("### 글타이틀링크 	[" + entry.getLink() +"]");
		            log.debug("### 글URL 		[" + entry.getUri() +"]");
		            log.debug("### 글내용 		[" + entry.getDescription().getValue() +"]");
		            log.debug("### 글작성시간	[" + DateTime.format(entry.getPublishedDate()) +"]");
		            log.debug("### 글작성자		[" + entry.getAuthor() +"]");
		        */
	            
	            contentDtlTblDTO.setContent_title(entry.getTitle());			// 글타이틀
	            contentDtlTblDTO.setContent_title_link(entry.getLink());		// 글타이틀링크
	            contentDtlTblDTO.setContent_desc(entry.getDescription().getValue());	// 글내용
	            contentDtlTblDTO.setAuthor_nm(entry.getAuthor());				// 글작성자
	            
	            // 원 컨텐트 생성일이 존재하지 않을때는 임의로 날짜를 설정한다.
	            if (entry.getPublishedDate() != null)
	            {
	            	try
	            	{
	            		

	            		// 국내 RSS 사이트중 표준을 지키지않는 사이트 들이 존재해서 getPublishedDate 에 날짜 정규식이 아닌 문자열나열형식 20100101 등으로 설정한것이 있는데
	            		// 그에 대한 보완이 추가 필요함.
            		 
	            		if ( entry.getPublishedDate().toString().length() == 14 ||  entry.getPublishedDate().toString().length() == 8  )	// 정규날짜 형식이 아닌 스트링형식일때
	            		{
	            			if (log.isDebugEnabled())  log.debug("날짜형식 문자");
	            			contentDtlTblDTO.setOrg_create_dt(entry.getPublishedDate().toString());
	            		}
	            		else
	            		{
	            			if (log.isDebugEnabled())  log.debug("날짜형식 정규");
	            			contentDtlTblDTO.setOrg_create_dt(DateTime.format(entry.getPublishedDate(),"yyyyMMddHHmmss"));
	            		}
		            	
	            		log.debug("변경후 문자:" + contentDtlTblDTO.getOrg_create_dt() );
	            		
		            	if (!DateTime.isValid(contentDtlTblDTO.getOrg_create_dt(), "yyyyMMddHHmmss"))
		            	{
		            		// 날짜형식에 맞지않을떄에는 기본 날짜로 변경한다.
		            		contentDtlTblDTO.setOrg_create_dt("20100101000000");
		            	}
	            	}
	            	catch (Exception e)
	            	{
	            		// 예외가 발생하면 기본날짜로 처리한다.
	            		e.printStackTrace();
	            		contentDtlTblDTO.setOrg_create_dt("20100101000000");
	            	}
	            }
	            else
	            {
	            	contentDtlTblDTO.setOrg_create_dt("20100101000000");
	            }
	            
	            log.debug("contentDtlTblDTO.getOrg_create_dt():" + contentDtlTblDTO.getOrg_create_dt() );
	            
	            /*카테고리*/
	            StringBuffer cate = new StringBuffer();
	            if(entry.getCategories()!=null && entry.getCategories().size()>0)
	            {
	            	for(int ii=0,jj=entry.getCategories().size(); ii<jj; ii++)
	            	{
	            		 SyndCategoryImpl ss = (SyndCategoryImpl)entry.getCategories().get(ii);
	            		 categoryList[ii] =  ss.getName().replaceAll(" ","");
	            	}
	            }
	            
	            contentDtlTblDTO.setCategorieList(categoryList);
	            contentDtlResult.add(contentDtlTblDTO);
	        }
	        
	        // 해당 소스의 원본을 별도로 보관한다.
	        contentSourceParam.getContentCollect().setRow_data(entries.toString());
	        
//	        if (log.isDebugEnabled())
//	        {
//	        	log.debug("RSS원본:" +  entries.toString());
//	        	throw new Exception ("강제종료");
//
//	        }
		}
        catch (Exception e)
        { 
        	e.printStackTrace();
        	throw e;
        }
		
        if (log.isDebugEnabled())
        {
        	log.debug(contentSourceParam.getRss2_url() + " :" + contentDtlResult.size()+ "건 추출");
        }
		
		return contentDtlResult;
		
	}
	
}