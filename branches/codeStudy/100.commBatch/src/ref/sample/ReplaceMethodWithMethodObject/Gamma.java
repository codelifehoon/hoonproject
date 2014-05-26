/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ReplaceMethodWithMethodObject;

public class Gamma {

	private final Account account;
    private int inputVal;
    private int quantity;
    private int yearToDate;
    private int importantValue1;
    private int importantValue2;
    private int importantValue3;
    
	public Gamma(Account account,int inputVal, int quantity, int yearToDate)
	{

		this.account =  account;
		this.inputVal = inputVal;
		this.quantity = quantity;
		this.yearToDate = yearToDate;
		
	}
	
	public int compute()
	{
		int importantValue1 = (inputVal * quantity) + account.delta();
	    int importantValue2 = (inputVal * yearToDate) + 100;
	    if ((yearToDate - importantValue1) > 100)
	        importantValue2 -= 20;
	    int importantValue3 = importantValue2 * 7;
	    
	    // and so on...
	    return importantValue3 - 2 * importantValue1;
	}
}
