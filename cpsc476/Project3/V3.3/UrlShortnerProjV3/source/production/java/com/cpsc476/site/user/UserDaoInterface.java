package com.cpsc476.site.user;

import com.cpsc476.site.url.Url;

public interface UserDaoInterface {

	Url getOneRow(String username,String password);

	void insertOneRow(String username, String password);

	Url checkforuser(String username);

}