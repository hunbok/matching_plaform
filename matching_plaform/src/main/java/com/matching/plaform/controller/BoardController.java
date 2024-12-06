package com.matching.plaform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.matching.plaform.domain.Board;
import com.matching.plaform.service.BoardService;

@Controller
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
	
}
