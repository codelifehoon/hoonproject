package socialUp.service.content.dto;



import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContentTitleListTblDTO 
{
	String tt_no;
	String title_name;
	String title_kind;
	String branch_conf_yn;
	String mt_no;
	String order_mem_open_yn;
	String order_mem_join_yn;
	String order_mem_join_passwd;
	String create_dt;
	String create_no;
	String update_dt;
	String update_no;
	
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
	
	}