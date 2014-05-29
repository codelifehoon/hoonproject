/**
 * @FileName  : Emp.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 12. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ReplaceTypeCodeWithSubclasses;

public abstract class Emp {

	public static final int ENGINEER = 0;
	public static final int SALESMAN = 1;
	public static final int MANAGER = 2;
	
	public abstract int getType();

	public static Emp create(int type) 
	  {
	        switch (type) {
	            case ENGINEER:
	               return new EmpEngineer();
	            case SALESMAN:
	               return new EmpSalesman();
	            case MANAGER:
	               return new EmpManager();
	            default:
	               throw new IllegalArgumentException("Incorrect type code value");
	        }
	    }
	
}