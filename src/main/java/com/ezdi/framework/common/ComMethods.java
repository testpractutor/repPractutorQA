package com.ezdi.framework.common;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComMethods
{

	@FindBy(name = "username")
	public static WebElement	txtUsername;

	@FindBy(name = "password")
	public static WebElement	txtPassword;

	@FindBy(id = "logon")
	public static WebElement	btnLogin;

	@FindBy(xpath = "//*[@id=\"suite-header-info-bar\"]/a[3]")
	public WebElement			btnLogout;

	//	@FindBy(id = "continueToSite")
	//	WebElement lnkOverridelink;
	//	

	@FindBy(id = "overridelink")
	public WebElement			lnkOverridelink;

	static WebDriver			driver;

	public static ComMethods getInstance(WebDriver driver)
	{
		return PageFactory.initElements(driver, ComMethods.class);
	}

	public static boolean Login(String username, String password)
	{

		try
		{
			//CommonLib.highlightElement(txtUsername);
			Thread.sleep(3000);
			//CommonMethodsForApp.DriverWait(driver.findElement(By.name("username")));

			txtUsername.sendKeys(username);
			txtPassword.sendKeys(password);
			btnLogin.click();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log4J.logf.error("Login Failed");
			Log4J.logf.error(e.getMessage());
			return false;
		}

	}

	public void Logout()
	{
		btnLogout.click();
	}

	//	public static void highlightElement(WebElement element) { 
	//		for (int i = 0; i < 2; i++) { 
	//			JavascriptExecutor js = (JavascriptExecutor) driver;
	//			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 2px solid yellow;");
	//			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
	//			}
	//		} 
	//	

}
