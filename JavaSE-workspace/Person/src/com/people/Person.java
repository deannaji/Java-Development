package com.people; 

public class Person {
	   protected String name;
	   
	   public Person(String name){
		   this.name=name;
	   }
	   public void getName(){
		   System.out.println("My name is: "+this.name);
	   }
}


