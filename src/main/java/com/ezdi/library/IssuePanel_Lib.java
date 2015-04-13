package com.ezdi.library;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ezdi.framework.common.JDBCMySql;
import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.webelements.CodingPnl_WebE;
import com.ezdi.webelements.Comman_WebE;
import com.ezdi.webelements.GroupinPnl_WebE;
import com.ezdi.webelements.IssuePnl_WebE;

public class IssuePanel_Lib
{

	public static WebDriver			driver;
	public static IssuePnl_WebE		issuepnl_webe;
	public static Comman_WebE		common_webe;
	public static CodingPnl_WebE	codingpnl_webe;
	public static WebDriverWait		wait;
	public static GroupinPnl_WebE	groupingpnl_webe;

	/**
	 * To send new query to physician it gets To,Your Question and Comments values from the database
	 * 
	 * @author fmodi
	 * @param rowId
	 * @return
	 */

	public static boolean send_QueryToPhysician(String rowId)
	{
		String to = null;
		String your_question = null;
		String comments = null;

		Map<String, String> rowTestData = null;
		JDBCMySql sendQueryToPhysician_TestData;

		try
		{
			Log4J.logp.info("--------------- In send_QueryToPhysician for rowId '" + rowId + "' ---------------");
			driver = ExecutionSetup.getDriver();
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			wait = new WebDriverWait(driver, 20);

			sendQueryToPhysician_TestData = new JDBCMySql();

			rowTestData = sendQueryToPhysician_TestData.getRowbyID("td_sendquery", rowId);

			to = rowTestData.get("To");
			your_question = rowTestData.get("Your_Question");
			comments = rowTestData.get("Comments");
			if (to != null)
			{
				/*issuepnl_webe.lst_tophysician.click();
				Thread.sleep(2000);*/
				issuepnl_webe.lst_tophysician.sendKeys(to);
				Thread.sleep(500);
				issuepnl_webe.lst_tophysician.sendKeys(Keys.ENTER);
				//Thread.sleep(1500);
				Log4J.logp.info("Physician successfully selected");

			}

			Common_Lib.scroll_Page(issuepnl_webe.query_dragger, 60);
			Thread.sleep(500);

			if (your_question != null)
			{
				issuepnl_webe.txt_yourquestionquery.clear();
				Thread.sleep(500);
				issuepnl_webe.txt_yourquestionquery.sendKeys(your_question);
				//Thread.sleep(1000);
				Log4J.logp.info("Question successfully entered");
			}

			//Thread.sleep(1000);

			Common_Lib.scroll_Page(issuepnl_webe.query_dragger, 30);
			Thread.sleep(500);

			if (comments != null)
			{
				issuepnl_webe.txt_querycomments.clear();
				//Thread.sleep(1000);
				issuepnl_webe.txt_querycomments.sendKeys(comments);
				//Thread.sleep(1000);
				Log4J.logp.info("Comments successfully entered");
			}

			Common_Lib.scroll_Page(issuepnl_webe.query_dragger, 60);

			Log4J.logp.info("In send_QueryToPhysician ::: all data inserted properly for rowId '" + rowId + "'");

			//wait.until(ExpectedConditions.elementToBeClickable(issuepnl_webe.btn_sendquery));

			issuepnl_webe.btn_sendquery.click();

			Log4J.logp.info("--------------- In send_QueryToPhysician ::: query sent for rowId '" + rowId + "' ---------------");
			return true;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log4J.logp.error("--------------- In send_QueryToPhysician ::: query sending is failed for rowId=" + rowId + "' ---------------");
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
	 * To send new discussion to colleague it gets To and Your Question values from the database
	 * 
	 * @author fmodi
	 * @param rowId
	 * @return
	 */

	public static boolean send_DiscussWithColleague(String rowId)
	{
		String to = null;
		String your_question = null;

		Map<String, String> rowTestData = null;
		JDBCMySql sendNewMessage_TestData;

		try
		{
			Log4J.logp.info("---------------  In send_DiscussWithColleague for rowId '" + rowId + "' ---------------");
			driver = ExecutionSetup.getDriver();
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			wait = new WebDriverWait(driver, 20);

			sendNewMessage_TestData = new JDBCMySql();

			rowTestData = sendNewMessage_TestData.getRowbyID("td_senddiscussion", rowId);

			to = rowTestData.get("To");
			your_question = rowTestData.get("Your_Question");
			Thread.sleep(500);
			if (to != null)
			{
				issuepnl_webe.lst_ToColleague.click();
				issuepnl_webe.txt_ToColleague.sendKeys(to);
				issuepnl_webe.txt_ToColleague.sendKeys(Keys.ENTER);
				Log4J.logp.info("Colleague successfully selected");
				Thread.sleep(1000);
			}

			if (your_question != null)
			{
				issuepnl_webe.txt_yourquestiondiscuss.clear();
				issuepnl_webe.txt_yourquestiondiscuss.sendKeys(your_question);
				Log4J.logp.info("Question successfully entered");
				Thread.sleep(1000);
			}

			Log4J.logp.info("In send_DiscussWithColleague ::: all data inserted properly for rowId '" + rowId + "'");

			//wait.until(ExpectedConditions.elementToBeClickable(issuepnl_webe.btn_sendquery));
			Thread.sleep(1000);
			issuepnl_webe.btn_senddiscussion.click();

			Log4J.logp.info("---------------  In send_DiscussWithColleague ::: discussion sent for rowId '" + rowId + "' ---------------");
			return true;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log4J.logp.error("---------------  In send_DiscussWithColleague ::: discussion sending is failed for rowId=" + rowId + "' ---------------");
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
	 * To send new query to physician with CDI role Already on CDI query page Without evidence.This method is also check duplicate code
	 * 
	 * @author skhalasi
	 * @param rowId
	 * @param codeNumber
	 * @since 27/10/2014
	 * @return
	 */

	public static String send_QueryToPhysician_CDI_withoutEvidence(String rowId, String evidencename)
	{
		String strprobable_code = null;
		String strto = null;
		String stryour_question = null;
		String strcomments = null;
		Map<String, String> rowTestData = null;
		String strcodeNumber = null;
		String strSequence = null;

		try
		{
			Log4J.logp.info("----------------  In send_QueryToPhysician_CDI_withoutEvidence ---------------- ");
			driver = ExecutionSetup.getDriver();
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			wait = new WebDriverWait(driver, 20);
			codingpnl_webe = CodingPnl_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);

			if (rowId != null)
			{
				JDBCMySql sendQueryToPhysician_TestData = new JDBCMySql();

				rowTestData = sendQueryToPhysician_TestData.getRowbyID("td_cdiquery", rowId);

				strto = rowTestData.get("to");
				stryour_question = rowTestData.get("question");
				strcomments = rowTestData.get("comments");
				strprobable_code = rowTestData.get("probable_code");
				Log4J.logp.info("Tha evidence name is =" + evidencename);
				if (evidencename != null)
				{
					WebElement codeNumber_webe = driver.findElement(By.xpath("//div[@id='right-pan']//span[contains(text(),'" + evidencename + "')]/ancestor::div[@class='row-1']//div[contains(@class,'num-container num')]"));
					strcodeNumber = codeNumber_webe.getText();
					Log4J.logp.info("Code Number is =" + strcodeNumber);

					Thread.sleep(1000);
					//driver.findElement(By.id("queryPhysicianAssociatedCode_chosen")).click();
					issuepnl_webe.lnk_AssoCodeList.click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//div[@id='queryPhysicianAssociatedCode_chosen']//li[contains(text(),'" + strcodeNumber + "')]")).click();

				}
				if (strprobable_code != null)
				{

					Thread.sleep(1000);
					issuepnl_webe.txt_probableCode.clear();
					Thread.sleep(1000);
					//char[] charArray = probable_code.toCharArray();

					for (int j = 0; j < strprobable_code.length(); j++)
					{
						char s1 = strprobable_code.charAt(j);
						strSequence = Character.toString(s1);
						issuepnl_webe.txt_probableCode.sendKeys(strSequence);
						Thread.sleep(1000);
					}
					//issuepnl_webe.txt_probableCode.sendKeys(probable_code);
					Thread.sleep(2000);
					//issuepnl_webe.txt_probableCode.sendKeys(Keys.SPACE);

					issuepnl_webe.lnk_firstProbableCode.click();
					Thread.sleep(1000);
					issuepnl_webe.txt_probableCode.sendKeys(Keys.TAB);
					Log4J.logp.info("Probable code successfully selected");

				}

				if (strto != null)
				{
					issuepnl_webe.lst_tophysician.sendKeys(strto);
					Thread.sleep(500);
					issuepnl_webe.lst_tophysician.sendKeys(Keys.ENTER);
					Log4J.logp.info("Physician successfully selected");

				}

				Common_Lib.scroll_Page(issuepnl_webe.query_dragger, 60);
				Thread.sleep(700);

				if (stryour_question != null)
				{
					issuepnl_webe.txt_yourquestionquery.clear();
					Thread.sleep(500);
					issuepnl_webe.txt_yourquestionquery.sendKeys(stryour_question);
					Log4J.logp.info("Question successfully entered");
				}

				Common_Lib.scroll_Page(issuepnl_webe.query_dragger, 30);
				Thread.sleep(700);

				if (strcomments != null)
				{
					issuepnl_webe.txt_querycomments.clear();
					issuepnl_webe.txt_querycomments.sendKeys(strcomments);
					Log4J.logp.info("Comments successfully entered");
				}

				Common_Lib.scroll_Page(issuepnl_webe.query_dragger, 60);

				Log4J.logp.info("In send_QueryToPhysician_CDI_withoutEvidence  --all data inserted properly for rowId   " + rowId);
				Thread.sleep(2000);
				issuepnl_webe.btn_sendQueryCDI.click();
				if (Common_Lib.IsCustomAlertPresent())
				{
					Log4J.logp.info("Same Associated and Probable code can not add");
					Assert.assertTrue(true, "Same Associated and Probable code can not add");
					Log4J.logp.info("----------------  In send_QueryToPhysician_CDI_withoutEvidence --CDI query sent for rowId  ---------------- ");
					return "sameCodeNotAdd";

				}

				Log4J.logp.info("----------------  In send_QueryToPhysician_CDI_withoutEvidence --CDI query sent for rowId   " + rowId + " ---------------- ");

				return "queryWithRowIdSuccess";

			}
			else
			{
				WebElement codeNumber_webe = driver.findElement(By.xpath("//div[@id='right-pan']//span[contains(text(),'" + evidencename + "')]/ancestor::div[@class='row-1']//div[contains(@class,'num-container num')]"));
				strcodeNumber = codeNumber_webe.getText();
				Log4J.logp.info("Code Number is =" + strcodeNumber);

				Thread.sleep(1000);
				//driver.findElement(By.id("queryPhysicianAssociatedCode_chosen")).click();
				issuepnl_webe.lnk_AssoCodeList.click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//div[@id='queryPhysicianAssociatedCode_chosen']//li[contains(text(),'" + strcodeNumber + "')]")).click();

				Thread.sleep(1000);
				issuepnl_webe.txt_probableCode.clear();
				Thread.sleep(1000);
				//char[] charArray = probable_code.toCharArray();

				for (int j = 0; j < strcodeNumber.length(); j++)
				{
					char s1 = strcodeNumber.charAt(j);
					strSequence = Character.toString(s1);
					issuepnl_webe.txt_probableCode.sendKeys(strSequence);
					Thread.sleep(1000);
				}
				//issuepnl_webe.txt_probableCode.sendKeys(probable_code);
				Thread.sleep(2000);
				//issuepnl_webe.txt_probableCode.sendKeys(Keys.SPACE);

				issuepnl_webe.lnk_firstProbableCode.click();
				Thread.sleep(1000);
				issuepnl_webe.txt_probableCode.sendKeys(Keys.TAB);
				Log4J.logp.info("Probable code successfully selected");

				issuepnl_webe.lst_tophysician.sendKeys("Chris Tyler");
				Thread.sleep(500);
				issuepnl_webe.lst_tophysician.sendKeys(Keys.ENTER);
				Log4J.logp.info("Physician successfully selected");

				Common_Lib.scroll_Page(issuepnl_webe.query_dragger, 60);
				Thread.sleep(700);

				issuepnl_webe.txt_yourquestionquery.clear();
				Thread.sleep(500);
				issuepnl_webe.txt_yourquestionquery.sendKeys("Test Question");
				Log4J.logp.info("Question successfully entered");

				Common_Lib.scroll_Page(issuepnl_webe.query_dragger, 30);
				Thread.sleep(700);

				issuepnl_webe.txt_querycomments.clear();
				issuepnl_webe.txt_querycomments.sendKeys("Test Comment");
				Log4J.logp.info("Comments successfully entered");

				Common_Lib.scroll_Page(issuepnl_webe.query_dragger, 60);

				Log4J.logp.info("In send_QueryToPhysician_CDI_withoutEvidence  --all data inserted properly for null rowId");
				Thread.sleep(2000);
				issuepnl_webe.btn_sendQueryCDI.click();
				Thread.sleep(2000);
				if (Common_Lib.IsCustomAlertPresent())
				{
					Log4J.logp.info("Same Associated and Probable code can not add");
					Assert.assertTrue(true, "Same Associated and Probable code can not add");
					Log4J.logp.info("----------------  In send_QueryToPhysician_CDI_withoutEvidence --CDI query sent for null rowId  ---------------- ");
					return "queryWithoutRowIdSuccess";

				}
				else
				{
					Log4J.logp.error("Same Associated and Probable code can add");
					Assert.assertTrue(false, "Same Associated and Probable code can add");
					return "queryWithoutRowIdFail";
				}

			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log4J.logp.error("----------------  In send_QueryToPhysician_CDI_withoutEvidence  --CDI query sending is failed for rowId=" + rowId + " ---------------- ");
			if (issuepnl_webe.lnk_DiscussWithCol.isDisplayed())
			{
				issuepnl_webe.btn_Issues.click();
			}
			return "queryAddfail";
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
	 * To send new query to physician with CDI role
	 * 
	 * With evidence
	 * 
	 * @author skhalasi
	 * @param rowId
	 * @since 16/10/2014
	 * @return
	 */

	public static boolean send_QueryToPhysician_CDI(String rowId)
	{
		String strprobable_code = null;
		String strto = null;
		String stryour_question = null;
		String strcomments = null;
		String evidencename = null;

		Map<String, String> rowTestData = null;
		String strcodeNumber = null;
		String strSequence = null;

		try
		{
			Log4J.logp.info("----------------  In send_QueryToPhysician_CDI for rowId   " + rowId + " ---------------- ");
			driver = ExecutionSetup.getDriver();
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			wait = new WebDriverWait(driver, 20);
			codingpnl_webe = CodingPnl_WebE.getInstance(driver);

			JDBCMySql sendQueryToPhysician_TestData = new JDBCMySql();

			rowTestData = sendQueryToPhysician_TestData.getRowbyID("td_cdiquery", rowId);

			strto = rowTestData.get("to");
			stryour_question = rowTestData.get("question");
			strcomments = rowTestData.get("comments");
			strprobable_code = rowTestData.get("probable_code");
			evidencename = rowTestData.get("evidencename");

			if (evidencename != null)
			{

				Log4J.logp.info("Evidence lable name is =" + evidencename);
				List<WebElement> evidenceList = driver.findElements(By.xpath("//span[@class='evidenceLabel']"));
				for (int i = 0; i < evidenceList.size(); i++)
				{
					Common_Lib.scroll_Until_FindWebe(codingpnl_webe.coding_Dragger, evidenceList.get(i), 15);
					Log4J.logp.info("Webelement evidence lable name is =" + evidenceList.get(i).getText());
					if (evidenceList.get(i).getText().equals(evidencename))
					{
						//WebElement codeNumber_webe = driver.findElement(By.xpath("//ancestor::div[@class='row-1']//div[contains(@class,'num-container')]"));
						WebElement codeNumber_webe = driver.findElement(By.xpath("//div[@id='right-pan']//span[text()='" + evidencename + "']/ancestor::div[@class='row-1']//div[contains(@class,'num-container')]"));
						strcodeNumber = codeNumber_webe.getText();
						Log4J.logp.info("Code Number is =" + strcodeNumber);

						//	driver.findElement(By.xpath("(//span[@class='evidenceLabel']/ancestor::span[@class='brd code-status']//span[@class='code-status-drop-down'])[" + (i + 1) + "]")).click();
						Common_Lib.scroll_Page(codingpnl_webe.coding_Dragger, 16);
						Thread.sleep(2000);
						//Common_Lib.scroll_Page(codingpnl_webe.coding_Dragger, 12);
						driver.findElement(By.xpath("(//span[@class='evidenceLabel']/ancestor::span[@class='brd code-status']//span[@class='code-status-drop-down'])[" + (i + 1) + "]")).click();

						driver.findElement(By.xpath("(//span[@class='evidenceLabel']/ancestor::span[@class='brd code-status']//a[@id='coding_panel_add_query'])[" + (i + 1) + "]")).click();

						/*Select gender = new Select(driver.findElement(By.xpath("(//ul[@class='chosen-results'])[2]")));
						gender.selectByVisibleText(codeNumber);*/
						//driver.findElement(By.id("queryPhysicianAssociatedCode_chosen")).click();
						issuepnl_webe.lnk_AssoCodeList.click();
						Thread.sleep(1000);
						driver.findElement(By.xpath("//div[@id='queryPhysicianAssociatedCode_chosen']//li[contains(text(),'" + strcodeNumber + "')]")).click();

						/*driver.findElement(By.id("queryPhysicianAssociatedCode_chosen")).click();

						List<WebElement> options = driver.findElements(By.xpath("//li[contains(@class,'active-result')]"));

						for (WebElement option : options)
						{
							if (codeNumber.contains(option.getText()))
							{
								option.click();
								break;
							}
						}*/
						if (strprobable_code != null)
						{

							issuepnl_webe.txt_probableCode.clear();
							Thread.sleep(1000);
							//char[] charArray = probable_code.toCharArray();

							for (int j = 0; j < strprobable_code.length(); j++)
							{
								char s1 = strprobable_code.charAt(j);
								strSequence = Character.toString(s1);
								issuepnl_webe.txt_probableCode.sendKeys(strSequence);
								Thread.sleep(1000);
							}
							//issuepnl_webe.txt_probableCode.sendKeys(probable_code);
							Thread.sleep(2000);
							//issuepnl_webe.txt_probableCode.sendKeys(Keys.SPACE);

							issuepnl_webe.lnk_firstProbableCode.click();
							Thread.sleep(1000);
							issuepnl_webe.txt_probableCode.sendKeys(Keys.TAB);
							Log4J.logp.info("Probable code successfully selected");

						}

						if (strto != null)
						{
							issuepnl_webe.lst_tophysician.sendKeys(strto);
							Thread.sleep(500);
							issuepnl_webe.lst_tophysician.sendKeys(Keys.ENTER);
							Log4J.logp.info("Physician successfully selected");

						}

						Common_Lib.scroll_Page(issuepnl_webe.query_dragger, 60);
						Thread.sleep(500);

						if (stryour_question != null)
						{
							issuepnl_webe.txt_yourquestionquery.clear();
							Thread.sleep(500);
							issuepnl_webe.txt_yourquestionquery.sendKeys(stryour_question);
							Log4J.logp.info("Question successfully entered");
						}

						Common_Lib.scroll_Page(issuepnl_webe.query_dragger, 30);
						Thread.sleep(500);

						if (strcomments != null)
						{
							issuepnl_webe.txt_querycomments.clear();
							issuepnl_webe.txt_querycomments.sendKeys(strcomments);
							Log4J.logp.info("Comments successfully entered");
						}

						Common_Lib.scroll_Page(issuepnl_webe.query_dragger, 60);

						Log4J.logp.info("In send_QueryToPhysician_CDI  --all data inserted properly for rowId   " + rowId);
						Thread.sleep(3000);
						issuepnl_webe.btn_sendQueryCDI.click();
						Log4J.logp.info("In send_QueryToPhysician_CDI --CDI query sent for rowId   " + rowId);
						return true;
					}
				}
				Log4J.logp.info("----------------  Evidencename is not match with all evidence for rowId= " + rowId + "---------------- ");
				return false;
			}
			else
			{
				Log4J.logp.info("---------------- Evidencename can not be null for rowId= " + rowId + "---------------- ");
				return false;
			}

		}
		catch (Exception e)
		{
			if (issuepnl_webe.lnk_DiscussWithCol.isDisplayed())
			{
				issuepnl_webe.btn_Issues.click();
			}
			e.printStackTrace();
			Log4J.logp.error("----------------  In send_QueryToPhysician_CDI  --CDI query sending is failed for rowId=" + rowId + " ---------------- ");
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

}
