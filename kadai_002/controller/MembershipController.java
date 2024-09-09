package com.example.kadai_002.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.kadai_002.entity.User;
import com.example.kadai_002.service.MembershipService;
import com.example.kadai_002.service.StripeService;
import com.example.kadai_002.service.UserService;
import com.stripe.exception.StripeException;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/membership")
public class MembershipController {
	private final StripeService stripeService;
	private final MembershipService membershipService;
	private final UserService userService;

	public MembershipController(MembershipService membershipService, StripeService stripeService,
			UserService userService) {
		this.membershipService = membershipService;
		this.stripeService = stripeService;
		this.userService = userService;
	}

	@ExceptionHandler(Exception.class)
	public String handleError(Exception ex, Model model) {
		model.addAttribute("errorMessage", ex.getMessage());
		return "error";
	}

	@GetMapping("/subscribe")
	public String showSubscribePage(Principal principal, HttpServletRequest request,
			@RequestParam(required = false) String error, Model model) throws StripeException {
		if (error != null && error.equals("payment_cancelled")) {
			model.addAttribute("errorMessage", "決済がキャンセルされました。");
		}
		String userName = principal.getName();
		String sessionId = stripeService.createStripeSession(userName, request);
		model.addAttribute("sessionId", sessionId);
		System.out.println(sessionId);
		return "membership/subscribe";
	}

	@PostMapping("/subscribe")
	public String subscribe(Principal principal, HttpServletRequest request, Model model) throws StripeException {
		String userName = principal.getName();
		String sessionId = stripeService.createStripeSession(userName, request);
		model.addAttribute("sessionId", sessionId);
		System.out.println(sessionId);
		return "redirect:https://checkout.stripe.com/pay/" + sessionId;
	}

	@GetMapping("/subscribe-success")
	public String subscribeSuccess(@RequestParam("session_id") String sessionId,
			Principal principal, Model model) {
		try {
			String userEmail = principal.getName();
			User user = userService.findByEmail(userEmail);

			membershipService.updateMembershipStatus(userEmail);
			stripeService.processSessionCompleted(sessionId, user);

			userService.saveUser(user);

			return "redirect:/membership/info?session_id=" + sessionId;
		} catch (StripeException | IllegalStateException e) {
			// エラーメッセージをモデルに追加
			model.addAttribute("error", "サブスクリプションの処理中にエラーが発生しました: " + e.getMessage());
			return "error"; // エラーページへリダイレクト
		}
	}

	@GetMapping("/info")
	public String showMembershipInfo(Principal principal, Model model,
			@RequestParam(required = false) String session_id) throws StripeException {
		User user = userService.findByEmail(principal.getName());
		model.addAttribute("user", user);

		if (user.isPaidMember()) {
			model.addAttribute("cardInfo", user); // ユーザーオブジェクトにカード情報が含まれているため
		}

		if (session_id != null) {
			model.addAttribute("paymentMessage", "決済が完了しました。");
		}

		return "membership/info";
	}

	/*@PostMapping("/update-card")
	public ResponseEntity<?> updateCard(@RequestBody Map<String, String> payload, Principal principal) {
		try {
			String paymentMethodId = payload.get("paymentMethodId");
			User user = userService.findByEmail(principal.getName());
			stripeService.updatePaymentMethod(user.getStripeCustomerId(), paymentMethodId);
			return ResponseEntity.ok().build();
		} catch (StripeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}*/
}

/*@Controller
@RequestMapping("/membership")
public class MembershipController {
	private final StripeService stripeService;
	private final MembershipService membershipService;
	private final UserService userService;

	public MembershipController(MembershipService membershipService, StripeService stripeService,
			UserService userService) {
		this.membershipService = membershipService;
		this.stripeService = stripeService;
		this.userService = userService;
	}

	@GetMapping("/subscribe")
	public String showSubscribePage(Principal principal, HttpServletRequest request,
			@RequestParam(required = false) String error, Model model) throws StripeException {
		if (error != null && error.equals("payment_cancelled")) {
			model.addAttribute("errorMessage", "決済がキャンセルされました。");
		}
		String userName = principal.getName();
		String sessionId = stripeService.createStripeSession(userName, request);
		model.addAttribute("sessionId", sessionId);
		System.out.println(sessionId);
		return "membership/subscribe";
	}

	@PostMapping("/subscribe")
	public String subscribe(Principal principal, HttpServletRequest request, Model model) throws StripeException {
		String userName = principal.getName();
		String sessionId = stripeService.createStripeSession(userName, request);
		model.addAttribute("sessionId", sessionId);
		System.out.println(sessionId);
		return "membership/subscribe";
	}

	@GetMapping("/subscribe-success")
	public String subscribeSuccess(@RequestParam("session_id") String sessionId,
	        Principal principal, Model model) {
	    try {
	        String userEmail = principal.getName();
	        String customerId = stripeService.getCustomerIdFromSession(sessionId);
	        membershipService.updateMembershipStatus(userEmail, customerId);
	        return "redirect:/membership/info?session_id=" + sessionId;
	    } catch (StripeException e) {
	        // エラーハンドリング
	        e.printStackTrace();
	        return "error-page";
	    }
	}

	@GetMapping("/info")
	public String showMembershipInfo(Principal principal, Model model,
			@RequestParam(required = false) String session_id) throws StripeException {
		User user = userService.findByEmail(principal.getName());
		model.addAttribute("user", user);

		if (user.isPaidMember()) {
			PaymentMethod paymentMethod = stripeService.getPaymentMethod(user.getStripeCustomerId());
			model.addAttribute("paymentMethod", paymentMethod);
		}

		if (session_id != null) {
			model.addAttribute("paymentMessage", "決済が完了しました。");
		}

		return "membership/info";
	}

	// カード情報の表示
	@GetMapping("/card-info")
	public String showCardInfo(Principal principal, Model model) throws StripeException {
		User user = userService.findByEmail(principal.getName());
		PaymentMethod paymentMethod = stripeService.getPaymentMethod(user.getStripeCustomerId());
		model.addAttribute("paymentMethod", paymentMethod);
		return "membership/card_info";
	}
}*/