package com.ezdi.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.webelements.AddGroup_WebE;
import com.ezdi.webelements.AddPhysician_WebE;
import com.ezdi.webelements.AddUser_WebE;
import com.ezdi.webelements.AdminHome_WebE;
import com.ezdi.webelements.Comman_WebE;
import com.ezdi.webelements.GroupinPnl_WebE;
import com.ezdi.webelements.LandingP_WebE;
import com.ezdi.webelements.ManualAllocation_WebE;
import com.ezdi.webelements.MedicalRecordPnl_WebE;
import com.ezdi.webelements.SearchCriteria_WebE;

public class WorkFlow_Lib
{
	static WebDriver				driver;
	static AddUser_WebE				addUser_webe;
	static AddPhysician_WebE		addPhysician_webe;
	static AddGroup_WebE			addGroup_webe;
	static Actions					dragger;
	static WebDriverWait			wait;
	static Comman_WebE				comman_webe;
	static SearchCriteria_WebE		searchCriteria_webe;
	static LandingP_WebE			landingp_webe;
	static ManualAllocation_WebE	manualAllocation_webe;
	static AdminHome_WebE			adminhome_webe;
	static GroupinPnl_WebE			groupingpnl_webe;
	static MedicalRecordPnl_WebE	medicalrecordpnl_webe;

