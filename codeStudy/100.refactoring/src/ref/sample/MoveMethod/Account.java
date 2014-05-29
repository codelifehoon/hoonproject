/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.MoveMethod;

public class Account {

		public double bankCharge() {
	       double result = 4.5;
	       if (daysOverdrawn > 0) result += type.overdraftCharge(this);
	       return result;
	   }
	   AccountType type;
	   int daysOverdrawn;
}
