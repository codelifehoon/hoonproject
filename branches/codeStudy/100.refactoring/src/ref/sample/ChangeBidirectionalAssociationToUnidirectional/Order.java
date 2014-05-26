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
	 private Customer _customer;
			
	Customer getCustomer() {
        return Customer.getOrderMap().get(this);
    }


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
        return 1  * ( getCustomer().getDisCount() - 1 );
    }
	
	
    
}
