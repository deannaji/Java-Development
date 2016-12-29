package com.cpsc476.site.url;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class UrlDaoImp extends JdbcDaoSupport implements UrlDaoInterface {

	@Override
	public void insertoneurl(String username, String lurl, String surl, int clicks) {
		//getJdbcTemplate().update("insert into project2.url(username,longurl,shorturl,clicks)values (?,?,?,?)", username,lurl, surl, clicks);
        Url url = new Url();
        url.setSUrl(surl);
        url.setLongurl(lurl);
        url.setUsername(username);  
		UrlsDataBase.database.put(surl, url);
	}

	@Override
	//getting all url data: long,short,clicks for certain username:
	public List<Url> findAllurl(String username) {
		//return getJdbcTemplate().query("select distinct m.longurl ,m.shorturl , (select sum(clicks)  from project2.url where shorturl = m.shorturl)as totalclicks from project2.url as m where username = ? ",new UrlMapper(), username);
		List<Url> urlsList = new ArrayList<>();
		for(String shortUrl : UrlsDataBase.database.keySet()){
			Url url= (Url) UrlsDataBase.database.get(shortUrl);
			if (url.getUsername().equals(username)){
				urlsList.add(url);
			}
		}
		return urlsList;
	}

	@Override
	public Url checkforUrl(String username, String longurl) {
		//return getJdbcTemplate().queryForObject("Select count(longurl) as counts from project2.url where longurl = ? and username = ?",new urlcountMapper(), longurl, username);
		Url url= new Url();
		int count=0;
		for(String shortUrl : UrlsDataBase.database.keySet()){
			Url tempurl= (Url) UrlsDataBase.database.get(shortUrl);
			if (tempurl.getUsername().equals(username)){
				if(tempurl.getLongurl().equals(longurl)){
					count++;
				}
			}
		}
		url.seturlCount(count);
		return url;
	}

	@Override
	public Url checkifUrlExists(String shorturl) {
		//return getJdbcTemplate().queryForObject("Select count(longurl) as counts from project2.url where shorturl = ?", new urlcountMapper(), shorturl);
	    Url urlObj = new Url();
		if (UrlsDataBase.database.get(shorturl) != null){
	    	urlObj.seturlCount(1);
	    	//found!
	    }
		else{
			urlObj.seturlCount(0);
			//not found!
		}
	    return urlObj;
	}

	@Override
	public Url getlongurl(String url) {
		//return getJdbcTemplate().queryForObject("select distinct longurl from project2.url where SHORTURL = ?", new longurlMapper(), url);
		
		//getting the short URL entered by the guest user:
    	String shortUrl= url;
    	String originalUrl="";
    	Url urlObj = new Url();
   
    	//looking, in the URL DB, for the long version of the URL:     	
    	for(String item: UrlsDataBase.database.keySet()){
    	  Url tempObj= (Url) UrlsDataBase.database.get(item);
    	    if(tempObj.getSUrl().equals(shortUrl)){
    	    	originalUrl= tempObj.getLongurl();
    	    	break;
    	    }
    	}
    	urlObj.setLongurl(originalUrl);
    	urlObj.setSUrl(shortUrl);
    	return urlObj;
	}

	@Override
	public void updateclicks(String surl, String username) {
		//getJdbcTemplate().update("UPDATE project2.url set clicks = clicks+1  where shorturl = ? and username = ?", surl,username);
		for(String item: UrlsDataBase.database.keySet()){
	    	  Url tempObj= (Url) UrlsDataBase.database.get(item);
	    	    if(tempObj.getSUrl().equals(surl) && tempObj.getUsername().equals(username)){
	    	    	tempObj.Click();
	    	    }
	    	}
	}

	private static final class UrlMapper implements RowMapper<Url> {

		public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
			Url url = new Url();
			url.setSUrl(rs.getString("SHORTURL"));
			url.setLongurl(rs.getString("LONGURL"));
			url.setClicks(rs.getString("totalclicks"));

			return url;
		}
	}

	private static final class longurlMapper implements RowMapper<Url> {

		public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
			Url url = new Url();
			url.setLongurl(rs.getString("LONGURL"));
			// url.setClicks(rs.getString("clicks"));
			return url;
		}
	}

	private static final class urlcountMapper implements RowMapper<Url> {

		public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
			Url url = new Url();
			url.seturlCount(rs.getInt("counts"));
			// url.setClicks(rs.getString("clicks"));
			return url;
		}
	}

}// End of UrlDao class.
