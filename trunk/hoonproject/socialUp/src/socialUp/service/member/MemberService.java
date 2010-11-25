package socialUp.service.member;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import socialUp.service.member.dto.MemTblDTO;

public interface MemberService {

public SqlSession getSqlMap() ;

public void setSqlMap(SqlSession sqlMap) ;
	
/**
	 * 회원가입시 회원가입 가능여부를 확인한다.
	 * 
	 * @param memtbldto
	 * @throws Exception 
	 */
public List<MemTblDTO> validateRegMemData(MemTblDTO memTblDTO) throws Exception;




		
}
