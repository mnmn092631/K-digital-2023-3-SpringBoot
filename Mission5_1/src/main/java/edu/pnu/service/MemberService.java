package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import edu.pnu.dao.LogDAO;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

public class MemberService {
	
	@Autowired
	private MemberInterface memberDAO;
	
	@Autowired
	private LogDAO logDAO;
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getMembers(){
		Map<String, Object> map = memberDAO.getMembers();
		
		String sqlstring = map.get("sqlstring").toString();
		boolean success = (boolean) map.get("success");
		
		logDAO.addLog("GET", sqlstring, success);
		
		return (List<MemberVO>) map.get("result");
	}
	
	public MemberVO getMember(int id) {
		Map<String, Object> map = memberDAO.getMember(id);
		
		String sqlstring = map.get("sqlstring").toString();
		boolean success = (boolean) map.get("success");
		
		logDAO.addLog("GET", sqlstring, success);
		
		return (MemberVO) map.get("result");
	}
	
	public MemberVO addMember(MemberVO Member) {
		Map<String, Object> map = memberDAO.addMember(Member);
		
		String sqlstring = map.get("sqlstring").toString();
		boolean success = (boolean) map.get("success");
		
		logDAO.addLog("POST", sqlstring, success);
		
		return (MemberVO) map.get("result");
	};
	
	public MemberVO updateMember(MemberVO member) {
		Map<String, Object> map = memberDAO.updateMember(member);
		
		String sqlstring = map.get("sqlstring").toString();
		boolean success = (boolean) map.get("success");
		
		logDAO.addLog("PUT", sqlstring, success);
		
		return (MemberVO) map.get("result");
	};
	
	public int deleteMember(Integer id) {
		Map<String, Object> map = memberDAO.deleteMember(id);
		
		String sqlstring = map.get("sqlstring").toString();
		boolean success = (boolean) map.get("success");
		
		logDAO.addLog("DELETE", sqlstring, success);
		
		return (int) map.get("result");
	}

}
