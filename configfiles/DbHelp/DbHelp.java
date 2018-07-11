package com.keishengit.util;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.keishengit.exception.DataAccessException;

public class DbHelp {
	private static QueryRunner runner = new QueryRunner(ConnectionManager.getDataSource());
	
	public static void update(String sql, Object... params) throws DataAccessException{
		
		try {
			runner.update(sql, params);
			System.out.println("SQL:" + sql);
		} catch (SQLException e) {
			throw new DataAccessException("执行" + sql + "异常"); 
		}
		
	}
	
	public static <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)  throws DataAccessException{
		T t = null;
		try {
			t = runner.query(sql, rsh, params);
			System.out.println("SQL:" + sql);
		} catch (SQLException e) {
			throw new DataAccessException("执行" + sql + "异常"); 
		}
		return t;
	}
	
	public static <T> T insert(String sql, ResultSetHandler<T> rsh, Object... params) throws DataAccessException{
		T t = null;
		try {
			t = runner.insert(sql, rsh, params);
			System.out.println("SQL:" + sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("执行" + sql + "异常"); 
		}
		return t;
	}
	
}
