package com.cpsc476.site;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class UserDao extends JdbcDaoSupport implements UserDaoInterface{

	/* (non-Javadoc)
	 * @see com.cpsc476.UserDaoInterface#getOneRow(java.lang.String)
	 */
	@Override
	public Url getOneRow(String username,String password){
		return getJdbcTemplate().queryForObject("Select count(username) as counts from project2.userdetails where username = ? and password = ?", new userMapper(),username,password);

	}


	/* (non-Javadoc)
	 * @see com.cpsc476.UserDaoInterface#insertOneRow(java.lang.String, java.lang.String)
	 */
	@Override
	public void insertOneRow(String username, String password){

		getJdbcTemplate().update("insert into project2.userdetails(username, password) values (?,?)",username,password);

	}



	/* (non-Javadoc)
	 * @see com.cpsc476.UserDaoInterface#checkforuser(java.lang.String)
	 */
	@Override
	public Url checkforuser(String username){
		return getJdbcTemplate().queryForObject("Select count(username) as counts from project2.userdetails where username = ?", new usercheckerMapper(),username);
	}
	private static final class userMapper implements RowMapper<Url> {

		public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
			Url actor = new Url();
			actor.setUserCount(rs.getInt("counts"));

			//actor.setUsername(rs.getString("username"));
			//actor.setPassword(rs.getString("password"));
			return actor;
		}
	}


	private static final class usercheckerMapper implements RowMapper<Url> {

		public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
			Url actor = new Url();
			actor.setUserCount(rs.getInt("counts"));
			/*actor.setPassword(rs.getString("password"));*/
			return actor;
		}
	}
}