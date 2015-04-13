package com.ezdi.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CodeBook_WebeE
{
	/** frame of code book */
	@FindBy(id = "code-book-iframe")
	public WebElement		frm_CodeBook;

	/** to enter search code in Code book */
	@FindBy(xpath = "//input[starts-with(@class, 'search-box k-input')]")
	public WebElement		txt_searchCode;

	/** to click on search button in code book */
	@FindBy(css = "button[class='search-btn k-button pad-left']")
	public WebElement		btn_search;

	/** to click on Find all check box in code book */
	@FindBy(css = "input[value='FindAll']")
	public WebElement		chk_findAll;

	/** to resize code book from right side vertical screen */
	@FindBy(css = "div[class='k-splitbar k-state-default k-secondary k-splitbar-horizontal k-splitbar-draggable-horizontal']>div")
	public WebElement		btn_HorisontalResize;

	/** to resize code book from bottom side horizontal screen */
	//@FindBy(css = "div[class='k-splitbar k-state-default k-secondary k-splitbar-vertical k-splitbar-draggable-vertical k-splitbar-vertical-hover']>div")
	@FindBy(xpath = "//div[contains(@class,'k-splitbar k-state-default k-splitbar-vertical k-splitbar-draggable-vertical')]/div")
	public WebElement		btn_VerticalResize;

	/** to scroll search result in code book */
	@FindBy(css = "pane-content pane-text pane-slim-n pane-slim-e default-pane")
	public WebElement		div_searchedCodeResult;

	/** to scroll div of search result of half code in code book */
	@FindBy(css = "div[class='index-result item-selected']")
	public WebElement		div_search;

	/** to click on show more result in code book */
	@FindBy(partialLinkText = "search results")
	public WebElement		lnk_showResult;

	/** to click on forward button in code book */
	@FindBy(css = "button[class='tabular-forward tabular-history disabled k-state-disabled k-button'] > span")
	public WebElement		btn_forward;

	/** to click on backward button in code book */
	@FindBy(css = "button[class='tabular-back tabular-history disabled k-state-disabled k-button']>span")
	public WebElement		btn_backward;

	/** to click on drop down of select code type in coding panel */
	@FindBy(xpath = "//span[text()='select']")
	public WebElement		lst_codeType;

	/** to select Diagnosis ICD-9 CM in code book */
	@FindBy(xpath = "//li[text()='Diagnosis, ICD-9-CM']")
	public WebElement		lst_diagnosis_ICD9;

	/** to select E code ICD-9 in code book */
	@FindBy(xpath = "//li[text()='E Code, ICD-9-CM']")
	public WebElement		lst_eCode_ICD9;

	/** to select Procedure in ICD-9 in code book */
	@FindBy(xpath = "//li[text()='Procedure, ICD-9-CM']")
	public WebElement		lst_procedure_ICD9;

	/** to select Diagnosis in ICD-10 in code book */
	@FindBy(xpath = "//li[text()='Diagnosis, ICD-10-CM']")
	public WebElement		lst_diagnosis_ICD10;

	/** to select External Cause in ICD-10 in code book */
	@FindBy(xpath = "//li[text()='External Cause, ICD-10-CM']")
	public WebElement		lst_extarnalCause_ICD10;

	/** to select Procedure in ICD-10 in code book */
	@FindBy(xpath = "//li[text()='Procedure, ICD-10-PCS']")
	public WebElement		lst_procedure_ICD10;

	/** to select CPT tabular in ICD-10 in code book */
	@FindBy(xpath = "//li[text()='CPT Tabular']")
	public WebElement		lst_cptTabular;

	/** to select HCPCS Tabular in ICD-10 in code book */
	@FindBy(xpath = "//li[text()='HCPCS Tabular']")
	public WebElement		lst_HCPCSTabular;

	/** to click for show modifiers in code book */
	@FindBy(linkText = "Modifiers")
	public WebElement		lnk_modifiers;

	/** to click on searched code in code book with CSS path */
	//@FindBy(css = "div[class='tabular-result tabular-cpt-result type-codeentry level-1 inote selectable item-selected']>div:nth-child(2)>span>a")
	//@FindBy(css = "div[class='tabular-result tabular-cpt-result type-codeentry level-2 inote selectable item-selected']>div:nth-child(2)>span>a")
	//	@FindBy(xpath = "//div[contains(@class,'inote selectable item-selected')]/div[2]/span/a")
	@FindBy(xpath = "//div[contains(@class,'selectable item-selected')]//div[@class='codes-wrapper col']/span/a")
	public WebElement		lnk_searchCode;

	/** to click on add Code in code book for ICD 10 Procedure */
	@FindBy(css = "button[class='k-button btn-add-code enabled']")
	public WebElement		lnk_addCode;

	/** element of Find all and check box in code book */
	@FindBy(xpath = "//label[@class='checkbox find-all']")
	public WebElement		webe_FindAll;

	/** element of use enethesia and checkbox in code book */
	@FindBy(xpath = "//label[@class='checkbox use-anesthesia']")
	public WebElement		webe_UseEnethesia;

	/** to click on close button of code book */
	@FindBy(xpath = "(//span[text()='close'])[1]")
	public WebElement		btn_closeCodeBook;

	/** to click on first result code of Search Results for */
	@FindBy(xpath = "(//div[contains(@class,'default-pane pane-slim-e')]//span[@class='code'])[1]")
	public WebElement		lnk_firstResultCode;

	/** to click on highlighted code of Code Book: Diagnosis Tabular */
	@FindBy(xpath = "//div[contains(@class,'selectable item-selected')]//div[@class='codes-wrapper col']/span/a")
	public WebElement		lnk_highlightedcode;

	/** to check Code Book text for ICD-9 & ICD-10 */
	@FindBy(xpath = "//span[contains(text(),'Book:')]")
	public WebElement		txt_codebookIndexType;

	static CodeBook_WebeE	INSTANCE	= null;

	public static CodeBook_WebeE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, CodeBook_WebeE.class);
		}
		return INSTANCE;
	}

}
