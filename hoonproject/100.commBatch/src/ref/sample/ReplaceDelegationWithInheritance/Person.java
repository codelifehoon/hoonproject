/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */


package ref.sample.ReplaceDelegationWithInheritance;

public class Person extends TelephoneNumber {

	private String _name;


	public void setName(String _name) {
        this._name = _name;
    }
    
	public String getName() {
        return _name;
    }
    public String getTelephoneNumber() {
        return ("(" + _officeAreaCode + ") " + _officeNumber);
    }
    public String getOfficeAreaCode() {
        return _officeAreaCode;
    }
    public void setOfficeAreaCode(String arg) {
        _officeAreaCode = arg;
    }
    public String getOfficeNumber() {
        return _officeNumber;
    }
    public void setOfficeNumber(String arg) {
        _officeNumber = arg;
    }
    

    
}
