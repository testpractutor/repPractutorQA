package com.ezdi.framework.common;

import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;

import com.ezdi.framework.driverscript.DriverTestNG;

/**
 * Log4 J Class
 * 
 * <p>
 * Log4j Class implementation
 * <p>
 * 
 * @author Jay Patel
 * @version 1.0
 * @since 01/03/2014
 */

public class Log4J
{
	public static org.apache.log4j.Logger	logf	= org.apache.log4j.Logger.getLogger("framework");
	public static org.apache.log4j.Logger	logp	= org.apache.log4j.Logger.getLogger("product");

	// public static Logger log = LoggerFactory.getLogger(Log4J.class);
	public static void loadLogger()
	{
		//static {

		String product = DriverTestNG.product;
		String qaBuild = DriverTestNG.strqabuild;
		String version = DriverTestNG.strVersion;
		String Browser = DriverTestNG.strBrowser;

		PropertyConfigurator.configure("src/main/resources/Properties/log4j.properties");
		PatternLayout pLayout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m - (in %M) %n");
		FileAppender faFrame = null;
		FileAppender faProd = null;
		try
		{
			String frameworkLogFilename = "Framework_Log_For_" + Browser + "_" + product + "_" + qaBuild + "_" + version + ".txt";
			String productLogFilename = "Log_" + Browser + "_" + qaBuild + "_" + version + ".txt";
			String productLogFilepath = "src/main/resources/Framework/Log/";
			faFrame = new FileAppender(pLayout, productLogFilepath + frameworkLogFilename, true);
			faProd = new FileAppender(pLayout, productLogFilepath + productLogFilename, true);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		logf.addAppender(faFrame);
		logp.addAppender(faProd);
	}
}
