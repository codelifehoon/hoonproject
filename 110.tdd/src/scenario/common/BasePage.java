/**
 * @FileName  : DriverSelector.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 27. 
 * @�ۼ���      : codelife
 * @�����̷� :
 * @���α׷� ���� :
 */
package scenario.common;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import test.testng.patnerproc.common.TestBase;

public abstract class BasePage {

	protected WebDriver driver;
	protected String baseUrl;
	protected String orderUrl;
	
	
	public BasePage(WebDriver driver,String baseUrl , String orderUrl) {
		super();
		this.driver = driver;		
		this.baseUrl = baseUrl;
		this.orderUrl = orderUrl;
	}

	private boolean acceptNextAlert = true;
	protected StringBuffer verificationErrors = new StringBuffer();

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getOrderUrl() {
		return orderUrl;
	}

	public void setOrderUrl(String orderUrl) {
		this.orderUrl = orderUrl;
	}
	
}