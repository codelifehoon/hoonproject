package ref.sample.PullUpMethod;

public class PreferredCustomer extends Customer {

	
	public double createBill(int weight)
	{
			double bill =  this.workingHours * chargeFor(weight);
			 System.out.println ("**************************");
			 System.out.println (this.toString() + ":" +bill);
			 System.out.println ("**************************");
			return bill;
	}
	
	private double chargeFor(int weight)
	{
		return weight * 0.5;
	}
		
}
