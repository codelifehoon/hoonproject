package ref.sample.PullUpMethod;

public class RegularCustomer extends Customer {

	@Override	
	protected double chargeFor(int weight)
	{
		return weight * 0.5;
	}
}
