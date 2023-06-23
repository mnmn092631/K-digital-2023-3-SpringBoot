package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.service.BoardService;

@SessionAttributes("member")
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}

	@RequestMapping("/getBoardList")
	public String getBoardList(@ModelAttribute("member") Member member, Model model) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		model.addAttribute("boardList", boardService.getBoardList());
		return "getBoardList";
	}

	@GetMapping("/insertBoard")
	public String insertBoard(@ModelAttribute("member") Member member) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		return "insertBoard";
	}

	@PostMapping("/insertBoard")
	public String insertBoardView(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}

	@GetMapping("/getBoard")
	public String getBoard(@ModelAttribute("member") Member member, Long seq, Model model) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		Board board = boardService.getBoard(Board.builder().seq(seq).build());
		model.addAttribute("board", board);
		return "getBoard";
	}

	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		boardService.updateBoard(board);
		return "redirect:getBoardList";
	}

	@GetMapping("/deleteBoard")
	public String deleteBoard(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}

}
