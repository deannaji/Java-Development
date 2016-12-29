package com.wrox;

import org.apache.commons.dbcp2.BasicDataSource;

public interface EmployeesDaoInterface {

	String getEmployeeName();
	
	Employee getEmployee(int id);

	String getPerson();

	void setPersonService(PersonService personService);

	void setdataSource(BasicDataSource dataSource);

}