/**
 * @FileName  : OrderData.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ReplaceDataValueWithObject;

public class OrderData {
	/**
	 * 
	 */
	private String customer;

	public OrderData(String customer) {
		super();
		this.customer = customer;
	}

	/**
	 * 
	 */
	public OrderData() {
	}

	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
}