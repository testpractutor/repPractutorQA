package com.ezdi.testscripts;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ezdi.framework.common.JDBCMySql;
import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.library.Common_Lib;
import com.ezdi.library.IssuePanel_Lib;
import com.ezdi.library.Login_Lib;
import com.ezdi.library.Reports_Lib;
import com.ezdi.library.WorkingScreen_Lib;
import com.ezdi.webelements.Comman_WebE;
import com.ezdi.webelements.Excel_WebE;
import com.ezdi.webelements.GroupinPnl_WebE;
import com.ezdi.webelements.IssuePnl_WebE;
import com.ezdi.webelements.LandingP_WebE;
import com.ezdi.webelements.Login_WebE;
import com.ezdi.webelements.MedicalRecordPnl_WebE;
import com.ezdi.webelements.PDF_WebE;
import com.ezdi.webelements.Reports_WebE;
import com.ezdi.webelements.Show_Hide_Columns_WebE;

public class Reports
{
	public static WebDriver					driver;
	public static Show_Hide_Columns_WebE	showHideColumn_webe;
	public static LandingP_WebE				landingp_webe;
	public static Login_WebE				login_webe;
	public static Reports_WebE				reports_webe;
	public static PDF_WebE					pdf_webe;
	public static Excel_WebE				excel_webe;
	public static Comman_WebE				common_webe;
	public static MedicalRecordPnl_WebE		medicalrecordpnl_webe;
	public static GroupinPnl_WebE			groupingpnl_webe;
	public static IssuePnl_WebE				issuepnl_webe;

