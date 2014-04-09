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
	 * ��ġ�α� ����
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
	 * ��ġ�α� ����
	 * @param batchNo ��ġ��ȣ
	 * @param sqlCd SQLCODE
	 * @param sqlMsg SQLERRM
	 * @param haltPos �߻���ġ
	 * @param errYn �����
	 * @param errMsg ����޽���
	 * @param smsMsg ����߻�� ������ SMS �޽���
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
	 * ��ġ �α� ����ϱ�
	 * @param batchNo ��ġ��ȣ
	 * @param bgnDt �����Ͻ�
	 * @param endDt �����Ͻ�
	 * @param sqlCd SQLCODE
	 * @param sqlMsg SQLERRM
	 * @param haltPos �߻���ġ
	 * @param errYn �����
	 * @param errMsg ����޽���
	 * @param smsMsg ����߻�� ������ SMS �޽���
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
	 * ��ġ SMS ������
	 * ��ġ ���� ���� ������ SMS �������� �Ҷ� �����
	 * �ش� ��ġ ����� ���� SMS �߼۵�
	 * @param batchNo
	 * @param smsMsg
	 */
	public void sendBatchSMS(long batchNo, String smsMsg) {
		//LogPrint(batchNo, null, null, "0", "N/A", "N/A", "Y", "SMS �߼��� ���� �α�", smsMsg);
		
		if (smsMsg == null || smsMsg.length() < 1) {
			System.out.println("���� �޽����� ����ϴ�.");
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
	 * SMS ���� ������
	 * ��ġ�� ������ Ư���ο��� SMS�� ������ ���
	 * @param phoneNo ��ȭ��ȣ
	 * @param smsMsg �޽���
	 * @param dt ���೯¥
	 */
	public void sendSMS(String phoneNo, String smsMsg, Date dt) {
		if (phoneNo == null || phoneNo.length() < 1) {
			System.out.println("��ȭ��ȣ�� ����ϴ�.");
			return;
		}
		
		if (smsMsg == null || smsMsg.length() < 1) {
			System.out.println("���� �޽����� ����ϴ�.");
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
	 * ��ġ EMAIL ������
	 * ��ġ ���� ���� ������ EMAIL�� �������� �Ҷ� �����
	 * Email Solution�� �̿��Ͽ� ����
	 * 
	 * @param batchNo - ��ġ ��ȣ
	 * @param title - ���� �޽��� ����
	 * @param text - ���� �޽��� ����
	 * @param p_send_date - ������¥(����) : null �� ��� ��� �߼�
	 */
	public void sendBatchEmail( long batchNo, String title, String text, Date sendDate ) {
		
		if ( title == null || title.length() < 1 
				|| text == null || text.length() < 1 ) {
			System.out.println("���� �޽����� ����ϴ�.");
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
