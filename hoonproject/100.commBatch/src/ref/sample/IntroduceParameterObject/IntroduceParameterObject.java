/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.IntroduceParameterObject;

import java.util.Enumeration;
import java.util.Vector;

public class IntroduceParameterObject {

	private Vector<Integer> entries = new Vector();
    
	
	  /**
	 * @deprecated Use {@link #getFlowBetween(FlowBetweenParameter)} instead
	 */
	public Integer getFlowBetween (Integer start, Integer end) {
		return getFlowBetween(new FlowBetweenParameter(start, end));
	}


	public Integer getFlowBetween (FlowBetweenParameter parameterObject) {
		  Integer result = 0;
	        Enumeration<Integer> e = entries.elements();
	        
	        while (e.hasMoreElements()) {
	        	Integer each = (Integer) e.nextElement();
	            if ( (each >= parameterObject.getStart() && each <= parameterObject.getEnd()))
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
