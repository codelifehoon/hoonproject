package ref.sample.FormTemplateMethod;

import java.util.Enumeration;

public class HtmlStatement extends Statement {
	 
	public String statement()
    {
        Enumeration<Rental> rentals = vRentals.elements();
 
        String result = getHeader();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            //show figures for each rental
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
		result += each.getMovieTitle() + ": " + String.valueOf(each.getCharge()) + "<BR>\n";
		return result;
	}

	/**
	 * @param result
	 * @return
	 */
	@Override
	protected String getFooter(String result) {
		result +=  "<P>You owe <EM>" + String.valueOf(getTotalCharge()) + "</EM><P>\n";
        result += "On this rental you earned <EM>" +
            String.valueOf(getTotalFrequentRenterPoints()) +
            "</EM> frequent renter points<P>";
		return result;
	}

	/**
	 * @return
	 */
	@Override
	protected String getHeader() {
		String result = "#####\n<H1>Rentals for <EM>" + getName() + "</EM></H1><P>\n";
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