package com.cpsc476;

public class Attachment {
	private String fileName;
	private byte[] content;
	
	public void setFileName(String name){
		this.fileName= name;
	}
	
	public String getFileName(){
		return this.fileName;
	}
	
	public void setContent(byte[] bytes){
		this.content = bytes;
	}
	public byte[] getContent(){
		return this.content;
	}
}