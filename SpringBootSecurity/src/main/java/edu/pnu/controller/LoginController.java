package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pnu.config.MemberService;
import edu.pnu.domain.Member;

@Controller
public class LoginController {
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/login")
	public void login() {
	}

	@GetMapping("/loginSuccess")
	public void loginSuccess() {
	}

	@GetMapping("/accessDenied")
	public void accessDenied() {
	}
	
	@GetMapping("/join")
	public void join() {}
	
	@PostMapping("/join")
	public String joinProc(Member member) {
		member.setRole("ROLE_MEMBER");
		member.setEnabled(true);
		member.setPassword(encoder.encode(member.getPassword()));
		memberService.save(member);
		return "login";
	}

	@GetMapping("/auth")
	public @ResponseBody String auth(@AuthenticationPrincipal OAuth2User user) {
		if (user == null) {
			return "user is Null";
		}
		return user.toString();
	}
	
}
