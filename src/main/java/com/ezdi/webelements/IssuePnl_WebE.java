package com.ezdi.webelements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IssuePnl_WebE
{

	/** to click on view issue */
	@FindBy(id = "view-issue")
	//	@FindBy(xpath = "//div[@id='view-issue']/h7")
	public WebElement		btn_Issues;

	/** to click on discuss with colleague */
	@FindBy(id = "discuss-colleague-link")
	public WebElement		lnk_DiscussWithCol;

	/** to click on report missing document */
	@FindBy(id = "report-missing-doc-link")
	public WebElement		lnk_ReportMissDoc;

	/** to click on all issues */
	@FindBy(linkText = "< all issues")
	public WebElement		lnl_AllIssues;

	/** to click on dropdown of "TO"(user) .It opens just textbox */
	@FindBy(css = "#txtColleagues_chosen > a")
	public WebElement		lst_ChooseCol;

	/** to enter the user name(colleague name) in to field of issue */
	@FindBy(css = "div[class='chosen-search']>input")
	public WebElement		txt_ColleagueName;

	/** to enter question in issue */
	@FindBy(id = "txtDiscussionAnswer")
	public WebElement		txt_Question;

	/** to click on cancle button */
	@FindBy(css = "#mCSB_2 > div.mCSB_container.mCS_no_scrollbar > div > div > div.disucss-action-pan > div > div.float-r > button.secondary")
	public WebElement		btn_Cancle;

	/** to click on save button */
	@FindBy(css = "#mCSB_2 > div.mCSB_container.mCS_no_scrollbar > div > div > div.disucss-action-pan > div > div.float-r > button.primary")
	public WebElement		btn_Save;

	/** to click on Discharge summary checkbox in report missing document */
	@FindBy(id = "1")
	public WebElement		chk_DischargeSummary;

	/** to click on Operative reporta checkbox in report missing document */
	@FindBy(id = "2")
	public WebElement		chk_OperativeReport;

	/** to click on progress report checkbox in report missing document */
	@FindBy(id = "3")
	public WebElement		chk_ProgressReport;

	/** to click on History and physical checkbox in report missing document */
	@FindBy(id = "4")
	public WebElement		chk_HistoryAndPhy;

	/** to click on Consultation reports checkbox in report missing document */
	@FindBy(id = "5")
	public WebElement		chk_ConsultationReport;

	/** to click on Consultation checkbox in report missing document */
	@FindBy(id = "6")
	public WebElement		chk_Consultation;

	/** to click on Clinic progress note checkbox in report missing document */
	@FindBy(id = "7")
	public WebElement		chk_ClinicProgNote;

	/** to click on Follow up Note checkbox in report missing document */
	@FindBy(id = "8")
	public WebElement		chk_FollowUpNote;

	/** to click on Office Visit checkbox in report missing document */
	@FindBy(id = "9")
	public WebElement		chk_OfficeVisit;

	/** to click on Critical care note checkbox in report missing document */
	@FindBy(id = "10")
	public WebElement		chk_CriticalCareNote;

	/** to click on Oncology Consultation checkbox in report missing document */
	@FindBy(id = "11")
	public WebElement		chk_OncologyCon;

	/** to click on emergency room report checkbox in report missing document */
	@FindBy(id = "12")
	public WebElement		chk_EmergencyRoomReport;

	/** to comment on missing documents */
	@FindBy(id = "missing-doc-comment")
	public WebElement		txt_MissComment;

	/** to click on cancel button on report missing issue */
	@FindBy(css = "#report-missing-doc > div.disucss-action-pan > div > div.float-r > button.secondary")
	public WebElement		btn_MissCancel;

	/** to click on save button on report missing issue */
	@FindBy(css = "#report-missing-doc > div.disucss-action-pan > div > div.float-r > button.primary")
	public WebElement		btn_MissSave;

	/** to click on to dropdown in query template */
	@FindBy(css = "select[id='ddlPhysicians']")
	public WebElement		lst_tophysician;

	/** to select question field in query template */
	@FindBy(css = "textarea[id='txtYourQuestion']")
	public WebElement		txt_yourquestionquery;

	/** to select comments filed in query template */
	@FindBy(css = "textarea[id='txtComment']")
	public WebElement		txt_querycomments;

	/** to click on send button in query template */
	//@FindBy(css = "#mCSB_9 > div.mCSB_container > div.disucss-action-pan > div > div.float-r > button.primary")
	@FindBy(xpath = "//*[contains(@id,'mCSB_')]/div[1]/div[2]/div/div[1]/button[2]")
	public WebElement		btn_sendquery;

	/** to click on cancel button in query template */
	//@FindBy(css = "#mCSB_9 > div.mCSB_container > div.disucss-action-pan > div > div.float-r > button.secondary")
	@FindBy(xpath = "//*[contains(@id,'mCSB_')]/div[1]/div[2]/div/div[1]/button[1]")
	public WebElement		btn_cancelquery;

	/** to identify replied status of an issue */
	@FindBy(xpath = "//p[@class='pending-btn']/a[text()='Replied']")
	public WebElement		lbl_replied;

	/** to identify replied status of an issue */
	@FindBy(xpath = "//p[@class='pending-btn']/a[text()='Pending']")
	public WebElement		lbl_pending;

	/** To access scroll bar of query template */
	//@FindBy(css = "#mCSB_8 > div.mCSB_scrollTools > div > div.mCSB_dragger > div")
	@FindBy(xpath = "(//div[@class='mCSB_dragger_bar'])[4]")
	public WebElement		query_dragger;

	/** To click on mark as resolved link */
	@FindBy(css = "a[class='mark-resolved']")
	public WebElement		lnk_markasresolved;

	/** List of on mark as resolved link */
	@FindBy(css = "a[class='mark-resolved']")
	public List<WebElement>	lnk_markasresolved_lst;

	/** To find a resolved query */
	@FindBy(css = "div[class='issue-details query-physician-ico-black']")
	public WebElement		img_resolvedquery;

	/** To get issue count */
	//@FindBy(css = "#issuesCount")
	@FindBy(xpath = "//div[@id='view-issue']/descendant::h5")
	public WebElement		txt_IssueCount;

	/** To click on To Colleague dropdown */
	@FindBy(css = "#txtColleagues_chosen > a")
	public WebElement		lst_ToColleague;

	/** to enter colleague name in to textbox */
	@FindBy(css = "#txtColleagues_chosen > div > div > input[type='text']")
	public WebElement		txt_ToColleague;

	/** to enter question in discuss with colleague template */
	@FindBy(css = "textarea[id='txtDiscussionAnswer']")
	public WebElement		txt_yourquestiondiscuss;

	/** to click on send button in discuss with colleague template */
	@FindBy(xpath = "//*[contains(@id,'mCSB_')]/div[1]/div/div/div[2]/div/div[1]/button[2]")
	public WebElement		btn_senddiscussion;

	/** to click on cancel button in discuss with colleague template */
	//@FindBy(xpath = "//*[contains(@id,'mCSB_)']/div[1]/div/div/div[2]/div/div[1]/button[1]")
	@FindBy(xpath = "//*[@id='mCSB_2']/div[1]/div/div/div[2]/div/div[1]/button[1]")
	//@FindBy(css = "#mCSB_2 > div.mCSB_container.mCS_no_scrollbar > div > div > div.disucss-action-pan > div > div.float-r > button.secondary")
	public WebElement		btn_canceldiscussion;

	/** To find a resolved discussion */
	@FindBy(css = "div[class='issue-details discuss-resolved-ico']")
	public WebElement		img_resolveddiscussion;

	/** to click on first "Mark as resolved" link in issue panel */
	@FindBy(xpath = "(//a[@class='mark-resolved'])[1]")
	public WebElement		lnk_first_resolved;

	/** to enter the value in probable code in Issue Panel for CDI */
	@FindBy(id = "queryPhysicianProbableCode")
	public WebElement		txt_probableCode;

	/** to select and click on link of first element of probable code list for CDI */
	@FindBy(xpath = "//ul[@class='ui-autocomplete ui-menu ui-widget ui-widget-content ui-corner-all queryPhyAutocomplete']/li[1]/a")
	public WebElement		lnk_firstProbableCode;

	/** to click on send button in Query to Physician in CDI user role */
	@FindBy(xpath = "(//button[@class='primary'])[3]")
	public WebElement		btn_sendQueryCDI;

	/** list of error message in issue panel */
	@FindBy(css = "span[class='error-block']")
	public WebElement		lst_error_IssuePnl;

	/** to click on reply button in discuss with colleague in Issue panel */
	@FindBy(id = "postReplyLink")
	public WebElement		lnk_reply;

	/** to enter reply in text area in discuss in Issue panel */
	@FindBy(id = "txtAreaReply")
	public WebElement		txt_replyText;

	/** to click on send button in reply in discuss in Issue panel */
	@FindBy(xpath = "(//button[@class='primary'])[2]")
	public WebElement		btn_SendReply;

	/** to click on cancel button in reply in discuss in Issue panel */
	@FindBy(xpath = "(//button[@class='secondary'])[2]")
	public WebElement		btn_CancelReply;

	/** to check on Resolved checkbox of Discuss With Colleagues view page */
	@FindBy(xpath = "//input[@id='discussionResolveCheckBox']")
	public WebElement		chk_Resolved;

	/** to click on OK button of Discuss With Colleagues view Page */
	@FindBy(css = "#resolvePanel > div > div.float-r > button")
	public WebElement		btn_OK;

	/** to click on first issue in issue panel */
	//@FindBy(xpath = "(//div[@id='issue-content']/div/div/div)[1]")
	@FindBy(xpath = "((//div[@id='issue-content']/div/div/div)[1]/div/div)[1]/p")
	public WebElement		lnk_First_Issue;

	/** to check reply of discussion */
	@FindBy(xpath = "//div[@class='MarTop16']")
	public WebElement		txt_Reply;

	/** to click on text box of associated code in CDS query */
	@FindBy(id = "queryPhysicianAssociatedCode_chosen")
	public WebElement		lnk_AssoCodeList;

	static IssuePnl_WebE	INSTANCE	= null;

	public static IssuePnl_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, IssuePnl_WebE.class);
		}
		return INSTANCE;
	}
}
