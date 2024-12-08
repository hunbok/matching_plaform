package com.matching.plaform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.matching.plaform.domain.Board;
import com.matching.plaform.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping({ "/", "/homePage" })
	public String boardbyCategory(Model model,
			@RequestParam(value = "categoryCode", required = false, defaultValue = "1") int categoryCode) {

		List<Board> boardbyCategory = boardService.boardbyCategory(categoryCode);

		model.addAttribute("bList", boardbyCategory);
		model.addAttribute("categoryCode", categoryCode);

		return "views/homePage";

	}
	
	@GetMapping("/detailView")
	public String getDetail(Model model, @RequestParam("boardNo")int boardNo) {
		Board getDetail = boardService.getDetail(boardNo);
				
		model.addAttribute("board", getDetail);
					  
		return "views/detailView";
		}
	
	@GetMapping("/writeBoard")
	public String writeBoard() {
		return"views/writeBoard";
	}
	
	@PostMapping("/writeBoard")
	public String writeBoard(Board board) {
		log.info("title : ", board.getBoardTitle());
		boardService.writeBoard(board);
		return "redirect:homePage";
	}
}
