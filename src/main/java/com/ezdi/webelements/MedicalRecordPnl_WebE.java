package com.ezdi.webelements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MedicalRecordPnl_WebE
{

	/** to click on Left side Medical Document Left Menu */
	@FindBy(id = "navicon")
	public WebElement				lst_LeftNavigationMenu;

	/** to click on menu item in medical record panel */
	@FindBy(id = "nav-menu-data")
	public WebElement				lnk_DocNaviMenu;

	/** to click on font setting of medical record panel */
	@FindBy(css = "div.settings-menu-wrapper.font-size-menu.float-r > img")
	public WebElement				img_FontSetting;

	/** to click on small size font in medical record panel */
	@FindBy(xpath = "(//a[text()='A'])[1]")
	public WebElement				lnk_smallFont;

	/** to click on medium size font in medical record panel */
	@FindBy(xpath = "(//a[text()='A'])[2]")
	public WebElement				lnk_mediumFont;

	/** to click on large size font in medical record panel */
	@FindBy(xpath = "(//a[text()='A'])[3]")
	public WebElement				lnk_largeFont;

	/** to scroll medical record panel */
	@FindBy(xpath = "(//div[@class='mCSB_dragger_bar'])[1]")
	public WebElement				medical_Dragger;

	/** to click on dock link of medical record panel */
	@FindBy(partialLinkText = "dock")
	public WebElement				lnk_dock;

	/** to click on undock link of medical record panel */
	@FindBy(partialLinkText = "undock")
	public WebElement				lnk_unDoc;

	/** to view highlighted evidence on medical document and coding panel */
	@FindBy(css = "span[class='sec-sys selectedEvidenceBGColor']")
	public List<WebElement>			lnk_highlighted_sys_evidence;

	/** to view highlighted evidence on medical document and coding panel */
	@FindBy(css = "span[class='sec-manual selectedEvidenceBGColor']")
	public List<WebElement>			lnk_highlighted_man_evidence;

	/** to view highlighted evidence from the medical document for suggested evidence */
	@FindBy(css = "span[class='sec-sys']")
	public List<WebElement>			lnk_highlighted_sys_med_evidence;

	/** to view highlighted evidence from the medical document for manual evidence */
	@FindBy(css = "span[class='sec-manual']")
	public List<WebElement>			lnk_highlighted_man_med_evidence;

	/** to click on context menu in medical records panel */
	@FindBy(id = "menu1")
	//@FindBy(css = "context1")
	public WebElement				lst_mrpanelcontextmenu;

	/** to click on context menu for Add as Evidence */
	@FindBy(id = "addEvidence")
	public WebElement				txt_addasevidence;

	/** to click on context menu for Add to Existing Code */
	@FindBy(xpath = "//span[@id='context2']//a[@id='add_exis_code']")
	public WebElement				txt_addtoexistingcode;

	/** to click on context menu for Add to New Code */
	@FindBy(css = "#addEvidence > ul > li:nth-child(2)")
	public WebElement				txt_addnewcode;

	/** to click on context menu for diagnosis */
	@FindBy(css = "#addEvidence > ul > li:nth-child(2) > ul > li:nth-child(1)")
	public WebElement				txt_diagnosis;

	/** to click on context menu for procedures */
	@FindBy(css = "#addEvidence > ul > li:nth-child(2) > ul > li:nth-child(2)")
	public WebElement				txt_procedures;

	/** to drag & copy evidence to primary diagnosis */
	@FindBy(xpath = "//div[@id='icd9-primary-diagnosis-code']//span[@id='reference']")
	public WebElement				copy_evidence_primary_diagnosis;

	/** to click on context menu for Query Physician */
	@FindBy(id = "query-physician-add-menu-item")
	public WebElement				txt_queryphysician;

	/** to chose the template in query physician */
	@FindBy(id = "ddlChooseTemplate")
	public WebElement				lbl_choosetemplate;

	/** to select the physician for TO */
	@FindBy(id = "ddlPhysicians")
	public WebElement				lst_physician;

	/** type your question for query physician */
	@FindBy(id = "txtYourQuestion")
	public WebElement				lbl_queryquestion;

	/** type your comment for query physician */
	@FindBy(id = "txtComment")
	public WebElement				lbl_querycomment;

	/** to click send button for query physician */
	@FindBy(xpath = "//*[contains(@id,'mCSB_')]/div[1]/div[2]/div/div[1]/button[2]")
	public WebElement				btn_sendqueryphysician;

	/** to click on issue count */
	@FindBy(id = "view-issue")
	public WebElement				div_issuecount;

	/** to click on Discuss with Colleague */
	@FindBy(id = "highlight_discussion")
	public WebElement				txt_discusscolleague;

	/** click on to dropdown for colleague */
	@FindBy(css = "#txtColleagues_chosen > a")
	public WebElement				lst_colleague;

	/** to select the colleague for discussion */
	@FindBy(css = "#txtColleagues_chosen > div > div > input[type='text']")
	public WebElement				lst_colleaguename;

	/** type Your Question for Discuss with colleague */
	@FindBy(id = "txtDiscussionAnswer")
	public WebElement				lbl_discussquestion;

	/** click on submit button for discuss with colleague */
	@FindBy(xpath = "//*[@id='mCSB_2']/div[1]/div/div/div[2]/div/div[1]/button[2]")
	public WebElement				btn_senddiscusscolleague;
	/** click on issue count */

	/** to select query to physician option from context menu */
	//@FindBy(css = "a[id='query-physician-add-item']")
	//@FindBy(xpath = "//ul[@id='menu1']//a[contains(@id,'query-physician')]")
	//@FindBy(css = "#query-physician-add-item")
	//@FindBy(css = "#menu1 > li:nth-child(2) > a:nth-child(1)")
	@FindBy(css = "a[id='query-physician-add-item']")
	public WebElement				lnk_querytophysician;

	/** to select discuss with colleague option from context menu */
	@FindBy(css = "a[id='discuss-colleague-add-item']")
	public WebElement				lnk_discusswithcolleague;

	/** List of all evidence in medical record panel */
	@FindBy(xpath = "//span[@class='sec-sys' or @class='sec-manual']")
	public List<WebElement>			allEvidence;

	static MedicalRecordPnl_WebE	INSTANCE	= null;

	public static MedicalRecordPnl_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, MedicalRecordPnl_WebE.class);
		}
		return INSTANCE;
	}

}