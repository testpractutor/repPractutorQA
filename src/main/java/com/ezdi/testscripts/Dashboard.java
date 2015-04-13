package com.ezdi.testscripts;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.library.Dashboard_Lib;
import com.ezdi.webelements.Comman_WebE;
import com.ezdi.webelements.GroupinPnl_WebE;
import com.ezdi.webelements.IssuePnl_WebE;
import com.ezdi.webelements.LandingP_WebE;
import com.ezdi.webelements.MedicalRecordPnl_WebE;
import com.ezdi.webelements.SearchCriteria_WebE;

public class Dashboard
{
	public static WebDriver				driver;
	public static LandingP_WebE			landingp_webe;
	public static Comman_WebE			common_webe;
	public static WebDriverWait			wait;
	public static SearchCriteria_WebE	searchcriteria_webe;
	public static GroupinPnl_WebE		groupingpnl_webe;
	public static IssuePnl_WebE			issuepnl_webe;
	public static MedicalRecordPnl_WebE	medicalrecordpnl_webe;

	@BeforeClass
	public static void DashboardBeforeClass()
	{
		try
		{
			driver = ExecutionSetup.getDriver();
			landingp_webe = LandingP_WebE.getInstance(driver);
			common_webe = Comman_WebE.getInstance(driver);
			searchcriteria_webe = SearchCriteria_WebE.getInstance(driver);
			groupingpnl_webe = GroupinPnl_WebE.getInstance(driver);
			issuepnl_webe = IssuePnl_WebE.getInstance(driver);
			medicalrecordpnl_webe = MedicalRecordPnl_WebE.getInstance(driver);
			Log4J.logp.info("In BeforeClass for LandingPage");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public static void uiTesting()
	{
		//boolean b = true;
		try
		{

			Log4J.logp.info("Started :::: uiTestting");
			driver.manage().window().setSize(new Dimension(1366, 768));
			/*landingp_webe.lnk_Coding.click();
			Thread.sleep(2000);
			Common_Lib.openCase("DB001");
			Thread.sleep(15000);
			Common_Lib.rightclick_system_sugggested_evidence();
			Thread.sleep(2000);
			medicalrecordpnl_webe.lnk_discusswithcolleague.click();
			Thread.sleep(2000);
			IssuePanel_Lib.send_DiscussWithColleague("DB001");
			Thread.sleep(2000);
			Common_Lib.rightclick_system_sugggested_evidence();
			Thread.sleep(2000);
			medicalrecordpnl_webe.lnk_querytophysician.click();
			IssuePanel_Lib.send_QueryToPhysician("DB001");
			Thread.sleep(2000);
			issuepnl_webe.btn_Issues.click();
			Thread.sleep(2000);
			groupingpnl_webe.btn_Later.click();
			Thread.sleep(3000);*/
			landingp_webe.lnk_All.click();
			Thread.sleep(2000);
			landingp_webe.lnk_Reports.click();
			Thread.sleep(2000);
			landingp_webe.lnk_CodingDashboard.click();
			Thread.sleep(2000);
			JavascriptExecutor jse;
			driver = ExecutionSetup.getDriver();
			jse = (JavascriptExecutor) driver;
			//scroll downward
			jse.executeScript("window.scrollBy(0,80)", "");
			//jse.executeScript("window.scrollBy(0,600)", "");
			WebElement e = driver.findElement(By.id("dischargeMain"));
			// Get entire page screenshot
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(screenshot, new File("D:/images/GoogleLogo_screenshot_main.jpg"));
			BufferedImage fullImg = ImageIO.read(screenshot);
			// Get the location of element in the page
			Point point = e.getLocation();
			System.out.println("Point is = " + point);
			int x = point.getX();
			System.out.println("X values is = " + x);
			int y = point.getY();
			System.out.println("Y value is = " + y);
			//Get width and height of the element

			int eleWidth = e.getSize().getWidth();
			System.out.println("Width is = " + eleWidth);
			int eleHeight = e.getSize().getHeight();
			System.out.println("Height is = " + eleHeight);
			//Crop the entire page screenshot to get only element screenshot
			System.out.println("x = " + point.getX() + " y = " + point.getY() + "width = " + e.getSize().getWidth() + "height =" + e.getSize().getHeight());
			Thread.sleep(3000);
			BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY() - 80, e.getSize().getWidth(), e.getSize().getHeight());
			Thread.sleep(3000);
			File ak = new File("image.jpg");
			ImageIO.write(eleScreenshot, "jpg", ak);
			FileUtils.copyFile(ak, new File("D:\\images\\sample.jpg"));
			//ImageIO.write(eleScreenshot, "png", screenshot);
			BufferedImage img1 = Dashboard_Lib.resize(eleScreenshot, 1295, 488);
			Thread.sleep(3000);
			File outputfile = new File("image.jpg");
			ImageIO.write(img1, "jpg", outputfile);

			//Copy the element screenshot to disk
			// Expected image
			FileUtils.copyFile(outputfile, new File("D:\\images\\akash.jpg"));

			String file1 = "D:\\images\\akash.jpg";
			String file2 = "D:\\images\\gupta.jpg";

			// Comparing the two images for number of bands, width & Height

			Image pic1 = Toolkit.getDefaultToolkit().getImage(file1);
			Image pic2 = Toolkit.getDefaultToolkit().getImage(file2);

			try
			{

				PixelGrabber grab11 = new PixelGrabber(pic1, 0, 0, -1, -1, false);
				PixelGrabber grab21 = new PixelGrabber(pic2, 0, 0, -1, -1, false);

				int[] array1 = null;

				if (grab11.grabPixels())
				{
					int width = grab11.getWidth();
					System.out.println("Width of Image 1 = " + width);
					int height = grab11.getHeight();
					System.out.println("Height of Image 1 = " + height);
					array1 = new int[width * height];
					array1 = (int[]) grab11.getPixels();
				}

				int[] array2 = null;

				if (grab21.grabPixels())
				{
					int width = grab21.getWidth();
					System.out.println("Width of Image 2 = " + width);
					int height = grab21.getHeight();
					System.out.println("Height of Image 2 = " + height);
					array2 = new int[width * height];
					array2 = (int[]) grab21.getPixels();
				}

				System.out.println(array1.length);

				System.out.println(array2.length);

				for (int i = 0; i < array2.length; i++)
				{
					if (array1[i] != array2[i])
					{

						System.out.println(array1[i] + " ! =" + array2[i]);
					}
				}

				System.out.println("Pixels equal: " + java.util.Arrays.equals(array1, array2));

			}
			catch (InterruptedException e1)
			{
				e1.printStackTrace();
			}
			Log4J.logp.info("Ended :::: uiTestting");

		}
		catch (Exception e)
		{
			// report error
			e.printStackTrace();

		}
	}
}