/**
 * 
 */
package test.junit.ref;

import static org.junit.Assert.*;




import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ref.sample.DecomposeConditional.DecomposeConditional;
import ref.sample.ExtractClass.Person;
import ref.sample.ExtractInterface.ExtractInterface;
import ref.sample.ExtractInterface.ExtractInterfaceImpl;
import ref.sample.ExtractSubclass.CalcSpecDay;
import ref.sample.Extract_Method.ExtractMethod;
import ref.sample.Extract_Method.OrderBO;
import ref.sample.Extract_Method.ReplaceTempWithQuery;
import ref.sample.PullUpMethod.Customer;
import ref.sample.PullUpMethod.PreferredCustomer;
import ref.sample.PullUpMethod.RegularCustomer;
import ref.sample.ReplaceMethodWithMethodObject.Account;
import ref.sample.ReplaceParameterWithMethod.ReplaceParameterWithMethod;
import ref.sample.FormTemplateMethod.HtmlStatement;
import ref.sample.FormTemplateMethod.Rental;
import ref.sample.FormTemplateMethod.TextStatement;
import ref.sample.IntroduceParameterObject.FlowBetweenParameter;
import ref.sample.IntroduceParameterObject.IntroduceParameterObject;
import ref.sample.ExtractSubclass.CalPay;




/**
 * @author codelife
 *
 */
public class RefTestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void ExtractMethodTest() {
		ExtractMethod m = new ExtractMethod();
		
		Vector<OrderBO> v = new  Vector<OrderBO>(3);
		
		v.addElement(new OrderBO(100));
		v.addElement(new OrderBO(200));
		v.addElement(new OrderBO(300));
		
		
		m.printOwing(v);
		
	}
	
	@Test
	public void ReplaceTempWithQueryTest() {
		ReplaceTempWithQuery m = new ReplaceTempWithQuery();
		
		Vector<OrderBO> v = new  Vector<OrderBO>(3);
		
		v.addElement(new OrderBO(100));
		v.addElement(new OrderBO(200));
		v.addElement(new OrderBO(300));
		
		
		m.printOwing(v);
		
	}
	
	@Test
	public void PullUpMethodTest() {
		PreferredCustomer 	m = new PreferredCustomer();
		RegularCustomer 	m2 = new RegularCustomer();
		
		
		m.addWorkingHours(10);
		m2.addWorkingHours(10);
		
		assertTrue(m.createBill(1) == 15);
		assertTrue(m2.createBill(2) == 10);
	}
	
	@Test
	public void FormTemplateMethodTest()
	{
		HtmlStatement h = new HtmlStatement();
		TextStatement t = new TextStatement();
		Vector<Rental> v = new  Vector<Rental>(1);
		v.addElement( new Rental("리팩토링",10));
		
		h.setvRentals(v);
		t.setvRentals(v);
		
		
		System.out.println(h.statement());
		System.out.println(t.statement());
		
	}
	
	@Test
	public void ExtractClassTest()
	{
		Person person = new Person();
		
		person.setName("Name");
		person.setOfficeAreaCode("OfficeAreaCode");
		person.setOfficeNumber("OfficeNumber");
		
		assertEquals("Name", person.getName());
		assertEquals("OfficeAreaCode", person.getOfficeAreaCode());
		assertEquals("OfficeNumber", person.getOfficeNumber());
		
		
	}
	
	
	@Test
	public void IntroduceParameterObjectTest()
	{
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
		
	}
	

	

	@Test
	public void ReplaceMethodWithMethodObject()
	{
		Account account	= new Account();
	
		assertEquals(account.gamma(10, 20, 30),2270);

	}
	
	@Test
	public void DecomposeConditionalTest()
	{
		DecomposeConditional decomposeConditional = new DecomposeConditional();
		
		System.out.println("######DecomposeConditionalTest:" + decomposeConditional.compute(5, 3, 6, 9));
		assertEquals( decomposeConditional.compute(5, 3, 6, 9),9);
		
	}
	
	
	@Test
	public void ExtractSubclassTest()
	{
		CalcSpecDay calcSpecDay = new CalcSpecDay(10,10);
		CalPay 		calPay 	= new CalPay(10,10);
		
		System.out.println("######ExtractSubclassTest:" +calcSpecDay.doCalc());
		System.out.println("######ExtractSubclassTest:" +calPay.doCalc());
		
		assertEquals( calcSpecDay.doCalc(),200);
		assertEquals( calPay.doCalc(),100);
		
		
	}
	
	@Test
	public void ExtractInterfaceImplTest() {
/*
 		// 리팩토링 전
		ExtractInterfaceImpl m = new ExtractInterfaceImpl();
		
		Vector<OrderBO> v = new  Vector<OrderBO>(3);
		
		v.addElement(new OrderBO(100));
		v.addElement(new OrderBO(200));
		v.addElement(new OrderBO(300));
		
		m.printOwing(v);
*/		
		
		// 리팩토링 후
		ExtractInterface m = new ExtractInterfaceImpl();
		
		Vector<OrderBO> v = new  Vector<OrderBO>(3);
		
		v.addElement(new OrderBO(100));
		v.addElement(new OrderBO(200));
		v.addElement(new OrderBO(300));
		
		m.printOwing(v);

		
	}

	
	@Test
	public void ReplaceParameterWithMethodTest()
	{
		ReplaceParameterWithMethod replaceParameterWithMethod = new ReplaceParameterWithMethod(10,10);
		
		
		System.out.println("######ReplaceParameterWithMethodTest:" +replaceParameterWithMethod.getPrice());
		assertEquals((int)replaceParameterWithMethod.getPrice(),(int)5);
		
	}
	
}
