package com.ezdi.framework.common;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.pagefactory.Annotations;

public class ComframUtils
{
	public static WebDriver	driver	= null;

	public static WebDriver launchURL(String browser)
	{

		if (browser.toUpperCase().equals("IE"))
		{
			//File file = new File("Resources\\Framework\\Drivers\\IEDriverServer.exe");
			File file = new File("src/main/resources/Framework/Drivers/Windows/IEDriverServer_Win32_2.40.0/IEDriverServer.exe");

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			//capabilities.setCapability("nativeEvents", false);
			//capabilities.setJavascriptEnabled(true);

			System.setProperty("webdriver.ie.driver", file.getPath());
			

			driver = new InternetExplorerDriver(capabilities);
			Log4J.logf.info("The Browser is IE");
		}
		else if (browser.toUpperCase().equals("FIREFOX"))
		{

			// ProfilesIni allProfiles = new ProfilesIni();
			FirefoxProfile profile = new FirefoxProfile();// allProfiles.getProfile("Your-profile-name");
			profile.setPreference("intl.accept_languages", "no,en-us,en");
			driver = new FirefoxDriver();
			Log4J.logf.info("The Browser is Firefox");

		}
		else if (browser.toUpperCase().equals("CHROME"))
		{

			if (System.getProperty("os.name").contains("Windows"))
			{
				System.setProperty("webdriver.chrome.driver", "src/main/resources/Framework/Drivers/Windows/chromedriver.exe");
			}
			else
			{
				System.setProperty("webdriver.chrome.driver", "src/main/resources/Framework/Drivers/Linux/chromedriver");
			}
			driver = new ChromeDriver();
			Log4J.logf.info("The Browser is Chrome");

		}
		return driver;
	}

	public By getBy(String fieldName)
	{
		try
		{
			return new Annotations(this.getClass().getDeclaredField(fieldName)).buildBy();
		}
		catch (NoSuchFieldException e)
		{
			return null;
		}
	}

}
