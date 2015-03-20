/**
 * @FileName  : DeliveryCostCalc.java
 * @Project     : code refactoring exam proj
 * @Date         : 2015. 3. 17. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.business3.delivery;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.*;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.jetty.websocket.ZeroMaskGen;

import example.business1.delivery.ConstCode;
import example.business1.delivery.domain.DlvCostBuyPriceSectBO;
import example.business1.delivery.domain.GoodsInfoBO;

public class DeliveryCostCalc {

	
	/**
	 * @deprecated Use {@link #calcDlvCost(GoodsInfoBO,List<DlvCostBuyPriceSectBO>)} instead
	 */
	public int calcDlvCost(GoodsInfoBO goodsInfoBO) {
		return calcDlvCost(goodsInfoBO, null);
	}

	public int calcDlvCost(GoodsInfoBO goodsInfoBO, List<DlvCostBuyPriceSectBO> sectBOList) {

		int calcDlvCost = -1;
		
		
		if (ConstCode.DLVKIND_BASIC.equals(goodsInfoBO.getDlvCalcKind())) calcDlvCost = goodsInfoBO.getDeliveryCost() ;
		if (ConstCode.DLVKIND_CNTGOODS.equals(goodsInfoBO.getDlvCalcKind())) calcDlvCost = goodsInfoBO.getDeliveryCost() * goodsInfoBO.getBuyCnt();
		if (ConstCode.DLVKIND_BUYPRICESEC.equals(goodsInfoBO.getDlvCalcKind()))
		{
			if (sectBOList == null) throw new NullPointerException();
			for(DlvCostBuyPriceSectBO sectBO : sectBOList)
			{
				if (sectBO.getStartVal() <= goodsInfoBO.getGoodsPrice()*goodsInfoBO.getBuyCnt() 
						&&   goodsInfoBO.getGoodsPrice()*goodsInfoBO.getBuyCnt() <= sectBO.getEndVal())
				{
					calcDlvCost =  sectBO.getDlvCost();
				}
			}
			
		}
		
		if (calcDlvCost < 0) throw new NullPointerException();
		
		return calcDlvCost;
	}

	public int calcDlvCost(List<GoodsInfoBO> goodsInfoBOList, List<DlvCostBuyPriceSectBO> sectBOList) throws Exception 
	{

		//assertThat("list size ",goodsInfoBOList.size() , greaterThan(0));
		//if (goodsInfoBOList.size() <=0) throw new Exception("goodsinfolist must be greater then 0");
		if (goodsInfoBOList.size() <=0) throw new Exception("goodsinfolist size는 0보다 커야함");
		int totGoodsPrice=0;
		
		for(GoodsInfoBO goodsInfoBO : goodsInfoBOList)
		{
			totGoodsPrice += goodsInfoBO.getGoodsPrice()*goodsInfoBO.getBuyCnt();
		}
		
		GoodsInfoBO newGoodsInfoBO = (GoodsInfoBO) BeanUtils.cloneBean(goodsInfoBOList.get(0));
		newGoodsInfoBO.setBuyCnt(1);
		newGoodsInfoBO.setGoodsPrice(totGoodsPrice);
		
		return calcDlvCost(newGoodsInfoBO, sectBOList);
		
	}

	
}
