package socialUp.service.content.dto;



import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContentSourceTblDTO 
{
	String cs_no;
	String tt_no;
	String rss2_url;
	String source_kind;
	String source_dtl_kind;
	String source_owner_kind;
	String source_login_id;
	String reg_stat;
	String create_dt;
	String create_no;
	String update_dt;
	String update_no;
	String mt_no;
	String use_yn;
	
	public String getCs_no() {
		return cs_no;
	}
	public void setCs_no(String cs_no) {
		this.cs_no = cs_no;
	}
	public String getTt_no() {
		return tt_no;
	}
	public void setTt_no(String tt_no) {
		this.tt_no = tt_no;
	}
	public String getRss2_url() {
		return rss2_url;
	}
	public void setRss2_url(String rss2_url) {
		this.rss2_url = rss2_url;
	}
	public String getSource_kind() {
		return source_kind;
	}
	public void setSource_kind(String source_kind) {
		this.source_kind = source_kind;
	}
	public String getSource_dtl_kind() {
		return source_dtl_kind;
	}
	public void setSource_dtl_kind(String source_dtl_kind) {
		this.source_dtl_kind = source_dtl_kind;
	}
	public String getSource_owner_kind() {
		return source_owner_kind;
	}
	public void setSource_owner_kind(String source_owner_kind) {
		this.source_owner_kind = source_owner_kind;
	}
	public String getSource_login_id() {
		return source_login_id;
	}
	public void setSource_login_id(String source_login_id) {
		this.source_login_id = source_login_id;
	}
	public String getReg_stat() {
		return reg_stat;
	}
	public void setReg_stat(String reg_stat) {
		this.reg_stat = reg_stat;
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
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	
}