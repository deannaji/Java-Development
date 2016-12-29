package com.wrox;

public class PersonImpl implements PersonService {
    private String name;
	
	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	@Override
	public String sayName() {
		return "Hi, Iam a new person and my name is "+this.name;
		
	}
  
}
