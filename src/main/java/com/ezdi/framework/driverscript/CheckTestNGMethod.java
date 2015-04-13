package com.ezdi.framework.driverscript;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class CheckTestNGMethod
{

	public static void main(String[] args)
	{

		XmlSuite suite = new XmlSuite();
		suite.setName("EZDI Automation");
		//  suite.setAnnotations(AnnotationTypeEnum.JDK.getName());

		XmlTest test = new XmlTest(suite);

		String className = "LoginTest";
		String fullClassName = "com.ezdi.testscripts.login." + className;

		//  String className = "LoginTest";
		test.setName("EZDI Automation - " + fullClassName);
		List<XmlClass> testClasses = new ArrayList<XmlClass>();

		XmlClass testBeforeAfter = new XmlClass("com.ezdi.framework.driverscript.ExecutionSetup");

		testBeforeAfter.getIncludedMethods().add(new XmlInclude("setUp"));
		testBeforeAfter.getIncludedMethods().add(new XmlInclude("tearDown"));

		testClasses.add(testBeforeAfter);

		XmlClass testClass = new XmlClass(fullClassName);

		testClass.getIncludedMethods().add(new XmlInclude("testLogin2"));
		testClass.getIncludedMethods().add(new XmlInclude("testLogin1"));
		testClasses.add(testClass);

		XmlClass testClass1 = new XmlClass("com.ezdi.testscripts.landingpage.LandingPageTest");
		testClass1.getIncludedMethods().add(new XmlInclude("landingPagetest1"));
		testClass1.getIncludedMethods().add(new XmlInclude("landingPagetest2"));

		testClasses.add(testClass1);

		test.setXmlClasses(testClasses);

		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		TestNG testng = new TestNG();
		testng.setXmlSuites(suites);
		testng.run();

	}

}
