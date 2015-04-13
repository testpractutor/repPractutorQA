package com.ezdi.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PDF_WebE
{

	/** to click on PDF link option */
	@FindBy(id = "pdf")
	public WebElement	lnk_PDF;

	/** to clear all checkbox in PDF menu */
	@FindBy(id = "pdfclear")
	public WebElement	lnk_Clear_All;

	/** to download PDF File */
	@FindBy(id = "pdfok")
	public WebElement	lnk_Download_PDF;

	/** to check on Account Number Checkbox */
	@FindBy(xpath = "(//label[text()='Account Number'])[1]")
	public WebElement	chk_Account_Number;

	/** to check on Patient Name checkbox */
	@FindBy(xpath = "(//label[text()='Patient Name'])[1]")
	public WebElement	chk_Patient_Name;

	/** to check on MRN Number */
	@FindBy(xpath = "(//label[text()='MRN'])[1]")
	public WebElement	chk_MRN;

	/** to check on Admit Date */
	@FindBy(xpath = "(//label[text()='Admit Date'])[1]")
	public WebElement	chk_Admit_Date;

	/** to check on Discharge Date */
	@FindBy(xpath = "(//label[text()='Discharge Date'])[1]")
	public WebElement	chk_Discharge_Date;

	/** to check on Coder */
	@FindBy(xpath = "(//label[text()='Coder'])[1]")
	public WebElement	chk_Coder;

	/** to check on Coded By */
	@FindBy(xpath = "(//label[text()='Coded by'])[1]")
	public WebElement	chk_Coded_By;

	/** to check on Code Completed Date */
	@FindBy(xpath = "(//label[text()='Code Completed Date'])[1]]")
	public WebElement	chk_Code_Completed_Date;

	/** to check on Service Line */
	@FindBy(xpath = "(//label[text()='Service Line'])[1]")
	public WebElement	chk_Service_Line;

	/** to check on Attending Physician */
	@FindBy(xpath = "(//label[text()='Attending Physician'])[1]")
	public WebElement	chk_Attending_Physician;

	/** to check in Final DRG/APC */
	@FindBy(xpath = "(//label[text()='Final DRG/APC'])[1]")
	public WebElement	chk_Final_DRG_APC;

	/** to check on DRG Weight */
	@FindBy(xpath = "(//label[text()='DRG Weight'])[1]")
	public WebElement	chk_DRG_Weight;

	/** to check on Time for Case */
	@FindBy(xpath = "(//label[text()='Time for case (mins)'])[1]")
	public WebElement	chk_Time_For_Case;

	/** to check on Amount */
	@FindBy(xpath = "(//label[text()='Amount'])[1]")
	public WebElement	chk_Amount;

	/** to check on Payor */
	@FindBy(xpath = "(//label[text()='Payor'])[1]")
	public WebElement	chk_Payor;

	/** to check on Issues */
	@FindBy(xpath = "(//label[text()='Issues'])[1]")
	public WebElement	chk_Issues;

	/** to check on Issues Type */
	@FindBy(xpath = "(//label[text()='Issue Type'])[1]")
	public WebElement	chk_Issue_Type;

	/** to check on DNFC Days */
	@FindBy(xpath = "(//label[text()='DNFC Days'])[1]")
	public WebElement	chk_DNFC_Days;

	/** to check on Coding Completed Date */
	@FindBy(xpath = "(//label[text()='Coding Completed Date'])[1]")
	public WebElement	chk_Coding_Completed_Date;

	/** to check on Coder DRG/APC */
	@FindBy(xpath = "(//label[text()='Coder DRG/APC'])[1]")
	public WebElement	chk_Coder_DRG_APC;

	/** to check on Amount Difference */
	@FindBy(xpath = "//label[text()='Amount Difference ($)'])[1]")
	public WebElement	chk_Amount_Difference;

	/** to check on checkboxes in CDI Reports */
	@FindBy(id = "pdfnavmenu")
	public WebElement	chk_CDI_Reports;

	/** to check on Physician */
	@FindBy(xpath = "(//label[text()='Physician'])[1]")
	public WebElement	chk_Physician;

	/** to check on Clinical Indicators */
	@FindBy(xpath = "(//label[text()='Clinical Indicator'])[1]")
	public WebElement	chk_Clinical_Indicator;

	/** to check on Coder Name */
	@FindBy(xpath = "(//label[text()='Coder Name'])[1]")
	public WebElement	chk_Coder_Name;

	/** to check on Coder Amount */
	@FindBy(xpath = "(//label[text()='Coder Amount ($)'])[1]")
	public WebElement	chk_Coder_Amount;

	/** to check on Final Amount */
	@FindBy(xpath = "(//label[text()='Final Amount ($)'])[1]")
	public WebElement	chk_Final_Amount;

	/** to check on No. of cases Completed Coding */
	@FindBy(xpath = "(//label[text()='No. of cases completed coding'])[1]")
	public WebElement	chk_Cases_Completed_Coding;

	/** to check on Average Accuracy */
	@FindBy(xpath = "(//label[text()='Average Accuracy (%)'])[1]")
	public WebElement	chk_Average_Accuracy;

	/** to check on Average Time */
	@FindBy(xpath = "(//label[text()='Average Time'])[1]")
	public WebElement	chk_Average_Time;

	/** to check on No. of Codes Suggested */
	@FindBy(xpath = "(//label[text()='No. of Codes Suggested'])[1]")
	public WebElement	chk_No_of_Codes_Suggested;

	/** to check on No. of Codes Accepted */
	@FindBy(xpath = "(//label[text()='No. of Codes Accepted'])[1]")
	public WebElement	chk_No_of_Codes_Accepted;

	/** to check on No. of Codes Rejected */
	@FindBy(xpath = "(//label[text()='No. of Codes Rejected'])[1]")
	public WebElement	chk_No_of_Codes_Rejected;

	/** to check on No. of Codes Modified */
	@FindBy(xpath = "(//label[text()='No. of Codes Modified'])[1]")
	public WebElement	chk_No_of_Codes_Modified;

	/** to check on No. of Codes Added */
	@FindBy(xpath = "(//label[text()='No. of Codes Added'])[1]")
	public WebElement	chk_No_of_Codes_Added;

	/** to check on Recall */
	@FindBy(xpath = "(//label[text()='Recall'])[1]")
	public WebElement	chk_Recall;

	/** to check on Precision */
	@FindBy(xpath = "(//label[text()='Precision'])[1]")
	public WebElement	chk_Precision;

	/** to check on Engine F Score */
	@FindBy(xpath = "(//label[text()='Engine F Score'])[1]")
	public WebElement	chk_Engine_F_Score;

	/** to check on Reviewer Name */
	@FindBy(xpath = "(//label[text()='Reviewer Name'])[1]")
	public WebElement	chk_Reviewer_Name;

	/** to check on No. of Cases Completed Review */
	@FindBy(xpath = "(//label[text()='No. of Cases Completed Review'])[1]")
	public WebElement	chk_Completed_Review;

	/** to check on No. of Queries */
	@FindBy(xpath = "(//label[text()='No. of Queries'])[1]")
	public WebElement	chk_No_of_Queries;

	/** to check on Difference in Amount */
	@FindBy(xpath = "(//label[text()='Difference in Amount ($)'])[1]")
	public WebElement	chk_Difference_Amount;

	/** to check on Reviewer By */
	@FindBy(xpath = "(//label[text()='Reviewed by'])[1]")
	public WebElement	chk_Reviewed_By;

	/** to check on Total Codes */
	@FindBy(xpath = "(//label[text()='Total Codes'])[1]")
	public WebElement	chk_Total_Codes;

	/** to check on Codes With Error */
	@FindBy(xpath = "(//label[text()='Codes with Error'])[1]")
	public WebElement	chk_Codes_With_Error;

	/** to check on Coder Accuracy */
	@FindBy(xpath = "(//label[text()='Coder Accuracy (%)'])[1]")
	public WebElement	chk_Coder_Accuarcy;

	/** to check on Reviewer DRG/APC */
	@FindBy(xpath = "(//label[text()='Reviewer DRG/APC'])[1]")
	public WebElement	chk_Reviewer_DRG_APC;

	/** to check on Reviewer Amount */
	@FindBy(xpath = "(//label[text()='Reviewer Amount ($)'])[1]")
	public WebElement	chk_Reviewer_Amount;

	static PDF_WebE		INSTANCE	= null;

	public static PDF_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, PDF_WebE.class);
		}
		return INSTANCE;
	}

}
