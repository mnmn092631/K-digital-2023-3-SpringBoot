package edu.pnu;


import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.Member;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MemberDAOTest {

	@Test
	@Order(1)
	public void insertMemberTest() {
		MemberDAO memberDao = new MemberDAO();

		for (int i = 1; i <= 10; i++) {
			memberDao.insertMember(Member.builder()
									.name("name" + i)
									.age(20 + i)
									.nickname("nickname" + i)
									.build());
		}
	}

	@Test
	@Order(2)
	public void selectAllMemberTest() {
		MemberDAO memberDao = new MemberDAO();

		List<Member> list = memberDao.getMembers();
		for (Member m : list) {
			System.out.println(m);
		}
	}

}
