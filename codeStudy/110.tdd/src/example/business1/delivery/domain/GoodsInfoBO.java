/**
 * @FileName  : GoodsInfoBO.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 29. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.business1.delivery.domain;

import java.util.List;

import lombok.Data;

@Data
public class GoodsInfoBO {

	private String goodsNo;
	private String deliverySeq;
	private Integer deliveryCost;
	private String deliveryTyle;
	private String dlvCalcKind;
	
	
	private Integer buyCnt;
	private Integer goodsPrice;
	private Integer deliveryCalcCost;
	
	private List<DlvCostBuyPriceSectBO> dlvCostBuyPriceSectList;
	
	
	/**
	 * @deprecated Use {@link #getDlvCalcKind()} instead
	 */
	public String getDlvKind()
	{
		return getDlvCalcKind();
	}

	
	/**
	 * @deprecated Use {@link #setDlvCalcKind(String)} instead
	 */
	public void setDlvKind(String dlvKind )
	{
		setDlvCalcKind(dlvKind);
	}

	
	
	
		
}
