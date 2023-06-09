package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberController {

	private MemberService memberService;

	public MemberController() {
		System.out.println("===> MemberController 생성");
		log.info("===> MemberController 생성");

		memberService = new MemberService();
	}

	// localhost:8080/member/1
	@GetMapping("/member/{id}")
	public Member getMember(@PathVariable Long id) {
		return memberService.getMember(id);
	}

	// 모든 멤버 데이터를 리턴
	@GetMapping("/member")
	public List<Member> getMembers() {
		return memberService.getMembers();
	}
	
	// 새로운 멤버 입력
	@PostMapping("/member")
	public Member insertMember(Member member) {
		return memberService.addMember(member);
	}
	
	// 멤버 이름, 비밀번호 수정
	@PutMapping("/member")
	public Member updateMember(Member member) {
		return memberService.updateMember(member);
	}
	
	// 멤버 삭제
	@DeleteMapping("/member/{id}")
	public Member deleteMember(@PathVariable Long id) {
		return memberService.deleteMember(id);
	}

}
