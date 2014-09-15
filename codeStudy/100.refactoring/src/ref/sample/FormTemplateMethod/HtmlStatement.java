/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.FormTemplateMethod;


public class HtmlStatement extends Statement {
	 
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