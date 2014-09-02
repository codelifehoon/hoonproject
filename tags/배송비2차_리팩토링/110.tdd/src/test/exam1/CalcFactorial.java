/**
 * @FileName  : CalcFactorial.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 19. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package test.exam1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;
import example.business1.CalcSomeNum;

/*
 *TDD example N!  
 *
 *0! = 1
 *1! = 1
 *2! = 2 x 1 = 2
 *3! = 3 x 2 x 1 = 6
 *
 * */
public class CalcFactorial {

	@Test
	public void shouldReturnFactorialVal() {
		CalcSomeNum a = new  CalcSomeNum();
		int[][] values ={{0,1}, {1,1}, {2,2}, {3,6}, {4,24}, {10, 3628800}};
		
		for(int[] value: values) {
			assertThat("calc num", CalcSomeNum.calcFactorial(2), is(2));
	    }
		
		
	}

	
}
