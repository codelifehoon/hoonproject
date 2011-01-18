package socialUp.service.content.dto;



import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import socialUp.service.common.dto.BaseDTO;


public class ContentTitleTblDTO  extends BaseDTO 
{
	private String tt_no;
	private String title_name;
	private String title_kind;
	private String branch_conf_yn;
	private String mt_no;
	private String order_mem_open_yn;
	private String order_mem_join_yn;
	private String order_mem_join_metd;
	private String order_mem_join_passwd;
	private String create_dt;
	private String create_no;
	private String update_dt;
	private String update_no;
	private String use_yn;
	

	
	private List<ContentDtlTblDTO>  contentDtlList;	// 컨텐츠 등록목록
	private List<ContentBranchDTO>	contentBranchList;  	// 컨텐츠 타이틀에 소속된 브렌치 목록
	
	public String getTt_no() {
		return tt_no;
	}
	public void setTt_no(String tt_no) {
		this.tt_no = tt_no;
	}
	public String getTitle_name() {
		return title_name;
	}
	public void setTitle_name(String title_name) {
		this.title_name = title_name;
	}
	public String getTitle_kind() {
		return title_kind;
	}
	public void setTitle_kind(String title_kind) {
		this.title_kind = title_kind;
	}
	public String getBranch_conf_yn() {
		return branch_conf_yn;
	}
	public void setBranch_conf_yn(String branch_conf_yn) {
		this.branch_conf_yn = branch_conf_yn;
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
	public String getMt_no() {
		return mt_no;
	}
	public void setMt_no(String mt_no) {
		this.mt_no = mt_no;
	}
	public String getOrder_mem_open_yn() {
		return order_mem_open_yn;
	}
	public void setOrder_mem_open_yn(String order_mem_open_yn) {
		this.order_mem_open_yn = order_mem_open_yn;
	}
	public String getOrder_mem_join_yn() {
		return order_mem_join_yn;
	}
	public void setOrder_mem_join_yn(String order_mem_join_yn) {
		this.order_mem_join_yn = order_mem_join_yn;
	}
	public String getOrder_mem_join_passwd() {
		return order_mem_join_passwd;
	}
	public void setOrder_mem_join_passwd(String order_mem_join_passwd) {
		this.order_mem_join_passwd = order_mem_join_passwd;
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
	public List<ContentDtlTblDTO> getContentDtlList() {
		return contentDtlList;
	}
	public void setContentDtlList(List<ContentDtlTblDTO> contentDtlList) {
		this.contentDtlList = contentDtlList;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getOrder_mem_join_metd() {
		return order_mem_join_metd;
	}
	public void setOrder_mem_join_metd(String order_mem_join_metd) {
		this.order_mem_join_metd = order_mem_join_metd;
	}
	public List<ContentBranchDTO> getContentBranchList() {
		return contentBranchList;
	}
	public void setContentBranchList(List<ContentBranchDTO> contentBranchList) {
		this.contentBranchList = contentBranchList;
	}
	
	}