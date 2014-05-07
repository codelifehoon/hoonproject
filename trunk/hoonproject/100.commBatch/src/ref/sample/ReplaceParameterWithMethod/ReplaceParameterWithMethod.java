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
	      
		int basePrice = quantity * itemPrice;
	       int discountLevel;
	       if (quantity > 100) discountLevel = 2;
	       else discountLevel = 1;
	       double finalPrice = discountedPrice (basePrice, discountLevel);
	       return finalPrice;
	   }
	 
	   private double discountedPrice (int basePrice, int discountLevel) {
	       if (discountLevel == 2) return basePrice * 0.1;
	       else return basePrice * 0.05;
	   }
	   
}
