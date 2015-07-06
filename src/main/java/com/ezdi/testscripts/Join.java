package com.ezdi.testscripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.DriverTestNG;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.library.Common_Lib;
import com.ezdi.library.Join_Lib;
import com.ezdi.library.Login_Lib;
import com.ezdi.webelements.Login_WebE;

public class Join
{

	public static WebDriver				driver;
	static Login_WebE login_webe;
	/**
	 * This script checks join practutor as parent *
	 *
	 * @author bchauhan
	 * @since 08/06/2015
	 */ 
	
	@Test(description = "Vefity Join as Student",  priority = 1)
	public static void joinAsStudent()
	{
		boolean bstatus;
		String strLogin_Rowid = "bc001";
		try
		{
			Log4J.logp.info ("**************  Started  ::::    joinAsStudent ************************");
			bstatus = Join_Lib.joinAsStudent(strLogin_Rowid);
			if (bstatus)
			{
				Log4J.logp.info("Join as student sucessfull -From TestScript "  );			
				Assert.assertTrue(bstatus, "Join as student sucessfull -From TestScript " );
			} 
			else
			{
				Log4J.logp.error("Join as student UN-sucessfull -From TestScript" );
				Assert.assertTrue(bstatus, "Join as student Un-sucessfull -From TestScript " );
			}
			Log4J.logp.info ("**************  Ended  ::::    joinAsStudent ************************");
		}
		catch (Exception e)
		{
		
			e.printStackTrace();
			Log4J.logp.error("Problem found in ::::    joinAsStudent");
			Assert.assertTrue(false , "Join as student Un-sucessfull -From TestScript " );
		}
	}

	/**
	 * ParentJoin - way1 : add new child details in 2nd step of parent join
	 */
	@Test(description = "Vefity Join as Parent",  priority = 2)
	public static void joinAsParent_way1()
	{
		boolean bstatus;
		String Rowid_Parent = "bc001";
		String Rowid_Student = "bc002";
		try
		{
			Log4J.logp.info ("**************  Started  ::::    joinAsParent_way1 ************************");
			bstatus = Join_Lib.joinAsParent_way1(Rowid_Parent,Rowid_Student);
			if (bstatus)
			{
				Log4J.logp.info("Join as Parent sucessfull -From TestScript "  );			
				Assert.assertTrue(bstatus, "Join as Parent sucessfull -From TestScript " );
			} 
			else
			{
				Log4J.logp.error("Join as Parent UN-sucessfull -From TestScript" );
				Assert.assertTrue(bstatus, "Join as Parent Un-sucessfull -From TestScript " );
			}
			Log4J.logp.info ("**************  Ended  ::::    joinAsParent_way1 ************************");
		}
		catch (Exception e)
		{
		
			e.printStackTrace();
			Log4J.logp.error("Problem found in ::::    joinAsParent_way1");
			Assert.assertTrue(false , "Join as Parent Un-sucessfull -From TestScript " );
		}
	}

	/**
	 * ParentJoin - way2 : add REGISTERED child details in 2nd step of parent join
	 */
	@Test(description = "Vefity Join as Parent",  priority = 3)
	public static void joinAsParent_way2()
	{
		boolean bstatus;
		String Rowid_Parent = "bc002";
		String Rowid_Student = "bc001";
		try
		{
			Log4J.logp.info ("**************  Started  ::::    joinAsParent_way1 ************************");
			bstatus = Join_Lib.joinAsParent_way2(Rowid_Parent,Rowid_Student);
			if (bstatus)
			{
				Log4J.logp.info("Join as Parent sucessfull -From TestScript "  );			
				Assert.assertTrue(bstatus, "Join as Parent sucessfull -From TestScript " );
			} 
			else
			{
				Log4J.logp.error("Join as Parent UN-sucessfull -From TestScript" );
				Assert.assertTrue(bstatus, "Join as Parent Un-sucessfull -From TestScript " );
			}
			Log4J.logp.info ("**************  Ended  ::::    joinAsParent_way2 ************************");
		}
		catch (Exception e)
		{
		
			e.printStackTrace();
			Log4J.logp.error("Problem found in ::::    joinAsParent_way2");
			Assert.assertTrue(false , "Join as Parent Un-sucessfull -From TestScript " );
		}
	}
	
