package com.ezdi.testscripts;

import org.openqa.selenium.WebDriver;
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
import com.ezdi.library.WorkFlow_Lib;
import com.ezdi.library.WorkingScreen_Lib;
import com.ezdi.webelements.Comman_WebE;
import com.ezdi.webelements.GroupinPnl_WebE;
import com.ezdi.webelements.IssuePnl_WebE;
import com.ezdi.webelements.LandingP_WebE;
import com.ezdi.webelements.ManualAllocation_WebE;
import com.ezdi.webelements.MedicalRecordPnl_WebE;
import com.ezdi.webelements.SearchCriteria_WebE;

public class WorkFlow
{

	public static WebDriver				driver;
	public static LandingP_WebE			landingp_webe;
	public static Comman_WebE			common_webe;
	public static WebDriverWait			wait;
	public static SearchCriteria_WebE	searchcriteria_webe;
	public static GroupinPnl_WebE		groupingpnl_webe;
	public static IssuePnl_WebE			issuepnl_webe;
	public static MedicalRecordPnl_WebE	medicalrecordpnl_webe;
	public static ManualAllocation_WebE	manualallocation_webe;

	@BeforeClass
	public static void WorkFlowBeforeClass()
	{
		try
		{
			driver = ExecutionSetup.getDriver();
			landingp_webe = LandingP_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			searchcriteria_webe = SearchCriteria_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			manualallocation_webe = ManualAllocation_WebE.getInstance(driver);

			Log4J.logp.info("In BeforeClass for Workflow");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * To check as a supervisor if CDI have unassigned cases with and without Discharge Summary
	 *
	 * @author mparikh
	 * @since 26/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2197:(Supervisor)To check if CDI have unassigned cases with Discharge Summary" + " ezCAC_MVP_Reg-2198:(Supervisor)To check if CDI have unassigned cases without Discharge Summary", priority = 0)
	public static void check_DS_CDI()
	{

		String strDischargeDate;

		try
		{
			Log4J.logp.info("********** Started :::check_DS_CDI **********");
			Thread.sleep(2000);
			Login_Lib.login("mp001");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);

			landingp_webe.lnk_All.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			SearchCriteria_Lib.search_Case("mp001");

			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 10);
			strDischargeDate = landingp_webe.td_DischargeDate.getText();

			if (strDischargeDate != null)
			{
				Log4J.logp.info("CDI have unassigned case with Discharge Summary");

			}
			else
			{
				Log4J.logp.info("CDI have unassigned case without Discharge Summary");

			}

			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: check_DS_CDI **********");
			Assert.assertTrue(true, "CDI have unassigned case with Discharge Summary");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - check_DS_CDI **********");
			e.printStackTrace();
			Assert.assertTrue(false, "check_DS_CDI is failed");
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	/**
	 * To check as a supervisor if CDI have assigned cases with and without Discharge Summary
	 *
	 * @author mparikh
	 * @since 26/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2199:(Supervisor)To check if CDI have assigned cases with Discharge Summary", priority = 1)
	public static void assign_case_CDI()
	{

		String strDischargeDate;

		try
		{
			Log4J.logp.info("********** Started :::assign_case_CDI **********");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("mp001");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);

			ManualAllocation_Lib.assignCase("mp002", "Malay Parikh");
			Common_Lib.waitForObject(searchcriteria_webe.btn_Search, "visibility", 10);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 10);

			searchcriteria_webe.lnk_SearchCreteria.click();
			//Common_Lib.waitForObject(searchcriteria_webe.btn_Search, "visibility", 10);

			SearchCriteria_Lib.search_Case("mp003");

			Common_Lib.waitForObject(landingp_webe.tbl_FirstRowSearch, "visibility", 10);

			strDischargeDate = landingp_webe.td_DischargeDate.getText();
			if (strDischargeDate != null)
			{
				Log4J.logp.info("CDI have assigned case with Discharge Summary");

			}
			else
			{
				Log4J.logp.info("CDI have assigned case without Discharge Summary");

			}
			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: assign_case_CDI **********");
			Assert.assertTrue(true, "CDI have assigned case with Discharge Summary");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - assign_case_CDI **********");
			e.printStackTrace();
			Assert.assertTrue(false, "assign_case_CDI is failed");
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	/**
	 * To check that Supervisor can able to search CDI's case with all status criteria
	 *
	 * @author mparikh
	 * @since 27/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2213:(Supervisor)To check when CDI working on any case" + "ezCAC_MVP_Reg-2217:(Supervisor)To check when CDI puts cases in 'On Hold'status" + "ezCAC_MVP_Reg-2225:(Supervisor)To check when CDI puts cases in 'Pending' status"
			+ "ezCAC_MVP_Reg-2232:(Supervisor)To check when CDI Completes the case", priority = 2)
	public static void status_Case_CDI()
	{

		try
		{
			Log4J.logp.info("********** Started :::status_Case_CDI **********");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("mp001");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);

			//For In Progress
			Log4J.logp.info("========== In Supervisor can check 'In Progress' status of CDI ==========");
			WorkFlow_Lib.supervisorCanSearchForCDI("In Progress", "mp003", "mp004");
			Common_Lib.waitForObject(landingp_webe.tbl_FirstRowSearch, "visibility", 10);
			Log4J.logp.info("========== Finished Supervisor can check 'In Progress' status of CDI ==========");

			//For On Hold
			Log4J.logp.info("========== In Supervisor can check 'On Hold' status of CDI ==========");
			WorkFlow_Lib.supervisorCanSearchForCDI("On Hold", "mp004", "mp005");
			Common_Lib.waitForObject(landingp_webe.tbl_FirstRowSearch, "visibility", 10);
			Log4J.logp.info("========== Finished Supervisor can check 'On Hold' status of CDI ==========");

			//For Pending
			Log4J.logp.info("========== In Supervisor can check 'Pending' status of CDI ==========");
			WorkFlow_Lib.supervisorCanSearchForCDI("Pending", "mp005", "mp006");
			Common_Lib.waitForObject(landingp_webe.tbl_FirstRowSearch, "visibility", 10);
			Log4J.logp.info("========== Finished Supervisor can check 'Pending' status of CDI ==========");

			//For Completed
			Log4J.logp.info("========== In Supervisor can check 'Completed' status of CDI ==========");
			WorkFlow_Lib.supervisorCanSearchForCDI("Completed", "mp001", "mp007");
			Common_Lib.waitForObject(landingp_webe.tbl_FirstRowSearch, "visibility", 10);
			Log4J.logp.info("========== Finished Supervisor can check 'Completed' status of CDI ==========");
			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: status_Case_CDI **********");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - status_Case_CDI **********");
			e.printStackTrace();
			Assert.assertTrue(false, "status_Case_CDI is failed");
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	/**
	 * To check that coder can able to open unassigned case which is also unassigned for CDI
	 *
	 * @author mparikh
	 * @since 27/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2248:(Coder)To check if CDI have unassigned cases with Discharge Summary", priority = 3)
	public static void coderOpen_CDIUnassignedCase()
	{
		boolean bstatus;
		//boolean bstatusfalse = true; //logic for hard false. Need to use without any dependency 
		try
		{

			Log4J.logp.info("********** Started :::coderOpen_CDIUnassignedCase **********");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("mp001");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);

			landingp_webe.lnk_All.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			landingp_webe.lnk_CDI.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);

			bstatus = SearchCriteria_Lib.search_Case("mp008");

			if (bstatus == true)
			{
				Log4J.logp.info("CDI have unassigned case with Discharge Summary");
				Assert.assertTrue(true, "CDI have unassigned case with Discharge Summary");
			}
			else
			{

				Log4J.logp.error("CDI have unassigned case without Discharge Summary");
				//Assert.assertTrue(false, "CDI have unassigned case without Discharge Summary");
			}

			landingp_webe.lnk_Coding.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);

			bstatus = Common_Lib.openCase("mp009");
			if (bstatus == true)
			{
				Log4J.logp.info("Coder can able to open unassigned case with Discharge Summary");

			}
			else
			{
				Log4J.logp.error("Coder can not able to open unassigned case with Discharge Summary");

			}
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);

			groupingpnl_webe.btn_Later.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: coderOpen_CDIUnassignedCase **********");
			Assert.assertTrue(true, "Coder can able to open unassigned case with Discharge Summary");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - coderOpen_CDIUnassignedCase **********");
			e.printStackTrace();
			Assert.assertTrue(false, "coderOpen_CDIUnassignedCase is failed");
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	/**
	 * To check that coder can able to open the case which is assigned to CDI user
	 *
	 * @author mparikh
	 * @since 27/11/2014
	 */
	@Test(description = " ezCAC_MVP_Reg-2252:(Coder)To check if CDI have assigned cases with Discharge Summary", priority = 4)
	public static void coderOpen_CDIassignedCase()
	{

		boolean bstatus;

		try
		{

			Log4J.logp.info("********** Started :::coderOpen_CDIassignedCase **********");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("mp001");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);

			ManualAllocation_Lib.assignCase("mp010", "Helen Harris");
			Common_Lib.waitForObject(searchcriteria_webe.btn_Search, "visibility", 10);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 10);

			searchcriteria_webe.lnk_SearchCreteria.click();
			Common_Lib.waitForObject(searchcriteria_webe.btn_Search, "visibility", 10);

			bstatus = SearchCriteria_Lib.search_Case("mp011");

			if (bstatus == true)
			{
				Log4J.logp.info("CDI have assigned case with Discharge Summary");
				Assert.assertTrue(true, "CDI have assigned case with Discharge Summary");
			}
			else
			{
				Log4J.logp.error("CDI have assigned case without Discharge Summary");

			}

			landingp_webe.lnk_Coding.click();
			bstatus = Common_Lib.openCase("mp012");
			if (bstatus == true)
			{
				Log4J.logp.info("Coder can able to open CDI's assigned case");

			}
			else
			{
				Log4J.logp.error("Coder can not able to open CDI's assigned case");
				//Assert.assertTrue(false, "Coder can not able to open CDI's assigned case");
			}
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 10);
			groupingpnl_webe.btn_Later.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: coderOpen_CDIassignedCase **********");
			Assert.assertTrue(true, "Coder can able to open CDI's assigned case");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - coderOpen_CDIassignedCase **********");
			e.printStackTrace();
			Assert.assertTrue(false, "coderOpen_CDIassignedCase is failed");
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	/**
	 * To check that Supervisor can able to search CDI's case with all status criteria
	 *
	 * @author mparikh
	 * @since 27/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2262:(Coder)To check when CDI working on any case" + " ezCAC_MVP_Reg-2266:(Coder)To check when CDI puts cases in 'On Hold' status" + "ezCAC_MVP_Reg-2280:(Coder)To check when CDI puts cases in 'Pending' status", priority = 5)
	public static void lockedCaseForCoder()
	{

		try
		{

			Log4J.logp.info("********** Started :::lockedCaseForCoder **********");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("mp001");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);

			//For In Progress
			Log4J.logp.info("========== In Coder getting locked case if case is in 'In Progress' for CDI ==========");
			WorkFlow_Lib.checkLockedCase("In Progress", "Unassigned", "mp013", "mp016");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished Coder getting locked case if case is in 'In Progress' for CDI ==========");

			//For On Hold
			Log4J.logp.info("========== In Coder getting locked case if case is in 'On Hold' for CDI ==========");
			WorkFlow_Lib.checkLockedCase("On Hold", "Unassigned", "mp014", "mp016");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished Coder getting locked case if case is in 'On Hold' for CDI ==========");

			//For Pending
			Log4J.logp.info("========== In Coder getting locked case if case is in 'Pending' for CDI ==========");
			WorkFlow_Lib.checkLockedCase("Pending", "Unassigned", "mp015", "mp016");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished Coder getting locked case if case is in 'Pending' for CDI ==========");
			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: lockedCaseForCoder **********");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - lockedCaseForCoder **********");
			e.printStackTrace();
			Assert.assertTrue(false, "lockedCaseForCoder is failed");
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	///////////////////////////////////////////////// Nupur ////////////////////////////////////////////////

	/**
	 * (Supervisor) To check if coder have unassigned case
	 *
	 * @author nchourasiya
	 * @since 28/11/2014
	 *
	 * */

	@Test(description = "ezCAC_MVP_Reg-2317:(Supervisor)To check if Coder have unassigned cases +ezCAC_MVP_Reg-2318:(Supervisor)To check if Coder have assigned cases", priority = 20)
	public static void coderUnassignedAndAssignedCase()
	{
		boolean bCaseStatus = false;
		try
		{
			Log4J.logp.info("********************************************************* Started coderUnassignedAndAssignedCase *********************************************************");
			Thread.sleep(2000);
			Log4J.logp.info("================ezCAC_MVP_Reg-2317:(Supervisor)To check if Coder have unassigned cases===================");
			Login_Lib.logOut_App();
			Login_Lib.login("nc003");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("NC011");
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);
			bCaseStatus = Common_Lib.checkElementPresent(manualallocation_webe.ico_FreshCase);
			if (bCaseStatus == true)
			{
				Log4J.logp.info("Supervisor is able to view case details with Search filter (Unassigned)");
				bCaseStatus = true;
			}
			else
			{
				bCaseStatus = false;
			}
			Log4J.logp.info("================ezCAC_MVP_Reg-2318:(Supervisor)To check if Coder have assigned cases===================");

			Thread.sleep(2000);
			driver.navigate().refresh();

			ManualAllocation_Lib.assignCase("NC011", "Nupur Chourasiya");

			Thread.sleep(2000);
			driver.navigate().refresh();

			SearchCriteria_Lib.search_Case("NC012");
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);
			bCaseStatus = Common_Lib.checkElementPresent(landingp_webe.ico_InQueue);
			if (bCaseStatus == true)
			{
				Log4J.logp.info("Supervisor is able to view case details with Search filter (In Queue + Coder)");
				bCaseStatus = true;
			}
			else
			{
				bCaseStatus = false;
			}
			Thread.sleep(2000);
			Log4J.logp.info("********************************************Ending coderUnassignedAndAssignedCase******************************************************");
			Assert.assertTrue(bCaseStatus, "coderUnassignedAndAssignedCase is Pass");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - coderUnassignedAndAssignedCase **********");
			e.printStackTrace();
			Assert.assertTrue(false, "coderUnassignedAndAssignedCase is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
		}
	}

