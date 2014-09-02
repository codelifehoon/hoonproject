package ref.sample.ExtractClass;

public class Person {

	private String _name;
    private String _officeAreaCode;
    private String _officeNumber;
    
    
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
