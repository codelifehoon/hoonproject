/**
 * @FileName  : Course.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 22. 
 * @�묒꽦��     : codelife
 * @蹂�꼍�대젰 :
 * @�꾨줈洹몃옩 �ㅻ챸 :
 */
package ref.sample.EncapsulateCollection;

public class Course {

	private String name;
	private boolean isAdvance;
 	public Course (String name, boolean isAdvanced) 
 	{
 		this.name = name;
 		this.isAdvance = isAdvanced;
 	}
 	
    public boolean isAdvanced() { return this.isAdvance; }
	    
}
