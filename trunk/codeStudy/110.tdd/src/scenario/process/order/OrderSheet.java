/**
 * @FileName  : OrderSheet.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 28. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package scenario.process.order;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import scenario.common.BasePage;
import test.testng.patnerproc.common.TestBase;


public class OrderSheet extends BasePage {

	  /**
	 * @param driver
	 * @param baseUrl
	 * @param orderUrl
	 */
	public OrderSheet(WebDriver driver,TestBase base) {
		super(driver, base);
		// TODO Auto-generated constructor stub
	}


	public void goodsOrderSheet() throws Exception {
	    driver.get(baseUrl + "/page/getPage.do?pageTypeCd=PD&prdNo=233626539");
	    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=Parent_window | ]]
	    new Select(driver.findElement(By.id("optionSel"))).selectByVisibleText("24");
	    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=Parent_window | ]]
	    driver.findElement(By.id("buynow")).click();
	    goodsOrderSheetCheckPoint();
	    saveImage("OrderSheet_goodsOrderSheet");
	  
	  }
	
	
	 
	  
	  public void orderProc()
	  {
		  	
		    
		    //driver.get(baseUrl + "/pay/newOrderStep1/getOrderInfo.do");
		    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=Parent_window | ]]
		    // ERROR: Caught exception [Error: Dom locators are not implemented yet!]
		    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=Parent_window | ]]
		    driver.findElement(By.cssSelector("#chagename")).click();
		    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=Parent_window | ]]
		    driver.findElement(By.name("ordNm")).clear();
		    driver.findElement(By.name("ordNm")).sendKeys("장재훈2");
		    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=Parent_window | ]]
		    driver.findElement(By.id("yes")).click();
		    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=Parent_window | ]]
		    driver.findElement(By.id("rdPayMethod_5101")).click();
		    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=Parent_window | ]]
		    new Select(driver.findElement(By.id("bankKind"))).selectByVisibleText("농협");
		    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=Parent_window | ]]
		    driver.findElement(By.id("chkReceiptIssueNo")).click();
		    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=Parent_window | ]]
		    driver.findElement(By.linkText("결제하기")).click();
		    saveImage("OrderSheet_orderProc");
		    
	  }


	  private void goodsOrderSheetCheckPoint()
		{
		  	WebDriverWait _wait = new WebDriverWait(this.driver, 10);
		    _wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#chagename")));
		}
	  
}
