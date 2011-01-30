package skt.tmall.daemon.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.ibatis.common.resources.Resources;

import skt.tmall.common.util.CmnUtil;

/**
 * @author need4spd, need4spd@cplanet.co.kr, 2010. 5. 26.
 *
 */
public class BatchAppSMS {
	private String dbUrl 	= null;
	private String dbId	= null;
	private String dbPwd 	= null;
	
	private Properties batchProp;
	
	private final String _PROP_FILE_PATH = "skt/tmall/daemon/browsing/config/browsing-batch-config.properties";
	
	public BatchAppSMS() {
		batchProp = getProperties();
		
		dbUrl = batchProp.getProperty("tmall.sqlMap.url");
        dbId = batchProp.getProperty("tmall.sqlMap.username");
        dbPwd = batchProp.getProperty("tmall.sqlMap.password");
	}

	public void sendSMS(long batchNo, String msg) {
		
		if (batchNo <= 0) {
			System.out.println("batchNo is NULL !!!");
			return;
		}
		
		if (CmnUtil.isEmpty(msg)) {
			msg = "";
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer buff = new StringBuffer();
		buff.append("	BEGIN                                 	  \n ");
		buff.append("		SP_SY_BATCH_APP_SMS(?,?);           \n ");
		buff.append("	END;																	\n ");
		
		try {
			conn = DBHandler.getConnection(dbUrl,dbId,dbPwd);
			if (conn == null) {
				System.out.println("Connection is NULL !!!");
				return;
			}
			
			pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, String.valueOf(batchNo));
			pstmt.setString(2, msg);

			pstmt.execute();
			
			DBHandler.closeDBResource(rs,pstmt);
			DBHandler.closeDBResource(conn);
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				DBHandler.closeDBResource(rs,pstmt);
				DBHandler.closeDBResource(conn);
			} catch (Exception e) {
				System.out.println("finally : " + e.toString());
			}
		}
	}
	
	private Properties getProperties()
    {
    	if(batchProp == null)
    	{
        	try{
        		batchProp = new Properties();
        		batchProp.load(Resources.getResourceAsStream(_PROP_FILE_PATH));
        	}catch(Exception e){
                System.out.println(e.toString());
        	}
        }

    	return batchProp;
    }
}
