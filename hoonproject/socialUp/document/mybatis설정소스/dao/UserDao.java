package dao;

import java.util.ArrayList;
import java.util.List;

import myBatis.MyBatisManager;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import vo.UserVO;

public class UserDao {
	
	public static SqlSessionFactory sqlMapper = MyBatisManager.getInstance();
	
	public List<UserVO> getUserList() {
		
		List<UserVO> list = new ArrayList<UserVO>();
		
		SqlSession session = sqlMapper.openSession();
		try {
			list = session.selectList("myBatis.mappers.UserMapper.getUserList");
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
}
