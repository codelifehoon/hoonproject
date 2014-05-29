/**
 * @FileName  : Course.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 22. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
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
