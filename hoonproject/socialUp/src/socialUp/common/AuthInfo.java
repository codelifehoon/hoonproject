package socialUp.common;

import socialUp.common.util.CmnUtil;

/**
 * 
 * 회원이 로그인 되었을때 로그인 정보
 * 
 * @author 장재훈
 *
 */
public class AuthInfo 
{

	private String mem_id;
	private String mt_no;
	private String mem_nm;
	
	
	/**
	 * 회원 인증여부
	 * @return
	 */
	public boolean isAuth()
	{
		if ( CmnUtil.nvl(mt_no).length() > 0 && !"-1".equals(mt_no))	return true;
		return false;
			
	}
	
	public String getMem_id() {
		return mem_id;
	}


	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}


	public String getMt_no() {
		return mt_no;
	}


	public void setMt_no(String mt_no) {
		this.mt_no = mt_no;
	}


	public String getMem_nm() {
		return mem_nm;
	}


	public void setMem_nm(String mem_nm) {
		this.mem_nm = mem_nm;
	}
	

}
