package com.example.kadai_002.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kadai_002.entity.Category;
import com.example.kadai_002.repository.CategoryRepository;


@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {
	
	private final CategoryRepository categoryRepository;
	
	public AdminCategoryController(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping
    public String index(Model model) {
        List<Category> categories = categoryRepository.findAll();       
        
        model.addAttribute("categories", categories);             
        
        return "admin/categories/index";
    }  
	
}
