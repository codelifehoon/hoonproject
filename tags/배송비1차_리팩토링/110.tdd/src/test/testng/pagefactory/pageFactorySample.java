package test.testng.pagefactory;

import org.testng.annotations.Test;

import example.PageFactory.DriverFactory;
import example.PageFactory.UsingPage;

public class pageFactorySample  {
  
  @Test
  public void UsingPageTest() {
	  
	  UsingPage usingPage = new UsingPage();
	  usingPage.setDriver(DriverFactory.selectDriver("FF"));
	  usingPage.guessLogin();
	  DriverFactory.destoryDriver(usingPage.getDriver());
	  
  }
}
