package com.pack;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

public class TC_004 extends BaseTest
{
	private static final Logger log=Logger.getLogger(TC_004.class.getName());
	
	public static void main(String[] args) throws Exception 
	{
		test=e.startTest("TC_004");
		
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
		String expectedLink="Customer Service";
		
		System.out.println("Actual link :" + actualLink);
		System.out.println("Expected link : " + expectedLink);
		
		//if(actualLink.equals(expectedLink))
		//if(actualLink.equalsIgnoreCase(expectedLink))
		//if(actualLink.contains(expectedLink))
		if(actualLink.contentEquals(expectedLink))
		{
			System.out.println("Both links are equal...");
		}
		else
		{
			System.out.println("Both links are not equal...");
		}
		
		
		
		WebElement loc = driver.findElement(By.id("twotabsearchtextbox"));
		loc.sendKeys("sony");
		String actvalue = loc.getText();
		System.out.println(actvalue);
		
		
		
	}

}
