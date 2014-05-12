/**
 * @FileName  : Employee.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 12. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ReplaceTypeCodeWithSubclasses;

public class EmpSalesman extends Emp  {

    @Override
	public int getType()
    {
    	return Emp.SALESMAN;
    }
}
