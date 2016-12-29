package com.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Employees{
	

	public static void displayEmployees(ResultSet rs) throws SQLException{
		while(rs.next()){
			 Employee bean = new Employee();
			 bean.setEmployeeId(rs.getInt("id"));
			 bean.setEmployeeName(rs.getString("name"));
			 
			 System.out.print(bean.getEmployeeId()+ ": ");
			 System.out.println(bean.getEmployeeName());
		 }
     }
}
