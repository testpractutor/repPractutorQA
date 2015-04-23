package com.ezdi.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PracCityElements_WebE {
	
	/** To click OpenCityElements button */
	@FindBy(id="cityStoreCloseLabel")
	public WebElement	btn_OpenCityStore;
	
	/** To click CloseCityElements button */
	@FindBy(id="cityStoreCloseImg")
	public WebElement	btn_CloseCityStore;
	
	/** To click on Vehicle tab in PracCity */
	@FindBy(id="VehicleLabelDiv")
	public WebElement	btn_BuyVehicle;
	
	/** To click on Buy button for car*/
	@FindBy(id="Vehicle35")
	public WebElement	btn_BuyCar;
	
	/** Area to drop the vehicle in City Area*/
	@FindBy(id="roadDiv")
	public WebElement	div_CityRoadArea;
	
	/** To click on Confirm button to confirm the buying */
	@FindBy(id="conformDrag")
	public WebElement	btn_ConfirmCityElementBuy;
	
	/** To click on Cancel button to cancel the buying */
	@FindBy(id="cancelDrag")
	public WebElement	btn_CancelCityElementBuy;		
	
	/** The message which shows buying is successful */
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
