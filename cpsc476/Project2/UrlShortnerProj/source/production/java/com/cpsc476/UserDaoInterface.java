package com.cpsc476;

public interface UserDaoInterface {

	Url getOneRow(String username,String password);

	void insertOneRow(String username, String password);

	Url checkforuser(String username);

}