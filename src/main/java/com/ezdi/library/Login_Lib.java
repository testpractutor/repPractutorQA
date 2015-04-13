package com.ezdi.library;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ezdi.framework.common.JDBCMySql;
import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.webelements.LandingP_WebE;
import com.ezdi.webelements.Login_WebE;

public class Login_Lib
{

	static WebDriver		driver;
	static Login_WebE		login_webe;
	static WebDriverWait	wait;
	static LandingP_WebE	landingp_webe;

	public static boolean logIn_App(String userName, String passWord)
	{
		try
		{

			driver = ExecutionSetup.getDriver();
			login_webe = Login_WebE.getInstance(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);

			
			login_webe.btn_SignIn.click();
			login_webe.txt_userName.sendKeys(userName);
			
			login_webe.txt_passWord.sendKeys(passWord);
			login_webe.btn_Go.click();
			

//			if (landingp_webe.lbl_UserName != null)
//			{
//				Log4J.logp.info("Login is sucessfull in Login library");
//				return true;
//			}
//			else
//			{
//				Log4J.logp.error("Login is un - sucessfull in Login library");
//				return false;
//			}

			

		}
		
		
		
		catch (Exception e)
		{
			Log4J.logf.error("Login is unsucessfully");
			return false;
		}
		return true;

	}

