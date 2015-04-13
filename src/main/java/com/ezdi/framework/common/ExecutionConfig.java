package com.ezdi.framework.common;

public class ExecutionConfig
{
	String	strModuleName;
	String	strTestCat;
	String	strDependency;
	String	strRiskLevel;

	public ExecutionConfig(String strModuleName, String strTestCat, String strRiskLevel)
	{
		super();
		this.strModuleName = strModuleName;
		this.strTestCat = strTestCat;
		//this.strDependency = strDependency;
		this.strRiskLevel = strRiskLevel;
	}

	public String getStrModuleName()
	{
		return strModuleName;
	}

	public void setStrModuleName(String strModuleName)
	{
		this.strModuleName = strModuleName;
	}

	public String getStrTestCat()
	{
		return strTestCat;
	}

	public void setStrTestCat(String strTestCat)
	{
		this.strTestCat = strTestCat;
	}

	//	public String getStrDependency() {
	//		return strDependency;
	//	}

	//	public void setStrDependency(String strDependency) {
	//		this.strDependency = strDependency;
	//	}

	public void setStrRiskLevel(String strRiskLevel)
	{
		this.strRiskLevel = strRiskLevel;
	}

	public String getStrRiskLevel()
	{
		return strRiskLevel;
	}

	@Override
	public String toString()
	{
		return "ExecutionConfig [strModuleName=" + strModuleName + ", strTestCat=" + strTestCat + ", strDependency=" + strDependency + "]";
	}
}
