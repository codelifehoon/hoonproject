/**
 * @FileName  : OrderTestCase.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 28. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package test.testng.patnerproc.order;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import scenario.process.member.MemberLogin;
import scenario.process.order.OrderSheet;
import test.testng.patnerproc.common.TestBase;

public class OrderTestCase extends TestBase {


	//@Test
	public void orderSheetTest() throws Exception {

		WebDriver webDriver = cretaeBrowser();
		
		//webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		//webDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
			
		MemberLogin memberLogin = new MemberLogin(webDriver,this.getBaseUrl(),this.getOrderUrl());
		OrderSheet orderSheet 	= new OrderSheet(webDriver,this.getBaseUrl(),this.getOrderUrl());
		memberLogin.login();
		orderSheet.goodsOrderSheet();
		afterMethod(webDriver);
		
	}

	@Test
	public void orderTest() throws Exception {

		WebDriver webDriver = cretaeBrowser();
		MemberLogin memberLogin = new MemberLogin(webDriver,this.getBaseUrl(),this.getOrderUrl());
		OrderSheet orderSheet 	= new OrderSheet(webDriver,this.getBaseUrl(),this.getOrderUrl());
		
		memberLogin.login();
		orderSheet.goodsOrderSheet();
		orderSheet.orderProc();
		afterMethod(webDriver);
	}


	
	
}
