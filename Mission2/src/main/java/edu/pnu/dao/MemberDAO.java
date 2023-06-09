package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.Member;

public class MemberDAO {
	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/mission2";
	private String username = "scott";
	private String password = "tiger";

	private Connection con;

	public MemberDAO() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Member getMember(Long id) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM member WHERE id = %d", id));

			rs.next();

			Member m = Member.builder().id(rs.getLong("id")).pass(rs.getString("pass")).name(rs.getString("name"))
					.regidate(rs.getDate("regidate")).build();

			rs.close();
			stmt.close();

			return m;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Member> getMembers() {
		try {
			List<Member> members = new ArrayList<>();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM member");

			while (rs.next()) {
				Member m = Member.builder()
							.id(rs.getLong("id"))
							.pass(rs.getString("pass"))
							.name(rs.getString("name"))
							.regidate(rs.getDate("regidate"))
							.build();
				members.add(m);
			}

			rs.close();
			stmt.close();

			return members;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Member addMember(Member member) {
		try {
			PreparedStatement psmt = con.prepareStatement(String.format("INSERT INTO member(pass, name) VALUES (%s, %s)",
															member.getPass(), member.getName()));
			psmt.executeUpdate();

			psmt.close();
			
			List<Member> members = getMembers();
			return members.get(members.size() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Member updateMember(Member member) {
		try {
			PreparedStatement psmt = con.prepareStatement(String.format("UPDATE member SET pass=%s, name=%s WHERE id=%d",
															member.getPass(), member.getName(), member.getId()));
			psmt.executeUpdate();

			psmt.close();
			
			return getMember(member.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Member deleteMember(Long id) {
		try {
			Member m = getMember(id);
			
			PreparedStatement psmt = con.prepareStatement(String.format("DELETE FROM member WHERE id=%d", id));
			psmt.executeUpdate();

			psmt.close();
			
			return m;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
