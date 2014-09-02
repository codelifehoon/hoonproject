package test.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import example.PageFactory.DriverFactory;
import example.PageFactory.PageBase;
import example.PageFactory.UsingPage;

public class WebTestCase extends PageBase{
	
  @BeforeMethod()
  @Parameters("browser")
  public void launchBrowser(String browser) {
	  
	  setDriver(DriverFactory.selectDriver(browser)) ;
  }
  

  @AfterMethod
  public void afterMethod() {
	  DriverFactory.destoryDriver(getDriver());
	  
  }


  	@Test
	public void webTest() {

      driver.get("https://order.guesskorea.com/login.do");
      
      WebElement pw = driver.findElement(By.cssSelector("#passWord"));
      WebElement username = driver.findElement(By.cssSelector("#loginName"));
      WebElement signInBtn = driver.findElement(By.cssSelector("#content div.login_form_main div.mem_login div.member_radio_wrap form fieldset div.member_login span.btn_submit3 input"));
      
      username.sendKeys("codelife");
      pw.sendKeys("ail733");
      signInBtn.click();
      
      
      String title = driver.getTitle();
      System.out.println("#######Ÿ��Ʋ:" + title);
  
	}
  	
  	
}
