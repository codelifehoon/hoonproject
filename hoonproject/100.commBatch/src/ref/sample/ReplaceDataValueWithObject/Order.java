/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */

package ref.sample.ReplaceDataValueWithObject;



public class Order {

	private String customer;
    
	
	 public Order (String customer) {
	        customer = customer;
	    }
	    public String getCustomer() {
	        return customer;
	    }
	    public void setCustomer(String arg) {
	        customer = arg;
	    } 
	    
}
