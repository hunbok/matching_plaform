package com.matching.plaform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matching.plaform.domain.Category;
import com.matching.plaform.mapper.CategoryMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService {
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	public List<Category> categoryList(){
		log.info("categoryService : categoryList()");
		return categoryMapper.categoryList();
	}
	
}
