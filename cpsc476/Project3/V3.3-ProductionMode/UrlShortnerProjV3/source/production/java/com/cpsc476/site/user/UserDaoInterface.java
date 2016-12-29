package com.cpsc476.site.user;

import com.cpsc476.site.url.Url;

public interface UserDaoInterface {

	User getOneRow(String username,String password);

	void insertOneRow(String username, String password);

	User checkforuser(String username);

}