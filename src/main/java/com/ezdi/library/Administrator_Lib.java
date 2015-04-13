package com.ezdi.library;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ezdi.framework.common.JDBCMySql;
import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.webelements.AddGroup_WebE;
import com.ezdi.webelements.AddPhysician_WebE;
import com.ezdi.webelements.AddUser_WebE;
import com.ezdi.webelements.Comman_WebE;

public class Administrator_Lib
{

	static WebDriver			driver;
	static AddUser_WebE			addUser_webe;
	static AddPhysician_WebE	addPhysician_webe;
	static AddGroup_WebE		addGroup_webe;
	static Comman_WebE			common_webe;
	public static WebDriverWait	wait;

	public static boolean add_User(String rowId)
	{
		String firstName = null;
		String lastName = null;
		String middleInitial = null;
		String emailId = null;
		String address = null;
		String city = null;
		String state = null;
		String zipCode = null;
		String country = null;
		String phone = null;
		String fax = null;
		String coder = null;
		String cdi = null;
		String reviewer = null;
		String coderGroup = null;
		String reviewerGroup = null;
		String cdiGroup = null;

		String onsite = null;
		String offsite = null;
		String testing_Inpatient = null;
		String testing_Outpatient = null;
		String speciality = null;
		Map<String, String> rowTestData = null;
		int i = 0;
		boolean bvalidationmsg;
		boolean bSucess;
		boolean bError;
		try
		{
			Log4J.logp.info("-----------------Started : add_User------------");

			Log4J.logp.info("In add_User for rowId   " + rowId);
			driver = ExecutionSetup.getDriver();
			addUser_webe = AddUser_WebE.getInstance(driver);

			// JDBCSQLLite addUser_TestData =
			// Administrator_Lib.load_AddUser_Data();
			JDBCMySql addUser_TestData = new JDBCMySql();

			rowTestData = addUser_TestData.getRowbyID("td_adduser", rowId);

			firstName = rowTestData.get("FirstName");
			lastName = rowTestData.get("LastName");
			middleInitial = rowTestData.get("MiddleInitial");
			emailId = rowTestData.get("EmailId");
			address = rowTestData.get("Address");
			city = rowTestData.get("City");
			state = rowTestData.get("State");
			zipCode = rowTestData.get("ZipCode");
			country = rowTestData.get("Country");
			phone = rowTestData.get("Phone");
			fax = rowTestData.get("Fax");
			coder = rowTestData.get("Coder");
			reviewer = rowTestData.get("Reviewer");
			cdi = rowTestData.get("CDI");
			coderGroup = rowTestData.get("CoderGroup");
			reviewerGroup = rowTestData.get("reviewerGroup");
			cdiGroup = rowTestData.get("cdiGroup");
			onsite = rowTestData.get("Onsite");
			offsite = rowTestData.get("Offsite");
			testing_Inpatient = rowTestData.get("Testing_Inpatient");
			testing_Outpatient = rowTestData.get("Testing_Outpatient");
			speciality = rowTestData.get("Speciality");

			if (firstName != null)
			{
				addUser_webe.txt_FirstName.clear();
				Thread.sleep(500);
				addUser_webe.txt_FirstName.sendKeys(firstName);
			}
			if (middleInitial != null)
			{
				addUser_webe.txt_MiddleName.clear();
				Thread.sleep(500);
				addUser_webe.txt_MiddleName.sendKeys(middleInitial);
			}
			if (lastName != null)
			{
				addUser_webe.txt_LastName.clear();
				Thread.sleep(500);
				addUser_webe.txt_LastName.sendKeys(lastName);
			}
			if (emailId != null)
			{
				addUser_webe.txt_Email.clear();
				Thread.sleep(500);
				addUser_webe.txt_Email.sendKeys(emailId);
			}

			if (address != null)
			{
				addUser_webe.txt_Address.clear();
				Thread.sleep(500);
				addUser_webe.txt_Address.sendKeys(address);
			}
			if (city != null)
			{
				addUser_webe.txt_City.clear();
				Thread.sleep(500);
				addUser_webe.txt_City.sendKeys(city);
			}
			if (state != null)
			{
				addUser_webe.txt_State.clear();
				Thread.sleep(500);
				addUser_webe.txt_State.sendKeys(state);
			}
			if (zipCode != null)
			{
				addUser_webe.txt_ZipCode.clear();
				Thread.sleep(500);
				addUser_webe.txt_ZipCode.sendKeys(zipCode);
			}
			if (country != null)
			{
				addUser_webe.txt_Country.clear();
				Thread.sleep(500);
				addUser_webe.txt_Country.sendKeys(country);
			}

			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 100);
			Thread.sleep(500);
			if (phone != null)
			{
				addUser_webe.txt_PhoneNo.clear();
				Thread.sleep(500);
				addUser_webe.txt_PhoneNo.sendKeys(phone);
			}
			if (fax != null)
			{
				addUser_webe.txt_FaxNo.clear();
				Thread.sleep(500);
				addUser_webe.txt_FaxNo.sendKeys(fax);
			}
			Thread.sleep(500);
			if (coder != null)
			{

				addUser_webe.chk_Coder.click();
				Log4J.logp.info("Role is Coder");
			}
			Thread.sleep(500);
			if (reviewer != null)
			{
				addUser_webe.chk_Reviewer.click();
				Log4J.logp.info("Role is Reviewer");
			}
			Thread.sleep(500);
			if (cdi != null)
			{
				addUser_webe.chk_CDI.click();
				Log4J.logp.info("Role is CDI");
			}
			Thread.sleep(500);
			if (coderGroup != null)
			{

				addUser_webe.txt_CoderGroup.click();

				addUser_webe.txt_CoderGroup.clear();
				Thread.sleep(500);
				addUser_webe.txt_CoderGroup.sendKeys(coderGroup);

				addUser_webe.txt_CoderGroup.sendKeys(Keys.ENTER);
			}
			Thread.sleep(500);
			if (reviewerGroup != null)
			{

				addUser_webe.txt_ReviewerGroup.click();

				addUser_webe.txt_ReviewerGroup.clear();
				Thread.sleep(500);
				addUser_webe.txt_ReviewerGroup.sendKeys(reviewerGroup);

				addUser_webe.txt_ReviewerGroup.sendKeys(Keys.ENTER);
			}
			Thread.sleep(500);
			if (cdiGroup != null)
			{

				addUser_webe.txt_CDIGroup.click();

				addUser_webe.txt_CDIGroup.clear();
				Thread.sleep(500);
				addUser_webe.txt_CDIGroup.sendKeys(cdiGroup);

				addUser_webe.txt_CDIGroup.sendKeys(Keys.ENTER);
			}
			Thread.sleep(1000);
			Common_Lib.scroll_Page(addUser_webe.dragger_adduser, 250);

			if (onsite != null)
			{
				addUser_webe.chk_Onsite.click();
			}
			if (offsite != null)
			{
				addUser_webe.chk_Offsite.click();
			}
			if (testing_Inpatient != null)
			{
				addUser_webe.chk_Inpatient_Testing.click();
			}
			if (testing_Outpatient != null)
			{
				addUser_webe.chk_Outpatient_Testing.click();
			}
			if (speciality != null)
			{
				addUser_webe.txt_Speciality.clear();
				Thread.sleep(500);
				addUser_webe.txt_Speciality.sendKeys(speciality);
			}

			Log4J.logp.info("In add_User --all data inserted properly for rowId   " + rowId);
			Thread.sleep(1000);
			/*Log4J.logp.info("*****Before Chlicking on save button -validation Message*****");
			boolean st = Common_Lib.checkListOfElementsPresent(addUser_webe.err_Block, i);
			if (st == true)
			{

				List<WebElement> webelements = addUser_webe.err_Block;

				for (WebElement errorMsg : webelements)
				{

					if (errorMsg.isDisplayed() == false)
					{
						Log4J.logp.info("--Found proper value--");

					}
					else if (errorMsg.getText() != null)
					{
						Log4J.logp.info(errorMsg.getText() + " - Validation Message Found");
					}

				}
				Log4J.logp.info("****Present validation Message*****");
				i++;
			}*/
			Thread.sleep(2000);
			Administrator_Lib.save_User();
			Thread.sleep(200);
			Log4J.logp.info("After Clicking on Save Button");
			bvalidationmsg = Common_Lib.checkListOfElementsPresent(addUser_webe.err_Block, i);
			if (bvalidationmsg == true)
			{

				List<WebElement> webelements = addUser_webe.err_Block;

				for (WebElement errorMsg : webelements)
				{

					if (errorMsg.isDisplayed() == false)
					{
						Log4J.logp.info("--Found proper value--");

					}
					else if (errorMsg.getText() != null)
					{
						Log4J.logp.error(errorMsg.getText() + " **Validation Message Found***");
					}

				}
				Log4J.logp.info("****Present validation Message*****");
				i++;
			}
			Thread.sleep(3000);
			bSucess = Common_Lib.checkElementPresent(addUser_webe.success_MsgTxt);
			if (bSucess == true)
			{

				Log4J.logp.info(addUser_webe.success_MsgTxt.getText());
				Log4J.logp.info(firstName + " User Added Sucessfully.");
			}

			bError = Common_Lib.checkElementPresent(addUser_webe.warning_MsgTxt);
			if (bError == true)
			{

				Log4J.logp.info(addUser_webe.warning_MsgTxt.getText());
				Log4J.logp.info(firstName + " User Not Added");
			}
			Log4J.logp.info("In add_User --user added for rowId   " + rowId);
			Log4J.logp.info("-----------------Ended : add_User------------");
			return true;

		}

