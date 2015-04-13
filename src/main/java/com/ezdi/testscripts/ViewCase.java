package com.ezdi.testscripts;

import java.util.List;

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
import com.ezdi.framework.driverscript.DriverTestNG;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.library.Common_Lib;
import com.ezdi.library.IssuePanel_Lib;
import com.ezdi.library.LandingPage_Lib;
import com.ezdi.library.Login_Lib;
import com.ezdi.library.ManualAllocation_Lib;
import com.ezdi.library.MessageCenter_Lib;
import com.ezdi.library.SearchCriteria_Lib;
import com.ezdi.library.ViewCase_Lib;
import com.ezdi.library.WorkingScreen_Lib;
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

public class ViewCase
{

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

			Login_Lib.login("nc003");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * This method checks that Supervisor can view case for Fresh Case and its behavior is proper
	 * 
	 * @author fmodi
	 * @since 29/10/2014
	 */
	@Test(description = "ezCAC_ MVP-2422:Check if Supervisor's View Case behavior for Fresh Case is appropriate" + "ezCAC_MVP_Reg-2421:Supervisor should be able to View Case details for Fresh Case", priority = 1)
	public static void viewFreshCase()
	{

		List<WebElement> listDocs, listAccept_Icons, listReject_Icons, listModify_Icons, listTemps, listDocs_ICD10, listAccept_Icons_ICD10, listReject_Icons_ICD10, listModify_Icons_ICD10, listTemps_ICD10;
		String strGetActualText = null, strGetActualText_ICD10 = null;
		String strGetExpectedText, strDrg_Temp1, strDrg_Temp2[], strDrg_Temp3[], strTempAccept, strTempReject, strTempModified, strGetExpectedText_ICD10, strDrg_Temp1_ICD10, strDrg_Temp2_ICD10[], strDrg_Temp3_ICD10[], strTempAccept_ICD10, strTempReject_ICD10, strTempModified_ICD10;
		int iDrgVal, iDrgVal_ICD10;
		boolean bStatus;
		try
		{
			Log4J.logp.info("*************** Started -viewFreshCase ***************");

			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("fm001");
			Thread.sleep(2000);
			LandingPage_Lib.click_on_expand();
			Thread.sleep(2000);
			landingp_webe.lnk_FreshViewCase.click();

			//Thread.sleep(15000);
			//driver.switchTo().frame("iFrmviewCaseElement");

			//below line is temp commented
			//Common_Lib.waitForObject(messagecenter_webe.fra_viewCase, "iFrmviewCaseElement", 15);

			Thread.sleep(2000);
			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);

			//below all is for ICD-9 coding standard
			Log4J.logp.info("========== Started For ICD-9 Coding Standard ==========");

			//keep temp this line
			Common_Lib.waitForObject(viewcase_webe.panel_PatientDemographic, "visibility", 8);

			//below code block will check behavior of patient demographic panel
			Log4J.logp.info("========== In Check Behavior of Patient Demographic Panel ==========");
			Thread.sleep(1000);
			viewcase_webe.panel_PatientDemographic.click();
			Thread.sleep(2000);
			viewcase_webe.panel_PatientDemographic.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior of Patient Demographic Panel ==========");

			//below code block will check behavior of navigation menu of medical records panel
			Log4J.logp.info("========== In Check Behavior of Navigation Menu of Medical Records Panel ==========");
			viewcase_webe.lst_LeftNavigationMenu.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_dock.click();
			Thread.sleep(1000);
			listDocs = viewcase_webe.lst_leftNavigtionMenuItem;
			for (WebElement temp : listDocs)
			{
				temp.click();
				Thread.sleep(2000);
			}
			Thread.sleep(1000);
			viewcase_webe.lnk_unDock.click();
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior of Navigation Menu of Medical Records Panel ==========");

			//below code block will check behavior of navigation menu of coding panel
			Log4J.logp.info("========== In Check Behavior of Navigation Menu of Coding Panel ==========");
			viewcase_webe.lst_rightnavigationMenu.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_diagnosis.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_navadmittingdiagnosis.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_navprincipaldiagnosis.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_navsecondarydiagnosis.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_diagnosis.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_procedure.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_procedureInDropDown.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_procedure.click();
			Thread.sleep(2000);
			viewcase_webe.lst_rightnavigationMenu.click();
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior of Navigation Menu of Coding Panel ==========");

			//below code block will check behavior of font settings option
			Log4J.logp.info("========== In Check Behavior of Font Size Settings Button ==========");
			viewcase_webe.btn_settings.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_mediumFont.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_largeFont.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_smallFont.click();
			Thread.sleep(2000);
			viewcase_webe.btn_settings.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior of Font Size Settings Button ==========");

			//below code block will check behavior of abstract panel
			Log4J.logp.info("========== In Check Behavior of Abstract Panel ==========");
			viewcase_webe.lnk_AbstactPnl.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_AbstactPnl.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior of Abstract Panel ==========");

			//below code block will check behavior of suggested code templates
			Log4J.logp.info("========== In Check Behavior of Suggested Code Templates ==========");
			viewcase_webe.div_first_admitDia_ICD9.click();
			Thread.sleep(2000);
			Common_Lib.scroll_Page(codingpnl_webe.coding_Dragger, 30);
			Thread.sleep(1000);
			viewcase_webe.div_primaryDia_ICD9.click();
			Thread.sleep(2000);
			Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 5);
			Thread.sleep(1000);
			Common_Lib.scroll_Until_FindWebe(codingpnl_webe.coding_Dragger, viewcase_webe.div_first_secondDia_ICD9, 30);
			Thread.sleep(1000);
			Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 7);
			Thread.sleep(1000);
			viewcase_webe.div_first_secondDia_ICD9.click();
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior of Suggested Code Templates ==========");

			//below code block will check behavior of system suggested evidences from coding panel
			Log4J.logp.info("========== In Check Highlight Behavior of System Suggested Evidences from Coding Panel ==========");

			for (WebElement we : viewcase_webe.lnk_evidence)
			{

				Thread.sleep(1000);

				if (!we.isDisplayed())
				{
					Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 20);
					Thread.sleep(1000);
					Common_Lib.scroll_Page(codingpnl_webe.coding_Dragger, 50);
					Thread.sleep(2000);
					Log4J.logp.info("First if condition in while");
				}

				Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 7);
				Thread.sleep(1000);
				we.click();
				Thread.sleep(2000);

				strGetExpectedText = we.getText().trim().toLowerCase();

				listTemps = medicalrecordpnl_webe.lnk_highlighted_sys_evidence;

				for (WebElement temp : listTemps)
				{

					strGetActualText = temp.getText().trim().toLowerCase();
				}

				Log4J.logp.info(strGetExpectedText + "  " + strGetActualText);
				Assert.assertEquals(strGetActualText, strGetExpectedText);

			}
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Highlight Behavior of System Suggested Evidences from Coding Panel ==========");

			//below code block will check behavior of system suggested evidences from medical records panel
			Log4J.logp.info("========== In Check Highlight Behavior of System Suggested Evidences from Medical Records Panel ==========");

			for (WebElement we : medicalrecordpnl_webe.lnk_highlighted_sys_med_evidence)
			{

				Thread.sleep(2000);

				while (true)
				{

					if (!we.isDisplayed())
					{
						Thread.sleep(1000);
						Common_Lib.scroll_Page_VerySmall(medicalrecordpnl_webe.medical_Dragger, 5);
						Thread.sleep(1000);
						Common_Lib.scroll_Page(medicalrecordpnl_webe.medical_Dragger, 50);
						Thread.sleep(1000);
						if (we.isDisplayed())
						{
							Thread.sleep(1000);
							Common_Lib.scroll_Page_VerySmall(medicalrecordpnl_webe.medical_Dragger, 5);
							Thread.sleep(1000);
							we.click();
							Thread.sleep(1000);
							break;
						}

					}

					if (we.isDisplayed())
					{

						//Thread.sleep(3000);
						//This change made on 17-12-2014
						Thread.sleep(2000);

						we.click();

						//Thread.sleep(3000);
						//This change made on 17-12-2014
						Thread.sleep(2000);

						break;
					}

				}
			}
			Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1500);
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Highlight Behavior of System Suggested Evidences from Medical Records Panel ==========");

			//below code block will check behavior of DRG value
			Log4J.logp.info("========== In Check Behavior of DRG Value ==========");
			strDrg_Temp1 = viewcase_webe.txt_DRG.getText();
			Thread.sleep(2000);
			strDrg_Temp2 = strDrg_Temp1.split(",");
			Thread.sleep(1000);
			strDrg_Temp3 = strDrg_Temp2[0].trim().split(":");
			Thread.sleep(1000);
			iDrgVal = Integer.parseInt(strDrg_Temp3[1].trim());
			Thread.sleep(2000);
			if (iDrgVal == 0 && iDrgVal != 999)
			{
				Log4J.logp.info("DRG value is displayed 0");
			}
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior of DRG Value ==========");

			//below code block will check behavior of issues panel
			Log4J.logp.info("========== In Check Behavior of Issues Panel ==========");
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(1000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior of Issues Panel ==========");

			//below code block will check behavior of grouping panel
			Log4J.logp.info("========== In Check Behavior of Grouping Panel ==========");
			groupingpnl_webe.lnk_GroupingPnl.click();
			Thread.sleep(1000);
			groupingpnl_webe.lnk_GroupingPnl.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior of Grouping Panel ==========");

			//below code block will check behavior reference panel
			Log4J.logp.info("========== In Check if Reference Panel is Visible ==========");
			bStatus = Common_Lib.checkElementPresent(abstractpnl_webe.lnk_Ref_Encoder);
			if (bStatus == false)
			{
				Log4J.logp.info("Reference panel is not displayed to Supervisor");
				Assert.assertTrue(true, "Reference panel is not displayed to Supervisor");
			}
			else
			{
				Log4J.logp.error("Reference panel is displayed to Supervisor");
				Assert.assertTrue(false, "Reference panel is displayed to Supervisor");
			}
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check if Reference Panel is Visible ==========");

			//below code will check user can't accept/reject or modify code
			Log4J.logp.info("========== In Check Behavior that User can't Accept/Reject or Modify Code ==========");
			Log4J.logp.info("========== Started For Accepted ==========");
			listAccept_Icons = viewcase_webe.lst_acceptcode;
			for (WebElement accept_Icon : listAccept_Icons)
			{
				Common_Lib.scroll_Until_FindWebe(codingpnl_webe.coding_Dragger, accept_Icon, 30);
				Thread.sleep(1000);

				//temp keep below change
				//Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 5);
				Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 7);

				Thread.sleep(1000);
				accept_Icon.click();
				strTempAccept = accept_Icon.getAttribute("id");
				//System.out.println(strTempAccept);
				Log4J.logp.info(strTempAccept);

				if (strTempAccept.contains("enabled"))
				{
					Log4J.logp.error("Accept icon is clickable");
					Assert.assertTrue(false, "Accept icon is clickable");
					break;
				}
				else
				{
					Log4J.logp.info("Accept icon is not clickable");
					Assert.assertTrue(true, "Accept icon is not clickable");
				}
			}
			Log4J.logp.info("========== Ended For Accepted ==========");
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("========== Started For Rejected ==========");
			listReject_Icons = viewcase_webe.lst_rejectedcode;
			for (WebElement reject_Icon : listReject_Icons)
			{
				Common_Lib.scroll_Until_FindWebe(codingpnl_webe.coding_Dragger, reject_Icon, 30);
				Thread.sleep(1000);

				//temp keep below change
				//Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 5);
				Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 7);

				Thread.sleep(1000);
				reject_Icon.click();
				strTempReject = reject_Icon.getAttribute("id");
				//System.out.println(strTempReject);
				Log4J.logp.info(strTempReject);
				if (strTempReject.contains("enabled"))
				{
					Log4J.logp.error("Reject icon is clickable");
					Assert.assertTrue(false, "Reject icon is clickable");
					break;
				}
				else
				{
					Log4J.logp.info("Reject icon is not clickable");
					Assert.assertTrue(true, "Reject icon is not clickable");
				}

			}
			Log4J.logp.info("========== Ended For Rejected ==========");
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("========== Started For Modified ==========");
			listModify_Icons = viewcase_webe.lst_modifiedcode;
			for (WebElement modify_Icon : listModify_Icons)
			{
				Common_Lib.scroll_Until_FindWebe(codingpnl_webe.coding_Dragger, modify_Icon, 30);
				Thread.sleep(1000);

				//temp keep below change
				//Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 5);
				Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 7);

				Thread.sleep(1000);
				modify_Icon.click();
				strTempModified = modify_Icon.getAttribute("id");
				//System.out.println(strTempModified);
				Log4J.logp.info(strTempModified);
				if (strTempModified.contains("enabled"))
				{
					Log4J.logp.error("Modify icon is clickable");
					Assert.assertTrue(false, "Modify icon is clickable");
					break;
				}
				else
				{
					Log4J.logp.info("Modify icon is not clickable");
					Assert.assertTrue(true, "Modify icon is not clickable");
				}
			}
			Log4J.logp.info("========== Ended For Modified ==========");
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior that User can't Accept/Reject or Modify Code ==========");

			//below code block will check if user can raise query or discussion from medical records panel
			Log4J.logp.info("========== In Check Behavior that User can't raise Query or Discussion from Medical Records Panel ==========");
			Thread.sleep(2000);
			Actions action = new Actions(driver);
			action.moveToElement(common_webe.lnk_sys_evidence.get(0)).contextClick().build().perform();

			//Thread.sleep(3000);
			//This change made on 17-12-2014
			Thread.sleep(2000);

			if ((medicalrecordpnl_webe.lnk_querytophysician.isDisplayed()) || (medicalrecordpnl_webe.lnk_discusswithcolleague.isDisplayed()))
			{
				Log4J.logp.error("User can make changes.");
				Assert.assertTrue(false, "User can make changes.");
			}
			else
			{
				Log4J.logp.info("User can not make changes.");
				Assert.assertTrue(true, "User can not make changes.");
			}
			action.release().build().perform();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior that User can't raise Query or Discussion from Medical Records Panel ==========");

			//below code block will check if user can raise query or discussion from coding panel
			Log4J.logp.info("========== In Check Behavior that User can't raise Query or Discussion from Coding Panel ==========");
			Thread.sleep(2000);
			//Actions action = new Actions(driver);
			action.moveToElement(codingpnl_webe.lst_first_admit_evi_arrow).click().build().perform();

			//Thread.sleep(3000);
			//This change made on 17-12-2014
			Thread.sleep(2000);

			if ((codingpnl_webe.lnk_query_first_admit_evi.isDisplayed()) || (codingpnl_webe.lnk_discuss_first_admit_evi.isDisplayed()))
			{
				Log4J.logp.error("User can make changes.");
				Assert.assertTrue(false, "User can make changes.");
			}
			else
			{
				Log4J.logp.info("User can not make changes.");
				Assert.assertTrue(true, "User can not make changes.");
			}

			action.release().build().perform();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior that User can't raise Query or Discussion from Coding Panel ==========");
			Log4J.logp.info("========== Ended For ICD-9 Coding Standard ==========");

			//below code block will switch coding standard from ICD-9 to ICD-10
			Log4J.logp.info("========== In switching Coding Standard from ICD-9 to ICD-10 ==========");
			codingpnl_webe.lst_codeType.click();
			Thread.sleep(2000);
			codingpnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished switching Coding Standard from ICD-9 to ICD-10 ==========");

			//below all is for ICD-10 coding standard
			Log4J.logp.info("========== Started For ICD-10 Coding Standard ==========");

			//below code block will check behavior of patient demographic panel
			Log4J.logp.info("========== In Check Behavior of Patient Demographic Panel ==========");
			viewcase_webe.panel_PatientDemographic.click();
			Thread.sleep(2000);
			viewcase_webe.panel_PatientDemographic.click();
			Thread.sleep(1000);
			Log4J.logp.info("========== Finished Check Behavior of Patient Demographic Panel ==========");

			//below code block will check behavior of navigation menu of medical records panel
			Log4J.logp.info("========== In Check Behavior of Navigation Menu of Medical Records Panel ==========");
			viewcase_webe.lst_LeftNavigationMenu.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_dock.click();
			Thread.sleep(1000);
			listDocs_ICD10 = viewcase_webe.lst_leftNavigtionMenuItem;
			for (WebElement temp_ICD10 : listDocs_ICD10)
			{
				temp_ICD10.click();
				Thread.sleep(2000);
			}
			Thread.sleep(1000);
			viewcase_webe.lnk_unDock.click();
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior of Navigation Menu of Medical Records Panel ==========");

			//below code block will check behavior of navigation menu of coding panel
			Log4J.logp.info("========== In Check Behavior of Navigation Menu of Coding Panel ==========");
			viewcase_webe.lst_rightnavigationMenu.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_diagnosis.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_navadmittingdiagnosis.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_navprincipaldiagnosis.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_navsecondarydiagnosis.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_diagnosis.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_procedure.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_procedureInDropDown.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_procedure.click();
			Thread.sleep(2000);
			viewcase_webe.lst_rightnavigationMenu.click();
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior of Navigation Menu of Coding Panel ==========");

			//below code block will check behavior of font settings option
			Log4J.logp.info("========== In Check Behavior of Font Size Settings Button ==========");
			viewcase_webe.btn_settings.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_mediumFont.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_largeFont.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_smallFont.click();
			Thread.sleep(2000);
			viewcase_webe.btn_settings.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior of Font Size Settings Button ==========");

			//below code block will check behavior of abstract panel
			Log4J.logp.info("========== In Check Behavior of Abstract Panel ==========");
			viewcase_webe.lnk_AbstactPnl.click();
			Thread.sleep(2000);
			viewcase_webe.lnk_AbstactPnl.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior of Abstract Panel ==========");

			//below code block will check behavior of suggested code templates
			Log4J.logp.info("========== In Check Behavior of Suggested Code Templates ==========");
			viewcase_webe.div_first_admitDia_ICD10.click();
			Thread.sleep(2000);
			Common_Lib.scroll_Page(codingpnl_webe.coding_Dragger, 30);
			Thread.sleep(1000);
			viewcase_webe.div_primaryDia_ICD10.click();
			Thread.sleep(2000);
			Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 5);
			Thread.sleep(1000);
			Common_Lib.scroll_Until_FindWebe(codingpnl_webe.coding_Dragger, viewcase_webe.div_first_secondDia_ICD10, 30);
			Thread.sleep(1000);
			Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 7);
			Thread.sleep(1000);
			viewcase_webe.div_first_secondDia_ICD10.click();
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior of Suggested Code Templates ==========");

			//below code block will check behavior of system suggested evidences from coding panel
			Log4J.logp.info("========== In Check Highlight Behavior of System Suggested Evidences from Coding Panel ==========");

			for (WebElement we_ICD10 : viewcase_webe.lnk_evidence)
			{

				Thread.sleep(1000);

				if (!we_ICD10.isDisplayed())
				{
					Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 20);
					Thread.sleep(1000);
					Common_Lib.scroll_Page(codingpnl_webe.coding_Dragger, 30);
					Thread.sleep(2000);
					Log4J.logp.info("First if condition in while");
				}

				Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 7);
				Thread.sleep(1000);
				we_ICD10.click();
				Thread.sleep(2000);

				strGetExpectedText_ICD10 = we_ICD10.getText().trim().toLowerCase();

				listTemps_ICD10 = medicalrecordpnl_webe.lnk_highlighted_sys_evidence;

				for (WebElement temp_ICD10 : listTemps_ICD10)
				{
					strGetActualText_ICD10 = temp_ICD10.getText().trim().toLowerCase();
					Thread.sleep(500);
				}

				Log4J.logp.info(strGetExpectedText_ICD10 + "  " + strGetActualText_ICD10);
				Assert.assertEquals(strGetActualText_ICD10, strGetExpectedText_ICD10);

			}
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Highlight Behavior of System Suggested Evidences from Coding Panel ==========");

			//below code block will check behavior of system suggested evidences from medical records panel
			Log4J.logp.info("========== In Check Highlight Behavior of System Suggested Evidences from Medical Records Panel ==========");

			for (WebElement we_ICD10 : medicalrecordpnl_webe.lnk_highlighted_sys_med_evidence)
			{

				Thread.sleep(2000);

				while (true)
				{

					if (!we_ICD10.isDisplayed())
					{
						Thread.sleep(1000);
						Common_Lib.scroll_Page_VerySmall(medicalrecordpnl_webe.medical_Dragger, 20);
						Thread.sleep(1000);
						Common_Lib.scroll_Page(medicalrecordpnl_webe.medical_Dragger, 30);
						Thread.sleep(1000);
						if (we_ICD10.isDisplayed())
						{
							Thread.sleep(1000);
							Common_Lib.scroll_Page_VerySmall(medicalrecordpnl_webe.medical_Dragger, 7);
							Thread.sleep(1000);
							we_ICD10.click();
							Thread.sleep(1000);
							break;
						}

					}

					if (we_ICD10.isDisplayed())
					{

						//Thread.sleep(3000);
						//This change made on 17-12-2014
						Thread.sleep(2000);

						we_ICD10.click();

						//Thread.sleep(3000);
						//This change made on 17-12-2014
						Thread.sleep(2000);

						break;
					}

				}
			}
			Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1500);
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Highlight Behavior of System Suggested Evidences from Medical Records Panel ==========");

			//below code block will check behavior of DRG value
			Log4J.logp.info("========== In Check Behavior of DRG Value ==========");
			strDrg_Temp1_ICD10 = viewcase_webe.txt_DRG.getText();
			Thread.sleep(2000);
			strDrg_Temp2_ICD10 = strDrg_Temp1_ICD10.split(",");
			Thread.sleep(1000);
			strDrg_Temp3_ICD10 = strDrg_Temp2_ICD10[0].trim().split(":");
			Thread.sleep(1000);
			iDrgVal_ICD10 = Integer.parseInt(strDrg_Temp3_ICD10[1].trim());
			Thread.sleep(2000);
			if (iDrgVal_ICD10 == 0 && iDrgVal_ICD10 != 999)
			{
				Log4J.logp.info("DRG value is displayed 0");
			}
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior of DRG Value ==========");

			//below code block will check behavior of issues panel
			Log4J.logp.info("========== In Check Behavior of Issues Panel ==========");
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(1000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior of Issues Panel ==========");

			//below code block will check behavior of grouping panel
			Log4J.logp.info("========== In Check Behavior of Grouping Panel ==========");
			groupingpnl_webe.lnk_GroupingPnl.click();
			Thread.sleep(1000);
			groupingpnl_webe.lnk_GroupingPnl.click();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior of Grouping Panel ==========");

			//below code block will check behavior reference panel
			Log4J.logp.info("========== In Check if Reference Panel is Visible ==========");
			bStatus = Common_Lib.checkElementPresent(abstractpnl_webe.lnk_Ref_Encoder);
			if (bStatus == false)
			{
				Log4J.logp.info("Reference panel is not displayed to Supervisor");
				Assert.assertTrue(true, "Reference panel is not displayed to Supervisor");
			}
			else
			{
				Log4J.logp.error("Reference panel is displayed to Supervisor");
				Assert.assertTrue(false, "Reference panel is displayed to Supervisor");
			}
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check if Reference Panel is Visible ==========");

			//below code will check user can't accept/reject or modify code
			Log4J.logp.info("========== In Check Behavior that User can't Accept/Reject or Modify Code ==========");
			Log4J.logp.info("========== Started For Accepted ==========");
			listAccept_Icons_ICD10 = viewcase_webe.lst_acceptcode;
			for (WebElement accept_Icon_ICD10 : listAccept_Icons_ICD10)
			{
				Common_Lib.scroll_Until_FindWebe(codingpnl_webe.coding_Dragger, accept_Icon_ICD10, 30);
				Thread.sleep(1000);

				//temp keep below change
				//Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 5);
				Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 7);

				Thread.sleep(1000);
				accept_Icon_ICD10.click();
				strTempAccept_ICD10 = accept_Icon_ICD10.getAttribute("id");
				//System.out.println(strTempAccept_ICD10);
				Log4J.logp.info(strTempAccept_ICD10);
				if (strTempAccept_ICD10.contains("enabled"))
				{
					Log4J.logp.error("Accept icon is clickable");
					Assert.assertTrue(false, "Accept icon is clickable");
					break;
				}
				else
				{
					Log4J.logp.info("Accept icon is not clickable");
					Assert.assertTrue(true, "Accept icon is not clickable");
				}
			}
			Log4J.logp.info("========== Ended For Accepted ==========");
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("========== Started For Rejected ==========");
			listReject_Icons_ICD10 = viewcase_webe.lst_rejectedcode;
			for (WebElement reject_Icon_ICD10 : listReject_Icons_ICD10)
			{
				Common_Lib.scroll_Until_FindWebe(codingpnl_webe.coding_Dragger, reject_Icon_ICD10, 30);
				Thread.sleep(1000);

				//temp keep below change
				//Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 5);
				Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 7);

				Thread.sleep(1000);
				reject_Icon_ICD10.click();
				strTempReject_ICD10 = reject_Icon_ICD10.getAttribute("id");
				//System.out.println(strTempReject_ICD10);
				Log4J.logp.info(strTempReject_ICD10);
				if (strTempReject_ICD10.contains("enabled"))
				{
					Log4J.logp.error("Reject icon is clickable");
					Assert.assertTrue(false, "Reject icon is clickable");
					break;
				}
				else
				{
					Log4J.logp.info("Reject icon is not clickable");
					Assert.assertTrue(true, "Reject icon is not clickable");
				}
			}
			Log4J.logp.info("========== Ended For Rejected ==========");
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("========== Started For Modified ==========");
			listModify_Icons_ICD10 = viewcase_webe.lst_modifiedcode;
			for (WebElement modify_Icon_ICD10 : listModify_Icons_ICD10)
			{
				Common_Lib.scroll_Until_FindWebe(codingpnl_webe.coding_Dragger, modify_Icon_ICD10, 30);
				Thread.sleep(1000);

				//temp keep below change
				//Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 5);
				Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 7);

				Thread.sleep(1000);
				modify_Icon_ICD10.click();
				strTempModified_ICD10 = modify_Icon_ICD10.getAttribute("id");
				//System.out.println(strTempModified_ICD10);
				Log4J.logp.info(strTempModified_ICD10);
				if (strTempModified_ICD10.contains("enabled"))
				{
					Log4J.logp.error("Modify icon is clickable");
					Assert.assertTrue(false, "Modify icon is clickable");
					break;
				}
				else
				{
					Log4J.logp.info("Modify icon is not clickable");
					Assert.assertTrue(true, "Modify icon is not clickable");
				}
			}
			Log4J.logp.info("========== Ended For Modified ==========");
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior that User can't Accept/Reject or Modify Code ==========");

			//below code block will check if user can raise query or discussion from medical records panel
			Log4J.logp.info("========== In Check Behavior that User can't raise Query or Discussion from Medical Records Panel ==========");
			Thread.sleep(2000);

			//Actions action = new Actions(driver);

			action.moveToElement(common_webe.lnk_sys_evidence.get(0)).contextClick().build().perform();

			//Thread.sleep(3000);
			//This change made on 17-12-2014
			Thread.sleep(2000);

			if ((medicalrecordpnl_webe.lnk_querytophysician.isDisplayed()) || (medicalrecordpnl_webe.lnk_discusswithcolleague.isDisplayed()))
			{
				Log4J.logp.error("User can make changes.");
				Assert.assertTrue(false, "User can make changes.");
			}
			else
			{
				Log4J.logp.info("User can not make changes.");
				Assert.assertTrue(true, "User can not make changes.");
			}
			action.release().build().perform();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior that User can't raise Query or Discussion from Medical Records Panel ==========");

			//below code block will check if user can raise query or discussion from coding panel
			Log4J.logp.info("========== In Check Behavior that User can't raise Query or Discussion from Coding Panel ==========");
			Thread.sleep(2000);

			//Actions action = new Actions(driver);

			action.moveToElement(codingpnl_webe.lst_first_admit_evi_arrow).click().build().perform();
			//Thread.sleep(3000);
			//This change made on 17-12-2014
			Thread.sleep(2000);

			if ((codingpnl_webe.lnk_query_first_admit_evi.isDisplayed()) || (codingpnl_webe.lnk_discuss_first_admit_evi.isDisplayed()))
			{
				Log4J.logp.error("User can make changes.");
				Assert.assertTrue(false, "User can make changes.");
			}
			else
			{
				Log4J.logp.info("User can not make changes.");
				Assert.assertTrue(true, "User can not make changes.");
			}
			action.release().build().perform();
			Thread.sleep(2000);
			Log4J.logp.info("========== Finished Check Behavior that User can't raise Query or Discussion from Coding Panel ==========");
			Log4J.logp.info("========== Ended For ICD-10 Coding Standard ==========");

			landingp_webe.btn_Close.click();
			Thread.sleep(2000);

			Log4J.logp.info("*************** Ending - viewFreshCase ***************");
			Assert.assertTrue(true, "viewFreshCase is passed");
		}

		catch (Exception e)
		{
			Log4J.logp.error("*************** Problem Found - viewFreshCase ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "viewFreshCase is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
			else if (landingp_webe.btn_Close.isDisplayed() == true)
			{
				landingp_webe.btn_Close.click();

				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
		}
	}

	/////////////////////////////////////////////////////////////// Nupur ////////////////////////////////////////////////////////////////

	/**
	 * This method is to check viewcase behavior by supervisor of case for which coding is in onhold status
	 *
	 * @author nchourasiya
	 * @since 17/11/2014
	 * 
	 * */

	@Test(description = " ezCAC_MVP_Reg-2922:Check if Supervisor's View Case behavior for Case for which Coding is in any status is appropriate", priority = 2)
	public static void viewCaseCodingOnhold()
	{
		try
		{
			Log4J.logp.info("********************* Started - veiwCaseCodingOnhold ********************************");
			landingp_webe.lnk_Coding.click();

			Common_Lib.openCase("NC002");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
			groupingpnl_webe.btn_Later.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			landingp_webe.lnk_All.click();

			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("NC002");
			Thread.sleep(2000);
			LandingPage_Lib.click_on_expand();
			Thread.sleep(2000);
			landingp_webe.lnk_ViewCaseVersion1.click();

			Thread.sleep(2000);
			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);

			ViewCase_Lib.commonCheckFor_ViewCase();
			Thread.sleep(2000);
			Log4J.logp.info("********************* Ended - veiwCaseCodingOnhold ********************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - veiwCaseCodingOnhold");
			e.printStackTrace();
			Assert.assertTrue(false, "veiwCaseCodingOnhold is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
			else if (landingp_webe.btn_Close.isDisplayed() == true)
			{
				landingp_webe.btn_Close.click();

				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}

		}

	}

	/**
	 * This method is to check viewcase behavior by supervisor of case for which coding is Completed status
	 * 
	 * @author nchourasiya
	 * @since 17/11/2014
	 * */
	@Test(description = "ezCAC_MVP_Reg-2921:Check if Supervisor's View Case behavior for Case for which Coding is Completed is appropriate.", priority = 3)
	public static void viewCaseCodingCompleted()
	{
		try
		{
			Log4J.logp.info("********************* Started - viewCaseCodingCompleted ********************************");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("NC001");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);

			Thread.sleep(1000);
			landingp_webe.lnk_All.click();

			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("NC002");
			Thread.sleep(2000);
			LandingPage_Lib.click_on_expand();
			Thread.sleep(2000);
			landingp_webe.lnk_ViewCaseVersion1.click();

			Thread.sleep(2000);
			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);
			ViewCase_Lib.commonCheckFor_ViewCase();
			Thread.sleep(2000);
			Log4J.logp.info("********************* Ended - viewCaseCodingCompleted ********************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseCodingCompleted");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseCodingCompleted is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
			else if (landingp_webe.btn_Close.isDisplayed() == true)
			{
				landingp_webe.btn_Close.click();

				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
		}

	}

	/**
	 * This method is to check viewcase behavior by supervisor of case for which Review is in pending status
	 * 
	 * @author nchourasiya
	 * 
	 * @since 18/11/2014
	 * */

	@Test(description = "ezCAC_MVP_Reg-2928:Check if Supervisor's View Case behavior for Case for which Review is in any status is appropriate.", priority = 4)
	public static void viewCaseReviewPending()
	{
		try
		{
			Log4J.logp.info("********************* Started - viewCaseReviewPending ********************************");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc023"); // Login as lisa (Reviewer)
			Thread.sleep(2000);
			Common_Lib.openCase("NC002");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(1000);
			issuepnl_webe.lnk_DiscussWithCol.click();
			IssuePanel_Lib.send_DiscussWithColleague("NC001");
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc003"); // Login as admin(Nupur)
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("NC002");
			Thread.sleep(2000);
			LandingPage_Lib.click_on_expand();
			Thread.sleep(2000);
			landingp_webe.lnk_ViewCaseVersion2.click();
			Thread.sleep(2000);
			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);

			ViewCase_Lib.commonCheckFor_ViewCase();
			Thread.sleep(2000);
			Log4J.logp.info("********************* Ended - viewCaseReviewPending ********************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseReviewPending");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseReviewPending is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
			else if (landingp_webe.btn_Close.isDisplayed() == true)
			{
				landingp_webe.btn_Close.click();

				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
		}

	}

	/**
	 * 
	 * This method is to check viewcase behavior by supervisor of case for which Review is in Billed status
	 * 
	 * @author nchourasiya
	 * 
	 * @since 18/11/2014
	 * */

	@Test(description = " ezCAC_MVP_Reg-2927:Check if Supervisor's View Case behavior for Case for which Review is Billed is appropriate.", priority = 5)
	public static void viewCaseReviewBilled()
	{
		try
		{
			Log4J.logp.info("********************* Started - viewCaseReviewBilled ********************************");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc023"); // Login as lisa (Reviewer)
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			WorkingScreen_Lib.submit_case("NC002");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc003"); // Login as admin(Nupur)
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("NC002");
			Thread.sleep(2000);
			LandingPage_Lib.click_on_expand();
			Thread.sleep(2000);
			landingp_webe.lnk_ViewCaseVersion2.click();
			Thread.sleep(2000);
			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);
			ViewCase_Lib.commonCheckFor_ViewCase();
			Thread.sleep(2000);
			Log4J.logp.info("********************* Ended - viewCaseReviewBilled ********************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseReviewBilled");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseReviewBilled is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
			else if (landingp_webe.btn_Close.isDisplayed() == true)
			{
				landingp_webe.btn_Close.click();

				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
		}

	}

	/**
	 * 
	 * This method is to check viewcase behavior by supervisor of case for which CDI is in onhold status
	 * 
	 * @author nchourasiya
	 * 
	 * @since 18/11/2014
	 * */

	@Test(description = "ezCAC_MVP_Reg-2924:Check if Supervisor's View Case behavior for Case for which CDI is in any status is appropriate.", priority = 6)
	public static void viewCaseCdiOnHold()
	{

		try
		{
			Log4J.logp.info("********************* Started - viewCaseCdiOnHold ********************************");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc024"); // Login as helen (CDI)
			Thread.sleep(2000);
			Common_Lib.openCase("NC002");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
			groupingpnl_webe.btn_Later.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc003"); // Login as admin(Nupur)
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("NC002");
			Thread.sleep(2000);
			LandingPage_Lib.click_on_expand();
			Thread.sleep(2000);
			landingp_webe.lnk_ViewCaseVersion3.click();
			Thread.sleep(2000);
			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);
			ViewCase_Lib.commonCheckFor_ViewCase();
			Thread.sleep(2000);
			Log4J.logp.info("********************* Ended - viewCaseCdiOnHold ********************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseCdiOnHold");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseCdiOnHold is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
			else if (landingp_webe.btn_Close.isDisplayed() == true)
			{
				landingp_webe.btn_Close.click();

				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
		}

	}

	/**
	 * 
	 * This method is to check viewcase behavior by supervisor of case for which CDI is in Complete status
	 * 
	 * @author nchourasiya
	 * 
	 * @since 18/11/2014
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-2923:Check if Supervisor's View Case behavior for Case for which CDI is Completed is appropriate.", priority = 7)
	public static void viewCaseCdiCompleted()
	{
		try
		{
			Log4J.logp.info("********************* Started - viewCaseCdiCompleted ********************************");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc024"); // Login as helen (CDI)
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			WorkingScreen_Lib.submit_case("NC003");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc003"); // Login as admin(Nupur)
			Thread.sleep(2000);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("NC002");
			Thread.sleep(2000);
			LandingPage_Lib.click_on_expand();
			Thread.sleep(2000);
			landingp_webe.lnk_ViewCaseVersion3.click();
			Thread.sleep(2000);
			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);
			ViewCase_Lib.commonCheckFor_ViewCase();
			Thread.sleep(2000);
			Log4J.logp.info("********************* Ended - viewCaseCdiCompleted ********************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseCdiCompleted");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseCdiCompleted is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
			else if (landingp_webe.btn_Close.isDisplayed() == true)
			{
				landingp_webe.btn_Close.click();

				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
		}

	}

	/**
	 * This method is to check that coder should not able to View case details by expanding case grid.
	 * 
	 * @author nchourasiya
	 * @since 18/11/2014
	 * */

	@Test(description = "ezCAC_MVP_Reg-2457:Coder should not be able to View Case details by expanding Case Grid.", priority = 8)
	public static void viewCaseDetailCoder()
	{
		boolean bClickAble;
		try
		{
			Log4J.logp.info("********************* Started - viewCaseDetailCoder ********************************");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(1000);
			Common_Lib.openCase("NC003");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
			groupingpnl_webe.btn_Later.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			LandingPage_Lib.expand_case();
			Thread.sleep(2000);
			Log4J.logp.info(landingp_webe.txt_VersionCoder.getText());
			bClickAble = Common_Lib.verify_ElemnenIsClickableAndClick(landingp_webe.txt_VersionCoder);

			if (bClickAble == true)
			{

				Log4J.logp.info("Not able to veiw case details from landing Page - Test Pass");
				Assert.assertTrue(true, "Not able to click on version");
			}
			else
			{

				Log4J.logp.error("View case sucessfully open");
				Assert.assertTrue(false, "Able to click on version");
			}
			Log4J.logp.info("********************* Ended - viewCaseDetailCoder ********************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseDetailCoder");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseDetailCoder is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
		}

	}

	/**
	 * 
	 * This method is to check that coder should not able to viewcase for his own version from abstract panel.
	 * 
	 * @author nchourasiya
	 * 
	 * @since 19/11/2014
	 * */

	@Test(description = " ezCAC_MVP_Reg-2956:Coder should not be able to View Case for his own version from Abstract Panel.", priority = 9)
	public static void viewCaseCoderFromAbstractPanel()
	{
		String alertmsg;
		boolean custom_alert;
		try
		{
			Log4J.logp.info("********************* Started - viewCaseCoderFromAbstractPanel ********************************");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(1000);
			Common_Lib.openCase("NC003");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
			viewcase_webe.panel_Abstract.click();
			Thread.sleep(1000);
			viewcase_webe.txt_versionFromAbstract.click();
			Thread.sleep(2000);
			alertmsg = "Case already In Progress.";
			Thread.sleep(2000);
			if (common_webe.lbl_customAlertMsg.isDisplayed() == true && common_webe.lbl_customAlertMsg.getText().equalsIgnoreCase(alertmsg))
			{

				Log4J.logp.info(common_webe.lbl_customAlertMsg.getText() + " - Alert Message Found - Test Pass ");
				Assert.assertTrue(true, "Not able to open view case from abstract panel.");
			}
			else
			{

				Log4J.logp.error("Case open sucessfully - Test Fail");
				Assert.assertTrue(false, "Able to open case");
			}

			custom_alert = Common_Lib.IsCustomAlertPresent();
			Log4J.logp.info("Custom alert responce :  " + custom_alert);
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			Log4J.logp.info("********************* Ended - viewCaseCoderFromAbstractPanel ********************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseCoderFromAbstractPanel");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseCoderFromAbstractPanel is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
		}

	}

	/**
	 * This method is to check that Coder should be able to View Case details for Case for which CDI is Completed First.
	 * 
	 * @author nchourasiya
	 * @since 19/11/2014
	 * */

	@Test(description = "ezCAC_MVP_Reg-2431:Coder should be able to View Case details for Case for which CDI is Completed First. " + " ezCAC_MVP_Reg-2935:Check if Coder's View Case behavior for Case for which CDI is Completed First is appropriate.", priority = 10)
	public static void viewCaseCoderForCDICompleted()
	{

		try
		{
			Log4J.logp.info("************************** viewCaseCoderForCDICompleted ******************************");
			//ezCAC_MVP_Reg-2431:Coder should be able to View Case details for Case for which CDI is Completed First.
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc024"); // Login as helen (CDI)
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			WorkingScreen_Lib.submit_case("NC004");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc003"); // Login as admin(Nupur)
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("NC004");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
			viewcase_webe.panel_Abstract.click();
			Thread.sleep(2000);
			viewcase_webe.txt_versionFromAbstract1.click();
			Log4J.logp.info("View Case sucessfully open for CDI compelted Case - Test Pass");
			Thread.sleep(2000);
			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);
			//ezCAC_MVP_Reg-2935:Check if Coder's View Case behavior for Case for which CDI is Completed First is appropriate.
			ViewCase_Lib.commonCheckFor_ViewCase();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			Log4J.logp.info("********************* Ended - viewCaseCoderForCDICompleted ********************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseCoderForCDICompleted");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseCoderForCDICompleted is failed");
		}
		finally
		{
			if (landingp_webe.lbl_UserName.isDisplayed() == true)
			{
				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
			else if (landingp_webe.btn_Close.isDisplayed() == true)
			{
				landingp_webe.btn_Close.click();

				groupingpnl_webe.btn_Later.click();

				Login_Lib.logOut_App();

				Login_Lib.login("nc003");
			}
		}

	}

	/**
	 * This method is to check Coder should not be able to View Case details for Case for which Review is Billed.
	 * 
	 * @author nchourasiya
	 * 
	 * @since 20/11/2014
	 * */

	@Test(description = "ezCAC_MVP_Reg-2441:Coder should not be able to View Case details for Case for which Review is Billed.", priority = 11)
	public static void viewCaseCoderReviewBilled()
	{
		boolean bStatus = false;
		boolean bFinalStatus = false;
		try
		{
			Log4J.logp.info("********************* Started - ViewCaseCoderReviewBilled ********************************");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("NC005");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc023"); // Login as lisa (Reviewer)
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("NC006");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc003");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			bStatus = Common_Lib.openCase("NC005");
			if (bStatus == false)
			{
				Log4J.logp.info("Unable to view details of case. - Test Pass");
				bFinalStatus = true;
			}
			else
			{
				bFinalStatus = false;
			}

			Log4J.logp.info("********************* Ended - ViewCaseCoderReviewBilled ********************************");
			Assert.assertTrue(bFinalStatus, "ViewCaseCoderRoviewBilled is passed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - ViewCaseCoderReviewBilled");
			e.printStackTrace();
			Assert.assertTrue(false, "ViewCaseCoderReviewBilled is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
		}

	}

	/**
	 * This method is to check Coder should not be able to View Case details for Case for which CDI is Completed.
	 * 
	 * @author nchourasiya
	 * 
	 * @since 21/11/2014
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-2885:Coder should not be able to View Case details for Case for which CDI is Completed.", priority = 12)
	public static void viewCaseCoderCDICompleteAfterBilled()
	{
		boolean bStatus = false;
		boolean bFinalStatus = false;
		try
		{
			Log4J.logp.info("********************* Started - viewCaseCoderCDICompleteAfterBilled ********************************");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc024"); // Login as helen (CDI)
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			WorkingScreen_Lib.submit_case("NC007");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc003");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			bStatus = Common_Lib.openCase("NC005");
			if (bStatus == false)
			{
				Log4J.logp.info("Unable to view details of case. - Test Pass");
				bFinalStatus = true;
			}
			else
			{
				bFinalStatus = false;
			}

			Log4J.logp.info("********************* Ended - viewCaseCoderCDICompleteAfterBilled ********************************");
			Assert.assertTrue(bFinalStatus, "viewCaseCoderCDICompleteAfterBilled is passed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseCoderCDICompleteAfterBilled");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseCoderCDICompleteAfterBilled is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
		}

	}

	/**
	 * This method is to check that CDI should not be able to View Case details by expanding Case Grid
	 * 
	 * @author nchourasiya
	 * @since 21/11/2014
	 * */
	@Test(description = "ezCAC_MVP_Reg-2458:CDI should not be able to View Case details by expanding Case Grid.", priority = 13)
	public static void viewCaseDetailCDI()
	{
		boolean bClickAble;
		try
		{
			Log4J.logp.info("********************* Started - viewCaseDetailCDI ********************************");
			Thread.sleep(2000);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			Common_Lib.openCase("NC006");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
			groupingpnl_webe.btn_Later.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			LandingPage_Lib.expand_case();
			Thread.sleep(2000);
			Log4J.logp.info(landingp_webe.txt_VersionCoder.getText());
			bClickAble = Common_Lib.verify_ElemnenIsClickableAndClick(landingp_webe.txt_VersionCoder);
			if (bClickAble == true)
			{
				Log4J.logp.info("Not able to veiw case details from landing Page - Test Pass");
				Assert.assertTrue(true, "Not able to click on version");
			}
			else
			{
				Log4J.logp.error("View case sucessfully open");
				Assert.assertTrue(false, "Able to click on version");
			}
			Log4J.logp.info("********************* Ended - viewCaseDetailCDI ********************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseDetailCDI");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseDetailCDI is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
		}

	}

	/**
	 * 
	 * This method is to Check CDI should not be able to View Case for his own version from Abstract Panel.
	 * 
	 * @author nchourasiya
	 * @since 21/11/2014
	 * */

	@Test(description = "ezCAC_MVP_Reg-2957:CDI should not be able to View Case for his own version from Abstract Panel.", priority = 14)
	public static void viewCaseCdiFromAbstract()
	{
		String alertmsg;
		boolean custom_alert;
		try
		{
			Log4J.logp.info("********************* Started - viewCaseCdiFromAbstract ********************************");
			Thread.sleep(2000);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(1000);
			Common_Lib.openCase("NC006");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
			viewcase_webe.panel_Abstract.click();
			Thread.sleep(1000);
			viewcase_webe.txt_versionFromAbstract.click();
			Thread.sleep(3000);
			alertmsg = "Case already In Progress.";
			if (common_webe.lbl_customAlertMsg.isDisplayed() == true && common_webe.lbl_customAlertMsg.getText().equalsIgnoreCase(alertmsg))
			{

				Log4J.logp.info(common_webe.lbl_customAlertMsg.getText() + " - Alert Message Found - Test Pass ");
				Assert.assertTrue(true, "Not able to open view case from abstract panel.");
			}
			else
			{

				Log4J.logp.error("Case open sucessfully - Test Fail");
				Assert.assertTrue(false, "Able to open case");
			}

			custom_alert = Common_Lib.IsCustomAlertPresent();
			Log4J.logp.info("Custom alert responce :  " + custom_alert);
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();

			Log4J.logp.info("********************* Ended - viewCaseCdiFromAbstract ********************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseCdiFromAbstract");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseCdiFromAbstract is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
		}

	}

	/**
	 * This method is to check CDI should not be able to View Case details for Case for which Coding is Completed,but not Billed.
	 * 
	 * @author nchourasiya
	 * @since 21/11/2014
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-2442:CDI should not be able to View Case details for Case for which Coding is Completed,but not Billed.", priority = 15)
	public static void viewCaseCdiCodingComplete()
	{
		boolean bStatus = false;
		boolean bFinalStatus = false;
		try
		{
			Log4J.logp.info("********************* Sarted - viewCaseCdiCodingComplete ********************************");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("NC002"); // susan (Coder)
			Thread.sleep(1000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("NC008");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc003"); // nupur 
			Thread.sleep(2000);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			bStatus = Common_Lib.openCase("NC007");
			if (bStatus == false)
			{
				Log4J.logp.info("Unable to view details of case. - Test Pass");
				bFinalStatus = true;
			}
			else
			{
				bFinalStatus = false;
			}

			Log4J.logp.info("********************* Ended - viewCaseCdiCodingComplete ********************************");
			Assert.assertTrue(bFinalStatus, "viewCaseCdiCodingComplete is passed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseCdiCodingComplete");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseCdiCodingComplete is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
		}

	}

	/**
	 * This method is to Check if CDI's View Case behavior for Case for which Coding is Completed=Billed First is appropriate.
	 * 
	 * @author nchourasiya
	 * @since 21/11/2014
	 * 
	 * */

	@Test(description = "ezCAC_MVP_Reg-2936:Check if CDI's View Case behavior for Case for which Coding is Completed=Billed First is appropriate + ezCAC_MVP_Reg-2937:Check if CDI's View Case behavior for Case for which Review is Billed is appropriate.", priority = 16)
	public static void viewCaseCdiForBilledCase()
	{
		boolean bStatus;
		boolean bFinalStatus;
		try
		{
			Log4J.logp.info("********************* Started - viewCaseCdiForBilledCase ********************************");

			//ezCAC_MVP_Reg-2936:Check if CDI's View Case behavior for Case for which Coding is Completed=Billed First is appropriate
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc023"); // Login as lisa (Reviewer)
			landingp_webe.lnk_Review.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("NC009");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc003"); // nupur
			Thread.sleep(2000);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			Common_Lib.openCase("NC007");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
			Log4J.logp.info("===============================Case 1=====================================");
			viewcase_webe.panel_Abstract.click();
			Thread.sleep(2000);
			viewcase_webe.txt_versionFromAbstract1.click();
			Thread.sleep(2000);
			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);
			bStatus = ViewCase_Lib.commonCheckFor_ViewCase();
			if (bStatus == true)
			{
				Log4J.logp.info("Check if CDI's View Case behavior for Case for which Coding is Completed=Billed First is appropriate -Test Pass");
			}
			Log4J.logp.info("===============================Case 2=====================================");

			//ezCAC_MVP_Reg-2937:Check if CDI's View Case behavior for Case for which Review is Billed is appropriate.
			viewcase_webe.txt_versionFromAbstract2.click();
			Thread.sleep(2000);
			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);
			bStatus = ViewCase_Lib.commonCheckFor_ViewCase();
			if (bStatus == true)
			{
				Log4J.logp.info("Check if CDI's View Case behavior for Case for which Review is Billed is appropriate. -Test Pass");
			}
			Thread.sleep(2000);
			viewcase_webe.panel_Abstract.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			if (bStatus == true)
			{
				bFinalStatus = true;
			}

			else
			{
				bFinalStatus = false;
			}

			Log4J.logp.info("********************* Ended - viewCaseCdiForBilledCase ********************************");
			Assert.assertTrue(bFinalStatus, "viewCaseCdiForBilledCase is passed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseCdiForBilledCase");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseCdiForBilledCase is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
		}

	}

	/**
	 * This method is to check CDI should not be able to View Case details for Case for Coding is Completed and Review is Billed.
	 * 
	 * @author nchourasiya
	 * @since 21/11/2014
	 * */

	@Test(description = "ezCAC_MVP_Reg-2459:CDI should not be able to View Case details for Case for which Review is Billed. + ezCAC_MVP_Reg-2886:CDI should not be able to View Case details for Case for which Coding is Completed=Billed.", priority = 17)
	public static void viewCaseCdiComplete()
	{
		boolean bStatus;
		boolean bFinalStatus;
		try
		{
			Log4J.logp.info("********************* Started - viewCaseCdiComplete ********************************");
			Thread.sleep(2000);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("NC010");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			bStatus = Common_Lib.openCase("NC007");
			if (bStatus == false)
			{
				Log4J.logp.info("Unable to view details of case. - Test Pass");
				bFinalStatus = true;
			}
			else
			{
				bFinalStatus = false;
			}

			Log4J.logp.info("********************* Ended - viewCaseCdiComplete ********************************");
			Assert.assertTrue(bFinalStatus, "viewCaseCdiCodingComplete is passed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseCdiComplete");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseCdiComplete is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
		}

	}

	/**
	 * This method is to check that Reviewer should not be able to View Case details by expanding Case Grid.
	 * 
	 * @author nchourasiya
	 * @since 21/11/2014
	 * */

	@Test(description = "ezCAC_MVP_Reg-2887:Reviewer should not be able to View Case details by expanding Case Grid.", priority = 18)
	public static void viewCaseDetailReviewer()
	{
		boolean bClickAble;
		try
		{
			Log4J.logp.info("********************* Started - viewCaseDetailReviewer ********************************");
			Thread.sleep(2000);
			//Login_Lib.logOut_App();
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("NC011");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 20);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc023"); // Login as lisa (Reviewer)
			Thread.sleep(2000);
			landingp_webe.lnk_Review.click();
			Thread.sleep(2000);
			Common_Lib.openCase("NC004");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
			groupingpnl_webe.btn_Later.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			LandingPage_Lib.expand_case();
			Thread.sleep(2000);

			Log4J.logp.info(landingp_webe.txt_VersionCoder.getText());

			bClickAble = Common_Lib.verify_ElemnenIsClickableAndClick(landingp_webe.txt_VersionCoder);

			if (bClickAble == true)
			{
				Log4J.logp.info("Not able to veiw case details from landing Page - Test Pass");
				Assert.assertTrue(true, "Not able to click on version");
			}
			else
			{
				Log4J.logp.error("View case sucessfully open");
				Assert.assertTrue(false, "able to click on version");
			}
			Log4J.logp.info("********************* Ended - viewCaseDetailReviewer ********************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseDetailReviewer");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseDetailReviewer is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
		}

	}

	/**
	 * This method is to check that Reviewer should not be able to View Case for his own version from Abstract Panel.
	 * 
	 * @author nchourasiya
	 * @since 21/11/2014
	 * */

	@Test(description = "ezCAC_MVP_Reg-2958:Reviewer should not be able to View Case for his own version from Abstract Panel.", priority = 19)
	public static void viewCaseReviewerFromAbstractPanel()
	{
		String alertmsg;
		boolean custom_alert;
		try
		{
			Log4J.logp.info("********************* Started - viewCaseReviewerFromAbstractPanel ********************************");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc023"); // Login as lisa (Reviewer)
			Thread.sleep(2000);
			landingp_webe.lnk_Review.click();
			Thread.sleep(2000);
			Common_Lib.openCase("NC004");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
			viewcase_webe.panel_Abstract.click();
			Thread.sleep(1000);
			viewcase_webe.txt_versionFromAbstract3.click();
			Thread.sleep(2000);
			alertmsg = "Case already In Progress.";
			Thread.sleep(2000);
			if (common_webe.lbl_customAlertMsg.isDisplayed() == true && common_webe.lbl_customAlertMsg.getText().equalsIgnoreCase(alertmsg))
			{

				Log4J.logp.info(common_webe.lbl_customAlertMsg.getText() + " - Alert Message Found - Test Pass ");
				Assert.assertTrue(true, "Not able to open view case from abstract panel.");
			}
			else
			{

				Log4J.logp.error("Case open sucessfully - Test Fail");
				Assert.assertTrue(false, "Able to open case");
			}

			custom_alert = Common_Lib.IsCustomAlertPresent();
			Log4J.logp.info("Custom alert responce :  " + custom_alert);
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();

			Log4J.logp.info("********************* Ended - viewCaseCoderFromAbstractPanel ********************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseReviewerFromAbstractPanel");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseReviewerFromAbstractPanel is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
		}

	}

	/**
	 * This method is to check if Reviewer's View Case behavior for Case for which Coding CDI and Coding are Completed.
	 * 
	 * @author nchourasiya
	 * @since 24/11/2014
	 * */
	@Test(description = " ezCAC_MVP_Reg-2938:Check if Reviewer's View Case behavior for Case for which Coding Completed,Not Billed is appropriate. + ezCAC_MVP_Reg-2939:Check if Reviewer's View Case behavior for Case for which CDI and Coding are Completed.", priority = 20)
	public static void viewCaseReviewerCompleteCase()
	{
		boolean bStatus;
		boolean bFinalStatus;
		try
		{
			Log4J.logp.info("********************* Ended - viewCaseReviewerCompleteCase ********************************");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc023"); // Login as lisa (Reviewer)
			Thread.sleep(2000);
			landingp_webe.lnk_Review.click();
			Thread.sleep(2000);
			Common_Lib.openCase("NC004");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
			Log4J.logp.info("===============================  viewCaseReviewerCompleteCase    Case 1  =====================================");
			viewcase_webe.panel_Abstract.click();
			Thread.sleep(2000);
			viewcase_webe.txt_versionFromAbstract1.click();
			Thread.sleep(2000);
			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);
			bStatus = ViewCase_Lib.commonCheckFor_ViewCase();
			if (bStatus == true)
			{
				Log4J.logp.info("Check if Reviewer's View Case behavior for Case for which CDI and Coding are Completed.-Test Pass");
			}
			Log4J.logp.info("===============================  viewCaseReviewerCompleteCase   Case 2  =====================================");

			//ezCAC_MVP_Reg-2937:Check if CDI's View Case behavior for Case for which Review is Billed is appropriate.
			viewcase_webe.txt_versionFromAbstract2.click();
			Thread.sleep(2000);
			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);
			bStatus = ViewCase_Lib.commonCheckFor_ViewCase();
			if (bStatus == true)
			{
				Log4J.logp.info("Check if Reviewer's View Case behavior for Case for which Coding Completed,Not Billed is appropriate. -Test Pass");
			}
			Thread.sleep(2000);
			viewcase_webe.panel_Abstract.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			if (bStatus == true)
			{
				bFinalStatus = true;
			}

			else
			{
				bFinalStatus = false;
			}

			Log4J.logp.info("********************* Ended - viewCaseReviewerCompleteCase ********************************");
			Assert.assertTrue(bFinalStatus, "viewCaseReviewerCompleteCase is passed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseReviewerCompleteCase");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseReviewerCompleteCase is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
		}

	}

	/**
	 * This method is to check that Reviewer should not be able to View Case details for Case for which CDI is Completed.
	 * 
	 * @author nchourasiya
	 * @since 24/11/2014
	 * */
	@Test(description = " ezCAC_MVP_Reg-2443:Reviewer should not be able to View Case details for Case for which CDI is Completed.", priority = 21)
	public static void veiwCaseReviewerCdiComplete()
	{
		String text = null;
		try
		{
			Log4J.logp.info("********************* Started - veiwCaseReviewerCdiComplete ********************************");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc003"); // Login as Nupur (Cdi)
			Thread.sleep(2000);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("NC012");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc023"); // Login as lisa (Reviewer)
			Thread.sleep(2000);
			SearchCriteria_Lib.search_Case("NC006");
			Thread.sleep(2000);
			text = "No data available in table";
			Thread.sleep(2000);
			if (landingp_webe.tbl_FirstRowSearch.isDisplayed() == true && landingp_webe.tbl_FirstRowSearch.getText().equalsIgnoreCase(text))
			{

				Log4J.logp.info(landingp_webe.tbl_FirstRowSearch.getText() + " - Text Found on searching - Test Pass ");
				Assert.assertTrue(true, "Cdi complete case not found in Reviewer's Search.");
			}
			else
			{

				Log4J.logp.error("Case open sucessfully - Test Fail");
				Assert.assertTrue(false, "Cdi complete case found in Reviewer's Search");
			}

			Log4J.logp.info("********************* Ended - veiwCaseReviewerCdiComplete ********************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - veiwCaseReviewerCdiComplete");
			e.printStackTrace();
			Assert.assertTrue(false, "veiwCaseReviewerCdiComplete is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
		}

	}

	/**
	 * This method is to Check if Physician can able to viewcase for all roles and View Case behavior for Coder/CDI/Reviewer Role is appropriate
	 * 
	 * @author nchourasiya
	 * @since 24/11/2014
	 * */

	@Test(description = " ezCAC_MVP_Reg-1392:Verify that physician can be able to view case for all user applicable user roles. +  ezCAC_MVP_Reg-2929:Check if Physician's View Case behavior for Coder/CDI/Reviewer Role is appropriate", priority = 22)
	public static void viewCasePhysicanForAllRole()
	{
		String currentwindow;
		try
		{
			Log4J.logp.info("********************* Started - viewCasePhysicanForAllRole ********************************");
			Log4J.logp.info("========= Coder ========");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("NC008");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
			common_webe.lnk_sys_evidence.get(0).click();
			Thread.sleep(2000);
			Common_Lib.rightclick_system_sugggested_evidence();

			medicalrecordpnl_webe.lnk_querytophysician.click();

			IssuePanel_Lib.send_QueryToPhysician("NC001");
			Thread.sleep(1000);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);

			Log4J.logp.info("Issue Panel Closed");
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			Login_Lib.logOut_App();

			//Following code will login as a physician and view case
			Login_Lib.login("NC025");
			Thread.sleep(2000);

			MessageCenter_Lib.find_message("NC001", "open");
			Thread.sleep(2000);
			currentwindow = driver.getWindowHandle();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(messagecenter_webe.lnk_ViewCase));
			messagecenter_webe.lnk_ViewCase.click();
			Thread.sleep(2000);

			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);
			ViewCase_Lib.commonCheckFor_ViewCase();
			driver.switchTo().window(currentwindow);

			messagecenter_webe.btn_Close.click();

			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);

			Login_Lib.logOut_App(); // phy logout
			Thread.sleep(2000);

			Login_Lib.login("nc003"); // nupur coder

			landingp_webe.lnk_Coding.click();

			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("NC013");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Log4J.logp.info("=========== Reviewer ========");
			Login_Lib.login("nc023"); // Login as lisa (Reviewer)
			Thread.sleep(2000);
			Common_Lib.openCase("NC008");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
			common_webe.lnk_sys_evidence.get(0).click();
			Thread.sleep(2000);
			Common_Lib.rightclick_system_sugggested_evidence();

			medicalrecordpnl_webe.lnk_querytophysician.click();

			IssuePanel_Lib.send_QueryToPhysician("NC001");
			Thread.sleep(1000);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);

			Log4J.logp.info("Issue Panel Closed");
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			Login_Lib.logOut_App();

			//Following code will login as a physician and view case
			Login_Lib.login("NC025");
			Thread.sleep(2000);

			MessageCenter_Lib.find_message("NC002", "open");
			Thread.sleep(2000);

			currentwindow = driver.getWindowHandle();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(messagecenter_webe.lnk_ViewCase));
			messagecenter_webe.lnk_ViewCase.click();
			Thread.sleep(2000);

			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);
			ViewCase_Lib.commonCheckFor_ViewCase();
			driver.switchTo().window(currentwindow);

			messagecenter_webe.btn_Close.click();

			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			Login_Lib.logOut_App(); // phy logout
			Thread.sleep(2000);

			Login_Lib.login("nc023"); // Login as lisa (Reviewer)
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("NC014");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Login_Lib.logOut_App();
			Log4J.logp.info("========== CDI =============");
			Thread.sleep(2000);
			Login_Lib.login("nc024"); // Login as helen (CDI)

			Thread.sleep(2000);
			Common_Lib.openCase("NC008");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

			common_webe.lnk_sys_evidence.get(0).click();
			Thread.sleep(2000);
			Common_Lib.rightclick_system_sugggested_evidence();

			medicalrecordpnl_webe.lnk_querytophysician.click();

			IssuePanel_Lib.send_QueryToPhysician_CDI_withoutEvidence("NC001", "");
			Thread.sleep(1000);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);

			Log4J.logp.info("Issue Panel Closed");
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			Login_Lib.logOut_App();

			//Following code will login as a physician and view case
			Login_Lib.login("NC025");
			Thread.sleep(2000);

			MessageCenter_Lib.find_message("NC003", "open");
			Thread.sleep(2000);

			currentwindow = driver.getWindowHandle();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(messagecenter_webe.lnk_ViewCase));
			messagecenter_webe.lnk_ViewCase.click();
			Thread.sleep(2000);

			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);
			ViewCase_Lib.commonCheckFor_ViewCase();
			driver.switchTo().window(currentwindow);

			messagecenter_webe.btn_Close.click();

			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);

			Log4J.logp.info("********************* Ended - viewCasePhysicanForAllRole ********************************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCasePhysicanForAllRole");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCasePhysicanForAllRole is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
		}

	}

	/**
	 * This method is to verify that Physician can not View Case after case is reassigned to another user.
	 * 
	 * @author nchourasiya
	 * @since 25/11/2014
	 * */

	@Test(description = "ezCAC_MVP_Reg-2945:To verify that Physician can not View Case after case is reassigned to another user.", priority = 23)
	public static void viewCasePhysicianCaseReassign()
	{

		boolean bStatus = false;
		try
		{
			Log4J.logp.info("********************* Ended - viewCasePhysicianCaseReassign ********************************");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc024"); // Login as helen (CDI)
			Thread.sleep(2000);
			Common_Lib.openCase("NC009");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
			common_webe.lnk_sys_evidence.get(0).click();
			Thread.sleep(2000);
			Common_Lib.rightclick_system_sugggested_evidence();

			medicalrecordpnl_webe.lnk_querytophysician.click();

			IssuePanel_Lib.send_QueryToPhysician_CDI_withoutEvidence("NC002", "");
			Thread.sleep(1000);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);

			Log4J.logp.info("Issue Panel Closed");
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			Login_Lib.logOut_App();
			Thread.sleep(2000);

			Login_Lib.login("nc003");

			landingp_webe.lnk_All.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 5);
			ManualAllocation_Lib.assignCase("NC009", "Sandra Martin"); // Case Assigned to sandra
			Thread.sleep(2000);
			Login_Lib.logOut_App();

			//Following code will login as a physician and view case
			Login_Lib.login("NC025");
			Thread.sleep(2000);

			MessageCenter_Lib.find_message("NC003", "open");
			Thread.sleep(2000);

			boolean bcheck = Common_Lib.checkElementPresent(messagecenter_webe.lnk_ViewCase);

			if (bcheck == false)
			{
				Log4J.logp.info("Message is locked - Test Pass ");
				bStatus = true;
			}
			else
			{
				Log4J.logp.error("Message is not locked - Test Fail ");
				bStatus = false;
			}

			Log4J.logp.info("********************* Ended - viewCasePhysicianCaseReassign ********************************");
			Assert.assertTrue(bStatus, "Message is Locked.");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCasePhysicianCaseReassign");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCasePhysicianCaseReassign is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
		}

	}

	/**
	 * This method is to verify that User can view case from message center.
	 * 
	 * @author nchourasiya
	 * @since 26/11/2014
	 * */

	@Test(description = "ezCAC_MVP_Reg-2943:To verify that User can view case from message center + ezCAC_MVP_Reg-2950:To verify that User can view case from message center", priority = 24)
	public static void viewCaseUserFromMessageCenter()
	{

		String currentwindow;
		try
		{
			Log4J.logp.info("********************* Stared - viewCaseUserFromMessageCenter ********************************");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("NC026"); // sandra (CDI)
			Thread.sleep(2000);
			Log4J.logp.info("========================= Case 1 =============================");
			Thread.sleep(2000);
			Common_Lib.openCase("NC009");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

			common_webe.lnk_sys_evidence.get(0).click();
			Thread.sleep(2000);
			Common_Lib.rightclick_system_sugggested_evidence();
			Thread.sleep(2000);
			medicalrecordpnl_webe.lnk_querytophysician.click();

			IssuePanel_Lib.send_QueryToPhysician_CDI_withoutEvidence("NC004", "");
			Thread.sleep(1000);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);

			Log4J.logp.info("Issue Panel Closed");
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			MessageCenter_Lib.find_message("NC004", "open");
			Thread.sleep(2000);

			currentwindow = driver.getWindowHandle();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(messagecenter_webe.lnk_ViewCase));
			messagecenter_webe.lnk_ViewCase.click();
			Thread.sleep(2000);

			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);
			ViewCase_Lib.commonCheckFor_ViewCase();
			driver.switchTo().window(currentwindow);

			messagecenter_webe.btn_Close.click();

			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);

			Log4J.logp.info("========================= Case 2 =============================");

			landingp_webe.lnk_Cases.click();

			Thread.sleep(2000);
			Common_Lib.openCase("NC009");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			issuepnl_webe.lnk_DiscussWithCol.click();
			IssuePanel_Lib.send_DiscussWithColleague("NC002");
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(3000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			MessageCenter_Lib.find_message("NC005", "open");
			Thread.sleep(2000);

			messagecenter_webe.lnk_ViewConversation.click();
			currentwindow = driver.getWindowHandle();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(messagecenter_webe.lnk_ViewCase));
			messagecenter_webe.lnk_ViewCase.click();
			Thread.sleep(2000);

			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);
			ViewCase_Lib.commonCheckFor_ViewCase();
			driver.switchTo().window(currentwindow);
			messagecenter_webe.lnk_CollpaseConversation.click();
			Thread.sleep(1000);
			messagecenter_webe.btn_Close.click();

			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			Log4J.logp.info("********************* Ended - viewCaseUserFromMessageCenter *****************************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseUserFromMessageCenter");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseUserFromMessageCenter is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
		}

	}

	/**
	 * This method is to verify that Colleague can not View Case after case is reassigned to another user
	 * 
	 * @author nchourasiya
	 * @since 26/11/2014
	 * */

	@Test(description = "ezCAC_MVP_Reg-2951:To verify that Colleague can not View Case after case is reassigned to another user.", priority = 25)
	public static void viewCaseColleagueCaseReassign()
	{
		boolean bStatus = false;
		try
		{
			Log4J.logp.info("********************* Started - viewCaseColleagueCaseReassign ****************************");
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("NC026"); // sandra (CDI)
			Thread.sleep(2000);
			Common_Lib.openCase("NC009");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			issuepnl_webe.lnk_DiscussWithCol.click();
			IssuePanel_Lib.send_DiscussWithColleague("NC003");
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("nc003");
			landingp_webe.lnk_All.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 5);
			ManualAllocation_Lib.assignCase("NC009", "Helen Harris"); // Case Assigned to Helen Harris
			Thread.sleep(2000);
			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("NC002");
			Thread.sleep(2000);
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			MessageCenter_Lib.find_message("NC006", "open");
			Thread.sleep(2000);

			boolean bcheck = Common_Lib.checkElementPresent(messagecenter_webe.lnk_ViewCase);

			if (bcheck == false)
			{
				Log4J.logp.info("Message is locked - Test Pass ");
				bStatus = true;
			}
			else
			{
				Log4J.logp.error("Message is not locked - Test Fail ");
				bStatus = false;
			}

			Log4J.logp.info("********************* Ended - viewCaseColleagueCaseReassign ***************************");
			Assert.assertTrue(bStatus, "Message is Locked.");

		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseColleagueCaseReassign");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseColleagueCaseReassign is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
		}

	}

	/**
	 * This method is to Verify that all users are able to view case for all users for Discuss with Colleague functionality and View Case behavior for
	 * Coder/CDI/Reviewer user is appropriate.
	 * 
	 * @author nchourasiya
	 * @since
	 * */

	@Test(description = " ezCAC_MVP_Reg-2556:Verify that all users are able to view case for all users for Discuss with Colleague functionality. + ezCAC_MVP_Reg-2934:Check if Another user (Colleague)'s View Case behavior for Coder/CDI/Reviewer user is appropriate.", priority = 26)
	public static void viewCaseDiscussWithColleagueForAllRole()
	{

		String currentwindow;
		try
		{
			Log4J.logp.info("********************* Started - viewCaseDiscussWithColleagueForAllRole *****************************");

			Log4J.logp.info("=================================== Coder =================================");

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("NC010");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			issuepnl_webe.lnk_DiscussWithCol.click();
			IssuePanel_Lib.send_DiscussWithColleague("NC004");
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			Login_Lib.logOut_App();

			Login_Lib.login("NC002"); //  login as Susan
			Thread.sleep(2000);

			Thread.sleep(2000);
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			MessageCenter_Lib.find_message("NC007", "open");
			Thread.sleep(2000);

			messagecenter_webe.lnk_ViewConversation.click();
			currentwindow = driver.getWindowHandle();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(messagecenter_webe.lnk_ViewCase));
			messagecenter_webe.lnk_ViewCase.click();
			Thread.sleep(2000);

			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);
			ViewCase_Lib.commonCheckFor_ViewCase();
			driver.switchTo().window(currentwindow);
			messagecenter_webe.lnk_CollpaseConversation.click();
			Thread.sleep(1000);
			messagecenter_webe.btn_Close.click();

			Thread.sleep(2000);

			Login_Lib.logOut_App(); // logout susan 
			Thread.sleep(2000);

			Login_Lib.login("nc003"); //Login as nupur 

			landingp_webe.lnk_Coding.click();

			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("NC015");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Login_Lib.logOut_App(); // logout nupur 
			Thread.sleep(2000);

			Log4J.logp.info("=============================== Reviewer ===================================");
			Login_Lib.login("nc023"); // Login as lisa (Reviewer)
			Thread.sleep(2000);
			Common_Lib.openCase("NC010");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			issuepnl_webe.lnk_DiscussWithCol.click();
			IssuePanel_Lib.send_DiscussWithColleague("NC005");
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			Login_Lib.logOut_App(); // Log out as lisa (Reviewer)

			Login_Lib.login("NC002");// Login as susan
			Thread.sleep(2000);

			Thread.sleep(2000);
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			MessageCenter_Lib.find_message("NC008", "open");
			Thread.sleep(2000);

			messagecenter_webe.lnk_ViewConversation.click();
			currentwindow = driver.getWindowHandle();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(messagecenter_webe.lnk_ViewCase));
			messagecenter_webe.lnk_ViewCase.click();
			Thread.sleep(2000);

			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);
			ViewCase_Lib.commonCheckFor_ViewCase();
			driver.switchTo().window(currentwindow);
			messagecenter_webe.lnk_CollpaseConversation.click();
			Thread.sleep(1000);
			messagecenter_webe.btn_Close.click();

			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);

			Login_Lib.logOut_App(); // logout as Susan 
			Thread.sleep(2000);

			Login_Lib.login("nc023"); // Login as lisa (Reviewer)
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("NC016");
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 10);
			Login_Lib.logOut_App();// Logout as lisa (Reviewer)
			Log4J.logp.info("==================================== CDI ============================================");
			Thread.sleep(2000);
			Login_Lib.login("nc024"); // Login as helen (CDI)

			Thread.sleep(2000);
			Common_Lib.openCase("NC010");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "visibility", 20);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			issuepnl_webe.lnk_DiscussWithCol.click();
			IssuePanel_Lib.send_DiscussWithColleague("NC006");
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			Login_Lib.logOut_App();// Logout as helen (CDI)

			Login_Lib.login("NC002"); //  Login as Susan
			Thread.sleep(2000);

			Thread.sleep(2000);
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			MessageCenter_Lib.find_message("NC009", "open");
			Thread.sleep(2000);

			messagecenter_webe.lnk_ViewConversation.click();
			currentwindow = driver.getWindowHandle();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(messagecenter_webe.lnk_ViewCase));
			messagecenter_webe.lnk_ViewCase.click();
			Thread.sleep(2000);

			driver.switchTo().frame("iFrmviewCaseElement");
			Thread.sleep(2000);
			ViewCase_Lib.commonCheckFor_ViewCase();
			driver.switchTo().window(currentwindow);
			messagecenter_webe.lnk_CollpaseConversation.click();
			Thread.sleep(1000);
			messagecenter_webe.btn_Close.click();

			Thread.sleep(1000);

			Log4J.logp.info("****************** Ended - viewCaseDiscussWithColleagueForAllRole ***********************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - viewCaseDiscussWithColleagueForAllRole");
			e.printStackTrace();
			Assert.assertTrue(false, "viewCaseDiscussWithColleagueForAllRole is failed");
		}
		finally
		{
			Login_Lib.logOut_App();

			Login_Lib.login("nc003");
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
			Login_Lib.logIn_App(DriverTestNG.username, DriverTestNG.password);

			Log4J.logp.info("In AfterClass for ViewCase");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
