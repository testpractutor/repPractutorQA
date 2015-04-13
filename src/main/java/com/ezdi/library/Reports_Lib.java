package com.ezdi.library;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.ezdi.framework.common.JDBCMySql;
import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.webelements.Excel_WebE;
import com.ezdi.webelements.LandingP_WebE;
import com.ezdi.webelements.PDF_WebE;
import com.ezdi.webelements.SearchCriteria_WebE;
import com.ezdi.webelements.Show_Hide_Columns_WebE;
import com.mysql.jdbc.CallableStatement;

public class Reports_Lib
{
	public static WebDriver					driver;
	public static Show_Hide_Columns_WebE	showHideColumn_webe;
	public static LandingP_WebE				landingp_webe;
	public static SearchCriteria_WebE		searchcriteria_webe;
	public static PDF_WebE					pdf_webe;
	public static Excel_WebE				excel_webe;
	public static Connection				con;

	/**
	 * Run stored procedure for reports in AWS Database
	 * 
	 * @author agupta
	 * @since 14/10/2014
	 */
	public static boolean stored_Procedure()
	{
		try
		{
			Log4J.logp.info("-------------- Started :: stored_Procedure ----------------");
			Class.forName("com.mysql.jdbc.Driver");
			Properties properties = new Properties();
			InputStream input = ClassLoader.getSystemResourceAsStream("Properties/database.properties");
			properties.load(input);
			con = DriverManager.getConnection(properties.getProperty("aws.database.url"), properties.getProperty("aws.database.username"), properties.getProperty("aws.database.password"));
			// Call procedure for reports module.  
			CallableStatement cstmt = (CallableStatement) con.prepareCall("{call `SP_REPORT_DASHBOARD_DATAGEN`(1)}");
			cstmt.executeUpdate();
			Log4J.logp.info("-------------- Ended :: stored_Procedure ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: stored_Procedure");
			Assert.assertTrue(false, "Problem found in stored_Procedure");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This script is hide columns from all reports page
	 * 
	 * @author agupta
	 * @since 10/10/2014
	 */
	public static boolean hide_Columns(String RowId)
	{
		String strfield = null;
		String[] strarrFields = null;
		boolean bstatus = false;
		try
		{
			Log4J.logp.info("----------- Started :: hide_Columns -----------");
			driver = ExecutionSetup.getDriver();
			showHideColumn_webe = Show_Hide_Columns_WebE.getInstance(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			Map<String, String> rowTestData = null;

			JDBCMySql showhideColumn_TestData = new JDBCMySql();
			rowTestData = showhideColumn_TestData.getRowbyID("td_showhidecolumn_reports", RowId);
			strfield = rowTestData.get("Field Name");
			strarrFields = strfield.split("\\$");
			if (strarrFields.length != 0)
			{
				for (int i = 0; i < strarrFields.length; i++)
				{
					WebElement element = driver.findElement(By.xpath("//span[contains(text(),'" + strarrFields[i] + "')]"));
					element.click();
				}
			}
			showHideColumn_webe.lnk_Save.click();
			Thread.sleep(3000);
			WebElement table = showHideColumn_webe.tbl_reports;
			WebElement row = table.findElement(By.xpath("//table[@id='reportsTable']/thead/tr"));
			List<WebElement> columns = row.findElements(By.tagName("th"));

			for (WebElement column : columns)
			{
				if (strfield.equalsIgnoreCase(column.getText()))
				{

					Log4J.logp.info("The show_Hide_Columns method Failed for row id   " + RowId + "for column " + column.getText());
					bstatus = false;
					break;
				}
				else
				{
					Log4J.logp.info(column.getText() + "\t Passed");
					bstatus = true;

				}
			}
			Log4J.logp.info("----------- Started :: hide_Columns -----------");
			return bstatus;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - hide_Columns");
			Assert.assertTrue(false, "Problem found in hide_Columns");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This script is show columns in all reports page.
	 * 
	 * @author agupta
	 * @since 10/10/2014
	 */
	public static boolean show_Columns(String RowId)
	{
		String strfield = null;
		String[] strarrFields = null;
		try
		{
			Log4J.logp.info("-------------- Started :: show_Columns -------------");
			driver = ExecutionSetup.getDriver();
			showHideColumn_webe = Show_Hide_Columns_WebE.getInstance(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			Map<String, String> rowTestData = null;

			JDBCMySql showhideColumn_TestData = new JDBCMySql();
			rowTestData = showhideColumn_TestData.getRowbyID("td_showhidecolumn_reports", RowId);
			strfield = rowTestData.get("Field Name");
			strarrFields = strfield.split("\\$");
			if (strarrFields.length != 0)
			{
				for (int i = 0; i < strarrFields.length; i++)
				{
					WebElement element = driver.findElement(By.xpath("//span[contains(text(),'" + strarrFields[i] + "')]"));
					element.click();
				}
			}
			showHideColumn_webe.lnk_Save.click();
			Thread.sleep(3000);
			Log4J.logp.info("-------------- Ended :: show_Columns -------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: show_Columns");
			Assert.assertTrue(false, "Problem found in show_Columns");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This script is check Validation message of Discharge Date in all reports
	 * 
	 * @author agupta
	 * @since 10/10/2014
	 */
	public static boolean dischargeDate()
	{
		String stralert;
		String stralert1;
		try
		{

			Log4J.logp.info("--------- Started :: Discharge Date --------- ");

			// when user not entered From Date in date range Discharge Date dropdown menu
			Reports_Lib.search_Data("SD014");
			Common_Lib.IsCustomAlertPresent();
			stralert = "You have not provided 'From Date'. Please make sure you provide 'From Date' before performing the search.";
			if (Common_Lib.CustomAlertText.equalsIgnoreCase(stralert))
			{
				Log4J.logp.info("Validation is True");
				Assert.assertTrue(true, "Validation is True");
			}
			searchcriteria_webe.lnk_ClearSearch.click();

			// when user not entered To Date in date range of Discharge Date dropdown menu
			Reports_Lib.search_Data("SD015");
			Common_Lib.IsCustomAlertPresent();
			stralert1 = "You have not provided 'To Date'. Please make sure you provide 'To Date' before performing the search.";
			if (Common_Lib.CustomAlertText.equalsIgnoreCase(stralert1))
			{
				Log4J.logp.info("Validation is True");
				Assert.assertTrue(true, "Validation is True");

			}
			searchcriteria_webe.lnk_ClearSearch.click();

			// when user not entered any date in date range of Discharge Date dropdown menu
			Reports_Lib.search_Data("SD016");
			Common_Lib.IsCustomAlertPresent();
			String alert2 = "You have not provided any date in Discharge Date. Please make sure you provide a date before performing the search.";
			if (Common_Lib.CustomAlertText.equalsIgnoreCase(alert2))
			{
				Log4J.logp.info("Validation is True");
				Assert.assertTrue(true, "Validation is True");
			}
			Log4J.logp.info("--------- Ended :: Discharge Date --------- ");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: Discharge Date");
			Assert.assertTrue(false, "Problem found in :: Discharge Date");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This script is to check validation message of Coding Completed Date in all reports
	 * 
	 * @author agupta
	 * @since 10/10/2014
	 */
	public static boolean codingCompletedDate()
	{
		String stralert;
		String stralert1;
		try
		{
			Log4J.logp.info("----------- Started :: Coding Completed Date ---------------");

			// when user not entered From Date in date range Coding Completed Date dropdown menu
			searchcriteria_webe.lnk_ClearSearch.click();
			Reports_Lib.search_Data("SD017");
			Common_Lib.IsCustomAlertPresent();
			stralert = "You have not provided 'From Date'. Please make sure you provide 'From Date' before performing the search.";
			if (Common_Lib.CustomAlertText.equalsIgnoreCase(stralert))
			{
				Log4J.logp.info("Validation is True");
				Assert.assertTrue(true, "Validation is True");
			}
			searchcriteria_webe.lnk_ClearSearch.click();

			// when user not entered To Date in date range of Coding Completed Date dropdown menu
			Reports_Lib.search_Data("SD018");
			Common_Lib.IsCustomAlertPresent();
			stralert1 = "You have not provided 'To Date'. Please make sure you provide 'To Date' before performing the search.";
			if (Common_Lib.CustomAlertText.equalsIgnoreCase(stralert1))
			{
				Log4J.logp.info("Validation is True");
				Assert.assertTrue(true, "Validation is True");

			}
			searchcriteria_webe.lnk_ClearSearch.click();

			// when user not entered any date in date range of Coding Completed Date dropdown menu	
			Reports_Lib.search_Data("SD019");
			Common_Lib.IsCustomAlertPresent();
			String alert2 = "You have not provided any date in Coding Completed Date. Please make sure you provide a date before performing the search.";
			if (Common_Lib.CustomAlertText.equalsIgnoreCase(alert2))
			{
				Log4J.logp.info("Validation is True");
				Assert.assertTrue(true, "Validation is True");
			}
			searchcriteria_webe.lnk_ClearSearch.click();
			Log4J.logp.info("----------- Ended :: Coding Completed Date ---------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: Coding Completed Date");
			Assert.assertTrue(false, "Problem found in :: Coding Completed Date");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This script is check Result Summary in all report page.
	 * 
	 * @author agupta
	 * @since 09/10/2014
	 */
	public static boolean resultSummary(String RowId)
	{
		String strfield = null;
		int i = 0;
		String[] strarrFields = null;
		try
		{
			Log4J.logp.info("------------ Started :: resultSummary -----------------");
			driver = ExecutionSetup.getDriver();
			landingp_webe = LandingP_WebE.getInstance(driver);
			Map<String, String> rowTestData = null;
			String actualText, expectedText;

			JDBCMySql resultSummary_TestData = new JDBCMySql();
			rowTestData = resultSummary_TestData.getRowbyID("td_resultSummary_reports", RowId);
			strfield = rowTestData.get("Field Name");
			strarrFields = strfield.split("\\@");

			List<WebElement> columns = driver.findElements(By.xpath("//div[@id='summaryView']/div/div"));
			for (WebElement column : columns)
			{
				WebElement getColumnName = column.findElement(By.xpath("//div/div[" + (i + 1) + "]/h3"));
				actualText = getColumnName.getText();
				Thread.sleep(2000);
				expectedText = strarrFields[i];
				Log4J.logp.info(expectedText + " ==  " + actualText);
				Assert.assertEquals(expectedText.trim(), actualText.trim());
				i++;
			}
			Log4J.logp.info("------------ Ended :: resultSummary -----------------");
			return true;
		}
		catch (Exception e)
		{

			Log4J.logp.error("Problem Found In - resultSummary");
			Assert.assertTrue(false, "Problem foumd In :: resultSummary");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This script is to check search result in all reports page
	 * 
	 * @author agupta
	 * @since 09/10/2014
	 */
	public static boolean searchResults(String RowId)
	{
		String strfield = null;
		String[] strarrFields = null;
		String actualText, expectedText;
		try
		{
			Log4J.logp.info("------------- Started :: searchResults ---------------");
			driver = ExecutionSetup.getDriver();
			landingp_webe = LandingP_WebE.getInstance(driver);
			Map<String, String> rowTestData = null;

			JDBCMySql searchResults_TestData = new JDBCMySql();
			rowTestData = searchResults_TestData.getRowbyID("td_searchresults_reports", RowId);
			strfield = rowTestData.get("Field Name");
			strarrFields = strfield.split("\\@");

			List<WebElement> row = driver.findElements(By.xpath("//table[@id='reportsTable']/thead/tr/th"));

			int i = 0;
			for (WebElement rows : row)
			{
				WebElement getColumnName = rows.findElement(By.xpath("//thead/tr/th[" + (i + 1) + "]"));
				actualText = getColumnName.getText();
				Thread.sleep(1000);
				expectedText = strarrFields[i];
				Log4J.logp.info(expectedText + " ==  " + actualText);
				Assert.assertEquals(expectedText.trim(), actualText.trim());
				i++;
			}
			Log4J.logp.info("------------- Ended :: searchResults ---------------");
			return true;
		}
		catch (Exception e)
		{

			Log4J.logp.error("Problem Found In - searchResults");
			Assert.assertTrue(false, "Problem found in :: searchResults");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This script is to download PDF file in all report page
	 * 
	 * @author agupta
	 * @since 09/10/2014
	 */
	public static boolean download_PDF(String RowId)
	{
		String strfield = null;
		String[] strarrFields = null;
		try
		{
			Log4J.logp.info("-------- Started :: download_PDF -------------");
			driver = ExecutionSetup.getDriver();
			pdf_webe = PDF_WebE.getInstance(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			Map<String, String> rowTestData = null;

			JDBCMySql pdf_TestData = new JDBCMySql();
			rowTestData = pdf_TestData.getRowbyID("td_pdf_excel_reports", RowId);
			strfield = rowTestData.get("Field Name");
			strarrFields = strfield.split("\\@");
			if (strarrFields.length != 0)
			{
				for (int i = 0; i < strarrFields.length; i++)
				{
					WebElement element = driver.findElement(By.xpath("//label[contains(text(),'" + strarrFields[i] + "')][1]"));
					element.click();
				}
			}
			Thread.sleep(3000);
			pdf_webe.lnk_Download_PDF.click();
			Thread.sleep(3000);
			Log4J.logp.info("-------- Ended :: download_PDF -------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - PDf");
			Assert.assertTrue(false, "Problem found in :: download_PDF");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This script is to download Excel file in all reports page
	 * 
	 * @author agupta
	 * @since 09/10/2014
	 */
	public static boolean download_Excel(String RowId)
	{
		String strfield = null;
		String[] strarrFields = null;
		try
		{
			Log4J.logp.info("-------------- Started :: download_Excel ----------------");
			driver = ExecutionSetup.getDriver();
			excel_webe = Excel_WebE.getInstance(driver);
			landingp_webe = LandingP_WebE.getInstance(driver);
			Map<String, String> rowTestData = null;

			JDBCMySql pdf_TestData = new JDBCMySql();
			rowTestData = pdf_TestData.getRowbyID("td_pdf_excel_reports", RowId);
			strfield = rowTestData.get("Field Name");
			strarrFields = strfield.split("\\@");
			if (strarrFields.length != 0)
			{
				for (int i = 0; i < strarrFields.length; i++)
				{

					WebElement element = driver.findElement(By.xpath("(//label[contains(text(),'" + strarrFields[i] + "')])[2]"));
					element.click();
				}
			}
			excel_webe.lnk_Download.click();
			Thread.sleep(3000);
			Log4J.logp.info("-------------- Ended :: download_Excel ----------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem Found In - Excel");
			Assert.assertTrue(false, "Problem Found in :: download_Excel");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This script is to check that data is available or not for selected MRN number
	 * 
	 * @author agupta
	 * @since 14/10/2014
	 */
	public static boolean chk_Data_Report(String mrn)
	{
		try
		{
			Log4J.logp.info("----------- Started :: chk_Data_Report -------------");
			showHideColumn_webe = Show_Hide_Columns_WebE.getInstance(driver);
			WebElement table = showHideColumn_webe.tbl_reports;
			List<WebElement> row = table.findElements(By.tagName("tr"));
			int count = 0;
			for (int i = 0; i < row.size(); i++)
			{
				List<WebElement> ThirdColumn = table.findElements(By.xpath("//tr[" + i + "]/td[3]"));
				for (WebElement c : ThirdColumn)
				{
					if (mrn.equals(c.getText()))
					{
						count++;
						Log4J.logp.info("Case " + mrn + " is Displayed in Report and count = " + count);

					}

				}
			}
			if (count == 0)
			{
				Log4J.logp.info("Not found any case");
				Assert.assertTrue(false, "Not found any case");
			}
			Log4J.logp.info("----------- Ended :: chk_Data_Report -------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.info("Problem found In :: chk_Data_Report");
			Assert.assertTrue(false, "Problem found In :: chk_Data_Report");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This script is to check search criteria in all reports page
	 * 
	 * @author agupta
	 * @since 09/10/2014
	 */
	public static boolean search_Data(String RowId)
	{
		String dischargeDate = null;
		String fromDischargeDate = null;
		String toDischargeDate = null;
		String codingCompletedDate = null;
		String fromCodingCompletedDate = null;
		String toCodingCompletedDate = null;
		String payor = null;
		String coder = null;
		String physician = null;
		String clinicalIndicators = null;
		String reviewer = null;
		String serviceLine = null;
		String issueType = null;
		String codingSystem = null;
		String[] arrServiceLine = null;
		String[] arrissueType = null;
		String[] arrCoder = null;
		String[] arrPhysician = null;
		String[] arrClinicalIndicators = null;
		String[] arrReviewer = null;
		String[] arrCodingSystem = null;
		//String[] arrStage = null;
		try
		{
			Log4J.logp.info("-------------------- Started :: search_Data --------------------");
			driver = ExecutionSetup.getDriver();
			searchcriteria_webe = SearchCriteria_WebE.getInstance(driver);

			Map<String, String> rowTestData = null;

			JDBCMySql searchCriteria_TestData = new JDBCMySql();

			rowTestData = searchCriteria_TestData.getRowbyID("td_searchdata_reports", RowId);

			dischargeDate = rowTestData.get("DischargeDate");

			fromDischargeDate = rowTestData.get("FromDischargeDate");

			toDischargeDate = rowTestData.get("ToDischargeDate");

			codingCompletedDate = rowTestData.get("CodingCompletedDate");

			fromCodingCompletedDate = rowTestData.get("FromCodingCompletedDate");

			toCodingCompletedDate = rowTestData.get("ToCodingCompletedDate");

			payor = rowTestData.get("Payor");

			coder = rowTestData.get("Coder");

			physician = rowTestData.get("Physician");

			clinicalIndicators = rowTestData.get("ClinicalIndicator");

			reviewer = rowTestData.get("Reviewer");

			serviceLine = rowTestData.get("ServiceLine");

			issueType = rowTestData.get("IssueType");

			codingSystem = rowTestData.get("CodingSystem");

			if (serviceLine != null)
			{
				arrServiceLine = serviceLine.split("\\$");
				if (arrServiceLine.length != 0)
				{

					for (int i = 0; i < arrServiceLine.length; i++)
					{

						WebElement webElement = driver.findElement(By.xpath("//div[@id='serviceLine']/div[1]/label[contains(text(),'" + arrServiceLine[i] + "')]"));
						webElement.click();
					}

				}

			}
			if (issueType != null)
			{
				arrissueType = issueType.split("\\$");
				if (arrissueType.length != 0)
				{
					for (int i = 0; i < arrissueType.length; i++)
					{
						WebElement webElement = driver.findElement(By.xpath("//div[@id='issueType']/label[contains(text(),'" + arrissueType[i] + "')]"));
						webElement.click();
					}
				}
			}
			if (codingSystem != null)
			{
				arrCodingSystem = codingSystem.split("\\$");
				if (arrCodingSystem.length != 0)
				{
					for (int i = 0; i < arrCodingSystem.length; i++)
					{
						WebElement webElement = driver.findElement(By.xpath("//div[@id='codingSystem']/label[contains(text(),'" + arrCodingSystem[i] + "')]"));
						webElement.click();
					}
				}
			}

			if (dischargeDate != null)
			{
				Select dropdown = new Select(searchcriteria_webe.lst_dischargeDate);
				dropdown.selectByVisibleText(dischargeDate);
				Thread.sleep(2000);

			}
			if (fromDischargeDate != null)
			{

				Common_Lib.fillDate(searchcriteria_webe.txt_FromDischargeDate, fromDischargeDate);
			}
			if (toDischargeDate != null)
			{
				Common_Lib.fillDate(searchcriteria_webe.txt_ToDischargeDate, toDischargeDate);
			}
			if (codingCompletedDate != null)
			{
				Select dropdown = new Select(searchcriteria_webe.lst_codingCompleteDate);
				dropdown.selectByVisibleText(codingCompletedDate);
			}
			if (fromCodingCompletedDate != null)
			{
				Common_Lib.fillDate(searchcriteria_webe.txt_from_codingCompleted, fromCodingCompletedDate);

			}
			if (toCodingCompletedDate != null)
			{
				Common_Lib.fillDate(searchcriteria_webe.txt_to_codingCompleted, toCodingCompletedDate);
			}
			if (payor != null)
			{
				Select dropdown = new Select(searchcriteria_webe.lst_payor);
				dropdown.selectByVisibleText(payor);
				Thread.sleep(2000);
			}
			if (coder != null)
			{
				arrCoder = coder.split("\\$");
				if (arrCoder.length != 0)
				{
					WebElement element = driver.findElement(By.xpath("//ul[@class='token-input-list-facebook coder']"));
					element.click();
					searchcriteria_webe.txt_Coder.clear();
					for (int i = 0; i < arrCoder.length; i++)
					{
						searchcriteria_webe.txt_Coder.sendKeys(arrCoder[i]);
						Thread.sleep(2000);
						searchcriteria_webe.txt_Coder.sendKeys(Keys.ENTER);
					}

				}
			}
			if (physician != null)
			{
				arrPhysician = physician.split("\\$");
				if (arrPhysician.length != 0)
				{
					WebElement element = driver.findElement(By.xpath("//ul[@class='token-input-list-facebook physician']"));
					element.click();
					searchcriteria_webe.txt_Physician1.clear();
					for (int i = 0; i < arrPhysician.length; i++)
					{
						searchcriteria_webe.txt_Physician1.sendKeys(arrPhysician[i]);
						Thread.sleep(2000);
						searchcriteria_webe.txt_Physician1.sendKeys(Keys.ENTER);
					}

				}
			}
			if (clinicalIndicators != null)
			{
				arrClinicalIndicators = clinicalIndicators.split("\\$");
				if (arrClinicalIndicators.length != 0)
				{
					WebElement element = driver.findElement(By.xpath("//ul[@class='token-input-list-facebook clinicalIndicator']"));
					element.click();
					searchcriteria_webe.txt_clinicalIndicator.clear();
					for (int i = 0; i < arrClinicalIndicators.length; i++)
					{
						searchcriteria_webe.txt_clinicalIndicator.sendKeys(arrClinicalIndicators[i]);
						Thread.sleep(2000);
						searchcriteria_webe.txt_clinicalIndicator.sendKeys(Keys.ENTER);
					}

				}

			}
			if (reviewer != null)
			{
				arrReviewer = reviewer.split("\\$");
				if (arrReviewer.length != 0)
				{
					WebElement element = driver.findElement(By.xpath("//ul[@class='token-input-list-facebook reviewer']"));
					element.click();
					searchcriteria_webe.txt_Reviewer.clear();
					for (int i = 0; i < arrReviewer.length; i++)
					{
						searchcriteria_webe.txt_Reviewer.sendKeys(arrReviewer[i]);
						Thread.sleep(2000);
						searchcriteria_webe.txt_Reviewer.sendKeys(Keys.ENTER);
					}

				}

			}
			Thread.sleep(3000);
			searchcriteria_webe.btn_Search.click();
			Log4J.logp.info("-------------------- Ended :: search_Data --------------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.info("Problem found in :: search_Data");
			Assert.assertTrue(false, "Problem found in :: search_Data");
			e.printStackTrace();
			return false;
		}

	}

}
