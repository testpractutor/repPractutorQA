package com.ezdi.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Show_Hide_Columns_WebE
{

	/** to click on Show/Hide Columns link option */
	@FindBy(xpath = "//span[text() = 'Show / Hide Columns']")
	public WebElement				lnk_Show_Hide_Columns;

	/** to click on Save button */
	@FindBy(xpath = "//span[text() = 'Save']")
	public WebElement				lnk_Save;

	/** Panel of Show/Hide Columns */
	@FindBy(xpath = "//div[@class='relative']")
	public WebElement				main_Panel;

	/** Reports Table */
	@FindBy(id = "reportsTable")
	public WebElement				tbl_reports;

	/** panel of Show/Hide Columns */
	@FindBy(css = ".relative")
	public WebElement				panel_Main;

	static Show_Hide_Columns_WebE	INSTANCE	= null;

	public static Show_Hide_Columns_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, Show_Hide_Columns_WebE.class);
		}
		return INSTANCE;
	}
}
