package com.example.kadai_002.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.kadai_002.entity.Role;
import com.example.kadai_002.entity.User;
import com.example.kadai_002.repository.RoleRepository;
import com.example.kadai_002.repository.UserRepository;

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

	public void updateMembershipStatus(String email) {
	    User user = userRepository.findByEmail(email);
	    if (user != null) {
	        Role paidMemberRole = roleRepository.findByName("ROLE_PAID_MEMBER");
	        user.setRole(paidMemberRole);
	        user.setIsPaidMember(true);
	        userRepository.save(user);
	    }
	}
}

/*@Service
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
}*/
