/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */


package ref.sample.ExtractClass;

public class Person {

	private String _name;
    private TelephoneNumber data = new TelephoneNumber();


	public void setName(String _name) {
        this._name = _name;
    }
    
	public String getName() {
        return _name;
    }
    public String getTelephoneNumber() {
        return ("(" + data._officeAreaCode + ") " + data._officeNumber);
    }
    public String getOfficeAreaCode() {
        return data._officeAreaCode;
    }
    public void setOfficeAreaCode(String arg) {
        data._officeAreaCode = arg;
    }
    public String getOfficeNumber() {
        return data._officeNumber;
    }
    public void setOfficeNumber(String arg) {
        data._officeNumber = arg;
    }

    
}
