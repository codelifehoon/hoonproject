package socialUp.action.test;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class ActionTest extends ActionSupport 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4261025391544710799L;

	public Logger logger = Logger.getLogger(this.getClass());
	
	private String commonVal;
	public String getCommonVal() {
		return commonVal;
	}

	public void setCommonVal(String commonVal) {
		this.commonVal = commonVal;
	}


	@Override
	public String execute() throws Exception 
	{
		
		System.out.println("execute실행");
		this.commonVal = "execute";
		logger.debug("log4j execute실행");
		
		
		return super.execute();
	}
	
	public String Select() throws Exception 
	{
		
		
		System.out.println("Select 실행");
		this.commonVal = "Select";
		logger.debug("log4j Select실행");
		
		  String resource = "mybatisConf.xml";
		  Reader reader;
		  try 
		  {
			   reader = Resources.getResourceAsReader(resource);
			   SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			   
			   SqlSession session = sqlMapper.openSession();

			   
			   try {
				   String blog = (String) session.selectOne("BlogMapper.selectTable", 322);
			       logger.debug("mysql 결과:" + blog);
			       
			   } finally 
			   {
			   session.close();
			   }
			  } catch (IOException e) 
		  {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		  }
		return "SUCCESS";
	}
	
	

}
