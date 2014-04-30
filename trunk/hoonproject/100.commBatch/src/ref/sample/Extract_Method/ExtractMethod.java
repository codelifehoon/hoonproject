package ref.sample.Extract_Method;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

public class ExtractMethod {

	private String _name  = "#name#";
	
	 public void printOwing(Vector<OrderBO>  _orders) 
	{
	    Enumeration<OrderBO> e =_orders.elements();
	    double outstanding = 0.0;
	 
	    // print banner
	    printBanner();
	 
	    // calculate outstanding
	    while (e.hasMoreElements()) {
	    	OrderBO each = (OrderBO) e.nextElement();
	        outstanding += each.getAmount();
	    }
	 
	    printDetails(outstanding);
	}

	/**
	 * @param outstanding
	 */
	private void printDetails(double outstanding) {
		//print details
	    System.out.println ("name:" + _name);
	    System.out.println ("amount" + outstanding);
	}

	/**
	 * 
	 */
	private void printBanner() {
		System.out.println ("**************************");
	    System.out.println ("***** Customer Owes ******");
	    System.out.println ("**************************");
	}
	
}
