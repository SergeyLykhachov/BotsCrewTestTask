package com.botscrew.testtask.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariDataSource;

class DataSource {
	private static final String DB_DRIVER_CLASS = "driver.class.name";
	private static final String DB_URL = "db.url";
	private static final String DB_USR_NAME = "db.usr.name";
	private static final String DB_PASSWORD = "db.password";
	private static Properties properties;
	static {
		try {
			properties = new Properties();
			properties.load(new FileInputStream("properties/database.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private HikariDataSource dataSource;
	DataSource() {
		this.dataSource = new HikariDataSource();
		this.dataSource.setDriverClassName(properties.getProperty(DB_DRIVER_CLASS));
		this.dataSource.setJdbcUrl(properties.getProperty(DB_URL));
		this.dataSource.setUsername(properties.getProperty(DB_USR_NAME));
		this.dataSource.setPassword(properties.getProperty(DB_PASSWORD));
		this.dataSource.setMinimumIdle(100);
		this.dataSource.setMaximumPoolSize(10);
		this.dataSource.setAutoCommit(false);
	}
	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}
}