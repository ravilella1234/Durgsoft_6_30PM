package com.pack;

import org.apache.log4j.Logger;

import com.relevantcodes.extentreports.LogStatus;

public class TC_006 extends BaseTest
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
		
		if(!verifyText("customerservice_linktext", "Customer Service"))
			reportFailure("LinkText are not Equal");
		else
			reportPass("LinkText are equal...");
			

			
		e.endTest(test);
		e.flush();
			
	}

}
