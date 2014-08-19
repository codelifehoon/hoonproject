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
	public static int Calc(int i) {
		
		int retVal=0;
		if (i <= 1) {
			retVal =1;
		}
		else if(i==2)  {
			retVal =2;
		}
		
		return retVal;
	}

}
