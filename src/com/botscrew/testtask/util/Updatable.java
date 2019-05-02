package com.botscrew.testtask.util;

import java.sql.ResultSet;

public interface Updatable {
	void post(ResultSet resultSet);
}