package com.ezdi.webelements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewCase_WebE
{

	/** to click on patient demographic panel for expand in view case */
	@FindBy(css = "#accordionLeft")
	public WebElement		panel_PatientDemographic;	// accordionLeft

	/** to click on abstract panel for expand in view case */
	@FindBy(css = "#accordionRight")
	public WebElement		panel_Abstract;

	/** to click on grouping panel for expand in view case */
	@FindBy(id = "accordionGroup")
	public WebElement		panel_Grouping;

	/** to click on issue panel */
	@FindBy(id = "view-issue")
	public WebElement		panel_Issue;

	/** to get issue count */
	@FindBy(id = "issuesCount")
	public WebElement		issue_Count;

	/** to click on settings */
	@FindBy(css = "#left-pan > div.l-hdr > div.utility-bar > div.settings-menu-wrapper.font-size-menu.float-r > img")
	public WebElement		btn_settings;

	/** to click on font size one */
	//@FindBy(xpath = "//*[@id='left-pan']/div[1]/div[3]/div[2]/div/ul/li[1]/a")
	//public WebElement		btn_size1;

	/** to click on small size font in medical record panel */
	@FindBy(xpath = "(//a[text()='A'])[1]")
	public WebElement		lnk_smallFont;

	/** to click on medium size font in medical record panel */
	@FindBy(xpath = "(//a[text()='A'])[2]")
	public WebElement		lnk_mediumFont;

	/** to click on large size font in medical record panel */
	@FindBy(xpath = "(//a[text()='A'])[3]")
	public WebElement		lnk_largeFont;

	/** to click on evidence from the medical document */
	@FindBy(id = "9DS_781")
	public WebElement		evidence_man;

	/** to click on all evidence in view case */
	@FindBy(css = "span[class='evidenceLabel']")
	public List<WebElement>	lnk_evidence;

	/** to click on Left side Medical Document Left Menu in view case */
	@FindBy(id = "navicon")
	public WebElement		lst_LeftNavigationMenu;

	/** to click on menu item in medical record panel */
	@FindBy(xpath = "//ul[@id='nav-menu-data']/li/ul/li/ul/li/a")
	public List<WebElement>	lst_leftNavigtionMenuItem;

	/** to click on dock link of medical record panel */
	@FindBy(partialLinkText = "dock")
	public WebElement		lnk_dock;

	/** to click on undock link of medical record panel */
	@FindBy(partialLinkText = "undock")
	public WebElement		lnk_unDock;

	/** to click on navigation menu on header to navigate coding panel in view case */
	@FindBy(id = "navigationMenu")
	public WebElement		lst_rightnavigationMenu;

	/** to click on Diagnosis in right side menu in view case */
	@FindBy(partialLinkText = "Diagnosis")
	public WebElement		lnk_diagnosis;

	/** to click on admitting diagnosis in navigation menu in view case */
	@FindBy(id = "admit")
	public WebElement		lnk_navadmittingdiagnosis;

	/** to click on admitting diagnosis in navigation menu in view case */
	@FindBy(id = "primary")
	public WebElement		lnk_navprincipaldiagnosis;

	/** to click on admitting diagnosis in navigation menu in view case */
	@FindBy(id = "secondary")
	public WebElement		lnk_navsecondarydiagnosis;

	/** to click on procedure in right side menu in view case */
	@FindBy(partialLinkText = "Procedures")
	public WebElement		lnk_procedure;

	/** to click on list of procedure in view case */
	@FindBy(css = "#showProcedure>li>a")
	public WebElement		lnk_procedureInDropDown;

	/** to scroll medical record panel in view case */
	@FindBy(xpath = "(//div[@class='mCSB_dragger_bar'])[1]")
	public WebElement		medical_Dragger;

	/** to click for open abstract panel in view case */
	@FindBy(id = "accordionRight")
	public WebElement		lnk_AbstactPnl;

	/** first suggested code in admitting diagnosis in coding panel */
	//@FindBy(xpath = "(//h2[@id='admit']/..//div[@class='expand'])[1]")
	@FindBy(xpath = "//div[@id='icd9-admitting-diagnosis-code']//div[contains(@class,'num-container num')]")
	public WebElement		div_first_admitDia_ICD9;

	/** suggested code in principal diagnosis in coding panel */
	//@FindBy(xpath = "(//h2[@id='primary']/..//div[@class='expand'])[1]")
	@FindBy(xpath = "//div[@id='icd9-primary-diagnosis-code']//div[contains(@class,'num-container num')]")
	public WebElement		div_primaryDia_ICD9;

	/** first suggested code in secondary diagnosis in coding panel */
	//@FindBy(xpath = "(//h2[@id='secondary']/..//div[@class='expand'])[1]")
	@FindBy(xpath = "(//div[@id='icd9-secondary-diagnosis-code']//div[contains(@class,'num-container num')])[1]")
	public WebElement		div_first_secondDia_ICD9;

	/** first suggested code in admitting diagnosis in coding panel */
	//@FindBy(xpath = "(//h2[@id='admit']/..//div[@class='expand'])[1]")
	@FindBy(xpath = "//div[@id='icd10-admitting-diagnosis-code']//div[contains(@class,'num-container num')]")
	public WebElement		div_first_admitDia_ICD10;

	/** suggested code in principal diagnosis in coding panel */
	//@FindBy(xpath = "(//h2[@id='primary']/..//div[@class='expand'])[1]")
	@FindBy(xpath = "//div[@id='icd10-primary-diagnosis-code']//div[contains(@class,'num-container num')]")
	public WebElement		div_primaryDia_ICD10;

	/** first suggested code in secondary diagnosis in coding panel */
	//@FindBy(xpath = "(//h2[@id='secondary']/..//div[@class='expand'])[1]")
	@FindBy(xpath = "(//div[@id='icd10-secondary-diagnosis-code']//div[contains(@class,'num-container num')])[1]")
	public WebElement		div_first_secondDia_ICD10;

	/** to get DRG value */
	@FindBy(css = "h3[id='accordionGroup']")
	public WebElement		txt_DRG;

	/** list of all code accept icons */
	@FindBy(css = "span[id='code-accepted-disabled']")
	public List<WebElement>	lst_acceptcode;

	/** list of all code reject icons */
	@FindBy(css = "span[id='code-rejected-disabled']")
	public List<WebElement>	lst_rejectedcode;

	/** list of all code modify icons */
	@FindBy(css = "span[id='code-modified-disabled']")
	public List<WebElement>	lst_modifiedcode;

	/** To click on version from abstract panel for fresh case */
	@FindBy(xpath = "//*[@id='versionInformation']/p/span")
	public WebElement		txt_versionFromAbstract;

	/** To click on version from abstract panel for version 1 */
	@FindBy(xpath = "//*[@id='versionInformation']/p[1]/span")
	public WebElement		txt_versionFromAbstract1;

	/** To click on version from abstract panel for version 2 */
	@FindBy(xpath = "//*[@id='versionInformation']/p[2]/span")
	public WebElement		txt_versionFromAbstract2;

	/** To click on version from abstract panel for version 3 */
	@FindBy(xpath = "//*[@id='versionInformation']/p[3]/span")
	public WebElement		txt_versionFromAbstract3;

	/** To click on close button on view case */
	@FindBy(id = "closeBtn")
	public WebElement		btn_Close;
	
	static ViewCase_WebE	INSTANCE	= null;

	public static ViewCase_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, ViewCase_WebE.class);
		}
		return INSTANCE;
	}

}
