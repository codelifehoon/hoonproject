/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */

package ref.sample.Extract_Method;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

public class ReplaceTempWithQuery {

	private String _name  = "#name#";
	
	 public void printOwing(Vector<OrderBO>  _orders) 
	{
	    Enumeration<OrderBO> e =_orders.elements();
	    double outstanding = 0.0;
	    // print banner
	    System.out.println ("**************************");
	    System.out.println ("***** Customer Owes ******");
	    System.out.println ("**************************");
	 
	    // calculate outstanding
	    while (e.hasMoreElements()) {
	    	OrderBO each = e.nextElement();
	        outstanding += each.getAmount() * getTempVal();
	    }
	 
	    //print details
	    System.out.println ("name:" + _name);
	    System.out.println ("amount" + outstanding);
	}

	/**
	 * @return
	 */
	private double getTempVal() {
		double tempVal = 123*123;
		return tempVal;
	}
	
}
