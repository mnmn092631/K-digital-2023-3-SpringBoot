package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;

	@Override
	public List<Board> getBoardList() {
		return (List<Board>) boardRepo.findAll();
	}

	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}

	@Override
	public Board getBoard(Board board) {
		Board findBoard = boardRepo.findById(board.getSeq()).get();
		findBoard.setCnt(findBoard.getCnt() + 1);
		return boardRepo.save(findBoard);
	}

	@Override
	public void updateBoard(Board board) {
		Board findBoard = boardRepo.findById(board.getSeq()).get();
		
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);
	}

	@Override
	public void deleteBoard(Board board) {
		boardRepo.deleteById(board.getSeq());
	}

}
