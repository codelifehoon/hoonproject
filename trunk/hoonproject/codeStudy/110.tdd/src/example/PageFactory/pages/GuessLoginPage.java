/**
 * @FileName  : GuessLoginPage.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 27. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.PageFactory.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GuessLoginPage {
	
	@FindBy(css="#loginName")
	private WebElement loginId;
	@FindBy(css="#passWord")
	private WebElement password;
	@FindBy(css="#content div.login_form_main div.mem_login div.member_radio_wrap form fieldset div.member_login span.btn_submit3 input")
	private WebElement submitBtn;
	
	

	public void loginPage(String loginId, String password) {
	
		this.loginId.sendKeys(loginId) ;
		this.password.sendKeys(password);
		
		this.submitBtn.click();
	}
	
}
