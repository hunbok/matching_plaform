package com.matching.plaform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.matching.plaform.domain.Category;
import com.matching.plaform.service.BoardService;
import com.matching.plaform.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
    @GetMapping("/home")
    public String homePage(Model model) {
        List<Category> categories = categoryService.categoryList();
        model.addAttribute("categories", categories); 
        return "homePage"; 
    }

}
