package com.pack;

import org.openqa.selenium.By;

public class TC_001 extends BaseTest
{

	public static void main(String[] args) throws Exception 
	{
		init();
				
		launch("chromebrowser");
		
		navigate("hdfcurl");
		
		driver.manage().window().maximize();
		
		driver.findElement(By.id("loginsubmit")).click();
		
		/*String title = driver.getTitle();
		System.out.println(title);
		
		String url = driver.getCurrentUrl();
		System.out.println(url);
		
		driver.manage().deleteAllCookies();
		
		driver.navigate().back();
		
		Thread.sleep(4000);
		
		driver.navigate().forward();
		
		Thread.sleep(4000);
		
		driver.navigate().refresh();*/
		
		//driver.close();
		
		driver.quit();

	}

}
