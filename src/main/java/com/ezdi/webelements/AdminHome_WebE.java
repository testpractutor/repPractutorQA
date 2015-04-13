package com.ezdi.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminHome_WebE
{

	/** to click on add new user */
	@FindBy(css = "#hospitalUsers > table > tbody > tr:nth-child(2) > td > h2")
	public WebElement		lnk_AddNew_user;

	/** to click on add new physician */
	@FindBy(css = "#hospitalPhysician > table > tbody > tr:nth-child(2) > td > h2")
	public WebElement		lnk_AddNew_Physician;

	/** to click on add new group */
	@FindBy(css = "#hospitalGroups > table > tbody > tr:nth-child(2) > td > h2")
	public WebElement		lnk_AddNew_Group;

	/** to click on Users div to view users */

	@FindBy(css = "#hospitalUsers > table > tbody > tr:nth-child(2) > td > h2")
	public WebElement		lnk_ViewUsers;

	static AdminHome_WebE	INSTANCE	= null;

	public static AdminHome_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, AdminHome_WebE.class);
		}
		return INSTANCE;
	}

}
