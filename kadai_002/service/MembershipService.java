package com.example.kadai_002.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	
	public MembershipService(UserRepository userRepository, RoleRepository roleRepository, UserDetailsService userDetailsService) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userDetailsService = userDetailsService;
	}

	public void updateMembershipStatus(String email) {
		User user = userRepository.findByEmail(email);

		if (user != null) {
			Role paidMemberRole = roleRepository.findByName("ROLE_PAID_MEMBER");
			user.setRole(paidMemberRole);
			userRepository.save(user);
		}
	}

	public void updateSecurityContext(String email) {
    	User user = userRepository.findByEmail(email);
        if (user != null && user.getRole().getName().equals("ROLE_PAID_MEMBER")) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            Authentication authentication = 
                new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
	}
}