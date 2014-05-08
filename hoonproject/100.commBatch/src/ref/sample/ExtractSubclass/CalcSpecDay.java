/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ExtractSubclass;

public class CalcSpecDay extends CalPay{

	public CalcSpecDay(int pay, int workingDay) {
		super(pay, workingDay);
		// TODO Auto-generated constructor stub
	}
	
	public int doCalc()
	{
		
		return	 pay * workingDay * 2;
	
	
	}
		

}
