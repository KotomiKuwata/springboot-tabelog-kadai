package com.example.kadai_002.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@GetMapping("/subscribe")
	public String showSubscribePage(@RequestParam(required = false) String error, Model model) {
		if (error != null && error.equals("payment_cancelled")) {
			model.addAttribute("errorMessage", "決済がキャンセルされました。");
		}
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
		String userEmail = principal.getName();
		membershipService.updateMembershipStatus(userEmail);
		return "redirect:/membership/info?session_id=" + sessionId;
	}

	@GetMapping("/info")
    public String showMembershipInfo(Principal principal, Model model, @RequestParam(required = false) String session_id) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        if (session_id != null) {
            // セッションIDを使用して決済結果を確認し、必要に応じてメッセージを表示
            model.addAttribute("paymentMessage", "決済が完了しました。");
        }
        return "membership/info";
    }
}
/*@Autowired
public MembershipController(StripeService stripeService, MembershipService membershipService) {
    this.stripeService = stripeService;
    this.membershipService = membershipService;
}

@GetMapping("/subscribe")
public String showSubscribePage(Model model) {
    return "membership/subscribe";
}*/