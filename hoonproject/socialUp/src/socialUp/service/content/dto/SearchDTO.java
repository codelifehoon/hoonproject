package socialUp.service.content.dto;



import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import socialUp.service.common.dto.BaseDTO;


public class SearchDTO extends BaseDTO
{
	private String searchStr;
	private String searchType;			// 00 : 컨텐츠 제목 , 상세내용 모두 검색  01 : 컨텐츠 제목검색 , 02 :상세내용검색 
	private String[] searchStrArr;
	private String tt_no;				// content_title 단위로 검색을 할때 사용함
	private String realSearchStr;			// 실제검색될 검색문장생성된값
	
	
	public String getSearchStr() {
		return searchStr;
	}
	
	// 검색문자를 공백을 기준으로 분리
	public void setSearchStr(String searchStr) 
	{
		this.searchStr = searchStr;
		this.realSearchStr = "";
		
		String[] tempArray = searchStr.split(" ");
		for (int i=0;i<tempArray.length;i++)
		{
			tempArray[i]  = tempArray[i].trim();
			
			this.realSearchStr += tempArray[i] + "*";
		}
		
		this.searchStrArr = tempArray;
		
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String[] getSearchStrArr() {
		return searchStrArr;
	}
	public void setSearchStrArr(String[] searchStrArr) {
		this.searchStrArr = searchStrArr;
	}

	public String getTt_no() {
		return tt_no;
	}

	public void setTt_no(String tt_no) {
		this.tt_no = tt_no;
	}

	public String getRealSearchStr() {
		return realSearchStr;
	}

	public void setRealSearchStr(String realSearchStr) {
		this.realSearchStr = realSearchStr;
	}
	
}