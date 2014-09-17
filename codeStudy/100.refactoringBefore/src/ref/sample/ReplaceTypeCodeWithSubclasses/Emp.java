/**
 * @FileName  : Emp.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 12. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ReplaceTypeCodeWithSubclasses;

public  class Emp {

	public static final int ENGINEER = 0;
	public static final int SALESMAN = 1;
	public static final int MANAGER = 2;
	private int type;
	
	public Emp(int type)
	{
		this.type = type;
	}
	public int getType() {
		return type;
	}
	public int create(int type) 
	  {
	        switch (type) {
	            case Emp.ENGINEER:
	               return 100;
	            case Emp.SALESMAN:
	               return 200;
	            case Emp.MANAGER:
	               return 300;
	            default:
	               throw new IllegalArgumentException("Incorrect type code value");
	        }
	    }

	
	
}