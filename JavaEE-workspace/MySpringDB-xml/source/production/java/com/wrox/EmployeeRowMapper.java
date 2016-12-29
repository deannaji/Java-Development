package com.wrox;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setFirstName(rs.getString("firstname"));
		employee.setLastName(rs.getString("lastname"));
		employee.setSalary(rs.getDouble("salary"));
		return employee;
	}

}
