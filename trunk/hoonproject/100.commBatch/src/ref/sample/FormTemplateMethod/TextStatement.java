package ref.sample.FormTemplateMethod;

import java.util.Enumeration;
import java.util.Vector;

public class TextStatement {
	 
   
	private Vector<Rental> vRentals;
	
	
	 public String statement()
     {
           Enumeration rentals = vRentals.elements();
 
            String result = "#####\nRental Record for " + getName() + "\n";
           while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            //show figures for this rental
            result += "\t" + each.getMovieTitle()+ "\t" +
            String.valueOf(each.getCharge()) + "\n";
        }
 
        //add footer lines
        result +=  "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints())
            + "frequent renter points";
 
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