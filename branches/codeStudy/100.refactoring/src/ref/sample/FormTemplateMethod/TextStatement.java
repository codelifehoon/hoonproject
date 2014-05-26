/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.FormTemplateMethod;

import java.util.Enumeration;
import java.util.Vector;

public class TextStatement extends Statement {
	 
   
	 public String statement()
     {
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



	/**
	 * @param result
	 * @param each
	 * @return
	 */
	@Override
	protected String getBody(String result, Rental each) {
		result += "\t" + each.getMovieTitle()+ "\t" +  String.valueOf(each.getCharge()) + "\n";
		return result;
	}



	/**
	 * @param result
	 * @return
	 */
	@Override
	protected String getFooter(String result) {
		result +=  "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints())
            + "frequent renter points";
		return result;
	}



	/**
	 * @return
	 */
	@Override
	protected String getHeader() {
		String result = "#####\nRental Record for " + getName() + "\n";
		return result;
	}
    
    

    public String getName()
    {
    	return "movie list";
    }
    
    public int getTotalCharge()
    {
    	return 1000;
    }
    
    public int getTotalFrequentRenterPoints()
    {
    	return 999999;
    }
    

	
 }