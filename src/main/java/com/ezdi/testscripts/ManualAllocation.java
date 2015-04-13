package com.ezdi.testscripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.library.Common_Lib;
import com.ezdi.library.Login_Lib;
import com.ezdi.library.ManualAllocation_Lib;
import com.ezdi.library.SearchCriteria_Lib;
import com.ezdi.library.WorkingScreen_Lib;
import com.ezdi.webelements.AdminHome_WebE;
import com.ezdi.webelements.CodingPnl_WebE;
import com.ezdi.webelements.Comman_WebE;
import com.ezdi.webelements.DemographicPnl_WebE;
import com.ezdi.webelements.GroupinPnl_WebE;
import com.ezdi.webelements.IssuePnl_WebE;
import com.ezdi.webelements.LandingP_WebE;
import com.ezdi.webelements.Login_WebE;
import com.ezdi.webelements.ManualAllocation_WebE;
import com.ezdi.webelements.MedicalRecordPnl_WebE;
import com.ezdi.webelements.MessageCenter_WebE;
import com.ezdi.webelements.SearchCriteria_WebE;

public class ManualAllocation
{

	public static WebDriver				driver;
	public static LandingP_WebE			landingp_webe;
	public static WebDriverWait			wait;
	public static Comman_WebE			common_webe;
	public static MessageCenter_WebE	messagecenter_webe;
	public static Login_WebE			login_webe;
	public static MedicalRecordPnl_WebE	medicalrecordpnl_webe;
	public static GroupinPnl_WebE		groupingpnl_webe;
	public static IssuePnl_WebE			issuepnl_webe;
	public static DemographicPnl_WebE	demographicpnl_webe;
	public static SearchCriteria_WebE	searchcriteria_webe;
	public static CodingPnl_WebE		codingpnl_webe;
	public static ManualAllocation_WebE	manualAllocation_webe;
	public static AdminHome_WebE		adminhome_webe;

