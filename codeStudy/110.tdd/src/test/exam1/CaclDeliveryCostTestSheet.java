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

import example.business1.delivery.ConstCode;

import example.business1.delivery.DeliveryBind;
import example.business1.delivery.DeliveryBindCalc;
import example.business1.delivery.DeliveryCost;
import example.business1.delivery.DeliveryCostCalc;
import example.business1.delivery.domain.DeliveryInfoBO;
import example.business1.delivery.domain.DlvCostBuyPriceSectBuilder;
import example.business1.delivery.domain.GoodsInfoBO;
import example.business1.delivery.domain.GoodsInfoBuilder;

public class CaclDeliveryCostTestSheet {

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
	public void bindCostCalc() {
		
		DeliveryBind   deliveryCalc	   = new DeliveryBindCalc();

		List<GoodsInfoBO> dlist = new ArrayList<GoodsInfoBO>();
		

		dlist.add(new GoodsInfoBuilder().withGoodsNo("11111").withDeliverySeq("001").withDeliveryCalcCost(2500).withDeliveryTyle(ConstCode.DLVCOST_PREPAY).build());
		dlist.add(new GoodsInfoBuilder().withGoodsNo("2222").withDeliverySeq("001").withDeliveryCalcCost(3000).withDeliveryTyle(ConstCode.DLVCOST_PREPAY).build());
		dlist.add(new GoodsInfoBuilder().withGoodsNo("11111").withDeliverySeq("002").withDeliveryCalcCost(0).withDeliveryTyle(ConstCode.DLVCOST_PREPAY).build());
		dlist.add(new GoodsInfoBuilder().withGoodsNo("2222").withDeliverySeq("002").withDeliveryCalcCost(2500).withDeliveryTyle(ConstCode.DLVCOST_PREPAY).build());
		dlist.add(new GoodsInfoBuilder().withGoodsNo("11111").withDeliverySeq("003").withDeliveryCalcCost(1000).withDeliveryTyle(ConstCode.DLVCOST_DEFPAY).build());
		dlist.add(new GoodsInfoBuilder().withGoodsNo("2222").withDeliverySeq("003").withDeliveryCalcCost(2000).withDeliveryTyle(ConstCode.DLVCOST_PREPAY).build());
		dlist.add(new GoodsInfoBuilder().withGoodsNo("1111").withDeliverySeq("004").withDeliveryCalcCost(2000).withDeliveryTyle(ConstCode.DLVCOST_PREPAY).build());
		dlist.add(new GoodsInfoBuilder().withGoodsNo("1111").withDeliverySeq("005").withDeliveryCalcCost(2000).withDeliveryTyle(ConstCode.DLVCOST_DEFPAY).build());
		
		
		List<DeliveryInfoBO> deliveryInfoBOList =  deliveryCalc.bindCostCalc(dlist);
		
		
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
	
	@Test
	public void dlvCostCalc() {
		
		DeliveryCost	deliveryCost	= new DeliveryCostCalc();
	
		
		
		List<GoodsInfoBO> dlist = new ArrayList<GoodsInfoBO>();

		dlist.add(new GoodsInfoBuilder().withGoodsNo("10001")
				.withDeliverySeq("001")
				.withDeliveryCost(2500)
				.withDlvCalcKind(ConstCode.DLVKIND_BASIC).build());
		
		dlist.add(new GoodsInfoBuilder().withGoodsNo("10002")
				.withDeliverySeq("001")
				.withDeliveryCost(2500)
				.withDlvCalcKind(ConstCode.DLVKIND_BASIC).build());
				
		dlist.add(new GoodsInfoBuilder().withGoodsNo("10003")
				.withDeliverySeq("002")
				.withDeliveryCost(2500)
				.withBuyCnt(2)
				.withDlvCalcKind(ConstCode.DLVKIND_CNTGOODS).build());
		
		dlist.add(new GoodsInfoBuilder().withGoodsNo("10004")
				.withDeliverySeq("003")
				.withBuyCnt(2)
				.withGoodsPrice(1000)
				.withdlvCostBuyPriceSect(new DlvCostBuyPriceSectBuilder().withAddSec(0,1999,3000).withAddSec(2000,3999,3000).withAddSec(4000,-1,3000).build())
				.withDlvCalcKind(ConstCode.DLVKIND_BUYPRICESEC).build());
		
		dlist.add(new GoodsInfoBuilder().withGoodsNo("10005")
				.withDeliverySeq("003")
				.withBuyCnt(1)
				.withGoodsPrice(1000)
				.withdlvCostBuyPriceSect(new DlvCostBuyPriceSectBuilder().withAddSec(0,1999,3000).withAddSec(2000,3999,3000).withAddSec(4000,-1,3000).build())
				.withDlvCalcKind(ConstCode.DLVKIND_BUYPRICESEC).build());
				
		deliveryCost.dlvCostCalc(dlist);
		
		
		for (GoodsInfoBO goodsInfoBO : dlist)
		{
			switch (goodsInfoBO.getGoodsNo()) {
			   case "10001" : 
					assertThat("delivery cost", goodsInfoBO.getDeliveryCalcCost(), is(2500));
				   	break;
			   case "10002" : 
				   assertThat("delivery cost", goodsInfoBO.getDeliveryCalcCost(), is(2500));
					break;
			   case "10003" :
				   assertThat("delivery cost", goodsInfoBO.getDeliveryCalcCost(), is(5000));
				   	break;
			   case "10004" :
				   assertThat("delivery cost", goodsInfoBO.getDeliveryCalcCost(), is(3000));
				   	break;
			   case "10005" :
				   assertThat("delivery cost", goodsInfoBO.getDeliveryCalcCost(), is(3000));
				   	break;
				  
			   default:  break;
			}
		}
	}

}
