package ref.sample.ExtractInterface;

import java.util.Enumeration;
import java.util.Vector;

import ref.sample.Extract_Method.OrderBO;

public class ExtractInterfaceImpl {

	private String _name  = "#name#";
	
	 public void printOwing(Vector<OrderBO>  _orders) 
	{
	    Enumeration<OrderBO> e =_orders.elements();
	    double outstanding = 0.0;
	 
	    // print banner
	    printBanner();
	 
	    outstanding = getOutstanding(e, outstanding);
	 
	    printDetails(outstanding);
	}

	/**
	 * @param e
	 * @param outstanding
	 * @return
	 */
	private double getOutstanding(Enumeration<OrderBO> e, double outstanding) {
		// calculate outstanding
	    while (e.hasMoreElements()) {
	    	OrderBO each = (OrderBO) e.nextElement();
	        outstanding += each.getAmount();
	    }
		return outstanding;
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
