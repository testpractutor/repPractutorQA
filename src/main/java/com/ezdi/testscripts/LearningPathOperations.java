package com.ezdi.testscripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.DriverTestNG;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.webelements.Comman_WebE;
import com.ezdi.library.LearningPathOp_Lib;

public class LearningPathOperations {
	
	
	public static WebDriver				driver;
	public static Comman_WebE			common_webe;
	
	@BeforeClass
	public static void ViewCaseBeforeClass()
	{
		try
		{
			driver = ExecutionSetup.getDriver();
			common_webe = Comman_WebE.getInstance(driver);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
				
	}
	

	@Test(description = "Vefity PracCity Element buying",  priority = 1)
		public static void buyPracCityElement()
	{
		boolean bstatus;

		bstatus = LearningPathOp_Lib.buyCityElement_App();
		if (bstatus){
			Log4J.logp.info("Buying City Element sucessfull -From TestScript "  );
		} else{
			Log4J.logp.error("Buying City Element UN-sucessfully -From TestScript" );
		}
	}

	
	@AfterClass
	public static void ViewCaseAfterClass()
	{
		try
		{
			driver = null;
			common_webe = null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	}	
	

