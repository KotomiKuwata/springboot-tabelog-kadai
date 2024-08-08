package com.example.kadai_002.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kadai_002.entity.Membership;
import com.example.kadai_002.entity.User;
import com.example.kadai_002.repository.MembershipRepository;

@Service
public class MembershipService {
	@Autowired
    private MembershipRepository membershipRepository;

    public Membership getActiveMembership(User user) {
        return membershipRepository.findByUserIdAndSubscriptionStatus(user.getId(), "active");
    }
}

