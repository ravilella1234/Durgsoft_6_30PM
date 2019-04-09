package com.pack;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTest 
{
	public static WebDriver driver;
	public static Properties p;
	public static Properties or;
	public static FileInputStream fis;
	public static String projectpath="./";
	
	
	//Extent Report Initialization
	public static ExtentReports e = ExtentManager.getInstance();
	public static ExtentTest test;
	
	
	public static void init() throws Exception
	{
		p=new Properties();
		fis=new FileInputStream(projectpath+"//config.properties");
		p.load(fis);
		
		or=new Properties();
		fis=new FileInputStream(projectpath+"//OR.properties");
		or.load(fis);
		
		PropertyConfigurator.configure(projectpath+"log4j.properties");
		
	}
	
		
	
	public static void launch(String browser)
	{
		if(p.getProperty(browser).equals("chrome")){
			System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, System.getProperty("user.dir")+"//drivers//chromedriver.exe");
			driver=new ChromeDriver();
		}else if (p.getProperty(browser).equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//drivers//geckodriver.exe" );
			driver=new FirefoxDriver();
		}else if (p.getProperty(browser).equals("ie")) {
			System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY, System.getProperty("user.dir")+"//drivers//IEDriverServer.exe" );
			driver=new InternetExplorerDriver();
		}else if (p.getProperty(browser).equals("edge")) {
			System.setProperty(EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY, System.getProperty("user.dir")+"//drivers//MicrosoftWebDriver.exe" );
			driver=new EdgeDriver();
		}	
		
		driver.manage().window().maximize();
	}
	
	public static void navigate(String url)
	{
		//driver.get(p.getProperty(url));
		driver.navigate().to(p.getProperty(url));
	}
	
	public static void selectItem(String locatorKey, int item) 
	{
		Select s=new Select(getElement(locatorKey));
		s.selectByIndex(item);
	}
	
	
	public static void type(String locatorKey, String text) 
	{
		getElement(locatorKey).sendKeys(text);
	}
	
	
	public static WebElement getElement(String locatorKey) 
	{
		WebElement element=null;
		if(locatorKey.endsWith("_id")){
			element=driver.findElement(By.id(or.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_name")) {
			element=driver.findElement(By.name(or.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_classname")) {
			element=driver.findElement(By.className(or.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_xpath")) {
			element=driver.findElement(By.xpath(or.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_css")) {
			element=driver.findElement(By.cssSelector(or.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_linktext")) {
			element=driver.findElement(By.linkText(or.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_partiallink")) {
			element=driver.findElement(By.partialLinkText(or.getProperty(locatorKey)));
		}
		return element;
		
	}


	public static void click(String locatorKey) 
	{
		getElement(locatorKey).click();
	}

}
