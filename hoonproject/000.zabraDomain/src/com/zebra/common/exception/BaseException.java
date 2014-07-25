/**
 * @FileName  : BaseException.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 7. 2. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package com.zebra.common.exception;

public class BaseException extends Exception {
	
	public BaseException(String message)
	{
		super(message);
	}
	
	public BaseException(String message,Exception e)
	{
		super(message,e);
	}
	
}
