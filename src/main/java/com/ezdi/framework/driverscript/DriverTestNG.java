package com.ezdi.framework.driverscript;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.WebDriver;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.ezdi.framework.common.ExecutionConfig;
import com.ezdi.framework.common.JDBCMySql;
import com.ezdi.framework.common.Log4J;
import com.ezdi.framework.common.MailTestOutput;
import com.ezdi.framework.common.TestCases;

/**
 * @author Jay Patel
 * @version 1.0
 * @since 02/08/2014
 */

public class DriverTestNG
{

	public static String	url, product;
	public static String	username, password;
	public static String	predata;
	public static String	INDUSTRY_TYPE;
	public static String	strBrowser;
	public static String	strqabuild, strVersion;
	public boolean			testStatus, blnLoginStatus;
	public static JDBCMySql	selectedConfig;
	public static String	mailTo	= null;

	public static WebDriver	driver;

	public static void main(String[] args) throws Exception
	{

		selectedConfig = new JDBCMySql();
		// selectedConfig.loadExcel();
		// selectedConfig.loadExcel("Resources/Framework/Configuration/SelectedConfig.db");

		// selectedConfig.getValuebyParamName("productconfig","Product");

		username = selectedConfig.getValuebyParamName("productconfig", "UserName");
		password = selectedConfig.getValuebyParamName("productconfig", "Password");
		url = selectedConfig.getValuebyParamName("productconfig", "URL");
		strqabuild = selectedConfig.getValuebyParamName("productconfig", "QA Build");
		strVersion = selectedConfig.getValuebyParamName("productconfig", "Version");
		strBrowser = selectedConfig.getValuebyParamName("productconfig", "Browser");
		predata = selectedConfig.getValuebyParamName("productconfig", "Prepare Data");
		mailTo = selectedConfig.getValuebyParamName("productconfig", "mailto");

		Log4J.loadLogger();

		Log4J.logf.info("***************************************************** ");
		Log4J.logf.info("***** EZDI Execution Started *****");

		List<ExecutionConfig> listModule = selectedConfig.getModules();

		XmlSuite suite = new XmlSuite();
		suite.setName("EZDI Automation Execution");

		XmlTest test = null;
		List<XmlClass> classes = null;

		if ((predata.toUpperCase() == "FALSE") && listModule.size() == 0)
		{
			JOptionPane.showMessageDialog(null, "NO MODULES SELECTED FOR EXECUTION", "From ezdi Automation", 0);
			selectedConfig.connectionClose();
		}
		else
		{

			JDBCMySql productTestCases = new JDBCMySql();
			// productTestCases.loadExcel("Resources/TestCases/MPV_TestCases.db");
			test = new XmlTest(suite);
			classes = new ArrayList<XmlClass>();

			// add before and after

			XmlClass testBeforeAfter = new XmlClass("com.ezdi.framework.driverscript.ExecutionSetup");

			testBeforeAfter.getIncludedMethods().add(new XmlInclude("setUp"));
			testBeforeAfter.getIncludedMethods().add(new XmlInclude("tearDown"));

			classes.add(testBeforeAfter);
			ArrayList<String> arr_Modules = new ArrayList<String>();
			XmlClass testClass = null;

			for (int i = 0; i < listModule.size(); i++)
			{
				ExecutionConfig module = listModule.get(i);
				String currnetModule = module.getStrModuleName();

				currnetModule = currnetModule.replaceAll("\\s+", "");
				arr_Modules.add(currnetModule);

				String fullClassName = "com.ezdi.testscripts." + currnetModule;

				List<TestCases> listActions = productTestCases.getActionList(module);

				int occurrences = Collections.frequency(arr_Modules, currnetModule);

				if (occurrences == 1)
				{
					testClass = new XmlClass(fullClassName);
					testClass.getIncludedMethods().add((new XmlInclude(currnetModule.toLowerCase() + "BeforeClass")));
					testClass.getIncludedMethods().add((new XmlInclude(currnetModule.toLowerCase() + "AfterClass")));

				}

				for (int j = 0; j < listActions.size(); j++)
				{
					testClass.getIncludedMethods().add(new XmlInclude(listActions.get(j).getStrAction()));
					Log4J.logf.info("Method included : " + listActions.get(j));
				}
				classes.add(testClass);
				currnetModule = null;
				// includeLibrary = null;
			}

			test.setXmlClasses(classes);
			List<XmlSuite> suites = new ArrayList<XmlSuite>();
			suites.add(suite);
			TestNG testng = new TestNG();
			testng.setXmlSuites(suites);
			testng.run();
			MailTestOutput.sendMail();
			Log4J.logf.info("***** EZDI Execution Completed *****");

		}
	}
}
