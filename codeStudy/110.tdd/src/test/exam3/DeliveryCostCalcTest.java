/**
 * @FileName  : DeliveryCostCalcTest.java
 * @Project     : code refactoring exam proj
 * @Date         : 2015. 3. 17. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package test.exam3;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import example.business1.delivery.ConstCode;
import example.business1.delivery.domain.DlvCostBuyPriceSectBO;
import example.business1.delivery.domain.GoodsInfoBO;
import example.business1.delivery.domain.GoodsInfoBuilder;
import example.business3.delivery.DeliveryCostCalc;

public class DeliveryCostCalcTest {

	@Rule
    public ExpectedException thrown= ExpectedException.none();
	private final DeliveryCostCalc deliveryCostCalc = new DeliveryCostCalc();
	
	@Before
	public void setUp()  throws Exception {
	}
	
	@Test
	public void 기본상품_배송비계산_null() {

		thrown.expect(NullPointerException.class);
		int calcDlvCost = deliveryCostCalc.calcDlvCost(new GoodsInfoBO(), null);
		
		
		
		//assertThat("calcDlvCost", 1000, is(equalTo(calcDlvCost)));
		
	}
	
	@Test
	public void 기본상품_배송비계산() {

		GoodsInfoBO goodsInfoBO = new GoodsInfoBuilder().withGoodsPrice(10000).withBuyCnt(10).withDeliveryCost(1000).withDlvCalcKind(ConstCode.DLVKIND_BASIC).build();
		
		
		int calcDlvCost = deliveryCostCalc.calcDlvCost(goodsInfoBO, null);
		
		assertThat("calcDlvCost", 1000, is(equalTo(calcDlvCost)));
		
	}
	
	
	
	@Test
	public void 상품개당_배송비계산_1개() {

		
		
		GoodsInfoBO goodsInfoBO = new GoodsInfoBO();
		
		goodsInfoBO.setGoodsPrice(10000);
		goodsInfoBO.setBuyCnt(1);
		goodsInfoBO.setDeliveryCost(1000);
		goodsInfoBO.setDlvCalcKind(ConstCode.DLVKIND_BASIC);
		
		
		int calcDlvCost = deliveryCostCalc.calcDlvCost(goodsInfoBO, null);
		
		assertThat("calcDlvCost", 1000, is(equalTo(calcDlvCost)));
		
	}
	
	@Test
	public void 상품개당_배송비계산_3개() {

		
		
		GoodsInfoBO goodsInfoBO = new GoodsInfoBO();
		
		goodsInfoBO.setGoodsPrice(10000);
		goodsInfoBO.setBuyCnt(3);
		goodsInfoBO.setDeliveryCost(1000);
		goodsInfoBO.setDlvCalcKind(ConstCode.DLVKIND_CNTGOODS);
		
		
		int calcDlvCost = deliveryCostCalc.calcDlvCost(goodsInfoBO, null);
		
		assertThat("calcDlvCost", 3000, is(equalTo(calcDlvCost)));
		
	}
	

	@Test
	public void 상품구매금액_배송비계산_조건이없을떄() {

		
		
		GoodsInfoBO goodsInfoBO = new GoodsInfoBO();
		goodsInfoBO.setDlvCalcKind(ConstCode.DLVKIND_BUYPRICESEC);

		
		thrown.expect(NullPointerException.class);
		int calcDlvCost = deliveryCostCalc.calcDlvCost(goodsInfoBO, null);
		
		
	}
	
	
	@Test
	public void 상품구매금액_배송비계산_조건_between() {

		
		
		GoodsInfoBO goodsInfoBO = new GoodsInfoBO();
		DlvCostBuyPriceSectBO sectBO1 = new DlvCostBuyPriceSectBO();
		DlvCostBuyPriceSectBO sectBO2 = new DlvCostBuyPriceSectBO();
		
		List<DlvCostBuyPriceSectBO> sectList = new ArrayList<DlvCostBuyPriceSectBO>();
		
		goodsInfoBO.setGoodsPrice(10000);
		goodsInfoBO.setBuyCnt(2);
		//goodsInfoBO.setDeliveryCost(1000);
		goodsInfoBO.setDlvCalcKind(ConstCode.DLVKIND_BUYPRICESEC);
		
		sectBO1.setStartVal(0);
		sectBO1.setEndVal(10000);
		sectBO1.setDlvCost(2500);
		sectList.add(sectBO1);
		
		sectBO2.setStartVal(10001);
		sectBO2.setEndVal(999999999);
		sectBO2.setDlvCost(1500);
		sectList.add(sectBO2);
		
		
		int calcDlvCost = deliveryCostCalc.calcDlvCost(goodsInfoBO, sectList);
		assertThat("calcDlvCost", calcDlvCost, is(equalTo(1500)));
		
	}
	
	@Test
	public void 상품구매금액_여러건_배송비계산_null() throws Exception {

		
		
		List<GoodsInfoBO> goodsInfoBOList = new ArrayList<GoodsInfoBO>();
		
		//thrown.expect(AssertionError.class);
		thrown.expect(Exception.class);
		int calcDlvCost = deliveryCostCalc.calcDlvCost(goodsInfoBOList, null);
		//assertThat("calcDlvCost", calcDlvCost, is(equalTo(1500)));
		
	}
	
	

	@Test
	public void 상품구매금액_여러건_배송비계산_여러상품() throws Exception {

		
		
		GoodsInfoBO goodsInfoBO1 = new GoodsInfoBO();
		GoodsInfoBO goodsInfoBO2 = new GoodsInfoBO();
		List<GoodsInfoBO> goodsInfoBOList = new ArrayList<GoodsInfoBO>();
		
		DlvCostBuyPriceSectBO sectBO1 = new DlvCostBuyPriceSectBO();
		DlvCostBuyPriceSectBO sectBO2 = new DlvCostBuyPriceSectBO();
		DlvCostBuyPriceSectBO sectBO3 = new DlvCostBuyPriceSectBO();
		List<DlvCostBuyPriceSectBO> sectList = new ArrayList<DlvCostBuyPriceSectBO>();
		
		
		goodsInfoBO1.setGoodsPrice(10000);
		goodsInfoBO1.setBuyCnt(1);
		//goodsInfoBO1.setDeliveryCost(1000);
		goodsInfoBO1.setDlvCalcKind(ConstCode.DLVKIND_BUYPRICESEC);
		goodsInfoBOList.add(goodsInfoBO1);
		
		goodsInfoBO2.setGoodsPrice(20000);
		goodsInfoBO2.setBuyCnt(1);
		//goodsInfoBO2.setDeliveryCost(1000);
		goodsInfoBO2.setDlvCalcKind(ConstCode.DLVKIND_BUYPRICESEC);
		goodsInfoBOList.add(goodsInfoBO2);
		
		
		sectBO1.setStartVal(0);
		sectBO1.setEndVal(10000);
		sectBO1.setDlvCost(2500);
		sectList.add(sectBO1);
		
		sectBO2.setStartVal(10001);
		sectBO2.setEndVal(20000);
		sectBO2.setDlvCost(1500);
		sectList.add(sectBO2);
		
		sectBO3.setStartVal(20001);
		sectBO3.setEndVal(999999999);
		sectBO3.setDlvCost(500);
		sectList.add(sectBO3);
		

		int calcDlvCost = deliveryCostCalc.calcDlvCost(goodsInfoBOList, sectList);
		
		assertThat("calcDlvCost", calcDlvCost, is(equalTo(500)));
		
	}
	

	

}
