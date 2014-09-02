/**
 * @FileName  : DlvCostBuyPriceSectBuilder.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 9. 1. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.business1.delivery.domain;

import java.util.ArrayList;
import java.util.List;

public class DlvCostBuyPriceSectBuilder {

	List<DlvCostBuyPriceSectBO> list = new ArrayList<DlvCostBuyPriceSectBO>();

	/**
	 * @param i
	 * @param j
	 * @param k
	 * @return
	 */
	public DlvCostBuyPriceSectBuilder withAddSec(Integer startVal, Integer endVal, Integer dlvCost) {

		DlvCostBuyPriceSectBO dlvCostBuyPriceSectBO = new DlvCostBuyPriceSectBO();
		
		dlvCostBuyPriceSectBO.setStartVal(startVal);
		dlvCostBuyPriceSectBO.setEndVal(endVal);
		dlvCostBuyPriceSectBO.setDlvCost(dlvCost);
		list.add(dlvCostBuyPriceSectBO);
		
		return this;
	}
	
	public List<DlvCostBuyPriceSectBO> build()
	{
		return this.list;
	}
	
	
	
	
}
