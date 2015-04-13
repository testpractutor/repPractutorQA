package com.ezdi.testscripts;

import java.net.URLDecoder;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.library.Common_Lib;
import com.ezdi.library.IssuePanel_Lib;
import com.ezdi.library.LandingPage_Lib;
import com.ezdi.library.Login_Lib;
import com.ezdi.library.SearchCriteria_Lib;
import com.ezdi.library.WorkingScreen_Lib;
import com.ezdi.webelements.CodingPnl_WebE;
import com.ezdi.webelements.Comman_WebE;
import com.ezdi.webelements.GroupinPnl_WebE;
import com.ezdi.webelements.IssuePnl_WebE;
import com.ezdi.webelements.LandingP_WebE;
import com.ezdi.webelements.MedicalRecordPnl_WebE;
import com.ezdi.webelements.SearchCriteria_WebE;

public class LandingPage
{
	public static WebDriver				driver;
	public static LandingP_WebE			landingp_webe;
	public static Comman_WebE			common_webe;
	public static WebDriverWait			wait;
	public static SearchCriteria_WebE	searchcriteria_webe;
	public static GroupinPnl_WebE		groupingpnl_webe;
	public static IssuePnl_WebE			issuepnl_webe;
	public static MedicalRecordPnl_WebE	medicalrecordpnl_webe;
	public static CodingPnl_WebE		codingpnl_webe;

	@BeforeClass
	public static void LandingPageBeforeClass()
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
			codingpnl_webe = CodingPnl_WebE.getInstance(driver);
			Log4J.logp.info("In BeforeClass for LandingPage");

			// Login with Akash user

