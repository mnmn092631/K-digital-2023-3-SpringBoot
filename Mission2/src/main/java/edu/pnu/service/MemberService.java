package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.Member;

public class MemberService {

	private MemberDAO memberDAO;

	public MemberService() {
		memberDAO = new MemberDAO();
	}

	public Member getMember(Long id) {
		return memberDAO.getMember(id);
	}

	public List<Member> getMembers() {
		return memberDAO.getMembers();
	}

	public Member addMember(Member member) {
		return memberDAO.addMember(member);
	}

	public Member updateMember(Member member) {
		return memberDAO.updateMember(member);
	}

	public Member deleteMember(Long id) {
		return memberDAO.deleteMember(id);
	}

}
