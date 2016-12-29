package com.cpsc476.site.url;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class UrlDaoImp extends JdbcDaoSupport implements UrlDaoInterface {

	@Override
	public void insertoneurl(String username, String lurl, String surl, int clicks) {
		getJdbcTemplate().update("insert into project3.url(username,longurl,shorturl,clicks)values (?,?,?,?)", username,
				lurl, surl, clicks);

	}

	@Override
	public List<Url> findAllurl(String username) {
		return getJdbcTemplate().query(
				"select distinct m.longurl ,m.shorturl , (select sum(clicks)  from project3.url where shorturl = m.shorturl)as totalclicks from project3.url as m where username = ? ",
				new UrlMapper(), username);
	}

	@Override
	public Url checkforUrl(String username, String longurl) {
		return getJdbcTemplate().queryForObject(
				"Select count(longurl) as counts from project3.url where longurl = ? and username = ?",
				new urlcountMapper(), longurl, username);
	}

	@Override
	public Url checkifUrlExists(String shorturl) {
		return getJdbcTemplate().queryForObject("Select count(longurl) as counts from project3.url where shorturl = ?",
				new urlcountMapper(), shorturl);
	}

	@Override
	public Url getlongurl(String url) {
		return getJdbcTemplate().queryForObject("select distinct longurl from project3.url where SHORTURL = ?",
				new longurlMapper(), url);
	}

	@Override
	public void updateclicks(String surl, String username) {
		getJdbcTemplate().update("UPDATE project3.url set clicks = clicks+1  where shorturl = ? and username = ?", surl,
				username);
	}

	private static final class UrlMapper implements RowMapper<Url> {

		public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
			Url actor = new Url();
			actor.setSUrl(rs.getString("SHORTURL"));
			actor.setLongurl(rs.getString("LONGURL"));
			actor.setClicks(rs.getString("totalclicks"));

			return actor;
		}
	}

	private static final class longurlMapper implements RowMapper<Url> {

		public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
			Url actor = new Url();
			actor.setLongurl(rs.getString("LONGURL"));
			// actor.setClicks(rs.getString("clicks"));
			return actor;
		}
	}

	private static final class urlcountMapper implements RowMapper<Url> {

		public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
			Url actor = new Url();
			actor.seturlCount(rs.getInt("counts"));
			// actor.setClicks(rs.getString("clicks"));
			return actor;
		}
	}

}// End of UrlDao class.