	@BeforeClass
	public static void ReportsBeforeClass()
	{
		try
		{

			driver = ExecutionSetup.getDriver();
			showHideColumn_webe = Show_Hide_Columns_WebE.getInstance(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			login_webe = Login_WebE.getInstance(driver);
			reports_webe = Reports_WebE.getInstance(driver);
			pdf_webe = PDF_WebE.getInstance(driver);
			excel_webe = Excel_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);

			//login with Akash user
			Login_Lib.login("ag007");
			Thread.sleep(2000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This script is to check that Role wise reports
	 * 
	 * @author agupta
	 * @since 8/10/2014
	 */
	@Test(description = "ezCAC_MVP_2772 :: To check Role wise Reports", priority = 0)
	public static void roleWiseReports()
	{
		try
		{
			Log4J.logp.info("************************* Started ::: roleWiseReports *************************");
			landingp_webe.lnk_All.click();
			landingp_webe.lnk_Reports.click();
			reports_webe.lnk_All_Reports.click();
			for (int i = 0; i < 4; i++)
			{
				switch (i)
				{
					case 0:
						Log4J.logp.info("============ Supervisor Role =============");
						if (reports_webe.lnk_Coding_Completed.getText().equalsIgnoreCase("Coding Completed"))
						{
							Log4J.logp.info("Coding Completed is Displayed");
						}
						if (reports_webe.lnk_Pending_Cases.getText().equalsIgnoreCase("Pending Cases"))
						{
							Log4J.logp.info("Pending Cases is Displayed");
						}
						if (reports_webe.lnk_Coding_Quality.getText().equalsIgnoreCase("Coding Quality"))
						{
							Log4J.logp.info("Coding Quality is Displayed");
						}
						if (reports_webe.lnk_CDI_Impact_on_Query_by_CI.getText().equalsIgnoreCase("CDI Impact on Query by CI"))
						{
							Log4J.logp.info("CDI Impact on Query by CI is Displayed");
						}
						if (reports_webe.lnk_CDI_Impact_on_Query_by_Physician.getText().equalsIgnoreCase("CDI Impact on Query by Physician"))
						{
							Log4J.logp.info("CDI Impact on Query by Physician is Displayed");
						}
						if (reports_webe.lnk_Coder_Performance_and_Accuracy.getText().equalsIgnoreCase("Coder Performance and Accuracy"))
						{
							Log4J.logp.info("Coder Performance and Accuracy is Displayed");
						}
						if (reports_webe.lnk_Engine_Performance.getText().equalsIgnoreCase("Engine Performance"))
						{
							Log4J.logp.info("Engine Performance is Displayed");
						}
						if (reports_webe.lnk_Reviewer_Completed_Cases.getText().equalsIgnoreCase("Reviewer Completed Cases"))
						{
							Log4J.logp.info("Reviewer Completed Cases is Displayed");
						}
						break;
					case 1:
						landingp_webe.lnk_Cases.click();
						landingp_webe.lnk_Coding.click();
						Log4J.logp.info("============ Coder Role ============");
						landingp_webe.lnk_Reports.click();
						landingp_webe.lnk_CodingReports.click();
						if (reports_webe.lnk_My_Productivity.getText().equalsIgnoreCase("My Productivity"))
						{
							Log4J.logp.info("Coder My Productivity is Displayed");
						}
						break;
					case 2:
						landingp_webe.lnk_Cases.click();
						landingp_webe.lnk_Review.click();
						Log4J.logp.info("============ Reviewer Role ============");
						landingp_webe.lnk_Reports.click();
						landingp_webe.lnk_ReviewerReports.click();
						if (reports_webe.lnk_My_Productivity.getText().equalsIgnoreCase("My Productivity"))
						{
							Log4J.logp.info("Reviewer My Productivity is Displayed");
						}
						break;
					case 3:
						landingp_webe.lnk_Cases.click();
						landingp_webe.lnk_CDI.click();
						Log4J.logp.info("============ CDI Role ============");
						landingp_webe.lnk_Reports.click();
						landingp_webe.lnk_CDIReports.click();
						if (reports_webe.lnk_CDI_Impact_on_Query_by_CI.getText().equalsIgnoreCase("CDI Impact on Query by CI"))
						{
							Log4J.logp.info("CDI Impact on Query by CI is Displayed");
						}
						if (reports_webe.lnk_CDI_Impact_on_Query_by_Physician.getText().equalsIgnoreCase("CDI Impact on Query by Physician"))
						{
							Log4J.logp.info("CDI Impact on Query by Physician is Displayed");
						}
						break;
				}
			}
			Log4J.logp.info("************************* Ended ::: roleWiseReports *************************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: rolwWiseReports");
			Assert.assertTrue(false, "Problem found in roleWiseReport");
			e.printStackTrace();
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
	 * This script is to generate data in all reports page
	 * 
	 * @author agupta
	 * @since 14/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-1646 : To check when Coder Complete the case" + "ezCAC_MVP_Reg-1647 : To check when Reviewer Billed the case" + "ezCAC_MVP_Reg-1739 :To check when Reviewer Billed the case"
			+ "ezCAC_MVP_Reg-1679 :  To check when coder raise any issue and puts case in 'Pending' status" + "ezCAC_MVP_Reg-1869 : To check when Reviewer billed the cases " + "ezCAC_MVP_Reg-1914 : To check when Reviewer Billed the cases"
			+ "ezCAC_MVP_Reg-1714 : To check when Reviewer billed the cases" + "ezCAC_MVP_Reg-2186 : To check when Reviewer billed the cases" + "ezCAC_MVP_Reg-1790 : To check when Coder completes the case" + "ezCAC_MVP_Reg-1791 : To check when Reviewer billed the case"
			+ "ezCAC_MVP_Reg-2509,ezCAC_MVP_Reg-2494 : To check when CDI raise query with select physician name" + "ezCAC_MVP_Reg-2533,ezCAC_MVP_Reg-1980 : To check when CDI raise query with Clinical Indicators", priority = 1)
	public static void report_Data()
	{
		String strmrn = null;
		String strmrn1 = null;
		String strCI = null;
		String strphysician = null;
		String strserviceline = null;
		boolean bstatus = false;
		int count = 0;
		try
		{
			Log4J.logp.info("************************ Started :: report_Data ******************************");
			Thread.sleep(3000);
			driver = ExecutionSetup.getDriver();
			Map<String, String> rowTestData = null;
			JDBCMySql searchCriteria_TestData = new JDBCMySql();
			rowTestData = searchCriteria_TestData.getRowbyID("td_searchcase", "ag003");
			strmrn = rowTestData.get("MRN");
			for (int i = 1; i < 9; i++)
			{
				switch (i)
				{
					case 1:
						// For Engine Performance Report
						Log4J.logp.info("=========== Started :: Engine Performance Report ============");
						rowTestData = searchCriteria_TestData.getRowbyID("td_searchcase", "ag008");
						strserviceline = rowTestData.get("service_line");
						landingp_webe.lnk_Coding.click();
						Thread.sleep(2000);
						WorkingScreen_Lib.submit_case("ag008");
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 4);
						bstatus = Reports_Lib.stored_Procedure();
						Thread.sleep(10000);
						if (bstatus == true)
						{
							Log4J.logp.info("Stored Procedure Run Successfully");
							Assert.assertTrue(true, "Stored Procedure run Successfully.");
						}
						landingp_webe.lnk_All.click();
						Thread.sleep(2000);
						landingp_webe.lnk_Reports.click();
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Engine_Performance.click();
						Thread.sleep(2000);
						WebElement table2 = showHideColumn_webe.tbl_reports;
						List<WebElement> row2 = table2.findElements(By.tagName("tr"));
						for (int j = 0; j < row2.size(); j++)
						{
							List<WebElement> ServiceLine = table2.findElements(By.xpath("//tr[" + j + "]/td[8]"));
							for (WebElement c : ServiceLine)
							{
								if (strserviceline.equals(c.getText()))
								{
									count++;
									Log4J.logp.info("Case of " + strserviceline + " Service line is Displayed in Engine Performance Report and count = " + count);
								}
							}
						}
						if (count == 0)
						{
							Log4J.logp.info("Not found any case");
							Assert.assertTrue(false, "Not found any case");
						}
						Log4J.logp.info("============== Ended :: Engine Performance Report ===========");
						break;
					case 2:
						// For Pending Cases Report
						Log4J.logp.info("============== Started :: Pending Cases Report ==============");
						Thread.sleep(2000);
						Login_Lib.logOut_App(); // logout from Akash User
						rowTestData = searchCriteria_TestData.getRowbyID("td_searchcase", "ag004");
						strmrn1 = rowTestData.get("MRN");
						Thread.sleep(2000);
						Login_Lib.login("ag010"); // Login with Patricia User
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "Visibility", 3);
						landingp_webe.lnk_Coding.click();
						Thread.sleep(2000);
						Common_Lib.openCase("ag004");
						Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);

						// Following code will raise discuss with colleagues issue on first system suggested evidence
						Common_Lib.rightclick_system_sugggested_evidence();
						Thread.sleep(2000);
						medicalrecordpnl_webe.lnk_discusswithcolleague.click();
						IssuePanel_Lib.send_DiscussWithColleague("ag001");
						Thread.sleep(2000);

						// Following code will raise Query issue on first system suggested evidence
						/*Actions action = new Actions(driver);
						action.moveToElement(common_webe.lnk_sys_evidence.get(0)).contextClick().build().perform();*/
						Common_Lib.rightclick_system_sugggested_evidence();
						Thread.sleep(2000);
						medicalrecordpnl_webe.lnk_querytophysician.click();
						IssuePanel_Lib.send_QueryToPhysician("ag002");
						Thread.sleep(2000);
						issuepnl_webe.btn_Issues.click();
						Thread.sleep(2000);

						Log4J.logp.info("Issue Panel Closed");
						groupingpnl_webe.btn_Later.click();
						Thread.sleep(3000);
						bstatus = Reports_Lib.stored_Procedure();
						Thread.sleep(10000);
						if (bstatus == true)
						{
							Log4J.logp.info("Stored Procedure Run Successfully");
							Assert.assertTrue(true, "Stored Procedure run Successfully.");
						}
						Thread.sleep(3000);
						Login_Lib.logOut_App(); // logout from Patricia user role
						Thread.sleep(2000);
						Login_Lib.login("ag007"); // login with Akash user role
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
						landingp_webe.lnk_All.click();
						landingp_webe.lnk_Reports.click();
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Pending_Cases.click();
						Reports_Lib.search_Data("SD013");
						Thread.sleep(2000);
						Reports_Lib.chk_Data_Report(strmrn1);
						Log4J.logp.info("============= Ended :: Pending Cases Report ===============");
						break;
					case 3:
						// For Coding Completed Cases Report
						Log4J.logp.info("============ Started :: Coding Completed Report ============");
						Thread.sleep(2000);
						Login_Lib.logOut_App(); // Logout from Akash user
						Thread.sleep(2000);
						Login_Lib.login("ag010"); // login with Patricia user
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
						WorkingScreen_Lib.submit_case("ag001");
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
						bstatus = Reports_Lib.stored_Procedure();
						Thread.sleep(10000);
						if (bstatus == true)
						{
							Log4J.logp.info("Stored Procedure Run Successfully");
							Assert.assertTrue(true, "Stored Procedure run Successfully.");
						}
						Thread.sleep(2000);
						Login_Lib.logOut_App(); // logout from Patricia user role
						Thread.sleep(2000);
						Login_Lib.login("ag007"); // login with Akash user role
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
						landingp_webe.lnk_All.click();
						landingp_webe.lnk_Reports.click();
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coding_Completed.click();
						Reports_Lib.search_Data("SD013");
						Thread.sleep(2000);
						Reports_Lib.chk_Data_Report(strmrn);
						Log4J.logp.info("================= Ended :: Coding Completed Report ===============");
						break;
					case 4:
						// For Coding Quality Report
						Log4J.logp.info("================ Started ::: Coding Quality Report =============");
						Thread.sleep(2000);
						Login_Lib.logOut_App(); // logout from Akash user
						Thread.sleep(2000);
						Login_Lib.login("ag011");// login with Margaret Reviewer user role
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
						WorkingScreen_Lib.submit_case("ag002");
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
						bstatus = Reports_Lib.stored_Procedure();
						Thread.sleep(10000);
						if (bstatus == true)
						{
							Log4J.logp.info("Stored Procedure Run Successfully");
							Assert.assertTrue(true, "Stored Procedure run Successfully.");
						}
						Thread.sleep(2000);
						Login_Lib.logOut_App(); // logout from Margaret reviewer user role
						Thread.sleep(2000);
						Login_Lib.login("ag007"); // login with akash user role
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
						landingp_webe.lnk_All.click();
						landingp_webe.lnk_Reports.click();
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coding_Quality.click();
						Reports_Lib.search_Data("SD013");
						Thread.sleep(2000);
						Reports_Lib.chk_Data_Report(strmrn);
						Log4J.logp.info("=============== Ended : Coding Quality Report ==============");
						break;
					case 5:
						// For Coder performance and Accuracy Report
						Log4J.logp.info("================== Started :: Coder Performance and Accuracy Report =============");
						Thread.sleep(2000);
						reports_webe.lnk_All_Reports.click();
						Thread.sleep(2000);
						reports_webe.lnk_Coder_Performance_and_Accuracy.click();
						Reports_Lib.search_Data("SD013");
						Thread.sleep(2000);
						WebElement table = showHideColumn_webe.tbl_reports;
						List<WebElement> row = table.findElements(By.tagName("tr"));
						String name = "Patricia Johnson";
						for (int j = 0; j < row.size(); j++)
						{
							List<WebElement> FirstColumn = table.findElements(By.xpath("//tr[" + j + "]/td[1]"));
							for (WebElement c : FirstColumn)
							{
								if (name.equals(c.getText()))
								{
									Log4J.logp.info("Cases is displayed in Coder Performance and Accuracy Report for coder " + name);

								}

							}
						}
						Log4J.logp.info("================= Ended :: Coder Performance and Accuracy Report ================");
						break;
					case 6:
						// For Reviewer Completed Cases Report
						Log4J.logp.info("============ Started :: Reviewer Completed Cases Report =============");
						Thread.sleep(2000);
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Reviewer_Completed_Cases.click();
						Reports_Lib.search_Data("SD013");
						Thread.sleep(2000);
						WebElement table1 = showHideColumn_webe.tbl_reports;
						List<WebElement> row1 = table1.findElements(By.tagName("tr"));
						String name1 = "Margaret Moore";
						for (int j = 0; j < row1.size(); j++)
						{
							List<WebElement> FirstColumn = table1.findElements(By.xpath("//tr[" + j + "]/td[1]"));
							for (WebElement c : FirstColumn)
							{
								if (name1.equals(c.getText()))
								{
									Log4J.logp.info("Cases is displayed in Reviewer completed cases for Reviewer " + name1);

								}

							}
						}
						Log4J.logp.info("================ Ended :: Reviewer Completed Cases Report ===============");
						break;
					/* case 7:
					  // For My Productivity Report for Coder
					  Log4J.logp.info("============= Started :: My Productivity Report for coder ===============");
					  Login_Lib.logOut_App(); // logout from Akash user role
					  Thread.sleep(2000); 
					  Login_Lib.login("ag010"); // login with Patricia user role 
					  Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
					  //Reports_Lib.stored_Procedure();
					  landingp_webe.lnk_Coding.click(); Thread.sleep(2000);
					  landingp_webe.lnk_Reports.click();
					  reports_webe.lnk_Coding_Reports.click();
					  reports_webe.lnk_My_Productivity.click(); 
					  Thread.sleep(2000);
					  Reports_Lib.search_Data("SD013"); 
					  Thread.sleep(2000);
					  Reports_Lib.chk_Data_Report(strmrn); 
					  Log4J.logp.info("============ Ended : My Productivity Report For coder ==============="); 
					  break;
					  
					  
					  case 8: 
					  //For My Productivity Report for Reviewer
					  Log4J.logp.info("================ Started :: My Productivity Report for Reviewer ====");
					  Login_Lib.logOut_App(); // logout from Patricia user role
					  Thread.sleep(2000); 
					  Login_Lib.login("ag011"); // login with Margaret reviewer user role 
					  Thread.sleep(2000);
					  //Reports_Lib.stored_Procedure(); 
					   Thread.sleep(2000);
					  landingp_webe.lnk_Reports.click();
					  reports_webe.lnk_Reviewer_Reports.click();
					  reports_webe.lnk_My_Productivity.click(); 
					  Thread.sleep(2000);
					  Reports_Lib.search_Data("SD013"); 
					  Thread.sleep(2000);
					  Reports_Lib.chk_Data_Report(strmrn);
					  Log4J.logp.info("===============  Ended : My Productivity Report for Reviewer ===================");
					   break;
					 */

					case 7:
						// For CDI Impact on Query by Clinical Indicators report
						Log4J.logp.info("=========== Started :: CDI Impact on Query by Clinical Indicators Report =================");
						Thread.sleep(2000);
						landingp_webe.lnk_Cases.click();
						Thread.sleep(2000);
						landingp_webe.lnk_CDI.click();
						Thread.sleep(2000);
						Common_Lib.openCase("ag003");
						Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);
						IssuePanel_Lib.send_QueryToPhysician_CDI("ag002");
						Thread.sleep(2000);
						issuepnl_webe.btn_Issues.click();
						Thread.sleep(2000);
						Log4J.logp.info("Issue Panel Closed");
						groupingpnl_webe.btn_Later.click();
						Thread.sleep(2000);
						JDBCMySql updatequery = new JDBCMySql();
						updatequery.executeQuery("QueryMaster", "datetime", "2014-07-22 07:57:43", "encounterid", "61");
						Thread.sleep(2000);
						Log4J.logp.info("Date has been changed in raised query");
						bstatus = Reports_Lib.stored_Procedure();
						Thread.sleep(10000);
						if (bstatus == true)
						{
							Log4J.logp.info("Stored Procedure Run Successfully");
							Assert.assertTrue(true, "Stored Procedure run Successfully.");
						}
						rowTestData = searchCriteria_TestData.getRowbyID("td_cdiquery", "ag002");
						strCI = rowTestData.get("evidencename");
						strphysician = rowTestData.get("to");
						landingp_webe.lnk_All.click();
						Thread.sleep(2000);
						landingp_webe.lnk_Reports.click();
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_CI.click();
						Thread.sleep(2000);
						WebElement table3 = showHideColumn_webe.tbl_reports;
						List<WebElement> row3 = table3.findElements(By.tagName("tr"));
						for (int j = 0; j < row3.size(); j++)
						{
							List<WebElement> ci = table3.findElements(By.xpath("//tr[" + j + "]/td[1]"));
							for (WebElement c : ci)
							{
								if (strCI.equals(c.getText()))
								{
									count++;
									Log4J.logp.info("Data for " + strCI + " Clinical Indicator is Displayed in CDI Impact on queruy by Clinical indicators reports and count = " + count);
								}
							}
						}
						if (count == 0)
						{
							Log4J.logp.info("Not found any case");
							Assert.assertTrue(false, "Not found any case");
						}
						Log4J.logp.info("============= Ended : CDI Impact on Query by Clinical Indicators Report =============== ");

						Log4J.logp.info("================ Started CDI Impact on Query by Physician =================== ");
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_Physician.click();
						Thread.sleep(2000);
						for (int j = 0; j < row3.size(); j++)
						{
							List<WebElement> phy = table3.findElements(By.xpath("//tr[" + j + "]/td[1]"));
							for (WebElement c : phy)
							{
								if (strphysician.equals(c.getText()))
								{
									count++;
									Log4J.logp.info("Data for " + strphysician + " Physician is Displayed in CDI Impact on queruy by Clinical indicators reports and count = " + count);
								}
							}
						}
						if (count == 0)
						{
							Log4J.logp.info("Not found any case");
							Assert.assertTrue(false, "Not found any case");
						}
						Log4J.logp.info("================== Ended : CDI Impact on Query by Physician Report ================ ");
						break;
					case 8:
						// For CDI Impact on Query by Clinical Indicators report
						Log4J.logp.info("=========== Started :: CDI Impact on Query by Clinical Indicators Report for CDI ============== ");
						Thread.sleep(2000);
						landingp_webe.lnk_Cases.click();
						Thread.sleep(2000);
						landingp_webe.lnk_CDI.click();
						landingp_webe.lnk_Reports.click();
						strCI = null;
						strphysician = null;
						rowTestData = searchCriteria_TestData.getRowbyID("td_cdiquery", "ag002");
						strCI = rowTestData.get("evidencename");
						strphysician = rowTestData.get("to");
						reports_webe.lnk_CDI_Reports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_CI.click();
						Thread.sleep(2000);
						WebElement table4 = showHideColumn_webe.tbl_reports;
						List<WebElement> row4 = table4.findElements(By.tagName("tr"));
						for (int j = 0; j < row4.size(); j++)
						{
							List<WebElement> ci = table4.findElements(By.xpath("//tr[" + j + "]/td[1]"));
							for (WebElement c : ci)
							{
								if (strCI.equals(c.getText()))
								{
									count++;
									Log4J.logp.info("Data for " + strCI + " Clinical Indicator is Displayed in CDI Impact on queruy by Clinical indicators reports and count = " + count);
								}
							}
						}
						if (count == 0)
						{
							Log4J.logp.info("Not found any case");
							Assert.assertTrue(false, "Not found any case");
						}
						Log4J.logp.info("=============== Ended  :: CDI Impact on Query by Clinical Indicators Report for CDI ============= ");

						Log4J.logp.info("============ Started :: CDI Impact on Query by Physician for CDI =========== ");
						reports_webe.lnk_CDI_Reports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_Physician.click();
						Thread.sleep(2000);
						for (int j = 0; j < row4.size(); j++)
						{
							List<WebElement> phy = table4.findElements(By.xpath("//tr[" + j + "]/td[1]"));
							for (WebElement c : phy)
							{
								if (strphysician.equals(c.getText()))
								{
									count++;
									Log4J.logp.info("Data for " + strphysician + " Physician is Displayed in CDI Impact on queruy by Clinical indicators reports and count = " + count);
								}
							}
						}
						if (count == 0)
						{
							Log4J.logp.info("Not found any case");
							Assert.assertTrue(false, "Not found any case");
						}
						Log4J.logp.info("============= Ended :: CDI Impact on Query by Physician Report for CDI ============ ");
						break;
				}
			}
			Log4J.logp.info("**************** Ended :: report_Data ********************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: report_Data");
			Assert.assertTrue(false, "Problem found in :: report_Data");
			e.printStackTrace();
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
	 * This Script check result summary in reports page
	 * 
	 * @author agupta
	 * @since 09/10/2014
	 */
	@Test(description = "ezCAC_MVP-1628,ezCAC_MVP-1721,ezCAC_MVP-1754,ezCAC_MVP-1658,ezCAC_MVP-1692,ezCAC_MVP-2164,ezCAC_MVP_1852,ezCAC_MVP_1898 :: To check result summary in Reports page", priority = 2)
	public static void resultSummary()
	{
		boolean bstatus = false;
		String rowid = null;
		try
		{
			Log4J.logp.info("************** Started - resultSummary ***************");
			landingp_webe.lnk_All.click();
			landingp_webe.lnk_Reports.click();
			Thread.sleep(3000);
			reports_webe.lnk_All_Reports.click();
			Thread.sleep(2000);
			for (int i = 1; i <= 6; i++)
			{

				switch (i)
				{
					case 1:
						reports_webe.lnk_Coding_Completed.click();
						Log4J.logp.info("====== Coding Completed Report ======\n");
						Reports_Lib.search_Data("SD013");
						rowid = "RD001";
						break;
					case 2:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Pending_Cases.click();
						Log4J.logp.info("====== Pending Cases Report ======\n");
						Reports_Lib.search_Data("SD013");
						rowid = "RD002";
						break;
					case 3:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coding_Quality.click();
						Log4J.logp.info("====== Coding Quality Report ======\n");
						Reports_Lib.search_Data("SD013");
						rowid = "RD003";
						break;
					case 4:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coder_Performance_and_Accuracy.click();
						Log4J.logp.info("====== Coder Performance and Accuracy Report ======\n");
						Reports_Lib.search_Data("SD013");
						rowid = "RD004";
						break;
					case 5:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Engine_Performance.click();
						Log4J.logp.info("====== Engine Performance Report ======\n");
						rowid = "RD005";
						break;
					case 6:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Reviewer_Completed_Cases.click();
						Log4J.logp.info("====== Reviewer Completed Report ======\n");
						Reports_Lib.search_Data("SD013");
						rowid = "RD006";
						break;
				// Data is not generate for My Productivity Report coder and Reviewer
				/*
				  case 7: 
				  
				  Login_Lib.logOut_App(); // logout from Akash user role 
				  Thread.sleep(2000);
				  Login_Lib.login("ag010"); // Login with Patricia user role 
				  Thread.sleep(2000);
				  landingp_webe.lnk_Reports.click();
				  landingp_webe.lnk_CodingReports.click();
				  reports_webe.lnk_My_Productivity.click();
				  Log4J.logp.info("==== My Productivity Report for Coder====");
				  Reports_Lib.search_Data("SD013"); 
				  rowid = "RD007"; 
				  break;
				  
				  
				  case 8: 
				  
				  Login_Lib.logOut_App(); // logout from patricia user role 
				  Thread.sleep(2000); 
				  Login_Lib.login("ag011"); // Login with Margaret Reviewer user role 
				  Thread.sleep(2000);
				  landingp_webe.lnk_Reports.click();
				  landingp_webe.lnk_ReviewerReports.click();
				  reports_webe.lnk_My_Productivity.click();
				  Log4J.logp.info("==== My Productivity Report for Reviewer ==="); 
				  Reports_Lib.search_Data("SD013"); 
				  rowid = "RD008"; 
				  break;
				 */
				}
				bstatus = Reports_Lib.resultSummary(rowid);
				Thread.sleep(2000);
			}
			Log4J.logp.info("***************** Ending - resultSummary ******************");
			Assert.assertTrue(bstatus, "The resultSummary passed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - resultSummary");
			Assert.assertTrue(false, "The resultSummary failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App(); // logout from margaret user
		}
	}

	/**
	 * This Script check Search Results in reports page
	 * 
	 * @author agupta
	 * @since 9/10/2014
	 */
	@Test(description = "ezCAC_MVP-1629,ezCAC_MVP-1722,ezCAC_MVP-1755,ezCAC_MVP-1659,ezCAC_MVP-1693,ezCAC_MVP-2503,ezCAC_MVP-2522,ezCAC_MVP-2165,ezCAC_MVP-1853,ezCAC_MVP-1973,ezCAC_MVP-2488,ezCAC_MVP-1899 :: To check Search Results in Reports page", priority = 3)
	public static void searchResults()
	{
		boolean bstatus = false;
		String rowid = null;
		try
		{

			Log4J.logp.info("************ Started - searchResults *************");
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			landingp_webe.lnk_All.click();
			landingp_webe.lnk_Reports.click();
			Thread.sleep(2000);
			reports_webe.lnk_All_Reports.click();
			Thread.sleep(2000);
			for (int i = 1; i <= 10; i++)
			{

				switch (i)
				{
					case 1:
						reports_webe.lnk_Coding_Completed.click();
						Log4J.logp.info("====== Coding Completed Report ======\n");
						Reports_Lib.search_Data("SD013");
						rowid = "SD001";
						break;
					case 2:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Pending_Cases.click();
						Log4J.logp.info("====== Pending Cases Report ======\n");
						Reports_Lib.search_Data("SD013");
						rowid = "SD002";
						break;
					case 3:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coding_Quality.click();
						Log4J.logp.info("====== Coding Quality Report ======\n");
						Reports_Lib.search_Data("SD013");
						rowid = "SD003";
						break;
					case 4:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_Physician.click();
						Log4J.logp.info("====== CDI Impact on Query by Physician ======\n");
						rowid = "SD004";
						break;
					case 5:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_CI.click();
						Log4J.logp.info("====== CDI Impact on Query by Clinical Indicators ======\n");
						rowid = "SD005";
						break;
					case 6:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coder_Performance_and_Accuracy.click();
						Log4J.logp.info("====== Coder Performamce and Accuracy Report ======\n");
						Reports_Lib.search_Data("SD013");
						rowid = "SD006";
						break;
					case 7:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Engine_Performance.click();
						Log4J.logp.info("====== Engine Performance Report ======\n");
						rowid = "SD007";
						break;
					case 8:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Reviewer_Completed_Cases.click();
						Reports_Lib.search_Data("SD013");
						Log4J.logp.info("====== Reviewer Completed Cases Report ======\n");
						rowid = "SD008";
						break;
					case 9:
						landingp_webe.lnk_Cases.click();
						landingp_webe.lnk_CDI.click();
						landingp_webe.lnk_Reports.click();
						landingp_webe.lnk_CDIReports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_Physician.click();
						Log4J.logp.info("==== CDI Impact on Query by Physician ====");
						rowid = "SD010";
						break;
					case 10:
						landingp_webe.lnk_CDIReports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_CI.click();
						Log4J.logp.info("==== CDI Impact on Query by Clinical Indicators ====");
						rowid = "SD011";
						break;
				// Data is not generate for My Productivity Coder and Reviewer
				/*
				  
				  case 11: 
				  
				  Login_Lib.logOut_App(); // logout from Akash user role 
				  Thread.sleep(2000); 
				  Login_Lib.login("ag010"); // Login with Patricia user role 
				  Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);				 
				  slandingp_webe.lnk_Reports.click();
				  landingp_webe.lnk_CodingReports.click();
				  reports_webe.lnk_My_Productivity.click();
				  Log4J.logp.info("==== My Productivity Report for Coder====");
				  Reports_Lib.search_Data("SD013"); 
				  rowid = "SD009"; 
				  break;
				 
				  
				  case 12: 
				  
				  Login_Lib.logOut_App(); // logout from patricia user role 
				  Thread.sleep(2000); 
				  Login_Lib.login("ag011"); // Login with Margaret Reviewer user role 
				  Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
				  landingp_webe.lnk_Reports.click();
				  landingp_webe.lnk_ReviewerReports.click();
				  reports_webe.lnk_My_Productivity.click();
				  Log4J.logp.info("==== My Productivity Report for Reviewer ==="); 
				  Reports_Lib.search_Data("SD013"); 
				  rowid = "SD012"; 
				  break;
				 */
				}
				bstatus = Reports_Lib.searchResults(rowid);
				Thread.sleep(3000);
			}
			Log4J.logp.info("************ Ending - searchResults **********");
			Assert.assertTrue(bstatus, "The searchResult Passed Successfully");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - searchResults");
			Assert.assertTrue(false, "The searchResults failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App(); // logout from Margaret user role
		}
	}

	/**
	 * This script check search criteria in reports page
	 * 
	 * @author agupta
	 * @since 09/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-1630, ezCAC_MVP_Reg-1723,ezCAC_MVP_Reg-1756,ezCAC_MVP_Reg-1660,ezCAC_MVP_Reg-1694,ezCAC_MVP_Reg-2504,ezCAC_MVP_Reg-2525,ezCAC_MVP_Reg-2166,ezCAC_MVP_Reg-1854,ezCAC_MVP_Reg-1974,ezCAC_MVP_Reg-2489,ezCAC_MVP_Reg-1900 :: To check search criteria in Reports page", priority = 4)
	public static void searchData()
	{
		boolean bstatus = false;
		String rowid = null;
		try
		{
			Log4J.logp.info("**************** Started - searchData ****************");
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			landingp_webe.lnk_All.click();
			landingp_webe.lnk_Reports.click();
			Thread.sleep(2000);
			reports_webe.lnk_All_Reports.click();
			Thread.sleep(2000);
			for (int i = 1; i <= 10; i++)
			{
				switch (i)
				{
					case 1:
						reports_webe.lnk_Coding_Completed.click();
						Log4J.logp.info("==== Coding Completed Report ====");
						rowid = "SD001";
						break;
					case 2:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Pending_Cases.click();
						Log4J.logp.info("==== Pending Cases Report ====");
						rowid = "SD002";
						break;
					case 3:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coding_Quality.click();
						Log4J.logp.info("==== Coding Quality Report ====");
						rowid = "SD003";
						break;
					case 4:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_Physician.click();
						Log4J.logp.info("==== CDI Impact on Query by Physician Report ====");
						rowid = "SD004";
						break;
					case 5:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_CI.click();
						Log4J.logp.info("==== CDI Impact on Query by Clinical Inidcators ====");
						rowid = "SD005";
						break;
					case 6:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coder_Performance_and_Accuracy.click();
						Log4J.logp.info("==== Coder Performance and Accuracy Report ====");
						rowid = "SD006";
						break;
					case 7:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Engine_Performance.click();
						Log4J.logp.info("==== Engine Performance Report ====");
						rowid = "SD007";
						break;
					case 8:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Reviewer_Completed_Cases.click();
						Log4J.logp.info("==== Reviewer Completed Cases Report ====");
						rowid = "SD008";
						break;
					case 9:
						landingp_webe.lnk_Cases.click();
						landingp_webe.lnk_CDI.click();
						landingp_webe.lnk_Reports.click();
						landingp_webe.lnk_CDIReports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_Physician.click();
						Log4J.logp.info("==== CDI Impact on Query by Physician ====");
						rowid = "SD010";
						break;
					case 10:
						landingp_webe.lnk_CDIReports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_CI.click();
						Log4J.logp.info("==== CDI Impact on Query by Clinical Indicators ====");
						rowid = "SD011";
						break;

				// Data is not generate for My Productivity Report coder and  Reviewer
				/*
				  case 11: 
				  
				  Login_Lib.logOut_App(); // logout from Akash user role 
				  Thread.sleep(2000); 
				  Login_Lib.login("ag010"); // Login with Patricia user role 
				  Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
				  landingp_webe.lnk_Reports.click();
				  landingp_webe.lnk_CodingReports.click();
				  reports_webe.lnk_My_Productivity.click();
				  Log4J.logp.info("==== My Productivity Report for Coder====");
				  rowid = "SD009";
				  break; 
				  
				  case 12: 
				  
				  Login_Lib.logOut_App(); // logout from patricia user role 
				  Thread.sleep(2000);
				  Login_Lib.login("ag011"); // Login with Margaret Reviewer user role 
				  Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
				  landingp_webe.lnk_Reports.click();
				  landingp_webe.lnk_ReviewerReports.click();
				  reports_webe.lnk_My_Productivity.click();
				  Log4J.logp.info("==== My Productivity Report for Reviewer ===" ); 
				  rowid = "SD012"; 
				  break;
				 */
				}
				bstatus = Reports_Lib.search_Data(rowid);
				Thread.sleep(3000);

			}
			Log4J.logp.info("************  Ending - searchData ********");
			Assert.assertTrue(bstatus, "The searchData passed successfully");

		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - searchData");
			Assert.assertTrue(false, "The searchData failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App(); // logout from Margaret user role
		}
	}

	/**
	 * 
	 * This script download PDF file from Reports page.
	 * 
	 * @author agupta
	 * @since 9/10/2014
	 */
	@Test(description = "ezCAC_MVP-3043 :: To check Supervisor can able to download the PDF file ", priority = 5)
	public static void download_PDF()
	{
		boolean bstatus;
		String rowid = null;
		try
		{
			Log4J.logp.info("************* Started - download_PDF ***************");
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			landingp_webe.lnk_All.click();
			landingp_webe.lnk_Reports.click();
			Thread.sleep(2000);
			reports_webe.lnk_All_Reports.click();
			Thread.sleep(2000);
			for (int i = 1; i <= 12; i++)
			{
				switch (i)
				{
					case 1:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coding_Completed.click();
						Log4J.logp.info("==== Coding Completed Cases Report ====");
						rowid = "PD001";
						break;
					case 2:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Pending_Cases.click();
						Log4J.logp.info("==== Pending Cases Report ====");
						rowid = "PD002";
						break;
					case 3:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coding_Quality.click();
						Log4J.logp.info("==== Coding Quality Report ====");
						rowid = "PD003";
						break;
					case 4:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_Physician.click();
						Log4J.logp.info("==== CDI Impact on Query by Physician ====");
						rowid = "PD004";
						break;
					case 5:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_CI.click();
						Log4J.logp.info("==== CDI Impact on Query by Clinical Indicators =====");
						rowid = "PD005";
						break;
					case 6:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coder_Performance_and_Accuracy.click();
						Log4J.logp.info("==== Coder Performance and Accuracy Report ====");
						rowid = "PD006";
						break;
					case 7:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Engine_Performance.click();
						Log4J.logp.info("==== Engine Performance Report ====");
						rowid = "PD007";
						break;
					case 8:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Reviewer_Completed_Cases.click();
						Log4J.logp.info("==== Reviewer Completed Cases Report ====");
						rowid = "PD008";
						break;
					case 9:
						landingp_webe.lnk_Cases.click();
						landingp_webe.lnk_CDI.click();
						landingp_webe.lnk_Reports.click();
						landingp_webe.lnk_CDIReports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_CI.click();
						Log4J.logp.info("==== CDI Impact on Query by Clinical Indicators ====");
						rowid = "PD010";
						break;
					case 10:
						reports_webe.lnk_CDI_Reports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_Physician.click();
						Log4J.logp.info("==== CDI Impact on Query by Physician ====");
						rowid = "PD011";
						break;
					case 11:
						Login_Lib.logOut_App(); // logout from Akash user role
						Thread.sleep(2000);
						Login_Lib.login("ag010"); // Login with Patricia user role
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);

						landingp_webe.lnk_Reports.click();
						landingp_webe.lnk_CodingReports.click();
						reports_webe.lnk_My_Productivity.click();
						Log4J.logp.info("==== My Productivity Report for Coder====");
						rowid = "PD009";
						break;

					case 12:
						Login_Lib.logOut_App(); // logout from patricia user role
						Thread.sleep(2000);
						Login_Lib.login("ag011"); // Login with Margaret Reviewer user role
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
						landingp_webe.lnk_Reports.click();
						landingp_webe.lnk_ReviewerReports.click();
						reports_webe.lnk_My_Productivity.click();
						Log4J.logp.info("==== My Productivity Report for Reviewer ===");
						rowid = "PD012";
						break;
				}
				pdf_webe.lnk_PDF.click();
				Thread.sleep(2000);
				pdf_webe.lnk_Clear_All.click();
				Thread.sleep(2000);
				bstatus = Reports_Lib.download_PDF(rowid);
				Assert.assertTrue(bstatus, "The download_PDF passed");
			}
			Log4J.logp.info("************* Ending - download_PDF *************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - downloadPDF");
			Assert.assertTrue(false, "The download_PDF failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App(); // Logout from Margaret user role
		}
	}

	/**
	 * This script download Excel file from Reports page.
	 * 
	 * @author agupta
	 * @since 09/10/2014
	 */
	@Test(description = "ezCAC_MVP-3044 :: To check Supervisor can able to download the Excel file ", priority = 6)
	public static void download_Excel()
	{
		boolean bstatus = false;
		String rowid = null;
		try
		{
			Log4J.logp.info("**************** Started - download_Excel ***************");
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			landingp_webe.lnk_All.click();
			landingp_webe.lnk_Reports.click();
			Thread.sleep(2000);
			reports_webe.lnk_All_Reports.click();
			Thread.sleep(2000);
			for (int i = 1; i <= 12; i++)
			{
				switch (i)
				{
					case 1:
						reports_webe.lnk_Coding_Completed.click();
						Log4J.logp.info("==== Coding Completed Cases Report ====");
						rowid = "PD001";
						break;
					case 2:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Pending_Cases.click();
						Log4J.logp.info("==== Pending Cases Report ====");
						rowid = "PD002";
						break;
					case 3:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coding_Quality.click();
						Log4J.logp.info("==== Coding Quality Report ====");
						rowid = "PD003";
						break;
					case 4:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_Physician.click();
						Log4J.logp.info("==== CDI Impact on Query by Physician ====");
						rowid = "PD004";
						break;
					case 5:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_CI.click();
						Log4J.logp.info("==== CDI Impact on Query by Clinical Indicators =====");
						rowid = "PD005";
						break;
					case 6:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coder_Performance_and_Accuracy.click();
						Log4J.logp.info("==== Coder Performance and Accuracy Report ====");
						rowid = "PD006";
						break;
					case 7:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Engine_Performance.click();
						Log4J.logp.info("==== Engine Performance Report ====");
						rowid = "PD007";
						break;
					case 8:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Reviewer_Completed_Cases.click();
						Log4J.logp.info("==== Reviewer Completed Cases Report ====");
						rowid = "PD008";
						break;
					case 9:
						landingp_webe.lnk_Cases.click();
						landingp_webe.lnk_CDI.click();
						landingp_webe.lnk_Reports.click();
						landingp_webe.lnk_CDIReports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_CI.click();
						Log4J.logp.info("==== CDI Impact on Query by Clinical Indicators ====");
						rowid = "PD010";
						break;
					case 10:
						reports_webe.lnk_CDI_Reports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_Physician.click();
						Log4J.logp.info("==== CDI Impact on Query by Physician ====");
						rowid = "PD011";
						break;
					case 11:
						Login_Lib.logOut_App(); // logout from Akash user role
						Thread.sleep(2000);
						Login_Lib.login("ag010"); // Login with Patricia user role
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
						landingp_webe.lnk_Reports.click();
						landingp_webe.lnk_CodingReports.click();
						reports_webe.lnk_My_Productivity.click();
						Log4J.logp.info("==== My Productivity Report for Coder====");
						rowid = "PD009";
						break;
					case 12:
						Login_Lib.logOut_App(); // logout from patricia user role
						Thread.sleep(2000);
						Login_Lib.login("ag011"); // Login with Margaret Reviewer user role
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
						landingp_webe.lnk_Reports.click();
						landingp_webe.lnk_ReviewerReports.click();
						reports_webe.lnk_My_Productivity.click();
						Log4J.logp.info("==== My Productivity Report for Reviewer ===");
						rowid = "PD012";
						break;
				}
				excel_webe.lnk_Excel.click();
				Thread.sleep(2000);
				bstatus = Reports_Lib.download_Excel(rowid);
				Assert.assertTrue(bstatus, "The download_Excel passed");
			}
			Log4J.logp.info("*************** Ended - download_Excel ****************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - downloadExcel");
			Assert.assertTrue(false, "download_Excel failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App(); // Logout from Margaret user role
		}
	}

	/**
	 * This Script check Show/Hide Columns in Report page
	 * 
	 * @author agupta
	 * @since 10/10/2014
	 */
	@Test(description = "ezCAC_MVP-3045 :: To check Show/Hide Columns in Report Page", priority = 7)
	public static void saveShowHideColumns()
	{
		boolean bstatus = false;
		String rowid = null;
		try
		{
			Log4J.logp.info("************* Started - saveShowHideColumns ************");
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			landingp_webe.lnk_All.click();
			landingp_webe.lnk_Reports.click();
			reports_webe.lnk_All_Reports.click();
			for (int i = 1; i <= 10; i++)
			{

				switch (i)
				{
					case 1:
						reports_webe.lnk_Coding_Completed.click();
						Log4J.logp.info("====== Coding Completed Report ======\n");
						Reports_Lib.search_Data("SD013");
						rowid = "SS001";
						break;
					case 2:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Pending_Cases.click();
						Log4J.logp.info("====== Pending Cases Report ======\n");
						Reports_Lib.search_Data("SD013");
						rowid = "SS002";
						break;
					case 3:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coding_Quality.click();
						Log4J.logp.info("====== Coding Quality Report ======\n");
						Reports_Lib.search_Data("SD013");
						rowid = "SS003";
						break;
					case 4:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_Physician.click();
						Log4J.logp.info("====== CDI Impact on Query by Physician ======\n");
						rowid = "SS004";
						break;
					case 5:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_CI.click();
						Log4J.logp.info("====== CDI Impact on Query by Clinical Indicators ======\n");
						rowid = "SS005";
						break;
					case 6:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coder_Performance_and_Accuracy.click();
						Log4J.logp.info("====== Coder Performamce and Accuracy Report ======\n");
						Reports_Lib.search_Data("SD013");
						rowid = "SS006";
						break;
					case 7:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Engine_Performance.click();
						Log4J.logp.info("====== Engine Performance Report ======\n");
						rowid = "SS007";
						break;
					case 8:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Reviewer_Completed_Cases.click();
						Log4J.logp.info("====== Reviewer Completed Cases Report ======\n");
						Reports_Lib.search_Data("SD013");
						rowid = "SS008";
						break;
					case 9:
						Thread.sleep(2000);
						landingp_webe.lnk_Cases.click();
						landingp_webe.lnk_CDI.click();
						landingp_webe.lnk_Reports.click();
						landingp_webe.lnk_CDIReports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_Physician.click();
						Log4J.logp.info("==== CDI Impact on Query by Physician ====");
						rowid = "SS010";
						break;
					case 10:
						landingp_webe.lnk_CDIReports.click();
						reports_webe.lnk_CDI_Impact_on_Query_by_CI.click();
						Log4J.logp.info("==== CDI Impact on Query by Clinical Indicators ====");
						rowid = "SS011";
						break;

				// Data is not generate for My Productivity Report coder and Reviewer
				/*
				  case 11: 
				  
				  Thread.sleep(2000);
				  Login_Lib.logOut_App(); // logout from Akash user role 
				  Thread.sleep(2000);
				  Login_Lib.login("ag010"); // Login with Patricia user role
				  Thread.sleep(2000); 
				  landingp_webe.lnk_Reports.click();
				  landingp_webe.lnk_CodingReports.click();
				  reports_webe.lnk_My_Productivity.click();
				  Log4J.logp.info("==== My Productivity Report for Coder====");
				  Reports_Lib.search_Data("SD013"); 
				  rowid = "SS009"; 
				  break;
				  
				  case 12: 
				  Thread.sleep(2000); 
				  Login_Lib.logOut_App(); // logout from patricia user role 
				  Thread.sleep(2000);
				  Login_Lib.login("ag011"); // Login with Margaret Reviewer user role 
				  Thread.sleep(2000);
				  landingp_webe.lnk_Reports.click();
				  landingp_webe.lnk_ReviewerReports.click();
				  reports_webe.lnk_My_Productivity.click();
				  Log4J.logp.info("==== My Productivity Report for Reviewer ==="); 
				  Reports_Lib.search_Data("SD013"); 
				  rowid = "SS012"; 
				  break;
				 */
				}
				Thread.sleep(2000);
				showHideColumn_webe.lnk_Show_Hide_Columns.click();
				Thread.sleep(2000);
				bstatus = Reports_Lib.hide_Columns(rowid);
				Thread.sleep(2000);
				showHideColumn_webe.lnk_Show_Hide_Columns.click();
				Thread.sleep(2000);
				Reports_Lib.show_Columns(rowid);
			}
			Log4J.logp.info("***************** Ending - saveShowHideColumns *********************");
			Assert.assertTrue(bstatus, "The saveShowHideColumns passed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - saveShowHideColumns");
			Assert.assertTrue(false, "The saveShowHideColumns failed");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App(); // Logout from Margaret user role
		}
	}

	/**
	 * This script is to check that Date Field control for all reports
	 * 
	 * @author agupta
	 * @since 10/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-3043 :: To check Date field control for all reports", priority = 8)
	public static void dateField()
	{
		try
		{
			Log4J.logp.info("********** Started ::: dateField **************");
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			landingp_webe.lnk_Reports.click();
			Thread.sleep(2000);
			landingp_webe.lnk_AllReports.click();
			Thread.sleep(2000);
			for (int i = 1; i <= 7; i++)
			{
				switch (i)
				{
					case 1:
						reports_webe.lnk_Coding_Completed.click();
						Log4J.logp.info("==== Coding Completed Report ====");
						Reports_Lib.dischargeDate();
						Reports_Lib.codingCompletedDate();
						break;
					case 2:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Pending_Cases.click();
						Log4J.logp.info("==== Pending Cases Report ====");
						Reports_Lib.dischargeDate();
						break;
					case 3:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coding_Quality.click();
						Log4J.logp.info("==== Coding Quality Report ====");
						Reports_Lib.dischargeDate();
						Reports_Lib.codingCompletedDate();
						break;
					case 4:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coder_Performance_and_Accuracy.click();
						Log4J.logp.info("==== Coder Performance and Accuracy Report ====");
						Reports_Lib.dischargeDate();
						Reports_Lib.codingCompletedDate();
						break;
					case 5:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Reviewer_Completed_Cases.click();
						Log4J.logp.info("==== Reviewer Completed Cases Report ====");
						Reports_Lib.dischargeDate();
						Reports_Lib.codingCompletedDate();
						break;
					case 6:
						Login_Lib.logOut_App(); // logout from Akash user role
						Thread.sleep(2000);
						Login_Lib.login("ag010"); // Login with Patricia user role
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
						landingp_webe.lnk_Reports.click();
						reports_webe.lnk_Coding_Reports.click();
						reports_webe.lnk_My_Productivity.click();
						Log4J.logp.info("==== My Productivity Report for Coder");
						Reports_Lib.codingCompletedDate();
						break;
					case 7:
						Login_Lib.logOut_App(); // logout from patricia user role
						Thread.sleep(2000);
						Login_Lib.login("ag011"); // Login with Margaret Reviewer user role
						Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
						landingp_webe.lnk_Reports.click();
						landingp_webe.lnk_ReviewerReports.click();
						reports_webe.lnk_My_Productivity.click();
						Log4J.logp.info("==== My Productivity Report for Reviewer ====");
						Reports_Lib.codingCompletedDate();
						break;
				}
			}
			Log4J.logp.info("**************** Ended ::: dateField *******************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: dateField");
			Assert.assertTrue(false, "Problem found in :: dateField");
			e.printStackTrace();
		}
		finally
		{
			Login_Lib.logOut_App(); // Logout from Margaret user role
		}
	}

	/**
	 * This script is to check that when user selects "Current Month" and "Last Week" option from Discharge Date then Last Month option should be disabled in
	 * Coding Completed Date dropdown
	 * 
	 * @author agupta
	 * @since 10/13/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-1639 :: To check when user selects Current month from Discharge Date dropdown menu" + "ezCAC_MVP_Reg-1640 :: To check when user selects Last Week option from Discharge Date", priority = 9)
	public static void disabled_LastMonth()
	{
		try
		{
			Log4J.logp.info("************** Started ::: disabled_LastMonth ****************");
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			landingp_webe.lnk_All.click();
			landingp_webe.lnk_Reports.click();
			landingp_webe.lnk_AllReports.click();
			Thread.sleep(2000);
			for (int i = 1; i <= 4; i++)
			{
				switch (i)
				{
					case 1:
						reports_webe.lnk_Coding_Completed.click();
						Thread.sleep(2000);
						Log4J.logp.info("==== Coding Completed Cases Report ====");
						break;
					case 2:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coding_Quality.click();
						Thread.sleep(2000);
						Log4J.logp.info("==== Coding Quality Report ====");
						break;
					case 3:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Coder_Performance_and_Accuracy.click();
						Thread.sleep(2000);
						Log4J.logp.info("==== Coder Performance and Accuracy Report ====");
						break;
					case 4:
						reports_webe.lnk_All_Reports.click();
						reports_webe.lnk_Reviewer_Completed_Cases.click();
						Thread.sleep(2000);
						Log4J.logp.info("==== Reviewer Completed Cases Report ====");
						break;
				}
				Log4J.logp.info("Select Current Month");
				for (int j = 0; j < 2; j++)
				{
					Reports_Lib.search_Data("SD02" + j);
					boolean b = Common_Lib.checkElementPresent(reports_webe.dis_LastMonth);
					if (b == true)
					{
						Log4J.logp.info("Last Month is Disabled");
						Assert.assertTrue(true, "Last Month is Disabled");
					}
					Log4J.logp.info("Select Last Week");
				}
			}
			Log4J.logp.info("*********** Ended ::: disabled_LastMonth ******************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: disabled_LastMonth");
			Assert.assertTrue(false, "Problem found in :: disabled_LastMonth");
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void ReportsAfterClass()
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
			if (showHideColumn_webe != null)
			{
				showHideColumn_webe = null;
			}
			if (reports_webe != null)
			{
				reports_webe = null;
			}
			if (pdf_webe != null)
			{
				pdf_webe = null;
			}
			if (excel_webe != null)
			{
				excel_webe = null;
			}
			if (issuepnl_webe != null)
			{
				issuepnl_webe = null;
			}
			if (medicalrecordpnl_webe != null)
			{
				medicalrecordpnl_webe = null;
			}
			if (common_webe != null)
			{
				common_webe = null;
			}
			if (groupingpnl_webe != null)
			{
				groupingpnl_webe = null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
