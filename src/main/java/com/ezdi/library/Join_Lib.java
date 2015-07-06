package com.ezdi.library;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.lang.String;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ezdi.framework.common.JDBCMySql;
import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.webelements.Join_WebE;


public class Join_Lib
{
	static WebDriver driver;
	static Join_WebE join_webe; 

	public static boolean joinAsStudent(String rowid) {
		boolean bStatus = false;
		String RegTeacherId = null;
		String firstName = null;
		String lastName = null;
		String userName = null;
		String passWord = null;
		String gender = null;
		String grade = null;
		String RegParentId = null;
		
		Map<String, String> rowTestData = null;
		try {

			Log4J.logp.info("--------------- STARTED : joinAsStudent ----------------");
			JDBCMySql navigationBar_TestData = new JDBCMySql();
			rowTestData = navigationBar_TestData.getRowbyID("td_joinnewstudentdata",
					rowid);
			RegTeacherId = rowTestData.get("RegTeacherId");
			firstName = rowTestData.get("firstName");
			lastName = rowTestData.get("lastName");
			userName = rowTestData.get("userName");
			passWord = rowTestData.get("passWord");
			gender = rowTestData.get("gender");
			grade = rowTestData.get("grade");
			RegParentId = rowTestData.get("RegParentId");
			
			
			bStatus = Join_Lib.JoinAsStudent_App(RegTeacherId, firstName, lastName, userName, passWord, gender, grade, RegParentId);
			Log4J.logp.info("--------------- COMPLETED : joinAsStudent ----------------");
			return bStatus;

		} catch (Exception e) {
			e.printStackTrace();
			Log4J.logp.info("--------------- PROBLEM OCCURED : joinAsStudent ----------------");
			return false;
		}
	}
	
	public static boolean JoinAsStudent_App(String RegTeacherId, String firstName,String lastName, String userName, String passWord,String gender,String grade,String RegParentId)
	{
		try 
		{
			Log4J.logp.info("--------------- STARTED: JoinAsStudent_App --------------- ");
			driver = ExecutionSetup.getDriver();
			join_webe = Join_WebE.getInstance(driver);
			
			
			Actions action = new Actions(driver);
			action.moveToElement(join_webe.btn_Join).click().build().perform();
			
			//join_webe.btn_Join.click();
			if(Common_Lib.waitForObject(join_webe.btn_JoinAsStudent,"clickable", 30))
			{
				
				join_webe.btn_JoinAsStudent.click();
			}
			
			Common_Lib.waitForObject(join_webe.btn_NextDisabled,"visibility", 30);
			if(RegTeacherId != null)
			{
				join_webe.txt_RegisteredTeacherId.sendKeys(RegTeacherId);	
			}
			else
			{
				join_webe.txt_RegisteredParentId.sendKeys(RegParentId);
			}
			
			if(Common_Lib.waitForObject(join_webe.btn_NextOnstudentJoin,"clickable", 30))
			{
				join_webe.btn_NextOnstudentJoin.click();
			}
//			Common_Lib.waitForObject(join_webe.btn_DoneOnStudentJoinStep2,"visibility", 30);
			join_webe.txt_FirstNameStudent.sendKeys(firstName);
			join_webe.txt_LastNameStudent.sendKeys(lastName);
			join_webe.txt_UserNameStudent.sendKeys(userName);
			join_webe.txt_PasswordStudent.sendKeys(passWord);
			if(gender == "M") 
			{
				join_webe.radio_GenderMaleStudent.click();
			}
			else
			{
				join_webe.radio_GenderFeMaleStudent.click();
			}
			Select select=new Select(join_webe.cbk_GradeStudent);
			select.selectByValue(grade);
			join_webe.btn_DoneOnStudentJoinStep2.click();
			
			if(Common_Lib.waitForObject(join_webe.div_MTLearningpathStudent,"visibility", 30))
			{
				Log4J.logf.error(" --------------- COMPLETED : JoinAsStudent_App --------------- ");
				return true;
			}
			else
			{
				Log4J.logf.error(" --------------- FAILED : JoinAsStudent_App --------------- ");
				return false;
			}
				
		}
		catch(Exception e) 
		{
			Log4J.logf.error(" --------------- PROBLEM OCCURED: JoinAsStudent_App --------------- ");
			return false;
		}
	 }
	
