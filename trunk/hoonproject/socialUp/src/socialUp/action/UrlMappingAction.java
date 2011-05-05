package socialUp.action;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.apache.log4j.Logger;

import socialUp.action.main.BaseActionSupport;
import socialUp.common.DaoFactory;
import socialUp.service.content.dao.ContentTitleListTblDAO;
import socialUp.service.content.dao.ContentTitleListTblDAOImpl;
import socialUp.service.content.dto.ContentTitleTblDTO;

public class UrlMappingAction extends BaseActionSupport 
{


	public Logger log = Logger.getLogger(this.getClass());

	
	private List<ContentTitleTblDTO> 	contentTitleList 	= null;
	
	
	
	public List<ContentTitleTblDTO> getContentTitleList() {
		return contentTitleList;
	}

	public void setContentTitleList(List<ContentTitleTblDTO> contentTitleList) {
		this.contentTitleList = contentTitleList;
	}



	
	/**
	 * 컨텐츠를 등록할  화면 step1
	 * 
	 * @return
	 * @throws Exception
	 */ 
	public String aliasMapping() throws Exception 
	{
		
		String returnVal = "DEFAULT";

		log.debug("aliasMapping 시작");
		String aliasStr = this.getRequest().getRequestURI().replaceAll("/go/", "");
		aliasStr = URLDecoder.decode(aliasStr,"UTF-8");
		if ("".equals(aliasStr)) return returnVal;
		
		
		ContentTitleTblDTO  conTitleParam 		=  new ContentTitleTblDTO();
		ContentTitleListTblDAO contentTitleDao =  (ContentTitleListTblDAOImpl)DaoFactory.createDAO(ContentTitleListTblDAOImpl.class);
		
		contentTitleDao.initSql();
		conTitleParam.setTitle_short_name(aliasStr);
		this.contentTitleList =  contentTitleDao.selectContentTitleListTbl(conTitleParam);
		
		return  returnVal;
	
	}
	

		
}
