package socialUp.service.content.dto;



import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import socialUp.service.common.dto.BaseDTO;
import socialUp.service.member.dto.MemTblDTO;


public class UploadFilesDTO extends BaseDTO
{
	private String  uf_id;
	private String	cd_no;
	private String	file_name;
	private String	file_path;
	private String	file_kind;
	private String	file_size;
	private String	use_yn;
	private String	create_dt;
	private String	create_no;
	private String	fileFullName;		// 경로 파일명 포함된 파일명
	
	public String getUf_id() {
		return uf_id;
	}
	public void setUf_id(String uf_id) {
		this.uf_id = uf_id;
	}
	public String getCd_no() {
		return cd_no;
	}
	public void setCd_no(String cd_no) {
		this.cd_no = cd_no;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_kind() {
		return file_kind;
	}
	public void setFile_kind(String file_kind) {
		this.file_kind = file_kind;
	}
	public String getFile_size() {
		return file_size;
	}
	public void setFile_size(String file_size) {
		this.file_size = file_size;
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
	public String getFileFullName() {
		return fileFullName;
	}
	public void setFileFullName(String fileFullName) {
		this.fileFullName = fileFullName;
	}
	
}