package com.ezdi.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManualAllocation_WebE
{

	/** to identify Fresh Case icon */
	//@FindBy(css = "a[title='Fresh Case, ']")
	@FindBy(css = "a[title='Fresh Case']")
	public WebElement				ico_FreshCase;

	/** to get Assign message */
	@FindBy(css = "div[class='alert-contain']")
	public WebElement				txt_AssignMessage;

	static ManualAllocation_WebE	INSTANCE	= null;

	public static ManualAllocation_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, ManualAllocation_WebE.class);
		}
		return INSTANCE;
	}

}
