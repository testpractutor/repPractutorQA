package com.ezdi.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchCriteria_WebE
{

	/** to click on search criteria link */
	@FindBy(id = "searchPanelClk")
	public WebElement			lnk_SearchCreteria;

	/** to click on saved search in search criteria */
	@FindBy(css = "a[class='ffSelectButton'] > span")
	public WebElement			lnk_SavedSearch;

	/** to select first select keyword in saved search */
	@FindBy(css = "li[class='selected'] > h6 > span")
	public WebElement			lst_SavedSearch_FirstSelection;

	/** to click on clear search for clear saved search */
	@FindBy(id = "clearit")
	public WebElement			lnk_ClearSearch;

	/** to click on unassigned case in status bar */
	@FindBy(id = "status_toggle1")
	public WebElement			btn_UnAssigned;

	/** to click on in queue case in status bar */
	@FindBy(id = "status_toggle2")
	public WebElement			btn_InQueue;

	/** to click on In progress in status bar */
	@FindBy(id = "status_toggle3")
	public WebElement			btn_InProgress;

	/** to click on On Hold case in status bar */
	@FindBy(id = "status_toggle4")
	public WebElement			btn_OnHold;

	/** to click on Pending case in status bar */
	@FindBy(id = "status_toggle5")
	public WebElement			btn_Pending;

	/** to click on Completed case in status bar */
	@FindBy(id = "status_toggle6")
	public WebElement			btn_Completed;

	/** to click on Billed case in status bar */
	@FindBy(id = "status_toggle7")
	public WebElement			btn_Billed;

	/** to click on Code stage */
	@FindBy(id = "stage_toggle2")
	public WebElement			btn_Code;

	/** to click on Review stage */
	@FindBy(id = "stage_toggle3")
	public WebElement			btn_Review;

	/** to click on CDI stage */
	@FindBy(id = "stage_toggle4")
	public WebElement			btn_CDI;

	/** to click Inpatient service line */
	@FindBy(id = "service_toggle1")
	public WebElement			btn_Inpatient;

	/** to click Outpatient service line */
	@FindBy(id = "service_toggle2")
	public WebElement			btn_Outpatient;

	/** to click MED service line */
	@FindBy(id = "service_toggle3")
	public WebElement			btn_MED;

	/** to click SUR service line */
	@FindBy(id = "service_toggle4")
	public WebElement			btn_SUR;

	/** to click URO service line */
	@FindBy(id = "service_toggle5")
	public WebElement			btn_URO;

	/** to click PUL service line */
	@FindBy(id = "service_toggle6")
	public WebElement			btn_PUL;

	/** to click CAR service line */
	@FindBy(id = "service_toggle7")
	public WebElement			btn_CAR;

	/** to enter patient name in search criteria */
	@FindBy(id = "patient_name")
	public WebElement			txt_PatientName;

	/** to enter MRN number in search criteria */
	@FindBy(id = "mrn")
	public WebElement			txt_MRN;

	/** to enter Account number in search criteria */
	@FindBy(id = "acc_no")
	public WebElement			txt_AccountNumber;

	/** to enter from admission date in search criteria */
	@FindBy(id = "from_admission")
	public WebElement			txt_FromAdmissionDate;

	/** to enter to admission date in search criteria */
	@FindBy(id = "to_admission")
	public WebElement			txt_ToAdmissionDate;

	/** to enter from discharge date in search criteria */
	@FindBy(id = "from_dateofdischarge")
	public WebElement			txt_FromDischargeDate;

	/** to enter to discharge date in search criteria */
	@FindBy(id = "to_dateofdischarge")
	public WebElement			txt_ToDischargeDate;

	/** to enter physician in search criteria */
	@FindBy(id = "physician-list")
	public WebElement			txt_Physician;

	/** to enter payor in search criteria */
	@FindBy(id = "payer-list")
	public WebElement			txt_Payor;

	/** to enter from billed date in search criteria */
	@FindBy(id = "from_billed")
	public WebElement			txt_FromBilledDate;

	/** to enter to billed date in search criteria */
	@FindBy(id = "to_billed")
	public WebElement			txt_ToBilledDate;

	/** to enter hospital in search criteria */
	@FindBy(id = "hospital-list")
	public WebElement			txt_Hospital;

	/** to select DNFC days > , < or = in search criteria */
	@FindBy(id = "dnfcOp")
	public WebElement			lst_DNFCDays;

	/** to enter DNFC value with DNFC drop down in search criteria */
	@FindBy(id = "dnfcVal")
	public WebElement			txt_DNFCDaya_Value;

	/** to enter location in search criteria */
	@FindBy(css = "li[class='search-field'] > input")
	public WebElement			txt_Location;

	/** to enter keyword to save the search in search criteria */
	@FindBy(id = "save_search")
	public WebElement			txt_SaveThisSearch;

	/** to select values from Discharge Date dropdown menu */
	@FindBy(id = "dischargeDate")
	public WebElement			lst_dischargeDate;

	/** to select values from Coding Completed Date dropdown menu */
	@FindBy(id = "codingCompleteDate")
	public WebElement			lst_codingCompleteDate;

	/** to enter from date in Coding Completed date search criteria */
	@FindBy(id = "from_codingCompleted")
	public WebElement			txt_from_codingCompleted;

	/** to enter to date in Coding Completed date search criteria */
	@FindBy(id = "to_codingCompleted")
	public WebElement			txt_to_codingCompleted;

	/** to select values from Payor dropdown menu */
	@FindBy(id = "payor")
	public WebElement			lst_payor;

	/** to enter coder name in Coder textbox */
	@FindBy(id = "token-input-coder")
	public WebElement			txt_Coder;

	/** to enter physician name in Physician textbox */
	@FindBy(id = "token-input-physician")
	public WebElement			txt_Physician1;

	/** to enter Clinical Indicator in textbox */
	@FindBy(id = "token-input-clinicalIndicator")
	public WebElement			txt_clinicalIndicator;

	/** to enter reviewer name in Reviewer textbox */
	@FindBy(id = "token-input-reviewer")
	public WebElement			txt_Reviewer;

	/** to select Physician Query Issue type */
	@FindBy(id = "issue_toggle1")
	public WebElement			btn_Physician_Query;

	/** to select Discussion issue type */
	@FindBy(id = "issue_toggle2")
	public WebElement			btn_Discussion;

	/** to select Missing Document issue type */
	@FindBy(id = "issue_toggle3")
	public WebElement			btn_Missing_Document;

	/** to select ICD-9 Coding System */
	@FindBy(id = "system_toggle0")
	public WebElement			btn_ICD_9;

	/** to select ICD-10 Coding System */
	@FindBy(id = "system_toggle1")
	public WebElement			btn_ICD_10;

	/** to click on search button in search criteria */
	@FindBy(id = "search")
	public WebElement			btn_Search;

	/** to click on date picker icon of from Discharge Date */
	@FindBy(xpath = "//div[@id='searchTab']/div[1]/div[2]/div[1]/img")
	public WebElement			icon_fromdate;

	/* to click on delete icon of Saved Searches dropdown menu */
	@FindBy(css = ".srch-remove")
	public WebElement			delete_icon;

	@FindBy(css = ".srch-undo")
	public WebElement			lnk_undo;
	static SearchCriteria_WebE	INSTANCE	= null;

	public static SearchCriteria_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, SearchCriteria_WebE.class);
		}
		return INSTANCE;
	}

}
