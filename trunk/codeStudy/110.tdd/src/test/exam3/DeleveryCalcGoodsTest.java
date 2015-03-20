/**
 * @FileName  : DeleveryCalcGoodsTest.java
 * @Project     : code refactoring exam proj
 * @Date         : 2015. 3. 16. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package test.exam3;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import example.business1.delivery.ConstCode;
import example.business1.delivery.domain.DeliveryInfoBO;
import example.business1.delivery.domain.GoodsInfoBO;
import example.business3.delivery.DeleveryCalcGoods;

public class DeleveryCalcGoodsTest {

	private DeleveryCalcGoods deleveryCalcGoods = null;
	
	@Before
	public void Setup()
	{
		
		this.deleveryCalcGoods = new DeleveryCalcGoods();
	}
	
	@Test
	public void fixbindcost_0_prepay() {
		
		
		List<GoodsInfoBO> goodsInfoBOList	= new ArrayList<GoodsInfoBO>();
		GoodsInfoBO goodsInfoBO = new GoodsInfoBO();
		
		goodsInfoBO.setGoodsNo("11111");
		goodsInfoBO.setDeliverySeq("001");
		goodsInfoBO.setDeliveryCost(0);
		goodsInfoBO.setDeliveryTyle(ConstCode.DLVCOST_PREPAY);
		goodsInfoBOList.add(goodsInfoBO);
		
		DeliveryInfoBO result = deleveryCalcGoods.calcBindDlvCostList(goodsInfoBOList);
		validateDlvValue("001", ConstCode.DLVCOST_PREPAY, 0, result);
	}

	@Test
	public void fixbindcost_2500_deferredpay() {
		
		List<GoodsInfoBO> goodsInfoBOList	= new ArrayList<GoodsInfoBO>();
		DeleveryCalcGoods deleveryCalcGoods = new DeleveryCalcGoods();
		GoodsInfoBO goodsInfoBO = new GoodsInfoBO();
		
		goodsInfoBO.setGoodsNo("11111");
		goodsInfoBO.setDeliverySeq("001");
		goodsInfoBO.setDeliveryCost(2500);
		goodsInfoBO.setDeliveryTyle(ConstCode.DLVCOST_DEFPAY);
		goodsInfoBOList.add(goodsInfoBO);
		
		DeliveryInfoBO result = this.deleveryCalcGoods.calcBindDlvCostList(goodsInfoBOList);
		validateDlvValue("001", ConstCode.DLVCOST_DEFPAY, 0, result);
	}

	@Test
	public void fixbindcost_multy_list_call_param_null() {
		
		
		DeliveryInfoBO result = this.deleveryCalcGoods.calcBindDlvCostList(null);
		assertThat("calcBindDlvCostList null param", result, equalTo(null));
	} 
	
	
	@Test
	public void fixbindcost_0_pp_2500_pp() {
		
		DeleveryCalcGoods deleveryCalcGoods = new DeleveryCalcGoods();
		List<GoodsInfoBO> goodsInfoBOList	= new ArrayList<GoodsInfoBO>();
		
		GoodsInfoBO goodsInfoBO = new GoodsInfoBO();
		goodsInfoBO.setGoodsNo("11111");
		goodsInfoBO.setDeliverySeq("001");
		goodsInfoBO.setDeliveryCost(0);
		goodsInfoBO.setDeliveryTyle(ConstCode.DLVCOST_PREPAY);
		goodsInfoBOList.add(goodsInfoBO);
		
		GoodsInfoBO goodsInfoBO2 = new GoodsInfoBO();
		goodsInfoBO2.setGoodsNo("22222");
		goodsInfoBO2.setDeliverySeq("001");
		goodsInfoBO2.setDeliveryCost(3000);
		goodsInfoBO2.setDeliveryTyle(ConstCode.DLVCOST_PREPAY);	
		goodsInfoBOList.add(goodsInfoBO2);

		DeliveryInfoBO result = this.deleveryCalcGoods.calcBindDlvCostList(goodsInfoBOList);
		validateDlvValue("001",ConstCode.DLVCOST_PREPAY, 0, result);
	}
	

	@Test
	public void fixbindcost_multyds_2500_pp_3000_pp_and_2500_pp_3000_df() {
		
		DeleveryCalcGoods deleveryCalcGoods = new DeleveryCalcGoods();
		List<GoodsInfoBO> goodsInfoBOList	= new ArrayList<GoodsInfoBO>();
		
		GoodsInfoBO goodsInfoBO = new GoodsInfoBO();
		goodsInfoBO.setGoodsNo("11111");
		goodsInfoBO.setDeliverySeq("001");
		goodsInfoBO.setDeliveryCost(2500);
		goodsInfoBO.setDeliveryTyle(ConstCode.DLVCOST_PREPAY);
		goodsInfoBOList.add(goodsInfoBO);
		

		GoodsInfoBO goodsInfoBO2 = new GoodsInfoBO();
		goodsInfoBO2.setGoodsNo("22222");
		goodsInfoBO2.setDeliverySeq("001");
		goodsInfoBO2.setDeliveryCost(3000);
		goodsInfoBO2.setDeliveryTyle(ConstCode.DLVCOST_DEFPAY);	
		goodsInfoBOList.add(goodsInfoBO2);

		DeliveryInfoBO result = this.deleveryCalcGoods.calcBindDlvCostList(goodsInfoBOList);
		validateDlvValue("001",ConstCode.DLVCOST_DEFPAY, 0, result);
	}
	


	/**
	 * @param dlvSeq
	 * @param dlvType
	 * @param dlvcost
	 * @param result
	 */
	private void validateDlvValue(String dlvSeq, String dlvType, int dlvcost,
			DeliveryInfoBO result) {
		assertThat("dlvCost ",result.getDeliveryCost(),is(equalTo(dlvcost)));
		assertThat("dlvSeq ",result.getDeliverySeq(),is(equalTo(dlvSeq)));
		assertThat("dlvType ",result.getDeliveryType(),is(equalTo(dlvType)));
	}

	
}
