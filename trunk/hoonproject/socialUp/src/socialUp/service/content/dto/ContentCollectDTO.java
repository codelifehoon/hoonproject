package socialUp.service.content.dto;



import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import socialUp.service.common.dto.BaseDTO;


public class ContentCollectDTO  extends BaseDTO 
{
	private String coll_no;
	private String cs_no;
	private String proc_step;
	private String row_data;
	private String create_dt;
	private String update_dt;
	
	String  proc_error_msg;
	
	public String getColl_no() {
		return coll_no;
	}
	public void setColl_no(String coll_no) {
		this.coll_no = coll_no;
	}
	public String getCs_no() {
		return cs_no;
	}
	public void setCs_no(String cs_no) {
		this.cs_no = cs_no;
	}
	public String getProc_stop() {
		return proc_step;
	}
	public void setProc_stop(String proc_stop) {
		this.proc_step = proc_stop;
	}
	public String getRow_data() {
		return row_data;
	}
	public void setRow_data(String row_data) {
		this.row_data = row_data;
	}
	public String getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(String create_dt) {
		this.create_dt = create_dt;
	}
	public String getProc_error_msg() {
		return proc_error_msg;
	}
	public void setProc_error_msg(String proc_error_msg) {
		this.proc_error_msg = proc_error_msg;
	}
	public String getUpdate_dt() {
		return update_dt;
	}
	public void setUpdate_dt(String update_dt) {
		this.update_dt = update_dt;
	}

}