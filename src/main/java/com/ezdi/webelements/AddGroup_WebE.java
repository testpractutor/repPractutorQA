package com.ezdi.webelements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddGroup_WebE
{

	/** to click on common dragger on page without right pannel */
	@FindBy(css = "div[class='mCSB_dragger_bar']")
	public WebElement		main_Dragger;

	/** to click for go back to admin home page */
	@FindBy(id = "adminSetting")
	public WebElement		lnk_Administrator_Homepage;

	/** to click on table of group */
	@FindBy(id = "admin-table")
	public WebElement		tbl_Group;

	/** to click previous in group table */
	@FindBy(id = "prevPageLink")
	public WebElement		lnk_Previous;

	/** to click next in group table */
	@FindBy(id = "nextPageLink")
	public WebElement		lnk_Next;

	/** to click on add group button */
	@FindBy(id = "addGroupBtn")
	public WebElement		btn_AddGroup;

	/** to click on close add group pannel */
	@FindBy(css = "a[class='message-close']")
	public WebElement		lnk_Close_RightPannel;

	/** to enter the group name in add group */
	@FindBy(id = "nameTxt")
	public WebElement		txt_Name;

	/** to enter the group description in add group */
	@FindBy(id = "descriptionTxt")
	public WebElement		txt_Description;

	/** to select role of users in add group */
	@FindBy(id = "roleTxt")
	public WebElement		lst_Role;

	/** to enter select members in add group */
	@FindBy(css = "#memberSelect_chosen > ul > li.search-field > input[type='text']")
	public WebElement		txt_Members;

	/** to click on save button in add group */
	@FindBy(id = "sendReplyBtn")
	public WebElement		btn_Save;

	/** top click on cancel button in add group */
	@FindBy(id = "cancelAdd")
	public WebElement		btn_Cancle;

	/** to see error block on add group panel */
	@FindBy(className = "error-block")
	public List<WebElement>	err_Block;

	/** to get maximum number of group on page */
	@FindBy(id = "totalListingRecords")
	public WebElement		get_GroupCount;

	/** to see added member of group */
	//	@FindBy(css = "#memberSelect_chosen > ul > li > span")
	//	@FindBy(css = "div[id='memberSelect_chosen'] > ul > li > span")
	@FindBy(xpath = "//div[@id='memberSelect_chosen']//span")
	public List<WebElement>	added_Member;

	/** to click on delete button in edit group */

	@FindBy(xpath = "//div[@class='MarTop24 float-r']/a")
	public WebElement		delete_btn;

	/** to get the list of members present for opend group */
	@FindBy(css = ".chosen-choices > li > span")
	public List<WebElement>	lst_MemberList;

	/** To get success message in Add Group */
	@FindBy(className = "success-box")
	public WebElement		success_MsgTxt;

	/** To get error message in Add Group */
	@FindBy(className = "warning-box")
	public WebElement		warning_MsgTxt;

	static AddGroup_WebE	INSTANCE	= null;

	public static AddGroup_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, AddGroup_WebE.class);
		}

		return INSTANCE;
	}

}
