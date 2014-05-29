/**
 * @FileName  : memberLogin.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 27. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package scenario.process.member;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import scenario.common.BasePage;

public class MemberLogin extends BasePage {

	
	/**
	 * @param driver
	 * @param baseUrl
	 * @param orderUrl
	 */
	public MemberLogin(WebDriver driver, String baseUrl, String orderUrl) {
		super(driver, baseUrl, orderUrl);
		// TODO Auto-generated constructor stub
	}

	public void login() throws Exception {
		    driver.get(baseUrl + "/page/getPage.do?pageTypeCd=M");
		    driver.findElement(By.linkText("로그인")).click();
		    driver.findElement(By.id("loginName")).clear();
		    driver.findElement(By.id("loginName")).sendKeys("codelife");
		    driver.findElement(By.id("passWord")).clear();
		    driver.findElement(By.id("passWord")).sendKeys("ail733");
		    driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
		    
		    loginCheckPoint();

		  }
	
	private void loginCheckPoint()
	{
		    WebDriverWait _wait = new WebDriverWait(this.driver, 10);
		    WebElement chkPoint = driver.findElement(By.cssSelector("#PH_S1_M1_C3"));
		    _wait.until(ExpectedConditions.textToBePresentInElement(chkPoint,"로그아웃"));
	}
	
	  
}
