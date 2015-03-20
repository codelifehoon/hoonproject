/**
 * @FileName  : DeleveryCalcGoods.java
 * @Project     : code refactoring exam proj
 * @Date         : 2015. 1. 13. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.business2.delivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import example.business1.delivery.ConstCode;


import example.business1.delivery.domain.DeliveryInfoBO;
import example.business1.delivery.domain.GoodsInfoBO;

public class DeleveryCalcGoods implements DeleveryCalc {

	/* (non-Javadoc)
	 * @see example.business2.delivery.DeleveryCalc#calc(java.util.List)
	 */
	@Override
	public List<DeliveryInfoBO> calc(List<GoodsInfoBO> dlist) {

		
		List<DeliveryInfoBO>  retVal = new ArrayList<DeliveryInfoBO>();
		HashMap<String,List<GoodsInfoBO>> dlvMap = new HashMap<String,List<GoodsInfoBO>>();
		

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
		
		for (String mapKey : dlvMap.keySet())
		{
			List<GoodsInfoBO> goodsList = dlvMap.get(mapKey);
			DeliveryInfoBO deliveryInfoBO = new DeliveryInfoBO();
			
			deliveryInfoBO.setDeliverySeq(mapKey);
			deliveryInfoBO.setDeliveryType(ConstCode.DLVCOST_PREPAY);
			deliveryInfoBO.setDeliveryCost(-1);
			
			
			for (GoodsInfoBO goodsInfoBO : goodsList)
			{
								
				if (goodsInfoBO.getDeliveryTyle().equals(ConstCode.DLVCOST_DEFPAY))
				{
					deliveryInfoBO.setDeliveryCost(0);
					deliveryInfoBO.setDeliveryType(ConstCode.DLVCOST_DEFPAY);
					
					break;
				}
				else
				{
					if (goodsInfoBO.getDeliveryCost() > deliveryInfoBO.getDeliveryCost() )
					{
						if (deliveryInfoBO.getDeliveryCost() != 0) 
						{
							deliveryInfoBO.setDeliveryCost(goodsInfoBO.getDeliveryCost());
						}
						else
						{
							deliveryInfoBO.setDeliveryCost(0);
						}
					}
				}
			}
			
			retVal.add(deliveryInfoBO);
		}
		
		return retVal;
	
	}

}
