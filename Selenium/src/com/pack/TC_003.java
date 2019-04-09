package com.pack;

import org.apache.log4j.Logger;

import com.relevantcodes.extentreports.LogStatus;

public class TC_003 extends BaseTest 
{
	protected static final Logger log=Logger.getLogger(TC_003.class.getName());
		
	public static void main(String[] args) throws Exception 
	{
		test=e.startTest("TC_003");
		
		init();
		log.info("Loded... Config, OR, Log4j properties Files");
		test.log(LogStatus.INFO, "Loded... Config, OR, Log4j properties Files");
		
		
		launch("chromebrowser");
		log.info("Opened the Browser :- " + p.getProperty("chromebrowser"));
		test.log(LogStatus.INFO, "Opened the Browser :- " + p.getProperty("chromebrowser"));
		
		navigate("amazonurl");
		log.info("Navigated to Url :- " + p.getProperty("amazonurl"));
		test.log(LogStatus.INFO, "Navigated to Url :- " + p.getProperty("amazonurl"));
		
		selectItem("amazondropdown_id",10);
		log.info("Selected the item 10  By using Locator :- " + or.getProperty("amazondropdown_id"));
		test.log(LogStatus.INFO, "Selected the item 10  By using Locator :- " + or.getProperty("amazondropdown_id"));
		
		type("amazonsearchtext_id","harry Potter");
		log.info("Entered the Text Harry Potter By using the Locator :- " + or.getProperty("amazonsearchtext_id"));
		test.log(LogStatus.INFO, "Entered the Text Harry Potter By using the Locator :- " + or.getProperty("amazonsearchtext_id"));
		
		click("amazonsearchbutton_xpath");
		log.info("Clicked on Amazon Search Button By using Locator :- " + or.getProperty("amazonsearchbutton_xpath"));
		test.log(LogStatus.INFO, "Clicked on Amazon Search Button By using Locator :- " + or.getProperty("amazonsearchbutton_xpath"));
		
		e.endTest(test);
		e.flush();
	}

}
