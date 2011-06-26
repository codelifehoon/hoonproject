package socialUp.service.content.dto;



import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import socialUp.service.common.dto.BaseDTO;
import socialUp.service.member.dto.MemTblDTO;


public class ContentDtlCommentDTO extends BaseDTO
{
	private String cdc_no;
	private String cd_no;
	private String comment;
	private String use_yn;
	private String mt_no;
	private String create_dt;
	private String create_no;
	private String update_dt;
	private String update_no;
	private String create_ip;
	private String update_ip;
	private String passwd;
	
	
	private String cdc_no_gt;	// cdc_no_gt보다 큰값을 가져올경우
	private String newFlag;
	
	private MemTblDTO	memTblDTO;
	
	public String getCdc_no() {
		return cdc_no;
	}
	public void setCdc_no(String cdc_no) {
		this.cdc_no = cdc_no;
	}
	public String getCd_no() {
		return cd_no;
	}
	public void setCd_no(String cd_no) {
		this.cd_no = cd_no;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getMt_no() {
		return mt_no;
	}
	public void setMt_no(String mt_no) {
		this.mt_no = mt_no;
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
	public String getCdc_no_gt() {
		return cdc_no_gt;
	}
	public void setCdc_no_gt(String cdc_no_gt) {
		this.cdc_no_gt = cdc_no_gt;
	}
	public MemTblDTO getMemTblDTO() {
		return memTblDTO;
	}
	public void setMemTblDTO(MemTblDTO memTblDTO) {
		this.memTblDTO = memTblDTO;
	}
	public String getNewFlag() {
		return newFlag;
	}
	public void setNewFlag(String newFlag) {
		this.newFlag = newFlag;
	}

	public String getCreate_ip() {
		return create_ip;
	}
	public void setCreate_ip(String create_ip) {
		this.create_ip = create_ip;
	}
	public String getUpdate_ip() {
		return update_ip;
	}
	public void setUpdate_ip(String update_ip) {
		this.update_ip = update_ip;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}