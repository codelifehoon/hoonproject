package socialUp.service.content.dto;



import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import socialUp.service.common.dto.BaseDTO;


public class ContentBranchDTO extends BaseDTO
{
	private String branch_no;
	private String tt_no;
	private String belong_tt_no;
	private String org_branch_no;
	private String mt_no;
	private String use_yn;
	private String create_dt;
	private String create_no;
	private String update_dt;
	private String update_no;
	
	private String orgBranchTtNo;		// 브랜치 생성할때  브랜치 목록을 가져올 대상 컨텐츠 타이틀 번호
	
	
	private String[] ttNos				= null;	// 여러개의 목록을 넘길때 사용
	private String[] orgBranchTtNos		= null; // 여러개의 목록을 넘길때 사용
	
	
	private ContentTitleTblDTO contentTitle;	// 브렌치로 가지고 있는 content title의 상세한정보  
	
	public String getBranch_no() {
		return branch_no;
	}
	public void setBranch_no(String branch_no) {
		this.branch_no = branch_no;
	}
	public String getTt_no() {
		return tt_no;
	}
	public void setTt_no(String tt_no) {
		this.tt_no = tt_no;
	}
	public String getBelong_tt_no() {
		return belong_tt_no;
	}
	public void setBelong_tt_no(String belong_tt_no) {
		this.belong_tt_no = belong_tt_no;
	}
	public String getOrg_branch_no() {
		return org_branch_no;
	}
	public void setOrg_branch_no(String org_branch_no) {
		this.org_branch_no = org_branch_no;
	}
	public String getMt_no() {
		return mt_no;
	}
	public void setMt_no(String mt_no) {
		this.mt_no = mt_no;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
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
	public ContentTitleTblDTO getContentTitle() {
		return contentTitle;
	}
	public void setContentTitle(ContentTitleTblDTO contentTitle) {
		this.contentTitle = contentTitle;
	}
	public String getOrgBranchTtNo() {
		return orgBranchTtNo;
	}
	public void setOrgBranchTtNo(String orgBranchTtNo) {
		this.orgBranchTtNo = orgBranchTtNo;
	}
	public String[] getTtNos() {
		return ttNos;
	}
	public void setTtNos(String[] ttNos) {
		this.ttNos = ttNos;
	}
	public String[] getOrgBranchTtNos() {
		return orgBranchTtNos;
	}
	public void setOrgBranchTtNos(String[] orgBranchTtNos) {
		this.orgBranchTtNos = orgBranchTtNos;
	}

}