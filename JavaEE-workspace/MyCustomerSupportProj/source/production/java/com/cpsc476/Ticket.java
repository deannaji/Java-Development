package com.cpsc476;
import java.util.HashMap;
import java.util.Map;

public class Ticket {
   private String customerName;
   private Map<String, Attachment> files= new HashMap<>();
   //private String body;
   //private String subject;
   
   public void setName(String name){
	   this.customerName = name;
   }
   
   public String getName(){
	   return this.customerName;
   }
   
   public void addFile(Attachment file){
	   this.files.put(file.getFileName(), file);
   }
   
   public Attachment getFile(String name){
	   return this.files.get(name);
   }
   
   
}