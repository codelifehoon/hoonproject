/**
 * @FileName  : BloodGroup.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ReplaceTypeCodeWithClass;

public class BloodGroup {
	/**
	 * 
	 */
	
 	public static final BloodGroup O = new BloodGroup(0);
    public static final BloodGroup A = new BloodGroup(1);
    public static final BloodGroup B = new BloodGroup(2);
    public static final BloodGroup AB = new BloodGroup(3);
    private static final BloodGroup[] values = {O,A,B,AB};
    
    private final int code;

	private BloodGroup(int code) {
		  this.code = code;
	}

	public int getCode() {
		return code;
	}

	/*
	private static BloodGroup code(int arg)
	{
		return values[arg];
	}
	*/

}