	/**
	 *TeacherJoin - way1 : Let student join by their selves
	 */
	@Test(description = "Vefity Join as Teacher",  priority = 4)
	public static void joinAsTeacher_way1()
	{
		boolean bstatus;
		String Rowid_Teacher = "bc001";
		
		try
		{
			Log4J.logp.info ("**************  Started  ::::    joinAsTeacher ************************");
			bstatus = Join_Lib.joinAsTeacher_way1(Rowid_Teacher);
			if (bstatus)
			{
				Log4J.logp.info("Join as Teacher sucessfull -From TestScript "  );			
				Assert.assertTrue(bstatus, "Join as Teacher sucessfull -From TestScript " );
			} 
			else
			{
				Log4J.logp.error("Join as Teacher UN-sucessfull -From TestScript" );
				Assert.assertTrue(bstatus, "Join as Teacher Un-sucessfull -From TestScript " );
			}
			Log4J.logp.info ("**************  Ended  ::::    joinAsTeacher_way1 ************************");
		}
		catch (Exception e)
		{
		
			e.printStackTrace();
			Log4J.logp.error("Problem found in ::::    joinAsTeacher_way1");
			Assert.assertTrue(false , "Join as Teacher Un-sucessfull -From TestScript " );
		}
	}
	
	/**
	 *TeacherJoin - way2(below 2 methods) : Fill student details
	 */
	@Test(description = "Vefity Join as Teacher",  priority = 5)
	public static void joinAsTeacher_way2()
	{
		boolean bstatus;
		String Rowid_Teacher = "bc002";
		String Rowid_Student = "bc003";
		try
		{
			Log4J.logp.info ("**************  Started  ::::    joinAsTeacher_way2 ************************");
			bstatus = Join_Lib.joinAsTeacher_way2(Rowid_Teacher,Rowid_Student);
			if (bstatus)
			{
				Log4J.logp.info("Join as Teacher sucessfull -From TestScript "  );			
				Assert.assertTrue(bstatus, "Join as Teacher sucessfull -From TestScript " );
			} 
			else
			{
				Log4J.logp.error("Join as Teacher UN-sucessfull -From TestScript" );
				Assert.assertTrue(bstatus, "Join as Teacher Un-sucessfull -From TestScript " );
			}
			Log4J.logp.info ("**************  Ended  ::::    joinAsTeacher_way2 ************************");
		}
		catch (Exception e)
		{
		
			e.printStackTrace();
			Log4J.logp.error("Problem found in ::::    joinAsTeacher_way2");
			Assert.assertTrue(false , "Join as Teacher Un-sucessfull -From TestScript " );
		}
	}
	
	/**
	 *TeacherJoin - way3(below 3 methods) : Import students From Other existing Teacher
	 */
	@Test(description = "Vefity Join as Teacher",  priority = 6)
	public static void joinAsTeacher_way3()
	{
		boolean bstatus;
		String Rowid_Teacher = "bc003";
		String Rowid_Student = "bc001";
		try
		{
			Log4J.logp.info ("**************  Started  ::::    joinAsTeacher_way3 ************************");
			bstatus = Join_Lib.joinAsTeacher_way3(Rowid_Teacher,Rowid_Student);
			if (bstatus)
			{
				Log4J.logp.info("Join as Teacher sucessfull -From TestScript "  );			
				Assert.assertTrue(bstatus, "Join as Teacher sucessfull -From TestScript " );
			} 
			else
			{
				Log4J.logp.error("Join as Teacher UN-sucessfull -From TestScript" );
				Assert.assertTrue(bstatus, "Join as Teacher Un-sucessfull -From TestScript " );
			}
			Log4J.logp.info ("**************  Ended  ::::    joinAsTeacher_way3 ************************");
		}
		catch (Exception e)
		{
		
			e.printStackTrace();
			Log4J.logp.error("Problem found in ::::    joinAsTeacher_way3");
			Assert.assertTrue(false , "Join as Teacher Un-sucessfull -From TestScript " );
		}
	}
	
	@AfterMethod(description = "Log out after each join")
	public static void LogoutAfterEachJoin()
	{
		Login_Lib.logOut_App();
		Log4J.logp.info("**************In AfterMethod for after each join Do logout **************");
	}
		
	@AfterClass
	public static void JoinAfterClass()
	{
		try
		{
			if (driver != null)
			{
				driver = null;
			}
//			Login_Lib.logOut_App();
//			Login_Lib.logIn_App(DriverTestNG.username, DriverTestNG.password);

			Log4J.logp.info("In AfterClass for Join");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
