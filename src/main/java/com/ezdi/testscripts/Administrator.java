package com.ezdi.testscripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ezdi.framework.common.JDBCMySql;
import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.library.Administrator_Lib;
import com.ezdi.library.Common_Lib;
import com.ezdi.library.Login_Lib;
import com.ezdi.webelements.AddGroup_WebE;
import com.ezdi.webelements.AddPhysician_WebE;
import com.ezdi.webelements.AddUser_WebE;
import com.ezdi.webelements.AdminHome_WebE;
import com.ezdi.webelements.Comman_WebE;
import com.ezdi.webelements.GroupinPnl_WebE;
import com.ezdi.webelements.LandingP_WebE;
import com.ezdi.webelements.Login_WebE;
import com.ezdi.webelements.SearchCriteria_WebE;

public class Administrator
{

	public static WebDriver				driver;
	public static AddUser_WebE			addUser_webe;
	public static Login_WebE			login_webe;
	public static AdminHome_WebE		adminHome_webe;
	public static LandingP_WebE			landingp_webe;
	public static WebDriverWait			wait;
	public static AddPhysician_WebE		addPhysician_webe;
	public static AddGroup_WebE			addGroup_webe;
	public static Comman_WebE			common_webe;
	public static SearchCriteria_WebE	searchcriteria_webe;
	public static GroupinPnl_WebE		groupingpnl_webe;

	@BeforeClass
	public static void AdministratorBeforeClass()
	{

		try
		{
			driver = ExecutionSetup.getDriver();

			addUser_webe = AddUser_WebE.getInstance(driver);
			adminHome_webe = AdminHome_WebE.getInstance(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			addPhysician_webe = AddPhysician_WebE.getInstance(driver);
			addGroup_webe = AddGroup_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			searchcriteria_webe = SearchCriteria_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);

			Login_Lib.login("nc003");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * This script to check for add user with single role
	 * 
	 * @author jayp
	 * @author nchourasiya - changes in database and script
	 * */
	@Test(description = "ezCAC_ MVP-51:Verify that Admin can add user with single roles", priority = 0)
	public static void addUserWithSingleRole()
	{
		boolean bstatus = false;
		JDBCMySql result;
		String firstName, lastName, fullName;
		
		try
		{

			Log4J.logp.info("********************* Started - addUserWithSingleRole ********************************");
			result = new JDBCMySql();

			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			adminHome_webe.lnk_AddNew_user.click();
			Thread.sleep(3000);

			wait = new WebDriverWait(driver, 20);

			addUser_webe.btn_AddUser.click();

			Thread.sleep(2000);
			Administrator_Lib.add_User("JP001");
			Thread.sleep(2000);

			driver.navigate().refresh();

			firstName = result.getValuebyColumnName("td_adduser", "JP001", "FirstName");

			lastName = result.getValuebyColumnName("td_adduser", "JP001", "LastName");
			fullName = firstName + " " + lastName;
			Thread.sleep(5000);

			bstatus = Administrator_Lib.find_user(fullName, "find");

			Log4J.logp.info("ezCAC_ MVP-51:Verify that Admin can add user with single roles- Test Pass");
			Log4J.logp.info("******************************Ending - addUserWithSingleRole *************************");
			Assert.assertTrue(bstatus, "Add User with single role test case passed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - addUserWithSingleRole");
			e.printStackTrace();
			Assert.assertTrue(false, "addUserWithSingleRole is failed");

		}
		finally
		{

			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}

		}

	}

	/**
	 * This script to will add new Physician
	 * 
	 * @author skhalasi
	 * @author nchourasiya - changes in database and Script
	 * */
	@Test(description = "ezCAC_MVP-68:Verify that Admin can add physician", priority = 1)
	public static void addPhysician()

	{
		String firstName = null;
		String lastName = null;
		String fullName = null;
		JDBCMySql result;
		try
		{
			Log4J.logp.info("************************************** Started - addPhysician *************************");
			result = new JDBCMySql();
			landingp_webe.imgAdmin_Setting.click();
			adminHome_webe.lnk_AddNew_Physician.click();
			Thread.sleep(3000);
			addPhysician_webe.btn_AddPhysician.click();
			Thread.sleep(2000);

			Administrator_Lib.add_Physician("1");
			Thread.sleep(1000);
			driver.navigate().refresh();
			Thread.sleep(2000);
			firstName = result.getValuebyColumnName("td_addphysician", "1", "FirstName");

			lastName = result.getValuebyColumnName("td_addphysician", "1", "LastName");
			fullName = firstName + " " + lastName;

			Thread.sleep(500);
			Administrator_Lib.find_physician(fullName, "find");
			Thread.sleep(1000);
			Log4J.logp.info("ezCAC_MVP-68:Verify that Admin can add physician- Test Pass");
			Log4J.logp.info("**************************************Ending - addPhysician ***************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found In - addPhysician");
			e.printStackTrace();
			Assert.assertTrue(false, "addPhysician is failed");
		}
		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}

	}

