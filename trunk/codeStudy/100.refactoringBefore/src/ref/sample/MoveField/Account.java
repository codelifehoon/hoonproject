package ref.sample.MoveField;

public class Account {


	  private double interestRate  = 10.0;
	 
	  
	   public double interestForAmount_days (double amount, int days) {
	       return interestRate * amount * days / 365;
	   }
	   
}
