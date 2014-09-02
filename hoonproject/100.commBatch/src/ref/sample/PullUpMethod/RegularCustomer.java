package ref.sample.PullUpMethod;

public class RegularCustomer extends Customer {

		
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
		return weight * 1.5;
	}
}
