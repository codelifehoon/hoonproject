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
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsInAnyOrder;


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
import example.business1.delivery.domain.GoodsInfoBuilder;

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

		List<GoodsInfoBO> dlist = new ArrayList<GoodsInfoBO>();
		

		dlist.add(new GoodsInfoBuilder().withGoodsNo("11111").withDeliverySeq("001").withDeliveryCost(2500).withDeliveryTyle(ConstCode.DLVCOST_PREPAY).build());
		dlist.add(new GoodsInfoBuilder().withGoodsNo("2222").withDeliverySeq("001").withDeliveryCost(3000).withDeliveryTyle(ConstCode.DLVCOST_PREPAY).build());
		dlist.add(new GoodsInfoBuilder().withGoodsNo("11111").withDeliverySeq("002").withDeliveryCost(0).withDeliveryTyle(ConstCode.DLVCOST_PREPAY).build());
		dlist.add(new GoodsInfoBuilder().withGoodsNo("2222").withDeliverySeq("002").withDeliveryCost(2500).withDeliveryTyle(ConstCode.DLVCOST_PREPAY).build());
		dlist.add(new GoodsInfoBuilder().withGoodsNo("11111").withDeliverySeq("003").withDeliveryCost(1000).withDeliveryTyle(ConstCode.DLVCOST_DEFPAY).build());
		dlist.add(new GoodsInfoBuilder().withGoodsNo("2222").withDeliverySeq("003").withDeliveryCost(2000).withDeliveryTyle(ConstCode.DLVCOST_PREPAY).build());
		dlist.add(new GoodsInfoBuilder().withGoodsNo("1111").withDeliverySeq("004").withDeliveryCost(2000).withDeliveryTyle(ConstCode.DLVCOST_PREPAY).build());
		dlist.add(new GoodsInfoBuilder().withGoodsNo("1111").withDeliverySeq("005").withDeliveryCost(2000).withDeliveryTyle(ConstCode.DLVCOST_DEFPAY).build());
		
		
		List<DeliveryInfoBO> deliveryInfoBOList =  deliveryCalc.calc(dlist);
		
		
		List<String> typeList = new ArrayList<String>();
		for (DeliveryInfoBO deliveryInfoBO : deliveryInfoBOList)
		{
			
			
			switch (deliveryInfoBO.getDeliverySeq()) {
			   case "001" : 
					assertThat("delivery cost", deliveryInfoBO.getDeliveryCost(), is(3000));
					assertThat("delivery cost type", deliveryInfoBO.getDeliveryType(), is(ConstCode.DLVCOST_PREPAY));
				   	break;
			   case "002" : 
				   	assertThat("delivery cost", deliveryInfoBO.getDeliveryCost(), is(0));
					assertThat("delivery cost type", deliveryInfoBO.getDeliveryType(), is(ConstCode.DLVCOST_PREPAY));
					break;
			   case "003" :
				   	assertThat("delivery cost",deliveryInfoBO.getDeliveryCost(), is(0) );
					assertThat("delivery cost type", deliveryInfoBO.getDeliveryType(), is(ConstCode.DLVCOST_DEFPAY));
				   	break;
			   case "004" :
				   assertThat("delivery cost", deliveryInfoBO.getDeliveryCost(), is(2000));
					assertThat("delivery cost type", deliveryInfoBO.getDeliveryType(), is(ConstCode.DLVCOST_PREPAY));
				   	break;
			   case "005" :
				   assertThat("delivery cost", deliveryInfoBO.getDeliveryCost(),is(0));
					assertThat("delivery cost type", deliveryInfoBO.getDeliveryType(), is(ConstCode.DLVCOST_DEFPAY));
				   	break;
				  
			   default:  break;
			}
			
		
			typeList.add(deliveryInfoBO.getDeliverySeq());
		}
		
		assertThat( typeList, containsInAnyOrder("001","002","003","004","005"));
		
	
	}

}
