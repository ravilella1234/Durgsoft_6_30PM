package com.project.EMobiles;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;

public class TC_103 extends BaseTest
{
	
 
  @BeforeMethod
  @Parameters("browser")
  public void startProcess(String browserType) throws Exception 
  {
	  init();
	  launch(browserType);
	  
  }
  
  @Test
  public void amazon() 
  {
	  test=e.startTest("amazon");
	  
	  
	  
	  navigate("amazonurl");
	  test.log(LogStatus.INFO, "Navigated to url :- " + p.getProperty("amazonurl"));
	  
	  selectItem("amazondropdown_id", 10);
	  test.log(LogStatus.INFO, "Selected the item 10  By using Locator :- " + or.getProperty("amazondropdown_id"));
	  
	  type("amazonsearchtext_id", "harry potter");
	  test.log(LogStatus.INFO, "Entered the Text Harry Potter By using the Locator :- " + or.getProperty("amazonsearchtext_id"));
	  
	  click("amazonsearchbutton_xpath");
	  test.log(LogStatus.INFO, "Clicked on Amazon Search Button By using Locator :- " + or.getProperty("amazonsearchbutton_xpath"));
  }

  @AfterMethod
  public void endProcess()
  {
	  e.endTest(test);
	  e.flush();
	  closeBrowser();
  }



}