	@BeforeClass
	public static void ManualAllocationBeforeClass()
	{
		try
		{
			driver = ExecutionSetup.getDriver();
			landingp_webe = LandingP_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			messagecenter_webe = MessageCenter_WebE.getInstance(driver);
			login_webe = Login_WebE.getInstance(driver);
			wait = new WebDriverWait(driver, 30);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			demographicpnl_webe = DemographicPnl_WebE.getInstance(driver);
			searchcriteria_webe = SearchCriteria_WebE.getInstance(driver);
			manualAllocation_webe = ManualAllocation_WebE.getInstance(driver);
			adminhome_webe = AdminHome_WebE.getInstance(driver);
			//Login_Lib.logOut_App();
			Login_Lib.login("fm005");

			Log4J.logp.info("In BeforeClass for ManualAllocation");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * This test script checks that Supervisor can assign cases on which no user has worked yet to a Coder
	 * 
	 * @author fmodi
	 * @since 09/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2200:Supervisor can allocate fresh case(s) to Coder", priority = 0)
	public static void assignFreshCaseCoder()
	{

		boolean bstatus;
		int i;

		try

		{

			Log4J.logp.info("*************** Started - assignFreshCaseCoder ***************");

			//Thread.sleep(5000);
			Common_Lib.waitForObject(landingp_webe.lnk_All, "clickable", 5);
			//ManualAllocation_Lib.assignFreshCase("fm004", "Faiz Modi");
			//below is acc to git online
			ManualAllocation_Lib.assignFreshCase("fm004", "Susan Wilson");
			Thread.sleep(2000);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);

			searchcriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			bstatus = SearchCriteria_Lib.search_Case("fm005");
			if (bstatus == true)
			{
				Log4J.logp.info("Status of case is changed from 'Unassigned' to 'In Queue'");
				Assert.assertTrue(true, "Status of case is changed from 'Unassigned' to 'In Queue'");
			}
			else
			{
				Log4J.logp.error("Status of case is not changed from 'Unassigned' to 'In Queue'");
				Assert.assertTrue(false, "Status of case is not changed from 'Unassigned' to 'In Queue'");
			}
			Thread.sleep(2000);

			//below is acc to git online
			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm006");
			Thread.sleep(2000);

			//below is ATGO
			/*landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);*/

			i = 50;
			SearchCriteria_Lib.search_Case("SC00" + i);
			Thread.sleep(2000);

			bstatus = ManualAllocation_Lib.checkNoDataAvailable(i);
			if (bstatus == false)
			{
				Log4J.logp.info("Case is displayed in Coder's case grid.");
				Assert.assertTrue(true, "Case is displayed in Coder's case grid.");
			}
			else
			{
				Log4J.logp.error("Case is not displayed in Coder's case grid.");
				Assert.assertTrue(false, "Case is not displayed in Coder's case grid.");
			}

			Thread.sleep(2000);

			Log4J.logp.info("*************** Ended - assignFreshCaseCoder ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - assignFreshCaseCoder ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "assignFreshCaseCoder is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that Supervisor can assign cases on which no user has worked yet to a CDI
	 * 
	 * @author fmodi
	 * @since 10/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2202:Supervisor can allocate fresh case(s) to CDI", priority = 1)
	public static void assignFreshCaseCDI()
	{

		boolean bstatus;
		int i;

		try

		{

			Log4J.logp.info("*************** Started - assignFreshCaseCDI ***************");

			//Thread.sleep(5000);
			Common_Lib.waitForObject(landingp_webe.lnk_All, "clickable", 5);
			ManualAllocation_Lib.assignFreshCase("fm006", "Helen Harris");
			Thread.sleep(2000);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);

			searchcriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			bstatus = SearchCriteria_Lib.search_Case("fm007");
			if (bstatus == true)
			{
				Log4J.logp.info("Status of case is changed from 'Unassigned' to 'In Queue'");
				Assert.assertTrue(true, "Status of case is changed from 'Unassigned' to 'In Queue'");
			}
			else
			{
				Log4J.logp.error("Status of case is not changed from 'Unassigned' to 'In Queue'");
				Assert.assertTrue(false, "Status of case is not changed from 'Unassigned' to 'In Queue'");
			}
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm007");
			Thread.sleep(2000);
			i = 51;
			SearchCriteria_Lib.search_Case("SC00" + i);
			bstatus = ManualAllocation_Lib.checkNoDataAvailable(i);
			if (bstatus == false)
			{
				Log4J.logp.info("Case is displayed in CDI's case grid.");
				Assert.assertTrue(true, "Case is displayed in CDI's case grid.");
			}
			else
			{
				Log4J.logp.error("Case is not displayed in CDI's case grid.");
				Assert.assertTrue(false, "Case is not displayed in CDI's case grid.");
			}

			Thread.sleep(2000);

			Log4J.logp.info("*************** Ended - assignFreshCaseCDI ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - assignFreshCaseCDI ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "assignFreshCaseCDI is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that Supervisor can assign cases to Reviewer which are completed by Coder and request to review is made for that
	 * 
	 * @author fmodi
	 * @since 13/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2880:Supervisor can allocate 'Unassigned' Case(s) to Reviewer" + "ezCAC_MVP_Reg-2756:Supervisor can be able to allocate case(s) which is 'Completed' for a Coder to Reviewer", priority = 2)
	public static void assignUnassignedCaseToReviewer()
	{

		boolean bstatus;
		int i;

		try

		{

			Log4J.logp.info("*************** Started - assignUnassignedCaseToReviewer ***************");

			//Thread.sleep(5000);
			Common_Lib.waitForObject(landingp_webe.lnk_Coding, "clickable", 5);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);

			WorkingScreen_Lib.submit_case("fm001");
			Thread.sleep(2000);

			//landingp_webe.lnk_All.click();
			//Thread.sleep(5000);
			Common_Lib.waitForObject(landingp_webe.lnk_All, "clickable", 5);

			//below is ATGO
			//ManualAllocation_Lib.assignFreshCase("fm009", "Rahil Kanani");

			//below is ATGO
			ManualAllocation_Lib.assignCase("fm009", "Rahil Kanani");

			Thread.sleep(2000);
			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);

			searchcriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			bstatus = SearchCriteria_Lib.search_Case("fm010");
			if (bstatus == true)
			{
				Log4J.logp.info("Status of case is changed from 'Unassigned' to 'In Queue'");
				Assert.assertTrue(true, "Status of case is changed from 'Unassigned' to 'In Queue'");
			}
			else
			{
				Log4J.logp.error("Status of case is not changed from 'Unassigned' to 'In Queue'");
				Assert.assertTrue(false, "Status of case is not changed from 'Unassigned' to 'In Queue'");
			}
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm001");
			Thread.sleep(2000);
			landingp_webe.lnk_Review.click();
			Thread.sleep(2000);
			i = 52;
			SearchCriteria_Lib.search_Case("SC00" + i);
			bstatus = ManualAllocation_Lib.checkNoDataAvailable(i);
			if (bstatus == false)
			{
				Log4J.logp.info("Case is displayed in Reviewer's case grid.");
				Assert.assertTrue(true, "Case is displayed in Reviewer's case grid.");
			}
			else
			{
				Log4J.logp.error("Case is not displayed in Reviewer's case grid.");
				Assert.assertTrue(false, "Case is not displayed in Reviewer's case grid.");
			}
			Thread.sleep(2000);

			Log4J.logp.info("*************** Ended - assignUnassignedCaseToReviewer ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - assignUnassignedCaseToReviewer ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "assignUnassignedCaseToReviewer is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that Coder can allocate cases to self which are either fresh or "Completed" for CDI
	 * 
	 * @author fmodi
	 * @since 31/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2759:Coder can allocate case(s) to self", priority = 3)
	public static void selfAssignCaseForCoder()
	{

		boolean bstatus;
		int i1, i2;

		try

		{

			Log4J.logp.info("*************** Started - selfAssignCaseForCoder ***************");

			Thread.sleep(2000);

			//below code will check if coder can self-allocate fresh case
			Log4J.logp.info("========== In SelfAssign Fresh Case ==========");

			//below is ATGO
			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm006");
			Thread.sleep(2000);

			//below is ATGO
			/*landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);*/

			ManualAllocation_Lib.selfAssignFreshCase("fm011");

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			i1 = 53;
			SearchCriteria_Lib.search_Case("SC00" + i1);
			bstatus = ManualAllocation_Lib.checkNoDataAvailable(i1);
			if (bstatus == false)
			{
				Log4J.logp.info("Fresh Case is allocated to Coder");
				Assert.assertTrue(true, "Fresh Case is allocated to Coder");
			}
			else
			{
				Log4J.logp.error("Fresh Case is not allocated to Coder");
				Assert.assertTrue(false, "Fresh Case is not allocated to Coder");
			}
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished SelfAssign Fresh Case ==========");

			//below code will check if coder can self-allocate CDI 'Completed' case
			Log4J.logp.info("========== In SelfAssign CDI 'Completed' Case ==========");
			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm007");

			WorkingScreen_Lib.submit_case("fm002");
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm005");
			Thread.sleep(2000);

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);

			Common_Lib.openCase("fm013");
			//Thread.sleep(15000);
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 20);

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			i2 = 54;
			SearchCriteria_Lib.search_Case("SC00" + i2);
			bstatus = ManualAllocation_Lib.checkNoDataAvailable(i2);
			if (bstatus == false)
			{
				Log4J.logp.info("CDI 'Completed' Case is allocated to Coder");
				Assert.assertTrue(true, "CDI 'Completed' Case is allocated to Coder");
			}
			else
			{
				Log4J.logp.error("CDI 'Completed' Case is not allocated to Coder");
				Assert.assertTrue(false, "CDI 'Completed' Case is not allocated to Coder");
			}
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished SelfAssign CDI 'Completed' Case ==========");

			Log4J.logp.info("*************** Ended - selfAssignCaseForCoder ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - selfAssignCaseForCoder ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "selfAssignCaseForCoder is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that Coder can't open cases which are "Completed" or "Billed" by him
	 * 
	 * @author fmodi
	 * @since 31/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2843:Coder can't allocate case which is 'Completed/Billed' for a coder to self", priority = 4)
	public static void canNotSelfAssignCaseForCoder()
	{

		WebElement table1, table2;
		WebElement row1, row2;
		int i1, i2, icount1, icount2;

		try

		{

			Log4J.logp.info("*************** Started - canNotSelfAssignCaseForCoder ***************");

			Thread.sleep(2000);

			//below is ATGO
			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm006");
			Thread.sleep(2000);

			//below code will check if coder can self-allocate already "Completed" case
			Log4J.logp.info("========== In Can Not SelfAssign 'Completed' Case ==========");

			//below is ATGO
			/*landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);*/

			WorkingScreen_Lib.submit_case("fm003");
			Thread.sleep(2000);

			SearchCriteria_Lib.search_Case("fm015");
			Thread.sleep(2000);

			//to scroll page
			//below is ATGO
			/*Common_Lib.scroll_without_element();
			Thread.sleep(2000);*/

			table1 = landingp_webe.tbl_SearchResult;
			//find click-able column
			row1 = table1.findElement(By.tagName("td"));
			row1.click();
			Thread.sleep(2000);

			icount1 = 0;
			List<WebElement> ls1 = landingp_webe.lst_casedisabled;
			for (i1 = 0; i1 < ls1.size(); i1++)
			{
				if (ls1.get(i1).isDisplayed())
				{
					icount1++;
				}
			}
			if (icount1 == 0)
			{
				Log4J.logp.info("'Completed' case is not locked");
				//below is ATGO
				//Uncomment below line when bug 2438 is solved
				//Assert.assertTrue(false, "'Completed' case is not locked");
			}
			else
			{
				Log4J.logp.info("User can not allocate 'Completed' case to self");
				//below is ATGO
				//Uncomment below line when bug 2438 is solved
				//Assert.assertTrue(true, "User can not allocate 'Completed' case to self");
			}
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not SelfAssign 'Completed' Case ==========");

			//below code will check if coder can self-allocate already "Billed" case
			Log4J.logp.info("========== In Can Not SelfAssign 'Billed' Case ==========");

			//below is ATGO
			driver.navigate().refresh();
			Thread.sleep(2000);

			//below is ATGO
			/*landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);*/

			WorkingScreen_Lib.submit_case("fm004");
			Thread.sleep(2000);

			SearchCriteria_Lib.search_Case("fm017");
			Thread.sleep(2000);

			//to scroll page
			//below is ATGO
			/*Common_Lib.scroll_without_element();
			Thread.sleep(2000);*/

			table2 = landingp_webe.tbl_SearchResult;
			//find click-able column
			row2 = table2.findElement(By.tagName("td"));
			row2.click();
			Thread.sleep(2000);

			icount2 = 0;
			List<WebElement> ls2 = landingp_webe.lst_casedisabled;
			for (i2 = 0; i2 < ls2.size(); i2++)
			{
				if (ls2.get(i2).isDisplayed())
				{
					icount2++;
				}
			}
			if (icount2 == 0)
			{
				Log4J.logp.info("'Billed' case is not locked");
				//below is ATGO
				//Uncomment below line when bug 2438 is solved
				//Assert.assertTrue(false, "'Billed' case is not locked");
			}
			else
			{
				Log4J.logp.info("User can not allocate 'Billed' case to self");
				//below is ATGO
				//Uncomment below line when bug 2438 is solved
				//Assert.assertTrue(true, "User can not allocate 'Billed' case to self");
			}
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Can Not SelfAssign 'Billed' Case ==========");

			Log4J.logp.info("*************** Ended - canNotSelfAssignCaseForCoder ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - canNotSelfAssignCaseForCoder ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "canNotSelfAssignCaseForCoder is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that Supervisor can't assign case which is already allocated to that Coder and is in "In Queue" or "On Hold" or "Pending"
	 * 
	 * @author fmodi
	 * @since 03/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2865:Supervisor can't re-allocate a case to same coder", priority = 5)
	public static void canNotReassignCaseCoder()
	{

		try

		{

			Log4J.logp.info("*************** Started - canNotReassignCaseCoder ***************");

			Thread.sleep(2000);

			//below code is for "In Queue" status
			Log4J.logp.info("========== In Can Not Reassign 'In Queue' Case ==========");

			//Thread.sleep(5000);
			Common_Lib.waitForObject(landingp_webe.lnk_All, "clickable", 5);
			ManualAllocation_Lib.assignFreshCase("fm018", "Faiz Modi");
			Thread.sleep(2000);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);

			searchcriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			ManualAllocation_Lib.canNotReassignCase("Coder", "In Queue", "fm019", "Faiz Modi", 55);
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Reassign 'In Queue' Case ==========");

			//below code is for "On Hold" status
			Log4J.logp.info("========== In Can Not Reassign 'On Hold' Case ==========");

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);

			Common_Lib.openCase("fm019");
			//Thread.sleep(15000);
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 20);

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			ManualAllocation_Lib.canNotReassignCase("Coder", "On Hold", "fm020", "Faiz Modi", 56);
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Reassign 'On Hold' Case ==========");

			//below code is for "Pending" status
			Log4J.logp.info("========== In Can Not Reassign 'Pending' Case ==========");

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);

			Common_Lib.putCaseInPendingStatus("fm020");
			Thread.sleep(2000);

			ManualAllocation_Lib.canNotReassignCase("Coder", "Pending", "fm021", "Faiz Modi", 57);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Can Not Reassign 'Pending' Case ==========");

			Log4J.logp.info("*************** Ended - canNotReassignCaseCoder ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - canNotReassignCaseCoder ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "canNotReassignCaseCoder is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that CDI can allocate fresh cases to self
	 * 
	 * @author fmodi
	 * @since 14/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2760:CDI can allocate fresh case(s) to self.", priority = 7)
	public static void selfAssignCaseForCDI()
	{

		boolean bstatus;
		int i;

		try

		{

			Log4J.logp.info("*************** Started - selfAssignCaseForCDI ***************");

			Thread.sleep(2000);

			//below code will check if coder can self-allocate fresh case
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);

			ManualAllocation_Lib.selfAssignFreshCase("fm022");

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			i = 58;
			SearchCriteria_Lib.search_Case("SC00" + i);
			bstatus = ManualAllocation_Lib.checkNoDataAvailable(i);
			if (bstatus == false)
			{
				Log4J.logp.info("Fresh Case is allocated to CDI");
				Assert.assertTrue(true, "Fresh Case is allocated to CDI");
			}
			else
			{
				Log4J.logp.error("Fresh Case is not allocated to CDI");
				Assert.assertTrue(false, "Fresh Case is not allocated to CDI");
			}
			Thread.sleep(2000);

			Log4J.logp.info("*************** Ended - selfAssignCaseForCDI ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - selfAssignCaseForCDI ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "selfAssignCaseForCDI is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that CDI can assign cases which are "Billed" by Coder or Reviewer to self
	 * 
	 * @author fmodi
	 * @since 14/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2761:CDI can allocate case(s) which is 'Billed' to self.", priority = 8)
	public static void selfAssignBilledCaseForCDI()
	{

		boolean bstatus;
		int i1, i2;

		try

		{

			Log4J.logp.info("*************** Started - selfAssignBilledCaseForCDI ***************");

			Thread.sleep(2000);

			//below code will check if CDI can self-allocate Coder 'Billed' case
			Log4J.logp.info("========== In Can SelfAssign Coder 'Billed' Case ==========");

			/*Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm006");

			WorkingScreen_Lib.submit_case("fm005");
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm005");
			Thread.sleep(2000);*/

			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);

			Common_Lib.openCase("fm023");
			//Thread.sleep(15000);
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 20);

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			i1 = 59;
			SearchCriteria_Lib.search_Case("SC00" + i1);
			bstatus = ManualAllocation_Lib.checkNoDataAvailable(i1);
			if (bstatus == false)
			{
				Log4J.logp.info("Coder 'Billed' Case is allocated to CDI");
				Assert.assertTrue(true, "Coder 'Billed' Case is allocated to CDI");
			}
			else
			{
				Log4J.logp.error("Coder 'Billed' Case is not allocated to CDI");
				Assert.assertTrue(false, "Coder 'Billed' Case is not allocated to CDI");
			}
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Can SelfAssign Coder 'Billed' Case ==========");

			//below code will check if CDI can self-allocate Reviewer 'Billed' case
			Log4J.logp.info("========== In Can SelfAssign Reviewer 'Billed' Case ==========");

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm006");

			WorkingScreen_Lib.submit_case("fm005");
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm001");
			Thread.sleep(2000);

			landingp_webe.lnk_Review.click();
			Thread.sleep(2000);

			WorkingScreen_Lib.submit_case("fm006");
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm005");
			Thread.sleep(2000);

			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);

			Common_Lib.openCase("fm026");
			//Thread.sleep(15000);
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 20);

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			i2 = 60;
			SearchCriteria_Lib.search_Case("SC00" + i2);
			bstatus = ManualAllocation_Lib.checkNoDataAvailable(i2);
			if (bstatus == false)
			{
				Log4J.logp.info("Reviewer 'Billed' Case is allocated to CDI");
				Assert.assertTrue(true, "Reviewer 'Billed' Case is allocated to CDI");
			}
			else
			{
				Log4J.logp.error("Reviewer 'Billed' Case is not allocated to CDI");
				Assert.assertTrue(false, "Reviewer 'Billed' Case is not allocated to CDI");
			}
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Can SelfAssign Reviewer 'Billed' Case ==========");

			Log4J.logp.info("*************** Ended - selfAssignBilledCaseForCDI ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - selfAssignBilledCaseForCDI ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "selfAssignBilledCaseForCDI is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that Supervisor can't assign case which is already allocated to that CDI and is in "In Queue" or "On Hold" or "Pending"
	 * 
	 * @author fmodi
	 * @since 17/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2869:Supervisor can't allocate a case which is already allocated to that CDI", priority = 9)
	public static void canNotReassignCaseCDI()
	{

		try

		{

			Log4J.logp.info("*************** Started - canNotReassignCaseCDI ***************");

			//For In Queue Status
			Log4J.logp.info("========== In Can Not Reassign 'In Queue' Case ==========");
			//Thread.sleep(5000);
			Common_Lib.waitForObject(landingp_webe.lnk_All, "clickable", 5);
			ManualAllocation_Lib.assignFreshCase("fm027", "Faiz Modi");
			Thread.sleep(2000);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);

			searchcriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			ManualAllocation_Lib.canNotReassignCase("CDI", "In Queue", "fm028", "Faiz Modi", 69);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Can Not Reassign 'In Queue' Case ==========");

			//For On Hold Status
			Log4J.logp.info("========== In Can Not Reassign 'On Hold' Case ==========");
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);

			Common_Lib.openCase("fm028");
			//Thread.sleep(15000);
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 20);

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			ManualAllocation_Lib.canNotReassignCase("CDI", "On Hold", "fm029", "Faiz Modi", 61);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Can Not Reassign 'On Hold' Case ==========");

			//For Pending Status
			Log4J.logp.info("========== In Can Not Reassign 'Pending' Case ==========");
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);

			Common_Lib.putCaseInPendingStatus("fm029");
			Thread.sleep(2000);

			ManualAllocation_Lib.canNotReassignCase("CDI", "Pending", "fm030", "Faiz Modi", 62);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Can Not Reassign 'Pending' Case ==========");

			Log4J.logp.info("*************** Ended - canNotReassignCaseCDI ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - canNotReassignCaseCDI ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "canNotReassignCaseCDI is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that Reviewer can assign cases which are "Completed" by 'CDI & Coder' or 'Coder' to self
	 * 
	 * @author fmodi
	 * @since 17/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2764:Reviewer can allocate case which is 'Completed' for other role(s) to self.", priority = 10)
	public static void selfAssignCompletedCaseForReviewer()
	{

		boolean bstatus;
		int i1, i2;

		try

		{

			Log4J.logp.info("*************** Started - selfAssignCompletedCaseForReviewer ***************");

			Thread.sleep(2000);

			//below code will check if Reviewer can self-allocate CDI & Coder 'Completed' case
			Log4J.logp.info("========== In Can SelfAssign CDI & Coder 'Completed' Case ==========");

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm007");

			WorkingScreen_Lib.submit_case("fm007");
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm001");
			Thread.sleep(2000);

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);

			WorkingScreen_Lib.submit_case("fm008");
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm005");
			Thread.sleep(2000);

			landingp_webe.lnk_Review.click();
			Thread.sleep(2000);

			Common_Lib.openCase("fm033");
			//Thread.sleep(15000);
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 20);

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			i1 = 63;
			SearchCriteria_Lib.search_Case("SC00" + i1);
			bstatus = ManualAllocation_Lib.checkNoDataAvailable(i1);
			if (bstatus == false)
			{
				Log4J.logp.info("CDI & Coder 'Completed' Case is allocated to Reviewer");
				Assert.assertTrue(true, "CDI & Coder 'Completed' Case is allocated to Reviewer");
			}
			else
			{
				Log4J.logp.error("CDI & Coder 'Completed' Case is not allocated to Reviewer");
				Assert.assertTrue(false, "CDI & Coder 'Completed' Case is not allocated to Reviewer");
			}
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Can SelfAssign CDI & Coder 'Completed' Case ==========");

			//below code will check if Reviewer can self-allocate Coder 'Completed' case

			Log4J.logp.info("========== In Can SelfAssign Coder 'Completed' Case ==========");

			//temporarily keep below code commented
			/*Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm006");

			WorkingScreen_Lib.submit_case("fm010");
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm005");
			Thread.sleep(2000);*/

			//to scroll page upwards
			//below is ATGO
			/*Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);*/

			//below is ATGO
			driver.navigate().refresh();
			Thread.sleep(2000);

			landingp_webe.lnk_Review.click();
			Thread.sleep(2000);

			Common_Lib.openCase("fm034");
			//Thread.sleep(15000);
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 20);

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			i2 = 64;
			SearchCriteria_Lib.search_Case("SC00" + i2);
			bstatus = ManualAllocation_Lib.checkNoDataAvailable(i2);
			if (bstatus == false)
			{
				Log4J.logp.info("Coder 'Completed' Case is allocated to Reviewer");
				Assert.assertTrue(true, "Coder 'Completed' Case is allocated to Reviewer");
			}
			else
			{
				Log4J.logp.error("Coder 'Completed' Case is not allocated to Reviewer");
				Assert.assertTrue(false, "Coder 'Completed' Case is not allocated to Reviewer");
			}

			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Can SelfAssign CDI & Coder 'Completed' Case ==========");

			Log4J.logp.info("*************** Ended - selfAssignCompletedCaseForReviewer ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - selfAssignCompletedCaseForReviewer ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "selfAssignCompletedCaseForReviewer is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that Supervisor can't assign case which is already allocated to that Reviewer and is in "In Queue" or "On Hold" or "Pending"
	 * 
	 * @author fmodi
	 * @since 17/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2876:Supervisor can't re-allocate a case which is already allocated to that Reviewer", priority = 11)
	public static void canNotReassignCaseReviewer()
	{

		try

		{

			Log4J.logp.info("*************** Started - canNotReassignCaseReviewer ***************");

			//To generate case for reviewer submitting it by Coder
			Log4J.logp.info("========== In Submitting Case by Coder ==========");
			//Thread.sleep(5000);
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "clickable", 5);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm006");

			WorkingScreen_Lib.submit_case("fm009");
			//Thread.sleep(3000);
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "clickable", 3);
			Log4J.logp.info("========== Finished Submitting Case by Coder ==========");

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm005");
			Thread.sleep(2000);

			//For In Queue Status
			Log4J.logp.info("========== In Can Not Reassign 'In Queue' Case ==========");
			landingp_webe.lnk_All.click();

			//Thread.sleep(3000);
			//This change made on 17-12-2014
			Thread.sleep(2000);

			ManualAllocation_Lib.assignCase("fm036", "Faiz Modi");
			Thread.sleep(2000);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);

			searchcriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			ManualAllocation_Lib.canNotReassignCase("Reviewer", "In Queue", "fm037", "Faiz Modi", 70);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Can Not Reassign 'In Queue' Case ==========");

			//For On Hold Status
			Log4J.logp.info("========== In Can Not Reassign 'On Hold' Case ==========");
			landingp_webe.lnk_Review.click();
			Thread.sleep(2000);

			Common_Lib.openCase("fm037");
			//Thread.sleep(15000);
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 20);

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			ManualAllocation_Lib.canNotReassignCase("Reviewer", "On Hold", "fm038", "Faiz Modi", 65);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Can Not Reassign 'On Hold' Case ==========");

			//For Pending Status
			Log4J.logp.info("========== In Can Not Reassign 'Pending' Case ==========");
			landingp_webe.lnk_Review.click();
			Thread.sleep(2000);

			Common_Lib.putCaseInPendingStatus("fm038");
			Thread.sleep(2000);

			ManualAllocation_Lib.canNotReassignCase("Reviewer", "Pending", "fm039", "Faiz Modi", 66);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Can Not Reassign 'Pending' Case ==========");

			Log4J.logp.info("*************** Ended - canNotReassignCaseReviewer ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - canNotReassignCaseReviewer ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "canNotReassignCaseReviewer is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that Supervisor can't assign fresh cases to Reviewer
	 * 
	 * @author fmodi
	 * @since 18/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2203:Supervisor can't allocate fresh case(s) to Reviewer.", priority = 12)
	public static void canNotAssignFreshCaseToReviewer()
	{

		boolean bstatus;

		try

		{

			Log4J.logp.info("*************** Started - canNotAssignFreshCaseToReviewer ***************");

			//Thread.sleep(5000);
			Common_Lib.waitForObject(landingp_webe.lnk_Coding, "clickable", 5);

			/*ManualAllocation_Lib.assignFreshCase("fm008", "Faiz Modi");
			Thread.sleep(2000);*/

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);

			WorkingScreen_Lib.submit_case("fm010");
			Thread.sleep(2000);

			landingp_webe.lnk_All.click();
			//Thread.sleep(5000);
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "clickable", 5);

			SearchCriteria_Lib.search_Case("fm041");
			Thread.sleep(2000);

			//to scroll page
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);

			bstatus = Common_Lib.checkElementPresent(manualAllocation_webe.ico_FreshCase);
			if (bstatus == false)
			{
				Log4J.logp.info("Supervisor can not assign Fresh Case to Reviewer");
				Assert.assertTrue(true, "Supervisor can not assign Fresh Case to Reviewer");
			}
			else
			{
				Log4J.logp.error("Supervisor can assign Fresh Case to Reviewer");
				Assert.assertTrue(false, "Supervisor can assign Fresh Case to Reviewer");
			}
			Thread.sleep(2000);

			Log4J.logp.info("*************** Ended - canNotAssignFreshCaseToReviewer ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - canNotAssignFreshCaseToReviewer ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "canNotAssignFreshCaseToReviewer is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that Supervisor can't delete Coder when Coder has cases in "In Progress" or "On Hold" or "Pending"
	 * 
	 * @author fmodi
	 * @since 18/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2853:Admin can't delete coder user role when cases are already allocated to that coder.", priority = 13)
	public static void canNotDeleteCoder()
	{

		try

		{

			Log4J.logp.info("*************** Started - canNotDeleteCoder ***************");

			Thread.sleep(2000);

			//For "In Progress"
			Log4J.logp.info("========== In Can Not Delete Coder when he has case(s) in 'In Progress' ==========");

			ManualAllocation_Lib.canNotDeleteUser("fm006", "fm042", "Susan Wilson", "In Progress");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Delete Coder when he has case(s) in 'In Progress' ==========");

			//For "On Hold"
			Log4J.logp.info("========== In Can Not Delete Coder when he has case(s) in 'On Hold' ==========");

			ManualAllocation_Lib.canNotDeleteUser("fm006", "fm043", "Susan Wilson", "On Hold");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Delete Coder when he has case(s) in 'On Hold' ==========");

			//For "Pending"
			Log4J.logp.info("========== In Can Not Delete Coder when he has case(s) in 'Pending' ==========");

			ManualAllocation_Lib.canNotDeleteUser("fm006", "fm043", "Susan Wilson", "Pending");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Can Not Delete Coder when he has case(s) in 'Pending' ==========");

			Log4J.logp.info("*************** Ended - canNotDeleteCoder ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - canNotDeleteCoder ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "canNotDeleteCoder is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that Supervisor can't assign case to Reviewer which is already allocated to Coder and is not "Completed"
	 * 
	 * @author fmodi
	 * @since 18/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2205:Supervisor can't allocate case(s) which is already allocated to a Coder to Reviewer.", priority = 14)
	public static void canNotAssignCoderCaseToReviewer()
	{

		boolean bstatus;
		int i;

		try

		{

			Log4J.logp.info("*************** Started - canNotAssignCoderCaseToReviewer ***************");

			//Thread.sleep(5000);
			Common_Lib.waitForObject(landingp_webe.lnk_All, "clickable", 5);

			//For In Queue
			Log4J.logp.info("========== In Can Not Assign Case To Reviewer For Coder when he has case(s) in 'In Queue' ==========");
			ManualAllocation_Lib.assignFreshCase("fm044", "Susan Wilson");
			Thread.sleep(2000);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);

			searchcriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			bstatus = SearchCriteria_Lib.search_Case("fm045");
			if (bstatus == true)
			{
				Log4J.logp.info("Status of case is changed from 'Unassigned' to 'In Queue'");
				Assert.assertTrue(true, "Status of case is changed from 'Unassigned' to 'In Queue'");
			}
			else
			{
				Log4J.logp.error("Status of case is not changed from 'Unassigned' to 'In Queue'");
				Assert.assertTrue(false, "Status of case is not changed from 'Unassigned' to 'In Queue'");
			}

			Thread.sleep(2000);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);

			searchcriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			i = 67;
			SearchCriteria_Lib.search_Case("SC00" + i);
			bstatus = ManualAllocation_Lib.checkNoDataAvailable(i);
			if (bstatus == true)
			{
				Log4J.logp.info("Supervisor can not assign case which is 'In Queue' for a Coder to Reviewer");
				Assert.assertTrue(true, "Supervisor can not assign case which is 'In Queue' for a Coder to Reviewer.");
			}
			else
			{
				Log4J.logp.info("Supervisor can assign case which is 'In Queue' for a Coder to Reviewer");
				Assert.assertTrue(false, "Supervisor can assign case which is 'In Queue' for a Coder to Reviewer.");
			}

			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Assign Case To Reviewer For Coder when he has case(s) in 'In Queue' ==========");

			//For In Progress
			Log4J.logp.info("========== In Can Not Assign Case To Reviewer For Coder when he has case(s) in 'In Progress' ==========");

			ManualAllocation_Lib.canNotAssignUserCaseToReviewer("fm006", "Coder", "In Queue", "In Progress", "fm045", "fm046", 67);
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Assign Case To Reviewer For Coder when he has case(s) in 'In Progress' ==========");

			//temp keep below code commented
			/*Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm006");
			Thread.sleep(2000);

			Common_Lib.openCase("fm045");
			Thread.sleep(10000);

			driver.navigate().back();
			Thread.sleep(5000);

			boolean b3 = SearchCriteria_Lib.search_Case("fm046");
			if (b3 == true)
			{
				Assert.assertTrue(true, "Status of case is changed from 'In Queue' to 'In Progress'");
				Log4J.logp.info("Status of case is changed from 'In Queue' to 'In Progress'");
			}
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm005");
			Thread.sleep(3000);

			landingp_webe.lnk_All.click();
			Thread.sleep(5000);

			SearchCriteria_Lib.search_Case("SC00" + i);
			boolean b4 = ManualAllocation_Lib.checkNoDataAvailable(i);
			if (b4 == true)
			{
				Assert.assertTrue(true, "Supervisor can not assign case which is 'In Progress' for a Coder to Reviewer.");
				Log4J.logp.info("Supervisor can not assign case which is 'In Progress' for a Coder to Reviewer");
			}
			Thread.sleep(2000);*/

			//For "On Hold"
			Log4J.logp.info("========== In Can Not Assign Case To Reviewer For Coder when he has case(s) in 'On Hold' ==========");

			ManualAllocation_Lib.canNotAssignUserCaseToReviewer("fm006", "Coder", "In Progress", "On Hold", "fm046", "fm047", 67);
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Assign Case To Reviewer For Coder when he has case(s) in 'On Hold' ==========");

			/*Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm006");
			Thread.sleep(3000);

			Common_Lib.openCase("fm046");
			Thread.sleep(15000);

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			boolean b5 = SearchCriteria_Lib.search_Case("fm047");
			if (b5 == true)
			{
				Assert.assertTrue(true, "Status of case is changed from 'In Progress' to 'On Hold'");
				Log4J.logp.info("Status of case is changed from 'In Progress' to 'On Hold'");
			}
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm005");
			Thread.sleep(3000);

			landingp_webe.lnk_All.click();
			Thread.sleep(5000);

			SearchCriteria_Lib.search_Case("SC00" + i);
			boolean b6 = ManualAllocation_Lib.checkNoDataAvailable(i);
			if (b6 == true)
			{
				Assert.assertTrue(true, "Supervisor can not assign case which is 'On Hold' for a Coder to Reviewer.");
				Log4J.logp.info("Supervisor can not assign case which is 'On Hold' for a Coder to Reviewer");
			}
			Thread.sleep(2000);*/

			//For "Pending"
			Log4J.logp.info("========== In Can Not Assign Case To Reviewer For Coder when he has case(s) in 'Pending' ==========");

			ManualAllocation_Lib.canNotAssignUserCaseToReviewer("fm006", "Coder", "On Hold", "Pending", "fm047", "fm048", 67);
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Assign Case To Reviewer For Coder when he has case(s) in 'Pending' ==========");

			/*Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm006");
			Thread.sleep(3000);

			Common_Lib.openCase("fm047");
			Thread.sleep(15000);

			common_webe.lnk_sys_evidence.get(0).click();
			Thread.sleep(2000);

			Common_Lib.rightclick_system_sugggested_evidence();

			medicalrecordpnl_webe.lnk_discusswithcolleague.click();

			IssuePanel_Lib.send_DiscussWithColleague("fz001");
			Thread.sleep(2000);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			Log4J.logp.info("Issue Panel Closed");

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			boolean b7 = SearchCriteria_Lib.search_Case("fm048");
			if (b7 == true)
			{
				Assert.assertTrue(true, "Status of case is changed from 'On Hold' to 'Pending'");
				Log4J.logp.info("Status of case is changed from 'On Hold' to 'Pending'");
			}
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm005");
			Thread.sleep(3000);

			landingp_webe.lnk_All.click();
			Thread.sleep(5000);

			SearchCriteria_Lib.search_Case("SC00" + i);
			boolean b8 = ManualAllocation_Lib.checkNoDataAvailable(i);
			if (b8 == true)
			{
				Assert.assertTrue(true, "Supervisor can not assign case which is 'Pending' for a Coder to Reviewer.");
				Log4J.logp.info("Supervisor can not assign case which is 'Pending' for a Coder to Reviewer");
			}
			Thread.sleep(2000);*/

			//For "Billed"
			Log4J.logp.info("========== In Can Not Assign Case To Reviewer For Coder when he has case(s) in 'Billed' ==========");

			ManualAllocation_Lib.canNotAssignUserCaseToReviewer("fm006", "Coder", "Pending", "Billed", "fm011", "fm049", 67);
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Assign Case To Reviewer For Coder when he has case(s) in 'Billed' ==========");

			/*Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm006");
			Thread.sleep(3000);

			WorkingScreen_Lib.submit_case("fm011");
			Thread.sleep(2000);

			boolean b9 = SearchCriteria_Lib.search_Case("fm049");
			if (b9 == true)
			{
				Assert.assertTrue(true, "Status of case is changed from 'Pending' to 'Billed'");
				Log4J.logp.info("Status of case is changed from 'Pending' to 'Billed'");
			}
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm005");
			Thread.sleep(3000);

			landingp_webe.lnk_All.click();
			Thread.sleep(5000);

			SearchCriteria_Lib.search_Case("SC00" + i);
			boolean b10 = ManualAllocation_Lib.checkNoDataAvailable(i);
			if (b10 == true)
			{
				Assert.assertTrue(true, "Supervisor can not assign case which is 'Billed' for a Coder to Reviewer.");
				Log4J.logp.info("Supervisor can not assign case which is 'Billed' for a Coder to Reviewer");
			}
			Thread.sleep(2000);*/

			Log4J.logp.info("*************** Ended - canNotAssignCoderCaseToReviewer ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - canNotAssignCoderCaseToReviewer ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "canNotAssignCoderCaseToReviewer is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that Supervisor can't assign case to Reviewer which is already allocated to CDI and is not "Completed" by Coder
	 * 
	 * @author fmodi
	 * @since 18/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2208:Supervisor can't allocate case(s) which is already allocated to a CDI to Reviewer.", priority = 15)
	public static void canNotAssignCDICaseToReviewer()
	{

		boolean bstatus;
		int i;

		try

		{

			Log4J.logp.info("*************** Started - canNotAssignCDICaseToReviewer ***************");

			//Thread.sleep(5000);
			Common_Lib.waitForObject(landingp_webe.lnk_All, "clickable", 5);

			//For In Queue
			Log4J.logp.info("========== In Can Not Assign Case To Reviewer For CDI when he has case(s) in 'In Queue' ==========");

			ManualAllocation_Lib.assignFreshCase("fm050", "Helen Harris");
			Thread.sleep(2000);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);

			searchcriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			bstatus = SearchCriteria_Lib.search_Case("fm051");
			if (bstatus == true)
			{
				Log4J.logp.info("Status of case is changed from 'Unassigned' to 'In Queue'");
				Assert.assertTrue(true, "Status of case is changed from 'Unassigned' to 'In Queue'");
			}
			else
			{
				Log4J.logp.error("Status of case is not changed from 'Unassigned' to 'In Queue'");
				Assert.assertTrue(false, "Status of case is not changed from 'Unassigned' to 'In Queue'");
			}

			Thread.sleep(2000);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);

			searchcriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			i = 68;
			SearchCriteria_Lib.search_Case("SC00" + i);
			bstatus = ManualAllocation_Lib.checkNoDataAvailable(i);
			if (bstatus == true)
			{
				Log4J.logp.info("Supervisor can not assign case which is 'In Queue' for a CDI to Reviewer");
				Assert.assertTrue(true, "Supervisor can not assign case which is 'In Queue' for a CDI to Reviewer.");
			}
			else
			{
				Log4J.logp.error("Supervisor can assign case which is 'In Queue' for a CDI to Reviewer");
				Assert.assertTrue(false, "Supervisor can assign case which is 'In Queue' for a CDI to Reviewer.");
			}

			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Assign Case To Reviewer For CDI when he has case(s) in 'In Queue' ==========");

			//For In Progress
			Log4J.logp.info("========== In Can Not Assign Case To Reviewer For CDI when he has case(s) in 'In Progress' ==========");

			ManualAllocation_Lib.canNotAssignUserCaseToReviewer("fm007", "CDI", "In Queue", "In Progress", "fm051", "fm052", 68);
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Assign Case To Reviewer For CDI when he has case(s) in 'In Progress' ==========");

			//For "On Hold"
			Log4J.logp.info("========== In Can Not Assign Case To Reviewer For CDI when he has case(s) in 'On Hold' ==========");

			ManualAllocation_Lib.canNotAssignUserCaseToReviewer("fm007", "CDI", "In Progress", "On Hold", "fm052", "fm053", 68);
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Assign Case To Reviewer For CDI when he has case(s) in 'On Hold' ==========");

			//For "Pending"
			Log4J.logp.info("========== In Can Not Assign Case To Reviewer For CDI when he has case(s) in 'Pending' ==========");

			ManualAllocation_Lib.canNotAssignUserCaseToReviewer("fm007", "CDI", "On Hold", "Pending", "fm053", "fm054", 68);
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Assign Case To Reviewer For CDI when he has case(s) in 'Pending' ==========");

			//For "Completed"
			Log4J.logp.info("========== In Can Not Assign Case To Reviewer For CDI when he has case(s) in 'Completed' ==========");

			ManualAllocation_Lib.canNotAssignUserCaseToReviewer("fm007", "CDI", "Pending", "Completed", "fm012", "fm055", 68);
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Assign Case To Reviewer For CDI when he has case(s) in 'Completed' ==========");

			Log4J.logp.info("*************** Ended - canNotAssignCDICaseToReviewer ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - canNotAssignCDICaseToReviewer ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "canNotAssignCDICaseToReviewer is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that Supervisor can't delete CDI when CDI has cases in "In Progress" or "On Hold" or "Pending"
	 * 
	 * @author fmodi
	 * @since 19/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2857:Admin can't delete CDI user role when cases are already allocated to that coder.", priority = 16)
	public static void canNotDeleteCDI()
	{

		try

		{

			Log4J.logp.info("*************** Started - canNotDeleteCDI ***************");

			Thread.sleep(2000);

			//For "In Progress"
			Log4J.logp.info("========== In Can Not Delete CDI when he has case(s) in 'In Progress' ==========");

			//below is ATGO
			//ManualAllocation_Lib.canNotDeleteUser("fm056", "fm057", "Helen Harris", "On Hold");

			//below is ATGO
			ManualAllocation_Lib.canNotDeleteUser("fm007", "fm056", "Helen Harris", "In Progress");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Delete CDI when he has case(s) in 'In Progress' ==========");

			//For "On Hold"
			Log4J.logp.info("========== In Can Not Delete CDI when he has case(s) in 'On Hold' ==========");

			ManualAllocation_Lib.canNotDeleteUser("fm007", "fm057", "Helen Harris", "On Hold");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Delete CDI when he has case(s) in 'On Hold' ==========");

			//For "Pending"
			Log4J.logp.info("========== In Can Not Delete CDI when he has case(s) in 'Pending' ==========");

			//below is ATGO
			//ManualAllocation_Lib.canNotDeleteUser("fm007", "fm057", "Helen Harris", "On Hold");

			//below is ATGO
			ManualAllocation_Lib.canNotDeleteUser("fm007", "fm057", "Helen Harris", "Pending");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Can Not Delete CDI when he has case(s) in 'Pending' ==========");

			Log4J.logp.info("*************** Ended - canNotDeleteCDI ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - canNotDeleteCDI ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "canNotDeleteCDI is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that Supervisor can't delete Reviewer when Reviewer has cases in "In Progress" or "On Hold" or "Pending"
	 * 
	 * @author fmodi
	 * @since 19/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2858:Admin can't delete Reviewer user role when cases are already allocated to that coder.", priority = 17)
	public static void canNotDeleteReviewer()
	{

		try

		{

			Log4J.logp.info("*************** Started - canNotDeleteReviewer ***************");

			Thread.sleep(2000);

			// To generate data for Reviewer
			Log4J.logp.info("========== In Submitting Case by Coder ==========");

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);

			//below is ATGO
			//WorkingScreen_Lib.submit_case("fm013");

			//below is ATGO
			WorkingScreen_Lib.submit_case("fm014");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Submitting Case by Coder ==========");

			//For "In Progress"
			Log4J.logp.info("========== In Can Not Delete Reviewer when he has case(s) in 'In Progress' ==========");

			ManualAllocation_Lib.canNotDeleteUser("fm008", "fm059", "Dorothy Taylor", "In Progress");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Delete Reviewer when he has case(s) in 'In Progress' ==========");

			//For "On Hold"
			Log4J.logp.info("========== In Can Not Delete Reviewer when he has case(s) in 'On Hold' ==========");

			ManualAllocation_Lib.canNotDeleteUser("fm008", "fm060", "Dorothy Taylor", "On Hold");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Not Delete Reviewer when he has case(s) in 'On Hold' ==========");

			//For "Pending"
			Log4J.logp.info("========== In Can Not Delete Reviewer when he has case(s) in 'Pending' ==========");

			ManualAllocation_Lib.canNotDeleteUser("fm008", "fm060", "Dorothy Taylor", "Pending");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Can Not Delete Reviewer when he has case(s) in 'Pending' ==========");

			Log4J.logp.info("*************** Ended - canNotDeleteReviewer ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - canNotDeleteReviewer ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "canNotDeleteReviewer is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that Supervisor can assign case to Coder which is already allocated to CDI
	 * 
	 * @author fmodi
	 * @since 26/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2235:Supervisor can allocate case(s) which are already allocated to a CDI to a Coder.", priority = 18)
	public static void canAssignCDICaseToCoder()
	{

		boolean bstatus;

		try

		{

			Log4J.logp.info("*************** Started - canAssignCDICaseToCoder ***************");

			//Thread.sleep(5000);
			Common_Lib.waitForObject(landingp_webe.lnk_All, "clickable", 5);

			//For In Queue
			Log4J.logp.info("========== In Can Assign Case To Coder when CDI has case(s) in 'In Queue' ==========");
			ManualAllocation_Lib.assignFreshCase("fm061", "Helen Harris");
			Thread.sleep(2000);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);

			searchcriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			bstatus = SearchCriteria_Lib.search_Case("fm062");
			if (bstatus == true)
			{
				Log4J.logp.info("Status of case is changed from 'Unassigned' to 'In Queue'");
				Assert.assertTrue(true, "Status of case is changed from 'Unassigned' to 'In Queue'");
			}
			else
			{
				Log4J.logp.error("Status of case is not changed from 'Unassigned' to 'In Queue'");
				Assert.assertTrue(false, "Status of case is not changed from 'Unassigned' to 'In Queue'");
			}

			Thread.sleep(2000);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);

			searchcriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			bstatus = ManualAllocation_Lib.assignCase("fm067", "Faiz Modi");
			if (bstatus == true)
			{
				Log4J.logp.info("Supervisor can assign case which is 'In Queue' for a CDI to Coder");
				Assert.assertTrue(true, "Supervisor can assign case which is 'In Queue' for a CDI to Coder.");
			}
			else
			{
				Log4J.logp.info("Supervisor can not assign case which is 'In Queue' for a CDI to Coder");
				Assert.assertTrue(false, "Supervisor can not assign case which is 'In Queue' for a CDI to Coder");
			}

			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Assign Case To Coder when CDI has case(s) in 'In Queue' ==========");

			//For In Progress
			Log4J.logp.info("========== In Can Assign Case To Coder when CDI has case(s) in 'In Progress' ==========");

			ManualAllocation_Lib.canAssignRoleCaseToAnotherRole("fm007", "CDI", "Coder", "In Queue", "In Progress", "fm062", "fm063", "fm068", "Susan Wilson");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Assign Case To Coder when CDI has case(s) in 'In Progress' ==========");

			//For "On Hold"
			Log4J.logp.info("========== In Can Assign Case To Coder when CDI has case(s) in 'On Hold' ==========");

			ManualAllocation_Lib.canAssignRoleCaseToAnotherRole("fm007", "CDI", "Coder", "In Progress", "On Hold", "fm063", "fm064", "fm068", "Faiz Modi");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Assign Case To Coder when CDI has case(s) in 'On Hold' ==========");

			//For "Pending"
			Log4J.logp.info("========== In Can Assign Case To Coder when CDI has case(s) in 'Pending' ==========");

			ManualAllocation_Lib.canAssignRoleCaseToAnotherRole("fm007", "CDI", "Coder", "On Hold", "Pending", "fm064", "fm065", "fm068", "Susan Wilson");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Assign Case To Coder when CDI has case(s) in 'Pending' ==========");

			//For "Completed"
			Log4J.logp.info("========== In Can Assign Case To Coder when CDI has case(s) in 'Completed' ==========");

			ManualAllocation_Lib.canAssignRoleCaseToAnotherRole("fm007", "CDI", "Coder", "Pending", "Completed", "fm015", "fm066", "fm068", "Faiz Modi");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Assign Case To Coder when CDI has case(s) in 'Completed' ==========");

			Log4J.logp.info("*************** Ended - canAssignCDICaseToCoder ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - canAssignCDICaseToCoder ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "canAssignCDICaseToCoder is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that Supervisor can assign case to CDI which is already allocated to Coder
	 * 
	 * @author fmodi
	 * @since 26/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2417:Supervisor can allocate case(s) which is already allocated to a Coder to CDI.", priority = 19)
	public static void canAssignCoderCaseToCDI()
	{

		boolean bstatus;

		try

		{

			Log4J.logp.info("*************** Started - canAssignCoderCaseToCDI ***************");

			//Thread.sleep(5000);
			Common_Lib.waitForObject(landingp_webe.lnk_All, "clickable", 5);

			//For In Queue
			Log4J.logp.info("========== In Can Assign Case To CDI when Coder has case(s) in 'In Queue' ==========");
			ManualAllocation_Lib.assignFreshCase("fm069", "Susan Wilson");
			Thread.sleep(2000);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);

			searchcriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			bstatus = SearchCriteria_Lib.search_Case("fm070");
			if (bstatus == true)
			{
				Log4J.logp.info("Status of case is changed from 'Unassigned' to 'In Queue'");
				Assert.assertTrue(true, "Status of case is changed from 'Unassigned' to 'In Queue'");
			}
			else
			{
				Log4J.logp.error("Status of case is not changed from 'Unassigned' to 'In Queue'");
				Assert.assertTrue(false, "Status of case is not changed from 'Unassigned' to 'In Queue'");
			}

			Thread.sleep(2000);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);

			searchcriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			bstatus = ManualAllocation_Lib.assignCase("fm077", "Faiz Modi");
			if (bstatus == true)
			{
				Log4J.logp.info("Supervisor can assign case which is 'In Queue' for a Coder to CDI");
				Assert.assertTrue(true, "Supervisor can assign case which is 'In Queue' for a Coder to CDI.");
			}
			else
			{
				Log4J.logp.info("Supervisor can not assign case which is 'In Queue' for a CDI to Coder");
				Assert.assertTrue(false, "Supervisor can not assign case which is 'In Queue' for a CDI to Coder");
			}

			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Assign Case To CDI when Coder has case(s) in 'In Queue' ==========");

			//For In Progress
			Log4J.logp.info("========== In Can Assign Case To CDI when Coder has case(s) in 'In Progress' ==========");

			ManualAllocation_Lib.canAssignRoleCaseToAnotherRole("fm006", "Coder", "CDI", "In Queue", "In Progress", "fm070", "fm071", "fm078", "Helen Harris");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Assign Case To CDI when Coder has case(s) in 'In Progress' ==========");

			//For "On Hold"
			Log4J.logp.info("========== In Can Assign Case To CDI when Coder has case(s) in 'On Hold' ==========");

			ManualAllocation_Lib.canAssignRoleCaseToAnotherRole("fm006", "Coder", "CDI", "In Progress", "On Hold", "fm071", "fm072", "fm078", "Faiz Modi");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Assign Case To CDI when Coder has case(s) in 'On Hold' ==========");

			//For "Pending"
			Log4J.logp.info("========== In Can Assign Case To CDI when Coder has case(s) in 'Pending' ==========");

			ManualAllocation_Lib.canAssignRoleCaseToAnotherRole("fm006", "Coder", "CDI", "On Hold", "Pending", "fm072", "fm073", "fm078", "Helen Harris");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Assign Case To CDI when Coder has case(s) in 'Pending' ==========");

			//For "Completed"
			Log4J.logp.info("========== In Can Assign Case To CDI when Coder has case(s) in 'Completed' ==========");

			ManualAllocation_Lib.canAssignRoleCaseToAnotherRole("fm006", "Coder", "CDI", "Pending", "Completed", "fm016", "fm074", "fm078", "Faiz Modi");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Assign Case To CDI when Coder has case(s) in 'Completed' ==========");

			//For "Billed"
			Log4J.logp.info("========== In Can Assign Case To CDI when Coder has case(s) in 'Billed' ==========");

			ManualAllocation_Lib.canAssignRoleCaseToAnotherRole("fm006", "Coder", "CDI", "Unassigned", "Billed", "fm017", "fm076", "fm079", "Faiz Modi");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Assign Case To CDI when Coder has case(s) in 'Billed' ==========");

			Log4J.logp.info("*************** Ended - canAssignCoderCaseToCDI ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - canAssignCoderCaseToCDI ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "canAssignCoderCaseToCDI is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that Supervisor can assign case to CDI which is already allocated to Coder
	 * 
	 * @author fmodi
	 * @since 27/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2259:Supervisor can allocate case(s) which is already allocated to a Reviewer to CDI.", priority = 20)
	public static void canAssignReviewerCaseToCDI()
	{

		boolean bstatus;

		try

		{

			Log4J.logp.info("*************** Started - canAssignReviewerCaseToCDI ***************");

			//Thread.sleep(5000);
			Common_Lib.waitForObject(landingp_webe.lnk_All, "clickable", 5);

			// To generate data for Reviewer
			Log4J.logp.info("========== In Submitting Case by Coder ==========");

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm006");
			Thread.sleep(2000);

			WorkingScreen_Lib.submit_case("fm018");
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Submitting Case by Coder ==========");
			Thread.sleep(2000);

			Login_Lib.login("fm005");
			Thread.sleep(2000);

			//For In Queue
			Log4J.logp.info("========== In Can Assign Case To CDI when Reviewer has case(s) in 'In Queue' ==========");
			ManualAllocation_Lib.assignCase("fm081", "Dorothy Taylor");
			Thread.sleep(2000);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);

			searchcriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			bstatus = SearchCriteria_Lib.search_Case("fm082");
			if (bstatus == true)
			{
				Log4J.logp.info("Status of case is changed from 'Unassigned' to 'In Queue'");
				Assert.assertTrue(true, "Status of case is changed from 'Unassigned' to 'In Queue'");
			}
			else
			{
				Log4J.logp.error("Status of case is not changed from 'Unassigned' to 'In Queue'");
				Assert.assertTrue(false, "Status of case is not changed from 'Unassigned' to 'In Queue'");
			}

			Thread.sleep(2000);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);

			searchcriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			bstatus = ManualAllocation_Lib.assignCase("fm087", "Faiz Modi");
			if (bstatus == true)
			{
				Log4J.logp.info("Supervisor can assign case which is 'In Queue' for a Reviewer to CDI");
				Assert.assertTrue(true, "Supervisor can assign case which is 'In Queue' for a Reviewer to CDI.");
			}
			else
			{
				Log4J.logp.info("Supervisor can not assign case which is 'In Queue' for a Reviewer to Coder");
				Assert.assertTrue(false, "Supervisor can not assign case which is 'In Queue' for a Reviewer to Coder");
			}

			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Assign Case To CDI when Reviewer has case(s) in 'In Queue' ==========");

			//For In Progress
			Log4J.logp.info("========== In Can Assign Case To CDI when Reviewer has case(s) in 'In Progress' ==========");

			ManualAllocation_Lib.canAssignRoleCaseToAnotherRole("fm008", "Reviewer", "CDI", "In Queue", "In Progress", "fm082", "fm083", "fm088", "Helen Harris");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Assign Case To CDI when Reviewer has case(s) in 'In Progress' ==========");

			//For "On Hold"
			Log4J.logp.info("========== In Can Assign Case To CDI when Reviewer has case(s) in 'On Hold' ==========");

			ManualAllocation_Lib.canAssignRoleCaseToAnotherRole("fm008", "Reviewer", "CDI", "In Progress", "On Hold", "fm083", "fm084", "fm088", "Faiz Modi");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Assign Case To CDI when Reviewer has case(s) in 'On Hold' ==========");

			//For "Pending"
			Log4J.logp.info("========== In Can Assign Case To CDI when Reviewer has case(s) in 'Pending' ==========");

			ManualAllocation_Lib.canAssignRoleCaseToAnotherRole("fm008", "Reviewer", "CDI", "On Hold", "Pending", "fm084", "fm085", "fm088", "Helen Harris");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Assign Case To CDI when Reviewer has case(s) in 'Pending' ==========");

			//For "Billed"
			Log4J.logp.info("========== In Can Assign Case To CDI when Reviewer has case(s) in 'Billed' ==========");

			ManualAllocation_Lib.canAssignRoleCaseToAnotherRole("fm008", "Reviewer", "CDI", "Pending", "Billed", "fm019", "fm086", "fm088", "Faiz Modi");
			Thread.sleep(2000);

			Log4J.logp.info("========== Finished Can Assign Case To CDI when Reviewer has case(s) in 'Billed' ==========");

			Log4J.logp.info("*************** Ended - canAssignReviewerCaseToCDI ***************");

		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - canAssignReviewerCaseToCDI ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "canAssignReviewerCaseToCDI is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	@AfterClass
	public static void ManualAllocationAfterClass()
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
			if (messagecenter_webe != null)
			{
				messagecenter_webe = null;
			}
			if (login_webe != null)
			{
				login_webe = null;
			}
			if (medicalrecordpnl_webe != null)
			{
				medicalrecordpnl_webe = null;
			}
			if (groupingpnl_webe != null)
			{
				groupingpnl_webe = null;
			}
			if (issuepnl_webe != null)
			{
				issuepnl_webe = null;
			}

			Login_Lib.logOut_App();
			//Login_Lib.logIn_App(DriverTestNG.username, DriverTestNG.password);

			Log4J.logp.info("In AfterClass for ManualAllocation");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}