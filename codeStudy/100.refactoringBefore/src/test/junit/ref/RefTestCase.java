/**
 * @FileName  : RefTestCase.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package test.junit.ref;

import static org.junit.Assert.assertEquals;


import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.AssertThrows;

import ref.sample.DecomposeConditional.DecomposeConditional;
import ref.sample.ExtractClass.Person;
import ref.sample.ExtractInterface.ExtractInterfaceImpl;
import ref.sample.ExtractSubclass.CalPay;
import ref.sample.Extract_Method.ExtractMethod;
import ref.sample.Extract_Method.OrderBO;
import ref.sample.FormTemplateMethod.HtmlStatement;
import ref.sample.FormTemplateMethod.Rental;
import ref.sample.FormTemplateMethod.TextStatement;
import ref.sample.IntroduceAssertion.IntroduceAssertion;
import ref.sample.IntroduceForeignMethod.IntroduceForeignMethod;
import ref.sample.IntroduceLocalExtension.IntroduceLocalExtension;
import ref.sample.IntroduceNullObject.Customer;

import ref.sample.IntroduceParameterObject.IntroduceParameterObject;
import ref.sample.PullUpMethod.PreferredCustomer;
import ref.sample.PullUpMethod.RegularCustomer;
import ref.sample.RemoveParameter.RemoveParam;
import ref.sample.ReplaceArrayWithObject.Performance;
import ref.sample.ReplaceDataValueWithObject.Order;
import ref.sample.ReplaceInheritanceWithDelegation.MyStack;
import ref.sample.ReplaceMethodWithMethodObject.Account;
import ref.sample.ReplaceParameterWithMethod.ReplaceParameterWithMethod;
import ref.sample.ReplaceTempWithQuery.ReplaceTempWithQuery;
import ref.sample.ReplaceTypeCodeWithSubclasses.Emp;
import ref.selfTest.Charge;





public class RefTestCase {

	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass	public static void 
	setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before public void 
	setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After public void 
	tearDown() throws Exception {
	}

	@Test public void 
	ExtractMethodTest() {
		ExtractMethod m = new ExtractMethod();
		
		Vector<OrderBO> v = new  Vector<OrderBO>(3);
		
		v.addElement(new OrderBO(100));
		v.addElement(new OrderBO(200));
		v.addElement(new OrderBO(300));
		
		
		m.printOwing(v);
		
	}
	
	@Test public void 
	ReplaceTempWithQueryTest() {
		ReplaceTempWithQuery m = new ReplaceTempWithQuery();
		
		Vector<OrderBO> v = new  Vector<OrderBO>(3);
		
		v.addElement(new OrderBO(100));
		v.addElement(new OrderBO(200));
		v.addElement(new OrderBO(300));
		
		
		m.printOwing(v);
		
	}
	
	@Test public void 
	PullUpMethodTest() {
		PreferredCustomer 	m = new PreferredCustomer();
		RegularCustomer 	m2 = new RegularCustomer();
		
		
		m.addWorkingHours(10);
		m2.addWorkingHours(10);
		

		assertTrue(m.createBill(1) == 5);
		assertTrue(m2.createBill(2) == 30);
	}
	
	@Test public void 
	FormTemplateMethodTest()
	{
		// 리팩토링전
		HtmlStatement h = new HtmlStatement();
		TextStatement t = new TextStatement();
		Vector<Rental> v = new  Vector<Rental>(1);
		v.addElement( new Rental("리팩토링",10));
		
		h.setvRentals(v);
		t.setvRentals(v);
		
		
		System.out.println(h.htmlStatement());
		System.out.println(t.statement());
		/*
		// 리팩토링 후
		HtmlStatement h = new HtmlStatement();
		TextStatement t = new TextStatement();
		Vector<Rental> v = new  Vector<Rental>(1);
		v.addElement( new Rental("리팩토링",10));
		
		h.setvRentals(v);
		t.setvRentals(v);
		
		
		System.out.println(h.statement());
		System.out.println(t.statement());
		*/
	}
	
	@Test public void 
	ExtractClassTest()
	{
		Person person = new Person();
		
		person.setName("Name");
		person.setOfficeAreaCode("OfficeAreaCode");
		person.setOfficeNumber("OfficeNumber");
		
		assertEquals("Name", person.getName());
		assertEquals("OfficeAreaCode", person.getOfficeAreaCode());
		assertEquals("OfficeNumber", person.getOfficeNumber());
		
		
	}
	
	
	@Test public void 
	IntroduceParameterObjectTest()
	{
		// 리팩토링 전
		IntroduceParameterObject introduceParameterObject = new IntroduceParameterObject();
		
		Vector<Integer> v = new Vector<Integer>();
		
		v.addElement(1);
		v.addElement(3);
		v.addElement(5);
		v.addElement(7);
		v.addElement(9);
		
		introduceParameterObject.setEntries(v);
		
		assertEquals(introduceParameterObject.getFlowBetween(3, 8), Integer.valueOf(15));
		assertEquals(introduceParameterObject.getFlowBetween(2, 5), Integer.valueOf(8));
		
		
		// 리팩토링후
		/*
		IntroduceParameterObject introduceParameterObject = new IntroduceParameterObject();
		
		Vector<Integer> v = new Vector<Integer>();
		
		v.addElement(1);
		v.addElement(3);
		v.addElement(5);
		v.addElement(7);
		v.addElement(9);
		
		introduceParameterObject.setEntries(v);
		
		assertEquals(introduceParameterObject.getFlowBetween(new FlowBetweenParameter(3, 8)), Integer.valueOf(15));
		assertEquals(introduceParameterObject.getFlowBetween(new FlowBetweenParameter(2, 5)), Integer.valueOf(8));
		*/
	}
	

	

	@Test public void 
	ReplaceMethodWithMethodObject()
	{
		Account account	= new Account();
	
		assertEquals(account.gamma(10, 20, 30),2270);

	}
	
	@Test public void 
	DecomposeConditionalTest()
	{
		DecomposeConditional decomposeConditional = new DecomposeConditional();
		
		System.out.println("######DecomposeConditionalTest:" + decomposeConditional.compute(5, 3, 6, 9));
		assertEquals( decomposeConditional.compute(5, 3, 6, 9),9);
		
	}
	
	
	@Test public void 
	ExtractSubclassTest()
	{
		// 리팩토링 전
		CalPay 		calPay 	= new CalPay(10,10,true);
		assertEquals( calPay.doCalc(),200);
		
		
		/*
		// 리팩토링 후
		CalcSpecDay calcSpecDay = new CalcSpecDay(10,10);
		CalPay 		calPay 	= new CalPay(10,10);
		
		System.out.println("######ExtractSubclassTest:" +calcSpecDay.doCalc());
		System.out.println("######ExtractSubclassTest:" +calPay.doCalc());
		
		assertEquals( calcSpecDay.doCalc(),200);
		assertEquals( calPay.doCalc(),100);
		*/
		
	}
	
	@Test public void 
	ExtractInterfaceImplTest() {

 		// 리팩토링 전
		ExtractInterfaceImpl m = new ExtractInterfaceImpl();
		
		Vector<OrderBO> v = new  Vector<OrderBO>(3);
		
		v.addElement(new OrderBO(100));
		v.addElement(new OrderBO(200));
		v.addElement(new OrderBO(300));
		
		m.printOwing(v);
	
		
		// 리팩토링 후
		/*
		ExtractInterface m = new ExtractInterfaceImpl();
		
		Vector<OrderBO> v = new  Vector<OrderBO>(3);
		
		v.addElement(new OrderBO(100));
		v.addElement(new OrderBO(200));
		v.addElement(new OrderBO(300));
		
		m.printOwing(v);
*/	
		
	}

	
	@Test public void 
	ReplaceParameterWithMethodTest()
	{
		ReplaceParameterWithMethod replaceParameterWithMethod = new ReplaceParameterWithMethod(10,10);
		
		
		System.out.println("######ReplaceParameterWithMethodTest:" +replaceParameterWithMethod.getPrice());
		assertEquals((int)replaceParameterWithMethod.getPrice(),5);
		
	}

	@Test public void 
	MoveFieldTest()
	{
		// 리팩토링 전
		ref.sample.MoveField.Account account = new ref.sample.MoveField.Account();
		
		System.out.println("######MoveFieldTestTest:" +(int)account.interestForAmount_days(20.0,3));
		assertEquals((int)account.interestForAmount_days(20.0,3),1);
		
				
		/*
		// 리팩토링 후
		ref.sample.MoveField.Account account = new ref.sample.MoveField.Account();
		
		System.out.println("######MoveFieldTestTest:" +(int)account.InterestForAmountDays(20.0,3));
		assertEquals((int)account.InterestForAmountDays(20.0,3),1);
		*/
	}
	
	@Test public void 
	MoveMethodTest()
	{
		ref.sample.MoveMethod.Account account = new ref.sample.MoveMethod.Account();
		
		System.out.println("######MoveMethodTest:" +(int)account.bankCharge());
		assertEquals((int)account.bankCharge(),4);
		
		
	}
	
	@Test public void 
	InlineClassTest()
	{
		 ref.sample.InlineClass.Person person = new ref.sample.InlineClass.Person();

		 // 리팩토링전
		 person.getOfficeTelephone().setAreaCode("0000");
		 person.getOfficeTelephone().setNumber("1111");
		 assertEquals( person.getOfficeTelephone().getAreaCode(),"0000");
		 assertEquals( person.getOfficeTelephone().getNumber(),"1111");

		
			
		 // 리팩토링후
		 /*	
		 person.setAreaCode("0000");
		 person.setNumber("1111");
		 assertEquals( person.getAreaCode(),"0000");
		 assertEquals( person.getNumber(),"1111");
		*/		 
	}
	
	@Test public void 
	 ReplaceDataValueWithObject()
	{

		Order order = new Order("name");
		assertEquals( order.getCustomer(),"name");
	
	}
	
	
	@Test public void 
	ReplaceArrayWithObjectTest()
	{
		// 리팩토링전
		String[] row = new String[3];
		 
	    row [0] = "Liverpool";
	    row [1] = "15";
	 
	    String name = row[0];
	    int wins = Integer.parseInt(row[1]);
	    
	    
	 // 리팩토링후
	 /*
	    Performance performance = new Performance();
	    
	    performance.setName("Liverpool");
	    performance.setWins("15");
	    
	    
	    assertEquals( name,performance.getName());
	    assertEquals( wins,Integer.parseInt(performance.getWins()));    
	 */
	}
	
	@Test public void 
	ReplaceTypeCodeWithClassTest()
	{
	
		// 리팩토링전
		ref.sample.ReplaceTypeCodeWithClass.Person p = new ref.sample.ReplaceTypeCodeWithClass.Person(ref.sample.ReplaceTypeCodeWithClass.Person.O);

		p.getBloodGroup();
		assertEquals(p.getBloodGroup() , ref.sample.ReplaceTypeCodeWithClass.Person.O); 
		p.setBloodGroup(ref.sample.ReplaceTypeCodeWithClass.Person.AB);
		assertEquals(p.getBloodGroup() , ref.sample.ReplaceTypeCodeWithClass.Person.AB); 
	
		
		// 리팩토링후
		/*	
		ref.sample.ReplaceTypeCodeWithClass.Person p = new ref.sample.ReplaceTypeCodeWithClass.Person(BloodGroup.O);

		p.getBloodGroup();
		assertEquals(p.getBloodGroup().getCode() , BloodGroup.O.getCode()); 
		p.setBloodGroup(BloodGroup.AB);
		assertEquals(p.getBloodGroup().getCode() , BloodGroup.AB.getCode()); 
			*/		
	}
	
	@Test public void 
	ReplaceTypeCodeWithSubclassesTest()
	{
		/*리팩토링전*/

		Emp employee = new Emp(Emp.ENGINEER);
		assertEquals(employee.getType() ,Emp.ENGINEER); 
		
		
		/*리팩토링후*/
		/*
		Emp employee = EmpEngineer.create(Emp.ENGINEER);
		assertEquals(employee.getType() ,Emp.ENGINEER); 
		*/
	}
	
	@Test public void  
	ReplaceTypeCodeWithStateStrategyTest()
	{
		// 리팩토링전
		ref.sample.ReplaceTypeCodeWithStateStrategy.Employee employee = 
				new ref.sample.ReplaceTypeCodeWithStateStrategy.Employee(ref.sample.ReplaceTypeCodeWithStateStrategy.Employee.ENGINEER);
		
		employee.payAmount();
		employee.setType(ref.sample.ReplaceTypeCodeWithStateStrategy.Employee.SALESMAN);
		assertEquals(employee.payAmount() ,40); 

		// 리팩토링후
		/*
		ref.sample.ReplaceTypeCodeWithStateStrategy.Employee employee = 
				new ref.sample.ReplaceTypeCodeWithStateStrategy.Employee(ref.sample.ReplaceTypeCodeWithStateStrategy.EmployeeType.ENGINEER);
		
		employee.payAmount();
		employee.setType(ref.sample.ReplaceTypeCodeWithStateStrategy.EmployeeType.SALESMAN);
		assertEquals(employee.payAmount() ,40);
	*/
	}
	
	@Test public void  
	ReplaceConditionalWithPolymorphismTest()
	{
		ref.sample.ReplaceConditionalWithPolymorphism.Employee employee = 
				new ref.sample.ReplaceConditionalWithPolymorphism.Employee(ref.sample.ReplaceConditionalWithPolymorphism.EmployeeType.ENGINEER);
		
		employee.payAmount();
		employee.setType(ref.sample.ReplaceConditionalWithPolymorphism.EmployeeType.SALESMAN);
		assertEquals(employee.payAmount() ,40);


	}
	
	@Test public void  
	IntroduceNullObjectTest()
	{
		String str1 = "";
		String str2 = "";
		
		// 리팩토링전
		ref.sample.IntroduceNullObject.Customer customer = null;
	
		// 리팩토링후
		/*
		ref.sample.IntroduceNullObject.Customer customer = ref.sample.IntroduceNullObject.Customer.newNull();
		
		
		str1 = getCustomerInfo(customer);
		
		customer = new ref.sample.IntroduceNullObject.Customer();
		customer.setName("j");
		customer.setAge("1");
		customer.setGender("M");
		 
		str2 = getCustomerInfo(customer);
		 
		assertEquals(str1,"nameagegender");
		assertEquals(str2,"j1M");
		*/
		
		
	}

	/**
	 * @param str1
	 * @param customer
	 * @return
	 */
	// 리팩토링전
