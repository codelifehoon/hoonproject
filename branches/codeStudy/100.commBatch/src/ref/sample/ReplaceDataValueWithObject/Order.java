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

	private OrderData data ;


	public Order (String customer) {
		data =  new OrderData(customer);
	    }
	    public String getCustomer() {
	        return data.getCustomer();
	    }
	    public void setCustomer(String arg) {
	        data.setCustomer(arg);
	    } 
	    
}
