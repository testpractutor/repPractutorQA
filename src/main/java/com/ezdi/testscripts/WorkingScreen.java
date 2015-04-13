package com.ezdi.testscripts;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import com.ezdi.library.ManualAllocation_Lib;
import com.ezdi.library.MessageCenter_Lib;
import com.ezdi.library.SearchCriteria_Lib;
import com.ezdi.library.WorkingScreen_Lib;
import com.ezdi.webelements.AbstractPnl_WebE;
import com.ezdi.webelements.CodeBook_WebeE;
import com.ezdi.webelements.CodingPnl_WebE;
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

/**
 * 
 * @author skhalasi
 * @author agupta
 * @author fmodi
 * @author jsolanki
 *
 */
public class WorkingScreen
{

	public static WebDriver				driver;
	public static LandingP_WebE			landingp_webe;
	public static WebDriverWait			wait;
	public static Comman_WebE			common_webe;
	public static GroupinPnl_WebE		groupingpnl_webe;
	public static DemographicPnl_WebE	demographicpnl_webe;
	public static IssuePnl_WebE			issuepnl_webe;
	public static AbstractPnl_WebE		abstractpnl_webe;
	public static CodingPnl_WebE		codingPnl_webe;
	public static CodeBook_WebeE		codeBook_webe;
	public static MedicalRecordPnl_WebE	medicalrecordpnl_webe;
	public static SearchCriteria_WebE	searchCriteria_webe;
	public static MessageCenter_WebE	messagecenter_webe;
	public static ViewCase_WebE			viewcase_webe;
	public static Login_WebE			login_webe;