	/**
	 * To check that Supervisor can able to search Coder case with all status criteria
	 *
	 * @author nchourasiya
	 * @since 28/11/2014
	 * */
	@Test(description = " ezCAC_MVP_Reg-2322:(Supervisor)To check when coder working on any case + ezCAC_MVP_Reg-2323:(Supervisor)To check when coder puts cases in On Hold status + ezCAC_MVP_Reg-2324:(Supervisor)To check when coder puts cases in Pending status + ezCAC_MVP_Reg-2325:(Supervisor)To check when coder completes the case and request to review + ezCAC_MVP_Reg-2326:(Supervisor)To check when coder billed the cases", priority = 21)
	public static void searchCoderCaseWithAllStatus()
	{

		try
		{
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc003");

			Log4J.logp.info("************************************************** Started searchCoderCaseWithAllStatus ******************************************************");
			Thread.sleep(2000);
			//For In Progress
			Log4J.logp.info("========== ezCAC_MVP_Reg-2322:(Supervisor)To check when coder working on any case ==========");
			WorkFlow_Lib.userCanSearchCaseForCoder("Coder", "In Progress", "NC013", "NC014", "Supervisor", "nc003");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Supervisor can check 'In Progress' status of Coder ==========");

			//For On Hold
			Log4J.logp.info("========== ezCAC_MVP_Reg-2323:(Supervisor)To check when coder puts cases in On Hold status ==========");
			WorkFlow_Lib.userCanSearchCaseForCoder("Coder", "On Hold", "NC014", "NC015", "Supervisor", "nc003");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Supervisor can check 'On Hold' status of Coder ==========");

			//For Pending
			Log4J.logp.info("==========  ezCAC_MVP_Reg-2324:(Supervisor)To check when coder puts cases in Pending status ==========");
			WorkFlow_Lib.userCanSearchCaseForCoder("Coder", "Pending", "NC015", "NC016", "Supervisor", "nc003");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Supervisor can check 'Pending' status of Coder ==========");

			//For Completed
			Log4J.logp.info("========== ezCAC_MVP_Reg-2325:(Supervisor)To check when coder completes the case and request to review ==========");
			WorkFlow_Lib.userCanSearchCaseForCoder("Coder", "Completed", "NC017", "NC018", "Supervisor", "nc003");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Supervisor can check 'Completed' status of Coder ==========");

			//For Billed
			Log4J.logp.info("========== ezCAC_MVP_Reg-2326:(Supervisor)To check when coder billed the cases ==========");
			WorkFlow_Lib.userCanSearchCaseForCoder("Coder", "Billed", "NC018", "NC019", "Supervisor", "nc003");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Supervisor can check 'Completed' status of Coder ==========");
			Thread.sleep(2000);

			Log4J.logp.info("**************************************************Ending searchCoderCaseWithAllStatus******************************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - searchCoderCaseWithAllStatus **********");
			e.printStackTrace();
			Assert.assertTrue(false, "searchCoderCaseWithAllStatus is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
		}
	}

	/**
	 * (CDI)This method is to check if coder have assigned and unassigned cases
	 * 
	 * @author nchourasiya
	 * @since 1/12/2014
	 * */

	@Test(description = "ezCAC_MVP_Reg-2327:(CDI)To check if Coder have unassigned cases + ezCAC_MVP_Reg-2328:(CDI)To check if Coder have assigned cases", priority = 22)
	public static void CDIOpen_CoderAssignUnassignCase()
	{
		boolean bCaseStatus;
		try
		{
			Log4J.logp.info("********************************************************* Started CDIOpen_CoderAssignUnassignCase *********************************************************");
			Thread.sleep(2000);
			Log4J.logp.info("================ezCAC_MVP_Reg-2327:(CDI)To check if Coder have unassigned cases===================");
			Login_Lib.logOut_App();
			Login_Lib.login("nc003");
			Thread.sleep(2000);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("NC020"); // change value
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);
			bCaseStatus = Common_Lib.checkElementPresent(manualallocation_webe.ico_FreshCase);
			if (bCaseStatus == true)
			{
				Log4J.logp.info("CDI is able to view case details of case which is Unassignned to Coder");
				bCaseStatus = true;
			}
			else
			{
				bCaseStatus = false;
			}
			Log4J.logp.info("================ezCAC_MVP_Reg-2328:(CDI)To check if Coder have assigned cases===================");

			Thread.sleep(2000);
			driver.navigate().refresh();
			Thread.sleep(2000);
			/*landingp_webe.lnk_All.click();
			Thread.sleep(1000);*/
			ManualAllocation_Lib.assignCase("NC020", "Susan Wilson"); //change case no

			Thread.sleep(2000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(1000);
			SearchCriteria_Lib.search_Case("NC020");
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);
			bCaseStatus = Common_Lib.checkElementPresent(manualallocation_webe.ico_FreshCase);
			if (bCaseStatus == true)
			{
				Log4J.logp.info("CDI is able to view case details of case which is assignned to Coder");
				bCaseStatus = true;
			}
			else
			{
				bCaseStatus = false;
			}
			Thread.sleep(2000);
			Log4J.logp.info("********************************************Ending CDIOpen_CoderAssignUnassignCase******************************************************");
			Assert.assertTrue(bCaseStatus, "CDIOpen_CoderAssignUnassignCase is Pass");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - CDIOpen_CoderAssignUnassignCase **********");
			e.printStackTrace();
			Assert.assertTrue(false, "CDIOpen_CoderAssignUnassignCase is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
		}
	}

	/**
	 * (CDI)This method is to check when coder is working in any case
	 * 
	 * @author nchourasiya
	 * @since 1/12/2014
	 * 
	 * */
	@Test(description = "ezCAC_MVP_Reg-2329:(CDI)To check when Coder working on any case(Unassigned/Assigned)", priority = 23)
	public static void CDISearch_CoderCaseInAllStatus()
	{
		try
		{
			Login_Lib.logOut_App();
			Login_Lib.login("NC002"); //susan coder

			Log4J.logp.info("************************************************** Started CDISearch_CoderCaseInAllStatus ******************************************************");
			Thread.sleep(2000);

			//For In Progress
			Log4J.logp.info("========== ezCAC_MVP_Reg-2329:(CDI)To check when Coder working on any case(Unassigned/Assigned) ==========");
			WorkFlow_Lib.userCanSearchCaseForCoder("Coder", "In Progress", "NC021", "NC022", "CDI", "nc003");
			Thread.sleep(2000);
			Log4J.logp.info("Cases is Locked for the CDI And not able to view case Details - Test Pass");
			Log4J.logp.info("========== Finished CDI can check 'In Progress' status of Coder ==========");
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("NC002");

			//For On Hold
			Log4J.logp.info("========== ezCAC_MVP_Reg-2323:(Supervisor)To check when coder puts cases in On Hold status ==========");
			WorkFlow_Lib.userCanSearchCaseForCoder("Coder", "On Hold", "NC022", "NC023", "CDI", "nc003");
			Thread.sleep(2000);
			Log4J.logp.info("Cases is Locked for the CDI And not able to view case Details - Test Pass");
			Log4J.logp.info("========== Finished CDI can check 'On Hold' status of Coder ==========");
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("NC002");

			//For Pending
			Log4J.logp.info("==========  ezCAC_MVP_Reg-2324:(Supervisor)To check when coder puts cases in Pending status ==========");
			WorkFlow_Lib.userCanSearchCaseForCoder("Coder", "Pending", "NC023", "NC024", "CDI", "nc003");
			Thread.sleep(2000);
			Log4J.logp.info("Cases is Locked for the CDI And not able to view case Details - Test Pass");
			Log4J.logp.info("========== Finished CDI can check 'Pending' status of Coder ==========");
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("NC002");

			//For Completed
			Log4J.logp.info("========== ezCAC_MVP_Reg-2325:(Supervisor)To check when coder completes the case and request to review ==========");
			WorkFlow_Lib.userCanSearchCaseForCoder("Coder", "Completed", "NC019", "NC025", "CDI", "nc003");
			Thread.sleep(2000);
			Log4J.logp.info("Cases is Locked for the CDI And not able to view case Details - Test Pass");
			Log4J.logp.info("========== Finished CDI can check 'Completed' status of Coder ==========");
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("NC002");

			//For Billed
			Log4J.logp.info("========== ezCAC_MVP_Reg-2326:(Supervisor)To check when coder billed the cases ==========");
			WorkFlow_Lib.userCanSearchCaseForCoder("Coder", "Billed", "NC020", "NC026", "CDI", "nc003");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 10);

			groupingpnl_webe.btn_Later.click();
			Log4J.logp.info("Cases is UnLocked for the CDI with Unassigned Status And Able to Open case - Test Pass");
			Log4J.logp.info("========== Finished CDI can check 'Completed' status of Coder ==========");
			Thread.sleep(2000);
			Log4J.logp.info("*********************************************************Ending CDISearch_CoderCaseInAllStatus*******************************************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - CDISearch_CoderCaseInAllStatus **********");
			e.printStackTrace();
			Assert.assertTrue(false, "CDISearch_CoderCaseInAllStatus is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
		}
	}

	/**
	 * (Reviewer)This method is to check if coder have assigned or unassigned case
	 * 
	 * @author nchourasiya
	 * @since 1/12/2014
	 * */

	@Test(description = "ezCAC_MVP_Reg-2355:(Reviewer)To check if Coder have Unassigned cases + ezCAC_MVP_Reg-2356:(Reviewer)To check if Coder have assigned cases", priority = 24)
	public static void reviewerSearch_CoderAssignUnassignCase()
	{
		boolean bCaseStatus = false;
		String text;
		try
		{
			Log4J.logp.info("******************************************* Started reviewerSearch_CoderAssignUnassignCase ************************************************");
			Thread.sleep(2000);
			Log4J.logp.info("================ezCAC_MVP_Reg-2355:(Reviewer)To check if Coder have Unassigned cases===================");
			Login_Lib.logOut_App();
			Login_Lib.login("nc003");
			Thread.sleep(2000);
			landingp_webe.lnk_Review.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("NC027"); // change value
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);

			text = "No data available in table";

			if (landingp_webe.tbl_FirstRowSearch.isDisplayed() == true && landingp_webe.tbl_FirstRowSearch.getText().equalsIgnoreCase(text))
			{

				Log4J.logp.info(landingp_webe.tbl_FirstRowSearch.getText() + " - Text Found on searching - Test Pass ");
				Assert.assertTrue(true, "Cdi complete case not found in Reviewer's Search.");
			}
			else
			{

				Log4J.logp.error("Case open sucessfully - Test Fail");
				Assert.assertTrue(false, "Cdi complete case found in Reviewer's Search");
			}

			Log4J.logp.info("================ezCAC_MVP_Reg-2356:(Reviewer)To check if Coder have assigned cases===================");

			Thread.sleep(2000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			landingp_webe.lnk_All.click();
			Thread.sleep(1000);
			ManualAllocation_Lib.assignCase("NC027", "Susan Wilson"); //change case no

			Thread.sleep(2000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			landingp_webe.lnk_Review.click();
			Thread.sleep(1000);
			SearchCriteria_Lib.search_Case("NC027");
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);

			text = "No data available in table";

			if (landingp_webe.tbl_FirstRowSearch.isDisplayed() == true && landingp_webe.tbl_FirstRowSearch.getText().equalsIgnoreCase(text))
			{

				Log4J.logp.info(landingp_webe.tbl_FirstRowSearch.getText() + " - Text Found on searching - Test Pass ");
				bCaseStatus = true;
			}
			else
			{

				Log4J.logp.error("Case open sucessfully - Test Fail");
				bCaseStatus = false;
			}
			Thread.sleep(2000);
			Log4J.logp.info("*********************************Ending reviewerSearch_CoderAssignUnassignCase********************************************");
			Assert.assertTrue(bCaseStatus, "reviewerSearch_CoderAssignUnassignCase is Pass");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - reviewerSearch_CoderAssignUnassignCase **********");
			e.printStackTrace();
			Assert.assertTrue(false, "reviewerSearch_CoderAssignUnassignCase is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
		}
	}

	/**
	 * This method is to check the behavior for reviewer when coder puts case in any status.
	 * 
	 * @author nchourasiya
	 * @since 2/12/2014
	 * */

	@Test(description = "ezCAC_MVP_Reg-2357:(Reviewer)To check when Coder working on any case + ezCAC_MVP_Reg-2358:(Reviewer)To check when Coder puts cases in On Hold status + ezCAC_MVP_Reg-2359:(Reviewer)To check when Coder puts cases in Pending status + ezCAC_MVP_Reg-2360:(Reviewer)To check when Coder completes the case and request to review + ezCAC_MVP_Reg-2361:(Reviewer)To check when Coder billed the cases", priority = 25)
	public static void reviewerSearch_CoderCaseInAllStatus()
	{
		try
		{
			Login_Lib.logOut_App();
			Login_Lib.login("NC002"); //susan coder

			Log4J.logp.info("********************************************* Started reviewerSearch_CoderCaseInAllStatus *********************************************");
			Thread.sleep(2000);

			//For In Progress
			Log4J.logp.info("========== ezCAC_MVP_Reg-2357:(Reviewer)To check when Coder working on any case ==========");
			WorkFlow_Lib.userCanSearchCaseForCoder("Coder", "In Progress", "NC028", "NC029", "Reviewer", "nc003");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Reviewer can check 'In Progress' status of Coder ==========");
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("NC002");

			//For On Hold
			Log4J.logp.info("========== ezCAC_MVP_Reg-2358:(Reviewer)To check when Coder puts cases in On Hold status ==========");
			WorkFlow_Lib.userCanSearchCaseForCoder("Coder", "On Hold", "NC029", "NC030", "Reviewer", "nc003");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Reviewer can check 'On Hold' status of Coder ==========");
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("NC002");

			//For Pending
			Log4J.logp.info("========== ezCAC_MVP_Reg-2359:(Reviewer)To check when Coder puts cases in Pending status ==========");
			WorkFlow_Lib.userCanSearchCaseForCoder("Coder", "Pending", "NC030", "NC031", "Reviewer", "nc003");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Reviewer can check 'Pending' status of Coder ==========");
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("NC002");

			//For Completed
			Log4J.logp.info("========== ezCAC_MVP_Reg-2360:(Reviewer)To check when Coder completes the case and request to review ==========");
			WorkFlow_Lib.userCanSearchCaseForCoder("Coder", "Completed", "NC021", "NC032", "Reviewer", "nc003");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "clickable", 10);
			groupingpnl_webe.btn_Later.click();
			Log4J.logp.info("========== Finished Reviewer can check 'Completed' status of Coder ==========");
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("NC002");

			//For Billed
			Log4J.logp.info("========== ezCAC_MVP_Reg-2361:(Reviewer)To check when Coder billed the cases ==========");
			WorkFlow_Lib.userCanSearchCaseForCoder("Coder", "Billed", "NC022", "NC034", "Reviewer", "nc003");

			Log4J.logp.info("========== Finished Reviewer can check 'Completed' status of Coder ==========");
			Thread.sleep(2000);
			Log4J.logp.info("**************************************************Ending CDISearch_CoderCaseInAllStatus*********************************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - CDISearch_CoderCaseInAllStatus **********");
			e.printStackTrace();
			Assert.assertTrue(false, "CDISearch_CoderCaseInAllStatus is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
		}
	}

	/**
	 * (CDI)This method is to check when coder have unassigned or assigned case
	 * 
	 * @author nchourasiya
	 * @since 2/12/2014
	 * */

	@Test(description = " ezCAC_MVP_Reg-2362:(CDI)To check when Coder have Unassigned cases + ezCAC_MVP_Reg-2363:(CDI)To check when Coder have assigned cases", priority = 26)
	public static void cdiSearch_CoderAssignUnassignCaseCdiCompleted()
	{
		boolean bCaseStatus = false;
		String text;
		try
		{
			Log4J.logp.info("*********************************************** Started cdiSearch_CoderAssignUnassignCaseCdiCompleted *****************************************************");
			Thread.sleep(2000);
			Log4J.logp.info("================ezCAC_MVP_Reg-2362:(CDI)To check when Coder have Unassigned cases===================");
			Login_Lib.logOut_App();
			//case complete
			Login_Lib.login("nc024"); // Logged in as Helen (CDI)
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("NC023"); // case id
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc003");
			Thread.sleep(2000);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("NC035"); // change value
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);

			text = "No data available in table";

			if (landingp_webe.tbl_FirstRowSearch.isDisplayed() == true && landingp_webe.tbl_FirstRowSearch.getText().equalsIgnoreCase(text))
			{

				Log4J.logp.info(landingp_webe.tbl_FirstRowSearch.getText() + " - Text Found on searching - Test Pass ");
				Assert.assertTrue(true, "Cdi complete case not found in Reviewer's Search.");
			}
			else
			{

				Log4J.logp.error("Case open sucessfully - Test Fail");
				Assert.assertTrue(false, "Cdi complete case found in Reviewer's Search");
			}

			Log4J.logp.info("================ezCAC_MVP_Reg-2363:(CDI)To check when Coder have assigned cases===================");

			Thread.sleep(2000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			landingp_webe.lnk_All.click();
			Thread.sleep(1000);
			ManualAllocation_Lib.assignCase("NC035", "Susan Wilson"); //change case no

			Thread.sleep(2000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(1000);
			SearchCriteria_Lib.search_Case("NC035");//change case no
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);

			text = "No data available in table";

			if (landingp_webe.tbl_FirstRowSearch.isDisplayed() == true && landingp_webe.tbl_FirstRowSearch.getText().equalsIgnoreCase(text))
			{

				Log4J.logp.info(landingp_webe.tbl_FirstRowSearch.getText() + " - Text Found on searching - Test Pass ");
				bCaseStatus = true;
			}
			else
			{

				Log4J.logp.error("Case open sucessfully - Test Fail");
				bCaseStatus = false;
			}
			Thread.sleep(2000);
			Log4J.logp.info("*********************************************Ending cdiSearch_CoderAssignUnassignCaseCdiCompleted**************************************");
			Assert.assertTrue(bCaseStatus, "cdiSearch_CoderAssignUnassignCaseCdiCompleted is Pass");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - cdiSearch_CoderAssignUnassignCaseCdiCompleted **********");
			e.printStackTrace();
			Assert.assertTrue(false, "cdiSearch_CoderAssignUnassignCaseCdiCompleted is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
		}
	}

	/**
	 * This method is to check the behavior for CDI when coder puts case in any status.
	 * 
	 * @author nchourasiya
	 * @since 2/12/2014
	 * */

	@Test(description = " ezCAC_MVP_Reg-2364:(CDI)To check when Coder working on any case +  ezCAC_MVP_Reg-2365:(CDI)To check when Coder puts case in On Hold status +   ezCAC_MVP_Reg-2366:(CDI)To check when Coder puts case in Pending status +   ezCAC_MVP_Reg-2367:(CDI)To check when Coder completes the cases and request to review +   ezCAC_MVP_Reg-2368:(CDI)To check when Coder billed the cases", priority = 27)
	public static void cdi_Search()
	{
		try
		{
			Login_Lib.logOut_App();
			Login_Lib.login("NC002"); //susan coder

			Log4J.logp.info("*************************************** Started cdi_Search **************************************************");
			Thread.sleep(2000);
			//For In Progress
			Log4J.logp.info("========== ezCAC_MVP_Reg-2357:(CDI)To check when Coder working on any case ==========");
			WorkFlow_Lib.cdiSearchCoderCaseForCdiCompleteCase("Coder", "In Progress", "NC036", "NC040", "CDI", "nc003");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished CDI can check 'In Progress' status of Coder ==========");
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("NC002");

			//For On Hold
			Log4J.logp.info("========== ezCAC_MVP_Reg-2358:(CDI)To check when Coder puts cases in On Hold status ==========");

			WorkFlow_Lib.cdiSearchCoderCaseForCdiCompleteCase("Coder", "On Hold", "NC037", "NC040", "CDI", "nc003");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished CDI can check 'On Hold' status of Coder ==========");
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("NC002");

			//For Pending
			Log4J.logp.info("========== ezCAC_MVP_Reg-2359:(CDI)To check when Coder puts cases in Pending status ==========");
			WorkFlow_Lib.cdiSearchCoderCaseForCdiCompleteCase("Coder", "Pending", "NC038", "NC040", "CDI", "nc003");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished CDI can check 'Pending' status of Coder ==========");
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("NC002");

			//For Completed
			Log4J.logp.info("========== ezCAC_MVP_Reg-2360:(CDI)To check when Coder completes the case and request to review ==========");
			WorkFlow_Lib.cdiSearchCoderCaseForCdiCompleteCase("Coder", "Completed", "NC024", "NC040", "CDI", "nc003");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished CDI can check 'Completed' status of Coder ==========");
			Login_Lib.logOut_App();
			Thread.sleep(1000);

			Login_Lib.login("nc024"); // Logged in as Helen (CDI)
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("NC025");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("NC002");
			Thread.sleep(2000);
			//For Billed
			Log4J.logp.info("========== ezCAC_MVP_Reg-2361:(CDI)To check when Coder billed the cases ==========");
			WorkFlow_Lib.cdiSearchCoderCaseForCdiCompleteCase("Coder", "Billed", "NC026", "NC042", "CDI", "nc003");

			Log4J.logp.info("========== Finished CDI can check 'Completed' status of Coder ==========");
			Thread.sleep(2000);
			Log4J.logp.info("****************************************************Ending cdi_Search*************************************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - cdi_Search **********");
			e.printStackTrace();
			Assert.assertTrue(false, "cdi_Search is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
		}
	}

	/**
	 * (Coder)This method is to check if reviewer have unassigned or assigned case.
	 * 
	 * @author nchourasiya
	 * @since 4/12/2014
	 * 
	 * */
	@Test(description = " ezCAC_MVP_Reg-2403:(Coder)To check if Reviewer have Unassigned cases + ezCAC_MVP_Reg-2404:(Coder)To check if Reviewer have assigned cases", priority = 28)
	public static void coderSearch_ReviewerUnassignAssignCase()
	{
		boolean bCaseStatus = false;
		String text;
		try
		{
			Log4J.logp.info("*********************************************** Started coderSearch_ReviewerUnassignAssignCase *****************************************************");
			Thread.sleep(2000);
			Log4J.logp.info("================ezCAC_MVP_Reg-2403:(Coder)To check if Reviewer have Unassigned cases===================");
			Login_Lib.logOut_App();
			//case complete
			Login_Lib.login("NC002"); // Logged in as Susan (Coder)
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("NC027");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc003"); // Logged in Nupur (Coder)
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("NC044");
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);

			text = "No data available in table";

			if (landingp_webe.tbl_FirstRowSearch.isDisplayed() == true && landingp_webe.tbl_FirstRowSearch.getText().equalsIgnoreCase(text))
			{

				Log4J.logp.info(landingp_webe.tbl_FirstRowSearch.getText() + " - Text Found on searching - Test Pass ");
				bCaseStatus = true;
			}
			else
			{

				Log4J.logp.error("Case open sucessfully - Test Fail");
				bCaseStatus = false;
			}

			Log4J.logp.info("================ezCAC_MVP_Reg-2404:(Coder)To check if Reviewer have assigned cases===================");

			Thread.sleep(2000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			landingp_webe.lnk_All.click();
			Thread.sleep(1000);
			ManualAllocation_Lib.assignCase("NC045", "Lisa Anderson");

			Thread.sleep(2000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(1000);
			SearchCriteria_Lib.search_Case("NC044");
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);

			text = "No data available in table";

			if (landingp_webe.tbl_FirstRowSearch.isDisplayed() == true && landingp_webe.tbl_FirstRowSearch.getText().equalsIgnoreCase(text))
			{

				Log4J.logp.info(landingp_webe.tbl_FirstRowSearch.getText() + " - Text Found on searching - Test Pass ");
				bCaseStatus = true;
			}
			else
			{

				Log4J.logp.error("Case open sucessfully - Test Fail");
				bCaseStatus = false;
			}
			Thread.sleep(2000);
			Log4J.logp.info("*********************************************Ending cdiSearch_CoderAssignUnassignCaseCdiCompleted**************************************");
			Assert.assertTrue(bCaseStatus, "coderSearch_ReviewerUnassignAssignCase is Pass");

		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - coderSearch_ReviewerUnassignAssignCase **********");
			e.printStackTrace();
			Assert.assertTrue(false, "coderSearch_ReviewerUnassignAssignCase is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
		}
	}

	/**
	 * This method is to check the behavior for coder when reviewer puts case in any status.
	 * 
	 * @author nchourasiya
	 * @since 4/12/2014
	 * */

	@Test(description = "ezCAC_MVP_Reg-2406:(Coder)To check when Reviewer working on any case + ezCAC_MVP_Reg-2407:(Coder)To check when Reviewer puts cases in On Hold status +  ezCAC_MVP_Reg-2408:(Coder)To check when Reviewer puts cases in Pending status + ezCAC_MVP_Reg-2409:(Coder)To check when Reviewer billed the cases", priority = 29)
	public static void coderSearch_ReviewerCaseInAllStatus()
	{
		try
		{
			Login_Lib.logOut_App();
			Login_Lib.login("nc023"); // Lisa (Reviewer)

			Log4J.logp.info("*************************************** Started coderSearch_ReviewerCaseInAllStatus **************************************************");
			Thread.sleep(2000);

			//For In Progress
			Log4J.logp.info("========== ezCAC_MVP_Reg-2406:(Coder)To check when Reviewer working on any case ==========");
			WorkFlow_Lib.SearchCoder_ForReviewerInAllStage("Reviewer", "In Progress", "NC046", "NC044", "Coder", "NC002");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Coder can check 'In Progress' status of Reviewer ==========");
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("nc023");

			//For On Hold
			Log4J.logp.info("========== ezCAC_MVP_Reg-2358:(Coder)To check when Reviewer puts cases in On Hold status ==========");

			WorkFlow_Lib.SearchCoder_ForReviewerInAllStage("Reviewer", "On Hold", "NC047", "NC044", "Coder", "NC002");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Coder can check 'On Hold' status of Reviewer ==========");
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("nc023");

			//For Pending
			Log4J.logp.info("========== ezCAC_MVP_Reg-2359:(Coder)To check when Reviewer puts cases in Pending status ==========");
			WorkFlow_Lib.SearchCoder_ForReviewerInAllStage("Reviewer", "Pending", "NC048", "NC044", "Coder", "NC002");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Coder can check 'Pending' status of Reviewer ==========");
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("nc023");

			//For Billed
			Log4J.logp.info("========== ezCAC_MVP_Reg-2361:(Coder)To check when Reviewer billed the cases ==========");
			WorkFlow_Lib.SearchCoder_ForReviewerInAllStage("Reviewer", "Billed", "NC028", "NC044", "Coder", "NC002");

			Log4J.logp.info("========== Finished Coder can check 'billed' status of Reviewer ==========");
			Thread.sleep(2000);
			Log4J.logp.info("****************************************************Ending coderSearch_ReviewerCaseInAllStatus*************************************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - coderSearch_ReviewerCaseInAllStatus **********");
			e.printStackTrace();
			Assert.assertTrue(false, "coderSearch_ReviewerCaseInAllStatus is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
		}

	}

	//////////////////////////////////////////////////////////Nupur /////////////////////////////////////////////////////////////////

	/**
	 * To check that coder can able to open the case which is already completed by CDI user.
	 *
	 * @author mparikh
	 * @since 28/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2283:(Coder)To check when CDI Completes the case with Discharge Summary", priority = 6)
	public static void coderOpen_CDIcompletedCase()
	{

		boolean bstatus;

		try
		{
			Log4J.logp.info("********** Started :::coderOpen_CDIcompletedCase **********");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Login_Lib.login("mp002");
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("mp002");
			Common_Lib.waitForObject(landingp_webe.lnk_CDI, "clickable", 10);

			Login_Lib.logOut_App();

			Login_Lib.login("mp001");

			Common_Lib.waitForObject(landingp_webe.lnk_Coding, "clickable", 10);
			landingp_webe.lnk_Coding.click();
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 10);

			bstatus = Common_Lib.openCase("mp018");

			if (bstatus == true)
			{
				Log4J.logp.info("Coder can able to open the CDI's 'Completed' case");
				Assert.assertTrue(true, "Coder can able to open the CDI's 'Completed' case");
			}
			else
			{
				Log4J.logp.error("Coder can not able to open the CDI's 'Completed' case");
				Assert.assertTrue(false, "Coder can not able to open the CDI's 'Completed' case");
			}

			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);
			groupingpnl_webe.btn_Later.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: coderOpen_CDIcompletedCase **********");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - coderOpen_CDIcompletedCase **********");
			e.printStackTrace();
			Assert.assertTrue(false, "coderOpen_CDIcompletedCase is failed");
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	/**
	 * To check that if coder billed any unassigned case then cdi have that unassigned case in his/her queue.
	 *
	 * @author mparikh
	 * @since 28/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2369:(Coder)To check if CDI have Unassigned cases which is billed by the coder", priority = 7)
	public static void cdiUnassignedCase_BilledByCoder()
	{

		boolean bstatus;

		try
		{

			Log4J.logp.info("********** Started :::cdiUnassignedCase_BilledByCoder **********");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(1000);

			Login_Lib.login("mp001");
			Common_Lib.waitForObject(landingp_webe.lnk_Coding, "clickable", 10);
			landingp_webe.lnk_Coding.click();
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "clickable", 10);

			WorkingScreen_Lib.submit_case("mp003");
			Common_Lib.waitForObject(landingp_webe.lnk_All, "clickable", 10);
			landingp_webe.lnk_All.click();
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "clickable", 10);

			bstatus = SearchCriteria_Lib.search_Case("mp020");

			if (bstatus == true)
			{
				Log4J.logp.info("CDI has 'unassigned' case which is 'billed' by coder");
				Assert.assertTrue(true, "CDI has 'unassigned' case which is 'billed' by coder");
			}
			else
			{
				Log4J.logp.error("CDI has not 'unassigned' case which is 'billed' by coder");

			}
			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Common_Lib.waitForObject(landingp_webe.lnk_Coding, "clickable", 10);

			landingp_webe.lnk_Coding.click();
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "clickable", 10);

			SearchCriteria_Lib.search_Case("mp021");
			Common_Lib.waitForObject(landingp_webe.td_lockedcase, "clickable", 10);

			bstatus = Common_Lib.checkElementPresent(landingp_webe.td_lockedcase);

			if (bstatus == true)
			{
				Log4J.logp.info("Coder can not open 'billed' case which is 'Unassigned' for CDI");

			}
			else
			{
				Log4J.logp.error("Coder can able to open 'billed' case which is 'Unassigned' for CDI");

			}
			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: cdiUnassignedCase_BilledByCoder **********");
			Assert.assertTrue(true, "Coder can not open 'billed' case which is 'Unassigned' for CDI");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - cdiUnassignedCase_BilledByCoder **********");
			e.printStackTrace();
			Assert.assertTrue(false, "cdiUnassignedCase_BilledByCoder is failed");
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	/**
	 * To check thaat coder can not able to open the case when case is assigned to cdi.
	 *
	 * @author mparikh
	 * @since 01/12/2014
	 */
	@Test(description = " ezCAC_MVP_Reg-2370:(Coder)To check when CDI have assigned case which is billed by the Coder" + "ezCAC_MVP_Reg-2286:(Coder)To check when CDI open the case which is billed by the coder"
			+ "ezCAC_MVP_Reg-2319:(Coder)To check when CDI puts cases in 'On Hold' status which is already billed by the coder" + "ezCAC_MVP_Reg-2320:(Coder)To check when CDI puts cases in 'Pending' status which is already billed by the coder"
			+ "ezCAC_MVP_Reg-2321:(Coder)To check when CDI Completes the case which is already billed by the coder", priority = 8)
	public static void lockedCaseForCoder_AssignedToCDI()
	{

		try
		{
			Log4J.logp.info("********** Started :::lockedCaseForCoder_AssignedToCDI **********");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("mp001");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);

			landingp_webe.lnk_Coding.click();
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 10);
			WorkingScreen_Lib.submit_case("mp004");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			landingp_webe.lnk_All.click();
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 10);
			ManualAllocation_Lib.assignCase("mp023", "Helen Harris");
			Common_Lib.waitForObject(searchcriteria_webe.btn_Search, "visibility", 10);

			//For In Queue
			Log4J.logp.info("========== In Coder getting locked case if case is in 'In Queue' for CDI ==========");
			WorkFlow_Lib.checkLockedCase("In Queue", "Billed", "mp024", "mp028");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished Coder getting locked case if case is in 'In Queue' for CDI ==========");

			//For In Progress
			Log4J.logp.info("========== In Coder getting locked case if case is in 'In Progress' for CDI ==========");
			WorkFlow_Lib.checkLockedCase("In Progress", "Billed", "mp024", "mp028");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished Coder getting locked case if case is in 'In Progress' for CDI ==========");

			//For On Hold
			Log4J.logp.info("========== In Coder getting locked case if case is in 'On Hold' for CDI ==========");
			WorkFlow_Lib.checkLockedCase("On Hold", "Billed", "mp025", "mp028");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished Coder getting locked case if case is in 'On Hold' for CDI ==========");

			//For Pending
			Log4J.logp.info("========== In Coder getting locked case if case is in 'Pending' for CDI ==========");
			WorkFlow_Lib.checkLockedCase("Pending", "Billed", "mp026", "mp028");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished Coder getting locked case if case is in 'Pending' for CDI ==========");

			//For Completed
			Log4J.logp.info("========== In Coder getting locked case if case is in 'Completed' for CDI ==========");
			WorkFlow_Lib.checkLockedCase("Completed", "Billed", "mp005", "mp028");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished Coder getting locked case if case is in 'Completed' for CDI ==========");
			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: lockedCaseForCoder_AssignedToCDI **********");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - lockedCaseForCoder_AssignedToCDI **********");
			e.printStackTrace();
			Assert.assertTrue(false, "lockedCaseForCoder_AssignedToCDI is failed");
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	/**
	 * To check as a supervisor if CDI have unassigned cases with and without Discharge Summary
	 *
	 * @author mparikh
	 * @since 01/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2294:(Reviewer)To check if CDI have unassigned cases with Discharge Summary", priority = 9)
	public static void checkCDIUnassignedForReviewer()
	{

		boolean bstatusCDI, bstatusReviewer;
		int i1, i2;

		try
		{

			Log4J.logp.info("********** Started :::checkCDIUnassignedForReviewer **********");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("mp002");

			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 10);
			i1 = 71;
			SearchCriteria_Lib.search_Case("SC00" + i1);
			bstatusCDI = ManualAllocation_Lib.checkNoDataAvailable(i1);

			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
			Common_Lib.waitForObject(landingp_webe.lnk_Review, "clickable", 10);
			landingp_webe.lnk_Review.click();
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 10);
			i2 = 72;
			SearchCriteria_Lib.search_Case("SC00" + i2);
			bstatusReviewer = ManualAllocation_Lib.checkNoDataAvailable(i2);

			if (bstatusCDI != bstatusReviewer)
			{
				Log4J.logp.info("Reviewer does not have CDI's 'Unassigned' case in his Landing page");

			}
			else
			{
				Log4J.logp.error("Reviewer does have CDI's 'Unassigned' case in his Landing page");

			}

			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: checkCDIUnassignedForReviewer **********");
			Assert.assertTrue(true, "Reviewer does not have CDI's 'Unassigned' case in his Landing page");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - checkCDIUnassignedForReviewer **********");
			e.printStackTrace();
			Assert.assertTrue(false, "checkCDIUnassignedForReviewer is failed");
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	/**
	 * To check that coder can not able to open the case when case is assigned to cdi.
	 *
	 * @author mparikh
	 * @since 01/12/2014
	 */
	@Test(description = " ezCAC_MVP_Reg-2297:(Reviewer)To check if CDI have assigned cases with Discharge Summary" + "ezCAC_MVP_Reg-2304:(Reviewer)To check when CDI working on any case" + "ezCAC_MVP_Reg-2305:(Reviewer)To check when CDI puts cases in 'On Hold' status"
			+ "ezCAC_MVP_Reg-2307:(Reviewer)To check when CDI puts cases in 'Pending' status" + "ezCAC_MVP_Reg-2308:(Reviewer)To check when CDI completes the cases", priority = 10)
	public static void caseNotDisplayedForReviewer_AssignedToCDI()
	{

		try
		{

			Log4J.logp.info("********** Started :::caseNotDisplayedForReviewer_AssignedToCDI **********");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("mp001");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);

			landingp_webe.lnk_All.click();
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 10);
			ManualAllocation_Lib.assignCase("mp030", "Helen Harris");
			Common_Lib.waitForObject(searchcriteria_webe.btn_Search, "visibility", 10);

			//For In Queue
			Log4J.logp.info("========== In Reviewer case is not displayed if case is in 'In Queue' for CDI ==========");
			WorkFlow_Lib.checkCaseIsNotDisplayed("In Queue", "mp031");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished Reviewer case is not displayed if case is in 'In Queue' for CDI ==========");

			//For In Progress
			Log4J.logp.info("========== In Reviewer case is not displayed if case is in 'In Progress' for CDI ==========");
			WorkFlow_Lib.checkCaseIsNotDisplayed("In Progress", "mp031");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished Reviewer case is not displayed if case is in 'In Progress' for CDI ==========");

			//For On Hold
			Log4J.logp.info("========== In Reviewer case is not displayed if case is in 'On Hold' for CDI ==========");
			WorkFlow_Lib.checkCaseIsNotDisplayed("On Hold", "mp032");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished Reviewer case is not displayed if case is in 'On Hold' for CDI ==========");

			//For Pending
			Log4J.logp.info("========== In Reviewer case is not displayed if case is in 'Pending' for CDI ==========");
			WorkFlow_Lib.checkCaseIsNotDisplayed("Pending", "mp033");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished Reviewer case is not displayed if case is in 'Pending' for CDI ==========");

			//For Completed
			Log4J.logp.info("========== In Reviewer case is not displayed if case is in 'Completed' for CDI ==========");
			WorkFlow_Lib.checkCaseIsNotDisplayed("Completed", "mp006");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished Reviewer case is not displayed if case is in 'Completed' for CDI ==========");
			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: caseNotDisplayedForReviewer_AssignedToCDI **********");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - caseNotDisplayedForReviewer_AssignedToCDI **********");
			e.printStackTrace();
			Assert.assertTrue(false, "caseNotDisplayedForReviewer_AssignedToCDI is failed");
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	/**
	 * To check that reviewer have unassigned case which is completed by coder
	 *
	 * @author mparikh
	 * @since 02/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2373:(Supervisor)To check if Reviewer have Unassigned cases", priority = 11)
	public static void reviewerHaveUnassignedCase_CompletedByCoder()
	{

		boolean bstatus;

		try
		{
			Log4J.logp.info("********** Started :::reviewerHaveUnassignedCase_CompletedByCoder **********");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("mp001");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);

			landingp_webe.lnk_Coding.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			WorkingScreen_Lib.submit_case("mp007");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");

			Common_Lib.waitForObject(landingp_webe.lnk_All, "visibility", 10);

			landingp_webe.lnk_All.click();
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 10);

			bstatus = SearchCriteria_Lib.search_Case("mp036");

			if (bstatus == true)
			{
				Log4J.logp.info("Reviewer have unassigned case which is completed by coder");

			}
			else
			{
				Log4J.logp.error("Not found unassigned case in Reviewer which is completed by coder");

			}
			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: reviewerHaveUnassignedCase_CompletedByCoder **********");
			Assert.assertTrue(true, "Reviewer have unassigned case which is completed by coder");

		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - reviewerHaveUnassignedCase_CompletedByCoder **********");
			e.printStackTrace();
			Assert.assertTrue(false, "reviewerHaveUnassignedCase_CompletedByCoder is failed");
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	/**
	 * To check that supervisor can able to search reviewer's assigned case
	 *
	 * @author mparikh
	 * @since 02/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2374:(Supervisor)To check if Reviewer have assigned cases", priority = 12)
	public static void supervisorCanSearchAssignedCaseTo_Reviewer()
	{
		boolean bstatus;

		try
		{

			Log4J.logp.info("********** Started :::supervisorCanSearchAssignedCaseTo_Reviewer **********");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("mp001");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);

			landingp_webe.lnk_Coding.click();

			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 10);
			WorkingScreen_Lib.submit_case("mp008");
			Common_Lib.waitForObject(landingp_webe.lnk_All, "visibility", 10);

			landingp_webe.lnk_All.click();

			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 10);

			ManualAllocation_Lib.assignCase("mp038", "Lisa Anderson");
			Common_Lib.waitForObject(searchcriteria_webe.btn_Search, "visibility", 10);

			//to scroll page upwards
			Common_Lib.scrollUp_without_element();
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 10);

			searchcriteria_webe.lnk_SearchCreteria.click();
			//Common_Lib.waitForObject(searchcriteria_webe.btn_Search, "visibility", 10);

			bstatus = SearchCriteria_Lib.search_Case("mp039");

			Common_Lib.waitForObject(landingp_webe.tbl_FirstRowSearch, "visibility", 10);

			if (bstatus == true)
			{
				Log4J.logp.info("Supervisor can able to search case with 'In Queue + Reviewer'");

			}
			else
			{
				Log4J.logp.error("Supervisor can not able to search case with 'In Queue + Reviewer'");

			}
			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: supervisorCanSearchAssignedCaseTo_Reviewer **********");
			Assert.assertTrue(true, "Supervisor can able to search case with 'In Queue + Reviewer'");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - supervisorCanSearchAssignedCaseTo_Reviewer **********");
			e.printStackTrace();
			Assert.assertTrue(false, "supervisorCanSearchAssignedCaseTo_Reviewer is failed");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	/**
	 * To check that Supervisor can able to search with different status which is assigned to reviewer.
	 *
	 * @author mparikh
	 * @since 02/12/2014
	 */
	@Test(description = " ezCAC_MVP_Reg-2375:(Supervisor)To check when Reviewer working on any case" + "ezCAC_MVP_Reg-2376:(Supervisor)To check when Reviewer puts cases in 'On Hold' status" + "ezCAC_MVP_Reg-2377:(Supervisor)To check when Reviewer puts cases in 'Pending' status"
			+ "ezCAC_MVP_Reg-2378:(Supervisor)To check when Reviewer billed the cases", priority = 13)
	public static void supervisorCanSearchForAllStatus_Reviewer()
	{

		try
		{

			Log4J.logp.info("********** Started :::supervisorCanSearchForAllStatus_Reviewer **********");
			Thread.sleep(2000);

			//For In Progress
			Log4J.logp.info("========== In Supervisor can search Reviewer's case 'In Progress' ==========");
			WorkFlow_Lib.supervisorCanSearchFor_Reviewer("In Progress", "mp039", "mp040");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished Supervisor can search Reviewer's case 'In Progress' ==========");

			//For On Hold
			Log4J.logp.info("========== In Supervisor can search Reviewer's case 'On Hold' ==========");
			WorkFlow_Lib.supervisorCanSearchFor_Reviewer("On Hold", "mp040", "mp041");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished Supervisor can search Reviewer's case 'On Hold' ==========");

			//For Pending
			Log4J.logp.info("========== In Supervisor can search Reviewer's case 'Pending' ==========");
			WorkFlow_Lib.supervisorCanSearchFor_Reviewer("Pending", "mp041", "mp042");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished Supervisor can search Reviewer's case 'Pending' ==========");

			//For Billed
			Log4J.logp.info("========== In Supervisor can search Reviewer's case 'Billed' ==========");
			WorkFlow_Lib.supervisorCanSearchFor_Reviewer("Billed", "mp009", "mp043");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished Supervisor can search Reviewer's case 'Billed' ==========");
			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: supervisorCanSearchForAllStatus_Reviewer **********");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - supervisorCanSearchForAllStatus_Reviewer **********");
			e.printStackTrace();
			Assert.assertTrue(false, "supervisorCanSearchForAllStatus_Reviewer is failed");
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	/**
	 * To check that Reviewer's unassigned case should be locked for the CDI user.
	 *
	 * @author mparikh
	 * @since 03/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2379:(CDI)To check if Reviewer have Unassigned cases", priority = 14)
	public static void cdiLockedCase_UnassignedToReviewer()
	{

		boolean bstatus;

		try
		{

			Log4J.logp.info("********** Started :::cdiLockedCase_UnassignedToReviewer **********");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(1000);

			Login_Lib.login("mp001");
			Common_Lib.waitForObject(landingp_webe.lnk_Coding, "clickable", 10);
			landingp_webe.lnk_Coding.click();
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "clickable", 10);

			WorkingScreen_Lib.submit_case("mp010");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Login_Lib.logOut_App();
			Login_Lib.login("mp002");

			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "clickable", 10);

			SearchCriteria_Lib.search_Case("mp045");
			Common_Lib.waitForObject(landingp_webe.td_lockedcase, "clickable", 10);

			bstatus = Common_Lib.checkElementPresent(landingp_webe.td_lockedcase);

			if (bstatus == true)
			{
				Log4J.logp.info("CDI has locked cases which is 'Unassigned'in Reviewer");

			}
			else
			{
				Log4J.logp.error("CDI has not locked cases which is 'Unassigned'in Reviewer");

			}
			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: cdiLockedCase_UnassignedToReviewer **********");
			Assert.assertTrue(true, "CDI has locked cases which is 'Unassigned'in Reviewer");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - cdiLockedCase_UnassignedToReviewer **********");
			e.printStackTrace();
			Assert.assertTrue(false, "cdiLockedCase_UnassignedToReviewer is failed");
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	/**
	 * To check that supervisor can able to search reviewer's assigned case
	 *
	 * @author mparikh
	 * @since 03/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2380:(CDI)To check if Reviewer have assigned cases" + "ezCAC_MVP_Reg-2381:(CDI)To check when Reviewer working on any case" + "ezCAC_MVP_Reg-2390:(CDI)To check when Reviewer puts cases in 'On Hold' status"
			+ "ezCAC_MVP_Reg-2395:(CDI)To check when Reviewer puts cases in 'Pending' status", priority = 15)
	public static void cdiLockedCase_AssignedToReviewer()
	{

		try
		{

			Log4J.logp.info("********** Started :::cdiLockedCase_AssignedToReviewer **********");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(1000);

			Login_Lib.login("mp001");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			landingp_webe.lnk_Coding.click();

			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 10);
			WorkingScreen_Lib.submit_case("mp011");
			Common_Lib.waitForObject(landingp_webe.lnk_All, "visibility", 10);

			landingp_webe.lnk_All.click();

			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 10);

			ManualAllocation_Lib.assignCase("mp047", "Lisa Anderson");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);

			//For In Queue
			Log4J.logp.info("========== In CDI has locked case when Reviewer has case in 'In Queue' ==========");
			WorkFlow_Lib.reviewerToCDI_checkLockedCase("In Queue", "mp049", "mp048");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished CDI has locked case when Reviewer has case in 'In Queue' ==========");

			//For In Progress
			Log4J.logp.info("========== In CDI has locked case when Reviewer has case in 'In Progress' ==========");
			WorkFlow_Lib.reviewerToCDI_checkLockedCase("In Progress", "mp049", "mp048");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished CDI has locked case when Reviewer has case in 'In Progress' ==========");

			//For On Hold
			Log4J.logp.info("========== In CDI has locked case when Reviewer has case in 'On Hold' ==========");
			WorkFlow_Lib.reviewerToCDI_checkLockedCase("On Hold", "mp050", "mp048");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished CDI has locked case when Reviewer has case in 'On Hold' ==========");

			//For Pending
			Log4J.logp.info("========== In CDI has locked case when Reviewer has case in 'Pending' ==========");
			WorkFlow_Lib.reviewerToCDI_checkLockedCase("Pending", "mp051", "mp048");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished CDI has locked case when Reviewer has case in 'Pending' ==========");
			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: cdiLockedCase_AssignedToReviewer **********");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - cdiLockedCase_AssignedToReviewer **********");
			e.printStackTrace();
			Assert.assertTrue(false, "cdiLockedCase_AssignedToReviewer is failed");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	/**
	 * To check that CDI can able to open unassigned case which is already billed by Reviewer.
	 *
	 * @author mparikh
	 * @since 04/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2401:(CDI)To check when Reviewer Billed the cases", priority = 16)
	public static void cdiOpenCaseAfterBilledBy_Reviewer()
	{

		boolean bstatus;

		try
		{

			Log4J.logp.info("********** Started :::cdiOpenCaseAfterBilledBy_Reviewer **********");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(1000);

			Login_Lib.login("mp003");
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "clickable", 10);

			WorkingScreen_Lib.submit_case("mp012");
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 3);
			bstatus = SearchCriteria_Lib.search_Case("mp053");

			if (bstatus == true)
			{
				Log4J.logp.info("Reviewer has case with 'Billed' status");
				Assert.assertTrue(true, "Reviewer has case with 'Billed' status");
			}
			else
			{
				Log4J.logp.error("Reviewer has not case with 'Billed' status");

			}

			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("mp002");
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "clickable", 10);
			bstatus = Common_Lib.openCase("mp048");

			if (bstatus == true)
			{
				Log4J.logp.info("CDI can able to open 'Unassigned' case which is already billed by Reviewer");

			}
			else
			{
				Log4J.logp.error("CDI can not able to open 'Unassigned' case which is already billed by Reviewer");

			}

			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);

			groupingpnl_webe.btn_Later.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: cdiOpenCaseAfterBilledBy_Reviewer **********");
			Assert.assertTrue(true, "CDI can able to open 'Unassigned' case which is already billed by Reviewer");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - cdiOpenCaseAfterBilledBy_Reviewer **********");
			e.printStackTrace();
			Assert.assertTrue(false, "cdiOpenCaseAfterBilledBy_Reviewer is failed");
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	/**
	 * To check that coder can not able to open the case when case is assigned to cdi.
	 *
	 * @author mparikh
	 * @since 04/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2410:(CDI)To check if Reviewer have Unassigned case" + " ezCAC_MVP_Reg-2411:(CDI)To check if Reviewer have assigned case" + " ezCAC_MVP_Reg-2412:(CDI)To check when Reviewer working on any case."
			+ " ezCAC_MVP_Reg-2413:(CDI)To check when Reviewer puts cases in 'On Hold' status" + "ezCAC_MVP_Reg-2414:(CDI)To check when Reviewer puts cases in 'Pending' status" + "ezCAC_MVP_Reg-2415:(CDI)To check when Reviewer Billed the cases", priority = 17)
	public static void caseNotDisplayedForCDI_AssignedToReviewer()
	{

		try
		{

			Log4J.logp.info("********** Started :::caseNotDisplayedForCDI_AssignedToReviewer **********");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("mp004");

			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 4);
			WorkingScreen_Lib.submit_case("mp013");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);

			//For Unassigned
			Log4J.logp.info("========== In CDI case is not displayed if case is in 'Unassigned' for Reviewer ==========");
			WorkFlow_Lib.cdiCompletedToReviewer_CaseNotDisplayed("Unassigned", "mp055");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished CDI case is not displayed if case is in 'Unassigned' for Reviewer ==========");

			//For In Queue
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
			Common_Lib.waitForObject(landingp_webe.lnk_All, "visibility", 10);

			Log4J.logp.info("========== In CDI case is not displayed if case is in 'In Queue' for Reviewer ==========");
			landingp_webe.lnk_All.click();
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 10);

			ManualAllocation_Lib.assignCase("mp055", "Lisa Anderson");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			WorkFlow_Lib.cdiCompletedToReviewer_CaseNotDisplayed("In Queue", "mp056");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished CDI case is not displayed if case is in 'In Queue' for Reviewer ==========");

			//For In Progress
			Log4J.logp.info("========== In CDI case is not displayed if case is in 'In Progress' for Reviewer ==========");
			WorkFlow_Lib.cdiCompletedToReviewer_CaseNotDisplayed("In Progress", "mp056");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished CDI case is not displayed if case is in 'In Progress' for Reviewer ==========");

			//For On Hold
			Log4J.logp.info("========== In CDI case is not displayed if case is in 'On Hold' for Reviewer ==========");
			WorkFlow_Lib.cdiCompletedToReviewer_CaseNotDisplayed("On Hold", "mp057");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished CDI case is not displayed if case is in 'On Hold' for Reviewer ==========");

			//For Pending
			Log4J.logp.info("========== In CDI case is not displayed if case is in 'Pending' for Reviewer ==========");
			WorkFlow_Lib.cdiCompletedToReviewer_CaseNotDisplayed("Pending", "mp058");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished CDI case is not displayed if case is in 'Pending' for Reviewer ==========");

			//For Billed
			Log4J.logp.info("========== In CDI case is not displayed if case is in 'Billed' for Reviewer ==========");
			WorkFlow_Lib.cdiCompletedToReviewer_CaseNotDisplayed("Billed", "mp014");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Log4J.logp.info("========== Finished CDI case is not displayed if case is in 'Billed' for Reviewer ==========");
			Thread.sleep(2000);
			Log4J.logp.info("********** Ended ::: caseNotDisplayedForReviewer_AssignedToCDI **********");
		}
		catch (Exception e)
		{
			Log4J.logp.error("********** Problem found in - caseNotDisplayedForCDI_AssignedToReviewer **********");
			e.printStackTrace();
			Assert.assertTrue(false, "caseNotDisplayedForCDI_AssignedToReviewer is failed");
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("mp001");
		}
	}

	@AfterClass
	public static void WorkFlowAfterClass()
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
			if (searchcriteria_webe != null)
			{
				searchcriteria_webe = null;
			}
			if (medicalrecordpnl_webe != null)
			{
				medicalrecordpnl_webe = null;
			}
			if (groupingpnl_webe != null)
			{
				groupingpnl_webe = null;
			}
			if (common_webe != null)
			{
				common_webe = null;
			}

			Log4J.logp.info("In AfterClass for Workflow");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
