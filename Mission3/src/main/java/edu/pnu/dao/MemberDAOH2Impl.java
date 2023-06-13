package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDAOH2Impl implements MemberInterface {
	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/mission2";
	private String username = "scott";
	private String password = "tiger";

	private Connection con;

	public MemberDAOH2Impl() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int getMaxId() {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) FROM member");

			rs.next();
			int maxId = rs.getInt(1);

			rs.close();
			stmt.close();

			return maxId;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public List<MemberVO> getMembers() {
		List<MemberVO> members = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM member");

			while (rs.next()) {
				MemberVO m = MemberVO.builder().id(rs.getInt("id")).pass(rs.getString("pass"))
						.name(rs.getString("name")).regidate(rs.getDate("regidate")).build();
				members.add(m);
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
		}
		return members;
	}

	@Override
	public MemberVO getMember(Integer id) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM member WHERE id=%d", id));

			rs.next();

			MemberVO m = MemberVO.builder().id(rs.getInt("id")).pass(rs.getString("pass")).name(rs.getString("name"))
					.regidate(rs.getDate("regidate")).build();

			rs.close();
			stmt.close();

			return m;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public MemberVO addMember(MemberVO member) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(String.format("INSERT INTO member(pass, name) VALUES ('%s', '%s')", member.getPass(),
					member.getName()));

			stmt.close();

			int id = getMaxId();
			return getMember(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MemberVO updateMember(MemberVO member) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(String.format("UPDATE member SET pass='%s', name='%s' WHERE id=%d", member.getPass(),
					member.getName(), member.getId()));

			stmt.close();

			return getMember(member.getId());
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public int deleteMember(Integer id) {
		try {			
			Statement stmt = con.createStatement();
			stmt.executeUpdate(String.format("DELETE FROM member WHERE id=%d", id));
			
			stmt.close();
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
