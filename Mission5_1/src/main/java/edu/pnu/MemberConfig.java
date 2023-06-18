package edu.pnu;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.pnu.dao.LogDAO;
import edu.pnu.dao.MemberDAOH2Impl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.service.MemberService;

@Configuration
public class MemberConfig {

	@Bean
	public MemberService memberService(MemberInterface memberInterface, LogDAO logDAO) {
		return new MemberService(memberInterface, logDAO);
	}
	
	@Bean
	public MemberDAOH2Impl memberDAOH2Impl(DataSource dataSource) {
		return new MemberDAOH2Impl(dataSource);
	}
	
	@Bean
	public LogDAO logDAO(DataSource dataSource) {
		return new LogDAO(dataSource);
	}
	
}
