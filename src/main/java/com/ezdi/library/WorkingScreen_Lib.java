package com.ezdi.library;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ezdi.framework.common.JDBCMySql;
import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
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
 * 
 * @author skhalasi
 * @author agupta
 * @author fmodi
 * @author jsolanki
 *
 */
public class WorkingScreen_Lib
{
	public static WebDriver				driver;
	public static CodeBook_WebeE		codeBook_webe;
	public static CodingPnl_WebE		codingPnl_webe;
	public static LandingP_WebE			landingp_webe;
	public static GroupinPnl_WebE		groupingpnl_webe;
	public static MedicalRecordPnl_WebE	medicalrecordpnl_webe;
	public static Comman_WebE			common_webe;
	public static IssuePnl_WebE			issuepnl_webe;
	public static DemographicPnl_WebE	demographicpnl_webe;
	public static SearchCriteria_WebE	searchCriteria_webe;
	public static String				strdrg;

	/**
	 * To add code in ICD 9.This method called by working screen. We also call search_Code_ICD9 inside this method which search our code and add it
	 *
	 * We get data from "td_searchcode"
	 * 
	 * @author skhalasi
	 * @since 08/09/2014
	 * @param rowId
	 */
	public static boolean addnewCode_withData_ICD9(String rowId)
	{
		boolean bstatus = false;
		boolean balertStatus = false;

		WebDriverWait wait;
		String strcodeType = null;
		try
		{

			Log4J.logp.info("---------------- Started - addnewCode_withData_ICD9 ----------------");

			driver = ExecutionSetup.getDriver();
			wait = new WebDriverWait(driver, 20);
			landingp_webe = LandingP_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			codeBook_webe = CodeBook_WebeE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);

			//to get code type from database
			JDBCMySql query = new JDBCMySql();
			strcodeType = query.getValuebyColumnName("td_searchcode", rowId, "codetype");
			Log4J.logp.info("codeType is--" + strcodeType);

			//add admitting code in icd9
			if (strcodeType.equalsIgnoreCase("admitdiagnosisicd9"))
			{
				Log4J.logp.info("For codetype--" + strcodeType);
				//scroll for find add link
				bstatus = Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_addAdmit_DignosisCode_ICD9, 11);
				//bstatus = Common_Lib.scrollToWebE(codingPnl_webe.lnk_addAdmit_DignosisCode_ICD9, codingPnl_webe.coding_Dragger);
				//Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
				Thread.sleep(500);
				if (bstatus == true)
				{
					Log4J.logp.info("Add Admit Diagnosis code link found--" + strcodeType);

					//if element is found then click on it
					//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.lnk_addAdmit_DignosisCode_ICD9));
					Common_Lib.waitForObject(codingPnl_webe.lnk_addAdmit_DignosisCode_ICD9, "clickable", 10);

					codingPnl_webe.lnk_addAdmit_DignosisCode_ICD9.click();
				}
				else
				{
					Log4J.logp.info("Element is not found for click");
					Assert.assertTrue(bstatus, "Element " + codingPnl_webe.lnk_addAdmit_DignosisCode_ICD9 + " is not found for Add Code");
					return false;
				}
				Log4J.logp.info("Add Admit Diagnosis link clicked");

				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
				//Thread.sleep(3000);
				//wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
				Common_Lib.waitForObject(codeBook_webe.webe_FindAll, "visibility", 10);

				Log4J.logp.info("Add Admit Diagnosis codebook frame switched ");

				bstatus = WorkingScreen_Lib.search_Code_ICD9(rowId, strcodeType);
				if (bstatus == false)
				{
					return false;
				}
				Log4J.logp.info("Add admitDignosis code is searched ");

				Thread.sleep(500);
				//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.btn_saveAdmitDignosis_ICD9));
				Common_Lib.waitForObject(codingPnl_webe.btn_saveAdmitDignosis_ICD9, "clickable", 10);
				Thread.sleep(500);
				//uncomment when you want to save code...
				codingPnl_webe.btn_saveAdmitDignosis_ICD9.click();
				//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Thread.sleep(1000);
				balertStatus = Common_Lib.IsCustomAlertPresent();
				if (balertStatus)
				{
					Log4J.logp.info("Admit Diagnosis is already added");
					return true;

				}

				Log4J.logp.info("Add Admit Diagnosis completed in Lib");

			}
			//add diagnosis in icd9
			if (strcodeType.equalsIgnoreCase("diagnosisicd9"))
			{
				Log4J.logp.info("For codetype--" + strcodeType);
				//scroll for find add link
				bstatus = Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_addDignosisCode_ICD9, 20);
				//bstatus = Common_Lib.scrollToWebE(codingPnl_webe.lnk_addDignosisCode_ICD9, codingPnl_webe.coding_Dragger);
				Thread.sleep(1000);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
				if (bstatus == true)
				{
					Log4J.logp.info("Add diagnosis code link found--" + strcodeType);

					//if element is found then click on it
					//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.lnk_addDignosisCode_ICD9));
					Common_Lib.waitForObject(codingPnl_webe.lnk_addDignosisCode_ICD9, "clickable", 10);

					codingPnl_webe.lnk_addDignosisCode_ICD9.click();
				}
				else
				{
					Log4J.logp.info("Element is not found for click");
					Assert.assertTrue(bstatus, "Element " + codingPnl_webe.lnk_addDignosisCode_ICD9 + " is not found for Add Code");
					return false;
				}
				Log4J.logp.info("Add Diagnosis link clicked");

				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				//Thread.sleep(3000);
				//wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
				Common_Lib.waitForObject(codeBook_webe.webe_FindAll, "visibility", 10);

				Log4J.logp.info("Add Diagnosis codebook frame switched ");

				bstatus = WorkingScreen_Lib.search_Code_ICD9(rowId, strcodeType);
				if (bstatus == false)
				{
					return false;
				}
				Log4J.logp.info("Add Diagnosis code is searched ");

				Thread.sleep(500);
				//	wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.btn_saveDignosis_ICD9));
				Common_Lib.waitForObject(codingPnl_webe.btn_saveDignosis_ICD9, "clickable", 10);

				Thread.sleep(500);
				//uncomment when you want to save code...
				codingPnl_webe.btn_saveDignosis_ICD9.click();

