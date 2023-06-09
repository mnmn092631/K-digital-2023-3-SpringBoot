package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

	public TestConfig() {
		System.out.println("===> TestConfig 생성");
	}

	@Bean
	public TestBean testBean() {
		return new TestBean();
	}

}

class TestBean {

	public TestBean() {
		System.out.println("===> TestBean 생성");
	}

}