	/**
	 * ParentJoin - way1(below 3 methods) : add new child details in 2nd step of parent join
	 */
	public static boolean joinAsParent_way1(String rowid_parent,String rowid_student) {
		
		boolean bStatus = false;
		String firstNameParent = null;
		String lastNameParent = null;
		String passWordParent = null;
		String eMailIdParent = null;
		String title = null;
		String contactNumber = null;
		String contactExt = null;
		
		
		Map<String, String> rowTestData = null;
		try 
		{
			Log4J.logp.info("--------------- STARTED : joinAsParent_way1 ----------------");
			JDBCMySql navigationBar_TestData = new JDBCMySql();
			rowTestData = navigationBar_TestData.getRowbyID("td_joinasparentdata",
					rowid_parent);
			firstNameParent = rowTestData.get("firstNameParent");
			lastNameParent = rowTestData.get("lastNameParent");
			eMailIdParent = rowTestData.get("eMailIdParent");
			passWordParent = rowTestData.get("passWordParent");
			title = rowTestData.get("title");
			contactNumber = rowTestData.get("contactNumber");
			contactExt = rowTestData.get("contactExt");
			
			
			bStatus = Join_Lib.joinAsParent_App_way1(firstNameParent, lastNameParent, eMailIdParent, passWordParent, title, contactNumber, contactExt, rowid_student);
			Log4J.logp.info("--------------- COMPLETED : joinAsParent_way1 ----------------");
			return bStatus;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log4J.logf.error(" --------------- PROBLEM OCCURED : joinAsParent_way1 --------------- ");
			return false;
		}
		
	}

	public static boolean joinAsParent_App_way1(String firstNameParent,String lastNameParent, String eMailIdParent, String passWordParent,String title,String contactNumber,String contactExt, String rowid_student)
	{
		try 
		{
			Log4J.logp.info("--------------- STARTED: joinAsParent_App_way1 --------------- ");
			
			driver = ExecutionSetup.getDriver();
			join_webe = Join_WebE.getInstance(driver);
			
			Actions action = new Actions(driver);
			action.moveToElement(join_webe.btn_Join).click().build().perform();
			
			join_webe.btn_JoinAsParent.click();

//			Common_Lib.waitForObject(join_webe.btn_NextOnParentJoinStep1,"visibility", 30);
			Select select=new Select(join_webe.cbk_TitleParent);
			select.selectByValue(title);
			join_webe.txt_FirstNameParent.sendKeys(firstNameParent);
			join_webe.txt_LastNameParent.sendKeys(lastNameParent);
			join_webe.txt_EmailParent.sendKeys(eMailIdParent);
			join_webe.txt_PasswordParent.sendKeys(passWordParent);
			
			if(contactNumber != null) 
			{
				join_webe.txt_ContactNumberParent.sendKeys(contactNumber);
				join_webe.txt_ContactExtParent.sendKeys(contactExt);
			}
			join_webe.btn_NextOnParentJoinStep1.click();
			
			// Page-2 : add child details
			Common_Lib.waitForObject(join_webe.btn_DoneOnStudentViaParentJoinStep2,"visibility", 30);
			
			//Add new Child details 
			boolean dataFilled = addNewChildDetails(rowid_student);
			if(dataFilled)
			{
				join_webe.btn_DoneOnStudentViaParentJoinStep2.click();
			}
			else
			{
				Log4J.logp.info("--------------- COULD NOT FEEL DATA : joinAsParent_App_way1 ----------------");
				
			}
			
			if(Common_Lib.waitForObject(join_webe.btn_AddStudentParent,"visibility", 30))
			{
				Log4J.logp.info("--------------- COMPLETED : joinAsParent_App_way1 ----------------");
				return true;
			}
			else
			{
				Log4J.logp.info("--------------- FAILED : joinAsParent_App_way1 ----------------");
				return false;
			}
			
		    

		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log4J.logp.error(" --------------- PROBLEM OCCURED : joinAsParent_App_way1 --------------- ");
			return false;
		}
		
	}
	
