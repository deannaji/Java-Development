package com.cpsc476.site.url;

public class Url {
	// long url
	private String url;

	private String shorturl;
	private String longurl;
	private String username;
	private String password;

	// clicks:
	private int clicks = 0;
	private int c;
	private int u;

	public void setSUrl(String shorturl) {
		this.shorturl = shorturl;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Url(String url) {
		this.url = url;
	}

	public Url() {
		// TODO Auto-generated constructor stub
	}

	public void setClicks(String c) {
		this.clicks = Integer.parseInt(c);
	}

	public void Click() {
		this.clicks += 1;
	}

	public void setUserCount(int count) {
		this.c = count;
	}

	public int getuserCount() {
		return this.c;
	}

	public void seturlCount(int count) {
		this.u = count;
	}

	public int geturlCount() {
		return this.u;
	}

	public String getSUrl() {
		return this.shorturl;
	}

	public String getLongurl() {
		return longurl;
	}

	public void setLongurl(String longurl) {
		this.longurl = longurl;
	}

	public String getUrl() {
		return this.url;
	}

	public int getClicks() {
		return this.clicks;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

}
