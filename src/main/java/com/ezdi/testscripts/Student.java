package com.ezdi.testscripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.DriverTestNG;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.library.Login_Lib;
import com.ezdi.webelements.AbstractPnl_WebE;
import com.ezdi.webelements.CodingPnl_WebE;
import com.ezdi.webelements.Comman_WebE;
import com.ezdi.webelements.GroupinPnl_WebE;
import com.ezdi.webelements.IssuePnl_WebE;
import com.ezdi.webelements.LandingP_WebE;
import com.ezdi.webelements.ManualAllocation_WebE;
import com.ezdi.webelements.MedicalRecordPnl_WebE;
import com.ezdi.webelements.MessageCenter_WebE;
import com.ezdi.webelements.SearchCriteria_WebE;
import com.ezdi.webelements.ViewCase_WebE;

public class Student {
	
	public static WebDriver				driver;
	public static LandingP_WebE			landingp_webe;
	public static Comman_WebE			common_webe;
	public static WebDriverWait			wait;
	public static SearchCriteria_WebE	searchcriteria_webe;
	public static ViewCase_WebE			viewcase_webe;
	public static CodingPnl_WebE		codingpnl_webe;
	public static MedicalRecordPnl_WebE	medicalrecordpnl_webe;
	public static IssuePnl_WebE			issuepnl_webe;
	public static GroupinPnl_WebE		groupingpnl_webe;
	public static AbstractPnl_WebE		abstractpnl_webe;
	public static MessageCenter_WebE	messagecenter_webe;
	public static ManualAllocation_WebE	manualallocation_webe;

	@BeforeClass
	public static void ViewCaseBeforeClass()
	{
		try
		{
			driver = ExecutionSetup.getDriver();
			landingp_webe = LandingP_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			wait = new WebDriverWait(driver, 20);
			searchcriteria_webe = SearchCriteria_WebE.getInstance(driver);
			viewcase_webe = ViewCase_WebE.getInstance(driver);
			codingpnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			abstractpnl_webe = AbstractPnl_WebE.getInstance(driver);
			messagecenter_webe = MessageCenter_WebE.getInstance(driver);
			manualallocation_webe = ManualAllocation_WebE.getInstance(driver);

			//Login_Lib.login("nc003");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	@Test(description = "Vefity student login",  priority = 1)
	public static void loginAsStudent()
	
	{
		boolean bstatus; 
		String strLogin_Rowid = "bc001"  ;
				bstatus = Login_Lib.login(strLogin_Rowid);
		if (bstatus){
			Log4J.logp.info("login sucessfully for row id " + strLogin_Rowid );
		} else{
			Log4J.logp.error("login un - sucessfully for row id " + strLogin_Rowid );
		}
			
	
	}
	
		
	@AfterClass
	public static void ViewCaseAfterClass()
	{
		try
		{
			if (driver != null)
			{
				driver = null;
			}
			if (landingp_webe != null)
			{
				landingp_webe = null;
			}
			if (wait != null)
			{
				wait = null;
			}
			if (common_webe != null)
			{
				common_webe = null;
			}
			if (searchcriteria_webe != null)
			{
				searchcriteria_webe = null;
			}
			if (codingpnl_webe != null)
			{
				codingpnl_webe = null;
			}
			if (medicalrecordpnl_webe != null)
			{
				medicalrecordpnl_webe = null;
			}

			Login_Lib.logOut_App();
//			Login_Lib.logIn_App(DriverTestNG.username, DriverTestNG.password);

			Log4J.logp.info("In AfterClass for ViewCase");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	

}
