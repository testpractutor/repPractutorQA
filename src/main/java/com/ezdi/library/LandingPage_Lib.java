package com.ezdi.library;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.webelements.LandingP_WebE;
import com.ezdi.webelements.SearchCriteria_WebE;

public class LandingPage_Lib
{

	static WebDriver			driver;
	static LandingP_WebE		landingp_webe;
	static SearchCriteria_WebE	searchcriteria_webe;

	/**
	 * This method is expand the case in landing page
	 * 
	 * @author agupta
	 * @since 4/9/2014
	 */
	public static boolean click_on_expand()
	{
		try
		{
			Log4J.logp.info("----------------- Started :: click_on_expand ------------------");
			driver = ExecutionSetup.getDriver();
			landingp_webe = LandingP_WebE.getInstance(driver);
			WebElement table = landingp_webe.tbl_SearchResult;
			WebElement row = table.findElement(By.cssSelector("tr:nth-child(1) > td.control.center.expand-td"));
			row.click();
			Thread.sleep(1000);
			Log4J.logp.info("----------------- Ended :: click_on_expand ------------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problrm found in ::click_on_expand");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author nchourasiya
	 * @since 18/11/2014 This method is for expanding the case for other users than Supervisor
	 * */
	public static boolean expand_case()
	{
		try
		{
			Log4J.logp.info("-----------------Started :: expand_case --------------------");
			driver = ExecutionSetup.getDriver();
			landingp_webe = LandingP_WebE.getInstance(driver);
			WebElement table = landingp_webe.tbl_SearchResult;
			WebElement row = table.findElement(By.cssSelector("#searchResults > tbody > tr > td.expand-td.center"));
			row.click();
			Thread.sleep(1000);
			Log4J.logp.info("-----------------Ended :: expand_case --------------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: expand_case");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is check that data is available or not in landing page
	 * 
	 * @author agupta
	 * @since 5/9/2014
	 */
	public static boolean search_Status(int i)
	{
		try
		{
			Log4J.logp.info("-------------- Started :: search_Status ----------------------");
			driver = ExecutionSetup.getDriver();
			landingp_webe = LandingP_WebE.getInstance(driver);
			WebElement table = driver.findElement(By.cssSelector("#searchResults > tbody > tr"));
			List<WebElement> columns = table.findElements(By.tagName("td"));
			if (columns.size() >= 2)
			{
				Log4J.logp.info("Data is available for selected Row ID ::: SC00" + i);

			}
			else
			{
				Log4J.logp.info("No Data Available for selected Row ID ::: SC00" + i);
			}
			Log4J.logp.info("-------------- Ended :: search_Status ----------------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: search_Status");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is chech that default status and stage are selected or not
	 * 
	 * @author agupta
	 * @since 1/10/2014
	 */
	public static boolean default_Status_Stage()
	{
		try
		{
			Log4J.logp.info("-------------- Started :: default_Status_Stage -------------------");
			driver = ExecutionSetup.getDriver();
			searchcriteria_webe = SearchCriteria_WebE.getInstance(driver);
			searchcriteria_webe.lnk_SearchCreteria.click();
			Thread.sleep(2000);
			List<WebElement> statusList = driver.findElements(By.xpath("//*[@id='statusList']/label[not(@class='label dis-link')]"));
			Thread.sleep(2000);
			for (WebElement webElement : statusList)
			{
				webElement.getText();
				Log4J.logp.info("Default Status :: " + webElement.getText());
			}
			List<WebElement> stagelist = driver.findElements(By.xpath("//div[@id='stageList']/label[1]"));
			for (WebElement webElement : stagelist)
			{
				webElement.getText();
				Log4J.logp.info("Default Stage is ::" + webElement.getText());
			}
			Log4J.logp.info("-------------- Ended :: default_Status_Stage -------------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: default_Status_Stage");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is check first level navigation bar in landing page
	 * 
	 * @author agupta
	 * @since 4/9/2014
	 */
	public static boolean first_level_navigationBar()
	{
		try
		{
			Log4J.logp.info("------------- Started :: first_level_navigationBar ------------------");
			landingp_webe = LandingP_WebE.getInstance(driver);
			Thread.sleep(2000);
			Log4J.logp.info("1st level Navigation Bar");
			if (landingp_webe.lnk_Cases.isDisplayed())
			{
				Log4J.logp.info("Cases label is Displayed");
				Assert.assertTrue(landingp_webe.lnk_Cases.isDisplayed(), "Cases label is Displayed");
			}
			if (landingp_webe.lnk_Reports.isDisplayed())
			{
				Log4J.logp.info("Reports label is Displayed");
				Assert.assertTrue(landingp_webe.lnk_Reports.isDisplayed(), "Reports label is Displayed");
			}
			Log4J.logp.info("------------- Ended :: first_level_navigationBar ------------------");
			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: first_level_navigationBar");
			e.printStackTrace();
			return false;
		}
	}
}