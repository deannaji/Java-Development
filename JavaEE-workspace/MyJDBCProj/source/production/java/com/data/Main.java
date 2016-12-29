package com.data;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	  private static final String USERNAME="root";
	  private static final String PASSWORD="root";
	  private static final String CONNECTION_STR="jdbc:mysql://localhost/company?useSSL=false";

	public static void main(String[] args) throws SQLException {
		
		try(
		 Connection conn= DriverManager.getConnection(CONNECTION_STR,USERNAME,PASSWORD);
		 Statement stmt= conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		 ResultSet rs= ((java.sql.Statement) stmt).executeQuery("select * from employees");
				
		){
			 System.out.println("Connected to MySQl DB!");
			 Employees.displayEmployees(rs);
			 	 
		}
		catch(SQLException e){
			System.err.println(e);
		}

	}

}
