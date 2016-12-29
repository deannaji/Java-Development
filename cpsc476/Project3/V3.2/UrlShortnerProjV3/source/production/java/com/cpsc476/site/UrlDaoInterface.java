package com.cpsc476.site;

import java.util.List;

public interface UrlDaoInterface {

	Url checkforUrl(String username, String longurl ) ;

	void insertoneurl(String username, String lurl, String surl, int clicks);

	List<Url> findAllurl(String username);

	Url getlongurl(String url);

	void updateclicks(String surl, String username);
	
	Url checkifUrlExists(String shorturl);

}