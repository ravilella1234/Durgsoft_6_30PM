package com.pack;

import org.apache.log4j.Logger;

public class TC_003 extends BaseTest 
{
	protected static final Logger log=Logger.getLogger(TC_003.class.getName());
		
	public static void main(String[] args) throws Exception 
	{
		init();
		log.info("Loded... Config, OR, Log4j properties Files");
		
		launch("chromebrowser");
		log.info("Opened the Browser :- " + p.getProperty("chromebrowser"));
		
		navigate("amazonurl");
		log.info("Navigated to Url :- " + p.getProperty("amazonurl"));
		
		selectItem("amazondropdown_id",10);
		log.info("Selected the item 10  By using Locator :- " + or.getProperty("amazondropdown_id"));
		
		type("amazonsearchtext_id","harry Potter");
		log.info("Entered the Text Harry Potter By using the Locator :- " + or.getProperty("amazonsearchtext_id"));
		
		click("amazonsearchbutton_xpath");
		log.info("Clicked on Amazon Search Button By using Locator :- " + or.getProperty("amazonsearchbutton_xpath"));
		
	}

}