				balertStatus = Common_Lib.IsCustomAlertPresent();
				if (balertStatus)
				{
					Log4J.logp.info("Diagnosis is already added");
					return true;
				}
				Log4J.logp.info("Add Diagnosis completed in Lib");

			}
			//add procedure ICD9
			if (strcodeType.equalsIgnoreCase("procedureicd9"))
			{
				Log4J.logp.info("For codetype--" + strcodeType);
				//scroll for find add link
				bstatus = Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_addProcedureCode_ICD9, 11);
				//bstatus = Common_Lib.scrollToWebE(codingPnl_webe.lnk_addProcedureCode_ICD9, codingPnl_webe.coding_Dragger);
				Thread.sleep(500);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
				if (bstatus == true)
				{
					Log4J.logp.info("Add procedure code link found--" + strcodeType);

					//if element is found then click on it
					//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.lnk_addProcedureCode_ICD9));
					Common_Lib.waitForObject(codingPnl_webe.lnk_addProcedureCode_ICD9, "clickable", 10);
					codingPnl_webe.lnk_addProcedureCode_ICD9.click();
				}
				else
				{
					Log4J.logp.info("Element is not found for click");
					Assert.assertTrue(bstatus, "Element " + codingPnl_webe.lnk_addProcedureCode_ICD9 + " is not found for Add Code");
					return false;
				}
				Log4J.logp.info("Add procedure link clicked");

				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				//Thread.sleep(3000);
				//wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
				Common_Lib.waitForObject(codeBook_webe.webe_FindAll, "visibility", 10);

				Log4J.logp.info("Add procedure codebook frame switched ");

				bstatus = WorkingScreen_Lib.search_Code_ICD9(rowId, strcodeType);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 15);
				if (bstatus == false)
				{
					return false;
				}
				Log4J.logp.info("Add procedure code is searched ");

				Thread.sleep(500);
				//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.btn_saveProcedure_ICD9));
				Common_Lib.waitForObject(codingPnl_webe.btn_saveProcedure_ICD9, "clickable", 10);

				Thread.sleep(500);
				//uncomment when you want to save code...
				codingPnl_webe.btn_saveProcedure_ICD9.click();
				balertStatus = Common_Lib.IsCustomAlertPresent();
				if (balertStatus)
				{
					Log4J.logp.info("Procedure is already added");
					return true;
				}
				Log4J.logp.info("Add procedure completed in Lib");

			}

			//add CPT code
			if (strcodeType.equalsIgnoreCase("cpticd9"))
			{
				Log4J.logp.info("For codetype--" + strcodeType);
				//scroll for find add link
				bstatus = Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_addCPTcode, 11);
				//bstatus = Common_Lib.scrollToWebE(codingPnl_webe.lnk_addCPTcode, codingPnl_webe.coding_Dragger);
				Thread.sleep(500);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
				if (bstatus == true)
				{
					Log4J.logp.info("Add CPT code link found--" + strcodeType);
					//if element is found then click on it
					//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.lnk_addCPTcode));
					Common_Lib.waitForObject(codingPnl_webe.lnk_addCPTcode, "clickable", 10);

					codingPnl_webe.lnk_addCPTcode.click();
				}
				else
				{
					Log4J.logp.info("Element is not found for click");
					Assert.assertTrue(bstatus, "Element " + codingPnl_webe.lnk_addCPTcode + " is not found for Add Code");
					return false;
				}
				Log4J.logp.info("Add CPT link clicked");

				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				Thread.sleep(3000);
				//wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_UseEnethesia));
				Common_Lib.waitForObject(codeBook_webe.webe_UseEnethesia, "visibility", 10);

				Log4J.logp.info("Add CPT codebook frame switched ");

				bstatus = WorkingScreen_Lib.search_Code_ICD9(rowId, strcodeType);
				if (bstatus == false)
				{
					return false;
				}
				Log4J.logp.info("Add CPT code is searched ");

				Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 15);

				Thread.sleep(1000);
				//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.btn_saveCPT));
				Common_Lib.waitForObject(codingPnl_webe.btn_saveCPT, "clickable", 10);

				//uncomment when you want to save code...
				codingPnl_webe.btn_saveCPT.click();
				balertStatus = Common_Lib.IsCustomAlertPresent();
				if (balertStatus)
				{
					Log4J.logp.info("CPT is already added");
					return true;
				}
				Log4J.logp.info("Add CPT completed in Lib");

			}
			//add E & M code
			if (strcodeType.equalsIgnoreCase("enmicd9"))
			{
				Log4J.logp.info("For codetype--" + strcodeType);
				//scroll for find add link
				bstatus = Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_addEandMcode, 15);
				//bstatus = Common_Lib.scrollToWebE(codingPnl_webe.lnk_addEandMcode, codingPnl_webe.coding_Dragger);
				Thread.sleep(500);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
				if (bstatus == true)
				{
					Log4J.logp.info("Add E & M  code link found--" + strcodeType);

					//if element is found then click on it
					//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.lnk_addEandMcode));
					Common_Lib.waitForObject(codingPnl_webe.lnk_addEandMcode, "clickable", 10);

					codingPnl_webe.lnk_addEandMcode.click();
				}
				else
				{
					Log4J.logp.info("Element is not found for click");
					Assert.assertTrue(bstatus, "Element " + codingPnl_webe.lnk_addEandMcode + " is not found for Add Code");
					return false;
				}
				Log4J.logp.info("Add E & M link clicked");

				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				Thread.sleep(3000);
				//wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_UseEnethesia));
				Common_Lib.waitForObject(codeBook_webe.webe_UseEnethesia, "visibility", 10);

				Log4J.logp.info("Add E & M codebook frame switched ");

				bstatus = WorkingScreen_Lib.search_Code_ICD9(rowId, strcodeType);
				if (bstatus == false)
				{
					return false;
				}
				Log4J.logp.info("Add E & M code is searched ");

				Thread.sleep(500);
				//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.btn_saveEandM));
				Common_Lib.waitForObject(codingPnl_webe.btn_saveEandM, "clickable", 10);

				Thread.sleep(500);
				//uncomment when you want to save code...
				codingPnl_webe.btn_saveEandM.click();
				balertStatus = Common_Lib.IsCustomAlertPresent();
				if (balertStatus)
				{
					Log4J.logp.info("E & M is already added");
					return true;
				}
				Log4J.logp.info("Add E & M completed in Lib");

			}
			//add HCPSC code
			if (strcodeType.equalsIgnoreCase("hcpcsicd9"))
			{
				Log4J.logp.info("For codetype--" + strcodeType);
				//scroll for find add link
				bstatus = Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_addHCPCScode, 15);
				//bstatus = Common_Lib.scrollToWebE(codingPnl_webe.lnk_addHCPCScode, codingPnl_webe.coding_Dragger);
				Thread.sleep(500);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
				if (bstatus == true)
				{
					Log4J.logp.info("If add HCPCS  code link found--" + strcodeType);

					//if element is found then click on it
					//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.lnk_addHCPCScode));
					Common_Lib.waitForObject(codingPnl_webe.lnk_addHCPCScode, "clickable", 10);
					codingPnl_webe.lnk_addHCPCScode.click();
				}
				else
				{
					Log4J.logp.info("Element is not found for click");
					Assert.assertTrue(bstatus, "Element " + codingPnl_webe.lnk_addHCPCScode + " is not found for Add Code");
					return false;
				}
				Log4J.logp.info("Add HCPCS link clicked");

				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				//	wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_Lable_CodeBook));
				Thread.sleep(3000);
				Log4J.logp.info("Add HCPCS codebook frame switched ");

				bstatus = WorkingScreen_Lib.search_Code_ICD9(rowId, strcodeType);
				if (bstatus == false)
				{
					return false;
				}
				Log4J.logp.info("Add HCPCS code is searched ");

				Thread.sleep(500);
				//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.btn_saveHCPCS));
				Common_Lib.waitForObject(codingPnl_webe.btn_saveHCPCS, "clickable", 10);

				Thread.sleep(500);
				//uncomment when you want to save code...
				codingPnl_webe.btn_saveHCPCS.click();
				balertStatus = Common_Lib.IsCustomAlertPresent();
				if (balertStatus)
				{
					Log4J.logp.info(" HCPCS is already added");
					return true;
				}
				Log4J.logp.info("Add HCPCS completed in Lib");

			}

			Log4J.logp.info("---------------- Ending - addnewCode_withData_ICD9 ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- Problem found In - addnewCode_withData_ICD9 ----------------");
			e.printStackTrace();
			return false;
		}
		finally
		{
			/*if (driver != null)
			{
			driver = null;
			}
			if (landingp_webe != null)
			{
			landingp_webe = null;
			}
			if (groupingpnl_webe != null)
			{
			groupingpnl_webe = null;
			}
			if (codingPnl_webe != null)
			{
			codingPnl_webe = null;
			}

			if (codeBook_webe != null)
			{
			codeBook_webe = null;
			}*/
		}

	}

	/**
	 * This method is for search code in ICD 9 .
	 * 
	 * We get data from "td_searchcode"
	 * 
	 * @author skhalasi
	 * @since 08/09/2014
	 * @param rowId
	 * @param codeType
	 * @return
	 */
	public static boolean search_Code_ICD9(String rowId, String codeType)
	{
		String strcode = null;
		String strevidenceFoundIn = null;
		JDBCMySql query = null;
		Map<String, String> rowTestData = null;
		//WebDriverWait wait;
		String getCode = null;
		try
		{
			Log4J.logp.info("---------------- In search_Code_ICD9 -- for rowId   " + rowId + " ----------------");

			query = new JDBCMySql();
			driver = ExecutionSetup.getDriver();
			//wait = new WebDriverWait(driver, 20);
			codeBook_webe = CodeBook_WebeE.getInstance(driver);
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);

			JDBCMySql searchCode_TestData = new JDBCMySql();

			//to get list of td_searchcode
			rowTestData = searchCode_TestData.getRowbyID("td_searchcode", rowId);

			strcode = rowTestData.get("code");
			strevidenceFoundIn = rowTestData.get("evifoundin");

			if (strcode != null)
			{
				codeBook_webe.txt_searchCode.clear();
				Thread.sleep(1000);
				codeBook_webe.txt_searchCode.sendKeys(strcode);
			}

			Log4J.logp.info("All data inserted for rowId   " + rowId);
			Thread.sleep(1000);
			codeBook_webe.btn_search.click();

			getCode = query.getValuebyColumnName("td_searchcode", rowId, "code");
			Log4J.logp.info("Our inserting code is --" + getCode);

			if (strevidenceFoundIn != null)
			{
				if (codeType.equalsIgnoreCase("admitdiagnosisicd9"))
				{
					//wait.until(ExpectedConditions.elementToBeClickable(codeBook_webe.lnk_searchCode));
					Common_Lib.waitForObject(codeBook_webe.lnk_searchCode, "clickable", 10);

					//to click on searched code
					codeBook_webe.lnk_searchCode.click();

					Log4J.logp.info("Searched code is  added to coding panel for rowId   " + rowId);

					//come back to main working screen
					driver.switchTo().defaultContent();

					//to select dropdown for admit diagnosis
					Log4J.logp.info("In coding panel for codetype --" + codeType);

					Select dropdown = new Select(codingPnl_webe.lst_admit_Diagnosis_ICD9);
					dropdown.selectByVisibleText(strevidenceFoundIn);

				}
				else if (codeType.equalsIgnoreCase("diagnosisicd9"))
				{
					//wait.until(ExpectedConditions.elementToBeClickable(codeBook_webe.lnk_searchCode));
					Common_Lib.waitForObject(codeBook_webe.lnk_searchCode, "clickable", 10);

					//to click on searched code
					codeBook_webe.lnk_searchCode.click();

					Log4J.logp.info("Searched code is  added to coding panel for rowId   " + rowId);

					//come back to main working screen
					driver.switchTo().defaultContent();
					if (!codingPnl_webe.btn_saveDignosis_ICD9.isDisplayed())
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 30);
					}
					Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 7);

					//to select dropdown for diagnosis
					Log4J.logp.info("In coding panel for codetype --" + codeType);

					Select dropdown = new Select(codingPnl_webe.lst_secondDiagnosis_ICD9);
					dropdown.selectByVisibleText(strevidenceFoundIn);

				}
				else if (codeType.equalsIgnoreCase("procedureicd9"))
				{
					//wait.until(ExpectedConditions.elementToBeClickable(codeBook_webe.lnk_searchCode));
					Common_Lib.waitForObject(codeBook_webe.lnk_searchCode, "clickable", 10);

					//to click on searched code
					codeBook_webe.lnk_searchCode.click();

					Log4J.logp.info("Searched code is  added to coding panel for rowId   " + rowId);

					//come back to main working screen
					driver.switchTo().defaultContent();
					if (!codingPnl_webe.btn_saveProcedure_ICD9.isDisplayed())
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 30);
					}

					//to select dropdown for procedure
					Log4J.logp.info("In coding panel for codetype --" + codeType);

					Select dropdown = new Select(codingPnl_webe.lst_procedure_ICD9);
					dropdown.selectByVisibleText(strevidenceFoundIn);

				}
				else if (codeType.equalsIgnoreCase("cpticd9"))
				{
					//wait.until(ExpectedConditions.elementToBeClickable(codeBook_webe.lnk_searchCode));
					Common_Lib.waitForObject(codeBook_webe.lnk_searchCode, "clickable", 10);

					//to click on searched code
					codeBook_webe.lnk_searchCode.click();

					Log4J.logp.info("Searched code is  added to coding panel for rowId   " + rowId);

					//come back to main working screen
					driver.switchTo().defaultContent();
					if (!codingPnl_webe.btn_saveCPT.isDisplayed())
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 30);
					}

					//to select dropdown for cpt
					Log4J.logp.info("In coding panel for codetype --" + codeType);

					Select dropdown = new Select(codingPnl_webe.lst_CPT);
					dropdown.selectByVisibleText(strevidenceFoundIn);

				}
				else if (codeType.equalsIgnoreCase("enmicd9"))
				{
					//wait.until(ExpectedConditions.elementToBeClickable(codeBook_webe.lnk_searchCode));
					Common_Lib.waitForObject(codeBook_webe.lnk_searchCode, "clickable", 10);

					//to click on searched code
					codeBook_webe.lnk_searchCode.click();

					Log4J.logp.info("Searched code is  added to coding panel for rowId   " + rowId);

					//come back to main working screen
					driver.switchTo().defaultContent();
					if (!codingPnl_webe.btn_saveEandM.isDisplayed())
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 30);
					}

					//to select dropdown for E & M
					//if we add E & M code please add code started with 99. Eg.99201
					Log4J.logp.info("In coding panel for codetype --" + codeType);

					Select dropdown = new Select(codingPnl_webe.lst_EandM);
					dropdown.selectByVisibleText(strevidenceFoundIn);

				}
				else if (codeType.equalsIgnoreCase("hcpcsicd9"))
				{
					//wait.until(ExpectedConditions.elementToBeClickable(codeBook_webe.lnk_searchCode));
					Common_Lib.waitForObject(codeBook_webe.lnk_searchCode, "clickable", 10);

					//to click on searched code
					codeBook_webe.lnk_searchCode.click();

					Log4J.logp.info("Searched code is  added to coding panel for rowId   " + rowId);

					//come back to main working screen
					driver.switchTo().defaultContent();
					if (!codingPnl_webe.btn_saveHCPCS.isDisplayed())
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 30);
					}

					//to select dropdown for admit HCPSC
					Log4J.logp.info("In coding panel for codetype --" + codeType);

					Select dropdown = new Select(codingPnl_webe.lst_HCPCS);
					dropdown.selectByVisibleText(strevidenceFoundIn);

				}

				else
				{
					Log4J.logp.error("Dropdown for select page is not found");
					return false;
				}
			}
			Log4J.logp.info("----------------  search_Code_ICD9 completed ---------------- ");
			return true;
		}
		catch (Exception e)
		{

			Log4J.logp.error("----------------  In search_Code_ICD9 --searched code is failed for rowId   " + rowId + " ---------------- ");
			driver.switchTo().defaultContent();
			codeBook_webe.btn_closeCodeBook.click();
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * To add code in ICD 10.This method called by working screen.
	 * 
	 * We also call search_Code_ICD10 inside this method which search our code and add it.
	 * 
	 * We get data from "td_searchcode"
	 * 
	 * @author skhalasi
	 * @since 09/09/2014
	 * @param rowId
	 * 
	 */
	public static boolean addnewCode_withData_ICD10(String rowId)
	{
		boolean bstatus;
		boolean balertStatus = false;
		WebDriverWait wait;
		String getcodeType = null;

		try
		{

			Log4J.logp.info("---------------- Started - addnewCode_withData_ICD10 ----------------");

			driver = ExecutionSetup.getDriver();
			wait = new WebDriverWait(driver, 20);
			landingp_webe = LandingP_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			codeBook_webe = CodeBook_WebeE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);

			//to get code type from database
			JDBCMySql query = new JDBCMySql();
			getcodeType = query.getValuebyColumnName("td_searchcode", rowId, "codetype");
			Log4J.logp.info("codeType is--" + getcodeType);

			//add admitting code for ICD 10
			if (getcodeType.equalsIgnoreCase("admitdiagnosisicd10"))
			{
				Log4J.logp.info("For codetype--" + getcodeType);
				//scroll for find add link
				bstatus = Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_addAdmit_DignosisCode_ICD10, 11);
				//bstatus = Common_Lib.scrollToWebE(codingPnl_webe.lnk_addAdmit_DignosisCode_ICD10, codingPnl_webe.coding_Dragger);
				Thread.sleep(500);
				if (bstatus == true)
				{
					Log4J.logp.info("If add Admit diagnosis code link found--" + getcodeType);

					//if element is found then click on it
					//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.lnk_addAdmit_DignosisCode_ICD10));
					Common_Lib.waitForObject(codingPnl_webe.lnk_addAdmit_DignosisCode_ICD10, "clickable", 10);

					codingPnl_webe.lnk_addAdmit_DignosisCode_ICD10.click();
				}
				else
				{
					Log4J.logp.info("Element is not found for click");
					Assert.assertTrue(bstatus, "Element " + codingPnl_webe.lnk_addAdmit_DignosisCode_ICD10 + " is not found for Add Code");
					return false;
				}
				Log4J.logp.info("Add Admit diagnosis link clicked");

				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				Thread.sleep(3000);
				//wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
				Common_Lib.waitForObject(codeBook_webe.webe_FindAll, "visibility", 10);

				Log4J.logp.info("Add Admit Diagnosis codebook frame switched ");

				bstatus = WorkingScreen_Lib.search_Code_ICD10(rowId, getcodeType);
				if (bstatus == false)
				{
					return false;
				}
				Log4J.logp.info("Add Admit diagnosis code is searched ");

				Thread.sleep(500);
				//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.btn_saveAdmitDignosis_ICD10));
				Common_Lib.waitForObject(codingPnl_webe.btn_saveAdmitDignosis_ICD10, "clickable", 10);

				Thread.sleep(500);
				//uncomment when you want to save code...
				codingPnl_webe.btn_saveAdmitDignosis_ICD10.click();
				balertStatus = Common_Lib.IsCustomAlertPresent();
				if (balertStatus)
				{
					Log4J.logp.info("Admit diagnosis is already added");
					return true;
				}

				Log4J.logp.info("Add Admit diagnosis completed in Lib");

			}
			//add diagnosis in ICD 10
			if (getcodeType.equalsIgnoreCase("diagnosisicd10"))
			{
				Log4J.logp.info("For codetype--" + getcodeType);
				//scroll for find add link
				bstatus = Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_addDignosisCode_ICD10, 11);
				//bstatus = Common_Lib.scrollToWebE(codingPnl_webe.lnk_addDignosisCode_ICD10, codingPnl_webe.coding_Dragger);
				Thread.sleep(1000);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
				Thread.sleep(500);
				if (bstatus == true)
				{
					Log4J.logp.info("If add diagnosis code link found--" + getcodeType);

					//if element is found then click on it
					//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.lnk_addDignosisCode_ICD10));
					Common_Lib.waitForObject(codingPnl_webe.lnk_addDignosisCode_ICD10, "clickable", 10);

					codingPnl_webe.lnk_addDignosisCode_ICD10.click();
				}
				else
				{
					Log4J.logp.info("Element is not found for click");
					Assert.assertTrue(bstatus, "Element " + codingPnl_webe.lnk_addDignosisCode_ICD10 + " is not found for Add Code");
					return false;
				}
				Log4J.logp.info("Add Diagnosis link clicked");

				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				Thread.sleep(4000);
				//wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
				Common_Lib.waitForObject(codeBook_webe.webe_FindAll, "visibility", 10);

				Log4J.logp.info("Add Diagnosis codebook frame switched ");

				bstatus = WorkingScreen_Lib.search_Code_ICD10(rowId, getcodeType);
				if (bstatus == false)
				{
					return false;
				}
				Log4J.logp.info("Add Diagnosis code is searched ");

				Thread.sleep(2000);
				//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.btn_saveDignosis_ICD10));
				Common_Lib.waitForObject(codingPnl_webe.btn_saveDignosis_ICD10, "clickable", 10);

				Thread.sleep(2000);
				//uncomment when you want to save code...
				codingPnl_webe.btn_saveDignosis_ICD10.click();
				balertStatus = Common_Lib.IsCustomAlertPresent();
				if (balertStatus)
				{
					Log4J.logp.info("Diagnosis is already added");
					return true;
				}

				Log4J.logp.info("Add Diagnosis completed in Lib");

			}
			//add procedure in ICD10
			if (getcodeType.equalsIgnoreCase("procedureicd10"))
			{
				Log4J.logp.info("For codetype--" + getcodeType);
				//scroll for find add link
				bstatus = Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_addProcedureCode_ICD10, 11);
				//bstatus = Common_Lib.scrollToWebE(codingPnl_webe.lnk_addProcedureCode_ICD10, codingPnl_webe.coding_Dragger);
				Thread.sleep(1000);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 15);
				if (bstatus == true)
				{
					Log4J.logp.info("If add procedure code link found--" + getcodeType);

					//if element is found then click on it
					//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.lnk_addProcedureCode_ICD10));
					Common_Lib.waitForObject(codingPnl_webe.lnk_addProcedureCode_ICD10, "clickable", 10);

					codingPnl_webe.lnk_addProcedureCode_ICD10.click();
				}
				else
				{
					Log4J.logp.info("Element is not found for click");
					Assert.assertTrue(bstatus, "Element " + codingPnl_webe.lnk_addProcedureCode_ICD10 + " is not found for Add Code");
					return false;
				}
				Log4J.logp.info("Add procedure link clicked");

				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				Thread.sleep(3000);
				//wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
				Common_Lib.waitForObject(codeBook_webe.webe_FindAll, "visibility", 10);

				Log4J.logp.info("Add procedure codebook frame switched ");

				bstatus = WorkingScreen_Lib.search_Code_ICD10(rowId, getcodeType);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 15);
				if (bstatus == false)
				{
					return false;
				}
				Log4J.logp.info("Add procedure code is searched ");

				Thread.sleep(2000);
				//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.btn_saveProcedure_ICD10));
				Common_Lib.waitForObject(codingPnl_webe.btn_saveProcedure_ICD10, "clickable", 10);

				Thread.sleep(2000);
				//uncomment when you want to save code...
				codingPnl_webe.btn_saveProcedure_ICD10.click();
				balertStatus = Common_Lib.IsCustomAlertPresent();
				if (balertStatus)
				{
					Log4J.logp.info("Procedure is already added");
					return true;
				}

				Log4J.logp.info("Add procedure completed in Lib");

			}
			//add CPT code in ICD 10
			if (getcodeType.equalsIgnoreCase("cpticd10"))
			{
				Log4J.logp.info("For codetype--" + getcodeType);
				//scroll for find add link
				bstatus = Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_addCPTcode, 11);
				//bstatus = Common_Lib.scrollToWebE(codingPnl_webe.lnk_addCPTcode, codingPnl_webe.coding_Dragger);
				Thread.sleep(1000);
				if (bstatus == true)
				{
					Log4J.logp.info("If add CPT code link found--" + getcodeType);

					//if element is found then click on it
					//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.lnk_addCPTcode));
					Common_Lib.waitForObject(codingPnl_webe.lnk_addCPTcode, "clickable", 10);

					codingPnl_webe.lnk_addCPTcode.click();
				}
				else
				{
					Log4J.logp.info("Element is not found for click");
					Assert.assertTrue(bstatus, "Element " + codingPnl_webe.lnk_addCPTcode + " is not found for Add Code");
					return false;
				}
				Log4J.logp.info("Add CPT link clicked");

				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				Thread.sleep(3000);
				//wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_UseEnethesia));
				Common_Lib.waitForObject(codeBook_webe.webe_UseEnethesia, "visibility", 10);

				Log4J.logp.info("Add CPT codebook frame switched ");

				bstatus = WorkingScreen_Lib.search_Code_ICD10(rowId, getcodeType);
				if (bstatus == false)
				{
					return false;
				}
				Log4J.logp.info("Add CPT code is searched ");

				Thread.sleep(500);
				//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.btn_saveCPT));
				Common_Lib.waitForObject(codingPnl_webe.btn_saveCPT, "clickable", 10);

				Thread.sleep(500);
				//uncomment when you want to save code...
				codingPnl_webe.btn_saveCPT.click();
				balertStatus = Common_Lib.IsCustomAlertPresent();
				if (balertStatus)
				{
					Log4J.logp.info("CPT is already added");
					return true;
				}

				Log4J.logp.info("Add CPT completed in Lib");

			}
			//add E & M code
			if (getcodeType.equalsIgnoreCase("enmicd10"))
			{
				Log4J.logp.info("For codetype--" + getcodeType);
				//scroll for find add link
				bstatus = Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_addEandMcode, 15);
				//bstatus = Common_Lib.scrollToWebE(codingPnl_webe.lnk_addEandMcode, codingPnl_webe.coding_Dragger);
				Thread.sleep(1000);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);

				if (bstatus == true)
				{
					Log4J.logp.info("If add E & M  code link found--" + getcodeType);

					//if element is found then click on it
					//Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 20);
					//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.lnk_addEandMcode));
					Common_Lib.waitForObject(codingPnl_webe.lnk_addEandMcode, "clickable", 10);

					codingPnl_webe.lnk_addEandMcode.click();
				}
				else
				{
					Log4J.logp.info("Element is not found for click");
					Assert.assertTrue(bstatus, "Element " + codingPnl_webe.lnk_addEandMcode + " is not found for Add Code");
					return false;
				}
				Log4J.logp.info("Add E & M link clicked");

				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				Thread.sleep(3000);
				//wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_UseEnethesia));
				Common_Lib.waitForObject(codeBook_webe.webe_UseEnethesia, "visibility", 10);

				Log4J.logp.info("Add E & M codebook frame switched ");

				bstatus = WorkingScreen_Lib.search_Code_ICD10(rowId, getcodeType);
				if (bstatus == false)
				{
					return false;
				}
				Log4J.logp.info("Add E & M code is searched ");

				Thread.sleep(500);
				//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.btn_saveEandM));
				Common_Lib.waitForObject(codingPnl_webe.btn_saveEandM, "clickable", 10);

				Thread.sleep(500);
				//uncomment when you want to save code...
				codingPnl_webe.btn_saveEandM.click();
				balertStatus = Common_Lib.IsCustomAlertPresent();
				if (balertStatus)
				{
					Log4J.logp.info("E & M is already added");
					return true;
				}

				Log4J.logp.info("Add E & M completed in Lib");

			}
			//add HCPSC code in ICD 10
			if (getcodeType.equalsIgnoreCase("hcpcsicd10"))
			{
				Log4J.logp.info("For codetype--" + getcodeType);
				//scroll for find add link
				bstatus = Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_addHCPCScode, 15);
				//bstatus = Common_Lib.scrollToWebE(codingPnl_webe.lnk_addHCPCScode, codingPnl_webe.coding_Dragger);
				Thread.sleep(1000);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);

				if (bstatus == true)
				{
					Log4J.logp.info("If add HCPCS  code link found--" + getcodeType);

					//if element is found then click on it
					//Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 20);
					//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.lnk_addHCPCScode));
					Common_Lib.waitForObject(codingPnl_webe.lnk_addHCPCScode, "clickable", 10);

					codingPnl_webe.lnk_addHCPCScode.click();
				}
				else
				{
					Log4J.logp.info("Element is not found for click");
					Assert.assertTrue(bstatus, "Element " + codingPnl_webe.lnk_addHCPCScode + " is not found for Add Code");
					return false;
				}
				Log4J.logp.info("Add HCPCS link clicked");

				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				//	wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_UseEnethesia));
				Thread.sleep(3000);
				Log4J.logp.info("Add HCPCS codebook frame switched ");

				bstatus = WorkingScreen_Lib.search_Code_ICD10(rowId, getcodeType);
				if (bstatus == false)
				{
					return false;
				}
				Log4J.logp.info("Add HCPCS code is searched ");

				Thread.sleep(500);
				//wait.until(ExpectedConditions.elementToBeClickable(codingPnl_webe.btn_saveHCPCS));
				Common_Lib.waitForObject(codingPnl_webe.btn_saveHCPCS, "clickable", 10);

				Thread.sleep(500);
				//uncomment when you want to save code...
				codingPnl_webe.btn_saveHCPCS.click();
				balertStatus = Common_Lib.IsCustomAlertPresent();
				if (balertStatus)
				{
					Log4J.logp.info("HCPCS is already added");
					return true;
				}

				Log4J.logp.info("Add HCPCS completed in Lib");

			}

			Log4J.logp.info("---------------- Ending - addnewCode_withData_ICD10 ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- Problem found In - addnewCode_withData_ICD10 ----------------");
			e.printStackTrace();
			return false;
		}
		finally
		{
			/*	if (driver != null)
			{
			driver = null;
			}
			if (landingp_webe != null)
			{
			landingp_webe = null;
			}
			if (groupingpnl_webe != null)
			{
			groupingpnl_webe = null;
			}
			if (codingPnl_webe != null)
			{
			codingPnl_webe = null;
			}
			if (codeBook_webe != null)
			{
			codeBook_webe = null;
			}*/
		}

	}

	/**
	 * This method is for search code in ICD10
	 * 
	 * We get data from "td_searchcode"
	 * 
	 * @author skhalasi
	 * @since 09/09/2014
	 * @param rowId
	 * @param codeType
	 * @return
	 */

	public static boolean search_Code_ICD10(String rowId, String codeType)
	{
		String strcode = null;
		String strevidenceFoundIn = null;
		JDBCMySql query = null;
		Map<String, String> rowTestData = null;
		//WebDriverWait wait;
		String getCode = null;
		try
		{
			Log4J.logp.info("----------------  In search_Code_ICD10 -- for rowId   " + rowId + " ----------------");

			query = new JDBCMySql();
			driver = ExecutionSetup.getDriver();
			//	wait = new WebDriverWait(driver, 20);
			codeBook_webe = CodeBook_WebeE.getInstance(driver);
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);

			JDBCMySql searchCode_TestData = new JDBCMySql();

			//to get list of td_searchcode
			rowTestData = searchCode_TestData.getRowbyID("td_searchcode", rowId);

			strcode = rowTestData.get("code");
			strevidenceFoundIn = rowTestData.get("evifoundin");

			if (strcode != null)
			{
				codeBook_webe.txt_searchCode.clear();
				Thread.sleep(1000);
				codeBook_webe.txt_searchCode.sendKeys(strcode);
			}

			Log4J.logp.info("All data inserted for rowId   " + rowId);
			Thread.sleep(1000);
			codeBook_webe.btn_search.click();

			getCode = query.getValuebyColumnName("td_searchcode", rowId, "code");
			Log4J.logp.info("Our inserting code is --" + getCode);

			if (strevidenceFoundIn != null)
			{
				if (codeType.equalsIgnoreCase("admitdiagnosisicd10"))
				{
					//wait.until(ExpectedConditions.elementToBeClickable(codeBook_webe.lnk_searchCode));
					Common_Lib.waitForObject(codeBook_webe.lnk_searchCode, "clickable", 10);

					//to click on searched code
					codeBook_webe.lnk_searchCode.click();

					Log4J.logp.info("Searched code is  added to coding panel for rowId   " + rowId);

					//come back to main working screen
					driver.switchTo().defaultContent();
					//Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 20);

					//to select dropdown for admit diagnosis
					Log4J.logp.info("In coding panel for codetype --" + codeType);
					//Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.btn_saveAdmitDignosis_ICD10, 15);

					Select dropdown = new Select(codingPnl_webe.lst_admit_Diagnosis_ICD10);
					dropdown.selectByVisibleText(strevidenceFoundIn);
				}
				else if (codeType.equalsIgnoreCase("diagnosisicd10"))
				{
					//wait.until(ExpectedConditions.elementToBeClickable(codeBook_webe.lnk_searchCode));
					Common_Lib.waitForObject(codeBook_webe.lnk_searchCode, "clickable", 10);

					//to click on searched code
					codeBook_webe.lnk_searchCode.click();

					Log4J.logp.info("Searched code is  added to coding panel for rowId   " + rowId);

					//come back to main working screen
					driver.switchTo().defaultContent();
					if (!codingPnl_webe.btn_saveDignosis_ICD10.isDisplayed())
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 30);
					}
					Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 7);
					//to select dropdown for diagnosis
					Log4J.logp.info("In coding panel for codetype --" + codeType);
					//Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.btn_saveDignosis_ICD10, 15);

					Select dropdown = new Select(codingPnl_webe.lst_secondDiagnosis_ICD10);
					dropdown.selectByVisibleText(strevidenceFoundIn);

				}
				else if (codeType.equalsIgnoreCase("procedureicd10"))
				{
					codeBook_webe.btn_VerticalResize.click();
					Actions actions = new Actions(driver);
					//actions.clickAndHold(codeBook_webe.btn_VerticalResize).clickAndHold().moveByOffset(0, 40).release().perform();
					actions.clickAndHold(codeBook_webe.btn_VerticalResize).moveByOffset(0, 100).release().perform();
					//wait.until(ExpectedConditions.elementToBeClickable(codeBook_webe.lnk_addCode));
					Common_Lib.waitForObject(codeBook_webe.lnk_addCode, "clickable", 10);

					//to click on searched code
					codeBook_webe.lnk_addCode.click();

					Log4J.logp.info("Searched code is  added to coding panel for rowId   " + rowId);

					//come back to main working screen
					driver.switchTo().defaultContent();
					//Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 20);

					Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 30);

					//to select dropdown for admit HCPSC
					Log4J.logp.info("In coding panel for codetype --" + codeType);
					//Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.btn_saveProcedure_ICD10, 15);

					Select dropdown = new Select(codingPnl_webe.lst_procedure_ICD10);
					dropdown.selectByVisibleText(strevidenceFoundIn);

				}
				else if (codeType.equalsIgnoreCase("cpticd10"))
				{
					//wait.until(ExpectedConditions.elementToBeClickable(codeBook_webe.lnk_searchCode));
					Common_Lib.waitForObject(codeBook_webe.lnk_searchCode, "clickable", 10);

					//to click on searched code
					codeBook_webe.lnk_searchCode.click();

					Log4J.logp.info("Searched code is  added to coding panel for rowId   " + rowId);

					//come back to main working screen
					driver.switchTo().defaultContent();
					if (!codingPnl_webe.btn_saveCPT.isDisplayed())
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 30);
					}
					//Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 30);

					//to select dropdown for cpt
					Log4J.logp.info("In coding panel for codetype --" + codeType);
					//Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.btn_saveCPT, 15);

					Select dropdown = new Select(codingPnl_webe.lst_CPT);
					dropdown.selectByVisibleText(strevidenceFoundIn);

				}
				else if (codeType.equalsIgnoreCase("enmicd10"))
				{
					//wait.until(ExpectedConditions.elementToBeClickable(codeBook_webe.lnk_searchCode));
					Common_Lib.waitForObject(codeBook_webe.lnk_searchCode, "clickable", 10);

					//to click on searched code
					codeBook_webe.lnk_searchCode.click();

					Log4J.logp.info("Searched code is  added to coding panel for rowId   " + rowId);

					//come back to main working screen
					driver.switchTo().defaultContent();
					if (!codingPnl_webe.btn_saveEandM.isDisplayed())
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 30);
					}
					//Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 20);

					//to select dropdown for E & M
					//if we add E & M code please add code started with 99. Eg.99201
					Log4J.logp.info("In coding panel for codetype --" + codeType);
					//Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.btn_saveEandM, 15);

					Select dropdown = new Select(codingPnl_webe.lst_EandM);
					dropdown.selectByVisibleText(strevidenceFoundIn);

				}
				else if (codeType.equalsIgnoreCase("hcpcsicd10"))
				{
					//wait.until(ExpectedConditions.elementToBeClickable(codeBook_webe.lnk_searchCode));
					Common_Lib.waitForObject(codeBook_webe.lnk_searchCode, "clickable", 10);

					//to click on searched code
					codeBook_webe.lnk_searchCode.click();

					Log4J.logp.info("Searched code is  added to coding panel for rowId   " + rowId);

					//come back to main working screen
					driver.switchTo().defaultContent();
					if (!codingPnl_webe.btn_saveHCPCS.isDisplayed())
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 30);
					}
					//Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 20);

					//to select dropdown for admit HCPSC
					Log4J.logp.info("In coding panel for codetype --" + codeType);
					//Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.btn_saveHCPCS, 15);

					Select dropdown = new Select(codingPnl_webe.lst_HCPCS);
					dropdown.selectByVisibleText(strevidenceFoundIn);

				}

				else
				{
					Log4J.logp.error("Dropdown for select page is not found");
					return false;
				}
			}
			Log4J.logp.info("----------------  search_Code_ICD10 completed ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("----------------  In search_Code_ICD10 --searched code is failed for rowId   " + rowId + " ----------------");
			driver.switchTo().defaultContent();
			codeBook_webe.btn_closeCodeBook.click();
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for remove evidence from coding panel
	 *
	 * @author skhalasi
	 * @since 11/09/2014
	 * @param list
	 * 
	 * */

	public static boolean remove_evidence(String evidenceType)
	{

		try
		{
			Log4J.logp.info("---------------- Started  -- In remove_evidence for evidenceType= " + evidenceType + " ----------------");
			driver = ExecutionSetup.getDriver();

			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			Log4J.logp.info("Type of evidence =" + evidenceType);
			//	List<WebElement> ls;
			//int sizeOfList = ls.size();

			//Log4J.logp.info("size of evidence" + sizeOfList);

			//for (int j = 0; j < sizeOfList;)
			//for (WebElement eachElement : ls)
			//	while (!driver.findElements(By.xpath("//span[@class='sec-sys' or @class='sec-manual']")).isEmpty())
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Thread.sleep(2000);
			if (evidenceType.equalsIgnoreCase("all"))
			{
				//	ls = driver.findElements(By.xpath("//span[@class='sec-sys' or @class='sec-manual']"));
				//ls = medicalrecordpnl_webe.allEvidence;
				while (!driver.findElements(By.xpath("//span[@class='sec-sys' or @class='sec-manual']")).isEmpty())
				{
					Thread.sleep(2000);
					//List<WebElement> allEvidenceList = driver.findElements(By.xpath("//span[@class='sec-sys' or @class='sec-manual']"));

					//ls = driver.findElements(By.xpath("//span[@class='sec-sys' or @class='sec-manual']"));
					//	List<WebElement> ls = driver.findElements(By.cssSelector("span[class = 'brd code-status']"));
					/*List<WebElement> ls = allEvidenceList;
					Thread.sleep(2000);
					Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, ls.get(0).findElement(By.cssSelector("span.code-status-drop-down")), 30);
					Thread.sleep(1000);
					ls.get(0).findElement(By.cssSelector("span.code-status-drop-down")).click();
					Thread.sleep(1000);
					Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, ls.get(0).findElement(By.cssSelector("#removEvidence")), 20);
					Thread.sleep(1000);
					ls.get(0).findElement(By.cssSelector("span.code-status-drop-down")).click();
					Thread.sleep(1000);
					WebElement webtemp = ls.get(0).findElement(By.cssSelector("#removEvidence"));
					webtemp.click();
					Thread.sleep(2000);*/
					WebElement webe = driver.findElements(By.xpath("//span[@class='sec-sys' or @class='sec-manual']")).get(0);
					Common_Lib.scroll_Until_FindWebe(medicalrecordpnl_webe.medical_Dragger, webe, 15);
					//Common_Lib.scrollToWebE(webe, medicalrecordpnl_webe.medical_Dragger);
					Thread.sleep(2000);
					Common_Lib.scroll_Page_VerySmall(medicalrecordpnl_webe.medical_Dragger, 4);
					Thread.sleep(2000);
					webe.click();
					//driver.findElements(By.xpath("//span[@class='sec-sys' or @class='sec-manual']")).get(0).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//span[@class='code-status-menu selectedEvidenceBGColor']/..//span[@class='code-status-drop-down']")).click();
					//codingPnl_webe.highlgt_dropD_coding.click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//span[contains(@class,'code-status-menu selectedEvidenceBGColor')]//a[@id='removEvidence']")).click();
					//codingPnl_webe.highlgt_removeEvi_coding.click();
					//	allEvidenceList.remove(0);

				}
				return true;
			}
			else if (evidenceType.equalsIgnoreCase("suggested"))
			{
				while (!driver.findElements(By.xpath("//span[@class='sec-sys']")).isEmpty())
				{
					Thread.sleep(2000);
					WebElement webe = driver.findElements(By.xpath("//span[@class='sec-sys']")).get(0);
					Common_Lib.scroll_Until_FindWebe(medicalrecordpnl_webe.medical_Dragger, webe, 15);
					//Common_Lib.scrollToWebE(webe, medicalrecordpnl_webe.medical_Dragger);
					Thread.sleep(2000);
					Common_Lib.scroll_Page_VerySmall(medicalrecordpnl_webe.medical_Dragger, 4);
					Thread.sleep(2000);
					webe.click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//span[@class='code-status-menu selectedEvidenceBGColor']/..//span[@class='code-status-drop-down']")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//span[contains(@class,'code-status-menu selectedEvidenceBGColor')]//a[@id='removEvidence']")).click();

				}
				return true;
			}
			else if (evidenceType.equalsIgnoreCase("manual"))
			{
				while (!driver.findElements(By.xpath("//span[@class='sec-manual']")).isEmpty())
				{
					Thread.sleep(2000);
					WebElement webe = driver.findElements(By.xpath("//span[@class='sec-manual']")).get(0);
					Common_Lib.scroll_Until_FindWebe(medicalrecordpnl_webe.medical_Dragger, webe, 15);
					//Common_Lib.scrollToWebE(webe, medicalrecordpnl_webe.medical_Dragger);
					Thread.sleep(2000);
					Common_Lib.scroll_Page_VerySmall(medicalrecordpnl_webe.medical_Dragger, 4);
					Thread.sleep(2000);
					webe.click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//span[@class='code-status-menu selectedEvidenceBGColor']/..//span[@class='code-status-drop-down']")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//span[contains(@class,'code-status-menu selectedEvidenceBGColor')]//a[@id='removEvidence']")).click();

				}
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				return true;
			}
			else
			{
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Log4J.logp.error("---------------- Please pass 'all' or 'suggested' or 'manual' for remove evidence ----------------");
				Assert.assertTrue(false, "Please pass 'all' or 'suggested' or 'manual' for remove evidence");
				return false;
			}

			/*
			 * This portion will not work, another approach
			int intCount = 0;
			if (removableEvidence.size() != 0)
			{
			for (int j = 0; j < removableEvidence.size();)
			{
			if (intCount == removableEvidence.size())
			{
			Log4J.logp.info("Count is =" + intCount);
			break;
			}
			else
			{
			Log4J.logp.info("Value of j=" + j);
			Log4J.logp.info("Size of evidence in fro loop=" + removableEvidence.size());
			WebElement webe = removableEvidence.get(j);

			//Common_Lib.scroll_Until_FindWebe(medicalrecordpnl_webe.medical_Dragger, webe, 30);

			//Log4J.logp.info("Removble evidence is --" + webe.getText());
			//webe.click();
			//	Thread.sleep(200);
			//common_webe.lst_evidence1.click();
			//Thread.sleep(200);
			//	Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, common_webe.lnk_removeEvi, 15);
			//Thread.sleep(500);
			//common_webe.lnk_removeEvi.click();
			//Thread.sleep(1000);
			//If you do j++ then it jumps two times..only even or odd jump
			//j = j + 1;
			intCount++;
			}
			}
			}*/
			//Log4J.logp.info("remove_evidence completed");
			//return false;

		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- In remove_evidence --remove code is failed ----------------");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for accept any code from coding panel
	 * 
	 * We get data from "td_searchcode"
	 * 
	 * @author skhalasi
	 * @since 11/09/2014
	 * @param rowId
	 * */

	public static boolean accept_Code(String rowId)
	{
		String getCode = null;
		String strafterClassName = null;
		try
		{
			Log4J.logp.info("---------------- Started  -- In acceptCode for rowId=" + rowId + "----------------");
			driver = ExecutionSetup.getDriver();
			JDBCMySql query = new JDBCMySql();
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);

			//search code
			getCode = query.getValuebyColumnName("td_searchcode", rowId, "code");
			Log4J.logp.info("Our searched code is --" + getCode);

			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
			driver.findElement(By.xpath("//div[text()='" + getCode + "']/ancestor::div[@class='expand']//span[contains(@id,'code-accepted')]")).click();
			Thread.sleep(1000);
			WebElement webe = driver.findElement(By.xpath("//div[text()='" + getCode + "']/ancestor::div[@class='expand']//span[contains(@id,'code-accepted')]"));

			strafterClassName = webe.getAttribute("id");
			Log4J.logp.info("Web element class=" + strafterClassName);
			if (!strafterClassName.contains("enabled"))
			{
				Log4J.logp.error("acceptCode error");
				return false;
			}

			Log4J.logp.info("---------------- acceptCode-- completed ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- In acceptCode --accept code is failed ----------------");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for modify code from coding panel
	 * 
	 * We get data from "td_searchcode"
	 * 
	 * @author skhalasi
	 * @since 11/09/2014
	 * @param rowId
	 * @param modifyRowId
	 * @return
	 */

	public static boolean modify_Code(String rowId, String modifyRowId)
	{
		WebDriverWait wait;
		Map<String, String> rowTestData = null;
		String strcode = null;
		String getCode = null;
		String getModifyCode = null;
		String strafterCodeText = null;

		try
		{
			Log4J.logp.info("---------------- Started  -- In modifyCode for rowId=" + rowId + " ----------------");
			driver = ExecutionSetup.getDriver();
			wait = new WebDriverWait(driver, 20);
			JDBCMySql query = new JDBCMySql();
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			codeBook_webe = CodeBook_WebeE.getInstance(driver);

			//search code
			getCode = query.getValuebyColumnName("td_searchcode", rowId, "code");
			Log4J.logp.info("Our searched code is --" + getCode);
			getModifyCode = query.getValuebyColumnName("td_searchcode", modifyRowId, "code");
			Log4J.logp.info("Our modify code is --" + getModifyCode);
			Thread.sleep(500);
			Common_Lib.codeSearchInCodingPnl(getCode);
			Thread.sleep(500);
			driver.findElement(By.xpath("//div[text()='" + getCode + "']/ancestor::div[contains(@class,'expand')]//span[contains(@id,'code-modified')]")).click();

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
			//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
			//wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
			//Important to open all code book
			Thread.sleep(4000);
			Log4J.logp.info("In modifyCode -- codebook frame switched ");

			rowTestData = query.getRowbyID("td_searchcode", modifyRowId);

			strcode = rowTestData.get("code");

			if (strcode != null)
			{
				codeBook_webe.txt_searchCode.clear();
				Thread.sleep(1000);
				codeBook_webe.txt_searchCode.sendKeys(strcode);
			}

			Log4J.logp.info("In modifyCode -- all data inserted for rowId   " + rowId);

			codeBook_webe.btn_search.click();
			Thread.sleep(2000);
			Common_Lib.waitForObject(codeBook_webe.lnk_searchCode, "clickable", 10);

			codeBook_webe.lnk_searchCode.click();

			//come back to main working screen
			driver.switchTo().defaultContent();
			Log4J.logp.info("In modifyCode -- code is searched ");

			WebElement webe = driver.findElement(By.xpath("//div[text()='" + getModifyCode + "']"));

			strafterCodeText = webe.getText();
			Log4J.logp.info("Web element text=" + strafterCodeText);
			if (!strafterCodeText.equals(getModifyCode))
			{
				Log4J.logp.error("modifyCode error");
				return false;
			}

			Log4J.logp.info("---------------- modifyCode completed ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- In modifyCode --modify code is failed ----------------");
			driver.switchTo().defaultContent();
			codeBook_webe.btn_closeCodeBook.click();
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for modify code from coding panel with search that code and modify it code only
	 * 
	 * @author skhalasi
	 * @since 26/11/2014
	 * @param getCode
	 * @param getModifyCode
	 * @return
	 */

	public static boolean modify_oneCode_with_Second(String codeCaseType, WebElement webeOldCode, WebElement webeNewcode)
	{
		WebDriverWait wait;
		String getCode = null;
		String getModifyCode = null;
		try
		{
			Log4J.logp.info("---------------- Started  -- In modify_oneCode_with_Second ----------------");
			driver = ExecutionSetup.getDriver();
			wait = new WebDriverWait(driver, 20);
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			codeBook_webe = CodeBook_WebeE.getInstance(driver);

			if (Common_Lib.checkElementPresent(webeOldCode))
			{
				getCode = webeOldCode.getText();
				getModifyCode = webeNewcode.getText();
				Thread.sleep(500);
				Log4J.logp.info("The code is =" + getCode);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
				//Common_Lib.codeSearchInCodingPnl(getCode);
				/*Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, webeOldCode, 30);
				Thread.sleep(1000);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 20);*/
				Thread.sleep(1000);
				driver.findElement(By.xpath("//div[text()='" + getCode + "']/ancestor::div[@class='expand']//span[contains(@id,'code-modified')]")).click();

				//	Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, webe, 30);

				Thread.sleep(2000);

				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				Thread.sleep(2000);

				if (getModifyCode != null)
				{
					codeBook_webe.txt_searchCode.clear();
					Thread.sleep(1000);
					codeBook_webe.txt_searchCode.sendKeys(getModifyCode);
					Thread.sleep(2000);
					codeBook_webe.btn_search.click();
					Thread.sleep(1000);
				}
				if (codeCaseType.equalsIgnoreCase("icd10inpatient"))
				{
					codeBook_webe.btn_VerticalResize.click();
					Actions actions = new Actions(driver);
					actions.clickAndHold(codeBook_webe.btn_VerticalResize).moveByOffset(0, 100).release().perform();
					Thread.sleep(2000);
					wait.until(ExpectedConditions.elementToBeClickable(codeBook_webe.lnk_addCode));
					//to click on searched code
					Thread.sleep(2000);
					codeBook_webe.lnk_addCode.click();
					Thread.sleep(2000);
					if (Common_Lib.IsAlertPresent())
					{
						driver.switchTo().defaultContent();
						codeBook_webe.btn_closeCodeBook.click();
						driver.switchTo().defaultContent();
						return true;
					}
				}
				else
				{
					Thread.sleep(1000);
					codeBook_webe.lnk_searchCode.click();
					Thread.sleep(1000);
					if (Common_Lib.IsAlertPresent())
					{
						driver.switchTo().defaultContent();
						codeBook_webe.btn_closeCodeBook.click();
						driver.switchTo().defaultContent();
						return true;
					}
				}
				//come back to main working screen
				driver.switchTo().defaultContent();
			}
			else
			{
				Log4J.logp.info("Not found any code");

			}
			Log4J.logp.info("---------------- modify_oneCode_with_Second completed ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- In modify_oneCode_with_Second --modify code is failed ----------------");
			driver.switchTo().defaultContent();
			codeBook_webe.btn_closeCodeBook.click();
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for modify code directly with any text code
	 * 
	 * @author skhalasi
	 * @since 01/12/2014
	 * @param codeCaseType
	 * @param getCode
	 * @param getModifyCode
	 * @return
	 */

	public static boolean modify_oneCode_with_Second_withText(String codeCaseType, String getCode, String getModifyCode)
	{
		WebDriverWait wait;

		try
		{
			Log4J.logp.info("---------------- Started  -- In modify_oneCode_with_Second_withText ----------------");
			driver = ExecutionSetup.getDriver();
			wait = new WebDriverWait(driver, 20);
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			codeBook_webe = CodeBook_WebeE.getInstance(driver);

			Thread.sleep(500);
			Log4J.logp.info("The code is =" + getCode);

			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[text()='" + getCode + "']/ancestor::div[@class='expand']//span[contains(@id,'code-modified')]")).click();

			Thread.sleep(500);

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
			Thread.sleep(2000);

			if (getModifyCode != null)
			{
				codeBook_webe.txt_searchCode.clear();
				Thread.sleep(1000);
				codeBook_webe.txt_searchCode.sendKeys(getModifyCode);
				Thread.sleep(2000);
				codeBook_webe.btn_search.click();
				Thread.sleep(1000);
			}
			if (codeCaseType.equalsIgnoreCase("icd10inpatient"))
			{
				codeBook_webe.btn_VerticalResize.click();
				Actions actions = new Actions(driver);
				actions.clickAndHold(codeBook_webe.btn_VerticalResize).moveByOffset(0, 100).release().perform();
				wait.until(ExpectedConditions.elementToBeClickable(codeBook_webe.lnk_addCode));
				//to click on searched code
				Thread.sleep(2000);
				codeBook_webe.lnk_addCode.click();

			}
			else
			{
				Thread.sleep(1000);
				codeBook_webe.lnk_searchCode.click();

			}
			//come back to main working screen
			driver.switchTo().defaultContent();

			Log4J.logp.info("---------------- modify_oneCode_with_Second_withText completed ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- In modify_oneCode_with_Second_withText --modify code is failed ----------------");
			driver.switchTo().defaultContent();
			codeBook_webe.btn_closeCodeBook.click();
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This methods is for reject code from coding panel
	 * 
	 * We get data from "td_searchcode"
	 * 
	 * @author skhalasi
	 * @since 11/09/2014
	 * @param rowId
	 * @return
	 */

	public static boolean reject_Code(String rowId)
	{
		String getCode = null;
		String strafterClassName = null;
		try
		{
			Log4J.logp.info("---------------- Started  -- In rejectCode ----------------");
			driver = ExecutionSetup.getDriver();
			JDBCMySql query = new JDBCMySql();
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);

			//search code
			getCode = query.getValuebyColumnName("td_searchcode", rowId, "code");
			Log4J.logp.info("Our searched code is --" + getCode);

			driver.findElement(By.xpath("//div[text()='" + getCode + "']/ancestor::div[@class='expand']//span[contains(@id,'code-rejected')]")).click();
			Thread.sleep(1000);
			WebElement webe = driver.findElement(By.xpath("//div[text()='" + getCode + "']/ancestor::div[@class='expand']//span[contains(@id,'code-rejected')]"));

			strafterClassName = webe.getAttribute("id");
			Log4J.logp.info("Web element class=" + strafterClassName);
			if (!strafterClassName.contains("enabled"))
			{
				Log4J.logp.error("acceptCode error");
				return false;
			}

			Log4J.logp.info("---------------- rejectCode completed ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- In rejectCode --reject code is failed ----------------");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for make any code to principal diagnosis
	 * 
	 * We get data from "td_searchcode"
	 * 
	 * @author skhalasi
	 * @since 11/09/2014
	 * @param rowId
	 * @return
	 */

	public static boolean principal_Code(String rowId)
	{
		String getCode = null;
		String strafterCodeText = null;

		try
		{
			Log4J.logp.info("---------------- Started  -- In principalCode ----------------");
			driver = ExecutionSetup.getDriver();
			JDBCMySql query = new JDBCMySql();
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);

			//search code
			getCode = query.getValuebyColumnName("td_searchcode", rowId, "code");
			Log4J.logp.info("Our searched code is --" + getCode);

			driver.findElement(By.xpath("//div[text()='" + getCode + "']/ancestor::div[@class='expand']//span[contains(@id,'make-it-principal')]")).click();
			Thread.sleep(1000);
			WebElement webe = driver.findElement(By.xpath("//h2[text()='Principal Diagnosis']/..//div[@class='num-container num accepted-code']"));

			strafterCodeText = webe.getText();
			Log4J.logp.info("Web element text=" + strafterCodeText);
			if (!strafterCodeText.equals(getCode))
			{
				Log4J.logp.error("principal code error");
				return false;
			}

			Log4J.logp.info("---------------- principalCode completed ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- In principal_Code --pricipal code is failed ----------------");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for copy evidence with its type (manual or suggested) for secondary diagnosis
	 * 
	 * @author skhalasi
	 * @since 11/09/2014
	 * @param evidenceType
	 * @return
	 */
	public static boolean copy_EvidenceWithType(String evidenceType)
	{
		try
		{
			Log4J.logp.info("---------------- Started  -- In copy_EvidenceWithType for evidenceType=" + evidenceType + "----------------");
			driver = ExecutionSetup.getDriver();
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);

			Log4J.logp.info("The evidence type is =" + evidenceType);
			Thread.sleep(2000);
			if (evidenceType.equalsIgnoreCase("suggested"))
			{
				Log4J.logp.info("If suggested evidence then..");
				//to click on dropdown of evidence
				//driver.findElement(By.xpath("((//h2[@id='secondary']/..//div[@class='expand'])[1]//span[@class='brd code-status'])[1]//span[@class='code-status-drop-down']")).click();
				codingPnl_webe.lst_first_secondary_evi_arrow.click();
				Thread.sleep(2000);
				//to click on copy evidence
				//driver.findElement(By.xpath("((//h2[@id='secondary']/..//div[@class='expand'])[1]//span[@class='brd code-status'])[1]//a[@id='copyEvidence']")).click();
				codingPnl_webe.lnk_copyEviSecondaryFirst.click();
				Thread.sleep(2000);
				//WebElement webe = driver.findElement(By.xpath("(//h2[@id='secondary']/..//span[@id='make-it-principal'])[2]"));
				WebElement webe = codingPnl_webe.lnk_secondMakePrincipal;
				/*Point ps = webe.getLocation();
				 * Robot rb = new Robot();
				rb.mouseMove(ps.getX() + 90, ps.getY());
				rb.delay(2000);
				rb.mouseMove(ps.getX(), ps.getY() + 50);
				rb.delay(2000);
				rb.mousePress(InputEvent.BUTTON1_MASK);*/

				Actions action = new Actions(driver);
				//action.moveToElement(webe, ps.getX(), ps.getY() - 50).click().build().perform();
				action.moveToElement(webe, -3, 25).build().perform();
				Thread.sleep(1000);
				action.click().build().perform();
				//common_webe.div_second_secondDia.click();
				Thread.sleep(1000);
				Log4J.logp.info("copy_EvidenceWithType with suggested evidence completed");
				return true;

			}
			else if (evidenceType.equalsIgnoreCase("manual"))
			{
				Log4J.logp.info("If manual evidence then..");
				//to click on dropdown of evidence
				//driver.findElement(By.xpath("((//h2[@id='secondary']/..//div[@class='expand'])[1]//span[@class='brd code-status manual'])[1]//span[@class='code-status-drop-down']")).click();
				codingPnl_webe.lst_man_firstSecond_evi_arrow.click();
				Thread.sleep(2000);
				//to click on copy evidence
				//driver.findElement(By.xpath("((//h2[@id='secondary']/..//div[@class='expand'])[1]//span[@class='brd code-status manual'])[1]//a[@id='copyEvidence']")).click();
				codingPnl_webe.lnk_copyManEviSecondaryFirst.click();
				Thread.sleep(2000);
				//WebElement webe = driver.findElement(By.xpath("(//h2[@id='secondary']/..//span[@id='make-it-principal'])[2]"));
				WebElement webe = codingPnl_webe.lnk_secondMakePrincipal;

				Actions action = new Actions(driver);
				action.moveToElement(webe, -3, 25).build().perform();
				Thread.sleep(1000);
				action.click().build().perform();
				Thread.sleep(1000);
				//common_webe.div_second_secondDia.click();
				Common_Lib.IsCustomAlertPresent();
				Log4J.logp.info("copy_EvidenceWithType with manual evidence completed");
				return true;

			}
			else
			{
				Log4J.logp.error("---------------- In copy_EvidenceWithType --evidence is not manual or suggested ----------------");
				return false;
			}

		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- In copy_EvidenceWithType --copy code is failed ----------------");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is from add E and M code with its Form in coding panel
	 * 
	 * We get data from "td_enmform"
	 * 
	 * @author skhalasi
	 * @since 12/09/2014
	 * @param rowId
	 * @return
	 */
	public static boolean add_EandM_With_Form(String rowId)
	{
		String strselectFirstBox = null;
		String strselectSecondBox = null;
		String strselectThirdBox = null;
		String strselectForthBox = null;
		String strenmType = null;
		String strtime = null;
		Map<String, String> rowTestData = null;
		try
		{
			Log4J.logp.info("---------------- Started  -- In add_EandM_With_Form for rowId=" + rowId + "----------------");

			driver = ExecutionSetup.getDriver();
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);

			Log4J.logp.info("The rowId is  =" + rowId);

			JDBCMySql addEandM_TestData = new JDBCMySql();

			rowTestData = addEandM_TestData.getRowbyID("td_enmform", rowId);

			strselectFirstBox = rowTestData.get("selectfirst");
			strselectSecondBox = rowTestData.get("selectsecond");
			strselectThirdBox = rowTestData.get("selectthird");
			strselectForthBox = rowTestData.get("selectforth");
			strenmType = rowTestData.get("enmtype");
			strtime = rowTestData.get("time");
			if (strenmType != null)
			{

				//Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_addEandMform, 30);
				Common_Lib.scrollToWebE(codingPnl_webe.lnk_addEandMform, codingPnl_webe.coding_Dragger);
				Thread.sleep(300);
				codingPnl_webe.lnk_addEandMform.click();
				if (strselectFirstBox != null)
				{
					Select dropdown = new Select(codingPnl_webe.lst_firstSelect_EandM);
					dropdown.selectByVisibleText(strselectFirstBox);
				}
				if (strselectSecondBox != null)
				{
					Select dropdown = new Select(codingPnl_webe.lst_secondSelect_EandM);
					dropdown.selectByVisibleText(strselectSecondBox);
				}
				if (strselectThirdBox != null)
				{
					Select dropdown = new Select(codingPnl_webe.lst_thirdSelect_EandM);
					dropdown.selectByVisibleText(strselectThirdBox);
				}
				if (strselectForthBox != null)
				{
					Select dropdown = new Select(codingPnl_webe.lst_forthSelect_EandM);
					dropdown.selectByVisibleText(strselectForthBox);
				}

				if (strenmType.equalsIgnoreCase("time"))
				{
					Log4J.logp.info("If code type is time then..");
					Thread.sleep(1000);
					codingPnl_webe.chk_time_EandM.click();
					if (strtime != null)
					{
						codingPnl_webe.txt_timeBox_EandM.clear();
						codingPnl_webe.txt_timeBox_EandM.sendKeys(strtime);
						codingPnl_webe.txt_timeBox_EandM.sendKeys(Keys.TAB);
					}
					Thread.sleep(1000);
					if (codingPnl_webe.lbl_CTPCode_EandM.getText() == null)
					{
						codingPnl_webe.btn_cancel_EandM.click();
						Log4J.logp.error("add_EandM_With_Form -- can not found any code after tab for time based");
						return false;
					}
					else
					{
						Log4J.logp.info("The code is=" + codingPnl_webe.lbl_CTPCode_EandM.getText());
						codingPnl_webe.btn_done_EandM.click();

						Log4J.logp.info("add_EandM_With_Form completed for time based");
						return true;
					}

				}
				else if (strenmType.equalsIgnoreCase("enmform"))
				{
					Log4J.logp.info("If code type is enmform then..");
					//	Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_addEandMform, 12);
					Common_Lib.scrollToWebE(codingPnl_webe.lnk_addEandMform, codingPnl_webe.coding_Dragger);
					Thread.sleep(500);
					codingPnl_webe.chk_useEandMForm.click();
					Thread.sleep(500);
					//Common_Lib.scroll_Until_FindWebe(codingPnl_webe.enm_dragger, codingPnl_webe.tbl_medicalDicision_EandM, 40);
					Common_Lib.scrollToWebE(codingPnl_webe.tbl_medicalDicision_EandM, codingPnl_webe.coding_Dragger);
					//click on first column
					codingPnl_webe.tbl_ChiefComplaint_EandM.click();

					//click on second column
					codingPnl_webe.tbl_HPI_EandM.click();
					//click on third column
					codingPnl_webe.tbl_PFSH_EandM.click();
					//click on forth column
					codingPnl_webe.tbl_ROS_EandM.click();
					Common_Lib.scroll_Page(codingPnl_webe.enm_dragger, 30);
					//to click on examination 
					codingPnl_webe.tbl_examination_EandM.click();
					//to click on MEDICAL DECISION MAKING
					codingPnl_webe.tbl_medicalDicision_EandM.click();

					//Common_Lib.scroll_Until_FindWebe(codingPnl_webe.enm_dragger, codingPnl_webe.btn_done_EandM, 40);
					Common_Lib.scrollToWebE(codingPnl_webe.btn_done_EandM, codingPnl_webe.coding_Dragger);

					Thread.sleep(500);
					if (codingPnl_webe.lbl_CTPCode_EandM.getText() == null)
					{
						codingPnl_webe.btn_cancel_EandM.click();
						Log4J.logp.error("---------------- add_EandM_With_Form -- can not found any code if form based ----------------");
						return false;
					}
					else
					{
						Log4J.logp.info("The code is=" + codingPnl_webe.lbl_CTPCode_EandM.getText());

						codingPnl_webe.btn_done_EandM.click();

						Log4J.logp.info("---------------- add_EandM_With_Form completed for form based ----------------");
						return true;
					}

				}
				else
				{
					Log4J.logp.error("---------------- In add_EandM_With_Form --E and M form type is not valid ----------------");
					return false;
				}

			}
			else
			{
				Log4J.logp.error("---------------- In add_EandM_With_Form -- \"enmtype\" can not be null.Please enter a enmtype in database before execution ----------------");
				return false;
			}
		}

		catch (Exception e)
		{
			Log4J.logp.error("---------------- In add_EandM_With_Form --copy code is failed ----------------*");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for search evidence and click on its dropdown and select option on it and raise query and discuss with colleague
	 * 
	 * @author skhalasi
	 * @since 01/10/2014
	 * @param downArrow
	 * @param queryLnk
	 * @return
	 */

	public static boolean issue_CodingPnl(String issueType, WebElement downArrow, WebElement queryLnk)
	{

		boolean bstatus = false;
		try
		{
			Log4J.logp.info("---------------- Started  -- In query_Physician_CodingPnl ----------------");
			driver = ExecutionSetup.getDriver();
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);

			Log4J.logp.info("The Issue Type is =" + issueType);
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			if (Common_Lib.checkElementPresent(downArrow))
			{

				if (issueType.equalsIgnoreCase("query"))
				{
					Log4J.logp.info("The Issue Type is query then.... ");

					Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, downArrow, 20);
					//Common_Lib.scrollToWebE(downArrow, codingPnl_webe.coding_Dragger);
					Thread.sleep(300);
					Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 8);
					Thread.sleep(600);
					downArrow.click();

					Thread.sleep(600);
					Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 20);
					//Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 13);
					Thread.sleep(600);
					downArrow.click();
					Thread.sleep(600);
					queryLnk.click();

					bstatus = IssuePanel_Lib.send_QueryToPhysician("sk001");

					if (bstatus)
					{

						Assert.assertTrue(true, "Query Successfully added");
						Log4J.logp.info("query_Physician_CodingPnl completed");
						return true;
					}
					else
					{
						Assert.assertTrue(false, "Query not added properly");
						Log4J.logp.error("query_Physician_CodingPnl completed");
						return false;
					}
				}
				else if (issueType.equalsIgnoreCase("discuss"))
				{
					Log4J.logp.info("The Issue Type is discuss then.... ");
					Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 4);
					downArrow.click();
					Thread.sleep(500);
					queryLnk.click();

					bstatus = IssuePanel_Lib.send_DiscussWithColleague("sk001");

					if (bstatus)
					{

						Assert.assertTrue(true, "Discussion Successfully added");
						Log4J.logp.info("query_Physician_CodingPnl completed");
						return true;
					}
					else
					{
						Assert.assertTrue(false, "Discussion not added properly");
						Log4J.logp.error("query_Physician_CodingPnl completed");
						return false;
					}

				}
				else
				{
					Assert.assertTrue(false, "---------------- Please pass 'query/discuss' parameter for 'Issue Type' ----------------");
					Log4J.logp.error("query_Physician_CodingPnl completed");
					return false;

				}
			}
			else
			{
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				issuepnl_webe.btn_Issues.click();
				Log4J.logp.info("---------------- Not found evidence query_Physician_CodingPnl completed ----------------");
				return true;
			}

			/*Log4J.logp.info("query_Physician_CodingPnl completed");
			return true;*/
		}
		catch (Exception e)
		{
			if (issuepnl_webe.lnk_DiscussWithCol.isDisplayed())
			{
				issuepnl_webe.btn_Issues.click();
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Log4J.logp.error("---------------- In query_Physician_CodingPnl --query_Physician_CodingPnl is failed ----------------");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This methods is for resolve all issues in Issue panel
	 *
	 * @author skhalasi
	 * @since 01/10/2014
	 * @return
	 */

	public static boolean resolve_Issue()
	{
		int icount = 0;
		try
		{
			Log4J.logp.info("---------------- Started  -- In resolve_Issue ----------------");
			driver = ExecutionSetup.getDriver();
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Log4J.logp.info("Size of pending issues is =" + issuepnl_webe.lnk_markasresolved_lst.size());
			icount = issuepnl_webe.lnk_markasresolved_lst.size();
			for (int i = 1; i <= icount; i++)
			{
				Log4J.logp.info("Value of i==" + i);
				Thread.sleep(1000);

				//	driver.findElement(By.xpath("(//a[@class='mark-resolved'])[1]")).click();
				issuepnl_webe.lnk_first_resolved.click();
				Thread.sleep(1000);
				//driver.findElement(By.xpath("not((//a[@class='mark-resolved'])[" + i + "]/ancestor::div[contains(@class,'issue-details')]//a)"));
				//Log4J.logp.info("The flag is==" + flag);
				//	String str = driver.findElement(By.xpath("not((//a[@class='mark-resolved'])[" + i + "]/ancestor::div[contains(@class,'issue-details')]//a)")).getText();
				WebElement webe = driver.findElement(By.xpath("(//div[contains(@class,'issue-details')])[" + i + "]"));
				if (webe.getAttribute("class").equals("issue-details query-physician-ico") || webe.getAttribute("class").equals("issue-details discuss-ico"))
				{
					Log4J.logp.error("Issue is not resolved properly");
					return false;
				}

			}
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			Log4J.logp.info("---------------- resolve_Issue completed ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- In resolve_Issue --issues are not resolved is failed ----------------");
			if (issuepnl_webe.lnk_DiscussWithCol.isDisplayed())
			{
				issuepnl_webe.btn_Issues.click();
			}
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This methods is raise CDS query from coding panel Already open case with CDI role
	 * 
	 * We get data from "td_searchcode"
	 * 
	 * @author skhalasi
	 * @since 27/10/2014
	 * @param downArrow
	 * @param queryLnk
	 * @param queryrowId
	 * @return
	 */

	public static String CDS_QueryCodingPnl(String queryrowId, WebElement downArrow, WebElement queryLnk)
	{
		String strStatus = null;
		WebDriverWait wait;
		Map<String, String> rowTestData = null;
		String getcode = null;
		String getcodeNumber = null;
		String strevidenceName = null;
		try
		{
			Log4J.logp.info("----------------  Started -- In CDS_QueryCodingPnl for query rowId=" + queryrowId + " ----------------");
			driver = ExecutionSetup.getDriver();
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			demographicpnl_webe = DemographicPnl_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);

			Actions action = new Actions(driver);

			wait = new WebDriverWait(driver, 20);
			JDBCMySql query = new JDBCMySql();
			codeBook_webe = CodeBook_WebeE.getInstance(driver);
			if (Common_Lib.checkElementPresent(downArrow))
			{
				//open demographic panel
				demographicpnl_webe.lnk_DemoPnlExpand.click();
				Thread.sleep(600);
				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, downArrow, 20);
				Thread.sleep(500);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 4);
				if (demographicpnl_webe.lbl_AccountType.getText().equalsIgnoreCase("inpatient"))
				{
					Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 4);
					downArrow.click();
					Thread.sleep(1000);
					Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 4);
					Thread.sleep(1000);
					if (!Common_Lib.checkElementPresent(queryLnk))
					{
						Log4J.logp.info("If query link is not present then...");
						WebElement webe = driver.findElement(By.xpath("(//div[@id='icd10-procedure-code']//span[contains(@class,'brd code-status')])[1]//span[@class='code-status-drop-down']/ancestor::div[@class='content float-l']/preceding-sibling::div//div[contains(@class,'num-container num')]"));
						getcodeNumber = webe.getText();
						Log4J.logp.info("Code number is =" + getcodeNumber);
						Thread.sleep(600);
						downArrow.click();
						WebElement proWebe = downArrow.findElement(By.xpath("//ancestor::div[@class='tab-container']//h5[@id='icd10-procedure']"));
						if (proWebe.getText().equalsIgnoreCase("procedures"))
						{
							if (getcodeNumber.length() != 7)
							{
								Log4J.logp.info("If code number length is not seven then...");
								Thread.sleep(2000);
								driver.findElement(By.xpath("//div[text()='" + getcodeNumber + "']/ancestor::div[@class='expand']//span[contains(@id,'code-modified')]")).click();
								wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
								//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
								wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
								Log4J.logp.info("In modifyCode -- codebook frame switched ");
								rowTestData = query.getRowbyID("td_searchcode", "sk011");
								getcode = rowTestData.get("code");
								if (getcode != null)
								{
									codeBook_webe.txt_searchCode.clear();
									Thread.sleep(1000);
									codeBook_webe.txt_searchCode.sendKeys(getcode);
								}
								codeBook_webe.btn_search.click();
								Thread.sleep(5000);
								codeBook_webe.btn_VerticalResize.click();
								Actions actions = new Actions(driver);
								//actions.clickAndHold(codeBook_webe.btn_VerticalResize).clickAndHold().moveByOffset(0, 40).release().perform();
								actions.clickAndHold(codeBook_webe.btn_VerticalResize).moveByOffset(0, 100).release().perform();
								wait.until(ExpectedConditions.elementToBeClickable(codeBook_webe.lnk_addCode));
								//to click on searched code
								codeBook_webe.lnk_addCode.click();
								//come back to main working screen
								driver.switchTo().defaultContent();
								Thread.sleep(3000);
								/*Common_Lib.codeSearchInCodingPnl(codeNumber);
								Thread.sleep(1000);
								Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 30);
								Thread.sleep(1000);*/
								WebElement newDownArrow = driver.findElement(By.xpath("(//div[text()='" + getcode + "']/../following-sibling::div[@class='content float-l']//span[@class='code-status-drop-down'])[1]"));
								Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, newDownArrow, 20);
								Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 9);
								Thread.sleep(1000);
								strevidenceName = newDownArrow.findElement(By.xpath(".//preceding-sibling::span[@class='evidenceLabel']")).getText();
								newDownArrow.click();
								WebElement newQueryLink = driver.findElement(By.xpath("(//div[text()='" + getcode + "']/../following-sibling::div[@class='content float-l']//a[@id='coding_panel_add_query'])[1]"));
								Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 20);
								Thread.sleep(600);
								newDownArrow.click();
								action.moveByOffset(20, 20).build().perform();
								Thread.sleep(600);
								newQueryLink.click();
							}
							else
							{
								Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 5);
								Thread.sleep(500);
								strevidenceName = downArrow.findElement(By.xpath(".//preceding-sibling::span[@class='evidenceLabel']")).getText();
								Thread.sleep(600);
								downArrow.click();
								Thread.sleep(600);
								Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 15);
								Thread.sleep(600);
								downArrow.click();
								action.moveByOffset(20, 20).build().perform();
								Thread.sleep(600);
								queryLnk.click();
							}
						}
						else
						{
							Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 5);
							Thread.sleep(500);
							strevidenceName = downArrow.findElement(By.xpath(".//preceding-sibling::span[@class='evidenceLabel']")).getText();
							Thread.sleep(600);
							downArrow.click();
							Thread.sleep(600);
							Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 15);
							Thread.sleep(600);
							downArrow.click();
							action.moveByOffset(20, 20).build().perform();
							Thread.sleep(600);
							queryLnk.click();
						}
					}
					else
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 4);
						Thread.sleep(500);
						strevidenceName = downArrow.findElement(By.xpath(".//preceding-sibling::span[@class='evidenceLabel']")).getText();
						Thread.sleep(600);
						codingPnl_webe.coding_Dragger.click();

						//downArrow.click();
						//Thread.sleep(1000);
						//Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 12);
						Thread.sleep(600);
						downArrow.click();
						action.moveByOffset(20, 20).build().perform();
						Thread.sleep(1000);
						queryLnk.click();
					}
				}
				else
				{
					Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 20);
					Thread.sleep(500);
					strevidenceName = downArrow.findElement(By.xpath(".//preceding-sibling::span[@class='evidenceLabel']")).getText();
					Thread.sleep(600);
					codingPnl_webe.coding_Dragger.click();

					//downArrow.click();
					//Thread.sleep(600);
					//Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 15);
					Thread.sleep(600);
					downArrow.click();
					action.moveByOffset(20, 20).build().perform();
					Thread.sleep(1000);
					queryLnk.click();
				}
				Thread.sleep(1000);
				strStatus = IssuePanel_Lib.send_QueryToPhysician_CDI_withoutEvidence(queryrowId, strevidenceName);

				demographicpnl_webe.lnk_DemoPnlExpand.click();
				if (strStatus.equals("queryWithRowIdSuccess"))
				{
					Assert.assertTrue(true, "Query Successfully added");
					Log4J.logp.info("----------------  CDS_QueryCodingPnl completed ----------------");
					return "queryAdd";
				}
				else if (strStatus.equals("queryWithoutRowIdSuccess"))
				{
					Assert.assertTrue(true, "Query is not added");
					Log4J.logp.info("----------------  CDS_QueryCodingPnl completed ----------------");
					return "queryNotAddWitoutRowId";
				}
				else if (strStatus.equals("queryWithoutRowIdFail"))
				{
					Assert.assertTrue(false, "Query Successfully added");
					Log4J.logp.info("---------------- CDS_QueryCodingPnl completed ----------------");
					return "queryAddWitoutRowId";
				}
				else if (strStatus.equals("sameCodeNotAdd"))
				{
					Assert.assertTrue(true, "Query Not added");
					Log4J.logp.info("----------------  CDS_QueryCodingPnl completed ----------------");
					return "sameCodeNotAdd";
				}
				else
				{
					Assert.assertTrue(false, "Query not added properly");
					Log4J.logp.error("----------------  CDS_QueryCodingPnl completed ----------------");
					return "queryNotAdd";
				}
				/*}
				else
				{
				Log4J.logp.info("CDS_QueryCodingPnl completed and evidence is not found");
				return true;
				}*/
			}
			else
			{

				if (!groupingpnl_webe.btn_Later.isDisplayed())
				{
					issuepnl_webe.btn_Issues.click();
				}
				Log4J.logp.info("---------------- Not found evidence CDS_QueryCodingPnl completed ----------------");
				return "notFonund";
			}
		}
		catch (Exception e)
		{
			Log4J.logp.error("----------------  In CDS_QueryCodingPnl --raise CDS query is failed ----------------");
			if (issuepnl_webe.lnk_DiscussWithCol.isDisplayed())
			{
				issuepnl_webe.btn_Issues.click();
			}
			e.printStackTrace();
			return "queryNotAdd";
		}
	}

	/**
	 * This method is for add CDS query for any code number(Probable)
	 * 
	 * @author skhalasi
	 * @since 05/12/2014
	 * @param downArrow
	 * @param queryLnk
	 * @param getCodeNum
	 * @return
	 */
	public static boolean CDS_QueryWithCodetext(WebElement downArrow, WebElement queryLnk, String getCodeNum)
	{
		String strcodeNumber = null;
		String strSequence = null;
		String strevidenceName = null;
		try
		{
			Log4J.logp.info("----------------  Started -- In CDS_QueryWithCodetext for query  ----------------");
			driver = ExecutionSetup.getDriver();
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			demographicpnl_webe = DemographicPnl_WebE.getInstance(driver);
			codeBook_webe = CodeBook_WebeE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			Actions action = new Actions(driver);
			Log4J.logp.info("getCodeNum :=" + getCodeNum);
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, downArrow, 30);
			Thread.sleep(1000);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 20);
			Thread.sleep(1000);
			strevidenceName = downArrow.findElement(By.xpath(".//preceding-sibling::span[@class='evidenceLabel']")).getText();
			Thread.sleep(600);
			codingPnl_webe.coding_Dragger.click();

			Thread.sleep(600);
			downArrow.click();
			action.moveByOffset(20, 20).build().perform();
			Thread.sleep(1000);
			queryLnk.click();

			WebElement codeNumber_webe = driver.findElement(By.xpath("//div[@id='right-pan']//span[contains(text(),'" + strevidenceName + "')]/ancestor::div[@class='row-1']//div[contains(@class,'num-container num')]"));
			strcodeNumber = codeNumber_webe.getText();
			Log4J.logp.info("Code Number is =" + strcodeNumber);

			Thread.sleep(1000);
			driver.findElement(By.id("queryPhysicianAssociatedCode_chosen")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[@id='queryPhysicianAssociatedCode_chosen']//li[contains(text(),'" + strcodeNumber + "')]")).click();

			Thread.sleep(1000);
			issuepnl_webe.txt_probableCode.clear();
			Thread.sleep(1000);
			//char[] charArray = probable_code.toCharArray();

			for (int j = 0; j < getCodeNum.length(); j++)
			{
				char s1 = getCodeNum.charAt(j);
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
				Log4J.logp.info("Duplicate Probable code can not add from another code");
				Assert.assertTrue(true, "Duplicate Probable code can not add from another code");
			}
			else
			{
				Log4J.logp.error("Duplicate Probable code can add from anothre code");
				Assert.assertTrue(false, "Duplicate Probable code can add from anothre code");
			}
			Thread.sleep(1000);
			Log4J.logp.info("----------------  CDS_QueryWithCodetext completed ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("----------------  In CDS_QueryWithCodetext --raise CDS query with text is failed ----------------");
			if (issuepnl_webe.lnk_DiscussWithCol.isDisplayed())
			{
				issuepnl_webe.btn_Issues.click();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is for modify simple and probable code in CDI USer after query only
	 * 
	 * We get data from "td_modifyassopro_code"
	 * 
	 * codeType= Ad Dx, Pr Dx , CPT etc
	 * 
	 * subCodeType = probable and simple We get data from "td_modifyassopro_code"
	 * 
	 * @author skhalasi
	 * @since 17/11/2014
	 * @param codeType
	 * @param subCodeType
	 * @return
	 */
	public static boolean modify_CDS_Code(String rowId)
	{
		WebDriverWait wait;
		Map<String, String> rowTestData = null;
		String strCode = null;
		String strCodeType = null;
		String strSubCodeType = null;
		try
		{
			Log4J.logp.info("---------------- Started  -- In modify_CDS_Code ----------------");
			driver = ExecutionSetup.getDriver();
			wait = new WebDriverWait(driver, 20);
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			codeBook_webe = CodeBook_WebeE.getInstance(driver);

			JDBCMySql modifyCode_Data = new JDBCMySql();

			rowTestData = modifyCode_Data.getRowbyID("td_modifyassopro_code", rowId);

			strCode = rowTestData.get("code");
			strCodeType = rowTestData.get("codetype");
			strSubCodeType = rowTestData.get("subcodetype");
			if (strCode != null)
			{
				Thread.sleep(2000);
				if (strSubCodeType.equalsIgnoreCase("probable"))
				{
					Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 25);
					Thread.sleep(500);
					if (strCodeType.equalsIgnoreCase("admiticd9"))
					{
						// for both
						//driver.findElement(By.xpath("//div[contains(@id,'admitting-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-modified-disabled']")).click();
						codingPnl_webe.lnk_modifyProbDesFirstAdmit.click();
						//Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.lnk_searchCode.click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();

					}
					else if (strCodeType.equalsIgnoreCase("principalicd9"))
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
						//for both
						//driver.findElement(By.xpath("//div[contains(@id,'primary-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-modified-disabled']")).click();
						codingPnl_webe.lnk_modifyProbDesFirstPrincipal.click();
						//Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.lnk_searchCode.click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();
					}
					else if (strCodeType.equalsIgnoreCase("diagnosisicd9"))
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
						// for both
						//driver.findElement(By.xpath("//div[contains(@id,'secondary-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-modified-disabled']")).click();
						codingPnl_webe.lnk_modifyProbDesFirstSecondary.click();
						//Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.lnk_searchCode.click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();
					}
					else if (strCodeType.equalsIgnoreCase("procedureicd9"))
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
						// for both
						//for CPT also
						//driver.findElement(By.xpath("//div[contains(@id,'procedure-code')]//div[@class='probable-option expand']//span[@id='code-modified-disabled']")).click();
						codingPnl_webe.lnk_modifyProbDesFirstProcedure.click();
						//Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.lnk_searchCode.click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();

					}
					else if (strCodeType.equalsIgnoreCase("admiticd10"))
					{
						// for both
						//driver.findElement(By.xpath("//div[contains(@id,'admitting-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-modified-disabled']")).click();
						codingPnl_webe.lnk_modifyProbDesFirstAdmit.click();
						//Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.lnk_searchCode.click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();

					}
					else if (strCodeType.equalsIgnoreCase("principalicd10"))
					{
						//for both
						//driver.findElement(By.xpath("//div[contains(@id,'primary-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-modified-disabled']")).click();
						codingPnl_webe.lnk_modifyProbDesFirstPrincipal.click();
						//Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.lnk_searchCode.click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();
					}
					else if (strCodeType.equalsIgnoreCase("diagnosisicd10"))
					{
						// for both
						//driver.findElement(By.xpath("//div[contains(@id,'secondary-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-modified-disabled']")).click();
						codingPnl_webe.lnk_modifyProbDesFirstSecondary.click();
						//Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.lnk_searchCode.click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();
					}

					else if (strCodeType.equalsIgnoreCase("procedureicd10"))
					{
						//just for ICD10
						//driver.findElement(By.xpath("//div[contains(@id,'procedure-code')]//div[@class='probable-option expand']//span[@id='code-modified-disabled']")).click();
						codingPnl_webe.lnk_modifyProbDesFirstProcedure.click();
						//Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.btn_VerticalResize.click();
						Actions actions = new Actions(driver);
						actions.clickAndHold(codeBook_webe.btn_VerticalResize).moveByOffset(0, 100).release().perform();
						Thread.sleep(3000);
						codeBook_webe.lnk_addCode.click();
						driver.switchTo().defaultContent();
					}
					else if (strCodeType.equalsIgnoreCase("cpt"))
					{
						// for both
						//driver.findElement(By.xpath("//div[contains(@id,'procedure-code')]//div[@class='probable-option expand']//span[@id='code-modified-disabled']")).click();
						codingPnl_webe.lnk_modifyProbDesFirstProcedure.click();
						//Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_UseEnethesia));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.lnk_searchCode.click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();

					}
					else
					{
						Log4J.logp.error("Code Type is not valid =" + strCodeType);
						Assert.assertTrue(false, "Code Type is not valid =" + strCodeType);
						return false;
					}

				}
				else if (strSubCodeType.equalsIgnoreCase("simple"))
				{
					Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 30);
					Thread.sleep(500);
					if (strCodeType.equalsIgnoreCase("admiticd9"))
					{
						// for both
						//	driver.findElement(By.xpath("(//div[contains(@id,'admitting-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
						codingPnl_webe.lnk_modifyDesFirstAdmit.click();
						//Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.lnk_searchCode.click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();

					}
					else if (strCodeType.equalsIgnoreCase("principalicd9"))
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
						//for both
						//driver.findElement(By.xpath("(//div[contains(@id,'primary-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
						codingPnl_webe.lnk_modifyDesFirstPrincipal.click();
						//Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.lnk_searchCode.click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();
					}
					else if (strCodeType.equalsIgnoreCase("diagnosisicd9"))
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
						Thread.sleep(1000);
						// for both
						//driver.findElement(By.xpath("(//div[contains(@id,'secondary-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
						codingPnl_webe.lnk_modifyDesFirstSecondary.click();
						//Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.lnk_searchCode.click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();
					}
					else if (strCodeType.equalsIgnoreCase("procedureicd9"))
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
						// for both
						//driver.findElement(By.xpath("(//div[contains(@id,'procedure-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
						codingPnl_webe.lnk_modifyDesFirstProcedure.click();
						//Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.lnk_searchCode.click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();

					}

					else if (strCodeType.equalsIgnoreCase("admiticd10"))
					{
						// for both
						//driver.findElement(By.xpath("(//div[contains(@id,'admitting-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
						codingPnl_webe.lnk_modifyDesFirstAdmit.click();
						//	Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.lnk_searchCode.click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();

					}
					else if (strCodeType.equalsIgnoreCase("principalicd10"))
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
						//for both
						//	driver.findElement(By.xpath("(//div[contains(@id,'primary-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
						codingPnl_webe.lnk_modifyDesFirstPrincipal.click();
						//Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.lnk_searchCode.click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();
					}
					else if (strCodeType.equalsIgnoreCase("diagnosisicd10"))
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
						// for both
						//driver.findElement(By.xpath("(//div[contains(@id,'secondary-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
						codingPnl_webe.lnk_modifyDesFirstSecondary.click();
						//Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.lnk_searchCode.click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();
					}

					else if (strCodeType.equalsIgnoreCase("procedureicd10"))
					{
						Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
						//just for ICD10
						//driver.findElement(By.xpath("(//div[contains(@id,'procedure-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
						codingPnl_webe.lnk_modifyDesFirstProcedure.click();
						//Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.btn_VerticalResize.click();
						Actions actions = new Actions(driver);
						actions.clickAndHold(codeBook_webe.btn_VerticalResize).moveByOffset(0, 100).release().perform();
						Thread.sleep(3000);
						codeBook_webe.lnk_addCode.click();
						driver.switchTo().defaultContent();
					}
					else if (strCodeType.equalsIgnoreCase("cpt"))
					{
						// for both
						//driver.findElement(By.xpath("(//div[contains(@id,'procedure-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
						codingPnl_webe.lnk_modifyDesFirstProcedure.click();
						//Thread.sleep(5000);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
						Thread.sleep(3000);
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_UseEnethesia));
						Thread.sleep(3000);
						codeBook_webe.txt_searchCode.clear();
						Thread.sleep(1000);
						codeBook_webe.txt_searchCode.sendKeys(strCode);
						Thread.sleep(1000);
						codeBook_webe.btn_search.click();
						Thread.sleep(4000);
						codeBook_webe.lnk_searchCode.click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();

					}
					else
					{
						Log4J.logp.error("Code Type is not valid =" + strCodeType);
						Assert.assertTrue(false, "Code Type is not valid =" + strCodeType);
						return false;
					}

				}
				else
				{
					Log4J.logp.error("Sub Code Type is not valid =" + strSubCodeType);
					Assert.assertTrue(false, "Sub Code Type is not valid =" + strSubCodeType);
					return false;
				}
				Log4J.logp.info("---------------- modify_CDS_Code completed ----------------");
				return true;
			}
			else
			{
				Log4J.logp.error("Code number can not be null");
				return false;
			}
		}

		catch (Exception e)
		{
			Log4J.logp.error("---------------- In modify_CDS_Code --Code is not modify for CDS user ----------------");
			driver.switchTo().defaultContent();
			codeBook_webe.btn_closeCodeBook.click();
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for modify code in Coder User
	 * 
	 * codeType= Ad Dx, Pr Dx , CPT etc
	 * 
	 * @author skhalasi
	 * @since 26/11/2014
	 * @param codeType
	 * @param subCodeType
	 * @return
	 */

	public static boolean modify_AllType_Code(String codeType)
	{
		WebDriverWait wait;
		try
		{
			Log4J.logp.info("---------------- Started  -- In modify_AllType_Code ----------------");
			driver = ExecutionSetup.getDriver();
			wait = new WebDriverWait(driver, 20);
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			codeBook_webe = CodeBook_WebeE.getInstance(driver);

			Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 30);
			Thread.sleep(500);
			if (codeType.equalsIgnoreCase("admiticd9"))
			{
				// for both
				//	driver.findElement(By.xpath("(//div[contains(@id,'admitting-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
				codingPnl_webe.lnk_modifyDesFirstAdmit.click();
				//Thread.sleep(5000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
				Thread.sleep(3000);
				wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
				Thread.sleep(3000);
				codeBook_webe.txt_searchCode.clear();
				Thread.sleep(1000);
				codeBook_webe.txt_searchCode.sendKeys("701.1");
				Thread.sleep(1000);
				codeBook_webe.btn_search.click();
				Thread.sleep(4000);
				codeBook_webe.lnk_searchCode.click();
				Thread.sleep(2000);
				driver.switchTo().defaultContent();

			}
			else if (codeType.equalsIgnoreCase("principalicd9"))
			{
				//for both
				//driver.findElement(By.xpath("(//div[contains(@id,'primary-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
				codingPnl_webe.lnk_modifyDesFirstPrincipal.click();
				//Thread.sleep(5000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
				Thread.sleep(3000);
				wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
				Thread.sleep(3000);
				codeBook_webe.txt_searchCode.clear();
				Thread.sleep(1000);
				codeBook_webe.txt_searchCode.sendKeys("574.61");
				Thread.sleep(1000);
				codeBook_webe.btn_search.click();
				Thread.sleep(4000);
				codeBook_webe.lnk_searchCode.click();
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
			}
			else if (codeType.equalsIgnoreCase("diagnosisicd9"))
			{
				// for both
				driver.findElement(By.xpath("(//div[contains(@id,'secondary-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
				codingPnl_webe.lnk_modifyDesFirstSecondary.click();
				//Thread.sleep(5000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
				Thread.sleep(3000);
				wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
				Thread.sleep(3000);
				codeBook_webe.txt_searchCode.clear();
				Thread.sleep(1000);
				codeBook_webe.txt_searchCode.sendKeys("341.20");
				Thread.sleep(1000);
				codeBook_webe.btn_search.click();
				Thread.sleep(4000);
				codeBook_webe.lnk_searchCode.click();
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
			}
			else if (codeType.equalsIgnoreCase("procedureicd9"))
			{
				// for both
				driver.findElement(By.xpath("(//div[contains(@id,'procedure-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
				//Thread.sleep(5000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
				Thread.sleep(3000);
				wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
				Thread.sleep(3000);
				codeBook_webe.txt_searchCode.clear();
				Thread.sleep(1000);
				codeBook_webe.txt_searchCode.sendKeys("45.29");
				Thread.sleep(1000);
				codeBook_webe.btn_search.click();
				Thread.sleep(4000);
				codeBook_webe.lnk_searchCode.click();
				Thread.sleep(2000);
				driver.switchTo().defaultContent();

			}

			else if (codeType.equalsIgnoreCase("admiticd10"))
			{
				// for both
				//driver.findElement(By.xpath("(//div[contains(@id,'admitting-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
				codingPnl_webe.lnk_modifyDesFirstAdmit.click();
				//	Thread.sleep(5000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
				Thread.sleep(3000);
				wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
				Thread.sleep(3000);
				codeBook_webe.txt_searchCode.clear();
				Thread.sleep(1000);
				codeBook_webe.txt_searchCode.sendKeys("R53.81");
				Thread.sleep(1000);
				codeBook_webe.btn_search.click();
				Thread.sleep(4000);
				codeBook_webe.lnk_searchCode.click();
				Thread.sleep(2000);
				driver.switchTo().defaultContent();

			}
			else if (codeType.equalsIgnoreCase("principalicd10"))
			{
				//for both
				//driver.findElement(By.xpath("(//div[contains(@id,'primary-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
				codingPnl_webe.lnk_modifyDesFirstPrincipal.click();
				//Thread.sleep(5000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
				Thread.sleep(3000);
				wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
				Thread.sleep(3000);
				codeBook_webe.txt_searchCode.clear();
				Thread.sleep(1000);
				codeBook_webe.txt_searchCode.sendKeys("K80.67");
				Thread.sleep(1000);
				codeBook_webe.btn_search.click();
				Thread.sleep(4000);
				codeBook_webe.lnk_searchCode.click();
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
			}
			else if (codeType.equalsIgnoreCase("diagnosisicd10"))
			{
				// for both
				//	driver.findElement(By.xpath("(//div[contains(@id,'secondary-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
				codingPnl_webe.lnk_modifyDesFirstSecondary.click();
				//Thread.sleep(5000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
				Thread.sleep(3000);
				wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
				Thread.sleep(3000);
				codeBook_webe.txt_searchCode.clear();
				Thread.sleep(1000);
				codeBook_webe.txt_searchCode.sendKeys("K57.93");
				Thread.sleep(1000);
				codeBook_webe.btn_search.click();
				Thread.sleep(4000);
				codeBook_webe.lnk_searchCode.click();
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
			}

			else if (codeType.equalsIgnoreCase("procedureicd10"))
			{
				//just for ICD10
				//driver.findElement(By.xpath("(//div[contains(@id,'procedure-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
				codingPnl_webe.lnk_modifyDesFirstProcedure.click();
				//Thread.sleep(5000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
				Thread.sleep(3000);
				wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_FindAll));
				Thread.sleep(3000);
				codeBook_webe.txt_searchCode.clear();
				Thread.sleep(1000);
				codeBook_webe.txt_searchCode.sendKeys("BF101ZZ");
				Thread.sleep(1000);
				codeBook_webe.btn_search.click();
				Thread.sleep(4000);
				codeBook_webe.btn_VerticalResize.click();
				Actions actions = new Actions(driver);
				actions.clickAndHold(codeBook_webe.btn_VerticalResize).moveByOffset(0, 100).release().perform();
				Thread.sleep(3000);
				codeBook_webe.lnk_addCode.click();
				driver.switchTo().defaultContent();
			}
			else if (codeType.equalsIgnoreCase("cpt"))
			{
				// for both
				driver.findElement(By.xpath("(//div[contains(@id,'procedure-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")).click();
				//Thread.sleep(5000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
				//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='k-list k-reset']>li")));
				Thread.sleep(3000);
				wait.until(ExpectedConditions.visibilityOf(codeBook_webe.webe_UseEnethesia));
				Thread.sleep(3000);
				codeBook_webe.txt_searchCode.clear();
				Thread.sleep(1000);
				codeBook_webe.txt_searchCode.sendKeys("33406");
				Thread.sleep(1000);
				codeBook_webe.btn_search.click();
				Thread.sleep(4000);
				codeBook_webe.lnk_searchCode.click();
				Thread.sleep(2000);
				driver.switchTo().defaultContent();

			}
			else
			{
				Log4J.logp.error("Code Type is not valid =" + codeType);
				Assert.assertTrue(false, "Code Type is not valid =" + codeType);
				return false;
			}

			Log4J.logp.info("---------------- modify_AllType_Code completed ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- In modify_AllType_Code --Code is not modify for coder user ----------------");
			driver.switchTo().defaultContent();
			codeBook_webe.btn_closeCodeBook.click();
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for submit case.For this method we have to enter entry in following tables: td_searchcode,td_searchcase,td_submit_case
	 * 
	 * We get data from "td_submit_case" , "td_searchcase" , "td_searchcode"
	 * 
	 * @author skhalasi
	 * @since 10/10/2014
	 * @param rowId
	 * @return
	 */

	public static boolean	warningStatus	= false;

	public static boolean submit_case(String rowId)
	{
		boolean bcodeSearchStatus = false;
		Map<String, String> rowTestData = null;
		String strcaseNumber = null;
		String struserRole = null;
		String strcaseSubmitType = null;
		String strcodingType = null;
		String strserviceLine = null;
		String getcode = null;
		String strcode1 = null;
		String strcode2 = null;

		try
		{
			Log4J.logp.info("---------------- Started  -- In submit_case ----------------");
			driver = ExecutionSetup.getDriver();
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			JDBCMySql query = new JDBCMySql();
			//String code = query.getValuebyColumnName("td_searchcode", "sk020", "code");

			rowTestData = query.getRowbyID("td_submit_case", rowId);

			strcaseNumber = rowTestData.get("casenumber");
			strserviceLine = rowTestData.get("serviceline");
			struserRole = rowTestData.get("userrole");
			strcodingType = rowTestData.get("codingtype");
			strcaseSubmitType = rowTestData.get("casesubmittype");
			//open case
			Common_Lib.openCase(strcaseNumber);

			if (strserviceLine.equalsIgnoreCase("inpatient"))
			{
				Log4J.logp.info("If Service line is Inpatient then...");

				if (strcodingType.equalsIgnoreCase("icd9"))
				{
					Log4J.logp.info("If codeType is ICD9 then...");
					getcode = query.getValuebyColumnName("td_searchcode", "sk020", "code");
					Log4J.logp.info("The code is ==" + getcode);

					//search particular code
					bcodeSearchStatus = Common_Lib.codeSearchInCodingPnl(getcode);
					if (!bcodeSearchStatus)
					{
						Log4J.logp.info("Code is not added");
						//add code
						WorkingScreen_Lib.addnewCode_withData_ICD9("sk020");

						/*WebElement webe = driver.findElement(By.xpath("//div[text()='" + code + "']"));

						Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, webe, 30);
						Thread.sleep(1000);
						driver.findElement(By.xpath("//div[text()='" + code + "']/ancestor::div[@class='expand']//span[contains(@id,'code-accepted')]")).click();*/
					}
					List<WebElement> webe = driver.findElements(By.xpath("//div[text()='" + getcode + "']/ancestor::div[@class='expand']//span[contains(@id,'make-it-principal')]"));
					if (webe.size() == 0)
					{
						//accept code
						WorkingScreen_Lib.accept_Code("sk020");
					}
					else
					{
						//make code principal 
						WorkingScreen_Lib.principal_Code("sk020");

					}
					//refresh grouping value
					groupingpnl_webe.btn_Done.click();
					Thread.sleep(1000);

					if (!groupingpnl_webe.btn_Done.getText().equals("DONE"))
					{
						Log4J.logp.error("Not found grouping value");
						Assert.assertTrue(false, "Not found grouping value");
						return false;
					}

					//click on Done value
					groupingpnl_webe.btn_Done.click();

					Thread.sleep(500);
					if (groupingpnl_webe.webe_WarningIssueSubmit.isDisplayed())
					{
						warningStatus = true;
					}

					if (struserRole.equalsIgnoreCase("coder") && strcaseSubmitType.equalsIgnoreCase("completed"))
					{
						Log4J.logp.info("If role is coder and coder want to complete case then...");
						// click here to case completed
						groupingpnl_webe.chk_routeForReview.click();
						//click here to go to landing page
						groupingpnl_webe.chk_takeMeQueue.click();

						groupingpnl_webe.btn_submitAfterDone.click();
					}
					else if (struserRole.equalsIgnoreCase("coder") && strcaseSubmitType.equalsIgnoreCase("billed"))
					{
						Log4J.logp.info("If role is coder and coder want to billed case then...");
						//click here to go to landing page
						groupingpnl_webe.chk_takeMeQueue.click();

						groupingpnl_webe.btn_submitAfterDone.click();

					}
					else if (struserRole.equalsIgnoreCase("cdi") || struserRole.equalsIgnoreCase("reviewer"))
					{
						Log4J.logp.info("If role is CDI and Reviewer then...");
						//click here to go to landing page
						groupingpnl_webe.chk_takeMeQueue.click();

						groupingpnl_webe.btn_submitAfterDone.click();

					}
					else
					{
						Log4J.logp.error("Case submit type is =" + strcaseSubmitType + " or user type is =" + struserRole + " is not valid");
						Assert.assertTrue(false, "Case submit type is =" + strcaseSubmitType + " or user type is =" + struserRole + " is not valid");
						return false;
					}
				}
				else if (strcodingType.equalsIgnoreCase("icd10"))
				{
					Log4J.logp.info("If codeType is ICD10 then...");
					//to move on ICD 10
					codingPnl_webe.lst_codeType.click();
					Thread.sleep(500);
					codingPnl_webe.lnk_ICD10_Menu.click();
					Thread.sleep(500);

					getcode = query.getValuebyColumnName("td_searchcode", "sk021", "code");
					Log4J.logp.info("The code is ==" + getcode);

					//search particular code
					bcodeSearchStatus = Common_Lib.codeSearchInCodingPnl(getcode);
					if (!bcodeSearchStatus)
					{
						Log4J.logp.info("Code is not added");
						//add code
						WorkingScreen_Lib.addnewCode_withData_ICD10("sk021");

						/*WebElement webe = driver.findElement(By.xpath("//div[text()='" + code + "']"));

						Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, webe, 30);
						Thread.sleep(1000);
						driver.findElement(By.xpath("//div[text()='" + code + "']/ancestor::div[@class='expand']//span[contains(@id,'code-accepted')]")).click();*/
					}

					List<WebElement> webe = driver.findElements(By.xpath("//div[text()='" + getcode + "']/ancestor::div[@class='expand']//span[contains(@id,'make-it-principal')]"));
					if (webe.size() == 0)
					{
						//accept code
						WorkingScreen_Lib.accept_Code("sk021");
					}
					else
					{
						//make code principal 
						WorkingScreen_Lib.principal_Code("sk021");

					}

					//select grouper
					groupingpnl_webe.lnk_CMSVer.click();

					//select grouper
					Select dropdown = new Select(groupingpnl_webe.lst_grouperSelect);
					dropdown.selectByVisibleText("CMS ver 31.0");
					Thread.sleep(500);
					//click on submit
					groupingpnl_webe.btn_submitGrouper.click();
					//refresh grouping value
					//	groupingpnl_webe.btn_Done.click();
					Thread.sleep(1000);

					if (!groupingpnl_webe.btn_Done.getText().equals("DONE"))
					{
						Log4J.logp.error("Not found grouping value");
						Assert.assertTrue(false, "Not found grouping value");
						return false;
					}
					Thread.sleep(1000);
					//click on Done value
					groupingpnl_webe.btn_Done.click();

					Thread.sleep(500);
					if (groupingpnl_webe.webe_WarningIssueSubmit.isDisplayed())
					{
						warningStatus = true;
					}

					if (struserRole.equalsIgnoreCase("coder") && strcaseSubmitType.equalsIgnoreCase("completed"))
					{
						Log4J.logp.info("If role is coder and coder want to complete case then...");
						// click here to case completed
						groupingpnl_webe.chk_routeForReview.click();
						//click here to go to landing page
						groupingpnl_webe.chk_takeMeQueue.click();

						groupingpnl_webe.btn_submitAfterDone.click();
					}
					else if (struserRole.equalsIgnoreCase("coder") && strcaseSubmitType.equalsIgnoreCase("billed"))
					{
						Log4J.logp.info("If role is coder and coder want to billed case then...");
						//click here to go to landing page
						groupingpnl_webe.chk_takeMeQueue.click();

						groupingpnl_webe.btn_submitAfterDone.click();

					}
					else if (struserRole.equalsIgnoreCase("cdi") && struserRole.equalsIgnoreCase("reviewer"))
					{
						Log4J.logp.info("If role is CDI and Reviewer then...");
						//click here to go to landing page
						groupingpnl_webe.chk_takeMeQueue.click();

						groupingpnl_webe.btn_submitAfterDone.click();

					}
					else
					{
						Log4J.logp.error("Case submit type is =" + strcaseSubmitType + " or user type is =" + struserRole + " is not valid");
						Assert.assertTrue(false, "Case submit type is =" + strcaseSubmitType + " or user type is =" + struserRole + " is not valid");
						return false;
					}

				}
				else
				{
					Log4J.logp.error("Code Type is not valid =" + strcodingType);
					Assert.assertTrue(false, "Code Type is not valid =" + strcodingType);
					return false;
				}
			}
			else if (strserviceLine.equalsIgnoreCase("outpatient") || strserviceLine.equalsIgnoreCase("med") || strserviceLine.equalsIgnoreCase("pul") || strserviceLine.equalsIgnoreCase("sur") || strserviceLine.equalsIgnoreCase("car") || strserviceLine.equalsIgnoreCase("uro"))
			{
				Log4J.logp.info("If Service line is outpatient then...");
				if (strcodingType.equalsIgnoreCase("icd9"))
				{
					Log4J.logp.info("If codeType is ICD9 then...");
					strcode1 = query.getValuebyColumnName("td_searchcode", "sk023", "code");
					Log4J.logp.info("The first code is ==" + strcode1);

					//search particular code
					bcodeSearchStatus = Common_Lib.codeSearchInCodingPnl(strcode1);
					if (!bcodeSearchStatus)
					{
						Log4J.logp.info("First Code is not added");
						//add code
						WorkingScreen_Lib.addnewCode_withData_ICD9("sk023");
						Thread.sleep(1000);

						/*WebElement webe = driver.findElement(By.xpath("//div[text()='" + code + "']"));

						Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, webe, 30);
						Thread.sleep(1000);
						driver.findElement(By.xpath("//div[text()='" + code + "']/ancestor::div[@class='expand']//span[contains(@id,'code-accepted')]")).click();*/
					}
					List<WebElement> webe = driver.findElements(By.xpath("//div[text()='" + strcode1 + "']/ancestor::div[@class='expand']//span[contains(@id,'make-it-principal')]"));
					if (webe.size() == 0)
					{
						//accept code
						WorkingScreen_Lib.accept_Code("sk023");
					}
					else
					{
						//make code principal 
						WorkingScreen_Lib.principal_Code("sk023");

					}
					Thread.sleep(3000);

					strcode2 = query.getValuebyColumnName("td_searchcode", "sk022", "code");
					Log4J.logp.info("The second code is ==" + strcode2);
					Thread.sleep(1000);
					//search particular code
					bcodeSearchStatus = Common_Lib.codeSearchInCodingPnl(strcode2);
					if (!bcodeSearchStatus)
					{
						Log4J.logp.info("Second Code is not added");
						//add code
						WorkingScreen_Lib.addnewCode_withData_ICD9("sk022");
						Thread.sleep(1000);

						/*WebElement webe = driver.findElement(By.xpath("//div[text()='" + code + "']"));

						Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, webe, 30);
						Thread.sleep(1000);
						driver.findElement(By.xpath("//div[text()='" + code + "']/ancestor::div[@class='expand']//span[contains(@id,'code-accepted')]")).click();*/
					}

					//Accept code
					WorkingScreen_Lib.accept_Code("sk022");

					//refresh grouping value
					groupingpnl_webe.btn_Done.click();
					Thread.sleep(1000);

					if (!groupingpnl_webe.btn_Done.getText().equals("DONE"))
					{
						Log4J.logp.error("Not found grouping value");
						Assert.assertTrue(false, "Not found grouping value");
						return false;
					}

					//click on Done value
					groupingpnl_webe.btn_Done.click();

					Thread.sleep(500);
					if (groupingpnl_webe.webe_WarningIssueSubmit.isDisplayed())
					{
						warningStatus = true;
					}

					if (struserRole.equalsIgnoreCase("coder") && strcaseSubmitType.equalsIgnoreCase("completed"))
					{
						Log4J.logp.info("If role is coder and coder want to complete case then...");
						// click here to case completed
						groupingpnl_webe.chk_routeForReview.click();
						//click here to go to landing page
						groupingpnl_webe.chk_takeMeQueue.click();

						groupingpnl_webe.btn_submitAfterDone.click();
					}
					else if (struserRole.equalsIgnoreCase("coder") && strcaseSubmitType.equalsIgnoreCase("billed"))
					{
						Log4J.logp.info("If role is coder and coder want to billed case then...");
						//click here to go to landing page
						groupingpnl_webe.chk_takeMeQueue.click();

						groupingpnl_webe.btn_submitAfterDone.click();

					}
					else if (struserRole.equalsIgnoreCase("cdi") || struserRole.equalsIgnoreCase("reviewer"))
					{
						Log4J.logp.info("If role is CDI and Reviewer then...");
						//click here to go to landing page
						groupingpnl_webe.chk_takeMeQueue.click();

						groupingpnl_webe.btn_submitAfterDone.click();

					}
					else
					{
						Log4J.logp.error("Case submit type is =" + strcaseSubmitType + " or user type is =" + struserRole + " is not valid");
						Assert.assertTrue(false, "Case submit type is =" + strcaseSubmitType + " or user type is =" + struserRole + " is not valid");
						return false;
					}
				}
				else if (strcodingType.equalsIgnoreCase("icd10"))
				{
					Log4J.logp.info("If codeType is ICD10 then...");
					//to move on ICD 10
					codingPnl_webe.lst_codeType.click();
					Thread.sleep(500);
					codingPnl_webe.lnk_ICD10_Menu.click();
					Thread.sleep(500);
					getcode = query.getValuebyColumnName("td_searchcode", "sk020", "code");
					Log4J.logp.info("The code is ==" + getcode);

					//search particular code
					bcodeSearchStatus = Common_Lib.codeSearchInCodingPnl(getcode);
					if (!bcodeSearchStatus)
					{
						Log4J.logp.info("Code is not added");
						//add code
						WorkingScreen_Lib.addnewCode_withData_ICD10("sk020");

						/*WebElement webe = driver.findElement(By.xpath("//div[text()='" + code + "']"));

						Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, webe, 30);
						Thread.sleep(1000);
						driver.findElement(By.xpath("//div[text()='" + code + "']/ancestor::div[@class='expand']//span[contains(@id,'code-accepted')]")).click();*/
					}

					//Accept code
					WorkingScreen_Lib.accept_Code("sk020");
					//refresh grouping value
					groupingpnl_webe.btn_Done.click();
					Thread.sleep(1000);

					if (!groupingpnl_webe.btn_Done.getText().equals("DONE"))
					{
						Log4J.logp.error("Not found grouping value");
						Assert.assertTrue(false, "Not found grouping value");
						return false;
					}

					//click on Done value
					//groupingpnl_webe.btn_Done.click();
					Thread.sleep(500);
					if (groupingpnl_webe.webe_WarningIssueSubmit.isDisplayed())
					{
						warningStatus = true;
					}

					if (struserRole.equalsIgnoreCase("coder") && strcaseSubmitType.equalsIgnoreCase("completed"))
					{
						Log4J.logp.info("If role is coder and coder want to complete case then...");
						// click here to case completed
						groupingpnl_webe.chk_routeForReview.click();
						//click here to go to landing page
						groupingpnl_webe.chk_takeMeQueue.click();

						groupingpnl_webe.btn_submitAfterDone.click();
					}
					else if (struserRole.equalsIgnoreCase("coder") && strcaseSubmitType.equalsIgnoreCase("billed"))
					{
						Log4J.logp.info("If role is coder and coder want to billed case then...");
						//click here to go to landing page
						groupingpnl_webe.chk_takeMeQueue.click();

						groupingpnl_webe.btn_submitAfterDone.click();

					}
					else if (struserRole.equalsIgnoreCase("cdi") && struserRole.equalsIgnoreCase("reviewer"))
					{
						Log4J.logp.info("If role is CDI and Reviewer then...");
						//click here to go to landing page
						groupingpnl_webe.chk_takeMeQueue.click();

						groupingpnl_webe.btn_submitAfterDone.click();

					}
					else
					{
						Log4J.logp.error("Case submit type is =" + strcaseSubmitType + " or user type is =" + struserRole + " is not valid");
						Assert.assertTrue(false, "Case submit type is =" + strcaseSubmitType + " or user type is =" + struserRole + " is not valid");
						return false;
					}

				}
				else
				{
					Log4J.logp.error("Code Type is not valid =" + strcodingType);
					Assert.assertTrue(false, "Code Type is not valid =" + strcodingType);
					return false;
				}

			}
			else
			{
				Log4J.logp.error("Service Line is not valid =" + strserviceLine);
				Assert.assertTrue(false, "Service Line is not valid =" + strserviceLine);
				return false;
			}
			Log4J.logp.info("---------------- submit_case -- completed ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- In submit_case --case submission is failed ----------------");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is for select POA value in all type diagnosis code
	 * 
	 * @author skhalasi
	 * @since 1/12/2014
	 * @param diagnosisType
	 * @param POAValue
	 * @return
	 */

	public static boolean select_POA_Value(String diagnosisType, String POAValue)
	{

		try
		{
			Log4J.logp.info("---------------- Started  -- In select_POA_Value ----------------");
			driver = ExecutionSetup.getDriver();
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			if (diagnosisType.equalsIgnoreCase("admit"))
			{
				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstAdmitCodeNum, 30);
				Thread.sleep(1000);
				codingPnl_webe.lbl_firstAdmitCodeNum.click();
				Thread.sleep(1000);
				Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 20);
				Thread.sleep(1000);
				if (POAValue.equals("Y"))
				{
					codingPnl_webe.lnk_YPOA_firstAdmit.click();
					Thread.sleep(1000);
					if (!codingPnl_webe.lnk_YPOA_firstAdmit.getAttribute("class").equals("poa-label"))
					{
						Log4J.logp.error("POA value 'Y' is not selected for Admitting Diagnosis");
						return false;
					}
				}
				else if (POAValue.equals("N"))
				{
					codingPnl_webe.lnk_NPOA_firstAdmit.click();
					Thread.sleep(1000);
					if (!codingPnl_webe.lnk_NPOA_firstAdmit.getAttribute("class").equals("poa-label"))
					{
						Log4J.logp.error("POA value 'N' is not selected for Admitting Diagnosis");
						return false;
					}
				}
				else if (POAValue.equals("U"))
				{
					codingPnl_webe.lnk_UPOA_firstAdmit.click();
					Thread.sleep(1000);
					if (!codingPnl_webe.lnk_UPOA_firstAdmit.getAttribute("class").equals("poa-label"))
					{
						Log4J.logp.error("POA value 'U' is not selected for Admitting Diagnosis");
						return false;
					}
				}
				else if (POAValue.equals("W"))
				{
					codingPnl_webe.lnk_WPOA_firstAdmit.click();
					Thread.sleep(1000);
					if (!codingPnl_webe.lnk_WPOA_firstAdmit.getAttribute("class").equals("poa-label"))
					{
						Log4J.logp.error("POA value 'W' is not selected for Admitting Diagnosis");
						return false;
					}
				}
				else if (POAValue.equals("E"))
				{
					codingPnl_webe.lnk_EPOA_firstAdmit.click();
					Thread.sleep(1000);
					if (!codingPnl_webe.lnk_EPOA_firstAdmit.getAttribute("class").equals("poa-label"))
					{
						Log4J.logp.error("POA value 'E' is not selected for Admitting Diagnosis");
						return false;
					}
				}
				else
				{
					Log4J.logp.error("Please select proper POAValue type. e.g. Y for Admitting diagnosis");
					return false;
				}
			}
			else if (diagnosisType.equalsIgnoreCase("principal"))
			{
				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstPrincipalCodeNum, 30);
				Thread.sleep(1000);
				codingPnl_webe.lbl_firstPrincipalCodeNum.click();
				Thread.sleep(1000);
				Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 20);
				Thread.sleep(1000);
				if (POAValue.equals("Y"))
				{
					codingPnl_webe.lnk_YPOA_firstPrincipal.click();
					Thread.sleep(1000);
					if (!codingPnl_webe.lnk_YPOA_firstPrincipal.getAttribute("class").equals("poa-label"))
					{
						Log4J.logp.error("POA value 'Y' is not selected for Principal diagnosis");
						return false;
					}
				}
				else if (POAValue.equals("N"))
				{
					codingPnl_webe.lnk_NPOA_firstPrincipal.click();
					Thread.sleep(1000);
					if (!codingPnl_webe.lnk_NPOA_firstPrincipal.getAttribute("class").equals("poa-label"))
					{
						Log4J.logp.error("POA value 'N' is not selected for Principal diagnosis");
						return false;
					}
				}
				else if (POAValue.equals("U"))
				{
					codingPnl_webe.lnk_UPOA_firstPrincipal.click();
					Thread.sleep(1000);
					if (!codingPnl_webe.lnk_UPOA_firstPrincipal.getAttribute("class").equals("poa-label"))
					{
						Log4J.logp.error("POA value 'U' is not selected for Principal diagnosis");
						return false;
					}
				}
				else if (POAValue.equals("W"))
				{
					codingPnl_webe.lnk_WPOA_firstPrincipal.click();
					Thread.sleep(1000);
					if (!codingPnl_webe.lnk_WPOA_firstPrincipal.getAttribute("class").equals("poa-label"))
					{
						Log4J.logp.error("POA value 'W' is not selected for Principal diagnosis");
						return false;
					}
				}
				else if (POAValue.equals("E"))
				{
					codingPnl_webe.lnk_EPOA_firstPrincipal.click();
					Thread.sleep(1000);
					if (!codingPnl_webe.lnk_EPOA_firstPrincipal.getAttribute("class").equals("poa-label"))
					{
						Log4J.logp.error("POA value 'E' is not selected for Principal diagnosis");
						return false;
					}
				}
				else
				{
					Log4J.logp.error("Please select proper POAValue type. e.g. Y for Secondary diagnosis");
					return false;
				}
			}
			else if (diagnosisType.equalsIgnoreCase("secondary"))
			{
				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstSecondaryCodeNum, 30);
				Thread.sleep(1000);
				codingPnl_webe.lbl_firstSecondaryCodeNum.click();
				Thread.sleep(1000);
				Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 20);
				Thread.sleep(1000);
				if (POAValue.equals("Y"))
				{
					codingPnl_webe.lnk_YPOA_firstSecondary.click();
					Thread.sleep(1000);
					if (!codingPnl_webe.lnk_YPOA_firstSecondary.getAttribute("class").equals("poa-label"))
					{
						Log4J.logp.error("POA value 'Y' is not selected for secondary diagnosis");
						return false;
					}
				}
				else if (POAValue.equals("N"))
				{
					codingPnl_webe.lnk_NPOA_firstSecondary.click();
					Thread.sleep(1000);
					if (!codingPnl_webe.lnk_NPOA_firstSecondary.getAttribute("class").equals("poa-label"))
					{
						Log4J.logp.error("POA value 'N' is not selected for secondary diagnosis");
						return false;
					}
				}
				else if (POAValue.equals("U"))
				{
					codingPnl_webe.lnk_UPOA_firstSecondary.click();
					Thread.sleep(1000);
					if (!codingPnl_webe.lnk_UPOA_firstSecondary.getAttribute("class").equals("poa-label"))
					{
						Log4J.logp.error("POA value 'U' is not selected for secondary diagnosis");
						return false;
					}
				}
				else if (POAValue.equals("W"))
				{
					codingPnl_webe.lnk_WPOA_firstSecondary.click();
					Thread.sleep(1000);
					if (!codingPnl_webe.lnk_WPOA_firstSecondary.getAttribute("class").equals("poa-label"))
					{
						Log4J.logp.error("POA value 'W' is not selected for secondary diagnosis");
						return false;
					}
				}
				else if (POAValue.equals("E"))
				{
					codingPnl_webe.lnk_EPOA_firstSecondary.click();
					Thread.sleep(1000);
					if (!codingPnl_webe.lnk_EPOA_firstSecondary.getAttribute("class").equals("poa-label"))
					{
						Log4J.logp.error("POA value 'E' is not selected for secondary diagnosis");
						return false;
					}
				}
				else
				{
					Log4J.logp.error("Please select proper POAValue type. e.g. Y");
					return false;
				}
			}
			else
			{
				Log4J.logp.error("Please select proper diagnosis type. e.g. admit");
				return false;
			}

			Log4J.logp.info("---------------- select_POA_Value -- completed ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- In select_POA_Value --Select POA value is failed ----------------");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is use for click on down arrow of evidence and check quey link is present
	 * 
	 * @author skhalasi
	 * @since 01/12/2014
	 * @param webeDownArrow
	 * @param webeQueryLnk
	 * @return
	 */

	public static boolean checkQuryLinkPresent(WebElement webeDownArrow, WebElement webeQueryLnk)
	{
		try
		{
			Log4J.logp.info("---------------- Started - checkQuryLinkPresent ----------------");
			driver = ExecutionSetup.getDriver();
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);

			webeDownArrow.click();
			Thread.sleep(1000);
			if (Common_Lib.checkElementPresent(webeQueryLnk))
			{
				Log4J.logp.error("Query link is present");
				Assert.assertTrue(false, "Query link is present");
			}
			else
			{
				Log4J.logp.info("Query link is not present");
				Assert.assertTrue(true, "Query link is not present");
			}
			Thread.sleep(1000);
			webeDownArrow.click();
			Thread.sleep(1000);
			Log4J.logp.info("---------------- checkQuryLinkPresent -- completed ----------------");

			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- In checkQuryLinkPresent -- is failed ----------------");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is check Accept, Reject and Modify icon status after CDI user raise query issue
	 * 
	 * @author agupta
	 * @since 3/12/2014
	 */
	public static boolean checkIconStatus(WebElement elAccept, WebElement elpAccept, WebElement elModify, String stricon)
	{
		boolean bicon = false;
		boolean bstatus = true;
		try
		{
			Log4J.logp.info("----------------- Started :: checkIconStatus -----------------");
			if (stricon.equalsIgnoreCase("accepted"))
			{
				bicon = Common_Lib.checkElementPresent(elpAccept);
				if (bicon == true)
				{
					Log4J.logp.info("Probable code Accepted after CDI raise query issue ");
				}
				else
				{
					bstatus = false;
				}
				Thread.sleep(2000);
				bicon = Common_Lib.checkElementPresent(elAccept);
				if (bicon == true)
				{
					Log4J.logp.info("Associated code is Accepted after raise query issue");
				}
				else
				{
					bstatus = false;
				}
			}

			if (stricon.equalsIgnoreCase("modified"))
			{
				bicon = Common_Lib.checkElementPresent(elAccept);
				if (bicon == true)
				{
					bicon = Common_Lib.checkElementPresent(elModify);
					if (bicon == true)
					{
						Log4J.logp.info("Associated code is Displayed with Modify and Accepeted icon");
					}
				}
				else
				{
					bstatus = false;
				}
				bicon = Common_Lib.checkElementPresent(elpAccept);
				if (bicon == true)
				{
					Log4J.logp.info("Probable code  is Accepted after raise query on modified code ");
				}
				else
				{
					bstatus = false;
				}
			}
			Log4J.logp.info("--------------- Ended :: checkIconStatus -------------------");
			return bstatus;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Probelm Found In :: checkIconStatus");
			Assert.assertTrue(false, "Probelm Found In :: checkIconStatus");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is check that Associated and Probable code is modified or not
	 * 
	 * @author agupta
	 * @since 09/12/2014
	 */
	public static boolean checkModifyStatus(WebElement elAssociated, WebElement eleProbable)
	{
		boolean bicon = false;
		boolean bstatus = true;

		try
		{
			Log4J.logp.info("-------------------- Started :: checkModifyStatus ------------------------ ");
			bicon = Common_Lib.checkElementPresent(elAssociated);
			if (bicon == true)
			{
				Log4J.logp.info("Modified icon is displayed after re-modified associated code");
			}
			else
			{
				bstatus = false;
			}
			bicon = Common_Lib.checkElementPresent(eleProbable);
			if (bicon == true)
			{
				Log4J.logp.info("Modify icon is displayed on probable code after remodified Probable code");
			}
			else
			{
				bstatus = false;
			}
			Log4J.logp.info("-------------------- Ended :: checkModifyStatus ------------------------ ");
			return bstatus;
		}
		catch (Exception e)
		{

			Log4J.logp.error("Problem found in :: checkModifyStatus");
			Assert.assertTrue(false, "Problem Found in :: checkModifyStatus");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is use for get DRG value
	 * 
	 * @author agupta
	 * @since 24/11/2014
	 */

	public static String getDRG(String strDRG)
	{
		String strtemp1[], strtemp2[];
		String strfalse = null;
		try
		{
			Log4J.logp.info("-------------- Started : getDRG ---------------");

			Log4J.logp.info("DRG = " + strDRG);
			strtemp1 = strDRG.split(",");
			strtemp2 = strtemp1[0].split(":");
			strdrg = strtemp2[1].trim();
			Log4J.logp.info("--------------- Ended : getDRG ---------------");
			return strdrg;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: getDRG");
			Assert.assertTrue(false, "Problem found in :: getDRG");
			e.printStackTrace();
			return strfalse;
		}
	}

	/**
	 * This method is for enters values in Procedures codes
	 * 
	 * @author agupta
	 * @since 20/11/2014
	 */
	public static boolean addParameteres(String RowId, String lbl)
	{
		String strm1 = null;
		String strm2 = null;
		String strm3 = null;
		String strm4 = null;
		String unitOfService = null;
		String revenueCode = null;
		String episode = null;
		String anesthesiaType = null;
		String provider = null;
		String anesthesiaProvider = null;
		String Date = null;
		String anesthesiaTime = null;
		WebElement elevalue;
		try
		{
			Log4J.logp.info("------------ Started : addParameteres ---------------");

			driver = ExecutionSetup.getDriver();
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);

			Map<String, String> rowTestData = null;

			JDBCMySql searchCriteria_TestData = new JDBCMySql();

			rowTestData = searchCriteria_TestData.getRowbyID("td_addparameters", RowId);
			strm1 = rowTestData.get("Modifier1");
			strm2 = rowTestData.get("Modifier2");
			strm3 = rowTestData.get("Modifier3");
			strm4 = rowTestData.get("Modifier4");
			unitOfService = rowTestData.get("UnitofService");
			revenueCode = rowTestData.get("RevenueCode");
			episode = rowTestData.get("Episode");
			anesthesiaType = rowTestData.get("AnesthesiaType");
			provider = rowTestData.get("Provider");
			anesthesiaProvider = rowTestData.get("AnesthesiaProvider");
			Date = rowTestData.get("Date");
			anesthesiaTime = rowTestData.get("AnesthesiaTime");

			if (strm1 != null)
			{

				elevalue = driver.findElement(By.xpath("(//*[contains(@id, '" + lbl + "')]//div[@class='modifier-input-group']//select)[1]"));
				Select dropdown = new Select(elevalue);
				dropdown.selectByVisibleText(strm1);
			}
			if (strm2 != null)
			{

				elevalue = driver.findElement(By.xpath("(//*[contains(@id, '" + lbl + "')]//div[@class='modifier-input-group']//select)[2]"));
				Select dropdown = new Select(elevalue);
				dropdown.selectByVisibleText(strm2);
			}
			if (strm3 != null)
			{

				elevalue = driver.findElement(By.xpath("(//*[contains(@id, '" + lbl + "')]//div[@class='modifier-input-group']//select)[3]"));
				Select dropdown = new Select(elevalue);
				dropdown.selectByVisibleText(strm3);
			}
			if (strm4 != null)
			{

				elevalue = driver.findElement(By.xpath("(//*[contains(@id, '" + lbl + "')]//div[@class='modifier-input-group']//select)[4]"));
				Select dropdown = new Select(elevalue);
				dropdown.selectByVisibleText(strm4);
			}

			if (unitOfService != null)
			{
				elevalue = driver.findElement(By.xpath("//*[contains(@id, '" + lbl + "')]//input[contains(@id,'unitOfService-textbox')]"));
				elevalue.sendKeys(unitOfService);
			}

			if (revenueCode != null)
			{
				elevalue = driver.findElement(By.xpath("//*[contains(@id, '" + lbl + "')]//input[contains(@id,'revenuecode-textbox')]"));
				elevalue.sendKeys(revenueCode);
			}

			if (episode != null)
			{
				codingPnl_webe.txt_Episode.sendKeys(episode);
			}

			if (anesthesiaType != null)
			{
				codingPnl_webe.txt_AnesthesiaType.sendKeys(anesthesiaType);
			}

			if (provider != null)
			{
				codingPnl_webe.txt_Provider.sendKeys(provider);
			}

			if (anesthesiaProvider != null)
			{
				codingPnl_webe.txt_AnesthesiaProvider.sendKeys(anesthesiaProvider);
			}

			if (Date != null)
			{
				//Common_Lib.fillDate(codingPnl_webe.txt_Date, Date);
				Common_Lib.fillDate(codingPnl_webe.txt_Date, Date + "ws");
			}

			if (anesthesiaTime != null)
			{
				codingPnl_webe.txt_AnesthesiaTime.sendKeys(anesthesiaTime);
			}
			Log4J.logp.info("----------------- Ended : addParameteres ------------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: addParameteres");
			Assert.assertTrue(false, "Problem found in ::  addParameteres");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is check LATER and Done button with Issues and Edits
	 * 
	 * @author agupta
	 * @since 27/11/2014
	 */
	public static boolean checkButton(WebElement eOnHold, WebElement ePending)
	{
		boolean biconstatus;
		boolean bstatus = true;

		try
		{
			Log4J.logp.info("----------------- Started : checkButton ------------------");

			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			demographicpnl_webe = DemographicPnl_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			demographicpnl_webe = DemographicPnl_WebE.getInstance(driver);

			// Check LATER button with On Hold Status
			Common_Lib.openCase("ap008");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "clickable", 20);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("ap008");
			biconstatus = eOnHold.isDisplayed();
			if (biconstatus == true)
			{
				Log4J.logp.info("Case has been Displayed with On Hold Status without issue");

			}
			else
			{
				bstatus = false;
			}
			searchCriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			// Check LATER button with Pending Status
			Common_Lib.openCase("ap008");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "clickable", 15);
			Common_Lib.rightclick_system_sugggested_evidence();
			Thread.sleep(1000);
			medicalrecordpnl_webe.lnk_discusswithcolleague.click();
			Thread.sleep(2000);
			IssuePanel_Lib.send_DiscussWithColleague("ap001");
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(1000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(3000);
			SearchCriteria_Lib.search_Case("ap008");
			biconstatus = ePending.isDisplayed();
			if (biconstatus = true)
			{
				Log4J.logp.info("Case has been displayed with Pending status after raise issue in issue panel");
			}
			else
			{
				bstatus = false;
			}
			searchCriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			// to check warning message with Pending issue in issue panel 
			Common_Lib.openCase("ap008");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(3000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(2000);
			if (groupingpnl_webe.btn_Done.getText().equals("DONE"))
			{
				Log4J.logp.info("Done button is Displayed after calculated DRG value");
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(2000);
			biconstatus = groupingpnl_webe.webe_WarningIssueSubmit.isDisplayed();
			if (biconstatus == true)
			{
				Log4J.logp.info("Warning message has been displayed of Pending message in Confirmation box");
			}
			else
			{
				bstatus = false;
			}

			groupingpnl_webe.btn_cancelAfetrDone.click();
			Thread.sleep(2000);

			// to check Done button with critical edits
			demographicpnl_webe.lnk_DemoPnlExpand.click();
			Thread.sleep(1000);
			Select dropdown = new Select(demographicpnl_webe.lst_DischargeDisposition);
			dropdown.selectByValue("15");
			Thread.sleep(2000);
			demographicpnl_webe.lnk_DemoPnlExpand.click();
			Thread.sleep(1000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			biconstatus = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (biconstatus == true)
			{
				Log4J.logp.info("Grouping icon has been displayed with critical edits case has not be submitted");
			}
			else
			{
				bstatus = false;
			}

			// Check LATER button with resolved all Pending issue
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			issuepnl_webe.lnk_markasresolved.click();
			Thread.sleep(1000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(3000);
			SearchCriteria_Lib.search_Case("ap008");
			biconstatus = eOnHold.isDisplayed();
			if (biconstatus == true)
			{
				Log4J.logp.info("Case has been Displayed with On Hold Status after resolved issue");
			}
			else
			{
				bstatus = false;
			}
			searchCriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			// to check user can able to submit the case with resolved and zero edits
			Common_Lib.openCase("ap008");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);
			demographicpnl_webe.lnk_DemoPnlExpand.click();
			Thread.sleep(1000);
			dropdown = new Select(demographicpnl_webe.lst_DischargeDisposition);
			dropdown.selectByValue("2");
			Thread.sleep(2000);
			demographicpnl_webe.lnk_DemoPnlExpand.click();
			Thread.sleep(1000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			if (groupingpnl_webe.btn_Done.getText().equals("DONE"))
			{
				Log4J.logp.info("Button has been displayed with Done label Coder can submit the case");
			}
			else
			{
				bstatus = false;
			}
			Log4J.logp.info("----------------- Ended : checkButton -----------------");
			return bstatus;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: checkButton");
			Assert.assertTrue(false, "Problem found in :: checkButton");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is to find diagnosiscode in case
	 * 
	 * @author jsolanki
	 * @since 05/12/2014
	 */
	public static boolean find_DiagnosisCode()
	{

		try
		{
			Log4J.logp.info("**************************** Started  -- find_DiagnosisCode ****************************");
			driver = ExecutionSetup.getDriver();
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstPrincipalCodeNum, 30);
			Thread.sleep(1000);
			if (Common_Lib.checkElementPresent(codingPnl_webe.lbl_firstPrincipalCodeNum))
			{
				Log4J.logp.info("-- Principal Diagnosis code found");
			}
			else
			{
				Log4J.logp.error("-- Principal Diagnosis code not found");
			}

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstAdmitCodeNum, 30);
			Thread.sleep(1000);

			if (Common_Lib.checkElementPresent(codingPnl_webe.lbl_firstAdmitCodeNum))
			{
				Log4J.logp.info("--  Admiting Diagnosis code found");
			}
			else
			{
				Log4J.logp.info("-- Admiting Diagnosis code not found");
			}

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstSecondaryCodeNum, 30);
			Thread.sleep(1000);

			if (Common_Lib.checkElementPresent(codingPnl_webe.lbl_firstSecondaryCodeNum))
			{
				Log4J.logp.info("--  Secondary Diagnosis code found");
			}
			else
			{
				Log4J.logp.info("-- Secondary Diagnosis code not found");
			}

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstProcedureCodeNum, 30);
			Thread.sleep(1000);

			if (Common_Lib.checkElementPresent(codingPnl_webe.lbl_firstProcedureCodeNum))
			{
				Log4J.logp.info("--  Procedure code found");
			}
			else
			{
				Log4J.logp.info("-- Procedure code not found");
			}

			Log4J.logp.info("**************************** find_DiagnosisCode completed ****************************");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************************** In find_DiagnosisCode -- Is failed as no code found ****************************");
			e.printStackTrace();
			return false;
		}

	}

	public static boolean selecttext(String rowId)
	{
		Map<String, String> rowTestData = null;
		String text = null;
		String selectText = null;
		String operation = null;
		String foundin = null;
		String str = null;
		JSONObject j = null;
		int _y = 0;
		int _x = 0;
		int _tempNode = 0;
		int _begin = 0;
		int _end = 0;
		String string1 = null;
		String text1 = null;
		Point coordinates = null;
		Point coordinates1 = null;
		WebElement webe = null;
		WebElement c1 = null;
		Robot robot = null;
		try
		{
			Log4J.logp.info("********* Started : selecttext **********");
			driver = ExecutionSetup.getDriver();
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			codeBook_webe = CodeBook_WebeE.getInstance(driver);
			JDBCMySql query = new JDBCMySql();
			rowTestData = query.getRowbyID("td_selecttext", rowId);
			selectText = rowTestData.get("selecttext");
			operation = rowTestData.get("operation");
			foundin = rowTestData.get("foundin");
			if (selectText != null)
			{
				text = null;
				String key = selectText;
				if (driver instanceof JavascriptExecutor)
				{
					text = (String) ((JavascriptExecutor) driver)
							.executeScript(

							"var getTextNodesIn=function(a){var c,b=$(a).find(\":not(iframe)\").addBack().contents().filter(function(){return 3==this.nodeType});return $(\"#doc-content\").find(\".doc-divider\").find(\":not(iframe)\").addBack().contents().filter(function(){return 3==this.nodeType}).each(function(a){return this.data==b[0].data?(c=a,!1):void 0}),c};"
									+ "var getTextNodes=function(a){return $(a).find(\":not(iframe)\").addBack().contents().filter(function(){return 3==this.nodeType})};"
									+ "var getNodeNumber=function(a,b){var c=!1;return $.each(a,function(a){return key=this.data.indexOf(b),key>-1?(c=a,!1):void 0}),c};"
									+ "String.prototype.getPosition=function(a){var b=a.parent;return\"object\"==typeof this?\"undefined\"==typeof _pos&&(_searchKey=this.toString(),_tempNodes=getTextNodes(\"#doc-content .doc-divider\"),_tempNode=getNodeNumber(_tempNodes,_searchKey),$(b).html(function(a,b){return b.replace(_searchKey,'<span class=\"_tmp_string\">'+_searchKey+\"</span>\")}),_pos=$(\"._tmp_string\").offset(),_eTop=$(\"._tmp_string\").offset().top,_pos.top=parseInt(_eTop-$(\"._tmp_string\").scrollTop()),_pos.left=parseInt(_pos.left),_pos.tempNode=_tempNode-1,$(b).html(function(a,b){return b.replace('<span class=\"_tmp_string\">'+_searchKey+\"</span>\",_searchKey)}),_start=_tempNodes[_tempNode].data.indexOf(_searchKey),_end=_start+_searchKey.length,_pos.begin=_start,_pos.end=_end):_pos={},JSON.stringify(_pos)};"
									+ "var searchKey = '" + key + "';" + "return searchKey.getPosition({parent : '#doc-content .doc-divider'});");

				}
				Log4J.logp.info(text);
				j = new JSONObject(text);
				Log4J.logp.info("Object = " + j);
				_y = (int) j.getInt("top");
				_x = j.getInt("left");
				_tempNode = j.getInt("tempNode");
				_begin = j.getInt("begin");
				_end = j.getInt("end");
				//System.out.println("$('#doc-content').mCustomScrollbar(\"scrollTo\","+(_y-100)+");");
				string1 = "var getTextNodes=function(a){return $(a).find(\":not(iframe)\").addBack().contents().filter(function(){return 3==this.nodeType})};" + "var tempNode = getTextNodes(\"#doc-content .doc-divider\");" + "$('#doc-content').mCustomScrollbar(\"scrollTo\"," + (_y - 110) + ");"
						+ "var selection = window.getSelection();" + "var range = document.createRange();" + "range.setStart(tempNode[" + _tempNode + "]," + _begin + ");" + "range.setEnd(tempNode[" + _tempNode + "]," + _end + ");" + "selection.removeAllRanges();" + "selection.addRange(range);" +
						//"$(\"#doc-content\").trigger('mousedown');"+
						//"$(\"#doc-content\").trigger('mouseup');"+
						"return selection.toString();";
				text1 = (String) ((JavascriptExecutor) driver).executeScript(string1);
				Log4J.logp.info("Hurry Text Selected text is " + text1);
				Thread.sleep(2500);
				Log4J.logp.info("js for selecting evidence has been executed");
				robot = new Robot();
				robot.mouseMove(_x + 36, 215);
				robot.delay(500);
				robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
				robot.delay(500);
				robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
				robot.delay(3000); // This delay is compolsary for the method as it fails other wise
				if (!System.getProperty("os.name").contains("Windows"))
				{
					Log4J.logp.info("Others");
					robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
					robot.delay(500);
					robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
					robot.delay(500);
				}

				if (operation != null)
				{
					if (operation.equals("addexistingcode"))
					{
						Log4J.logp.info("Now robot script runs for add as evidence");
						WebElement c = medicalrecordpnl_webe.txt_addasevidence;
						coordinates = c.getLocation();
						Log4J.logp.info(coordinates);
						robot.mouseMove(coordinates.getX() + 36, coordinates.getY() + 100);
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
						robot.delay(3000); // This delay is compolsary for the method as it fails other wise
						c1 = medicalrecordpnl_webe.txt_addtoexistingcode;
						Log4J.logp.info("Now robot script runs for add to existing code");
						coordinates1 = c1.getLocation();
						robot.mouseMove(coordinates1.getX() + 36, coordinates1.getY() + 100);
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
						robot.delay(3000); // This delay is compolsary for the method as it fails other wise
						webe = codingPnl_webe.lbl_firstPrincipalCodeNum;
						Actions action = new Actions(driver);
						action.moveToElement(webe).perform();
						Thread.sleep(1000);
						action.click().build().perform();
						Log4J.logp.info("********* Ending : for add as evidence **********");
						return true;
					}
					else if (operation.equals("queryphysician"))
					{
						Log4J.logp.info("Now robot script runs for QueryPhysician");
						medicalrecordpnl_webe.txt_queryphysician.click();
						Thread.sleep(1000);
						IssuePanel_Lib.send_QueryToPhysician("js001");
						Thread.sleep(1000);
						issuepnl_webe.btn_Issues.click();
						Thread.sleep(1000);
						Log4J.logp.info("********* Ending : for QueryPhysician **********");
						return true;

					}

					else if (operation.equals("discusscolleague"))
					{
						Log4J.logp.info("Now robot script runs for Discuss with Colleague");
						medicalrecordpnl_webe.txt_discusscolleague.click();
						IssuePanel_Lib.send_DiscussWithColleague("js001");
						Thread.sleep(1000);
						issuepnl_webe.btn_Issues.click();
						Thread.sleep(1000);
						Log4J.logp.info("********* Ending : Discuss with Colleague **********");
						return true;
					}

					else if (operation.equals("diagnosis"))
					{
						Log4J.logp.info("Now robot script runs for Add new code in diagnosis");
						WebElement c = medicalrecordpnl_webe.txt_addasevidence;
						coordinates = c.getLocation();
						Log4J.logp.info(coordinates);
						robot.mouseMove(coordinates.getX() + 36, coordinates.getY() + 100);
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
						robot.delay(3000); // This delay is compolsary for the method as it fails other wise
						c1 = medicalrecordpnl_webe.txt_addnewcode;
						Log4J.logp.info("Now robot script runs for Add to New Code for Diagnosis");
						coordinates1 = c1.getLocation();
						robot.mouseMove(coordinates1.getX() + 36, coordinates1.getY() + 100);
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
						robot.delay(3000); // This delay is compolsary for the method as it fails other wise
						medicalrecordpnl_webe.txt_diagnosis.click();
						WebDriverWait wait = new WebDriverWait(driver, 10);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.lnk_firstResultCode));
						codeBook_webe.lnk_firstResultCode.click();
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.lnk_highlightedcode));
						codeBook_webe.lnk_highlightedcode.click();
						driver.switchTo().defaultContent();
						str = codingPnl_webe.lst_codeType.getText();
						if (str.contains("ICD-9"))
						{
							wait.until(ExpectedConditions.visibilityOf(codingPnl_webe.lst_secondDiagnosis_ICD9));
							codingPnl_webe.lst_secondDiagnosis_ICD9.sendKeys(foundin);
							codingPnl_webe.btn_saveDignosis_ICD9.click();
						}
						else
						{
							wait.until(ExpectedConditions.visibilityOf(codingPnl_webe.lst_secondDiagnosis_ICD10));
							codingPnl_webe.lst_secondDiagnosis_ICD10.sendKeys(foundin);
							codingPnl_webe.btn_saveDignosis_ICD10.click();
						}

						Log4J.logp.info("********* Ending : Add to New Code for Diagnosis **********");
						return true;
					}

					else if (operation.equals("procedures"))

					{
						Log4J.logp.info("Now robot script runs for Add new code in procedures");
						WebElement c = medicalrecordpnl_webe.txt_addasevidence;
						coordinates = c.getLocation();
						Log4J.logp.info(coordinates);
						robot.mouseMove(coordinates.getX() + 36, coordinates.getY() + 100);
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
						robot.delay(3000); // This delay is compolsary for the method as it fails other wise
						c1 = medicalrecordpnl_webe.txt_addnewcode;
						Log4J.logp.info("Now robot script runs for Add to New Code for Procedures");
						coordinates1 = c1.getLocation();
						robot.mouseMove(coordinates1.getX() + 36, coordinates1.getY() + 100);
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
						robot.delay(3000); // This delay is compolsary for the method as it fails other wise
						medicalrecordpnl_webe.txt_procedures.click();
						WebDriverWait wait = new WebDriverWait(driver, 10);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
						wait.until(ExpectedConditions.visibilityOf(codeBook_webe.lnk_firstResultCode));
						codeBook_webe.lnk_firstResultCode.click();
						Thread.sleep(5000); // This delay is compolsary for the method as it fails other wise
						str = codeBook_webe.txt_codebookIndexType.getText();
						if (str.contains("ICD-9-CM Procedure Tabular"))
						{
							codeBook_webe.lnk_highlightedcode.click();
							driver.switchTo().defaultContent();
							wait.until(ExpectedConditions.visibilityOf(codingPnl_webe.lst_procedure_ICD9));
							codingPnl_webe.lst_procedure_ICD9.sendKeys(foundin);
							codingPnl_webe.btn_saveProcedure_ICD9.click();
						}
						else if (str.contains("Book: —"))
						{
							codeBook_webe.btn_VerticalResize.click();
							Actions actions = new Actions(driver);
							actions.clickAndHold(codeBook_webe.btn_VerticalResize).moveByOffset(0, 100).release().perform();
							Common_Lib.waitForObject(codeBook_webe.lnk_addCode, "clickable", 10);
							codeBook_webe.lnk_addCode.click();
							driver.switchTo().defaultContent();
							wait.until(ExpectedConditions.visibilityOf(codingPnl_webe.lst_procedure_ICD10));
							codingPnl_webe.lst_procedure_ICD10.sendKeys(foundin);
							codingPnl_webe.btn_saveProcedure_ICD10.click();
						}

						Log4J.logp.info("********* Ending : Add to New Code for procedures **********");

						return true;
					}
					else
					{
						Log4J.logp.info("********* Ending : selecttext **********");
						Log4J.logp.error("Please check operation entry in database");
						return false;
					}
				}
				else
				{
					Log4J.logp.info("********* Ending : selecttext **********");
					Log4J.logp.error("Operation can not be null");
					return false;
				}

			}
			else
			{
				Log4J.logp.info("********* Ending : selecttext **********");
				Log4J.logp.error("selecttext can not be null");
				return false;
			}

		}
		catch (Exception e)
		{
			Log4J.logp.error("---------------- In selecttext --text selction is failed ----------------");
			e.printStackTrace();
			return false;
		}

	}
}
