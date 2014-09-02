/**
 * @FileName  : CaclDeliveryCost.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 29. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package test.exam1;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.contains;


import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.example.common.ConstCode;

import example.business1.delivery.DeleveryCalc;
import example.business1.delivery.DeleveryCalcGoods;
import example.business1.delivery.domain.DeliveryInfoBO;
import example.business1.delivery.domain.GoodsInfoBO;

public class CaclDeliveryCost {

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
		
		DeleveryCalc   deliveryCalc	   = new DeleveryCalcGoods();
		GoodsInfoBO goodsInfoBO1 = new GoodsInfoBO();
		GoodsInfoBO goodsInfoBO2 = new GoodsInfoBO();
		GoodsInfoBO goodsInfoBO3 = new GoodsInfoBO();
		GoodsInfoBO goodsInfoBO4 = new GoodsInfoBO();
		GoodsInfoBO goodsInfoBO5 = new GoodsInfoBO();
		GoodsInfoBO goodsInfoBO6 = new GoodsInfoBO();

		
		List<GoodsInfoBO> dlist = new ArrayList<GoodsInfoBO>();
		
		goodsInfoBO1.setGoodsNo("11111");
		goodsInfoBO1.setDeliverySeq("001");
		goodsInfoBO1.setDeliveryCost(2500);
		goodsInfoBO1.setDeliveryTyle(ConstCode.DLVCOST_PREPAY);		// 01: 선결제  	02:후불
		
		goodsInfoBO2.setGoodsNo("2222");
		goodsInfoBO2.setDeliverySeq("001");
		goodsInfoBO2.setDeliveryCost(3000);
		goodsInfoBO2.setDeliveryTyle(ConstCode.DLVCOST_PREPAY);		// 01: 선결제  	02:후불
		
		goodsInfoBO3.setGoodsNo("11111");
		goodsInfoBO3.setDeliverySeq("002");
		goodsInfoBO3.setDeliveryCost(0);
		goodsInfoBO3.setDeliveryTyle(ConstCode.DLVCOST_PREPAY);		// 01: 선결제  	02:후불
		
		goodsInfoBO4.setGoodsNo("22222");
		goodsInfoBO4.setDeliverySeq("002");
		goodsInfoBO4.setDeliveryCost(2500);
		goodsInfoBO4.setDeliveryTyle(ConstCode.DLVCOST_PREPAY);		// 01: 선결제  	02:후불
		
		
		goodsInfoBO5.setGoodsNo("11111");
		goodsInfoBO5.setDeliverySeq("003");
		goodsInfoBO5.setDeliveryCost(1000);
		goodsInfoBO5.setDeliveryTyle(ConstCode.DLVCOST_DEFPAY);		// 01: 선결제  	02:후불
		
		goodsInfoBO6.setGoodsNo("22222");
		goodsInfoBO6.setDeliverySeq("003");
		goodsInfoBO6.setDeliveryCost(2000);
		goodsInfoBO6.setDeliveryTyle(ConstCode.DLVCOST_PREPAY);		// 01: 선결제  	02:후불
		
		
		
		dlist.add(goodsInfoBO1);
		dlist.add(goodsInfoBO2);
		dlist.add(goodsInfoBO3);
		dlist.add(goodsInfoBO4);
		dlist.add(goodsInfoBO5);
		dlist.add(goodsInfoBO6);
		
		
		List<DeliveryInfoBO> deliveryInfoBOList =  deliveryCalc.calc(dlist);
		
		
		List<String> typeList = new ArrayList<String>();
		for (DeliveryInfoBO deliveryInfoBO : deliveryInfoBOList)
		{
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
			
			typeList.add(deliveryInfoBO.getDeliverySeq());
		}
		
		
		assertThat( typeList, contains("001","002","003"));
		
	
	}

}
