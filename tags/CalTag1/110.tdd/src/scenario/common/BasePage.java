/**
 * @FileName  : DriverSelector.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 27. 
 * @�ۼ���      : codelife
 * @�����̷� :
 * @���α׷� ���� :
 */
package scenario.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import test.testng.patnerproc.common.TestBase;

public abstract class BasePage {

	protected WebDriver driver;
	protected String baseUrl;
	protected String orderUrl;
	protected String browser;
	protected String screenShotYn;
	
	
	public BasePage(WebDriver driver,TestBase base) {
		super();
		this.driver = driver;		
		this.baseUrl = base.getBaseUrl();
		this.orderUrl = base.getOrderUrl();
		this.browser = base.getBrowser();
		this.screenShotYn = base.getScreenShotYn();
		
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

	public void saveImage(String URL)
	{
		if (!"Y".equals(screenShotYn) && "HtmlUnitDriver".equals(browser)) return;
		
		URL = URL.replaceAll("/","_");
		String fileName = "d:\\ATimage\\" + browser + "_" + URL + ".png";
		System.out.println("fileName#####:" + fileName);
		 // 스냅샷 생성
      File scrFile =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
 
		try {
			
        		FileUtils.copyFile(scrFile, new File(fileName));
			} catch (IOException e) {
			     e.printStackTrace();
			}
	}
	
	
	
}