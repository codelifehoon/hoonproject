/**
 * @FileName  : DeliveryCost.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 9. 2. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.business1.delivery;

import java.util.List;

import example.business1.delivery.domain.GoodsInfoBO;

public interface DeliveryCost {

	public void dlvCostCalc(List<GoodsInfoBO> dlist);

}