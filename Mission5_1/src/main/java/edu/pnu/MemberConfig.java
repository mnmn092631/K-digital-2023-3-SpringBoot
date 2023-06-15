package edu.pnu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.pnu.dao.LogDAO;
import edu.pnu.dao.MemberDAOH2Impl;
import edu.pnu.service.MemberService;

@Configuration
public class MemberConfig {

	@Bean
	public MemberService memberService() {
		return new MemberService();
	}
	
	@Bean
	public MemberDAOH2Impl memberDAOH2Impl() {
		return new MemberDAOH2Impl();
	}
	
	@Bean
	public LogDAO logDAO() {
		return new LogDAO();
	}
	
}