/*
	public String getCustomerInfo(ref.sample.IntroduceNullObject.Customer customer) {
		String str1 = "";
		
		if (customer == null) str1 += "name";
		else str1 += customer.getName();
		
		if (customer == null) str1 += "age";
		else str1 += customer.getAge();
		
		if (customer == null) str1 += "gender";
		else str1 += customer.getGender();
		return str1;
	}
*/	
	// 리팩토링 후
	public String getCustomerInfo(ref.sample.IntroduceNullObject.Customer customer) {
		String str1 = "";
		
		str1 += customer.getName();
		str1 += customer.getAge();
		str1 += customer.getGender();
		return str1;
	}
	
	@Test public void  
	RemoveParamTest()
	{
		// 리팩토링전
	
		RemoveParam removeParam = new RemoveParam(0, 0);
		removeParam.getPrice(1);
	/*
	 	// 리팩토링 후
		RemoveParam removeParam = new RemoveParam(0, 0);
		removeParam.getPrice();
	 */
	}
	
	@Test public void  
	HideDelegateTest()
	{
		// Remove Middle Man 예제로 같이 참고 할 수 있음
		ref.sample.HideDelegate.Person person = new ref.sample.HideDelegate.Person();
		
		person.setOfficeAreaCode("111");
		assertEquals(person.getOfficeAreaCode(),person.getData().get_officeAreaCode());
		
	}
	
	@Test public void  
	ReplaceDelegationWithInheritanceTest()
	{
		ref.sample.ReplaceDelegationWithInheritance.Person person =  new ref.sample.ReplaceDelegationWithInheritance.Person();
		
		person.setOfficeAreaCode("111");
		assertEquals(person.getOfficeAreaCode(),"111");
		
	}

	@Test public void  
	ChangeBidirectionalAssociationToUnidirectionalTest() throws Exception
	{
		// 리팩토리전

		ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order order1 = new ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order ();
		ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order order2 = new ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order ();
		ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order order3 = new ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order ();
		ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order order4 = new ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order ();
		
		ref.sample.ChangeBidirectionalAssociationToUnidirectional.Customer customer1 = new ref.sample.ChangeBidirectionalAssociationToUnidirectional.Customer();
		ref.sample.ChangeBidirectionalAssociationToUnidirectional.Customer customer2 = new ref.sample.ChangeBidirectionalAssociationToUnidirectional.Customer();
		
		String temp="";
		order1.setName("Name1");
		order1.setTel("Tel1");
		order2.setName("Name2");
		order2.setTel("Tel2");
		order3.setName("Name3");
		order3.setTel("Tel3");
		order4.setName("Name4");
		order4.setTel("Tel4");
		
		customer1.addOrder(order1);
		customer1.addOrder(order2);
		customer1.setDisCount(2);
		customer2.addOrder(order3);
		customer2.addOrder(order4);
		customer2.setDisCount(4);
		
		System.out.println("ChangeBidirectionalAssociationToUnidirectionalTest1==================================");
		
		for (ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order o : customer1.friendOrders())
		{
			temp += o.getName();
			temp += o.getTel();
			assertEquals(customer1.getPriceFor(o),1);
			
		}

		for (ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order o : customer2.friendOrders())
		{
			temp += o.getName();
			temp += o.getTel();
			assertEquals(customer2.getPriceFor(o),3);
		}
		
		System.out.println("1:" + temp);
		assertEquals(temp.length(),36);
		//assertEquals(temp,"Name1Tel1Name2Tel2Name4Tel4Name3Tel3");
		
	
		temp = "";
		
		customer2.addOrder(order1);
		customer2.addOrder(order2);
		customer1.addOrder(order3);
		customer1.addOrder(order4);
		
		for (ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order o : customer1.friendOrders())
		{
			temp += o.getName();
			temp += o.getTel();
		}
		
		for (ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order o : customer2.friendOrders())
		{
			temp += o.getName();
			temp += o.getTel();
		}
		
		System.out.println("2:" + temp);
		assertEquals(temp.length(),36);
	
		
		// 리팩토링후
		/*
		ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order order1 = new ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order ();
		ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order order2 = new ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order ();
		ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order order3 = new ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order ();
		ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order order4 = new ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order ();
		
		ref.sample.ChangeBidirectionalAssociationToUnidirectional.Customer customer1 = new ref.sample.ChangeBidirectionalAssociationToUnidirectional.Customer();
		ref.sample.ChangeBidirectionalAssociationToUnidirectional.Customer customer2 = new ref.sample.ChangeBidirectionalAssociationToUnidirectional.Customer();
		
		String temp="";
		order1.setName("Name1");
		order1.setTel("Tel1");
		order2.setName("Name2");
		order2.setTel("Tel2");
		order3.setName("Name3");
		order3.setTel("Tel3");
		order4.setName("Name4");
		order4.setTel("Tel4");
		
		customer1.addOrder(order1);
		customer1.addOrder(order2);
		customer1.setDisCount(2);
		customer2.addOrder(order3);
		customer2.addOrder(order4);
		customer2.setDisCount(4);
		
		System.out.println("ChangeBidirectionalAssociationToUnidirectionalTest1==================================");
		
		for (ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order o : customer1.friendOrders())
		{
			temp += o.getName();
			temp += o.getTel();
			assertEquals(customer1.getPriceFor(o),1);
		}

		for (ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order o : customer2.friendOrders())
		{
			temp += o.getName();
			temp += o.getTel();
			assertEquals(customer2.getPriceFor(o),3);
		}
		
		System.out.println("1:" + temp);
		assertEquals(temp.length(),36);
		//assertEquals(temp,"Name1Tel1Name2Tel2Name4Tel4Name3Tel3");
		
	
		temp = "";
		
		customer2.addOrder(order1);
		customer2.addOrder(order2);
		customer1.addOrder(order3);
		customer1.addOrder(order4);
		
		

		for (ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order o : customer1.friendOrders())
		{
			temp += o.getName();
			temp += o.getTel();
		}
		
		for (ref.sample.ChangeBidirectionalAssociationToUnidirectional.Order o : customer2.friendOrders())
		{
			temp += o.getName();
			temp += o.getTel();
		}
		
		System.out.println("2:" + temp);
		assertEquals(temp.length(),36);
		*/
		
	}
	
	@Test public void  
	ReplaceInheritanceWithDelegationTest()
	{
		MyStack myStack = new MyStack();
		
		myStack.push("pushString");
		String temp = myStack.pop();
		
		assertEquals(temp ,"pushString");
		
		
	}
	@Test public void  
	IntroduceForeignMethodTest()
	{
		IntroduceForeignMethod introduceForeignMethod = new IntroduceForeignMethod();
		java.text.SimpleDateFormat formatter =
	            new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
		
		
		Date d =  new java.util.Date(2010-1900,0,1);
		assertEquals(formatter.format(introduceForeignMethod.newStart(d)) ,"20100102");
		
		
	}
	@Test public void 
	IntroduceLocalExtensionTest()
	{
		// 리팩토링전

		IntroduceLocalExtension introduceLocalExtension = new IntroduceLocalExtension();
		java.text.SimpleDateFormat formatter =
	            new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
		
		
		Date d =  new java.util.Date(2010-1900,0,1);
		assertEquals(formatter.format(introduceLocalExtension.newStart(d)) ,"20100102");

		
		
		// 리팩토링후
		/*
		IntroduceLocalExtension introduceLocalExtension = new IntroduceLocalExtension(2010-1900,0,1);
		java.text.SimpleDateFormat formatter =
	            new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
		
		assertEquals(formatter.format(introduceLocalExtension.newStart()) ,"20100102");
		 */
	}
	
	@Test public void 
	IntroduceAssertionTest()
	{
		IntroduceAssertion introduceAssertion = new IntroduceAssertion();
		
		
		assertEquals(introduceAssertion.getCondition(),"conditionA");
		
		introduceAssertion.setConditionB("conditionB");
		
		assertEquals(introduceAssertion.getCondition(),"conditionB");
		
		//introduceAssertion.setConditionB(null);
		//introduceAssertion.getCondition();
		
	}
	
	@Test public void 
	EncapsulateCollectionTest()
	{
		
		// 리팩토링전
		
		ref.sample.EncapsulateCollection.Person kent = new ref.sample.EncapsulateCollection.Person();
	    Set s = new HashSet();
	    s.add(new ref.sample.EncapsulateCollection.Course ("Smalltalk Programming", false));
	    s.add(new ref.sample.EncapsulateCollection.Course ("Appreciating Single Malts", true));
	    kent.setCourses(s);
	    assertEquals(2, kent.getCourses().size());
	    ref.sample.EncapsulateCollection.Course refact = new ref.sample.EncapsulateCollection.Course ("Refactoring", true);
	    kent.getCourses().add(refact);
	    kent.getCourses().add(new ref.sample.EncapsulateCollection.Course ("Brutal Sarcasm", false));
	    assertEquals (4, kent.getCourses().size());
	    kent.getCourses().remove(refact);
	    assertEquals (3, kent.getCourses().size());
		
	    
	    Iterator iter = kent.getCourses().iterator();
	    int count = 0;
	    while (iter.hasNext()) {
	    	ref.sample.EncapsulateCollection.Course each = (ref.sample.EncapsulateCollection.Course) iter.next();
	       if (each.isAdvanced()) count ++;
	    }
	    
	    assertEquals (1, count);
		
		
		// 리팩토링후
	    /*
		ref.sample.EncapsulateCollection.Person kent = new ref.sample.EncapsulateCollection.Person();
	    Set s = new HashSet();
	    s.add(new ref.sample.EncapsulateCollection.Course ("Smalltalk Programming", false));
	    s.add(new ref.sample.EncapsulateCollection.Course ("Appreciating Single Malts", true));
	    kent.initializeCourses(s);
	    assertEquals(2, kent.getCourses().size());
	    ref.sample.EncapsulateCollection.Course refact = new ref.sample.EncapsulateCollection.Course ("Refactoring", true);
	    kent.addCourse(refact);
	    kent.addCourse(new ref.sample.EncapsulateCollection.Course ("Brutal Sarcasm", false));
	    assertEquals (4, kent.getCourses().size());
	    kent.removeCourse(refact);
	    assertEquals (3, kent.getCourses().size());
		
	    
	    int count = kent.numberOfAdvancedCourses();
	    
	    assertEquals (1, count);
	    */
	    
	}


	@Test public void 
	ChargeTest()
	{
		Charge charge = new Charge();
		
		assertEquals((int)charge.calculate(Charge.BUS, 10, 10),300);
		assertEquals((int)charge.calculate(Charge.BUS, 20, 10),600);
		assertEquals((int)charge.calculate(Charge.BUS, 70, 10),420);
		
		assertEquals((int)charge.calculate(Charge.SUBWAY, 10, 10),500);
		assertEquals((int)charge.calculate(Charge.SUBWAY, 20, 10),1000);
		assertEquals((int)charge.calculate(Charge.SUBWAY, 70, 10),700);
		assertEquals((int)charge.calculate(Charge.SUBWAY, 10, 60),750);
		assertEquals((int)charge.calculate(Charge.SUBWAY, 20, 60),1500);
		assertEquals((int)charge.calculate(Charge.SUBWAY, 70, 60),1050);
		
		assertEquals((int)charge.calculate(Charge.BUS, 10, 10),300);
		assertEquals((int)charge.calculate(Charge.BUS, 20, 20),600);
		assertEquals((int)charge.calculate(Charge.BUS, 70, 30),420);
		
		
	}	
	
}
