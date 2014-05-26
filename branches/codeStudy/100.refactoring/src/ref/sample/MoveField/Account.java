/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.MoveField;

public class Account {

	  private AccountType type = new AccountType();
	  public double InterestForAmountDays (double amount, int days) {
	       return type.getInterestRate() * amount * days / 365;
	   }
	   
}
