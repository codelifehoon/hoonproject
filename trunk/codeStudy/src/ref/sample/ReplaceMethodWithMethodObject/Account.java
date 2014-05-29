/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ReplaceMethodWithMethodObject;

public class Account {


	public int gamma (int inputVal, int quantity, int yearToDate) 
	{
		return new Gamma(this, inputVal, quantity, yearToDate).compute();
	}

	public int delta()
	{
		return 13*5;
	}

}
