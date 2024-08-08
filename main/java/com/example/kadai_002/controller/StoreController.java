package com.example.kadai_002.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.kadai_002.entity.Category;
import com.example.kadai_002.entity.Store;
import com.example.kadai_002.form.ReservationInputForm;
import com.example.kadai_002.repository.CategoryRepository;
import com.example.kadai_002.repository.StoreRepository;

@Controller
@RequestMapping("/stores")
public class StoreController {
	private final StoreRepository storeRepository;        
	private final CategoryRepository categoryRepository;

	public StoreController(StoreRepository storeRepository, CategoryRepository categoryRepository) {
		this.storeRepository = storeRepository;
		this.categoryRepository = categoryRepository;
	}

	@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "categoryName", required = false) String categoryName,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			Model model) 
	{
		Page<Store> storePage;

		if (categoryName != null && !categoryName.isEmpty()) {
			storePage = storeRepository.findByCategory_Name(categoryName, pageable);
		} else if (keyword != null && !keyword.isEmpty()) {
			storePage = storeRepository.findByNameLike("%" + keyword + "%", pageable);
		} else {
			storePage = storeRepository.findAll(pageable);
		}              

		model.addAttribute("storePage", storePage);
		model.addAttribute("keyword", keyword); 
		model.addAttribute("categoryName", categoryName);
		List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

		return "stores/index";
	}
	
	@GetMapping("/{id}")
    public String show(@PathVariable(name = "id") Integer id, Model model) {
        Store store = storeRepository.getReferenceById(id);
        
        model.addAttribute("store", store);         
        model.addAttribute("reservationInputForm", new ReservationInputForm());
        
        return "stores/show";
    }
	
}
