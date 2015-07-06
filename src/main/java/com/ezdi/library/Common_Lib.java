package com.ezdi.library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.webelements.AbstractPnl_WebE;
import com.ezdi.webelements.AddGroup_WebE;
import com.ezdi.webelements.AddPhysician_WebE;
import com.ezdi.webelements.AddUser_WebE;
import com.ezdi.webelements.CodeBook_WebeE;
import com.ezdi.webelements.CodingPnl_WebE;
import com.ezdi.webelements.Comman_WebE;
import com.ezdi.webelements.DemographicPnl_WebE;
import com.ezdi.webelements.GroupinPnl_WebE;
import com.ezdi.webelements.IssuePnl_WebE;
import com.ezdi.webelements.LandingP_WebE;
import com.ezdi.webelements.MedicalRecordPnl_WebE;
import com.ezdi.webelements.SearchCriteria_WebE;

/**
 * @author jpatel
 * 
 */
/**
 * @author jpatel
 *
 */
public class Common_Lib
{

	static WebDriver					driver;
	static AddUser_WebE					addUser_webe;
	static AddPhysician_WebE			addPhysician_webe;
	static AddGroup_WebE				addGroup_webe;
	static CodingPnl_WebE				codingpnl_webe;
	static GroupinPnl_WebE				groupingpnl_webe;
	static Actions						dragger;
	static WebDriverWait				wait;
	static Comman_WebE					comman_webe;
	static SearchCriteria_WebE			searchCriteria_webe;
	public static LandingP_WebE			landingp_webe;
	public static DemographicPnl_WebE	demographicpnl_webe;
	public static IssuePnl_WebE			issuepnl_webe;
	public static AbstractPnl_WebE		abstractpnl_webe;
	public static CodeBook_WebeE		codeBook_webe;
	public static MedicalRecordPnl_WebE	medicalrecordpnl_webe;


	/**
	 * This method is for scroll browser downwards to reach 'element'
	 * 
	 * @author bchauhan
	 * */
	
	public static Object scrollBrowsertoElementView(WebDriver driver, WebElement element) 
	{
		Log4J.logp.info("----------------  Started - scroll_Page ----------------");
	    return ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	    
	}

