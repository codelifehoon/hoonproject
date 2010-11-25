package socialUp.common.mybatis;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class MyBatisManager 
{
	public static Logger logger = Logger.getLogger(MyBatisManager.class);
	
	public static SqlSessionFactory sqlMapper = null;
	
	//  SqlSessionFactory  리턴 메서드
	public static SqlSessionFactory getInstance(String resourceName){
		if(sqlMapper == null) {
			try {
				// 특정 conf파일을 지정해주지않으면 기본파일설정
				if (resourceName == null || "".equals(resourceName)) resourceName = "mybatisConf.xml";

				Reader reader = Resources.getResourceAsReader(resourceName);
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				reader.close();
				
			}catch(Exception e){
				
				logger.debug("SqlSessionFactory.getInstance 오류");
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}

	//  SqlSession 리턴 메서드
	public static SqlSession getInstanceSqlSession(String resourceName){
		if(sqlMapper == null) {
			try {
				// 특정 conf파일을 지정해주지않으면 기본파일설정
				if (resourceName == null || "".equals(resourceName))resourceName = "mybatisConf.xml";

				Reader reader = Resources.getResourceAsReader(resourceName);
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				reader.close();
				
			}catch(Exception e){
				
				logger.debug("SqlSessionFactory.getInstanceSqlSession 오류");
				e.printStackTrace();
			}
		}
		return sqlMapper.openSession();
	}
	
}
