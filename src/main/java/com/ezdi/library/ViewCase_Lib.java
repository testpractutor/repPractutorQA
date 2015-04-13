package com.ezdi.library;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.webelements.AbstractPnl_WebE;
import com.ezdi.webelements.CodingPnl_WebE;
import com.ezdi.webelements.Comman_WebE;
import com.ezdi.webelements.GroupinPnl_WebE;
import com.ezdi.webelements.IssuePnl_WebE;
import com.ezdi.webelements.LandingP_WebE;
import com.ezdi.webelements.MedicalRecordPnl_WebE;
import com.ezdi.webelements.SearchCriteria_WebE;
import com.ezdi.webelements.ViewCase_WebE;

public class ViewCase_Lib
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

	/**
	 * Common test for case in view case frame
	 * 
	 * @author nchourasiya
	 * @since 20/11/2014
	 * 
	 * */

	public static boolean commonCheckFor_ViewCase()
	{

		String getActualText_ICD9 = null;
		String getExpectedText_ICD9 = null;

		String getActualText_ICD10 = null;
		String getExpectedText_ICD10 = null;
		List<WebElement> listDocs;

		boolean bAddAdmitCode;
		boolean bAddDiagonsisCode;
		boolean bAddProcedureCode;
		List<WebElement> listAccept_Icons, listReject_Icons, listModify_Icons;
		String strTempAccept, strTempReject, strTempModified;

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

			Log4J.logp.info("-----------------------------Started: commonCheckFor_ViewCase ------------------------------");

			Log4J.logp.info("================ Started : commonCheckFor_ViewCase- ICD9 ====================");

			Log4J.logp.info("User should be able to Expand and Collapse Patient Demographic Panel");

			Common_Lib.waitForObject(viewcase_webe.panel_PatientDemographic, "visibility", 8);

			ViewCase_Lib.expandAndCollapse(viewcase_webe.panel_PatientDemographic, "ExpandAndCollapse", "Demographic Panel");

			Log4J.logp.info("User should be able to Expand and Navigation Menu - Medical Record Panel");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lst_LeftNavigationMenu, "Expand", "Navigation Menu - Medical Record Panel");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_dock, "Expand", "Dock");

			listDocs = viewcase_webe.lst_leftNavigtionMenuItem;
			for (WebElement temp : listDocs)
			{
				temp.click();
				Thread.sleep(2000);
			}

			Common_Lib.waitForObject(viewcase_webe.lnk_unDock, "clickable", 4);

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_unDock, "Collapse", "Undock");

			Log4J.logp.info("Scroll Up");
			Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1000);
			Thread.sleep(2000);

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lst_rightnavigationMenu, "Expand", "Navigation Menu -  Coding Panel");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_diagnosis, "Expand", "Link Diagnosis");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_navadmittingdiagnosis, "Click", "Admiting Diagonsis");

			Log4J.logp.info("Coder's Code Template :User should not be able to Expand and Collapse it.");
			bAddAdmitCode = Common_Lib.checkElementPresent(codingpnl_webe.lnk_addAdmit_DignosisCode_ICD9);
			bAddDiagonsisCode = Common_Lib.checkElementPresent(codingpnl_webe.lnk_addDignosisCode_ICD9);
			bAddProcedureCode = Common_Lib.checkElementPresent(codingpnl_webe.lnk_addProcedureCode_ICD9);
			if (bAddAdmitCode == false)
			{
				Log4J.logp.info("Not Able to add Admiting code. -Test Pass");

			}
			else
			{
				Log4J.logp.error("Able to add Admiting Code. -Test Fail");

			}

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_navprincipaldiagnosis, "Click", "Principal Diagonsis");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_navsecondarydiagnosis, "Click", "Secondary Diagonsis");

			if (bAddDiagonsisCode == false)
			{
				Log4J.logp.info("Not Able to add diagonsis Code. -Test Pass");
			}
			else
			{
				Log4J.logp.error("Able to add diagonsis Code. -Test Fail");

			}

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_diagnosis, "Collapse", "Link Diagnosis");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_procedure, "Expand", "Link Procedure");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_procedureInDropDown, "Click", "list of procedure");
			if (bAddProcedureCode == false)
			{
				Log4J.logp.info("Not Able to add Procedure Code. -Test Pass");
			}
			else
			{
				Log4J.logp.error("Able to add Procedure Code. -Test Fail");

			}

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_procedure, "Collapse", "Link Procedure");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lst_rightnavigationMenu, "Collapse", "Navigation Menu -  Coding Panel");
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);

			Log4J.logp.info("User should be able to go through all the medical documents by scrolling it.");
			Log4J.logp.info("Scroll Down");
			Common_Lib.scroll_Page(medicalrecordpnl_webe.medical_Dragger, 1000);
			Thread.sleep(2000);
			Log4J.logp.info("Scroll Up");
			Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1000);
			Thread.sleep(2000);

			Log4J.logp.info("User should be able to switch from one Coding system to another.");

			ViewCase_Lib.expandAndCollapse(codingpnl_webe.lst_codeType, "Expand", "Coding System Menu");

			ViewCase_Lib.expandAndCollapse(codingpnl_webe.lnk_ICD10_Menu, "Click", "Switched to ICD10");

			ViewCase_Lib.expandAndCollapse(codingpnl_webe.lst_codeType, "Expand", "Coding System Menu");

			ViewCase_Lib.expandAndCollapse(codingpnl_webe.lnk_ICD9_Menu, "Click", "Switched to ICD9");

			Log4J.logp.info("User should be able to change font size of medical records from the settings.");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.btn_settings, "Expand", "Setting Menu");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_mediumFont, "Click", "Medium Font");
			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_largeFont, "Click", "Large Font");
			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_smallFont, "Click", "Small Font");
			ViewCase_Lib.expandAndCollapse(viewcase_webe.btn_settings, "Collapse", "Setting Menu");

			Log4J.logp.info("User should be able to Expand and Collapse Abstract Panel.");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.panel_Abstract, "ExpandAndCollapse", "Setting Menu");

			Thread.sleep(2000);

			Log4J.logp.info("Coder's Code Template :User should not be able to Expand and Collapse it.");
			bAddAdmitCode = Common_Lib.checkElementPresent(codingpnl_webe.lnk_addAdmit_DignosisCode_ICD9);
			bAddDiagonsisCode = Common_Lib.checkElementPresent(codingpnl_webe.lnk_addDignosisCode_ICD9);
			bAddProcedureCode = Common_Lib.checkElementPresent(codingpnl_webe.lnk_addProcedureCode_ICD9);

			if (bAddAdmitCode == false && bAddDiagonsisCode == false && bAddProcedureCode == false)
			{
				Log4J.logp.info("Not Able to add Code. -Test Pass");

			}
			else
			{
				codingpnl_webe.lnk_addAdmit_DignosisCode_ICD9.click();
				codingpnl_webe.lnk_addDignosisCode_ICD9.click();
				codingpnl_webe.lnk_addProcedureCode_ICD9.click();
				Log4J.logp.error("Able to add Code. -Test Fail");

			}

			Log4J.logp.info("It should display all Issues raised by Coder.");

			Thread.sleep(2000);
			Log4J.logp.info(viewcase_webe.issue_Count.getText() + " Issue Found");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.panel_Issue, "Expand", "Issuse Panel");
			Thread.sleep(2000);

			if (issuepnl_webe.lnk_DiscussWithCol.isDisplayed() == false)
			{
				Log4J.logp.info("Not able to send query to collegue - Test pass");

			}
			else
			{
				issuepnl_webe.lnk_DiscussWithCol.click();
				Log4J.logp.error("Discuss With collegue sucessfully open- Test Fail ");

			}
			Thread.sleep(2000);

			ViewCase_Lib.expandAndCollapse(viewcase_webe.panel_Issue, "Collapse", "Issuse Panel");
			Log4J.logp.info("User should be able to Expand and Collapse Grouping Panel.");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.panel_Grouping, "ExpandAndCollapse", "Grouping Panel");

			Log4J.logp.info("It should display DRG which is Calculated by Coder.");

			String drg_temp1_ICD9 = viewcase_webe.txt_DRG.getText();
			Thread.sleep(2000);
			String drg_temp2_ICD9[] = drg_temp1_ICD9.split(",");
			Thread.sleep(1000);
			String drg_temp3_ICD9[] = drg_temp2_ICD9[0].trim().split(":");
			Thread.sleep(1000);
			int drgval_ICD9 = Integer.parseInt(drg_temp3_ICD9[1].trim());
			Thread.sleep(2000);
			if (drgval_ICD9 == 0 && drgval_ICD9 != 999)
			{
				Log4J.logp.info("DRG/APC value is displayed 0");

			}
			else
			{
				Log4J.logp.info(drgval_ICD9 + " - DRG / APC Value");
			}
			Thread.sleep(2000);

			Log4J.logp.info("User Should not able to Accept/Reject/Modify Code");

			Log4J.logp.info("Started : For Accepted ");
			listAccept_Icons = viewcase_webe.lst_acceptcode;
			for (WebElement accept_Icon : listAccept_Icons)
			{
				Common_Lib.scroll_Until_FindWebe(codingpnl_webe.coding_Dragger, accept_Icon, 30);
				Thread.sleep(1000);
				Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 10);
				Thread.sleep(1000);
				accept_Icon.click();
				strTempAccept = accept_Icon.getAttribute("id");
				Log4J.logp.info(strTempAccept);
				if (strTempAccept.contains("enabled"))
				{
					Log4J.logp.error("Accept icon is clickable");

					break;
				}
				else
				{
					Log4J.logp.info("Accept icon is not clickable");

				}
			}
			Log4J.logp.info(" Ended : For Accepted ");
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("Started : For Rejected");
			listReject_Icons = viewcase_webe.lst_rejectedcode;
			for (WebElement reject_Icon : listReject_Icons)
			{
				Common_Lib.scroll_Until_FindWebe(codingpnl_webe.coding_Dragger, reject_Icon, 30);
				Thread.sleep(1000);
				Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 10);
				Thread.sleep(1000);
				reject_Icon.click();
				strTempReject = reject_Icon.getAttribute("id");
				Log4J.logp.info(strTempReject);
				if (strTempReject.contains("enabled"))
				{
					Log4J.logp.error("Reject icon is clickable");

					break;
				}
				else
				{
					Log4J.logp.info("Reject icon is not clickable");

				}

			}
			Log4J.logp.info("Ended : For Rejected");
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("Started : For Modified");
			listModify_Icons = viewcase_webe.lst_modifiedcode;
			for (WebElement modify_Icon : listModify_Icons)
			{
				Common_Lib.scroll_Until_FindWebe(codingpnl_webe.coding_Dragger, modify_Icon, 30);
				Thread.sleep(1000);
				Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 10);
				Thread.sleep(1000);
				modify_Icon.click();
				strTempModified = modify_Icon.getAttribute("id");
				Log4J.logp.info(strTempModified);
				if (strTempModified.contains("enabled"))
				{
					Log4J.logp.error("Modify icon is clickable");

					break;
				}
				else
				{
					Log4J.logp.info("Modify icon is not clickable");

				}
			}
			Log4J.logp.info("Ended : For Modified");
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("User should be able to see System suggested Evidences.");

			for (WebElement we_ICD9 : viewcase_webe.lnk_evidence)
			{

				Thread.sleep(2000);

				if (!we_ICD9.isDisplayed())
				{
					Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 70);
					Thread.sleep(1000);
					Common_Lib.scroll_Page(codingpnl_webe.coding_Dragger, 50);
					Thread.sleep(2000);

				}

				Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 10);
				Thread.sleep(3000);
				we_ICD9.click();
				Thread.sleep(4000);

				getExpectedText_ICD9 = we_ICD9.getText().trim().toLowerCase();

				List<WebElement> temps_ICD9 = medicalrecordpnl_webe.lnk_highlighted_sys_evidence;

				for (WebElement temp_ICD9 : temps_ICD9)
				{

					getActualText_ICD9 = temp_ICD9.getText().trim().toLowerCase();
				}

				Log4J.logp.info(getExpectedText_ICD9 + "  " + getActualText_ICD9);

			}
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1500);
			Thread.sleep(2000);

			for (WebElement we_ICD9 : medicalrecordpnl_webe.lnk_highlighted_sys_med_evidence)
			{

				Thread.sleep(2000);

				while (true)
				{

					if (!we_ICD9.isDisplayed())
					{
						Thread.sleep(1000);
						Common_Lib.scroll_Page_VerySmall(medicalrecordpnl_webe.medical_Dragger, 5);
						Thread.sleep(1000);
						Common_Lib.scroll_Page(medicalrecordpnl_webe.medical_Dragger, 50);
						Thread.sleep(1000);
						if (we_ICD9.isDisplayed())
						{
							Thread.sleep(1000);
							Common_Lib.scroll_Page_VerySmall(medicalrecordpnl_webe.medical_Dragger, 5);
							Thread.sleep(1000);
							we_ICD9.click();
							Thread.sleep(1000);
							break;
						}

					}

					if (we_ICD9.isDisplayed())
					{
						Thread.sleep(3000);
						we_ICD9.click();
						Thread.sleep(3000);
						break;
					}

				}

			}

			Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1500);
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);

			Log4J.logp.info("============= Ended : commonCheckFor_ViewCase- ICD9 ====================");

			Log4J.logp.info("=================== Started : commonCheckFor_ViewCase- ICD10 ================");
			Thread.sleep(2000);
			ViewCase_Lib.expandAndCollapse(codingpnl_webe.lst_codeType, "Expand", "Coding System");

			ViewCase_Lib.expandAndCollapse(codingpnl_webe.lnk_ICD10_Menu, "Click", "Switched to ICD10");

			Log4J.logp.info("User should be able to Expand and Collapse Patient Demographic panel.");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.panel_PatientDemographic, "ExpandAndCollapse", "Demographic Panel");
			Log4J.logp.info("User should be able to Expand and Collapse Navigation Menu - Medical Record Panel.");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lst_LeftNavigationMenu, "Expand", "Navigation Menu - Medical Record Panel");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_dock, "Expand", "Dock");
			listDocs = viewcase_webe.lst_leftNavigtionMenuItem;
			for (WebElement temp : listDocs)
			{
				temp.click();
				Thread.sleep(2000);
			}

			Common_Lib.waitForObject(viewcase_webe.lnk_unDock, "clickable", 4);
			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_unDock, "Collapse", "Undock");

			Log4J.logp.info("Scroll Up");
			Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1000);
			Thread.sleep(2000);

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lst_rightnavigationMenu, "Expand", "Navigation Menu -  Coding Panel");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_diagnosis, "Expand", "Link Diagnosis");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_navadmittingdiagnosis, "Click", "Admiting Diagonsis");
			Log4J.logp.info("Coder's Code Template : User should not be able to Expand and Collapse it.");

			bAddAdmitCode = Common_Lib.checkElementPresent(codingpnl_webe.lnk_addAdmit_DignosisCode_ICD10);
			bAddDiagonsisCode = Common_Lib.checkElementPresent(codingpnl_webe.lnk_addDignosisCode_ICD10);
			bAddProcedureCode = Common_Lib.checkElementPresent(codingpnl_webe.lnk_addProcedureCode_ICD10);

			if (bAddAdmitCode == false)
			{
				Log4J.logp.info("Not Able to add Admiting code. -Test Pass");

			}
			else
			{
				Log4J.logp.error("Able to add Admiting Code. -Test Fail");

			}

			///admiting Code

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_navprincipaldiagnosis, "Click", "Principal Diagonsis");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_navsecondarydiagnosis, "Click", "Secondary Diagonsis");

			if (bAddDiagonsisCode == false)
			{
				Log4J.logp.info("Not Able to add diagonsis Code. -Test Pass");
			}
			else
			{
				Log4J.logp.error("Able to add diagonsis Code. -Test Fail");

			}

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_diagnosis, "Collapse", "Link Diagnosis");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_procedure, "Expand", "Link Procedure");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_procedureInDropDown, "Click", "list of procedure");
			if (bAddProcedureCode == false)
			{
				Log4J.logp.info("Not Able to add Procedure Code. -Test Pass");
			}
			else
			{
				Log4J.logp.error("Able to add Procedure Code. -Test Fail");

			}
			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_procedure, "Collapse", "Link Procedure");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lst_rightnavigationMenu, "Collapse", "Navigation Menu -  Coding Panel");
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("User should be able to go through all the medical documents by scrolling it.");
			Log4J.logp.info("Scroll Down");
			Common_Lib.scroll_Page(medicalrecordpnl_webe.medical_Dragger, 1000);
			Thread.sleep(2000);
			Log4J.logp.info("Scroll Up");
			Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1000);
			Thread.sleep(2000);

			Log4J.logp.info("User should be able to switch from one Coding system to another.");

			ViewCase_Lib.expandAndCollapse(codingpnl_webe.lst_codeType, "Expand", "Coding System Menu");
			ViewCase_Lib.expandAndCollapse(codingpnl_webe.lnk_ICD9_Menu, "Click", "Switched to ICD9");
			ViewCase_Lib.expandAndCollapse(codingpnl_webe.lst_codeType, "Expand", "Coding System Menu");
			ViewCase_Lib.expandAndCollapse(codingpnl_webe.lnk_ICD10_Menu, "Click", "Switched to ICD10");

			Log4J.logp.info("User should be able to change font size of medical records from the settings.");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.btn_settings, "Expand", "Setting Menu");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_mediumFont, "Click", "Medium Font");
			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_largeFont, "Click", "Large Font");
			ViewCase_Lib.expandAndCollapse(viewcase_webe.lnk_smallFont, "Click", "Small Font");
			ViewCase_Lib.expandAndCollapse(viewcase_webe.btn_settings, "Collapse", "Setting Menu");

			Log4J.logp.info("User should be able to Expand and Collapse Abstract Panel.");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.panel_Abstract, "ExpandAndCollapse", "Setting Menu");

			Thread.sleep(2000);

			Log4J.logp.info("It should display all Issues raised by Coder.");

			Thread.sleep(2000);
			Log4J.logp.info(viewcase_webe.issue_Count.getText() + " Issue Found");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.panel_Issue, "Expand", "Issuse Panel");
			Thread.sleep(2000);

			if (issuepnl_webe.lnk_DiscussWithCol.isDisplayed() == false)
			{
				Log4J.logp.info("Not able to send query to collegue - Test pass");

			}
			else
			{
				issuepnl_webe.lnk_DiscussWithCol.click();
				Log4J.logp.error("Discuss With collegue sucessfully open- Test Fail ");

			}
			Thread.sleep(2000);

			ViewCase_Lib.expandAndCollapse(viewcase_webe.panel_Issue, "Collapse", "Issuse Panel");
			Log4J.logp.info("User should be able to Expand and Collapse Grouping Panel.");

			ViewCase_Lib.expandAndCollapse(viewcase_webe.panel_Grouping, "ExpandAndCollapse", "Grouping Panel");

			Log4J.logp.info("It should display DRG which is Calculated by Coder.");

			String drg_temp1_ICD10 = viewcase_webe.txt_DRG.getText();
			Thread.sleep(2000);
			String drg_temp2_ICD10[] = drg_temp1_ICD10.split(",");
			Thread.sleep(1000);
			String drg_temp3_ICD10[] = drg_temp2_ICD10[0].trim().split(":");
			Thread.sleep(1000);
			int drgval_ICD10 = Integer.parseInt(drg_temp3_ICD10[1].trim());
			Thread.sleep(2000);
			if (drgval_ICD10 == 0 && drgval_ICD10 != 999)
			{
				Log4J.logp.info("DRG/APC value is displayed 0");

			}
			else
			{
				Log4J.logp.info(drgval_ICD10 + " - DRG / APC Value");
			}
			Thread.sleep(2000);

			Log4J.logp.info("User Should not able to Accept/Reject/Modify Code");

			Log4J.logp.info("Started : For Accepted ");
			listAccept_Icons = viewcase_webe.lst_acceptcode;
			for (WebElement accept_Icon : listAccept_Icons)
			{
				Common_Lib.scroll_Until_FindWebe(codingpnl_webe.coding_Dragger, accept_Icon, 30);
				Thread.sleep(1000);
				Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 10);
				Thread.sleep(1000);
				accept_Icon.click();
				strTempAccept = accept_Icon.getAttribute("id");
				Log4J.logp.info(strTempAccept);
				if (strTempAccept.contains("enabled"))
				{
					Log4J.logp.error("Accept icon is clickable");

					break;
				}
				else
				{
					Log4J.logp.info("Accept icon is not clickable");

				}
			}
			Log4J.logp.info("Ended : For Accepted");
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("Started : For Rejected");
			listReject_Icons = viewcase_webe.lst_rejectedcode;
			for (WebElement reject_Icon : listReject_Icons)
			{
				Common_Lib.scroll_Until_FindWebe(codingpnl_webe.coding_Dragger, reject_Icon, 30);
				Thread.sleep(1000);
				Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 10);
				Thread.sleep(1000);
				reject_Icon.click();
				strTempReject = reject_Icon.getAttribute("id");
				Log4J.logp.info(strTempReject);
				if (strTempReject.contains("enabled"))
				{
					Log4J.logp.error("Reject icon is clickable");

					break;
				}
				else
				{
					Log4J.logp.info("Reject icon is not clickable");

				}

			}
			Log4J.logp.info("Ended : For Rejected");
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("Started : For Modified");
			listModify_Icons = viewcase_webe.lst_modifiedcode;
			for (WebElement modify_Icon : listModify_Icons)
			{
				Common_Lib.scroll_Until_FindWebe(codingpnl_webe.coding_Dragger, modify_Icon, 30);
				Thread.sleep(1000);
				Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 10);
				Thread.sleep(1000);
				modify_Icon.click();
				strTempModified = modify_Icon.getAttribute("id");
				Log4J.logp.info(strTempModified);
				if (strTempModified.contains("enabled"))
				{
					Log4J.logp.error("Modify icon is clickable");

					break;
				}
				else
				{
					Log4J.logp.info("Modify icon is not clickable");

				}
			}
			Log4J.logp.info("Ended : For Modified");
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Log4J.logp.info("Finished Check Behavior that User can't Accept/Reject or Modify Code");
			Thread.sleep(5000);
			Log4J.logp.info("User should be able to see System suggested Evidences.");

			for (WebElement we_ICD10 : viewcase_webe.lnk_evidence)
			{

				Thread.sleep(2000);

				if (!we_ICD10.isDisplayed())
				{
					Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 70);
					Thread.sleep(1000);
					Common_Lib.scroll_Page(codingpnl_webe.coding_Dragger, 50);
					Thread.sleep(2000);

				}

				Common_Lib.scroll_Page_VerySmall(codingpnl_webe.coding_Dragger, 10);
				Thread.sleep(3000);
				we_ICD10.click();
				Thread.sleep(4000);

				getExpectedText_ICD10 = we_ICD10.getText().trim().toLowerCase();

				List<WebElement> temps_ICD10 = medicalrecordpnl_webe.lnk_highlighted_sys_evidence;

				for (WebElement temp_ICD10 : temps_ICD10)
				{

					getActualText_ICD10 = temp_ICD10.getText().trim().toLowerCase();
				}

				Log4J.logp.info(getExpectedText_ICD10 + "  " + getActualText_ICD10);

			}
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1500);
			Thread.sleep(2000);

			for (WebElement we_ICD10 : medicalrecordpnl_webe.lnk_highlighted_sys_med_evidence)
			{

				Thread.sleep(2000);

				while (true)
				{

					if (!we_ICD10.isDisplayed())
					{
						Thread.sleep(1000);
						Common_Lib.scroll_Page_VerySmall(medicalrecordpnl_webe.medical_Dragger, 5);
						Thread.sleep(1000);
						Common_Lib.scroll_Page(medicalrecordpnl_webe.medical_Dragger, 50);
						Thread.sleep(1000);
						if (we_ICD10.isDisplayed())
						{
							Thread.sleep(1000);
							Common_Lib.scroll_Page_VerySmall(medicalrecordpnl_webe.medical_Dragger, 5);
							Thread.sleep(1000);
							we_ICD10.click();
							Thread.sleep(1000);
							break;
						}

					}

					if (we_ICD10.isDisplayed())
					{
						Thread.sleep(3000);
						we_ICD10.click();
						Thread.sleep(3000);
						break;
					}

				}

			}
			Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1500);
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(codingpnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			ViewCase_Lib.expandAndCollapse(landingp_webe.btn_Close, "Click", "Frame Closed Successfully");
			Log4J.logp.info("=================== Ended : commonCheckFor_ViewCase- ICD10 ================");
			Log4J.logp.info("----------------------Ended: commonCheckFor_ViewCase --------------------------");
			return true;
		}

		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - commonCheckFor_ViewCase- Method");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * @author nchourasiya
	 * @since 9/12/2014 This is a common method for expand , collapse or click element.
	 * */

	public static boolean expandAndCollapse(WebElement ele, String actionString, String eleString)
	{
		try
		{
			Log4J.logp.info("---------------------Started : expandAndCollapse----------------------");
			if (actionString.equals("Expand") || actionString.equals("Collapse") || actionString.equals("Click"))
			{
				Thread.sleep(1000);
				Log4J.logp.info(actionString + " : " + eleString);
				ele.click();
				Thread.sleep(2000);

			}
			else if (actionString.equals("ExpandAndCollapse"))
			{
				Thread.sleep(1000);
				Log4J.logp.info("Expand : " + eleString);
				ele.click();
				Thread.sleep(2000);
				Log4J.logp.info("Collapse : " + eleString);
				ele.click();
				Thread.sleep(2000);
			}
			else
			{
				Log4J.logp.info("Something went wrong for" + ele.getText());

			}
			Log4J.logp.info("---------------------Ended : expandAndCollapse------------------------");
			return true;

		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - expandAndCollapse- Method");
			e.printStackTrace();
			return false;
		}

	}

}
