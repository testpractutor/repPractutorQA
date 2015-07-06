package com.ezdi.testscripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.DriverTestNG;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.webelements.Comman_WebE;
import com.ezdi.library.LearningPathOp_Lib;
import com.ezdi.library.Login_Lib;

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
			Login_Lib.login("bc001");
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
				
	}
	
	/**
	 * This script check different landing page after login with different user *
	 *
	 * @author bchauhan
	 * @since 24/4/2015
	 */
	
	@Test(description = "Vefity PracCity Element buying",  priority = 1)
	public static void buyPracCityElement()
	{
		boolean bstatus;
		bstatus = LearningPathOp_Lib.buyCityElement_App();
		if (bstatus)
		{
			Log4J.logp.info("Buying City Element sucessfull -From TestScript "  );			
			Assert.assertTrue(bstatus, "Buying City Element sucessfull -From TestScript " );
		} 
		else
		{
			Log4J.logp.error("Buying City Element UN-sucessfull -From TestScript" );
			Assert.assertTrue(bstatus, "Buying City Element Un-sucessfull -From TestScript " );
		}
	}
	
//	@Test(description = "Vefity Add class Details:TEACHER",  priority = 1)
//	public static void addClassDeatils()
//	{
//		boolean bstatus;
//		bstatus = LearningPathOp_Lib.addClassDeatils_App();
//		if (bstatus)
//		{
//			Log4J.logp.info("Vefity Add class Details sucessfull -From TestScript "  );			
//			Assert.assertTrue(bstatus, "Vefity Add class Details sucessfull -From TestScript " );
//		} 
//		else
//		{
//			Log4J.logp.error("Vefity Add class Details UN-sucessfull -From TestScript" );
//			Assert.assertTrue(bstatus, "Vefity Add class Details Un-sucessfull -From TestScript " );
//		}
//	}
			
	@AfterClass
	public static void ViewCaseAfterClass()
	{
		try
		{
			Login_Lib.logOut_App();
			driver = null;
			common_webe = null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}	
	

