/**
 * @FileName  : DriverFactory.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 27. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class DriverFactory {

	public static WebDriver selectDriver(String browser) {
		  String path = "D:\\workspace\\webDrive";
		  WebDriver driver;
		  
	      if(browser.equalsIgnoreCase("FF")){
	          driver = new FirefoxDriver();
	      }	else if(browser.equalsIgnoreCase("Chrome")){
	          System.setProperty("webdriver.chrome.driver",path + "\\chromedriver.exe");
	          driver = new ChromeDriver();
	      }
	      else
	      {
	    	  driver = new HtmlUnitDriver();
	      }
	      
	      return driver;
	}
	
	public static void destoryDriver( WebDriver driver)
	{
		 driver.close();
	     driver.quit();
	}
	
	
}
