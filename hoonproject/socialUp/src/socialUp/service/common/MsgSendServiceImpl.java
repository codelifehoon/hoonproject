package socialUp.service.common;


import java.io.UnsupportedEncodingException;

import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import socialUp.service.common.dto.MsgInfoDTO;
import socialUp.service.content.dto.ContentBranchDTO;
import socialUp.service.content.dto.ContentDtlCommentDTO;
import socialUp.service.content.dto.ContentDtlTblDTO;
import socialUp.service.content.dto.ContentJoinMemDTO;
import socialUp.service.content.dto.ContentSourceTblDTO;
import socialUp.service.content.dto.ContentTitleTblDTO;
import socialUp.service.content.dto.SearchDTO;
import org.apache.log4j.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 




public class MsgSendServiceImpl implements MsgSendService 
{

	public Logger log = Logger.getLogger(this.getClass());
	
	MsgInfoDTO msgInfo;
	String host = "";
	String port = "";
	boolean starttlsEnable = true;
	
	
	/**
	 * 메세지 전송에 필요한 각종값들 전달
	 * 
	 * @param msgInfo
	 * @throws Exception
	 */
	public void setMsgParam(MsgInfoDTO msgInfo) throws Exception
	{
		this.msgInfo =  msgInfo;
	}
	
	/**
	 * 메세지 전송 설정된값 리턴
	 * 
	 * @return
	 * @throws Exception
	 */
	public MsgInfoDTO getMsgParam() throws Exception
	{
		return this.msgInfo;
	}
	
	
	/**
	 * 
	 * 설정된값을 이용해서 메세지 전송
	 * 
	 * @throws Exception
	 */
	public void send() throws Exception
	{ 
		// 참고소스 http://blog.kangwoo.kr/71
		try 
		{
        Properties props = new Properties();
        props.put("mail.transport.protocol", this.msgInfo.getProtocol());
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", String.valueOf(port));
        Authenticator authenticator = null;


        if (StringUtils.isNotBlank(this.msgInfo.getUserName())) 
        {

            props.put("mail.smtp.auth", "true");
            authenticator =  new SMTPAuthenticator(this.msgInfo.getUserName(), this.msgInfo.getPassword());
        }
        if (this.starttlsEnable) 
        {
            props.put("mail.smtp.starttls.enable", Boolean.toString(this.starttlsEnable));  
        }
        
        Session session = Session.getInstance(props, authenticator);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(this.msgInfo.getFromAddress(), this.msgInfo.getFromName()));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.msgInfo.getToAddress(), this.msgInfo.getToName()));
        message.setSubject(this.msgInfo.getSubject());
        message.setContent(this.msgInfo.getContent(), this.msgInfo.getType());
        Transport.send(message);
        

		} catch (UnsupportedEncodingException e) 
		{

			log.debug("메일발송실패 :UnsupportedEncodingException ");


	    } catch (MessagingException e) 
	    {
	
	    	log.debug("메일발송실패 :MessagingException ");
	    }
		
	}
	
	class SMTPAuthenticator extends Authenticator 
	{
        PasswordAuthentication passwordAuthentication;
        SMTPAuthenticator(String userName, String password) 
        {
        	passwordAuthentication = new PasswordAuthentication(userName, password);
        }
        public PasswordAuthentication getPasswordAuthentication() 
        {
            return passwordAuthentication;
        }
    }

	
	
}
