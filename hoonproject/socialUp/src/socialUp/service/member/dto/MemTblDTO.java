package socialUp.service.member.dto;



import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import socialUp.service.common.dto.BaseDTO;


public class MemTblDTO extends BaseDTO
{

	String mt_no;
	String mem_id;
	String mem_nm;
	String passwd;
	String relation_kind;
	String create_dt;
	String create_ip;
	String loginYn;
	String update_dt;
	String update_ip;
	
	
	
	public String getMt_no() {
		return mt_no;
	}
	public void setMt_no(String mt_no) {
		this.mt_no = mt_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_nm() {
		return mem_nm;
	}
	public void setMem_nm(String mem_nm) {
		this.mem_nm = mem_nm;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getRelation_kind() {
		return relation_kind;
	}
	public void setRelation_kind(String relation_kind) {
		this.relation_kind = relation_kind;
	}
	public String getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(String create_dt) {
		this.create_dt = create_dt;
	}
	public String getCreate_ip() {
		return create_ip;
	}
	public void setCreate_ip(String create_ip) {
		this.create_ip = create_ip;
	}
	public String getLoginYn() {
		return loginYn;
	}
	public void setLoginYn(String loginYn) {
		this.loginYn = loginYn;
	}
	public String getUpdate_dt() {
		return update_dt;
	}
	public void setUpdate_dt(String update_dt) {
		this.update_dt = update_dt;
	}
	public String getUpdate_ip() {
		return update_ip;
	}
	public void setUpdate_ip(String update_ip) {
		this.update_ip = update_ip;
	}

	
}