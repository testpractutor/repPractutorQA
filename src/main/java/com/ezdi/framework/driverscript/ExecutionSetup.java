package com.ezdi.framework.driverscript;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ezdi.framework.common.ComframUtils;
import com.ezdi.framework.common.Log4J;

public class ExecutionSetup
{

	static WebDriver	driver;

	static boolean		loginStatus;

	@BeforeSuite
	public static void setUp()
	{
		try
		{

			System.out.println("-------------------------> In BeforeSuite");
			driver = ComframUtils.launchURL(DriverTestNG.strBrowser);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(DriverTestNG.url);
			driver.manage().window().maximize();
			if (DriverTestNG.strBrowser.equalsIgnoreCase("ie"))
			{

				driver.get("javascript:document.getElementById('overridelink').click();");
				//driver.navigate().to("javascript:document.getElementById('overridelink').click();");
			}
	

		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log4J.logf.error(e);
		}

	}

	@AfterSuite
	public static void tearDown() throws Exception
	{

		try
		{
			Thread.sleep(5000);
			Log4J.logf.info("------------------------------->In AfterSuite");
			//driver.close();
			//driver.quit();
			//Login_Lib.logOut_App();
			// testing commit
			DriverTestNG.selectedConfig.connectionClose();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static WebDriver getDriver()
	{
		return driver;

	}

}
