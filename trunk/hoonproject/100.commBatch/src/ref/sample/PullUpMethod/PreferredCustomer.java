package ref.sample.PullUpMethod;

public class PreferredCustomer extends Customer {

	
	@Override
	protected double chargeFor(int weight)
	{
		return weight * 1.5;
	}
		
}
