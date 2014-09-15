/**
 * @FileName  : Statement.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 9. 15. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.FormTemplateMethod;

import java.util.Enumeration;
import java.util.Vector;

public abstract class Statement {

	private Vector<Rental> vRentals;

	public abstract int getTotalFrequentRenterPoints();

	public abstract int getTotalCharge();

	public abstract String getName();

	protected abstract String getHeader();

	protected abstract String getBody(String result, Rental each);

	protected abstract String getFooter(String result);

	/**
	 * 
	 */
	public Statement() {
		super();
	}

	public String statement() {
	       Enumeration rentals = vRentals.elements();
	
	        String result = getHeader();
	       while (rentals.hasMoreElements()) {
	        Rental each = (Rental) rentals.nextElement();
	        //show figures for this rental
	        result = getBody(result, each);
	    }
	
	    //add footer lines
	    result = getFooter(result);
	
	    return result;
	 }

	public void setvRentals(Vector<Rental> vRentals) {
		this.vRentals = vRentals;
	}

}