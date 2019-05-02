package com.botscrew.testtask.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.botscrew.testtask.util.Subject;
import com.botscrew.testtask.util.Observer;

public class Model implements Subject {
	private DataSource dataSource;
	private Observer observer;
	private ResultSet resultSet;
	public Model() {
		this.dataSource = new DataSource();
	}
	public void executeQuery(String sql) {
		try (
			Connection con = this.dataSource.getConnection();
			Statement st = con.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
              	ResultSet.CONCUR_UPDATABLE
			);
		) {
			this.resultSet = st.executeQuery(sql);
			this.notifyObserver();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { 
				if (this.resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {}
		}
	}
	@Override
	public void registerObserver(Observer o) { 
		this.observer = o;
	}
	@Override
	public void notifyObserver() {
		this.observer.update(this.resultSet);
	}
}
