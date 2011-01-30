package socialUp.service.content.dto;



import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import socialUp.service.common.dto.BaseDTO;
import socialUp.service.member.dto.MemTblDTO;


public class ContentDtlImgDTO extends BaseDTO
{
	private String cdi_no;
	private String cd_no;
	private String img_kind;
	private String img_url;
	private String create_dt;
	private String create_no;
	private String update_dt;
	private String update_no;
	private String thumbnail_url;
	
	public String getCdi_no() {
		return cdi_no;
	}
	public void setCdi_no(String cdi_no) {
		this.cdi_no = cdi_no;
	}
	public String getCd_no() {
		return cd_no;
	}
	public void setCd_no(String cd_no) {
		this.cd_no = cd_no;
	}
	public String getImg_kind() {
		return img_kind;
	}
	public void setImg_kind(String img_kind) {
		this.img_kind = img_kind;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
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
	public String getThumbnail_url() {
		return thumbnail_url;
	}
	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}

}