package socialUp.service.content.dto;



import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContentTblDTO 
{
	String ct_no;
	String tt_no;
	String cs_no;
	String content_title;
	String source_kind;
	String content_desc;
	String create_ip;
	String create_dt;
	String create_no;
	String update_dt;
	String update_no;
	
	public String getCt_no() {
		return ct_no;
	}
	public void setCt_no(String ct_no) {
		this.ct_no = ct_no;
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
}