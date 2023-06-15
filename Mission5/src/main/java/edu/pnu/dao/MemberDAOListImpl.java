package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

public class MemberDAOListImpl implements MemberInterface {

	private List<MemberVO> list;

	public MemberDAOListImpl() {
		list = new ArrayList<>();
	}

	@Override
	public Map<String, Object> getMembers() {
		Map<String, Object> map = new HashMap<>();
		map.put("sqlstring", "getMembers");
		map.put("success", true);
		map.put("result", list);
		
		return map;
	}

	@Override
	public Map<String, Object> getMember(Integer id) {
		Map<String, Object> map = new HashMap<>();
		map.put("sqlstring", "getMember");
		map.put("success", true);
		map.put("result", null);
		
		
		for (MemberVO m : list) {
			if (m.getId() == id) {
				map.put("result", m);
				break;
			}
		}
		return map;
	}

	@Override
	public Map<String, Object> addMember(MemberVO member) {
		Map<String, Object> map = new HashMap<>();
		map.put("sqlstring", "addMember");
		map.put("success", true);
		
		member.setId(list.size() + 1);
		member.setRegidate(new Date());
		list.add(member);
		map.put("result", member);
		
		return map;
	}

	@Override
	public Map<String, Object> updateMember(MemberVO member) {
		Map<String, Object> map = new HashMap<>();
		map.put("sqlstring", "updateMember");
		map.put("success", true);
		map.put("result", null);
		
		for (MemberVO m : list) {
			if (m.getId() == member.getId()) {
				m.setName(member.getName());
				m.setPass(member.getPass());
				map.put("result", m);
				break;
			}
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteMember(Integer id) {
		Map<String, Object> map = new HashMap<>();
		map.put("sqlstring", "deleteMember");
		map.put("success", true);
		map.put("result", 0);
		
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
				map.put("result", 1);
				break;
			}
		}
		
		return map;
	}

}
