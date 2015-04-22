package com.ezdi.library;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Map;

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
	
	public static boolean buyCityElement_App() 
	{
		
		try
		{
			driver = ExecutionSetup.getDriver();
			praccityelements_webe = PracCityElements_WebE.getInstance(driver);
			
			praccityelements_webe.btn_OpenCityStore.click();
			praccityelements_webe.btn_BuyVehicle.click();
			praccityelements_webe.btn_BuyCar.click();
			praccityelements_webe.div_CityArea.click();
			
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
			Log4J.logf.error("buyCityElement - unsucessful");
			return false;
		}
	}
	
}
