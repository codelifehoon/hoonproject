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
	 	public static final int O = 0;
	    public static final int A = 1;
	    public static final int B = 2;
	    public static final int AB = 3;
	 
	    private int bloodGroup;
	 
	    public Person (int bloodGroup) {
	        bloodGroup = bloodGroup;
	    }
	 
	    public void setBloodGroup(int arg) {
	        bloodGroup = arg;
	    }
	 
	    public int getBloodGroup() {
	        return bloodGroup;
	    }
}
