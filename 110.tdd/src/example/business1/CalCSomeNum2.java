/**
 * @FileName  : CalCSomeNum2.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 22. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.business1;

public class CalCSomeNum2 {

	/**
	 * @param i
	 * @return
	 */
	public Integer Calc(int i) {
		
		if (i < 2) {
		    return 1;
		  }
		    return CalcSomeNum.calcFactorial(i-1)*i;
	}

}
