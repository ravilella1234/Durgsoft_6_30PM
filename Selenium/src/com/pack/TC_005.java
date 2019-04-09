package com.pack;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

public class TC_005 extends BaseTest
{
	private static final Logger log=Logger.getLogger(TC_005.class.getName());
	
	public static void main(String[] args) throws Exception 
	{
		test=e.startTest("TC_005");
		
		init();
		log.info("Properties Objects  (config,OR,log4j) are getting initilized.....");
		test.log(LogStatus.INFO, "Properties Objects  (config,OR,log4j) are getting initilized.....");
		
		launch("chromebrowser");
		log.info("Opened the Browser :- " + p.getProperty("chromebrowser"));
		test.log(LogStatus.INFO, "Opened the Browser :- " + p.getProperty("chromebrowser"));
		
		navigate("amazonurl");
		log.info("Navigated to url :- " + p.getProperty("amazonurl"));
		test.log(LogStatus.INFO, "Navigated to url :- " + p.getProperty("amazonurl"));
		
		String actualLink = driver.findElement(By.linkText("Customer Service")).getAttribute("innerHTML");
		String expectedLink="Customer service";
		
		System.out.println("Actual link :" + actualLink);
		System.out.println("Expected link : " + expectedLink);
		
		//Assert.assertEquals(actualLink, expectedLink);
		
		SoftAssert s=new SoftAssert();
		s.assertEquals(actualLink, expectedLink);
		
		int a=40, b=30;
		s.assertTrue(a>b, "error0");
		
		
		s.assertTrue(false, "error1");
		
		
		
		s.assertTrue(false, "error2");
		
		s.assertTrue(true, "error3");
		
		s.assertTrue(false, "error4");
		
		
		
		type("amazonsearchtext_id","harry Potter");
		log.info("Entered the text - harry Potter by using locator :- " + or.getProperty("amazonsearchtext_id"));
		test.log(LogStatus.INFO, "Entered the text - harry Potter by using locator :- " + or.getProperty("amazonsearchtext_id"));
		
		
		System.out.println("bye bye..");
		
		s.assertAll();
		
	}

}
