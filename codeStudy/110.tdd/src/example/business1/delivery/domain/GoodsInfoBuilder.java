/**
 * @FileName  : GoodsInfoBuilder.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 29. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.business1.delivery.domain;

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
	
	
}
