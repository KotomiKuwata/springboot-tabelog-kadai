package com.example.kadai_002.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.kadai_002.entity.Review;
import com.example.kadai_002.entity.Store;
import com.example.kadai_002.entity.User;
import com.example.kadai_002.form.ReviewRegisterForm;
import com.example.kadai_002.repository.StoreRepository;
import com.example.kadai_002.security.UserDetailsImpl;
import com.example.kadai_002.service.ReviewService;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
	private final ReviewService reviewService;
	private final StoreRepository storeRepository;

	public ReviewController(ReviewService reviewService, StoreRepository storeRepository) {
		this.reviewService = reviewService;
		this.storeRepository = storeRepository;
	}

	@GetMapping
	public String index(Model model) {
		List<Review> reviews = reviewService.getAllReviews();
		model.addAttribute("reviews", reviews);
		return "reviews/index";
	}

	@GetMapping("/create")
	public String showCreateForm(Model model) {
		model.addAttribute("reviewRegisterForm", new ReviewRegisterForm());
		return "reviews/create";
	}

	/*@PostMapping("/create")
	public String createReview(@ModelAttribute @Validated ReviewRegisterForm form, BindingResult bindingResult,
			@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errorMessage", "レビューの投稿に失敗しました。");
			return "redirect:/stores/" + form.getStoreId();
		}
	
		Review review = new Review();
		review.setStore(new Store(form.getStoreId()));
		review.setUser(((MyCustomUserDetails) userDetails).getUser());
		review.setRating(form.getRating());
		review.setComment(form.getComment());
	
		reviewService.createReview(review, form.getStoreId(), userDetails);
		redirectAttributes.addFlashAttribute("successMessage", "レビューを投稿しました。");
		return "redirect:/stores/" + form.getStoreId();
	}*/

	@PostMapping("/create")
	public String create(@ModelAttribute @Validated ReviewRegisterForm reviewRegisterForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		if (bindingResult.hasErrors()) {
			return "reviews/create";
		}

		Store store = storeRepository.getReferenceById(reviewRegisterForm.getStoreId());
		User user = userDetailsImpl.getUser();

		reviewService.create(reviewRegisterForm);

		redirectAttributes.addFlashAttribute("successMessage", "レビューを投稿しました。");
		return "redirect:/stores/" + store.getId();
	}
}
