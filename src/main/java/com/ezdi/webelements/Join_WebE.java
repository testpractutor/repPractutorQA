package com.ezdi.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Join_WebE
{

	/** Join button on Static page */
	@FindBy(id="responsiveJoinBtn")
	public WebElement	btn_Join;
	
	/**************************************** Join As Student **********************************************/
		
	/** Student join on static page*/
	@FindBy(xpath = "//*[@id='responsiveJoinBtn']/li/ul/li[3]")
	public WebElement	btn_JoinAsStudent;
	
	/**btn Disabled Next  join as student-step-1*/
	@FindBy(id="gotoDisStudentAboutYou")
	public WebElement	btn_NextDisabled;
	
	/** Next button on join as student-step-1*/
	@FindBy(id="gotoStudentAboutYou")
	public WebElement	btn_NextOnstudentJoin;
	
	/** Parent Id text box on join as student-step-1*/
	@FindBy(id="pTEmail")
	public WebElement	txt_RegisteredParentId;
	
	/** Teacher Id text box on join as student-step-1*/
	@FindBy(id="uPTeacherID")
	public WebElement	txt_RegisteredTeacherId;
	
	/** Parent EmailId text box on join as student-step-1*/
	@FindBy(id="pTEmail")
	public WebElement	txt_RegisteredParentEmailId;
	
	/** Done button on join as student-step-2*/
	@FindBy(id="teacherDetails")
	public WebElement	btn_DoneOnStudentJoinStep2;
	
	/** First name text box on join as student-step-2*/
	@FindBy(id="uFName")
	public WebElement	txt_FirstNameStudent;
	
	/** Last name text box on join as student-step-2*/
	@FindBy(id="uLName")
	public WebElement	txt_LastNameStudent;
	
	/** Last name text box on join as student-step-2*/
	@FindBy(id="uSEmail")
	public WebElement	txt_UserNameStudent;
	
	/** Password text box on join as student-step-2*/
	@FindBy(id="password")
	public WebElement	txt_PasswordStudent;
	
	/** Gender-Male radio button on join as student-step-2*/
	@FindBy(id="sUGenderMale")
	public WebElement	radio_GenderMaleStudent;
	
	/** Gender-FeMale radio button on join as student-step-2*/
	@FindBy(id="sUGenderFemale")
	public WebElement	radio_GenderFeMaleStudent;
	
	/** Grade combo box on join as student-step-2*/
	@FindBy(id="uGrade")
	public WebElement	cbk_GradeStudent;
		
	/** MT learningpath for join as student-step-2*/
	@FindBy(id="MT_link")
	public WebElement	div_MTLearningpathStudent;
	
	/**************************************** Join As Parent **********************************************/
	
		
	/** Parent join on static page*/
	@FindBy(xpath = "//*[@id='responsiveJoinBtn']/li/ul/li[2]/a")
	public WebElement	btn_JoinAsParent;
	
	
	/** Choose title for parent in step-1*/
	@FindBy(id="uSelGender")
	public WebElement	cbk_TitleParent;
	
	/**First name text box join as parent join step-1*/
	@FindBy(id="uFName")
	public WebElement	txt_FirstNameParent;
	
	/** Last name text box on join as parent-step-1*/
	@FindBy(id="uLName")
	public WebElement	txt_LastNameParent;
	
	/**Email text box join as parent step-1*/
	@FindBy(id="uEmail")
	public WebElement	txt_EmailParent;
	
	/** Password text box join as parent step-1*/
	@FindBy(id="password")
	public WebElement	txt_PasswordParent;		
	
	/** contactNumber text box join as parent step-1*/
	@FindBy(id="contactNumber")
	public WebElement	txt_ContactNumberParent;
	
	/** ContactExt text box join as parent join step-1*/
	@FindBy(id="contactExt")
	public WebElement	txt_ContactExtParent;
	
	/** Done button on join as parent-step-1*/
	@FindBy(id="teacherDetails")
	public WebElement	btn_NextOnParentJoinStep1;
	
	/** FirstNameStudent text box on join as parent-step-1 */
	@FindBy(id="cuFName")
	public WebElement	txt_FirstNameStudentViaParent;
	
	/** Last name text box on join as parent-step-1*/
	@FindBy(id="cuLName")
	public WebElement	txt_LastNameStudentViaParent;
	
	/** User-name of student text box on join as parent-step-2*/
	@FindBy(id="userName")
	public WebElement	txt_UNameStudentViaParent;
	
	/** Password of student text box on join as parent-step-2*/
	@FindBy(id="cPassword")
	public WebElement	txt_PasswordStudentViaParent;
	
	/** Grade of student text box on join as parent-step-2*/
	@FindBy(id="gradeInfo")
	public WebElement	cbk_GradeStudentViaParent;
	
	/** Gender-Male of student radio button on join as parent-step-2*/
	@FindBy(id="uGenderMale")
	public WebElement	radio_GenderMaleStudentViaParent;
	
	/** Gender-FeMale radio button on join as parent-step-2*/
	@FindBy(id="uGenderFemale")
	public WebElement	radio_GenderFeMaleStudentViaParent;
	
	/** Teacher Id division for Student on join as parent-step-2*/
	@FindBy(xpath = "//*[@id='addChildDetail']/div[3]")
	public WebElement	div_TIdStudentViaParent;
	
	/** UserNameof student (WithTId) for Student on join as parent-step-2*/
	@FindBy(id="tUserName")
	public WebElement	txt_UNameWithTIdStudentViaParent;
	
	/** TId Student text box on join as parent-step-2*/
	@FindBy(id="tTeascherID")
	public WebElement	txt_TIdStudentViaParent;
	
	/** Done button on join as parent-step-2*/
	@FindBy(id="gotoDoneBtn")
	public WebElement	btn_DoneOnStudentViaParentJoinStep2;
	
	/** Done button on join as parent-step-2*/
	@FindBy(id="btnAddChild")
	public WebElement	btn_AddStudentParent;
	
	
	/**************************************** Join As Teacher **********************************************/
	
	/** Teacher join on static page*/
	@FindBy(xpath = "//*[@id='responsiveJoinBtn']/li/ul/li[1]/a")
	public WebElement	btn_JoinAsTeacher;
	
	/** Choose title for Teacher in step-1*/
	@FindBy(id="uSelGender")
	public WebElement	cbk_TitleTeacher;
	
	/**First name text box join as Teacher join step-1*/
	@FindBy(id="uFName")
	public WebElement	txt_FirstNameTeacher;
	
	/** Last name text box on join as Teacher-step-1*/
	@FindBy(id="uLName")
	public WebElement	txt_LastNameTeacher;
	
	/**Email text box join as Teacher step-1*/
	@FindBy(id="uEmail")
	public WebElement	txt_EmailTeacher;
	
	/** Password text box join as Teacher step-1*/
	@FindBy(id="password")
	public WebElement	txt_PasswordTeacher;		
	
	/** contactNumber text box join as Teacher step-1*/
	@FindBy(id="contactNumber")
	public WebElement	txt_ContactNumberTeacher;
	
	/** ContactExt text box join as Teacher join step-1*/
	@FindBy(id="contactExt")
	public WebElement	txt_ContactExtTeacher;
	
	/** Done button on join as Teacher-step-1*/
	@FindBy(id="teacherDetails")
	public WebElement	btn_NextOnTeacherJoinStep1;
	
	/** Skip link on join as parent-step-2*/
	@FindBy(id="skipAddSchoolBtn")
	public WebElement	lnk_SkipAddSchoolDetails;
	
	/** Grade of student text box on join as Teacher-step-3*/
	@FindBy(id="grade")
	public WebElement	cbk_GradeForClassOfTeacher;
	
	/** Teacher wants their student join by themselves division join as Teacher-step-3*/
	@FindBy(xpath = "//*[@id='addStudentDetail']/div[5]")
	public WebElement	div_StudentByTheirOwnViaTeacher;
	
	/** Done button on join as Teacher-step-3*/
	@FindBy(id="gotoDoneBtn")
	public WebElement	btn_DoneOnTeacherJoinStep3;
	
	/** Teacher join on static page*/
	@FindBy(xpath = "//*[@id='header']/div[1]/div/div[4]")
	public WebElement	cbk_HeaderListTeacher;
	
	/** GenericPassword text box on join as Teacher-step-3*/
	@FindBy(id="sPassword")
	public WebElement	txt_GenericPasswordStudentViaTeacher;
	
	/** First name text box on join as Teacher-step-3*/
	@FindBy(id="sFName1")
	public WebElement	txt_FnameOfStudentViaTeacher;
	
	/** Last name text box on join as Teacher-step-3*/
	@FindBy(id="sLName1")
	public WebElement	txt_LnameOfStudentViaTeacher;
	
	/** User name text box on join as Teacher-step-3*/
	@FindBy(id="sUEmail1")
	public WebElement	txt_UNameOfStudentViaTeacher;
	
	/** Gender-Male of student radio button on join as Teacher-step-3*/
	@FindBy(id="sUGenderMale1")
	public WebElement	radio_GenderMaleStudentViaTeacher;
	
	/** Gender-FeMale radio button on join as Teacher-step-3*/
	@FindBy(id="sUGenderFemale1")
	public WebElement	radio_GenderFeMaleStudentViaTeacher;
	
	/** select Add Student Method cmbo-box on join as Teacher-step-3*/
	@FindBy(id="selectAddStudMethod")
	public WebElement	cbk_selectAddStudMethod;
	
	/** Enter Co-teacher Id text box on join as Teacher-step-3*/
	@FindBy(id="uCoTeacherIdBox")
	public WebElement	txt_CoTeacherIdToImport;
	
	/** Search for co-teacher button on join as Teacher-step-3*/
	@FindBy(id="addCoTeacherID")
	public WebElement	btn_SearchCoTeacherID;
	
	/** Teacher wants their student join by themselves division join as Teacher-step-3*/
	@FindBy(id="example2")
	public WebElement	div_StudentsFoundByCoTeacherId;
	
	/** Download templateStudentInfo button on join as Teacher-step-3*/
	@FindBy(id="download")
	public WebElement	btn_DownloadStudentTamplateInfo;
	
	/** Upload templateStudentInfo button on join as Teacher-step-3*/
	@FindBy(id="upload")
	public WebElement	btn_UploadStudentTamplateInfo;
	
	static Join_WebE	INSTANCE	= null;

	public static Join_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, Join_WebE.class);
		}
		return INSTANCE;
	}

}