	/**
	 * this method is for editUser
	 * 
	 * @author skhalasi
	 * @author nchourasiya - changes in database and script
	 */
	@Test(description = "ezCAC_ MVP-54:Verify that Admin can edit user information", priority = 2)
	/** This script to will edit the user information  */
	public static void editUser()
	{
		boolean bstatus = false;
		JDBCMySql getData;
		try
		{
			//Thread.sleep(3000);
			Log4J.logp.info("****************************************Started - editUser *****************************");

			getData = new JDBCMySql();
			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			adminHome_webe.lnk_AddNew_user.click();
			Thread.sleep(2000);
			addUser_webe.btn_AddUser.click();
			Thread.sleep(2000);

			Administrator_Lib.add_User("sk008");
			Thread.sleep(500);
			driver.navigate().refresh();
			Thread.sleep(500);
			String userFname1 = getData.getValuebyColumnName("td_adduser", "sk008", "FirstName");
			String userLname1 = getData.getValuebyColumnName("td_adduser", "sk008", "LastName");
			String user = userFname1 + " " + userLname1;
			Thread.sleep(1000);
			Administrator_Lib.find_user(user, "open");
			Thread.sleep(1000);

			bstatus = Administrator_Lib.add_User("NC029");

			Log4J.logp.info("ezCAC_ MVP-54:Verify that Admin can edit user information- Test Pass");
			Log4J.logp.info("*****************************************Ending - editUser ******************************");
			Assert.assertTrue(bstatus, "editUser is passed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - editUser");
			e.printStackTrace();
			Assert.assertTrue(false, "editUser is failed");

		}
		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}
	}

	/**
	 * This method is for add group
	 * 
	 * @author skhalasi
	 * @author nchourasiya - Changes in script and database
	 */
	@Test(description = "ezCAC_MVP-72:Verify that Admin can create new group(s) for all Role", priority = 3)
	public static void addGroup()
	{
		boolean bstatus = false;
		String firstName1 = null;
		String firstName2 = null;
		String firstName3 = null;
		JDBCMySql result;

		try
		{

			result = new JDBCMySql();
			Log4J.logp.info("****************************** Started - addGroup  ***********************************");

			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(adminHome_webe.lnk_AddNew_Group));
			adminHome_webe.lnk_AddNew_Group.click();
			Thread.sleep(3000);

			addGroup_webe.btn_AddGroup.click();
			wait.until(ExpectedConditions.visibilityOf(addGroup_webe.txt_Name));
			Thread.sleep(3000);
			Administrator_Lib.add_Group("NC014");
			Thread.sleep(500);
			driver.navigate().refresh();
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOf(addGroup_webe.tbl_Group));
			Thread.sleep(5000);
			firstName1 = result.getValuebyColumnName("td_addgroup", "NC014", "Name");
			Log4J.logp.info(firstName1);
			Administrator_Lib.find_group(firstName1, "find");
			Thread.sleep(3000);
			addGroup_webe.btn_AddGroup.click();
			wait.until(ExpectedConditions.visibilityOf(addGroup_webe.txt_Name));
			Thread.sleep(5000);
			Administrator_Lib.add_Group("NC015");
			Thread.sleep(500);
			driver.navigate().refresh();
			Thread.sleep(500);
			Thread.sleep(5000);
			firstName2 = result.getValuebyColumnName("td_addgroup", "NC015", "Name");
			Log4J.logp.info(firstName2);
			Administrator_Lib.find_group(firstName2, "find");
			Thread.sleep(3000);
			addGroup_webe.btn_AddGroup.click();
			wait.until(ExpectedConditions.visibilityOf(addGroup_webe.txt_Name));
			Thread.sleep(5000);
			Administrator_Lib.add_Group("NC016");
			Thread.sleep(500);
			driver.navigate().refresh();
			Thread.sleep(500);
			Thread.sleep(5000);
			firstName3 = result.getValuebyColumnName("td_addgroup", "NC016", "Name");
			Log4J.logp.info(firstName3);
			bstatus = Administrator_Lib.find_group(firstName3, "find");

			Log4J.logp.info("ezCAC_MVP-72:Verify that Admin can create new group(s) for all Role- Test Pass");
			Log4J.logp.info("********************************** Ending - addGroup ***********************************");
			Assert.assertTrue(bstatus, "Group sucessfully added");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found In - addGroup");
			e.printStackTrace();
			Assert.assertTrue(false, "addGroup is failed");

		}
		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}

	}

	/**
	 * this method is for verify add group field
	 * 
	 * @author skhalasi
	 */
	@Test(description = "ezCAC_MVP-84:Verify that Admin can not add group without entering mandatory fields", priority = 4)
	public static void verifyGroupField()
	{
		try
		{
			Log4J.logp.info("*************************** Started - verifyGroupField *****************************");

			landingp_webe.imgAdmin_Setting.click();
			adminHome_webe.lnk_AddNew_Group.click();
			Thread.sleep(5000);

			addGroup_webe.btn_AddGroup.click();
			Thread.sleep(2000);
			Administrator_Lib.add_Group("NC022");
			Thread.sleep(2000);

			Thread.sleep(500);
			Log4J.logp.info("ezCAC_MVP-84:Verify that Admin can not add group without entering mandatory fields- Test Pass");
			Log4J.logp.info("********************************* Ending - verifyGroupField *****************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found In - verifyGroupField");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyGroupField is failed");

		}
		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}

	}

	/**
	 * the script will add three new users and search them on corresponding group
	 * 
	 * @author skhalasi
	 * @author nchourasiya Changes in script and database
	 * 
	 * */
	@Test(description = "ezCAC_MVP-80:Members will be updated in group(s) after adding new user", priority = 5)
	public static void verifyGroupMemUpdate()
	{
		int count = 0;
		JDBCMySql getData;
		try
		{

			Log4J.logp.info("******************************** Started - verifyGroupMemUpdate **********************");

			// Navigating to add user page
			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			adminHome_webe.lnk_AddNew_user.click();
			for (int i = 4; i <= 6; i++)
			{
				Thread.sleep(2000);
				Log4J.logp.info("Add user number--" + i);
				addUser_webe.btn_AddUser.click();
				Thread.sleep(6000);
				Administrator_Lib.add_User("sk00" + i);

			}
			Thread.sleep(2000);
			addUser_webe.lnk_Administrator_Homepage.click();
			adminHome_webe.lnk_AddNew_Group.click();

			getData = new JDBCMySql();

			for (int i = 4; i <= 6; i++)
			{
				String userFname1 = getData.getValuebyColumnName("td_adduser", "sk00" + i, "FirstName");
				String userLname1 = getData.getValuebyColumnName("td_adduser", "sk00" + i, "LastName");
				Log4J.logp.info("first name=" + userFname1 + "Last name =" + userLname1);
				String group1 = null;
				switch (i)
				{
					case 4:
						group1 = getData.getValuebyColumnName("td_adduser", "sk004", "CoderGroup");
						Log4J.logp.info(group1);
						Thread.sleep(2000);
						break;
					case 5:

						group1 = getData.getValuebyColumnName("td_adduser", "sk005", "reviewerGroup");
						Log4J.logp.info(group1);
						Thread.sleep(2000);
						break;
					case 6:

						group1 = getData.getValuebyColumnName("td_adduser", "sk006", "cdiGroup");
						Log4J.logp.info(group1);
						Thread.sleep(2000);
						break;
					default:
						Log4J.logp.info("We Found Group is null");
				}

				Administrator_Lib.find_group(group1, "open");
				Thread.sleep(500);
				List<WebElement> webelm = addGroup_webe.added_Member;

				for (WebElement ele : webelm)

				{

					if (ele.getText().contains(userFname1 + " " + userLname1))
					{
						count++;
						Log4J.logp.info("find " + i + " group ");
					}

				}
				Thread.sleep(1000);
				addGroup_webe.lnk_Close_RightPannel.click();
				Thread.sleep(2000);
				Common_Lib.scroll_Page(addGroup_webe.main_Dragger, 30);
				Thread.sleep(500);
			}
			if (count == 3)
			{
				Assert.assertTrue(true, "Members are found in all groups");
			}
			else
			{
				Assert.assertTrue(false, "Members are not found in all groups");
			}

			Log4J.logp.info("ezCAC_MVP-80:Members will be updated in group(s) after adding new user- Test Pass");
			Log4J.logp.info("******************************************  Ending - verifyGroupMemUpdate *************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found In - verifyGroupMemUpdate");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyGroupMemUpdate is failed");

		}
		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}
	}

	/**
	 * This method is for delete User
	 * 
	 * @author skhalasi
	 * @author nchourasiya - Changes in script and database
	 */
	@Test(description = "ezCAC_ MVP-55:Verify that Admin can add and delete with single roles" + "ezCAC_ MVP-55:Verify that Admin can add and delete user inside edit page user.", priority = 6)
	public static void deleteUserWithSingleRole()
	{

		JDBCMySql Q1 = null;
		boolean deleteUser = false;
		boolean btatus = false;
		String fname1 = null;
		String lname1 = null;
		String uname1 = null;

		String fname2 = null;
		String lname2 = null;
		String uname2 = null;
		//ezCAC_ MVP-55:Verify that Admin can add and delete with single roles
		try
		{
			Log4J.logp.info("************************************ Started - deleteUserWithSingleRole **************");

			Q1 = new JDBCMySql();
			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			adminHome_webe.lnk_AddNew_user.click();
			Thread.sleep(1000);
			addUser_webe.btn_AddUser.click();
			Thread.sleep(3000);
			Administrator_Lib.add_User("sk007");
			Thread.sleep(500);
			driver.navigate().refresh();
			Thread.sleep(1000);
			fname1 = Q1.getValuebyColumnName("td_adduser", "sk007", "FirstName");

			lname1 = Q1.getValuebyColumnName("td_adduser", "sk007", "LastName");
			uname1 = fname1 + " " + lname1;

			Log4J.logp.info("Username is = " + uname1);
			//	Thread.sleep(1000);
			btatus = Administrator_Lib.find_user(uname1, "Delete");

			Assert.assertTrue(btatus, "User deleted sucessfully");
			Thread.sleep(1000);

			// ezCAC_ MVP-55:Verify that Admin can add and delete user inside edit page user.

			addUser_webe.btn_AddUser.click();

			Thread.sleep(3000);
			Administrator_Lib.add_User("NC030");
			Thread.sleep(500);
			driver.navigate().refresh();
			Thread.sleep(1000);
			fname2 = Q1.getValuebyColumnName("td_adduser", "NC030", "FirstName");

			lname2 = Q1.getValuebyColumnName("td_adduser", "NC030", "LastName");
			uname2 = fname2 + " " + lname2;
			Log4J.logp.info("Username is = " + uname2);
			Thread.sleep(1000);
			Administrator_Lib.find_user(uname2, "Open");
			Thread.sleep(2000);
			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 350);

			addUser_webe.btn_delete.click();
			deleteUser = Common_Lib.IsAlertPresent();
			Log4J.logp.info("Alert for Delete User :" + deleteUser);
			Log4J.logp.info("ezCAC_ MVP-55:Verify that Admin can add and delete with single roles" + "ezCAC_ MVP-55:Verify that Admin can add and delete user inside edit page user - Test Pass");
			Log4J.logp.info("************************************ Ending - deleteUserWithSingleRole *****************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found In - deleteUserWithSingleRole");
			e.printStackTrace();
			Assert.assertTrue(false, "deleteUserWithSingleRole is failed");

		}
		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}

	}

	/**
	 * This method is for delete physician
	 * 
	 * @author skhalasi
	 * @author nchourasiya - changes with database
	 * */
	@Test(description = "ezCAC_MVP-70:Verify that Admin can delete physician" + "ezCAC_ MVP-70:Verify that Admin can delete physician user inside edit page user.", priority = 7)
	public static void deletePhysician()
	{

		JDBCMySql Q1 = null;
		boolean deletePhysician = false;
		boolean btatus = false;
		String fname1 = null;
		String lname1 = null;
		String pname1 = null;

		String fname2 = null;
		String lname2 = null;
		String pname2 = null;
		try
		{
			Log4J.logp.info(" ***************************     Started - deletePhysician  *************************");

			Q1 = new JDBCMySql();

			landingp_webe.lnk_All.click();

			landingp_webe.imgAdmin_Setting.click();

			adminHome_webe.lnk_AddNew_Physician.click();

			//	ezCAC_MVP-70:Verify that Admin can delete physician

			Thread.sleep(1000);
			addPhysician_webe.btn_AddPhysician.click();
			Thread.sleep(3000);
			Administrator_Lib.add_Physician("sk003");
			Thread.sleep(500);
			driver.navigate().refresh();
			Thread.sleep(1000);

			fname1 = Q1.getValuebyColumnName("td_addphysician", "sk003", "FirstName");

			lname1 = Q1.getValuebyColumnName("td_addphysician", "sk003", "LastName");
			pname1 = fname1 + " " + lname1;
			Log4J.logp.info("Physician is = " + pname1);

			//delete user directly from table
			btatus = Administrator_Lib.find_physician(pname1, "Delete");
			Assert.assertTrue(btatus, "Physician deleted sucessfully");

			Thread.sleep(1000);

			//ezCAC_ MVP-70:Verify that Admin can delete physician user inside edit page user.

			addPhysician_webe.btn_AddPhysician.click();
			Thread.sleep(500);
			Administrator_Lib.add_Physician("sk004");
			Thread.sleep(500);
			driver.navigate().refresh();
			Thread.sleep(1000);
			fname2 = Q1.getValuebyColumnName("td_addphysician", "sk004", "FirstName");

			lname2 = Q1.getValuebyColumnName("td_addphysician", "sk004", "LastName");
			pname2 = fname2 + " " + lname2;
			Log4J.logp.info("Username is = " + pname2);

			Administrator_Lib.find_physician(pname2, "Open");
			Thread.sleep(2000);
			Common_Lib.scroll_Page(addPhysician_webe.dragger_addPhysicain, 350);
			Thread.sleep(1000);
			addPhysician_webe.btn_delete.click();
			deletePhysician = Common_Lib.IsAlertPresent();
			Log4J.logp.info("Alert for Delete Physician :" + deletePhysician);
			Log4J.logp.info("ezCAC_MVP-70:Verify that Admin can delete physician" + "ezCAC_ MVP-70:Verify that Admin can delete physician user inside edit page user - Test Pass");
			Log4J.logp.info(" *********************************** Ending - deletePhysician *************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found In - deletePhysician");
			e.printStackTrace();
			Assert.assertTrue(false, "deletePhysician is failed");

		}
		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}

	}

	/**
	 * This method is for add user with multiple role
	 * 
	 * @author skhalasi
	 * @author nchourasiya - changes in database and script
	 * 
	 * */

	@Test(description = "ezCAC_ MVP-48:Verify that Admin can add user with multiple roles", priority = 8)
	public static void addUserWithMultipleRole()
	{
		JDBCMySql Q1 = null;

		String fname2 = null;
		String mname2 = null;
		String lname2 = null;
		String pname2 = null;

		try
		{
			boolean bstatus = false;
			Log4J.logp.info("**************************** Started - addUserWithMultipleRole **********************");
			Q1 = new JDBCMySql();
			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			adminHome_webe.lnk_AddNew_user.click();
			Thread.sleep(1000);
			addUser_webe.btn_AddUser.click();
			Thread.sleep(3000);
			bstatus = Administrator_Lib.add_User("sk003");
			Thread.sleep(500);
			driver.navigate().refresh();
			Thread.sleep(1000);
			fname2 = Q1.getValuebyColumnName("td_adduser", "sk003", "FirstName");
			mname2 = Q1.getValuebyColumnName("td_adduser", "sk003", "MiddleInitial");
			lname2 = Q1.getValuebyColumnName("td_adduser", "sk003", "LastName");
			pname2 = fname2 + " " + mname2 + " " + lname2;

			Thread.sleep(2000);
			Administrator_Lib.find_user(pname2, "find");

			Log4J.logp.info("ezCAC_ MVP-48:Verify that Admin can add user with multiple roles - Test Pass");
			Log4J.logp.info(" ***************************** Ending - addUserWithMultipleRole ***********************");
			Assert.assertTrue(bstatus, "The user is added with multiple roles sucessfully");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - addUserWithMultipleRole");
			e.printStackTrace();
			Assert.assertTrue(false, "addUserWithMultipleRole is failed");

		}
		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}

	}

	/**
	 * This method is to Edit group Roles
	 * 
	 * @author nchourasiya
	 * 
	 * 
	 * */

	@Test(description = " ezCAC_MVP_Reg-73:Verify that Admin can edit group with Role", priority = 9)
	public static void editGroupWithRole()
	{
		boolean bstatus = false;
		String GroupName = null;
		JDBCMySql result;
		try
		{

			result = new JDBCMySql();
			Log4J.logp.info("****************************** Started - editGroupWithRole  ***********************************");

			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(adminHome_webe.lnk_AddNew_Group));
			adminHome_webe.lnk_AddNew_Group.click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(addGroup_webe.btn_AddGroup));
			addGroup_webe.btn_AddGroup.click();

			Thread.sleep(2000);

			Administrator_Lib.add_Group("NC004");
			Thread.sleep(500);
			driver.navigate().refresh();
			Thread.sleep(500);
			GroupName = result.getValuebyColumnName("td_addgroup", "NC004", "Name");
			Thread.sleep(3000);

			wait.until(ExpectedConditions.visibilityOf(addGroup_webe.tbl_Group));

			Administrator_Lib.find_group(GroupName, "open");

			Thread.sleep(3000);

			bstatus = Administrator_Lib.add_Group("NC005");

			Log4J.logp.info("ezCAC_MVP_Reg-73:Verify that Admin can edit group with Role - Test Pass");
			Log4J.logp.info("****************************** Ending - editGroupWithRole  ***********************************");
			Assert.assertTrue(bstatus, "Group edit done");
		}
		catch (Exception e)
		{
			Log4J.logp.info("Problem Found In - editGroupWithRole");
			Assert.assertTrue(false, "editGroupWithRole is failed");
			e.printStackTrace();
		}

		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}
	}

	/**
	 * 
	 * This method is to edit group members
	 * 
	 * @author nchourasiya
	 * 
	 * @author nchourasiya- changes in database
	 * 
	 * */

	@Test(description = " ezCAC_MVP_Reg-75:Verify that Admin can edit group with Members", priority = 10)
	public static void editGroupWithMember()
	{
		String GroupName = null;
		boolean bstatus = false;
		JDBCMySql result;
		try
		{

			result = new JDBCMySql();
			Log4J.logp.info("****************************** Started - editGroupWithMember  ***********************************");

			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(adminHome_webe.lnk_AddNew_Group));
			adminHome_webe.lnk_AddNew_Group.click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(addGroup_webe.btn_AddGroup));
			addGroup_webe.btn_AddGroup.click();

			Thread.sleep(5000);
			Administrator_Lib.add_Group("NC017");
			Thread.sleep(1000);
			driver.navigate().refresh();
			Thread.sleep(2000);
			GroupName = result.getValuebyColumnName("td_addgroup", "NC017", "Name");

			//Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOf(addGroup_webe.tbl_Group));

			Thread.sleep(2000);
			Administrator_Lib.find_group(GroupName, "open");

			Thread.sleep(3000);

			bstatus = Administrator_Lib.add_Group("NC018");

			Log4J.logp.info("ezCAC_MVP_Reg-75:Verify that Admin can edit group with Members - Test Pass");
			Log4J.logp.info("****************************** Ending - editGroupWithMember  ***********************************");
			Assert.assertTrue(bstatus, "Group edit done");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - editGroupWithMember");
			Assert.assertTrue(false, "editGroupWithMember is failed");
			e.printStackTrace();
		}
		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}
	}

	/**
	 * 
	 * This method is to delete Group
	 * 
	 * @author nchourasiya
	 * 
	 * */

	@Test(description = "  ezCAC_MVP_Reg-76:Verify that Admin can delete group(s)", priority = 11)
	public static void deleteEditGroup()
	{
		boolean bstatus = false;
		String GroupName = null;
		JDBCMySql result;
		try
		{

			result = new JDBCMySql();
			Log4J.logp.info("****************************** Started - deleteEditGroup  ***********************************");

			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();

			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(adminHome_webe.lnk_AddNew_Group));
			adminHome_webe.lnk_AddNew_Group.click();
			Thread.sleep(2000);
			addGroup_webe.btn_AddGroup.click();
			Thread.sleep(1000);

			Administrator_Lib.add_Group("NC003");
			Thread.sleep(500);
			driver.navigate().refresh();
			Thread.sleep(1000);
			GroupName = result.getValuebyColumnName("td_addgroup", "NC003", "Name");
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(addGroup_webe.btn_AddGroup));

			Administrator_Lib.find_group(GroupName, "Open");
			Thread.sleep(2000);
			addGroup_webe.delete_btn.click();
			bstatus = Common_Lib.IsAlertPresent();
			Log4J.logp.info("Alert for Delete Group ");

			Log4J.logp.info("ezCAC_MVP_Reg-76:Verify that Admin can delete group(s) - Test Pass");
			Log4J.logp.info("****************************** Ending - deleteEditGroup  ***********************************");
			Assert.assertTrue(bstatus, "Group delete done");
		}
		catch (Exception e)
		{

			Log4J.logp.error("Problem Found In - deleteEditGroup");

			e.printStackTrace();
			Assert.assertTrue(false, "deleteEditGroup is failed");
		}
		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}

	}

	/**
	 * This method is to delete user and verify the update in group
	 * 
	 * @author nchourasiya
	 * 
	 * @author nchourasiya - changes in database
	 * */

	@Test(description = " ezCAC_MVP_Reg-78:If admin delete any user(s) then group(s) will be update + ezCAC_MVP_Reg-81:Members will be updated in group(s) after delete user", priority = 12)
	public static void deleteUserGroupUpdate()
	{
		boolean bstatus = false;
		String strActualName;
		String firstName = null;
		String lastName = null;
		String Name = null;
		String GroupName = null;
		JDBCMySql result;
		try
		{
			result = new JDBCMySql();
			Log4J.logp.info("****************************** Started - deleteUserGroupUpdate  ***********************************");

			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			wait = new WebDriverWait(driver, 20);

			wait.until(ExpectedConditions.visibilityOf(adminHome_webe.lnk_AddNew_user));
			adminHome_webe.lnk_AddNew_user.click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(addUser_webe.btn_AddUser));
			addUser_webe.btn_AddUser.click();
			Thread.sleep(1000);
			Administrator_Lib.add_User("NC002");
			Thread.sleep(500);
			driver.navigate().refresh();
			Thread.sleep(500);
			firstName = result.getValuebyColumnName("td_adduser", "NC002", "FirstName");
			lastName = result.getValuebyColumnName("td_adduser", "NC002", "LastName");
			Thread.sleep(1000);
			Name = firstName + " " + lastName;

			Thread.sleep(2000);
			addUser_webe.lnk_Administrator_Homepage.click();

			wait.until(ExpectedConditions.visibilityOf(adminHome_webe.lnk_AddNew_Group));
			adminHome_webe.lnk_AddNew_Group.click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(addGroup_webe.btn_AddGroup));
			addGroup_webe.btn_AddGroup.click();
			Thread.sleep(1000);

			Administrator_Lib.add_Group("NC006");
			Thread.sleep(500);
			driver.navigate().refresh();
			Thread.sleep(500);
			GroupName = result.getValuebyColumnName("td_addgroup", "NC006", "Name");

			Thread.sleep(1000);
			addGroup_webe.lnk_Administrator_Homepage.click();
			Thread.sleep(2000);

			adminHome_webe.lnk_AddNew_user.click();
			Thread.sleep(3000);

			bstatus = Administrator_Lib.find_user(Name, "Open");
			Thread.sleep(2000);
			Common_Lib.scroll_Page(addUser_webe.main_dragger, 350);
			Thread.sleep(500);
			addUser_webe.del_btn.click();
			Common_Lib.IsAlertPresent();
			Log4J.logp.info("Alert for Delete User ");
			Thread.sleep(2000);
			addUser_webe.lnk_Administrator_Homepage.click();
			Thread.sleep(1000);
			adminHome_webe.lnk_AddNew_Group.click();
			Thread.sleep(2000);

			Administrator_Lib.find_group(GroupName, "Open");

			for (WebElement temp : addGroup_webe.lst_MemberList)
			{
				strActualName = temp.getText();

				if (strActualName == Name)
				{
					bstatus = false;
					Log4J.logp.error("Deleted user is present on group");
					break;

				}
				else
				{
					bstatus = true;
					Log4J.logp.info("Deleted user is not present on group");
				}

			}

			Thread.sleep(1000);

			Log4J.logp.info(" ezCAC_MVP_Reg-78:If admin delete any user(s) then group(s) will be update + ezCAC_MVP_Reg-81:Members will be updated in group(s) after delete user - Test Pass");
			Log4J.logp.info(" ***************************** Ending - deleteUserGroupUpdate ***********************");
			Assert.assertTrue(bstatus, "deleteUserGroupUpdate");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - deleteUserGroupUpdate");
			e.printStackTrace();
			Assert.assertTrue(false, "deleteUserGroupUpdate is failed");
		}

		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}
	}

	/**
	 * This method is to edit group name
	 * 
	 * @author nchourasiya
	 * 
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-82:Verify that Admin can edit group name", priority = 13)
	public static void editGroupName()
	{
		boolean bstatus = false;
		String GroupName = null;
		JDBCMySql result;
		try
		{

			result = new JDBCMySql();
			Log4J.logp.info("****************************** Started - editGroupName  ***********************************");

			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(adminHome_webe.lnk_AddNew_Group));
			adminHome_webe.lnk_AddNew_Group.click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(addGroup_webe.btn_AddGroup));
			addGroup_webe.btn_AddGroup.click();

			Thread.sleep(1000);
			Administrator_Lib.add_Group("NC007");
			Thread.sleep(500);
			driver.navigate().refresh();
			Thread.sleep(500);
			GroupName = result.getValuebyColumnName("td_addgroup", "NC007", "Name");

			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOf(addGroup_webe.tbl_Group));

			Administrator_Lib.find_group(GroupName, "open");

			Thread.sleep(3000);

			bstatus = Administrator_Lib.add_Group("NC008");

			Log4J.logp.info("Group Name Edited Successfully");

			Log4J.logp.info("ezCAC_MVP_Reg-82:Verify that Admin can edit group name - Test Pass");
			Log4J.logp.info(" ***************************** Ending - editGroupName ***********************");
			Assert.assertTrue(bstatus, "Group Name edit done");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - editGroupName");
			e.printStackTrace();
			Assert.assertTrue(false, "editGroupName is failed");
		}

		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}
	}

	/**
	 * This method is to verify mandatory fields in add user
	 * 
	 * @author nchourasiya
	 * 
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-61:Verify that Admin can add user with only mandatory fields", priority = 14)
	public static void addUserWithMandatoryFields()
	{
		boolean bstatus = false;
		JDBCMySql result;
		String firstName = null;
		String LastName = null;
		String Name = null;
		try
		{

			result = new JDBCMySql();
			Log4J.logp.info("**************************** Started - addUserWithMandatoryFields **********************");

			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			adminHome_webe.lnk_AddNew_user.click();
			Thread.sleep(1000);
			addUser_webe.btn_AddUser.click();
			Thread.sleep(3000);
			bstatus = Administrator_Lib.add_User("NC003");
			Thread.sleep(500);
			driver.navigate().refresh();
			Thread.sleep(500);
			firstName = result.getValuebyColumnName("td_adduser", "NC003", "FirstName");
			LastName = result.getValuebyColumnName("td_addUser", "NC003", "LastName");
			Name = firstName + " " + LastName;

			Administrator_Lib.find_user(Name, "find");
			Thread.sleep(1000);

			Log4J.logp.info("ezCAC_MVP_Reg-61:Verify that Admin can add user with only mandatory fields - Test Pass");
			Log4J.logp.info(" ***************************** Ending - addUserWithMandatoryFields ***********************");
			Assert.assertTrue(bstatus, "The user is added with mandatory sucessfully");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - addUserWithMandatoryFields");
			e.printStackTrace();
			Assert.assertTrue(false, "addUserWithMandatoryFields is failed");
		}

		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}
	}

	/**
	 * This is to verify that deleted email id can not be used
	 * 
	 * @author nchourasiya
	 * 
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-828:Verify that Admin can not use email id which is already deleted previously", priority = 15)
	public static void addUserWithDeletedEmailId()
	{
		boolean bstatus = false;
		JDBCMySql result;
		String firstName = null;
		String lastName = null;
		String fullName = null;

		try
		{

			result = new JDBCMySql();
			Log4J.logp.info("**************************** Started - addUserWithDeletedEmailId **********************");
			//	int i = 0;
			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			adminHome_webe.lnk_AddNew_user.click();
			Thread.sleep(1000);
			addUser_webe.btn_AddUser.click();
			Thread.sleep(2000);
			Administrator_Lib.add_User("NC004");
			Thread.sleep(2000);
			driver.navigate().refresh();
			Thread.sleep(500);
			firstName = result.getValuebyColumnName("td_adduser", "NC004", "FirstName");

			lastName = result.getValuebyColumnName("td_adduser", "NC004", "LastName");

			fullName = firstName + " " + lastName;

			Thread.sleep(2000);

			bstatus = Administrator_Lib.find_user(fullName, "delete");
			Thread.sleep(3000);

			addUser_webe.btn_AddUser.click();
			Thread.sleep(5000);
			bstatus = Administrator_Lib.add_User("NC004");
			Thread.sleep(2000);

			Log4J.logp.info("ezCAC_MVP_Reg-828:Verify that Admin can not use email id which is already deleted previously - Test Pass ");
			Log4J.logp.info(" ***************************** Ending - addUserWithDeletedEmailId ***********************");
			Assert.assertTrue(bstatus, "The user is added with Deleted Email ID sucessfully");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - addUserWithDeletedEmailId");
			e.printStackTrace();
			Assert.assertTrue(false, "addUserWithDeletedEmailId is failed");
		}

		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}

	}

	/**
	 * This method is to remove specific member from group.
	 * 
	 * @author nchourasiya
	 * 
	 * @author nchourasiya - changes in Scripts
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-835:Verify that admin can remove members from the specific group", priority = 16)
	public static void removeMemberFromGroup()
	{
		boolean bstatus = false;
		JDBCMySql result;
		String GroupName = null;

		boolean bSucess;
		boolean bError;
		try
		{
			result = new JDBCMySql();

			Log4J.logp.info("****************************** Started - removeMemberFromGroup  ***********************************");

			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(adminHome_webe.lnk_AddNew_Group));
			adminHome_webe.lnk_AddNew_Group.click();
			Thread.sleep(1000);
			addGroup_webe.btn_AddGroup.click();
			Thread.sleep(2000);
			Administrator_Lib.add_Group("NC001");
			Thread.sleep(1000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			GroupName = result.getValuebyColumnName("td_addgroup", "NC001", "Name");
			Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(addGroup_webe.btn_AddGroup));
			Thread.sleep(1000);
			Administrator_Lib.find_group(GroupName, "Open");
			Thread.sleep(5000);

			String st = "Neil Shah";

			WebElement webElement = driver.findElement(By.xpath("//li[@class = 'search-choice']/span[text()='" + st + "']/following-sibling::a"));
			Thread.sleep(5000);
			webElement.click();
			Thread.sleep(5000);

			addGroup_webe.btn_Save.click();

			bSucess = Common_Lib.checkElementPresent(addGroup_webe.success_MsgTxt);
			if (bSucess == true)
			{

				Log4J.logp.info(addGroup_webe.success_MsgTxt.getText());
				Log4J.logp.info(GroupName + " Group Added Sucessfully.(Member Removed From The Group)");
				Assert.assertTrue(true, "Group added Successfully.");
			}

			bError = Common_Lib.checkElementPresent(addGroup_webe.warning_MsgTxt);
			if (bError == true)
			{

				Log4J.logp.info(addGroup_webe.warning_MsgTxt.getText());
				Log4J.logp.info(GroupName + " - Group not Added(Member Removed From The Group) ");
				Assert.assertTrue(true, "Group Not Added.");
			}
			bstatus = true;

			Thread.sleep(2000);

			Log4J.logp.info(" ezCAC_MVP_Reg-835:Verify that admin can remove members from the specific group- Test Pass");
			Log4J.logp.info(" ***************************** Ending - removeMemberFromGroup ***********************");
			Assert.assertTrue(bstatus, "Member Removed From The Group ");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - removeMemberFromGroup");
			e.printStackTrace();
			Assert.assertTrue(false, "removeMemberFromGroup is failed");
		}

		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}
	}

	/**
	 * This method is to add member to specific group.
	 * 
	 * @author nchourasiya
	 * 
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-834:Verify that admin can add members to specific group", priority = 17)
	public static void addMemberToGroup()
	{
		boolean bstatus = false;
		JDBCMySql result;
		String GroupName = null;
		try
		{

			result = new JDBCMySql();

			Log4J.logp.info("****************************** Started - addMemberToGroup  ***********************************");

			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(adminHome_webe.lnk_AddNew_Group));
			adminHome_webe.lnk_AddNew_Group.click();

			wait.until(ExpectedConditions.elementToBeClickable(addGroup_webe.btn_AddGroup));
			GroupName = result.getValuebyColumnName("td_addgroup", "NC001", "Name");
			Thread.sleep(1000);

			Administrator_Lib.find_group(GroupName, "Open");
			Thread.sleep(2000);

			bstatus = Administrator_Lib.add_Group("NC009");

			Log4J.logp.info("ezCAC_MVP_Reg-834:Verify that admin can add members to specific group - Test Pass");
			Log4J.logp.info(" ***************************** Ending - addMemberToGroup ***********************");
			Assert.assertTrue(bstatus, "Members Added to The Group ");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - addMemberToGroup");
			e.printStackTrace();
			Assert.assertTrue(false, "addMemberToGroup is failed");
		}

		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}

	}

	/**
	 * This method is to verify grid feature on groups page.
	 * 
	 * @author nchourasiya
	 * 
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-83:To verify User's page and grid features on Groups page", priority = 18)
	public static void verifyGridFeatureOnGroup()
	{
		boolean bstatus = false;
		try
		{

			Log4J.logp.info("****************************** Started - verifyGridFeatureOnGroup  ***********************************");

			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(adminHome_webe.lnk_AddNew_Group));
			adminHome_webe.lnk_AddNew_Group.click();
			Thread.sleep(1000);
			int rowCount = driver.findElements(By.xpath("//*[@id='admin-table']/tbody/tr")).size();
			Log4J.logp.info("Total no of rows including header: " + rowCount);
			int t = Integer.parseInt(addGroup_webe.get_GroupCount.getText());
			Log4J.logp.info("Total Groups : " + addGroup_webe.get_GroupCount.getText());
			if (rowCount > 16)
			{
				for (int i = 1; i <= (t / 15); i++)
				{
					Log4J.logp.info("More Than 15 Group are Present2");
					WebElement first = driver.findElement(By.xpath("//*[@id='pagingContainer']/ul/li[1]/strong[1]"));
					WebElement second = driver.findElement(By.xpath("//*[@id='pagingContainer']/ul/li[1]/strong[2]"));
					Log4J.logp.info("From -" + first.getText() + " To -" + second.getText() + " of " + t);
					addGroup_webe.lnk_Next.click();
					Thread.sleep(1000);

				}
			}
			else
			{
				Log4J.logp.info("Less Than 15 Group are Present");
				bstatus = true;
			}
			bstatus = true;

			Log4J.logp.info("ezCAC_MVP_Reg-83:To verify User's page and grid features on Groups page - Test Pass");
			Log4J.logp.info(" ***************************** Ending - verifyGridFeatureOnGroup ***********************");
			Assert.assertTrue(bstatus, "verifyGridFeatureOnGroup ");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - verifyGridFeatureOnGroup");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyGridFeatureOnGroup is failed");
		}

		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}

	}

	/**
	 * This method is to change single user role to another single user role
	 * 
	 * @author nchourasiya
	 * 
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-56:Verify that Admin can change single user role to another single user role.", priority = 19)
	public static void verifyAdminChangeSigleUserRole()
	{
		String fullName1 = null;
		String fullName = null;
		boolean bstatus = false;
		boolean bCdiPresent;
		try
		{

			Log4J.logp.info("****************************** Started - VerifyAdminChangeSigleUserRole  ***********************************");

			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(adminHome_webe.lnk_AddNew_user));
			adminHome_webe.lnk_AddNew_user.click();
			Thread.sleep(1000);
			/*addUser_webe.btn_AddUser.click();
			Thread.sleep(2000);*/
			//Administrator_Lib.add_User("NC006");
			fullName1 = "Coder Test1";
			fullName = "Coder Test";
			Administrator_Lib.find_user(fullName1, "open");
			Thread.sleep(500);
			Administrator_Lib.add_User("NC006");
			Thread.sleep(1000);
			driver.navigate().refresh();
			Thread.sleep(1000);

			Thread.sleep(2000);
			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);
			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);
			Thread.sleep(500);
			if (addUser_webe.chk_Coder.isSelected() == true)
			{
				addUser_webe.chk_Coder.click();
				//Log4J.logp.info("i am checking here - uncheck");
				Thread.sleep(1000);
				Administrator_Lib.add_User("NC007");
				Thread.sleep(500);
				Common_Lib.scroll_Page_Up(addUser_webe.main_dragger, 120);
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(1000);
			}
			Thread.sleep(3000);
			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);

			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);
			if (addUser_webe.chk_CDI.isSelected() == true)
			{
				Thread.sleep(1000);
				addUser_webe.chk_CDI.click();
				Thread.sleep(1000);

				Administrator_Lib.add_User("NC009");
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(1000);
			}
			Thread.sleep(1000);
			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);

			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);
			if (addUser_webe.chk_Reviewer.isSelected() == true)
			{
				Thread.sleep(1000);
				addUser_webe.chk_Reviewer.click();
				Thread.sleep(1000);
				Administrator_Lib.add_User("NC011");
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(1000);
			}
			Thread.sleep(1000);
			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);

			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);
			if (addUser_webe.chk_Coder.isSelected() == true)
			{
				Thread.sleep(1000);
				addUser_webe.chk_Coder.click();
				Thread.sleep(1000);

				Administrator_Lib.add_User("NC010");
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(1000);
			}
			Thread.sleep(3000);
			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);

			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);
			if (addUser_webe.chk_Reviewer.isSelected() == true)
			{
				Thread.sleep(1000);
				addUser_webe.chk_Reviewer.click();
				Thread.sleep(1000);

				Administrator_Lib.add_User("NC008");
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(1000);
			}
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("nc019");
			Thread.sleep(2000);

			bCdiPresent = Common_Lib.checkElementPresent(landingp_webe.lnk_CDI);

			if (bCdiPresent == true)
			{
				Assert.assertTrue(true, "VerifyAdminChangeSigleUserRole is passed");
				Log4J.logp.info(landingp_webe.lnk_CDI.getText() + " - User Found With This Role ");
				Log4J.logp.info("VerifyAdminChangeSigleUserRole is passed");

			}
			else
			{

				Log4J.logp.info("VerifyAdminChangeSigleUserRole is Fail");

			}
			Thread.sleep(1000);
			bstatus = Login_Lib.logOut_App();

			Log4J.logp.info("ezCAC_MVP_Reg-56:Verify that Admin can change single user role to another single user role. - Test Pass");
			Log4J.logp.info(" ***************************** Ending - VerifyAdminChangeSigleUserRole ***********************");
			Assert.assertTrue(bstatus, "VerifyAdminChangeSigleUserRole ");
		}

		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - VerifyAdminChangeSigleUserRole");
			e.printStackTrace();
			Assert.assertTrue(false, "VerifyAdminChangeSigleUserRole is failed");
		}

		finally
		{
			driver.navigate().refresh();

		}

	}

	/**
	 * This method is to change single user role to another multiple user role
	 * 
	 * @author nchourasiya
	 * 
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-57:Verify that Admin can change single user role to multiple user role.", priority = 20)
	public static void verifyAdminChangeSingleTomultipleRole()
	{
		String fullName;
		boolean bRolePresent;
		boolean bstatus = false;
		try
		{

			Log4J.logp.info("****************************** Started - verifyAdminChangeSingleTomultipleRole  ***********************************");
			/*Login_Lib.logOut_App();*/

			Login_Lib.login("nc003");
			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(adminHome_webe.lnk_AddNew_user));
			adminHome_webe.lnk_AddNew_user.click();

			fullName = "Linda Williams";
			Thread.sleep(3000);

			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);
			Administrator_Lib.add_User("NC012");
			Thread.sleep(1000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			Thread.sleep(2000);
			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);
			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);
			Thread.sleep(500);
			if (addUser_webe.chk_Coder.isSelected() == true)
			{
				addUser_webe.chk_Coder.click();

				Thread.sleep(1000);
				Administrator_Lib.add_User("NC013");
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(1000);
			}
			Thread.sleep(2000);
			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);
			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);
			Thread.sleep(500);
			if (addUser_webe.chk_CDI.isSelected() == true && addUser_webe.chk_Reviewer.isSelected() == true)
			{
				addUser_webe.chk_CDI.click();
				addUser_webe.chk_Reviewer.click();

				Thread.sleep(2000);
				Administrator_Lib.add_User("NC014");
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(1000);
			}
			Thread.sleep(2000);
			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);
			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);
			Thread.sleep(500);
			if (addUser_webe.chk_Reviewer.isSelected() == true)
			{
				addUser_webe.chk_Reviewer.click();

				Thread.sleep(2000);
				Administrator_Lib.add_User("NC015");
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(1000);
			}

			Thread.sleep(2000);
			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);
			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);
			Thread.sleep(500);
			if (addUser_webe.chk_Coder.isSelected() == true && addUser_webe.chk_CDI.isSelected() == true)
			{
				addUser_webe.chk_Coder.click();
				addUser_webe.chk_CDI.click();

				Thread.sleep(2000);
				Administrator_Lib.add_User("NC016");
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(1000);
			}
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("nc020");
			Thread.sleep(2000);

			int i = 0;

			Log4J.logp.info("****Checking Present Role For User*****");
			bRolePresent = Common_Lib.checkListOfElementsPresent(landingp_webe.user_Role, i);
			if (bRolePresent == true)
			{

				List<WebElement> webelements = landingp_webe.user_Role;

				for (WebElement role : webelements)
				{

					if (role.isDisplayed() == false)
					{
						Log4J.logp.info("----");

					}
					else if (role.getText() != null)
					{
						Log4J.logp.info(" - User Role Found -" + role.getText());

					}

				}
				Log4J.logp.info("****Present User Role*****");
				i++;
			}
			Thread.sleep(2000);
			boolean coder = Common_Lib.checkElementPresent(landingp_webe.lnk_Coding);
			boolean review = Common_Lib.checkElementPresent(landingp_webe.lnk_Review);
			boolean cdi = Common_Lib.checkElementPresent(landingp_webe.lnk_CDI);

			if (coder == true)
			{
				Log4J.logp.info(landingp_webe.lnk_Coding.getText() + "- Coder Present - Test Pass");
			}
			else
			{
				Log4J.logp.info("Coder not Present - Test Fail");
			}

			if (review == true)
			{
				Log4J.logp.info(landingp_webe.lnk_Review.getText() + " - review Present - Test Pass");
			}
			else
			{
				Log4J.logp.info("review not Present - Test Fail");
			}

			if (cdi == true)
			{
				Log4J.logp.info(landingp_webe.lnk_CDI.getText() + " - cdi Present - Test Pass");
			}
			else
			{
				Log4J.logp.info("cdi not Present - Test Fail");
			}

			bstatus = Login_Lib.logOut_App();

			Thread.sleep(1000);
			Log4J.logp.info("ezCAC_MVP_Reg-57:Verify that Admin can change single user role to multiple user role. - Test Pass");
			Log4J.logp.info(" ***************************** Ending - verifyAdminChangeSingleTomultipleRole ***********************");
			Assert.assertTrue(bstatus, "verifyAdminChangeSingleTomultipleRole ");
		}
		catch (Exception e)
		{
			Log4J.logp.info("Problem Found In - verifyAdminChangeSingleTomultipleRole");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyAdminChangeSingleTomultipleRole is failed");
		}

		finally
		{
			driver.navigate().refresh();
		}

	}

	/**
	 * This method is to change single multiple role to another multiple user role
	 * 
	 * @author nchourasiya
	 * 
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-58:Verify that Admin can change multiple user roles to multiple user roles", priority = 21)
	public static void verifyAdminChangeMultipleRoleToMultipleRole()
	{
		boolean bstatus = false;
		String fullName = null;
		boolean bCheckingRole;

		boolean bcoder;
		boolean breview;
		boolean bcdi;

		try
		{

			Log4J.logp.info("****************************** Started - VerifyAdminChangeMultipleRoleToMultipleRole  ***********************************");
			/*	Login_Lib.logOut_App();*/
			Login_Lib.login("nc003");
			Thread.sleep(4000);
			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(adminHome_webe.lnk_AddNew_user));
			adminHome_webe.lnk_AddNew_user.click();
			fullName = "Dorothy Taylor";
			Thread.sleep(500);
			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(1000);
			Administrator_Lib.add_User("NC017");
			Thread.sleep(1000);
			driver.navigate().refresh();

			Thread.sleep(2000);

			Thread.sleep(2000);
			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);
			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);
			Thread.sleep(500);
			if (addUser_webe.chk_CDI.isSelected() == true && addUser_webe.chk_Reviewer.isSelected() == true)
			{
				addUser_webe.chk_CDI.click();
				addUser_webe.chk_Reviewer.click();
				Thread.sleep(1000);
				Administrator_Lib.add_User("NC018");
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(1000);
			}

			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);
			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);
			if (addUser_webe.chk_Coder.isSelected() == true && addUser_webe.chk_Reviewer.isSelected() == true)
			{
				addUser_webe.chk_Coder.click();
				addUser_webe.chk_Reviewer.click();
				Thread.sleep(1000);
				Administrator_Lib.add_User("NC019");
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(1000);
			}

			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);
			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);
			if (addUser_webe.chk_CDI.isSelected() == true && addUser_webe.chk_Coder.isSelected() == true)
			{
				addUser_webe.chk_CDI.click();
				addUser_webe.chk_Coder.click();
				Thread.sleep(1000);
				Administrator_Lib.add_User("NC020");
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(1000);

			}
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc021");
			Thread.sleep(2000);

			int i = 0;
			Thread.sleep(2000);
			Log4J.logp.info("****Checking Present Role For User*****");
			bCheckingRole = Common_Lib.checkListOfElementsPresent(landingp_webe.user_Role, i);
			if (bCheckingRole == true)
			{

				List<WebElement> webelements = landingp_webe.user_Role;

				for (WebElement role : webelements)
				{

					if (role.isDisplayed() == false)
					{
						Log4J.logp.info("----");

					}
					else if (role.getText() != null)
					{
						Log4J.logp.info(role.getText() + " - User Role Found");

					}

				}
				Log4J.logp.info("****Present User Role*****");
				i++;
			}
			bcoder = Common_Lib.checkElementPresent(landingp_webe.lnk_Coding);
			breview = Common_Lib.checkElementPresent(landingp_webe.lnk_Review);
			bcdi = Common_Lib.checkElementPresent(landingp_webe.lnk_CDI);

			if (bcoder == true)
			{
				Log4J.logp.info(landingp_webe.lnk_Coding.getText() + "- Coder Present - Test Pass");
			}
			else
			{
				Log4J.logp.info("Coder not Present - Test Fail");
			}

			if (breview == true)
			{
				Log4J.logp.info(landingp_webe.lnk_Review.getText() + " - review Present - Test Pass");
			}
			else
			{
				Log4J.logp.info("review not Present - Test Fail");
			}

			if (bcdi == true)
			{
				Log4J.logp.info(landingp_webe.lnk_CDI.getText() + " - cdi Present - Test Pass");
			}
			else
			{
				Log4J.logp.info("cdi not Present - Test Fail");
			}

			bstatus = Login_Lib.logOut_App();

			Thread.sleep(2000);
			Log4J.logp.info("ezCAC_MVP_Reg-58:Verify that Admin can change multiple user roles to multiple user roles - Test Pass");
			Log4J.logp.info(" ***************************** Ending - VerifyAdminChangeMultipleRoleToMultipleRole ***********************");
			Assert.assertTrue(bstatus, "VerifyAdminChangeMultipleRoleToMultipleRole ");
		}

		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - VerifyAdminChangeMultipleRoleToMultipleRole");
			e.printStackTrace();
			Assert.assertTrue(false, "VerifyAdminChangeMultipleRoleToMultipleRole is failed");
		}

		finally
		{
			driver.navigate().refresh();
		}
	}

	/**
	 * This method is to change single multiple role to another single user name
	 * 
	 * @author nchourasiya
	 * 
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-59:Verify that Admin can change multiple user roles to single user role", priority = 22)
	public static void verifyAdminChangeMultipleToSingleRole()
	{
		boolean bstatus = false;
		String fullName = null;
		boolean bCheckingRolePresent;

		try
		{

			Log4J.logp.info("****************************** Started - VerifyAdminChangeMultipleToSingleRole  ***********************************");
			Login_Lib.login("nc003");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(adminHome_webe.lnk_AddNew_user));
			adminHome_webe.lnk_AddNew_user.click();
			Thread.sleep(2000);
			/*addUser_webe.btn_AddUser.click();*/
			fullName = "Patricia Johnson";
			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(5000);
			Administrator_Lib.add_User("NC021");
			Thread.sleep(2000);

			driver.navigate().refresh();
			Thread.sleep(1000);

			Thread.sleep(2000);
			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);
			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);
			Thread.sleep(500);
			if (addUser_webe.chk_Coder.isSelected() == true && addUser_webe.chk_CDI.isSelected() == true)
			{
				addUser_webe.chk_CDI.click();
				addUser_webe.chk_Coder.click();
				Thread.sleep(1000);
				Administrator_Lib.add_User("NC022");
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(1000);

			}

			Thread.sleep(2000);
			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);
			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);
			Thread.sleep(500);
			if (addUser_webe.chk_Reviewer.isSelected() == true)
			{
				addUser_webe.chk_Reviewer.click();

				Thread.sleep(1000);
				Administrator_Lib.add_User("NC023");
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(1000);

			}

			Thread.sleep(2000);
			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);
			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);
			Thread.sleep(500);
			if (addUser_webe.chk_Reviewer.isSelected() == true && addUser_webe.chk_CDI.isSelected() == true)
			{
				addUser_webe.chk_CDI.click();
				addUser_webe.chk_Reviewer.click();
				Thread.sleep(1000);
				Administrator_Lib.add_User("NC024");
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(1000);

			}

			Thread.sleep(2000);
			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);
			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);
			if (addUser_webe.chk_Coder.isSelected() == true)
			{

				addUser_webe.chk_Coder.click();
				Thread.sleep(1000);
				Administrator_Lib.add_User("NC025");
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(1000);

			}
			Thread.sleep(2000);
			Administrator_Lib.find_user(fullName, "open");
			Thread.sleep(2000);
			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);
			if (addUser_webe.chk_Coder.isSelected() == true && addUser_webe.chk_Reviewer.isSelected() == true)
			{
				addUser_webe.chk_Reviewer.click();
				addUser_webe.chk_Coder.click();
				Thread.sleep(1000);
				Administrator_Lib.add_User("NC026");
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(1000);

			}

			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			Login_Lib.login("nc022");
			Thread.sleep(2000);

			bCheckingRolePresent = Common_Lib.checkElementPresent(landingp_webe.lnk_CDI);

			if (bCheckingRolePresent == true)
			{
				Log4J.logp.info(landingp_webe.lnk_CDI.getText() + " - User Found With This Role ");
				Log4J.logp.info("VerifyAdminChangeMultipleToSingleRole is passed");
				Assert.assertTrue(true, "VerifyAdminChangeMultipleToSingleRole is passed");
			}
			else
			{
				Log4J.logp.info("VerifyAdminChangeMultipleToSingleRole is Fail");

			}

			bstatus = Login_Lib.logOut_App();

			Thread.sleep(1000);
			Log4J.logp.info("ezCAC_MVP_Reg-59:Verify that Admin can change multiple user roles to single user role - Test Pass");
			Log4J.logp.info(" ***************************** Ending - VerifyAdminChangeMultipleToSingleRole ***********************");
			Assert.assertTrue(bstatus, "VerifyAdminChangeMultipleToSingleRole ");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - VerifyAdminChangeMultipleToSingleRole");
			e.printStackTrace();
			Assert.assertTrue(false, "VerifyAdminChangeMultipleToSingleRole is failed");
		}

		finally
		{
			driver.navigate().refresh();
		}
	}

	/**
	 * 
	 * This method is to check mandatory fields in add user
	 * 
	 * @author nchourasiya
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-60:Verify that Admin can not add user without entering mandatory fields", priority = 23)
	public static void adminCanNotAddUserWithoutMandatoryFields()
	{
		boolean bstatus = false;
		try
		{

			Log4J.logp.info("****************************** Started - AdminCanNotAddUserWithoutMandatoryFields  ***********************************");
			Login_Lib.login("nc003");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(adminHome_webe.lnk_AddNew_user));
			adminHome_webe.lnk_AddNew_user.click();
			Thread.sleep(2000);
			addUser_webe.btn_AddUser.click();
			Thread.sleep(5000);
			Administrator_Lib.add_User("NC027");

			if (addUser_webe.err_Block.toString() != null)
			{
				Log4J.logp.info("User not added without entering mandatory fields - Test Passed.");
				Assert.assertTrue(true, "User not added");
				bstatus = true;
			}
			else
			{
				Log4J.logp.info("User added without entering mandatory fields - Test Failed.");
				Assert.assertTrue(false, "User added");
				bstatus = false;
			}

			Log4J.logp.info("ezCAC_MVP_Reg-60:Verify that Admin can not add user without entering mandatory fields - Script Pass");
			Log4J.logp.info(" ***************************** Ending - AdminCanNotAddUserWithoutMandatoryFields ***********************");
			Assert.assertTrue(bstatus, " AdminCanNotAddUserWithoutMandatoryFields");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In -  AdminCanNotAddUserWithoutMandatoryFields");
			e.printStackTrace();
			Assert.assertTrue(false, " AdminCanNotAddUserWithoutMandatoryFields is failed");
		}

		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}
	}

	/**
	 * This method is to edit physician
	 * 
	 * @author nchourasiya
	 * 
	 * 
	 * */
	@Test(description = " ezCAC_MVP_Reg-69:Verify that Admin can edit physician", priority = 24)
	public static void adminEditPhysician() throws Exception
	{
		boolean bstatus = false;
		String firstName = null;

		String lastName = null;
		String fullName = null;

		try
		{

			JDBCMySql result = new JDBCMySql();
			Log4J.logp.info("************************************** Started - AdminEditPhysician *************************");
			Thread.sleep(2000);
			landingp_webe.imgAdmin_Setting.click();
			adminHome_webe.lnk_AddNew_Physician.click();
			Thread.sleep(3000);
			addPhysician_webe.btn_AddPhysician.click();
			Thread.sleep(5000);
			Administrator_Lib.add_Physician("NC001");
			Thread.sleep(2000);
			driver.navigate().refresh();
			Thread.sleep(2000);
			firstName = result.getValuebyColumnName("td_addphysician", "1", "FirstName");

			lastName = result.getValuebyColumnName("td_addphysician", "1", "LastName");
			fullName = firstName + " " + lastName;

			Administrator_Lib.find_physician(fullName, "open");
			Thread.sleep(2000);

			bstatus = Administrator_Lib.add_Physician("NC002");

			Log4J.logp.info("ezCAC_MVP_Reg-69:Verify that Admin can edit physician - Test Pass");
			Log4J.logp.info(" ***************************** Ending - AdminEditPhysician ***********************");
			Assert.assertTrue(bstatus, " AdminEditPhysician");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In -  AdminEditPhysician");
			e.printStackTrace();
			Assert.assertTrue(false, " AdminEditPhysician is failed");
		}

		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}
	}

	/**
	 * This method is to check mandatory fields in Physician
	 * 
	 * @author nchourasiya
	 * 
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-71:Verify that Admin can not add physician without entering mandatory fields", priority = 25)
	public static void adminCanNotAddPhysicianWithoutMandatoryFields()
	{
		boolean bstatus = false;
		try
		{

			Log4J.logp.info("************************************** Started - AdminCanNotAddPhysicianWithoutMandatoryFields *************************");

			landingp_webe.imgAdmin_Setting.click();
			adminHome_webe.lnk_AddNew_Physician.click();
			Thread.sleep(3000);
			addPhysician_webe.btn_AddPhysician.click();
			Thread.sleep(5000);
			Administrator_Lib.add_Physician("NC003");

			if (addPhysician_webe.err_Block.toString() != null)
			{

				Log4J.logp.info("Physician not added without entering mandatory fields - Test Passed.");
				Assert.assertTrue(true, "Physician not added");
				bstatus = true;
			}
			else
			{
				Assert.assertTrue(false, "Physician added");
				bstatus = false;
			}

			Thread.sleep(2000);

			Log4J.logp.info("ezCAC_MVP_Reg-71:Verify that Admin can not add physician without entering mandatory fields - Test Pass");
			Log4J.logp.info(" ***************************** Ending - AdminCanNotAddPhysicianWithoutMandatoryFields ***********************");
			Assert.assertTrue(bstatus, " AdminEditPhysician");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In -  AdminCanNotAddPhysicianWithoutMandatoryFields");
			e.printStackTrace();
			Assert.assertTrue(false, " AdminCanNotAddPhysicianWithoutMandatoryFields is failed");
		}

		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}
	}

	/**
	 * 
	 * This method is to check Admin can not create same group with same parameters
	 * 
	 * @author nchourasiya
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-826:Verify that Admin can not create same group with same parameters", priority = 26)
	public static void sameGroupWithSameParameters() throws Exception
	{
		boolean bstatus = false;
		try
		{

			Log4J.logp.info("****************************** Started - sameGroupWithSameParameters  ***********************************");

			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(adminHome_webe.lnk_AddNew_Group));
			adminHome_webe.lnk_AddNew_Group.click();
			Thread.sleep(3000);

			addGroup_webe.btn_AddGroup.click();
			wait.until(ExpectedConditions.visibilityOf(addGroup_webe.txt_Name));

			Administrator_Lib.add_Group("NC010");

			Thread.sleep(5000);
			addGroup_webe.btn_AddGroup.click();
			Thread.sleep(2000);
			bstatus = Administrator_Lib.add_Group("NC011");

			Log4J.logp.info("sameGroupWithSameParameters - Test Pass");

			Log4J.logp.info("ezCAC_MVP_Reg-826:Verify that Admin can not create same group with same parameters - Test Pass");
			Log4J.logp.info("********************************** Ending - sameGroupWithSameParameters ***********************************");
			Assert.assertTrue(bstatus, " sameGroupWithSameParameters");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In -  sameGroupWithSameParameters");
			e.printStackTrace();
			Assert.assertTrue(false, " sameGroupWithSameParameters is failed");
		}

		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}
	}

	/**
	 * 
	 * This method is to Check Admin can not edit group with same name which is already created in any group
	 * 
	 * @author nchourasiya
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-827:Verify that Admin can not edit group with same name which is already created in any group(s)", priority = 27)
	public static void GroupWithSameName() throws Exception
	{
		boolean bstatus = false;

		try
		{

			Log4J.logp.info("****************************** Started - GroupWithSameName  ***********************************");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(adminHome_webe.lnk_AddNew_Group));
			adminHome_webe.lnk_AddNew_Group.click();
			Thread.sleep(3000);

			addGroup_webe.btn_AddGroup.click();
			wait.until(ExpectedConditions.visibilityOf(addGroup_webe.txt_Name));

			Administrator_Lib.add_Group("NC012");

			Thread.sleep(5000);
			addGroup_webe.btn_AddGroup.click();
			Thread.sleep(2000);
			bstatus = Administrator_Lib.add_Group("NC013");

			Log4J.logp.info("GroupWithSameName - Test Pass");

			Log4J.logp.info("ezCAC_MVP_Reg-827:Verify that Admin can not edit group with same name which is already created in any group(s) - Test Pass");
			Log4J.logp.info("********************************** Ending - GroupWithSameName ***********************************");
			Assert.assertTrue(bstatus, " GroupWithSameName");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In -  GroupWithSameName");
			e.printStackTrace();
			Assert.assertTrue(false, " GroupWithSameName is failed");
		}
		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}
	}

	/**
	 * This method is to check the admin setting icon in each role
	 * 
	 * 
	 * @author nchourasiya
	 * 
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-831:Verify that admin page should not be displayed with cdi, coder and reviewer user role", priority = 28)
	public static void adminPageShouldNotBeDisplayWithAnyRole()
	{
		boolean bstatus = false;
		try
		{

			Log4J.logp.info("****************************** Started - adminPageShouldNotBeDisplayWithAnyRole  ***********************************");
			Thread.sleep(5000);

			if (landingp_webe.lnk_All.isDisplayed() == true)
			{
				landingp_webe.lnk_All.click();

				Log4J.logp.info("In Admin Role");
				Thread.sleep(3000);

				if (landingp_webe.lnk_All.isEnabled() == true && Common_Lib.checkElementPresent(landingp_webe.imgAdmin_Setting) == true)

				{
					Log4J.logp.info("In Admin Role with admin page");
					bstatus = true;
				}
				else
				{
					Log4J.logp.error("In Admin Role no with admin page");
					bstatus = false;
				}
			}

			Thread.sleep(5000);

			if (landingp_webe.lnk_Coding.isDisplayed() == true)
			{

				landingp_webe.lnk_Coding.click();
				Log4J.logp.info("In coder Role ");
				Thread.sleep(3000);

				if (landingp_webe.lnk_Coding.isEnabled() == true && Common_Lib.checkElementPresent(landingp_webe.imgAdmin_Setting) == false)
				{
					Log4J.logp.info("In coder Role with no admin page");
					bstatus = true;

				}
				else
				{
					Log4J.logp.error("In coder Role with admin page");
					bstatus = false;
				}
			}

			Thread.sleep(5000);
			if (landingp_webe.lnk_Review.isDisplayed() == true)
			{
				landingp_webe.lnk_Review.click();
				Log4J.logp.info("In Reviwer Role");
				Thread.sleep(3000);
				if (landingp_webe.lnk_Review.isEnabled() == true && Common_Lib.checkElementPresent(landingp_webe.imgAdmin_Setting) == false)
				{
					Log4J.logp.info("In Reviwer Role with no admin page");
					bstatus = true;
				}
				else
				{
					Log4J.logp.error("In Reviwer Role with admin page");
					bstatus = false;
				}
			}
			Thread.sleep(5000);

			if (landingp_webe.lnk_CDI.isDisplayed() == true)
			{
				landingp_webe.lnk_CDI.click();
				Log4J.logp.info("In CDI Role");
				Thread.sleep(3000);
				if (landingp_webe.lnk_CDI.isEnabled() == true && Common_Lib.checkElementPresent(landingp_webe.imgAdmin_Setting) == false)
				{
					Log4J.logp.info("In CDI Role with no admin page");
					bstatus = true;

				}
				else
				{
					Log4J.logp.error("In CDI Role with admin page");
					bstatus = false;
				}
			}

			Log4J.logp.info("ezCAC_MVP_Reg-831:Verify that admin page should not be displayed with cdi, coder and reviewer user role - Test Pass");
			Log4J.logp.info("********************************** Ending - adminPageShouldNotBeDisplayWithAnyRole ***********************************");
			Assert.assertTrue(bstatus, " adminPageShouldNotBeDisplayWithAnyRole");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In -  adminPageShouldNotBeDisplayWithAnyRole");
			e.printStackTrace();
			Assert.assertTrue(false, " adminPageShouldNotBeDisplayWithAnyRole is failed");
		}
		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}
	}

	/**
	 * 
	 * This method is to check if user is working on any case then admin can nhot delete that user
	 * 
	 * @author nchourasiya- changes in script and database
	 * */
	@Test(description = "ezCAC_MVP_Reg-2980:To check when user working on any case and admin try to delete this users", priority = 29)
	public static void workingUserCanNotBeDeleted()
	{

		String checkingUserName = null;

		try
		{
			boolean bstatus = false;
			Log4J.logp.info("**************************** Started - workingUserCanNotBeDeleted **********************");
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("NC002");

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("NC001");
			Thread.sleep(15000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(5000);
			WebElement name = driver.findElement(By.xpath("//*[@id='searchResults']/tbody/tr[1]/td[4]"));
			Log4J.logp.info("User is :" + name.getText());

			checkingUserName = name.getText();
			if (checkingUserName == null)
			{
				Log4J.logp.info("Case is IN Queue");
			}
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("NC003");

			landingp_webe.lnk_All.click();
			Thread.sleep(1000);
			landingp_webe.imgAdmin_Setting.click();
			Thread.sleep(3000);
			adminHome_webe.lnk_AddNew_user.click();
			Thread.sleep(4000);

			//Log4J.logp.info("User not deleted");
			bstatus = Administrator_Lib.find_user(checkingUserName, "delete");

			Log4J.logp.info("ezCAC_MVP_Reg-2980:To check when user working on any case and admin try to delete this users - Test Pass");
			Log4J.logp.info("********************************** Ending - workingUserCanNotBeDeleted ***********************************");
			Assert.assertTrue(bstatus, " workingUserCanNotBeDeleted");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In -  workingUserCanNotBeDeleted");
			e.printStackTrace();
			Assert.assertTrue(false, " workingUserCanNotBeDeleted is failed");
		}
		finally
		{
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				landingp_webe.lnk_Cases.click();
			}
		}

	}

	@AfterClass
	public static void AdministratorAfterClass()
	{
		try
		{
			if (driver != null)
			{
				driver = null;
			}
			if (addUser_webe != null)
			{
				addUser_webe = null;
			}
			if (adminHome_webe != null)
			{
				adminHome_webe = null;
			}
			if (landingp_webe != null)
			{
				landingp_webe = null;
			}
			if (addPhysician_webe != null)
			{
				addPhysician_webe = null;
			}
			if (addGroup_webe != null)
			{
				addGroup_webe = null;
			}
			if (wait != null)
			{
				wait = null;
			}
			if (common_webe != null)
			{
				common_webe = null;
			}
			if (landingp_webe != null)
			{
				landingp_webe = null;
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
