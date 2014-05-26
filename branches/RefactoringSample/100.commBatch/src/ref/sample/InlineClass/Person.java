/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.InlineClass;

public class Person {

    private String name;
    private TelephoneNumber officeTelephone = new TelephoneNumber();
    private String number;
    private String areaCode;
    
	 public String getName() {
	        return name;
	    }

    public TelephoneNumber getOfficeTelephone() {
        return officeTelephone;
    }
	 

	public String getAreaCode() {
        return areaCode;
    }
	public void setAreaCode(String arg) {
        areaCode = arg;
    }
	public String getNumber() {
        return number;
    }
	public void setNumber(String arg) {
        number = arg;
    }
	

	    
}
