package com.ezdi.framework.driverscript;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlMethodSelector;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class CheckTestNG
{

	public static void main(String[] args)
	{

		XmlSuite suite = new XmlSuite();
		suite.setName("EZDI automation");

		XmlTest test = null;

		System.out.println();
		String className = "LoginTest";
		String fullClassName = "com.ezdi.testscripts.login." + className;

		System.out.println("fullClassName = " + fullClassName);
		test = new XmlTest(suite);

		test.setName(className);

		List<XmlClass> classes = new ArrayList<XmlClass>();
		//List <XmlMethodSelector> method = new ArrayList <XmlMethodSelector>();

		List<XmlInclude> methodsToRun = new ArrayList<XmlInclude>();
		//methodsToRun.add((new XmlInclude("loginTest1")));
		//classes.setIncludedMethods(methodsToRun);

		classes.add(new XmlClass(fullClassName));

		XmlClass testBeforeAfter = new XmlClass("com.ezdi.framework.driverscript.ExecutionSetup");

		testBeforeAfter.getIncludedMethods().add(new XmlInclude("setUp"));
		testBeforeAfter.getIncludedMethods().add(new XmlInclude("tearDown"));

		classes.add(testBeforeAfter);

		//	method.add(new XmlMethodSelector());

		//method.add
		test.setXmlClasses(classes);
		//test.setMethodSelectors();

		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		TestNG testng = new TestNG();
		testng.setXmlSuites(suites);
		testng.run();

	}

}
