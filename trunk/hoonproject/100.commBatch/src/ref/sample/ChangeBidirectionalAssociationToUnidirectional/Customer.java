/**
 * @FileName  : Customer.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 19. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ChangeBidirectionalAssociationToUnidirectional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.util.Assert;

public class Customer {

	 private Set<Order> _orders = new HashSet<>();
	 private int disCount = 1;
	 private static HashMap<Order,Customer> orderMap = new HashMap<Order,Customer>() ;
	 
	 public void addOrder(Order arg) throws Exception {
		 	friendOrders().add(arg);
		 	addOrderMap(arg);
	    }

	/**
	 * @param arg
	 * @throws Exception 
	 */
	public void addOrderMap(Order arg) throws Exception {
		if (orderMap.get(arg) != null) throw new Exception("order object alread exists.");
		orderMap.put(arg, this);
	}
	
	    
	 public Set<Order>   friendOrders() {
	        /** should only be used by Order */
	        return _orders;
	    }

	public int getDisCount() {
		return disCount;
	}

	public void setDisCount(int disCount) {
		this.disCount = disCount;
	}

	public int getPriceFor(Order order) {
        if (_orders.contains(order))   return order.getDiscountedPrice();
        return 0;
	}
	
	public boolean containsOrder(Order order)
	{
		return _orders.contains(order);
	}

	public static HashMap<Order, Customer> getOrderMap() {
		return orderMap;
	}


	

}
