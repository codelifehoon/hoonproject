/**
 * @FileName  : DeleveryCalcGoods.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 29. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.business1.delivery;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import  example.business1.delivery.ConstCode;

import example.business1.delivery.domain.DeliveryInfoBO;
import example.business1.delivery.domain.DlvCostBuyPriceSectBO;
import example.business1.delivery.domain.GoodsInfoBO;
import lombok.Data;


public class DeliveryBindCalc implements DeliveryBind {

	/* (non-Javadoc)
	 * @see example.business1.delivery.DeleveryCalc#calc(java.util.List)
	 */
	@Override
	public List<DeliveryInfoBO> bindCostCalc(List<GoodsInfoBO> dlist) {
		
		DeliveryPolicy deliveryPolicy = new DeliveryPolicy();
		List<DeliveryInfoBO>  retVal = new ArrayList<DeliveryInfoBO>();
		HashMap<String,List<GoodsInfoBO>> dlvMap = new HashMap<String,List<GoodsInfoBO>>();
		

		deliveryPolicy.bindPolicyDlvNo(dlist, dlvMap);
		
		for (String mapKey : dlvMap.keySet())
		{
			List<GoodsInfoBO> goodsList = dlvMap.get(mapKey);
			DeliveryInfoBO deliveryInfoBO = new DeliveryInfoBO();
			
			deliveryInfoBO.setDeliverySeq(mapKey);
			deliveryInfoBO.setDeliveryType(ConstCode.DLVCOST_PREPAY);
			deliveryInfoBO.setDeliveryCost(-1);
			
			
			calcBindDlvCost(goodsList, deliveryInfoBO);
			
			retVal.add(deliveryInfoBO);
		}
		
		return retVal;
	}

	/**
	 * @param goodsList
	 * @param deliveryInfoBO
	 */
	private void calcBindDlvCost(List<GoodsInfoBO> goodsList,
			DeliveryInfoBO deliveryInfoBO) {
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
				if (goodsInfoBO.getDeliveryCalcCost() > deliveryInfoBO.getDeliveryCost() )
				{
					if (deliveryInfoBO.getDeliveryCost() != 0) 
					{
						deliveryInfoBO.setDeliveryCost(goodsInfoBO.getDeliveryCalcCost());
					}
					else
					{
						deliveryInfoBO.setDeliveryCost(0);
					}
				}
			}
		}
	}

	


	
	
}