	public static boolean addNewChildDetails(String rowid_student)
	{
		String firstName = null;
		String lastName = null;
		String userName = null;
		String passWord = null;
		String gender = null;
		String grade = null;
		
		Map<String, String> rowTestData = null;
		try {

			Log4J.logp.info("--------------- STARTED : addNewChildDetails ----------------");
			JDBCMySql navigationBar_TestData = new JDBCMySql();
			rowTestData = navigationBar_TestData.getRowbyID("td_joinnewstudentdata",
					rowid_student);
			
			firstName = rowTestData.get("firstName");
			lastName = rowTestData.get("lastName");
			userName = rowTestData.get("userName");
			passWord = rowTestData.get("passWord");
			gender = rowTestData.get("gender");
			grade = rowTestData.get("grade");
			
				join_webe.txt_FirstNameStudentViaParent.sendKeys(firstName);
				join_webe.txt_LastNameStudentViaParent.sendKeys(lastName);
				join_webe.txt_UNameStudentViaParent.sendKeys(userName);
				join_webe.txt_PasswordStudentViaParent.sendKeys(passWord);
				if(gender == "M") 
				{
					join_webe.radio_GenderMaleStudentViaParent.click();
				}
				else
				{
					join_webe.radio_GenderFeMaleStudentViaParent.click();
				}
				Select select=new Select(join_webe.cbk_GradeStudentViaParent);
				select.selectByValue(grade);
			
			Log4J.logp.info("--------------- COMPLETED : addNewChildDetails ----------------");
		return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log4J.logp.info("--------------- PROBLEM OCCURED : addNewChildDetails ----------------");
			return false;
		}
		
	}
	
	/**
	 * ParentJoin - way2(below 3 methods) : add REGISTERED child details in 2nd step of parent join
	 */
	public static boolean joinAsParent_way2(String rowid_parent,String rowid_student) {
		
		boolean bStatus = false;
		String firstNameParent = null;
		String lastNameParent = null;
		String passWordParent = null;
		String eMailIdParent = null;
		String title = null;
		String contactNumber = null;
		String contactExt = null;
		
		
		Map<String, String> rowTestData = null;
		try 
		{
			Log4J.logp.info("--------------- STARTED : joinAsParent_way2 ----------------");
			JDBCMySql navigationBar_TestData = new JDBCMySql();
			rowTestData = navigationBar_TestData.getRowbyID("td_joinasparentdata",
					rowid_parent);
			firstNameParent = rowTestData.get("firstNameParent");
			lastNameParent = rowTestData.get("lastNameParent");
			eMailIdParent = rowTestData.get("eMailIdParent");
			passWordParent = rowTestData.get("passWordParent");
			title = rowTestData.get("title");
			contactNumber = rowTestData.get("contactNumber");
			contactExt = rowTestData.get("contactExt");
			
			
			bStatus = Join_Lib.joinAsParent_App_way2(firstNameParent, lastNameParent, eMailIdParent, passWordParent, title, contactNumber, contactExt, rowid_student);
			Log4J.logp.info("--------------- COMPLETED : joinAsParent_way2 ----------------");
			return bStatus;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log4J.logf.error(" --------------- PROBLEM OCCURED : joinAsParent_way2 --------------- ");
			return false;
		}
		
	}

