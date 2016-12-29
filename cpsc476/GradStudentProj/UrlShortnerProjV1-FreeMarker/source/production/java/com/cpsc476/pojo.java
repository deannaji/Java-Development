package com.cpsc476;

import java.util.HashMap;
import java.util.Map;

//user object template
public class pojo {

	String username;
	protected Map<String, Object> database= new HashMap<>(); 
	public pojo(String username){
		this.username = username;
	}
	public void insertUrl( String l, Url u){
		database.put(l, u);

	}
	public void showpojo(){
		//System.out.println("user: "+this.username+" Map:"+this.database);
	}
}
