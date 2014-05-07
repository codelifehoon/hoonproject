package ref.sample.MoveField;

public class Account {

	  private AccountType type = new AccountType();
	  public double InterestForAmountDays (double amount, int days) {
	       return type.getInterestRate() * amount * days / 365;
	   }
	   
}
