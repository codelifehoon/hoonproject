package ref.sample.PullUpMethod;

import java.util.Date;

public abstract class Customer {
	
		protected int workingHours  =0 ;

		public void addWorkingHours(int workingHours) {
			this.workingHours = workingHours;
		}

		protected abstract double chargeFor(int weight);

		public double createBill(int weight) {
				double bill =  this.workingHours * chargeFor(weight);
				 System.out.println ("**************************");
				 System.out.println (this.toString() + ":" +bill);
				 System.out.println ("**************************");
				return bill;
		}

		
		

}
