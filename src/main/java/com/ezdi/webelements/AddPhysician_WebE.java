package com.ezdi.webelements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddPhysician_WebE
{

	/** to click for go back to admin home page */
	@FindBy(id = "adminSetting")
	public WebElement			lnk_Administrator_Homepage;

	/** to click on add physician button */
	//@FindBy (xpath="//div[@class='float-r MarTop32']/button")
	@FindBy(css = "#addPhysicianBtn:nth-child(1)")
	public WebElement			btn_AddPhysician;

	/** to click on table of physician */
	@FindBy(id = "admin-table")
	public WebElement			tbl_Physician;

	/** to click previous in physician table */
	@FindBy(id = "prevPageLink")
	public WebElement			lnk_Previous;

	/** to click next in physician table */
	@FindBy(id = "nextPageLink")
	public WebElement			lnk_Next;

	/** to click on close add physician pannel */
	@FindBy(css = "a[class='message-close']")
	public WebElement			lnk_Close_RightPannel;

	/** to enter the first name of physician */
	@FindBy(id = "physicianDetails.physicianFirstName")
	public WebElement			txt_FirstName;

	/** to enter the middle name of phisician */
	@FindBy(id = "physicianDetails.physicianMiddleName")
	public WebElement			txt_MiddleName;

	/** to enter the last name of phisician */
	@FindBy(id = "physicianDetails.physicianLastName")
	public WebElement			txt_LastName;

	/** to enter email id or user name of physcian */
	@FindBy(id = "physicianDetails.emailId")
	public WebElement			txt_Email;

	/** to select one hospital from dropdown of physician */
	@FindBy(id = "phyHospital")
	public WebElement			lst_Hospital;

	/** to select location from dropdown of physician */
	@FindBy(id = "phyLocation")
	public WebElement			lst_Location;

	/** to enter the address of physician */
	@FindBy(id = "physicianDetails.address")
	public WebElement			txt_Address;

	/** to enter the city of physician */
	@FindBy(id = "physicianDetails.city")
	public WebElement			txt_City;

	/** to enter the state of physician */
	@FindBy(id = "physicianDetails.state")
	public WebElement			txt_State;

	/** to enter the zipcode of physician */
	@FindBy(id = "physicianDetails.zipCode")
	public WebElement			txt_ZipCode;

	/** to enter the country of physician */
	@FindBy(id = "physicianDetails.country")
	public WebElement			txt_Country;

	/** to enter the phone number of physician */
	@FindBy(id = "physicianDetails.phoneNum")
	public WebElement			txt_PhoneNo;

	/** to enter the fax number of physician */
	@FindBy(id = "physicianDetails.faxNum")
	public WebElement			txt_FaxNo;

	/** to enter the Crendentials of physician */
	@FindBy(name = "physicianDetails.credentials")
	public WebElement			txt_Credentials;

	/** to enter the Speciality of physician */
	@FindBy(name = "physicianDetails.speciality")
	public WebElement			txt_Speciality;

	/** to click on scroll dragger bar */
	@FindBy(className = "mCSB_dragger_bar")
	public WebElement			dragger;

	/** to save new physician */
	@FindBy(id = "savePhysician")
	public WebElement			btn_Save;

	/** to cancel add new physician */
	@FindBy(id = "cancelAdd")
	public WebElement			btn_Cancel;

	/** to drag add physician panel in right side */
	@FindBy(xpath = "(//div[@class='mCSB_dragger_bar'])[1]")
	public WebElement			dragger_addPhysicain;

	/** to click on delete button in add physician panel */
	@FindBy(id = "deletePhysician")
	public WebElement			btn_delete;

	/** to get maximum number of Physician on page */
	@FindBy(id = "totalListingRecords")
	public WebElement			get_PhysicianCount;

	/** To get success message in Physician */
	@FindBy(className = "success-box")
	public WebElement			success_MsgTxt;

	/** To get error message in physicain */
	@FindBy(className = "warning-box")
	public WebElement			warning_MsgTxt;

	/** to see error block on add User panel */
	@FindBy(className = "error-block")
	public List<WebElement>		err_Block;

	static AddPhysician_WebE	INSTANCE	= null;

	public static AddPhysician_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, AddPhysician_WebE.class);
		}
		return INSTANCE;
	}

}
