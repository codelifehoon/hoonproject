package ref.sample.FormTemplateMethod;

import java.util.Enumeration;
import java.util.Vector;

public class HtmlStatement {
	 
	private Vector<Rental> vRentals;
	
    public String htmlStatement()
    {
        Enumeration<Rental> rentals = vRentals.elements();
 
        String result = "#####\n<H1>Rentals for <EM>" + getName() + "</EM></H1><P>\n";
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            //show figures for each rental
            result += each.getMovieTitle() + ": " +
            String.valueOf(each.getCharge()) + "<BR>\n";
        }
        //add footer lines
        result +=  "<P>You owe <EM>" + String.valueOf(getTotalCharge()) + "</EM><P>\n";
        result += "On this rental you earned <EM>" +
            String.valueOf(getTotalFrequentRenterPoints()) +
            "</EM> frequent renter points<P>";
 
 
 
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
    public void setvRentals(Vector<Rental> vRentals) {
		this.vRentals = vRentals;
	}
	
}