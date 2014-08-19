/**
 * @FileName  : CalcSomeNum.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 19. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.business1;

public class CalcSomeNum {

	/**
	 * @param i 
	 * @return
	 */
	public static int calcFactorial(int i) {
		
		if (i < 2) {
		    return 1;
		  }
		    return CalcSomeNum.calcFactorial(i-1)*i;
	}

}
