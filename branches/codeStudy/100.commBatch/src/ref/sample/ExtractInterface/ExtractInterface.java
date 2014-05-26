
/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */

package ref.sample.ExtractInterface;

import java.util.Vector;

import ref.sample.Extract_Method.OrderBO;

public interface ExtractInterface {

	public void printOwing(Vector<OrderBO> _orders);

}