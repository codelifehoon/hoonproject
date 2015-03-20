/**
 * @FileName  : DeleveryCalc.java
 * @Project     : code refactoring exam proj
 * @Date         : 2015. 1. 16. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.business2.delivery;

import java.util.List;


import example.business1.delivery.domain.DeliveryInfoBO;
import example.business1.delivery.domain.GoodsInfoBO;

public interface DeleveryCalc {

	/**
	 * @param goodsInfoBoList
	 * @return
	 */
	public List<DeliveryInfoBO> calc(List<GoodsInfoBO> goodsInfoBoList);

}