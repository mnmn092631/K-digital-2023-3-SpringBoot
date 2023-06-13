package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDAOH2Impl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

public class MemberService {
	
	private MemberInterface memberDAO;
	
	public MemberService() {
		// List
//		memberDAO = new MemberDAOListImpl();
		
		// H2
		memberDAO = new MemberDAOH2Impl();
	}
	
	public List<MemberVO> getMembers(){
		return memberDAO.getMembers();
	}
	
	public MemberVO getMember(int id) {
		return memberDAO.getMember(id);
	}
	
	public MemberVO addMember(MemberVO Member) {
		return memberDAO.addMember(Member);
	};
	
	public MemberVO updateMember(MemberVO member) {
		return memberDAO.updateMember(member);
	};
	
	public int deleteMember(Integer id) {
		return memberDAO.deleteMember(id);
	};

}
