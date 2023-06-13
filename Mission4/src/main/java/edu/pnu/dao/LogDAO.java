package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class LogDAO {
	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/mission2";
	private String username = "scott";
	private String password = "tiger";

	private Connection con;

	public LogDAO() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addLog(String method, String sqlstring, boolean success) {
		try {
			sqlstring = sqlstring.replace("'", "\"");
			Statement stmt = con.createStatement();
			stmt.executeUpdate(String.format("INSERT INTO dblog(method, sqlstring, success) VALUES ('%s', '%s', %b)", method, sqlstring, success));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
