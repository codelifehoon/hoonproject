/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.MoveMethod;


public class AccountType {
	private double kind;
	private double interestRate  = 10.0;
	
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public boolean isPremium()
	{
		return true;
	}
	public double overdraftCharge(Account account) {
	   if (isPremium()) {
	       double result = 10;
	       if (account.daysOverdrawn > 7) result += (account.daysOverdrawn - 7) * 0.85;
	       return result;
	   }
	   else return account.daysOverdrawn * 1.75;
	}
}
