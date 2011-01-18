package socialUp.service.content.dto;



import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import socialUp.service.common.dto.BaseDTO;


public class ContentJoinMemDTO  extends BaseDTO 
{
	private	String	tt_no;
	private	String	mt_no;
	private	String	stat;
	private	String	create_dt;
	private	String	create_no;
	private	String	update_dt;
	private	String	update_no;
	private	String	mt_grade;
	
	public String getTt_no() {
		return tt_no;
	}
	public void setTt_no(String tt_no) {
		this.tt_no = tt_no;
	}
	public String getMt_no() {
		return mt_no;
	}
	public void setMt_no(String mt_no) {
		this.mt_no = mt_no;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
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
	public String getMt_grade() {
		return mt_grade;
	}
	public void setMt_grade(String mt_grade) {
		this.mt_grade = mt_grade;
	}

	
}