	/**
	 * This method is used so that Supervisor can able to search with all status for CDI user.
	 * 
	 * @author mparikh
	 * @since 27/11/2014
	 */
	public static boolean supervisorCanSearchForCDI(String status, String caseId, String searchId)
	{

		boolean bstatus;

		try
		{
			Log4J.logp.info("------------------- In supervisorCanSearchForCDI for status='" + status + "' started -------------------");
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			manualAllocation_webe = ManualAllocation_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			Thread.sleep(2000);

			landingp_webe.lnk_CDI.click();

			Thread.sleep(2000);

			if (status.equals("In Progress"))
			{
				Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

				driver.navigate().back();
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			}
			else if (status.equals("On Hold"))
			{
				Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

				groupingpnl_webe.btn_Later.click();
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			}
			else if (status.equals("Pending"))
			{
				Common_Lib.putCaseInPendingStatus(caseId);
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			}
			else if (status.equals("Completed"))
			{
				WorkingScreen_Lib.submit_case(caseId);
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			}

			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			bstatus = SearchCriteria_Lib.search_Case(searchId);

			if (bstatus == true)
			{
				Log4J.logp.info("Supervisor can able to search with '" + status + "' + CDI");
				Assert.assertTrue(true, "Supervisor can able to search with '" + status + "' + CDI");
			}
			else
			{
				Log4J.logp.info("Supervisor can not able to search with '" + status + "' + CDI");
				Assert.assertTrue(false, "Supervisor can not able to search with '" + status + "' + CDI");
			}
			Thread.sleep(2000);
			Log4J.logp.info("------------------- In supervisorCanSearchForCDI- completed -------------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("------------------- supervisorCanSearchForCDI is failed -------------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used so that Supervisor can able to search for coder's locked case which is access by CDI user with different status(In Queue, In
	 * Progress, On Hold, Pending, Completed).
	 * 
	 * @author mparikh
	 * @since 27/11/2014
	 */
	public static boolean checkLockedCase(String cdiStatus, String coderStatus, String caseId, String searchId)
	{

		boolean bstatus;

		try
		{
			Log4J.logp.info("*************** In supervisorCanSearchForCDI for CDI status='" + cdiStatus + "' and coderStatus='" + coderStatus + "' started ***************");
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			manualAllocation_webe = ManualAllocation_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);

			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("mp002");
			Thread.sleep(2000);
			if (cdiStatus.equals("In Queue"))
			{
				SearchCriteria_Lib.search_Case(caseId);
				Thread.sleep(2000);

			}

			else if (cdiStatus.equals("In Progress"))
			{
				Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

				driver.navigate().back();
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			}
			else if (cdiStatus.equals("On Hold"))
			{
				Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

				groupingpnl_webe.btn_Later.click();
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			}
			else if (cdiStatus.equals("Pending"))
			{
				Common_Lib.putCaseInPendingStatus(caseId);
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			}
			else if (cdiStatus.equals("Completed"))
			{
				WorkingScreen_Lib.submit_case(caseId);
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			}

			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("mp001");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();

			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case(searchId);
			Thread.sleep(2000);
			bstatus = Common_Lib.checkElementPresent(landingp_webe.td_lockedcase);
			Thread.sleep(2000);

			if (bstatus == true)
			{
				Log4J.logp.info("Coder can not open case which CDI has in '" + cdiStatus + "' and coder has in '" + coderStatus + "'");
				Assert.assertTrue(true, "Coder can not open case which CDI has in '" + cdiStatus + "' and coder has in '" + coderStatus + "'");
			}
			else
			{
				Log4J.logp.error("Coder can able to open the case which CDI has in '" + cdiStatus + "' and coder has in '" + coderStatus + "'");
				Assert.assertTrue(false, "Coder can able to open the case which CDI has in '" + cdiStatus + "' and coder has in '" + coderStatus + "'");
			}
			Thread.sleep(2000);
			Log4J.logp.info("------------------- In checkLockedCase- completed -------------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("------------------- checkLockedCase is failed -------------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used so that Supervisor can able to search with all status for CDI user and check that case does not display in Reviewer queue.
	 * 
	 * @author mparikh
	 * @since 01/12/2014
	 */
	public static boolean checkCaseIsNotDisplayed(String status, String caseId)
	{

		boolean bstatus;
		int i;

		try
		{
			Log4J.logp.info("------------------- In checkCaseIsNotDisplayed for CDI status='" + status + "' started -------------------");
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			manualAllocation_webe = ManualAllocation_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("mp002");
			Thread.sleep(2000);
			if (status.equals("In Queue"))
			{
				SearchCriteria_Lib.search_Case(caseId);
				Thread.sleep(2000);

			}

			else if (status.equals("In Progress"))
			{
				Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

				driver.navigate().back();
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			}
			else if (status.equals("On Hold"))
			{
				Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
				groupingpnl_webe.btn_Later.click();
				Thread.sleep(2000);
			}
			else if (status.equals("Pending"))
			{
				Common_Lib.putCaseInPendingStatus(caseId);
				Thread.sleep(2000);
			}
			else if (status.equals("Completed"))
			{
				WorkingScreen_Lib.submit_case(caseId);
				Thread.sleep(2000);
			}

			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("mp001");
			Thread.sleep(2000);
			landingp_webe.lnk_Review.click();

			Thread.sleep(2000);
			i = 72;
			SearchCriteria_Lib.search_Case("SC00" + i);
			bstatus = ManualAllocation_Lib.checkNoDataAvailable(i);

			if (bstatus == true)
			{
				Log4J.logp.info("Reviewer does not have case in his landing page which is in CDI '" + status + "'");
				Assert.assertTrue(true, "Reviewer does not have case in his landing page which is in CDI '" + status + "'");
			}
			else
			{
				Log4J.logp.error("Reviewer does have case in his landing page which is in CDI '" + status + "'");
				Assert.assertTrue(false, "Reviewer does have case in his landing page which is in CDI '" + status + "'");
			}
			Thread.sleep(2000);
			Log4J.logp.info("------------------- In checkCaseIsNotDisplayed- completed -------------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("------------------- checkCaseIsNotDisplayed is failed -------------------");
			e.printStackTrace();
			return false;
		}
	}

	/***
	 * 
	 * This method is used so that User can able to search with all status for Coder user
	 * 
	 * @author nchourasiya
	 * @since 1/11/2014
	 * 
	 *        Status : case status(on hold ,In Progress, Pending, Billed) Case Id : case id in which operation is to performed Search Id : Case id which we want
	 *        to search Role : Role from which role(Coder, Supervisor,CDI, Reviewer) we want to check the condition login_ForRole : Login with role from which
	 *        me want to check condition. roleActionToBePreformed = User role in which action is to performed
	 * */

	public static boolean userCanSearchCaseForCoder(String roleActionToBePreformed, String status, String caseId, String searchId, String role, String login_ForRole)
	{
		boolean bstatus = false;
		boolean bCaseStatus;
		String text;
		try
		{
			Log4J.logp.info("--------------- In userCanSearchCaseForCoder for status='" + status + "' started -----------------");
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			manualAllocation_webe = ManualAllocation_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);

			if (roleActionToBePreformed.equals("Coder"))
			{
				landingp_webe.lnk_Coding.click();
				Log4J.logp.info("Checking for :" + roleActionToBePreformed);
			}

			Thread.sleep(2000);

			if (status.equals("In Progress"))
			{
				Thread.sleep(1000);
				Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

				driver.navigate().back();
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			}
			else if (status.equals("On Hold"))
			{

				Thread.sleep(1000);
				Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

				groupingpnl_webe.btn_Later.click();
				Thread.sleep(2000);
			}
			else if (status.equals("Pending"))
			{
				Thread.sleep(1000);
				Common_Lib.putCaseInPendingStatus(caseId);
				Thread.sleep(2000);

			}
			else if (status.equals("Completed"))
			{

				Thread.sleep(1000);
				WorkingScreen_Lib.submit_case(caseId);
				Thread.sleep(2000);
			}

			else if (status.equals("Billed"))
			{

				Thread.sleep(1000);
				WorkingScreen_Lib.submit_case(caseId);
				Thread.sleep(2000);
			}

			if (role.equals("Supervisor"))
			{
				if (landingp_webe.lnk_All.isDisplayed() == true)
				{
					landingp_webe.lnk_All.click();
					Log4J.logp.info("In Supervisor Role");
					bstatus = SearchCriteria_Lib.search_Case(searchId);
					searchCriteria_webe.lnk_SearchCreteria.click();
					Common_Lib.scroll_without_element();
					Thread.sleep(2000);
				}
				else
				{
					Login_Lib.logOut_App();
					Thread.sleep(2000);
					Login_Lib.login(login_ForRole);
					Thread.sleep(2000);
					landingp_webe.lnk_All.click();
					Log4J.logp.info("In Supervisor Role with Login Id :" + login_ForRole);
					bstatus = SearchCriteria_Lib.search_Case(searchId);
					searchCriteria_webe.lnk_SearchCreteria.click();
					Common_Lib.scroll_without_element();
					Thread.sleep(2000);
				}
			}
			else if (role.equals("CDI"))
			{
				if (Common_Lib.checkElementPresent(landingp_webe.lnk_CDI) == true)
				{
					landingp_webe.lnk_CDI.click();
					Log4J.logp.info("In CDI Role");
					bCaseStatus = Common_Lib.openCase(searchId);
					//Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
					Thread.sleep(2000);
					if (status.equals("In Progress") || status.equals("On Hold") || status.equals("Pending") || status.equals("Completed"))
					{
						if (bCaseStatus == false)
						{
							bstatus = true;
						}
						else
						{
							bstatus = false;
						}
					}
					else if (status.equals("Billed"))
					{
						if (bCaseStatus == true)
						{
							bstatus = true;
						}
						else
						{
							bstatus = false;
						}
					}
				}
				else
				{
					Thread.sleep(1000);
					Login_Lib.logOut_App();
					Thread.sleep(2000);
					Login_Lib.login(login_ForRole);
					Thread.sleep(2000);
					landingp_webe.lnk_CDI.click();
					Log4J.logp.info("In CDI Role with Login Id : " + login_ForRole);
					bCaseStatus = Common_Lib.openCase(searchId);
					//Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
					Thread.sleep(2000);
					if (status.equals("In Progress") || status.equals("On Hold") || status.equals("Pending") || status.equals("Completed"))
					{
						if (bCaseStatus == false)
						{
							bstatus = true;
						}
						else
						{
							bstatus = false;
						}
					}
					else if (status.equals("Billed"))
					{
						if (bCaseStatus == true)
						{
							bstatus = true;
						}
						else
						{
							bstatus = false;
						}
					}
				}
			}

			else if (role.equals("Reviewer"))
			{
				if (Common_Lib.checkElementPresent(landingp_webe.lnk_Review) == true)
				{
					Thread.sleep(1000);
					landingp_webe.lnk_Review.click();
					Log4J.logp.info("In Reviwer Role");
					bCaseStatus = Common_Lib.openCase(searchId);
					//Common_Lib.waitForObject(medicalrecordpnl_webe.img_FontSetting, "clickable", 20);
					Thread.sleep(2000);
					if (status.equals("In Progress") || status.equals("On Hold") || status.equals("Pending") || status.equals("Billed"))
					{
						text = "No data available in table";
						if (bCaseStatus == false)
						{

							if (landingp_webe.tbl_FirstRowSearch.isDisplayed() == true && landingp_webe.tbl_FirstRowSearch.getText().equalsIgnoreCase(text))
							{

								Log4J.logp.info(landingp_webe.tbl_FirstRowSearch.getText() + " - Text Found on searching - Test Pass ");
								bstatus = true;
							}
							else
							{

								Log4J.logp.info("Case open sucessfully - Test Fail");
								bstatus = false;
							}
						}
						else
						{
							bstatus = false;
						}
					}
					else if (status.equals("Completed"))
					{
						if (bCaseStatus == true)
						{
							bstatus = true;
						}
						else
						{
							Log4J.logp.info("Case not open sucessfully - Test Fail");
							bstatus = false;

						}
					}
				}
				else
				{
					Login_Lib.logOut_App();
					Thread.sleep(2000);
					Login_Lib.login(login_ForRole);
					Thread.sleep(2000);
					landingp_webe.lnk_Review.click();
					Log4J.logp.info("In Reviewer Role with Login Id : " + login_ForRole);
					bCaseStatus = Common_Lib.openCase(searchId);
					//Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
					Thread.sleep(2000);
					if (status.equals("In Progress") || status.equals("On Hold") || status.equals("Pending") || status.equals("Billed"))
					{
						text = "No data available in table";
						if (bCaseStatus == false)
						{

							if (landingp_webe.tbl_FirstRowSearch.isDisplayed() == true && landingp_webe.tbl_FirstRowSearch.getText().equalsIgnoreCase(text))
							{

								Log4J.logp.info(landingp_webe.tbl_FirstRowSearch.getText() + " - Text Found on searching - Test Pass ");
								bstatus = true;
							}
							else
							{

								Log4J.logp.info("Case open sucessfully - Test Fail");
								bstatus = false;
							}
						}
						else
						{
							bstatus = false;
						}
					}
					else if (status.equals("Completed"))
					{
						if (bCaseStatus == true)
						{
							bstatus = true;
						}
						else
						{
							bstatus = false;
						}
					}

				}

			}

			Thread.sleep(3000);

			if (bstatus == true)
			{
				Log4J.logp.info("'" + role + "'+ can able to search with '" + status + "' + Coder");
				Assert.assertTrue(true, "User can able to search with '" + status + "' + Coder");
			}
			else
			{
				Log4J.logp.error("'" + role + "'+ can not able to search with '" + status + "' + Coder");
				Assert.assertTrue(false, "User can not able to search with '" + status + "' + Coder");
			}

			Log4J.logp.info("--------------- In userCanSearchCaseForCoder- completed ----------------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in : userCanSearchCaseForCoder is failed.");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 
	 * @author nchourasiya
	 * @since 2/12/2014
	 * 
	 * */

	public static boolean cdiSearchCoderCaseForCdiCompleteCase(String roleActionToBePreformed, String status, String caseId, String searchId, String role, String login_ForRole)
	{
		boolean bstatus = false;
		boolean bCaseStatus;
		String text;
		try
		{
			Log4J.logp.info("------------ In cdiSearchCoderCaseForCdiCompleteCase for status='" + status + "' started -----------");
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			manualAllocation_webe = ManualAllocation_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);

			if (roleActionToBePreformed.equals("Coder"))
			{
				landingp_webe.lnk_Coding.click();
				Log4J.logp.info("Checking for :" + roleActionToBePreformed);
			}

			Thread.sleep(2000);

			if (status.equals("In Progress"))
			{
				Thread.sleep(1000);
				Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

				driver.navigate().back();
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			}
			else if (status.equals("On Hold"))
			{

				Thread.sleep(1000);
				Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

				groupingpnl_webe.btn_Later.click();
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			}
			else if (status.equals("Pending"))
			{
				Thread.sleep(1000);
				Common_Lib.putCaseInPendingStatus(caseId);
				Thread.sleep(2000);

			}
			else if (status.equals("Completed"))
			{

				Thread.sleep(1000);
				WorkingScreen_Lib.submit_case(caseId);
				Thread.sleep(2000);
			}

			else if (status.equals("Billed"))
			{

				Thread.sleep(1000);
				WorkingScreen_Lib.submit_case(caseId);
				Thread.sleep(2000);
			}
			if (role.equals("CDI"))
			{
				if (Common_Lib.checkElementPresent(landingp_webe.lnk_CDI) == true)
				{
					Thread.sleep(1000);
					landingp_webe.lnk_CDI.click();
					Log4J.logp.info("In CDI Role");
					bCaseStatus = Common_Lib.openCase(searchId);
					//Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
					Thread.sleep(2000);
					if (status.equals("In Progress") || status.equals("On Hold") || status.equals("Pending") || status.equals("Billed") || status.equals("Completed"))
					{
						text = "No data available in table";
						if (bCaseStatus == false)
						{

							if (landingp_webe.tbl_FirstRowSearch.isDisplayed() == true && landingp_webe.tbl_FirstRowSearch.getText().equalsIgnoreCase(text))
							{

								Log4J.logp.info(landingp_webe.tbl_FirstRowSearch.getText() + " -Text Found on searching with Search Id +'" + searchId + "'+ for +'" + status + "'+ of Coder - Test Pass ");
								bstatus = true;
							}
							else
							{

								Log4J.logp.info("Case open sucessfully - Test Fail");
								bstatus = false;
							}
						}
						else
						{
							bstatus = false;
						}
					}

				}
				else
				{
					Login_Lib.logOut_App();
					Thread.sleep(2000);
					Login_Lib.login(login_ForRole);
					Thread.sleep(2000);
					landingp_webe.lnk_CDI.click();
					Log4J.logp.info("In CDI Role with Login Id : " + login_ForRole);
					bCaseStatus = Common_Lib.openCase(searchId);
					//Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
					Thread.sleep(2000);
					if (status.equals("In Progress") || status.equals("On Hold") || status.equals("Pending") || status.equals("Billed") || status.equals("Completed"))
					{
						text = "No data available in table";
						if (bCaseStatus == false)
						{

							if (landingp_webe.tbl_FirstRowSearch.isDisplayed() == true && landingp_webe.tbl_FirstRowSearch.getText().equalsIgnoreCase(text))
							{

								Log4J.logp.info(landingp_webe.tbl_FirstRowSearch.getText() + " -Text Found on searching with Search Id +'" + searchId + "'+ for +'" + status + "'+ of Coder - Test Pass ");
								bstatus = true;
							}
							else
							{

								Log4J.logp.info("Case open sucessfully - Test Fail");
								bstatus = false;
							}
						}
						else
						{
							bstatus = false;
						}
					}

				}

			}
			Thread.sleep(2000);

			if (bstatus == true)
			{
				Log4J.logp.info("'" + role + "'+ is not able to view  '" + status + "' Case of Coder");
				Assert.assertTrue(true, "CDI is not able to view  '" + status + "'  Case of Coder");
			}
			else
			{
				Log4J.logp.error("'" + role + "'+ is able to view'" + status + "'  Case of Coder");
				Assert.assertTrue(false, "CDI is able to view '" + status + "'Case of Coder");
			}

			Log4J.logp.info("----------------In cdiSearchCoderCaseForCdiCompleteCase- completed -------------------");
			return true;

		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in : cdiSearchCoderCaseForCdiCompleteCase is failed.");
			return false;
		}
	}

	/**
	 * This method is used so that Supervisor can able to search with all(In Progress, On Hold, Pending, Completed) status for Reviewer user.
	 * 
	 * @author mparikh
	 * @since 27/11/2014
	 **/
	public static boolean supervisorCanSearchFor_Reviewer(String status, String caseId, String searchId)
	{

		boolean bstatus;

		try
		{
			Log4J.logp.info("------------------- In supervisorCanSearchFor_Reviewer for Reviewer status='" + status + "' started -------------------");
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			manualAllocation_webe = ManualAllocation_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("mp003");
			Thread.sleep(2000);

			if (status.equals("In Progress"))
			{
				Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

				driver.navigate().back();
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			}
			else if (status.equals("On Hold"))
			{
				Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

				groupingpnl_webe.btn_Later.click();
				Thread.sleep(2000);
			}
			else if (status.equals("Pending"))
			{
				Common_Lib.putCaseInPendingStatus(caseId);
				Thread.sleep(2000);
			}
			else if (status.equals("Billed"))
			{
				WorkingScreen_Lib.submit_case(caseId);
				Thread.sleep(2000);
			}

			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("mp001");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();

			//Thread.sleep(3000); 
			//This change made on 17/12/2014
			Thread.sleep(2000);

			bstatus = SearchCriteria_Lib.search_Case(searchId);

			if (bstatus == true)
			{
				Log4J.logp.info("Supervisor can able to search case which  Reviewer has in '" + status + "'");
				Assert.assertTrue(true, "Supervisor can able to search case which  Reviewer has in '" + status + "'");
			}
			else
			{
				Log4J.logp.error("Supervisor can not able to search case which  Reviewer has in '" + status + "'");
				Assert.assertTrue(false, "Supervisor can not able to search case which  Reviewer has in '" + status + "'");
			}
			Thread.sleep(2000);
			Log4J.logp.info("------------------- In supervisorCanSearchFor_Reviewer- completed -------------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("------------------- supervisorCanSearchFor_Reviewer is failed -------------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Coder To Check when Reviewer puts cases in all status
	 * 
	 * @author nchourasiya
	 * @since 4/12/2014
	 * */

	public static boolean SearchCoder_ForReviewerInAllStage(String roleActionToBePreformed, String status, String caseId, String searchId, String role, String login_ForRole)
	{
		boolean bstatus = false;

		try
		{
			Log4J.logp.info("----------- In SearchCoder_ForReviewerInAllStage for Reviewer status='" + status + "' started -------------");

			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			manualAllocation_webe = ManualAllocation_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			Thread.sleep(2000);

			if (roleActionToBePreformed.equals("Reviewer"))
			{
				Thread.sleep(2000);
				landingp_webe.lnk_Review.click();
				Log4J.logp.info("Checking for :" + roleActionToBePreformed);
			}

			Thread.sleep(2000);

			if (status.equals("In Progress"))
			{
				Thread.sleep(1000);
				bstatus = Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

				driver.navigate().back();
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			}
			else if (status.equals("On Hold"))
			{

				Thread.sleep(1000);
				bstatus = Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

				groupingpnl_webe.btn_Later.click();
				Thread.sleep(2000);
			}
			else if (status.equals("Pending"))
			{
				Thread.sleep(1000);
				bstatus = Common_Lib.putCaseInPendingStatus(caseId);
				Thread.sleep(2000);

			}

			else if (status.equals("Billed"))
			{

				Thread.sleep(1000);
				bstatus = WorkingScreen_Lib.submit_case(caseId);
				Thread.sleep(2000);
			}

			if (role.equals("Coder")) // Role Coder Checking cases For Reviewer
			{
				if (Common_Lib.checkElementPresent(landingp_webe.lnk_Coding) == true)
				{
					Thread.sleep(1000);
					landingp_webe.lnk_Coding.click();
					Log4J.logp.info("In Coder Role");
					Common_Lib.openCase(searchId);
					//Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
					Thread.sleep(2000);

					if (status.equals("In Progress") || status.equals("On Hold") || status.equals("Pending") || status.equals("Billed"))
					{
						bstatus = Common_Lib.checkElementPresent(landingp_webe.td_lockedcase);
						Thread.sleep(2000);
						if (bstatus == true)
						{
							Log4J.logp.info("Case is Locked with '" + status + "' Status. ");
						}
						else
						{
							Log4J.logp.info("Case is not Locked and open sucessfully with '" + status + "' Status. ");
							bstatus = false;
						}

					}

				}
				else
				{
					Login_Lib.logOut_App();
					Thread.sleep(2000);
					Login_Lib.login(login_ForRole);
					Thread.sleep(2000);
					landingp_webe.lnk_Coding.click();
					Log4J.logp.info("In Coder Role with Login Id : " + login_ForRole);
					Common_Lib.openCase(searchId);
					//Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
					Thread.sleep(2000);
					if (status.equals("In Progress") || status.equals("On Hold") || status.equals("Pending") || status.equals("Billed"))
					{
						bstatus = Common_Lib.checkElementPresent(landingp_webe.td_lockedcase);
						Thread.sleep(2000);
						if (bstatus == true)
						{
							Log4J.logp.info("Case is Locked with '" + status + "' Status. ");
						}
						else
						{
							Log4J.logp.info("Case is not Locked and open sucessfully with '" + status + "' Status. ");
							bstatus = false;
						}

					}

				}

			}
			Thread.sleep(2000);

			if (bstatus == true)
			{
				Log4J.logp.info("'" + role + "' can able to search with '" + status + "' Case of  '" + roleActionToBePreformed + "'");
				Assert.assertTrue(true, "User can able to search with '" + status + "'  Case of  '" + roleActionToBePreformed + "'");
			}
			else
			{
				Log4J.logp.error("'" + role + "' can not able to search with '" + status + "' Case of  '" + roleActionToBePreformed + "'");
				Assert.assertTrue(false, "User can not able to search with '" + status + "' Case of  '" + roleActionToBePreformed + "'");
			}

			Log4J.logp.info("------------ In SearchCoder_ForReviewerInAllStage- completed ------------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.info("Problem found in : SearchCoder_ForReviewerInAllStage");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is used to check CDI's locked state when reviewer has case with In Queue, In Progress, On Hold and Pending status.
	 * 
	 * @author mparikh
	 * @since 04/12/2014
	 */
	public static boolean reviewerToCDI_checkLockedCase(String reviewerStatus, String caseId, String searchId)
	{

		boolean bstatus;

		try
		{
			Log4J.logp.info("------------------- In reviewerToCDI_checkLockedCase for Reviewer status='" + reviewerStatus + "' started -------------------");
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			manualAllocation_webe = ManualAllocation_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("mp003");
			Thread.sleep(2000);
			if (reviewerStatus.equals("In Queue"))
			{
				SearchCriteria_Lib.search_Case(caseId);
				Thread.sleep(2000);

			}

			else if (reviewerStatus.equals("In Progress"))
			{
				Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

				driver.navigate().back();
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			}
			else if (reviewerStatus.equals("On Hold"))
			{
				Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

				groupingpnl_webe.btn_Later.click();
				Thread.sleep(2000);
			}
			else if (reviewerStatus.equals("Pending"))
			{
				Common_Lib.putCaseInPendingStatus(caseId);
				Thread.sleep(2000);
			}

			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("mp002");
			Thread.sleep(2000);

			SearchCriteria_Lib.search_Case(searchId);
			Thread.sleep(2000);
			bstatus = Common_Lib.checkElementPresent(landingp_webe.td_lockedcase);
			Thread.sleep(2000);

			if (bstatus == true)
			{
				Log4J.logp.info("CDI can not able to open the case when Reviewer is in '" + reviewerStatus + "'");
				Assert.assertTrue(true, "CDI can not able to open the case when Reviewer is in '" + reviewerStatus + "'");
			}
			else
			{
				Log4J.logp.error("CDI can able to open the case when Reviewer is in '" + reviewerStatus + "'");
				Assert.assertTrue(false, "CDI can  able to open the case when Reviewer is in '" + reviewerStatus + "'");
			}
			Thread.sleep(2000);
			Log4J.logp.info("------------------- In reviewerToCDI_checkLockedCase- completed -------------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("------------------- reviewerToCDI_checkLockedCase is failed -------------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used to check that cdi's completed case should not be displayed when Reviewer has that case in Unassigned, In Queue, In Progress, On Hold
	 * and Pending and Billed status.
	 * 
	 * @author mparikh
	 * @since 04/12/2014
	 */
	public static boolean cdiCompletedToReviewer_CaseNotDisplayed(String reviewerStatus, String caseId)
	{

		boolean bstatus;
		int i;

		try
		{
			Log4J.logp.info("------------------- In cdiCompletedToReviewer_CaseNotDisplayed for Reviewer status='" + reviewerStatus + "' started -------------------");
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			manualAllocation_webe = ManualAllocation_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("mp003");
			Thread.sleep(2000);

			if (reviewerStatus.equals("Unassigned"))
			{
				SearchCriteria_Lib.search_Case(caseId);
				Thread.sleep(2000);

			}

			else if (reviewerStatus.equals("In Queue"))
			{
				SearchCriteria_Lib.search_Case(caseId);
				Thread.sleep(2000);

			}

			else if (reviewerStatus.equals("In Progress"))
			{
				Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

				driver.navigate().back();
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			}
			else if (reviewerStatus.equals("On Hold"))
			{
				Common_Lib.openCase(caseId);
				Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

				groupingpnl_webe.btn_Later.click();
				Thread.sleep(2000);
			}
			else if (reviewerStatus.equals("Pending"))
			{
				Common_Lib.putCaseInPendingStatus(caseId);
				Thread.sleep(2000);
			}

			else if (reviewerStatus.equals("Billed"))
			{
				WorkingScreen_Lib.submit_case(caseId);
				Thread.sleep(2000);
			}

			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("mp001");
			Thread.sleep(2000);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			i = 73;
			SearchCriteria_Lib.search_Case("SC00" + i);
			bstatus = ManualAllocation_Lib.checkNoDataAvailable(i);

			if (bstatus == true)
			{
				Log4J.logp.info("CDI does not have case in his landing page which is in Reviewer '" + reviewerStatus + "'");
				Assert.assertTrue(true, "CDI does not have case in his landing page which is in Reviewer '" + reviewerStatus + "'");
			}
			else
			{
				Log4J.logp.error("CDI does have case in his landing page which is in Reviewer '" + reviewerStatus + "'");
				Assert.assertTrue(false, "CDI does have case in his landing page which is in Reviewer '" + reviewerStatus + "'");
			}
			Thread.sleep(2000);
			Log4J.logp.info("------------------- In cdiCompletedToReviewer_CaseNotDisplayed- completed -------------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("------------------- cdiCompletedToReviewer_CaseNotDisplayed is failed -------------------");
			e.printStackTrace();
			return false;
		}
	}

}