	/**
	 * This method is for scroll page downwards with dragger element
	 * 
	 * @author skhalasi
	 * */
	public static boolean scroll_Page(WebElement webelement, int scrollPoints)
	{
		try
		{
			Log4J.logp.info("----------------  Started - scroll_Page ----------------");
			Thread.sleep(500);
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);

			// drag downwards
			int numberOfPixelsToDragTheScrollbarDown = 10;
			for (int i = 10; i < scrollPoints; i = i + numberOfPixelsToDragTheScrollbarDown)
			{
				// this causes a gradual drag of the scroll bar, 10 units at a
				// time
				dragger.moveToElement(webelement).clickAndHold().moveByOffset(0, numberOfPixelsToDragTheScrollbarDown).release(webelement).build().perform();

				/*	dragger = new Actions(driver);
					dragger.moveToElement(driver.findElement(By.cssSelector(".mCSB_dragger_bar"))).clickAndHold().moveByOffset(0, 110).release().perform();
				*/
			}
			Thread.sleep(500);
			Log4J.logp.info("----------------  Ending - scroll_Page ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("----------------  scroll is unsucessfully done in scroll_Page ----------------");
			wait = null;
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for scroll down very small with dragger element
	 *
	 * @author skhalasi
	 * @param webelement
	 * @param scrollPoints
	 * @return
	 */
	public static boolean scroll_Page_VerySmall(WebElement webelement, int scrollPoints)
	{

		try
		{
			Log4J.logp.info("----------------  Started - scroll_Page_VerySmall ----------------");
			Thread.sleep(500);
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);

			// drag downwards
			int numberOfPixelsToDragTheScrollbarDown = 3;
			for (int i = 3; i < scrollPoints; i = i + numberOfPixelsToDragTheScrollbarDown)
			{
				// this causes a gradual drag of the scroll bar, 10 units at a
				// time
				dragger.moveToElement(webelement).clickAndHold().moveByOffset(0, numberOfPixelsToDragTheScrollbarDown).release(webelement).build().perform();

			}
			Thread.sleep(500);
			Log4J.logp.info("----------------  Ending - scroll_Page_VerySmall ----------------");

			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- scroll is unsucessfully done in scroll_Page_VerySmall ----------------");
			wait = null;
			e.printStackTrace();
			return false;
		}

	}

	/** Pass the webelement get the text inside */
	public static int get_Count(WebElement webelement)
	{
		try
		{
			Log4J.logp.info("In get_Count");
			int count = 0;

			webelement.getText();
			Log4J.logp.info(webelement.getText());
			return count;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Get Count method failed for element " + webelement.getTagName().toLowerCase());
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * This method for upwards the page with dragger element
	 * 
	 * @author skhalasi
	 * */
	public static boolean scroll_Page_Up(WebElement webelement, int scrollPoints)
	{

		try
		{
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);

			// drag upwards
			int numberOfPixelsToDragTheScrollbarUp = -10;
			for (int i = scrollPoints; i > 10; i = i + numberOfPixelsToDragTheScrollbarUp)
			{
				// this causes a gradual drag of the scroll bar, 10 units at a
				// time
				dragger.moveToElement(webelement).clickAndHold().moveByOffset(0, numberOfPixelsToDragTheScrollbarUp).release(webelement).build().perform();

			}
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- scroll is unsucessfully done ----------------");
			wait = null;
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for checking alert is present or not. And print message
	 * 
	 * @author skhalasi
	 * */
	public static String	AlertText;

	public static boolean IsAlertPresent()
	{
		try
		{
			Log4J.logp.info("---------------- Started- IsAlertPresent ----------------");
			Thread.sleep(3000);
			Alert alert = driver.switchTo().alert();
			AlertText = alert.getText();
			Log4J.logp.info("Alert Text is=" + AlertText);
			Thread.sleep(1000);
			alert.accept();

			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.info("---------------- Not found any alert in IsAlertPresent ----------------");
			return false;
		}
	}

	/**
	 * This method is for checking Custom alert is present or not. And print message
	 * 
	 * @author skhalasi
	 * @since 21/10/2014
	 * */
	public static String	CustomAlertText;

	public static boolean IsCustomAlertPresent()
	{

		try
		{
			Log4J.logp.info("---------------- Started - IsCustomAlertPresent ----------------");
			comman_webe = Comman_WebE.getInstance(driver);

			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			CustomAlertText = comman_webe.lbl_customAlertMsg.getText();
			Log4J.logp.info("Custom Alert Text is=" + CustomAlertText);
			Thread.sleep(2000);
			comman_webe.btn_OK_Alert.click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			Log4J.logp.info("---------------- Ending - IsCustomAlertPresent ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.info("---------------- Not found any custom alert ----------------");
			return false;
		}
	}

	/**
	 * This method is for scroll down page without any dragger element
	 * 
	 * @author skhalasi
	 * */
	public static boolean scroll_without_element()
	{
		JavascriptExecutor jse;
		try
		{
			Log4J.logp.info("---------------- Started - scroll_without_element ----------------");
			driver = ExecutionSetup.getDriver();
			jse = (JavascriptExecutor) driver;
			//scroll downward
			jse.executeScript("window.scrollBy(0,400)", "");
			Log4J.logp.info("---------------- Ending - scroll_without_element ----------------");

			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- scroll is unsucessfully done in scroll_without_element ----------------");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for scroll up page without any dragger element
	 * 
	 * @author fmodi
	 * @since 10/10/2014
	 * */
	public static boolean scrollUp_without_element()
	{
		JavascriptExecutor jse;
		try
		{
			Log4J.logp.info("---------------- Started - scrollUp_without_element ----------------");

			driver = ExecutionSetup.getDriver();
			jse = (JavascriptExecutor) driver;
			//scroll upward
			jse.executeScript("window.scrollBy(0,-400)", "");

			Log4J.logp.info("---------------- Ending - scrollUp_without_element ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- scroll is unsucessfully done in scrollUp_without_element ----------------");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for open case
	 * 
	 * We get data from "td_searchcase"
	 * 
	 * @author skhalasi
	 * */
	public static boolean openCase(String rowId)
	{

		WebElement table;
		WebElement row;
		boolean bstatus = false;
		try
		{
			Log4J.logp.info("---------------- Started - openCase ----------------");

			driver = ExecutionSetup.getDriver();
			landingp_webe = LandingP_WebE.getInstance(driver);
			demographicpnl_webe = DemographicPnl_WebE.getInstance(driver);

			Log4J.logp.info("The searched rowId is =" + rowId);
			Thread.sleep(1000);
			SearchCriteria_Lib.search_Case(rowId);

			//to scroll page
			Common_Lib.scroll_without_element();

			table = landingp_webe.tbl_SearchResult;
			//find click-able column
			row = table.findElement(By.tagName("td"));
			row.click();

			Thread.sleep(2000);
			//Common_Lib.waitForObject(demographicpnl_webe.lnk_DemoPnlExpand, "visibility", 10);
			bstatus = Common_Lib.checkElementPresent(demographicpnl_webe.lnk_DemoPnlExpand);

			if (bstatus)
			{
				Log4J.logp.info("Case open successfully");
				Log4J.logp.info("---------------- Ending - openCase ----------------");
				return true;
			}
			else
			{
				Log4J.logp.info("Case can not be open");
				Log4J.logp.info("---------------- Ending - openCase ----------------");
				return false;
			}

		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- Problem Found in openCase ----------------");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * to fill the date
	 * 
	 * @param webe = pass the web element for which you want to fill the date
	 * @param strDate = the date
	 * @return
	 */
	public static boolean fillDate(WebElement webe, String strDate)
	{
		WebElement datePicker;
		try
		{
			Log4J.logp.info("---------------- Started - fillDate ----------------");

			driver = ExecutionSetup.getDriver();

			String id = webe.getAttribute("id");

			//WebElement datePicker = webe.findElement(By.xpath("//following-sibling::img[1]"));

			if (strDate.endsWith("ws"))
			{
				webe.click();
			}
			else
			{
				datePicker = webe.findElement(By.cssSelector("#" + id + " + img"));
				datePicker.click();
			}

			strDate = StringUtils.removeEnd(strDate, "ws");
			Log4J.logp.info("Date is = " + strDate);

			String[] arrDate = strDate.split("/");
			String month = arrDate[0];
			String date = arrDate[1];
			String year = arrDate[2];

			int intmonth = Integer.parseInt(month.replaceFirst("^0+(?!$)", ""));
			intmonth = intmonth - 1;

			int intDay = Integer.parseInt(date.replaceFirst("^0+(?!$)", ""));
			date = String.valueOf(intDay);

			// select month
			driver.findElement(By.className("ui-datepicker-month")).click();
			driver.findElement(By.cssSelector("#ui-datepicker-div > div > div > select.ui-datepicker-month > [value='" + intmonth + "']")).click();
			Thread.sleep(2000);

			// select year
			driver.findElement(By.className("ui-datepicker-year")).click();

			driver.findElement(By.cssSelector("#ui-datepicker-div > div > div > select.ui-datepicker-year > [value='" + year + "']")).click();
			Thread.sleep(2000);

			// select date	
			driver.findElement(By.linkText(date)).click();
			Thread.sleep(1000);

			webe = null;
			datePicker = null;
			Log4J.logp.info("---------------- Ending - fillDate ----------------");

			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- Problem found in fillDate ----------------");

			e.printStackTrace();
			return false;
		}
	}

	/**
	 * dragEle is dragger of panel, ele is element which we need to find
	 * 
	 * @author skhalasi
	 * @param dragEle
	 * @param ele
	 * @param scrollPoint
	 * @return
	 */

	public static boolean scroll_Until_FindWebe(WebElement dragEle, WebElement ele, int scrollPoint)
	{
		try
		{
			Log4J.logp.info("---------------- Started - scroll_Until_FindWebe for element ----------------");
			Thread.sleep(500);
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			if (Common_Lib.checkElementPresent(ele))
			{
				// drag downwards
				int numberOfPixelsToDragTheScrollbarDown = 10;
				for (int i = 10; i < scrollPoint; i = i + numberOfPixelsToDragTheScrollbarDown)
				{
					if (ele.isDisplayed()) //if the tag options is displayed
					{
						return true;
					}
					// this causes a gradual drag of the scroll bar, 10 units at a
					// time

					dragger.moveToElement(dragEle).clickAndHold().moveByOffset(0, numberOfPixelsToDragTheScrollbarDown).release(dragEle).build().perform();

				}
				Thread.sleep(500);

				return scroll_Until_FindWebe(dragEle, ele, 30);

			}
			else
			{
				Log4J.logp.info("In scroll_Until_FindWebe - Element is not present on DOM ");
				return true;
			}

		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- scroll_Until_FindWebe is failed to find elment ----------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is to check for element is present or not
	 * 
	 * @param webe
	 * @author jpatel
	 * @return
	 */
	public static boolean checkElementPresent(WebElement webe)
	{
		try
		{
			Log4J.logp.info("---------------- Started - checkElementPresent  ----------------");

			driver = ExecutionSetup.getDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			webe.getTagName();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Log4J.logp.info("---------------- Ending - checkElementPresent ----------------");

			return true;
		}
		catch (NoSuchElementException e)
		{
			Log4J.logp.info("---------------- Ending - checkElementPresent  ----------------");
			return false;
		}
	}

	/**
	 * To check List of Elements Present
	 * 
	 * @author nchourasiya
	 * @since 30/10/2014
	 * */
	public static boolean checkListOfElementsPresent(List<WebElement> webe, int i)
	{
		try
		{
			Log4J.logp.info("---------------- Started - checkListOfElementsPresent ----------------");

			driver = ExecutionSetup.getDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			webe.get(i);
			Log4J.logp.info("---------------- Ending - checkListOfElementsPresent ----------------");

			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.info("---------------- Ending - checkListOfElementsPresent ----------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is for scroll up until find web element, dragEle is dragger of panel, ele is element which we need to find
	 * 
	 * @author skhalasi
	 * @param dragEle
	 * @param ele
	 * @param scrollPoint
	 * @return
	 */

	public static boolean scroll_Up_Until_FindWebe(WebElement dragEle, WebElement ele, int scrollPoint)
	{
		try
		{
			Log4J.logp.info("---------------- Started - scroll_Up_Until_FindWebe for element ----------------");
			Thread.sleep(500);
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);

			// drag upwards
			int numberOfPixelsToDragTheScrollbarUp = -10;
			for (int i = scrollPoint; i > 10; i = i + numberOfPixelsToDragTheScrollbarUp)
			{
				if (ele.isDisplayed()) //if the tag options is displayed
				{
					return true;
				}
				// this causes a gradual drag up of the scroll bar, 10 units at a
				// time

				dragger.moveToElement(dragEle).clickAndHold().moveByOffset(0, numberOfPixelsToDragTheScrollbarUp).release().build().perform();

			}
			Thread.sleep(500);

			return scroll_Up_Until_FindWebe(dragEle, ele, 30);
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- scroll_Up_Until_FindWebe is failed to find elment ----------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * this method is just for verify flag and print message
	 * 
	 * @author skhalasi
	 * @since 07/10/2014
	 * @param flag
	 * @param message
	 */

	public static void verifyFlag(boolean flag, String message)
	{
		Log4J.logp.info("Message is = " + message);

		if (flag)
		{
			Log4J.logp.info(message + " is passed");
			Assert.assertTrue(true, message + " is passed");
		}
		else
		{
			Log4J.logp.error(message + " is failed");
			Assert.assertTrue(false, message + " is failed");
		}
	}

	/**
	 * This method is for search code in coding panel
	 * 
	 * @author skhalasi
	 * @since 10/10/2014
	 * @param code
	 * @return
	 */

	public static boolean codeSearchInCodingPnl(String code)
	{
		try
		{
			Log4J.logp.info("---------------- Started - codeSearchInCodingPnl for code=" + code + " ----------------");
			Thread.sleep(500);
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			codingpnl_webe = CodingPnl_WebE.getInstance(driver);
			//get list of codes
			List<WebElement> ls = codingpnl_webe.allCodes;

			for (int i = 0; i < ls.size(); i++)
			{
				Common_Lib.scroll_Until_FindWebe(codingpnl_webe.coding_Dragger, ls.get(i), 30);
				//Common_Lib.scrollToWebE(ls.get(i), codingpnl_webe.coding_Dragger);

				Log4J.logp.info(ls.get(i).getText() + "=Code is for i =" + i);

				if (ls.get(i).getText().equals(code))
				{
					Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 6);
					Log4J.logp.info("We found perfect match with code");
					return true;
				}
			}
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(1000);
			Log4J.logp.info("---------------- Ending -  codeSearchInCodingPnl ----------------");
			return false;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- codeSearchInCodingPnl is failed ----------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is for convert case into "On Hold" or "Pending" from "In Progress"
	 * 
	 * We get data from "td_searchcase"
	 * 
	 * @author skhalasi
	 * @since 13/10/2014
	 * @param rowId
	 * @return
	 */

	public static boolean caseInprogerssToOnholdOrPending(String rowId)
	{

		WebDriverWait wait;
		WebElement table;
		WebElement row;
		try
		{
			Log4J.logp.info("---------------- Started codeSearchInCodingPnl for rowId=" + rowId + " ----------------");
			driver = ExecutionSetup.getDriver();
			dragger = new Actions(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			codingpnl_webe = CodingPnl_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			wait = new WebDriverWait(driver, 20);
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case(rowId);
			//to scroll page
			Common_Lib.scroll_without_element();
			Log4J.logp.info("Case result number is =" + landingp_webe.no_caseOnTable.getText());

			if (landingp_webe.no_caseOnTable.getText().contains("0"))
			{
				Log4J.logp.info("No case is In progress");
				Log4J.logp.info("In caseInprogerssToOnholdOrPending- completed");
				Common_Lib.scrollUp_without_element();
				searchCriteria_webe.lnk_SearchCreteria.click();
				Thread.sleep(1000);
				return true;
			}
			else
			{
				Log4J.logp.info("One case is In progress");
				//click on that case
				table = landingp_webe.tbl_SearchResult;
				//find click-able column
				row = table.findElement(By.tagName("td"));
				row.click();
				//case is open
				//wait.until(ExpectedConditions.elementToBeClickable(groupingpnl_webe.btn_Later));

				//driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				//Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 10);
				wait.until(ExpectedConditions.elementToBeClickable(groupingpnl_webe.btn_Later));
				Thread.sleep(3000);
				groupingpnl_webe.btn_Later.click();
				Thread.sleep(2000);
				Log4J.logp.info("---------------- Ending - caseInprogerssToOnholdOrPending ----------------");

				return true;
			}

		}
		catch (Exception e)
		{
			Log4J.logp.error("----------------  caseInprogerssToOnholdOrPending is failed ----------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is to right click on first system suggested evidence on which no issue has been raised
	 * 
	 * @author fmodi
	 * @since 17/10/2014
	 * */
	public static boolean rightclick_system_sugggested_evidence()
	{

		driver = ExecutionSetup.getDriver();
		comman_webe = Comman_WebE.getInstance(driver);

		try
		{
			Log4J.logp.info("---------------- Started - rightclick_system_sugggested_evidence ----------------");
			Actions action = new Actions(driver);
			action.moveToElement(comman_webe.lnk_sys_evidence.get(0)).contextClick().build().perform();
			Thread.sleep(3000);
			Log4J.logp.info("---------------- Ended - rightclick_system_sugggested_evidence ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- Not able to right click on evidence ----------------");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for check where web element is click-able or not and click on that element
	 * 
	 * @author skhalasi
	 * @param webe
	 * @since 20/10/2014
	 * @return
	 */
	public static boolean verify_ElemnenIsClickableAndClick(WebElement webe)
	{

		try
		{
			Log4J.logp.info("---------------- Started - verify_ElemnenIsClickableAndClick ----------------");

			driver = ExecutionSetup.getDriver();
			landingp_webe = LandingP_WebE.getInstance(driver);
			comman_webe = Comman_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			demographicpnl_webe = DemographicPnl_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			abstractpnl_webe = AbstractPnl_WebE.getInstance(driver);
			codingpnl_webe = CodingPnl_WebE.getInstance(driver);
			codeBook_webe = CodeBook_WebeE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			wait = new WebDriverWait(driver, 20);
			if (webe.getText() != null)
			{
				Log4J.logp.info("The text for element is = " + webe.getText());
			}

			if (webe.isDisplayed())
			{
				webe.click();
				Thread.sleep(1500);
				Log4J.logp.info("---------------- Ending - verify_ElemnenIsClickableAndClick ----------------");

				return true;
			}
			else
			{
				Log4J.logp.info("---------------- Ending - verify_ElemnenIsClickableAndClick ----------------");

				return false;
			}

		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- Not able to click on evidence ----------------");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is used for explicit wait
	 * 
	 * @author jpatel
	 * @since 25/11/2014
	 * @param webe
	 * @param strProperty
	 * @param d
	 * @return boolean
	 * 
	 *         this takes three arguments - webelement , condition to be true, max time you want to wait
	 * 
	 */

	public static boolean waitForObject(WebElement webe, String strProperty, long d)
	{

		try
		{

			Log4J.logp.info("---------------- Started - waitForObject ----------------");
			//Log4J.logp.info("In wait for Object Method for  :=" + webe.getText());
			driver = ExecutionSetup.getDriver();

			wait = new WebDriverWait(driver, d);

			if (strProperty.equalsIgnoreCase("visibility"))
			{
				wait.until(ExpectedConditions.visibilityOf(webe));
			}
			else if (strProperty.equalsIgnoreCase("clickable"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(webe));
			}
			else if (strProperty.equalsIgnoreCase("selectable"))
			{

				wait.until(ExpectedConditions.elementToBeSelected(webe));

			}

			else
			{

				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(strProperty));
			}

			wait = null;
			return true;

		}
		catch (Exception e)
		{
			Log4J.logp.error("Errors in waitForObject  ");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for directly vertical scroll to the element for dragger div
	 * 
	 * @author skhalasi
	 * @since 10/11/2014
	 * @param webe
	 * @param drag
	 * @return
	 */

	public static boolean scrollToWebE(WebElement webe, WebElement drag)
	{

		try
		{
			Log4J.logp.info("---------------- Started - scrollToWebE ----------------");

			driver = ExecutionSetup.getDriver();
			landingp_webe = LandingP_WebE.getInstance(driver);
			comman_webe = Comman_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			demographicpnl_webe = DemographicPnl_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			abstractpnl_webe = AbstractPnl_WebE.getInstance(driver);
			codingpnl_webe = CodingPnl_WebE.getInstance(driver);
			codeBook_webe = CodeBook_WebeE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			wait = new WebDriverWait(driver, 20);

			Actions dragger = new Actions(driver);
			//get point
			Point ps = webe.getLocation();
			Thread.sleep(2000);
			//get y point
			if (!webe.isDisplayed())
			{
				int y = ps.getY();

				y = y / 10;

				// (y/10) means convert point in moveByOffset format
				Thread.sleep(2000);
				dragger.moveToElement(drag).clickAndHold().moveByOffset(0, y).release().build().perform();
				//dragger.clickAndHold(drag).moveToElement(drag, 0, y).release(drag).build().perform();
				Thread.sleep(2000);

				if (!webe.isDisplayed())
				{
					Common_Lib.scroll_Until_FindWebe(drag, webe, 15);
				}
			}
			Thread.sleep(1000);
			Log4J.logp.info("---------------- Ending - scrollToWebE ----------------");

			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- In scrollToWebE - Not able to scroll to web element ----------------");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for putting the case in "Pending" status by raising a discussion
	 * 
	 * @author fmodi
	 * @since 21/11/2014
	 * */
	public static boolean putCaseInPendingStatus(String getRowId)
	{

		try
		{
			Log4J.logp.info("---------------- Started - putCaseInPendingStatus ----------------");

			driver = ExecutionSetup.getDriver();
			comman_webe = Comman_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);

			Thread.sleep(2000);
			Common_Lib.openCase(getRowId);
			//Thread.sleep(15000);
			Common_Lib.waitForObject(comman_webe.lnk_sys_evidence.get(0), "clickable", 15);

			comman_webe.lnk_sys_evidence.get(0).click();
			Thread.sleep(2000);

			Common_Lib.rightclick_system_sugggested_evidence();

			medicalrecordpnl_webe.lnk_discusswithcolleague.click();

			IssuePanel_Lib.send_DiscussWithColleague("fz001");
			Thread.sleep(2000);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			Log4J.logp.info("Issue Panel Closed");

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			Log4J.logp.info("---------------- Ending - putCaseInPendingStatus ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- Case not successfully put in 'Pending' status ----------------");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for check status of code like accepted or rejected or modified
	 * 
	 * @author skhalasi
	 * @since 21/11/2014
	 * */
	public static boolean checkStatusOfCode(String codeType)
	{
		int strCodeCount = 0;
		try
		{
			Log4J.logp.info("---------------- Started - checkStatusOfCode ----------------");

			driver = ExecutionSetup.getDriver();
			comman_webe = Comman_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			codingpnl_webe = CodingPnl_WebE.getInstance(driver);

			strCodeCount = codingpnl_webe.lst_probableCount_Admit.size();

			Log4J.logp.info("Total probable code in admitting diagnosis is " + strCodeCount);

			/*ArrayList<ArrayList<WebElement>> array = new ArrayList<ArrayList<WebElement>>();

			array.get(0).add(codingpnl_webe.lnk_Accept_Admitting);
			array.get(1).add(codingpnl_webe.lnk_Accept_Principal);
			System.out.println(array.get(0));*/

			//ArrayList<WebElement> array = new ArrayList<WebElement>();

			Map<String, List<WebElement>> lst_probCode = new HashMap<String, List<WebElement>>();
			String[] arr_codeNumber = new String[strCodeCount];

			for (int i = 0; i < strCodeCount; i++)
			{
				arr_codeNumber[i] = codingpnl_webe.lst_probableCount_Admit.get(i).findElement(By.xpath("//div[@class='num num-container edit-code']")).getText();
				lst_probCode.put(arr_codeNumber[i], codingpnl_webe.lst_probableCount_Admit.get(i).findElements(By.xpath("//div[@id='code-operation']/span[contains(@id,'code')]")));

			}

			//	lst_probCode.get().

			//System.out.println(lst_probCode.get(codingpnl_webe.lst_probableCount_Admit.get(0)).get(0).getAttribute("id"));

			/*array.add(codingpnl_webe.lnk_Accept_Admitting);
			array.add(codingpnl_webe.lnk_Accept_Principal);

			ArrayList<ArrayList<WebElement>> array1 = new ArrayList<ArrayList<WebElement>>();

			array1.add(array);
			*/
			Log4J.logp.info("---------------- Ending - checkStatusOfCode ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- Problem found in - checkStatusOfCode ----------------");
			e.printStackTrace();
			return false;
		}

	}
}