	public static boolean joinAsParent_App_way2(String firstNameParent,String lastNameParent, String eMailIdParent, String passWordParent,String title,String contactNumber,String contactExt, String rowid_student)
	{
		try 
		{
			Log4J.logp.info("--------------- STARTED: joinAsParent_App_way2 --------------- ");
			
			driver = ExecutionSetup.getDriver();
			join_webe = Join_WebE.getInstance(driver);
			
			Actions action = new Actions(driver);
			action.moveToElement(join_webe.btn_Join).click().build().perform();
			
			join_webe.btn_JoinAsParent.click();

//			Common_Lib.waitForObject(join_webe.btn_NextOnParentJoinStep1,"visibility", 30);
			Select select=new Select(join_webe.cbk_TitleParent);
			select.selectByValue(title);
			join_webe.txt_FirstNameParent.sendKeys(firstNameParent);
			join_webe.txt_LastNameParent.sendKeys(lastNameParent);
			join_webe.txt_EmailParent.sendKeys(eMailIdParent);
			join_webe.txt_PasswordParent.sendKeys(passWordParent);
			
			if(contactNumber != null) 
			{
				join_webe.txt_ContactNumberParent.sendKeys(contactNumber);
				join_webe.txt_ContactExtParent.sendKeys(contactExt);
			}
			join_webe.btn_NextOnParentJoinStep1.click();
			
			// Page-2 : add child details
			Common_Lib.waitForObject(join_webe.btn_DoneOnStudentViaParentJoinStep2,"visibility", 30);
			
			//Add registered Child details 
			boolean dataFilled = addRegisteredChildDetails(rowid_student);
			if(dataFilled)
			{
				join_webe.btn_DoneOnStudentViaParentJoinStep2.click();
			}
			else
			{
				Log4J.logp.info("--------------- COULD NOT FEEL DATA : joinAsParent_App_way2 ----------------");
				
			}
			
			if(Common_Lib.waitForObject(join_webe.btn_AddStudentParent,"visibility", 30))
			{
				Log4J.logp.info("--------------- COMPLETED : joinAsParent_App_way2 ----------------");
				return true;
			}
			else
			{
				Log4J.logp.info("--------------- FAILED : joinAsParent_App_way2 ----------------");
				return false;
			}
			
		    

		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log4J.logp.error(" --------------- PROBLEM OCCURED : joinAsParent --------------- ");
			return false;
		}
		
	}
	
 	
	public static boolean addRegisteredChildDetails(String rowid_student)
	{
		
		String userName = null;
		String RegTeacherId = null;
		Map<String, String> rowTestData = null;
		try {

			Log4J.logp.info("--------------- STARTED : addRegisteredChildDetails ----------------");
			JDBCMySql navigationBar_TestData = new JDBCMySql();
			rowTestData = navigationBar_TestData.getRowbyID("td_joinregisteredstudentdata",
					rowid_student);
			
			RegTeacherId = rowTestData.get("RegTeacherId");
			userName = rowTestData.get("userName");
					
			if(RegTeacherId != null)
			{
				join_webe.div_TIdStudentViaParent.click();
				join_webe.txt_UNameWithTIdStudentViaParent.sendKeys(userName);
				join_webe.txt_TIdStudentViaParent.sendKeys(RegTeacherId);
			}
			
			Log4J.logp.info("--------------- COMPLETED : addRegisteredChildDetails ----------------");
		return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log4J.logp.info("--------------- PROBLEM OCCURED : addRegisteredChildDetails ----------------");
			return false;
		}
		
	}

	/**
	 *TeacherJoin - way1(below 2 methods) : Let student join by their selves
	 */

