/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */

package ref.sample.Extract_Method;

public class OrderBO {

		int amount;

		public OrderBO(int amount )
		{
			this.amount = amount;
		}
		
		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}
		
		
}
