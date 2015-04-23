package com.ezdi.library;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Map;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ezdi.framework.common.JDBCMySql;
import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.driverscript.ExecutionSetup;
import com.ezdi.webelements.PracCityElements_WebE;



public class LearningPathOp_Lib {
	
	static WebDriver driver;
	static WebDriverWait wait;
	static PracCityElements_WebE praccityelements_webe;
	
	/**
	 * This method is to automate buying car from praccity
	 * 
	 * @author bchauhan
	 * @since 23/4/2015
	 */
	
	public static boolean buyCityElement_App() 
	{		
		try
		{
			Log4J.logp.info("----------------- Started :: buyCityElement_App------------------");
			driver = ExecutionSetup.getDriver();
			praccityelements_webe = PracCityElements_WebE.getInstance(driver);
			
			praccityelements_webe.btn_OpenCityStore.click();
			praccityelements_webe.btn_BuyVehicle.click();
			praccityelements_webe.btn_BuyCar.click();
			Thread.sleep(2000);
			Common_Lib.waitForObject(praccityelements_webe.div_CityRoadArea, "clickable", 30);
						
			Robot robot=new Robot();
			Point point = praccityelements_webe.btn_BuyVehicle.getLocation();
			robot.mouseMove(point.getX(),point.getY()-30); 
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
								
			Common_Lib.waitForObject(praccityelements_webe.btn_ConfirmCityElementBuy, "clickable", 30);
			praccityelements_webe.btn_ConfirmCityElementBuy.click();
			Common_Lib.waitForObject(praccityelements_webe.msg_ConfirmMsgforBuyCityElement, "visibility", 30);
						
			if (praccityelements_webe.msg_ConfirmMsgforBuyCityElement != null) {
				Log4J.logp.info("Buying City Element sucessfull for Student");
				
				return true;
			} else {
				Log4J.logp.error("Buying City Element UN-sucessfull for Student");
				return false;
			}			
			
			
		}
		catch(Exception e)
		{
			Log4J.logp.error("Problrm found in ::buyCityElement_App");
			e.printStackTrace();
			return false;
		}
	}
	
}
