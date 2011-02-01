package socialUp.service.common.dto;



/**
 * message 전송에 필요한 값에 대한 정의 (mail,sms,twitter , blog)
 * @author 장재훈
 *
 */
public class MsgInfoDTO 
{
	private String protocol;			// smtp
	private String type = ""; 			// text/html; charset=KSC5601,
	private String userName;
	private String password;
	private String subject;				// 메세지 제목
	private String content;				// 메세지 제목
	
	private String fromAddress;			//발신자 정보 
	private String fromName; 
	
	private String toAddress;			// 수신자정보
	private String toName;
	
	
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	
}