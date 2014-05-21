/**
 * @FileName  : Order.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 19. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ChangeBidirectionalAssociationToUnidirectional;

public class Order {

	private String name;
	private String tel;
	
		
	Customer getCustomer() {
        return _customer;
    }
    void setCustomer (Customer arg) {
        if (_customer != null) _customer.friendOrders().remove(this);
        _customer = arg;
        if (_customer != null) _customer.friendOrders().add(this);
    }
    private Customer _customer;


    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public int getDiscountedPrice() {
        return 1  * ( _customer.getDisCount() - 1 );
    }
	
	
    
}
