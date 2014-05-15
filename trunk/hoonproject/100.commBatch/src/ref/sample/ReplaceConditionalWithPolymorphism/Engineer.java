/**
 * @FileName  : Engineer.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 13. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ReplaceConditionalWithPolymorphism;

public class Engineer extends EmployeeType {

	/* (non-Javadoc)
	 * @see ref.sample.ReplaceTypeCodeWithStateStrategy.EmployeeType#getTypeCode()
	 */
	@Override
	int getTypeCode() {
		// TODO Auto-generated method stub
		return  EmployeeType.ENGINEER;
	}

}
