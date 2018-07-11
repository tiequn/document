package com.keishengit.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import com.keishengit.exception.DataAccessException;

public class ConnectionManager {

	private static String DRIVER;//= "com.mysql.jdbc.Driver";
	private static String URL;//= "jdbc:mysql:///book_system";
	private static String USERNAME;// = "root";
	private static String PASSWORD;// = "rootroot";
	// 创建数据库连接池
	private static BasicDataSource dataSource = new BasicDataSource();
	
	static {
		// 读取properties配置文件
		Properties prop = new Properties();
		try {
			prop.load(ConnectionManager.class.getClassLoader().getResourceAsStream("config.properties"));
			DRIVER = prop.getProperty("jdbc.driver");
			URL = prop.getProperty("jdbc.url");
			USERNAME = prop.getProperty("jdbc.username");
			PASSWORD = prop.getProperty("jdbc.password");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 数据路连接池设置属性
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		
		dataSource.setInitialSize(5);
		dataSource.setMaxIdle(10);
		dataSource.setMinIdle(5);
		dataSource.setMaxWaitMillis(5000);
		
	}
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new DataAccessException("数据库连接异常");
		}
		return conn;
				 
		/*Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;*/
	}
	
	
}
