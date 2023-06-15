package edu.pnu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import edu.pnu.domain.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberDAOH2Impl implements MemberInterface {

	private final DataSource dataSource;

	private int getMaxId() {
		try (Connection con = dataSource.getConnection()) {
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
	public Map<String, Object> getMembers() {
		Map<String, Object> map = new HashMap<>();
		List<MemberVO> members = new ArrayList<>();
		String query = "SELECT * FROM member";

		map.put("sqlstring", query);

		try (Connection con = dataSource.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				MemberVO m = MemberVO.builder().id(rs.getInt("id")).pass(rs.getString("pass"))
						.name(rs.getString("name")).regidate(rs.getDate("regidate")).build();
				members.add(m);
			}
			map.put("success", true);

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}

		map.put("result", members);
		return map;
	}

	@Override
	public Map<String, Object> getMember(Integer id) {
		Map<String, Object> map = new HashMap<>();
		String query = "SELECT * FROM member WHERE id=" + id;

		map.put("sqlstring", query);
		map.put("result", null);

		try (Connection con = dataSource.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			rs.next();

			MemberVO m = MemberVO.builder().id(rs.getInt("id")).pass(rs.getString("pass")).name(rs.getString("name"))
					.regidate(rs.getDate("regidate")).build();
			map.put("success", true);

			rs.close();
			stmt.close();

			map.put("result", m);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}

		return map;
	}

	@Override
	public Map<String, Object> addMember(MemberVO member) {
		Map<String, Object> map = new HashMap<>();
		String query = String.format("INSERT INTO member(pass, name) VALUES ('%s', '%s')", member.getPass(),
				member.getName());

		map.put("sqlstring", query);
		map.put("result", null);

		try (Connection con = dataSource.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			map.put("success", true);

			stmt.close();

			int id = getMaxId();
			map.put("result", getMember(id).get("result"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}

		return map;
	}

	@Override
	public Map<String, Object> updateMember(MemberVO member) {
		Map<String, Object> map = new HashMap<>();
		String query = String.format("UPDATE member SET pass='%s', name='%s' WHERE id=%d", member.getPass(),
				member.getName(), member.getId());

		map.put("sqlstring", query);
		map.put("result", null);

		try (Connection con = dataSource.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);

			stmt.close();

			map.put("success", true);
			map.put("result", getMember(member.getId()).get("result"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}

		return map;
	}

	@Override
	public Map<String, Object> deleteMember(Integer id) {
		Map<String, Object> map = new HashMap<>();
		String query = String.format("DELETE FROM member WHERE id=%d", id);

		map.put("sqlstring", query);
		map.put("result", 0);

		try (Connection con = dataSource.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);

			stmt.close();

			map.put("success", true);
			map.put("result", 1);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return map;
	}

}
