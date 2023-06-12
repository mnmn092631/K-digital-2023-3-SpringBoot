package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.Member;

public class MemberDAO {
	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/chapter03";
	private String username = "scott";
	private String password = "tiger";

	private Connection con;

	// Database Connection 설정(con)
	public MemberDAO() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int insertMember(Member m) {
		try {
			Statement stmt = con.createStatement();
			String sql = String.format("INSERT INTO member(name, age, nickname) VALUES ('%s', %d, '%s')", m.getName(),
					m.getAge(), m.getNickname());
			return stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Member> getMembers() {
		List<Member> members = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM member");

			while (rs.next()) {
				Member m = Member.builder()
							.id(rs.getLong("id"))
							.name(rs.getString("name"))
							.age(rs.getInt("age"))
							.nickname(rs.getString("nickname"))
							.build();
				members.add(m);
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return members;
	}

	public Member getMember(Long id) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM member WHERE id=%d", id));
			rs.next();
			if(rs != null) {
				return Member.builder()
						.id(rs.getLong("id"))
						.name(rs.getString("name"))
						.age(rs.getInt("age"))
						.nickname(rs.getString("nickname"))
						.build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
