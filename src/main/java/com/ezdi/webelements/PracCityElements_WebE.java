package com.ezdi.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PracCityElements_WebE {
	
	@FindBy(id="cityStoreCloseLabel")
	public WebElement	btn_OpenCityStore;
	
	@FindBy(id="cityStoreCloseImg")
	public WebElement	btn_CloseCityStore;
	
	@FindBy(id="VehicleLabelDiv")
	public WebElement	btn_BuyVehicle;
	
	@FindBy(id="Vehicle35")
	public WebElement	btn_BuyCar;
	
	@FindBy(id="backgroundLowerDiv")
	public WebElement	div_CityArea;
	
	@FindBy(id="conformDrag")
	public WebElement	btn_ConfirmCityElementBuy;
	
	@FindBy(id="cancelDrag")
	public WebElement	btn_CancelCityElementBuy;		
	
	@FindBy(id="submitMsgDiv")
	public WebElement	msg_ConfirmMsgforBuyCityElement;
	
	static PracCityElements_WebE	INSTANCE	= null;
	
	public static PracCityElements_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, PracCityElements_WebE.class);
		}
		return INSTANCE;
	}
			
}	
