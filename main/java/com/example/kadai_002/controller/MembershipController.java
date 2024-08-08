package com.example.kadai_002.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.kadai_002.entity.Membership;
import com.example.kadai_002.security.UserDetailsImpl;
import com.example.kadai_002.service.MembershipService;
import com.example.kadai_002.service.StripeService;

@Controller
@RequestMapping("/membership")
public class MembershipController {

	 @Autowired
	    private StripeService stripeService;

	    @Autowired
	    private MembershipService membershipService;

	    @GetMapping("/subscribe")
	    public String showSubscriptionPage(Model model) {
	        return "membership/subscribe";
	    }

	    @PostMapping("/subscribe")
	    public String subscribe(@RequestParam("stripeToken") String stripeToken,
	                            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
	        try {
	            stripeService.createSubscription(stripeToken, userDetailsImpl.getUser());
	            return "redirect:/membership/status";
	        } catch (Exception e) {
	            model.addAttribute("error", "Subscription failed: " + e.getMessage());
	            return "membership/subscribe";
	        }
	    }

	    @GetMapping("/status")
	    public String subscriptionStatus(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
	        Membership membership = membershipService.getActiveMembership(userDetailsImpl.getUser());
	        model.addAttribute("membership", membership);
	        return "membership/status";
	    }
	    
}