	public static boolean joinAsTeacher_way1(String rowid) {
		
		boolean bStatus = false;
		String firstNameTeacher = null;
		String lastNameTeacher = null;
		String passWordTeacher = null;
		String eMailIdTeacher = null;
		String title = null;
		String contactNumber = null;
		String contactExt = null;
		String grade = null;
		
		Map<String, String> rowTestData = null;
		try {

			Log4J.logp.info("--------------- STARTED : joinAsTeacher_way1 ----------------");
			JDBCMySql navigationBar_TestData = new JDBCMySql();
			rowTestData = navigationBar_TestData.getRowbyID("td_joinasteacherdata",
					rowid);
			firstNameTeacher = rowTestData.get("firstNameTeacher");
			lastNameTeacher = rowTestData.get("lastNameTeacher");
			passWordTeacher = rowTestData.get("passWordTeacher");
			eMailIdTeacher = rowTestData.get("eMailIdTeacher");
			title = rowTestData.get("title");
			contactNumber = rowTestData.get("contactNumber");
			contactExt = rowTestData.get("contactExt");
			grade = rowTestData.get("grade");
			
			
			bStatus = Join_Lib.joinAsTeacher_App_way1(firstNameTeacher, lastNameTeacher, passWordTeacher, eMailIdTeacher, title, contactNumber, contactExt, grade);
			Log4J.logp.info("--------------- COMPLETED : joinAsTeacher_way1 ----------------");
			return bStatus;

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			Log4J.logp.info("--------------- PROBLEM OCCURED : joinAsTeacher_way1 ----------------");
			return false;
		}
	}
	

