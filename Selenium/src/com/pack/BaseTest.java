package com.pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest 
{
	public static WebDriver driver;
	public static Properties p;
	public static Properties or;
	public static FileInputStream fis;
	public static String projectpath="./";
	public static SoftAssert s;
	public static String screenshotFileName=null;
	
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
	
	
	
	public static List<WebElement> getElements(String locatorKey) 
	{
		List<WebElement> elementList=null;
		
			try 
			{
				if(locatorKey.endsWith("_id")) {
					elementList=driver.findElements(By.id(or.getProperty(locatorKey)));
				}else if(locatorKey.endsWith("_name")) {
					elementList=driver.findElements(By.name(or.getProperty(locatorKey)));
				}else if(locatorKey.endsWith("_classname")) {
					elementList=driver.findElements(By.className(or.getProperty(locatorKey)));
				}else if(locatorKey.endsWith("_linktext")) {
					elementList=driver.findElements(By.linkText(or.getProperty(locatorKey)));
				}else if(locatorKey.endsWith("_xpath")) {
					elementList=driver.findElements(By.xpath(or.getProperty(locatorKey)));
				}else if(locatorKey.endsWith("_css")) {
					elementList=driver.findElements(By.cssSelector(or.getProperty(locatorKey)));
				}
				else
				{
					reportFailure("Locator not Correct -" + locatorKey);
					Assert.fail("Locator not Correct -" + locatorKey);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return elementList;	
	}
	


	public static void click(String locatorKey) 
	{
		getElement(locatorKey).click();
	}
	
	
	/********************************* Validations ****************************/
	public static boolean verifyText(String locatorKey, String expectedValue)
	{
			String actualValue = getElement(locatorKey).getText().trim();
			System.out.println(actualValue);
			if(actualValue.equals(expectedValue))
				return true;
			else 
				return false;
	}
	
	
	public static boolean isElementPresent(String locatorKey)
	{
		List<WebElement> elementList=null;
		
		elementList=getElements(locatorKey);
		if(elementList.size()==0)
			return false;
		else
			return true;
	}

	
/********************************* Reporting ****************************/
	
	public static void reportPass(String msg)
	{
		test.log(LogStatus.PASS, msg);
		takeScreenShot();
	}
	
	
	public static void reportFailure(String msg)
	{
		test.log(LogStatus.FAIL, msg);
		takeScreenShot();
		Assert.fail(msg);
	}
	
	
	
	public static void takeScreenShot() 
	{
		Date dt=new Date();
		screenshotFileName = dt.toString().replace(":", "_").replace(" ", "_")+".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(scrFile, new File(projectpath+"//FailureScreenShots//"+screenshotFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//put screen shot file in extent reports
		//test.log(LogStatus.INFO, "Screenshot --> "+ test.addScreenCapture(System.getProperty("user.dir"))+"//FailureScreenShots//"+screenshotFileName);
		test.log(LogStatus.INFO, "Screenshot --> "+ test.addScreenCapture(projectpath+"//FailureScreenShots//"+screenshotFileName));
	}
	
	
}
