package test.testng.patnerproc.member;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import scenario.process.member.MemberLogin;
import test.testng.patnerproc.common.TestBase;

public class MemberTestCase extends TestBase {
	
	@Test
	public void loginTest() throws Exception {

		WebDriver webDriver = cretaeBrowser();
		MemberLogin memberLogin = new MemberLogin(webDriver,this.getBaseUrl(),this.getOrderUrl());
		memberLogin.login();
		afterMethod(webDriver);
	}



  	
}