	public static boolean login(String rowid)
	{
		boolean bStatus = false;
		String username = null;
		String password = null;
		Map<String, String> rowTestData = null;
		try
		{

			Log4J.logp.info("--------------- Started : login ----------------");
			JDBCMySql navigationBar_TestData = new JDBCMySql();
			rowTestData = navigationBar_TestData.getRowbyID("td_logindata", rowid);
			username = rowTestData.get("userName");
			password = rowTestData.get("passWord");
			bStatus = Login_Lib.logIn_App(username, password);
			Log4J.logp.info("--------------- Ended : login ----------------");
			return bStatus;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author nchourasiya
	 * */
	public static boolean checkLoginSucess(String rowID, String lbl, String err)
	{
		boolean bUsername;
		boolean bStatus = false;
		try
		{
			Log4J.logp.info("--------------- Started : checkLoginSucess ----------------");

			Login_Lib.login(rowID);

			if (lbl == "check_login")
			{

				bUsername = Common_Lib.checkElementPresent(landingp_webe.lbl_UserName);
				Log4J.logp.info(landingp_webe.lbl_UserName.getText() + " Welcome to ezcac");
				Thread.sleep(1000);
				Login_Lib.logOut_App();
				bStatus = true;
			}
			else if (err == "checkError")

			{
				bUsername = Common_Lib.checkElementPresent(login_webe.txt_ErrMsg);
				Log4J.logp.info(login_webe.txt_ErrMsg.getText() + " - Validation Message");
				Thread.sleep(3000);
				driver.navigate().refresh();
				Thread.sleep(2000);
				bStatus = true;
			}
			else
			{
				Log4J.logp.info("Some thing went wrong");
				bStatus = false;
			}

			Log4J.logp.info("-------------- Ended : checkLoginSucess----------------");
			return bStatus;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;

		}

	}

	public static boolean logOut_App()
	{
		try
		{
			driver = ExecutionSetup.getDriver();
			login_webe = Login_WebE.getInstance(driver);

			wait = new WebDriverWait(driver, 20);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(login_webe.btn_Salutation));
			login_webe.btn_Salutation.click();
			//Thread.sleep(2000);
			//Common_Lib.waitForObject(login_webe.lnk_Logout, "visibility", 30);
			login_webe.lnk_Logout.click();
			Thread.sleep(2000);

			//Assert.assertTrue((landingp_webe.lbl_UserName.getText()).toLowerCase().contains((userName.toLowerCase())));
			wait = null;
			Log4J.logf.info("Logout is sucessful in Login library");
			return true;

			// landingp_webe.imgAdmin_Setting.isDisplayed();

		}
		catch (Exception e)
		{
			Log4J.logf.error("Logout is unsucessful");
			e.printStackTrace();
			return false;

		}

	}

	/**
	 * @author nchourasiya
	 * 
	 * @since 21/10/2014
	 * 
	 * */

	public static boolean profile_EditPassword(String rowid)
	{
		String newpassword = null;
		String confirmpassword = null;
		Map<String, String> rowTestData = null;
		JDBCMySql navigationBar_TestData = new JDBCMySql();
		try
		{
			Log4J.logp.info("---------------- profile_EditPassword --------------------");
			rowTestData = navigationBar_TestData.getRowbyID("td_profile", rowid);
			newpassword = rowTestData.get("NewPassword");
			confirmpassword = rowTestData.get("ConfirmPassword");

			if (newpassword != null)
			{
				login_webe.txt_New_Pass.clear();
				login_webe.txt_New_Pass.sendKeys(newpassword);
			}
			if (confirmpassword != null)
			{
				login_webe.txt_Conf_Pass.clear();
				login_webe.txt_Conf_Pass.sendKeys(confirmpassword);
			}

			Log4J.logp.info("In profile_EditPassword " + rowid);
			Thread.sleep(2000);

			boolean eightChar = login_webe.chk_EightChar.isDisplayed();

			boolean upperAndLower = login_webe.chk_UpperAndLowerCase.isDisplayed();

			boolean oneSpecialChar = login_webe.chk_OneSpecialChar.isDisplayed();

			Log4J.logp.info("Validation Check For Change Password.");

			if (eightChar == true && login_webe.chk_EightChar.isDisplayed() == true)
			{

				Log4J.logp.info("Eight Character Present - Pass ");
			}
			else
			{

				Log4J.logp.error("Eight Character not present - Fail");
			}

			if (upperAndLower == true && login_webe.chk_UpperAndLowerCase.isDisplayed() == true)
			{

				Log4J.logp.info("Upper and Lower Case Present - Pass ");
			}
			else
			{

				Log4J.logp.error("Upper and Lower Case not Present - Fail");
			}

			if (oneSpecialChar == true && login_webe.chk_OneSpecialChar.isDisplayed() == true)
			{

				Log4J.logp.info("One special character Present - Pass");
			}
			else
			{

				Log4J.logp.error("One Special Character not Present - Fail");
			}

			login_webe.lnk_Save_Password.click();
			Log4J.logp.info("In profile_EditPassword --Password Changed for rowId   " + rowid);
			Log4J.logp.info("----------------- Ended :profile_EditPassword --------------------");
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log4J.logp.error("In profile_EditPassword --add Edit PassWord is failed for rowId=" + rowid);
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

	/**
	 * To add values in Edit Profile page
	 * 
	 * @author agupta
	 * @since 22/12/2014
	 */
	public static boolean editProfile(String rowid)
	{
		String address = null;
		String city = null;
		String state = null;
		String zipcode = null;
		String country = null;
		String phone = null;
		String fax = null;
		Map<String, String> rowTestData = null;
		JDBCMySql navigationBar_TestData = new JDBCMySql();
		try
		{
			Log4J.logp.info("---------------- editProfile --------------------");
			rowTestData = navigationBar_TestData.getRowbyID("td_profile", rowid);
			address = rowTestData.get("Address");
			city = rowTestData.get("City");
			state = rowTestData.get("State");
			zipcode = rowTestData.get("ZipCode");
			phone = rowTestData.get("Phone");
			fax = rowTestData.get("Fax");
			country = rowTestData.get("Country");

			if (address != null)
			{
				login_webe.txt_Address.clear();
				login_webe.txt_Address.sendKeys(address);
			}
			if (city != null)
			{
				login_webe.txt_City.clear();
				login_webe.txt_City.sendKeys(city);
			}
			if (state != null)
			{
				login_webe.txt_State.clear();
				login_webe.txt_State.sendKeys(state);

			}
			if (country != null)
			{
				login_webe.txt_Country.clear();
				login_webe.txt_Country.sendKeys(country);
			}
			if (zipcode != null)
			{
				login_webe.txt_Zip_Code.clear();
				login_webe.txt_Zip_Code.sendKeys(zipcode);

			}
			if (phone != null)
			{
				login_webe.txt_PhoneNo.clear();
				login_webe.txt_PhoneNo.sendKeys(phone);

			}
			if (fax != null)
			{
				login_webe.txt_FaxNo.clear();
				login_webe.txt_FaxNo.sendKeys(fax);

			}
			Log4J.logp.info("----------------- Ended :viewProfile --------------------");
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log4J.logp.error("In profile_EditPassword --add Edit PassWord is failed for rowId=" + rowid);
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

	/** to upload the file from windows system */
	public static boolean uploadfile(String path)
	{
		try
		{
			Log4J.logp.info("------------------- Started :: uploadfile ------------------");
			login_webe.lnk_EditPicture.click();
			Thread.sleep(2000);
			login_webe.lnk_Browse.click();
			Thread.sleep(2000);

			// Set the file name in clipboard
			StringSelection ss = new StringSelection(path);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

			// Peform native keystrokes for CTRL + V and Enter keys
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			Log4J.logp.info("------------------- Ended :: uploadfile ------------------");
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * @author nchourasiya
	 *
	 * @since 22/10/2014
	 * 
	 * */

	public static boolean forgot_Password(String rowid)
	{

		String forgotpwd = null;
		Map<String, String> rowTestData = null;
		JDBCMySql navigationBar_TestData = new JDBCMySql();

		try
		{
			Log4J.logp.info("---------------Started : forgot_Password ------------ ");
			rowTestData = navigationBar_TestData.getRowbyID("td_forgot_password", rowid);
			forgotpwd = rowTestData.get("EmailID");

			if (forgotpwd != null)
			{
				login_webe.txt_ForgotUsername.clear();
				login_webe.txt_ForgotUsername.sendKeys(forgotpwd);
			}
			login_webe.btn_ForgotPassword.click();
			Log4J.logp.info("In forgot_Password --Forgot Password is Passed for rowId   " + rowid);
			Log4J.logp.info("---------------Ended : forgot_Password ------------ ");
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log4J.logp.error("In forgot_Password --Forgot Password is Failed for rowId=" + rowid);
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

	/**
	 * @author nchourasiya
	 * */

	public static boolean reset_Password(String rowid, String username)
	{
		boolean bStatus = false;
		try
		{
			Log4J.logp.info("---------------------Started: reset_Password--------------------");

			String reset_Password = null;
			String Confirm_reset_Password = null;
			Map<String, String> rowTestData = null;
			JDBCMySql navigationBar_TestData = new JDBCMySql();
			rowTestData = navigationBar_TestData.getRowbyID("td_logindata", rowid);

			reset_Password = rowTestData.get("passWord");
			Confirm_reset_Password = rowTestData.get("passWord");
			if (reset_Password != null)
			{
				login_webe.txt_Reset_Pwd.clear();
				login_webe.txt_Reset_Pwd.sendKeys(reset_Password);
			}
			if (Confirm_reset_Password != null)
			{
				login_webe.txt_Confirm_ResetPwd.clear();
				login_webe.txt_Confirm_ResetPwd.sendKeys(Confirm_reset_Password);
			}

			login_webe.btn_ResetLogin.click();

			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Log4J.logp.info("Login is Sucessfull For new user with Username :" + username + "Password :" + Confirm_reset_Password);
				bStatus = true;
			}
			else
			{
				bStatus = false;
			}
			Log4J.logp.info("------------------------------Ended: reset_Password----------------------------");
			return bStatus;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;

		}

	}

}