	@BeforeClass
	public static void WorkingScreenBeforeClass()
	{
		/*String strUname;
		String strPass;
		Map<String, String> rowTestData = null;*/
		try
		{
			driver = ExecutionSetup.getDriver();
			landingp_webe = LandingP_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			demographicpnl_webe = DemographicPnl_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			abstractpnl_webe = AbstractPnl_WebE.getInstance(driver);
			codingPnl_webe = CodingPnl_WebE.getInstance(driver);
			codeBook_webe = CodeBook_WebeE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			messagecenter_webe = MessageCenter_WebE.getInstance(driver);
			viewcase_webe = ViewCase_WebE.getInstance(driver);
			login_webe = Login_WebE.getInstance(driver);

			//Logout first Current User
			/*Thread.sleep(2000);
			Login_Lib.logOut_App();*/

			/*JDBCMySql getLoginData = new JDBCMySql();

			rowTestData = getLoginData.getRowbyID("td_logindata", "sk001");

			strUname = rowTestData.get("userName");
			strPass = rowTestData.get("passWord");
			
			Login_Lib.logIn_App(strUname, strPass);*/
			Login_Lib.login("sk001");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * This method is for add code for in patient case in ICD-9 and ICD-10
	 * 
	 * We get data from "td_searchcode" , "td_searchcase"
	 * 
	 * @author skhalasi
	 * @since 08/09/2014
	 */
	@Test(description = "ezCAC_MVP-2806:Verify that User can add code from Coding panel," + "ezCAC_ MVP-469:Verify that User can add diagnosis code from the medical doc. for Inpatient case"
			+ "ezCAC_ MVP-473:Verify that User can add procedure code from the medical doc. for Inpatient case in both coding sys." + "ezCAC_MVP-471:Verify that User can add diagnosis code from the medical doc. for ICD-10", priority = 0)
	public static void addnewCodeICD910Inpatient()
	{
		String getRowId = null;
		boolean bstatus = false;
		try
		{
			Thread.sleep(2000);

			Log4J.logp.info("***************  Started - addnewCodeICD9Inpatient  ***************");

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Common_Lib.openCase("sk007");

			Log4J.logp.info("addnewCodeICD9Inpatient -- case is opend");

			for (int i = 1; i <= 3; i++)
			{
				Log4J.logp.info("In addnewCodeICD9Inpatient for loop for \"" + i + "\"number of entry");

				switch (i)
				{
					case 1:
						getRowId = "sk001";
						break;
					case 2:
						getRowId = "sk002";
						break;
					case 3:
						getRowId = "sk003";
						break;
					default:
						Log4J.logp.info("Please check rowId In Database");

				}
				bstatus = WorkingScreen_Lib.addnewCode_withData_ICD9(getRowId);
				Thread.sleep(1000);
			}
			Common_Lib.verifyFlag(bstatus, "Add code in ICD9 with Inpatient case");
			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			for (int i = 1; i <= 3; i++)
			{
				Log4J.logp.info("In addnewCodeICD10Inpatient for loop for \"" + i + "\"number of entry");

				switch (i)
				{
					case 1:
						getRowId = "sk009";
						break;
					case 2:
						getRowId = "sk010";
						break;
					case 3:
						getRowId = "sk011";
						break;
					default:
						Log4J.logp.info("Please check rowId In Database");

				}
				bstatus = WorkingScreen_Lib.addnewCode_withData_ICD10(getRowId);
				Thread.sleep(1000);
			}
			Common_Lib.verifyFlag(bstatus, "Add code in ICD10 with Inpatient case");

			Log4J.logp.info("***************  Ending - addnewCodeICD9Inpatient  ***************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("***************  Problem Found in - addnewCodeICD9  ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "addnewCodeICD9Inpatient is failed");
		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method for add code in outpatient case in ICD-9 and ICD-10
	 * 
	 * We get data from "td_searchcode" , "td_searchcase"
	 * 
	 * @author skhalasi
	 * @since 08/09/2014
	 * 
	 */
	@Test(description = "ezCAC_ MVP-469:Verify that User can add diagnosis code from the medical doc. for Inpatient case and Outpatient Case" + "ezCAC_ MVP-474:Verify that User can add CPTcode from the medical doc. for Outpatient case"
			+ "ezCAC_ MVP-479:To verify E & M code adding functionality from the form, time based inputs and from the coding book" + "ezCAC_MVP-481:Verify that User can add HCPCS code from the coding panel"
			+ "ezCAC_MVP-473:Verify that User can add procedure code from the medical doc. for Inpatient case in both coding sys." + "ezCAC_MVP-471:Verify that User can add diagnosis code from the medical doc. for ICD-10"
			+ "ezCAC_MVP-472:Verify that User can add diagnosis code from the medical doc. for ICD-10 and Outpatient case", priority = 1)
	public static void addnewCodeICD910Outpatient()
	{
		String getRowId = null;
		boolean bstatus = false;
		try
		{
			Thread.sleep(2000);

			Log4J.logp.info("***************  Started - addnewCodeICD9Outpatient  ***************");

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Common_Lib.openCase("sk002");

			Log4J.logp.info("addnewCodeICD9Outpatient -- case is opend");

			for (int i = 1; i <= 5; i++)
			{
				Log4J.logp.info("In addnewCodeICD9 for loop for \"" + i + "\"number of entry");
				switch (i)
				{
					case 1:
						getRowId = "sk004";
						break;
					case 2:
						getRowId = "sk005";
						break;
					case 3:
						getRowId = "sk006";
						break;
					case 4:
						getRowId = "sk007";
						break;
					case 5:
						getRowId = "sk008";
						break;
					default:
						Log4J.logp.info("Please check rowId In Database");

				}
				bstatus = WorkingScreen_Lib.addnewCode_withData_ICD9(getRowId);
				Thread.sleep(1000);
			}
			Common_Lib.verifyFlag(bstatus, "Add code in ICD9 with Outpatient case");

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);
			for (int i = 1; i <= 5; i++)
			{
				Log4J.logp.info("In addnewCodeICD10Inpatient for loop for \"" + i + "\"number of entry");
				switch (i)
				{
					case 1:
						getRowId = "sk012";
						break;
					case 2:
						getRowId = "sk013";
						break;
					case 3:
						getRowId = "sk014";
						break;
					case 4:
						getRowId = "sk015";
						break;
					case 5:
						getRowId = "sk016";
						break;
					default:
						Log4J.logp.info("Please check rowId In Database");

				}
				bstatus = WorkingScreen_Lib.addnewCode_withData_ICD10(getRowId);
				Thread.sleep(1000);
			}
			Common_Lib.verifyFlag(bstatus, "Add code in ICD10 with Outpatient case");

			Log4J.logp.info("***************  Ending - addnewCodeICD9Outpatient  ***************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("***************  Problem Found in - addnewCodeICD9Outpatient  ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "addnewcodeICD9Outpatient is failed");
		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}
	}

	/**
	 * This method is for add E and M code from E and M form in coding panel.Only Outpatient case is require.
	 * 
	 * Assume select Box1=Home services , select box2=New Patient
	 * 
	 * We get data from "td_searchcode" , "td_searchcase" , "td_enmform"
	 * 
	 * @author skhalasi
	 * @since 11/09/2014
	 */
	@Test(description = "ezCAC_MVP-477:To verify E & M code adding functionality from the form, time based inputs and from the coding book", priority = 2)
	public static void addCodeEandMForm()
	{
		boolean bstatus = false;
		try
		{
			Log4J.logp.info("***************  Started - addCodeEandMForm  ***************");
			Thread.sleep(2000);

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);
			Common_Lib.openCase("sk002");
			Thread.sleep(1000);

			//ezCAC_MVP-478:Verify that User can add E & M code based on time inputs
			Log4J.logp.info("======= ezCAC_MVP-478:Verify that User can add E & M code based on time inputs =======");
			bstatus = WorkingScreen_Lib.add_EandM_With_Form("sk001");
			Common_Lib.verifyFlag(bstatus, "Add Time based E and M code");

			//ezCAC_MVP-477:Verify that User can add E & M code from the form
			Log4J.logp.info("======= ezCAC_MVP-477:Verify that User can add E & M code from the form =======");
			bstatus = WorkingScreen_Lib.add_EandM_With_Form("sk002");
			Common_Lib.verifyFlag(bstatus, "Add form based E and M code");

			Log4J.logp.info("***************  Ending - addCodeEandMForm  ***************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("***************   Problem Found in - addCodeEandMForm  ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "addCodeEandMForm is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This test case for check user can not add duplicate code.
	 * 
	 * Here we assume only ICD 9 inpatient case
	 * 
	 * We get data from "td_searchcode" , "td_searchcase"
	 * 
	 * @author skhalasi
	 * @since 09/09/2014
	 * */

	@Test(description = "ezCAC_MVP-823:Verify that user can not add duplicate code", priority = 3)
	public static void verifyDuplicateCode()
	{

		boolean bstatus = false;
		try
		{
			Log4J.logp.info("***************  Started - verifyDuplicateCode  ***************");
			Thread.sleep(2000);

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Common_Lib.openCase("sk007");
			WorkingScreen_Lib.addnewCode_withData_ICD9("sk020");
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.addnewCode_withData_ICD9("sk020");
			Common_Lib.verifyFlag(bstatus, "Add duplicate code");

			Log4J.logp.info("***************  Ending - verifyDuplicateCode  ***************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("***************  Problem Found in - verifyDuplicateCode  ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyDuplicateCode is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for accept , reject and modify code operations and also check reference panel.
	 * 
	 * Here we assume ICD 9 inpatient case
	 * 
	 * We get data from "td_searchcode" , "td_searchcase"
	 * 
	 * @author skhalasi
	 * @since 10/09/2014
	 */

	@Test(description = "ezCAC_MVP-461:By default, ICD-9 coding standard will be displayed to user" + "ezCAC_MVP-482:To verify accept, modify and reject functionality for all code types" + "ezCAC_MVP-523:Verify that User can make secondary diagnosis to principal diagnosis"
			+ "ezCAC_MVP-657:Verify that User can open reference panel from the code template", priority = 4)
	public static void evidenceOperation()
	{
		String getrowId = null;
		String getmodifyRowId = null;
		boolean bacceptStatus = false;
		boolean bmodifyStatus = false;
		boolean brejectStatus = false;
		boolean bprincipalStatus = false;
		try
		{
			Log4J.logp.info("***************  Started - evidenceOperation  ***************");
			Thread.sleep(2000);

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Common_Lib.openCase("sk002");
			getrowId = "sk018";
			getmodifyRowId = "sk019";

			//ezCAC_MVP-461:By default, ICD-9 coding standard will be displayed to user
			Log4J.logp.info("======= ezCAC_MVP-461:By default, ICD-9 coding standard will be displayed to user =======");

			if (!codingPnl_webe.lst_codeType.getText().equalsIgnoreCase("icd-9"))
			{
				Log4J.logp.error("By Default ICD9 code standard is not there");
				Assert.assertTrue(false, "By Default ICD9 code standard is not there");
			}
			else
			{
				Log4J.logp.info("By Default ICD9 code standard is there");
				Assert.assertTrue(true, "By Default ICD9 code standard is there");
			}

			//ezCAC_MVP-657:Verify that User can open reference panel from the code template
			Log4J.logp.info("======= ezCAC_MVP-657:Verify that User can open reference panel from the code template =======");

			abstractpnl_webe.lnk_Ref_Encoder.click();
			Thread.sleep(500);
			if (abstractpnl_webe.txt_SearchKeyword.isDisplayed())
			{
				Log4J.logp.info("Reference panel is open successfully");
				Assert.assertTrue(true, "Reference panel open succesfully");
			}
			else
			{
				Log4J.logp.error("Reference panel can not open successfully");
				Assert.assertTrue(false, "Reference panel can not open successfully");
			}

			abstractpnl_webe.lnk_Ref_Encoder.click();
			Thread.sleep(500);

			//rowId of td_searchcode

			//add code uncomment under
			WorkingScreen_Lib.addnewCode_withData_ICD9(getrowId);

			//uncomment this
			//ezCAC_MVP-482:Verify that User can accept code(s) from the coding panel
			Log4J.logp.info("======= ezCAC_MVP-482:Verify that User can accept code(s) from the coding panel =======");

			bacceptStatus = WorkingScreen_Lib.accept_Code(getrowId);
			Common_Lib.verifyFlag(bacceptStatus, "Accept code directly ");

			//un comment as under
			//ezCAC_MVP-483:Verify that User can modify code(s) from the coding panel
			//ezCAC_MVP-485:Verify that User can modify already accepted code
			Log4J.logp.info("======= ezCAC_MVP-483:Verify that User can modify code(s) from the coding panel =======");
			Log4J.logp.info("======= ezCAC_MVP-485:Verify that User can modify already accepted code =======");

			bmodifyStatus = WorkingScreen_Lib.modify_Code(getrowId, getmodifyRowId);
			Common_Lib.verifyFlag(bmodifyStatus, "Already accepted code modify ");

			//uncomment
			//ezCAC_MVP-487:Verify that After modify the code, user can accept the code
			Log4J.logp.info("======= ezCAC_MVP-487:Verify that After modify the code, user can accept the code =======");

			bacceptStatus = WorkingScreen_Lib.accept_Code(getmodifyRowId);
			Common_Lib.verifyFlag(bacceptStatus, "Already Modified code accept ");

			//uncomment
			//ezCAC_MVP-484:Verify that User can reject code(s) from the coding panel
			//ezCAC_MVP-488:Verify that After modify the code, user can reject the code

			Log4J.logp.info("======= ezCAC_MVP-484:Verify that User can reject code(s) from the coding panel =======");
			Log4J.logp.info("======= ezCAC_MVP-488:Verify that After modify the code, user can reject the code =======");

			brejectStatus = WorkingScreen_Lib.reject_Code(getmodifyRowId);
			Common_Lib.verifyFlag(brejectStatus, "Already accepted code reject ");

			//uncomment
			//ezCAC_MVP-489:Verify that User can accept already rejected code
			Log4J.logp.info("======= ezCAC_MVP-489:Verify that User can accept already rejected code =======");

			bacceptStatus = WorkingScreen_Lib.accept_Code(getmodifyRowId);
			Common_Lib.verifyFlag(bacceptStatus, "Already rejected code accept ");

			//uncomment
			//ezCAC_MVP-486:Verify that User can reject already accepted code
			Log4J.logp.info("======= ezCAC_MVP-486:Verify that User can reject already accepted code =======");

			brejectStatus = WorkingScreen_Lib.reject_Code(getmodifyRowId);
			Common_Lib.verifyFlag(brejectStatus, "Already accepted code reject ");

			//ezCAC_MVP-490:Verify that User can modify already rejected code
			Log4J.logp.info("======= ezCAC_MVP-490:Verify that User can modify already rejected code =======");

			bmodifyStatus = WorkingScreen_Lib.modify_Code(getmodifyRowId, getrowId);
			Common_Lib.verifyFlag(bmodifyStatus, "Already rejected code modify ");

			//ezCAC_MVP-523:Verify that User can make secondary diagnosis to principal diagnosis
			Log4J.logp.info("======= ezCAC_MVP-523:Verify that User can make secondary diagnosis to principal diagnosis =======");

			bprincipalStatus = WorkingScreen_Lib.principal_Code(getrowId);
			Common_Lib.verifyFlag(bprincipalStatus, "Make secondary diagnosis code to prinicipal code ");

			Log4J.logp.info("***************  Ending - evidenceOperation  ***************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("***************  Problem Found in - evidenceOperation  ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "evidenceOperation is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This test case for check user can copy evidence from secondary diagnosis to another div, Here we assume ICD 9 inpatient case and two code is already
	 * added to the secondary diagnosis and we have evidence added into code, add manual evidence also in first div of secondary daignosis Note : please add
	 * evidence from medical record pane
	 * 
	 * We get data from "td_searchcase"
	 * 
	 * @author skhalasi
	 * @since 11/09/2014
	 * */

	@Test(description = "ezCAC_MVP-511:Verify that User can copy and paste suggested evidence", priority = 5)
	public static void copyEvidence()
	{
		boolean bstatus = false;
		try
		{
			Log4J.logp.info("***************  Started - copyEvidence  ***************");
			Thread.sleep(2000);

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Common_Lib.openCase("sk004");
			Thread.sleep(5000);
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, common_webe.div_second_secondDia, 40);
			Thread.sleep(2000);
			Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 30);
			Thread.sleep(2000);
			//WorkingScreen_Lib.selecttext("2");

			bstatus = WorkingScreen_Lib.copy_EvidenceWithType("suggested");
			Common_Lib.verifyFlag(bstatus, "Sugested evidence copy ");

			/*	bstatus = WorkingScreen_Lib.copy_EvidenceWithType("manual");
				Common_Lib.verifyFlag(bstatus, "Manual evidence copy ");
			*/
			Log4J.logp.info("***************  Ending - copyEvidence  ***************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("***************  Problem Found in - copyEvidence  ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "copyEvidence is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for query to physician from suggested and manually added evidence. Assume we take only ICD-9-10 inpatient and outpatient case.
	 * 
	 * This is also cover "Discuss with colleague" and "Mark as Resolved". We check resolved multiple issues so we can safely say that this works with also
	 * single resolved issue .
	 * 
	 * Always take without issue case.
	 * 
	 * Always take fresh case if possible.
	 * 
	 * Please do not put "In progress" ,"Billed" and "Completed" case,
	 * 
	 * Always login with "All" and "Coder" both role
	 * 
	 * We get data from "td_searchcode" , "td_searchcase" , "td_senddiscussion" , "td_sendquery"
	 * 
	 * @author skhalasi
	 * @since 15/09/2014
	 */

	@Test(description = "ezCAC_MVP-491:To verify that user can able to raise different type of issue on suggested and manual evidence" + " ezCAC_MVP_Reg-506:Verify that User can resolve single/multiple issue(s) on evidence"
			+ " ezCAC_MVP_Reg-791:User can able to resolved query to physician on suggested/manual evidence", priority = 6)
	public static void queryAndDisscusWithResolveCodingPnl()
	{
		boolean bstatus = false;
		try
		{
			Log4J.logp.info("***************  Started - queryAndDisscusWithResolveCodingPnl  ***************");

			Thread.sleep(2000);

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(2000);

			//Uncomment this 
			//open inpatient case
			Common_Lib.openCase("sk005");
			Log4J.logp.info("====== In queryAndDisscusWithResolveCodingPnl - in Inpatient case for ICD9 ======");

			//ICD 9
			/*Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lst_first_admit_evi_arrow, 30);
			//to click on drop down first suggested evidence in admitting Icd9
			Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 40);
			Thread.sleep(1000);
			codingPnl_webe.lst_first_admit_evi_arrow.click();
			Thread.sleep(1000);
			//to click on query to physician  on first suggested evidence in admitting Icd9
			//driver.findElement(By.xpath("(//h2[@id='admit']/..//span[@class='brd code-status'])[1]//a[@id='coding_panel_add_query']")).click();
			codingPnl_webe.lnk_query_first_admit_evi.click();
			bstatus = IssuePanel_Lib.send_QueryToPhysician("sk001");*/

			//Uncomment this

			//query from first suggested evidence in admitting diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for suggested evidence in admitting diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first suggested evidence in admitting diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_discuss_first_admit_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for suggested evidence in admitting diagnosis for ICD9");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for suggested evidence in admitting diagnosis for ICD9");

			/*if (bstatus)
			{
				Log4J.logp.info("Query to physician in suggested evidence successfully done in admitting diagnosis for ICD9");
				Assert.assertTrue(true, "Query to physician in suggested evidence successfully done in admitting diagnosis for ICD9");
			}
			else
			{
				Log4J.logp.error("Query to physician in suggested evidence unsuccessfully done in admitting diagnosis for ICD9");
				Assert.assertTrue(false, "Query to physician in suggested evidence unsuccessfully done in admitting diagnosis for ICD9");
			}*/
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			/*//to click on drop down first manual evidence in admitting Icd9
			//driver.findElement(By.xpath("(//h2[@id='admit']/..//span[contains(@class,'brd code-status manual')])[1]//span[@class='code-status-drop-down']")).click();
			codingPnl_webe.lst_first_admit_man_evi_arrow.click();
			Thread.sleep(1000);
			//to click on query to physician  on first manual evidence in admitting Icd9
			//driver.findElement(By.xpath("(//h2[@id='admit']/..//span[contains(@class,'brd code-status manual')])[1]//a[@id='coding_panel_add_query']")).click();
			codingPnl_webe.lnk_query_first_man_admit_evi.click();
			bstatus = IssuePanel_Lib.send_QueryToPhysician("sk001");*/

			//Uncomment this

			//query from first manual evidence in admitting diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_admit_man_evi_arrow, codingPnl_webe.lnk_query_first_man_admit_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for manual evidence in admitting diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first manual evidence in admitting diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_admit_man_evi_arrow, codingPnl_webe.lnk_discuss_first_man_admit_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for manual evidence in admitting diagnosis for ICD9");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for manual evidence in admitting diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//Uncomment this

			//query from first suggested evidence in primary diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for suggested evidence in prinipal diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first suggested evidence in primary diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_discuss_first_Principal_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for sugessted evidence in principal diagnosis for ICD9");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for suggested evidence in principal diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			//uncomment this
			//query from first manual evidence in Principal diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_Principal_man_evi_arrow, codingPnl_webe.lnk_query_first_man_Principal_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for manual evidence in prinicipal diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first manual evidence in Principal diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_Principal_man_evi_arrow, codingPnl_webe.lnk_discuss_first_man_Principal_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for manual evidence in principal diagnosis for ICD9");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for manual evidence in principal diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//uncomment this

			//query from first suggested evidence in secondary diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for suggested evidence in secondary diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first suggested evidence in secondary diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_discuss_first_secondary_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for suggested evidence in secondary diagnosis for ICD9");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for suggested evidence in secondary diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//uncomment this
			//query from first manual evidence in secondary diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_secondary_man_evi_arrow, codingPnl_webe.lnk_query_first_man_secondary_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for manual evidence in secondary diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first manual evidence in secondary diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_secondary_man_evi_arrow, codingPnl_webe.lnk_discuss_first_man_secondary_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for manual evidence in secondary diagnosis for ICD9");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for manual evidence in secondary diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//uncomment this

			//query from first suggested evidence in procedure  in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for suggested evidence in procedure diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first suggested evidence in procedure  in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_discuss_first_procedure_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for suggested evidence in procedure for ICD9");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for suggested evidence in procedure for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//uncomment this
			//query from first manual evidence in procedure in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_procedure_man_evi_arrow, codingPnl_webe.lnk_query_first_man_procedure_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for manual evidence in procedure diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first manual evidence in procedure in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_procedure_man_evi_arrow, codingPnl_webe.lnk_discuss_first_man_procedure_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for manual evidence in procedure for ICD9");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for manual evidence in procedure for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			// Uncomment this

			//ICD 10
			Log4J.logp.info("====== In queryAndDisscusWithResolveCodingPnl - in Inpatient case for ICD10 ======");
			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			//Uncomment this

			//query from first suggested evidence in admitting diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for suggested evidence in admitting diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first suggested evidence in admitting diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_discuss_first_admit_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for suggested evidence in admitting diagnosis for ICD10");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for suggested evidence in admitting diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//Uncomment this

			//query from first manual evidence in admitting diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_admit_man_evi_arrow, codingPnl_webe.lnk_query_first_man_admit_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for manual evidence in admitting diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first manual evidence in admitting diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_admit_man_evi_arrow, codingPnl_webe.lnk_discuss_first_man_admit_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for manual evidence in admitting diagnosis for ICD10");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for manual evidence in admitting diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//Uncomment this

			//query from first suggested evidence in primary diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for suggested evidence in principal diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first suggested evidence in primary diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_discuss_first_Principal_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for suggested evidence in principal diagnosis for ICD10");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for suggested evidence in principal diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//uncomment this
			//query from first manual evidence in Principal diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_Principal_man_evi_arrow, codingPnl_webe.lnk_query_first_man_Principal_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for manual evidence in principal diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first manual evidence in Principal diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_Principal_man_evi_arrow, codingPnl_webe.lnk_discuss_first_man_Principal_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for manual evidence in principal diagnosis for ICD10");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for manual evidence in principal diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//uncomment this

			//query from first suggested evidence in secondary diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for suggested evidence in secondary diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first suggested evidence in secondary diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_discuss_first_secondary_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for suggested evidence in secondary diagnosis for ICD10");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for suggested evidence in secondary diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//uncomment this
			//query from first manual evidence in secondary diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_secondary_man_evi_arrow, codingPnl_webe.lnk_query_first_man_secondary_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for manual evidence in secondary diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first manual evidence in secondary diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_secondary_man_evi_arrow, codingPnl_webe.lnk_discuss_first_man_secondary_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for manual evidence in secondary diagnosis for ICD10");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for manual evidence in secondary diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//uncomment this

			//query from first suggested evidence in procedure  in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for suggested evidence in procedure  for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first suggested evidence in procedure  in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_discuss_first_procedure_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for suggested evidence in procedure for ICD10");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for suggested evidence in procedure for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//uncomment this
			//query from first manual evidence in procedure in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_procedure_man_evi_arrow, codingPnl_webe.lnk_query_first_man_procedure_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for manual evidence in procedure  for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first manual evidence in procedure in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_procedure_man_evi_arrow, codingPnl_webe.lnk_discuss_first_man_procedure_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for manual evidence in procedure for ICD10");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for manual evidence in procedure for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			Thread.sleep(1000);
			//Uncomment this
			//click on "Later" button for choose Outpatient case
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			//open out patient case
			Common_Lib.openCase("sk006");
			Thread.sleep(2000);
			Log4J.logp.info("====== In queryAndDisscusWithResolveCodingPnl - in Outpatient case for ICD9 ======");
			//ICD 9

			//Uncomment this
			//query from first suggested evidence in admitting diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for suggested evidence in admitting diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first suggested evidence in admitting diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_discuss_first_admit_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for suggested evidence in admitting diagnosis for ICD9");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for suggested evidence in admitting diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//Uncomment this

			//query from first manual evidence in admitting diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_admit_man_evi_arrow, codingPnl_webe.lnk_query_first_man_admit_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for manual evidence in admitting diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first manual evidence in admitting diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_admit_man_evi_arrow, codingPnl_webe.lnk_discuss_first_man_admit_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for manual evidence in admitting diagnosis for ICD9");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for manual evidence in admitting diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			//Uncomment this

			//query from first suggested evidence in primary diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for suggested evidence in principal diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first suggested evidence in primary diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_discuss_first_Principal_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for suggested evidence in principal diagnosis for ICD9");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for suggested evidence in principal diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//uncomment this
			//query from first manual evidence in Principal diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_Principal_man_evi_arrow, codingPnl_webe.lnk_query_first_man_Principal_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for manual evidence in principal diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first manual evidence in Principal diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_Principal_man_evi_arrow, codingPnl_webe.lnk_discuss_first_man_Principal_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for manual evidence in principal diagnosis for ICD9");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for manual evidence in principal diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//uncomment this

			//query from first suggested evidence in secondary diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for suggested evidence in secondary diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first suggested evidence in secondary diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_discuss_first_secondary_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for suggested evidence in secondary diagnosis for ICD9");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for suggested evidence in secondary diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//uncomment this
			//query from first manual evidence in secondary diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_secondary_man_evi_arrow, codingPnl_webe.lnk_query_first_man_secondary_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for manual evidence in secondary diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first manual evidence in secondary diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_secondary_man_evi_arrow, codingPnl_webe.lnk_discuss_first_man_secondary_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for manual evidence in secondary diagnosis for ICD9");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for manual evidence in secondary diagnosis for ICD9");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//ICD 10
			Log4J.logp.info("====== In queryAndDisscusWithResolveCodingPnl - in Outpatient case for ICD10 ======");

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			//Uncomment this

			//query from first suggested evidence in admitting diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for suggested evidence in admitting diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first suggested evidence in admitting diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_discuss_first_admit_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for suggested evidence in admitting diagnosis for ICD10");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for suggested evidence in admitting diagnosis for ICD10");
			bstatus = false;

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			bstatus = false;

			//Uncomment this

			//query from first manual evidence in admitting diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_admit_man_evi_arrow, codingPnl_webe.lnk_query_first_man_admit_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for manual evidence in admitting diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first manual evidence in admitting diagnosis in ICD9
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_admit_man_evi_arrow, codingPnl_webe.lnk_discuss_first_man_admit_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for manual evidence in admitting diagnosis for ICD10");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for manual evidence in admitting diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//Uncomment this

			//query from first suggested evidence in primary diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for suggested evidence in principal diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first suggested evidence in primary diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_discuss_first_Principal_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for suggested evidence in principal diagnosis for ICD10");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for suggested evidence in principal diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//uncomment this
			//query from first manual evidence in Principal diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_Principal_man_evi_arrow, codingPnl_webe.lnk_query_first_man_Principal_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for manual evidence in principal diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first manual evidence in Principal diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_Principal_man_evi_arrow, codingPnl_webe.lnk_discuss_first_man_Principal_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for manual evidence in principal diagnosis for ICD10");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for manual evidence in principal diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//uncomment this

			//query from first suggested evidence in secondary diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for suggested evidence in secondary diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first suggested evidence in secondary diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_discuss_first_secondary_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for suggested evidence in secondary diagnosis for ICD10");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for suggested evidence in secondary diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//uncomment this
			//query from first manual evidence in secondary diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_secondary_man_evi_arrow, codingPnl_webe.lnk_query_first_man_secondary_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for manual evidence in secondary diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first manual evidence in secondary diagnosis in ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_secondary_man_evi_arrow, codingPnl_webe.lnk_discuss_first_man_secondary_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for manual evidence in secondary diagnosis for ICD10");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for manual evidence in secondary diagnosis for ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//uncomment this

			//query from first suggested evidence in procedure (CPT)  in ICD9 and ICD10
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 30);
			Thread.sleep(500);
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for suggested evidence in procedure(CPT)  for ICD9 and ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first suggested evidence in procedure (CPT)  in ICD9 and ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_discuss_first_procedure_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for suggested evidence in procedure(CPT)  for ICD9 and ICD10");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for suggested evidence in procedure(CPT)  for ICD9 and ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//uncomment this
			//query from first manual evidence in procedure in ICD9 and ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("query", codingPnl_webe.lst_first_procedure_man_evi_arrow, codingPnl_webe.lnk_query_first_man_procedure_evi);
			Common_Lib.verifyFlag(bstatus, "Query to physician for manual evidence in procedure(CPT)  for ICD9 and ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//discussion from first manual evidence in procedure in ICD9 and ICD10
			bstatus = WorkingScreen_Lib.issue_CodingPnl("discuss", codingPnl_webe.lst_first_procedure_man_evi_arrow, codingPnl_webe.lnk_discuss_first_man_procedure_evi);
			Common_Lib.verifyFlag(bstatus, "Discuss with colleague for manual evidence in procedure(CPT)  for ICD9 and ICD10");

			//click on resolve and check it
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Issues resolve for manual evidence in procedure(CPT)  for ICD9 and ICD10");

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			Thread.sleep(1000);

			Log4J.logp.info("***************  Ending - queryAndDisscusWithResolveCodingPnl  ***************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("***************  Problem Found in - queryAndDisscusWithResolveCodingPnl  ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "queryAndDisscusWithResolveCodingPnl is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for remove all evidence in coding panel ,Here We assume ICD 9 inpatient - outpatient case.
	 * 
	 * Also we check case is not submitted after remove all evidence
	 * 
	 * We get data from "td_searchcase"
	 * 
	 * @author skhalasi
	 * @since 10/09/2014
	 */

	@Test(description = "ezCAC_MVP-2971:Verify that user can remove all the suggested and manually added evidence(s)" + "ezCAC_MVP-517:To verify remove evidence functionality with suggested/manual evidence"
			+ "ezCAC_MVP_Reg-2972:Verify that if user removes all the evidence(s) and try to submit the case", priority = 7)
	public static void removeAllEvidence()
	{
		boolean bstatus = false;
		try
		{
			Log4J.logp.info("***************  Started - removeAllEvidence  ***************");

			//Common_Lib.assignCase("sk010", "Mary Smith");
			//Thread.sleep(1000);
			//Common_Lib.assignCase("sk010", "Sagar Khalasi");
			//Thread.sleep(1000);
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Common_Lib.openCase("sk017");
			Thread.sleep(1000);

			//Here all suggested and manual evidence will remove,
			bstatus = WorkingScreen_Lib.remove_evidence("all");
			Common_Lib.verifyFlag(bstatus, "Remove all evidence ");
			Thread.sleep(2000);

			groupingpnl_webe.btn_Done.click();
			Thread.sleep(1000);
			groupingpnl_webe.lnk_GroupingPnl.click();
			Thread.sleep(500);
			if (groupingpnl_webe.lbl_DRGInOpenPanel.getText().equals("0"))
			{
				Log4J.logp.info("Outpatient Case is not submitted after remove all evidence");
				Assert.assertTrue(true, "Outpatient Case is not submitted after remove all evidence");
			}
			else
			{
				Log4J.logp.error("Outpatient Case is may be submitted after remove all evidence");
				Assert.assertTrue(false, "Outpatient Case is not may be submitted after remove all evidence");
			}
			Thread.sleep(1000);

			groupingpnl_webe.btn_Later.click();

			Thread.sleep(1000);

			Common_Lib.openCase("sk035");
			Thread.sleep(1000);

			//Here all suggested and manual evidence will remove,
			bstatus = WorkingScreen_Lib.remove_evidence("all");
			Common_Lib.verifyFlag(bstatus, "Remove all evidence ");
			Thread.sleep(2000);

			groupingpnl_webe.btn_Done.click();
			Thread.sleep(1000);
			groupingpnl_webe.lnk_GroupingPnl.click();
			Thread.sleep(500);
			if (groupingpnl_webe.lbl_DRGInOpenPanel.getText().equals("0"))
			{
				Log4J.logp.info("Inpatient Case is not submitted after remove all evidence");
				Assert.assertTrue(true, "Inpatient Case is not submitted after remove all evidence");
			}
			else
			{
				Log4J.logp.error("Inpatient Case is may be submitted after remove all evidence");
				Assert.assertTrue(false, "Inpatient Case is not may be submitted after remove all evidence");
			}
			Thread.sleep(1000);

			groupingpnl_webe.btn_Later.click();

			/*Uncomment you want remove all manual evidence
			 *here all manual evidence will remove
			bstatus = WorkingScreen_Lib.remove_evidence("manual");
			Common_Lib.verifyFlag(bstatus, "Remove suggested evidence ");
			bstatus = false;
			Thread.sleep(2000);*/

			/*Uncomment you want remove all manual evidence
			 *here all suggested evidence will remove
			bstatus = WorkingScreen_Lib.remove_evidence("manual");
			Common_Lib.verifyFlag(bstatus, "Remove suggested evidence ");
			bstatus = false;
			Thread.sleep(2000);*/

			//old code not usable but future use possible
			//Thread.sleep(2000);
			/*medicalrecordpnl_webe.lst_LeftNavigationMenu.click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("Discharge Summary")).click();
			Thread.sleep(1000);
			bstatus = WorkingScreen_Lib.remove_evidence(common_webe.lnk_sys_evidence);

			if (!bstatus)
			{
				Assert.assertTrue(false, "All system suggested evidence are not removed");
			}
			else
			{
				Log4J.logp.error("All evidence are removed from coding panel..");
				Assert.assertTrue(true, "All system suggested evidence are not removed");
			}
			*/
			Log4J.logp.info("***************  Ending - removeAllEvidence  ***************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("***************  Problem Found in - removeAllEvidence  ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "removeAllEvidence is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for single and multiple CDS query with modify probable and associated codes
	 * 
	 * We get data from "td_searchcase" , "td_cdiquery"
	 * 
	 * @author skhalasi
	 * @since 20/10/2014
	 * 
	 * */
	@Test(description = "ezCAC_MVP_Reg-2771:Verify that CDS user can raise query on normal associated code" + "ezCAC_MVP_Reg-2793:Verify that if CDS user modifies associated code" + "ezCAC_MVP_Reg-2795:Verify that if CDS user modifies probable code"
			+ "ezCAC_MVP_Reg-2810:Verify that user can not raise query on Probable code(s)" + "ezCAC_MVP_Reg-2786:Verify that if 1st probable code is modified and CDS adds new probable code"
			+ "ezCAC_MVP_Reg-2788:Verify that if 1st probable code is modified + accepted and CDS adds new probable code" + "ezCAC_MVP_Reg-620:Verify that CDI user can not add same code for associated and probable code", priority = 8)
	public static void CDSQuery()
	{
		String strReplyStatus = null;
		String strProbCodePrincipal = null;
		String strProbCodeSecondPrincipal = null;
		String strProbCodeSecondary = null;
		try
		{
			Log4J.logp.info("***************  Started - CDSQuery  ***************");
			Thread.sleep(3000);

			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Common_Lib.openCase("sk001");
			Thread.sleep(2000);
			//ICD 9
			Log4J.logp.info("====== In CDSQuery - in Inpatient case for ICD9 ======");

			//first suggested admitting evidence
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk001", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in admitting diagnosis for ICD9");
				WorkingScreen_Lib.checkQuryLinkPresent(codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);
				//modify codes
				WorkingScreen_Lib.modify_CDS_Code("sk001");
				Thread.sleep(500);
				WorkingScreen_Lib.modify_CDS_Code("sk010");
				Thread.sleep(500);
				//driver.findElement(By.xpath("(//div[contains(@id,'admitting-diagnosis-code')]//span[@id='code-accepted-disabled'])[2]")).click();
				codingPnl_webe.lnk_associatedAcceptAdmit.click();
				Thread.sleep(2000);
				//add another probable code
				WorkingScreen_Lib.CDS_QueryCodingPnl("sk018", codingPnl_webe.lnk_associatedAdmit_arrow, codingPnl_webe.lnk_associatedAdmit_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				//check only on probable code is accepted
				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableAdmit.size() == 1, "Accept Only one admitting diagnosis probable code");
				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk018", codingPnl_webe.lnk_associatedAdmit_arrow, codingPnl_webe.lnk_associatedAdmit_query);
				Thread.sleep(500);
				Common_Lib.verifyFlag(strReplyStatus.equals("sameCodeNotAdd"), "Add Duplicate Probable code");
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in admitting diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in primary diagnosis in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk002", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{

				Assert.assertTrue(true, "Query to physician for suggested evidence in prinipal diagnosis for ICD9");
				WorkingScreen_Lib.checkQuryLinkPresent(codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);
				WorkingScreen_Lib.modify_CDS_Code("sk002");
				Thread.sleep(500);
				WorkingScreen_Lib.modify_CDS_Code("sk011");
				Thread.sleep(500);
				strProbCodePrincipal = codingPnl_webe.lbl_firstPrincipalCodeNum.getText();
				Thread.sleep(1000);
				codingPnl_webe.lnk_associatedAcceptPrincipal.click();
				Thread.sleep(2000);
				WorkingScreen_Lib.CDS_QueryCodingPnl("sk019", codingPnl_webe.lnk_associatedPrincipal_arrow, codingPnl_webe.lnk_associatedPrincipal_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				strProbCodeSecondPrincipal = codingPnl_webe.lbl_secondPrincipalCodeNum.getText();
				Thread.sleep(1000);
				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbablePrincipal.size() == 1, "Accept Only one principale diagnosis probable code");
				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk019", codingPnl_webe.lnk_associatedPrincipal_arrow, codingPnl_webe.lnk_associatedPrincipal_query);
				Thread.sleep(500);
				Common_Lib.verifyFlag(strReplyStatus.equals("sameCodeNotAdd"), "Add Duplicate Probable code");
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in prinipal diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{

				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in secondary diagnosis in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk003", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{

				Assert.assertTrue(true, "Query to physician for suggested evidence in secondary diagnosis for ICD9");
				WorkingScreen_Lib.checkQuryLinkPresent(codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);
				WorkingScreen_Lib.modify_CDS_Code("sk003");
				Thread.sleep(500);
				WorkingScreen_Lib.modify_CDS_Code("sk012");
				Thread.sleep(500);
				codingPnl_webe.lnk_associatedAcceptSecondary.click();
				Thread.sleep(2000);
				WorkingScreen_Lib.CDS_QueryCodingPnl("sk020", codingPnl_webe.lnk_associatedSecondary_arrow, codingPnl_webe.lnk_associatedSecondary_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				strProbCodeSecondary = codingPnl_webe.lbl_firstSecondaryCodeNum.getText();
				Thread.sleep(1000);
				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableSecondary.size() == 1, "Accept Only one secondary diagnosis probable code");
				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk020", codingPnl_webe.lnk_associatedSecondary_arrow, codingPnl_webe.lnk_associatedSecondary_query);
				Thread.sleep(500);
				Common_Lib.verifyFlag(strReplyStatus.equals("sameCodeNotAdd"), "Add Duplicate Probable code");
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);
				Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 1000);
				Thread.sleep(1000);

				WorkingScreen_Lib.CDS_QueryWithCodetext(codingPnl_webe.lnk_associatedAdmit_arrow, codingPnl_webe.lnk_associatedAdmit_query, strProbCodePrincipal);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 60);
				Thread.sleep(1000);

				Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 60);
				Thread.sleep(1000);
				WorkingScreen_Lib.CDS_QueryWithCodetext(codingPnl_webe.lnk_associatedPrincipal_arrow, codingPnl_webe.lnk_associatedPrincipal_query, strProbCodeSecondary);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);

				WorkingScreen_Lib.CDS_QueryWithCodetext(codingPnl_webe.lnk_associatedSecondary_arrow, codingPnl_webe.lnk_associatedSecondary_query, strProbCodeSecondPrincipal);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in secondary diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in procedure  in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk004", codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in procedure diagnosis for ICD9");
				WorkingScreen_Lib.checkQuryLinkPresent(codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);
				WorkingScreen_Lib.modify_CDS_Code("sk004");
				Thread.sleep(500);
				WorkingScreen_Lib.modify_CDS_Code("sk013");
				Thread.sleep(500);
				codingPnl_webe.lnk_associatedAcceptProcedure.click();
				Thread.sleep(2000);
				WorkingScreen_Lib.CDS_QueryCodingPnl("sk021", codingPnl_webe.lnk_associatedProcedure_arrow, codingPnl_webe.lnk_associatedProcedure_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk021", codingPnl_webe.lnk_associatedProcedure_arrow, codingPnl_webe.lnk_associatedProcedure_query);
				Thread.sleep(500);
				Common_Lib.verifyFlag(strReplyStatus.equals("sameCodeNotAdd"), "Add Duplicate Probable code");
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);
				Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 20);
				Thread.sleep(1000);
				WorkingScreen_Lib.CDS_QueryWithCodetext(codingPnl_webe.lnk_assoSecondProcedure_arrow, codingPnl_webe.lnk_assoSecondProcedure_query, codingPnl_webe.lbl_secondProbCodeSecoProcedure.getText());
				Thread.sleep(1000);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in procedure diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//ICD 10
			Log4J.logp.info("====== In CDSQuery - in Inpatient case for ICD10 ======");

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(1000);

			//Uncomment this

			//query from first suggested evidence in admitting diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk005", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in admitting diagnosis for ICD10");
				WorkingScreen_Lib.checkQuryLinkPresent(codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);
				WorkingScreen_Lib.modify_CDS_Code("sk005");
				Thread.sleep(500);
				WorkingScreen_Lib.modify_CDS_Code("sk014");
				Thread.sleep(500);
				codingPnl_webe.lnk_associatedAcceptAdmit.click();
				Thread.sleep(2000);
				WorkingScreen_Lib.CDS_QueryCodingPnl("sk022", codingPnl_webe.lnk_associatedAdmit_arrow, codingPnl_webe.lnk_associatedAdmit_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableAdmit.size() == 1, "Accept Only one admitting diagnosis probable code");
				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk022", codingPnl_webe.lnk_associatedAdmit_arrow, codingPnl_webe.lnk_associatedAdmit_query);
				Thread.sleep(500);
				Common_Lib.verifyFlag(strReplyStatus.equals("sameCodeNotAdd"), "Add Duplicate Probable code");
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in admitting diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in primary diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk006", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{

				Assert.assertTrue(true, "Query to physician for suggested evidence in principal diagnosis for ICD10");
				WorkingScreen_Lib.checkQuryLinkPresent(codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);
				WorkingScreen_Lib.modify_CDS_Code("sk006");
				Thread.sleep(500);
				WorkingScreen_Lib.modify_CDS_Code("sk015");
				Thread.sleep(500);
				strProbCodePrincipal = codingPnl_webe.lbl_firstPrincipalCodeNum.getText();
				Thread.sleep(1000);
				codingPnl_webe.lnk_associatedAcceptPrincipal.click();
				Thread.sleep(2000);
				WorkingScreen_Lib.CDS_QueryCodingPnl("sk023", codingPnl_webe.lnk_associatedPrincipal_arrow, codingPnl_webe.lnk_associatedPrincipal_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				strProbCodeSecondPrincipal = codingPnl_webe.lbl_secondPrincipalCodeNum.getText();
				Thread.sleep(1000);
				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbablePrincipal.size() == 1, "Accept Only one principale diagnosis probable code");
				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk023", codingPnl_webe.lnk_associatedPrincipal_arrow, codingPnl_webe.lnk_associatedPrincipal_query);
				Thread.sleep(500);
				Common_Lib.verifyFlag(strReplyStatus.equals("sameCodeNotAdd"), "Add Duplicate Probable code");
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in principal diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in secondary diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk007", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{

				Assert.assertTrue(true, "Query to physician for suggested evidence in secondary diagnosis for ICD10");
				WorkingScreen_Lib.checkQuryLinkPresent(codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);
				WorkingScreen_Lib.modify_CDS_Code("sk007");
				Thread.sleep(500);
				WorkingScreen_Lib.modify_CDS_Code("sk016");
				Thread.sleep(500);
				codingPnl_webe.lnk_associatedAcceptSecondary.click();
				Thread.sleep(2000);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 7);
				Thread.sleep(500);
				WorkingScreen_Lib.CDS_QueryCodingPnl("sk024", codingPnl_webe.lnk_associatedSecondary_arrow, codingPnl_webe.lnk_associatedSecondary_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				strProbCodeSecondary = codingPnl_webe.lbl_firstSecondaryCodeNum.getText();
				Thread.sleep(1000);
				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableSecondary.size() == 1, "Accept Only one secondary diagnosis probable code");
				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk024", codingPnl_webe.lnk_associatedSecondary_arrow, codingPnl_webe.lnk_associatedSecondary_query);
				Thread.sleep(500);
				Common_Lib.verifyFlag(strReplyStatus.equals("sameCodeNotAdd"), "Add Duplicate Probable code");
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);
				Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 1000);
				Thread.sleep(1000);
				WorkingScreen_Lib.CDS_QueryWithCodetext(codingPnl_webe.lnk_associatedAdmit_arrow, codingPnl_webe.lnk_associatedAdmit_query, strProbCodePrincipal);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);
				WorkingScreen_Lib.CDS_QueryWithCodetext(codingPnl_webe.lnk_associatedPrincipal_arrow, codingPnl_webe.lnk_associatedPrincipal_query, strProbCodeSecondary);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);
				WorkingScreen_Lib.CDS_QueryWithCodetext(codingPnl_webe.lnk_associatedSecondary_arrow, codingPnl_webe.lnk_associatedSecondary_query, strProbCodeSecondPrincipal);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);
			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in secondary diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in procedure  in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk008", codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in procedure  for ICD10");
				WorkingScreen_Lib.checkQuryLinkPresent(codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);
				WorkingScreen_Lib.modify_CDS_Code("sk008");
				Thread.sleep(500);
				WorkingScreen_Lib.modify_CDS_Code("sk017");
				Thread.sleep(500);
				codingPnl_webe.lnk_associatedAcceptProcedure.click();
				Thread.sleep(2000);
				WorkingScreen_Lib.CDS_QueryCodingPnl("sk025", codingPnl_webe.lnk_associatedProcedure_arrow, codingPnl_webe.lnk_associatedProcedure_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk025", codingPnl_webe.lnk_associatedProcedure_arrow, codingPnl_webe.lnk_associatedProcedure_query);
				Thread.sleep(500);
				Common_Lib.verifyFlag(strReplyStatus.equals("sameCodeNotAdd"), "Add Duplicate Probable code");
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);
				Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 20);
				Thread.sleep(1000);

				/* Uncomment when new case available with second procedure code 
				* 
				* 
				* 	WorkingScreen_Lib.CDS_QueryWithCodetext(codingPnl_webe.lnk_assoSecondProcedure_arrow, codingPnl_webe.lnk_assoSecondProcedure_query, codingPnl_webe.lbl_secondProbCodeSecoProcedure.getText());
				Thread.sleep(1000);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);*/

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in procedure  for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			Thread.sleep(2000);
			//click on "Later" button for choose Outpatient case
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(3000);
			//open out patient case
			Common_Lib.openCase("sk014");
			Log4J.logp.info("====== In CDSQuery - in Outpatient case for ICD9 ======");
			Thread.sleep(2000);
			//ICD 9

			//Uncomment this
			//query from first suggested evidence in admitting diagnosis in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk009", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in admitting diagnosis for ICD9");
				WorkingScreen_Lib.checkQuryLinkPresent(codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);
				WorkingScreen_Lib.modify_CDS_Code("sk001");
				Thread.sleep(500);
				WorkingScreen_Lib.modify_CDS_Code("sk010");
				Thread.sleep(500);
				codingPnl_webe.lnk_associatedAcceptAdmit.click();
				Thread.sleep(2000);
				WorkingScreen_Lib.CDS_QueryCodingPnl("sk018", codingPnl_webe.lnk_associatedAdmit_arrow, codingPnl_webe.lnk_associatedAdmit_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableAdmit.size() == 1, "Accept Only one admitting diagnosis probable code");
				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk018", codingPnl_webe.lnk_associatedAdmit_arrow, codingPnl_webe.lnk_associatedAdmit_query);
				Thread.sleep(500);
				Common_Lib.verifyFlag(strReplyStatus.equals("sameCodeNotAdd"), "Add Duplicate Probable code");
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in admitting diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in primary diagnosis in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk010", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{

				Assert.assertTrue(true, "Query to physician for suggested evidence in principal diagnosis for ICD9");
				WorkingScreen_Lib.checkQuryLinkPresent(codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);
				WorkingScreen_Lib.modify_CDS_Code("sk002");
				Thread.sleep(500);
				WorkingScreen_Lib.modify_CDS_Code("sk011");
				Thread.sleep(500);
				strProbCodePrincipal = codingPnl_webe.lbl_firstPrincipalCodeNum.getText();
				Thread.sleep(1000);
				codingPnl_webe.lnk_associatedAcceptPrincipal.click();
				Thread.sleep(2000);
				WorkingScreen_Lib.CDS_QueryCodingPnl("sk019", codingPnl_webe.lnk_associatedPrincipal_arrow, codingPnl_webe.lnk_associatedPrincipal_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				strProbCodeSecondPrincipal = codingPnl_webe.lbl_secondPrincipalCodeNum.getText();
				Thread.sleep(1000);
				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbablePrincipal.size() == 1, "Accept Only one principale diagnosis probable code");
				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk019", codingPnl_webe.lnk_associatedPrincipal_arrow, codingPnl_webe.lnk_associatedPrincipal_query);
				Thread.sleep(500);
				Common_Lib.verifyFlag(strReplyStatus.equals("sameCodeNotAdd"), "Add Duplicate Probable code");
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in principal diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in secondary diagnosis in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk011", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{

				Assert.assertTrue(true, "Query to physician for suggested evidence in secondary diagnosis for ICD9");
				WorkingScreen_Lib.checkQuryLinkPresent(codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);
				WorkingScreen_Lib.modify_CDS_Code("sk003");
				Thread.sleep(500);
				WorkingScreen_Lib.modify_CDS_Code("sk012");
				Thread.sleep(500);
				codingPnl_webe.lnk_associatedAcceptSecondary.click();
				Thread.sleep(2000);
				Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 20);
				Thread.sleep(1000);
				WorkingScreen_Lib.CDS_QueryCodingPnl("sk020", codingPnl_webe.lnk_associatedSecondary_arrow, codingPnl_webe.lnk_associatedSecondary_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				strProbCodeSecondary = codingPnl_webe.lbl_firstSecondaryCodeNum.getText();
				Thread.sleep(1000);
				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableSecondary.size() == 1, "Accept Only one secondary diagnosis probable code");
				Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 20);
				Thread.sleep(1000);
				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk020", codingPnl_webe.lnk_associatedSecondary_arrow, codingPnl_webe.lnk_associatedSecondary_query);
				Thread.sleep(500);
				Common_Lib.verifyFlag(strReplyStatus.equals("sameCodeNotAdd"), "Add Duplicate Probable code");
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);
				Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 1000);
				Thread.sleep(1000);
				WorkingScreen_Lib.CDS_QueryWithCodetext(codingPnl_webe.lnk_associatedAdmit_arrow, codingPnl_webe.lnk_associatedAdmit_query, strProbCodePrincipal);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);
				WorkingScreen_Lib.CDS_QueryWithCodetext(codingPnl_webe.lnk_associatedPrincipal_arrow, codingPnl_webe.lnk_associatedPrincipal_query, strProbCodeSecondary);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);
				WorkingScreen_Lib.CDS_QueryWithCodetext(codingPnl_webe.lnk_associatedSecondary_arrow, codingPnl_webe.lnk_associatedSecondary_query, strProbCodeSecondPrincipal);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);
			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in secondary diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//ICD 10
			Log4J.logp.info("====== In CDSQuery - in Outpatient case for ICD10 ======");

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			//Uncomment this

			//query from first suggested evidence in admitting diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk012", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in admitting diagnosis for ICD10");
				WorkingScreen_Lib.checkQuryLinkPresent(codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);
				WorkingScreen_Lib.modify_CDS_Code("sk005");
				Thread.sleep(500);
				WorkingScreen_Lib.modify_CDS_Code("sk014");
				Thread.sleep(500);
				codingPnl_webe.lnk_associatedAcceptAdmit.click();
				Thread.sleep(2000);
				WorkingScreen_Lib.CDS_QueryCodingPnl("sk022", codingPnl_webe.lnk_associatedAdmit_arrow, codingPnl_webe.lnk_associatedAdmit_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableAdmit.size() == 1, "Accept Only one admitting diagnosis probable code");
				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk022", codingPnl_webe.lnk_associatedAdmit_arrow, codingPnl_webe.lnk_associatedAdmit_query);
				Thread.sleep(500);
				Common_Lib.verifyFlag(strReplyStatus.equals("sameCodeNotAdd"), "Add Duplicate Probable code");
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in admitting diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in primary diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk013", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{

				Assert.assertTrue(true, "Query to physician for suggested evidence in principal diagnosis for ICD10");
				WorkingScreen_Lib.checkQuryLinkPresent(codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);
				WorkingScreen_Lib.modify_CDS_Code("sk006");
				Thread.sleep(500);
				WorkingScreen_Lib.modify_CDS_Code("sk015");
				Thread.sleep(500);
				strProbCodePrincipal = codingPnl_webe.lbl_firstPrincipalCodeNum.getText();
				Thread.sleep(1000);
				codingPnl_webe.lnk_associatedAcceptPrincipal.click();
				Thread.sleep(2000);
				WorkingScreen_Lib.CDS_QueryCodingPnl("sk023", codingPnl_webe.lnk_associatedPrincipal_arrow, codingPnl_webe.lnk_associatedPrincipal_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				strProbCodeSecondPrincipal = codingPnl_webe.lbl_secondPrincipalCodeNum.getText();
				Thread.sleep(1000);
				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbablePrincipal.size() == 1, "Accept Only one principale diagnosis probable code");
				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk023", codingPnl_webe.lnk_associatedPrincipal_arrow, codingPnl_webe.lnk_associatedPrincipal_query);
				Thread.sleep(500);
				Common_Lib.verifyFlag(strReplyStatus.equals("sameCodeNotAdd"), "Add Duplicate Probable code");
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in principal diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in secondary diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk014", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{

				Assert.assertTrue(true, "Query to physician for suggested evidence in secondary diagnosis for ICD10");
				WorkingScreen_Lib.checkQuryLinkPresent(codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);
				WorkingScreen_Lib.modify_CDS_Code("sk007");
				Thread.sleep(500);
				WorkingScreen_Lib.modify_CDS_Code("sk016");
				Thread.sleep(500);
				codingPnl_webe.lnk_associatedAcceptSecondary.click();
				Thread.sleep(2000);
				Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 20);
				Thread.sleep(500);
				WorkingScreen_Lib.CDS_QueryCodingPnl("sk024", codingPnl_webe.lnk_associatedSecondary_arrow, codingPnl_webe.lnk_associatedSecondary_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				strProbCodeSecondary = codingPnl_webe.lbl_firstSecondaryCodeNum.getText();
				Thread.sleep(1000);
				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableSecondary.size() == 1, "Accept Only one secondary diagnosis probable code");
				Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 20);
				Thread.sleep(1000);
				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk024", codingPnl_webe.lnk_associatedSecondary_arrow, codingPnl_webe.lnk_associatedSecondary_query);
				Thread.sleep(500);
				Common_Lib.verifyFlag(strReplyStatus.equals("sameCodeNotAdd"), "Add Duplicate Probable code");
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);
				Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 1000);
				Thread.sleep(1000);
				WorkingScreen_Lib.CDS_QueryWithCodetext(codingPnl_webe.lnk_associatedAdmit_arrow, codingPnl_webe.lnk_associatedAdmit_query, strProbCodePrincipal);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);
				WorkingScreen_Lib.CDS_QueryWithCodetext(codingPnl_webe.lnk_associatedPrincipal_arrow, codingPnl_webe.lnk_associatedPrincipal_query, strProbCodeSecondary);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);
				WorkingScreen_Lib.CDS_QueryWithCodetext(codingPnl_webe.lnk_associatedSecondary_arrow, codingPnl_webe.lnk_associatedSecondary_query, strProbCodeSecondPrincipal);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);
			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in secondary diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in procedure (CPT)  in ICD9 and ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk015", codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in procedure(CPT)  for ICD9 and ICD10");
				WorkingScreen_Lib.checkQuryLinkPresent(codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);
				WorkingScreen_Lib.modify_CDS_Code("sk009");
				Thread.sleep(500);
				WorkingScreen_Lib.modify_CDS_Code("sk018");
				Thread.sleep(500);
				codingPnl_webe.lnk_associatedAcceptCPT.click();
				Thread.sleep(2000);
				WorkingScreen_Lib.CDS_QueryCodingPnl("sk026", codingPnl_webe.lnk_associatedProcedure_arrow, codingPnl_webe.lnk_associatedProcedure_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk026", codingPnl_webe.lnk_associatedProcedure_arrow, codingPnl_webe.lnk_associatedProcedure_query);
				Thread.sleep(500);
				Common_Lib.verifyFlag(strReplyStatus.equals("sameCodeNotAdd"), "Add Duplicate Probable code");
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(1000);
				Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 20);
				Thread.sleep(1000);
				WorkingScreen_Lib.CDS_QueryWithCodetext(codingPnl_webe.lnk_assoSecondProcedure_arrow, codingPnl_webe.lnk_assoSecondProcedure_query, codingPnl_webe.lbl_secondProbCodeSecoProcedure.getText());
				Thread.sleep(1000);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in procedure(CPT)  for ICD9 and ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			Thread.sleep(1000);

			Log4J.logp.info("***************  Ending - CDSQuery  ***************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("***************  Problem Found in - CDSQuery  ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "CDSQuery is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
			else
			{
				driver.navigate().back();
			}

		}

	}

	/**
	 * This method is for add discussion from Issue Panel and Reply on it and Resolve it
	 * 
	 * We get data from "td_searchcase" , "td_senddiscussion"
	 * 
	 * @author skhalasi
	 * @since 30/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-685:To verify user add discuss with colleague issue" + "ezCAC_MVP_Reg-711:Verify that user can be able to send reply" + "ezCAC_MVP_Reg-690:Verify that user can able to resolved discuss with colleague issue with click on 'Mark as Resolved'", priority = 9)
	public static void discussWithColleageIssuePnl()
	{
		boolean bstatus = false;
		try
		{
			Log4J.logp.info("***************  Started - discussWithColleageIssuePnl  ***************");
			Thread.sleep(2000);

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Common_Lib.openCase("sk018");
			Thread.sleep(1000);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.lnk_DiscussWithCol.click();
			Thread.sleep(500);

			IssuePanel_Lib.send_DiscussWithColleague("sk002");
			if (Common_Lib.checkElementPresent(issuepnl_webe.lst_error_IssuePnl))
			{
				Log4J.logp.info("Error message is present");
				Common_Lib.verifyFlag(true, "Add Question compulsory");
			}
			else
			{
				Log4J.logp.error("Error message is not present");
				Assert.assertTrue(false, "Not found any error message for question");
			}
			Thread.sleep(1000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.lnk_DiscussWithCol.click();
			Thread.sleep(500);

			IssuePanel_Lib.send_DiscussWithColleague("sk003");
			if (Common_Lib.checkElementPresent(issuepnl_webe.lst_error_IssuePnl))
			{
				Log4J.logp.info("Error message is present");
				Common_Lib.verifyFlag(true, "Choose colleague compulsory");
			}
			else
			{
				Log4J.logp.error("Error message is not present");
				Assert.assertTrue(false, "Not found any error message for colleague");
			}
			Thread.sleep(1000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.lnk_DiscussWithCol.click();
			Thread.sleep(500);
			Thread.sleep(1000);
			IssuePanel_Lib.send_DiscussWithColleague("sk001");

			if ((issuepnl_webe.lnk_markasresolved_lst.size()) != 0)
			{
				Log4J.logp.info("Mark as resolved link is visible");
				Common_Lib.verifyFlag(true, "Mark As Resolved  is display");
			}
			else
			{
				Log4J.logp.error("Mark as resolved link is not visible");
				Assert.assertTrue(false, "Mark As Resolved is not display");
			}
			if (!Common_Lib.checkElementPresent(issuepnl_webe.lbl_pending))
			{
				Assert.assertTrue(false, "Pending Link is not dispaly");
			}

			//ezCAC_MVP_Reg-711:Verify that user can be able to send reply
			Thread.sleep(500);
			//	issuepnl_webe.lnk_first_resolved.findElement(By.xpath("//ancestor::div[@class='issue-details discuss-ico']")).click();
			driver.findElement(By.xpath("//strong[text()='Discussion']")).click();

			Thread.sleep(500);
			issuepnl_webe.lnk_reply.click();

			Thread.sleep(500);
			issuepnl_webe.txt_replyText.sendKeys("");
			Thread.sleep(500);
			issuepnl_webe.btn_SendReply.click();
			if (!Common_Lib.IsCustomAlertPresent())
			{
				Log4J.logp.error("Not found any validation message");
				Assert.assertTrue(false, "Not found any validation message");
			}

			Thread.sleep(500);
			issuepnl_webe.txt_replyText.sendKeys("Test Reply in Disscuss");

			Thread.sleep(500);
			issuepnl_webe.btn_SendReply.click();
			Thread.sleep(500);

			//ezCAC_MVP_Reg-690:Verify that user can able to resolved discuss with colleague issue with click on 'Mark as Resolved'
			bstatus = WorkingScreen_Lib.resolve_Issue();
			Common_Lib.verifyFlag(bstatus, "Resolve discuss Issue");

			Thread.sleep(1000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(1000);
			Log4J.logp.info("***************  Ending - discussWithColleageIssuePnl  ***************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("***************  Problem Found in - discussWithColleageIssuePnl  ***************");
			e.printStackTrace();
			Assert.assertTrue(false, "discussWithColleageIssuePnl is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}

		}

	}

	/**
	 * This method is for checking features of Grouper and Pricer with Coder CDI and Reviewer role
	 * 
	 * We get data from "td_searchcase" , "td_cdsquery" , "td_logindata"
	 * 
	 * @author skhalasi
	 * @throws Exception
	 * @since 30/10/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2911:To verify that user can be able to change grouper and pricer" + " ezCAC_MVP_Reg-2915:To verify that CDS user can be able to change grouper and pricer with Initial and Working DRG"
			+ "ezCAC_MVP_Reg-2918 :Verify that user can not be able to submits the case after change grouper & pricer value", priority = 10)
	public static void grouperAndPricer() throws Exception
	{
		String strUname;
		String strPass;
		Map<String, String> rowTestData = null;
		String strReplyStatus = null;
		try
		{
			Log4J.logp.info("***************  Started - grouperAndPricer  ***************");
			Thread.sleep(2000);

			//for CDI
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Common_Lib.openCase("sk019");
			Thread.sleep(1000);
			//query from first suggested evidence in primary diagnosis in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk016", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in prinipal diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			strReplyStatus = null;
			Thread.sleep(1000);
			groupingpnl_webe.lnk_CMSVer.click();

			if (!groupingpnl_webe.selectedOptionGrouper.getText().equals("CMS ver 30.0"))
			{
				Assert.assertTrue(false, "Default grouper is not selected ");

			}

			if (!groupingpnl_webe.selectedOptionPricer.getText().equals("Medicare IPPS/Medicare Managed Care"))
			{
				Assert.assertTrue(false, "Default pricer is not selected ");

			}
			Thread.sleep(500);

			Select dropdown = new Select(groupingpnl_webe.lst_grouperSelect);
			dropdown.selectByVisibleText("CMS ver 31.0");
			Thread.sleep(500);
			groupingpnl_webe.btn_submitGrouper.click();
			Thread.sleep(1000);
			groupingpnl_webe.lnk_CMSVer.click();

			Thread.sleep(1000);
			if (!groupingpnl_webe.selectedOptionGrouper.getText().equals("CMS ver 31.0"))
			{
				Assert.assertTrue(false, "Default grouper is not selected ");

			}

			if (!groupingpnl_webe.selectedOptionPricer.getText().equals("Medicare IPPS/Medicare Managed Care"))
			{
				Assert.assertTrue(false, "Default pricer is not selected ");

			}
			groupingpnl_webe.btn_cancelGrouper.click();
			Thread.sleep(500);

			/*ICD 10 grouping is not working
			 * //to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);
			//query from first suggested evidence in primary diagnosis in ICD10
				replyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk017", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				if (replyStatus.equals("queryNotAdd"))
				{
					Assert.assertTrue(false, "Query to physician for suggested evidence in principal diagnosis for ICD10");
				}
				else if (replyStatus.equals("notFound"))
				{
					Log4J.logp.info("Evidence is not exists");
				}
				Thread.sleep(1000);
				groupingpnl_webe.lnk_CMSVer.click();

				if (!groupingpnl_webe.selectedOptionGrouper.getText().equals("CMS ver 31.0"))
				{
					Assert.assertTrue(false, "Default grouper is not selected ");

				}

				if (!groupingpnl_webe.selectedOptionPricer.getText().equals("Medicare IPPS/Medicare Managed Care"))
				{
					Assert.assertTrue(false, "Default pricer is not selected ");

				}
				Thread.sleep(500);
				Select dropdown1 = new Select(groupingpnl_webe.lst_grouperSelect);
				dropdown1.selectByVisibleText("CMS ver 30.0");
				Thread.sleep(500);
				groupingpnl_webe.btn_submitGrouper.click();
				Thread.sleep(1000);
				groupingpnl_webe.lnk_CMSVer.click();
				Thread.sleep(500);
				if (!groupingpnl_webe.selectedOptionGrouper.getText().equals("CMS ver 30.0"))
				{
					Assert.assertTrue(false, "Default grouper is not selected ");

				}

				if (!groupingpnl_webe.selectedOptionPricer.getText().equals("Medicare IPPS/Medicare Managed Care"))
				{
					Assert.assertTrue(false, "Default pricer is not selected ");

				}

				groupingpnl_webe.btn_cancelGrouper.click();
				Thread.sleep(500);

				
			*/
			Thread.sleep(1000);
			groupingpnl_webe.btn_Later.click();

			Thread.sleep(1000);
			//for Coder

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);

			Common_Lib.openCase("sk009");
			Thread.sleep(1000);

			//ICD9
			groupingpnl_webe.lnk_CMSVer.click();
			Thread.sleep(500);
			if (!groupingpnl_webe.selectedOptionGrouper.getText().equals("CMS ver 30.0"))
			{
				Assert.assertTrue(false, "Default grouper is not selected ");

			}

			if (!groupingpnl_webe.selectedOptionPricer.getText().equals("Medicare IPPS/Medicare Managed Care"))
			{
				Assert.assertTrue(false, "Default pricer is not selected ");

			}
			Thread.sleep(500);
			Select dropdown2 = new Select(groupingpnl_webe.lst_grouperSelect);
			dropdown2.selectByVisibleText("CMS ver 31.0");
			Thread.sleep(500);
			groupingpnl_webe.btn_submitGrouper.click();
			Thread.sleep(1000);
			groupingpnl_webe.lnk_CMSVer.click();
			Thread.sleep(500);

			if (!groupingpnl_webe.selectedOptionGrouper.getText().equals("CMS ver 31.0"))
			{
				Assert.assertTrue(false, "Default grouper is not selected ");

			}
			Thread.sleep(500);
			if (!driver.findElement(By.xpath("//option[contains(@value,'Medicare IPPS/Medicare Managed Care')]")).getText().equals("Medicare IPPS/Medicare Managed Care"))
			{
				Assert.assertTrue(false, "Default pricer is not selected ");

			}
			Thread.sleep(500);

			groupingpnl_webe.btn_cancelGrouper.click();
			Thread.sleep(1000);
			/*
			 * ICD10 grouper is not worrking
			 * 
			 * //to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(1000);

			groupingpnl_webe.lnk_CMSVer.click();

			if (!groupingpnl_webe.selectedOptionGrouper.getText().equals("CMS ver 31.0"))
			{
				Assert.assertTrue(false, "Default grouper is not selected ");

			}

			if (!groupingpnl_webe.selectedOptionPricer.getText().equals("Medicare IPPS/Medicare Managed Care"))
			{
				Assert.assertTrue(false, "Default pricer is not selected ");

			}
			Thread.sleep(500);
			Select dropdown3 = new Select(groupingpnl_webe.lst_grouperSelect);
			dropdown3.selectByVisibleText("CMS ver 30.0");
			Thread.sleep(500);
			groupingpnl_webe.btn_submitGrouper.click();
			Thread.sleep(1000);
			groupingpnl_webe.lnk_CMSVer.click();
			Thread.sleep(500);
			if (!groupingpnl_webe.selectedOptionGrouper.getText().equals("CMS ver 30.0"))
			{
				Assert.assertTrue(false, "Default grouper is not selected ");

			}

			if (!groupingpnl_webe.selectedOptionPricer.getText().equals("Medicare IPPS/Medicare Managed Care"))
			{
				Assert.assertTrue(false, "Default pricer is not selected ");

			}

			groupingpnl_webe.btn_submitGrouper.click();
			Thread.sleep(3000);
			*/
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.submit_case("sk005");

			Thread.sleep(2000);
			//logout from current user 
			Login_Lib.logOut_App();

			JDBCMySql getLoginData = new JDBCMySql();

			rowTestData = getLoginData.getRowbyID("td_logindata", "sk002");

			strUname = rowTestData.get("userName");
			strPass = rowTestData.get("passWord");

			//login with reviewer role
			Login_Lib.logIn_App(strUname, strPass);
			Thread.sleep(2000);
			landingp_webe.lnk_Review.click();
			Thread.sleep(2000);

			Common_Lib.openCase("sk009");
			Thread.sleep(1000);

			groupingpnl_webe.lnk_CMSVer.click();
			Thread.sleep(500);
			if (!groupingpnl_webe.selectedOptionGrouper.getText().equals("CMS ver 31.0"))
			{
				Assert.assertTrue(false, "Default grouper is not selected ");

			}

			if (!groupingpnl_webe.selectedOptionPricer.getText().equals("Medicare IPPS/Medicare Managed Care"))
			{
				Assert.assertTrue(false, "Default pricer is not selected ");

			}
			Thread.sleep(500);
			Select dropdown4 = new Select(groupingpnl_webe.lst_grouperSelect);
			dropdown4.selectByVisibleText("CMS ver 30.0");
			Thread.sleep(500);
			groupingpnl_webe.btn_submitGrouper.click();
			Thread.sleep(1000);
			groupingpnl_webe.lnk_CMSVer.click();
			Thread.sleep(500);

			if (!groupingpnl_webe.selectedOptionGrouper.getText().equals("CMS ver 30.0"))
			{
				Assert.assertTrue(false, "Default grouper is not selected ");

			}
			Thread.sleep(500);
			if (!driver.findElement(By.xpath("//option[contains(@value,'Medicare IPPS/Medicare Managed Care')]")).getText().equals("Medicare IPPS/Medicare Managed Care"))
			{
				Assert.assertTrue(false, "Default pricer is not selected ");

			}
			Thread.sleep(500);

			groupingpnl_webe.btn_cancelGrouper.click();
			Thread.sleep(500);

			/*ICD10 grouping is not working
			 * //to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(1000);

			groupingpnl_webe.lnk_CMSVer.click();

			if (!groupingpnl_webe.selectedOptionGrouper.getText().equals("CMS ver 31.0"))
			{
				Assert.assertTrue(false, "Default grouper is not selected ");

			}

			if (!groupingpnl_webe.selectedOptionPricer.getText().equals("Medicare IPPS/Medicare Managed Care"))
			{
				Assert.assertTrue(false, "Default pricer is not selected ");

			}
			Thread.sleep(500);
			Select dropdown5 = new Select(groupingpnl_webe.lst_grouperSelect);
			dropdown5.selectByVisibleText("CMS ver 30.0");
			Thread.sleep(500);
			groupingpnl_webe.btn_submitGrouper.click();
			Thread.sleep(1000);
			groupingpnl_webe.lnk_CMSVer.click();
			Thread.sleep(500);
			if (!groupingpnl_webe.selectedOptionGrouper.getText().equals("CMS ver 30.0"))
			{
				Assert.assertTrue(false, "Default grouper is not selected ");

			}

			if (!groupingpnl_webe.selectedOptionPricer.getText().equals("Medicare IPPS/Medicare Managed Care"))
			{
				Assert.assertTrue(false, "Default pricer is not selected ");

			}

			groupingpnl_webe.btn_submitGrouper.click();
			Thread.sleep(3000);*/
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			//CMS ver 30.0
			//Medicare IPPS/Medicare Managed Care
			//CMS ver 31.0
			//Medicare IPPS/Medicare Managed Care
			Thread.sleep(2000);
			Log4J.logp.info("**************  Ending - grouperAndPricer  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - grouperAndPricer  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "grouperAndPricer is failed");

		}
		finally
		{

			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("sk001");
			Thread.sleep(2000);
		}

	}

	/**
	 * This method is for check principal code rank with all secondary code
	 * 
	 * We get data from "td_searchcase"
	 * 
	 * @author skhalasi
	 * @since 24/11/2014
	 */
	@Test(description = " ezCAC_MVP_Reg-2879:To verify rank feature with make it principal and grouping activity", priority = 11)
	public static void verifyPrincipalRank()
	{
		int isecondCodeCount = 0;
		String strSecondCode = null;
		String strPrincipalCode = null;
		String strOldPrincipalCode = null;
		int icount = 0;
		String strChangedRank = null;
		try
		{
			Log4J.logp.info("**************  Started - verifyPrincipalRank  **************");
			Thread.sleep(2000);

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Common_Lib.openCase("sk020");
			Thread.sleep(1000);

			isecondCodeCount = driver.findElements(By.xpath("//div[@id='icd9-secondary-diagnosis-code']//div[@class='code-number']")).size();
			Log4J.logp.info("Total number of secondary code are =" + isecondCodeCount);

			while (!driver.findElements(By.id("make-it-principal")).isEmpty())
			{
				if (isecondCodeCount == 0)
				{
					break;
				}
				//old principal code
				WebElement oldPriCodeNumber = driver.findElement(By.xpath("//div[@id='icd9-primary-diagnosis-code']//div[contains(@class,'num-container num')]"));
				strOldPrincipalCode = oldPriCodeNumber.getText();
				Log4J.logp.info("old principal code is =" + strOldPrincipalCode);
				//make new primary
				WebElement priWebe = driver.findElements(By.id("make-it-principal")).get(0);
				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, priWebe, 20);

				Thread.sleep(500);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 15);
				// take new secondary
				WebElement codeNumber = priWebe.findElement(By.xpath(".//ancestor::div[@class='expand']//div[contains(@class,'num-container num')]"));
				strSecondCode = codeNumber.getText();
				Thread.sleep(500);
				priWebe.click();
				Thread.sleep(2000);

				//changed primary code
				WebElement webe = driver.findElement(By.xpath("//div[@id='icd9-primary-diagnosis-code']//span[@class='evidance-seq']"));
				WebElement priCodeNumber = driver.findElement(By.xpath("//div[@id='icd9-primary-diagnosis-code']//div[contains(@class,'num-container num')]"));
				strPrincipalCode = priCodeNumber.getText();

				if (!webe.getText().equals("1"))
				{

					Assert.assertTrue(false, "Found null ranking in Principal code");
				}

				if (strPrincipalCode.equals(strSecondCode))
				{
					Assert.assertTrue(true, "Secondary code has been updated to principal");
				}
				else
				{

					Assert.assertTrue(false, "Secondary code has not been updated to principal");
				}

				if (icount != 0)
				{
					List<WebElement> ls = driver.findElements(By.xpath("//div[@id='icd9-secondary-diagnosis-code']//div[@class='num-container num accepted-code']"));
					for (int j = 0; j < ls.size(); j++)
					{

						WebElement secWebeNew = driver.findElements(By.xpath("//div[text()='" + strOldPrincipalCode + "']//ancestor::div[@class='expand']//span[@class='evidance-seq']")).get(j);
						Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, secWebeNew, 12);
						Thread.sleep(500);
						strChangedRank = secWebeNew.getText();
						if (strChangedRank.equals("1"))
						{
							Assert.assertTrue(false, "Secondary code has rank 1");
							break;
						}

					}

				}
				icount++;
				isecondCodeCount--;
			}
			Log4J.logp.info("**************  Ending - verifyPrincipalRank  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyPrincipalRank  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyPrincipalRank is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for check "Case Submit with/without Route for Review"
	 * 
	 * We get data from "td_searchcase" , "td_submit_case"
	 * 
	 * @author skhalasi
	 * @since 03/11/2014
	 * 
	 * */
	@Test(description = "ezCAC_MVP_Reg-696:Coder can able to submit the case successfully with / without 'Request to Review'", priority = 12)
	public static void verifyRequestToReview()
	{
		String strNoOfResult = null;
		try
		{
			Log4J.logp.info("**************  Started - verifyRequestToReview  **************");
			Thread.sleep(2000);

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			//complete case
			WorkingScreen_Lib.submit_case("sk006");
			Thread.sleep(3000);
			SearchCriteria_Lib.search_Case("sk100");

			strNoOfResult = landingp_webe.no_caseOnTable.getText();
			if (strNoOfResult.contains("1"))
			{
				Assert.assertTrue(true, "We found one completed case");
			}
			else
			{
				Assert.assertTrue(false, "Case is not completed properly");
			}

			Thread.sleep(3000);
			searchCriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(500);
			//billed case
			Thread.sleep(1000);
			WorkingScreen_Lib.submit_case("sk007");
			Thread.sleep(3000);
			SearchCriteria_Lib.search_Case("sk101");

			strNoOfResult = landingp_webe.no_caseOnTable.getText();
			if (strNoOfResult.contains("1"))
			{
				Assert.assertTrue(true, "We found one billed case");
			}
			else
			{
				Assert.assertTrue(false, "Case is not billed properly");
			}

			Log4J.logp.info("**************  Ending - verifyRequestToReview  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyRequestToReview  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyRequestToReview is failed");

		}
		finally
		{
			driver.navigate().refresh();
		}

	}

	/**
	 * This method is for verify Later and Done behaviour with and without Issues
	 * 
	 * We get data from "td_searchcase" , "td_submit_case", "td_senddiscussion"
	 * 
	 * @author skhalasi
	 * @since 03/11/2014
	 * 
	 * */
	@Test(description = "ezCAC_MVP_Reg-700:To verify [Later] & [Done] button behavior with issues and edits", priority = 13)
	public static void verifyLaterDoneWithIssues()
	{
		String strNoOfResult = null;
		try
		{
			Log4J.logp.info("**************  Started - verifyLaterDoneWithIssues  **************");
			Thread.sleep(2000);

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			//check on hold status
			Common_Lib.openCase("sk023");
			Thread.sleep(1000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(1000);

			SearchCriteria_Lib.search_Case("sk102");
			strNoOfResult = landingp_webe.no_caseOnTable.getText();
			if (strNoOfResult.contains("1"))
			{
				Assert.assertTrue(true, "We found case is On Hold");
			}
			else
			{
				Assert.assertTrue(false, "We found case is not On Hold");
			}

			searchCriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(500);

			//Add issue
			Common_Lib.openCase("sk023");
			Thread.sleep(1000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.lnk_DiscussWithCol.click();
			Thread.sleep(500);

			IssuePanel_Lib.send_DiscussWithColleague("sk001");
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(1000);
			SearchCriteria_Lib.search_Case("sk103");
			strNoOfResult = landingp_webe.no_caseOnTable.getText();
			if (strNoOfResult.contains("1"))
			{
				Assert.assertTrue(true, "We found case is Pending");
			}
			else
			{
				Assert.assertTrue(false, "We found case is not Pending");
			}
			searchCriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(500);

			//resolve issues
			Common_Lib.openCase("sk023");
			Thread.sleep(1000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			WorkingScreen_Lib.resolve_Issue();
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(1000);

			SearchCriteria_Lib.search_Case("sk102");
			strNoOfResult = landingp_webe.no_caseOnTable.getText();
			if (strNoOfResult.contains("1"))
			{
				Assert.assertTrue(true, "We found case is On Hold");
			}
			else
			{
				Assert.assertTrue(false, "We found case is not On Hold");
			}
			searchCriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(500);

			//submit case
			WorkingScreen_Lib.submit_case("sk008");

			SearchCriteria_Lib.search_Case("sk104");
			strNoOfResult = landingp_webe.no_caseOnTable.getText();
			if (strNoOfResult.contains("1"))
			{
				Assert.assertTrue(true, "We found one completed case");
			}
			else
			{
				Assert.assertTrue(false, "Case is not completed properly");
			}
			strNoOfResult = null;

			searchCriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(1000);
			//Add issue
			Common_Lib.openCase("sk024");
			Thread.sleep(1000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.lnk_DiscussWithCol.click();
			Thread.sleep(500);

			IssuePanel_Lib.send_DiscussWithColleague("sk001");
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(1000);
			WorkingScreen_Lib.submit_case("sk009");
			Thread.sleep(500);

			if (WorkingScreen_Lib.warningStatus)
			{
				Assert.assertTrue(true, "Warning is present before submit case.");
			}
			else
			{
				Assert.assertTrue(false, "Warning is not present before submit case.");
			}
			Thread.sleep(500);
			SearchCriteria_Lib.search_Case("sk105");
			strNoOfResult = landingp_webe.no_caseOnTable.getText();
			if (strNoOfResult.contains("1"))
			{
				Assert.assertTrue(true, "We found one completed case");
			}
			else
			{
				Assert.assertTrue(false, "Case is not completed properly");
			}

			Log4J.logp.info("**************  Ending - verifyLaterDoneWithIssues  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyLaterDoneWithIssues  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyLaterDoneWithIssues is failed");

		}
		finally
		{
			driver.navigate().refresh();
		}

	}

	/**
	 * This method is to check that counter value of Issue option will be update with each new issues added or Resolved
	 * 
	 * @author agupta
	 * @since 17/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-712 : Verify that counter value of Issue option will be update with each new issues added or resolved", priority = 150)
	public static void issues_Counter()
	{
		boolean bstatus = true;
		try
		{
			Log4J.logp.info("************************ Started - issues_Counter **********************");
			Thread.sleep(2000);
			Login_Lib.logOut_App(); // Logout from Sagar user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash User role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("ip001");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);
			Log4J.logp.info("Issue count is = " + issuepnl_webe.txt_IssueCount.getText());
			if (issuepnl_webe.txt_IssueCount.getText().equalsIgnoreCase("0/0"))
			{
				Log4J.logp.info("No Issued in issue panel");
				Assert.assertTrue(true, "There are no any issue in Issue Panel");
			}
			else
			{
				bstatus = false;
			}
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(1000);
			issuepnl_webe.lnk_DiscussWithCol.click();
			IssuePanel_Lib.send_DiscussWithColleague("ip001");
			Thread.sleep(2000);
			Common_Lib.rightclick_system_sugggested_evidence();
			Thread.sleep(1000);
			medicalrecordpnl_webe.lnk_querytophysician.click();
			IssuePanel_Lib.send_QueryToPhysician("ip001");
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			Log4J.logp.info("After raise issue count is = " + issuepnl_webe.txt_IssueCount.getText());
			if (issuepnl_webe.txt_IssueCount.getText().equalsIgnoreCase("2/2"))
			{
				Log4J.logp.info("Counter value of Issues is increase with 2 issues");
				Assert.assertTrue(true, "There are issue in issue panel");
			}
			else
			{
				bstatus = false;
			}
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			issuepnl_webe.lnk_markasresolved.click();
			Thread.sleep(1000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			Log4J.logp.info("Issue count is = " + issuepnl_webe.txt_IssueCount.getText());
			if (issuepnl_webe.txt_IssueCount.getText().equalsIgnoreCase("1/2"))
			{
				Log4J.logp.info("Counter value is decrese with 1 count after resolved issue");
				Assert.assertTrue(true, "1 issue has been resolved in issue panel");
			}
			else
			{
				bstatus = false;
			}
			Log4J.logp.info("************************ Ended - issues_Counter **********************");
			Assert.assertTrue(bstatus, "issues_Conter Completed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in Issue counter");
			Assert.assertTrue(false, "Problem found in issue counter");
			e.printStackTrace();
		}
		finally
		{
			groupingpnl_webe.btn_Later.click();
		}

	}

	/**
	 * This script is to check that when user clicks on Discuss with colleagues option in issue panel
	 * 
	 * @author agupta
	 * @since 17/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg_682: Verify that when user clicks on 'Discuss With Colleagues' option ", priority = 151)
	public static void check_DiscussWithColleague()
	{
		try
		{
			Log4J.logp.info("************** Started :: check_DiscussWithColleague ***************");
			Thread.sleep(2000);
			/*Login_Lib.logOut_App(); // logout from sagar user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // login with akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);*/
			Common_Lib.openCase("ip001");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			issuepnl_webe.lnk_DiscussWithCol.click();
			Thread.sleep(2000);
			boolean chk_webe_ToColleague = Common_Lib.checkElementPresent(issuepnl_webe.lst_ToColleague);
			boolean chk_webe_Question = Common_Lib.checkElementPresent(issuepnl_webe.txt_Question);
			boolean chk_webe_Cancel = Common_Lib.checkElementPresent(issuepnl_webe.btn_canceldiscussion);
			boolean chk_webe_Send = Common_Lib.checkElementPresent(issuepnl_webe.btn_senddiscussion);
			if (chk_webe_ToColleague == true & chk_webe_Question == true & chk_webe_Cancel == true & chk_webe_Send == true)
			{
				Log4J.logp.info("To Colleagues Dropdown menu is Displayed");
				Log4J.logp.info("Your Question textbox  is Displayed");
				Log4J.logp.info("Cancel Button is Displayed is Displayed");
				Log4J.logp.info("Send Button is Displayed");
				Assert.assertTrue(true, "All fields Displayed in Discuss with Colleagues template");
			}
			else
			{
				Assert.assertTrue(false, "Discuss with Colleagues template is not displayed with all fields");
			}
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(1000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			Log4J.logp.info("************** Ended :: check_DiscussWithColleague ***************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in check_DiscussWithColleague");
			Assert.assertTrue(false, "Problem found in check_DiscussWithColleague");
			e.printStackTrace();
		}
		finally
		{
			groupingpnl_webe.btn_Later.click();
		}
	}

	/**
	 * This method is for when user get the reply from another colleagues
	 * 
	 * @author agupta
	 * @since 17/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg_689 : Verify that when user get the reply from another colleagues", priority = 152)
	public static void getReply()
	{
		String strReply;
		try
		{
			Log4J.logp.info("**************** Started :: getReply ***************");
			Thread.sleep(2000);
			/*Login_Lib.logOut_App(); // Logout from Sagar user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);*/
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("ip001");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "visibility", 15);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(1000);
			issuepnl_webe.lnk_DiscussWithCol.click();
			Thread.sleep(2000);
			IssuePanel_Lib.send_DiscussWithColleague("ip002");
			Thread.sleep(2000);
			Log4J.logp.info("Disucussion has been sent to colleagues successfully");
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			Login_Lib.logOut_App(); // Logout from Akash User role
			Thread.sleep(2000);
			Login_Lib.login("fm005"); // Login with Faiz user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			landingp_webe.lnk_MessageHeader.click();
			Thread.sleep(2000);
			MessageCenter_Lib.find_message("ag001", "open");
			Thread.sleep(2000);
			messagecenter_webe.btn_Reply.click();
			Thread.sleep(1000);
			messagecenter_webe.txt_Reply.sendKeys("Reply of Discuss with Colleagues issues....");
			Thread.sleep(1000);
			messagecenter_webe.btn_ReplySend.click();
			Thread.sleep(1000);
			Log4J.logp.info("Reply sent successfully");
			Thread.sleep(2000);
			Login_Lib.logOut_App();// Logout from  Faiz user
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("ip001");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "visibility", 15);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			if (issuepnl_webe.lbl_replied.isDisplayed())
			{
				Log4J.logp.info("Replied label is displayed for Discussion issue in issue panel.");
				Assert.assertTrue(true, "Replied label is displayed for Discussion issue in issue panel.");
			}
			issuepnl_webe.lbl_replied.click();
			Thread.sleep(2000);
			strReply = issuepnl_webe.txt_Reply.getText();
			Log4J.logp.info("Reply Message is = " + strReply);
			if (strReply.equalsIgnoreCase("Reply of Discuss with Colleagues issues...."))
			{
				Log4J.logp.info("Reply message is Displayed in Discussion With Colleagues template");
				Assert.assertTrue(true, "Replied is Displayed in Discuss With Collegues template");
			}
			else
			{
				Assert.assertTrue(false, "Reply message is not displayed in Discuss With Colleagues Template");
			}
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			Log4J.logp.info("**************** Ended :: getReply ***************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found In :: getReply");
			Assert.assertTrue(false, "Problem found In :: getReply");
			e.printStackTrace();
		}
		finally
		{
			groupingpnl_webe.btn_Later.click();
		}
	}

	/**
	 * This method is to check that user can able to resolved issue from discuss with colleagues view page
	 * 
	 * @author agupta
	 * @since 17/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-692 :: Verify that user can able to resolved the issue from Discussion With Colleagues view page", priority = 153)
	public static void resolved_ViewPage()
	{
		try
		{
			Log4J.logp.info("*************** Started :: resolved_viewPage**************************");
			Thread.sleep(2000);
			/*Login_Lib.logOut_App(); // Logout from Sagar user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lnk_Coding, "clickable", 2);*/
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("ip001");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(1000);
			issuepnl_webe.lnk_DiscussWithCol.click();
			Thread.sleep(2000);
			IssuePanel_Lib.send_DiscussWithColleague("ip002");
			Thread.sleep(2000);
			Log4J.logp.info("Disucussion has been sent to colleagues successfully");
			issuepnl_webe.lbl_pending.click();
			Thread.sleep(2000);
			Log4J.logp.info("Discuss With Colleagues Issue has been opened successfully.....");
			issuepnl_webe.chk_Resolved.click();
			Thread.sleep(2000);
			issuepnl_webe.btn_OK.click();
			Thread.sleep(2000);
			Log4J.logp.info("Discuss With Colleagues Issue resolved from view page successfully......... ");
			if (issuepnl_webe.img_resolveddiscussion.isDisplayed())
			{
				Log4J.logp.info("Discussion Issue is resolved in issue panel.");
				Assert.assertTrue(true, "Discusion Issue is resolved in issue panel.");
			}
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			Log4J.logp.info("*************** Started :: resolved_viewPage********************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found in :: resolved_viewPage");
			Assert.assertTrue(false, "Problem found In ::resolved_viewPage");
			e.printStackTrace();
		}
		finally
		{
			groupingpnl_webe.btn_Later.click();
		}
	}

	/**
	 * This method is to check that user directly submit the case without calculate DRG/APC value
	 * 
	 * @author agupta
	 * @since 18/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg_669: Verify that user directly submit the case without calculate DRG/APC value", priority = 154)
	public static void submit_Case_Without_DRG()
	{
		boolean chkGroupingIcon;
		boolean bstatus = true;
		try
		{
			Log4J.logp.info("**************** Started ::submit_Case_Without_DRG *************** ");
			Thread.sleep(2000);
			/*Login_Lib.logOut_App(); // logout from sagar user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);*/
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			// For Inpatient Case
			Log4J.logp.info("===================== Inpatient Case =======================");
			Common_Lib.openCase("gp001");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			chkGroupingIcon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (chkGroupingIcon == true)
			{
				Log4J.logp.info("Grouping button is Displayed without calculate DRG value");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Later.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);

			// For Outpatient Case
			Log4J.logp.info("==================== Outpatient Case ====================");
			Thread.sleep(2000);
			Common_Lib.openCase("gp002");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			chkGroupingIcon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (chkGroupingIcon == true)
			{
				Log4J.logp.info("Grouping button is Displayed without calculate APC value");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				bstatus = false;
			}
			Log4J.logp.info("**************** Ended ::submit_Case_Without_DRG *************** ");
			Assert.assertTrue(bstatus, "submit_Case_Without_DRG Completed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: submit_Case_Without_DRG");
			Assert.assertTrue(false, "Problem found in :: submit_Case_Without_DRG");
			e.printStackTrace();
		}
		finally
		{
			groupingpnl_webe.btn_Later.click();
		}
	}

	/**
	 * This method is to check Working and Initial DRG if CDI open fresh Inpatient case
	 * 
	 * @author agupta
	 * @since 18/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-691: To verify working and Intial DRG if CDI open fresh Inpatient case", priority = 155)
	public static void intial_Working_DRG()
	{
		int workingDRG, intialDRG;
		String strDRG, strdrg;
		boolean bstatus = true;
		try
		{
			Log4J.logp.info("**************Started : intial_Working_DRG **************");
			Thread.sleep(2000);
			/*Login_Lib.logOut_App(); // logout from Sagar user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);*/
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);
			Common_Lib.openCase("gp003");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);

			// Click on directly grouping icon and refer grouping panel DRG values
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			strDRG = groupingpnl_webe.txt_WorkingDRG.getText();
			strdrg = WorkingScreen_Lib.getDRG(strDRG);
			workingDRG = Integer.parseInt(strdrg);
			if (workingDRG == 0)
			{
				Log4J.logp.info(" Wokring DRG value is Zero");
				Assert.assertTrue(true, " Working DRG value is zero");
			}
			else
			{
				bstatus = false;
			}
			strDRG = groupingpnl_webe.txt_IntialDRG.getText();
			strdrg = WorkingScreen_Lib.getDRG(strDRG);
			intialDRG = Integer.parseInt(strdrg);
			if (intialDRG == 0)
			{
				Log4J.logp.info("Intial DRG value is Zero");
				Assert.assertTrue(true, "Initial DRG value is zero");
			}
			else
			{
				bstatus = false;
			}
			//Accept principal code in coding panel
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Principal, 30);

			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
			Thread.sleep(500);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(2000);
			Log4J.logp.info("===================== After Accept Principal code ===================");
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			strDRG = groupingpnl_webe.txt_WorkingDRG.getText();
			strdrg = WorkingScreen_Lib.getDRG(strDRG);
			workingDRG = Integer.parseInt(strdrg);
			if (workingDRG > 0)
			{
				Log4J.logp.info(" Wokring DRG value is Calculated Successfully");
				Assert.assertTrue(true, " Working DRG value is not zero");
			}
			else
			{
				bstatus = false;
			}
			strDRG = groupingpnl_webe.txt_IntialDRG.getText();
			strdrg = WorkingScreen_Lib.getDRG(strDRG);
			intialDRG = Integer.parseInt(strdrg);
			if (intialDRG > 0)
			{
				Log4J.logp.info("Intial DRG value is calculated Successfully");
				Assert.assertTrue(true, "Initial DRG value is not zero zero");
			}
			else
			{
				bstatus = false;
			}
			Thread.sleep(2000);

			// Calculate DRG value with added Probable code
			//WorkingScreen_Lib.CDS_QueryCodingPnl("gp001", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD001", codingPnl_webe.lst_Principal_Diagnosis, codingPnl_webe.lst_QueryPhysician_Principal);
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			Log4J.logp.info("===================== After added Prbable code ===================");
			strDRG = groupingpnl_webe.txt_WorkingDRG.getText();
			strdrg = WorkingScreen_Lib.getDRG(strDRG);
			workingDRG = Integer.parseInt(strdrg);

			strDRG = groupingpnl_webe.txt_IntialDRG.getText();
			strdrg = WorkingScreen_Lib.getDRG(strDRG);
			intialDRG = Integer.parseInt(strdrg);
			if (workingDRG == intialDRG)
			{
				Log4J.logp.info("Working DRG and Intial DRG are same");
			}
			else
			{
				Log4J.logp.info("Working DRG and Initial DRG both are different.");
			}
			Thread.sleep(2000);
			Log4J.logp.info("************** Ended : intial_Working_DRG **************");
			Assert.assertTrue(bstatus, "intial_Working_DRG completed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found in :: intial_Working_DRG");
			Assert.assertTrue(false, "Problem found in :: intial_Working_DRG");
			e.printStackTrace();
		}
		finally
		{
			groupingpnl_webe.btn_Later.click();
		}

	}

	/**
	 * This methos is check that If user click on CANCEL after change grouper pricer
	 * 
	 * @author agupta
	 * @since 18/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2912 : To verify that if user click on CANCEL after change grouper and pricer value" + "ezCAC_MVP_Reg_2913 : To verify that if user click on CLOSE after change Grouper ands Pricer value", priority = 156)
	public static void cancelGrouperPricer()
	{
		String strDRG, strdrg;
		int i, j, k;
		try
		{
			Log4J.logp.info("************* Started : cancelGrouperPricer *************");
			Thread.sleep(2000);
			/*Login_Lib.logOut_App(); // Logout from Sagar user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);*/
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("gp004");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);

			//Accept principal code in coding panel
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Principal, 30);

			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 5);
			Thread.sleep(500);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(2000);
			Log4J.logp.info("======================= After Accept Principal code =======================");
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			strDRG = groupingpnl_webe.txt_CoderDRG.getText();
			strdrg = WorkingScreen_Lib.getDRG(strDRG);
			i = Integer.parseInt(strdrg);
			if (i > 0)
			{
				Log4J.logp.info("DRG value has been calculated Successfully");
			}
			Thread.sleep(2000);
			Log4J.logp.info("==================== CANCEL Button =====================");
			groupingpnl_webe.lnk_CMSVer.click();
			Thread.sleep(2000);
			Select dropdown = new Select(groupingpnl_webe.lst_grouperSelect);
			dropdown.selectByVisibleText("CMS ver 31.0");
			Thread.sleep(500);
			groupingpnl_webe.btn_cancelGrouper.click();
			Thread.sleep(2000);
			strDRG = groupingpnl_webe.txt_CoderDRG.getText();
			strdrg = WorkingScreen_Lib.getDRG(strDRG);
			j = Integer.parseInt(strdrg);
			if (j == i)
			{
				Log4J.logp.info("DRG value is not change after click on CANCEL button");
				Assert.assertTrue(true, "DRG value is not change");
			}
			else
			{
				Assert.assertTrue(false, "DRG value is Changed ");
			}
			Thread.sleep(2000);
			Log4J.logp.info("=============== Close Icon ===============");
			groupingpnl_webe.lnk_CMSVer.click();
			Thread.sleep(2000);
			Select dropdown1 = new Select(groupingpnl_webe.lst_grouperSelect);
			dropdown1.selectByVisibleText("CMS ver 31.0");
			Thread.sleep(500);
			groupingpnl_webe.ico_Close.click();
			Thread.sleep(2000);
			strDRG = groupingpnl_webe.txt_CoderDRG.getText();
			strdrg = WorkingScreen_Lib.getDRG(strDRG);
			k = Integer.parseInt(strdrg);
			if (k == i)
			{
				Log4J.logp.info("DRG value is not change after click on Close icon");
				Assert.assertTrue(true, "DRG value is not change");
			}
			else
			{
				Assert.assertTrue(false, "DRG value is Changed ");
			}
			Log4J.logp.info("************* Ended : cancelGrouperPricer *************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found In :: cancelGrouperPricer");
			Assert.assertTrue(false, "Problem found In :: cancelGrouperPricer");
			e.printStackTrace();
		}
		finally
		{
			groupingpnl_webe.btn_Later.click();
		}
	}

	/**
	 * This method is check when user open any fresh case and click on Group/Regroup button
	 * 
	 * @author agupta
	 * @since 19/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-1022: Verify that when user open any fresh case and click on Group/Regroup button", priority = 157)
	public static void groupingWithFreshCase()
	{
		boolean bstatus;
		try
		{
			Log4J.logp.info("************ Started : groupingWithFreshCase *************");
			Thread.sleep(2000);
			/*Login_Lib.logOut_App(); // logout from sagar user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);*/

			// Coder user role
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Log4J.logp.info("================ For Coder user role =================");
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);
			Common_Lib.openCase("ap001");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			bstatus = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bstatus == true)
			{
				Log4J.logp.info("DRG valus is not calculated so button is Displayed with Grouping icon");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				Assert.assertTrue(false, "DRG value calculated");
			}
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			// CDI user role
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			Log4J.logp.info("============== For CDI user role ================");
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);
			Common_Lib.openCase("ap002");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			bstatus = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bstatus == true)
			{
				Log4J.logp.info("APC valus is not calculated so button is Displayed with Grouping icon");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				Assert.assertTrue(false, "APC value calculated");
			}
			Log4J.logp.info("************ Ended : groupingWithFreshCase *************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in groupingWithFreshCase");
			Assert.assertTrue(false, "Problem found in :: groupingWithFreshCase");
			e.printStackTrace();
		}
		finally
		{
			groupingpnl_webe.btn_Later.click();
		}
	}

	/**
	 * This method is check the grouping button with code accept,Reject and modify activity
	 * 
	 * @author agupta
	 * @since 19/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-1023 : Verify the grouping button with Coder accept, Reject and modify activity" + "ezCAC_MVP_Reg-1024: Verify that when user calculate correct DRG", priority = 158)
	public static void groupingWithAcceptRejectModify()
	{
		boolean bstatus;
		boolean biconstatus = true;
		try
		{
			Log4J.logp.info("************ Started :groupingWithAcceptRejectModify");
			Thread.sleep(2000);
			/*Login_Lib.logOut_App(); // Logout from sagar user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash User role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);*/

			// For Coder user role
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Log4J.logp.info("===================== In Coder user role =====================");
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);
			Common_Lib.openCase("ap001");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);

			// Accept activity
			Log4J.logp.info("================== Started Accept Activity ====================");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Principal, 30);
			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 5);
			Thread.sleep(500);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(2000);
			Log4J.logp.info("Principal Code accepted");
			Log4J.logp.info("================== After Accept Principal code ======================");
			bstatus = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after accept the code");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}
			Thread.sleep(2000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			if (groupingpnl_webe.btn_Done.getText().equalsIgnoreCase("Done"))
			{
				Log4J.logp.info("DRG value has been calculated so button is Displayed with DONE label");
				Assert.assertTrue(true, "DRG Value Calculated");
			}
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_First_Secondary, 30);
			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
			Thread.sleep(500);
			codingPnl_webe.lnk_First_Secondary.click();
			Thread.sleep(2000);
			Log4J.logp.info("Secondary code Accepted");
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after once again accept the code when DRG value has been already calculated");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}
			Thread.sleep(2000);
			Log4J.logp.info("=============== Ended Accept Activity =================");

			// Reject Principal Code
			Log4J.logp.info("================== Sterted Reject Activity ===================");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Reject_Principal, 30);
			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 5);
			Thread.sleep(500);
			codingPnl_webe.lnk_Reject_Principal.click();
			Thread.sleep(2000);
			Log4J.logp.info("Principal Code rejected");
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after reject the code when DRG value has been already calculated");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}

			// Reject Secondary diagnosis code when grouping icon is already displayed on button
			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
			Thread.sleep(500);
			codingPnl_webe.lnk_Reject_Secondary.click();
			Log4J.logp.info("Secondary Code is Rejected");
			Thread.sleep(2000);
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after reject the code when DRG value has been already calculated");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}
			Thread.sleep(2000);
			Log4J.logp.info("==================== Ended Reject Activity ====================");

			// Modify Activity
			Log4J.logp.info("=================== Started Modify Activity =====================");
			Thread.sleep(2000);
			WorkingScreen_Lib.modify_Code("ag001", "ag002");
			Thread.sleep(2000);
			Log4J.logp.info("Code Modify Successfully");
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after modify the code the code");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Principal, 30);
			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 5);
			Thread.sleep(500);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(2000);
			Log4J.logp.info("Principal Code accepted");
			Thread.sleep(2000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			if (groupingpnl_webe.btn_Done.getText().equalsIgnoreCase("Done"))
			{
				Log4J.logp.info("DRG value has been calculated so button is Displayed with DONE label");
				Assert.assertTrue(true, "DRG Value Calculated");
			}
			else
			{
				biconstatus = false;
			}
			Thread.sleep(2000);
			WorkingScreen_Lib.modify_Code("ag003", "ag004");
			Thread.sleep(2000);
			Log4J.logp.info("Code Modify Successfully");
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after modify the code when DRg value has been already calculated");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}
			Thread.sleep(2000);
			Log4J.logp.info("================= Ended Modify Activity ====================");
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(2000);
			//click on Done value
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(1000);
			// click here to case completed
			groupingpnl_webe.chk_routeForReview.click();
			//click here to go to landing page
			groupingpnl_webe.chk_takeMeQueue.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_submitAfterDone.click();
			Thread.sleep(5000);
			Log4J.logp.info("Case completed successfully and assign to the reviewer");
			Log4J.logp.info("============== Ended Coder User role ==================");

			// CDI user role
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			Log4J.logp.info("================ In CDI user role ===================");
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);
			Common_Lib.openCase("ap003");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);

			// Accept activity
			Log4J.logp.info("=============== Started Accept Activity ===================");
			//Accept principal code in coding panel
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Principal, 30);
			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 5);
			Thread.sleep(500);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(2000);
			Log4J.logp.info("Principal Code accepted");
			Log4J.logp.info("================== After Accept Principal code ===================");
			bstatus = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after accept the code");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}
			Thread.sleep(2000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			if (groupingpnl_webe.btn_Done.getText().equalsIgnoreCase("Done"))
			{
				Log4J.logp.info("DRG value has been calculated so button is Displayed with DONE label");
				Assert.assertTrue(true, "DRG Value Calculated");
			}
			else
			{
				biconstatus = false;
			}

			// Accept first secondary code when DRG value has been already calculated
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_First_Secondary, 30);
			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
			Thread.sleep(500);
			codingPnl_webe.lnk_First_Secondary.click();
			Thread.sleep(2000);
			Log4J.logp.info("Secondary code Accepted");
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after once again accept the code when DRG value has been already calculated");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}
			Thread.sleep(2000);
			Log4J.logp.info("=================== Ended Accept Activity ====================");

			// Reject Principal Code
			Log4J.logp.info("==================== Sterted Reject Activity ====================");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Reject_Principal, 30);
			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 5);
			Thread.sleep(500);
			codingPnl_webe.lnk_Reject_Principal.click();
			Thread.sleep(2000);
			Log4J.logp.info("Principal Code rejected");
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after reject the code when DRG value has been already calculated");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}

			// Reject Secondary diagnosis code when grouping icon is already displayed on button
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Reject_Secondary, 30);
			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
			Thread.sleep(500);
			codingPnl_webe.lnk_Reject_Secondary.click();
			Log4J.logp.info("Secondary Code is Rejected");
			Thread.sleep(2000);
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after reject the code when DRG value has been already calculated");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}
			Thread.sleep(2000);
			Log4J.logp.info("================ Ended Reject Activity ===================");

			// Modify Activity
			Log4J.logp.info("================ Started Modify Activity ================");
			Thread.sleep(2000);
			WorkingScreen_Lib.modify_Code("ag005", "ag006");
			Thread.sleep(2000);
			Log4J.logp.info("Code Modify Successfully");
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after modify the code the code");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Principal, 30);

			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 5);
			Thread.sleep(500);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(2000);
			Log4J.logp.info("Principal Code accepted");
			Thread.sleep(2000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			if (groupingpnl_webe.btn_Done.getText().equalsIgnoreCase("Done"))
			{
				Log4J.logp.info("DRG value has been calculated so button is Displayed with DONE label");
				Assert.assertTrue(true, "DRG Value Calculated");
			}
			else
			{
				biconstatus = false;
			}
			Thread.sleep(2000);
			WorkingScreen_Lib.modify_Code("ag007", "ag008");
			Thread.sleep(2000);
			Log4J.logp.info("Code Modify Successfully");
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after modify the code when DRg value has been already calculated");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}
			Thread.sleep(2000);
			Log4J.logp.info("=============== Ended Modify Activity ================");
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			Log4J.logp.info("============== Ended CDI User role ==============");

			// For Reviwer user role
			Thread.sleep(2000);
			Login_Lib.logOut_App(); // Logout from Akash user role
			Thread.sleep(2000);
			Login_Lib.login("ag002"); // Login wit Lisa reviewer user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			landingp_webe.lnk_Review.click();
			Thread.sleep(2000);
			Log4J.logp.info("================= Started Reviewer user role ==================");
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);
			Common_Lib.openCase("ap001");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);

			// Accept activity
			Log4J.logp.info("=================== Started Accept Activity ==================");
			//Accept principal code in coding panel
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Principal, 30);

			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 5);
			Thread.sleep(500);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(2000);
			Log4J.logp.info("Principal Code accepted");
			Log4J.logp.info("================== After Accept Principal code ==================");
			bstatus = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after accept the code");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}
			Thread.sleep(2000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			if (groupingpnl_webe.btn_Done.getText().equalsIgnoreCase("Done"))
			{
				Log4J.logp.info("DRG value has been calculated so button is Displayed with DONE label");
				Assert.assertTrue(true, "DRG Value Calculated");
			}
			else
			{
				biconstatus = false;
			}
			// Accept first secondary code when DRG value has been already calculated
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_First_Secondary, 30);

			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
			Thread.sleep(500);
			codingPnl_webe.lnk_First_Secondary.click();
			Thread.sleep(2000);
			Log4J.logp.info("Secondary code Accepted");
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after once again accept the code when DRG value has been already calculated");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}
			Thread.sleep(2000);
			Log4J.logp.info("================ Ended Accept Activity ====================");

			// Reject Principal Code
			Log4J.logp.info("================ Sterted Reject Activity ==================");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Reject_Principal, 30);

			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 5);
			Thread.sleep(500);
			codingPnl_webe.lnk_Reject_Principal.click();
			Thread.sleep(2000);
			Log4J.logp.info("Principal Code rejected");
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after reject the code when DRG value has been already calculated");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}

			// Reject Secondary diagnosis code when grouping icon is already displayed on button
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Reject_Secondary, 30);

			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
			Thread.sleep(500);
			codingPnl_webe.lnk_Reject_Secondary.click();
			Log4J.logp.info("Secondary Code is Rejected");
			Thread.sleep(2000);
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after reject the code when DRG value has been already calculated");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}
			Thread.sleep(2000);
			Log4J.logp.info("============== Ended Reject Activity ================");

			// Modify Activity
			Log4J.logp.info("================= Started Modify Activity =================");
			Thread.sleep(2000);
			WorkingScreen_Lib.modify_Code("ag002", "ag005");
			Thread.sleep(2000);
			Log4J.logp.info("Code Modify Successfully");
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after modify the code the code");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Principal, 30);

			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 5);
			Thread.sleep(500);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(2000);
			Log4J.logp.info("Principal Code accepted");
			Thread.sleep(2000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			if (groupingpnl_webe.btn_Done.getText().equalsIgnoreCase("Done"))
			{
				Log4J.logp.info("DRG value has been calculated so button is Displayed with DONE label");
				Assert.assertTrue(true, "DRG Value Calculated");
			}
			else
			{
				biconstatus = false;
			}
			Thread.sleep(2000);
			WorkingScreen_Lib.modify_Code("ag009", "ag002");
			Thread.sleep(2000);
			Log4J.logp.info("Code Modify Successfully");
			if (bstatus == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after modify the code when DRg value has been already calculated");
				Assert.assertTrue(true, "Grouping icon is Displayed");
			}
			else
			{
				biconstatus = false;
			}
			Thread.sleep(2000);
			Log4J.logp.info("================== Ended Modify Activity =================");
			Log4J.logp.info("================ Ended Reviewer User role ===================");
			Log4J.logp.info("************ Ended :groupingWithAcceptRejectModify ***************");
			Assert.assertTrue(biconstatus, "groupingWithAcceptRejectModify completed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found in :: groupingWithAcceptRejectModify");
			Assert.assertTrue(false, "Problem Found in : groupingWithAcceptRejectModify");
			e.printStackTrace();
		}
		finally
		{
			groupingpnl_webe.btn_Later.click();
		}
	}

	/**
	 * This method is to check that when user change parameters of any codes after calculate DRG/APC value
	 * 
	 * @author agupta
	 * @since 19/11/2014
	 */
	@Test(description = "ezCAC_MVP-Reg-1114 : Verify that when user change parameters of any codes after calculate DRG/APC value" + "ezCAC_MVP_Reg-1115 : Verify that when user add new code after calculate DRG value"
			+ "ezCAC_MVP_Reg-1116 : Verify that when raise issue in issue panel after calculate DRG value" + "Verify that when user copy evidence from one code to another code" + "Verify that when user Remove evidence from coding panel" + "Verify that when user change discharge disposition value "
			+ "Verify that when user seitch to ICD-9 to ICD-10 coding system" + "Verify that when user make code from secondary to principal diagnosis code", priority = 159)
	public static void changeParameters()
	{
		boolean bstatus = true;
		boolean bdonestatus = true;
		boolean bchkgroupingicon = false;
		try
		{
			Log4J.logp.info("****************** Started :: changeParameters  ******************");
			Thread.sleep(2000);
			Login_Lib.logOut_App(); // Logout from lisa reviewer user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash User role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("ap004");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);

			Log4J.logp.info("================ Inpatient Case ===============");

			// ICD-9 Coding System
			Log4J.logp.info("==================== ICD-9 Coding System =================== ");
			// Accept Principal Code
			Log4J.logp.info("=================== Check Grouping icon with change POA value ===================");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Principal, 10);

			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 5);
			Thread.sleep(500);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(2000);
			Log4J.logp.info("Principal Code accepted");

			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			if (groupingpnl_webe.btn_Done.getText().equalsIgnoreCase("Done"))
			{
				Log4J.logp.info("DRG value has been calculated so button is Displayed with DONE label");
			}
			else
			{
				bstatus = false;
			}
			Thread.sleep(2000);
			codingPnl_webe.expand_Principal_Code.click();
			Thread.sleep(2000);

			// Click on POA Y value of Principal code
			codingPnl_webe.txt_POAY.click();
			Thread.sleep(1000);
			codingPnl_webe.btn_Save.click();
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after change the POA value in Principal code");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 750);
			Thread.sleep(2000);

			// Add Admitting Diagnosis code
			WorkingScreen_Lib.addnewCode_withData_ICD9("ag010");
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after add new Admitting Diagnosis code");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// Add Secondary Diagnosis code
			WorkingScreen_Lib.addnewCode_withData_ICD9("ag011");
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after add secondary diagnosis code");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// Add Procedure codes
			WorkingScreen_Lib.addnewCode_withData_ICD9("sk003");
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after add Procedures code");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// Change Parameter of Procedure code
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.expand_Procedure, 40);
			Thread.sleep(500);
			codingPnl_webe.expand_Procedure.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.addParameteres("ag002", "procedure");
			Thread.sleep(4000);
			codingPnl_webe.btn_Save_Procedure.click();
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after change parameters of procedure code in Inpatient case");
			}
			else
			{
				bstatus = false;
			}

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(3000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after switch to ICD-10 Coding system.");
			}
			else
			{
				bstatus = false;
			}
			Log4J.logp.info("=================== ICD-10 Coding System =================== ");

			Log4J.logp.info("=================== Check Grouping icon with change POA value =================== ");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Principal, 10);

			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 5);
			Thread.sleep(500);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(2000);
			Log4J.logp.info("Principal Code accepted");

			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			bdonestatus = Common_Lib.checkElementPresent(groupingpnl_webe.btn_Done);
			if (bdonestatus == false)
			{
				Log4J.logp.info("Done button is not Displayed in action panel");
			}
			else
			{
				bstatus = false;
			}
			Thread.sleep(2000);
			codingPnl_webe.expand_Principal_Code.click();
			Thread.sleep(2000);

			// Click on POA Y value of Principal code
			codingPnl_webe.txt_POAY.click();
			Thread.sleep(1000);
			codingPnl_webe.btn_Save.click();
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after change the POA value in Principal code");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 750);
			Thread.sleep(2000);

			// Add Admitting Diagnosis code
			WorkingScreen_Lib.addnewCode_withData_ICD10("ag012");
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after add new Admitting Diagnosis code");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// Add Secondary Diagnosis code
			WorkingScreen_Lib.addnewCode_withData_ICD10("ag013");
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after add secondary diagnosis code");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// Add Procedure codes
			WorkingScreen_Lib.addnewCode_withData_ICD10("ag014");
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after add Procedures code");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// Change Parameter of ICD-10 Procedure code
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.expand_Procedure, 40);
			Thread.sleep(500);
			codingPnl_webe.expand_Procedure.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.addParameteres("ag003", "procedure");
			Thread.sleep(4000);
			codingPnl_webe.btn_Save_Procedure.click();
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after change parameters of procedure code in Inpatient case");
			}
			else
			{
				bstatus = false;
			}

			//to move on ICD 9

			// Check Grouping icon with raise issue in issue panel

			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD9_Menu.click();
			Thread.sleep(3000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after switch to ICD-9 Coding system.");
			}
			else
			{
				bstatus = false;
			}
			Log4J.logp.info("=================== ICD-9 Coding System =================== ");
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			Log4J.logp.info("=================== Grouping icon with raise issue =================== ");
			Common_Lib.rightclick_system_sugggested_evidence();
			Thread.sleep(1000);
			medicalrecordpnl_webe.lnk_discusswithcolleague.click();
			Thread.sleep(2000);
			IssuePanel_Lib.send_DiscussWithColleague("ap001");
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after switch to Raise issue in Issue panel");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// Copy evidence from one code to another code
			Log4J.logp.info("=================== Grouping icon with Copy Paste Evidence =================== ");

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, common_webe.div_second_secondDia, 40);
			Thread.sleep(2000);
			Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 70);
			codingPnl_webe.lst_first_secondary_evi_arrow.click();
			Thread.sleep(2000);
			//to click on copy evidence
			driver.findElement(By.xpath("((//h2[@id='secondary']/..//div[@class='expand'])[2]//span[@class='brd code-status'])[1]//a[@id='copyEvidence']")).click();
			Thread.sleep(2000);
			WebElement webe = driver.findElement(By.xpath("(//h2[@id='secondary']/..//span[@id='make-it-principal'])[1]"));

			Actions action = new Actions(driver);
			action.moveToElement(webe, -3, -10).build().perform();
			Thread.sleep(1000);
			Thread.sleep(4000);
			action.click().build().perform();
			Thread.sleep(4000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after copy evidence from one code to another code ");
			}
			else
			{
				bstatus = false;
			}
			Thread.sleep(4000);

			// check Grouping icon with remove evidence from coding panel
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			Log4J.logp.info("=================== Grouping icon with Remove Evidence =================== ");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lst_first_secondary_man_evi_arrow, 40);
			Thread.sleep(2000);
			codingPnl_webe.lst_first_secondary_man_evi_arrow.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//h2[@id='secondary']/..//span[contains(@class,'brd code-status') and contains(@class,'manual')])[1]//a[@id='removEvidence']")).click();
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after remove evidence from coding panel ");
			}
			else
			{
				bstatus = false;
			}

			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// to check grouping icon with change Discharge Disposition value
			Log4J.logp.info("=================== Grouping icon with Change Discharge Disposition value =================== ");
			demographicpnl_webe.lnk_DemoPnlExpand.click();
			Thread.sleep(2000);
			demographicpnl_webe.lst_DischargeDisposition.click();
			Thread.sleep(2000);
			Select dropdown = new Select(demographicpnl_webe.lst_DischargeDisposition);
			dropdown.selectByValue("20");
			Thread.sleep(2000);
			demographicpnl_webe.lnk_DemoPnlExpand.click();
			Thread.sleep(1000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after Change Discharge Disposition value in Patient Demographic panel ");
			}
			else
			{
				bstatus = false;
			}

			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// To check grouping icon with move code to principal diagnosis code
			Log4J.logp.info("=================== Grouping icon with move code to Principal Code =================== ");
			codingPnl_webe.ico_Principal.click();
			Thread.sleep(3000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after move to code in Principal code ");
			}
			else
			{
				bstatus = false;
			}

			//to move on ICD 10

			// Check Grouping icon with raise issue in issue panel

			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(3000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after switch to ICD-10 Coding system.");
			}
			else
			{
				bstatus = false;
			}
			Log4J.logp.info("=================== ICD-10 Coding System =================== ");
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			Log4J.logp.info("=================== Grouping icon with raise issue =================== ");
			Common_Lib.rightclick_system_sugggested_evidence();
			Thread.sleep(1000);
			medicalrecordpnl_webe.lnk_discusswithcolleague.click();
			Thread.sleep(2000);
			IssuePanel_Lib.send_DiscussWithColleague("ap001");
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after switch to Raise issue in Issue panel");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// Copy evidence from one code to another code
			Log4J.logp.info("=================== Grouping icon with Copy Paste Evidence =================== ");

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, common_webe.div_second_secondDia, 40);
			Thread.sleep(2000);
			Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 70);
			Thread.sleep(2000);
			codingPnl_webe.lst_first_secondary_evi_arrow.click();
			Thread.sleep(2000);
			//to click on copy evidence
			driver.findElement(By.xpath("((//h2[@id='secondary']/..//div[@class='expand'])[2]//span[@class='brd code-status'])[1]//a[@id='copyEvidence']")).click();
			Thread.sleep(2000);
			webe = driver.findElement(By.xpath("(//h2[@id='secondary']/..//span[@id='make-it-principal'])[1]"));

			action = new Actions(driver);
			action.moveToElement(webe, -3, -10).build().perform();
			Thread.sleep(1000);
			Thread.sleep(4000);
			action.click().build().perform();
			Thread.sleep(4000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after copy evidence from one code to another code ");
			}
			else
			{
				bstatus = false;
			}
			Thread.sleep(4000);

			// check Grouping icon with remove evidence from coding panel
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			Log4J.logp.info("=================== Grouping icon with Remove Evidence =================== ");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lst_first_secondary_man_evi_arrow, 40);
			Thread.sleep(2000);
			codingPnl_webe.lst_first_secondary_man_evi_arrow.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//h2[@id='secondary']/..//span[contains(@class,'brd code-status') and contains(@class,'manual')])[1]//a[@id='removEvidence']")).click();
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after remove evidence from coding panel ");
			}
			else
			{
				bstatus = false;
			}

			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// to check grouping icon with change Discharge Disposition value
			Log4J.logp.info("=================== Grouping icon with Change Discharge Disposition value =================== ");
			demographicpnl_webe.lnk_DemoPnlExpand.click();
			Thread.sleep(2000);
			demographicpnl_webe.lst_DischargeDisposition.click();
			Thread.sleep(2000);
			dropdown = new Select(demographicpnl_webe.lst_DischargeDisposition);
			dropdown.selectByValue("2");
			Thread.sleep(2000);
			demographicpnl_webe.lnk_DemoPnlExpand.click();
			Thread.sleep(1000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after Change Discharge Disposition value in Patient Demographic panel ");
			}
			else
			{
				bstatus = false;
			}

			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// To check grouping icon with move code to principal diagnosis code
			Log4J.logp.info("=================== Grouping icon with move code to Principal Code =================== ");
			codingPnl_webe.ico_Principal.click();
			Thread.sleep(3000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after Change move to code in Principal code ");
			}
			else
			{
				bstatus = false;
			}

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(3000);

			//  OUTPATIENT CASE

			Common_Lib.openCase("ap005");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "clickable", 15);
			Log4J.logp.info("================ Outpatient Case ===============");

			// ICD-9 Coding System
			Log4J.logp.info("==================== ICD-9 Coding System =================== ");
			// Accept Principal Code
			Log4J.logp.info("=================== Check Grouping icon with change POA value ===================");

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Principal, 10);

			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 5);
			Thread.sleep(500);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(2000);
			Log4J.logp.info("Principal Code accepted");

			// Accept CPT code 
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_CPT, 40);
			Thread.sleep(500);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 5);
			Thread.sleep(500);
			codingPnl_webe.lnk_Accept_CPT.click();
			Thread.sleep(2000);
			Log4J.logp.info("CPT Code accepted");

			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			if (groupingpnl_webe.btn_Done.getText().equalsIgnoreCase("Done"))
			{
				Log4J.logp.info("DRG value has been calculated so button is Displayed with DONE label");
			}
			else
			{
				bstatus = false;
			}
			Thread.sleep(2000);
			codingPnl_webe.expand_Principal_Code.click();
			Thread.sleep(2000);

			// Click on POA Y value of Principal code
			codingPnl_webe.txt_POAY.click();
			Thread.sleep(1000);
			codingPnl_webe.btn_Save.click();
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after change the POA value in Principal code");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 750);
			Thread.sleep(2000);

			// Add Admitting Diagnosis code
			WorkingScreen_Lib.addnewCode_withData_ICD9("ag018");
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after add new Admitting Diagnosis code");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// Add Secondary Diagnosis code
			WorkingScreen_Lib.addnewCode_withData_ICD9("ag019");
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after add secondary diagnosis code");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// Add CPT code
			WorkingScreen_Lib.addnewCode_withData_ICD9("ag015");
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after add CPT code");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// Add E & M code
			WorkingScreen_Lib.addnewCode_withData_ICD9("ag016");
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after add E & M  code");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// Add HCPCS code
			WorkingScreen_Lib.addnewCode_withData_ICD9("ag017");
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after add HCPCS code");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// Change Parameter of CPT code
			Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 750);
			Thread.sleep(2000);
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.expand_CPT, 50);
			Thread.sleep(2000);
			codingPnl_webe.expand_CPT.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.addParameteres("ag004", "cpt-code");
			Thread.sleep(4000);
			codingPnl_webe.btn_Save_CPT.click();
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after change parameters of CPT code in outpatient case");
			}
			else
			{
				bstatus = false;
			}

			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// Change Parameter of E & M code
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Expand_EnM, 40);
			Thread.sleep(500);
			codingPnl_webe.lnk_Expand_EnM.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.addParameteres("ag005", "EnM-code");
			Thread.sleep(4000);
			codingPnl_webe.btn_Save_EnM.click();
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after change parameters of E & M code in outpatient case");
			}
			else
			{
				bstatus = false;
			}

			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// Change Parameter of HCPCS code
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Expand_HCPCS, 40);
			Thread.sleep(500);
			codingPnl_webe.lnk_Expand_HCPCS.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.addParameteres("ag006", "hcpcs-code");
			Thread.sleep(4000);
			codingPnl_webe.btn_Save_HCPCS.click();
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after change parameters of HCPCS code in outpatient case");
			}
			else
			{
				bstatus = false;
			}

			// CHeck grouping icon with raise issue in issue panel
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			Log4J.logp.info("=================== Grouping icon with raise issue =================== ");
			Common_Lib.rightclick_system_sugggested_evidence();
			Thread.sleep(1000);
			medicalrecordpnl_webe.lnk_discusswithcolleague.click();
			Thread.sleep(2000);
			IssuePanel_Lib.send_DiscussWithColleague("ap001");
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after switch to Raise issue in Issue panel");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// Copy evidence from one code to another code
			Log4J.logp.info("=================== Grouping icon with Copy Paste Evidence =================== ");

			Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, common_webe.div_second_secondDia, 40);
			Thread.sleep(2000);
			Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 70);
			Thread.sleep(2000);
			codingPnl_webe.lst_first_secondary_evi_arrow.click();
			Thread.sleep(2000);
			//to click on copy evidence
			driver.findElement(By.xpath("((//h2[@id='secondary']/..//div[@class='expand'])[2]//span[@class='brd code-status'])[1]//a[@id='copyEvidence']")).click();
			Thread.sleep(2000);
			webe = driver.findElement(By.xpath("(//h2[@id='secondary']/..//span[@id='make-it-principal'])[1]"));

			action = new Actions(driver);
			action.moveToElement(webe, -3, -10).build().perform();
			Thread.sleep(1000);
			Thread.sleep(4000);
			action.click().build().perform();
			Thread.sleep(4000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after copy evidence from one code to another code ");
			}
			else
			{
				bstatus = false;
			}
			Thread.sleep(4000);

			// check Grouping icon with remove evidence from coding panel
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			Log4J.logp.info("=================== Grouping icon with Remove Evidence =================== ");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lst_first_secondary_man_evi_arrow, 40);
			Thread.sleep(2000);
			Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 60);
			Thread.sleep(2000);
			codingPnl_webe.lst_first_secondary_man_evi_arrow.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//h2[@id='secondary']/..//span[contains(@class,'brd code-status') and contains(@class,'manual')])[1]//a[@id='removEvidence']")).click();
			Thread.sleep(2000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after remove evidence from coding panel ");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);

			// To check grouping icon with move code to principal diagnosis code
			Log4J.logp.info("=================== Grouping icon with move code to Principal Code =================== ");
			codingPnl_webe.ico_Principal.click();
			Thread.sleep(3000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Grouping icon is Displayed after Change move to code in Principal code ");
			}
			else
			{
				bstatus = false;
			}

			Log4J.logp.info("****************** Ended :: changeParameters  ******************");
			Assert.assertTrue(bstatus, "changeParameters Failed");

		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: changeParameters");
			Assert.assertTrue(false, "Problem found in :: changeParameters");
			e.printStackTrace();
		}
		finally
		{
			groupingpnl_webe.btn_Later.click();
		}

	}

	/**
	 * This method is check that user can able to redirect on next case after submit the case
	 * 
	 * @author agupta
	 * @since 26/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-710 : Verify that user can able to redirect on next case after submit the case", priority = 160)
	public static void redirect_NextCase()
	{
		boolean bchkgroupingicon = false;
		boolean bstatus = true;
		try
		{
			Log4J.logp.info("********** Started :: redirect_NextCase ************");
			Thread.sleep(2000);
			/*Login_Lib.logOut_App(); // Logout from sagar user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);*/

			// for CDI user role
			Log4J.logp.info("========== CDI user role ===========");
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			Common_Lib.openCase("ap006");
			Common_Lib.waitForObject(codingPnl_webe.lnk_Accept_Principal, "clickable", 20);
			Thread.sleep(2000);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			if (groupingpnl_webe.btn_Done.getText().equals("DONE"))
			{
				Log4J.logp.info("Done button is Displayed after calculate DRG value");
				Assert.assertTrue(true, "Done button is Displayed");
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_submitAfterDone.click();
			Common_Lib.waitForObject(codingPnl_webe.lnk_Accept_Principal, "clickable", 20);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("CDI is redirect on next after submit the case");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			// coder user role
			Log4J.logp.info("=========== Coder user role ==========");
			Login_Lib.logOut_App(); // logout from Akash CDI user role
			Thread.sleep(2000);
			Login_Lib.login("ag012"); // Login with Jay Coder user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("ap006");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			if (groupingpnl_webe.btn_Done.getText().equals("DONE"))
			{
				Log4J.logp.info("Done button is Displayed after calculate DRG value");
				Assert.assertTrue(true, "Done button is Displayed");
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(2000);
			groupingpnl_webe.chk_routeForReview.click();
			Thread.sleep(1000);
			groupingpnl_webe.btn_submitAfterDone.click();
			Thread.sleep(10000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Coder is redirect on next after submit the case");
			}
			else
			{
				bstatus = false;
			}
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);
			Common_Lib.openCase("ag010");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);
			demographicpnl_webe.lnk_DemoPnlExpand.click();
			Thread.sleep(2000);
			demographicpnl_webe.lst_DischargeDisposition.click();
			Thread.sleep(2000);
			Select dropdown = new Select(demographicpnl_webe.lst_DischargeDisposition);
			dropdown.selectByValue("2");
			Thread.sleep(2000);
			demographicpnl_webe.lnk_DemoPnlExpand.click();
			Thread.sleep(1000);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(5000);
			if (groupingpnl_webe.btn_Done.getText().equals("DONE"))
			{
				Log4J.logp.info("Done button is Displayed after calculate DRG value");
				Assert.assertTrue(true, "Done button is Displayed");
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(2000);
			groupingpnl_webe.chk_routeForReview.click();
			Thread.sleep(1000);
			groupingpnl_webe.chk_takeMeQueue.click();
			Thread.sleep(1000);
			groupingpnl_webe.btn_submitAfterDone.click();
			Thread.sleep(2000);

			// Reviewer user role
			Log4J.logp.info("============ Reviewer user role ============");
			Login_Lib.logOut_App(); // Logout from Jay code user role
			Thread.sleep(2000);
			Login_Lib.login("ag011"); // Login with Margaret user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			Common_Lib.openCase("ap006");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(2000);
			if (groupingpnl_webe.btn_Done.getText().equals("DONE"))
			{
				Log4J.logp.info("Done button is Displayed after calculate DRG value");
				Assert.assertTrue(true, "Done button is Displayed");
			}
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_submitAfterDone.click();
			Thread.sleep(10000);
			bchkgroupingicon = Common_Lib.checkElementPresent(groupingpnl_webe.ico_Grouping);
			if (bchkgroupingicon == true)
			{
				Log4J.logp.info("Reviewer is redirect on next after submit the case");
			}
			else
			{
				bstatus = false;
			}
			Log4J.logp.info("********** Ended :: redirect_NextCase ************");
			Assert.assertTrue(bstatus, "redirect_NextCase failed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in : redirect_NextCase");
			Assert.assertTrue(false, "Problem Found in :: redirect_NextCase");
			e.printStackTrace();
		}
		finally
		{
			groupingpnl_webe.btn_Later.click();
		}
	}

	/**
	 * This method is check Later and Done button behavior with Issues and Edits
	 * 
	 * @author agupta
	 * @since 26/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-700 : User click on LATER button with zero issues" + "ezCAC_MVP_Reg-701 :Coder click on LATER button if pending issues > 0" + "ezCAC_MVP_Reg-702 : Coder click on LATER button after resolving all pending issues"
			+ "ezCAC_MVP_Reg-704 :  Coder clicks on DONE button if pending issues > 0" + "ezCAC_MVP_Reg-705 : Coder click on DONE button after resolving all pending issues" + "ezCAC_MVP_Reg-706 : Coder click on DONE button with zero Edit"
			+ "ezCAC_MVP_Reg-707 : Coder click on DONE button if unresolved Edit > 0" + "ezCAC_MVP_Reg-708 : User click on DOne button after resolving aal edits", priority = 161)
	public static void buttonWithIssuesEdits()
	{
		boolean bstatus = false;
		try
		{
			Log4J.logp.info("************* Started : buttonWithIssuesEdits **************");
			Login_Lib.logOut_App(); //Logout from Margaret user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Thread.sleep(2000);

			// For coder user role
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Log4J.logp.info("================ Coder User Role ==============");

			bstatus = WorkingScreen_Lib.checkButton(landingp_webe.ico_OnHold, landingp_webe.ico_Pending);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(2000);
			groupingpnl_webe.chk_routeForReview.click();
			groupingpnl_webe.chk_takeMeQueue.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_submitAfterDone.click();
			Thread.sleep(3000);

			// For Reviewer user role
			Login_Lib.logOut_App(); // Logout from Akash coder user role
			Thread.sleep(2000);
			Login_Lib.login("ag011"); // Login with Margaret user role
			Thread.sleep(2000);
			landingp_webe.lnk_Review.click();
			Thread.sleep(2000);
			Log4J.logp.info("================ Reviewer User Role ==============");
			bstatus = WorkingScreen_Lib.checkButton(landingp_webe.ico_Review_OnHold, landingp_webe.ico_Review_Pending);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(2000);
			groupingpnl_webe.chk_takeMeQueue.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_submitAfterDone.click();
			Thread.sleep(3000);

			// For CDI user role
			Login_Lib.logOut_App(); // Logout from Margaret coder user role
			Thread.sleep(2000);
			Login_Lib.login("ag003"); // Login with Sandra user role
			Thread.sleep(2000);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			Log4J.logp.info("================ CDI User Role ==============");
			bstatus = WorkingScreen_Lib.checkButton(landingp_webe.ico_CDI_OnHold, landingp_webe.ico_CDI_Pending);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(2000);
			groupingpnl_webe.chk_takeMeQueue.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_submitAfterDone.click();
			Thread.sleep(3000);

			Log4J.logp.info("************* Ended : buttonWithIssuesEdits **************");
			Assert.assertTrue(bstatus, "buttonWithIssuesEdits Failed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found in :: buttonWithIssuesEdits");
			Assert.assertTrue(false, "Problem Found In :: buttonWithIssuesEdits");
			e.printStackTrace();
		}
	}

	/**
	 * This method is check when user click on browser back when working screen is already open
	 * 
	 * @author agupta
	 * @since 27/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2808 : To check when user click on browswer back button from working screen" + "ezCAC_MVP_Reg-2825 : To check On Hold status for case with Login/Logout activity" + "ezCAC_MVP_Reg-2827 : To check Pending status for case with Login/Logout activity", priority = 161)
	public static void checkStatusWithBackBroswer()
	{
		boolean biconstatus;
		boolean bstatus = true;

		try
		{
			Log4J.logp.info("****************** Started : checkStatusWithBackBroswer *******************");
			Thread.sleep(2000);
			Login_Lib.logOut_App(); // Logout from Sandra user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);

			// To check On Hold status when Coder click on browser back button from working screen
			Common_Lib.openCase("tc001");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);
			driver.navigate().back();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			Login_Lib.logOut_App(); // logout from akash user role
			Thread.sleep(300000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Thread.sleep(300000);
			SearchCriteria_Lib.search_Case("tc001");
			Thread.sleep(2000);
			Log4J.logp.info("Case has been searched successfully");
			Thread.sleep(2000);
			biconstatus = Common_Lib.checkElementPresent(landingp_webe.ico_OnHold);
			if (biconstatus == true)
			{
				Log4J.logp.info("On Hold status icon is Displayed");
			}
			else
			{
				bstatus = false;
			}
			searchCriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			// To check Pending Status when coder click on browser back button from working screen
			Common_Lib.openCase("tc001");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);
			Common_Lib.rightclick_system_sugggested_evidence();
			Thread.sleep(1000);
			medicalrecordpnl_webe.lnk_discusswithcolleague.click();
			Thread.sleep(2000);
			IssuePanel_Lib.send_DiscussWithColleague("ag002");
			Thread.sleep(2000);
			driver.navigate().back();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			Login_Lib.logOut_App(); // logout from akash user role
			Thread.sleep(300000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Thread.sleep(300000);
			SearchCriteria_Lib.search_Case("tc001");
			Thread.sleep(2000);
			Log4J.logp.info("Case has been searched successfully");
			Thread.sleep(2000);
			biconstatus = Common_Lib.checkElementPresent(landingp_webe.ico_Pending);
			if (biconstatus == true)
			{
				Log4J.logp.info("Pending status icon is Displayed");
			}
			else
			{
				bstatus = false;
			}

			Log4J.logp.info("****************** Ended : checkStatusWithBackBroswer *******************");
			Assert.assertTrue(bstatus, "checkStatusWithBackBroswer is failed");
		}
		catch (Exception e)
		{
			Log4J.logp.info("Problem Found in : checkStatusWithCloseBroswer");
			Assert.assertTrue(false, "Problem found in : checkStatusWithBackBroswer");
			e.printStackTrace();
		}
	}

	/**
	 * This method is to check that when user close the browser tab when working screen is already opened
	 * 
	 * @author agupta
	 * @since 01/12/2014
	 */
	/*
	 
	 // Right Now not found any solution for browser close and reopen so I skipt this all testcases 
	 
	@Test(description = "ezCAC_MVP_Reg-2812 : To check when user directly close the tab of browser", priority = 162)
	public static void checkStatusWithBrowserClose()
	{
	try
	{

		Log4J.logp.info("***************** Started :: checkStatusWithBrowserClose *****************");
		Thread.sleep(2000);
		Login_Lib.logOut_App(); // Logout with Sagar user role
		Thread.sleep(2000);
		WebElement l = driver.findElement(By.cssSelector("#txtusername"));
		boolean blogin = Common_Lib.checkElementPresent(l);
		System.out.println(blogin);
		WebElement p = driver.findElement(By.cssSelector("#txtuserpassword"));
		blogin = Common_Lib.checkElementPresent(p);
		System.out.println(blogin);
		Login_Lib.login("ag007"); // Login with Akash user role
		Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
		landingp_webe.lnk_Coding.click();
		Thread.sleep(2000);
		Common_Lib.openCase("tc005");
		Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "visibility", 15);
		((JavascriptExecutor) driver).executeScript("window.open('https://54.174.2.236:8443/ezCACWeb-1.0.0.44/', '_blank');");
		Thread.sleep(5000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_W);
		Thread.sleep(2000);
		driver.navigate().refresh();
		WebElement l = driver.findElement(By.cssSelector("#txtusername"));
		boolean blogin = Common_Lib.checkElementPresent(l);
		System.out.println(blogin);
		WebElement p = driver.findElement(By.cssSelector("#txtuserpassword"));
		blogin = Common_Lib.checkElementPresent(p);
		System.out.println(blogin);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#loginbutton")).click();
		Thread.sleep(2000);
		Log4J.logp.info("***************** Ended :: checkStatusWithBrowserClose *****************");
	}
	catch (Exception e)
	{
		Log4J.logp.error("Problem found in : checkStatusWithBrowserClose");
		Assert.assertTrue(false, "Problem Found in :: checkStatusWithBrowserClose");
		e.printStackTrace();
	}
	}*/
	/**
	 * This method is check when one case is already in In Progress status and usern try to open another case
	 * 
	 * @author agupta
	 * @since 28/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2817 : To check when user try to open the case when one case is already in In Progres status" + "ezCAC_MVP_Reg-2819 : To check when one case is in In Progress status and another user role try to open the case"
			+ "ezCAC_MVP_Reg-2821 : To check when once case is in Progress status and another user try to open the case", priority = 162)
	public static void inProgressStatus()
	{
		String strAlert;
		boolean bstatus = true;
		try
		{
			Log4J.logp.info("************** Started :: inProgressStatus *************");
			Thread.sleep(2000);
			/*	Login_Lib.logOut_App(); // Logout from Sagar user role
				Thread.sleep(2000);
				Login_Lib.login("ag007"); // Login with Akash user role
				Thread.sleep(2000);*/

			searchCriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(1000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			// to check coder user role
			Log4J.logp.info("=============== Coder user role =========== ");
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("tc002");
			Common_Lib.waitForObject(groupingpnl_webe.ico_Grouping, "clickable", 15);
			driver.navigate().back();
			Thread.sleep(2000);

			// to check when coder open the case when one case is already in In Progess Status
			Common_Lib.openCase("tc003");
			Thread.sleep(2000);
			strAlert = "You already have a case in progress. Kindly work on existing case.";
			Log4J.logp.info("Expected Validation is :: " + strAlert);
			Common_Lib.IsCustomAlertPresent();
			if (Common_Lib.CustomAlertText.equalsIgnoreCase(strAlert))
			{
				Log4J.logp.info("Validation has been displayed successfully. User can not open the case");
			}
			else
			{
				bstatus = false;
			}

			// to check when CDI open the case when one case is already in In Progress status for coder
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			Common_Lib.openCase("tc003");
			Thread.sleep(2000);
			Log4J.logp.info("Expected Validation is :: " + strAlert);
			Common_Lib.IsCustomAlertPresent();
			if (Common_Lib.CustomAlertText.equalsIgnoreCase(strAlert))
			{
				Log4J.logp.info("Validation has been displayed successfully. CDI can not open the case");
			}
			else
			{
				bstatus = false;
			}

			// Check for CDI user role
			Log4J.logp.info("=============== CDI user role =========== ");
			// to check when user 2 open the case when one case is already In Progress status for user 1
			Thread.sleep(2000);
			Login_Lib.logOut_App(); // Logout from akash user role
			Thread.sleep(2000);
			Login_Lib.login("ag012"); // Login with Jay user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			Common_Lib.openCase("tc003");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "visibility", 10);
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				Log4J.logp.info("Case open successfully by CDI");
			}
			else
			{
				bstatus = false;
			}
			driver.navigate().back();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);

			// to check when CDI open another case when one case is already in Progress status
			Common_Lib.openCase("tc004");
			Thread.sleep(2000);
			strAlert = "You already have a case in progress. Kindly work on existing case.";
			Log4J.logp.info("Expected Validation is :: " + strAlert);
			Common_Lib.IsCustomAlertPresent();
			if (Common_Lib.CustomAlertText.equalsIgnoreCase(strAlert))
			{
				Log4J.logp.info("Validation has been displayed successfully. CDI can not open the case");
			}
			else
			{
				bstatus = false;
			}

			// to check when coder user role try to oprn the case when one case is already in In Progress status
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("tc004");
			strAlert = "You already have a case in progress. Kindly work on existing case.";
			Log4J.logp.info("Expected Validation is :: " + strAlert);
			Common_Lib.IsCustomAlertPresent();
			if (Common_Lib.CustomAlertText.equalsIgnoreCase(strAlert))
			{
				Log4J.logp.info("Validation has been displayed successfully. Coder can not open the case");
			}
			else
			{
				bstatus = false;
			}

			// to check when another user try to open the case
			Thread.sleep(2000);
			Login_Lib.logOut_App(); // Logout from Jay user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 2);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("tc002");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "visibility", 15);
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				Log4J.logp.info("Case open successfully by Coder");
			}
			else
			{
				bstatus = false;
			}

			Log4J.logp.info("************** Ended :: inProgressStatus *************");
			Assert.assertTrue(bstatus, "inPRogressStatus failed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: inProgressStatus");
			Assert.assertTrue(false, "Problem Found in :: inProgressStatus");
			e.printStackTrace();
		}
		finally
		{
			groupingpnl_webe.btn_Later.click();
		}
	}

	/**
	 * This method is check that CDS user can raise query on Accepted, Rejected and Modified Code
	 * 
	 * @author agupta
	 * @since 2/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2773 : Verify that CDS user can raise query on accepted, rejected, and modified code" + "ezCAC_MVP-Reg-2777 :  Verify that CDS user can raise query on Modified + accepted code"
			+ "ezCAC_MVP_Reg-2778 : Verify that CDS user can raise query on Modified + Rejected code", priority = 164)
	public static void CDSQueryWithAcceptRejectModifiedCode()
	{
		boolean bstatus = true;
		try
		{
			Log4J.logp.info("**************** Started :: CDSQueryWithAcceptRejectModifiedCode *****************");
			Thread.sleep(2000);
			/*Login_Lib.logOut_App(); // Logout from Sagar user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); //Login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);*/
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			Log4J.logp.info("===================================  INPATIENT Case ===============================================");
			Common_Lib.openCase("CD001");
			Common_Lib.waitForObject(codingPnl_webe.lnk_Accept_Admitting, "visibility", 15);

			// Check CDS query with Admitting Diagnosis code
			Log4J.logp.info("================= Started Admitting Diagnosis Code ============");
			codingPnl_webe.lnk_Accept_Admitting.click();
			Thread.sleep(2000);
			Log4J.logp.info("Admitting Diagnosis code is Accepted");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD001", codingPnl_webe.lst_Admitting_Diagnosis, codingPnl_webe.lst_QueryPhysician_Admittng);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Admitting, codingPnl_webe.ico_Accepted_Probable_Admitting, null, "accepted");

			// Reject Assocaited code of Admitting Diagnosis code
			codingPnl_webe.ico_Reject_Associated_Admitting.click();
			Thread.sleep(2000);
			Log4J.logp.info("Associated code Admitting Diagnosis code is Rejected");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD002", codingPnl_webe.lst_Admitting_Diagnosis, codingPnl_webe.lst_QueryPhysician_Admittng);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Admitting, codingPnl_webe.ico_Accepted_Probable_Admitting, null, "accepted");

			// Modify Associated code of Admitting Diagnosis code then raise query on that code
			WorkingScreen_Lib.modify_Code("CD001", "CD002");
			Thread.sleep(2000);
			Log4J.logp.info("Associated code of Admitting Diagnosis code is Modified successfully");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD003", codingPnl_webe.lst_Admitting_Diagnosis, codingPnl_webe.lst_QueryPhysician_Admittng);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Admitting, codingPnl_webe.ico_Accepted_Probable_Admitting, codingPnl_webe.ico_Modify_Associated_Admitting, "modified");

			// to check when coder raise query on Modified + Accepeted code 
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD013", codingPnl_webe.lst_Admitting_Diagnosis, codingPnl_webe.lst_QueryPhysician_Admittng);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Admitting, codingPnl_webe.ico_Accepted_Probable_Admitting, codingPnl_webe.ico_Modify_Associated_Admitting, "modified");

			// to check when coder raise query on Modified + Rejected code
			codingPnl_webe.ico_Reject_Associated_Admitting.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD014", codingPnl_webe.lst_Admitting_Diagnosis, codingPnl_webe.lst_QueryPhysician_Admittng);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Admitting, codingPnl_webe.ico_Accepted_Probable_Admitting, codingPnl_webe.ico_Modify_Associated_Admitting, "modified");
			Log4J.logp.info("================= Ended Admitting Diagnosis Code ============");

			// Check CDS query with Princiapl Diagnosis code
			Log4J.logp.info("================= Started Principal Diagnosis Code ============");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Principal, 7);
			Thread.sleep(200);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD004", codingPnl_webe.lst_Principal_Diagnosis, codingPnl_webe.lst_QueryPhysician_Principal);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Principal, codingPnl_webe.ico_Accpeted_Probable_Principal, null, "accepted");

			// Reject Assocaited code of Principal Diagnosis code
			codingPnl_webe.ico_Reject_Associated_Principal.click();
			Thread.sleep(2000);
			Log4J.logp.info("Associated code of Principal code is Rejected");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD005", codingPnl_webe.lst_Principal_Diagnosis, codingPnl_webe.lst_QueryPhysician_Principal);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Principal, codingPnl_webe.ico_Accpeted_Probable_Principal, null, "accepted");

			// Modify Associated code of Principal Diagnosis code then raise query on that code
			WorkingScreen_Lib.modify_Code("CD003", "CD004");
			Thread.sleep(2000);
			Log4J.logp.info("Associated code of Principal Diagnosis code is Modified successfully");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD006", codingPnl_webe.lst_Principal_Diagnosis, codingPnl_webe.lst_QueryPhysician_Principal);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Principal, codingPnl_webe.ico_Accpeted_Probable_Principal, codingPnl_webe.ico_Modify_Associated_Principal, "modified");

			// to check when CDI raise query on Modified + Accepeted code
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD015", codingPnl_webe.lst_Principal_Diagnosis, codingPnl_webe.lst_QueryPhysician_Principal);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Principal, codingPnl_webe.ico_Accpeted_Probable_Principal, codingPnl_webe.ico_Modify_Associated_Principal, "modified");

			// to check when CDI raise query on Modified + Rejected
			Thread.sleep(2000);
			codingPnl_webe.ico_Reject_Associated_Principal.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD016", codingPnl_webe.lst_Principal_Diagnosis, codingPnl_webe.lst_QueryPhysician_Principal);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Principal, codingPnl_webe.ico_Accpeted_Probable_Principal, codingPnl_webe.ico_Modify_Associated_Principal, "modified");
			Log4J.logp.info("================= Ended Principal Diagnosis Code ============");

			// Check CDS Query with Secondary Diagnosis Code
			Log4J.logp.info("================= Started Secondary Diagnosis Code ============");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_First_Secondary, 10);
			Thread.sleep(2000);
			codingPnl_webe.lnk_First_Secondary.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD007", codingPnl_webe.lst_Secondary_Diagnosis, codingPnl_webe.lst_QueryPhysician_Secondary);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Secondary, codingPnl_webe.ico_Accpeted_Probable_Secondary, null, "accepted");

			// Reject Assocaited code of Secondary Diagnosis code
			codingPnl_webe.ico_Reject_Associated_Secondary.click();
			Thread.sleep(2000);
			Log4J.logp.info("Associated code of Secondary code is Rejected");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.ico_Reject_Secondary, 10);
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD008", codingPnl_webe.lst_Last_Secondary_Diagnosis, codingPnl_webe.lst_QueryPhysician_Last_Secondary);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Secondary, codingPnl_webe.ico_Accpeted_Probable_Secondary, null, "accepted");

			// Modify Associated code of Secondary Diagnosis code then raise query on that code
			Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			WorkingScreen_Lib.modify_Code("CD005", "CD006");
			Thread.sleep(2000);
			Log4J.logp.info("Associated code of Secondary Diagnosis code is Modified successfully");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD009", codingPnl_webe.lst_Secondary_Diagnosis, codingPnl_webe.lst_QueryPhysician_Secondary);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Secondary, codingPnl_webe.ico_Accpeted_Probable_Secondary, codingPnl_webe.ico_Modify_Associated_Secondary, "modified");

			// to check CDI raise query on Modified + Accepted code
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD017", codingPnl_webe.lst_Secondary_Diagnosis, codingPnl_webe.lst_QueryPhysician_Secondary);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Secondary, codingPnl_webe.ico_Accpeted_Probable_Secondary, codingPnl_webe.ico_Modify_Associated_Secondary, "modified");

			// to check CDI raise query on Modified + Rejected code
			Thread.sleep(2000);
			codingPnl_webe.ico_Reject_Associated_Secondary.click();
			Thread.sleep(2000);
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.ico_Reject_Secondary, 10);
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD018", codingPnl_webe.lst_Last_Secondary_Diagnosis, codingPnl_webe.lst_QueryPhysician_Last_Secondary);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Secondary, codingPnl_webe.ico_Accpeted_Probable_Secondary, codingPnl_webe.ico_Modify_Associated_Secondary, "modified");

			Log4J.logp.info("================= Ended Secondary Diagnosis Code ============");

			// Check CDS query for Procedure code
			Log4J.logp.info("================= Started Procedure Diagnosis Code ============");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Procedure, 20);
			Thread.sleep(2000);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 7);
			Thread.sleep(2000);
			codingPnl_webe.lnk_Accept_Procedure.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD010", codingPnl_webe.lst_Procedure, codingPnl_webe.lst_QueryPhysician_Procedure);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Procedure, codingPnl_webe.ico_Accepted_Probable_Procedure, null, "accepted");

			// Reject Assocaited code of Procedure code
			codingPnl_webe.ico_Reject_Procedure.click();
			Thread.sleep(2000);
			Log4J.logp.info("Associated code of Procedure code is Rejected");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.ico_Reject_Enabled_Procedure, 5);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD011", codingPnl_webe.lst_Last_Procedure, codingPnl_webe.lst_QueryPhsycian_Last_Procedure);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Procedure, codingPnl_webe.ico_Accepted_Probable_Procedure, null, "accepted");

			// Modify Associated code of Procedure code then raise query on that code
			WorkingScreen_Lib.modify_Code("CD007", "CD008");
			Thread.sleep(2000);
			Log4J.logp.info("Associated code of Procedure code is Modified successfully");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD012", codingPnl_webe.lst_Procedure, codingPnl_webe.lst_QueryPhysician_Procedure);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Procedure, codingPnl_webe.ico_Accepted_Probable_Procedure, codingPnl_webe.ico_Modify_Procedure, "modified");

			// to check CDI raise query on Modified + Accepeted code
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD019", codingPnl_webe.lst_Procedure, codingPnl_webe.lst_QueryPhysician_Procedure);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Procedure, codingPnl_webe.ico_Accepted_Probable_Procedure, codingPnl_webe.ico_Modify_Procedure, "modified");

			// to check when CDI raise query on Modified + Rejected code
			Thread.sleep(2000);
			codingPnl_webe.ico_Reject_Procedure.click();
			Thread.sleep(2000);
			//Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.ico_Reject_Enabled_Procedure, 5);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD020", codingPnl_webe.lst_Last_Procedure, codingPnl_webe.lst_QueryPhsycian_Last_Procedure);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Procedure, codingPnl_webe.ico_Accepted_Probable_Procedure, codingPnl_webe.ico_Modify_Procedure, "modified");

			Log4J.logp.info("================= Ended Procedure Code ============");

			Thread.sleep(2000);
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(1000);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(2000);

			// Check CDS query with Admitting Diagnosis code
			Log4J.logp.info("=================== ICD-10 Coding System ===============");
			Log4J.logp.info("================= Started Admitting Diagnosis Code ============");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD021", codingPnl_webe.lst_Admitting_Diagnosis, codingPnl_webe.lst_QueryPhysician_Admittng);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Admitting, codingPnl_webe.ico_Accepted_Probable_Admitting, null, "accepted");

			// Reject Assocaited code of Admitting Diagnosis code
			codingPnl_webe.ico_Reject_Associated_Admitting.click();
			Thread.sleep(2000);
			Log4J.logp.info("Associated code Admitting Diagnosis code is Rejected");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD022", codingPnl_webe.lst_Admitting_Diagnosis, codingPnl_webe.lst_QueryPhysician_Admittng);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Admitting, codingPnl_webe.ico_Accepted_Probable_Admitting, null, "accepted");

			// Modify Associated code of Admitting Diagnosis code then raise query on that code
			WorkingScreen_Lib.modify_Code("CD009", "CD010");
			Thread.sleep(2000);
			Log4J.logp.info("Associated code of Admitting Diagnosis code is Modified successfully");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD023", codingPnl_webe.lst_Admitting_Diagnosis, codingPnl_webe.lst_QueryPhysician_Admittng);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Admitting, codingPnl_webe.ico_Accepted_Probable_Admitting, codingPnl_webe.ico_Modify_Associated_Admitting, "modified");

			// to check when coder raise query on Modified + Accepeted code 
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD024", codingPnl_webe.lst_Admitting_Diagnosis, codingPnl_webe.lst_QueryPhysician_Admittng);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Admitting, codingPnl_webe.ico_Accepted_Probable_Admitting, codingPnl_webe.ico_Modify_Associated_Admitting, "modified");

			// to check when coder raise query on Modified + Rejected code
			codingPnl_webe.ico_Reject_Associated_Admitting.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD025", codingPnl_webe.lst_Admitting_Diagnosis, codingPnl_webe.lst_QueryPhysician_Admittng);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Admitting, codingPnl_webe.ico_Accepted_Probable_Admitting, codingPnl_webe.ico_Modify_Associated_Admitting, "modified");
			Log4J.logp.info("================= Ended Admitting Diagnosis Code ============");

			// Check CDS query with Princiapl Diagnosis code
			Log4J.logp.info("================= Started Principal Diagnosis Code ============");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Principal, 7);
			Thread.sleep(200);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD026", codingPnl_webe.lst_Principal_Diagnosis, codingPnl_webe.lst_QueryPhysician_Principal);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Principal, codingPnl_webe.ico_Accpeted_Probable_Principal, null, "accepted");

			// Reject Assocaited code of Principal Diagnosis code
			codingPnl_webe.ico_Reject_Associated_Principal.click();
			Thread.sleep(2000);
			Log4J.logp.info("Associated code of Principal code is Rejected");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD027", codingPnl_webe.lst_Principal_Diagnosis, codingPnl_webe.lst_QueryPhysician_Principal);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Principal, codingPnl_webe.ico_Accpeted_Probable_Principal, null, "accepted");

			// Modify Associated code of Principal Diagnosis code then raise query on that code
			WorkingScreen_Lib.modify_Code("CD011", "CD012");
			Thread.sleep(2000);
			Log4J.logp.info("Associated code of Principal Diagnosis code is Modified successfully");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD028", codingPnl_webe.lst_Principal_Diagnosis, codingPnl_webe.lst_QueryPhysician_Principal);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Principal, codingPnl_webe.ico_Accpeted_Probable_Principal, codingPnl_webe.ico_Modify_Associated_Principal, "modified");

			// to check when CDI raise query on Modified + Accepeted code
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD029", codingPnl_webe.lst_Principal_Diagnosis, codingPnl_webe.lst_QueryPhysician_Principal);
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Principal, codingPnl_webe.ico_Accpeted_Probable_Principal, codingPnl_webe.ico_Modify_Associated_Principal, "modified");

			// to check when CDI raise query on Modified + Rejected
			Thread.sleep(2000);
			codingPnl_webe.ico_Reject_Associated_Principal.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD030", codingPnl_webe.lst_Principal_Diagnosis, codingPnl_webe.lst_QueryPhysician_Principal);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Principal, codingPnl_webe.ico_Accpeted_Probable_Principal, codingPnl_webe.ico_Modify_Associated_Principal, "modified");
			Log4J.logp.info("================= Ended Principal Diagnosis Code ============");

			// Check CDS Query with Secondary Diagnosis Code
			Log4J.logp.info("================= Started Secondary Diagnosis Code ============");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_First_Secondary, 10);
			Thread.sleep(2000);
			codingPnl_webe.lnk_First_Secondary.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD031", codingPnl_webe.lst_Secondary_Diagnosis, codingPnl_webe.lst_QueryPhysician_Secondary);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Secondary, codingPnl_webe.ico_Accpeted_Probable_Secondary, null, "accepted");

			// Reject Assocaited code of Secondary Diagnosis code
			codingPnl_webe.ico_Reject_Associated_Secondary.click();
			Thread.sleep(2000);
			Log4J.logp.info("Associated code of Secondary code is Rejected");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.ico_Reject_Secondary, 10);
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD032", codingPnl_webe.lst_Last_Secondary_Diagnosis, codingPnl_webe.lst_QueryPhysician_Last_Secondary);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Secondary, codingPnl_webe.ico_Accpeted_Probable_Secondary, null, "accepted");

			// Modify Associated code of Secondary Diagnosis code then raise query on that code
			Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 1500);
			Thread.sleep(2000);
			WorkingScreen_Lib.modify_Code("CD013", "CD014");
			Thread.sleep(2000);
			Log4J.logp.info("Associated code of Secondary Diagnosis code is Modified successfully");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD033", codingPnl_webe.lst_Secondary_Diagnosis, codingPnl_webe.lst_QueryPhysician_Secondary);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Secondary, codingPnl_webe.ico_Accpeted_Probable_Secondary, codingPnl_webe.ico_Modify_Associated_Secondary, "modified");

			// to check CDI raise query on Modified + Accepted code
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD034", codingPnl_webe.lst_Secondary_Diagnosis, codingPnl_webe.lst_QueryPhysician_Secondary);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Secondary, codingPnl_webe.ico_Accpeted_Probable_Secondary, codingPnl_webe.ico_Modify_Associated_Secondary, "modified");

			// to check CDI raise query on Modified + Rejected code
			codingPnl_webe.ico_Reject_Associated_Secondary.click();
			Thread.sleep(2000);
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.ico_Reject_Secondary, 10);
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD035", codingPnl_webe.lst_Last_Secondary_Diagnosis, codingPnl_webe.lst_QueryPhysician_Last_Secondary);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Secondary, codingPnl_webe.ico_Accpeted_Probable_Secondary, codingPnl_webe.ico_Modify_Associated_Secondary, "modified");
			Log4J.logp.info("================= Ended Secondary Diagnosis Code ============");

			// Check CDS query for Procedure code
			Log4J.logp.info("================= Started Procedure  Code ============");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Procedure, 25);
			Thread.sleep(2000);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 7);
			Thread.sleep(2000);
			codingPnl_webe.lnk_Accept_Procedure.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD036", codingPnl_webe.lst_Procedure, codingPnl_webe.lst_QueryPhysician_Procedure);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Procedure, codingPnl_webe.ico_Accepted_Probable_Procedure, null, "accepted");

			// Reject Assocaited code of Procedure code
			codingPnl_webe.ico_Reject_Procedure.click();
			Thread.sleep(2000);
			Log4J.logp.info("Associated code of Procedure code is Rejected");
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD037", codingPnl_webe.lst_Procedure, codingPnl_webe.lst_QueryPhysician_Procedure);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Procedure, codingPnl_webe.ico_Accepted_Probable_Procedure, null, "accepted");

			// Modify Associated code of Procedure code then raise query on that code
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 20);
			Thread.sleep(2000);
			WorkingScreen_Lib.modify_oneCode_with_Second_withText("icd10inpatient", "00B00ZX", "00B00ZZ");
			Thread.sleep(2000);
			Log4J.logp.info("Associated code of Procedure code is Modified successfully");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD038", codingPnl_webe.lst_Procedure, codingPnl_webe.lst_QueryPhysician_Procedure);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Procedure, codingPnl_webe.ico_Accepted_Probable_Procedure, codingPnl_webe.ico_Modify_Procedure, "modified");

			// to check when CDI raise query on Modified + Rejected code
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 20);
			codingPnl_webe.ico_Reject_Procedure.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD040", codingPnl_webe.lst_Procedure, codingPnl_webe.lst_QueryPhysician_Procedure);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Procedure, codingPnl_webe.ico_Accepted_Probable_Procedure, codingPnl_webe.ico_Modify_Procedure, "modified");
			Log4J.logp.info("================= Ended Procedure  Code ============");

			// Check CDS query for CPT code of Of Outpatient Case
			groupingpnl_webe.btn_Later.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);
			Log4J.logp.info("==============================  OUTPATIENT Case ========================================================");
			Common_Lib.openCase("CD002");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "visibility", 15);

			// Check CDS query for CPT code
			Log4J.logp.info("================= Started CPT Code ============");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Procedure, 25);
			Thread.sleep(2000);
			codingPnl_webe.lnk_Accept_Procedure.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD041", codingPnl_webe.lst_Procedure, codingPnl_webe.lst_QueryPhysician_Procedure);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Procedure, codingPnl_webe.ico_Accepted_Probable_Procedure, null, "accepted");

			// Reject Associated code of CPT code
			codingPnl_webe.ico_Reject_Procedure.click();
			Thread.sleep(2000);
			Log4J.logp.info("Associated code of CPT is Rejected");
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.ico_Reject_Enabled_Procedure, 5);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD042", codingPnl_webe.lst_Procedure, codingPnl_webe.lst_QueryPhysician_Procedure);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Procedure, codingPnl_webe.ico_Accepted_Probable_Procedure, null, "accepted");

			// Modify Associated code of CPT code then raise query on that code
			WorkingScreen_Lib.modify_Code("CD017", "CD018");
			Thread.sleep(2000);
			Log4J.logp.info("Associated code of CPT code is Modified successfully");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD043", codingPnl_webe.lst_Procedure, codingPnl_webe.lst_QueryPhysician_Procedure);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Procedure, codingPnl_webe.ico_Accepted_Probable_Procedure, codingPnl_webe.ico_Modify_Procedure, "modified");

			// to check CDI raise query on Modified + Accepeted code
			Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 5);
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD044", codingPnl_webe.lst_Procedure, codingPnl_webe.lst_QueryPhysician_Procedure);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Procedure, codingPnl_webe.ico_Accepted_Probable_Procedure, codingPnl_webe.ico_Modify_Procedure, "modified");

			// to check when CDI raise query on Modified + Rejected code
			codingPnl_webe.ico_Reject_Procedure.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD045", codingPnl_webe.lst_Procedure, codingPnl_webe.lst_QueryPhysician_Procedure);
			issuepnl_webe.btn_Issues.click();
			bstatus = WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Procedure, codingPnl_webe.ico_Accepted_Probable_Procedure, codingPnl_webe.ico_Modify_Procedure, "modified");
			Log4J.logp.info("================= Ended CPT Code ============");

			Log4J.logp.info("**************** Ended :: CDSQueryWithAcceptRejectModifiedCode *****************");
			Assert.assertTrue(bstatus, "CDSQueryWithAcceptRejectModifiedCode is Completed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: CDSQueryWithAcceptRejectModifiedCode");
			Assert.assertTrue(false, "Problem found in :: CDSQueryWithAcceptRejectModifiedCode");
			e.printStackTrace();
		}
		finally
		{
			groupingpnl_webe.btn_Later.click();
		}
	}

	/**
	 * This method is check that when CDS user remodified Associated and Probable code
	 * 
	 * @author agupta
	 * @since 09/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2796 : Verify that if CDS user re-modifies associated code which was previously suggested by the system" + "ezCAC_MVP_Reg-2797 : Verify that if CDS user re-modifes probable code which was previosly added on associated code", priority = 163)
	public static void reModifyCode()
	{
		boolean bstatus = true;
		try
		{
			Log4J.logp.info("*********************** Started ::: reModifyCode **********************");
			Thread.sleep(2000);
			/*Login_Lib.logOut_App(); // Logout from sagar user role
			Thread.sleep(2000);
			Login_Lib.login("ag007"); // Login with Akash user role
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);*/
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);

			// Outpatient Case
			Common_Lib.openCase("CD003");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);

			Log4J.logp.info("===================== Started :: Ouptatient Case =====================");

			// Check with Principal Diagnosis code
			Log4J.logp.info("============ Started :: Principal Code ==============");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD001", codingPnl_webe.lst_Principal_Diagnosis, codingPnl_webe.lnk_query_first_Principal_evi);
			issuepnl_webe.btn_Issues.click();
			WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Principal, codingPnl_webe.ico_Accpeted_Probable_Principal, null, "accepted");

			// Associated code
			WorkingScreen_Lib.modify_Code("CD019", "CD020");
			Log4J.logp.info("Associated code is Modified");
			WorkingScreen_Lib.modify_Code("CD020", "CD019");

			// Probable code
			Thread.sleep(2000);
			WorkingScreen_Lib.modify_Code("CD021", "CD022");
			Log4J.logp.info("Probable code of Principal diagnosis code has been modified");
			WorkingScreen_Lib.modify_Code("CD022", "CD0021");
			bstatus = WorkingScreen_Lib.checkModifyStatus(codingPnl_webe.ico_Modify_Associated_Principal, codingPnl_webe.ico_Modify_Probable_Principal);
			Log4J.logp.info("============ Ended :: Principal Code ==============");

			// Check with Secondary diagnosis code
			Log4J.logp.info("============ Started :: Secondary Diagnosis Code ==============");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD002", codingPnl_webe.lst_Secondary_Diagnosis, codingPnl_webe.lnk_query_first_secondary_evi);
			issuepnl_webe.btn_Issues.click();
			WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Secondary, codingPnl_webe.ico_Accpeted_Probable_Secondary, null, "accepted");

			// Associated Code
			WorkingScreen_Lib.modify_Code("CD023", "CD020");
			Log4J.logp.info("Associated code is modified");
			WorkingScreen_Lib.modify_Code("CD020", "CD023");

			//Probable Code
			WorkingScreen_Lib.modify_Code("CD024", "CD020");
			Log4J.logp.info("Probable code is modified");
			WorkingScreen_Lib.modify_Code("CD020", "CD024");
			bstatus = WorkingScreen_Lib.checkModifyStatus(codingPnl_webe.ico_Modify_Associated_Secondary, codingPnl_webe.ico_Modify_Probable_Secondary);
			Log4J.logp.info("============ Ended :: Secondary Diagnosis Code ==============");

			// Check with CPT code
			Log4J.logp.info("====================== Started :: CPT code =====================");
			Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 100);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD041", codingPnl_webe.lst_Procedure, codingPnl_webe.lnk_query_first_procedure_evi);
			issuepnl_webe.btn_Issues.click();
			WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Procedure, codingPnl_webe.ico_Accepted_Probable_Procedure, null, "accepted");

			// Associated Code
			WorkingScreen_Lib.modify_Code("CD025", "CD018");
			Log4J.logp.info("Associated code is modified");
			WorkingScreen_Lib.modify_Code("CD018", "CD025");

			// Probable code
			WorkingScreen_Lib.modify_Code("ag015", "CD018");
			Log4J.logp.info("Probable code is modified");
			WorkingScreen_Lib.modify_Code("CD018", "ag015");
			bstatus = WorkingScreen_Lib.checkModifyStatus(codingPnl_webe.ico_Modify_Procedure, codingPnl_webe.ico_Modify_Probable_Procedure);
			Log4J.logp.info("====================== Ended :: CPT code =====================");

			// Switch to ICD-10 Coding System
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(1000);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(2000);
			Log4J.logp.info("=========================== Started :: ICD-10 Coding System ============================");

			// Check with Principal Diagnosis code
			Log4J.logp.info("============ Started :: Principal Code ==============");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD024", codingPnl_webe.lst_Principal_Diagnosis, codingPnl_webe.lnk_query_first_Principal_evi);
			issuepnl_webe.btn_Issues.click();
			WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Principal, codingPnl_webe.ico_Accpeted_Probable_Principal, null, "accepted");

			// Associated code
			WorkingScreen_Lib.modify_Code("CD026", "CD014");
			Log4J.logp.info("Associated code is Modified");
			WorkingScreen_Lib.modify_Code("CD014", "CD026");

			// Probable code
			WorkingScreen_Lib.modify_Code("CD028", "CD011");
			Log4J.logp.info("Probable code of Principal diagnosis code has been modified");
			WorkingScreen_Lib.modify_Code("CD011", "CD028");
			bstatus = WorkingScreen_Lib.checkModifyStatus(codingPnl_webe.ico_Modify_Associated_Principal, codingPnl_webe.ico_Modify_Probable_Principal);
			Log4J.logp.info("============ Ended :: Principal Code ==============");

			// Check with Secondary diagnosis code
			Log4J.logp.info("============ Started :: Secondary Diagnosis Code ==============");
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD021", codingPnl_webe.lst_Secondary_Diagnosis, codingPnl_webe.lnk_query_first_secondary_evi);
			issuepnl_webe.btn_Issues.click();
			WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Secondary, codingPnl_webe.ico_Accpeted_Probable_Secondary, null, "accepted");

			// Associated Code
			WorkingScreen_Lib.modify_Code("CD029", "CD012");
			Log4J.logp.info("Associated code is modified");
			WorkingScreen_Lib.modify_Code("CD012", "CD029");

			//Probable Code
			WorkingScreen_Lib.modify_Code("CD027", "CD012");
			Log4J.logp.info("Probable code is modified");
			WorkingScreen_Lib.modify_Code("CD012", "CD027");
			bstatus = WorkingScreen_Lib.checkModifyStatus(codingPnl_webe.ico_Modify_Associated_Secondary, codingPnl_webe.ico_Modify_Probable_Secondary);
			Log4J.logp.info("============ Ended :: Secondary Diagnosis Code ==============");
			Log4J.logp.info("=========================== Ended :: ICD-10 Coding System ============================");
			Log4J.logp.info("===================== Ended :: Ouptatient Case =====================");

			groupingpnl_webe.btn_Later.click();
			Common_Lib.waitForObject(landingp_webe.lbl_UserName, "visibility", 3);

			// Inpatient Case
			Log4J.logp.info("===================== Started :: Inpatient Case =====================");

			// Check with Admitting Diagnosis code
			Common_Lib.openCase("CD004");
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD001", codingPnl_webe.lst_Admitting_Diagnosis, codingPnl_webe.lnk_query_first_admit_evi);
			issuepnl_webe.btn_Issues.click();
			WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Admitting, codingPnl_webe.ico_Accepted_Probable_Admitting, null, "accepted");

			// Associated Code
			WorkingScreen_Lib.modify_Code("CD030", "CD003");
			Log4J.logp.info("Associated code is modified");
			WorkingScreen_Lib.modify_Code("CD003", "CD030");

			// Probable code
			Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 100);
			Thread.sleep(2000);
			WorkingScreen_Lib.modify_Code("CD021", "CD019");
			Log4J.logp.info("Probable code modified");
			WorkingScreen_Lib.modify_Code("CD019", "CD021");
			bstatus = WorkingScreen_Lib.checkModifyStatus(codingPnl_webe.ico_Modify_Associated_Admitting, codingPnl_webe.ico_Modify_Probable_Admitting);

			//Check with Procedure Code
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Procedure, 25);
			Thread.sleep(2000);
			WorkingScreen_Lib.CDS_QueryCodingPnl("CD010", codingPnl_webe.lst_Procedure, codingPnl_webe.lnk_query_first_procedure_evi);
			issuepnl_webe.btn_Issues.click();
			WorkingScreen_Lib.checkIconStatus(codingPnl_webe.ico_Accepted_Associated_Procedure, codingPnl_webe.ico_Accepted_Probable_Procedure, null, "accepted");

			// Associated Code
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 20);
			WorkingScreen_Lib.modify_Code("CD031", "CD008");
			Log4J.logp.info("Associated Code modified");
			WorkingScreen_Lib.modify_Code("CD008", "CD031");

			// Probable code
			WorkingScreen_Lib.modify_Code("sk003", "CD007");
			Log4J.logp.info("Probable code is modified");
			WorkingScreen_Lib.modify_Code("CD007", "sk003");
			bstatus = WorkingScreen_Lib.checkModifyStatus(codingPnl_webe.ico_Modify_Procedure, codingPnl_webe.ico_Modify_Probable_Procedure);

			Log4J.logp.info("===================== Ended :: Inpatient Case =====================");

			Log4J.logp.info("*********************** Ended ::: reModifyCode	 **********************");
			Assert.assertTrue(bstatus, "reModifyCode is Completed");
		}
		catch (Exception e)
		{
			Log4J.logp.error("******************** Problem Found In : reModifyCode *****************");
			Assert.assertTrue(false, "Problem Found in :: reModifyCode");
			e.printStackTrace();
		}
		finally
		{
			groupingpnl_webe.btn_Later.click();
		}
	}

	/**
	 * This method is for verify Code type titles in coding panel
	 * 
	 * We get data from "td_searchcase"
	 * 
	 * @author skhalasi
	 * @since 20/10/2014
	 * 
	 * */
	@Test(description = "ezCAC_MVP_Reg-462:To verify coding panel titles with inpatient/outpatient and both coding systems", priority = 14)
	public static void verifyTitlesInCodingPnl()
	{
		try
		{
			Log4J.logp.info("************** Started - verifyTitlesInCodingPnl **************");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Log4J.logp.info("======= In verifyTitlesInCodingPnl for Inpatient =======");

			//open inpatient case
			Common_Lib.openCase("sk015");
			Thread.sleep(5000);

			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_diagnosis))
			{
				Log4J.logp.error("Diagnosis label is not present in ICD9");
				Assert.assertTrue(false, "Diagnosis label is not present in ICD9");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_Admitdiagnosis))
			{
				Log4J.logp.error("Admitting Diagnosis label is not present in ICD9");
				Assert.assertTrue(false, "Admitting Diagnosis label is not present in ICD9");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_Principaldiagnosis))
			{
				Log4J.logp.error("Principal Diagnosis label is not present in ICD9");
				Assert.assertTrue(false, "Principal Diagnosis label is not present in ICD9");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_Secondarydiagnosis))
			{
				Log4J.logp.error("Secondary Diagnosis label is not present in ICD9");
				Assert.assertTrue(false, "Secondary Diagnosis label is not present in ICD9");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_Procedure))
			{
				Log4J.logp.error("Procedure label is not present in ICD9");
				Assert.assertTrue(false, "Procedure label is not present in ICD9");
			}

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_diagnosis))
			{
				Log4J.logp.error("Diagnosis label is not present in ICD10");
				Assert.assertTrue(false, "Diagnosis label is not present in ICD10");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_Admitdiagnosis))
			{
				Log4J.logp.error("Admitting Diagnosis label is not present in ICD10");
				Assert.assertTrue(false, "Admitting Diagnosis label is not present in ICD10");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_Principaldiagnosis))
			{
				Log4J.logp.error("Principal Diagnosis label is not present in ICD10");
				Assert.assertTrue(false, "Principal Diagnosis label is not present in ICD10");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_Secondarydiagnosis))
			{
				Log4J.logp.error("Secondary Diagnosis label is not present in ICD10");
				Assert.assertTrue(false, "Secondary Diagnosis label is not present in ICD10");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_Procedure))
			{
				Log4J.logp.error("Procedure label is not present in ICD10");
				Assert.assertTrue(false, "Procedure label is not present in ICD10");
			}
			Thread.sleep(1000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			Log4J.logp.info("======= In verifyTitlesInCodingPnl for Outpatient =======");
			//open inpatient case
			Common_Lib.openCase("sk033");
			Thread.sleep(5000);

			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_diagnosis))
			{
				Log4J.logp.error("Diagnosis label is not present in ICD9");
				Assert.assertTrue(false, "Diagnosis label is not present in ICD9");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_Admitdiagnosis))
			{
				Log4J.logp.error("Admitting Diagnosis label is not present in ICD9");
				Assert.assertTrue(false, "Admitting Diagnosis label is not present in ICD9");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_Principaldiagnosis))
			{
				Log4J.logp.error("Principal Diagnosis label is not present in ICD9");
				Assert.assertTrue(false, "Principal Diagnosis label is not present in ICD9");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_Secondarydiagnosis))
			{
				Log4J.logp.error("Secondary Diagnosis label is not present in ICD9");
				Assert.assertTrue(false, "Secondary Diagnosis label is not present in ICD9");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_Procedure))
			{
				Log4J.logp.error("Procedure label is not present in ICD9");
				Assert.assertTrue(false, "Procedure label is not present in ICD9");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_CPT))
			{
				Log4J.logp.error("CPT label is not present in ICD9");
				Assert.assertTrue(false, "CPT label is not present in ICD9");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_EandM))
			{
				Log4J.logp.error("E and M label is not present in ICD9");
				Assert.assertTrue(false, "E and M label is not present in ICD9");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_HCPCS))
			{
				Log4J.logp.error("HCPCS label is not present in ICD9");
				Assert.assertTrue(false, "HCPCS label is not present in ICD9");
			}

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_diagnosis))
			{
				Log4J.logp.error("Diagnosis label is not present in ICD10");
				Assert.assertTrue(false, "Diagnosis label is not present in ICD10");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_Admitdiagnosis))
			{
				Log4J.logp.error("Admitting Diagnosis label is not present in ICD10");
				Assert.assertTrue(false, "Admitting Diagnosis label is not present in ICD10");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_Principaldiagnosis))
			{
				Log4J.logp.error("Principal Diagnosis label is not present in ICD10");
				Assert.assertTrue(false, "Principal Diagnosis label is not present in ICD10");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_Secondarydiagnosis))
			{
				Log4J.logp.error("Secondary Diagnosis label is not present in ICD10");
				Assert.assertTrue(false, "Secondary Diagnosis label is not present in ICD10");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_Procedure))
			{
				Log4J.logp.error("Procedure label is not present in ICD10");
				Assert.assertTrue(false, "Procedure label is not present in ICD10");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_CPT))
			{
				Log4J.logp.error("CPT label is not present in ICD10");
				Assert.assertTrue(false, "CPT label is not present in ICD10");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_EandM))
			{
				Log4J.logp.error("E and M label is not present in ICD10");
				Assert.assertTrue(false, "E and M label is not present in ICD10");
			}
			if (!Common_Lib.checkElementPresent(codingPnl_webe.lbl_HCPCS))
			{
				Log4J.logp.error("HCPCS label is not present in ICD10");
				Assert.assertTrue(false, "HCPCS label is not present in ICD10");
			}
			Log4J.logp.info("**************  Ending - verifyTitlesInCodingPnl  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyTitlesInCodingPnl  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyTitlesInCodingPnl is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for check discharge disposition in outpatient and inpatient case
	 * 
	 * We get data from "td_searchcase"
	 * 
	 * @author skhalasi
	 * @since 21/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-254:To verify 'Discharge Disposition' functionality for inpatient/outpatient case", priority = 15)
	public static void verifyDischargeDisposition()
	{
		String strDesable = null;
		try
		{
			Log4J.logp.info("**************  Started - verifyDischargeDisposition  **************");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			//outpatient case
			Common_Lib.openCase("sk028");
			Thread.sleep(5000);
			//open demograophic panel
			demographicpnl_webe.lnk_DemoPnlExpand.click();

			strDesable = demographicpnl_webe.lst_DischargeDisposition.getAttribute("disabled");
			//as per new version "disabled" is return boolean value so if desabled is present then true otherwise null
			if (strDesable.equals("true"))
			{
				Log4J.logp.info("Discharge disposition is desable for outpatient case");
				Assert.assertTrue(true, "Discharge disposition is desable for outpatient case");
			}
			else
			{
				Log4J.logp.error("Discharge disposition is not desable for outpatient case");
				Assert.assertTrue(false, "Discharge disposition is not desable for outpatient case");
			}
			groupingpnl_webe.btn_Later.click();

			//inpatient case
			Common_Lib.openCase("sk003");
			Thread.sleep(5000);
			//open demograophic panel
			demographicpnl_webe.lnk_DemoPnlExpand.click();

			strDesable = demographicpnl_webe.lst_DischargeDisposition.getAttribute("disabled");
			if (strDesable == null)
			{
				Log4J.logp.info("Discharge disposition is not desable for inpatient case");
				Assert.assertTrue(true, "Discharge disposition is not desable for inpatient case");
			}
			else
			{
				Log4J.logp.error("Discharge disposition is desable for inpatient case");
				Assert.assertTrue(false, "Discharge disposition is desable for inpatient case");
			}

			demographicpnl_webe.lst_DischargeDisposition.click();
			Select dropdown = new Select(demographicpnl_webe.lst_DischargeDisposition);
			//	dropdown.selectByVisibleText("71 Discharged/transferred to another institution for outpatient services  ");
			dropdown.selectByValue("20");
			demographicpnl_webe.lnk_DemoPnlExpand.click();
			Thread.sleep(1000);
			demographicpnl_webe.lnk_DemoPnlExpand.click();
			Thread.sleep(1000);
			if (demographicpnl_webe.lbl_PatientStatus.getText().contains("62"))
			{
				Log4J.logp.info("Discharge disposition is changed for inpatient case");
				Assert.assertTrue(true, "Discharge disposition is changed for inpatient case");
			}
			else
			{
				Log4J.logp.error("Discharge disposition is not changed for inpatient case");
				Assert.assertTrue(false, "Discharge disposition is not changed for inpatient case");
			}
			demographicpnl_webe.lnk_DemoPnlExpand.click();
			Thread.sleep(1000);
			demographicpnl_webe.lnk_DemoPnlExpand.click();
			Thread.sleep(1000);
			demographicpnl_webe.lst_DischargeDisposition.click();
			Select dropdown1 = new Select(demographicpnl_webe.lst_DischargeDisposition);
			//dropdown1.selectByVisibleText(" Other ");
			dropdown1.selectByValue("26");

			demographicpnl_webe.txt_commentDemographic.clear();
			demographicpnl_webe.txt_commentDemographic.sendKeys("ezCACer");
			demographicpnl_webe.btn_saveDemographic.click();

			demographicpnl_webe.lnk_DemoPnlExpand.click();
			Thread.sleep(1000);
			demographicpnl_webe.lnk_DemoPnlExpand.click();
			Thread.sleep(1000);

			/*verify added comment is remaining
			 * if (!demographicpnl_webe.txt_commentDemographic.getText().equals("ezCAC"))
			{
				Log4J.logp.error("Comment is not saved");
				Assert.assertTrue(false, "Comment is not saved");
			}*/
			Log4J.logp.info("**************  Ending - verifyDischargeDisposition  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyDischargeDisposition  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyDischargeDisposition is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * Thid method is for test abtract panel with expand and collapse mode
	 * 
	 * We get data from "td_searchcase"
	 * 
	 * @author skhalasi
	 * @since 24/11/2014
	 * 
	 */
	@Test(description = "ezCAC_MVP_Reg-267:To verify abstract panel in expand/collapse mode" + "ezCAC_MVP_Reg-270:To verify abstract panel status when user already on working screen", priority = 16)
	public static void verifyAbstractPanel()
	{
		String strCollapse;
		String[] strArrData = null;
		String strData = null;
		Map<String, String> mapVerifyTestData = null;

		try
		{
			Log4J.logp.info("**************  Started - verifyAbstractPanel  **************");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			//open fresh case
			Common_Lib.openCase("sk029");
			Thread.sleep(5000);
			abstractpnl_webe.lnk_AbstactPnl.click();
			Thread.sleep(1000);
			if (!abstractpnl_webe.lbl_Status.getText().contains("In Progress"))
			{
				Log4J.logp.error("Case is not showing In Progress from fresh case");
				Assert.assertTrue(false, "Case is not showing In Progress from fresh case");
			}
			Thread.sleep(1000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(1000);

			//open On Hold case
			Common_Lib.openCase("sk031");
			Thread.sleep(5000);
			abstractpnl_webe.lnk_AbstactPnl.click();
			Thread.sleep(1000);
			if (!abstractpnl_webe.lbl_Status.getText().contains("In Progress"))
			{
				Log4J.logp.error("Case is not showing In Progress from On hold");
				Assert.assertTrue(false, "Case is not showing In Progress from On hold");
			}
			Thread.sleep(1000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(1000);

			//open pending case
			Common_Lib.putCaseInPendingStatus("sk031");
			Thread.sleep(1000);
			Common_Lib.openCase("sk032");
			Thread.sleep(5000);
			abstractpnl_webe.lnk_AbstactPnl.click();
			Thread.sleep(1000);
			if (!abstractpnl_webe.lbl_Status.getText().contains("In Progress"))
			{
				Log4J.logp.error("Case is not showing In Progress from pending");
				Assert.assertTrue(false, "Case is not showing In Progress from pending");
			}
			Thread.sleep(1000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(1000);

			//allocate case for In queue stage
			ManualAllocation_Lib.assignCase("sk032", "Susan Wilson");
			Thread.sleep(2000);
			Common_Lib.scrollUp_without_element();
			Thread.sleep(2000);

			searchCriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);

			ManualAllocation_Lib.assignCase("sk030", "Sagar Khalasi");
			Thread.sleep(2000);

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);

			//open In queue case
			Common_Lib.openCase("sk030");
			Thread.sleep(5000);
			abstractpnl_webe.lnk_AbstactPnl.click();
			Thread.sleep(1000);
			if (!abstractpnl_webe.lbl_Status.getText().contains("In Progress"))
			{
				Log4J.logp.error("Case is not showing In Progress from In queue");
				Assert.assertTrue(false, "Case is not showing In Progress from In queue");
			}

			abstractpnl_webe.lnk_AbstactPnl.click();
			//ezCAC_MVP_Reg-267:To verify abstract panel in expand/collapse mode
			//open case

			strCollapse = abstractpnl_webe.lnk_AbstactPnl.getText();

			if (strCollapse.contains("V1") && strCollapse.contains("In Progress") && strCollapse.contains("Sagar Khalasi"))
			{
				Log4J.logp.info("Abstract panel is contains all data in 'collapse' mode");
				Assert.assertTrue(true, "Abstract panel is contains all data in 'collapse' mode");
			}
			else
			{
				Log4J.logp.error("Abstract panel is not contains all data in 'collapse' mode");
				Assert.assertTrue(false, "Abstract panel is not contains all data in 'collapse' mode");
			}
			abstractpnl_webe.lnk_AbstactPnl.click();

			//get data from database to compare
			JDBCMySql sqlConn = new JDBCMySql();

			mapVerifyTestData = sqlConn.getRowbyID("td_verifylistdata", "1");

			strData = mapVerifyTestData.get("expecteddata");
			if (strData != null)
			{
				strArrData = strData.split("\\$");
				if (strArrData.length != 0)
				{
					for (int i = 0; i < strArrData.length; i++)
					{
						if (i != 3)
						{
							if (abstractpnl_webe.lst_labelLst.get(i).getText().equals(strArrData[i]))
							{
								Log4J.logp.info("Found proper text for := " + abstractpnl_webe.lst_labelLst.get(i).getText());
								Assert.assertTrue(true, "Found proper text for := " + abstractpnl_webe.lst_labelLst.get(i).getText());
							}
							else
							{
								Log4J.logp.error("Not Found proper text for := " + abstractpnl_webe.lst_labelLst.get(i).getText());
								Assert.assertTrue(false, "Not Found proper text for := " + abstractpnl_webe.lst_labelLst.get(i).getText());
							}
						}
						else
						{
							if (abstractpnl_webe.lst_labelLst.get(3).isDisplayed())
							{
								Log4J.logp.info("Found DNFC Days found in abstract panel");
								Assert.assertTrue(true, "Found DNFC Days found in abstract panel");
							}
							else
							{
								Log4J.logp.error("Found DNFC Days not found in abstract panel");
								Assert.assertTrue(false, "Found DNFC Days not found in abstract panel");
							}
						}

					}
				}
			}
			Log4J.logp.info("**************  Ending - verifyAbstractPanel  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyAbstractPanel  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyAbstractPanel is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for verify test case based for reference panel
	 * 
	 * We get data from "td_searchcase"
	 * 
	 * @author skhalasi
	 * @since 23/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-657:Verify that User can open reference panel from the code template", priority = 17)
	public static void codingRefrencePnl()
	{
		try
		{
			Log4J.logp.info("**************  Started - codingRefrencePnl  **************");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Log4J.logp.info("====== Check for Inpatient case for ICD9  ======");
			//open fresh case
			Common_Lib.openCase("sk001");
			Thread.sleep(1000);
			for (WebElement singleLink : codingPnl_webe.lst_referenceCodingPnl)
			{
				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, singleLink, 30);
				Thread.sleep(500);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 15);
				Thread.sleep(500);
				//click on refrence button
				singleLink.click();
				Thread.sleep(1000);
				if (!codingPnl_webe.lbl_reference.isDisplayed())
				{
					Log4J.logp.error("Not found refrence panel");
					Assert.assertTrue(false, "Not found refrence panel");
				}
				codingPnl_webe.btn_Close.click();
				Thread.sleep(1000);
			}

			Log4J.logp.info("====== Check for Inpatient case for IC10  ======");

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			for (WebElement singleLink : codingPnl_webe.lst_referenceCodingPnl)
			{
				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, singleLink, 30);
				Thread.sleep(500);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 15);
				Thread.sleep(500);

				//click on refrence button
				singleLink.click();
				Thread.sleep(1000);
				if (!codingPnl_webe.lbl_reference.isDisplayed())
				{
					Log4J.logp.error("Not found refrence panel");
					Assert.assertTrue(false, "Not found refrence panel");
				}
				codingPnl_webe.btn_Close.click();
				Thread.sleep(1000);
			}

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(1000);
			Log4J.logp.info("====== Check for Outpatient case for ICD9  ======");
			//open fresh case
			Common_Lib.openCase("sk034");
			Thread.sleep(5000);

			for (WebElement singleLink : codingPnl_webe.lst_referenceCodingPnl)
			{
				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, singleLink, 30);
				Thread.sleep(500);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 15);
				Thread.sleep(500);

				//click on refrence button
				singleLink.click();
				Thread.sleep(1000);
				if (!codingPnl_webe.lbl_reference.isDisplayed())
				{
					Log4J.logp.error("Not found refrence panel");
					Assert.assertTrue(false, "Not found refrence panel");
				}
				codingPnl_webe.btn_Close.click();
				Thread.sleep(1000);
			}

			Log4J.logp.info("====== Check for Outpatient case for ICD10  ======");

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			for (WebElement singleLink : codingPnl_webe.lst_referenceCodingPnl)
			{
				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, singleLink, 30);
				Thread.sleep(500);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 15);
				Thread.sleep(500);

				//click on refrence button
				singleLink.click();
				Thread.sleep(1000);
				if (!codingPnl_webe.lbl_reference.isDisplayed())
				{
					Log4J.logp.error("Not found refrence panel");
					Assert.assertTrue(false, "Not found refrence panel");
				}
				codingPnl_webe.btn_Close.click();
				Thread.sleep(1000);
			}

			Log4J.logp.info("**************  Ending - codingRefrencePnl  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - codingRefrencePnl  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "codingRefrencePnl is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for verify DRG analysis
	 * 
	 * We get data from "td_searchcase"
	 * 
	 * @author skhalasi
	 * @since 26/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-659:To verify DRG Analysis functionality with below options", priority = 18)
	public static void verifyDRGAnalysis()
	{
		String strAdmitCode = null;
		String strPrincipalCode = null;
		String strViewCaseCode = null;
		String strNewCode = null;
		boolean badmitsatatus = false;
		boolean bprincipalstatus = false;
		WebDriverWait wait;
		try
		{
			Log4J.logp.info("**************  Started - verifyDRGAnalysis  **************");
			wait = new WebDriverWait(driver, 20);
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Common_Lib.openCase("ag006");
			Thread.sleep(1000);

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_Accept_Principal, 30);
			Thread.sleep(1000);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 20);
			Thread.sleep(1000);
			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(1000);

			// grouping 
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(1000);

			//open groping panel 
			groupingpnl_webe.lnk_GroupingPnl.click();
			Thread.sleep(1000);

			//click on DRG analysis
			groupingpnl_webe.lnk_DRGAnalysis.click();
			Thread.sleep(1000);

			// click on first DRG refrence code
			groupingpnl_webe.lnk_firstDRGRefCode.click();
			Thread.sleep(1000);
			strAdmitCode = groupingpnl_webe.lnk_firstDRGRefCode.getText();
			// add as admitting diagnosis
			groupingpnl_webe.lnk_addAdmitFromRef.click();
			Thread.sleep(1000);

			// click on second DRG refrence code
			groupingpnl_webe.lnk_secondDRGRefCode.click();
			Thread.sleep(1000);
			strPrincipalCode = groupingpnl_webe.lnk_secondDRGRefCode.getText();
			// add as principal diagnosis
			groupingpnl_webe.lnk_addPrincipalFromRef.click();
			Thread.sleep(1000);

			// click on Third DRG refrence code
			groupingpnl_webe.lnk_thirdDRGRefCode.click();
			Thread.sleep(1000);
			strViewCaseCode = groupingpnl_webe.lnk_thirdDRGRefCode.getText();
			// view code in code book
			groupingpnl_webe.lnk_viewCodeFromRef.click();
			Thread.sleep(1000);

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));

			strNewCode = codeBook_webe.lnk_searchCode.getText();
			codeBook_webe.lnk_searchCode.click();
			driver.switchTo().defaultContent();
			abstractpnl_webe.btn_Close.click();
			groupingpnl_webe.lnk_GroupingPnl.click();

			Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 1000);
			Thread.sleep(1000);

			// find codes
			badmitsatatus = Common_Lib.codeSearchInCodingPnl(strAdmitCode);
			Common_Lib.verifyFlag(badmitsatatus, "Add as admitting code");
			Thread.sleep(1000);

			bprincipalstatus = Common_Lib.codeSearchInCodingPnl(strPrincipalCode);
			Common_Lib.verifyFlag(bprincipalstatus, "Add as principal code");

			if (strNewCode.equals(strViewCaseCode))
			{
				Log4J.logp.info("Found same code in code book for view code");
				Assert.assertTrue(true, "Found same code in code book for view code");
			}
			else
			{
				Log4J.logp.error("Not found same code in code book for view code");
				Assert.assertTrue(false, "Not found same code in code book for view code");
			}

			Log4J.logp.info("**************  Ending - verifyDRGAnalysis  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyDRGAnalysis **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyDRGAnalysis is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for verify modify all type code, Precondition is we have to take case with two admiiting and full procedure code
	 * 
	 * We take second and third procedure code in ICD 10 inpatient case
	 * 
	 * We get data from "td_searchcase"
	 * 
	 * @author skhalasi
	 * @since 26/11/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-824:Verify that if user modify same code from the coding panel" + "ezCAC_MVP_Reg-825:Verify that if user modify code which is already exist on coding panel", priority = 19)
	public static void verifyModifyAllTypeCode()
	{
		boolean bmodifyStatus = false;
		try
		{
			Log4J.logp.info("**************  Started - verifyModifyAllTypeCode  **************");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Log4J.logp.info("====== In verifyModifyAllTypeCode - for outpatient case  ======");
			Common_Lib.openCase("sk027");
			Thread.sleep(1000);

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstAdmitCodeNum, 30);
			Thread.sleep(1000);
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstAdmitCodeNum, codingPnl_webe.lbl_firstAdmitCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD9 Outpatient Modify Admitting code with same code");
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstAdmitCodeNum, codingPnl_webe.lbl_firstPrincipalCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD9 Outpatient Modify Admitting code with principal code");

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstPrincipalCodeNum, 30);
			Thread.sleep(1000);
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstPrincipalCodeNum, codingPnl_webe.lbl_firstPrincipalCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD9 Outpatient Modify Principal code with same code");
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstPrincipalCodeNum, codingPnl_webe.lbl_firstSecondaryCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD9 Outpatient Modify Principal code with Secondary code");

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstSecondaryCodeNum, 30);
			Thread.sleep(1000);
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstSecondaryCodeNum, codingPnl_webe.lbl_firstSecondaryCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD9 Outpatient Modify Secondary code with same code");
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstSecondaryCodeNum, codingPnl_webe.lbl_firstPrincipalCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD9 Outpatient Modify Secondary code with Principal code");

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_secondProcedureCodeNum, 30);
			Thread.sleep(1000);
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstProcedureCodeNum, codingPnl_webe.lbl_firstProcedureCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD9 Outpatient Modify Procedure code with same code");
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstProcedureCodeNum, codingPnl_webe.lbl_secondProcedureCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD9 Outpatient Modify Procedure with second procedure code");

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstAdmitCodeNum, 30);
			Thread.sleep(1000);
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstAdmitCodeNum, codingPnl_webe.lbl_firstAdmitCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD10 Outpatient Modify Admitting code with same code");
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstAdmitCodeNum, codingPnl_webe.lbl_firstPrincipalCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD10 Outpatient Modify Admitting code with principal code");

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstPrincipalCodeNum, 30);
			Thread.sleep(1000);
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstPrincipalCodeNum, codingPnl_webe.lbl_firstPrincipalCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD10 Outpatient Modify Principal code with same code");
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstPrincipalCodeNum, codingPnl_webe.lbl_firstSecondaryCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD10 Outpatient Modify Principal code with Secondary code");

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstSecondaryCodeNum, 30);
			Thread.sleep(1000);
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstSecondaryCodeNum, codingPnl_webe.lbl_firstSecondaryCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD10 Outpatient Modify Secondary code with same code");
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstSecondaryCodeNum, codingPnl_webe.lbl_firstPrincipalCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD10 Outpatient Modify Secondary code with Principal code");

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_secondProcedureCodeNum, 30);
			Thread.sleep(1000);
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstProcedureCodeNum, codingPnl_webe.lbl_firstProcedureCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD10 Outpatient Modify Procedure code with same code");
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstProcedureCodeNum, codingPnl_webe.lbl_secondProcedureCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD10 Outpatient Modify Procedure with second procedure code");

			groupingpnl_webe.btn_Later.click();

			Log4J.logp.info("====== In verifyModifyAllTypeCode - for Inpatient case  ======");

			Common_Lib.openCase("sk012");
			Thread.sleep(1000);

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstAdmitCodeNum, 30);
			Thread.sleep(1000);
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstAdmitCodeNum, codingPnl_webe.lbl_firstAdmitCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD9 Inpatient Modify Admitting code with same code");
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstAdmitCodeNum, codingPnl_webe.lbl_firstPrincipalCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD9 Inpatient Modify Admitting code with principal code");

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstPrincipalCodeNum, 30);
			Thread.sleep(1000);
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstPrincipalCodeNum, codingPnl_webe.lbl_firstPrincipalCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD9 Inpatient Modify Principal code with same code");
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstPrincipalCodeNum, codingPnl_webe.lbl_firstSecondaryCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD9 Inpatient Modify Principal code with Secondary code");

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstSecondaryCodeNum, 30);
			Thread.sleep(1000);
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstSecondaryCodeNum, codingPnl_webe.lbl_firstSecondaryCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD9 Inpatient Modify Secondary code with same code");
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstSecondaryCodeNum, codingPnl_webe.lbl_firstPrincipalCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD9 Inpatient Modify Secondary code with Principal code");

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_secondProcedureCodeNum, 30);
			Thread.sleep(1000);
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstProcedureCodeNum, codingPnl_webe.lbl_firstProcedureCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD9 Inpatient Modify Procedure code with same code");
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstProcedureCodeNum, codingPnl_webe.lbl_secondProcedureCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD9 Inpatient Modify Procedure with second procedure code");

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstAdmitCodeNum, 30);
			Thread.sleep(1000);
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstAdmitCodeNum, codingPnl_webe.lbl_firstAdmitCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD10 Outpatient Modify Admitting code with same code");
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstAdmitCodeNum, codingPnl_webe.lbl_firstPrincipalCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD10 Outpatient Modify Admitting code with principal code");

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstPrincipalCodeNum, 30);
			Thread.sleep(1000);
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstPrincipalCodeNum, codingPnl_webe.lbl_firstPrincipalCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD10 Outpatient Modify Principal code with same code");
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstPrincipalCodeNum, codingPnl_webe.lbl_firstSecondaryCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD10 Outpatient Modify Principal code with Secondary code");

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstSecondaryCodeNum, 30);
			Thread.sleep(1000);
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstSecondaryCodeNum, codingPnl_webe.lbl_firstSecondaryCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD10 Outpatient Modify Secondary code with same code");
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("", codingPnl_webe.lbl_firstSecondaryCodeNum, codingPnl_webe.lbl_firstPrincipalCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD10 Outpatient Modify Secondary code with Principal code");

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_secondProcedureCodeNum, 30);
			Thread.sleep(1000);
			Common_Lib.scroll_Page(codingPnl_webe.coding_Dragger, 50);
			Thread.sleep(1000);
			/*bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("icd10inpatient", codingPnl_webe.lbl_firstProcedureCodeNum, codingPnl_webe.lbl_firstProcedureCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD10 Inpatient Modify Procedure code with same code");*/

			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("icd10inpatient", codingPnl_webe.lbl_secondProcedureCodeNum, codingPnl_webe.lbl_secondProcedureCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD10 Inpatient Modify Procedure code with same code");
			bmodifyStatus = WorkingScreen_Lib.modify_oneCode_with_Second("icd10inpatient", codingPnl_webe.lbl_secondProcedureCodeNum, codingPnl_webe.lbl_thirdProcedureCodeNum);
			Thread.sleep(1000);
			Common_Lib.verifyFlag(bmodifyStatus, "For ICD10 Inpatient Modify Procedure code with second procedure code");

			Log4J.logp.info("**************  Ending - verifyModifyAllTypeCode  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyModifyAllTypeCode **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyModifyAllTypeCode is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for verify CC and MCC code with modify functionality
	 * 
	 * We get data from "td_searchcase" , "td_searchcode"
	 * 
	 * @author skhalasi
	 * @since 27/11/2014
	 */

	@Test(description = "ezCAC_MVP_Reg-525:Verify that if user add/modify any cc/mcc diagnosis code", priority = 20)
	public static void verifyCCandMCC()
	{
		String strcode = null;
		WebElement webeMCC;
		WebElement webeCC;
		boolean bstatus = false;

		try
		{
			Log4J.logp.info("**************  Started - verifyCCandMCC  **************");
			JDBCMySql query = new JDBCMySql();
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Log4J.logp.info("====== In verifyCCandMCC - for Inpatient case  ======");
			//open case
			Common_Lib.openCase("sk001");
			Thread.sleep(1000);

			WorkingScreen_Lib.addnewCode_withData_ICD9("sk024");
			Thread.sleep(1000);
			strcode = query.getValuebyColumnName("td_searchcode", "sk024", "code");
			Thread.sleep(1000);

			webeMCC = driver.findElement(By.xpath("//div[text()='" + strcode + "']/../../..//span[@title='mcc']"));
			if (webeMCC.isDisplayed())
			{
				Log4J.logp.info("MCC code is added");
				Assert.assertTrue(true, "MCC code is added");
			}
			else
			{
				Log4J.logp.error("MCC code is not added");
				Assert.assertTrue(false, "MCC code is not added");
			}

			bstatus = WorkingScreen_Lib.modify_Code("sk024", "sk025");
			Common_Lib.verifyFlag(bstatus, "Modify MCC to CC code");

			Thread.sleep(1000);
			Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 1000);
			Thread.sleep(1000);

			bstatus = WorkingScreen_Lib.modify_Code("sk025", "sk024");
			Common_Lib.verifyFlag(bstatus, "Modify CC to MCC code");

			Thread.sleep(1000);

			WorkingScreen_Lib.addnewCode_withData_ICD9("sk025");
			Thread.sleep(1000);
			strcode = query.getValuebyColumnName("td_searchcode", "sk025", "code");
			Thread.sleep(1000);

			webeCC = driver.findElement(By.xpath("//div[text()='" + strcode + "']/../../..//span[@title='cc']"));
			if (webeCC.isDisplayed())
			{
				Log4J.logp.info("CC code is added");
				Assert.assertTrue(true, "CC code is added");
			}
			else
			{
				Log4J.logp.error("CC code is not added");
				Assert.assertTrue(false, "CC code is not added");
			}

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			WorkingScreen_Lib.addnewCode_withData_ICD10("sk028");
			Thread.sleep(1000);
			strcode = query.getValuebyColumnName("td_searchcode", "sk028", "code");
			Thread.sleep(1000);

			webeMCC = driver.findElement(By.xpath("//div[text()='" + strcode + "']/../../..//span[@title='mcc']"));
			if (webeMCC.isDisplayed())
			{
				Log4J.logp.info("MCC code is added");
				Assert.assertTrue(true, "MCC code is added");
			}
			else
			{
				Log4J.logp.error("MCC code is not added");
				Assert.assertTrue(false, "MCC code is not added");
			}

			bstatus = WorkingScreen_Lib.modify_Code("sk028", "sk029");
			Common_Lib.verifyFlag(bstatus, "Modify MCC to CC code");

			Thread.sleep(1000);
			Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 1000);
			Thread.sleep(1000);

			bstatus = WorkingScreen_Lib.modify_Code("sk029", "sk028");
			Common_Lib.verifyFlag(bstatus, "Modify CC to MCC code");

			Thread.sleep(1000);

			WorkingScreen_Lib.addnewCode_withData_ICD10("sk029");
			Thread.sleep(1000);
			strcode = query.getValuebyColumnName("td_searchcode", "sk029", "code");
			Thread.sleep(1000);

			webeCC = driver.findElement(By.xpath("//div[text()='" + strcode + "']/../../..//span[@title='cc']"));
			if (webeCC.isDisplayed())
			{
				Log4J.logp.info("CC code is added");
				Assert.assertTrue(true, "CC code is added");
			}
			else
			{
				Log4J.logp.error("CC code is not added");
				Assert.assertTrue(false, "CC code is not added");
			}

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(1000);
			Log4J.logp.info("====== In verifyCCandMCC - for Outpatient case  ======");
			//open case
			Common_Lib.openCase("sk011");
			Thread.sleep(1000);

			WorkingScreen_Lib.addnewCode_withData_ICD9("sk032");
			Thread.sleep(1000);
			strcode = query.getValuebyColumnName("td_searchcode", "sk032", "code");
			Thread.sleep(1000);

			webeMCC = driver.findElement(By.xpath("//div[text()='" + strcode + "']/../../..//span[@title='mcc']"));
			if (webeMCC.isDisplayed())
			{
				Log4J.logp.info("MCC code is added");
				Assert.assertTrue(true, "MCC code is added");
			}
			else
			{
				Log4J.logp.error("MCC code is not added");
				Assert.assertTrue(false, "MCC code is not added");
			}

			bstatus = WorkingScreen_Lib.modify_Code("sk032", "sk033");
			Common_Lib.verifyFlag(bstatus, "Modify MCC to CC code");

			Thread.sleep(1000);
			Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 1000);
			Thread.sleep(1000);

			bstatus = WorkingScreen_Lib.modify_Code("sk033", "sk032");
			Common_Lib.verifyFlag(bstatus, "Modify CC to MCC code");

			Thread.sleep(1000);

			WorkingScreen_Lib.addnewCode_withData_ICD9("sk033");
			Thread.sleep(1000);
			strcode = query.getValuebyColumnName("td_searchcode", "sk033", "code");
			Thread.sleep(1000);

			webeCC = driver.findElement(By.xpath("//div[text()='" + strcode + "']/../../..//span[@title='cc']"));
			if (webeCC.isDisplayed())
			{
				Log4J.logp.info("CC code is added");
				Assert.assertTrue(true, "CC code is added");
			}
			else
			{
				Log4J.logp.error("CC code is not added");
				Assert.assertTrue(false, "CC code is not added");
			}

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			WorkingScreen_Lib.addnewCode_withData_ICD10("sk036");
			Thread.sleep(1000);
			strcode = query.getValuebyColumnName("td_searchcode", "sk036", "code");
			Thread.sleep(1000);

			webeMCC = driver.findElement(By.xpath("//div[text()='" + strcode + "']/../../..//span[@title='mcc']"));
			if (webeMCC.isDisplayed())
			{
				Log4J.logp.info("MCC code is added");
				Assert.assertTrue(true, "MCC code is added");
			}
			else
			{
				Log4J.logp.error("MCC code is not added");
				Assert.assertTrue(false, "MCC code is not added");
			}

			bstatus = WorkingScreen_Lib.modify_Code("sk036", "sk037");
			Common_Lib.verifyFlag(bstatus, "Modify MCC to CC code");

			Thread.sleep(1000);
			Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 1000);
			Thread.sleep(1000);

			bstatus = WorkingScreen_Lib.modify_Code("sk037", "sk036");
			Common_Lib.verifyFlag(bstatus, "Modify CC to MCC code");

			Thread.sleep(1000);

			WorkingScreen_Lib.addnewCode_withData_ICD10("sk037");
			Thread.sleep(1000);
			strcode = query.getValuebyColumnName("td_searchcode", "sk037", "code");
			Thread.sleep(1000);

			webeCC = driver.findElement(By.xpath("//div[text()='" + strcode + "']/../../..//span[@title='cc']"));
			if (webeCC.isDisplayed())
			{
				Log4J.logp.info("CC code is added");
				Assert.assertTrue(true, "CC code is added");
			}
			else
			{
				Log4J.logp.error("CC code is not added");
				Assert.assertTrue(false, "CC code is not added");
			}

			Log4J.logp.info("**************  Ending - verifyCCandMCC  **************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyCCandMCC  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyCCandMCC is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for verify POA value functioanlity
	 * 
	 * Assume all diagnosis code is there
	 * 
	 * We get data from "td_searchcase"
	 * 
	 * @author skhalasi
	 * @since 27/11/2014
	 */

	@Test(description = "ezCAC_MVP_Reg-2837:Verify that POA value should not be displayed after modify any diagnosis code", priority = 21)
	public static void verifyPOAvalueAfterModify()
	{
		boolean bstatus = false;

		try
		{
			Log4J.logp.info("**************  Started - verifyPOAvalueAfterModify  **************");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Log4J.logp.info("====== In verifyPOAvalueAfterModify - for Inpatient case  ======");
			//open case
			Common_Lib.openCase("sk001");

			Thread.sleep(1000);
			bstatus = WorkingScreen_Lib.select_POA_Value("admit", "Y");
			Common_Lib.verifyFlag(bstatus, "Select POA value 'Y' for Admitting Diagnosis ICD9");

			WorkingScreen_Lib.modify_oneCode_with_Second_withText("", codingPnl_webe.lbl_firstAdmitCodeNum.getText(), "790.01");
			Thread.sleep(1000);
			codingPnl_webe.lbl_firstAdmitCodeNum.click();
			Thread.sleep(1000);
			if (codingPnl_webe.lnk_YPOA_firstAdmit.getAttribute("class").equals("poa-label"))
			{
				Log4J.logp.error("POA value 'Y' is not selected for Admitting Diagnosis ICD9 after modify");
				Assert.assertTrue(false, "POA value 'Y' is not selected for Admitting Diagnosis ICD9 after modify");
			}
			else
			{
				Log4J.logp.info("POA value 'Y' is selected for Admitting Diagnosis ICD9 after modify");
				Assert.assertTrue(true, "POA value 'Y' is selected for Admitting Diagnosis ICD9 after modify");
			}
			codingPnl_webe.lbl_firstAdmitCodeNum.click();

			Thread.sleep(1000);
			bstatus = WorkingScreen_Lib.select_POA_Value("principal", "Y");
			Common_Lib.verifyFlag(bstatus, "Select POA value 'Y' for Principal Diagnosis");

			WorkingScreen_Lib.modify_oneCode_with_Second_withText("", codingPnl_webe.lbl_firstPrincipalCodeNum.getText(), "543.9");
			Thread.sleep(1000);
			codingPnl_webe.lbl_firstPrincipalCodeNum.click();
			Thread.sleep(1000);
			if (codingPnl_webe.lnk_YPOA_firstPrincipal.getAttribute("class").equals("poa-label"))
			{
				Log4J.logp.error("POA value 'Y' is not selected for Principal Diagnosis ICD9 after modify");
				Assert.assertTrue(false, "POA value 'Y' is not selected for Admitting Diagnosis ICD9 after modify");
			}
			else
			{
				Log4J.logp.info("POA value 'Y' is selected for Principal Diagnosis ICD9 after modify");
				Assert.assertTrue(true, "POA value 'Y' is selected for Principal Diagnosis ICD9 after modify");
			}
			codingPnl_webe.lbl_firstPrincipalCodeNum.click();

			Thread.sleep(1000);
			bstatus = WorkingScreen_Lib.select_POA_Value("secondary", "Y");
			Common_Lib.verifyFlag(bstatus, "Select POA value 'Y' for secondary Diagnosis");

			WorkingScreen_Lib.modify_oneCode_with_Second_withText("", codingPnl_webe.lbl_firstSecondaryCodeNum.getText(), "614.8");
			Thread.sleep(1000);
			codingPnl_webe.lbl_firstSecondaryCodeNum.click();
			Thread.sleep(1000);
			if (codingPnl_webe.lnk_YPOA_firstSecondary.getAttribute("class").equals("poa-label"))
			{
				Log4J.logp.error("POA value 'Y' is not selected for secondary Diagnosis ICD9 after modify");
				Assert.assertTrue(false, "POA value 'Y' is not selected for secondary Diagnosis ICD9 after modify");
			}
			else
			{
				Log4J.logp.info("POA value 'Y' is selected for secondary Diagnosis ICD9 after modify");
				Assert.assertTrue(true, "POA value 'Y' is selected for secondary Diagnosis ICD9 after modify");
			}
			codingPnl_webe.lbl_firstSecondaryCodeNum.click();
			Thread.sleep(2000);
			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			Thread.sleep(1000);
			bstatus = WorkingScreen_Lib.select_POA_Value("admit", "Y");
			Common_Lib.verifyFlag(bstatus, "Select POA value 'Y' for Admitting Diagnosis");

			WorkingScreen_Lib.modify_oneCode_with_Second_withText("", codingPnl_webe.lbl_firstAdmitCodeNum.getText(), "R13.19");
			Thread.sleep(1000);
			codingPnl_webe.lbl_firstAdmitCodeNum.click();
			Thread.sleep(1000);
			if (codingPnl_webe.lnk_YPOA_firstAdmit.getAttribute("class").equals("poa-label"))
			{
				Log4J.logp.error("POA value 'Y' is not selected for Admitting Diagnosis ICD10 after modify");
				Assert.assertTrue(false, "POA value 'Y' is not selected for Admitting Diagnosis ICD10 after modify");
			}
			else
			{
				Log4J.logp.info("POA value 'Y' is selected for Admitting Diagnosis ICD10 after modify");
				Assert.assertTrue(true, "POA value 'Y' is selected for Admitting Diagnosis ICD10 after modify");
			}
			codingPnl_webe.lbl_firstAdmitCodeNum.click();

			Thread.sleep(1000);
			bstatus = WorkingScreen_Lib.select_POA_Value("principal", "Y");
			Common_Lib.verifyFlag(bstatus, "Select POA value 'Y' for Principal Diagnosis");

			WorkingScreen_Lib.modify_oneCode_with_Second_withText("", codingPnl_webe.lbl_firstPrincipalCodeNum.getText(), "K38.0");
			Thread.sleep(1000);
			codingPnl_webe.lbl_firstPrincipalCodeNum.click();
			Thread.sleep(1000);
			if (codingPnl_webe.lnk_YPOA_firstPrincipal.getAttribute("class").equals("poa-label"))
			{
				Log4J.logp.error("POA value 'Y' is not selected for Principal Diagnosis after modify");
				Assert.assertTrue(false, "POA value 'Y' is not selected for Principal Diagnosis ICD9 after modify");
			}
			else
			{
				Log4J.logp.info("POA value 'Y' is selected for Principal Diagnosis ICD10 after modify");
				Assert.assertTrue(true, "POA value 'Y' is selected for Principal Diagnosis ICD10 after modify");
			}
			codingPnl_webe.lbl_firstPrincipalCodeNum.click();

			Thread.sleep(1000);
			bstatus = WorkingScreen_Lib.select_POA_Value("secondary", "Y");
			Common_Lib.verifyFlag(bstatus, "Select POA value 'Y' for secondary Diagnosis");

			WorkingScreen_Lib.modify_oneCode_with_Second_withText("", codingPnl_webe.lbl_firstSecondaryCodeNum.getText(), "A66.4");
			Thread.sleep(1000);
			codingPnl_webe.lbl_firstSecondaryCodeNum.click();
			Thread.sleep(1000);
			if (codingPnl_webe.lnk_YPOA_firstSecondary.getAttribute("class").equals("poa-label"))
			{
				Log4J.logp.error("POA value 'Y' is not selected for secondary Diagnosis ICD10 after modify");
				Assert.assertTrue(false, "POA value 'Y' is not selected for secondary Diagnosis ICD10 after modify");
			}
			else
			{
				Log4J.logp.info("POA value 'Y' is selected for secondary Diagnosis ICD10 after modify");
				Assert.assertTrue(true, "POA value 'Y' is selected for secondary Diagnosis ICD10 after modify");
			}

			codingPnl_webe.lbl_firstSecondaryCodeNum.click();

			Thread.sleep(1000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(1000);

			Log4J.logp.info("====== In verifyPOAvalueAfterModify - for Outpatient case  ======");
			//open case
			Common_Lib.openCase("sk006");

			Thread.sleep(1000);
			bstatus = WorkingScreen_Lib.select_POA_Value("admit", "Y");
			Common_Lib.verifyFlag(bstatus, "Select POA value 'Y' for Admitting Diagnosis ICD9");

			WorkingScreen_Lib.modify_oneCode_with_Second_withText("", codingPnl_webe.lbl_firstAdmitCodeNum.getText(), "790.01");
			Thread.sleep(1000);
			codingPnl_webe.lbl_firstAdmitCodeNum.click();
			Thread.sleep(1000);
			if (codingPnl_webe.lnk_YPOA_firstAdmit.getAttribute("class").equals("poa-label"))
			{
				Log4J.logp.error("POA value 'Y' is not selected for Admitting Diagnosis ICD9 after modify");
				Assert.assertTrue(false, "POA value 'Y' is not selected for Admitting Diagnosis ICD9 after modify");
			}
			else
			{
				Log4J.logp.info("POA value 'Y' is selected for Admitting Diagnosis ICD9 after modify");
				Assert.assertTrue(true, "POA value 'Y' is selected for Admitting Diagnosis ICD9 after modify");
			}
			codingPnl_webe.lbl_firstAdmitCodeNum.click();

			Thread.sleep(1000);
			bstatus = WorkingScreen_Lib.select_POA_Value("principal", "Y");
			Common_Lib.verifyFlag(bstatus, "Select POA value 'Y' for Principal Diagnosis");

			WorkingScreen_Lib.modify_oneCode_with_Second_withText("", codingPnl_webe.lbl_firstPrincipalCodeNum.getText(), "543.9");
			Thread.sleep(1000);
			codingPnl_webe.lbl_firstPrincipalCodeNum.click();
			Thread.sleep(1000);
			if (codingPnl_webe.lnk_YPOA_firstPrincipal.getAttribute("class").equals("poa-label"))
			{
				Log4J.logp.error("POA value 'Y' is not selected for Principal Diagnosis ICD9 after modify");
				Assert.assertTrue(false, "POA value 'Y' is not selected for Admitting Diagnosis ICD9 after modify");
			}
			else
			{
				Log4J.logp.info("POA value 'Y' is selected for Principal Diagnosis ICD9 after modify");
				Assert.assertTrue(true, "POA value 'Y' is selected for Principal Diagnosis ICD9 after modify");
			}
			codingPnl_webe.lbl_firstPrincipalCodeNum.click();

			Thread.sleep(1000);
			bstatus = WorkingScreen_Lib.select_POA_Value("secondary", "Y");
			Common_Lib.verifyFlag(bstatus, "Select POA value 'Y' for secondary Diagnosis");

			WorkingScreen_Lib.modify_oneCode_with_Second_withText("", codingPnl_webe.lbl_firstSecondaryCodeNum.getText(), "614.8");
			Thread.sleep(1000);
			codingPnl_webe.lbl_firstSecondaryCodeNum.click();
			Thread.sleep(1000);
			if (codingPnl_webe.lnk_YPOA_firstSecondary.getAttribute("class").equals("poa-label"))
			{
				Log4J.logp.error("POA value 'Y' is not selected for secondary Diagnosis ICD9 after modify");
				Assert.assertTrue(false, "POA value 'Y' is not selected for secondary Diagnosis ICD9 after modify");
			}
			else
			{
				Log4J.logp.info("POA value 'Y' is selected for secondary Diagnosis ICD9 after modify");
				Assert.assertTrue(true, "POA value 'Y' is selected for secondary Diagnosis ICD9 after modify");
			}
			codingPnl_webe.lbl_firstSecondaryCodeNum.click();
			Thread.sleep(2000);

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			Thread.sleep(1000);
			bstatus = WorkingScreen_Lib.select_POA_Value("admit", "Y");
			Common_Lib.verifyFlag(bstatus, "Select POA value 'Y' for Admitting Diagnosis");

			WorkingScreen_Lib.modify_oneCode_with_Second_withText("", codingPnl_webe.lbl_firstAdmitCodeNum.getText(), "R13.19");
			Thread.sleep(1000);
			codingPnl_webe.lbl_firstAdmitCodeNum.click();
			Thread.sleep(1000);
			if (codingPnl_webe.lnk_YPOA_firstAdmit.getAttribute("class").equals("poa-label"))
			{
				Log4J.logp.error("POA value 'Y' is not selected for Admitting Diagnosis ICD10 after modify");
				Assert.assertTrue(false, "POA value 'Y' is not selected for Admitting Diagnosis ICD10 after modify");
			}
			else
			{
				Log4J.logp.info("POA value 'Y' is selected for Admitting Diagnosis ICD10 after modify");
				Assert.assertTrue(true, "POA value 'Y' is selected for Admitting Diagnosis ICD10 after modify");
			}
			codingPnl_webe.lbl_firstAdmitCodeNum.click();

			Thread.sleep(1000);
			bstatus = WorkingScreen_Lib.select_POA_Value("principal", "Y");
			Common_Lib.verifyFlag(bstatus, "Select POA value 'Y' for Principal Diagnosis");

			WorkingScreen_Lib.modify_oneCode_with_Second_withText("", codingPnl_webe.lbl_firstPrincipalCodeNum.getText(), "K38.0");
			Thread.sleep(1000);
			codingPnl_webe.lbl_firstPrincipalCodeNum.click();
			Thread.sleep(1000);
			if (codingPnl_webe.lnk_YPOA_firstPrincipal.getAttribute("class").equals("poa-label"))
			{
				Log4J.logp.error("POA value 'Y' is not selected for Principal Diagnosis after modify");
				Assert.assertTrue(false, "POA value 'Y' is not selected for Principal Diagnosis ICD9 after modify");
			}
			else
			{
				Log4J.logp.info("POA value 'Y' is selected for Principal Diagnosis ICD10 after modify");
				Assert.assertTrue(true, "POA value 'Y' is selected for Principal Diagnosis ICD10 after modify");
			}
			codingPnl_webe.lbl_firstPrincipalCodeNum.click();

			Thread.sleep(1000);
			bstatus = WorkingScreen_Lib.select_POA_Value("secondary", "Y");
			Common_Lib.verifyFlag(bstatus, "Select POA value 'Y' for secondary Diagnosis");

			WorkingScreen_Lib.modify_oneCode_with_Second_withText("", codingPnl_webe.lbl_firstSecondaryCodeNum.getText(), "A66.4");
			Thread.sleep(1000);
			codingPnl_webe.lbl_firstSecondaryCodeNum.click();
			Thread.sleep(1000);
			if (codingPnl_webe.lnk_YPOA_firstSecondary.getAttribute("class").equals("poa-label"))
			{
				Log4J.logp.error("POA value 'Y' is not selected for secondary Diagnosis ICD10 after modify");
				Assert.assertTrue(false, "POA value 'Y' is not selected for secondary Diagnosis ICD10 after modify");
			}
			else
			{
				Log4J.logp.info("POA value 'Y' is selected for secondary Diagnosis ICD10 after modify");
				Assert.assertTrue(true, "POA value 'Y' is selected for secondary Diagnosis ICD10 after modify");
			}

			codingPnl_webe.lbl_firstSecondaryCodeNum.click();
			Log4J.logp.info("**************  Ending - verifyPOAvalueAfterModify  **************");

		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyPOAvalueAfterModify  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyPOAvalueAfterModify is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for submit case with warning (not use "td_submit_case")
	 * 
	 * We get data from "td_searchcase"
	 * 
	 * @author skhalasi
	 * @since 01/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-581:Verify that User can submit the case with unresolved warnings", priority = 22)
	public static void verifySubmitCaseWithWarning()
	{
		String strNoOfResult = null;
		int iCount = 0;
		try
		{
			Log4J.logp.info("**************  Started - verifySubmitCaseWithWarning  **************");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Log4J.logp.info("====== In verifySubmitCaseWithWarning - for Inpatient case  ======");
			//open case
			Common_Lib.openCase("sk035");
			Thread.sleep(1000);

			codingPnl_webe.lnk_Accept_Principal.click();
			Thread.sleep(1000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(1000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(1000);
			groupingpnl_webe.chk_routeForReview.click();
			Thread.sleep(1000);
			groupingpnl_webe.chk_takeMeQueue.click();
			Thread.sleep(1000);
			groupingpnl_webe.btn_submitAfterDone.click();
			Thread.sleep(1000);

			SearchCriteria_Lib.search_Case("sk106");

			strNoOfResult = landingp_webe.no_caseOnTable.getText();
			if (strNoOfResult.contains("1"))
			{
				Log4J.logp.info("Case completed successfully with warning");
				Assert.assertTrue(true, "Case completed successfully with warning");
			}
			else
			{
				Log4J.logp.error("Case can not completed successfully with warning");
				Assert.assertTrue(false, "Case can not completed successfully with warning");
			}
			Common_Lib.scrollUp_without_element();
			Thread.sleep(1000);
			searchCriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(1000);
			searchCriteria_webe.lnk_ClearSearch.click();
			Thread.sleep(1000);
			Log4J.logp.info("====== In verifySubmitCaseWithWarning - for Outpatient case  ======");
			Common_Lib.openCase("sk002");
			Thread.sleep(1000);
			while (!driver.findElements(By.id("code-accepted-disabled")).isEmpty())
			{
				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, driver.findElements(By.id("code-accepted-disabled")).get(iCount), 30);
				Thread.sleep(1000);
				driver.findElements(By.id("code-accepted-disabled")).get(iCount).click();
				Thread.sleep(1000);
			}

			Thread.sleep(1000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(1000);
			groupingpnl_webe.btn_Done.click();
			Thread.sleep(1000);
			groupingpnl_webe.chk_routeForReview.click();
			Thread.sleep(1000);
			groupingpnl_webe.chk_takeMeQueue.click();
			Thread.sleep(1000);
			groupingpnl_webe.btn_submitAfterDone.click();
			Thread.sleep(1000);

			SearchCriteria_Lib.search_Case("sk107");

			strNoOfResult = landingp_webe.no_caseOnTable.getText();
			if (strNoOfResult.contains("1"))
			{
				Log4J.logp.info("Case completed successfully with warning");
				Assert.assertTrue(true, "Case completed successfully with warning");
			}
			else
			{
				Log4J.logp.error("Case can not completed successfully with warning");
				Assert.assertTrue(false, "Case can not completed successfully with warning");
			}
			Common_Lib.scrollUp_without_element();
			Thread.sleep(1000);
			searchCriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(1000);
			searchCriteria_webe.lnk_ClearSearch.click();
			Thread.sleep(1000);
			Log4J.logp.info("**************  Ending - verifySubmitCaseWithWarning  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifySubmitCaseWithWarning  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifySubmitCaseWithWarning is failed");

		}
		finally
		{

		}

	}

	/**
	 * This method is for add same probable and associated code
	 * 
	 * We get data from "td_searchcase" , "td_cdsquery"
	 * 
	 * @author skhalasi
	 * @since 02/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2807:Verify that duplicate codes should not be allowed if code already exists in coding panel", priority = 23)
	public static void verifySameAssoAndProbCode()
	{
		try
		{
			Log4J.logp.info("**************  Started - verifySameAssoAndProbCode  **************");
			Thread.sleep(2000);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Log4J.logp.info("====== In verifySameAssoAndProbCode - for Inpatient case  ======");
			//open case
			Common_Lib.openCase("sk012");
			Thread.sleep(1000);

			//first suggested admitting evidence
			WorkingScreen_Lib.CDS_QueryCodingPnl(null, codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			WorkingScreen_Lib.CDS_QueryCodingPnl(null, codingPnl_webe.lnk_associatedAdmit_arrow, codingPnl_webe.lnk_associatedAdmit_query);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//query from first suggested evidence in primary diagnosis in ICD9
			WorkingScreen_Lib.CDS_QueryCodingPnl(null, codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//query from first suggested evidence in secondary diagnosis in ICD9
			WorkingScreen_Lib.CDS_QueryCodingPnl(null, codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//query from first suggested evidence in procedure  in ICD9
			WorkingScreen_Lib.CDS_QueryCodingPnl(null, codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			//Uncomment this

			//query from first suggested evidence in admitting diagnosis in ICD10
			WorkingScreen_Lib.CDS_QueryCodingPnl(null, codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//query from first suggested evidence in primary diagnosis in ICD10
			WorkingScreen_Lib.CDS_QueryCodingPnl(null, codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//query from first suggested evidence in secondary diagnosis in ICD10
			WorkingScreen_Lib.CDS_QueryCodingPnl(null, codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//query from first suggested evidence in procedure  in ICD10
			WorkingScreen_Lib.CDS_QueryCodingPnl(null, codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			Thread.sleep(2000);
			//click on "Later" button for choose Outpatient case
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(3000);
			//open out patient case
			Log4J.logp.info("====== In verifySameAssoAndProbCode - in Outpatient case ======");
			Common_Lib.openCase("sk006");

			Thread.sleep(2000);
			//ICD 9

			//Uncomment this
			//query from first suggested evidence in admitting diagnosis in ICD9
			WorkingScreen_Lib.CDS_QueryCodingPnl(null, codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//query from first suggested evidence in primary diagnosis in ICD9
			WorkingScreen_Lib.CDS_QueryCodingPnl(null, codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//query from first suggested evidence in secondary diagnosis in ICD9
			WorkingScreen_Lib.CDS_QueryCodingPnl(null, codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			//Uncomment this

			//query from first suggested evidence in admitting diagnosis in ICD10
			WorkingScreen_Lib.CDS_QueryCodingPnl(null, codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//query from first suggested evidence in primary diagnosis in ICD10
			WorkingScreen_Lib.CDS_QueryCodingPnl(null, codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//query from first suggested evidence in secondary diagnosis in ICD10
			WorkingScreen_Lib.CDS_QueryCodingPnl(null, codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//query from first suggested evidence in procedure (CPT)  in ICD9 and ICD10
			WorkingScreen_Lib.CDS_QueryCodingPnl(null, codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);

			Log4J.logp.info("**************  Ending - verifySameAssoAndProbCode  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifySameAssoAndProbCode  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifySameAssoAndProbCode is failed");

		}
		finally
		{
			groupingpnl_webe.btn_Later.click();
		}

	}

	/**
	 * This method is for make it principal for CDI user for secondary diagnosis code
	 * 
	 * We get data from "td_searchcase" , "td_cdsquery"
	 * 
	 * @author skhalasi
	 * @since 04/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2804:To verify make it principal feature if one associated and probable code already in PDx section", priority = 24)
	public static void verifyMakePrincipalInCDI()
	{
		String strOldCode = null;
		String strNewCode = null;
		try
		{
			Log4J.logp.info("**************  Started - verifyMakePrincipalInCDI  **************");
			Thread.sleep(2000);
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Log4J.logp.info("====== In verifyMakePrincipalInCDI - for Inpatient case  ======");
			//open case
			Common_Lib.openCase("sk001");
			Thread.sleep(1000);
			//query from first suggested evidence in primary diagnosis in ICD9
			WorkingScreen_Lib.CDS_QueryCodingPnl("sk002", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//query from first suggested evidence in secondary diagnosis in ICD9
			WorkingScreen_Lib.CDS_QueryCodingPnl("sk003", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(1000);

			strOldCode = codingPnl_webe.lbl_codeNumAssoSecondary.getText();
			Log4J.logp.info("Old code number of secondary associated code is :=" + strOldCode);

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_makeItPrincipalAssoSecondary, 30);
			Thread.sleep(1000);

			codingPnl_webe.lnk_makeItPrincipalAssoSecondary.click();
			Thread.sleep(1000);

			Common_Lib.scroll_Up_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lst_first_Principal_evi_arrow, 40);
			Thread.sleep(1000);

			strNewCode = codingPnl_webe.lbl_codeNumAssoPrincipal.getText();
			Log4J.logp.info("New code number of principal associated code is :=" + strNewCode);
			Thread.sleep(1000);

			Common_Lib.verifyFlag(strOldCode.equals(strNewCode), "Change Secondary diagnosis to Principal diagnosis for Inpatient ICD9");
			Thread.sleep(1000);

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			//query from first suggested evidence in primary diagnosis in ICD10
			WorkingScreen_Lib.CDS_QueryCodingPnl("sk006", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//query from first suggested evidence in secondary diagnosis in ICD10
			WorkingScreen_Lib.CDS_QueryCodingPnl("sk007", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(1000);

			strOldCode = codingPnl_webe.lbl_codeNumAssoSecondary.getText();
			Log4J.logp.info("Old code number of secondary associated code is :=" + strOldCode);

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_makeItPrincipalAssoSecondary, 30);
			Thread.sleep(1000);

			codingPnl_webe.lnk_makeItPrincipalAssoSecondary.click();
			Thread.sleep(1000);

			Common_Lib.scroll_Up_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lst_first_Principal_evi_arrow, 40);
			Thread.sleep(1000);

			strNewCode = codingPnl_webe.lbl_codeNumAssoPrincipal.getText();
			Log4J.logp.info("New code number of principal associated code is :=" + strNewCode);
			Thread.sleep(1000);

			Common_Lib.verifyFlag(strOldCode.equals(strNewCode), "Change Secondary diagnosis to Principal diagnosis for Inpatient ICD10");
			Thread.sleep(1000);

			groupingpnl_webe.btn_Later.click();
			Thread.sleep(2000);

			Log4J.logp.info("====== In verifyMakePrincipalInCDI - for Outpatient case  ======");
			//open case
			Common_Lib.openCase("sk006");
			Thread.sleep(5000);

			//query from first suggested evidence in primary diagnosis in ICD9
			WorkingScreen_Lib.CDS_QueryCodingPnl("sk010", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//query from first suggested evidence in secondary diagnosis in ICD9
			WorkingScreen_Lib.CDS_QueryCodingPnl("sk011", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(1000);

			strOldCode = codingPnl_webe.lbl_codeNumAssoSecondary.getText();
			Log4J.logp.info("Old code number of secondary associated code is :=" + strOldCode);

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_makeItPrincipalAssoSecondary, 30);
			Thread.sleep(1000);

			codingPnl_webe.lnk_makeItPrincipalAssoSecondary.click();
			Thread.sleep(1000);

			Common_Lib.scroll_Up_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lst_first_Principal_evi_arrow, 40);
			Thread.sleep(1000);

			strNewCode = codingPnl_webe.lbl_codeNumAssoPrincipal.getText();
			Log4J.logp.info("New code number of principal associated code is :=" + strNewCode);
			Thread.sleep(1000);

			Common_Lib.verifyFlag(strOldCode.equals(strNewCode), "Change Secondary diagnosis to Principal diagnosis for Outpatient ICD9");
			Thread.sleep(1000);

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			//query from first suggested evidence in primary diagnosis in ICD10
			WorkingScreen_Lib.CDS_QueryCodingPnl("sk013", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);

			//query from first suggested evidence in secondary diagnosis in ICD10
			WorkingScreen_Lib.CDS_QueryCodingPnl("sk014", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(1000);

			strOldCode = codingPnl_webe.lbl_codeNumAssoSecondary.getText();
			Log4J.logp.info("Old code number of secondary associated code is :=" + strOldCode);

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_makeItPrincipalAssoSecondary, 30);
			Thread.sleep(1000);

			codingPnl_webe.lnk_makeItPrincipalAssoSecondary.click();
			Thread.sleep(1000);

			Common_Lib.scroll_Up_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lst_first_Principal_evi_arrow, 40);
			Thread.sleep(1000);

			strNewCode = codingPnl_webe.lbl_codeNumAssoPrincipal.getText();
			Log4J.logp.info("New code number of principal associated code is :=" + strNewCode);
			Thread.sleep(1000);

			Common_Lib.verifyFlag(strOldCode.equals(strNewCode), "Change Secondary diagnosis to Principal diagnosis for Outpatient ICD9");
			Thread.sleep(1000);

			Log4J.logp.info("**************  Ending - verifyMakePrincipalInCDI  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyMakePrincipalInCDI  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyMakePrincipalInCDI is failed");

		}
		finally
		{
			groupingpnl_webe.btn_Later.click();
		}

	}

	/**
	 * This method is for multiple CDS query with reject and modify probable code
	 * 
	 * We get data from "td_searchcase" , "td_cdsquery"
	 * 
	 * @author skhalasi
	 * @since 09/12/2014
	 * 
	 * */
	@Test(description = "ezCAC_MVP_Reg-2789:Verify that if 1st probable code is modified + rejected and CDS adds new probable", priority = 25)
	public static void CDSQueryWithRejectModifyCodes()
	{
		String strReplyStatus = null;
		String strCode = null;
		WebElement webeModify;
		WebElement webeReject;

		try
		{
			Log4J.logp.info("**************  Started - CDSQueryWithRejectModifyCodes  **************");
			Thread.sleep(3000);
			JDBCMySql query = new JDBCMySql();
			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Common_Lib.openCase("sk001");
			Thread.sleep(5000);

			//ICD 9
			Log4J.logp.info("====== In CDSQueryWithRejectModifyCodes - in Inpatient case for ICD9 ======");

			//first suggested admitting evidence
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk001", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in admitting diagnosis for ICD9");

				//modify codes
				WorkingScreen_Lib.modify_CDS_Code("sk001");
				Thread.sleep(500);

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectAdmit, 40);
				Thread.sleep(500);
				codingPnl_webe.lnk_associatedRejectAdmit.click();
				Thread.sleep(2000);

				//add another probable code
				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk018", codingPnl_webe.lnk_associatedRejectAdmit_arrow, codingPnl_webe.lnk_associatedRejectAdmit_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in admitting diagnosis for ICD9");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in admitting diagnosis for ICD9");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in admitting diagnosis for ICD9");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in admitting diagnosis for ICD9");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableAdmit.size() == 1, "Accept Only one admitting diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_modifyassopro_code", "sk001", "code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));
				webeModify = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-modified')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old admitting diagnosis probable code in modify status");
				Common_Lib.verifyFlag(webeModify.getAttribute("id").contains("enabled"), "Old admitting diagnosis probable code in modify status");

				//WebElement webe1 = driver.findElement(By.xpath("//div[text()='560.9']/../..//span[contains(@id,'code-rejected')]"));
				//WebElement webe2 = driver.findElement(By.xpath("//div[text()='560.9']/../..//span[contains(@id,'code-modified')]"));
				//WebElement webe3 = driver.findElement(By.xpath("//div[text()='444.01']/../..//span[contains(@id,'code-accepted')]"));

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in admitting diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in primary diagnosis in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk002", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in prinipal diagnosis for ICD9");

				WorkingScreen_Lib.modify_CDS_Code("sk002");
				Thread.sleep(500);

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectPrincipal, 40);
				Thread.sleep(500);
				Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 10);
				codingPnl_webe.lnk_associatedRejectPrincipal.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk019", codingPnl_webe.lnk_associatedRejectPrincipal_arrow, codingPnl_webe.lnk_associatedRejectPrincipal_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in principal diagnosis for ICD9");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in principal diagnosis for ICD9");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in principal diagnosis for ICD9");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in principal diagnosis for ICD9");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbablePrincipal.size() == 1, "Accept Only one principal diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_modifyassopro_code", "sk002", "code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));
				webeModify = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-modified')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old principal diagnosis probable code in modify status");
				Common_Lib.verifyFlag(webeModify.getAttribute("id").contains("enabled"), "Old principal diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in prinipal diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{

				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in secondary diagnosis in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk003", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in secondary diagnosis for ICD9");

				WorkingScreen_Lib.modify_CDS_Code("sk003");
				Thread.sleep(500);

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectSecondary, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectSecondary.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk020", codingPnl_webe.lnk_associatedRejectSecondary_arrow, codingPnl_webe.lnk_associatedRejectSecondary_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in secondary diagnosis for ICD9");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in secondary diagnosis for ICD9");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in secondary diagnosis for ICD9");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in secondary diagnosis for ICD9");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableSecondary.size() == 1, "Accept Only one secondary diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_modifyassopro_code", "sk003", "code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));
				webeModify = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-modified')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old secondary diagnosis probable code in modify status");
				Common_Lib.verifyFlag(webeModify.getAttribute("id").contains("enabled"), "Old secondary diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in secondary diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in procedure  in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk004", codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in procedure diagnosis for ICD9");

				WorkingScreen_Lib.modify_CDS_Code("sk004");
				Thread.sleep(500);

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectProcedure, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectProcedure.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk021", codingPnl_webe.lnk_associatedRejectProcedure_arrow, codingPnl_webe.lnk_associatedRejectProcedure_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in procedure for ICD9");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in procedure for ICD9");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in procedure for ICD9");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in procedure for ICD9");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableProcedure.size() == 1, "Accept Only one procedure probable code ");

				strCode = query.getValuebyColumnName("td_modifyassopro_code", "sk004", "code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));
				webeModify = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-modified')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old procedure probable code in modify status");
				Common_Lib.verifyFlag(webeModify.getAttribute("id").contains("enabled"), "Old procedure probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in procedure diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//ICD 10
			Log4J.logp.info("====== In CDSQueryWithRejectModifyCodes - in Inpatient case for ICD10 ======");

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(1000);

			//Uncomment this

			//query from first suggested evidence in admitting diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk005", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in admitting diagnosis for ICD10");

				WorkingScreen_Lib.modify_CDS_Code("sk005");
				Thread.sleep(500);

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectAdmit, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectAdmit.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk022", codingPnl_webe.lnk_associatedRejectAdmit_arrow, codingPnl_webe.lnk_associatedRejectAdmit_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in admitting diagnosis for ICD10");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in admitting diagnosis for ICD10");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in admitting diagnosis for ICD10");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in admitting diagnosis for ICD10");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableAdmit.size() == 1, "Accept Only one admitting diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_modifyassopro_code", "sk005", "code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));
				webeModify = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-modified')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old admitting diagnosis probable code in modify status");
				Common_Lib.verifyFlag(webeModify.getAttribute("id").contains("enabled"), "Old admitting diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in admitting diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in primary diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk006", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in principal diagnosis for ICD10");
				WorkingScreen_Lib.modify_CDS_Code("sk006");
				Thread.sleep(500);

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectPrincipal, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectPrincipal.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk023", codingPnl_webe.lnk_associatedRejectPrincipal_arrow, codingPnl_webe.lnk_associatedRejectPrincipal_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in principal diagnosis for ICD10");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in principal diagnosis for ICD10");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in principal diagnosis for ICD10");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in principal diagnosis for ICD10");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbablePrincipal.size() == 1, "Accept Only one principal diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_modifyassopro_code", "sk006", "code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));
				webeModify = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-modified')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old principal diagnosis probable code in modify status");
				Common_Lib.verifyFlag(webeModify.getAttribute("id").contains("enabled"), "Old principal diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in principal diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in secondary diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk007", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in secondary diagnosis for ICD10");

				WorkingScreen_Lib.modify_CDS_Code("sk007");
				Thread.sleep(500);

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectSecondary, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectSecondary.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk024", codingPnl_webe.lnk_associatedRejectSecondary_arrow, codingPnl_webe.lnk_associatedRejectSecondary_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in secondary diagnosis for ICD10");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in secondary diagnosis for ICD10");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in secondary diagnosis for ICD10");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in secondary diagnosis for ICD10");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableSecondary.size() == 1, "Accept Only one secondary diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_modifyassopro_code", "sk007", "code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));
				webeModify = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-modified')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old secondary diagnosis probable code in modify status");
				Common_Lib.verifyFlag(webeModify.getAttribute("id").contains("enabled"), "Old secondary diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in secondary diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in procedure  in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk008", codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in procedure  for ICD10");

				WorkingScreen_Lib.modify_CDS_Code("sk008");
				Thread.sleep(500);

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectProcedure, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectProcedure.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk025", codingPnl_webe.lnk_associatedRejectProcedure_arrow, codingPnl_webe.lnk_associatedRejectProcedure_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in procedure for ICD10");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in procedure for ICD10");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in procedure for ICD10");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in procedure for ICD10");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableProcedure.size() == 1, "Accept Only one procedure probable code ");

				strCode = query.getValuebyColumnName("td_modifyassopro_code", "sk008", "code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));
				webeModify = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-modified')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old procedure probable code in modify status");
				Common_Lib.verifyFlag(webeModify.getAttribute("id").contains("enabled"), "Old procedure probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in procedure  for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			Thread.sleep(2000);
			//click on "Later" button for choose Outpatient case
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(3000);
			//open out patient case
			Common_Lib.openCase("sk014");
			Log4J.logp.info("====== In CDSQueryWithRejectModifyCodes - in Outpatient case for ICD9 ======");
			Thread.sleep(2000);
			//ICD 9

			//Uncomment this
			//query from first suggested evidence in admitting diagnosis in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk009", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in admitting diagnosis for ICD9");

				WorkingScreen_Lib.modify_CDS_Code("sk001");
				Thread.sleep(500);

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectAdmit, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectAdmit.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk018", codingPnl_webe.lnk_associatedRejectAdmit_arrow, codingPnl_webe.lnk_associatedRejectAdmit_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in admitting diagnosis for ICD9");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in admitting diagnosis for ICD9");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in admitting diagnosis for ICD9");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in admitting diagnosis for ICD9");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableAdmit.size() == 1, "Accept Only one admitting diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_modifyassopro_code", "sk001", "code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));
				webeModify = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-modified')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old admitting diagnosis probable code in modify status");
				Common_Lib.verifyFlag(webeModify.getAttribute("id").contains("enabled"), "Old admitting diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in admitting diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in primary diagnosis in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk010", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in principal diagnosis for ICD9");

				WorkingScreen_Lib.modify_CDS_Code("sk002");
				Thread.sleep(500);

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectPrincipal, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectPrincipal.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk019", codingPnl_webe.lnk_associatedRejectPrincipal_arrow, codingPnl_webe.lnk_associatedRejectPrincipal_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in principal diagnosis for ICD9");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in principal diagnosis for ICD9");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in principal diagnosis for ICD9");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in principal diagnosis for ICD9");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbablePrincipal.size() == 1, "Accept Only one principal diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_modifyassopro_code", "sk002", "code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));
				webeModify = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-modified')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old principal diagnosis probable code in modify status");
				Common_Lib.verifyFlag(webeModify.getAttribute("id").contains("enabled"), "Old principal diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in principal diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in secondary diagnosis in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk011", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in secondary diagnosis for ICD9");

				WorkingScreen_Lib.modify_CDS_Code("sk003");
				Thread.sleep(500);

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectSecondary, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectSecondary.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk020", codingPnl_webe.lnk_associatedRejectSecondary_arrow, codingPnl_webe.lnk_associatedRejectSecondary_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in secondary diagnosis for ICD9");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in secondary diagnosis for ICD9");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in secondary diagnosis for ICD9");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in secondary diagnosis for ICD9");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableSecondary.size() == 1, "Accept Only one secondary diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_modifyassopro_code", "sk003", "code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));
				webeModify = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-modified')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old secondary diagnosis probable code in modify status");
				Common_Lib.verifyFlag(webeModify.getAttribute("id").contains("enabled"), "Old secondary diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in secondary diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//ICD 10
			Log4J.logp.info("====== In CDSQueryWithRejectModifyCodes - in Outpatient case for ICD10 ======");

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			//Uncomment this

			//query from first suggested evidence in admitting diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk012", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in admitting diagnosis for ICD10");

				WorkingScreen_Lib.modify_CDS_Code("sk005");
				Thread.sleep(500);

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectAdmit, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectAdmit.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk022", codingPnl_webe.lnk_associatedRejectAdmit_arrow, codingPnl_webe.lnk_associatedRejectAdmit_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in admitting diagnosis for ICD10");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in admitting diagnosis for ICD10");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in admitting diagnosis for ICD10");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in admitting diagnosis for ICD10");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableAdmit.size() == 1, "Accept Only one admitting diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_modifyassopro_code", "sk005", "code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));
				webeModify = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-modified')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old admitting diagnosis probable code in modify status");
				Common_Lib.verifyFlag(webeModify.getAttribute("id").contains("enabled"), "Old admitting diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in admitting diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in primary diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk013", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in principal diagnosis for ICD10");

				WorkingScreen_Lib.modify_CDS_Code("sk006");
				Thread.sleep(500);

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectPrincipal, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectPrincipal.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk023", codingPnl_webe.lnk_associatedRejectPrincipal_arrow, codingPnl_webe.lnk_associatedRejectPrincipal_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in principal diagnosis for ICD10");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in principal diagnosis for ICD10");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in principal diagnosis for ICD10");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in principal diagnosis for ICD10");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbablePrincipal.size() == 1, "Accept Only one principal diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_modifyassopro_code", "sk006", "code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));
				webeModify = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-modified')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old principal diagnosis probable code in modify status");
				Common_Lib.verifyFlag(webeModify.getAttribute("id").contains("enabled"), "Old principal diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in principal diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in secondary diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk014", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in secondary diagnosis for ICD10");

				WorkingScreen_Lib.modify_CDS_Code("sk007");
				Thread.sleep(500);

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectSecondary, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectSecondary.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk024", codingPnl_webe.lnk_associatedRejectSecondary_arrow, codingPnl_webe.lnk_associatedRejectSecondary_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in secondary diagnosis for ICD10");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in secondary diagnosis for ICD10");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in secondary diagnosis for ICD10");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in secondary diagnosis for ICD10");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableSecondary.size() == 1, "Accept Only one secondary diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_modifyassopro_code", "sk007", "code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));
				webeModify = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-modified')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old secondary diagnosis probable code in modify status");
				Common_Lib.verifyFlag(webeModify.getAttribute("id").contains("enabled"), "Old secondary diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in secondary diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in procedure (CPT)  in ICD9 and ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk015", codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in procedure(CPT)  for ICD9 and ICD10");

				WorkingScreen_Lib.modify_CDS_Code("sk009");
				Thread.sleep(500);

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectCPT, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectCPT.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk026", codingPnl_webe.lnk_associatedRejectProcedure_arrow, codingPnl_webe.lnk_associatedRejectProcedure_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in procedure(CPT)  for ICD9 and ICD10");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in procedure(CPT)  for ICD9 and ICD10");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in procedure(CPT)  for ICD9 and ICD10");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in procedure(CPT)  for ICD9 and ICD10");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableProcedure.size() == 1, "Accept Only one procedure probable code ");

				strCode = query.getValuebyColumnName("td_modifyassopro_code", "sk009", "code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));
				webeModify = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-modified')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old procedure(CPT) probable code in modify status");
				Common_Lib.verifyFlag(webeModify.getAttribute("id").contains("enabled"), "Old procedure(CPT) probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in procedure(CPT)  for ICD9 and ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			Thread.sleep(1000);

			Log4J.logp.info("**************  Ending - CDSQueryWithRejectModifyCodes  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - CDSQueryWithRejectModifyCodes  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "CDSQueryWithRejectModifyCodes is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for multiple CDS query with reject probable code
	 * 
	 * We get data from "td_searchcase" , "td_cdsquery"
	 * 
	 * @author skhalasi
	 * @since 09/12/2014
	 * 
	 * */
	@Test(description = "ezCAC_MVP_Reg-2787:Verify that if 1st probable code is rejected and CDS adds new probable code", priority = 26)
	public static void CDSQueryWithRejectCodes()
	{
		String strReplyStatus = null;
		String strCode = null;
		WebElement webeReject;
		try
		{
			Log4J.logp.info("**************  Started - CDSQueryWithRejectCodes  **************");
			Thread.sleep(3000);
			JDBCMySql query = new JDBCMySql();

			landingp_webe.lnk_CDI.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Common_Lib.openCase("sk001");
			Thread.sleep(5000);

			//ICD 9
			Log4J.logp.info("====== In CDSQueryWithRejectCodes - in Inpatient case for ICD9 ======");

			//first suggested admitting evidence
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk001", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in admitting diagnosis for ICD9");

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectAdmit, 40);
				Thread.sleep(500);
				codingPnl_webe.lnk_associatedRejectAdmit.click();
				Thread.sleep(2000);

				//add another probable code
				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk018", codingPnl_webe.lnk_associatedRejectAdmit_arrow, codingPnl_webe.lnk_associatedRejectAdmit_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in admitting diagnosis for ICD9");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in admitting diagnosis for ICD9");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in admitting diagnosis for ICD9");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in admitting diagnosis for ICD9");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableAdmit.size() == 1, "Accept Only one admitting diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_cdiquery", "sk001", "probable_code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old admitting diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in admitting diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in primary diagnosis in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk002", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in prinipal diagnosis for ICD9");

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectPrincipal, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectPrincipal.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk019", codingPnl_webe.lnk_associatedRejectPrincipal_arrow, codingPnl_webe.lnk_associatedRejectPrincipal_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);
				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in principal diagnosis for ICD9");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in principal diagnosis for ICD9");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in principal diagnosis for ICD9");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in principal diagnosis for ICD9");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbablePrincipal.size() == 1, "Accept Only one principal diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_cdiquery", "sk002", "probable_code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old principal diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in prinipal diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{

				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in secondary diagnosis in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk003", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in secondary diagnosis for ICD9");

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectSecondary, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectSecondary.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk020", codingPnl_webe.lnk_associatedRejectSecondary_arrow, codingPnl_webe.lnk_associatedRejectSecondary_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in secondary diagnosis for ICD9");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in secondary diagnosis for ICD9");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in secondary diagnosis for ICD9");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in secondary diagnosis for ICD9");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableSecondary.size() == 1, "Accept Only one secondary diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_cdiquery", "sk003", "probable_code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old secondary diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in secondary diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in procedure  in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk004", codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in procedure diagnosis for ICD9");

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectProcedure, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectProcedure.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk021", codingPnl_webe.lnk_associatedRejectProcedure_arrow, codingPnl_webe.lnk_associatedRejectProcedure_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in procedure for ICD9");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in procedure for ICD9");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in procedure for ICD9");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in procedure for ICD9");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableProcedure.size() == 1, "Accept Only one procedure probable code ");

				strCode = query.getValuebyColumnName("td_cdiquery", "sk004", "probable_code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old procedure probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in procedure diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//ICD 10
			Log4J.logp.info("====== In CDSQueryWithRejectCodes - in Inpatient case for ICD10 ======");

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(1000);

			//Uncomment this

			//query from first suggested evidence in admitting diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk005", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in admitting diagnosis for ICD10");

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectAdmit, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectAdmit.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk022", codingPnl_webe.lnk_associatedRejectAdmit_arrow, codingPnl_webe.lnk_associatedRejectAdmit_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in admitting diagnosis for ICD10");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in admitting diagnosis for ICD10");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in admitting diagnosis for ICD10");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in admitting diagnosis for ICD10");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableAdmit.size() == 1, "Accept Only one admitting diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_cdiquery", "sk005", "probable_code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old admitting diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in admitting diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in primary diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk006", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in principal diagnosis for ICD10");

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectPrincipal, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectPrincipal.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk023", codingPnl_webe.lnk_associatedRejectPrincipal_arrow, codingPnl_webe.lnk_associatedRejectPrincipal_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in principal diagnosis for ICD10");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in principal diagnosis for ICD10");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in principal diagnosis for ICD10");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in principal diagnosis for ICD10");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbablePrincipal.size() == 1, "Accept Only one principal diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_cdiquery", "sk006", "probable_code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old principal diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in principal diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in secondary diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk007", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in secondary diagnosis for ICD10");

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectSecondary, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectSecondary.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk024", codingPnl_webe.lnk_associatedRejectSecondary_arrow, codingPnl_webe.lnk_associatedRejectSecondary_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in secondary diagnosis for ICD10");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in secondary diagnosis for ICD10");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in secondary diagnosis for ICD10");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in secondary diagnosis for ICD10");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableSecondary.size() == 1, "Accept Only one secondary diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_cdiquery", "sk007", "probable_code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old secondary diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in secondary diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in procedure  in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk008", codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in procedure  for ICD10");

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectProcedure, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectProcedure.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk025", codingPnl_webe.lnk_associatedRejectProcedure_arrow, codingPnl_webe.lnk_associatedRejectProcedure_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in procedure for ICD10");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in procedure for ICD10");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in procedure for ICD10");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in procedure for ICD10");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableProcedure.size() == 1, "Accept Only one procedure probable code ");

				strCode = query.getValuebyColumnName("td_cdiquery", "sk008", "probable_code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old procedure probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in procedure  for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			Thread.sleep(2000);
			//click on "Later" button for choose Outpatient case
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(3000);
			//open out patient case
			Common_Lib.openCase("sk014");
			Log4J.logp.info("====== In CDSQueryWithRejectCodes - in Outpatient case for ICD9 ======");
			Thread.sleep(2000);
			//ICD 9

			//Uncomment this
			//query from first suggested evidence in admitting diagnosis in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk009", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in admitting diagnosis for ICD9");

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectAdmit, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectAdmit.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk018", codingPnl_webe.lnk_associatedRejectAdmit_arrow, codingPnl_webe.lnk_associatedRejectAdmit_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in admitting diagnosis for ICD9");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in admitting diagnosis for ICD9");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in admitting diagnosis for ICD9");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in admitting diagnosis for ICD9");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableAdmit.size() == 1, "Accept Only one admitting diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_cdiquery", "sk009", "probable_code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old admitting diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in admitting diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in primary diagnosis in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk010", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in principal diagnosis for ICD9");

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectPrincipal, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectPrincipal.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk019", codingPnl_webe.lnk_associatedRejectPrincipal_arrow, codingPnl_webe.lnk_associatedRejectPrincipal_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in principal diagnosis for ICD9");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in principal diagnosis for ICD9");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in principal diagnosis for ICD9");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in principal diagnosis for ICD9");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbablePrincipal.size() == 1, "Accept Only one principal diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_cdiquery", "sk010", "probable_code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old principal diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in principal diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in secondary diagnosis in ICD9
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk011", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in secondary diagnosis for ICD9");

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectSecondary, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectSecondary.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk020", codingPnl_webe.lnk_associatedRejectSecondary_arrow, codingPnl_webe.lnk_associatedRejectSecondary_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in secondary diagnosis for ICD9");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in secondary diagnosis for ICD9");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in secondary diagnosis for ICD9");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in secondary diagnosis for ICD9");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableSecondary.size() == 1, "Accept Only one secondary diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_cdiquery", "sk011", "probable_code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old secondary diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in secondary diagnosis for ICD9");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//ICD 10
			Log4J.logp.info("====== In CDSQueryWithRejectCodes - in Outpatient case for ICD10 ======");

			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			//Uncomment this

			//query from first suggested evidence in admitting diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk012", codingPnl_webe.lst_first_admit_evi_arrow, codingPnl_webe.lnk_query_first_admit_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in admitting diagnosis for ICD10");

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectAdmit, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectAdmit.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk022", codingPnl_webe.lnk_associatedRejectAdmit_arrow, codingPnl_webe.lnk_associatedRejectAdmit_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in admitting diagnosis for ICD10");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in admitting diagnosis for ICD10");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in admitting diagnosis for ICD10");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in admitting diagnosis for ICD10");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableAdmit.size() == 1, "Accept Only one admitting diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_cdiquery", "sk012", "probable_code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old admitting diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in admitting diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in primary diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk013", codingPnl_webe.lst_first_Principal_evi_arrow, codingPnl_webe.lnk_query_first_Principal_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in principal diagnosis for ICD10");

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectPrincipal, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectPrincipal.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk023", codingPnl_webe.lnk_associatedRejectPrincipal_arrow, codingPnl_webe.lnk_associatedRejectPrincipal_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in principal diagnosis for ICD10");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in principal diagnosis for ICD10");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in principal diagnosis for ICD10");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in principal diagnosis for ICD10");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbablePrincipal.size() == 1, "Accept Only one principal diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_cdiquery", "sk013", "probable_code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old principal diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in principal diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in secondary diagnosis in ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk014", codingPnl_webe.lst_first_secondary_evi_arrow, codingPnl_webe.lnk_query_first_secondary_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in secondary diagnosis for ICD10");

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectSecondary, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectSecondary.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk024", codingPnl_webe.lnk_associatedRejectSecondary_arrow, codingPnl_webe.lnk_associatedRejectSecondary_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in secondary diagnosis for ICD10");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in secondary diagnosis for ICD10");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in secondary diagnosis for ICD10");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in secondary diagnosis for ICD10");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableSecondary.size() == 1, "Accept Only one secondary diagnosis probable code ");

				strCode = query.getValuebyColumnName("td_cdiquery", "sk014", "probable_code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old secondary diagnosis probable code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in secondary diagnosis for ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			//query from first suggested evidence in procedure (CPT)  in ICD9 and ICD10
			strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk015", codingPnl_webe.lst_first_procedure_evi_arrow, codingPnl_webe.lnk_query_first_procedure_evi);

			issuepnl_webe.btn_Issues.click();
			Thread.sleep(500);
			if (strReplyStatus.equals("queryAdd"))
			{
				Assert.assertTrue(true, "Query to physician for suggested evidence in procedure(CPT)  for ICD9 and ICD10");

				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_associatedRejectCPT, 40);
				Thread.sleep(500);

				codingPnl_webe.lnk_associatedRejectCPT.click();
				Thread.sleep(2000);

				strReplyStatus = WorkingScreen_Lib.CDS_QueryCodingPnl("sk026", codingPnl_webe.lnk_associatedRejectProcedure_arrow, codingPnl_webe.lnk_associatedRejectProcedure_query);
				Thread.sleep(500);
				issuepnl_webe.btn_Issues.click();
				Thread.sleep(500);

				if (strReplyStatus.equals("queryAdd"))
				{
					Log4J.logp.info("Query is added after modify and reject probabale code in procedure(CPT)  for ICD9 and ICD10");
					Assert.assertTrue(true, "Query is added after modify and reject probabale code in procedure(CPT)  for ICD9 and ICD10");
				}
				else
				{
					Log4J.logp.error("Query is not added after modify and reject probabale code in procedure(CPT)  for ICD9 and ICD10");
					Assert.assertTrue(false, "Query is not added after modify and reject probabale code in procedure(CPT)  for ICD9 and ICD10");
				}

				Common_Lib.verifyFlag(codingPnl_webe.lst_acceptProbableProcedure.size() == 1, "Accept Only one procedure(CPT) probable code ");

				strCode = query.getValuebyColumnName("td_cdiquery", "sk015", "probable_code");

				webeReject = driver.findElement(By.xpath("//div[text()='" + strCode + "']/../..//span[contains(@id,'code-rejected')]"));

				Common_Lib.verifyFlag(webeReject.getAttribute("id").contains("enabled"), "Old procedure probable(CPT) code in modify status");

			}
			else if (strReplyStatus.equals("queryNotAdd"))
			{
				Assert.assertTrue(false, "Query to physician for suggested evidence in procedure(CPT)  for ICD9 and ICD10");
			}
			else if (strReplyStatus.equals("notFound"))
			{
				Log4J.logp.info("Evidence is not exists");
			}

			Thread.sleep(1000);
			Log4J.logp.info("**************  Ending - CDSQueryWithRejectCodes  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - CDSQueryWithRejectCodes  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "CDSQueryWithRejectCodes is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for verify Coding panel navigation
	 * 
	 * We get data from "td_searchcase"
	 * 
	 * @author skhalasi
	 * @since 20/10/2014
	 * 
	 * */
	@Test(description = "ezCAC_MVP_Reg-457:To verify navigation menu for inpatient and outpatient case", priority = 27)
	public static void verifyCodingPnlNavigation()
	{
		boolean bstatus = false;
		try
		{
			Log4J.logp.info("**************  Started - verifyCodingPnlNavigation  **************");
			Thread.sleep(2000);
			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.caseInprogerssToOnholdOrPending("sk008");
			Thread.sleep(1000);

			Log4J.logp.info("======  In verifyCodingPnlNavigation for Inpatient ======");

			Common_Lib.openCase("sk015");
			Thread.sleep(1000);
			//ICD9 links
			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lst_navigationMenu);
			Common_Lib.verifyFlag(bstatus, "Click on navigation menu for ICD9 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_diaNavi);
			Common_Lib.verifyFlag(bstatus, "Click on diagnosis dropdown for ICD9 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_admitNavi);
			Common_Lib.verifyFlag(bstatus, "Click on admitting diagnosis for ICD9 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_primaryNavi);
			Common_Lib.verifyFlag(bstatus, "Click on principal diagnosis for ICD9 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_secondaryNavi);
			Common_Lib.verifyFlag(bstatus, "Click on secondary diagnosis for ICD9 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_procedureNavi);
			Common_Lib.verifyFlag(bstatus, "Click on procedure dropdown for ICD9 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_procedureNavi_ICD9);
			Common_Lib.verifyFlag(bstatus, "Click on procedure diagnosis for ICD9 ");

			//for ICD10
			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lst_navigationMenu);
			Common_Lib.verifyFlag(bstatus, "Click on navigation menu for ICD10 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_diaNavi);
			Common_Lib.verifyFlag(bstatus, "Click on diagnosis dropdown for ICD10 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_admitNavi);
			Common_Lib.verifyFlag(bstatus, "Click on admitting diagnosis for ICD10 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_primaryNavi);
			Common_Lib.verifyFlag(bstatus, "Click on principal diagnosis for ICD10 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_secondaryNavi);
			Common_Lib.verifyFlag(bstatus, "Click on secondary diagnosis for ICD10 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_procedureNavi);
			Common_Lib.verifyFlag(bstatus, "Click on procedure dropdown for ICD10 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_procedureNavi_ICD10);
			Common_Lib.verifyFlag(bstatus, "Click on procedure diagnosis for ICD10 ");

			groupingpnl_webe.btn_Later.click();

			Log4J.logp.info("====== In verifyCodingPnlNavigation for Outpatient ======");
			Thread.sleep(2000);
			Common_Lib.openCase("sk011");
			Thread.sleep(2000);
			//ICD9 links
			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lst_navigationMenu);
			Common_Lib.verifyFlag(bstatus, "Click on navigation menu for ICD9 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_diaNavi);
			Common_Lib.verifyFlag(bstatus, "Click on diagnosis dropdown for ICD9 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_admitNavi);
			Common_Lib.verifyFlag(bstatus, "Click on admitting diagnosis for ICD9 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_primaryNavi);
			Common_Lib.verifyFlag(bstatus, "Click on principal diagnosis for ICD9 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_secondaryNavi);
			Common_Lib.verifyFlag(bstatus, "Click on secondary diagnosis for ICD9 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_procedureNavi);
			Common_Lib.verifyFlag(bstatus, "Click on procedure dropdown for ICD9 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_CPTNavi);
			Common_Lib.verifyFlag(bstatus, "Click on CPT for ICD9 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_EandMNavi);
			Common_Lib.verifyFlag(bstatus, "Click on E & M for ICD9 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_hcpcsNavi);
			Common_Lib.verifyFlag(bstatus, "Click on HCPCS for ICD9 ");

			//for ICD10
			//to move on ICD 10
			codingPnl_webe.lst_codeType.click();
			Thread.sleep(500);
			codingPnl_webe.lnk_ICD10_Menu.click();
			Thread.sleep(500);

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lst_navigationMenu);
			Common_Lib.verifyFlag(bstatus, "Click on navigation menu for ICD10 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_diaNavi);
			Common_Lib.verifyFlag(bstatus, "Click on diagnosis dropdown for ICD10 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_admitNavi);
			Common_Lib.verifyFlag(bstatus, "Click on admitting diagnosis for ICD10 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_primaryNavi);
			Common_Lib.verifyFlag(bstatus, "Click on principal diagnosis for ICD10 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_secondaryNavi);
			Common_Lib.verifyFlag(bstatus, "Click on secondary diagnosis for ICD10 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_procedureNavi);
			Common_Lib.verifyFlag(bstatus, "Click on procedure dropdown for ICD10 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_CPTNavi);
			Common_Lib.verifyFlag(bstatus, "Click on CPT for ICD10 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_EandMNavi);
			Common_Lib.verifyFlag(bstatus, "Click on E & M for ICD10 ");

			bstatus = Common_Lib.verify_ElemnenIsClickableAndClick(codingPnl_webe.lnk_hcpcsNavi);
			Common_Lib.verifyFlag(bstatus, "Click on HCPCS for ICD10 ");

			Log4J.logp.info("**************  Ending - verifyCodingPnlNavigation  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyCodingPnlNavigation  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyCodingPnlNavigation is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for selecttext for add evidence to existing code
	 * 
	 * @author jsolanki
	 * @since 05/12/2014
	 */
	@Test(description = " ezCAC_MVP_Reg-299:User can able to add to existing code from the medical record panel", priority = 250)
	public static void verifyAddEvidenceToExistingCode()
	{
		boolean bStatus = false;
		String str = null;
		String str1 = null;
		try
		{
			Log4J.logp.info("****************************  Started - verifyAddEvidenceToExistingCode  ****************************");
			//Logout first Current User
			Login_Lib.logOut_App();
			Login_Lib.login("js001");
			landingp_webe.lnk_Coding.click();
			Thread.sleep(1000);
			Common_Lib.openCase("js001");
			Thread.sleep(2000);
			str = codingPnl_webe.lst_codeType.getText();
			if (str.contains("ICD-9"))
			{
				Log4J.logp.info("Started - verifyAddEvidenceToExistingCode for ICD-9");
				bStatus = WorkingScreen_Lib.selecttext("js001");
				Log4J.logp.info("Ending - verifyAddEvidenceToExistingCode for ICD-9");
				groupingpnl_webe.btn_Later.click();
				Common_Lib.openCase("js001");
				Common_Lib.waitForObject(codingPnl_webe.lst_codeType, "visibility", 10);
				codingPnl_webe.lst_codeType.click();
				Common_Lib.waitForObject(codingPnl_webe.lnk_ICD10_Menu, "visibility", 10);
				codingPnl_webe.lnk_ICD10_Menu.click();
				Thread.sleep(2000);
				Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1500);
				str1 = codingPnl_webe.lst_codeType.getText();
				if (str1.contains("ICD-10"))
				{
					Log4J.logp.info("Started - verifyAddEvidenceToExistingCode ICD-10");
					bStatus = WorkingScreen_Lib.selecttext("js001");
					Log4J.logp.info("Ending - verifyAddEvidenceToExistingCode for ICD-10");
				}
				else
				{
					Log4J.logp.error("**************  Problem Found in - verifyAddEvidenceToExistingCode for ICD-10 **************");
				}

			}

			else
			{
				Log4J.logp.error("**************  Problem Found in - verifyAddEvidenceToExistingCode for ICD-9 **************");
			}
			Assert.assertTrue(bStatus, "verifyAddEvidenceToExistingCode is failed");
			Thread.sleep(2000);
			Log4J.logp.info("****************************  Ending - verifyAddEvidenceToExistingCode  ****************************");
			driver.navigate().refresh();
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyAddEvidenceToExistingCode  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyAddEvidenceToExistingCode is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for selecttext for add newcode for diagnosis
	 * 
	 * @author jsolanki
	 * @since 05/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-301:Verify that user can able to add new code for all type of code types", priority = 251)
	public static void verifyAddNewCodeDiagnosis()
	{

		boolean bStatus = false;
		String str = null;
		String str1 = null;
		try
		{
			Log4J.logp.info("****************************  Started - verifyAddNewCodeDiagnosis  ****************************");

			//Logout first Current User
			Login_Lib.logOut_App();
			Login_Lib.login("js001");
			landingp_webe.lnk_Coding.click();
			Thread.sleep(1000);
			Common_Lib.openCase("js001");
			str = codingPnl_webe.lst_codeType.getText();
			if (str.contains("ICD-9"))
			{
				//bStatus = WorkingScreen_Lib.selecttext("js003");
				Log4J.logp.info("Started - verifyAddNewCodeDiagnosis for ICD-9");
				bStatus = WorkingScreen_Lib.selecttext("js003");
				Log4J.logp.info("Ending - verifyAddNewCodeDiagnosis for ICD-9");
				groupingpnl_webe.btn_Later.click();
				Common_Lib.openCase("js001");
				Common_Lib.waitForObject(codingPnl_webe.lst_codeType, "visibility", 10);
				codingPnl_webe.lst_codeType.click();
				Common_Lib.waitForObject(codingPnl_webe.lnk_ICD10_Menu, "visibility", 10);
				codingPnl_webe.lnk_ICD10_Menu.click();
				Thread.sleep(2000);
				Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1500);
				str1 = codingPnl_webe.lst_codeType.getText();
				if (str1.contains("ICD-10"))
				{
					Log4J.logp.info("Started - verifyAddNewCodeDiagnosis ICD-10");
					bStatus = WorkingScreen_Lib.selecttext("js003");
					Log4J.logp.info("Ending - verifyAddNewCodeDiagnosis for ICD-10");
				}
				else
				{
					Log4J.logp.error("**************  Problem Found in - verifyAddNewCodeDiagnosis for ICD-10 **************");
				}

			}

			else
			{
				Log4J.logp.error("**************  Problem Found in - verifyAddNewCodeDiagnosis for ICD-9 **************");
			}

			Assert.assertTrue(bStatus, "verifyAddNewCodeDiagnosis is failed");
			Log4J.logp.info("****************************  Ending - verifyAddNewCodeDiagnosis  ****************************");
			driver.navigate().refresh();

		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyAddNewCodeDiagnosis  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyAddNewCodeDiagnosis is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for selecttext for add newcode for procedures
	 * 
	 * @author jsolanki
	 * @since 05/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-301:Verify that user can able to add new code for all type of code types", priority = 252)
	public static void verifyAddNewCodeProcedures()
	{

		boolean bStatus = false;
		String str = null;
		String str1 = null;
		try
		{
			Log4J.logp.info("****************************  Started - verifyAddNewCodeProcedures  ****************************");

			//Logout first Current User
			Login_Lib.logOut_App();
			Login_Lib.login("js001");
			landingp_webe.lnk_Coding.click();
			Thread.sleep(1000);
			Common_Lib.openCase("js001");
			Thread.sleep(2000);
			str = codingPnl_webe.lst_codeType.getText();
			if (str.contains("ICD-9"))
			{
				Log4J.logp.info("Started - verifyAddNewCodeProcedures for ICD-9");
				bStatus = WorkingScreen_Lib.selecttext("js004");
				Log4J.logp.info("Ending - verifyAddNewCodeProcedures for ICD-9");
				groupingpnl_webe.btn_Later.click();
				Common_Lib.openCase("js001");
				Common_Lib.waitForObject(codingPnl_webe.lst_codeType, "visibility", 10);
				codingPnl_webe.lst_codeType.click();
				Common_Lib.waitForObject(codingPnl_webe.lnk_ICD10_Menu, "visibility", 10);
				codingPnl_webe.lnk_ICD10_Menu.click();
				Thread.sleep(2000);
				Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1500);
				str1 = codingPnl_webe.lst_codeType.getText();
				if (str1.contains("ICD-10"))
				{
					Log4J.logp.info("Started - verifyAddNewCodeProcedures ICD-10");
					bStatus = WorkingScreen_Lib.selecttext("js004");
					Log4J.logp.info("Ending - verifyAddNewCodeProcedures for ICD-10");
				}
				else
				{
					Log4J.logp.error("**************  Problem Found in - verifyAddNewCodeProcedures for ICD-10 **************");
				}

			}

			else
			{
				Log4J.logp.error("**************  Problem Found in - verifyAddNewCodeProcedures for ICD-9 **************");
			}

			Assert.assertTrue(bStatus, "verifyAddNewCodeProcedures is failed");
			Log4J.logp.info("****************************  Ending - verifyAddNewCodeProcedures  ****************************");
			driver.navigate().refresh();
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyAddNewCodeProcedures  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyAddNewCodeProcedures is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for selecttext for query physician
	 * 
	 * @author jsolanki
	 * @since 05/12/2014
	 */

	@Test(description = "ezCAC_MVP_Reg-301:Verify that user can able to add new code for all type of code types", priority = 253)
	public static void verifyqueryPhysician()
	{

		boolean bStatus = false;
		String str = null;
		String str1 = null;
		try
		{
			Log4J.logp.info("****************************  Started - verifyqueryPhysician  ****************************");

			//Logout first Current User
			Login_Lib.logOut_App();
			Login_Lib.login("js001");
			landingp_webe.lnk_Coding.click();
			Thread.sleep(1000);
			Common_Lib.openCase("js001");
			Thread.sleep(2000);
			str = codingPnl_webe.lst_codeType.getText();
			if (str.contains("ICD-9"))
			{
				Log4J.logp.info("Started - verifyqueryPhysician for ICD-9");
				bStatus = WorkingScreen_Lib.selecttext("js002");
				Log4J.logp.info("Ending - verifyqueryPhysician for ICD-9");
				groupingpnl_webe.btn_Later.click();
				Common_Lib.openCase("js001");
				Common_Lib.waitForObject(codingPnl_webe.lst_codeType, "visibility", 10);
				codingPnl_webe.lst_codeType.click();
				Common_Lib.waitForObject(codingPnl_webe.lnk_ICD10_Menu, "visibility", 10);
				codingPnl_webe.lnk_ICD10_Menu.click();
				Thread.sleep(2000);
				Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1500);
				str1 = codingPnl_webe.lst_codeType.getText();
				if (str1.contains("ICD-10"))
				{
					Log4J.logp.info("Started - verifyqueryPhysician ICD-10");
					bStatus = WorkingScreen_Lib.selecttext("js006");
					Log4J.logp.info("Ending - verifyqueryPhysician for ICD-10");
				}
				else
				{
					Log4J.logp.error("**************  Problem Found in - verifyqueryPhysician for ICD-10 **************");
				}

			}

			else
			{
				Log4J.logp.error("**************  Problem Found in - verifyqueryPhysician for ICD-9 **************");
			}

			Assert.assertTrue(bStatus, "verifyqueryPhysician is failed");
			Log4J.logp.info("****************************  Ending - verifyqueryPhysician  ****************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyqueryPhysician  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyqueryPhysician is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for selecttext for discuss with colleague
	 * 
	 * @author jsolanki
	 * @since 05/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-301:Verify that user can able to add new code for all type of code types", priority = 254)
	public static void verifydiscusswithColleague()
	{

		boolean bStatus = false;
		String str = null;
		String str1 = null;
		try
		{
			Log4J.logp.info("****************************  Started - verifydiscusswithColleague  ****************************");

			//Logout first Current User
			Login_Lib.logOut_App();
			Login_Lib.login("js001");
			landingp_webe.lnk_Coding.click();
			Thread.sleep(1000);
			Common_Lib.openCase("js001");
			Thread.sleep(2000);
			str = codingPnl_webe.lst_codeType.getText();
			if (str.contains("ICD-9"))
			{
				Log4J.logp.info("Started - verifydiscusswithColleague for ICD-9");
				bStatus = WorkingScreen_Lib.selecttext("js005");
				Log4J.logp.info("Ending - verifydiscusswithColleague for ICD-9");
				groupingpnl_webe.btn_Later.click();
				Common_Lib.openCase("js001");
				Common_Lib.waitForObject(codingPnl_webe.lst_codeType, "visibility", 10);
				codingPnl_webe.lst_codeType.click();
				Common_Lib.waitForObject(codingPnl_webe.lnk_ICD10_Menu, "visibility", 10);
				codingPnl_webe.lnk_ICD10_Menu.click();
				Thread.sleep(2000);
				Common_Lib.scroll_Page_Up(medicalrecordpnl_webe.medical_Dragger, 1500);
				str1 = codingPnl_webe.lst_codeType.getText();
				if (str1.contains("ICD-10"))
				{
					Log4J.logp.info("Started - verifydiscusswithColleague ICD-10");
					bStatus = WorkingScreen_Lib.selecttext("js007");
					Log4J.logp.info("Ending - verifydiscusswithColleague for ICD-10");
				}
				else
				{
					Log4J.logp.error("**************  Problem Found in - verifydiscusswithColleague for ICD-10 **************");
				}

			}

			else
			{
				Log4J.logp.error("**************  Problem Found in - verifydiscusswithColleague for ICD-9 **************");
			}

			Assert.assertTrue(bStatus, "verifydiscusswithColleague is failed");
			Log4J.logp.info("****************************  Ending - verifydiscusswithColleague  ****************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifydiscusswithColleague  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifydiscusswithColleague is failed");

		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is to check diagnosis code
	 * 
	 * @author jsolanki
	 * @since 05/12/2014
	 */

	@Test(description = "ezCAC_MVP_Reg-2630:Verify that if Principal code is not received from the HL-7 and user accept secondary code", priority = 255)
	public static void acceptSecondaryDiagnosisCode()
	{
		boolean bStatus = false;
		try
		{
			Log4J.logp.info("**************  Started - verifyDiagnosisCode  **************");
			//Logout first Current User
			Login_Lib.logOut_App();
			Login_Lib.login("js001");
			landingp_webe.lnk_Coding.click();
			Thread.sleep(1000);
			Common_Lib.openCase("js001");
			Thread.sleep(2000);
			bStatus = WorkingScreen_Lib.find_DiagnosisCode();
			if (Common_Lib.checkElementPresent(codingPnl_webe.lbl_firstSecondaryCodeNum))
			{

				Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 1500);
				Thread.sleep(2000);
				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstSecondaryCodeNum, 30);
				Thread.sleep(2000);
				codingPnl_webe.lnk_makeItPrincipalAssoSecondary.click();
				Log4J.logp.info("secondary code is now Principal diagnosis code");
				Thread.sleep(2000);
				String str1 = codingPnl_webe.lbl_principalRank.getText();
				if (str1 != null)
				{
					str1.contains("1");
					Log4J.logp.info("Rank is 1");
					Thread.sleep(2000);
					Log4J.logp.info("Now Reject Principal diagnosis code");
					codingPnl_webe.ico_Reject_Associated_Principal.click();
					Thread.sleep(2000);
					Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 1500);
					Thread.sleep(2000);
					Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstSecondaryCodeNum, 30);
					Thread.sleep(2000);
					codingPnl_webe.lnk_makeItPrincipalAssoSecondary.click();
					Log4J.logp.info("secondary code is now Principal diagnosis code");
					Thread.sleep(2000);
					String str2 = codingPnl_webe.lbl_principalRank.getText();
					if (str2 != null)
					{
						str2.contains("1");
						Log4J.logp.info("Rank is 1");
					}
					else
					{
						Log4J.logp.info("Rank is not 1");
					}
				}
				else
				{
					Log4J.logp.info("Rank is not 1");
				}
			}

			Assert.assertTrue(bStatus, "acceptSecondaryDiagnosisCode is failed");
			Log4J.logp.info("****************************  Ending - acceptSecondaryDiagnosisCode  ****************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyDiagnosisCode  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyDiagnosisCode is failed");
		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is for accept admit dx code for rank 1
	 * 
	 * @author jsolanki
	 * @since 05/12/2014
	 */

	@Test(description = "ezCAC_MVP_Reg-2631:Verify that if Principal code is not received from the HL-7 and user accept admitting dx code +" + "ezCAC_MVP_Reg-2667:Verify that if user reject the principal dx code and accepted some secondary diagnosis code", priority = 256)
	public static void acceptAdmittingDiagnosisCode()
	{

		boolean bStatus = false;
		try
		{
			Log4J.logp.info("**************  Started - verifyDiagnosisCode  **************");
			//Logout first Current User
			Login_Lib.logOut_App();
			Login_Lib.login("js001");
			landingp_webe.lnk_Coding.click();
			Thread.sleep(1000);
			Common_Lib.openCase("js001");
			Thread.sleep(2000);
			WorkingScreen_Lib.addnewCode_withData_ICD9("js001");
			Thread.sleep(2000);
			driver.navigate().refresh();
			bStatus = WorkingScreen_Lib.find_DiagnosisCode();

			if (Common_Lib.checkElementPresent(codingPnl_webe.lbl_firstAdmitCodeNum))
			{

				Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 1500);
				Thread.sleep(2000);
				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstAdmitCodeNum, 30);
				Thread.sleep(2000);
				codingPnl_webe.ico_Reject_Associated_Principal.click();
				Thread.sleep(2000);
				codingPnl_webe.lnk_Accept_Admitting.click();
				String str1 = codingPnl_webe.txt_admitingdiagnosisRank.getText();
				if (str1 != null)
				{
					str1.contains("1");
					Log4J.logp.info("Admitting code is Rank 1");
				}

				else
				{
					Log4J.logp.info("Admitting code is not Rank 1");
				}
			}
			Assert.assertTrue(bStatus, "acceptAdmittingDiagnosisCode is failed");
			Log4J.logp.info("****************************  Ending - acceptAdmittingDiagnosisCode  ****************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - verifyDiagnosisCode  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "verifyDiagnosisCode is failed");
		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}
	}

	/**
	 * This method is for accept principal dx code from reference panel
	 * 
	 * @author jsolanki
	 * @since 12/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2691:Verify if user adds Principal code from the reference panel when Principal code is already exist", priority = 257)
	public static void acceptPrincipalcodefromReferencepanel()
	{

		boolean bStatus = false;
		try
		{
			Log4J.logp.info("**************  Started - acceptPrincipalcodefromReferencepanel  **************");
			//Logout first Current User
			Login_Lib.logOut_App();
			Login_Lib.login("js001");
			landingp_webe.lnk_Coding.click();
			Thread.sleep(1000);
			Common_Lib.openCase("js001");
			Thread.sleep(2000);
			bStatus = WorkingScreen_Lib.find_DiagnosisCode();

			if (Common_Lib.checkElementPresent(codingPnl_webe.lbl_firstPrincipalCodeNum))
			{
				Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 1500);
				Thread.sleep(1000);
				Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lnk_accept_principal_disabled, 30);
				Thread.sleep(2000);
				codingPnl_webe.lnk_accept_principal_disabled.click();
				Thread.sleep(2000);
				groupingpnl_webe.btn_Done.click();
				Thread.sleep(2000);
				groupingpnl_webe.lnk_GroupingPnl.click();
				Thread.sleep(2000);
				groupingpnl_webe.lnk_DRGAnalysis.click();
				Thread.sleep(2000);
				groupingpnl_webe.lnk_firstDRGRefCode.click();
				Thread.sleep(2000);
				groupingpnl_webe.lnk_addPrincipalFromRef.click();
				Thread.sleep(2000);
				groupingpnl_webe.btn_Close.click();
				Thread.sleep(1000);
				String str1 = codingPnl_webe.lbl_principalRank.getText();
				if (str1 != null)
				{
					str1.contains("1");
					Log4J.logp.info("Principal diagnosis code Rank is 1");
				}
				else
				{
					Log4J.logp.info("Principal diagnosis code Rank is not 1");
				}
			}
			Assert.assertTrue(bStatus, "acceptPrincipalcodefromReferencepanel is failed");
			Log4J.logp.info("****************************  Ending - acceptPrincipalcodefromReferencepanel  ****************************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - acceptPrincipalcodefromReferencepanel  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "acceptPrincipalcodefromReferencepanel is failed");
		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}
	}

	/**
	 * This method is to check user gets MNE edits and is able to search Covered Code in code book
	 * 
	 * @author fmodi
	 * @since 04/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2818:Verify that user can get MNE edits on CPT/HCPCS codes" + "ezCAC_MVP_Reg-2828:Verify that user can get MNE edits on CPT/HCPCS codes and is able to search the code", priority = 912)
	public static void MNEEditsAndSearchCode()
	{

		String strRefCode, strSearchedCode;

		try
		{
			Log4J.logp.info("**************  Started - MNEEditsAndSearchCode  **************");

			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("fm005");
			Thread.sleep(2000);

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);

			Common_Lib.openCase("fm091");
			//Thread.sleep(15000);
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);

			WorkingScreen_Lib.addnewCode_withData_ICD9("fm001");
			Thread.sleep(2000);
			WorkingScreen_Lib.accept_Code("fm001");
			Thread.sleep(2000);

			WorkingScreen_Lib.addnewCode_withData_ICD9("fm002");
			Thread.sleep(2000);
			WorkingScreen_Lib.accept_Code("fm002");
			Thread.sleep(2000);
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstProcedureCodeNum, 30);
			Thread.sleep(2000);
			codingPnl_webe.lbl_firstProcedureCodeNum.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.addParameteres("fm001", "cpt");
			Thread.sleep(2000);
			codingPnl_webe.btn_Save_CPT.click();
			Thread.sleep(2000);

			groupingpnl_webe.btn_Done.click();
			Thread.sleep(2000);

			//Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstProcedureCodeNum, 30);
			if (Common_Lib.checkElementPresent(codingPnl_webe.ico_editCodingPanel))
			{
				Log4J.logp.info("Edit icon is displayed on code template");
				Assert.assertTrue(true, "Edit icon is displayed on code template");
			}
			else
			{
				Log4J.logp.error("Edit icon is not displayed on code template");
				Assert.assertTrue(false, "Edit icon is not displayed on code template");
			}

			codingPnl_webe.lbl_firstProcedureCodeNum.click();
			Thread.sleep(2000);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 25);
			Thread.sleep(2000);
			if (Common_Lib.checkElementPresent(codingPnl_webe.lbl_MNEeditCPTerror) && (codingPnl_webe.lbl_MNEeditCPTerror.getText().contains("NCD") || codingPnl_webe.lbl_MNEeditCPTerror.getText().contains("LCD")))
			{
				Log4J.logp.info("MNE Edits is displayed");
				Assert.assertTrue(true, "MNE Edits is displayed");
			}
			else
			{
				Log4J.logp.error("MNE Edits is not displayed");
				Assert.assertTrue(false, "MNE Edits is not displayed");
			}

			codingPnl_webe.lnk_MNEeditDetails.click();
			Thread.sleep(2000);
			if (abstractpnl_webe.div_MNEReferencePnl.isDisplayed())
			{
				Log4J.logp.info("After clicking on MNE Details link,MNE Reference panel is open successfully");
				Assert.assertTrue(true, "After clicking on MNE Details link,MNE Reference panel is open successfully");
			}
			else
			{
				Log4J.logp.error("After clicking on MNE Details link,MNE Reference panel is not open successfully");
				Assert.assertTrue(false, "After clicking on MNE Details link,MNE Reference panel is not open successfully");
			}
			Thread.sleep(2000);

			if (codingPnl_webe.lnk_MNEcoveredCodes.isDisplayed())
			{
				Log4J.logp.info("Covered Codes are displayed in MNE Reference panel");
				Assert.assertTrue(true, "Covered Codes are displayed in MNE Reference panel");
			}
			else
			{
				Log4J.logp.error("Covered Codes are not displayed in MNE Reference panel");
				Assert.assertTrue(false, "Covered Codes are not displayed in MNE Reference panel");
			}
			Thread.sleep(2000);
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.MNEDetailsReferencePanel_Dragger, codingPnl_webe.lnk_MNEnonCoveredCodes, 100);
			Thread.sleep(2000);
			if (codingPnl_webe.lnk_MNEnonCoveredCodes.isDisplayed())
			{
				Log4J.logp.info("Non-Covered Codes are displayed in MNE Reference panel");
				Assert.assertTrue(true, "Non-Covered Codes are displayed in MNE Reference panel");
			}
			else
			{
				Log4J.logp.error("Non-Covered Codes are not displayed in MNE Reference panel");
				Assert.assertTrue(false, "Non-Covered Codes are not displayed in MNE Reference panel");
			}
			//Thread.sleep(3000);
			Common_Lib.waitForObject(codingPnl_webe.MNEDetailsReferencePanel_Dragger, "clickable", 3);

			Common_Lib.scroll_Page_Up(codingPnl_webe.MNEDetailsReferencePanel_Dragger, 300);
			Thread.sleep(2000);

			strRefCode = codingPnl_webe.lnk_MNEFirstCoveredCode.getText();
			Log4J.logp.info("strRefCode=" + strRefCode);
			codingPnl_webe.lnk_MNEFirstCoveredCode.click();
			Thread.sleep(2000);
			if (groupingpnl_webe.lnk_addPrincipalFromRef.isDisplayed())
			{
				Log4J.logp.info("'Add as Principal Diagnosis' option is displayed in MNE Reference panel");
				Assert.assertTrue(true, "'Add as Principal Diagnosis' option is displayed in MNE Reference panel");
			}
			else
			{
				Log4J.logp.error("'Add as Principal Diagnosis' option is not displayed in MNE Reference panel");
				Assert.assertTrue(false, "'Add as Principal Diagnosis' option is not displayed in MNE Reference panel");
			}
			Thread.sleep(2000);
			if (groupingpnl_webe.lnk_viewCodeFromRef.isDisplayed())
			{
				Log4J.logp.info("'View in Code Book' option is displayed in MNE Reference panel");
				Assert.assertTrue(true, "'View in Code Book' option is displayed in MNE Reference panel");
			}
			else
			{
				Log4J.logp.error("'View in Code Book' option is not displayed in MNE Reference panel");
				Assert.assertTrue(false, "'View in Code Book' option is not displayed in MNE Reference panel");
			}
			Thread.sleep(2000);

			groupingpnl_webe.lnk_viewCodeFromRef.click();
			//Thread.sleep(5000);

			//driver.switchTo().frame(driver.findElement(By.id("code-book-iframe")));
			Common_Lib.waitForObject(codeBook_webe.frm_CodeBook, "code-book-iframe", 5);
			//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(codeBook_webe.frm_CodeBook.getAttribute("id"))));
			//Thread.sleep(5000);
			Common_Lib.waitForObject(codeBook_webe.lnk_searchCode, "clickable", 5);

			strSearchedCode = codeBook_webe.lnk_searchCode.getText();
			Log4J.logp.info("strSearchedCode=" + strSearchedCode);
			codeBook_webe.lnk_searchCode.click();
			driver.switchTo().defaultContent();

			if (strSearchedCode.contains(strRefCode))
			{
				Log4J.logp.info("'Selected Covered Code' is searched in Code Book");
				Assert.assertTrue(true, "'Selected Covered Code' is searched in Code Book");
			}
			else
			{
				Log4J.logp.error("'Selected Covered Code' is not searched in Code Book");
				Assert.assertTrue(false, "'Selected Covered Code' is not searched in Code Book");
			}
			Thread.sleep(2000);

			Log4J.logp.info("**************  Ending - MNEEditsAndSearchCode  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - MNEEditsAndSearchCode  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "MNEEditsAndSearchCode is failed");
		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	/**
	 * This method is to check user gets MNE edits and can Add/Replace Principal code using Covered codes
	 * 
	 * @author fmodi
	 * @since 05/12/2014
	 */
	@Test(description = "ezCAC_MVP_Reg-2824:Verify that user can get MNE edits on CPT/HCPCS codes and is able to replace the code", priority = 911)
	public static void MNEEditsAndReplacePrincipalCode()
	{

		String strOldPriCode, strRefPriCode, strNewPriCode;

		try
		{
			Log4J.logp.info("**************  Started - MNEEditsAndReplacePrincipalCode  **************");

			Login_Lib.logOut_App();
			Thread.sleep(2000);
			Login_Lib.login("fm005");
			Thread.sleep(2000);

			landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);

			Common_Lib.openCase("fm090");
			//Thread.sleep(15000);
			Common_Lib.waitForObject(groupingpnl_webe.btn_Later, "clickable", 15);

			/*Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_principalCodeNum, 20);
			Thread.sleep(2000);*/
			strOldPriCode = codingPnl_webe.lbl_principalCodeNum.getText();
			Log4J.logp.info("strOldPriCode=" + strOldPriCode);

			WorkingScreen_Lib.addnewCode_withData_ICD9("fm001");
			Thread.sleep(2000);
			WorkingScreen_Lib.accept_Code("fm001");
			Thread.sleep(2000);

			WorkingScreen_Lib.addnewCode_withData_ICD9("fm002");
			Thread.sleep(2000);
			WorkingScreen_Lib.accept_Code("fm002");
			Thread.sleep(2000);
			codingPnl_webe.lbl_firstProcedureCodeNum.click();
			Thread.sleep(2000);
			WorkingScreen_Lib.addParameteres("fm001", "cpt");
			Thread.sleep(2000);
			codingPnl_webe.btn_Save_CPT.click();
			Thread.sleep(2000);

			groupingpnl_webe.btn_Done.click();
			Thread.sleep(2000);

			//Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstProcedureCodeNum, 30);
			if (Common_Lib.checkElementPresent(codingPnl_webe.ico_editCodingPanel))
			{
				Log4J.logp.info("Edit icon is displayed on code template");
				Assert.assertTrue(true, "Edit icon is displayed on code template");
			}
			else
			{
				Log4J.logp.error("Edit icon is not displayed on code template");
				Assert.assertTrue(false, "Edit icon is not displayed on code template");
			}

			codingPnl_webe.lbl_firstProcedureCodeNum.click();
			Thread.sleep(2000);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 25);
			Thread.sleep(2000);
			if (Common_Lib.checkElementPresent(codingPnl_webe.lbl_MNEeditCPTerror) && (codingPnl_webe.lbl_MNEeditCPTerror.getText().contains("NCD") || codingPnl_webe.lbl_MNEeditCPTerror.getText().contains("LCD")))
			{
				Log4J.logp.info("MNE Edits is displayed");
				Assert.assertTrue(true, "MNE Edits is displayed");
			}
			else
			{
				Log4J.logp.error("MNE Edits is not displayed");
				Assert.assertTrue(false, "MNE Edits is not displayed");
			}

			codingPnl_webe.lnk_MNEeditDetails.click();
			Thread.sleep(2000);

			if (codingPnl_webe.lnk_MNEcoveredCodes.isDisplayed())
			{
				Log4J.logp.info("Covered Codes are displayed in MNE Reference panel");
				Assert.assertTrue(true, "Covered Codes are displayed in MNE Reference panel");
			}
			else
			{
				Log4J.logp.error("Covered Codes are not displayed in MNE Reference panel");
				Assert.assertTrue(false, "Covered Codes are not displayed in MNE Reference panel");
			}
			Thread.sleep(2000);
			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.MNEDetailsReferencePanel_Dragger, codingPnl_webe.lnk_MNEnonCoveredCodes, 100);
			Thread.sleep(2000);
			if (codingPnl_webe.lnk_MNEnonCoveredCodes.isDisplayed())
			{
				Log4J.logp.info("Non-Covered Codes are displayed in MNE Reference panel");
				Assert.assertTrue(true, "Non-Covered Codes are displayed in MNE Reference panel");
			}
			else
			{
				Log4J.logp.error("Non-Covered Codes are not displayed in MNE Reference panel");
				Assert.assertTrue(false, "Non-Covered Codes are not displayed in MNE Reference panel");
			}
			//Thread.sleep(3000);
			Common_Lib.waitForObject(codingPnl_webe.MNEDetailsReferencePanel_Dragger, "clickable", 3);

			Common_Lib.scroll_Page_Up(codingPnl_webe.MNEDetailsReferencePanel_Dragger, 300);
			Thread.sleep(2000);

			strRefPriCode = codingPnl_webe.lnk_MNEFirstCoveredCode.getText();
			Log4J.logp.info("strRefPriCode=" + strRefPriCode);
			codingPnl_webe.lnk_MNEFirstCoveredCode.click();
			Thread.sleep(2000);
			if (groupingpnl_webe.lnk_addPrincipalFromRef.isDisplayed())
			{
				Log4J.logp.info("'Add as Principal Diagnosis' option is displayed in MNE Reference panel");
				Assert.assertTrue(true, "'Add as Principal Diagnosis' option is displayed in MNE Reference panel");
			}
			else
			{
				Log4J.logp.error("'Add as Principal Diagnosis' option is not displayed in MNE Reference panel");
				Assert.assertTrue(false, "'Add as Principal Diagnosis' option is not displayed in MNE Reference panel");
			}
			Thread.sleep(2000);
			if (groupingpnl_webe.lnk_viewCodeFromRef.isDisplayed())
			{
				Log4J.logp.info("'View in Code Book' option is displayed in MNE Reference panel");
				Assert.assertTrue(true, "'View in Code Book' option is displayed in MNE Reference panel");
			}
			else
			{
				Log4J.logp.error("'View in Code Book' option is not displayed in MNE Reference panel");
				Assert.assertTrue(false, "'View in Code Book' option is not displayed in MNE Reference panel");
			}
			Thread.sleep(2000);

			groupingpnl_webe.lnk_addPrincipalFromRef.click();
			Thread.sleep(2000);
			Common_Lib.scroll_Page_Up(codingPnl_webe.coding_Dragger, 150);
			Thread.sleep(2000);
			strNewPriCode = codingPnl_webe.lbl_principalCodeNum.getText();
			Log4J.logp.info("strNewPriCode=" + strNewPriCode);
			if (strNewPriCode.contains(strRefPriCode))
			{
				Log4J.logp.info("'Selected Covered Code' from MNE Reference panel is set as 'Principal Diagnosis' successfully");
				Assert.assertTrue(true, "'Selected Covered Code' from MNE Reference panel is set as 'Principal Diagnosis' successfully");
			}
			else
			{
				Log4J.logp.error("'Selected Covered Code' from MNE Reference panel is not set as 'Principal Diagnosis'");
				Assert.assertTrue(false, "'Selected Covered Code' from MNE Reference panel is not set as 'Principal Diagnosis'");
			}
			Thread.sleep(2000);

			if (Common_Lib.codeSearchInCodingPnl(strOldPriCode))
			{
				Log4J.logp.info("'Old Principal Code' is displayed in Coding Panel");
				Assert.assertTrue(true, "'Old Principal Code' is displayed in Coding Panel");
			}
			else
			{
				Log4J.logp.error("'Old Principal Code' is not displayed in Coding Panel");
				Assert.assertTrue(false, "'Old Principal Code' is not displayed in Coding Panel");
			}
			Thread.sleep(2000);

			groupingpnl_webe.btn_Done.click();
			Thread.sleep(2000);

			Common_Lib.scroll_Until_FindWebe(codingPnl_webe.coding_Dragger, codingPnl_webe.lbl_firstProcedureCodeNum, 50);
			Thread.sleep(2000);
			codingPnl_webe.lbl_firstProcedureCodeNum.click();
			Thread.sleep(2000);
			Common_Lib.scroll_Page_VerySmall(codingPnl_webe.coding_Dragger, 25);
			Thread.sleep(2000);
			if (!(Common_Lib.checkElementPresent(codingPnl_webe.lbl_MNEeditCPTerror) && (codingPnl_webe.lbl_MNEeditCPTerror.getText().contains("NCD") || codingPnl_webe.lbl_MNEeditCPTerror.getText().contains("LCD"))))
			{
				Log4J.logp.info("MNE Edits is not displayed");
				Assert.assertTrue(true, "MNE Edits is not displayed");
			}
			else
			{
				Log4J.logp.error("MNE Edits is displayed");
				Assert.assertTrue(false, "MNE Edits is displayed");
			}

			Log4J.logp.info("**************  Ending - MNEEditsAndReplacePrincipalCode  **************");
		}
		catch (Exception e)
		{
			Log4J.logp.error("**************  Problem Found in - MNEEditsAndReplacePrincipalCode  **************");
			e.printStackTrace();
			Assert.assertTrue(false, "MNEEditsAndReplacePrincipalCode is failed");
		}
		finally
		{
			if (groupingpnl_webe.btn_Later.isDisplayed())
			{
				groupingpnl_webe.btn_Later.click();
			}
		}

	}

	@AfterClass
	public static void WorkingScreenAfterClass() throws Exception
	{
		Login_Lib.logOut_App();
		Thread.sleep(1000);
		Log4J.logp.info("Logout with curent user");
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
			if (common_webe != null)
			{
				common_webe = null;
			}
			if (groupingpnl_webe != null)
			{
				groupingpnl_webe = null;
			}
			if (demographicpnl_webe != null)
			{
				demographicpnl_webe = null;
			}
			if (issuepnl_webe != null)
			{
				issuepnl_webe = null;
			}
			if (abstractpnl_webe != null)
			{
				abstractpnl_webe = null;
			}
			if (codingPnl_webe != null)
			{
				codingPnl_webe = null;
			}
			if (codeBook_webe != null)
			{
				codeBook_webe = null;
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
