package com.example.kadai_002.controller;

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

import com.example.kadai_002.entity.Store;
import com.example.kadai_002.form.ReservationInputForm;
import com.example.kadai_002.form.ReservationRegisterForm;
import com.example.kadai_002.repository.StoreRepository;
import com.example.kadai_002.service.ReservationService;

@Controller
@RequestMapping("/stores/{storeId}/reservations")
public class ReservationController {

	private final ReservationService reservationService;
	private final StoreRepository storeRepository;

	public ReservationController(ReservationService reservationService, StoreRepository storeRepository) {
		this.reservationService = reservationService;
		this.storeRepository = storeRepository;
	}

	@GetMapping("/input")
	public String input(@PathVariable("storeId") Integer storeId,
			@ModelAttribute @Validated ReservationInputForm reservationInputForm,
			BindingResult bindingResult,
			Model model) {
		Store store = storeRepository.findById(storeId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid store ID"));

		if (reservationInputForm.getReservationDatetime() != null &&
				!reservationService.isValidReservationTime(store, reservationInputForm.getReservationDatetime())) {
			bindingResult.rejectValue("reservationDatetime", "error.reservationDatetime",
					"営業時間外または最終受付時間を過ぎています。");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("store", store);
			return "stores/show";
		}

		return "redirect:/stores/" + storeId + "/reservations/confirm";
	}

	@GetMapping("/confirm")
	public String confirm(@PathVariable("storeId") Integer storeId,
			@ModelAttribute ReservationInputForm reservationInputForm,
			Model model) {
		Store store = storeRepository.findById(storeId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid store ID"));

		model.addAttribute("store", store);
		model.addAttribute("reservationInputForm", reservationInputForm);
		return "reservations/confirm";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute ReservationRegisterForm reservationRegisterForm,
			RedirectAttributes redirectAttributes) {
		reservationService.create(reservationRegisterForm);
		redirectAttributes.addFlashAttribute("successMessage", "予約が完了しました。");
		return "redirect:/reservations";
	}
}
