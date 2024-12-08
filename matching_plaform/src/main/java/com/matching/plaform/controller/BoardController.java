package com.matching.plaform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.matching.plaform.domain.Board;
import com.matching.plaform.domain.Reply;
import com.matching.plaform.service.BoardService;
import com.matching.plaform.service.ReplyService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private ReplyService replyService;

	@GetMapping({ "/", "/homePage" })
	public String boardbyCategory(Model model,
			@RequestParam(value = "categoryCode", defaultValue = "1") int categoryCode) {

		List<Board> boardbyCategory = boardService.boardbyCategory(categoryCode);
		
		model.addAttribute("bList", boardbyCategory);
		model.addAttribute("categoryCode", categoryCode);

		return "views/homePage";
	}
	
	@GetMapping("/detailView")
	public String getDetail(Model model, @RequestParam("boardNo")int boardNo){
		Board board = boardService.getDetail(boardNo);
		List<Reply> replyList = replyService.ReplyList(boardNo);

		model.addAttribute("rList", replyList);
		model.addAttribute("board", board);
					  
		return "views/detailView";
		}
	
	@GetMapping("/writeBoard")
	public String writeBoard(Model model,  @RequestParam("categoryCode") int categoryCode,
			HttpSession session) {
		
		Board board = boardService.getDetail(categoryCode);
		String memberId = (String)session.getAttribute("memberId");
		
		model.addAttribute("memberId", memberId);
	    model.addAttribute("board", board);
	    model.addAttribute("categoryCode", categoryCode);
	    return "views/writeBoard";
	}

	
	@PostMapping("/writeBoard")
	public String writeBoard(Board board) {
		
		boardService.writeBoard(board);
		return "redirect:/homePage?categoryCode=" + board.getCategoryCode();
	}
}