		catch (Exception e)
		{
			e.printStackTrace();
			Log4J.logp.error("In add_user --add user is failed for rowId=" + rowId);

			return false;
		}
		finally
		{
			if (rowTestData != null)
			{
				rowTestData = null;
			}
			/*	if (addUser_webe.btn_Cancel.isDisplayed())
				{
					addUser_webe.btn_Cancel.click();
					Log4J.logp.info("Pressed Cancel button on add user");
				}*/

			//Common_Lib.scroll_Page_Up(addUser_webe.dragger_adduser, 340);
		}

	}

	public static boolean save_User()
	{
		try
		{
			Log4J.logp.info("-----------------Started : save_User------------");
			Thread.sleep(500);
			addUser_webe.btn_Save.click();
			Log4J.logp.info("-----------------Ended : save_User------------");
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param rowId
	 * @return
	 */
	public static boolean add_Physician(String rowId)
	{
		String firstName = null;
		String lastName = null;
		String middleInitial = null;
		String emailId = null;
		String address = null;
		String city = null;
		String state = null;
		String zipCode = null;
		String country = null;
		String phone = null;
		String fax = null;
		String hospital = null;
		String location = null;
		String crendentials = null;
		String speciality = null;
		Map<String, String> rowTestData = null;
		int i = 0;
		boolean bvalidationmsg;
		boolean bSucess;
		try
		{
			Log4J.logp.info("-----------------Started : add_Physician------------");

			Log4J.logp.info("In add_Physician for rowId  " + rowId);
			driver = ExecutionSetup.getDriver();
			addPhysician_webe = AddPhysician_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			// JDBCSQLLite addPhysician_TestData =
			// Administrator_Lib.load_AddUser_Data();

			JDBCMySql addPhysician_TestData = new JDBCMySql();

			rowTestData = addPhysician_TestData.getRowbyID("td_addphysician", rowId);

			firstName = rowTestData.get("FirstName");
			lastName = rowTestData.get("LastName");
			middleInitial = rowTestData.get("MiddleName");
			emailId = rowTestData.get("EmailId");
			address = rowTestData.get("Address");
			city = rowTestData.get("City");
			state = rowTestData.get("State");
			zipCode = rowTestData.get("ZipCode");
			country = rowTestData.get("Country");
			phone = rowTestData.get("Phone");
			fax = rowTestData.get("Fax");
			hospital = rowTestData.get("Hospital");
			location = rowTestData.get("Location");
			crendentials = rowTestData.get("Crendentials");
			speciality = rowTestData.get("Speciality");

			if (firstName != null)
			{
				addPhysician_webe.txt_FirstName.clear();
				Thread.sleep(500);
				addPhysician_webe.txt_FirstName.sendKeys(firstName);
			}
			if (middleInitial != null)
			{
				addPhysician_webe.txt_MiddleName.clear();
				Thread.sleep(500);
				addPhysician_webe.txt_MiddleName.sendKeys(middleInitial);
			}
			if (lastName != null)
			{
				addPhysician_webe.txt_LastName.clear();
				Thread.sleep(500);
				addPhysician_webe.txt_LastName.sendKeys(lastName);
			}
			if (emailId != null)
			{
				addPhysician_webe.txt_Email.clear();
				Thread.sleep(500);
				addPhysician_webe.txt_Email.sendKeys(emailId);
			}
			if (hospital != null)
			{
				Select dropdown = new Select(addPhysician_webe.lst_Hospital);
				dropdown.selectByVisibleText(hospital);
			}
			if (location != null)
			{
				Select dropdown = new Select(addPhysician_webe.lst_Location);
				dropdown.selectByVisibleText(location);
			}

			if (address != null)
			{
				addPhysician_webe.txt_Address.clear();
				Thread.sleep(500);
				addPhysician_webe.txt_Address.sendKeys(address);
			}
			if (city != null)
			{
				addPhysician_webe.txt_City.clear();
				Thread.sleep(500);
				addPhysician_webe.txt_City.sendKeys(city);
			}
			Common_Lib.scroll_Page(addPhysician_webe.dragger, 250);
			Thread.sleep(500);
			if (state != null)
			{
				addPhysician_webe.txt_State.clear();
				Thread.sleep(500);
				addPhysician_webe.txt_State.sendKeys(state);
			}
			if (zipCode != null)
			{
				addPhysician_webe.txt_ZipCode.clear();
				Thread.sleep(500);
				addPhysician_webe.txt_ZipCode.sendKeys(zipCode);
			}
			if (country != null)
			{
				addPhysician_webe.txt_Country.clear();
				Thread.sleep(500);
				addPhysician_webe.txt_Country.sendKeys(country);
			}
			if (phone != null)
			{
				addPhysician_webe.txt_PhoneNo.clear();
				Thread.sleep(500);
				addPhysician_webe.txt_PhoneNo.sendKeys(phone);
			}
			if (fax != null)
			{
				addPhysician_webe.txt_FaxNo.clear();
				Thread.sleep(500);
				addPhysician_webe.txt_FaxNo.sendKeys(fax);
			}

			if (crendentials != null)
			{
				addPhysician_webe.txt_Credentials.clear();
				Thread.sleep(500);
				addPhysician_webe.txt_Credentials.sendKeys(crendentials);
			}

			if (speciality != null)
			{
				addPhysician_webe.txt_Speciality.clear();
				Thread.sleep(500);
				addPhysician_webe.txt_Speciality.sendKeys(speciality);
			}
			Log4J.logp.info("In add_Physician --all data inserted properly for rowId   " + rowId);
			Thread.sleep(500);
			bvalidationmsg = Common_Lib.checkListOfElementsPresent(addPhysician_webe.err_Block, i);
			if (bvalidationmsg == true)
			{

				List<WebElement> webelements = addPhysician_webe.err_Block;

				for (WebElement errorMsg : webelements)
				{

					if (errorMsg.isDisplayed() == false)
					{
						Log4J.logp.info("--Found proper value--");

					}
					else if (errorMsg.getText() != null)
					{
						Log4J.logp.info(errorMsg.getText() + " - Validation Message Found");
					}

				}
				Log4J.logp.info("Present validation Message");
				i++;
			}
			Thread.sleep(2000);
			Administrator_Lib.save_Physician();
			Thread.sleep(2000);
			bSucess = Common_Lib.checkElementPresent(addPhysician_webe.success_MsgTxt);
			if (bSucess == true)
			{

				Log4J.logp.info(addPhysician_webe.success_MsgTxt.getText());
				Log4J.logp.info("Physician sucessfully added.");
			}
			Log4J.logp.info("In add_Physician --added physician  for rowId   " + rowId);
			Log4J.logp.info("-----------------Ended : add_Physician------------");
			return true;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log4J.logp.error("In add_Physician --add physician is failed for rowId=" + rowId);
			return false;
		}
		finally
		{
			if (rowTestData != null)
			{
				rowTestData = null;
			}
		}

	}

	public static boolean save_Physician()
	{
		try
		{
			Log4J.logp.info("-----------------Started : save_Physician------------");

			Thread.sleep(500);
			addPhysician_webe.btn_Save.click();
			Log4J.logp.info("-----------------Ended : save_Physician------------");
			return true;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param rowId
	 * @return
	 */
	public static boolean add_Group(String rowId)
	{

		String name = null;
		String description = null;
		String role = null;
		String members = null;
		String[] choosemembers = null;
		int g = 0;
		boolean bSucess;
		boolean bError;

		Map<String, String> rowTestData = null;

		try
		{
			Log4J.logp.info("-----------------Started : add_Group------------");

			Log4J.logp.info("In add_group  for rowId   " + rowId);
			driver = ExecutionSetup.getDriver();
			addGroup_webe = AddGroup_WebE.getInstance(driver);

			// JDBCSQLLite
			// addGroup_TestData=Administrator_Lib.load_AddUser_Data();

			JDBCMySql addGroup_TestData = new JDBCMySql();

			rowTestData = addGroup_TestData.getRowbyID("td_addgroup", rowId);

			name = rowTestData.get("Name");
			description = rowTestData.get("Description");
			role = rowTestData.get("Role");
			members = rowTestData.get("Members");

			if (name != null)
			{
				//Log4J.logp.info("Name of Group");
				addGroup_webe.txt_Name.click();
				addGroup_webe.txt_Name.clear();
				Thread.sleep(500);
				addGroup_webe.txt_Name.sendKeys(name);
			}
			if (description != null)
			{
				addGroup_webe.txt_Description.clear();
				Thread.sleep(500);
				addGroup_webe.txt_Description.sendKeys(description);
			}

			if (role != null)
			{
				Select dropdown = new Select(addGroup_webe.lst_Role);
				dropdown.selectByVisibleText(role);
			}
			if (members != null)
			{
				//String st = addGroup_webe.choose_result.getText();
				Log4J.logp.info("Group Members" + members);
				choosemembers = members.split("\\$");
				if (choosemembers.length != 0)
				{
					Log4J.logp.info("Group Members 1");
					for (int i = 0; i < choosemembers.length; i++)
					{
						Log4J.logp.info("Group Members 2" + choosemembers[i]);
						Thread.sleep(500);
						addGroup_webe.txt_Members.clear();
						Thread.sleep(2000);
						addGroup_webe.txt_Members.sendKeys(choosemembers[i]);
						Thread.sleep(2000);
						addGroup_webe.txt_Members.sendKeys(Keys.ENTER);

					}

					/*if (choose_result != null)
					{
						Log4J.logp.info("member not found");

					}
					}

					/*addGroup_webe.txt_Members.click();
					addGroup_webe.txt_Members.clear();
					addGroup_webe.txt_Members.sendKeys(members);
					addGroup_webe.txt_Members.sendKeys(Keys.ENTER);*/
				}
			}

			Log4J.logp.info("In add_Group --all data inserted properly for rowId   " + rowId);
			Thread.sleep(500);
			Administrator_Lib.save_Group();
			Thread.sleep(500);
			Log4J.logp.info("validation Message");
			boolean st = Common_Lib.checkListOfElementsPresent(addGroup_webe.err_Block, g);
			if (st == true)
			{

				List<WebElement> webelements = addGroup_webe.err_Block;

				for (WebElement errorMsg : webelements)
				{

					if (errorMsg.isDisplayed() == false)
					{
						Log4J.logp.info("--Found proper value--");

					}
					else if (errorMsg.isDisplayed() == true)
					{
						Log4J.logp.error(errorMsg.getText() + " - Validation Message Found");
					}

				}
				Log4J.logp.info("Present validation Message");
				g++;
			}
			Thread.sleep(500);
			bSucess = Common_Lib.checkElementPresent(addGroup_webe.success_MsgTxt);
			if (bSucess == true)
			{

				Log4J.logp.info(addGroup_webe.success_MsgTxt.getText());
				Log4J.logp.info(name + " Group Added Sucessfully.");
			}

			bError = Common_Lib.checkElementPresent(addGroup_webe.warning_MsgTxt);
			if (bError == true)
			{

				Log4J.logp.info(addGroup_webe.warning_MsgTxt.getText());
				Log4J.logp.info(name + " - Group not Added ");
			}
			Thread.sleep(500);
			Log4J.logp.info("In add_Group --added group for rowId   " + rowId);
			Log4J.logp.info("-----------------Ended : add_Group------------");
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log4J.logp.error("add group is failed for rowId=" + rowId);
			return false;
		}
		finally
		{
			if (rowTestData != null)
			{
				rowTestData = null;
			}
		}

	}

	public static boolean save_Group()
	{
		try
		{
			Log4J.logp.info("-----------------Started : save_Group------------");

			wait = null;
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(addGroup_webe.btn_Save));
			addGroup_webe.btn_Save.click();
			Log4J.logp.info("-----------------Ended : save_Group------------");
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * public static JDBCSQLLite load_AddUser_Data() {
	 * 
	 * JDBCSQLLite load_AddUser=new JDBCSQLLite();
	 * load_AddUser.loadExcel("Resources/TestData/TestData.db"); return
	 * load_AddUser; }
	 */

	/**
	 * 
	 * To find user and perform operations
	 * 
	 * @return
	 */
	public static boolean find_user(String userName, String action) throws Exception
	{

		Log4J.logp.info("In Find User" + userName);
		driver = ExecutionSetup.getDriver();
		addUser_webe = AddUser_WebE.getInstance(driver);

		// int count = Common_Lib.get_Count(addUser_webe.get_Usercount);
		//	boolean bvalidationmsg;
		boolean bSucess;
		boolean bError;
		try
		{
			Log4J.logp.info("-----------------Started : find_user------------");

			WebElement table = addUser_webe.tbl_Users;

			int count = Integer.parseInt(addUser_webe.get_userCount.getText());

			mainloop: for (int i = 0; i <= (count / 15); i++)
			{

				List<WebElement> rows = table.findElements(By.tagName("tr"));

				for (WebElement row : rows)
				{

					WebElement firstColumn = row.findElement(By.cssSelector("td:nth-child(1)"));

					if (userName.equalsIgnoreCase(firstColumn.getText()))
					{

						if (action.equalsIgnoreCase("find"))
						{
							Log4J.logp.info("in find");
							Log4J.logp.info("User is present : " + firstColumn.getText());
							//To move cursor on the element found.
							Locatable hoverItem = (Locatable) firstColumn;
							Mouse mouse = ((HasInputDevices) driver).getMouse();
							mouse.mouseMove(hoverItem.getCoordinates());
							Thread.sleep(500);
							break mainloop;

						}
						else if (action.equalsIgnoreCase("open"))
						{
							Log4J.logp.info("in Open");
							firstColumn.click();
							Thread.sleep(3000);
							Log4J.logp.info("User is opened : " + firstColumn.getText());
							Thread.sleep(500);
							break mainloop;

						}
						else if (action.equalsIgnoreCase("delete"))
						{
							Log4J.logp.info("in delete");
							WebElement thirdColumn = row.findElement(By.cssSelector("td:nth-child(3) > a"));
							thirdColumn.click();
							Thread.sleep(1000);
							boolean deleteUser_Alert = Common_Lib.IsAlertPresent();
							Log4J.logp.info("Alert of delete user is :" + deleteUser_Alert);
							Thread.sleep(1000);
							bSucess = Common_Lib.checkElementPresent(addUser_webe.success_MsgTxt);
							bError = Common_Lib.checkElementPresent(addUser_webe.warning_MsgTxt);

							if (bSucess == true)
							{

								Log4J.logp.info(" User Deleted Sucessfully.");

							}
							else if (bError == true)
							{

								Log4J.logp.info(addUser_webe.warning_MsgTxt.getText());
								Log4J.logp.info(" User not Deleted ");

							}

							Thread.sleep(500);
							break mainloop;

						}

					}

					Common_Lib.scroll_Page(addUser_webe.main_dragger, 30);

				}

				Thread.sleep(2000);
				Common_Lib.scroll_Page_Up(addUser_webe.main_dragger, 150);

				addUser_webe.lnk_Next.click();
				Thread.sleep(2000);
			}
			Log4J.logp.info("-----------------Ended : find_user------------");
			return true;
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block

			e.printStackTrace();
			return false;
			// return false;
		}

	}

	//** this method is used to find group, to open group and delete the group 	* /

	/**
	 * @param groupName
	 * @param action
	 * @return
	 * @throws Exception
	 */
	public static boolean find_group(String groupName, String action) throws Exception
	{

		Log4J.logp.info("In Group : " + groupName);
		driver = ExecutionSetup.getDriver();
		addGroup_webe = AddGroup_WebE.getInstance(driver);
		common_webe = Comman_WebE.getInstance(driver);

		// int count = Common_Lib.get_Count(addUser_webe.get_Usercount);

		try
		{
			Log4J.logp.info("-----------------Started : find_group------------");
			WebElement table = addGroup_webe.tbl_Group;

			int count = Integer.parseInt(addGroup_webe.get_GroupCount.getText());

			mainloop: for (int i = 0; i <= (count / 15); i++)
			{

				List<WebElement> rows = table.findElements(By.tagName("tr"));

				for (WebElement row : rows)
				{

					WebElement firstColumn = row.findElement(By.cssSelector("td:nth-child(1)"));

					if (groupName.equalsIgnoreCase(firstColumn.getText()))
					{

						if (action.equalsIgnoreCase("find"))
						{
							Log4J.logp.info("group is present : " + firstColumn.getText());
							//To move cursor on the element found.
							Locatable hoverItem = (Locatable) firstColumn;
							Mouse mouse = ((HasInputDevices) driver).getMouse();
							mouse.mouseMove(hoverItem.getCoordinates());
							break mainloop;

						}
						else if (action.equalsIgnoreCase("open"))
						{
							firstColumn.click();
							Log4J.logp.info("group is opened : " + firstColumn.getText());

							break mainloop;

						}
						else if (action.equalsIgnoreCase("delete"))
						{
							WebElement thirdColumn = row.findElement(By.cssSelector("td:nth-child(3) > a"));
							thirdColumn.click();
							//driver.findElement(By.linkText("Delete")).click();
							boolean deleteUser_Alert = Common_Lib.IsAlertPresent();
							Log4J.logp.info("Alert of delete group is :" + deleteUser_Alert);
							Log4J.logp.info("group is Deleted : " + firstColumn.getText());
							break mainloop;

						}
					}
					Common_Lib.scroll_Page(common_webe.dragger, 30);
				}
				Common_Lib.scroll_Page_Up(common_webe.dragger, 150);
				Thread.sleep(500);
				addGroup_webe.lnk_Next.click();
				Thread.sleep(2000);
			}
			Log4J.logp.info("-----------------Ended : find_group------------");
			return true;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}

	}

	/** This method is for find physician,to open physician and to delete physician */
	public static boolean find_physician(String physicianName, String action) throws Exception
	{

		Log4J.logp.info("In Find Physician : " + physicianName);
		driver = ExecutionSetup.getDriver();
		addPhysician_webe = AddPhysician_WebE.getInstance(driver);
		common_webe = Comman_WebE.getInstance(driver);

		try
		{
			Log4J.logp.info("-----------------Started : find_physician------------");
			WebElement table = addPhysician_webe.tbl_Physician;

			int count = Integer.parseInt(addPhysician_webe.get_PhysicianCount.getText());

			mainloop: for (int i = 0; i <= (count / 15); i++)
			{

				List<WebElement> rows = table.findElements(By.tagName("tr"));

				for (WebElement row : rows)
				{

					WebElement firstColumn = row.findElement(By.cssSelector("td:nth-child(1)"));

					if (physicianName.equalsIgnoreCase(firstColumn.getText()))
					{

						if (action.equalsIgnoreCase("find"))
						{
							Log4J.logp.info("Physician is present : " + firstColumn.getText());
							//To move cursor on the element found.
							Locatable hoverItem = (Locatable) firstColumn;
							Mouse mouse = ((HasInputDevices) driver).getMouse();
							mouse.mouseMove(hoverItem.getCoordinates());
							break mainloop;

						}
						else if (action.equalsIgnoreCase("open"))
						{
							firstColumn.click();
							Log4J.logp.info("Physician is opened : " + firstColumn.getText());

							break mainloop;

						}
						else if (action.equalsIgnoreCase("delete"))
						{
							WebElement thirdColumn = row.findElement(By.cssSelector("td:nth-child(3) > a"));
							thirdColumn.click();
							boolean deleteUser_Alert = Common_Lib.IsAlertPresent();
							Log4J.logp.info("Alert of delete physician is :" + deleteUser_Alert);
							Log4J.logp.info("Physician is Deleted : " + firstColumn.getText());
							break mainloop;

						}
					}
					Common_Lib.scroll_Page(common_webe.dragger, 30);
					Thread.sleep(500);
				}
				Common_Lib.scroll_Page_Up(common_webe.dragger, 150);
				addPhysician_webe.lnk_Next.click();
				Thread.sleep(2000);
			}
			Log4J.logp.info("-----------------Ended : find_physician------------");
			return true;

		}
		catch (Exception e)
		{
			Log4J.logp.info("Problem Found in find_Physician");
			e.printStackTrace();
			return false;
		}

	}
}