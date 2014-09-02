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
}
