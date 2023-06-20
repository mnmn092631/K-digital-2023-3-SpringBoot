package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	Page<Board> findAll(Pageable paging);
	
	List<Board> findByTitleContaining(String searchKeyword, Pageable page);
	
	List<Board> findByTitleContaining(String searchKeyword);
	
	List<Board> findByTitleContainingAndCntGreaterThan(String searchKeyword, Long cnt);
	
	List<Board> findByCntBetweenOrderBySeqAsc(Long from, Long to);
	
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String searchKeyword1, String searchKeyword2);
	
	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest1(String searchKeyword);
	
	@Query("SELECT b FROM Board b WHERE b.title LIKE %:searchKeyword% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest2(String searchKeyword);
	
	@Query("SELECT b.seq, b.title, b.writer, b.createDate FROM Board b "
			+ "WHERE b.title LIKE %:searchKeyword% ORDER BY b.seq DESC")
	List<Object[]> queryAnnotationTest3(String searchKeyword);
	
	@Query(value="SELECT seq, title, writer, create_date "
			+ "FROM board WHERE title LIKE '%'||:sk||'%' "
			+ "ORDER BY seq DESC", nativeQuery = true)
	List<Object[]> queryAnnotationTest4(@Param("sk") String searchKeyword);
	
	@Query("SELECT b FROM Board b")
	List<Board> queryAnnotationTest5(Pageable paging);
}
