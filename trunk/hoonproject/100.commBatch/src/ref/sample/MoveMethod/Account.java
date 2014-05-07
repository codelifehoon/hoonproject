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
