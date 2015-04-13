package com.ezdi.webelements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingP_WebE
{

	/** to click on admin setting image on landing page */
	@FindBy(id = "adminHeaderLink")
	public WebElement		imgAdmin_Setting;

	/** to click on user name label on landing page */
	@FindBy(css = "#userMenu > h4")
	public WebElement		lbl_UserName;

	/** to click on profile link of user */
	@FindBy(id = "profile")
	public WebElement		lnk_Profile;

	/** to click on logout link */
	@FindBy(id = "logout")
	public WebElement		lnk_Logout;

	/** to click on inbox images */
	@FindBy(id = "mcHeaderLink")
	public WebElement		lnk_MessageHeader;

	/** to see unread message count */
	@FindBy(id = "headerUnReadMessageCount")
	public WebElement		lnk_UnReadMessageCount;

	/** to click on Cases on header */
	@FindBy(id = "casesHeaderLink")
	public WebElement		lnk_Cases;

	/** to click on reports on header */
	@FindBy(id = "reportsHeaderLink")
	public WebElement		lnk_Reports;

	/** to click on All user role in header */
	@FindBy(linkText = "All")
	public WebElement		lnk_All;

	/** to click on Coding role in header */
	//alternate xpath="//*[text()='Coding']"
	@FindBy(linkText = "Coding")
	public WebElement		lnk_Coding;

	/** to click on CDI role in header */
	@FindBy(linkText = "CDI")
	public WebElement		lnk_CDI;

	/** to click on reviewer role in header */
	@FindBy(linkText = "Review")
	public WebElement		lnk_Review;

	/** to click on assign link on landing page */
	@FindBy(id = "assign")
	public WebElement		lnk_Assign;

	/** to click on refresh for refresh the table */
	@FindBy(id = "refreshTables")
	public WebElement		lnk_Refresh;

	/** to see table on landing page after search */
	@FindBy(id = "searchResults")
	public WebElement		tbl_SearchResult;

	/** to click on view case in read only mode in landing page */
	//@FindBy(xpath = "//span[text()='DeAnne Bloomquist, Coding, 08/27/2014, On Hold']")
	//	@FindBy(partialLinkText = "On Hold")
	@FindBy(xpath = "//label[text()='Version 0']/../span")
	public WebElement		lnk_FreshViewCase;

	/** to click on view case in read only mode in landing page for version1 */

	@FindBy(xpath = "//label[text()='Version 1']/../span")
	public WebElement		lnk_ViewCaseVersion1;

	/** to click on view case in read only mode in landing page for version2 */

	@FindBy(xpath = "//label[text()='Version 2']/../span")
	public WebElement		lnk_ViewCaseVersion2;

	/** to click on view case in read only mode in landing page for version3 */

	@FindBy(xpath = "//label[text()='Version 3']/../span")
	public WebElement		lnk_ViewCaseVersion3;

	/** To click on expanad on Coders Landing page */

	@FindBy(className = "expand-td center")
	public WebElement		expand_Case;

	/** to click on version form coder's landing page */

	@FindBy(xpath = "//*[@id='searchResults']/tbody/tr[2]/td/div/div[1]/p/span")
	public WebElement		txt_VersionCoder;

	/** to close view case in landing page */
	@FindBy(css = "#closeBtn")
	public WebElement		btn_Close;

	/** to check No Data available in landing page */
	@FindBy(css = ".dataTables_empty")
	public List<WebElement>	lbl_No_Data;

	/** to click on All Reports in Reports tab for Supervisor role */
	@FindBy(id = "allReports")
	public WebElement		lnk_AllReports;

	/** to click on Coding Dashboard in Reports tab for Supervisor role */
	@FindBy(id = "codingDashboard")
	public WebElement		lnk_AllCodingDashboard;

	/** to click on Reviewer Dashboard in Reports tab for Supervisor role */
	@FindBy(id = "reviewerDashboard")
	public WebElement		lnk_AllReviewerDashboard;

	/** to click on CDI Dashboard in Reports tab for Supervisor role */
	@FindBy(id = "cdiDashboard")
	public WebElement		lnk_AllCDIDashboard;

	/** to click on Coding Dashboard in Reports tab for Coder role */
	@FindBy(id = "codingDashboard")
	public WebElement		lnk_CodingDashboard;

	/** to click on Coding Reports in Reports tab for Coder role */
	@FindBy(id = "codingReports")
	public WebElement		lnk_CodingReports;

	/** to click on Reviewer Dashboard in Reports tab for Reviewer role */
	@FindBy(id = "reviewerDashboard")
	public WebElement		lnk_ReviewerDashboard;

	/** to click on Reviewer Reports in Reports tab for Reviewer role */
	@FindBy(id = "reviewerReports")
	public WebElement		lnk_ReviewerReports;

	/** to click on CDI Dashboard in Reports tab for CDI role */
	@FindBy(id = "cdiDashboard")
	public WebElement		lnk_CDIDashboard;

	/** to click on CDI Reports in Reports tab for CDI role */
	@FindBy(id = "cdiReports")
	public WebElement		lnk_CDIReports;

	/** to click on pagination next button */

	@FindBy(id = "searchResults_next")
	public WebElement		lnk_Next;

	/** to click on pagination previous button */

	@FindBy(id = "searchResults_previous")
	public WebElement		lnk_Prev;

	/** to check active coding page */
	//@FindBy(xpath = "//li[@id='coding'][@class='active']")
	@FindBy(css = "[id='coding'][class='active']")
	public List<WebElement>	lnk_active_Coding;

	/** to check active Supervisor page */
	//@FindBy(xpath = "//li[@id='all'][@class='active']")
	@FindBy(css = "[id='all'][class='active']")
	public List<WebElement>	lnk_active_All;

	/** to check active CDI page */
	//@FindBy(xpath = "//li[@id='cdi'][@class='active']")
	@FindBy(css = "[id='cdi'][class='active']")
	public List<WebElement>	lnk_active_CDI;

	/** to check active Reviewer page */
	//@FindBy(xpath = "//li[@id='review'][@class='active']")
	@FindBy(css = "[id='review'][class='active']")
	public List<WebElement>	lnk_active_Review;

	/** To check the active page */
	@FindBy(xpath = "(//li[@class='active'])[2]")
	public WebElement		chk_Active_Page;

	/** to check on first case of landing page */
	//@FindBy(xpath = "//table[@id='searchResults']/tbody/tr[1]/td[1]/input[1]")
	@FindBy(xpath = "//table[@id='searchResults']/tbody/tr[1]/td[1]/span")
	public WebElement		chk_First_Case;

	/** to check that case have no any issues or fresh case (for only first case) */
	@FindBy(xpath = "//table[@id='searchResults']/tbody/tr[1]/td[3]")
	public WebElement		lbl_Issues;

	/** to check 0 Document issues in case */
	@FindBy(xpath = "//div[@class='innerDetails']/div[2]/p[1]/span")
	public WebElement		lbl_Missing_Document;

	/** to check 0 Queries in the case */
	@FindBy(xpath = "//div[@class='innerDetails']/div[2]/p[2]/span")
	public WebElement		lbl_Queries;

	/** to check 0 Discuss with colleagues issues in the case */
	@FindBy(xpath = "//div[@class='innerDetails']/div[2]/p[3]/span")
	public WebElement		lbl_Discussion;

	/** to check username for the case (Barbara Jones) */
	@FindBy(xpath = "//td[text()='Jennifer Davis']")
	public WebElement		lbl_User_Name;

	/** to click on Save Searches dropdown menu */
	@FindBy(css = ".ffSelectButton")
	public WebElement		lst_Saved_Searches;

	/** to get text of alert after save same search */
	@FindBy(id = "alert-msg")
	public WebElement		txt_alert;

	/** to click on DONE button of Assign box */
	/*
	@FindBy(xpath = "/html/body/div[4]/div[3]/div/button[2]")
	public WebElement		btn_Done;
	*/
	/** to get validation message when Supervisor click on DONE button without selecting any user */
	@FindBy(css = ".error-block")
	public WebElement		txt_validation;

	/** to select checkbox besides the case */
	@FindBy(css = "input[class='case enabled ']")
	public WebElement		chk_SelectCase;

	/** to select dropdown of assign popup */
	@FindBy(css = "select[id='allocategroups']")
	public WebElement		lst_AssignTo;

	/** to select done button in assign popup */
	@FindBy(css = "button[class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")
	public WebElement		btn_Done;

	/** to check High Priority case */
	@FindBy(xpath = "//td[@title='High Priority']")
	public List<WebElement>	high_Priority;

	/** to click on close icon of assign box */
	@FindBy(css = "span[class='ui-icon ui-icon-closethick']")
	public WebElement		ico_Close;

	/** to click on CANCEL button of Assign box */
	@FindBy(css = "button[class='secondary']")
	public WebElement		btn_Cancel;

	/** text of case results number (e.g. 1-1 of 1) on landing page table */
	@FindBy(id = "accountPage")
	public WebElement		no_caseOnTable;

	/** to check Version 0 details in landing page */
	@FindBy(xpath = "//div[@class='innerDetails']/div[1]/p[1]")
	public WebElement		txt_Version0;

	/** to get image of Version 1 */
	@FindBy(xpath = "//label[contains(text(),'Version 0')]/following-sibling::a/img")
	public WebElement		img_Version0;

	/** to get image of Version 1 */
	@FindBy(xpath = "//label[contains(text(),'Version 1')]/following-sibling::a/img")
	public WebElement		img_Version1;

	/** to get image of Version 2 */
	@FindBy(xpath = "//label[contains(text(),'Version 2')]/following-sibling::a/img")
	public WebElement		img_Version2;

	/** to get image of Version 3 */
	@FindBy(xpath = "//label[contains(text(),'Version 3')]/following-sibling::a/img")
	public WebElement		img_Version3;

	/** to identify case is locked or not */
	@FindBy(xpath = "//tr[contains(@class,'disabled')]")
	public List<WebElement>	lst_casedisabled;

	/** To Check the User Role */
	@FindBy(className = "secondary-nav")
	public List<WebElement>	user_Role;

	/** To get the first row of the search table */
	@FindBy(css = "#searchResults > tbody > tr > td")
	public WebElement		tbl_FirstRowSearch;

	/** To get the Discharge date of first row from the search result */
	@FindBy(css = "#searchResults > tbody > tr:nth-child(1)>td:nth-child(10)")
	public WebElement		td_DischargeDate;

	/** to Check On hold icon for coder */
	@FindBy(css = "a[title='Coding, On Hold']")
	public WebElement		ico_OnHold;

	/** to check Pending icon for coder */
	@FindBy(css = "a[title='Coding, Pending']")
	public WebElement		ico_Pending;

	/** to Check In Queue icon for Coder */
	@FindBy(css = "a[title='Coding, In Queue']")
	public WebElement		ico_InQueue;

	/** to check In Progress icon for Reviewer user role */
	@FindBy(css = "a[title='Review, In Progress']")
	public WebElement		ico_Review_InProgress;

	/** to check On Hold icon for Reviewer user role */
	@FindBy(css = "a[title='Review, On Hold']")
	public WebElement		ico_Review_OnHold;

	/** to check pending icon for Reviewer user role */
	@FindBy(css = "a[title='Review, Pending']")
	public WebElement		ico_Review_Pending;

	/** to check On Hold Status for CDI user role */
	@FindBy(css = "a[title='CDI, On Hold']")
	public WebElement		ico_CDI_OnHold;

	/** to check Pending status for CDI user role */
	@FindBy(css = "a[title='CDI, Pending']")
	public WebElement		ico_CDI_Pending;

	/** to check locked case */
	@FindBy(css = "#searchResults > tbody > tr.odd.disabled")
	public WebElement		td_lockedcase;

	/** to get the value of Amount column */
	@FindBy(xpath = "//*[@id='searchResults']/tbody/tr[1]/td[11]")
	public WebElement		txt_Amount;

	/** to check roles assigned to User */
	@FindBy(id = "userRoleList")
	public List<WebElement>	lst_UserRole;

	/** to check the landing page menu(Case, reports) */
	@FindBy(id = "landingPageMenu")
	public WebElement		lnk_CaseAndReport;

	static LandingP_WebE	INSTANCE	= null;

	public static LandingP_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, LandingP_WebE.class);
		}
		return INSTANCE;
	}
}