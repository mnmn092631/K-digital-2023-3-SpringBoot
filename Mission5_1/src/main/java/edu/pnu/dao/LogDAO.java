package edu.pnu.dao;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogDAO {

	private final DataSource dataSource;
	
	public void addLog(String method, String sqlstring, boolean success) {
		try (Connection con = dataSource.getConnection()) {
			sqlstring = sqlstring.replace("'", "\"");
			Statement stmt = con.createStatement();
			stmt.executeUpdate(String.format("INSERT INTO dblog(method, sqlstring, success) VALUES ('%s', '%s', %b)", method, sqlstring, success));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
