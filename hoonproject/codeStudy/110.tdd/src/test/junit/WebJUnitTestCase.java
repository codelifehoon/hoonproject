/**
 * @FileName  : WebTestCase.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 26. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package test.junit;


import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebJUnitTestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void webTest() {

        FirefoxDriver d1 = new FirefoxDriver();
        d1.get("https://order.guesskorea.com/login.do");
        
        //String path = "D:\\workspace\\webDrive"; // System.getProperty("user.dir"); // current path of project
        //System.setProperty("webdriver.chrome.driver",path + "\\chromedriver.exe");
        //ChromeDriver d2 = new ChromeDriver();
        //d2.get("http://coronasdk.tistory.com");
        
        //System.setProperty("webdriver.ie.driver",path + "\\IEDriverServer.exe");
        //InternetExplorerDriver d3 = new InternetExplorerDriver();
        //d3.get("http://www.qtpselenium.com");
        
        WebElement username = d1.findElement(By.cssSelector("#loginName "));
        WebElement pw = d1.findElement(By.cssSelector("#passWord"));
        WebElement signInBtn = d1.findElement(By.cssSelector("#content div.login_form_main div.mem_login div.member_radio_wrap form fieldset div.member_login span.btn_submit3 input"));
        
        username.sendKeys("codelife");
        pw.sendKeys("ail733");
        signInBtn.click();
        
        
        String title = d1.getTitle();
        System.out.println(title);
    
	}

}
