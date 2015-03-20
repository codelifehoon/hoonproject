/**
 * @FileName  : DeleveryCalcGoods.java
 * @Project     : code refactoring exam proj
 * @Date         : 2015. 3. 16. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.business3.delivery;

import java.util.List;

import example.business1.delivery.ConstCode;
import example.business1.delivery.domain.DeliveryInfoBO;
import example.business1.delivery.domain.GoodsInfoBO;

public class DeleveryCalcGoods {

	/**
	 * @param goodsInfoBO
	 * @return
	 */
	@Deprecated
	public DeliveryInfoBO calcBindDlvCost(GoodsInfoBO goodsInfoBO) {

		DeliveryInfoBO deliveryInfoBO = new DeliveryInfoBO();
		
		deliveryInfoBO.setDeliverySeq(goodsInfoBO.getDeliverySeq());
		deliveryInfoBO.setDeliveryType(goodsInfoBO.getDeliveryTyle());
		deliveryInfoBO.setDeliveryCost(goodsInfoBO.getDeliveryCost());
		
		
		if ("02".equals(goodsInfoBO.getDeliveryTyle())) deliveryInfoBO.setDeliveryCost(0);
		
		return deliveryInfoBO;
	}

	/**
	 * @param goodsInfoBOList
	 * @return
	 */
	public DeliveryInfoBO calcBindDlvCostList(List<GoodsInfoBO> goodsInfoBOList) {
		
		DeliveryInfoBO deliveryInfoBO = new DeliveryInfoBO();
		int fixDlvCost=-1;
		String dlvType="";
		if (goodsInfoBOList == null) return null;
		
		
		for (GoodsInfoBO goodsInfoBO : goodsInfoBOList)
		{
			
			if (ConstCode.DLVCOST_DEFPAY.equals(goodsInfoBO.getDeliveryTyle())) fixDlvCost = 0;
			if (!ConstCode.DLVCOST_DEFPAY.equals(dlvType)) dlvType = goodsInfoBO.getDeliveryTyle();
			if (goodsInfoBO.getDeliveryCost() == 0 ) fixDlvCost = 0;
			if (fixDlvCost != 0 &&  goodsInfoBO.getDeliveryCost() >  fixDlvCost )	// 현 배송비가 0이 아니고  이전 배송비보다 현 배송비가 클때 최고 배송비로 판단 
					fixDlvCost = goodsInfoBO.getDeliveryCost();
			
		}
		
		deliveryInfoBO.setDeliveryCost(fixDlvCost);
		deliveryInfoBO.setDeliverySeq(goodsInfoBOList.get(0).getDeliverySeq());
		deliveryInfoBO.setDeliveryType(dlvType);
		
		return deliveryInfoBO;
		
	}

}
