package com.cpsc476.ch3;

public class File {
	private String name;
	private byte[] content;
	
	public void setName(String filename){
		this.name= filename;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setContent(byte[] bytes){
		this.content = bytes;
	}
	public byte[] getContent(){
		return this.content;
	}

}
