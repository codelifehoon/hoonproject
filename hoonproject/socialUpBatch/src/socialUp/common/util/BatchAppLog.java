package socialUp.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import socialUp.common.util.CmnUtil;
import socialUp.common.util.EDate;

public class BatchAppLog {
	protected String dbUrl 	= null;
	protected String dbId	= null;
	protected String dbPwd 	= null;

	public BatchAppLog(String dbUrl, String dbId, String dbPwd) {
		this.dbUrl 	= dbUrl; 
		this.dbId 	= dbId; 
		this.dbPwd 	= dbPwd; 
	}

	public boolean isRunning(long batchNo) {
		boolean isRunning = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer buff = new StringBuffer();
		buff.append("	SELECT 	COUNT(0) AS CNT  	\n");
		buff.append("	FROM 	SY_BATCH_APP_LIST  	\n");
		buff.append("	WHERE 	BATCH_NO = ?  		\n");
		buff.append("	AND		END_DT IS NULL    	\n");

		try {
			conn = DBHandler.getConnection(dbUrl,dbId,dbPwd);
			if (conn == null) {
				System.out.println("Connection is NULL !!!");
				return isRunning;
			}
			
			pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, String.valueOf(batchNo));

			long cnt = 0;
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getLong("CNT");
			}
			
			isRunning = (cnt > 0);
			
