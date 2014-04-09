package com.zebra.batch.collect;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import socialUp.common.util.DBHandler;



/**
 * Java 배치를 위한 부모클래스
 * @author ZZ07237
 * @version 0.1
 */
public class BaseDaemon {
	public static final String CR = "\r";
	public static final String LF = "\n";
	public static final String CRLF = "\r\n";

	public static final String HOST_DEV = "dev";
	public static final String HOST_LOCAL = "local";
	public static final String HOST_STG = "stg";
	public static final String HOST_REAL = "batch";

	private final String CONFIG_LOCAL 	= "com/zebra/config/batch-config.properties";
	private final String CONFIG_DEV 	= "com/zebra/config/batch-config.properties";
	private final String CONFIG_STG 	= "com/zebra/config/batch-config.properties";
	private final String CONFIG_REAL 	= "com/zebra/config/batch-config.properties";

	
	public Logger log = Logger.getLogger(BaseDaemon.class);
	protected Properties prop = null;
	protected String dbDriver = null;
	protected String dbUrl = null;
	protected String dbId = null;
	protected String dbPwd = null;
	protected String sqlMapConfigList = null;
	protected String batchName = "";
	protected String batchID = "";
	protected long	batch_no = 0;

	protected ClassPathXmlApplicationContext	applicationContext;
	
	public BaseDaemon() {
		try {
			// log4j
			// socialUp 기본 project에 있는 설정이 먹도록 되어 있다 
			//DOMConfigurator.configure(Resources.getResourceURL("/socialUp/config/batch-log4j.xml"));

			String configFile = CONFIG_REAL;

			String hostName = System.getProperty("host.name", HOST_REAL);

			// DB 계정정보 config 호출
			if 		(HOST_LOCAL.equals(hostName)) 	configFile = CONFIG_LOCAL;
			else if (HOST_DEV.equals(hostName)) 	configFile = CONFIG_DEV;
			else if (HOST_STG.equals(hostName)) 	configFile = CONFIG_STG;
			else if (HOST_REAL.equals(hostName))	configFile = CONFIG_REAL;

			/* Properties 초기화 
			prop = new Properties();
			prop.load(Resources.getResourceAsStream(configFile));

			
			dbDriver	= prop.getProperty("sqlMap.driver");
			dbUrl 		= prop.getProperty("sqlMap.url");
			dbId 		= prop.getProperty("sqlMap.username");
			dbPwd		= prop.getProperty("sqlMap.password");

			// db 계정을 이용해서 DB 연결 정상여부 확인
			DBHandler.initDriver(dbDriver);

			log.info("----------------------------");
			log.info("dbDriver : " +dbDriver);
			log.info("dbUrl : " +dbUrl);
			log.info("dbId : " +dbId);
			log.info("dbPwd : " +dbPwd);
			log.info("----------------------------");*/

			
			applicationContext = new ClassPathXmlApplicationContext("com/zebra/batch/resource/spring-context-common.xml");
            applicationContext.start();

    
        	log.debug(this.toString() + " Load.");
        	
            
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static void initSqlMap() {
		String hostName = System.getProperty("host.name", HOST_REAL);

		String sqlMapFile = "com/zebra/config/mybatisConf.xml";

		if 		(HOST_LOCAL.equals(hostName)) 	sqlMapFile = "com/zebra/config/mybatisConf.xml";
		else if (HOST_DEV.equals(hostName)) 	sqlMapFile = "com/zebra/config/mybatisConf.xml";
		else if (HOST_STG.equals(hostName)) 	sqlMapFile = "com/zebra/config/mybatisConf.xml";
		else if (HOST_REAL.equals(hostName))	sqlMapFile = "com/zebra/config/mybatisConf.xml";

//		DBHandler.initSqlMap(sqlMapFile);
//		System.out.println("sqlMapFile = " + sqlMapFile);
	}


} // end of class
