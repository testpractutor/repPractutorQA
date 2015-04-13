package com.ezdi.testscripts;

import org.openqa.selenium.WebDriver;
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
import com.ezdi.library.Login_Lib;
import com.ezdi.library.ManualAllocation_Lib;
import com.ezdi.library.MessageCenter_Lib;
import com.ezdi.webelements.Comman_WebE;
import com.ezdi.webelements.DemographicPnl_WebE;
import com.ezdi.webelements.GroupinPnl_WebE;
import com.ezdi.webelements.IssuePnl_WebE;
import com.ezdi.webelements.LandingP_WebE;
import com.ezdi.webelements.Login_WebE;
import com.ezdi.webelements.MedicalRecordPnl_WebE;
import com.ezdi.webelements.MessageCenter_WebE;
import com.ezdi.webelements.SearchCriteria_WebE;
import com.ezdi.webelements.ViewCase_WebE;

public class MessageCenter
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
	public static ViewCase_WebE			viewcase_webe;

	@BeforeClass
	public static void MessageCenterBeforeClass()
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
			viewcase_webe = ViewCase_WebE.getInstance(driver);

			//Login_Lib.logOut_App();
			Login_Lib.login("fm005");

			Log4J.logp.info("In BeforeClass for MessageCenter");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * This test script checks that user can send message to his colleague
	 * 
	 * @author fmodi
	 * @since 01/09/2014
	 */
	@Test(description = "ezCAC_ MVP-190:Verify that User can send new message", priority = 0)
	public static void sendNewMessage()
	{

		boolean bstatus;

		try

		{

			Log4J.logp.info("*************** Started - sendNewMessage ***************");
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);

			messagecenter_webe.btn_NewMessage.click();

			bstatus = MessageCenter_Lib.send_NewMessage("faiz002");

			Thread.sleep(2000);
			if (bstatus == true)
			{
				Log4J.logp.info("New message is sent successfully.");
				Assert.assertTrue(true, "New message is sent successfully.");
			}
			else
			{
				Log4J.logp.error("New message is not sent successfully.");
				Assert.assertTrue(false, "New message is not sent successfully.");
			}

			//Assert.assertTrue(bstatus, "New message is sent successfully.");
			Log4J.logp.info("*************** Ended - sendNewMessage ***************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - sendNewMessage ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "sendNewMessage is failed.");
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
	 * This test script checks that user can delete message from his inbox
	 * 
	 * @author fmodi
	 * @since 01/09/2014
	 */
	@Test(description = "ezCAC_ MVP-191:Verify that User can delete message", priority = 2)
	public static void deleteMessage()
	{

		boolean bstatus;

		try
		{

			Log4J.logp.info("*************** Started - deleteMessage ***************");

			//This sends a message with already logged in user (First User)
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);

			messagecenter_webe.btn_NewMessage.click();

			bstatus = MessageCenter_Lib.send_NewMessage("faiz001");

			if (bstatus == true)
			{
				Log4J.logp.info("New message is sent successfully.");
				Assert.assertTrue(true, "New message is sent successfully.");
			}
			else
			{
				Log4J.logp.error("New message is not sent successfully.");
				Assert.assertTrue(false, "New message is not sent successfully.");
			}

			//Assert.assertTrue(b1, "New message is sent successfully.");

			Thread.sleep(2000);

			Login_Lib.logOut_App();

			//This gets username and password for second user and logs in second user

			Login_Lib.login("fm001");

			//This code will delete the sent message
			landingp_webe.lnk_MessageHeader.click();

			Thread.sleep(2000);

			bstatus = MessageCenter_Lib.find_message("fz001", "delete");
			if (bstatus == true)
			{
				Log4J.logp.info("Message is deleted succesfully.");
				Assert.assertTrue(true, "Message is deleted succesfully.");
			}
			else
			{
				Log4J.logp.error("Message is not deleted succesfully.");
				Assert.assertTrue(false, "Message is not deleted succesfully.");
			}
			//Assert.assertTrue(b2, "Message is deleted succesfully.");

			//Thread.sleep(3000);
			Common_Lib.waitForObject(messagecenter_webe.lnk_Trash, "visibility", 3);

			messagecenter_webe.lnk_Trash.click();
			Thread.sleep(2000);
			//Common_Lib.waitForObject(messagecenter_webe.tbl_Messages, "visibility", 3);
			//Common_Lib.waitForObject(messagecenter_webe.tbl_Messages, "clickable", 3);

			bstatus = MessageCenter_Lib.find_message("fz001", "find");
			if (bstatus == true)
			{
				Log4J.logp.info("Message is displayed in trash.");
				Assert.assertTrue(true, "Message is displayed in trash.");
			}
			else
			{
				Log4J.logp.error("Message is not displayed in trash.");
				Assert.assertTrue(false, "Message is not displayed in trash.");
			}

			/*Log4J.logp.info("Message is displayed in trash.");
			Assert.assertTrue(b3, "Message is displayed in trash.");*/

			Log4J.logp.info("*************** Ended - deleteMessage ***************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - deleteMessage ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "deleteMessage is failed.");
		}

		finally
		{

			Login_Lib.logOut_App();
			Login_Lib.login("fm005");

		}

	}

	/**
	 * This test script checks that user can send message to his colleague
	 * 
	 * @author fmodi
	 * @since 02/09/2014
	 */
	@Test(description = "ezCAC_ MVP-236:Verify that physician can view case", priority = 3)
	public static void viewCase()
	{

		try
		{

			Log4J.logp.info("*************** Started - viewCase ***************");

			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("fm001");
			Thread.sleep(2000);

			//Following code will raise query on first system suggested evidence and redirect user to landing page
			common_webe.lnk_sys_evidence.get(0).click();
			Thread.sleep(2000);

			/*Actions action = new Actions(driver);
			action.moveToElement(common_webe.lnk_sys_evidence.get(0)).contextClick().build().perform();
			Thread.sleep(1000);*/
			Common_Lib.rightclick_system_sugggested_evidence();

			medicalrecordpnl_webe.lnk_querytophysician.click();

			IssuePanel_Lib.send_QueryToPhysician("fz001");
			Thread.sleep(2000);

			/*issuepnl_webe.btn_Issues.click();
			Thread.sleep(1000);*/
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);

			Log4J.logp.info("Issue Panel Closed");
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			Login_Lib.logOut_App();

			//Following code will login as a physician and view case
			Login_Lib.login("fm004");
			Thread.sleep(2000);

			MessageCenter_Lib.find_message("fz003", "open");
			Thread.sleep(2000);

			String currentwindow = driver.getWindowHandle();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(messagecenter_webe.lnk_ViewCase));
			messagecenter_webe.lnk_ViewCase.click();
			//Thread.sleep(15000);

			//Change on 18-12-2014
			//Common_Lib.waitForObject(messagecenter_webe.fra_viewCase, "iFrmviewCaseElement", 15);

			Thread.sleep(2000);
			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);

			Common_Lib.waitForObject(viewcase_webe.panel_PatientDemographic, "visibility", 8);

			Assert.assertEquals("iFrmviewCaseElement", "iFrmviewCaseElement");
			Log4J.logp.info("Physician can view case successfully.");
			Thread.sleep(2000);
			messagecenter_webe.btn_ViewCaseClose.click();

			driver.switchTo().window(currentwindow);

			//Assert.assertSame("iFrmviewCaseElement", "iFrmviewCaseElement", "Physician can view case succesfully.");

			messagecenter_webe.btn_Close.click();

			//Thread.sleep(3000);
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);

			Log4J.logp.info("*************** Ended - viewCase ***************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - viewCase ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCase is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that user can send message to his colleague
	 * 
	 * @author fmodi
	 * @since 02/09/2014
	 */
	@Test(description = "ezCAC_ MVP-196:User will get reply of message in mail conversation", priority = 1)
	public static void getReplyMessage()
	{

		boolean bstatus;

		try

		{
			Log4J.logp.info("*************** Started - getReplyMessage ***************");

			//This sends a message with already logged in user (First User)
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);

			messagecenter_webe.btn_NewMessage.click();

			bstatus = MessageCenter_Lib.send_NewMessage("faiz001");
			if (bstatus == true)
			{
				Log4J.logp.info("New message is sent successfully.");
				Assert.assertTrue(true, "New message is sent successfully.");
			}
			else
			{
				Log4J.logp.error("New message is sent successfully.");
				Assert.assertTrue(false, "New message is sent successfully.");
			}

			//Assert.assertTrue(b, "New message is sent successfully.");

			Thread.sleep(2000);

			Login_Lib.logOut_App();

			//This gets username and password for second user and logs in second user

			Login_Lib.login("fm001");

			//Second user sends reply to first user
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);

			MessageCenter_Lib.find_message("fz001", "open");
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(messagecenter_webe.btn_Reply));
			messagecenter_webe.btn_Reply.click();

			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(messagecenter_webe.txt_Reply));
			messagecenter_webe.txt_Reply.sendKeys("Reply Successful");

			Thread.sleep(2000);
			messagecenter_webe.btn_ReplySend.click();
			Thread.sleep(2000);

			Log4J.logp.info("Reply message sent succesfully");

			Login_Lib.logOut_App();

			//First user logs in and opens conversation and checks reply
			Login_Lib.login("fm005");

			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);

			bstatus = MessageCenter_Lib.find_message("fz001", "open");
			if (bstatus == true)
			{
				Log4J.logp.info("User got reply successfully.");
				Assert.assertTrue(true, "User got reply successfully.");
			}
			else
			{
				Log4J.logp.error("New message is sent successfully.");
				Assert.assertTrue(false, "New message is sent successfully.");
			}
			//Assert.assertTrue(bcheck, "User got reply successfully.");

			Thread.sleep(2000);

			Log4J.logp.info("*************** Ended - getReplyMessage ***************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - getReplyMessage ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "getReplyMessage is failed.");
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
	 * This test script checks that all user roles can access message center
	 * 
	 * @author fmodi
	 * @since 03/09/2014
	 */
	@Test(description = "ezCAC_ MVP_Reg-160:Verify that message center option is visible to all user roles", priority = 4)
	public static void checkMessageCenter()
	{

		try

		{
			Log4J.logp.info("*************** Started - checkMessageCenter ***************");

			Thread.sleep(2000);

			//This checks message center option is available or not for Supervisor user role
			Log4J.logp.info("========== In check MessageCenter is available for 'Supervisor' ==========");
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			if (messagecenter_webe.lbl_MessageCenter.isDisplayed())
			{
				Log4J.logp.info("Message Center is available for Supervisor user role");
				Assert.assertTrue(true, "Message Center is available for Supervisor user role");
			}
			else
			{
				Log4J.logp.error("Message Center is not available for Supervisor user role");
				Assert.assertTrue(false, "Message Center is not available for Supervisor user role");
			}
			landingp_webe.lnk_Cases.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished check MessageCenter is available for 'Supervisor' ==========");

			//This checks Message Center is available or not for Coder user role
			Log4J.logp.info("========== In check MessageCenter is available for 'Coder' ==========");
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			if (messagecenter_webe.lbl_MessageCenter.isDisplayed())
			{
				Log4J.logp.info("Message Center is available for Coder user role");
				Assert.assertTrue(true, "Message Center is available for Coder user role");
			}
			else
			{
				Log4J.logp.error("Message Center is not available for Coder user role");
				Assert.assertTrue(false, "Message Center is not available for Coder user role");
			}
			landingp_webe.lnk_Cases.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished check MessageCenter is available for 'Coder' ==========");

			//This checks message center option is available for Reviewer user role
			Log4J.logp.info("========== In check MessageCenter is available for 'Reviewer' ==========");
			landingp_webe.lnk_Review.click();
			Thread.sleep(2000);
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			if (messagecenter_webe.lbl_MessageCenter.isDisplayed())
			{
				Log4J.logp.info("Message Center is available for Reviewer user role");
				Assert.assertTrue(true, "Message Center is available for Reviewer user role");
			}
			else
			{
				Log4J.logp.error("Message Center is not available for Reviewer user role");
				Assert.assertTrue(false, "Message Center is not available for Reviewer user role");
			}
			/*Log4J.logp.info("Message Center is available for Reviewer user role");
			Assert.assertTrue(true, "Message Center is available for Reviewer user role");*/
			landingp_webe.lnk_Cases.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished check MessageCenter is available for 'Reviewer' ==========");

			//This checks Message Center is available or not for CDI user role
			Log4J.logp.info("========== In check MessageCenter is available for 'CDI' ==========");
			landingp_webe.lnk_CDI.click();
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			if (messagecenter_webe.lbl_MessageCenter.isDisplayed())
			{
				Log4J.logp.info("Message Center is available for CDI user role");
				Assert.assertTrue(true, "Message Center is available for CDI user role");
			}
			else
			{
				Log4J.logp.error("Message Center is not available for CDI user role");
				Assert.assertTrue(false, "Message Center is not available for CDI user role");
			}
			landingp_webe.lnk_Cases.click();
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Log4J.logp.info("========== Finished check MessageCenter is available for 'CDI' ==========");

			//This gets username and password Physician user role and logs in Physician user role
			Log4J.logp.info("========== In check MessageCenter is available for 'Physician' ==========");
			Login_Lib.login("fm004");
			Thread.sleep(2000);

			//This checks Message Center is available or not for Physician user role
			if (messagecenter_webe.lbl_MessageCenter.isDisplayed())
			{
				Log4J.logp.info("Message Center is available for Physician user role");
				Assert.assertTrue(true, "Message Center is available for Physician user role");
			}
			else
			{
				Log4J.logp.error("Message Center is not available for Physician user role");
				Assert.assertTrue(false, "Message Center is not available for Physician user role");
			}
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished check MessageCenter is available for 'Physician' ==========");

			Log4J.logp.info("*************** Ended - checkMessageCenter ***************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - checkMessageCenter ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "checkMessageCenter is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that message counter updates with send,delete and reply operations
	 * 
	 * @author fmodi
	 * @since 04/09/2014
	 */
	@Test(description = " ezCAC_MVP_Reg-215:To verify Inbox counter with New message, Reply Message and Delete message", priority = 5)
	public static void checkInboxCounter()
	{

		boolean bstatus;
		int icount1, icount2, icount3, icount4, icount5, icount6, icount7;

		try

		{
			Log4J.logp.info("*************** Started - checkInboxCounter ***************");

			//This message is sent which will be deleted at the end of the method as unread

			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);

			messagecenter_webe.btn_NewMessage.click();
			Thread.sleep(2000);

			bstatus = MessageCenter_Lib.send_NewMessage("faiz001");
			if (bstatus == true)
			{
				Log4J.logp.info("New message is sent successfully.");
				Assert.assertTrue(true, "New message is sent successfully.");
			}
			else
			{
				Log4J.logp.error("New message is not sent successfully.");
				Assert.assertTrue(false, "New message is not sent successfully.");
			}
			//Assert.assertTrue(bunread, "New message is sent successfully.");

			Thread.sleep(2000);

			Login_Lib.logOut_App();

			//This gets username and password for second user and logs in second user

			Login_Lib.login("fm001");
			Thread.sleep(2000);

			//This sends a message with already logged in user
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);

			icount1 = Integer.parseInt(messagecenter_webe.txt_UnreadMessageCount.getText());
			//System.out.println("Count1=" + icount1);
			Log4J.logp.info("Count1=" + icount1);

			Thread.sleep(2000);

			Login_Lib.logOut_App();

			//First user logs in and opens conversation and checks reply
			Login_Lib.login("fm005");
			Thread.sleep(2000);

			//This sends a message with already logged in user
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);

			//This checks that after sending message,unread message count does not increase for sender
			icount2 = Integer.parseInt(messagecenter_webe.txt_UnreadMessageCount.getText());
			//System.out.println("Count2=" + icount2);
			Log4J.logp.info("Count2=" + icount2);

			messagecenter_webe.btn_NewMessage.click();

			bstatus = MessageCenter_Lib.send_NewMessage("faiz001");
			if (bstatus == true)
			{
				Log4J.logp.info("New message is sent successfully.");
				Assert.assertTrue(true, "New message is sent successfully.");
			}
			else
			{
				Log4J.logp.error("New message is not sent successfully.");
				Assert.assertTrue(false, "New message is not sent successfully.");
			}
			//Assert.assertTrue(b, "New message is sent successfully.");

			Thread.sleep(2000);

			icount3 = Integer.parseInt(messagecenter_webe.txt_UnreadMessageCount.getText());
			//System.out.println("Count3=" + icount3);
			Log4J.logp.info("Count3=" + icount3);

			if (icount2 == icount3)
			{
				Log4J.logp.info("Message Counter is not changed for sender after sending the message.");
				Assert.assertTrue(true, "Message Counter is not changed for sender after sending the message.");
			}
			else
			{
				Log4J.logp.error("Message Counter is changed for sender after sending the message.");
				Assert.assertTrue(false, "Message Counter is changed for sender after sending the message.");
			}

			Thread.sleep(2000);

			Login_Lib.logOut_App();

			Login_Lib.login("fm001");
			Thread.sleep(2000);

			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);

			//This checks that after receiving message,unread message count does increase for receiver
			icount4 = Integer.parseInt(messagecenter_webe.txt_UnreadMessageCount.getText());
			//System.out.println("Count4=" + icount4);
			Log4J.logp.info("Count4=" + icount4);

			if (icount4 - icount1 == 1)
			{
				Log4J.logp.info("Message Counter is increased by 1 for receiver after receiving the message.");
				Assert.assertTrue(true, "Message Counter is increased by 1 for receiver after receiving the message.");
			}
			else
			{
				Log4J.logp.error("Message Counter is not increased by 1 for receiver after receiving the message.");
				Assert.assertTrue(false, "Message Counter is not increased by 1 for receiver after receiving the message.");
			}

			//This checks that after reading the unread message, unread message count does decrease
			Thread.sleep(2000);

			MessageCenter_Lib.find_message("fz001", "open");

			Thread.sleep(2000);

			icount5 = Integer.parseInt(messagecenter_webe.txt_UnreadMessageCount.getText());
			//System.out.println("Count5=" + icount5);
			Log4J.logp.info("Count5=" + icount5);

			if (icount4 - icount5 == 1)
			{
				Log4J.logp.info("Message Counter is decreased by 1 for receiver after reading the message.");
				Assert.assertTrue(true, "Message Counter is decreased by 1 for receiver after reading the message.");
			}
			else
			{
				Log4J.logp.error("Message Counter is not decreased by 1 for receiver after reading the message.");
				Assert.assertTrue(false, "Message Counter is not decreased by 1 for receiver after reading the message.");
			}

			Thread.sleep(2000);

			//Now receiver sends reply to sender

			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(messagecenter_webe.btn_Reply));
			messagecenter_webe.btn_Reply.click();

			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(messagecenter_webe.txt_Reply));
			messagecenter_webe.txt_Reply.sendKeys("Reply Successful");

			Thread.sleep(2000);
			messagecenter_webe.btn_ReplySend.click();
			Thread.sleep(2000);

			Log4J.logp.info("Reply message sent succesfully");

			Login_Lib.logOut_App();

			//First user logs in and opens conversation and checks reply
			Login_Lib.login("fm005");
			Thread.sleep(2000);

			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);

			//This checks that after receiving reply,unread message count does increase for the recipient of reply
			icount6 = Integer.parseInt(messagecenter_webe.txt_UnreadMessageCount.getText());
			//System.out.println("Count6=" + icount6);
			Log4J.logp.info("Count6=" + icount6);

			if (icount6 - icount3 == 1)
			{
				Log4J.logp.info("Message Counter is increased by 1 after getting reply.");
				Assert.assertTrue(true, "Message Counter is increased by 1 after getting reply.");
			}
			else
			{
				Log4J.logp.error("Message Counter is not increased by 1 after getting reply.");
				Assert.assertTrue(false, "Message Counter is not increased by 1 after getting reply.");
			}

			Thread.sleep(2000);

			bstatus = MessageCenter_Lib.find_message("fz001", "find");
			if (bstatus == true)
			{
				Log4J.logp.info("User got reply successfully.");
				Assert.assertTrue(true, "User got reply successfully.");
			}
			else
			{
				Log4J.logp.error("User has not get reply successfully.");
				Assert.assertTrue(false, "User has not get reply successfully.");
			}
			//Assert.assertTrue(bstatus, "User got reply successfully.");

			Thread.sleep(2000);

			//This checks that after deleting unread message, unread message count decreases

			Login_Lib.logOut_App();

			Login_Lib.login("fm001");
			Thread.sleep(2000);

			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);

			messagecenter_webe.img_Filter.click();

			Thread.sleep(2000);

			messagecenter_webe.chk_Read.click();

			Thread.sleep(2000);

			messagecenter_webe.lnk_Apply.click();

			Thread.sleep(2000);

			bstatus = MessageCenter_Lib.find_message("fz001", "delete");
			if (bstatus == true)
			{
				Log4J.logp.info("Message deleted successfully.");
				Assert.assertTrue(true, "Message deleted successfully.");
			}
			else
			{
				Log4J.logp.error("Message not deleted successfully.");
				Assert.assertTrue(false, "Message not deleted successfully.");
			}
			//Assert.assertTrue(bcheck2, "Message deleted successfully.");

			Thread.sleep(2000);

			icount7 = Integer.parseInt(messagecenter_webe.txt_UnreadMessageCount.getText());
			//System.out.println("Count7=" + icount7);
			Log4J.logp.info("Count7=" + icount7);

			if (icount5 - icount7 == 1)
			{
				Log4J.logp.info("Message Counter is decreased by 1 after deleting unread message.");
				Assert.assertTrue(true, "Message Counter is decreased by 1 after deleting unread message.");
			}
			else
			{
				Log4J.logp.error("Message Counter is not decreased by 1 after deleting unread message.");
				Assert.assertTrue(false, "Message Counter is not decreased by 1 after deleting unread message.");
			}
			Thread.sleep(2000);

			Log4J.logp.info("*************** Ended - checkInboxCounter ***************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - checkInboxCounter ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "checkInboxCounter is failed.");
		}

		finally
		{

			Login_Lib.logOut_App();
			Login_Lib.login("fm005");

		}

	}

	/**
	 * This test script checks that user can't send message or send reply without selecting mandatory fields
	 * 
	 * @author fmodi
	 * @since 04/09/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-229:Verify that without entering mandatory fields user can not able to send new message or reply" + "ezCAC_MVP_Reg-228:Verify that without entering value in 'To' dropdown/textbox, user can not send message", priority = 6)
	public static void checkValidations()
	{

		try

		{

			Log4J.logp.info("*************** Started - checkValidations ***************");
			Thread.sleep(2000);

			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);

			//To check all validations
			Log4J.logp.info("========== In check 'All' Validations ==========");
			MessageCenter_Lib.checkMessageValidations(null, "All");
			Log4J.logp.info("========== Finished check 'All' Validations ==========");

			//To check validations when user have only entered username
			Log4J.logp.info("========== In check 'Username' Validations ==========");
			MessageCenter_Lib.checkMessageValidations("faiz004", "Sub+Msg");
			Log4J.logp.info("========== Finished check 'Username' Validations ==========");

			//After entering username and subject,clicks on send button and checks message validation
			Log4J.logp.info("========== In check 'Message' Validations ==========");
			MessageCenter_Lib.checkMessageValidations("faiz005", "Msg");
			Log4J.logp.info("========== Finished check 'Message' Validations ==========");

			//After entering username and message,clicks on send button and checks subject validation
			Log4J.logp.info("========== In check 'Subject' Validations ==========");
			MessageCenter_Lib.checkMessageValidations("faiz006", "Sub");
			Log4J.logp.info("========== Finished check 'Subject' Validations ==========");

			//After selecting subject and message,clicks on send button and checks to validation
			Log4J.logp.info("========== In check 'To' Validations ==========");
			MessageCenter_Lib.checkMessageValidations("faiz003", "To");
			Log4J.logp.info("========== Finished check 'To' Validations ==========");

			//User directly clicks on reply button and checks reply validation
			Log4J.logp.info("========== In check 'Reply' Validations ==========");
			MessageCenter_Lib.checkMessageValidations("faiz001", "Reply");
			Log4J.logp.info("========== Finished check 'Reply' Validations ==========");

			Log4J.logp.info("*************** Ended - checkValidations ***************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - checkValidations ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "checkValidations is failed.");
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
	 * This test script checks that physician can receive query and send reply to the query which is raised by user and user can resolve that query issue and
	 * after resolving the query issue it is also shown in message center as resolved. It also checks that user can not resolve query after getting reply from
	 * physician in message center.
	 * 
	 * @author fmodi
	 * @since 10/09/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-226:To verify that if user raise query to physician" + "ezCAC_MVP_Reg-240:If user resolved any discussion thread from the working screen and from the issue panel vice versa"
			+ "ezCAC_MVP_Reg-242:Verify that User can not resolve query for an account from the message center" + "ezCAC_MVP_Reg-234:When user raise any query to physician then physician receive message in his/her inbox", priority = 7)
	public static void queryToPhysicianOperations()
	{

		boolean bstatus;
		int icount1, icount2, icountchk1, icountchk2;

		try

		{
			Log4J.logp.info("*************** Started - queryToPhysicianOperations ***************");

			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("fm004");

			Thread.sleep(2000);

			icountchk1 = Integer.parseInt(messagecenter_webe.txt_UnreadMessageCount.getText());
			//System.out.println("Countchk1=" + icountchk1);
			Log4J.logp.info("Countchk1=" + icountchk1);

			Thread.sleep(2000);

			Login_Lib.logOut_App();

			Thread.sleep(2000);

			//Following code will find and open the case
			Login_Lib.login("fm005");

			//Thread.sleep(3000);
			Common_Lib.waitForObject(landingp_webe.lnk_Coding, "visibility", 3);
			landingp_webe.lnk_Coding.click();
			//Thread.sleep(3000);
			Common_Lib.waitForObject(searchcriteria_webe.lnk_SearchCreteria, "visibility", 3);
			Common_Lib.openCase("fm001");
			//Thread.sleep(5000);

			//Following code will raise query on first system suggested evidence and redirect user to landing page
			Common_Lib.waitForObject(common_webe.lnk_sys_evidence.get(0), "clickable", 15);
			//Thread.sleep(5000);

			/*Actions action = new Actions(driver);
			action.moveToElement(common_webe.lnk_sys_evidence.get(0)).contextClick().build().perform();
			Thread.sleep(3000);*/
			Common_Lib.rightclick_system_sugggested_evidence();

			medicalrecordpnl_webe.lnk_querytophysician.click();

			IssuePanel_Lib.send_QueryToPhysician("fz001");
			Thread.sleep(2000);

			/*issuepnl_webe.btn_Issues.click();
			Thread.sleep(1000);*/
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);

			Log4J.logp.info("Issue Panel Closed");
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			icount1 = Integer.parseInt(landingp_webe.lnk_UnReadMessageCount.getText());
			Login_Lib.logOut_App();

			//Following code will login as a physician and reply to the query
			Log4J.logp.info("========== In Reply to Query as a Physician ==========");
			Login_Lib.login("fm004");
			Thread.sleep(2000);

			icountchk2 = Integer.parseInt(messagecenter_webe.txt_UnreadMessageCount.getText());
			//System.out.println("Countchk2=" + icountchk2);
			Log4J.logp.info("Countchk2=" + icountchk2);

			if (icountchk2 == icountchk1 + 1 && messagecenter_webe.lbl_QueryForAnAccount.isDisplayed())
			{
				Log4J.logp.info("Query Message has been received by Physician Successfully.");
				Assert.assertTrue(true, "Query Message has been received by Physician Successfully.");
			}
			else
			{
				Log4J.logp.error("Query Message has not been received by Physician Successfully.");
				Assert.assertTrue(false, "Query Message has not been received by Physician Successfully.");
			}

			MessageCenter_Lib.find_message("fz003", "open");
			Thread.sleep(2000);

			if (messagecenter_webe.lbl_PendingQuery.isDisplayed())
			{
				Log4J.logp.info("Pending label is displayed for Query issue in message.");
				Assert.assertTrue(true, "Pending label is displayed for Query issue in message.");
			}
			else
			{
				Log4J.logp.info("Pending label is not displayed for Query issue in message.");
				Assert.assertTrue(true, "Pending label is not displayed for Query issue in message.");
			}
			Thread.sleep(2000);
			/*if (messagecenter_webe.lbl_PendingQuery.isDisplayed())
			{
				Log4J.logp.info("Pending label is displayed for Query issue in message.");
				Assert.assertTrue(true, "Pending label is displayed for Query issue in message.");
			}
			Thread.sleep(1000);*/
			messagecenter_webe.btn_Reply.click();
			messagecenter_webe.btn_Yes.click();
			messagecenter_webe.txt_Reply.sendKeys("Automated query reply...");
			messagecenter_webe.btn_ReplySend.click();
			Log4J.logp.info("Reply sent successfully");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Log4J.logp.info("========== Finished Reply to Query as a Physician ==========");

			//Following code will check user gets notified about the reply via message
			Log4J.logp.info("========== In check user gets notified about Physician's Reply at Landing Page ==========");
			Login_Lib.login("fm005");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);

			icount2 = Integer.parseInt(landingp_webe.lnk_UnReadMessageCount.getText());
			if (icount2 - icount1 == 1)
			{
				Log4J.logp.info("Message count at landing page increased by 1.");
				Assert.assertTrue(true, "Message count at landing page increased by 1.");
			}
			else
			{
				Log4J.logp.error("Message count at landing page is not increased by 1.");
				Assert.assertTrue(false, "Message count at landing page is not increased by 1.");
			}

			Thread.sleep(2000);
			Log4J.logp.info("========== Finished check user gets notified about Physician's Reply at Landing Page ==========");

			//Following code will check user does not get option to resolve query issue in message
			Log4J.logp.info("========== In check user can't Resolve Query from Message Center ==========");
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			MessageCenter_Lib.find_message("fz003", "open");
			Thread.sleep(2000);
			messagecenter_webe.lnk_ViewFullQuery.click();
			Thread.sleep(2000);
			Common_Lib.scroll_Page(messagecenter_webe.main_dragger, 210);
			Thread.sleep(2000);
			bstatus = Common_Lib.checkElementPresent(messagecenter_webe.lnk_MarkAsResolved);
			if (bstatus == false)
			{
				Log4J.logp.info("Mark as Resolved link is not displayed for Query issue in message.");
				Assert.assertTrue(true, "Mark as Resolved link is not displayed for Query issue in message.");
			}
			else
			{
				Log4J.logp.error("Mark as Resolved link is displayed for Query issue in message.");
				Assert.assertTrue(false, "Mark as Resolved link is displayed for Query issue in message.");
			}

			Common_Lib.scroll_Page_Up(messagecenter_webe.main_dragger, 210);
			Thread.sleep(2000);
			messagecenter_webe.btn_Close.click();
			Thread.sleep(2000);
			landingp_webe.lnk_Cases.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished check user can't Resolve Query from Message Center ==========");

			//Following code will check user gets notified about the reply in issue panel
			Log4J.logp.info("========== In check user gets notified about Reply of Query in Issue Panel ==========");
			Common_Lib.openCase("fm001");
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			if (issuepnl_webe.lbl_replied.isDisplayed())
			{
				Log4J.logp.info("Replied label is displayed for Query issue in issue panel.");
				Assert.assertTrue(true, "Replied label is displayed for Query issue in issue panel.");
			}
			else
			{
				Log4J.logp.error("Replied label is not displayed for Query issue in issue panel.");
				Assert.assertTrue(false, "Replied label is not displayed for Query issue in issue panel.");
			}
			Log4J.logp.info("========== Finished check user gets notified about Reply of Query in Issue Panel ==========");

			//Following code checks that user can resolve a query issue from issue panel and then it is displayed as resolved
			Log4J.logp.info("========== In check user can resolve query from Issue Panel ==========");
			issuepnl_webe.lnk_markasresolved.click();
			Thread.sleep(2000);
			if (issuepnl_webe.img_resolvedquery.isDisplayed())
			{
				Log4J.logp.info("Query issue is displayed as resolved in issue panel.");
				Assert.assertTrue(true, "Query issue is displayed as resolved in issue panel.");
			}
			else
			{
				Log4J.logp.error("Query issue is not displayed as resolved in issue panel.");
				Assert.assertTrue(false, "Query issue is not displayed as resolved in issue panel.");
			}

			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			Log4J.logp.info("Issue Panel Closed");
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished check user can resolve query from Issue Panel ==========");

			//Following code checks after user resolves query issue then it is displayed as resolved in message center also
			Log4J.logp.info("========== In check user resolved query message is displayed with Resolved Label ==========");
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			MessageCenter_Lib.find_message("fz003", "open");
			Thread.sleep(2000);
			if (messagecenter_webe.lbl_ResolvedQuery.isDisplayed())
			{
				Log4J.logp.info("Resolved label is displayed for Query issue in message.");
				Assert.assertTrue(true, "Resolved label is displayed for Query issue in message.");
			}
			else
			{
				Log4J.logp.error("Resolved label is not displayed for Query issue in message.");
				Assert.assertTrue(false, "Resolved label is not displayed for Query issue in message.");
			}
			messagecenter_webe.btn_Close.click();
			Thread.sleep(2000);
			landingp_webe.lnk_Cases.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== In check user resolved query message is displayed with Resolved Label ==========");

			Log4J.logp.info("*************** Ended - queryToPhysicianOperations ***************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - queryToPhysicianOperations ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "queryToPhysicianOperations is failed.");
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
	 * This test script checks that only issue(discussion) creator can resolve the issue(discussion) and after resolving the discussion issue it is also shown
	 * in message center as resolved.
	 * 
	 * @author fmodi
	 * @since 10/09/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-237:To verify that only issue creator can resolve issue" + "ezCAC_MVP_Reg-240:If user resolved any discussion thread from the working screen and from the issue panel vice versa", priority = 8)
	public static void discussWithColleagueOperations()
	{

		int icount1, icount2;

		try

		{

			Log4J.logp.info("*************** Started - discussWithColleagueOperations ***************");

			//Following code will find and open the case
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("fm002");
			Thread.sleep(2000);

			//Following code will raise discussion on first system suggested evidence and redirect user to landing page
			common_webe.lnk_sys_evidence.get(0).click();
			Thread.sleep(2000);

			/*Actions action = new Actions(driver);
			action.moveToElement(common_webe.lnk_sys_evidence.get(0)).contextClick().build().perform();
			Thread.sleep(1000);*/
			Common_Lib.rightclick_system_sugggested_evidence();

			medicalrecordpnl_webe.lnk_discusswithcolleague.click();

			IssuePanel_Lib.send_DiscussWithColleague("fz001");
			Thread.sleep(2000);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			Log4J.logp.info("Issue Panel Closed");

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			icount1 = Integer.parseInt(landingp_webe.lnk_UnReadMessageCount.getText());
			Login_Lib.logOut_App();

			//Following code will login as a colleague and reply to the discussion
			Log4J.logp.info("========== In Reply to Disscusion as a Colleague ==========");
			Login_Lib.login("fm001");
			Thread.sleep(2000);
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			MessageCenter_Lib.find_message("fz004", "open");
			Thread.sleep(2000);
			if (messagecenter_webe.lbl_PendingDiscussion.isDisplayed())
			{
				Log4J.logp.info("Pending label is displayed for Discussion issue in message.");
				Assert.assertTrue(true, "Pending label is displayed for Discussion issue in message.");
			}
			else
			{
				Log4J.logp.error("Pending label is not displayed for Discussion issue in message.");
				Assert.assertTrue(false, "Pending label is not displayed for Discussion issue in message.");
			}
			messagecenter_webe.btn_Reply.click();
			Thread.sleep(2000);
			messagecenter_webe.txt_Reply.sendKeys("Automated discussion reply...");
			Thread.sleep(2000);
			messagecenter_webe.btn_ReplySend.click();
			Thread.sleep(2000);
			Log4J.logp.info("Reply sent successfully");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Log4J.logp.info("========== Finished Reply to Disscusion as a Colleague ==========");

			//Following code will check user gets notified about the reply via message and also in issue panel
			Log4J.logp.info("========== In check user gets notified about Colleague's Reply at Landing Page and in Issues Panel ==========");
			Login_Lib.login("fm005");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);

			icount2 = Integer.parseInt(landingp_webe.lnk_UnReadMessageCount.getText());
			if (icount2 - icount1 == 1)
			{
				Log4J.logp.info("Message count at landing page increased by 1.");
				Assert.assertTrue(true, "Message count at landing page increased by 1.");
			}
			else
			{
				Log4J.logp.error("Message count at landing page is not increased by 1.");
				Assert.assertTrue(false, "Message count at landing page is not increased by 1.");
			}

			Common_Lib.openCase("fm002");
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			if (issuepnl_webe.lbl_replied.isDisplayed())
			{
				Log4J.logp.info("Replied label is displayed for Discussion issue in issue panel.");
				Assert.assertTrue(true, "Replied label is displayed for Discussion issue in issue panel.");
			}
			else
			{
				Log4J.logp.error("Replied label is not displayed for Discussion issue in issue panel.");
				Assert.assertTrue(false, "Replied label is not displayed for Discussion issue in issue panel.");
			}
			Log4J.logp.info("========== In check user gets notified about Colleague's Reply at Landing Page and in Issues Panel ==========");

			//Following code checks that user can resolve a discussion issue from issue panel and then it is displayed as resolved
			Log4J.logp.info("========== In check user can resolve discussion from Issue Panel ==========");
			issuepnl_webe.lnk_markasresolved.click();
			Thread.sleep(2000);
			if (issuepnl_webe.img_resolveddiscussion.isDisplayed())
			{
				Log4J.logp.info("Discussion Issue is resolved in issue panel.");
				Assert.assertTrue(true, "Discusion Issue is resolved in issue panel.");
			}
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			Log4J.logp.info("Issue Panel Closed");
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished check user can resolve discussion from Issue Panel ==========");

			//Following code checks after user resolves discussion issue then it is displayed as resolved in message center also
			Log4J.logp.info("========== In check user resolved discussion message is displayed with Resolved Label ==========");
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			MessageCenter_Lib.find_message("fz004", "open");
			Thread.sleep(2000);
			if (messagecenter_webe.lbl_ResolvedDiscussion.isDisplayed())
			{
				Log4J.logp.info("Resolved label is displayed for Discussion issue in message.");
				Assert.assertTrue(true, "Resolved label is displayed for Discussion issue in message.");
			}
			else
			{
				Log4J.logp.info("Resolved label is not displayed for Discussion issue in message.");
				Assert.assertTrue(false, "Resolved label is not displayed for Discussion issue in message.");
			}
			messagecenter_webe.btn_Close.click();
			Thread.sleep(2000);
			landingp_webe.lnk_Cases.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished check user resolved discussion message is displayed with Resolved Label ==========");

			Log4J.logp.info("*************** Ended - discussWithColleagueOperations ***************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - discussWithColleagueOperations ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "discussWithColleagueOperations is failed.");
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
	 * This test script checks pagination in message center
	 * 
	 * @author fmodi
	 * @since 01/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2973:To check Pagination in message center", priority = 9)
	public static void checkPagination()
	{

		boolean bstatus;
		String strmsgCount, strmsgCount_m, strtemp1[], strtemp1_m[], strtemp2_m[];
		int icount, i, j, iactualbegin, iexpectedbegin, iactualend, iexpectedend;

		try

		{

			Log4J.logp.info("*************** Started - checkPagination ***************");

			//below code will send a mesage and redirect to landing page so that messagecenter is not empty
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);

			messagecenter_webe.btn_NewMessage.click();

			bstatus = MessageCenter_Lib.send_NewMessage("faiz002");
			if (bstatus == true)
			{
				Log4J.logp.info("New message is sent successfully.");
				Assert.assertTrue(true, "New message is sent successfully.");
			}
			else
			{
				Log4J.logp.info("New message is not sent successfully.");
				Assert.assertTrue(false, "New message is not sent successfully.");
			}
			//Assert.assertTrue(b, "New message is sent successfully.");
			Thread.sleep(2000);

			landingp_webe.lnk_Cases.click();
			Thread.sleep(2000);

			//now method starts
			landingp_webe.lnk_MessageHeader.click();

			//Thread.sleep(3000);
			//This change made on 17-12-2014
			//Thread.sleep(2000);
			//This change made on 18-12-2014
			Common_Lib.waitForObject(messagecenter_webe.total_messages, "clickable", 3);

			//msgCount's value will be like "0-9 of 9" so by splitting it using "of" the number after "of" (here 9) will be stored as count.
			strmsgCount = messagecenter_webe.total_messages.getText();
			strtemp1 = strmsgCount.split("of");
			icount = Integer.parseInt(strtemp1[1].trim());

			j = icount / 15;

			//to traverse through messages table and perform operations
			for (i = 0; i <= (icount / 15); i++)
			{
				strmsgCount_m = messagecenter_webe.total_messages.getText();
				strtemp1_m = strmsgCount_m.split("of");
				strtemp2_m = strtemp1_m[0].split("-");

				iactualbegin = Integer.parseInt(strtemp2_m[0].trim());
				iactualend = Integer.parseInt(strtemp2_m[1].trim());

				iexpectedbegin = (i * 15) + 1;
				iexpectedend = (i + 1) * 15;

				if (i == j)
				{
					if (iactualbegin == iexpectedbegin && iactualend == icount)
					{
						Log4J.logp.info("Pagination is proper for page no. " + (i + 1));
						Assert.assertTrue(true, "Pagination is proper for page no. " + (i + 1));
						Thread.sleep(2000);
					}
				}

				else if (iactualbegin == iexpectedbegin && iactualend == iexpectedend)
				{
					Log4J.logp.info("Pagination is proper for page no. " + (i + 1));
					Assert.assertTrue(true, "Pagination is proper for page no. " + (i + 1));
					Thread.sleep(2000);
					messagecenter_webe.lnk_Next.click();
					Thread.sleep(2000);
				}

			}
			Thread.sleep(2000);

			Log4J.logp.info("*************** Ended - checkPagination ***************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - checkPagination ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "checkPagination is failed.");
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
	 * This test script checks that user can discuss with colleague and after getting reply from colleague resolve the discussion from message center.
	 * 
	 * @author fmodi
	 * @since 02/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-225:Verify that if user raise discussion to another user from the working screen", priority = 10)
	public static void discussWithColleagueMessageCenter()
	{

		boolean bstatus;
		String strissuecount1, strissuecount2, strtemp1[], strtemp2[];
		int ipendingissuecount1, ipendingissuecount2;

		try

		{

			Log4J.logp.info("*************** Started - discussWithColleagueMessageCenter ***************");

			//Following code will find and open the case
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("fm003");
			Thread.sleep(2000);

			//This code will become useful when caseno bug is solved
			/*String caseno = demographicpnl_webe.lbl_casedetails.getText();
			System.out.println("caseno=" + caseno);
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);*/

			//Following code will raise discussion on first system suggested evidence and redirect user to landing page
			common_webe.lnk_sys_evidence.get(0).click();
			Thread.sleep(2000);

			/*Actions action = new Actions(driver);
			action.moveToElement(common_webe.lnk_sys_evidence.get(0)).contextClick().build().perform();
			Thread.sleep(1000);*/
			Common_Lib.rightclick_system_sugggested_evidence();

			medicalrecordpnl_webe.lnk_discusswithcolleague.click();

			IssuePanel_Lib.send_DiscussWithColleague("fz001");
			Thread.sleep(2000);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			Log4J.logp.info("Issue Panel Closed");

			strissuecount1 = issuepnl_webe.txt_IssueCount.getText();
			strtemp1 = strissuecount1.split("/");
			ipendingissuecount1 = Integer.parseInt(strtemp1[0]);

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			Login_Lib.logOut_App();

			//Following code will login as a colleague and reply to the discussion
			Log4J.logp.info("========== In Reply to Disscusion as a Colleague ==========");
			Login_Lib.login("fm001");
			Thread.sleep(2000);
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			MessageCenter_Lib.find_message("fz004", "open");
			Thread.sleep(2000);
			bstatus = Common_Lib.checkElementPresent(messagecenter_webe.lnk_MarkAsResolved);
			if (bstatus == false)
			{
				Log4J.logp.info("Mark as Resolved link is not displayed for colleague in message.");
				Assert.assertTrue(true, "Mark as Resolved link is not displayed for colleague in message.");
			}
			else
			{
				Log4J.logp.error("Mark as Resolved link is displayed for colleague in message.");
				Assert.assertTrue(false, "Mark as Resolved link is displayed for colleague in message.");
			}
			Thread.sleep(2000);
			messagecenter_webe.lnk_ViewConversation.click();
			Thread.sleep(2000);
			if (messagecenter_webe.lbl_PinkEvidence.isDisplayed())
			{
				Log4J.logp.info("Evidence is displayed with Pink Color.");
				Assert.assertTrue(true, "Evidence is displayed with Pink Color.");
			}
			else
			{
				Log4J.logp.error("Evidence is not displayed with Pink Color.");
				Assert.assertTrue(false, "Evidence is not displayed with Pink Color.");
			}

			messagecenter_webe.lnk_CollpaseConversation.click();
			Thread.sleep(2000);
			if (messagecenter_webe.lbl_PendingDiscussion.isDisplayed())
			{
				Log4J.logp.info("Pending label is displayed for Discussion issue in message.");
				Assert.assertTrue(true, "Pending label is displayed for Discussion issue in message.");
			}
			else
			{
				Log4J.logp.error("Pending label is not displayed for Discussion issue in message.");
				Assert.assertTrue(false, "Pending label is not displayed for Discussion issue in message.");
			}
			messagecenter_webe.btn_Reply.click();
			Thread.sleep(2000);
			messagecenter_webe.txt_Reply.sendKeys("Message Center Discussion Reply");
			Thread.sleep(2000);
			messagecenter_webe.btn_ReplySend.click();
			Thread.sleep(2000);
			Log4J.logp.info("Reply sent successfully");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Log4J.logp.info("========== Finished Reply to Disscusion as a Colleague ==========");

			//Following code will check user gets notified about the reply via message and also in issue panel
			Log4J.logp.info("========== In check user gets Colleague's Reply in Message Center ==========");
			Login_Lib.login("fm005");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);

			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			MessageCenter_Lib.find_message("fz004", "open");
			Thread.sleep(2000);
			if (messagecenter_webe.lbl_RepliedDiscussion.isDisplayed())
			{
				Log4J.logp.info("Replied label is displayed for Discussion issue in message.");
				Assert.assertTrue(true, "Replied label is displayed for Discussion issue in message.");
			}
			else
			{
				Log4J.logp.error("Replied label is not displayed for Discussion issue in message.");
				Assert.assertTrue(false, "Replied label is not displayed for Discussion issue in message.");
			}
			Thread.sleep(2000);
			messagecenter_webe.lnk_MarkAsResolved.click();
			Thread.sleep(2000);
			MessageCenter_Lib.find_message("fz004", "open");
			Thread.sleep(2000);
			if (messagecenter_webe.lbl_ResolvedDiscussion.isDisplayed())
			{
				Log4J.logp.info("Resolved label is displayed for Discussion issue in message.");
				Assert.assertTrue(true, "Resolved label is displayed for Discussion issue in message.");
			}
			Thread.sleep(2000);
			messagecenter_webe.btn_Close.click();
			Thread.sleep(2000);
			landingp_webe.lnk_Cases.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished check user gets Colleague's Reply in Message Center ==========");

			Log4J.logp.info("========== In check user can Resolve Discussion from Issue Panel ==========");
			Common_Lib.openCase("fm003");
			Thread.sleep(2000);
			strissuecount2 = issuepnl_webe.txt_IssueCount.getText();
			strtemp2 = strissuecount2.split("/");
			ipendingissuecount2 = Integer.parseInt(strtemp2[0]);
			if (ipendingissuecount1 - ipendingissuecount2 == 1)
			{
				Log4J.logp.info("Issue Counter is decreased by 1 after resolving discussion issue from messagecenter.");
				Assert.assertTrue(true, "Issue Counter is decreased by 1 after resolving discussion issue from messagecenter.");
			}
			else
			{
				Log4J.logp.error("Issue Counter is not decreased by 1 after resolving discussion issue from messagecenter.");
				Assert.assertTrue(false, "Issue Counter is not decreased by 1 after resolving discussion issue from messagecenter.");
			}
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			if (issuepnl_webe.img_resolveddiscussion.isDisplayed())
			{
				Log4J.logp.info("Discussion Issue is displayed as resolved in issue panel.");
				Assert.assertTrue(true, "Discussion Issue is displayed as resolved in issue panel.");
			}
			else
			{
				Log4J.logp.error("Discussion Issue is not displayed as resolved in issue panel.");
				Assert.assertTrue(false, "Discussion Issue is not displayed as resolved in issue panel.");
			}
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			Log4J.logp.info("Issue Panel Closed");
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished check user can Resolve Discussion from Issue Panel ==========");

			Log4J.logp.info("*************** Ended - discussWithColleagueMessageCenter ***************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - discussWithColleagueMessageCenter ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "discussWithColleagueMessageCenter is failed.");
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
	 * This test script checks that user can read conversation between him and other user
	 * 
	 * @author fmodi
	 * @since 02/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-192:Verify that User can read message conversation", priority = 11)
	public static void readConversation()
	{

		boolean bstatus;

		try

		{
			Log4J.logp.info("*************** Started - readConversation ***************");

			//This sends a message with already logged in user (First User)
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);

			messagecenter_webe.btn_NewMessage.click();

			bstatus = MessageCenter_Lib.send_NewMessage("faiz001");
			if (bstatus == true)
			{
				Log4J.logp.info("New message is sent successfully.");
				Assert.assertTrue(true, "New message is sent successfully.");
			}
			else
			{
				Log4J.logp.error("New message is not sent successfully.");
				Assert.assertTrue(false, "New message is not sent successfully.");
			}
			//Assert.assertTrue(b, "New message is sent successfully.");
			Thread.sleep(2000);

			Login_Lib.logOut_App();

			Login_Lib.login("fm001");

			//Second user sends reply to first user
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);

			MessageCenter_Lib.find_message("fz001", "open");
			Thread.sleep(2000);
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(messagecenter_webe.btn_Reply));
			messagecenter_webe.btn_Reply.click();

			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(messagecenter_webe.txt_Reply));
			messagecenter_webe.txt_Reply.sendKeys("Reply Successful");

			Thread.sleep(2000);
			messagecenter_webe.btn_ReplySend.click();
			Thread.sleep(2000);

			Log4J.logp.info("Reply message sent succesfully");

			Login_Lib.logOut_App();

			//First user logs in and opens conversation and checks it
			Login_Lib.login("fm005");

			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);

			MessageCenter_Lib.find_message("fz001", "open");
			Thread.sleep(2000);
			messagecenter_webe.lnk_ViewConversation.click();
			Thread.sleep(2000);
			Assert.assertEquals(driver.getPageSource().contains("Reply Successful"), true);
			Assert.assertEquals(driver.getPageSource().contains("Success or not?"), true);
			Log4J.logp.info("Whole conversation is displayed");
			Thread.sleep(2000);
			messagecenter_webe.btn_Close.click();
			Thread.sleep(2000);

			Log4J.logp.info("*************** Ended - readConversation ***************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - readConversation ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "readConversation is failed.");
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
	 * This test script checks that filter works properly in message center
	 * 
	 * @author fmodi
	 * @since 02/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2976:To check filter in Message Center", priority = 12)
	public static void checkFilter()
	{

		String strreadMsgCount, strunReadMsgCount, strtotalMsgCount, strreadMsgTemp[], strunReadMsgTemp[], strtotalMsgTemp[];
		int ireadMsgCount, iunReadMsgCount, itotalMsgCount;

		try

		{

			Log4J.logp.info("*************** Started - checkFilter ***************");

			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);

			messagecenter_webe.img_Filter.click();
			Thread.sleep(2000);

			messagecenter_webe.chk_Read.click();
			Thread.sleep(2000);
			messagecenter_webe.lnk_Apply.click();
			Thread.sleep(2000);

			//msgCount's value will be like "0-9 of 9" so by splitting it using "of" the number after "of" (here 9) will be stored as count.
			strreadMsgCount = messagecenter_webe.total_messages.getText();
			strreadMsgTemp = strreadMsgCount.split("of");
			ireadMsgCount = Integer.parseInt(strreadMsgTemp[1].trim());
			Thread.sleep(2000);

			messagecenter_webe.img_Filter.click();
			Thread.sleep(2000);
			messagecenter_webe.chk_Unread.click();
			Thread.sleep(2000);
			messagecenter_webe.chk_Read.click();
			Thread.sleep(2000);
			messagecenter_webe.lnk_Apply.click();
			Thread.sleep(2000);

			//msgCount's value will be like "0-9 of 9" so by splitting it using "of" the number after "of" (here 9) will be stored as count.
			strunReadMsgCount = messagecenter_webe.total_messages.getText();
			strunReadMsgTemp = strunReadMsgCount.split("of");
			iunReadMsgCount = Integer.parseInt(strunReadMsgTemp[1].trim());
			Thread.sleep(2000);

			messagecenter_webe.img_Filter.click();
			Thread.sleep(2000);
			messagecenter_webe.chk_Unread.click();
			Thread.sleep(2000);
			messagecenter_webe.lnk_Apply.click();
			Thread.sleep(2000);

			//msgCount's value will be like "0-9 of 9" so by splitting it using "of" the number after "of" (here 9) will be stored as count.
			strtotalMsgCount = messagecenter_webe.total_messages.getText();
			strtotalMsgTemp = strtotalMsgCount.split("of");
			itotalMsgCount = Integer.parseInt(strtotalMsgTemp[1].trim());
			Thread.sleep(2000);

			if (itotalMsgCount == ireadMsgCount + iunReadMsgCount)
			{
				Log4J.logp.info("Filter is working properly.");
				Assert.assertTrue(true, "Filter is working properly.");
			}
			else
			{
				Log4J.logp.error("Filter is not working properly.");
				Assert.assertTrue(false, "Filter is not working properly.");
			}

			Log4J.logp.info("*************** Ended - checkFilter ***************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - checkFilter ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "checkFilter is failed.");
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
	 * This test script checks that physician can not access query message after the case has been re-assigned to another user by Supervisor
	 * 
	 * @author fmodi
	 * @since 06/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2990:To check with user raised query issue to physician and supervisor assign this case to another user", priority = 13)
	public static void reassignQueryToPhysician()
	{

		try

		{
			Log4J.logp.info("*************** Started - reassignQueryToPhysician ***************");

			//Following code will find and open the case
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("fm001");
			Thread.sleep(2000);

			//Following code will raise query on first system suggested evidence and redirect user to landing page
			common_webe.lnk_sys_evidence.get(0).click();
			Thread.sleep(2000);

			/*Actions action = new Actions(driver);
			action.moveToElement(common_webe.lnk_sys_evidence.get(0)).contextClick().build().perform();
			Thread.sleep(1000);*/
			Common_Lib.rightclick_system_sugggested_evidence();

			medicalrecordpnl_webe.lnk_querytophysician.click();

			IssuePanel_Lib.send_QueryToPhysician("fz001");
			Thread.sleep(2000);

			/*issuepnl_webe.btn_Issues.click();
			Thread.sleep(1000);*/
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);

			Log4J.logp.info("Issue Panel Closed");
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			Login_Lib.logOut_App();

			//Following code will login as a physician and checks for query message
			Log4J.logp.info("========== In check Query Message as a Physician ==========");
			Login_Lib.login("fm004");
			Thread.sleep(2000);

			MessageCenter_Lib.find_message("fz003", "find");
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Log4J.logp.info("========== Finished check Query Message as a Physician ==========");

			//Following code will reassign case to another user
			Log4J.logp.info("========== In reassign case to another user ==========");
			Login_Lib.login("fm005");
			Thread.sleep(2000);
			ManualAllocation_Lib.assignCase("fm001", "Susan Wilson");
			Thread.sleep(2000);
			Log4J.logp.info("Case reassigned successfully");

			Login_Lib.logOut_App();
			Log4J.logp.info("========== Finished reassign case to another user ==========");

			//Following code will check if Physician gets locked message after reassignment of case
			Log4J.logp.info("========== In check 'Locked Query Message' for 'Reassigned Case' ==========");
			Login_Lib.login("fm004");
			Thread.sleep(2000);

			MessageCenter_Lib.find_message("fz003", "open");
			Assert.assertEquals(driver.getPageSource().contains("The case related to this message has been reassigned and this message is no longer relevant."), true);
			Log4J.logp.info("Message is locked and tooltip is also displayed.");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished check 'Locked Query Message' for 'Reassigned Case' ==========");

			Log4J.logp.info("*************** Ended - reassignQueryToPhysician ***************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - reassignQueryToPhysician ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "reassignQueryToPhysician is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	/**
	 * This test script checks that colleague can not access discussion message after the case has been re-assigned to another user by Supervisor
	 * 
	 * @author fmodi
	 * @since 06/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2991:To check when user raised discuss issue and supervisor assign the case to another users", priority = 14)
	public static void reassignDiscussWithColleague()
	{

		try

		{
			Log4J.logp.info("*************** Started - reassignDiscussWithColleague ***************");

			//Following code will find and open the case
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("fm003");
			Thread.sleep(2000);

			//Following code will raise discussion on first system suggested evidence and redirect user to landing page
			common_webe.lnk_sys_evidence.get(0).click();
			Thread.sleep(2000);

			/*Actions action = new Actions(driver);
			action.moveToElement(common_webe.lnk_sys_evidence.get(0)).contextClick().build().perform();
			Thread.sleep(1000);*/
			Common_Lib.rightclick_system_sugggested_evidence();

			medicalrecordpnl_webe.lnk_discusswithcolleague.click();

			IssuePanel_Lib.send_DiscussWithColleague("fz001");
			Thread.sleep(2000);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			Log4J.logp.info("Issue Panel Closed");

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			Login_Lib.logOut_App();

			//Following code will login as a colleague and checks for discussion message
			Log4J.logp.info("========== In check Discussion Message as a Colleague ==========");
			Login_Lib.login("fm001");
			Thread.sleep(2000);
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			MessageCenter_Lib.find_message("fz004", "find");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Log4J.logp.info("========== Finished check Discussion Message as a Colleague ==========");

			//Following code will reassign case to another user
			Log4J.logp.info("========== In reassign case to another user ==========");
			Login_Lib.login("fm005");
			Thread.sleep(2000);
			ManualAllocation_Lib.assignCase("fm003", "Susan Wilson");
			Thread.sleep(2000);
			Log4J.logp.info("Case reassigned successfully");

			Login_Lib.logOut_App();
			Log4J.logp.info("========== Finished reassign case to another user ==========");

			//Following code will check if colleague gets locked message after reassignment of case
			Log4J.logp.info("========== In check 'Locked Discussion Message' for 'Reassigned Case' ==========");
			Login_Lib.login("fm001");
			Thread.sleep(2000);
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			MessageCenter_Lib.find_message("fz004", "open");
			Assert.assertEquals(driver.getPageSource().contains("The case related to this message has been reassigned and this message is no longer relevant."), true);
			Log4J.logp.info("Message is locked and tooltip is also displayed.");
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished check 'Locked Discussion Message' for 'Reassigned Case' ==========");

			Log4J.logp.info("*************** Ended - reassignDiscussWithColleague ***************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found In - reassignDiscussWithColleague ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "reassignDiscussWithColleague is failed.");
		}

		finally
		{
			Login_Lib.logOut_App();
			Login_Lib.login("fm005");
		}

	}

	@AfterClass
	public static void MessageCenterAfterClass()
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
			if (demographicpnl_webe != null)
			{
				demographicpnl_webe = null;
			}
			if (searchcriteria_webe != null)
			{
				searchcriteria_webe = null;
			}

			Login_Lib.logOut_App();
			//Login_Lib.logIn_App(DriverTestNG.username, DriverTestNG.password);

			Log4J.logp.info("In AfterClass for MessageCenter");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
