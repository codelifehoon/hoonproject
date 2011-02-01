package socialUp.service.common;


import java.util.List;
import org.apache.ibatis.session.SqlSession;

import socialUp.service.common.dto.MsgInfoDTO;
import socialUp.service.content.dto.ContentBranchDTO;
import socialUp.service.content.dto.ContentDtlCommentDTO;
import socialUp.service.content.dto.ContentDtlTblDTO;
import socialUp.service.content.dto.ContentJoinMemDTO;
import socialUp.service.content.dto.ContentSourceTblDTO;
import socialUp.service.content.dto.ContentTitleTblDTO;
import socialUp.service.content.dto.SearchDTO;

public interface MsgSendService 
{
	
	/**
	 * 메세지 전송에 필요한 각종값들 전달
	 * 
	 * @param msgInfo
	 * @throws Exception
	 */
	public void setMsgParam(MsgInfoDTO msgInfo) throws Exception;
	
	/**
	 * 메세지 전송 설정된값 리턴
	 * 
	 * @return
	 * @throws Exception
	 */
	public MsgInfoDTO getMsgParam() throws Exception;
	
	
	/**
	 * 
	 * 설정된값을 이용해서 메세지 전송
	 * 
	 * @throws Exception
	 */
	public void send() throws Exception;
}