	public static boolean joinAsTeacher_App_way1(String firstNameTeacher,String lastNameTeacher, String passWordTeacher, String eMailIdTeacher,String title,String contactNumber,String contactExt, String grade)
	{
		try 
		{
			Log4J.logp.info("--------------- STARTED: joinAsTeacher_App_way1 --------------- ");
			
			driver = ExecutionSetup.getDriver();
			join_webe = Join_WebE.getInstance(driver);
			
			Actions action = new Actions(driver);
			action.moveToElement(join_webe.btn_Join).click().build().perform();
			join_webe.btn_JoinAsTeacher.click();
			
			Select select=new Select(join_webe.cbk_TitleTeacher);
			select.selectByValue(title);
			join_webe.txt_FirstNameParent.sendKeys(firstNameTeacher);
			join_webe.txt_LastNameParent.sendKeys(lastNameTeacher);
			join_webe.txt_EmailParent.sendKeys(eMailIdTeacher);
			join_webe.txt_PasswordParent.sendKeys(passWordTeacher);
			
			if(contactNumber != null) 
			{
				join_webe.txt_ContactNumberParent.sendKeys(contactNumber);
				join_webe.txt_ContactExtParent.sendKeys(contactExt);
			}
			join_webe.btn_NextOnTeacherJoinStep1.click();
			
			// Page-2 : add school details
			Common_Lib.waitForObject(join_webe.lnk_SkipAddSchoolDetails,"visibility", 30);
			join_webe.lnk_SkipAddSchoolDetails.click();
			
			// Page-3 : add class details
			Common_Lib.waitForObject(join_webe.cbk_GradeForClassOfTeacher,"visibility", 30);
			Select selectgrade=new Select(join_webe.cbk_GradeForClassOfTeacher);
			selectgrade.selectByValue(grade);
			
			Common_Lib.scrollBrowsertoElementView(driver,join_webe.btn_DoneOnTeacherJoinStep3);
			join_webe.div_StudentByTheirOwnViaTeacher.click();
			Thread.sleep(4000);
			join_webe.btn_DoneOnTeacherJoinStep3.click();
			join_webe.btn_DoneOnTeacherJoinStep3.click();
			if(Common_Lib.waitForObject(join_webe.cbk_HeaderListTeacher,"clickable", 30))
			{
				Log4J.logp.info("--------------- COMPLETED : joinAsTeacher_App_way1 ----------------");
				return true;
			}
			else
			{
				Log4J.logp.info("--------------- FAILED : joinAsTeacher_App_way1 ----------------");
				return false;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log4J.logp.error(" --------------- PROBLEM OCCURED : joinAsTeacher_App_way1 --------------- ");
			return false;
		}
		
	}
	
	/**
	 *TeacherJoin - way2(below 2 methods) : Fill student details
	 */
	public static boolean joinAsTeacher_way2(String rowid_Teacher,String rowid_Student) {
			
		boolean bStatus = false;
		String firstNameTeacher = null;
		String lastNameTeacher = null;
		String passWordTeacher = null;
		String eMailIdTeacher = null;
		String title = null;
		String contactNumber = null;
		String contactExt = null;
		String grade = null;
			
		Map<String, String> rowTestData = null;
		try {

			Log4J.logp.info("--------------- STARTED : joinAsTeacher_way2 ----------------");
			JDBCMySql navigationBar_TestData = new JDBCMySql();
			rowTestData = navigationBar_TestData.getRowbyID("td_joinasteacherdata",
					rowid_Teacher);
			firstNameTeacher = rowTestData.get("firstNameTeacher");
			lastNameTeacher = rowTestData.get("lastNameTeacher");
			passWordTeacher = rowTestData.get("passWordTeacher");
			eMailIdTeacher = rowTestData.get("eMailIdTeacher");
			title = rowTestData.get("title");
			contactNumber = rowTestData.get("contactNumber");
			contactExt = rowTestData.get("contactExt");
			grade = rowTestData.get("grade");
				
				
			bStatus = Join_Lib.joinAsTeacher_App_way2(firstNameTeacher, lastNameTeacher, passWordTeacher, eMailIdTeacher, title, contactNumber, contactExt, grade, rowid_Student);
			Log4J.logp.info("--------------- COMPLETED : joinAsTeacher_way2 ----------------");
			return bStatus;

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			Log4J.logp.info("--------------- PROBLEM OCCURED : joinAsTeacher_way2 ----------------");
			return false;
		}
	}
		
	public static boolean joinAsTeacher_App_way2(String firstNameTeacher,String lastNameTeacher, String passWordTeacher, String eMailIdTeacher,String title,String contactNumber,String contactExt, String grade, String rowid_Student)
	{
		try 
		{
			Log4J.logp.info("--------------- STARTED: joinAsTeacher_App_way2 --------------- ");
				
			driver = ExecutionSetup.getDriver();
			join_webe = Join_WebE.getInstance(driver);
				
			Actions action = new Actions(driver);
			action.moveToElement(join_webe.btn_Join).click().build().perform();
			join_webe.btn_JoinAsTeacher.click();
				
			Select select=new Select(join_webe.cbk_TitleTeacher);
			select.selectByValue(title);
			join_webe.txt_FirstNameParent.sendKeys(firstNameTeacher);
			join_webe.txt_LastNameParent.sendKeys(lastNameTeacher);
			join_webe.txt_EmailParent.sendKeys(eMailIdTeacher);
			join_webe.txt_PasswordParent.sendKeys(passWordTeacher);
				
			if(contactNumber != null) 
			{
				join_webe.txt_ContactNumberParent.sendKeys(contactNumber);
				join_webe.txt_ContactExtParent.sendKeys(contactExt);
			}
			join_webe.btn_NextOnTeacherJoinStep1.click();
				
			// Page-2 : add school details
			Common_Lib.waitForObject(join_webe.lnk_SkipAddSchoolDetails,"visibility", 30);
			join_webe.lnk_SkipAddSchoolDetails.click();
				
			// Page-3 : add class details
			Common_Lib.waitForObject(join_webe.cbk_GradeForClassOfTeacher,"visibility", 30);
			Select selectgrade=new Select(join_webe.cbk_GradeForClassOfTeacher);
			selectgrade.selectByValue(grade);
				
			//Common_Lib.scrollBrowsertoElementView(driver,join_webe.btn_DoneOnTeacherJoinStep3);
			//Add NEW Student details 
			boolean dataFilled = addNewStudentDetails(rowid_Student);
			if(dataFilled)
			{
				join_webe.btn_DoneOnTeacherJoinStep3.click();
			}
			else
			{
				Log4J.logp.info("--------------- COULD NOT FEEL DATA : joinAsTeacher_App_way2 ----------------");
				
			}	
			
			//Common_Lib.scrollBrowsertoElementView(driver,join_webe.btn_DoneOnTeacherJoinStep3);
			//join_webe.btn_DoneOnTeacherJoinStep3.click();
			//join_webe.btn_DoneOnTeacherJoinStep3.click();
			Thread.sleep(2000);
			if(Common_Lib.waitForObject(join_webe.cbk_HeaderListTeacher,"clickable", 30))
			{
				Log4J.logp.info("--------------- COMPLETED : joinAsTeacher_App_way2 ----------------");
				return true;
			}
			else
			{
				Log4J.logp.info("--------------- FAILED : joinAsTeacher_App_way2 ----------------");
				return false;
			}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log4J.logp.error(" --------------- PROBLEM OCCURED : joinAsTeacher_App_way2 --------------- ");
			return false;
		}
		
	}
	
	public static boolean addNewStudentDetails(String rowid_student)
	{
		String firstName = null;
		String lastName = null;
		String userName = null;
		String passWord = null;
		String gender = null;
		
		Map<String, String> rowTestData = null;
		try {

			Log4J.logp.info("--------------- STARTED : addNewStudentDetails ----------------");
			JDBCMySql navigationBar_TestData = new JDBCMySql();
			rowTestData = navigationBar_TestData.getRowbyID("td_joinnewstudentdata",
					rowid_student);
			
			firstName = rowTestData.get("firstName");
			lastName = rowTestData.get("lastName");
			userName = rowTestData.get("userName");
			passWord = rowTestData.get("passWord");
			gender = rowTestData.get("gender");
			
			join_webe.txt_GenericPasswordStudentViaTeacher.sendKeys(passWord);
			join_webe.txt_FnameOfStudentViaTeacher.sendKeys(firstName);
			join_webe.txt_LnameOfStudentViaTeacher.sendKeys(lastName);
			join_webe.txt_UNameOfStudentViaTeacher.sendKeys(userName);
				
			if(gender == "M") 
			{
				join_webe.radio_GenderMaleStudentViaTeacher.click();
			}
			else
			{
				join_webe.radio_GenderFeMaleStudentViaTeacher.click();
			}
				
			
			Log4J.logp.info("--------------- COMPLETED : addNewStudentDetails ----------------");
		return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log4J.logp.info("--------------- PROBLEM OCCURED : addNewStudentDetails ----------------");
			return false;
		}
		
	}
	
	/**
	 *TeacherJoin - way3(below 3 methods) : Import students From Other existing Teacher
	 */
	public static boolean joinAsTeacher_way3(String rowid_Teacher,String rowid_Student) {
		
		boolean bStatus = false;
		String firstNameTeacher = null;
		String lastNameTeacher = null;
		String passWordTeacher = null;
		String eMailIdTeacher = null;
		String title = null;
		String contactNumber = null;
		String contactExt = null;
		String grade = null;
		String RegTeacherId = null;
		
		Map<String, String> rowTestData = null;
		Map<String, String> rowTestDataTID = null;
		try {

			Log4J.logp.info("--------------- STARTED : joinAsTeacher_way3 ----------------");
			JDBCMySql navigationBar_TestData = new JDBCMySql();
			rowTestData = navigationBar_TestData.getRowbyID("td_joinasteacherdata",
					rowid_Teacher);
			firstNameTeacher = rowTestData.get("firstNameTeacher");
			lastNameTeacher = rowTestData.get("lastNameTeacher");
			passWordTeacher = rowTestData.get("passWordTeacher");
			eMailIdTeacher = rowTestData.get("eMailIdTeacher");
			title = rowTestData.get("title");
			contactNumber = rowTestData.get("contactNumber");
			contactExt = rowTestData.get("contactExt");
			grade = rowTestData.get("grade");
				
			rowTestDataTID = navigationBar_TestData.getRowbyID("td_joinregisteredstudentdata",
					rowid_Student);
			RegTeacherId = rowTestDataTID.get("RegTeacherId");
			
			bStatus = Join_Lib.joinAsTeacher_App_way3(firstNameTeacher, lastNameTeacher, passWordTeacher, eMailIdTeacher, title, contactNumber, contactExt, grade, RegTeacherId);
			Log4J.logp.info("--------------- COMPLETED : joinAsTeacher_way3 ----------------");
			return bStatus;

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			Log4J.logp.info("--------------- PROBLEM OCCURED : joinAsTeacher_way3 ----------------");
			return false;
		}
	}
	
	
	public static boolean joinAsTeacher_App_way3(String firstNameTeacher,String lastNameTeacher, String passWordTeacher, String eMailIdTeacher,String title,String contactNumber,String contactExt, String grade, String RegTeacherId)
	{
		try 
		{
			Log4J.logp.info("--------------- STARTED: joinAsTeacher_App_way3 --------------- ");
				
			driver = ExecutionSetup.getDriver();
			join_webe = Join_WebE.getInstance(driver);
				
			Actions action = new Actions(driver);
			action.moveToElement(join_webe.btn_Join).click().build().perform();
			join_webe.btn_JoinAsTeacher.click();
				
			Select select=new Select(join_webe.cbk_TitleTeacher);
			select.selectByValue(title);
			join_webe.txt_FirstNameParent.sendKeys(firstNameTeacher);
			join_webe.txt_LastNameParent.sendKeys(lastNameTeacher);
			join_webe.txt_EmailParent.sendKeys(eMailIdTeacher);
			join_webe.txt_PasswordParent.sendKeys(passWordTeacher);
				
			if(contactNumber != null) 
			{
				join_webe.txt_ContactNumberParent.sendKeys(contactNumber);
				join_webe.txt_ContactExtParent.sendKeys(contactExt);
			}
			join_webe.btn_NextOnTeacherJoinStep1.click();
				
			// Page-2 : add school details
			Common_Lib.waitForObject(join_webe.lnk_SkipAddSchoolDetails,"visibility", 30);
			join_webe.lnk_SkipAddSchoolDetails.click();
				
			// Page-3 : add class details
			Common_Lib.waitForObject(join_webe.cbk_GradeForClassOfTeacher,"visibility", 30);
			Select selectgrade=new Select(join_webe.cbk_GradeForClassOfTeacher);
			selectgrade.selectByValue(grade);
			
			Common_Lib.scrollBrowsertoElementView(driver,join_webe.txt_GenericPasswordStudentViaTeacher);
			Select selectMethod3 = new Select(join_webe.cbk_selectAddStudMethod);
			selectMethod3.selectByValue("addExisting");
			
			Common_Lib.waitForObject(join_webe.txt_CoTeacherIdToImport,"visibility", 30);
			join_webe.txt_CoTeacherIdToImport.sendKeys(RegTeacherId);
			join_webe.btn_SearchCoTeacherID.click();
			
			Common_Lib.waitForObject(join_webe.div_StudentsFoundByCoTeacherId,"visibility", 30);
			Common_Lib.scrollBrowsertoElementView(driver,join_webe.btn_DoneOnTeacherJoinStep3);
			join_webe.btn_DoneOnTeacherJoinStep3.click();
			
			Thread.sleep(6000);
			if(Common_Lib.waitForObject(join_webe.cbk_HeaderListTeacher,"clickable", 30))
			{
				Log4J.logp.info("--------------- COMPLETED : joinAsTeacher_App_way3 ----------------");
				return true;
			}
			else
			{
				Log4J.logp.info("--------------- FAILED : joinAsTeacher_App_way3 ----------------");
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log4J.logp.error(" --------------- PROBLEM OCCURED : joinAsTeacher_App_way3 --------------- ");
			return false;
		}
		
		
	}

}
	
	
	



