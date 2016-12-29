package com.wrox;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class EmployeesDao extends JdbcDaoSupport implements EmployeesDaoInterface{
	private PersonService personService;
	private BasicDataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    
        
    @Override
	public String getEmployeeName() {
    	String firstName = (String) jdbcTemplate.queryForObject("Select firstname from MYCOMPANY.employees where id=1", String.class);
    	String lastName = (String) jdbcTemplate.queryForObject("Select lastname from MYCOMPANY.employees where id=1", String.class);
		return firstName+" "+lastName;
	}
    
    
    @Override
	public Employee getEmployee(int id){
    	jdbcTemplate= new JdbcTemplate(dataSource);    	
    	
    	String sql="Select id,firstname,lastname,salary from MYCOMPANY.employees where id=?";
    	
    	Employee employee = (Employee)getJdbcTemplate().queryForObject(
    			sql, new Object[] { id }, new EmployeeRowMapper());
    	
        return employee;
    }
 
  
    
    //Getters & Setters:
    @Override
	public String getPerson(){
    	personService.setName("Dean");
    	return personService.sayName();
    }
    
    @Override
	public void setPersonService(PersonService personService){
    	this.personService = personService;
    }
    
    @Override
	public void setdataSource(BasicDataSource dataSource){
         this.dataSource = dataSource;
    }

    
    
}
