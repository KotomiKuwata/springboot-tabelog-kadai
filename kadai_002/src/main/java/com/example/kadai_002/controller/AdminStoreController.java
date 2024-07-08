package com.example.kadai_002.controller;


import java.beans.PropertyEditorSupport;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.kadai_002.entity.Store;
import com.example.kadai_002.form.StoreRegisterForm;
import com.example.kadai_002.repository.CategoryRepository;
import com.example.kadai_002.repository.StoreRepository;
import com.example.kadai_002.services.StoreService;

@Controller
@RequestMapping("/admin/stores")
public class AdminStoreController {
	private final StoreRepository storeRepository;
	private final StoreService storeService;

	public AdminStoreController(StoreRepository storeRepository, StoreService storeService) {
		this.storeRepository = storeRepository; 
		this.storeService = storeService;  	
	}	
	

	@GetMapping
	public String index(Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, 
			@RequestParam(name = "keyword", required = false) String keyword) {
		Page<Store> storePage;  

		if (keyword != null && !keyword.isEmpty()) {
			storePage = storeRepository.findByNameLike("%" + keyword + "%", pageable);                
		} else {
			storePage = storeRepository.findAll(pageable);
		}  

		model.addAttribute("storePage", storePage);
		model.addAttribute("keyword", keyword);

		return "admin/stores/index";
	} 
	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model) {
		Store store = storeRepository.getReferenceById(id);

		model.addAttribute("store", store);

		return "admin/stores/show";
	} 

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("storeRegisterForm", new StoreRegisterForm());
		return "admin/stores/register";
	}
	
	@InitBinder("/register")
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Time.class, new PropertyEditorSupport() {
	        public void setAsText(String text) {
	            try {
	                // "HH:mm"の形式で入力を受け取り、java.sql.Timeに変換
	                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	                setValue(new Time(sdf.parse(text).getTime()));
	            } catch (ParseException e) {
	                setValue(null);
	            }
	        }

	        public String getAsText() {
	            Time value = (Time) getValue();
	            return (value != null ? value.toString() : "");
	        }
	    });
	}

	@PostMapping("/create")
	public String create(@ModelAttribute @Validated StoreRegisterForm storeRegisterForm, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {        
		if (bindingResult.hasErrors()) {
			return "admin/stores/register";
		}

		storeService.create(storeRegisterForm);
		redirectAttributes.addFlashAttribute("successMessage", "民宿を登録しました。");    

		return "redirect:/admin/stores";
	} 

	@Autowired
	private CategoryRepository categoryrepository;

	@GetMapping("/sotre/{}id") 
	public String getStoreDetails(@PathVariable("id") Integer id, Model model) {
		Store store = storeRepository.findById(id).orElse(null);
		model.addAttribute("store", store);
		return "storeDetails";
	} 



}

