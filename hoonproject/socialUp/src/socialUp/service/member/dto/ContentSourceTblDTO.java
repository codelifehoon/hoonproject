package socialUp.service.member.dto;



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
	String remot_login_id;
	String remot_login_pw;
	String feed_back_url;
	String cert_key;
	String create_dt;
	String create_no;
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
	public String getRemot_login_id() {
		return remot_login_id;
	}
	public void setRemot_login_id(String remot_login_id) {
		this.remot_login_id = remot_login_id;
	}
	public String getRemot_login_pw() {
		return remot_login_pw;
	}
	public void setRemot_login_pw(String remot_login_pw) {
		this.remot_login_pw = remot_login_pw;
	}
	public String getFeed_back_url() {
		return feed_back_url;
	}
	public void setFeed_back_url(String feed_back_url) {
		this.feed_back_url = feed_back_url;
	}
	public String getCert_key() {
		return cert_key;
	}
	public void setCert_key(String cert_key) {
		this.cert_key = cert_key;
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
	}