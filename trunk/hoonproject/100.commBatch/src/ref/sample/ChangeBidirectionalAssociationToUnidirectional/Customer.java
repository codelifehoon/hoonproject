/**
 * @FileName  : Customer.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 19. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ChangeBidirectionalAssociationToUnidirectional;

import java.util.HashSet;
import java.util.Set;

public class Customer {

	 private Set<Order> _orders = new HashSet<>();
	 
	 public void addOrder(Order arg) {
	        arg.setCustomer(this);
	    }
	    
	 public Set<Order>   friendOrders() {
	        /** should only be used by Order */
	        return _orders;
	    }
	    
}
