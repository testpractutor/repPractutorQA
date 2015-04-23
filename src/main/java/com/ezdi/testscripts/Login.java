/*package com.ezdi.testscripts;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SubjectTerm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.DriverTestNG;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.library.Administrator_Lib;
import com.ezdi.library.Common_Lib;
import com.ezdi.library.Login_Lib;
import com.ezdi.webelements.AddUser_WebE;
import com.ezdi.webelements.AdminHome_WebE;
import com.ezdi.webelements.GroupinPnl_WebE;
import com.ezdi.webelements.LandingP_WebE;
import com.ezdi.webelements.Login_WebE;
import com.ezdi.webelements.MessageCenter_WebE;
import com.ezdi.webelements.Reports_WebE;

public class Login
{

	public static WebDriver				driver;
	public static Login_WebE			login_webe;
	public static Login_Lib				login_lib;
	public static LandingP_WebE			landingp_webe;
	public static AdminHome_WebE		adminHome_webe;
	public static AddUser_WebE			addUser_webe;
	public static MessageCenter_WebE	messagecenter_webe;
	public static Reports_WebE			reports_webe;
	public static GroupinPnl_WebE		groupingpnl_webe;

	@BeforeClass
	public static void loginBeforeTest()
	{
		String username;
		String password;
		Map<String, String> rowTestData = null;
		try
		{
			driver = ExecutionSetup.getDriver();
			Log4J.logp.info("in login before test" + driver);
			login_webe = Login_WebE.getInstance(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			adminHome_webe = AdminHome_WebE.getInstance(driver);
			addUser_webe = AddUser_WebE.getInstance(driver);
			messagecenter_webe = MessageCenter_WebE.getInstance(driver);
			reports_webe = Reports_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);

			
						Login_Lib.logOut_App();
						JDBCMySql getLoginData = new JDBCMySql();

						rowTestData = getLoginData.getRowbyID("td_logindata", "nc003");

						username = rowTestData.get("userName");
						password = rowTestData.get("passWord");

						Login_Lib.logIn_App(username, password);
			Login_Lib.login("ag007");
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	*//**
	 * This method is to verify successful and unsuccessful login
	 * 
	 * @author nchourasiya
	 *
	 * @since 17/10/2014
	 * *//*

	@Test(description = "ezCAC_MVP_Reg-3:To verify successful and unsuccessful login activity ", priority = 1)
	public static void verifySuccessfulUnsucessfulLogin()
	{
		boolean bstatus = true;
		boolean finalStatus = true;

		try
		{

			Log4J.logp.info("**************************** Started - verifySuccessfulUnsucessfulLogin **********************");

			// ezCAC_MVP_Reg-3:Verify that user can able to login successfully with valid email id and password.

			Login_Lib.logOut_App();

			for (int i = 0; i < 10; i++)
			{

				switch (i)
				{

					case 0:
						bstatus = Login_Lib.checkLoginSucess("nc003", "check_login", "");

						if (bstatus == false)
						{
							finalStatus = false;
						}
						break;
					case 1:
						bstatus = Login_Lib.checkLoginSucess("NC004", "", "checkError");

						if (bstatus == false)
						{
							finalStatus = false;
						}
						break;
					case 2:
						bstatus = Login_Lib.checkLoginSucess("NC005", "", "checkError");

						if (bstatus == false)
						{
							finalStatus = false;
						}
						break;

					case 3:
						bstatus = Login_Lib.checkLoginSucess("NC006", "", "checkError");

						if (bstatus == false)
						{
							finalStatus = false;
						}
						break;

					case 4:
						bstatus = Login_Lib.checkLoginSucess("NC007", "", "checkError");

						if (bstatus == false)
						{
							finalStatus = false;
						}
						break;
					case 5:
						bstatus = Login_Lib.checkLoginSucess("NC008", "", "checkError");

						if (bstatus == false)
						{
							finalStatus = false;
						}
						break;
					case 6:
						bstatus = Login_Lib.checkLoginSucess("NC009", "", "checkError");

						if (bstatus == false)
						{
							finalStatus = false;
						}
						break;
					case 7:
						bstatus = Login_Lib.checkLoginSucess("NC010", "", "checkError");

						if (bstatus == false)
						{
							finalStatus = false;
						}
						break;
					case 8:
						bstatus = Login_Lib.checkLoginSucess("NC012", "check_login", "");

						if (bstatus == false)
						{
							finalStatus = false;
						}
						break;
					case 9:
						bstatus = Login_Lib.checkLoginSucess("NC013", "", "checkError");

						if (bstatus == false)
						{
							finalStatus = false;
						}

				}

			}

			Log4J.logp.info("********************************** Ending - verifySuccessfulUnsucessfulLogin ***********************************");
			Assert.assertTrue(finalStatus, " verifySuccessfulUnsucessfulLogin");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In -  verifySuccessfulUnsucessfulLogin");
			e.printStackTrace();
			Assert.assertTrue(false, " verifySuccessfulUnsucessfulLogin is failed");
		}

		finally
		{

			driver.navigate().refresh();

		}

	}

	*//**
	 * This method is to check application behavior with browser's Back/Forward button
	 * 
	 * @author nchourasiya
	 * @since 21/10/2014
	 * *//*

	@Test(description = "ezCAC_MVP_Reg-17:To verify application behavior with browser's Back/Forward button with login/logout", priority = 2)
	public static void verfiyApplicationBehaveOnBackOrFwdButton()
	{
		boolean bstatus = false;
		String SPreCurrentUrl = null;
		String SPostUrl = null;
		try
		{

			Log4J.logp.info("**************************** Started - verfiyApplicationBehaveOnBackOrFwdButton **********************");
			//Login_Lib.logOut_App();
			Login_Lib.login("nc003");
			Login_Lib.logOut_App();
			//	driver.navigate().refresh();
			SPreCurrentUrl = driver.getCurrentUrl();
			//ezCAC_MVP_Reg-17:Click on browser's back button after logout.
			Thread.sleep(3000);
			driver.navigate().back();
			SPostUrl = driver.getCurrentUrl();
			if (SPreCurrentUrl.equalsIgnoreCase(SPostUrl))
			{

				Log4J.logp.info("On Clicking on back button -- " + driver.getTitle().toString());
				Log4J.logp.info("Click on browser's back button after logout--Test Passed");
				Assert.assertTrue(true, "back button is enable - Test passed");
			}
			else
			{

				Log4J.logp.error("Some thing went wrong - Test Fail");
				Assert.assertTrue(false, "Some thing went wrong - Test Fail");
			}
			Thread.sleep(1000);
			driver.navigate().refresh();

			//ezCAC_MVP_Reg-18:Click on browser's forward button after logout.

			Login_Lib.login("nc003");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(3000);
			Log4J.logp.info(driver.getCurrentUrl());

			SPreCurrentUrl = driver.getCurrentUrl();

			Thread.sleep(2000);
			driver.navigate().forward();
			SPostUrl = driver.getCurrentUrl();

			if (SPreCurrentUrl.equalsIgnoreCase(SPostUrl))
			{

				Log4J.logp.info("It Should Remain on Login page -- " + driver.getTitle().toString());
				Log4J.logp.info("Forward button is disable - Test passed");
				Assert.assertTrue(true, "Forward button is disable - Test passed");
			}
			else
			{

				Log4J.logp.error("Forward button is enable- Test fail");
				Assert.assertTrue(false, "Forward is enable");
			}

			//ezCAC_MVP_Reg-19:Already logged in user click on browser's forward button

			Login_Lib.login("nc003");
			Thread.sleep(2000);
			Log4J.logp.info(driver.getCurrentUrl());

			SPreCurrentUrl = driver.getCurrentUrl();
			Thread.sleep(2000);
			driver.navigate().forward();
			Thread.sleep(1000);
			SPostUrl = driver.getCurrentUrl();

			if (SPreCurrentUrl.equalsIgnoreCase(SPostUrl))
			{

				Log4J.logp.info("It Should Remain on landing page -- " + driver.getTitle().toString());
				Log4J.logp.info("Already logged in user click on browser's forward button - Forward buttton is disable - Test passed");
				Assert.assertTrue(true, "Forward button is disable");
			}
			else
			{

				Log4J.logp.error("Forward button is enable - Test fail");
				Assert.assertTrue(false, "Forward button is enable");
			}
			Thread.sleep(1000);
			Login_Lib.logOut_App();
			Thread.sleep(3000);

			//ezCAC_MVP_Reg-26:Already logged in user click on browser's back button.

			Login_Lib.login("nc003");
			Log4J.logp.info(driver.getCurrentUrl());

			SPreCurrentUrl = driver.getCurrentUrl();

			driver.navigate().back();
			SPostUrl = driver.getCurrentUrl();
			Log4J.logp.info("After Clicking on browser back button" + SPostUrl);
			if (SPreCurrentUrl.equalsIgnoreCase(SPostUrl))
			{

				Log4J.logp.info("It Should Remain on landing page (ezCAC Worklist) -- " + driver.getTitle().toString());
				Log4J.logp.info("Already logged in user click on browser's back button - back buttton is disable - Test passed");
				Assert.assertTrue(false, "back button is disable");
			}
			else
			{

				Log4J.logp.error("It Should Remain on landing page (ezCAC Worklist) " + driver.getTitle().toString());
				Log4J.logp.error("back button is enable- Test fail");
				Assert.assertTrue(true, "back button is enable");
			}
			driver.navigate().forward();

			bstatus = Login_Lib.logOut_App();
			Assert.assertTrue(bstatus, " verfiyApplicationBehaveOnBackOrFwdButton");
			Log4J.logp.info("********************************** Ending - verfiyApplicationBehaveOnBackOrFwdButton ***********************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In -  verfiyApplicationBehaveOnBackOrFwdButton");
			e.printStackTrace();
			Assert.assertTrue(false, " verfiyApplicationBehaveOnBackOrFwdButton");
		}
		finally
		{
			driver.navigate().refresh();

		}

	}

	*//**
	 * This method is to check that user can not change password with invalid password
	 * 
	 * @author nchourasiya
	 *
	 * @since 21/10/2014
	 * 
	 * *//*

	@Test(description = "ezCAC_MVP_Reg-2988:To check user can not able to change password with enters passowrd in Invalid format", priority = 3)
	public static void userCanChangePwd()
	{
		boolean bstatus = false;
		try
		{

			Log4J.logp.info("**************************** Started - userCanChangePwd**********************");
			Login_Lib.login("nc014");
			Thread.sleep(1000);
			landingp_webe.lbl_UserName.click();
			landingp_webe.lnk_Profile.click();
			login_webe.lnk_Edit_Password.click();
			Thread.sleep(2000);
			Login_Lib.profile_EditPassword("nc001");
			Thread.sleep(2000);

			bstatus = Login_Lib.logOut_App();
			Assert.assertTrue(bstatus, " userCanChangePwd");
			Log4J.logp.info("********************************** Ending - userCanChangePwd ***********************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In -  userCanChangePwd");
			e.printStackTrace();
			Assert.assertTrue(false, " userCanChangePwd");
		}

		finally
		{
			driver.navigate().refresh();

		}

	}

	*//**
	 * This menthod is to check that User Should be Locked For 10 min after 3 un sucessful login
	 * 
	 * @author nchourasiya
	 * 
	 * @since 22/10/2014
	 * 
	 * *//*

	@Test(description = "ezCAC_MVP_Reg-27:To verify more than 3 unsuccessful login attempts", priority = 4)
	public static void threeUnsucessfulLoginAttempts()
	{
		boolean bstatus = false;
		boolean bErrorMsgAttempt;
		try
		{

			Log4J.logp.info("**************************** Started - threeUnsucessfulLoginAttempts**********************");
			//driver.navigate().refresh();
			//Login_Lib.logOut_App();

			// First Attempt with Wrong Password
			Login_Lib.login("nc015");
			Thread.sleep(1000);
			bErrorMsgAttempt = Common_Lib.checkElementPresent(login_webe.txt_ErrMsg);
			if (bErrorMsgAttempt == true)
			{

				Log4J.logp.info("Login is UnSuccessfully First Attempt-- Script Passed");
				Log4J.logp.info(login_webe.txt_ErrMsg.getText() + " -- Validation Message ");
				Assert.assertTrue(true, "Login is UnSuccessfully.");
			}
			else
			{

				Log4J.logp.error(" logged in some thing went wrong.");
				Assert.assertTrue(false, "User Logged In.");
			}
			Thread.sleep(2000);

			// Second Attempt with Wrong Password
			Login_Lib.login("nc016");
			Thread.sleep(1000);
			bErrorMsgAttempt = Common_Lib.checkElementPresent(login_webe.txt_ErrMsg);
			if (bErrorMsgAttempt == true)
			{

				Log4J.logp.info("Login is UnSuccessfully Second Attempt-- Script Passed");
				Log4J.logp.info(login_webe.txt_ErrMsg.getText() + " -- Validation Message ");
				Assert.assertTrue(true, "Login is UnSuccessfully.");
			}
			else
			{

				Log4J.logp.error(" logged in some thing went wrong.");
				Assert.assertTrue(false, "User Logged In.");
			}
			Thread.sleep(2000);

			// Third Attempt with Wrong Password
			Login_Lib.login("nc017");
			Thread.sleep(1000);

			bErrorMsgAttempt = Common_Lib.checkElementPresent(login_webe.txt_ErrMsg);

			if (bErrorMsgAttempt == true)
			{

				Log4J.logp.info("Login is UnSuccessfully user Third Attempt User Should be Locked For 10 min -- Script Passed");
				Log4J.logp.info(login_webe.txt_ErrMsg.getText() + " -- Validation Message ");
				Assert.assertTrue(true, "Login is UnSuccessfully.");
			}
			else
			{

				Log4J.logp.error(" logged in some thing went wrong.");
				Assert.assertTrue(false, "User Logged In.");
			}
			Thread.sleep(3000);

			// Fourth Attempt with Correct Password
			Login_Lib.login("nc018");
			Thread.sleep(1000);

			bErrorMsgAttempt = Common_Lib.checkElementPresent(login_webe.txt_ErrMsg);

			if (bErrorMsgAttempt == true)
			{

				Log4J.logp.info("Login is UnSuccessfully Fourth Attempt User Should not be able to Login for 10 min -- Script Passed");
				Log4J.logp.info(login_webe.txt_ErrMsg.getText() + " -- Validation Message ");
				Assert.assertTrue(true, "Login is UnSuccessfully.");
			}
			else
			{

				Log4J.logp.error(" logged in some thing went wrong.");
				Assert.assertTrue(false, "User Logged In.");
			}
			Thread.sleep(3000);
			bstatus = true;

			Log4J.logp.info("********************************** Ending - threeUnsucessfulLoginAttempts ***********************************");
			Assert.assertTrue(bstatus, " threeUnsucessfulLoginAttempts");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In -  threeUnsucessfulLoginAttempts");
			e.printStackTrace();
			Assert.assertTrue(false, " threeUnsucessfulLoginAttempts");
		}

		finally
		{
			driver.navigate().refresh();

		}

	}

	*//**
	 * This method is to check behaviour when user enter invalid email id in forgot password
	 * 
	 * @author nchourasiya
	 * 
	 * @since 22/10/2014
	 * 
	 * *//*

	@Test(description = "ezCAC_MVP_Reg-30:User try to enter an invalid email id into Forgot Password", priority = 5)
	public static void invalidEmailIdInForgotPassword()
	{
		boolean bstatus = false;
		boolean bErrorMsg;
		try
		{

			Log4J.logp.info("**************************** Started - invalidEmailIdInForgotPassword**********************");

			//Login_Lib.logOut_App();
			Thread.sleep(1000);
			//driver.navigate().refresh();
			login_webe.lnk_ForgotPwd.click();

			Thread.sleep(1000);
			Login_Lib.forgot_Password("nc001");

			bErrorMsg = Common_Lib.checkElementPresent(login_webe.txt_ErrMsg);
			if (bErrorMsg == true)
			{

				Log4J.logp.info("Forgot Password is UnSuccessfully -- Script Passed");
				Log4J.logp.info(login_webe.txt_ErrMsg.getText() + " -- Validation Message ");
				Assert.assertTrue(true, "Forgot Password is UnSuccessfully.");
			}
			else
			{

				Log4J.logp.error("some thing went wrong.");
				Assert.assertTrue(false, "Forgot Password is Successfully.");
			}

			bstatus = true;

			Log4J.logp.info("********************************** Ending - invalidEmailIdInForgotPassword***********************************");
			Assert.assertTrue(bstatus, " invalidEmailIdInForgotPassword");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - invalidEmailIdInForgotPassword");
			e.printStackTrace();
			Assert.assertTrue(false, " invalidEmailIdInForgotPassword");
		}

		finally
		{
			driver.navigate().refresh();

		}

	}

	*//**
	 * This method is to check behaviour when user enter unregistered valid email id in forgot password
	 * 
	 * @author nchourasiya
	 *
	 * @since 22/10/2014
	 * 
	 * *//*
	@Test(description = "ezCAC_MVP_Reg-31:User enters an unregistered valid email id into forgot password", priority = 6)
	public static void unregisteredEmailIdInForgotPassword()
	{
		boolean bErrorMsg;
		boolean bstatus = false;
		try
		{

			Log4J.logp.info("**************************** Started - unregisteredEmailIdInForgotPassword**********************");

			//Login_Lib.logOut_App();
			Thread.sleep(1000);
			//driver.navigate().refresh();
			login_webe.lnk_ForgotPwd.click();

			Thread.sleep(1000);
			Login_Lib.forgot_Password("nc002");

			bErrorMsg = Common_Lib.checkElementPresent(login_webe.txt_ErrMsg);
			if (bErrorMsg == true)
			{

				Log4J.logp.info("Forgot Password is UnSuccessfully -- Script Passed");
				Log4J.logp.info(login_webe.txt_ErrMsg.getText() + " -- Validation Message ");
				Assert.assertTrue(true, "Forgot Password is UnSuccessfully.");
			}
			else
			{

				Log4J.logp.error("some thing went wrong.");
				Assert.assertTrue(false, "Forgot Password is Successfully.");
			}

			bstatus = true;

			Log4J.logp.info("********************************** Ending - unregisteredEmailIdInForgotPassword***********************************");
			Assert.assertTrue(bstatus, " unregisteredEmailIdInForgotPassword");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - unregisteredEmailIdInForgotPassword");
			e.printStackTrace();
			Assert.assertTrue(false, " unregisteredEmailIdInForgotPassword");
		}

		finally
		{
			driver.navigate().refresh();

		}

	}

	*//**
	 * 
	 * This method is to verify that deleted user can not able to access the system
	 * 
	 * @author nchourasiya
	 *
	 *
	 * @since 22/10/2014
	 * 
	 * *//*
	@Test(description = "ezCAC_MVP_Reg-42:To verify deleted user can not able to access the system", priority = 7)
	public static void deletedUserCanNotAccessSystem()
	{
		boolean bstatus = false;
		boolean bErrorMsg;
		try
		{

			Log4J.logp.info("**************************** Started - deletedUserCanNotAccessSystem**********************");
			//UnComment these two line for single execution of this code
			//driver.navigate().refresh();
			//Login_Lib.logOut_App();   
			Login_Lib.login("nc003");
			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			adminHome_webe.lnk_AddNew_user.click();
			String user = "Coder Test1";
			Thread.sleep(1000);
			Administrator_Lib.find_user(user, "delete");
			Thread.sleep(3000);
			Login_Lib.logOut_App();
			Thread.sleep(1000);
			driver.navigate().refresh();
			Thread.sleep(1000);

			Login_Lib.login("nc019");
			bErrorMsg = Common_Lib.checkElementPresent(login_webe.txt_ErrMsg);
			if (bErrorMsg == true)
			{

				Log4J.logp.info("Login is UnSuccessfully with deleted User -- Script Passed");
				Log4J.logp.info(login_webe.txt_ErrMsg.getText() + " -- Validation Message ");
				Assert.assertTrue(true, "Login is UnSuccessfully.");
			}
			else
			{

				Log4J.logp.error("Login is Successfully with deleted User.");
				Assert.assertTrue(false, "Login is Successfully with deleted User.");
			}

			bstatus = true;

			Log4J.logp.info("********************************** Ending - deletedUserCanNotAccessSystem***********************************");
			Assert.assertTrue(bstatus, " deletedUserCanNotAccessSystem");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - deletedUserCanNotAccessSystem");
			e.printStackTrace();
			Assert.assertTrue(false, " deletedUserCanNotAccessSystem");
		}
		finally
		{
			driver.navigate().refresh();

		}

	}

	*//**
	 * This method is to verify login and logout activity for newly created user
	 * 
	 * @author nchourasiya
	 * @since 11/12/2014
	 * *//*

	@Test(description = "ezCAC_MVP_Reg-1:Verify that new user can able to login & logout successfully", priority = 8)
	public static void login_LogoutForNewUser()
	{
		int role = 0;
		Message[] messages = null;
		boolean isMailFound = false;
		Message mailFromezDI = null;
		String PassWordezDI;

		String pwd[];
		String Password;
		boolean bRole;
		try
		{
			Log4J.logp.info("****************** Started : login_LogoutForNewUser ***************** ");

			//Login_Lib.logOut_App();  // Uncomment it for single execution.
			Login_Lib.login("nc003");

			landingp_webe.lnk_All.click();
			landingp_webe.imgAdmin_Setting.click();
			Thread.sleep(1000);
			adminHome_webe.lnk_AddNew_user.click();
			Thread.sleep(2000);
			addUser_webe.btn_AddUser.click();
			Thread.sleep(2000);
			Administrator_Lib.add_User("NC033");
			Thread.sleep(2000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Log4J.logp.info("Fetching Password From Email..");
			java.util.Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Thread.sleep(2000);
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", "nupur.automation.ezdi@gmail.com", "Cac@ezdi2014");
			Thread.sleep(2000);
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_WRITE);
			Log4J.logp.info("Total msg in Mail :" + folder.getMessageCount());
			Log4J.logp.info("unRead Messages in Mail :" + folder.getUnreadMessageCount());

			//Search for mail from ezDI
			for (int i = 0; i < 5; i++)
			{
				messages = folder.search(new SubjectTerm("Welcome to ezCac"), folder.getMessages());
				//Wait for 10 seconds
				if (messages.length == 0)
				{
					Thread.sleep(10000);
				}
			}

			//Search for unread mail from ezDI
			//This is to avoid using the mail for which Registration is already done

			for (Message mail : messages)
			{
				if (!mail.isSet(Flags.Flag.SEEN))
				{
					mailFromezDI = mail;
					Log4J.logp.info("Message Count is: " + mailFromezDI.getMessageNumber());
					isMailFound = true;
				}
			}

			//Test fails if no unread mail was found from ezDI
			if (!isMailFound)
			{
				throw new Exception("Could not find new mail from ezDI");

				//Read the content of mail                 
			}
			else
			{
				String line;
				StringBuffer buffer = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(mailFromezDI.getInputStream()));
				while ((line = reader.readLine()) != null)
				{
					buffer.append(line);
				}
				//Log4J.logp.info(buffer);

				PassWordezDI = buffer.toString().split("&amp;gt;")[0].split("Your password is")[1];

				pwd = PassWordezDI.split("\\<br />");
				Password = pwd[0].trim();
				Password = Password.substring(0, Password.length() - 1);
				Log4J.logp.info("PassWord Form ezDI :" + Password);

				Login_Lib.logIn_App("nupur.automation.ezdi@gmail.com", Password);

				Login_Lib.reset_Password("NC027", "nupur.automation.ezdi@gmail.com");
			}
			Thread.sleep(1000);
			bRole = Common_Lib.checkListOfElementsPresent(landingp_webe.lst_UserRole, role);
			if (bRole == true)
			{
				for (WebElement Roles : landingp_webe.lst_UserRole)
				{
					Log4J.logp.info("Roles Found with User :" + Roles.getText());
				}

				role++;
			}
			Thread.sleep(1000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(1000);
			landingp_webe.lnk_Reports.click();
			Thread.sleep(1000);
			if (landingp_webe.lnk_CaseAndReport.isDisplayed() == true)
			{
				Log4J.logp.info("Cases and Report Link Found in Users landing page :" + landingp_webe.lnk_CaseAndReport.getText());
			}

			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(1000);
			if (messagecenter_webe.btn_NewMessage.isDisplayed() == true)
			{
				Thread.sleep(1000);
				Log4J.logp.info("User Clicked on Message Center icon and sucessfully redirected to Message Centre Page.");
			}
			Thread.sleep(1000);
			landingp_webe.lbl_UserName.click();
			Thread.sleep(1000);
			landingp_webe.lnk_Profile.click();
			if (login_webe.lnk_Edit_Password.isDisplayed() == true)
			{
				Log4J.logp.info("User Clicked on Profile link and profile page open Sucessfully");
			}
			Thread.sleep(1000);
			Login_Lib.logOut_App();

			Log4J.logp.info("****************** Ended : login_LogoutForNewUser ***************** ");

		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - login_LogoutForNewUser");
			e.printStackTrace();
			Assert.assertTrue(false, " login_LogoutForNewUser");
		}

		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();
			}
			else
			{

				driver.navigate().refresh();

			}
		}
	}

	*//**
	 * This method is check that user can able to see his/her own profile
	 * 
	 * @author agupta
	 * @since 22/12/2014
	 *//*
	@Test(description = "ezCAC_MVP_Reg-3048 - User should be able to see his/her own profile" + "ezCAC_MVP_Reg-3050 - To check Accessabliity of PRofile from Different Pages" + "ezCAC_MVP_Reg-3052 - User should be able to make changes in her/his own profile", priority = 9)
	public static void viewProfile()
	{
		String strname, strtemp1, strname1;
		boolean bstatus = true;
		try
		{
			Log4J.logp.info("***************** Started :: viewProfile ********************");
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("ag007");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			landingp_webe.lbl_UserName.click();
			landingp_webe.lnk_Profile.click();
			strname = login_webe.btn_Salutation.getText();
			strtemp1 = strname.split("Hi, ")[1];
			Log4J.logp.info(strtemp1);
			strname1 = login_webe.lbl_Name.getText();
			if (strtemp1.equalsIgnoreCase(strname1))
			{
				Log4J.logp.info("Name has been displayed in profile page" + "With Roles :: " + login_webe.lbl_Roles.getText());
			}
			else
			{
				bstatus = false;
			}
			if (DriverTestNG.username.equalsIgnoreCase(login_webe.lbl_EmailId.getText()))
			{
				Log4J.logp.info("EmailId has been displayed in landing page");
			}
			else
			{
				bstatus = false;
			}
			if (login_webe.lbl_Address.isDisplayed() && login_webe.lbl_Phone.isDisplayed() && login_webe.lbl_Fax.isDisplayed())
			{
				Log4J.logp.info("Address,Phone and Fax label has been displayed in profile page");
			}
			else
			{
				bstatus = false;
			}
			if (login_webe.ico_img.isDisplayed() && login_webe.lnk_EditPicture.isDisplayed() && login_webe.btn_Edit.isDisplayed())
			{
				Log4J.logp.info("Image thumbnail, Edit Picture link and EDIt button has been displayed on profile page");
			}
			else
			{
				bstatus = false;
			}

			//To check Accessibility of profile page from working screen
			landingp_webe.lnk_Cases.click();
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("fm003");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 15);
			if (Common_Lib.checkElementPresent(landingp_webe.lnk_Profile) == false)
			{
				Log4J.logp.info("Profile link has not been displayed on working screen");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			//User should be able to make changes in her/his own profile
			landingp_webe.lbl_UserName.click();
			landingp_webe.lnk_Profile.click();
			login_webe.btn_Edit.click();
			Thread.sleep(2000);
			if (login_webe.txt_FirstName.isEnabled() == false && login_webe.txt_MiddileName.isEnabled() == false && login_webe.txt_LastName.isEnabled() == false)
			{
				Log4J.logp.info("First Name, Middle Name and Last Name has been displayed with disbaled mode in Edit Profile page ");
			}
			else
			{
				bstatus = false;
			}
			Login_Lib.editProfile("ag001");
			login_webe.lnk_Cancel.click();
			Thread.sleep(2000);
			Log4J.logp.info("After click on Cancel button information has not been saved");
			login_webe.btn_Edit.click();
			Login_Lib.editProfile("ag001");
			login_webe.btn_Save.click();
			Thread.sleep(2000);
			if (login_webe.lbl_add != null && login_webe.lbl_phno != null && login_webe.lbl_fa != null)
			{
				Log4J.logp.info("Information has been saved");
			}
			else
			{
				bstatus = false;
			}
			Log4J.logp.info("***************** Ended :: viewProfile ********************");
			Assert.assertTrue(bstatus, "viewProfile Complated");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: viewProfile");
			e.printStackTrace();
			Assert.assertTrue(false, "Problem found in :: viewProfile");
		}
		finally
		{
			Login_Lib.logOut_App();
		}
	}

	*//**
	 * This method is check that user can able to change the password from profile page
	 * 
	 * @author agupta
	 * @since 22/12/2014
	 *//*
	@Test(description = "ezCAC_MVP_Reg-24:User can able to change password", priority = 10)
	public static void changePassword()
	{
		try
		{
			Log4J.logp.info("************* Started :: changePassword *******************");
			Thread.sleep(2000);
			Login_Lib.login("ag006");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			landingp_webe.lbl_UserName.click();
			landingp_webe.lnk_Profile.click();
			login_webe.lnk_Edit_Password.click();
			Thread.sleep(2000);
			Login_Lib.profile_EditPassword("ag002");
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("ag006");
			if (login_webe.txt_ErrMsg.isDisplayed())
			{
				Log4J.logp.info(" User can not able to login with old password");
			}
			else
			{
				Assert.assertTrue(false, "Login successfully");
			}
			Login_Lib.login("ag013");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			if (landingp_webe.lbl_UserName.isDisplayed())
			{
				Log4J.logp.info("Landing PAge is displayed after login with new password");
			}
			else
			{
				Assert.assertTrue(false, "Login Unsucessfully");
			}

			Log4J.logp.info("************* Ended :: changePassword *******************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: changePassword");
			Assert.assertTrue(false, "Problem found in :: changePassword");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
		}
	}

	*//**
	 * This script check that User should be able to edit in her/his own profile picture
	 * 
	 * @author agupta
	 * @since 23/12/2014
	 *//*
	@Test(description = "ezCAC_MVP_Reg-3053 - User should be able to edit in her/his own profile pricture", priority = 11)
	public static void editPicture()
	{
		boolean bstatus = true;
		try
		{
			Log4J.logp.info("************** Started :: editPicture ***************");
			Thread.sleep(2000);
			Login_Lib.login("ag007");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			landingp_webe.lbl_UserName.click();
			landingp_webe.lnk_Profile.click();
			Thread.sleep(2000);

			// Upload PNG file
			Login_Lib.uploadfile("Y:\\ProPics\\test.png");
			if (Common_Lib.IsCustomAlertPresent() == false)
			{
				Log4J.logp.info("PNG image uploaded successfully");
			}
			else
			{
				bstatus = false;
			}

			// Upload GIF file
			Login_Lib.uploadfile("Y:\\ProPics\\default.gif");
			if (Common_Lib.IsCustomAlertPresent() == false)
			{
				Log4J.logp.info("GIF image uploaded successfully");
			}
			else
			{
				bstatus = false;
			}

			// Upload JPG file
			Login_Lib.uploadfile("Y:\\ProPics\\testing.jpg");
			if (Common_Lib.IsCustomAlertPresent() == false)
			{
				Log4J.logp.info("JPG image uploaded successfully");
			}
			else
			{
				bstatus = false;
			}

			// to check if user upload invalid image in profile picture
			Login_Lib.uploadfile("Y:\\ProPics\\default_BMP.bmp");
			if (Common_Lib.IsCustomAlertPresent() == true)
			{
				Log4J.logp.info("Image type is invalid.");
			}
			else
			{
				bstatus = false;
			}

			// to check if user upload image more than 150px*150px dimension
			login_webe.lnk_picCancel.click();
			Thread.sleep(2000);
			Login_Lib.uploadfile("Y:\\ProPics\\dimension.png");
			if (Common_Lib.IsCustomAlertPresent() == true)
			{
				Log4J.logp.info("Image dimension is wrong");
			}
			else
			{
				bstatus = false;
			}

			// to check if user upload image more than 300 size
			login_webe.lnk_picCancel.click();
			Thread.sleep(2000);
			Login_Lib.uploadfile("Y:\\ProPics\\size.jpg");
			if (Common_Lib.IsCustomAlertPresent() == true)
			{
				Log4J.logp.info("Image size is wrong");
			}
			else
			{
				bstatus = false;
			}

			Log4J.logp.info("************** Ended :: editPicture ***************");
			Assert.assertTrue(bstatus, "editProfile completed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: editPicture");
			Assert.assertTrue(false, "Problem found in :: editPicture");
			e.printStackTrace();
		}
		finally
		{
			landingp_webe.lnk_Cases.click();
		}
	}

	@AfterClass
	public static void LoginAfterClass()
	{
		try
		{
			if (driver != null)
			{
				driver = null;
			}
			if (login_webe != null)
			{
				login_webe = null;
			}
			if (landingp_webe != null)
			{
				landingp_webe = null;
			}

			if (adminHome_webe != null)
			{
				adminHome_webe = null;
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
*/


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

public class Login {
	
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
