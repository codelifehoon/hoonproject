
package socialUp.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import socialUp.service.common.dto.BaseDTO;


public class ValidationUtil 
{

	/**
	 * 	이메일 유효성 검증
	 * @param data
	 * @return
	 */
	public static BaseDTO emailV(String data)
	{
		BaseDTO baseDTO = new BaseDTO();
		


		Pattern p = Pattern.compile("^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$");
		Matcher m = p.matcher(data);

		 if ( !m.matches())
		{
			 baseDTO.setRetCode("-1");
			 baseDTO.setRetMsg("요효한 e-mail 주소가 아닙니다.");
		}
		
		return baseDTO;		
	}
	
	/**
	 * 	자릿수 검증
	 * @param data
	 * @return
	 */
	public static BaseDTO lenghV(String data,int min,int max)
	{
		BaseDTO baseDTO = new BaseDTO();
		

		if (data == null)
		{
			 baseDTO.setRetCode("-1");
			 baseDTO.setRetMsg("데이터값이 없습니다.");
		}
		else if (data != null && ( data.length() < min ||   data.length() > max) )
		{
			 baseDTO.setRetCode("-1");
			 baseDTO.setRetMsg("글자의 자릿수는 " + min + "보다크고 " + max + "보다 작아야 합니다.");
		}
		
		return baseDTO;		
	}
	
} // end of class