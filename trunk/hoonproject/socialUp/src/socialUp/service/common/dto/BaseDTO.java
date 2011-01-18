package socialUp.service.common.dto;



public class BaseDTO
{
	private String idx;					// 조회 게시판 특정 primary key값 이후것을 조회할때
	private int choicePageNum;			// 선택된 페이지 번호
	private int pageRowCount=10;			// 리스트 나오는  row 수 
	private int pageBarCount=10;		// 페이지바에 나오는 페이지 표시수량
	private int allRowNum;				// 리스트의 전체row객수
	private String isPaging = "";     	    // 페이징 여부
	private int startRowNum;			// limit 시작 
	private int endRowNum;				// limit 종료
	

	private String rownum;					// 조회된 게시물의 순서
	
	private String retCode    ="0";		// 처리결과 코드	0:성공    -1 :실패
	private String retDetCode ="0";  // -1000 order_mem_join_passwd값이 틀려서 content_join_mem 등록불가
	
	
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public int getChoicePageNum() {
		return choicePageNum;
	}
	public void setChoicePageNum(int choicePageNum) 
	{
		
		this.choicePageNum = choicePageNum;
		
		// 선택된 페이지 번호를 이용해서 조회될 시작 row를 산출한다.
		calcPage();
	}
	public int getPageRowCount() {
		return pageRowCount;
	}
	public void setPageRowCount(int pageRowCount) {
		this.pageRowCount = pageRowCount;
	}
	public int getPageBarCount() {
		return pageBarCount;
	}
	public void setPageBarCount(int pageBarCount) {
		this.pageBarCount = pageBarCount;
	}
	public String getIsPaging() {
		return isPaging;
	}
	public void setIsPaging(String isPaging) {
		this.isPaging = isPaging;
	}
	
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	
	public int getStartRowNum() {
		return startRowNum;
	}
	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}
	public int getEndRowNum() {
		return endRowNum;
	}
	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}
	
	public int getAllRowNum() {
		return allRowNum;
	}
	public void setAllRowNum(int allRowNum) {
		this.allRowNum = allRowNum;
	}
	
	/*public void calcPage()
	{
		startRowNum = ( choicePageNum /(pageBarCount+1) )* pageRowCount * pageBarCount;
		endRowNum= (choicePageNum / (pageBarCount+1)+1)* pageRowCount * pageBarCount;
	}
	*/
	
	public void calcPage()
	{
		startRowNum = (choicePageNum-1) * pageRowCount ;
		endRowNum= choicePageNum * pageRowCount -1 ;
	}
	

	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetDetCode() {
		return retDetCode;
	}
	public void setRetDetCode(String retDetCode) {
		this.retDetCode = retDetCode;
	}
	
	
}