package com.ezdi.webelements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AbstractPnl_WebE
{

	/** to click for open abstract panel */
	@FindBy(id = "accordionRight")
	public WebElement		lnk_AbstactPnl;

	/** to see stage in abstract panel */
	@FindBy(xpath = "//*[@id='ui-accordion-right-accordion-panel-0']/div[1]/div[1]/p[1]/span")
	public WebElement		lbl_Stage;

	/** to see status in abstact panel */
	@FindBy(id = "currentStatus")
	public WebElement		lbl_Status;

	/** to see Assined to in abstract panel */
	@FindBy(xpath = "//*[@id='ui-accordion-right-accordion-panel-0']/div[1]/div[1]/p[3]/span")
	public WebElement		lbl_AssinedTo;

	/** to see DNFC days in abstract panel */
	@FindBy(xpath = "//*[@id='ui-accordion-right-accordion-panel-0']/div[1]/div[1]/p[4]/span")
	public WebElement		lbl_DNFCDays;

	/** to see amount in abstract panel */
	@FindBy(xpath = "//*[@id='ui-accordion-right-accordion-panel-0']/div[1]/div[1]/p[5]/span")
	public WebElement		lbl_Amount;

	/** to click on reference encoder */
	@FindBy(id = "referenceico")
	public WebElement		lnk_Ref_Encoder;

	/** to select reference code book list in references in abstract panel */
	@FindBy(id = "referenceBookList")
	public WebElement		lst_CodingBook;

	/** to enter the text for search on keyword */
	@FindBy(id = "searchTerm")
	public WebElement		txt_SearchKeyword;

	/** to close reference panel in abstract panel */
	@FindBy(css = ".close")
	public WebElement		btn_Close;

	/** to see label of user and case in abstract panel */
	@FindBy(css = "div[id='versionInformation']>p>span")
	public WebElement		lbl_versionUser;
	/** to get data of label list of all element in abstract panel */
	@FindBy(xpath = "//div[@aria-labelledby='accordionRight']//span")
	public List<WebElement>	lst_labelLst;

	/** to get data of label list of all element in abstract panel */
	@FindBy(id = "mnereference")
	public WebElement		div_MNEReferencePnl;

	static AbstractPnl_WebE	INSTANCE	= null;

	public static AbstractPnl_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, AbstractPnl_WebE.class);
		}
		return INSTANCE;
	}

}