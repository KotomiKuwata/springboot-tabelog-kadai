package com.example.kadai_002.service;

import java.security.Principal;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.kadai_002.entity.Role;
import com.example.kadai_002.entity.User;
import com.example.kadai_002.repository.RoleRepository;
import com.example.kadai_002.repository.UserRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.model.SetupIntent;

@Service
public class MembershipService {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final UserDetailsService userDetailsService;
	private final StripeService stripeService;
	private final UserService userService;

	public MembershipService(UserRepository userRepository, RoleRepository roleRepository,
			UserDetailsService userDetailsService, StripeService stripeService, UserService userService) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userDetailsService = userDetailsService;
		this.stripeService = stripeService;
		this.userService = userService;
	}

	public void updateMembershipStatus(String email, String customerId) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			Role paidMemberRole = roleRepository.findByName("ROLE_PAID_MEMBER");
			user.setRole(paidMemberRole);
			user.setIsPaidMember(true);

			try {
				// カード情報の取得と保存
				Customer customer = Customer.retrieve(customerId);
				PaymentMethod paymentMethod = PaymentMethod
						.retrieve(customer.getInvoiceSettings().getDefaultPaymentMethod());

				user.setCardLast4(paymentMethod.getCard().getLast4());
				user.setCardBrand(paymentMethod.getCard().getBrand());
				user.setCardExpMonth(paymentMethod.getCard().getExpMonth().intValue());
				user.setCardExpYear(paymentMethod.getCard().getExpYear().intValue());
			} catch (StripeException e) {
				// エラーハンドリング
				e.printStackTrace();
			}

			userRepository.save(user);
		}
	}

	public void updateSecurityContext(String email) {
		User user = userRepository.findByEmail(email);
		if (user != null && user.getRole().getName().equals("ROLE_PAID_MEMBER")) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(email);
			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
					userDetails.getPassword(), userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

	@PostMapping("/setup-intent")
	@ResponseBody
	public Map<String, String> createSetupIntent(Principal principal) throws StripeException {
		User user = userService.findByEmail(principal.getName());
		SetupIntent setupIntent = stripeService.createSetupIntent(user.getStripeCustomerId());
		return Map.of("clientSecret", setupIntent.getClientSecret());
	}

	@PostMapping("/attach-payment-method")
	public String attachPaymentMethod(Principal principal, @RequestParam("paymentMethodId") String paymentMethodId)
			throws StripeException {
		User user = userService.findByEmail(principal.getName());
		PaymentMethod paymentMethod = stripeService.attachPaymentMethod(user.getStripeCustomerId(), paymentMethodId);
		stripeService.saveCardInfo(user.getStripeCustomerId(), user.getEmail());
		return "redirect:/membership/card-info";
	}

	@PostMapping("/detach-payment-method")
	public String detachPaymentMethod(@RequestParam("paymentMethodId") String paymentMethodId) throws StripeException {
		stripeService.detachPaymentMethod(paymentMethodId);
		return "redirect:/membership/card-info";
	}
}
/*public void cancelMembership(String email) {
	User user = userRepository.findByEmail(email);
	if (user != null) {
		Role freeMemberRole = roleRepository.findByName("ROLE_GENERAL");
		user.setRole(freeMemberRole);
		user.setIsPaidMember(false);
		user.setStripeSubscriptionId(null);
		userRepository.save(user);
	}
}*/
