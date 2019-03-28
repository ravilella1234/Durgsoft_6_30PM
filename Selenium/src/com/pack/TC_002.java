package com.pack;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TC_002 extends BaseTest
{

	public static void main(String[] args) throws Exception 
	{
		init();
		
		launch("chromebrowser");
		
		navigate("amazonurl");
		
		WebElement loc = driver.findElement(By.id("twotabsearchtextbox"));
		
		loc.sendKeys("philips");
		
		loc.clear();
		
		driver.findElement(By.name("field-keywords")).sendKeys("sony");
		
		//driver.findElement(By.className("nav-input")).clear();
		
		driver.findElement(By.xpath("//input[@name='field-keywords']")).clear();
		
		driver.findElement(By.cssSelector("#twotabsearchtextbox")).sendKeys("samsung");
		
		driver.navigate().refresh();
		
		//driver.findElement(By.linkText("Customer Service")).click();
		
		driver.findElement(By.partialLinkText("Customer Ser")).click();
		
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for(int i=0;i<links.size();i++)
		{
			System.out.println(links.get(i).getText());
		}
		

	}

}
