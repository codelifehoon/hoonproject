package socialUp.service.member;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import socialUp.service.member.dto.MemTblDTO;

public interface MemberService {


/**
	 * 회원가입시 회원가입 가능여부를 확인한다.
	 * 
	 * @param memtbldto
	 * @throws Exception 
	 */
public List<MemTblDTO> validateRegMemData(MemTblDTO memTblDTO) throws Exception;


/**
 * 회원가입처리한다.
 * 
 * @param memtbldto
 * @throws Exception 
 */
public long RegMemData(MemTblDTO memTblDTO) throws Exception;


/**
 * 로그인처리한다
 * 
 * @param memtbldto
 * @throws Exception 
 */
public List<MemTblDTO>  MemLogin(MemTblDTO memTblDTO) throws Exception;

		
}
