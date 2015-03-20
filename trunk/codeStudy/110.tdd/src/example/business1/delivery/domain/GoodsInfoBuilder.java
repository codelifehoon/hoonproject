/**
 * @FileName  : GoodsInfoBuilder.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 29. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.business1.delivery.domain;

import java.util.List;

public class GoodsInfoBuilder {
	private GoodsInfoBO  goodsInfoBO = new GoodsInfoBO();

	

	public GoodsInfoBuilder withGoodsNo(String goodsNo) {
		goodsInfoBO.setGoodsNo(goodsNo);
		return this;
	}
	public GoodsInfoBuilder withDeliverySeq(String deliverySeq) {
		goodsInfoBO.setDeliverySeq(deliverySeq);
		return this;
	}
	public GoodsInfoBuilder withDeliveryCost(Integer deliveryCost) {
		goodsInfoBO.setDeliveryCost(deliveryCost);
		return this;
	}
	public GoodsInfoBuilder withDeliveryTyle(String deliveryTyle) {
		goodsInfoBO.setDeliveryTyle(deliveryTyle);
		return this;
	}
	
	public GoodsInfoBO build()
	{
		return goodsInfoBO;
	}
	/**
	 * @param dlvkindPergoods
	 * @return
	 * @deprecated Use {@link #withDlvCalcKind(String)} instead
	 */
	public GoodsInfoBuilder withDlvKind(String dlvkindPergoods) {
		return withDlvCalcKind(dlvkindPergoods);
	}
	/**
	 * @param dlvkindPergoods
	 * @return
	 */
	public GoodsInfoBuilder withDlvCalcKind(String dlvkindPergoods) {
		goodsInfoBO.setDlvCalcKind(dlvkindPergoods);
		return this;
	}
	/**
	 * @param i
	 * @return
	 */
	public GoodsInfoBuilder withBuyCnt(Integer i) {
		goodsInfoBO.setBuyCnt(i);
		return this;
	}
	/**
	 * @param i
	 * @return
	 */
	public GoodsInfoBuilder withGoodsPrice(Integer i) {
		goodsInfoBO.setGoodsPrice(i);
		return this;
	}
	/**
	 * @param build
	 * @return
	 */
	public GoodsInfoBuilder withdlvCostBuyPriceSect(
			List<DlvCostBuyPriceSectBO> dlvCostBuyPriceSectList) {
		goodsInfoBO.setDlvCostBuyPriceSectList(dlvCostBuyPriceSectList);
		return this;
	}
	
	public GoodsInfoBuilder withDeliveryCalcCost(Integer deliveryCalcCost) {
		goodsInfoBO.setDeliveryCalcCost(deliveryCalcCost);
		return this;
	}
	
	
}
