package com.matching.plaform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.matching.plaform.domain.Board;
import com.matching.plaform.domain.Category;
import com.matching.plaform.service.BoardService;
import com.matching.plaform.service.CategoryService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
    private CategoryService categoryService;
	
	@GetMapping({"/", "/homePage"})
	public String boardList(Model model,
			@RequestParam(value="categoryCode", required = false, defaultValue="2")int categoryCode){
		
		List<Category> categoryList = categoryService.categoryList();
		
		List<Board> boardList = boardService.boardList(categoryCode);
		
		model.addAttribute("cList", categoryList);
		model.addAttribute("bList", boardList);
		model.addAttribute("categoryCode", categoryCode);
		
		return "views/homePage";
		
		}
}
