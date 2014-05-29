/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ReplaceTypeCodeWithClass;

public class Person {

	 
	    private BloodGroup data;

		public Person (BloodGroup data) {
			this.data  = data;
	    }
	 
	    public void setBloodGroup(BloodGroup data) {
	    	this.data  = data;
	    }
	 
	    public BloodGroup getBloodGroup() {
	        return this.data;
	    }
}
