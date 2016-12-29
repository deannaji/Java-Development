package com.wrox;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController{
    private EmployeesDaoInterface EmployeesDao;
	
    @ResponseBody
    @RequestMapping("/")
    public String helloWorld(){
    	//EmployeesDao.getEmployeeName();
   
    	Employee employee = EmployeesDao.getEmployee(1);
    	String employeeRow= employee.getId()+", "+employee.getFirstName()+" "+employee.getLastName()+", $"+employee.getSalary();
    	return employeeRow;
    }
    
    
    
    @ResponseBody
    @RequestMapping("/person")
    public String personInfo(){
    	return EmployeesDao.getPerson();
    }



	public void setEmployeesDao(EmployeesDaoInterface EmployeesDao) {
		this.EmployeesDao = EmployeesDao;
	}
    
    
    
}
