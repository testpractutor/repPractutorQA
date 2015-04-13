package com.ezdi.library;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ezdi.framework.common.JDBCMySql;
import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.webelements.Comman_WebE;
import com.ezdi.webelements.MessageCenter_WebE;

public class MessageCenter_Lib
{

	static WebDriver			driver;
	static MessageCenter_WebE	messagecenter_webe;
	static Comman_WebE			common_webe;
	public static WebDriverWait	wait;

	/**
	 * To send new message it gets To,Subject and Message values from the database
	 * 
	 * @author fmodi
	 * @param rowId
	 * @return
	 */

	public static boolean send_NewMessage(String rowId)
	{
		String to = null;
		String subject = null;
		String message = null;

		Map<String, String> rowTestData = null;
		JDBCMySql sendNewMessage_TestData;

		try
		{
			Log4J.logp.info("--------------- In send_NewMessage for rowId '" + rowId + "' ---------------");
			driver = ExecutionSetup.getDriver();
			messagecenter_webe = MessageCenter_WebE.getInstance(driver);
			wait = new WebDriverWait(driver, 20);

			sendNewMessage_TestData = new JDBCMySql();

			rowTestData = sendNewMessage_TestData.getRowbyID("td_sendnewmessage", rowId);

			to = rowTestData.get("To");
			subject = rowTestData.get("Subject");
			message = rowTestData.get("Message");

			if (to != null)
			{
				messagecenter_webe.lst_To.click();
				messagecenter_webe.txt_To.sendKeys(to);
				messagecenter_webe.txt_To.sendKeys(Keys.ENTER);
			}
			if (subject != null)
			{
				messagecenter_webe.txt_Subject.clear();
				messagecenter_webe.txt_Subject.sendKeys(subject);
			}
			if (message != null)
			{
				messagecenter_webe.txt_Message.clear();
				messagecenter_webe.txt_Message.sendKeys(message);
			}

			Log4J.logp.info("In send_NewMessage ::: all data inserted properly for rowId '" + rowId + "'");

			Common_Lib.scroll_Page(messagecenter_webe.main_dragger, 60);

			//Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(messagecenter_webe.btn_Send));
			messagecenter_webe.btn_Send.click();

			Log4J.logp.info("--------------- In send_NewMessage ::: message sent for rowId '" + rowId + "' ---------------");
			return true;

		}
		catch (Exception e)
		{
			Log4J.logp.error("--------------- In send_NewMessage ::: message sending is failed for rowId=" + rowId + "' ---------------");
			e.printStackTrace();
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
	 * To find message and perform operations open message and delete message
	 * 
	 * @author fmodi
	 * @param rowId,action
	 * @return
	 */
	public static boolean find_message(String rowId, String action) throws Exception
	{

		Log4J.logp.info("--------------- In find_Message ::: starting to find message ---------------");
		driver = ExecutionSetup.getDriver();
		messagecenter_webe = MessageCenter_WebE.getInstance(driver);

		//String from = null;

		String strsubject = null;

		/*String to = null;
		String caseNo = null;
		String date_Time = null;*/

		Map<String, String> rowTestData = null;

		String strmsgCount, strtemp1[];
		int i, icount;
		boolean deleteMessage_Alert;
		WebElement table, secondColumn, sixthColumn;
		JDBCMySql findMessage_TestData;

		try
		{

			findMessage_TestData = new JDBCMySql();

			rowTestData = findMessage_TestData.getRowbyID("td_findmessage", rowId);

			//from = rowTestData.get("From");

			strsubject = rowTestData.get("Subject");

			/*to = rowTestData.get("To");
			caseNo = rowTestData.get("CaseNo");
			date_Time = rowTestData.get("DateTime");*/

			table = messagecenter_webe.tbl_Messages;

			/**
			 * msgCount's value will be like "0-9 of 9" so by splitting it using "of" the number after "of" (here 9) will be stored as count.
			 */
			strmsgCount = messagecenter_webe.total_messages.getText();
			strtemp1 = strmsgCount.split("of");
			icount = Integer.parseInt(strtemp1[1].trim());

			/**
			 * to traverse through messages table and perform operations
			 */
			mainloop: for (i = 0; i <= (icount / 15); i++)
			{
				List<WebElement> rows = table.findElements(By.tagName("tr"));

				for (WebElement row : rows)
				{

					//WebElement firstColumn = row.findElement(By.cssSelector("td:nth-child(1)"));

					secondColumn = row.findElement(By.cssSelector("td:nth-child(2)"));

					/*WebElement thirdColumn = row.findElement(By.cssSelector("td:nth-child(3)"));
					WebElement fourthColumn = row.findElement(By.cssSelector("td:nth-child(4)"));
					WebElement fifthColumn = row.findElement(By.cssSelector("td:nth-child(5)"));*/

					if (strsubject.equalsIgnoreCase(secondColumn.getText()))
					{
						Log4J.logp.info("conditions matched");
						Thread.sleep(2000);

						if (action.equalsIgnoreCase("find"))
						{
							Log4J.logp.info("Message is present");
							Thread.sleep(2000);
							break mainloop;

						}
						else if (action.equalsIgnoreCase("open"))
						{
							secondColumn.click();
							Thread.sleep(2000);
							Log4J.logp.info("Message is opened");
							Thread.sleep(2000);
							break mainloop;

						}
						else if (action.equalsIgnoreCase("delete"))
						{
							sixthColumn = row.findElement(By.cssSelector("td:nth-child(6) > a"));
							Thread.sleep(2000);
							sixthColumn.click();
							Thread.sleep(2000);
							deleteMessage_Alert = Common_Lib.IsAlertPresent();
							Thread.sleep(2000);
							Log4J.logp.info("Alert of delete message is :" + deleteMessage_Alert);
							Log4J.logp.info("Message is deleted");
							Thread.sleep(2000);
							break mainloop;

						}

					}
					Thread.sleep(2000);
					Common_Lib.scroll_Page(messagecenter_webe.main_dragger, 50);
					Thread.sleep(2000);
				}
				Thread.sleep(2000);
				Common_Lib.scroll_Page_Up(messagecenter_webe.main_dragger, 250);
				Thread.sleep(2000);
				messagecenter_webe.lnk_Next.click();
				Thread.sleep(2000);
			}
			Log4J.logp.info("--------------- In find_Message ::: message is found and operation is performed ---------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("--------------- In find_Message ::: execution is failed ---------------");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is used so that different validations for message can be checked
	 * 
	 * @author fmodi
	 * @since 09/12/2014
	 */
	public static boolean checkMessageValidations(String msgId, String validation)
	{

		WebElement toAlert, subjectAlert, messageAlert, replyAlert;

		try
		{
			Log4J.logp.info("--------------- In checkMessageValidations for validation='" + validation + "' started ---------------");
			driver = ExecutionSetup.getDriver();
			messagecenter_webe = MessageCenter_WebE.getInstance(driver);

			messagecenter_webe.btn_NewMessage.click();
			Thread.sleep(1000);

			if (!(validation.equals("All")))
			{
				MessageCenter_Lib.send_NewMessage(msgId);
				Thread.sleep(2000);
			}

			if (validation.equals("All"))
			{
				//Directly clicks on send button and checks all validations
				Common_Lib.scroll_Page(messagecenter_webe.main_dragger, 60);
				Thread.sleep(2000);
				messagecenter_webe.btn_Send.click();
				//Thread.sleep(2000);
				toAlert = messagecenter_webe.txt_MesageCenterAlerts.get(0);
				subjectAlert = messagecenter_webe.txt_MesageCenterAlerts.get(1);
				messageAlert = messagecenter_webe.txt_MesageCenterAlerts.get(2);
				if (toAlert.isDisplayed())
				{
					Log4J.logp.info("To validation is displayed");
					Assert.assertTrue(true, "To validation is displayed");
				}
				else
				{
					Log4J.logp.error("To validation is not displayed");
					Assert.assertTrue(false, "To validation is not displayed");
				}
				if (subjectAlert.isDisplayed())
				{
					Log4J.logp.info("Subject validation is displayed");
					Assert.assertTrue(true, "Subject validation is displayed");
				}
				else
				{
					Log4J.logp.error("Subject validation is not displayed");
					Assert.assertTrue(false, "Subject validation is not displayed");
				}
				if (messageAlert.isDisplayed())
				{
					Log4J.logp.info("Message validation is displayed");
					Assert.assertTrue(true, "Message validation is displayed");
				}
				else
				{
					Log4J.logp.error("Message validation is not displayed");
					Assert.assertTrue(false, "Message validation is not displayed");
				}

			}
			else if (validation.equals("Sub+Msg"))
			{
				subjectAlert = messagecenter_webe.txt_MesageCenterAlerts.get(0);
				messageAlert = messagecenter_webe.txt_MesageCenterAlerts.get(1);
				if (subjectAlert.isDisplayed())
				{
					Log4J.logp.info("Subject validation is displayed");
					Assert.assertTrue(true, "Subject validation is displayed");
				}
				else
				{
					Log4J.logp.error("Subject validation is not displayed");
					Assert.assertTrue(false, "Subject validation is not displayed");
				}
				if (messageAlert.isDisplayed())
				{
					Log4J.logp.info("Message validation is displayed");
					Assert.assertTrue(true, "Message validation is displayed");
				}
				else
				{
					Log4J.logp.error("Message validation is not displayed");
					Assert.assertTrue(false, "Message validation is not displayed");
				}

			}
			else if (validation.equals("Msg"))
			{
				messageAlert = messagecenter_webe.txt_MesageCenterAlerts.get(0);
				if (messageAlert.isDisplayed())
				{
					Log4J.logp.info("Message validation is displayed");
					Assert.assertTrue(true, "Message validation is displayed");
				}
				else
				{
					Log4J.logp.error("Message validation is not displayed");
					Assert.assertTrue(false, "Message validation is not displayed");
				}

			}
			else if (validation.equals("Sub"))
			{
				subjectAlert = messagecenter_webe.txt_MesageCenterAlerts.get(0);
				if (subjectAlert.isDisplayed())
				{
					Log4J.logp.info("Subject validation is displayed");
					Assert.assertTrue(true, "Subject validation is displayed");
				}
				else
				{
					Log4J.logp.error("Subject validation is not displayed");
					Assert.assertTrue(false, "Subject validation is not displayed");
				}
			}
			else if (validation.equals("To"))
			{
				toAlert = messagecenter_webe.txt_MesageCenterAlerts.get(0);
				if (toAlert.isDisplayed())
				{
					Log4J.logp.info("To validation is displayed");
					Assert.assertTrue(true, "To validation is displayed");
				}
				else
				{
					Log4J.logp.error("To validation is not displayed");
					Assert.assertTrue(false, "To validation is not displayed");
				}
			}
			else if (validation.equals("Reply"))
			{
				MessageCenter_Lib.find_message("fz001", "open");
				Thread.sleep(2000);
				messagecenter_webe.btn_Reply.click();
				Thread.sleep(1000);
				messagecenter_webe.btn_ReplySend.click();
				replyAlert = messagecenter_webe.txt_MesageCenterAlerts.get(0);
				if (replyAlert.isDisplayed())
				{
					Log4J.logp.info("Reply validation is displayed");
					Assert.assertTrue(true, "Reply validation is displayed");
				}
				else
				{
					Log4J.logp.error("Reply validation is not displayed");
					Assert.assertTrue(false, "Reply validation is not displayed");
				}
			}

			Thread.sleep(1000);

			if (!(validation.equals("Reply")))
			{
				messagecenter_webe.btn_Close.click();
				Thread.sleep(1000);
			}

			Log4J.logp.info("--------------- In checkMessageValidations- completed ---------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("--------------- checkMessageValidations is failed ---------------");
			e.printStackTrace();
			return false;
		}
	}

}
