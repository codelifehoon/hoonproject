package ref.sample.IntroduceParameterObject;

import java.util.Enumeration;
import java.util.Vector;

public class IntroduceParameterObject {

	private Vector<Integer> entries = new Vector();
    
	
	  public Integer getFlowBetween (Integer start, Integer end) {
		  Integer result = 0;
	        Enumeration<Integer> e = entries.elements();
	        
	        while (e.hasMoreElements()) {
	        	Integer each = (Integer) e.nextElement();
	            if ( (each >= start && each <= end))
	            {
	                result += each.intValue();
	            }
	        }
	        return result;
	    }


	public void setEntries(Vector<Integer> entries) {
		this.entries = entries;
	}
	    
}
