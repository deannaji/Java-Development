package com.cpsc476.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import com.cpsc476.site.UserDao;
import com.cpsc476.site.UrlDao;

@Configuration
@ComponentScan(
        basePackages = "com.cpsc476.site",
        excludeFilters = @ComponentScan.Filter(Controller.class)
)
public class RootContextConfiguration{
	
	@Bean
    public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:hsql://localhost/cpsc476;");
        dataSource.setUsername("SA");
        dataSource.setPassword("Passw0rd");     
        return dataSource;
    }
	
	@Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        return jdbcTemplate;
    }
 
    @Bean
    public UrlDao UrlDao(){
        UrlDao urlDao = new UrlDao();
        urlDao.setJdbcTemplate(jdbcTemplate());
        return urlDao;
    }
    
    @Bean
    public UserDao UserDao(){
        UserDao userDao = new UserDao();
        userDao.setJdbcTemplate(jdbcTemplate());
        return userDao;
    }
    
}
