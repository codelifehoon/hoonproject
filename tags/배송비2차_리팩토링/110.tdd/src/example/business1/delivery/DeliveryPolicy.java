/**
 * @FileName  : DeleveryPolicy.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 9. 2. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.business1.delivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import example.business1.delivery.domain.GoodsInfoBO;

public class DeliveryPolicy {

	/**
	 * 
	 */
	public DeliveryPolicy() {
		super();
	}

	/**
	 * @param dlist
	 * @param dlvMap
	 */
	protected void bindPolicyDlvNo(List<GoodsInfoBO> dlist, HashMap<String, List<GoodsInfoBO>> dlvMap) {
		
		for (GoodsInfoBO goodsInfoBO : dlist)
		{
			List<GoodsInfoBO> bindDlvInfo = dlvMap.get(goodsInfoBO.getDeliverySeq());
					
			if (bindDlvInfo != null)
			{
				bindDlvInfo.add(goodsInfoBO);
			}
			else
			{
				List<GoodsInfoBO> bindDlvInfoNew = new ArrayList<GoodsInfoBO>();
				
				bindDlvInfoNew.add(goodsInfoBO);
				dlvMap.put(goodsInfoBO.getDeliverySeq(), bindDlvInfoNew);
			}
			
		}
	}

}