package com.ezdi.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemographicPnl_WebE
{

	/** to click for open on patient demographic panel */
	@FindBy(id = "accordionLeft")
	public WebElement			lnk_DemoPnlExpand;

	/** to see patient name on the panel */
	@FindBy(id = "patientId")
	public WebElement			lbl_PatientNameOnHeader;

	/** to see patient name in panel */
	@FindBy(xpath = "//*[@id='column-3']/div[1]/p[1]/span")
	public WebElement			lbl_PatientName;

	/** to see MRN number in panel */
	@FindBy(xpath = "//*[@id='column-3']/div[1]/p[2]/span")
	public WebElement			lbl_MRN;

	/** to see age and sex in panel */
	@FindBy(xpath = "//*[@id='column-3']/div[1]/p[3]/span")
	public WebElement			lbl_Age_Sex;

	/** to see Account Number in panel */
	@FindBy(xpath = "//*[@id='column-3']/div[1]/p[4]/span")
	public WebElement			lbl_AccountNo;

	/** to see Account Type in panel */
	@FindBy(xpath = "//*[@id='column-3']/div[1]/p[5]/span")
	public WebElement			lbl_AccountType;

	/** to see admission date in panel */
	@FindBy(xpath = "//*[@id='column-3']/div[2]/p[1]/span")
	public WebElement			lbl_AdmissionDate;

	/** to see discharge date in panel */
	@FindBy(xpath = "//*[@id='column-3']/div[2]/p[2]/span")
	public WebElement			lbl_Dischargedate;

	/** to see LOS in panel */
	@FindBy(xpath = "//*[@id='column-3']/div[2]/p[3]/span")
	public WebElement			lbl_LOS;

	/** to see patient Status in panel */
	//@FindBy(xpath = "//*[@id='column-3']/div[2]/p[4]/span")
	@FindBy(id = "patientStatus")
	public WebElement			lbl_PatientStatus;

	/** to see physician name in panel */
	@FindBy(xpath = "//*[@id='column-3']/div[3]/p[1]/span")
	public WebElement			lbl_Physician;

	/** to see location in panel */
	@FindBy(xpath = "//*[@id='column-3']/div[3]/p[2]/span")
	public WebElement			lbl_Location;

	/** to see Hospital in panel */
	@FindBy(xpath = "//*[@id='column-3']/div[3]/p[3]/span")
	public WebElement			lbl_Hospital;

	/** to see payor in panel */
	@FindBy(xpath = "//*[@id='column-3']/div[3]/p[4]/span")
	public WebElement			lbl_Payor;

	/** to select discharge disposition in panel */
	@FindBy(id = "discharge-csb")
	public WebElement			lst_DischargeDisposition;

	/** To get mrn and accountno from patient demographic panel */
	@FindBy(css = "h3[id='accordionLeft']")
	public WebElement			lbl_casedetails;

	/** to enter text in comments of other discharge disposition */
	@FindBy(id = "dischargeComment")
	public WebElement			txt_commentDemographic;

	/** to click on save button after add comment in demographic panel */
	@FindBy(id = "btnSave")
	public WebElement			btn_saveDemographic;

	static DemographicPnl_WebE	INSTANCE	= null;

	public static DemographicPnl_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, DemographicPnl_WebE.class);
		}
		return INSTANCE;
	}

}
