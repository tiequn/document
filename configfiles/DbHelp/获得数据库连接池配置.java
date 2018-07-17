package com.kaishnegit.util;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConncetionManager {
	private static  String DRIVER;// = "com.mysql.jdbc.Driver";
	private static  String URL; //= "jdbc:mysql:///mydb";
	private static  String USERNAME ;//= "root";
	private static  String PASSWORD;// = "root";
	private static Properties properties = new Properties();
	private static BasicDataSource dataSource = new BasicDataSource();
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	static {
		try {
			properties.load(ConncetionManager.class.getClassLoader().getResourceAsStream("config.properties"));
			DRIVER = properties.getProperty("jdbc.driver");
			URL = properties.getProperty("jdbc.url");
			USERNAME = properties.getProperty("jdbc.username");
			PASSWORD = properties.getProperty("jdbc.password");
		} catch (IOException e) {
			e.printStackTrace();
		}
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		dataSource.setInitialSize(5);
		dataSource.setMaxIdle(10);
		dataSource.setMinIdle(5);
		dataSource.setMaxWaitMillis(5000);
	}

}