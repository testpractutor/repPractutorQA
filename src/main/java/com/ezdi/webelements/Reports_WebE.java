package com.ezdi.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Reports_WebE
{

	/** To click on All Reports tab for Supervisor user role */
	@FindBy(linkText = "All Reports")
	public WebElement	lnk_All_Reports;

	/** To click on Coding Completed Report link */
	@FindBy(linkText = "Coding Completed")
	public WebElement	lnk_Coding_Completed;

	/** To click on Pending Cases Report link */
	@FindBy(linkText = "Pending Cases")
	public WebElement	lnk_Pending_Cases;

	/** To click on Coding Quality Report link */
	@FindBy(linkText = "Coding Quality")
	public WebElement	lnk_Coding_Quality;

	/** To click on CDI Impact on Query by Physician */
	@FindBy(linkText = "CDI Impact on Query by Physician")
	public WebElement	lnk_CDI_Impact_on_Query_by_Physician;

	/** To click on CDI Impact on Query by CI */
	@FindBy(linkText = "CDI Impact on Query by CI")
	public WebElement	lnk_CDI_Impact_on_Query_by_CI;

	/** To click on Coder Performance & Accuracy Report link */
	@FindBy(linkText = "Coder Performance and Accuracy")
	public WebElement	lnk_Coder_Performance_and_Accuracy;

	/** To click on Engine Performance Report link */
	@FindBy(linkText = "Engine Performance")
	public WebElement	lnk_Engine_Performance;

	/** To Click on Reviewer Completed Cases Report link */
	@FindBy(linkText = "Reviewer Completed Cases")
	public WebElement	lnk_Reviewer_Completed_Cases;

	/** To click on Coding Reports tab for Coder User role */
	@FindBy(linkText = "Coding Reports")
	public WebElement	lnk_Coding_Reports;

	/** To click on MY Productivity Report link for coder */
	@FindBy(linkText = "My Productivity")
	public WebElement	lnk_My_Productivity;

	/** To click on Reviewer Report tab for Reviewer user role */
	@FindBy(linkText = "Reviewer Reports")
	public WebElement	lnk_Reviewer_Reports;

	/** To click on CDI Reports tab for CDI User role */
	@FindBy(linkText = "CDI Reports")
	public WebElement	lnk_CDI_Reports;

	/** To check Last Month is Disable or not in Coding Completed Date */
	@FindBy(css = "select[id='codingCompleteDate']>option[disabled='disabled']")
	public WebElement	dis_LastMonth;

	static Reports_WebE	INSTANCE	= null;

	public static Reports_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, Reports_WebE.class);
		}
		return INSTANCE;
	}
}
