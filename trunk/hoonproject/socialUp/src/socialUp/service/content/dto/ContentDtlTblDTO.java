package socialUp.service.content.dto;



import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import socialUp.service.common.dto.BaseDTO;


public class ContentDtlTblDTO  extends BaseDTO 
{
	private String cd_no;
	private String tt_no;
	private String cs_no;
	private String mt_no;
	private String content_title;
	private String source_kind;
	private String content_desc;
	private String create_ip;
	private String create_dt;
	private String create_no;
	private String update_dt;
	private String update_no;
	private String[] categorieList;				// 블로그 카테고리 목록
	private String categories;					// 블로그 카테고리 목록
	
	private String content_title_link;
	private String org_create_dt;
	private String author_nm;
	private String use_yn;
	
	private String revers_index;				// 최근글을 보기 위한 인덱스설정 여부값
	private String cdc_cnt;
	private String hit_cnt;
	
	private String cdc_flag;						// set 할때 값이 +a 면   카운트를 +a 해주고  -a 면 -a 해준다. (게시물을 한번 읽을때 발생)
	private String hit_flag;						// set 할때 값이 +a 면   카운트를 +a 해주고  -a 면 -a 해준다. (comment를 생성/삭제할떄 발생)
	
	
	
	public String getCt_no() {
		return cd_no;
	}
	public void setCt_no(String ct_no) {
		this.cd_no = ct_no;
	}
	public String getTt_no() {
		return tt_no;
	}
	public void setTt_no(String tt_no) {
		this.tt_no = tt_no;
	}
	public String getCs_no() {
		return cs_no;
	}
	public void setCs_no(String cs_no) {
		this.cs_no = cs_no;
	}
	public String getContent_title() {
		return content_title;
	}
	public void setContent_title(String content_title) {
		this.content_title = content_title;
	}
	public String getSource_kind() {
		return source_kind;
	}
	public void setSource_kind(String source_kind) {
		this.source_kind = source_kind;
	}
	public String getContent_desc() {
		return content_desc;
	}
	public void setContent_desc(String content_desc) {
		this.content_desc = content_desc;
	}
	public String getCreate_ip() {
		return create_ip;
	}
	public void setCreate_ip(String create_ip) {
		this.create_ip = create_ip;
	}
	public String getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(String create_dt) {
		this.create_dt = create_dt;
	}
	public String getCreate_no() {
		return create_no;
	}
	public void setCreate_no(String create_no) {
		this.create_no = create_no;
	}
	public String getUpdate_dt() {
		return update_dt;
	}
	public void setUpdate_dt(String update_dt) {
		this.update_dt = update_dt;
	}
	public String getUpdate_no() {
		return update_no;
	}
	public void setUpdate_no(String update_no) {
		this.update_no = update_no;
	}

	public String getContent_title_link() {
		return content_title_link;
	}
	public void setContent_title_link(String content_title_link) {
		this.content_title_link = content_title_link;
	}
	public String getOrg_create_dt() {
		return org_create_dt;
	}
	public void setOrg_create_dt(String org_create_dt) {
		this.org_create_dt = org_create_dt;
	}
	public String getAuthor_nm() {
		return author_nm;
	}
	public void setAuthor_nm(String author_nm) {
		this.author_nm = author_nm;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getCd_no() {
		return cd_no;
	}
	public void setCd_no(String cd_no) {
		this.cd_no = cd_no;
	}
	public String[] getCategorieList() {
		return categorieList;
	}
	public void setCategorieList(String[] categorieList) 
	{
		this.categorieList = categorieList;
		
		if (categorieList == null) return ;
		
		String str = "";
		for (int i=0;i<categorieList.length;i++ )
		{
			if (i > 0) str +=  "·" + categorieList[i] ;
			else  str = categorieList[i] ;
		}
		setCategories(str);
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public String getMt_no() {
		return mt_no;
	}
	public void setMt_no(String mt_no) {
		this.mt_no = mt_no;
	}
	public String getRevers_index() {
		return revers_index;
	}
	public void setRevers_index(String revers_index) {
		this.revers_index = revers_index;
	}
	public String getCdc_cnt() {
		return cdc_cnt;
	}
	public void setCdc_cnt(String cdc_cnt) {
		this.cdc_cnt = cdc_cnt;
	}
	public String getHit_cnt() {
		return hit_cnt;
	}
	public void setHit_cnt(String hit_cnt) {
		this.hit_cnt = hit_cnt;
	}
	public String getCdc_flag() {
		return cdc_flag;
	}
	public void setCdc_flag(String cdc_flag) {
		this.cdc_flag = cdc_flag;
	}
	public String getHit_flag() {
		return hit_flag;
	}
	public void setHit_flag(String hit_flag) {
		this.hit_flag = hit_flag;
	}
}