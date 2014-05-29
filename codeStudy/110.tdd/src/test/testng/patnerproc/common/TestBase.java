/**
 * @FileName  : TestBase.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 27. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package test.testng.patnerproc.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TestBase {


	protected String baseUrl;
	protected String orderUrl;
	protected String browser;

	/**
	 * 
	 */
	public TestBase() {
		super();
	}

	@BeforeMethod()
	@Parameters({"browser","baseUrl","orderUrl"})
	public void launchBrowser(String browser,String baseUrl,String orderUrl) {
		  
	
	      this.browser = browser;
	      this.baseUrl = baseUrl;
	      this.orderUrl = orderUrl;
	      
	      System.out.println("#####launchBrowser#####");
	      
	  }
	public WebDriver cretaeBrowser()
	{
		String path = "D:\\workspace\\webDrive";
		WebDriver driver;
		  
	      if(browser.equalsIgnoreCase("FF")){
	    	  driver = new FirefoxDriver();
	      }	else if(browser.equalsIgnoreCase("Chrome")){
	          System.setProperty("webdriver.chrome.driver",path + "\\chromedriver.exe");
	          driver = new ChromeDriver();
	      } else if(browser.equalsIgnoreCase("IE")){
	          System.setProperty("webdriver.ie.driver",path + "\\IEDriverServer.exe");
	         
	        
	         // DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
	          //caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
	          //caps.setCapability("ignoreZoomSetting", true);
	          //driver = new InternetExplorerDriver(caps);
          
	          driver = new InternetExplorerDriver();
	      }
	      else
	      {
	    	  driver = new HtmlUnitDriver();
	      }
	      
	      System.out.println("#####cretaeBrowser#####");
	     
	      return driver;
	    
	}


	public void afterMethod(WebDriver driver) {
		  driver.close();
		  driver.quit();
	
	  }

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getOrderUrl() {
		return orderUrl;
	}

	public String getBrowser() {
		return browser;
	}



}