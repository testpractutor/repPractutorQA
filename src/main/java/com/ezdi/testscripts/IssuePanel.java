package com.ezdi.testscripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.webelements.Comman_WebE;
import com.ezdi.webelements.IssuePnl_WebE;
import com.ezdi.webelements.Login_WebE;

public class IssuePanel
{
	public static WebDriver		driver;
	public static WebDriverWait	wait;
	public static Comman_WebE	common_webe;
	public static IssuePnl_WebE	issuepnl_webe;
	public static Login_WebE	login_webe;

	@BeforeClass
	public static void IssuePanelBeforeClass()
	{
		try
		{
			driver = ExecutionSetup.getDriver();
			common_webe = Comman_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			login_webe = Login_WebE.getInstance(driver);
			//wait = new WebDriverWait(driver, 30);

			Log4J.logp.info("In BeforeClass for IssuePanel");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	@AfterClass
	public static void IssuePanelAfterClass()
	{
		try
		{
			if (driver != null)
			{
				driver = null;
			}
			if (wait != null)
			{
				wait = null;
			}
			if (common_webe != null)
			{
				common_webe = null;
			}
			if (issuepnl_webe != null)
			{
				issuepnl_webe = null;
			}
			if (login_webe != null)
			{
				login_webe = null;
			}

			Log4J.logp.info("In AfterClass for IssuePanel");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
