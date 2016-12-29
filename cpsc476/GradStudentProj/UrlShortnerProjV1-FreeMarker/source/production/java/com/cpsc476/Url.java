package com.cpsc476;

public class Url {
     private String url;
     private int clicks=0;
     
     public Url(String url){
    	 this.url=url;
     }
     
     public void Click(){
    	 this.clicks+=1;
     }
     
     public String getUrl(){
    	 return this.url;
     }
     public int getClicks(){
    	 return this.clicks;
     }
}
