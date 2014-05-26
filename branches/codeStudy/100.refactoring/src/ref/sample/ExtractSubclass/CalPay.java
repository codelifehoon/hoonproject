/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ExtractSubclass;

public class CalPay {

	protected int pay;
	protected int workingDay;

    
	public CalPay(int pay, int workingDay) {
		super();
		this.pay = pay;
		this.workingDay = workingDay;
	}
	
	public int doCalc()
	{
		
		return	 pay * workingDay ;
		
	}
    
    
    
    
}