			DBHandler.closeDBResource(rs,pstmt);
			DBHandler.closeDBResource(conn);
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				DBHandler.closeDBResource(rs,pstmt);
				DBHandler.closeDBResource(conn);
			} catch (Exception e) {}
		}
		
		return isRunning;
	}
	
	/**
	 * 배치로그 시작
	 * @param batchNo
	 * @param errMsg
	 */
	public void LogStart(long batchNo, String errMsg) {
		if (batchNo <= 0) {
			System.out.println("batchNo is NULL !!!");
			return;
		}
		
		if (CmnUtil.isEmpty(errMsg)) {
			errMsg = "";
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer buff = new StringBuffer();
		buff.append("	BEGIN                                 	  \n ");	// 
		buff.append("		SP_SY_BATCH_APP_START(?,?);           \n ");	// 
		buff.append("	END;																	\n ");	// 

		try {
			conn = DBHandler.getConnection(dbUrl,dbId,dbPwd);
			if (conn == null) {
				System.out.println("Connection is NULL !!!");
				return;
			}
			
			pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, String.valueOf(batchNo));
			pstmt.setString(2, errMsg);

			pstmt.execute();
			
			DBHandler.closeDBResource(rs,pstmt);
			DBHandler.closeDBResource(conn);
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				DBHandler.closeDBResource(rs,pstmt);
				DBHandler.closeDBResource(conn);
			} catch (Exception e) {}
		}
	}
	
	/**
	 * 배치로그 종료
	 * @param batchNo 배치번호
	 * @param sqlCd SQLCODE
	 * @param sqlMsg SQLERRM
	 * @param haltPos 발생위치
	 * @param errYn 오류여부
	 * @param errMsg 오류메시지
	 * @param smsMsg 오류발생시 보내는 SMS 메시지
	 */
	public void LogEnd(long batchNo, String sqlCd, String sqlMsg, String haltPos, String errYn, String errMsg, String smsMsg) {
		if (batchNo <= 0) {
			System.out.println("batchNo is NULL !!!");
			return;
		}
		
		if (CmnUtil.isEmpty(sqlCd)) {
			sqlCd = "0";
		}

		if (CmnUtil.isEmpty(sqlMsg)) {
			sqlMsg = "";
		}

		if (CmnUtil.isEmpty(haltPos)) {
			haltPos = "";
		}

		if (CmnUtil.isEmpty(errYn)) {
			errYn = "N";
		}
		
		if (CmnUtil.isEmpty(errMsg)) {
			errMsg = "";
		}

		if (smsMsg == null) {
			smsMsg = "";
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer buff = new StringBuffer();
		buff.append("	BEGIN                                 	  \n ");	// 
		buff.append("		SP_SY_BATCH_APP_END(                  \n ");	// 
		buff.append("			  ?                               \n ");	// p_batch_no	IN NUMBER		  
		buff.append("			, ?                               \n ");	// p_sql_cd		IN VARCHAR2	  
		buff.append("			, ?                               \n ");	// p_sql_msg		IN VARCHAR2	
		buff.append("			, ?                               \n ");	// p_halt_pos	IN VARCHAR2	  
		buff.append("			, ?                               \n ");	// p_err_yn		IN VARCHAR2	  
		buff.append("			, ?                               \n ");	// p_err_msg		IN VARCHAR2	
		buff.append("			, ?);                             \n ");	// p_sms_msg		IN VARCHAR2	
		buff.append("	END;																	\n ");	// 

		try {
			conn = DBHandler.getConnection(dbUrl,dbId,dbPwd);
			if (conn == null) {
				System.out.println("Connection is NULL !!!");
				return;
			}
			
			pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, String.valueOf(batchNo));
			pstmt.setString(2, sqlCd);
			pstmt.setString(3, sqlMsg);
			pstmt.setString(4, haltPos);
			pstmt.setString(5, errYn);
			pstmt.setString(6, errMsg);
			pstmt.setString(7, smsMsg);

			pstmt.execute();
			
			DBHandler.closeDBResource(rs,pstmt);
			DBHandler.closeDBResource(conn);
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				DBHandler.closeDBResource(rs,pstmt);
				DBHandler.closeDBResource(conn);
			} catch (Exception e) {}
		}
	}
	
	
	/**
	 * 배치 로그 기록하기
	 * @param batchNo 배치번호
	 * @param bgnDt 시작일시
	 * @param endDt 종료일시
	 * @param sqlCd SQLCODE
	 * @param sqlMsg SQLERRM
	 * @param haltPos 발생위치
	 * @param errYn 오류여부
	 * @param errMsg 오류메시지
	 * @param smsMsg 오류발생시 보내는 SMS 메시지
	 */
	public void LogPrint(long batchNo, Date bgnDt, Date endDt, String sqlCd, String sqlMsg, String haltPos, String errYn, String errMsg, String smsMsg) {
		if (batchNo <= 0) {
			System.out.println("batchNo is NULL !!!");
			return;
		}
		
		if (bgnDt == null) {
			bgnDt = EDate.today();
		}

		if (endDt == null) {
			endDt = EDate.today();
		}

		if (CmnUtil.isEmpty(sqlCd)) {
			sqlCd = "0";
		}

		if (CmnUtil.isEmpty(sqlMsg)) {
			sqlMsg = "";
		}

		if (CmnUtil.isEmpty(haltPos)) {
			haltPos = "";
		}

		if (CmnUtil.isEmpty(errYn)) {
			errYn = "N";
		}
		
		if (CmnUtil.isEmpty(errMsg)) {
			errMsg = "";
		}

		if (smsMsg == null) {
			smsMsg = "";
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer buff = new StringBuffer();
		buff.append("	BEGIN                                 	  \n ");	// 
		buff.append("		SP_SY_BATCH_APP_LOG(                  \n ");	// 
		buff.append("			  ?                               \n ");	// p_batch_no	IN NUMBER		  
		buff.append("			, to_date(?,'yyyymmddhh24miss')   \n ");	// p_bgn_dt		IN DATE			  
		buff.append("			, to_date(?,'yyyymmddhh24miss')   \n ");	// p_end_dt		IN DATE			  
		buff.append("			, ?                               \n ");	// p_sql_cd		IN VARCHAR2	  
		buff.append("			, ?                               \n ");	// p_sql_msg		IN VARCHAR2	
		buff.append("			, ?                               \n ");	// p_halt_pos	IN VARCHAR2	  
		buff.append("			, ?                               \n ");	// p_err_yn		IN VARCHAR2	  
		buff.append("			, ?                               \n ");	// p_err_msg		IN VARCHAR2	
		buff.append("			, ?);                             \n ");	// p_sms_msg		IN VARCHAR2	
		buff.append("	END;																	\n ");	// 

		try {
			conn = DBHandler.getConnection(dbUrl,dbId,dbPwd);
			if (conn == null) {
				System.out.println("Connection is NULL !!!");
				return;
			}
			
			pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, String.valueOf(batchNo));
			pstmt.setString(2, EDate.format(bgnDt, EDate.yyyyMMddHHmmss));
			pstmt.setString(3, EDate.format(endDt, EDate.yyyyMMddHHmmss));
			pstmt.setString(4, sqlCd);
			pstmt.setString(5, sqlMsg);
			pstmt.setString(6, haltPos);
			pstmt.setString(7, errYn);
			pstmt.setString(8, errMsg);
			pstmt.setString(9, smsMsg);

			pstmt.execute();
			
			DBHandler.closeDBResource(rs,pstmt);
			DBHandler.closeDBResource(conn);
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				DBHandler.closeDBResource(rs,pstmt);
				DBHandler.closeDBResource(conn);
			} catch (Exception e) {}
		}
	}
	
	/**
	 * 배치 SMS 보내기
	 * 배치 실행 결과와 상관없이 SMS 보내고자 할때 사용함
	 * 해당 배치 관계자 전원에게 SMS 발송됨
	 * @param batchNo
	 * @param smsMsg
	 */
	public void sendBatchSMS(long batchNo, String smsMsg) {
		//LogPrint(batchNo, null, null, "0", "N/A", "N/A", "Y", "SMS 발송을 위한 로그", smsMsg);
		
		if (smsMsg == null || smsMsg.length() < 1) {
			System.out.println("보낼 메시지가 없습니다.");
			return;
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer buff = new StringBuffer();
		buff.append("	BEGIN                                 	  \n "); 
		buff.append("		SP_SY_BATCH_APP_SMS(               	  \n "); 
		buff.append("			  ?                               \n ");		  
		buff.append("			, ?                               \n ");		  
		buff.append("			);                             	  \n ");	
		buff.append("	END;																	\n ");	// 

		try {
			conn = DBHandler.getConnection(dbUrl,dbId,dbPwd);
			if (conn == null) {
				System.out.println("Connection is NULL !!!");
				return;
			}
			
			pstmt = conn.prepareStatement(buff.toString());
			pstmt.setLong(1, batchNo);
			pstmt.setString(2, smsMsg);

			pstmt.execute();
			
			DBHandler.closeDBResource(rs,pstmt);
			DBHandler.closeDBResource(conn);
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				DBHandler.closeDBResource(rs,pstmt);
				DBHandler.closeDBResource(conn);
			} catch (Exception e) {}
		}
	}

	/**
	 * SMS 문자 보내기
	 * 배치와 상관없이 특정인에게 SMS를 보낼때 사용
	 * @param phoneNo 전화번호
	 * @param smsMsg 메시지
	 * @param dt 예약날짜
	 */
	public void sendSMS(String phoneNo, String smsMsg, Date dt) {
		if (phoneNo == null || phoneNo.length() < 1) {
			System.out.println("전화번호가 없습니다.");
			return;
		}
		
		if (smsMsg == null || smsMsg.length() < 1) {
			System.out.println("보낼 메시지가 없습니다.");
			return;
		}
		
		if (dt == null) {
			dt = new Date();
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer buff = new StringBuffer();
		buff.append("	BEGIN                                 	  \n "); 
		buff.append("		SP_SY_SEND_SMS(                  	  \n "); 
		buff.append("			  ?                               \n ");		  
		buff.append("			, ?                               \n ");		  
		buff.append("			, to_date(?,'yyyymmddhh24miss')   \n ");			  
		buff.append("			);                             	  \n ");	
		buff.append("	END;																	\n ");	// 

		try {
			conn = DBHandler.getConnection(dbUrl,dbId,dbPwd);
			if (conn == null) {
				System.out.println("Connection is NULL !!!");
				return;
			}
			
			pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, phoneNo);
			pstmt.setString(2, smsMsg);
			pstmt.setString(3, EDate.format(dt, EDate.yyyyMMddHHmmss));

			pstmt.execute();
			
			DBHandler.closeDBResource(rs,pstmt);
			DBHandler.closeDBResource(conn);
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				DBHandler.closeDBResource(rs,pstmt);
				DBHandler.closeDBResource(conn);
			} catch (Exception e) {}
		}
	}
	
	/**
	 * 배치 EMAIL 보내기
	 * 배치 실행 결과와 상관없이 EMAIL을 보내고자 할때 사용함
	 * Email Solution을 이용하여 보냄
	 * 
	 * @param batchNo - 배치 번호
	 * @param title - 보낼 메시지 제목
	 * @param text - 보낼 메시지 본문
	 * @param p_send_date - 보낼날짜(예약) : null 일 경우 즉시 발송
	 */
	public void sendBatchEmail( long batchNo, String title, String text, Date sendDate ) {
		
		if ( title == null || title.length() < 1 
				|| text == null || text.length() < 1 ) {
			System.out.println("보낼 메시지가 없습니다.");
			return;
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer buff = new StringBuffer();
		buff.append("	BEGIN										\n ");
		buff.append("		SP_SY_BATCH_APP_EMAIL(					\n ");
		buff.append("			  ?									\n ");
		buff.append("			, ?									\n ");
		buff.append("			, ?									\n ");
		buff.append("			, ?									\n ");
		buff.append("			);									\n ");
		buff.append("	END;										\n ");

		try {
			conn = DBHandler.getConnection(dbUrl,dbId,dbPwd);
			if (conn == null) {
				System.out.println("Connection is NULL !!!");
				return;
			}
			
			pstmt = conn.prepareStatement(buff.toString());
			pstmt.setLong(1, batchNo);
			pstmt.setString(2, title);
			pstmt.setString(3, text);
			pstmt.setString(4, EDate.format(sendDate, EDate.yyyyMMddHHmmss));

			pstmt.execute();

			DBHandler.closeDBResource(rs,pstmt);
			DBHandler.closeDBResource(conn);
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				DBHandler.closeDBResource(rs,pstmt);
				DBHandler.closeDBResource(conn);
			} catch (Exception e) {}
		}
	}
	
} // end of class
