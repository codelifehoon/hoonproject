package ref.sample.FormTemplateMethod;


public class TextStatement extends Statement {
	 
   
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
	 * @param result
	 * @param each
	 * @return
	 */
	@Override
	protected String getBody(String result, Rental each) {
		result += "\t" + each.getMovieTitle()+ "\t" +
		String.valueOf(each.getCharge()) + "\n";
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
    
    

    @Override
	public String getName()
    {
    	return "movie list";
    }
    
    @Override
	public int getTotalCharge()
    {
    	return 1000;
    }
    
    @Override
	public int getTotalFrequentRenterPoints()
    {
    	return 999999;
    }

	
	
 }