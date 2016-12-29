package com.cpsc476;
/*
 * The job of this POJO, is to store shortUrl and clicks count, then this POJO is sent to the
 * view to be displayed. 
 */
public class ShortUrl {
	private String url;
    private String clicks;
    
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getClicks() {
		return clicks;
	}
	public void setClicks(String clicks) {
		this.clicks = clicks;
	}
    
}
