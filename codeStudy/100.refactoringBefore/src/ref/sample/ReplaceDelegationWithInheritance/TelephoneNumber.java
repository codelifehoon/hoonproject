/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */

package ref.sample.ReplaceDelegationWithInheritance;

public class TelephoneNumber {
	public String _officeAreaCode;
	public String _officeNumber;

	public TelephoneNumber() {
	}

	public String get_officeAreaCode() {
		return _officeAreaCode;
	}

	public String get_officeNumber() {
		return _officeNumber;
	}
}