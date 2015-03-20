/**
 * @FileName  : DeleveryCost.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 9. 2. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.business1.delivery;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import example.business1.delivery.ConstCode;
import example.business1.delivery.domain.DlvCostBuyPriceSectBO;
import example.business1.delivery.domain.GoodsInfoBO;

public  class DeliveryCostCalc implements DeliveryCost   {


	/* (non-Javadoc)
	 * @see example.business1.delivery.DeliveryCost#dlvCostCalc(java.util.List)
	 */
	public  void dlvCostCalc(List<GoodsInfoBO> dlist) {

		
		DeliveryPolicy deliveryPolicy = new DeliveryPolicy();
		HashMap<String,List<GoodsInfoBO>> dlvMap = new HashMap<String,List<GoodsInfoBO>>();
		deliveryPolicy.bindPolicyDlvNo(dlist, dlvMap);
		
		
		for (String mapKey : dlvMap.keySet())
		{
			List<GoodsInfoBO> goodsList = dlvMap.get(mapKey);
			Integer burPriceSecSum = 0;
			
			Iterator<GoodsInfoBO> i =  goodsList.iterator();
			while (i.hasNext())
			{
				
				GoodsInfoBO  goodsInfoBO = i.next();
				
				if (ConstCode.DLVKIND_BASIC.equals(goodsInfoBO.getDlvCalcKind()))
				{
					goodsInfoBO.setDeliveryCalcCost(goodsInfoBO.getDeliveryCost());
				}
				else if (ConstCode.DLVKIND_CNTGOODS.equals(goodsInfoBO.getDlvCalcKind()))
				{
					goodsInfoBO.setDeliveryCalcCost(goodsInfoBO.getDeliveryCost() * goodsInfoBO.getBuyCnt() );
				}
				else if (ConstCode.DLVKIND_BUYPRICESEC.equals(goodsInfoBO.getDlvCalcKind()))
				{
					burPriceSecSum += goodsInfoBO.getGoodsPrice() * goodsInfoBO.getBuyCnt();
					
					if (!i.hasNext())
					{
						
						Integer deliveryCalcCost=9999999;
						
						List<DlvCostBuyPriceSectBO> dlvCostBuyPriceSectList = goodsInfoBO.getDlvCostBuyPriceSectList();
						for (DlvCostBuyPriceSectBO dlvCostBuyPriceSectBO :  dlvCostBuyPriceSectList)
						{
							if (dlvCostBuyPriceSectBO.getStartVal() <= burPriceSecSum 
									&& dlvCostBuyPriceSectBO.getEndVal() >= burPriceSecSum )
							{
								deliveryCalcCost = dlvCostBuyPriceSectBO.getDlvCost();
							}
						}
						
						for (GoodsInfoBO tempGoodsInfo : goodsList)
						{
							tempGoodsInfo.setDeliveryCalcCost(deliveryCalcCost);
						}	
					}
				}
				else
				{
					
					System.out.print("##### 잘못된 배송비 계산 코드 입니다.!");
					System.out.print("goodsInfoBO:" + goodsInfoBO.toString());
				}
			}
				
		}
		
	}


	
}