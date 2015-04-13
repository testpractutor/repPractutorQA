package com.ezdi.webelements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddUser_WebE
{

	/** to click on add user button */
	@FindBy(id = "addUserBtn")
	public WebElement		btn_AddUser;

	/** to click for go back to administrator setting page */
	@FindBy(id = "adminSetting")
	public WebElement		lnk_Administrator_Homepage;

	/** to see table of user */
	@FindBy(id = "admin-table")
	public WebElement		tbl_Users;

	/** to click on previous in table of users */
	@FindBy(id = "prevPageLink")
	public WebElement		lnk_Previous;

	/** to click on next in table of users */
	@FindBy(id = "nextPageLink")
	public WebElement		lnk_Next;

	/** to enter the first name on add user */
	@FindBy(id = "firstNameTxt")
	public WebElement		txt_FirstName;

	/** to enter the middle name on add user */
	@FindBy(id = "middleNameTxt")
	public WebElement		txt_MiddleName;

	/** to enter the last name on add user */
	@FindBy(id = "lastNameTxt")
	public WebElement		txt_LastName;

	/** to enter the email i on add user */
	@FindBy(id = "emailIdTxt")
	public WebElement		txt_Email;

	/** to enter the address on add user */
	@FindBy(id = "addressTxt")
	public WebElement		txt_Address;

	/** to enter the city on add user */
	@FindBy(id = "cityTxt")
	public WebElement		txt_City;

	/** to enter the state on add user */
	@FindBy(id = "stateTxt")
	public WebElement		txt_State;

	/** to enter the zip code on add user */
	@FindBy(id = "zipCodeTxt")
	public WebElement		txt_ZipCode;

	/** to enter the country on add user */
	@FindBy(id = "countryTxt")
	public WebElement		txt_Country;

	/** to enter the phone number on add user */
	@FindBy(id = "phoneNoTxt")
	public WebElement		txt_PhoneNo;

	/** to enter the fax number on add user */
	@FindBy(id = "faxNoTxt")
	public WebElement		txt_FaxNo;

	/** to click on coder role on add user */
	@FindBy(css = "input[data-rolename='Coder']")
	public WebElement		chk_Coder;

	/** to click on reviewer role on add user */
	@FindBy(css = "input[data-rolename='Reviewer']")
	public WebElement		chk_Reviewer;

	/** to click on CDI role on add user */
	@FindBy(css = "input[data-rolename='CDI']")
	public WebElement		chk_CDI;

	/** to select group from suggested coder group in add user */
	@FindBy(css = "#role2Group_chosen > ul > li > input")
	public WebElement		txt_CoderGroup;

	/** to select group from suggested reviewer group in add user */
	@FindBy(css = "#role3Group_chosen > ul > li > input")
	public WebElement		txt_ReviewerGroup;

	/** to select group from suggested CDi group in add user */

	@FindBy(css = "#role4Group_chosen > ul > li > input")
	public WebElement		txt_CDIGroup;

	/** to click on Onsite on add user */
	@FindBy(css = "input[name='userDetails.workingFromList[]'][value='1']")
	public WebElement		chk_Onsite;

	/** to click on Offsite on add user */
	@FindBy(css = "input[name='userDetails.workingFromList[]'][value='2']")
	public WebElement		chk_Offsite;

	/** to click on Testing tab on add user */
	@FindBy(linkText = "Testing")
	public WebElement		lnk_Testing;

	/** to click on testing location on add user */
	@FindBy(linkText = "testing location")
	public WebElement		lnk_TestingLocation;

	/** This "Inpatient" element for Testing tab to click */
	@FindBy(css = "#tabs-7 > ul > li:nth-child(1) > input[type='checkbox']")
	public WebElement		chk_Inpatient_Testing;

	/** This "Outpatient" element for testing tab to click */
	@FindBy(css = "#tabs-7 > ul > li:nth-child(2) > input[type='checkbox']")
	public WebElement		chk_Outpatient_Testing;

	/** This "MED" element for testing tab to click */
	@FindBy(css = "#tabs-7 > ul > li:nth-child(3) > input[type='checkbox']")
	public WebElement		chk_MED_Testing;

	/** This "SUR" element for testing tab to click */
	@FindBy(css = "#tabs-7 > ul > li:nth-child(4) > input[type='checkbox']")
	public WebElement		chk_SUR_Testing;

	/** This "URO" element for testing tab to click */
	@FindBy(css = "#tabs-7 > ul > li:nth-child(5) > input[type='checkbox']")
	public WebElement		chk_URO_Testing;

	/** This "PUL" element for testing tab to click */
	@FindBy(css = "#tabs-7 > ul > li:nth-child(6) > input[type='checkbox']")
	public WebElement		chk_PUL_Testing;

	/** This "CAR" element for testing tab to click */
	@FindBy(css = "#tabs-7 > ul > li:nth-child(7) > input[type='checkbox']")
	public WebElement		chk_CAR_Testing;

	/** This "Inpatient" element for testing location tab to click */
	@FindBy(css = "#tabs-8 > ul > li:nth-child(1) > input[type='checkbox']")
	public WebElement		chk_Inpatient_Testing_Location;

	/** This "Outpatient" element for testing Location tab to click */
	@FindBy(css = "#tabs-8 > ul > li:nth-child(2) > input[type='checkbox']")
	public WebElement		chk_Outpatient_Testing_Location;

	/** This "MED" element for testing Location tab to click */
	@FindBy(css = "#tabs-8 > ul > li:nth-child(3) > input[type='checkbox']")
	public WebElement		chk_MED_Testing_Location;

	/** This "SUR" element for testing Location tab to click */
	@FindBy(css = "#tabs-8 > ul > li:nth-child(4) > input[type='checkbox']")
	public WebElement		chk_SUR_Testing_Location;

	/** This "URO" element for testing Location tab to click */
	@FindBy(css = "#tabs-8 > ul > li:nth-child(5) > input[type='checkbox']")
	public WebElement		chk_URO_Testing_Location;

	/** This "PUL" element for testing Location tab to click */
	@FindBy(css = "#tabs-8 > ul > li:nth-child(6) > input[type='checkbox']")
	public WebElement		chk_PUL_Testing_Location;

	/** This "CAR" element for testing Location tab to click */
	@FindBy(css = "#tabs-8 > ul > li:nth-child(7) > input[type='checkbox']")
	public WebElement		chk_CAR_Testing_Location;

	/** to enter Speciality input text in add user */
	@FindBy(id = "specialityTxt")
	public WebElement		txt_Speciality;

	/** to click save button on add user */
	@FindBy(id = "saveUser")
	//@FindBy(xpath = "//button[@id='saveUser']")
	public WebElement		btn_Save;

	/** to click cancel button on add user */
	@FindBy(id = "cancelAdd")
	public WebElement		btn_Cancel;

	/** to see error block on add User panel */
	@FindBy(className = "error-block")
	public List<WebElement>	err_Block;

	/** to scroll add user page */
	//@FindBy(xpath = "//*[@id='mCSB_3']/div[2]/div/div[1]/div")
	//@FindBy(xpath = "//div[@id='RightPane']/div[1]/div[2]/div[1]/div[1]/div[1]")
	@FindBy(xpath = "(//div[@class='mCSB_dragger_bar'])[1]")
	public WebElement		dragger_adduser;

	/** To scroll Admin User Page */
	@FindBy(css = "div[class='mCSB_dragger_bar']")
	public WebElement		main_dragger;

	/** To Delete user from edit user panel */
	//@FindBy(className = "deleteRowRecord")
	@FindBy(css = "div[class='MarTop24 float-r']>a")
	public WebElement		btn_delete;

	/** to get the maximum number of users on add user page */
	@FindBy(id = "totalListingRecords")
	public WebElement		get_userCount;

	/** to click on cross (X) button present on upper left corner */
	@FindBy(css = "#userDetailform > div > div.float-r.MarTop8 > a")
	public WebElement		lnk_cross;

	/** to click on delete button in edit User */

	@FindBy(xpath = "//*[@id='userDetailform']/div/div[14]/div[7]/a")
	public WebElement		del_btn;

	/** To get success message in Add User */
	@FindBy(className = "success-box")
	public WebElement		success_MsgTxt;

	/** To get error message in Add User */
	@FindBy(className = "warning-box")
	public WebElement		warning_MsgTxt;

	/** To Get Existing User(Validation Message) */
	/*
	@FindBy(css = "#userDetailform > div > div.form-raw.form-group.has-error > div > span")
	public WebElement		existing_User;*/

	static AddUser_WebE		INSTANCE	= null;

	public static AddUser_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, AddUser_WebE.class);
		}
		return INSTANCE;
	}

}
