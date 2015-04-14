package com.ezdi.webelements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Comman_WebE
{
		
	/** to click on scroll dragger bar */
	@FindBy(className = "mCSB_dragger_bar")
	public WebElement		dragger;

	/** to click on all evidence in coding panel */
	@FindBy(css = "span[class='evidenceLabel']")
	public List<WebElement>	lnk_evidence;

	/** to view highlighted evidence on medical document and coding panel */
	//@FindBy(css = "span[class='sec-sys']")
	@FindBy(xpath = "//span[contains(@class,'sec-sys')]")
	public List<WebElement>	lnk_sys_evidence;
	
	/** to view highlighted evidence on medical document and coding panel */
	//@FindBy(css = "span[class='sec-manual']")
	@FindBy(xpath = "//span[contains(@class,'sec-manual')]")
	public List<WebElement>	lnk_man_evidence;

	/** to click on drop down of evidence in coding panel */
	@FindBy(css = "span[class='code-status-drop-down']")
	public List<WebElement>	lst_evidence;

	/** to click on copy evidence */
	@FindBy(id = "copyEvidence")
	public List<WebElement>	lnk_copyEvidence;

	/** to click on query physician in coding panel */
	@FindBy(id = "coding_panel_add_query")
	public List<WebElement>	lnk_queryPhyCode;

	/** to click on discuss with colleague in coding panel */
	@FindBy(id = "coding_panel_add_discussion")
	public List<WebElement>	lnk_discussCol;

	/** to click on remove evidence in coding panel */
	@FindBy(id = "removEvidence")
	public List<WebElement>	lnk_removeEvidence;

	/** highlighted evidence on manually selected evidence in coding panel */
	@FindBy(css = "span[class='brd code-status manual selectedEvidenceBGColor']")
	public List<WebElement>	lnk_man_codingPnl;

	/** highlighted evidence on system suggested evidence in coding panel */
	@FindBy(css = "span[class='brd code-status selectedEvidenceBGColor']")
	public List<WebElement>	lnk_sys_codingPnl;

	/** to click in remove evidence of highlighted evidence in coding panel */
	@FindBy(xpath = "//span[contains(@class,'selectedEvidenceBGColor')]//a[@id='removEvidence']")
	public WebElement		lnk_removeEvi;

	/** to click on drop- down of highlighted evidence in coding panel */
	@FindBy(xpath = "//span[contains(@class,'selectedEvidenceBGColor')]//span[@class='code-status-drop-down']")
	public WebElement		lst_evidence1;

	/** to search Secondary Diagnosis in coding panel */
	//@FindBy(id = "secondary")
	@FindBy(xpath = "//h2[@id='secondary']")
	public WebElement		lbl_secondDia;

	/** first division of secondary diagnosis in coding panel */
	@FindBy(xpath = "(//h2[@id='secondary']/..//div[@class='expand'])[1]")
	public WebElement		div_first_secondDia;

	/** second division of secondary diagnosis in coding panel */
	@FindBy(xpath = "(//h2[@id='secondary']/..//div[@class='expand'])[2]")
	public WebElement		div_second_secondDia;

	/** list of all evidence in coding panel (manual and suggested) */
	@FindBy(css = "span[class = 'brd code-status']")
	public List<WebElement>	allEvidenc_codnPnl;

	/** to click on ok button of alert */
	@FindBy(xpath = "//div[contains(@class,'alert')]//span[@class='ui-button-text']")
	public WebElement		btn_OK_Alert;

	/** to get text of custom alert */
	@FindBy(id = "custom-alert")
	public WebElement		lbl_customAlertMsg;

	static Comman_WebE		INSTANCE	= null;

	public static Comman_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, Comman_WebE.class);
		}
		return INSTANCE;
	}

}
