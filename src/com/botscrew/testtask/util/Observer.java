package com.botscrew.testtask.util;

import java.sql.ResultSet;

public interface Observer {
	void update(ResultSet resultSet);
}