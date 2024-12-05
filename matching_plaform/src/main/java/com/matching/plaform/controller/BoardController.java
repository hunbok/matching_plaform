package com.matching.plaform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.matching.plaform.service.BoardService;

@Controller
public class BoardController {

	private BoardService boardService;
	
	@GetMapping("/homePage")
	public String boardList(Model model, @RequestParam(value="categoryCode", defaultValue = "1", required = true)int categoryCode) {
		model.addAttribute("bList", boardService.boardList(categoryCode));
		
		return "views/homePage";
	}
	
}
