package com.ezdi.library;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ezdi.framework.common.JDBCMySql;
import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.webelements.SearchCriteria_WebE;

public class SearchCriteria_Lib
{

	public static WebDriver				driver;
	public static SearchCriteria_WebE	searchCriteria_webe;
	public static WebDriverWait			wait;

	/**
	 * This method is for search case with particular data
	 * 
	 * @author skhalasi for standardisation
	 * */

	public static boolean search_Case(String rowId)
	{

		String patientName = null;
		String fromAdmissionDate = null;
		String toAdmissionDate = null;
		String physicianName = null;
		String fromBilledDate = null;
		String toBilledDate = null;
		String mrn;
		String fromDischargeDate = null;
		String toDischargeDate = null;
		String payor = null;
		String hospital = null;
		String accountNumber = null;
		String dnfc = null;
		String DNFCValue = null;
		String location = null;
		String saveThisSearch = null;
		String status = null;
		String stage = null;
		String serviceLine = null;
		String[] arrServiceLine = null;
		String[] arrStatus = null;
		String[] arrStage = null;

		try
		{
			Log4J.logp.info("----------------  Started - search_Case ----------------");
			driver = ExecutionSetup.getDriver();
			Log4J.logp.info("In search_Case");
			wait = new WebDriverWait(driver, 20);
			driver = ExecutionSetup.getDriver();
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);

			//clear all search first

			clearSearch();

			//store all data in list and then take all data in different variable
			Map<String, String> rowTestData = null;

			JDBCMySql searchCriteria_TestData = new JDBCMySql();

			rowTestData = searchCriteria_TestData.getRowbyID("td_searchcase", rowId);

			stage = rowTestData.get("stage");

			status = rowTestData.get("status");

			serviceLine = rowTestData.get("service_line");

			patientName = rowTestData.get("patient_name");

			fromAdmissionDate = rowTestData.get("from_admissionDate");

			toAdmissionDate = rowTestData.get("to_AdmissionDate");

			physicianName = rowTestData.get("physician_name");

			fromBilledDate = rowTestData.get("from_billeddate");

			toBilledDate = rowTestData.get("to_billeddate");

			mrn = rowTestData.get("MRN");

			fromDischargeDate = rowTestData.get("from_dischargeDate");

			toDischargeDate = rowTestData.get("to_dischargeDate");

			payor = rowTestData.get("payor");

			hospital = rowTestData.get("hospital");

			accountNumber = rowTestData.get("account_number");

			dnfc = rowTestData.get("dnfc");

			DNFCValue = rowTestData.get("dnfc_value");

			location = rowTestData.get("location");

			saveThisSearch = rowTestData.get("save_thissearch");

			//to get data one by one with $ seperator
			if (status != null)
			{
				arrStatus = status.split("\\$");
				if (arrStatus.length != 0)
				{

					// for (String eachStatus : arrStatus) {

					for (int i = 0; i < arrStatus.length; i++)
					{
						WebElement webElement = driver.findElement(By.xpath("//div[@id='statusList']/label[contains(text(), '" + arrStatus[i] + "')]"));
						webElement.click();
					}

				}

			}
			if (stage != null)
			{
				arrStage = stage.split("\\$");
				if (arrStage.length != 0)
				{
					for (int i = 0; i < arrStage.length; i++)
					{
						WebElement webElement = driver.findElement(By.xpath("//div[@id='stageList']/label[text()='" + arrStage[i] + "']"));
						webElement.click();
					}
				}
			}
			if (serviceLine != null)
			{
				arrServiceLine = serviceLine.split("\\$");
				if (arrServiceLine.length != 0)
				{

					// for (String eachStatus : arrStatus) {

					for (int i = 0; i < arrServiceLine.length; i++)
					{

						WebElement webElement = driver.findElement(By.xpath("//div[@id='serviceList']/label[contains(text(), '" + arrServiceLine[i] + "')]"));
						webElement.click();
					}

				}
			}

			//fill the data with are not null in database
			if (patientName != null)
			{
				searchCriteria_webe.txt_PatientName.clear();
				searchCriteria_webe.txt_PatientName.sendKeys(patientName);
			}
			if (fromAdmissionDate != null && toAdmissionDate != null)
			{
				Common_Lib.fillDate(searchCriteria_webe.txt_FromAdmissionDate, fromAdmissionDate);
				Common_Lib.fillDate(searchCriteria_webe.txt_ToAdmissionDate, toAdmissionDate);
			}
			if (physicianName != null)
			{
				searchCriteria_webe.txt_Physician.clear();
				searchCriteria_webe.txt_Physician.sendKeys(physicianName);
			}
			if (fromBilledDate != null && toBilledDate != null)
			{
				Common_Lib.fillDate(searchCriteria_webe.txt_FromBilledDate, fromBilledDate);
				Common_Lib.fillDate(searchCriteria_webe.txt_ToBilledDate, toBilledDate);
			}

			if (mrn != null)
			{
				searchCriteria_webe.txt_MRN.clear();
				searchCriteria_webe.txt_MRN.sendKeys(mrn);
			}
			if (fromDischargeDate != null && toDischargeDate != null)
			{
				Common_Lib.fillDate(searchCriteria_webe.txt_FromDischargeDate, fromDischargeDate);
				Common_Lib.fillDate(searchCriteria_webe.txt_ToDischargeDate, toDischargeDate);

			}
			if (payor != null)
			{
				searchCriteria_webe.txt_Payor.clear();
				searchCriteria_webe.txt_Payor.sendKeys(payor);
			}
			if (hospital != null)
			{
				searchCriteria_webe.txt_Hospital.clear();
				Thread.sleep(2000);
				searchCriteria_webe.txt_Hospital.sendKeys(hospital);
				Thread.sleep(2000);
				searchCriteria_webe.txt_Location.sendKeys(Keys.ENTER);
			}
			if (accountNumber != null)
			{
				searchCriteria_webe.txt_AccountNumber.clear();
				searchCriteria_webe.txt_AccountNumber.sendKeys(accountNumber);
			}
			if (dnfc != null)
			{
				Select dropdown = new Select(searchCriteria_webe.lst_DNFCDays);
				dropdown.selectByVisibleText(dnfc);
				Thread.sleep(2000);
			}
			if (DNFCValue != null)
			{
				searchCriteria_webe.txt_DNFCDaya_Value.clear();
				searchCriteria_webe.txt_DNFCDaya_Value.sendKeys(DNFCValue);
			}
			if (location != null)
			{
				searchCriteria_webe.txt_Location.clear();
				Thread.sleep(2000);
				searchCriteria_webe.txt_Location.sendKeys(location);
				Thread.sleep(2000);
				searchCriteria_webe.txt_Location.sendKeys(Keys.ENTER);
			}
			if (saveThisSearch != null)
			{
				searchCriteria_webe.txt_SaveThisSearch.clear();
				searchCriteria_webe.txt_SaveThisSearch.sendKeys(saveThisSearch);
			}
			//Thread.sleep(2000);
			Log4J.logp.info("In search_case all data inserted");
			wait.until(ExpectedConditions.elementToBeClickable(searchCriteria_webe.btn_Search));
			searchCriteria_webe.btn_Search.click();
			Log4J.logp.info("Case is searched with rowId=" + rowId);
			Log4J.logp.info("----------------  Ending - search_Case ----------------");
			return true;

		}
		catch (Exception e)
		{
			Log4J.logp.info("---------------- Problem Found In search_Case ----------------");
			e.printStackTrace();
			return false;
		}

	}

	/** this method is for clear search */
	public static boolean clearSearch()
	{
		try
		{
			Log4J.logp.info("---------------- In clearSearch ----------------");
			driver = ExecutionSetup.getDriver();
			searchCriteria_webe = SearchCriteria_WebE.getInstance(driver);
			Thread.sleep(1000);
			searchCriteria_webe.lnk_SearchCreteria.click();
			Common_Lib.waitForObject(searchCriteria_webe.lnk_SearchCreteria, "visibility", 5);
			Thread.sleep(1500);
			//to store list of selected criteria
			List<WebElement> statusList = driver.findElements(By.xpath("//*[@id='statusList']/label[not(@class='label dis-link')]"));
			for (WebElement webElement : statusList)
			{
				//to clear all selected search
				webElement.click();
			}
			Log4J.logp.info("---------------- Ending clearSearch  ----------------");
			return true;

		}
		catch (Exception e)
		{
			Log4J.logp.info("---------------- Problem Found in clearSearch ----------------");
			e.printStackTrace();
			return false;

		}

	}

}
