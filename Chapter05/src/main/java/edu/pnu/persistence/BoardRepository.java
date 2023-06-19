package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	Page<Board> findAll(Pageable paging);
	
	List<Board> findByTitleContaining(String searchKeyword, Pageable page);
	
	List<Board> findByTitleContaining(String searchKeyword);
	
	List<Board> findByTitleContainingAndCntGreaterThan(String searchKeyword, Long cnt);
	
	List<Board> findByCntBetweenOrderBySeqAsc(Long from, Long to);
	
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String searchKeyword1, String searchKeyword2);
}
