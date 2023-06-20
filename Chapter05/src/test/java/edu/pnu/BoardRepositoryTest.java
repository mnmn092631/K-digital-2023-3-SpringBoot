package edu.pnu;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class BoardRepositoryTest {
	@Autowired
	private BoardRepository boardRepo;

//	@Test
	@Order(1)
	public void testInsertBoard() {
		for (int i = 1; i <= 10; i++) {
			boardRepo.save(Board.builder().title("title" + i).writer("writer" + i).content("content" + i)
					.createDate(new Date()).cnt(0L).build());
		}
	}

//	@Test
	public void testGetBoard() {
//		Optional<Board> opt = boardRepo.findById(1L);
//		Board board = opt.get();

		Board board = boardRepo.findById(1L).get();
		System.out.println(board);
	}

//	@Test
	public void testUpdateBoard() {
//		System.out.println("=== 1번 게시글 조회 ===");
//		Board board = boardRepo.findById(1L).get();
//		
//		System.out.println("=== 1번 게시글 제목 수정 ===");
//		board.setTitle("제목을 수정했습니다.");
//		boardRepo.save(board);
		{
			Board board = boardRepo.findById(1L).get();
			System.out.println("수정 전");
			System.out.println(board);

			board.setTitle("제목 수정");
			boardRepo.save(board);
		}
		{
			Board board = boardRepo.findById(1L).get();
			System.out.println("수정 후");
			System.out.println(board);
		}
	}

//	@Test
	public void testDeleteBoard() {
		boardRepo.deleteById(2L);
	}

//	@Test
	public void testFindAll() {
		List<Board> list = boardRepo.findAll();
		System.out.println("모든 데이터를 출력합니다.");
		for (Board b : list) {
			System.out.println(b);
		}
	}

//	@Test
	public void testQueryAnnotationTest1() {
		List<Board> list = boardRepo.queryAnnotationTest1("title1");
		for (Board b : list) {
			System.out.println(b);
		}
	}

//	@Test
	public void testQueryAnnotationTest2() {
		List<Board> list = boardRepo.queryAnnotationTest2("title1");
		for (Board b : list) {
			System.out.println(b);
		}
	}

//	@Test
	public void testQueryAnnotationTest3() {
		List<Object[]> list = boardRepo.queryAnnotationTest3("title1");
		for (Object[] b : list) {
			System.out.println(Arrays.toString(b));
		}
	}

//	@Test
	public void testQueryAnnotationTest4() {
		List<Object[]> list = boardRepo.queryAnnotationTest4("title1");
		for (Object[] b : list) {
			System.out.println(Arrays.toString(b));
		}
	}

	@Test
	public void testQueryAnnotationTest5() {
//		Pageable paging = PageRequest.of(0, 3, Sort.Direction.DESC, "seq");
		Pageable paging = PageRequest.of(5, 5);
		List<Board> list = boardRepo.queryAnnotationTest5(paging);

		for (Board b : list) {
			System.out.println(b);
		}
	}

}
