package com.cpsc476.site.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class UserDaoImp extends JdbcDaoSupport implements UserDaoInterface{

	
	@Override
	public User getOneRow(String username,String password){
		//return getJdbcTemplate().queryForObject("Select count(username) as counts from project2.userdetails where username = ? and password = ?", new userMapper(),username,password);
		
		User user = new User();
		//if user been found on the DB: 
		if (UsersDataBase.usersDataBase.get(username) != null){
			user.setUserCount(1);
		}
		else{
			user.setUserCount(0);
		}
		return user;
	}


	
	@Override
	public void insertOneRow(String username, String password){

		//getJdbcTemplate().update("insert into project2.userdetails(username, password) values (?,?)",username,password);
        UsersDataBase.usersDataBase.put(username, password);
	}


	@Override
	public User checkforuser(String username){
		//return getJdbcTemplate().queryForObject("Select count(username) as counts from project2.userdetails where username = ?", new usercheckerMapper(),username);
		
		User user = new User();
		//if user been found on the DB: 
		if (UsersDataBase.usersDataBase.get(username) != null){
			user.setUserCount(1);
		}
		else{
			user.setUserCount(0);
		}
		return user;
	}
	
	
	/*private static final class userMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserCount(rs.getInt("counts"));
			return user;
		}
	}*/


	/*private static final class usercheckerMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserCount(rs.getInt("counts"));
			return user;
		}
	}*/
}