package com.ezdi.framework.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCSQLLite
{

	private Connection	con;
	private ResultSet	resultSet	= null;
	private Statement	st;
	private String		strParamValue;

	public void loadExcel(String path)
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			this.con = DriverManager.getConnection("jdbc:sqlite:" + path);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			// Log4J.logp.error("File not found at : " + path);
			// Log4J.logp.error(e.getMessage());
		}

	}

	public Map<String, String> getRowbyID(String sheet, String id) throws SQLException
	{
		Map<String, String> map = new HashMap<String, String>();
		String strQuery = "SELECT * FROM " + sheet + " WHERE ID = '" + id + "'";
		try
		{
			st = con.createStatement();
			resultSet = st.executeQuery(strQuery);
			ResultSetMetaData rmd = resultSet.getMetaData();
			while (resultSet.next())
			{
				int columntCount = rmd.getColumnCount();
				for (int i = 1; i <= columntCount; i++)
				{
					map.put(rmd.getColumnName(i), resultSet.getString(i));
				}
			}
			resultSet.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		return map;
	}

	public String getValuebyParamName(String table, String paramName) throws SQLException
	{
		String strQuery = "SELECT * FROM " + table + " WHERE ParamName = '" + paramName + "'";
		try
		{
			st = con.createStatement();
			resultSet = st.executeQuery(strQuery);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		while (resultSet.next())
		{
			strParamValue = resultSet.getString("ParamValue");
		}
		resultSet.close();
		return strParamValue;
	}

	public List<ExecutionConfig> getModules() throws SQLException
	{
		List<ExecutionConfig> listReturn = new ArrayList<ExecutionConfig>();
		String strQuery = "SELECT * FROM ExecutionConfig WHERE SelectedToExecute = 'Yes' and Executed = 'No' Order by Ranking";
		try
		{
			st = con.createStatement();
			resultSet = st.executeQuery(strQuery);
			while (resultSet.next())
			{
				ExecutionConfig obj = new ExecutionConfig(resultSet.getString("ModuleName"), resultSet.getString("TestCategory"), resultSet.getString("RiskLevel"));
				listReturn.add(obj);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		resultSet.close();
		return listReturn;
	}

	public List<TestCases> getActionList(ExecutionConfig selectedConfig) throws SQLException
	{
		String strModule = selectedConfig.strModuleName;
		String strTestCategory = selectedConfig.strTestCat;
		String strRiskLevel = selectedConfig.strRiskLevel;
		List<TestCases> listReturn = new ArrayList<TestCases>();
		String strQuery = "SELECT * FROM  " + strModule + " WHERE Enabled = 1 and TestExecution='Automated' and TestCategory='" + strTestCategory + "' and RiskLevel = '" + strRiskLevel + "'";
		//+ "' and IndustryType In ('All','" + DriverScript.INDUSTRY_TYPE + "')";
		try
		{
			st = con.createStatement();
			resultSet = st.executeQuery(strQuery);
			while (resultSet.next())
			{
				TestCases obj = new TestCases(resultSet.getString("Action"));
				listReturn.add(obj);
			}
		}
		catch (SQLException e)
		{
			// Log4J.logp.error(e.getMessage(), e);
			e.printStackTrace();
			return null;
		}
		resultSet.close();
		return listReturn;
	}

	// public int getRowCount(ResultSet resultSet) throws SQLException {
	// while (resultSet.next()) {
	// ++i;
	// }
	// return i;
	// }
	public void updateExecutionConfig(String strModuleName, String strTestCategory) throws SQLException
	{
		String strQuery = "update [ExecutionConfig$] set Executed = 'Yes' where ModuleName = '" + strModuleName + "' and TestCategory = '" + strTestCategory + "'";
		st = con.createStatement();
		int updateRow = st.executeUpdate(strQuery);
		System.out.println("updateRow = " + updateRow);
	}

	public void connectionClose() throws SQLException
	{
		if (con != null)
			con.close();
	}

	public Connection getCon()
	{
		return con;
	}

	public Statement getSt()
	{
		return st;
	}

	public void closeStmt() throws SQLException
	{
		if (st != null)
			st.close();
	}

}