			Login_Lib.login("ag007");
			Thread.sleep(2000);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This script check different landing page after login with different user *
	 *
	 * @author agupta
	 * @since 6/10/2014
	 */
	@Test(description = "ezCAC_MVP-101:To verify different role's landing page", priority = 0)
	public static void roles_Landing_Page()
	{
		String strActive_page;
		try
		{
			Log4J.logp.info("********************* Statrted ::: roles_Landing_Page **************************");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("ag005");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			for (int i = 1; i < 5; i++)
			{
				switch (i)
				{
					case 1:
						if (driver.findElements(By.cssSelector("[class='float-l Mail']")).size() != 0)
						{
							Log4J.logp.info("Physician's landing page is displayed");
							Assert.assertTrue(true, "Physician's landing page is displayed");
							Thread.sleep(2000);
						}
						else
						{
							Assert.assertTrue(false, "No landing page is displayed");
						}
						break;
					case 2:
						Thread.sleep(2000);
						Login_Lib.logOut_App();
						Thread.sleep(2000);
						Login_Lib.login("ag009");
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
						strActive_page = landingp_webe.chk_Active_Page.getAttribute("id");
						if (strActive_page.equalsIgnoreCase("coding"))
						{
							Log4J.logp.info("Coder's Landing page is displayed");
							Assert.assertTrue(true, "Coder's Landing page");
						}
						else
						{
							Assert.assertTrue(false, "No landing page is displayed");
						}
						break;
					case 3:
						Thread.sleep(2000);
						Login_Lib.logOut_App();
						Thread.sleep(2000);
						Login_Lib.login("ag0010");
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
						strActive_page = landingp_webe.chk_Active_Page.getAttribute("id");
						if (strActive_page.equalsIgnoreCase("cdi"))
						{
							Log4J.logp.info("CDI's landing page is displayed");
							Assert.assertTrue(true, "CDI's Landing page");
						}
						else
						{
							Assert.assertTrue(false, "No landing page is displayed");
						}
						break;
					case 4:
						Thread.sleep(2000);
						Login_Lib.logOut_App();
						Thread.sleep(2000);
						Login_Lib.login("ag002");
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
						strActive_page = landingp_webe.chk_Active_Page.getAttribute("id");
						if (strActive_page.equalsIgnoreCase("review"))
						{
							Log4J.logp.info("Reviewer's landing page is displayed");
							Assert.assertTrue(true, "Reviewer's landing page");
						}
						else
						{
							Assert.assertTrue(false, "No landing page is displayed");
						}
						break;
				}
			}
			Log4J.logp.info("*************************** Ended ::: roles_Landing_Page ******************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in - roles_Landing_Page");
			Assert.assertTrue(false, "roles_Landing_Page is failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check pagination on landing page
	 * 
	 * @author agupta
	 * @since 6/10/2014
	 */
	@Test(description = "ezCAC_ MVP-3042:To verify pagination on landing page", priority = 1)
	public static void verifyPagination()
	{
		try
		{
			Log4J.logp.info("******************* Started -verifyPagination **********************");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("SC0046");
			Thread.sleep(2000);
			Common_Lib.scroll_without_element();
			Common_Lib.waitForObject(landingp_webe.lnk_Next, "clickable", 10);
			landingp_webe.lnk_Next.click();
			Thread.sleep(2000);
			Common_Lib.scroll_without_element();
			Common_Lib.waitForObject(landingp_webe.lnk_Next, "clickable", 10);
			landingp_webe.lnk_Next.click();
			Thread.sleep(2000);
			landingp_webe.lnk_Prev.click();
			Thread.sleep(2000);
			Common_Lib.scroll_without_element();
			Common_Lib.waitForObject(landingp_webe.lnk_Prev, "clickable", 10);
			landingp_webe.lnk_Prev.click();
			Assert.assertTrue(true, "verifyPagination is passed");
			Log4J.logp.info("***************** Ended -verifyPagination ********************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found - verifyPagination");
			Assert.assertTrue(false, "verifyPagination is failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This Script is to check that 1st and 2nd level navigation bar after login with single and multiple user role
	 * 
	 * @author agupta
	 * @since 4/9/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-100 : To verify 1st and 2nd level navigation bar after login with single and multiple user role", priority = 2)
	public static void navigationBar()
	{
		String strActive_page;
		try
		{
			Log4J.logp.info("****************** Started ::: navigationBar **********************");
			Thread.sleep(2000);
			if (driver.findElements(By.cssSelector("#all > a")).size() != 0)
			{
				Log4J.logp.info("=============== Inside all role ===================");
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_All));
				// 1st level Navigation Bar
				LandingPage_Lib.first_level_navigationBar();
				// 2nd level Navigation Bar 
				Log4J.logp.info("2nd level Navigation Bar");
				if (landingp_webe.lnk_All.isDisplayed())
				{
					Log4J.logp.info("All label is Displayed");
					Assert.assertTrue(landingp_webe.lnk_All.isDisplayed(), "All label is displayed");
				}
			}
			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("ag001");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			if (driver.findElements(By.cssSelector("#coding > a")).size() != 0)
			{
				Log4J.logp.info("=============== Inside coder role =================");
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_Coding));
				// 1st level Navigation Bar
				LandingPage_Lib.first_level_navigationBar();
				// 2nd level Navigation Bar

				Log4J.logp.info(" 2nd level Navigation Bar ");
				if (landingp_webe.lnk_Coding.isDisplayed())
				{
					Log4J.logp.info("Coding label is Displayed");
					Assert.assertTrue(landingp_webe.lnk_Coding.isDisplayed(), "Coding label is displayed");
				}
			}
			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("ag002");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			if (driver.findElements(By.cssSelector("#review > a")).size() != 0)
			{
				Log4J.logp.info("================== Inside Reviewer role ==================");
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_Review));
				// 1st level Navigation Bar
				LandingPage_Lib.first_level_navigationBar();
				// 2nd level Navigation Bar
				Log4J.logp.info(" 2nd level Navigation Bar ");
				if (landingp_webe.lnk_Review.isDisplayed())
				{
					Log4J.logp.info("Review label is Displayed");
					Assert.assertTrue(landingp_webe.lnk_Review.isDisplayed(), "Review label is displayed");
				}
			}
			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("ag003");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			if (driver.findElements(By.cssSelector("#cdi > a")).size() != 0)
			{
				Log4J.logp.info("============== Inside CDI role =================");
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_CDI));
				// 1st level Navigation Bar
				LandingPage_Lib.first_level_navigationBar();
				// 2nd level Navigation Bar 
				Log4J.logp.info(" 2nd level Navigation Bar ");
				if (landingp_webe.lnk_CDI.isDisplayed())
				{
					Log4J.logp.info("CDI label is Displayed");
					Assert.assertTrue(landingp_webe.lnk_CDI.isDisplayed(), "CDI label is displayed");
				}
			}
			for (int i = 0; i < 4; i++)
			{
				Login_Lib.logOut_App();
				Login_Lib.login("ag004");
				switch (i)
				{
					case 0:
						landingp_webe.lnk_All.click();
						Login_Lib.logOut_App();
						Login_Lib.login("ag004");
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
						strActive_page = landingp_webe.chk_Active_Page.getAttribute("id");
						if (strActive_page.equalsIgnoreCase("all"))
						{
							Log4J.logp.info("Supervisor role is selected");
							Assert.assertTrue(true, "Supervisor role is selected");
						}
						else
						{
							Assert.assertTrue(false, "No role is selected");
						}
						break;
					case 1:
						landingp_webe.lnk_Coding.click();
						Login_Lib.logOut_App();
						Login_Lib.login("ag004");
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
						strActive_page = landingp_webe.chk_Active_Page.getAttribute("id");
						if (strActive_page.equalsIgnoreCase("coding"))
						{
							Log4J.logp.info("Coder role is selected");
							Assert.assertTrue(true, "Coder role is selected");
						}
						else
						{
							Assert.assertTrue(false, "No role is selected");
						}
						break;
					case 2:
						landingp_webe.lnk_Review.click();
						Login_Lib.logOut_App();
						Login_Lib.login("ag004");
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
						strActive_page = landingp_webe.chk_Active_Page.getAttribute("id");
						if (strActive_page.equalsIgnoreCase("review"))
						{
							Log4J.logp.info("Reviewer role is selected");
							Assert.assertTrue(true, "Reviewer role is selected");
						}
						else
						{
							Assert.assertTrue(false, "No role is selected");
						}
						break;
					case 3:
						landingp_webe.lnk_CDI.click();
						Login_Lib.logOut_App();
						Login_Lib.login("ag004");
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
						strActive_page = landingp_webe.chk_Active_Page.getAttribute("id");
						if (strActive_page.equalsIgnoreCase("cdi"))
						{
							Log4J.logp.info("CDI role is selected");
							Assert.assertTrue(true, "CDI role is selected");
						}
						else
						{
							Assert.assertTrue(false, "No role is selected");
						}
						break;
				}
			}
			Log4J.logp.info("*********************** Ended ::: navigationBar ************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in - navigationBar");
			Assert.assertTrue(false, "NavigationBar is failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check that user role wise reports links are properly displayed or not
	 * 
	 * @author agupta
	 * @since 5/9/2014
	 */
	@Test(description = "ezCAC_MVP-162:User clicks on Reports tab", priority = 3)
	public static void clickReportsTab()
	{
		try
		{
			Log4J.logp.info("****************** Started - clickReportsTab *********************");

			Thread.sleep(2000);
			// To check if reports link are present for Supervisor role

			if (driver.findElements(By.cssSelector("#all > a")).size() != 0)
			{
				Log4J.logp.info("Inside all role");
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_All));
				landingp_webe.lnk_All.click();

				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_Reports));

