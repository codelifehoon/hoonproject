/**
 * @FileName  : DeleveryCalcGoodsTest.java
 * @Project     : code refactoring exam proj
 * @Date         : 2015. 1. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package test.exam2;

import static org.hamcrest.Matchers.contains;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import  example.business1.delivery.ConstCode;
import example.business2.delivery.DeleveryCalc;
import example.business2.delivery.DeleveryCalcGoods;
import example.business1.delivery.domain.DeliveryInfoBO;
import example.business1.delivery.domain.GoodsInfoBO;
import example.business1.delivery.domain.GoodsInfoBuilder;

public class DeleveryCalcGoodsTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void dlvCalc() {
		
		// given
		DeleveryCalc deleveryCalcGoodsTest = new DeleveryCalcGoods();
		List<GoodsInfoBO> goodsInfoBoList = new ArrayList<GoodsInfoBO>();
		List<String> typeList = new ArrayList<String>();
		
		goodsInfoBoList.add(new GoodsInfoBuilder().withGoodsNo("11111").withDeliverySeq("001").withDeliveryCost(2500).withDeliveryTyle(ConstCode.DLVCOST_PREPAY).build());
		goodsInfoBoList.add(new GoodsInfoBuilder().withGoodsNo("2222").withDeliverySeq("001").withDeliveryCost(3000).withDeliveryTyle(ConstCode.DLVCOST_PREPAY).build());
		goodsInfoBoList.add(new GoodsInfoBuilder().withGoodsNo("11111").withDeliverySeq("002").withDeliveryCost(0).withDeliveryTyle(ConstCode.DLVCOST_PREPAY).build());
		goodsInfoBoList.add(new GoodsInfoBuilder().withGoodsNo("2222").withDeliverySeq("002").withDeliveryCost(2500).withDeliveryTyle(ConstCode.DLVCOST_PREPAY).build());
		goodsInfoBoList.add(new GoodsInfoBuilder().withGoodsNo("11111").withDeliverySeq("003").withDeliveryCost(1000).withDeliveryTyle(ConstCode.DLVCOST_DEFPAY).build());
		goodsInfoBoList.add(new GoodsInfoBuilder().withGoodsNo("2222").withDeliverySeq("003").withDeliveryCost(2000).withDeliveryTyle(ConstCode.DLVCOST_PREPAY).build());
	
		
		//when
		List<DeliveryInfoBO> deliveryInfoBOList = deleveryCalcGoodsTest.calc(goodsInfoBoList);
		
		
		//then
		for (DeliveryInfoBO deliveryInfoBO : deliveryInfoBOList)
		{
			typeList.add(deliveryInfoBO.getDeliverySeq());

			if ("001".equals(deliveryInfoBO.getDeliverySeq())) 
				{
					assertThat("delivery cost", deliveryInfoBO.getDeliveryCost(), is(3000));
					assertThat("delivery cost type", deliveryInfoBO.getDeliveryType(), is(ConstCode.DLVCOST_PREPAY));
				}
			if ("002".equals(deliveryInfoBO.getDeliverySeq())) 
				{
					assertThat("delivery cost", deliveryInfoBO.getDeliveryCost(), is(0));
					assertThat("delivery cost type", deliveryInfoBO.getDeliveryType(), is(ConstCode.DLVCOST_PREPAY));
				}
			if ("003".equals(deliveryInfoBO.getDeliverySeq())) 
				{
					assertThat("delivery cost", 0, is(deliveryInfoBO.getDeliveryCost()));
					assertThat("delivery cost type", deliveryInfoBO.getDeliveryType(), is(ConstCode.DLVCOST_DEFPAY));
				}
				
		}
		
		assertThat( typeList, contains("001","002","003"));
		
	}

}
