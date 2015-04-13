package com.ezdi.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GroupinPnl_WebE
{

	/** to see grouping in grouping header */
	@FindBy(xpath = "//h1[normalize-space(text())='Grouping']")
	public WebElement		lbl_GroupingHeader;

	/** to click on total of edit */
	@FindBy(id = "totalEdit")
	public WebElement		lnk_TotalEdit;

	/** to click on details of edit */
	@FindBy(id = "edit-detailsId")
	public WebElement		lnk_DetailsOfEdit;

	/** to click on group prizer version in grouping panel for CMS version */
	@FindBy(css = "#grouperPricer > h3")
	public WebElement		lnk_CMSVer;

	/** to click to see primary diagnosis in grouping panel */
	@FindBy(id = "primaryDia")
	public WebElement		lnk_PrimaryDia;

	/** to click for opening grouping panel */
	@FindBy(id = "accordionGroup")
	public WebElement		lnk_GroupingPnl;

	/** to click on DRG analysis in grouping panel */
	@FindBy(id = "drgAnalysis")
	public WebElement		lnk_DRGAnalysis;

	/** to close reference panel in grouping panel */
	@FindBy(css = ".close")
	public WebElement		btn_Close;

	/** to click on Late button in grouping panel */
	@FindBy(id = "later")
	public WebElement		btn_Later;

	/** to click on refresh coding panel(updated) or save in grouping panel */
	@FindBy(id = "done")
	public WebElement		btn_Done;

	/** to check the check box of "Route this case for review" after "Done" is clicked */
	@FindBy(id = "isReview")
	public WebElement		chk_routeForReview;

	/** to check the check box of "On submit take me to my queue" after "Done" is clicked */
	@FindBy(id = "inqueue1")
	public WebElement		chk_takeMeQueue;

	/** to click on "Submit" button after click on "Done" */
	@FindBy(xpath = "//span[text()='SUBMIT']")
	public WebElement		btn_submitAfterDone;

	/** to click on "Cancel" button after click on "Done" */
	@FindBy(xpath = "(//span[text()='Cancel'])[2]")
	public WebElement		btn_cancelAfetrDone;

	/** to click on cross sign for close the "Confirmation Div" after click on "Done" */
	@FindBy(xpath = "(//span[text()='close'])[4]")
	public WebElement		btn_closeAfterDone;

	/** to select grouper in grouping panel */
	@FindBy(id = "grouperSelection")
	public WebElement		lst_grouperSelect;

	/** to select pricer in grouping panel */
	@FindBy(id = "pricerSelection")
	public WebElement		lst_pricerSelect;

	/** to click on submit button of grouper and pricer in grouping panel */
	@FindBy(id = "submitGrouperPricer")
	public WebElement		btn_submitGrouper;

	/** to click on cancel button of grouper and pricer in grouping panel */
	@FindBy(id = "cancelGrouperPricer")
	public WebElement		btn_cancelGrouper;

	/** to get selected option in Grouper */
	@FindBy(xpath = "(//option[@selected])[1]")
	public WebElement		selectedOptionGrouper;

	/** to get selected option in Pricer */
	@FindBy(xpath = "(//option[@selected])[2]")
	public WebElement		selectedOptionPricer;

	/** warnig message of pending issues before submit case */
	@FindBy(id = "doneinfo")
	public WebElement		webe_WarningIssueSubmit;

	/** to check image of grouping icon */
	@FindBy(css = "#done > img")
	public WebElement		ico_Grouping;

	/** to check Working DRG in Working Screen */
	@FindBy(css = "#workingDrgHeader")
	public WebElement		txt_WorkingDRG;

	/** to check Initial DRG in Working Screen */
	@FindBy(css = "#initialDrgHeader")
	public WebElement		txt_IntialDRG;

	/** to check Coder DRg in Working Screen */
	@FindBy(css = "#grouping-accordion")
	public WebElement		txt_CoderDRG;

	/** to click on close icon of Grouper Pricer box */
	@FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.grouperPricerDialog > div.ui-dialog-titlebar.ui-widget-header.ui-corner-all.ui-helper-clearfix > a")
	public WebElement		ico_Close;

	/** to click on first DRG analysis reference code */
	@FindBy(xpath = "(//a[@id='drglink'])[1]")
	public WebElement		lnk_firstDRGRefCode;

	/** to click on first DRG analysis reference code */
	@FindBy(xpath = "(//a[@id='drglink'])[2]")
	public WebElement		lnk_secondDRGRefCode;

	/** to click on first DRG analysis reference code */
	@FindBy(xpath = "(//a[@id='drglink'])[3]")
	public WebElement		lnk_thirdDRGRefCode;

	/** to click on "Add As Admitting Diagnosis" in reference panel */
	@FindBy(xpath = "(//span[text()='Add as Admitting Diagnosis'])[1]")
	public WebElement		lnk_addAdmitFromRef;

	/** to click on "Add As Admitting Diagnosis" in reference panel */
	@FindBy(xpath = "(//span[text()='Add as Principal Diagnosis'])[1]")
	public WebElement		lnk_addPrincipalFromRef;

	/** to click on "Add As Admitting Diagnosis" in reference panel */
	@FindBy(xpath = "(//span[text()='View in Code Book'])[1]")
	public WebElement		lnk_viewCodeFromRef;

	/** to click on Edits button in grouping panel */
	@FindBy(css = "#undefined-sticky-wrapper > header > div.float-l.title-edits-wrapper > div.grouping-edits.float-l")
	public WebElement		btn_Edit;

	/** to get DRG value after opening grouping panel */
	@FindBy(xpath = "(//div[@class='grouping-details']//h2)[1]")
	public WebElement		lbl_DRGInOpenPanel;

	/** to get DRG value after opening grouping panel */
	@FindBy(xpath = "(//div[@class='grouping-details']//h2)[2]")
	public WebElement		lbl_ReimbursementInOpenPanel;

	static GroupinPnl_WebE	INSTANCE	= null;

	public static GroupinPnl_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, GroupinPnl_WebE.class);
		}
		return INSTANCE;
	}
}
