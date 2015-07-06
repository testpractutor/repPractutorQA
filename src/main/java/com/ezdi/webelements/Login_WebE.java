package com.ezdi.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_WebE
{
	/** Sign in button on Static page */
	@FindBy(id = "btnSignIn")
	public WebElement	btn_SignIn;
	
	/** Textbox to enter username */
	@FindBy(id = "userName2")
	public WebElement	txt_userName;

	/** Textbox to enter Password */
	@FindBy(id = "password2")
	public WebElement	txt_passWord;

	/** Go Button for sign in */
	@FindBy(id = "loginButton")
	public WebElement	btn_Go;

	@FindBy(id = "userMenu")
	public WebElement	btn_Salutation;

	@FindBy(id = "logout")
	public WebElement	lnk_Logout;

	/** To get the validation message of unsucessfull login */
	@FindBy(id = "errMsg")
	public WebElement	txt_ErrMsg;

	/** To reset Password */
	@FindBy(id = "resettxtuserpassword")
	public WebElement	txt_Reset_Pwd;

	/** To Confirm reset Password */
	@FindBy(id = "resetconfirmtxtuserpassword")
	public WebElement	txt_Confirm_ResetPwd;

	/** To login after reset password */
	@FindBy(id = "resetbutton")
	public WebElement	btn_ResetLogin;

	/**************************************** Forgot Password **********************************************/

	/** To Click on forgot password Link */
	@FindBy(id = "forgotClk")
	public WebElement	lnk_ForgotPwd;

	/** To enter email id in forgot password text box */
	@FindBy(id = "forgottxtusername")
	public WebElement	txt_ForgotUsername;

	/** To Click on Submit button on forgot password */
	@FindBy(id = "forgotpassbutton")
	public WebElement	btn_ForgotPassword;

	/** To Click on Cancel link in forgot Password */
	@FindBy(id = "forgotpasscancel")
	public WebElement	lnk_ForgotCancel;

	/************************************************ Profile Page *****************************************/
	
	/** User-name drop down in header */
	@FindBy(xpath = "//*[@id='header']/div[1]/div/div[4]")
	public WebElement	drp_UsernameHeader;
	
	/** Sign out link in header */
	@FindBy(linkText= "Sign out")
	public WebElement	lnk_SignoutHeader;
	
	
//	/** Fotter division in Footer area */
//	@FindBy(id = "footer")
//	public WebElement	div_Footer;
	
	
//	/** Sign out link in header for Parent Or Teacher*/
//	@FindBy(xpath = "//*[@id='headercontextmenu']/li[4]/a")
//	public WebElement	lnk_SignoutHeaderForParentOrTeaacher;
				
	/** To Click on Edit Button in Profile Page */
	@FindBy(id = "edit")
	public WebElement	btn_Edit;

	/** To Click on edit password Link */
	@FindBy(id = "edit_password")
	public WebElement	lnk_Edit_Password;

	/** To Enter new password in text box */
	@FindBy(id = "new_pass")
	public WebElement	txt_New_Pass;

	/** To Enter Confirm password in text box */
	@FindBy(id = "conf_pass")
	public WebElement	txt_Conf_Pass;

	/** To Click on Save link on edit password */
	@FindBy(id = "save_password")
	public WebElement	lnk_Save_Password;

	/** To check the validation check for Min. 8 characters */
	@FindBy(xpath = "//div[@id='pass_from']/div[4]/span[1]/span")
	public WebElement	chk_EightChar;

	/** To check the validation check for Upper and Lower case letters */
	@FindBy(xpath = "//div[@id='pass_from']/div[4]/span[2]/span")
	public WebElement	chk_UpperAndLowerCase;

	/** To check the validation check for One special character */
	@FindBy(xpath = "//div[@id='pass_from']/div[4]/span[3]/span")
	public WebElement	chk_OneSpecialChar;

	/** To enter the address in address text box */
	@FindBy(id = "address")
	public WebElement	txt_Address;

	/** To enter the city in city text box */
	@FindBy(id = "city")
	public WebElement	txt_City;

	/** To enter the state in state text box */
	@FindBy(id = "state")
	public WebElement	txt_State;

	/** To enter the zip_code in zip_code text box */
	@FindBy(id = "zip_code")
	public WebElement	txt_Zip_Code;

	/** To enter the country in country text box */
	@FindBy(id = "country")
	public WebElement	txt_Country;

	/** To enter the phone no in phone no text box */
	@FindBy(id = "phoneno")
	public WebElement	txt_PhoneNo;

	/** To enter the FaxNo in FaxNo text box */
	@FindBy(id = "faxno")
	public WebElement	txt_FaxNo;

	/** To Save the changes made in edit profile */
	@FindBy(id = "save")
	public WebElement	btn_Save;

	/** to Cancel the edit profile */
	@FindBy(id = "cancel")
	public WebElement	lnk_Cancel;

	/** To click on Edit Picture link */
	@FindBy(id = "editPicture")
	public WebElement	lnk_EditPicture;

	/** to check Name label and roles is displayed or not in profile page */
	@FindBy(xpath = "//div[@class='procontainer_one']//h2")
	public WebElement	lbl_Name;

	/** to check role is displayed or not */
	@FindBy(xpath = "//div[@class='procontainer_one']//h4")
	public WebElement	lbl_Roles;

	/** to check image thumbnail is displayed or not in profile page */
	@FindBy(css = "#pic > img")
	public WebElement	ico_img;

	/** to check Email Id (Username) is displayed or not on profile page */
	@FindBy(xpath = "(//div[@class='procontainer_two']//h6)[1]")
	public WebElement	lbl_EmailId;

	/** to check Address is displayed or not on profile page */
	@FindBy(xpath = "//div[@class='procontainer_two']//h7[text()='Address']")
	public WebElement	lbl_Address;

	/** to check address is displayed or not */
	@FindBy(xpath = "(//div[@class='procontainer_two']//h6)[2]")
	public WebElement	lbl_add;

	/** to check First Name is Disable or not in profile page */
	@FindBy(id = "profile_firstrname")
	public WebElement	txt_FirstName;

	/** to check Middle name is disabled or not */
	@FindBy(id = "profile_midlename")
	public WebElement	txt_MiddileName;

	/** to check Last Name is disbaled or not */
	@FindBy(id = "profile_lastname")
	public WebElement	txt_LastName;

	/** to check Phone label is displayed or not on profile page */
	@FindBy(xpath = "//div[@class='procontainer_two']//h7[text()='Phone #']")
	public WebElement	lbl_Phone;

	@FindBy(xpath = "(//div[@class='procontainer_two']//h6)[3]")
	public WebElement	lbl_phno;

	/** to check Fax label is displayed or not on profile page */
	@FindBy(xpath = "//div[@class='procontainer_two']//h7[text()='Fax #']")
	public WebElement	lbl_Fax;

	/** to check fax is saved or not */
	@FindBy(xpath = "(//div[@class='procontainer_two']//h6)[4]")
	public WebElement	lbl_fa;

	/** to click on browse link in profile page */
	@FindBy(id = "picBrowse")
	public WebElement	lnk_Browse;

	/** to click on cancel link on profile page */
	@FindBy(id = "picCancel")
	public WebElement	lnk_picCancel;

	static Login_WebE	INSTANCE	= null;

	public static Login_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, Login_WebE.class);
		}
		return INSTANCE;
	}

}
