package com.ezdi.framework.common;

public class TestCases
{

	private String	strAction;

	public TestCases(String... params)
	{
		this.strAction = params[0];

	}

	public String getStrAction()
	{
		return strAction;
	}
}
