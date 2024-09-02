package com.example.kadai_002.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.kadai_002.entity.Review;
import com.example.kadai_002.entity.User;
import com.example.kadai_002.form.ReviewInputForm;
import com.example.kadai_002.form.ReviewRegisterForm;
import com.example.kadai_002.repository.ReviewRepository;
import com.example.kadai_002.repository.StoreRepository;
import com.example.kadai_002.security.UserDetailsImpl;
import com.example.kadai_002.service.ReviewService;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
	private final ReviewService reviewService;
	private final StoreRepository storeRepository;
	private final ReviewRepository reviewRepository;
	private final ReviewRegisterForm reviewRegisterForm;

	public ReviewController(ReviewService reviewService, StoreRepository storeRepository,
			ReviewRepository reviewRepository, ReviewRegisterForm reviewRegisterForm) {
		this.reviewService = reviewService;
		this.storeRepository = storeRepository;
		this.reviewRepository = reviewRepository;
		this.reviewRegisterForm = reviewRegisterForm;
	}

	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.DESC) Pageable pageable,
			Model model) {
		User user = userDetailsImpl.getUser();
		Page<Review> reviewPage = reviewRepository.findByUserOrderByTimestampDesc(user, pageable);
		model.addAttribute("reviewPage", reviewPage);
		return "reviews/index";
	}

	@GetMapping("/stores/{id}/reviews/input")
	public String input(@PathVariable(name = "id") Integer id,
			@ModelAttribute @Validated ReviewInputForm reviewInputForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			Model model) {

		// バリデーションチェック
		if (reviewInputForm.getRating() == null || reviewInputForm.getRating() < 1 || reviewInputForm.getRating() > 5) {
			bindingResult.rejectValue("rating", "error.reviewInputForm", "評価は1から5の間で入力してください。");
		}

		if (reviewInputForm.getComment() == null || reviewInputForm.getComment().trim().isEmpty()) {
			bindingResult.rejectValue("comment", "error.reviewInputForm", "コメントを入力してください。");
		}

		if (reviewInputForm.getComment().length() > 200) {
			bindingResult.rejectValue("comment", "error.reviewInputForm", "コメントは200文字以内で入力してください。");
		}

		// 再度バリデーションチェック
		if (bindingResult.hasErrors()) {
			model.addAttribute("errorMessage", "レビュー内容に不備があります。");
			return "stores/show";
		}

		redirectAttributes.addFlashAttribute("reviewInputForm", reviewInputForm);

		return "redirect:/stores/{id}/reviews/confirm";

	}
	
	@PostMapping("/stores/{id}/reviews/create")
    public String create(@ModelAttribute ReviewRegisterForm reviewRegisterForm) {                
		reviewService.create(reviewRegisterForm);        
        
        return "redirect:/reviews?reviewed";
    }

}

/*@GetMapping("/stores/{id}/reviews/input")
public String input(@PathVariable(name = "id") Integer id,
		@ModelAttribute @Validated ReviewRegisterForm reviewRegisterForm,
		BindingResult bindingResult,
		RedirectAttributes redirectAttributes,
		Model model) {
	// 実際の処理
	Store store = storeRepository.getReferenceById(id);

	if (bindingResult.hasErrors()) {
		model.addAttribute("store", store);
		model.addAttribute("errorMessage", "投稿内容に不備があります。");
		return "stores/show";
	}

	redirectAttributes.addFlashAttribute("reviewRegisterForm", reviewRegisterForm);
	return "redirect:/stores/" + id + "/reviews/confirm";
}

@GetMapping("/confirm")
public String showCreateForm(Model model) {
	model.addAttribute("reviewRegisterForm", new ReviewRegisterForm());
	return "reviews/confirm";
}
 
 

@PostMapping("/confirm")
public String confirm(@ModelAttribute @Validated ReviewRegisterForm reviewRegisterForm,
		BindingResult bindingResult,
		RedirectAttributes redirectAttributes,
		@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
	if (bindingResult.hasErrors()) {
		return "reviews/confirm";
	}

	Store store = storeRepository.getReferenceById(reviewRegisterForm.getStoreId());
	User user = userDetailsImpl.getUser();

	reviewService.create(reviewRegisterForm);

	redirectAttributes.addFlashAttribute("successMessage", "レビューを投稿しました。");
	return "redirect:/stores/" + store.getId();
}

@GetMapping("/stores/{id}")
public String showReviewForm(@PathVariable(name = "id") Integer id, Model model) {
    Store store = storeRepository.getReferenceById(id);
    List<Review> reviews = reviewService.getReviewsByStoreId(id);
    
    ReviewRegisterForm reviewRegisterForm = new ReviewRegisterForm();
    reviewRegisterForm.setStoreId(store.getId());
    model.addAttribute("reviewRegisterForm", reviewRegisterForm);

    model.addAttribute("store", store);
    model.addAttribute("reviews", reviews);

    return "stores/show";*/