				landingp_webe.lnk_Reports.click();

				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_AllReports));

				if (landingp_webe.lnk_AllReports.isDisplayed())
				{
					Log4J.logp.info("All Reports is Displayed");
					Assert.assertTrue(landingp_webe.lnk_AllReports.isDisplayed(), "All Reports is Displayed");
				}

				if (landingp_webe.lnk_AllCodingDashboard.isDisplayed())
				{
					Log4J.logp.info("All Coding Dashboard is Displayed");
					Assert.assertTrue(landingp_webe.lnk_AllCodingDashboard.isDisplayed(), "All Coding Dashboard is Displayed");
				}

				if (landingp_webe.lnk_AllReviewerDashboard.isDisplayed())
				{
					Log4J.logp.info("All Reviewer Dashboard is Displayed");
					Assert.assertTrue(landingp_webe.lnk_AllReviewerDashboard.isDisplayed(), "All Reviewer Dashboard is Displayed");
				}

				if (landingp_webe.lnk_AllCDIDashboard.isDisplayed())
				{
					Log4J.logp.info("All CDI Dashboard is Displayed");
					Assert.assertTrue(landingp_webe.lnk_AllCDIDashboard.isDisplayed(), "All CDI Dashboard is Displayed");
				}

				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_Cases));

				landingp_webe.lnk_Cases.click();
			}

			// To check if reports link are present for Coder role

			if (driver.findElements(By.cssSelector("#coding > a")).size() != 0)
			{
				Log4J.logp.info("Inside Coder Role");

				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_Coding));

				landingp_webe.lnk_Coding.click();

				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_Reports));

				landingp_webe.lnk_Reports.click();

				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_CodingDashboard));

				if (landingp_webe.lnk_CodingDashboard.isDisplayed())
				{
					Log4J.logp.info("Coding Dashboard is Displayed");
					Assert.assertTrue(landingp_webe.lnk_CodingDashboard.isDisplayed(), "Coding Dashboard is Displayed");
				}

				if (landingp_webe.lnk_CodingReports.isDisplayed())
				{
					Log4J.logp.info("Coding Reports is Displayed");
					Assert.assertTrue(landingp_webe.lnk_CodingReports.isDisplayed(), "Coding Reports is Displayed");
				}

				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_Cases));

				landingp_webe.lnk_Cases.click();
			}

			// To check if reports link are present for Reviewer role

			if (driver.findElements(By.cssSelector("#review > a")).size() != 0)
			{
				Log4J.logp.info("Inside reviewer role");

				landingp_webe.lnk_Review.click();

				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_Reports));

				landingp_webe.lnk_Reports.click();

				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_ReviewerDashboard));

				if (landingp_webe.lnk_ReviewerDashboard.isDisplayed())
				{
					Log4J.logp.info("Reviewer Dashboard is Displayed");
					Assert.assertTrue(landingp_webe.lnk_ReviewerDashboard.isDisplayed(), "Reviewer Dashboard is Displayed");
				}

				if (landingp_webe.lnk_ReviewerReports.isDisplayed())
				{
					Log4J.logp.info("Reviewer Reports is Displayed");
					Assert.assertTrue(landingp_webe.lnk_ReviewerReports.isDisplayed(), "Reviewer Reports is Displayed");
				}

				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_Cases));

				landingp_webe.lnk_Cases.click();
			}

			// To check if reports link are present for CDI role
			if (driver.findElements(By.cssSelector("#cdi > a")).size() != 0)
			{
				Log4J.logp.info("Inside CDI role");
				landingp_webe.lnk_CDI.click();
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_Reports));
				landingp_webe.lnk_Reports.click();
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_CDIDashboard));
				if (landingp_webe.lnk_CDIDashboard.isDisplayed())
				{
					Log4J.logp.info("CDI Dashboard is Displayed");
					Assert.assertTrue(landingp_webe.lnk_CDIDashboard.isDisplayed(), "CDI Dashboard is Displayed");
				}
				if (landingp_webe.lnk_CDIReports.isDisplayed())
				{
					Log4J.logp.info("CDI Reports is Displayed");
					Assert.assertTrue(landingp_webe.lnk_CDIReports.isDisplayed(), "CDI Reports is Displayed");
				}
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(landingp_webe.lnk_Cases));
				landingp_webe.lnk_Cases.click();
			}
			Log4J.logp.info("****************** Ended - clickReportsTab ******************");
			Assert.assertTrue(true, "clickReportsTab is failed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in - clickReportsTab");
			Assert.assertTrue(false, "clickReportsTab is failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check that without selecting users Supervisor can not able to assign the cases.
	 * 
	 * @author agupta
	 * @since 6/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-143 : Without selecting users supervisor can not able to assign the cases", priority = 4)
	public static void cases_Not_Assign()
	{
		String strMessage;
		try
		{
			Log4J.logp.info("******************* Statrted ::: cases_Not_Assign *******************");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("SC0045");
			Thread.sleep(2000);
			// to click on checkbox of first case in landing page 
			landingp_webe.chk_First_Case.click();
			Thread.sleep(2000);
			landingp_webe.lnk_Assign.click();
			Thread.sleep(2000);
			landingp_webe.btn_Done.click();
			Thread.sleep(2000);
			Log4J.logp.info(landingp_webe.txt_validation.getText());
			strMessage = "Please select assignee";
			if (landingp_webe.txt_validation.getText().equalsIgnoreCase(strMessage))
			{
				Log4J.logp.info("Case is not assigned without selecting any users");
				Assert.assertTrue(true, "Cases is not assigned.");
			}
			Assert.assertEquals(landingp_webe.txt_validation.getText(), strMessage);
			Thread.sleep(2000);
			landingp_webe.btn_Cancel.click();
			Log4J.logp.info("**************** Ended ::: cases_Not_Assign ******************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in - Cases_Not Assign");
			Assert.assertTrue(false, "cases_Not_Assign is failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script to check that Supervisor should be able to know name of the user of the case
	 * 
	 * @author agupta
	 * @since 5/9/2014
	 */
	@Test(description = "ezCAC_MVP_157 : Supervisor should be able to know name of the user of the case", priority = 5)
	public static void userName()
	{
		String strusername;
		try
		{
			Log4J.logp.info("****************** Started ::: userName ********************");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("ag006");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("ag002");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "clickable", 15);
			groupingpnl_webe.btn_Later.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			SearchCriteria_Lib.search_Case("ag002");
			Common_Lib.scroll_without_element();
			strusername = landingp_webe.lbl_User_Name.getText();
			if (strusername.equalsIgnoreCase("Jennifer Davis"))
			{
				Log4J.logp.info("Case has been assigned to ::: " + strusername);
				Assert.assertTrue(true, "Case has been assignd to the user");
			}
			else
			{
				Assert.assertTrue(false, "User Name is wrong");
			}
			Log4J.logp.info("***************** Ended ::: userName ****************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in Username");
			Assert.assertTrue(false, "Case not found");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check that High priority cases should be displayed on top of all cases
	 * 
	 * @author agupta
	 * @since 6/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2794 :: To check High priority cases should be displayed on top of all cases", priority = 6)
	public static void high_Priority_Cases()
	{
		try
		{
			Log4J.logp.info("**************** Started :: high_Priority_Cases ****************");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("SC0047");
			Thread.sleep(2000);
			landingp_webe.chk_First_Case.click();
			landingp_webe.lnk_Assign.click();
			Thread.sleep(2000);
			landingp_webe.lst_AssignTo.sendKeys("Susan Wilson");
			Thread.sleep(2000);
			landingp_webe.btn_Done.click();
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Login_Lib.login("ag001");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			landingp_webe.high_Priority.size();
			Log4J.logp.info("Total High Priority Cases are ::: " + landingp_webe.high_Priority.size());
			Log4J.logp.info("****************** Ended :: high_Priority_Cases ******************");
		}
		catch (Exception e)
		{
			Log4J.logp.info("Problem found - high_Priority_Cases");
			Assert.assertTrue(false, "high_Priority_Cases is failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check that "Issues" on grid and expand mode if case is in "In Queue" status.
	 * 
	 * @author agupta
	 * @since 4/9/2014
	 */
	@Test(description = "ezCAC_MVP_Reg_150 : To verify Issues on grid and expand mode if case is in In Queue status", priority = 7)
	public static void issues_With_Fresh_Case()
	{
		String strtext, strqueries, strdiscussion;
		boolean bstatus;
		boolean bcheck = true;
		try
		{
			Log4J.logp.info("****************** Started ::: issues_With_Fresh_Case *******************");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("ag001");
			Thread.sleep(2000);
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);
			// To check no issue with fresh case in landing page (first case)
			strtext = landingp_webe.lbl_Issues.getText();
			if (strtext.equalsIgnoreCase("-"))
			{
				Log4J.logp.info(" Total number of issues is displayed as '-' in issue column");
				Assert.assertTrue(true, "Case is fresh case");
			}
			// Expand the case
			LandingPage_Lib.click_on_expand();
			Thread.sleep(2000);
			strqueries = landingp_webe.lbl_Queries.getText();
			strdiscussion = landingp_webe.lbl_Discussion.getText();
			Thread.sleep(2000);
			bstatus = strqueries.equalsIgnoreCase("0 Query");
			if (bstatus == true)
			{
				Log4J.logp.info(" 0 Query ");
				Assert.assertTrue(true, "No Queries issues");
			}
			else
			{
				bcheck = false;
			}
			bstatus = strdiscussion.equalsIgnoreCase("0 Discussion");
			if (bstatus == true)
			{
				Log4J.logp.info(" 0 Discussions ");
				Assert.assertTrue(true, "No Discussion issues");
			}
			else
			{
				bcheck = false;
			}
			Log4J.logp.info("***************** Ended ::: issues_With_Fresh_Case *******************");
			Assert.assertTrue(bcheck, "issues_With_Fresh_Case passed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found In :: issues_With_Fresh_Case");
			Assert.assertTrue(false, "Case is not fresh case");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * 
	 * This script is to check that "Issues" on grid if case with all Pending and Resolved issues
	 * 
	 * @author agupta
	 * @since 16/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-151 : To verify 'Issues' on grid with all Pending and Resolved issues", priority = 8)
	public static void cases_With_Issues()
	{
		try
		{
			Log4J.logp.info("******************** Started :: cases_With_Issues *******************");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("ag005");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "clickable", 15);
			for (int i = 0; i < 2; i++)
			{
				Common_Lib.rightclick_system_sugggested_evidence();
				Thread.sleep(2000);
				medicalrecordpnl_webe.lnk_discusswithcolleague.click();
				IssuePanel_Lib.send_DiscussWithColleague("ag001");
				Thread.sleep(2000);
			}

			Common_Lib.rightclick_system_sugggested_evidence();
			Thread.sleep(2000);
			medicalrecordpnl_webe.lnk_querytophysician.click();
			IssuePanel_Lib.send_QueryToPhysician("ag002");
			Thread.sleep(2000);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("ag005");
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);
			if (landingp_webe.lbl_Issues.getText().equalsIgnoreCase("3"))
			{
				Log4J.logp.info(" Total number of issues is displayed as 3 in issue column");
				Assert.assertTrue(true, "No Issues");
			}
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("ag005");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "clickable", 15);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			for (int i = 0; i < 2; i++)
			{
				issuepnl_webe.lnk_markasresolved.click();
			}
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("ag005");
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);
			if (landingp_webe.lbl_Issues.getText().equalsIgnoreCase("2"))
			{
				Log4J.logp.info(" Total number of issues is displayed as 2 in issue column");
				Assert.assertTrue(true, "No Issues");
			}
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("ag005");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "clickable", 15);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			issuepnl_webe.lnk_markasresolved.click();

			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("ag005");
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);
			Common_Lib.scroll_without_element();
			Thread.sleep(2000);
			if (landingp_webe.lbl_Issues.getText().equalsIgnoreCase("0"))
			{
				Log4J.logp.info(" Total number of issues is displayed as 0 in issue column");
				Assert.assertTrue(true, "No Issues");
			}
			LandingPage_Lib.click_on_expand();
			Thread.sleep(2000);
			if (landingp_webe.lbl_Queries.getText().equalsIgnoreCase("1 Query"))
			{
				Log4J.logp.info(" 1 Query ");
				Assert.assertTrue(true, "No Queries issues");
			}
			if (landingp_webe.lbl_Discussion.getText().equalsIgnoreCase("2 Discussions"))
			{
				Log4J.logp.info(" 2 Discussions ");
				Assert.assertTrue(true, "No Discussion issues");
			}
			Log4J.logp.info("**************** Ended :: cases_With_Issues ******************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: cases_With_Issues");
			Assert.assertTrue(false, "Problem found in cases_With_Issues");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}

	}

	/**
	 * This script is to check version details after expanding the case when case completed by the coder
	 * 
	 * @author agupta
	 * @since 17/10/2014
	 */
	@Test(description = "ezCAC_ MVP-149:To verify version details after expanding the case when case completed by the coder", priority = 9)
	public static void verifyVerExpand_Coder()
	{
		boolean bstatus = false;
		String strver;
		try
		{
			Log4J.logp.info("************** Started ::: verifyVerExpand_Coder ******************");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("ag006");
			Thread.sleep(2000);
			LandingPage_Lib.click_on_expand();
			Thread.sleep(1000);
			//Log4J.logp.info(landingp_webe.txt_Version0.getText());
			strver = landingp_webe.img_Version0.getAttribute("src");
			Log4J.logp.info(strver);
			strver = URLDecoder.decode(strver, "UTF-8");
			Log4J.logp.info(strver);
			bstatus = strver.toLowerCase().contains("fresh case");
			Log4J.logp.info(bstatus);
			if (bstatus == true)
			{
				Log4J.logp.info("Version 0 is Displyed with Fresh Case");
				Assert.assertTrue(bstatus, "Checking the Unassigned status");
			}

			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("ag006");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 15);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("ag006");
			Thread.sleep(2000);
			LandingPage_Lib.click_on_expand();
			Thread.sleep(1000);
			strver = landingp_webe.img_Version1.getAttribute("src");
			Log4J.logp.info(strver);
			strver = URLDecoder.decode(strver, "UTF-8");
			Log4J.logp.info(strver);
			bstatus = strver.toLowerCase().contains("coding_on hold");
			Log4J.logp.info(bstatus);
			if (bstatus == true)
			{
				Log4J.logp.info("Version 1 Displayed with On Hold Status");
				Assert.assertTrue(bstatus, "Checking the On Hold status");
			}
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("ag003");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("ag006");
			Thread.sleep(2000);
			LandingPage_Lib.click_on_expand();
			Thread.sleep(2000);
			strver = landingp_webe.img_Version1.getAttribute("src");
			Log4J.logp.info(strver);
			strver = URLDecoder.decode(strver, "UTF-8");
			Log4J.logp.info(strver);
			bstatus = strver.toLowerCase().contains("coding_completed");
			Log4J.logp.info(bstatus);
			if (bstatus == true)
			{
				Log4J.logp.info("Version 1 Displayed with Coding Completed Status");
				Assert.assertTrue(bstatus, "Checking the Coding Completed status");
			}
			Login_Lib.logOut_App(); // Logout from Akash User
			Thread.sleep(2000);
			Login_Lib.login("ag011"); // Login with Margaret reviewer user
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			WorkingScreen_Lib.submit_case("ag004");
			Thread.sleep(2000);
			Login_Lib.logOut_App(); // logout from Margaret reviewer user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("ag006");
			Thread.sleep(2000);
			LandingPage_Lib.click_on_expand();
			Thread.sleep(2000);
			strver = landingp_webe.img_Version2.getAttribute("src");
			Log4J.logp.info(strver);
			strver = URLDecoder.decode(strver, "UTF-8");
			Log4J.logp.info(strver);
			bstatus = strver.toLowerCase().contains("dollar_black");
			Log4J.logp.info(bstatus);
			if (bstatus == true)
			{
				Log4J.logp.info("Version 2 Displayed with Billed Status");
				Assert.assertTrue(bstatus, "Checking the billed status");
			}
			Log4J.logp.info("******************* Ended ::: verifyVerExpand_Coder *******************");
		}
		catch (Exception e)
		{
			Log4J.logp.info("Problem found in ::verifyVerExpand_Coder");
			Assert.assertTrue(false, "Problem found in ::: verifyVerExpand_Coder");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check version details after expanding the case when case completed by the CDI
	 * 
	 * @author agupta
	 * @since 21/10/2014
	 */
	@Test(description = "ezCAC_ MVP_Reg_2791:To verify version details after expanding the case when case completed by the CDI", priority = 10)
	public static void verifyVerExpand_CDI()
	{
		boolean bstatus = false;
		String strver;
		try
		{
			Log4J.logp.info("*********************** Started ::: verifyVerExpand_CDI *********************");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("ag007");
			Thread.sleep(2000);
			LandingPage_Lib.click_on_expand();
			Thread.sleep(1000);
			strver = landingp_webe.img_Version0.getAttribute("src");
			Log4J.logp.info(strver);
			strver = URLDecoder.decode(strver, "UTF-8");
			Log4J.logp.info(strver);
			bstatus = strver.toLowerCase().contains("fresh case");
			Log4J.logp.info(bstatus);
			if (bstatus == true)
			{
				Log4J.logp.info("Version 0 is Displyed with Fresh Case");
				Assert.assertTrue(bstatus, "Checking the Unassigned status");
			}

			Thread.sleep(2000);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			Common_Lib.openCase("ag007");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "clickable", 15);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("ag007");
			Thread.sleep(2000);
			LandingPage_Lib.click_on_expand();
			Thread.sleep(1000);
			strver = landingp_webe.img_Version1.getAttribute("src");
			Log4J.logp.info(strver);
			strver = URLDecoder.decode(strver, "UTF-8");
			Log4J.logp.info(strver);
			bstatus = strver.toLowerCase().contains("cdi_on hold");
			Log4J.logp.info(bstatus);
			if (bstatus == true)
			{
				Log4J.logp.info("Version 1 Displayed with On Hold Status for CDI");
				Assert.assertTrue(bstatus, "Checking the On Hold status");
			}
			Thread.sleep(2000);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("ag005");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("ag007");
			Thread.sleep(2000);
			LandingPage_Lib.click_on_expand();
			Thread.sleep(2000);
			strver = landingp_webe.img_Version1.getAttribute("src");
			Log4J.logp.info(strver);
			strver = URLDecoder.decode(strver, "UTF-8");
			Log4J.logp.info(strver);
			bstatus = strver.toLowerCase().contains("cdi_completed");
			Log4J.logp.info(bstatus);
			if (bstatus == true)
			{
				Log4J.logp.info("Version 1 Displayed with CDI Completed Status");
				Assert.assertTrue(bstatus, "Checking the CDI Completed status");
			}
			Login_Lib.logOut_App(); // logout from Akash user
			Thread.sleep(2000);
			Login_Lib.login("ag010"); // login with Patricia user
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("ag006");
			Thread.sleep(2000);
			Login_Lib.logOut_App(); // logout from patricia user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // login with akash user
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("ag007");
			Thread.sleep(2000);
			LandingPage_Lib.click_on_expand();
			Thread.sleep(2000);
			strver = landingp_webe.img_Version2.getAttribute("src");
			Log4J.logp.info(strver);
			strver = URLDecoder.decode(strver, "UTF-8");
			Log4J.logp.info(strver);
			bstatus = strver.toLowerCase().contains("coding_completed");
			Log4J.logp.info(bstatus);
			if (bstatus == true)
			{
				Log4J.logp.info("Version 2 Displayed with Coding Completed Status");
				Assert.assertTrue(bstatus, "Checking the Coding Completed status");
			}
			Login_Lib.logOut_App(); // Logout from Akash User
			Thread.sleep(2000);
			Login_Lib.login("ag011"); // Login with Margaret reviewer user
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			WorkingScreen_Lib.submit_case("ag007");
			Thread.sleep(2000);
			Login_Lib.logOut_App(); // logout from Margaret reviewer user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("ag007");
			Thread.sleep(2000);
			LandingPage_Lib.click_on_expand();
			Thread.sleep(2000);
			strver = landingp_webe.img_Version3.getAttribute("src");
			Log4J.logp.info(strver);
			strver = URLDecoder.decode(strver, "UTF-8");
			Log4J.logp.info(strver);
			bstatus = strver.toLowerCase().contains("dollar_black");
			Log4J.logp.info(bstatus);
			if (bstatus == true)
			{
				Log4J.logp.info("Version 3 Displayed with Billed Status");
				Assert.assertTrue(bstatus, "Checking the billed status");
			}
			Log4J.logp.info("********************* Ended ::: verifyVerExpand_CDI ***********************");
		}
		catch (Exception e)
		{
			Log4J.logp.info("Problem found in ::verifyVerExpand_CDI");
			Assert.assertTrue(false, "Problem found in ::: verifyVerExpand_CDI");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	// To check Search Criteria in Landing Page
	/**
	 * This script check is to check search criteria with single/multiple Status & Stage selection
	 * 
	 * @author agupta
	 * @since 5/9/2014
	 */
	@Test(description = "ezCAC_MVP_Reg_109 :: To verify search criteria with Single/Multiple Status & Stage Selection", priority = 11)
	public static void search_Status_Stage()
	{
		try
		{
			Log4J.logp.info("***************** Started ::: search_Status_Stage ********************");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			for (int i = 1; i < 25; i++)
			{
				Log4J.logp.info(SearchCriteria_Lib.search_Case("SC00" + i));
				Thread.sleep(2000);
				searchcriteria_webe.lnk_SearchCreteria.click();
				LandingPage_Lib.search_Status(i);
				Thread.sleep(2000);
			}
			Log4J.logp.info("**************** Ended ::: search_Status_Stage ******************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in  :: search_Status_Stage");
			Assert.assertTrue(false, "No Cases found");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check the single search operation with all search fields
	 * 
	 * @author agupta
	 * @since 12/9/2014
	 */
	@Test(description = "ezCAC_MVP_113 : Verify the single search opertaion with all search fields", priority = 12)
	public static void single_Search()
	{
		try
		{
			Log4J.logp.info("******************** Started :: single_Search ******************");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			for (int i = 26; i < 34; i++)
			{
				SearchCriteria_Lib.search_Case("SC00" + i);
				searchcriteria_webe.lnk_SearchCreteria.click();
				Thread.sleep(2000);
				LandingPage_Lib.search_Status(i);
				Thread.sleep(2000);
				searchcriteria_webe.lnk_ClearSearch.click();
				Thread.sleep(2000);
			}
			Log4J.logp.info("***************** Ended :: single_Search ******************");
		}
		catch (Exception e)
		{
			Assert.assertTrue(false, "single_Search failed");
			Log4J.logp.error("Problem found In ::: single_Search");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check User can able to search with Admission date
	 * 
	 * @author agupta
	 * @since 12/9/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-117 ::  Verify that user can able to search with Admission Date", priority = 13)
	public static void admission_Date()
	{
		try
		{
			int i = 34;
			Log4J.logp.info("***************** Started ::: admission_Date ********************");
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("SC00" + i);
			LandingPage_Lib.search_Status(i);
			Thread.sleep(2000);
			Log4J.logp.info("********************** Ended ::: admission_Date ******************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found - admission_Date");
			Assert.assertTrue(false, "admission_Date is failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check User can able to search with Discharge Date
	 * 
	 * @author agupta
	 * @since 12/9/2014
	 */
	@Test(description = "ezCAC_MVP_Reg_118 :: Verify that user can able to search with Discharge Date", priority = 14)
	public static void discharge_Date()
	{
		try
		{
			int i = 35;
			Log4J.logp.info("****************** Started ::: discharge_Date *********************");
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("SC00" + i);
			LandingPage_Lib.search_Status(i);
			Thread.sleep(2000);
			Log4J.logp.info("****************** Ended ::: discharge_Date *********************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found - discharge_Date");
			Assert.assertTrue(false, "discharge_Date is failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check user can able to search with DNFC Days
	 * 
	 * @author agupta
	 * @since 15/9/2014
	 */
	@Test(description = "ezCAC_MVP_Reg_2720 :: Verify that user can able to search with DNFC Days ", priority = 15)
	public static void dnfc_Days()
	{
		try
		{
			int i = 36;
			Log4J.logp.info("*************** Started :: dnfc_Days *****************");
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("SC00" + i);
			LandingPage_Lib.search_Status(i);
			Thread.sleep(2000);
			Log4J.logp.info("****************** Ended :: dnfc_Days ******************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found - dnfc_Days");
			Assert.assertTrue(false, "dnfc_Days is failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check that No. of Days behavior in DNFC Days search criteria
	 * 
	 * @author agupta
	 * @since 6/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-124 :: To verify behavior of No. of Days field of DNFC Days search criteria", priority = 16)
	public static void no_of_Days()
	{
		String strmessage;
		try
		{
			Log4J.logp.info("***************** Started ::: no_of_Days ***************");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("SC0042");
			Thread.sleep(2000);
			Common_Lib.IsCustomAlertPresent();
			strmessage = "Please Enter Valid Input";
			if (Common_Lib.CustomAlertText.equalsIgnoreCase(strmessage))
			{
				Log4J.logp.info("Validation is true");
			}
			Log4J.logp.info("******************* Ended ::: no_of_Days ******************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found - no_of_Days");
			Assert.assertTrue(false, "Wrong Validation Message");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This Script is to check that user can able to search with Billed Date
	 * 
	 * @author agupta
	 * @since 15/9/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2706 :: Verify that user can able to search with Billed Date", priority = 17)
	public static void billed_Date()
	{
		try
		{
			int i = 37;
			Log4J.logp.info("*************** Started :: billed_Date ***************");
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("SC00" + i);
			LandingPage_Lib.search_Status(i);
			Thread.sleep(2000);
			Log4J.logp.info("***************** Ended :: billed_Date ****************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found - billed_Date");
			Assert.assertTrue(false, "billed_Date is failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check that Supervisor can able to search with all valid parameters
	 * 
	 * @author agupta
	 * @since 12/9/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-111 : Verify that user can able to search with all valid parameters", priority = 18)
	public static void all_Parameters()
	{
		try
		{
			int i = 25;
			Log4J.logp.info("***************** Started ::: all_Parameters ****************");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("SC00" + i);
			LandingPage_Lib.search_Status(i);
			Thread.sleep(2000);
			Log4J.logp.info("***************** Ended ::: all_Parameters ******************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found In :: all_Parameters");
			Assert.assertTrue(false, "all_Parameters failed ");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is check that whenever user refresh the page default search should be displayed in search criteria
	 * 
	 * @author agupta
	 * @since 1/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-833 :: Verify the behavior when user refresh the browser before/after execution of saved search", priority = 19)
	public static void browser_Refresh()
	{
		try
		{
			Log4J.logp.info("************* Started :::: browser_Refresh *************");
			Thread.sleep(2000);
			int i = 39;
			SearchCriteria_Lib.search_Case("SC00" + i);
			Thread.sleep(2000);
			LandingPage_Lib.search_Status(i);
			driver.navigate().refresh();
			Thread.sleep(2000);
			LandingPage_Lib.default_Status_Stage();
			Log4J.logp.info("**************** Ended :::: browser_Refresh ********************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found - browser_Refresh");
			Assert.assertTrue(false, "browser_Refresh is failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check that After execution of search filters, user switch the role and come back to same role
	 * 
	 * @author agupta
	 * @since 1/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-133 :: After execution of search filters, user swithc the role and come back to same role", priority = 20)
	public static void switch_Roles()
	{
		try
		{
			Log4J.logp.info("****************** Started :: switch_Roles ********************");
			Thread.sleep(2000);
			int i = 40;
			SearchCriteria_Lib.search_Case("SC00" + i);
			Thread.sleep(2000);
			LandingPage_Lib.search_Status(i);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			LandingPage_Lib.default_Status_Stage();
			Log4J.logp.info("**************** Ended :: switch_Roles *********************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found - switch_Roles");
			Assert.assertTrue(false, "switch_Roles is failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check that User can save, delete, undo, and clear search successfully
	 * 
	 * @author agupta
	 * @since 1/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg_125 :: Verify that user can able to save,delete,undo, and clear search successfully", priority = 21)
	public static void save_Search()
	{
		try
		{
			int i = 38;
			Log4J.logp.info("**************** Started :: save_Search ************************");
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("SC00" + i);
			LandingPage_Lib.search_Status(i);
			searchcriteria_webe.lnk_SavedSearch.click();
			Thread.sleep(2000);
			Actions hover = new Actions(driver);
			WebElement e = driver.findElement(By.xpath("//span[text()='On Hold1']"));
			hover.moveToElement(e).build().perform();
			Thread.sleep(2000);
			searchcriteria_webe.delete_icon.click();
			Thread.sleep(1000);
			searchcriteria_webe.lnk_undo.click();
			searchcriteria_webe.lnk_ClearSearch.click();
			Thread.sleep(2000);
			LandingPage_Lib.default_Status_Stage();
			Log4J.logp.info("******************** Ended :: save_Search *******************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found - save_Search");
			Assert.assertTrue(false, "save_Search is failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check that selected search after logout activity
	 * 
	 * @author agupta
	 * @since 1/10/2014
	 */
	@Test(description = "ezCAC_MVP_134 :: Verify selected search and saved search after logout activity", priority = 22)
	public static void search_Logout()
	{
		try
		{
			Log4J.logp.info("**************** Started ::: search_Logout *******************");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			int i = 41;
			SearchCriteria_Lib.search_Case("SC00" + i);
			Thread.sleep(2000);
			LandingPage_Lib.search_Status(i);
			Login_Lib.logOut_App();
			Login_Lib.login("ag007");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			LandingPage_Lib.default_Status_Stage();
			Log4J.logp.info("****************** Ended ::: search_Logout ***********************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found - search_Logout");
			Assert.assertTrue(false, "search_Logout is failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check user can't give the same search name as already existing saved search name for same role
	 * 
	 * @author agupta
	 * @since 6/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-138 :: Verify that user can't give the same name as already existing saved search name for same role", priority = 23)
	public static void same_Name_Same_Role()
	{
		try
		{
			Log4J.logp.info("**************** Started :: same_Name_Same_Role *******************");
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("SC0043");
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("SC0043");
			landingp_webe.txt_alert.getText();
			String message = "Warning! Search query name duplicate.";
			Assert.assertEquals(landingp_webe.txt_alert.getText(), message);
			Log4J.logp.info(landingp_webe.txt_alert.getText());
			Log4J.logp.info("******************** Ended :: same_Name_Same_Role *****************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found - same_Name_Same_Role");
			Assert.assertTrue(false, "Message Not match");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check that User can give the same search name as already existing saved search for different role
	 * 
	 * @author agupta
	 * @since 6/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2800 ::: To verify that user can give the same name as already exisitng saved search for different role", priority = 24)
	public static void same_Name_Different_Role()
	{
		try
		{
			Log4J.logp.info("**************** Started ::: same_Name_Different_Role *****************");
			Thread.sleep(2000);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("SC0048");
			searchcriteria_webe.lnk_SavedSearch.click();
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("SC0048");
			searchcriteria_webe.lnk_SavedSearch.click();
			Log4J.logp.info("*************** Ended ::: same_Name_Different_Role *****************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found - same_Name_Different_Role");
			Assert.assertTrue(false, "same_Name_Different_Role is failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check that user can give the same name search name as already existing saved search name for other user
	 * 
	 * @author agupta
	 * @since 6/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2799 :: Verify that user can give the same name search name as already existing saved search name for other user", priority = 25)
	public static void same_Name_Different_User()
	{
		try
		{
			Log4J.logp.info("**************** Started :: same_Name_Different_User ******************");
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("SC0049");
			searchcriteria_webe.lnk_SavedSearch.click();
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("ag008");
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("SC0049");
			searchcriteria_webe.lnk_SavedSearch.click();
			Thread.sleep(2000);
			Log4J.logp.info("********************* Ended :: same_Name_Different_User ******************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found - same_Name_Different_User");
			Assert.assertTrue(false, "same_Name_Different_User is failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	/**
	 * This script is to check that "Amount" Completing the case
	 * 
	 * @author agupta
	 * @since 2/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-156 :: To verify Amount after completing the case", priority = 26)
	public static void amount_LandinPage()
	{
		String strtemp1[];
		String strDRG, strAmount, strLAmount;
		try
		{
			Log4J.logp.info("***************** Started :: amount_LandinPage *******************");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("ag010");
			Common_Lib.waitForObject(codingpnl_webe.lnk_Accept_Principal, "clickable", 20);
			codingpnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(2000);

			// to get amount from Grouping panel
			strDRG = groupingpnl_webe.txt_CoderDRG.getText();
			Log4J.logp.info("DRG = " + strDRG);
			strtemp1 = strDRG.split(":");
			strAmount = strtemp1[2].trim();
			strAmount = StringUtils.removeStart(strAmount, "$");
			Log4J.logp.info("Working Screen Amount =" + strAmount);

			groupingpnl_webe.btn_Done.click();
			Thread.sleep(2000);
			groupingpnl_webe.chk_takeMeQueue.click();
			groupingpnl_webe.btn_submitAfterDone.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 5);

			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("ag010");
			Thread.sleep(2000);

			strLAmount = landingp_webe.txt_Amount.getText();
			Log4J.logp.info("Landing Page Amount =" + strLAmount);

			if (strLAmount.equals(strAmount))
			{
				Log4J.logp.info("Working Screen Amount and Landing Page amount both are same");
				Assert.assertTrue(true, "Amount same");
			}
			else
			{
				Assert.assertTrue(false, "Amount not same");
			}
			Log4J.logp.info("**************** Ended :: amount_LandinPage *****************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found - amount_LandinPage");
			Assert.assertTrue(false, "amount_LandinPage  is failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("ag004");
		}
	}

	@AfterClass
	public static void LandingPageAfterClass()
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

			Log4J.logp.info("In AfterClass for Landing Page");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
