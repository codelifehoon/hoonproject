/**
 * @FileName  : UsingPage.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 27. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.PageFactory;

import org.openqa.selenium.support.PageFactory;

import bsh.This;

import example.PageFactory.pages.GuessLoginPage;

public class UsingPage extends PageBase {
	
	 public void guessLogin()
	{
		
	    // Navigate to the right place
	    driver.get("https://order.guesskorea.com/login.do");

	    // Create a new instance of the search page class
	    // and initialise any WebElement fields in it.
	    GuessLoginPage gGuessLoginPage = PageFactory.initElements(driver, GuessLoginPage.class);

	    // And now do the search.
	    gGuessLoginPage.loginPage("codelife", "ail733");
	    System.out.println( this.toString() + ":" + driver.getCurrentUrl());
	}
   

    
}
