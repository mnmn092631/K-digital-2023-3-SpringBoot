package edu.pnu;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@SpringBootTest
public class DynamicQueryTest {

	@Autowired
	private DynamicBoardRepository boardRepo;

	private void test(String searchCondition, String searchKeyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;

		if (searchCondition.equals("TITLE")) {
			// SELECT b FROM Board b WHERE b.title LIKE '%'||:searchKeyword||'%'
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		} else if (searchCondition.equals("CONTENT")) {
			// SELECT b FROM Board b WHERE b.content LIKE '%'||:searchKeyword||'%'
			builder.and(qboard.content.like("%" + searchKeyword + "%"));
		}

		Iterable<Board> list = boardRepo.findAll(builder);

		for (Board b : list) {
			System.out.println(b);
		}
	}

	private void test(Map<String, String> map) {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;

		Set<String> keys = map.keySet();
		for (String key : keys) {
			if (key.equals("TITLE")) {
				// SELECT b FROM Board b WHERE b.title LIKE '%'||:searchKeyword||'%'
				builder.and(qboard.title.like("%" + map.get(key) + "%"));
			} else if (key.equals("CONTENT")) {
				// SELECT b FROM Board b WHERE b.content LIKE '%'||:searchKeyword||'%'
				builder.and(qboard.content.like("%" + map.get(key) + "%"));
			}
		}

		Iterable<Board> list = boardRepo.findAll(builder);

		for (Board b : list) {
			System.out.println(b);
		}
	}

//	@Test
	public void testDynamicQuery() {
//		test("TITLE", "title1");
//		test("CONTENT", "content2");

		Map<String, String> map = new HashMap<>();
		map.put("TITLE", "title1");
		map.put("CONTENT", "content1");
		test(map);
	}

	// Title이 '%title1%'인 데이터를 출력
//	@Test
	public void testDynamicQuery1() {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;

		builder.and(qboard.title.like("%title1%"));

		Iterable<Board> list = boardRepo.findAll(builder);

		for (Board b : list) {
			System.out.println(b);
		}
	}

	// cnt가 50보다 큰 데이터를 출력
//	@Test
	public void testDynamicQuery2() {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;

		builder.and(qboard.cnt.gt(50));

		Iterable<Board> list = boardRepo.findAll(builder);

		for (Board b : list) {
			System.out.println(b);
		}
	}

	// 데이터를 출력할 때 Page 기능 추가
//	@Test
	public void testDynamicQuery3() {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;

		builder.and(qboard.title.like("%title1%"));

		Pageable paging = PageRequest.of(0, 5);

		Page<Board> list = boardRepo.findAll(builder, paging);

		for (Board b : list) {
			System.out.println(b);
		}
	}

	@Test
	public void testDynamicQuery4() {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;

		builder.and(qboard.cnt.gt(50));

		Pageable paging = PageRequest.of(0, 5);

		Page<Board> list = boardRepo.findAll(builder, paging);

		for (Board b : list) {
			System.out.println(b);
		}
	}

}
