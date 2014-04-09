
package com.zebra.common.util;
import java.util.regex.Matcher;

import java.util.regex.Pattern;

import com.zebra.common.domain.BaseBO;



public class ValidationUtil 
{

	/**
	 * 	이메일 유효성 검증
	 * @param data
	 * @return
	 */
	public static BaseBO emailV(String data)
	{
		BaseBO baseBO = new BaseBO();
		


		Pattern p = Pattern.compile("^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$");
		Matcher m = p.matcher(data);

		 if ( !m.matches())
		{
			 baseBO.setRetCode("-1");
			 baseBO.setRetMsg("요효한 e-mail 주소가 아닙니다.");
		}
		
		return baseBO;		
	}
	
	/**
	 * 	자릿수 검증
	 * @param data
	 * @return
	 */
	public static BaseBO lenghV(String data,int min,int max)
	{
		BaseBO baseBO = new BaseBO();
		

		if (data == null)
		{
			baseBO.setRetCode("-1");
			baseBO.setRetMsg("데이터값이 없습니다.");
		}
		else if (data != null && ( data.length() < min ||   data.length() > max) )
		{
			baseBO.setRetCode("-1");
			baseBO.setRetMsg("글자의 자릿수는 " + min + "보다크고 " + max + "보다 작아야 합니다.");
		}
		
		return baseBO;		
	}
	
} // end of class