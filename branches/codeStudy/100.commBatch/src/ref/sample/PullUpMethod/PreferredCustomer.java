/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.PullUpMethod;

public class PreferredCustomer extends Customer {

	
	@Override
	protected double chargeFor(int weight)
	{
		return weight * 1.5;
	}
		
}
