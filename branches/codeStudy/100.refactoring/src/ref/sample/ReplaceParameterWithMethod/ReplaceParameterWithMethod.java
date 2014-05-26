/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ReplaceParameterWithMethod;

public class ReplaceParameterWithMethod {
	 
	 private int quantity = 0;
	 private int itemPrice = 0;
	
	 public ReplaceParameterWithMethod(int _quantity, int _itemPrice) {
		super();
		this.quantity = _quantity;
		this.itemPrice = _itemPrice;
	}

	public double getPrice() {
	      
		   return discountedPrice ();
	   }

	/**
	 * @return
	 */
	protected int getDiscountLevel() {
		int discountLevel;
	       if (quantity > 100) discountLevel = 2;
	       else discountLevel = 1;
		return discountLevel;
	}

	/**
	 * @return
	 */
	protected int getBasePrice() {
		return quantity * itemPrice;
	}
	 
	   private double discountedPrice () {
	       if (getDiscountLevel() == 2) return getBasePrice() * 0.1;
	       else return getBasePrice() * 0.05;
	   }
	   
}
