package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class BoardRepositoryTest2 {
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	@Order(1)
	public void testFindTitleContaining() {
		List<Board> list = boardRepo.findByTitleContaining("1");
		
		System.out.println("title에 1이 포함되는 데이터 결과");
		for(Board b : list) {
			System.out.println("---> " + b);
		}
	}

	@Test
	@Order(2)
	public void testFindByTitleContainingAndCntGreaterThan() {
		List<Board> list = boardRepo.findByTitleContainingAndCntGreaterThan("1", 50L);
		
		System.out.println("title에 1이 포함되면서 cnt가 50보다 큰 데이터 결과");
		for(Board b : list) {
			System.out.println("---> " + b);
		}
	}
	
	@Test
	@Order(3)
	public void testFindByCntBetweenOrderBySeqAsc() {
		List<Board> list = boardRepo.findByCntBetweenOrderBySeqAsc(10L, 50L);
		
		System.out.println("cnt가 10~50사이인 데이터를 seq 오름차순 결과");
		for(Board b : list) {
			System.out.println("---> " + b);
		}
	}
	
	@Test
	@Order(4)
	public void testFindByTitleContainingOrContentContainingOrderBySeqDesc() {
		List<Board> list = boardRepo.findByTitleContainingOrContentContainingOrderBySeqDesc("10", "2");
		
		System.out.println("title에 10이 포함되거나 content에 2가 포함되는 데이터를 seq 내림차순 결과");
		for(Board b : list) {
			System.out.println("---> " + b);
		}
	}
	
}
