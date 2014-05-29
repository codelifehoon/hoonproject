/**
 * @FileName  : PageExtendBase.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 27. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package test.junit;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;

import example.PageFactory.DriverFactory;
import example.PageFactory.PageBase;

public class PageExtendBase extends PageBase {

	  @Before
	  public void setUp() throws Exception {
	    driver =  DriverFactory.selectDriver("FF");
	    baseUrl = "http://www.guesskorea.com/";
	    //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @After
	  public void tearDown() throws Exception {
		  DriverFactory.destoryDriver(driver);
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
	}

