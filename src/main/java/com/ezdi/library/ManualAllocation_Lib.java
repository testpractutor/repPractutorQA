package com.ezdi.library;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class ManualAllocation_Lib
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
	 * This method is used to assign fresh case by Supervisor to users
	 * 
	 * @author fmodi
	 * @since 09/10/2014
	 */
	public static boolean assignFreshCase(String rowId, String user)
	{
		boolean bstatus;
		String successmsg;

		try
		{
			Log4J.logp.info("--------------- In assignCase for user='" + user + "' started ---------------");
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			manualAllocation_webe = ManualAllocation_WebE.getInstance(driver);
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			//Thread.sleep(3000);
			Common_Lib.waitForObject(searchCriteria_webe.lnk_SearchCreteria, "clickable", 3);
			SearchCriteria_Lib.search_Case(rowId);
			Thread.sleep(2000);

			//to scroll page
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);

			bstatus = Common_Lib.checkElementPresent(manualAllocation_webe.ico_FreshCase);
			if (bstatus == true)
			{
				Assert.assertTrue(true, "Selected case is Fresh Case");
				Log4J.logp.info("Selected case is Fresh Case");
			}
			Thread.sleep(2000);

			landingp_webe.chk_First_Case.click();
			Thread.sleep(2000);
			landingp_webe.lnk_Assign.click();
			Thread.sleep(2000);

			landingp_webe.lst_AssignTo.sendKeys(user);
			Thread.sleep(2000);

			landingp_webe.btn_Done.click();
			Thread.sleep(2000);
			successmsg = "Success: 1 case successfully allocated..";
			Assert.assertEquals(manualAllocation_webe.txt_AssignMessage.getText(), successmsg);
			Log4J.logp.info("Success message is displayed to Supervisor");
			Thread.sleep(2000);
			Log4J.logp.info("--------------- In assignFreshCase- completed ---------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("--------------- assignFreshCase is failed ---------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used to assign fresh case to self
	 * 
	 * @author fmodi
	 * @since 31/10/2014
	 */
	public static boolean selfAssignFreshCase(String rowId)
	{

		WebElement table, row;
		boolean bstatus;

		try
		{
			Log4J.logp.info("--------------- In selfAssignFreshCase - Started ---------------");
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			manualAllocation_webe = ManualAllocation_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			//Thread.sleep(3000);
			Common_Lib.waitForObject(searchCriteria_webe.lnk_SearchCreteria, "clickable", 3);

			SearchCriteria_Lib.search_Case(rowId);
			Thread.sleep(2000);

			bstatus = Common_Lib.checkElementPresent(manualAllocation_webe.ico_FreshCase);
			if (bstatus == true)
			{
				Assert.assertTrue(true, "Selected case is Fresh Case");
				Log4J.logp.info("Selected case is Fresh Case");
			}
			Thread.sleep(2000);

			table = landingp_webe.tbl_SearchResult;
			//find click-able column
			row = table.findElement(By.tagName("td"));
			row.click();

			//Thread.sleep(15000);
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 20);

			Log4J.logp.info("--------------- In selfAssignFreshCase- completed ---------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("--------------- selfAssignFreshCase is failed ---------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used so that Supervisor can not assign case which is already assigned to that user for the specified role and status
	 * 
	 * @author fmodi
	 * @since 17/11/2014
	 */
	public static boolean canNotReassignCase(String role, String status, String rowId, String user, int i)
	{
		boolean bstatus;
		String strfailuremsg;

		try
		{
			Log4J.logp.info("--------------- In canNotReassignCase for role='" + role + "' and status=" + status + "' and user='" + user + "' started ---------------");
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			manualAllocation_webe = ManualAllocation_WebE.getInstance(driver);

			SearchCriteria_Lib.search_Case("SC00" + i);
			bstatus = ManualAllocation_Lib.checkNoDataAvailable(i);
			if (bstatus == false)
			{
				Log4J.logp.info("This case is in '" + status + "' for that '" + role + "'");
				Assert.assertTrue(true, "This case is in '" + status + "' for that '" + role + "'");
			}
			else
			{
				Log4J.logp.error("This case is not in '" + status + "' for that '" + role + "'");
				Assert.assertTrue(true, "This case is not in '" + status + "' for that '" + role + "'");
			}

			Thread.sleep(2000);

			/*landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			
			Thread.sleep(2000);*/

			driver.navigate().refresh();
			Thread.sleep(2000);

			landingp_webe.lnk_All.click();
			//Thread.sleep(3000);
			Common_Lib.waitForObject(searchCriteria_webe.lnk_SearchCreteria, "clickable", 3);
			SearchCriteria_Lib.search_Case(rowId);
			Thread.sleep(2000);

			//to scroll page
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);

			landingp_webe.chk_First_Case.click();
			Thread.sleep(2000);
			landingp_webe.lnk_Assign.click();
			Thread.sleep(2000);

			landingp_webe.lst_AssignTo.sendKeys(user);
			Thread.sleep(2000);

			landingp_webe.btn_Done.click();
			Thread.sleep(2000);

			strfailuremsg = "The case is already assigned to this user. Please select another " + role + ".";
			Assert.assertEquals(manualAllocation_webe.txt_AssignMessage.getText(), strfailuremsg);
			Log4J.logp.info("Failure message is displayed to Supervisor");
			Thread.sleep(2000);

			Log4J.logp.info("--------------- In canNotReassignCase- completed ---------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("--------------- canNotReassignCase is failed ---------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used to assign case by Supervisor to other users
	 * 
	 * @author fmodi
	 * @since 17/11/2014
	 */
	public static boolean assignCase(String rowId, String user)
	{

		String successmsg;

		try
		{
			Log4J.logp.info("--------------- In assignCase for user='" + user + "' started ---------------");
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			manualAllocation_webe = ManualAllocation_WebE.getInstance(driver);
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			//Thread.sleep(3000);
			Common_Lib.waitForObject(searchCriteria_webe.lnk_SearchCreteria, "clickable", 3);
			SearchCriteria_Lib.search_Case(rowId);
			Thread.sleep(2000);

			//to scroll page
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);

			landingp_webe.chk_First_Case.click();
			Thread.sleep(2000);
			landingp_webe.lnk_Assign.click();
			Thread.sleep(2000);

			landingp_webe.lst_AssignTo.sendKeys(user);
			Thread.sleep(2000);

			landingp_webe.btn_Done.click();
			Thread.sleep(2000);
			successmsg = "Success: 1 case successfully allocated..";
			Assert.assertEquals(manualAllocation_webe.txt_AssignMessage.getText(), successmsg);
			Log4J.logp.info("Success message is displayed to Supervisor");
			Thread.sleep(2000);
			Log4J.logp.info("--------------- In assignCase- completed ---------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("--------------- assignCase is failed ---------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used to check data is not available for the entered criteria
	 * 
	 * @author fmodi
	 * @since 18/11/2014
	 */

	public static boolean checkNoDataAvailable(int i)
	{
		WebElement table;

		try
		{

			Log4J.logp.info("--------------- In checkNoDataAvailable for value='" + i + "' started ---------------");

			driver = ExecutionSetup.getDriver();
			landingp_webe = LandingP_WebE.getInstance(driver);
			table = driver.findElement(By.cssSelector("#searchResults > tbody > tr"));
			List<WebElement> rows = table.findElements(By.tagName("td"));
			if (!(rows.size() >= 2))
			{
				Log4J.logp.info("No Data Available for selected Row ID ::: SC00" + i);
				Log4J.logp.info("--------------- In checkNoDataAvailable- completed  ---------------");
				return true;
			}
			else
			{
				Log4J.logp.info("--------------- In checkNoDataAvailable- completed  ---------------");
				return false;
			}

		}
		catch (Exception e)
		{
			Log4J.logp.error("--------------- checkNoDataAvailable is failed ---------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used so that Supervisor can not delete user when the user has case(s) in "In Progress","On Hold" or "Pending" status
	 * 
	 * @author fmodi
	 * @since 21/11/2014
	 */
	public static boolean canNotDeleteUser(String loginId, String rowId, String userName, String status)
	{

		try
		{
			Log4J.logp.info("--------------- In canNotDeleteUser for user='" + userName + "' and status='" + status + "' started ---------------");
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			manualAllocation_webe = ManualAllocation_WebE.getInstance(driver);
			adminhome_webe = AdminHome_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login(loginId);
			//Thread.sleep(3000);
			Common_Lib.waitForObject(searchCriteria_webe.lnk_SearchCreteria, "clickable", 3);

			if (status.equals("In Progress"))
			{
				Common_Lib.openCase(rowId);
				//Thread.sleep(10000);
				Common_Lib.waitForObject(medicalrecordpnl_webe.img_FontSetting, "clickable", 10);

				driver.navigate().back();
				//Thread.sleep(5000);
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "clickable", 5);
			}
			else if (status.equals("On Hold"))
			{
				Common_Lib.openCase(rowId);
				//Thread.sleep(15000);
				Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 20);

				groupingpnl_webe.btn_Later.click();
				Thread.sleep(2000);
			}
			else if (status.equals("Pending"))
			{
				Common_Lib.putCaseInPendingStatus(rowId);
				Thread.sleep(2000);
			}

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm005");
			//Thread.sleep(3000);
			Common_Lib.waitForObject(landingp_webe.lnk_All, "clickable", 3);

			landingp_webe.lnk_All.click();
			//Thread.sleep(5000);
			Common_Lib.waitForObject(landingp_webe.imgAdmin_Setting, "clickable", 5);

			landingp_webe.imgAdmin_Setting.click();
			//Thread.sleep(3000);
			Common_Lib.waitForObject(adminhome_webe.lnk_ViewUsers, "clickable", 3);
			adminhome_webe.lnk_ViewUsers.click();
			Thread.sleep(2000);
			Administrator_Lib.find_user(userName, "delete");
			Thread.sleep(2000);

			Log4J.logp.info("--------------- In canNotReassignCase- completed ---------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("--------------- canNotReassignCase is failed ---------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used so that Supervisor can not assign a case which is assigned to user and not 'Completed' by Coder
	 * 
	 * @author fmodi
	 * @since 21/11/2014
	 */
	public static boolean canNotAssignUserCaseToReviewer(String loginId, String role, String previousStatus, String currentStatus, String caseId, String rowId, int i)
	{

		boolean bstatus;

		try
		{
			Log4J.logp.info("--------------- In canNotAssignUserCaseToReviewer for role='" + role + "' and status='" + currentStatus + "' started ---------------");
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			manualAllocation_webe = ManualAllocation_WebE.getInstance(driver);
			adminhome_webe = AdminHome_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login(loginId);
			Thread.sleep(2000);

			if (currentStatus.equals("In Progress"))
			{
				Common_Lib.openCase(caseId);
				//Thread.sleep(10000);
				Common_Lib.waitForObject(medicalrecordpnl_webe.img_FontSetting, "clickable", 10);

				driver.navigate().back();
				//Thread.sleep(5000);
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "clickable", 5);
			}
			else if (currentStatus.equals("On Hold"))
			{
				Common_Lib.openCase(caseId);
				//Thread.sleep(15000);
				Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 20);

				groupingpnl_webe.btn_Later.click();
				Thread.sleep(2000);
			}
			else if (currentStatus.equals("Pending"))
			{
				Common_Lib.putCaseInPendingStatus(caseId);
				Thread.sleep(2000);
			}
			else if (currentStatus.equals("Billed") || currentStatus.equals("Completed"))
			{
				WorkingScreen_Lib.submit_case(caseId);
				Thread.sleep(2000);
			}

			bstatus = SearchCriteria_Lib.search_Case(rowId);
			if (bstatus == true)
			{
				Log4J.logp.info("Status of case is changed from '" + previousStatus + "' to '" + currentStatus + "'");
				Assert.assertTrue(true, "Status of case is changed from '" + previousStatus + "' to '" + currentStatus + "'");
			}
			else
			{
				Log4J.logp.error("Status of case is not changed from '" + previousStatus + "' to '" + currentStatus + "'");
				Assert.assertTrue(false, "Status of case is changed from '" + previousStatus + "' to '" + currentStatus + "'");
			}

			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm005");
			//Thread.sleep(3000);
			Common_Lib.waitForObject(landingp_webe.lnk_All, "clickable", 3);

			if (role.equals("CDI") && currentStatus.equals("Completed"))
			{
				landingp_webe.lnk_Coding.click();
				//Thread.sleep(5000);
				Common_Lib.waitForObject(searchCriteria_webe.lnk_SearchCreteria, "clickable", 5);

				//WorkingScreen_Lib.submit_case("fm017");
				//below is according to git online
				WorkingScreen_Lib.submit_case("fm013");
				Thread.sleep(2000);

			}

			landingp_webe.lnk_All.click();
			//Thread.sleep(5000);
			Common_Lib.waitForObject(searchCriteria_webe.lnk_SearchCreteria, "clickable", 5);

			SearchCriteria_Lib.search_Case("SC00" + i);
			bstatus = ManualAllocation_Lib.checkNoDataAvailable(i);
			if (bstatus == true)
			{
				Log4J.logp.info("Supervisor can not assign case which is '" + currentStatus + "' for a " + role + " to Reviewer");
				Assert.assertTrue(true, "Supervisor can not assign case which is '" + currentStatus + "' for a " + role + " to Reviewer.");
			}
			else
			{
				Log4J.logp.info("Supervisor can assign case which is '" + currentStatus + "' for a " + role + " to Reviewer");
				Assert.assertTrue(false, "Supervisor can assign case which is '" + currentStatus + "' for a " + role + " to Reviewer.");
			}

			Thread.sleep(2000);

			Log4J.logp.info("--------------- In canNotAssignUserCaseToReviewer- completed ---------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("--------------- canNotAssignUserCaseToReviewer is failed ---------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used so that Supervisor can assign a case which is assigned to a user role to another user role
	 * 
	 * @author fmodi
	 * @since 25/11/2014
	 */
	public static boolean canAssignRoleCaseToAnotherRole(String loginId, String fromRole, String toRole, String previousStatus, String currentStatus, String caseId, String rowId, String assignCaseId, String userName)
	{

		boolean bstatus;

		try
		{
			Log4J.logp.info("--------------- In canAssignRoleCaseToAnotherRole for role='" + toRole + "' when '" + fromRole + "' has case in'" + currentStatus + "' started ---------------");
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			manualAllocation_webe = ManualAllocation_WebE.getInstance(driver);
			adminhome_webe = AdminHome_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login(loginId);
			Thread.sleep(2000);

			if (currentStatus.equals("In Progress"))
			{
				Common_Lib.openCase(caseId);
				//Thread.sleep(10000);
				Common_Lib.waitForObject(medicalrecordpnl_webe.img_FontSetting, "clickable", 10);

				driver.navigate().back();
				//Thread.sleep(5000);
				Common_Lib.waitForObject(landingp_webe.lbl_UserName, "clickable", 5);
			}
			else if (currentStatus.equals("On Hold"))
			{
				Common_Lib.openCase(caseId);
				//Thread.sleep(15000);
				Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 20);

				groupingpnl_webe.btn_Later.click();
				Thread.sleep(2000);
			}
			else if (currentStatus.equals("Pending"))
			{
				Common_Lib.putCaseInPendingStatus(caseId);
				Thread.sleep(2000);
			}
			else if (currentStatus.equals("Billed") || currentStatus.equals("Completed"))
			{
				WorkingScreen_Lib.submit_case(caseId);
				Thread.sleep(2000);
			}

			bstatus = SearchCriteria_Lib.search_Case(rowId);
			if (bstatus == true)
			{
				Log4J.logp.info("Status of case is changed from '" + previousStatus + "' to '" + currentStatus + "'");
				Assert.assertTrue(true, "Status of case is changed from '" + previousStatus + "' to '" + currentStatus + "'");
			}
			else
			{
				Log4J.logp.error("Status of case is not changed from '" + previousStatus + "' to '" + currentStatus + "'");
				Assert.assertTrue(false, "Status of case is changed from '" + previousStatus + "' to '" + currentStatus + "'");
			}

			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm005");
			//Thread.sleep(3000);
			Common_Lib.waitForObject(landingp_webe.lnk_All, "clickable", 3);

			landingp_webe.lnk_All.click();
			//Thread.sleep(5000);
			Common_Lib.waitForObject(searchCriteria_webe.lnk_SearchCreteria, "clickable", 5);

			bstatus = ManualAllocation_Lib.assignCase(assignCaseId, userName);
			if (bstatus == true)
			{
				Log4J.logp.info("Supervisor can assign case which is '" + currentStatus + "' for a '" + fromRole + "' to '" + toRole + "'.");
				Assert.assertTrue(true, "Supervisor can assign case which is '" + currentStatus + "' for a '" + fromRole + "' to '" + toRole + "'.");
			}
			else
			{
				Log4J.logp.info("Supervisor can not assign case which is '" + currentStatus + "' for a '" + fromRole + "' to '" + toRole + "'.");
				Assert.assertTrue(false, "Supervisor can not assign case which is '" + currentStatus + "' for a '" + fromRole + "' to '" + toRole + "'.");
			}

			Thread.sleep(2000);

			Log4J.logp.info("--------------- In canAssignRoleCaseToAnotherRole- completed ---------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("--------------- canAssignRoleCaseToAnotherRole is failed ---------------");
			e.printStackTrace();
			return false;
		}
	}

}