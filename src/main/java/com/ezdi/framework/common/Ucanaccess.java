package com.ezdi.framework.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ucanaccess
{

	public static void main(String[] args) throws Exception
	{

		//Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		//Connection conn=DriverManager.getConnection("jdbc:ucanaccess://C://Jay//workspace//EzdiAutomation//Resources//Database1.mdb");
		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://Resources//Database2.accdb");

		Statement s = conn.createStatement();

		ResultSet rs = s.executeQuery("SELECT * FROM [ProductConfig] where ParamName = 'URL'");

		while (rs.next())
		{
			//System.out.println(rs.getString());
			//System.out.println(rs.getString("ParamValue"));
		}

	}
}