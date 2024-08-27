package com.example.kadai_002.service;

import org.springframework.stereotype.Service;

import com.example.kadai_002.entity.User;
import com.example.kadai_002.repository.UserRepository;

@Service
public class MembershipService {
    private final UserRepository userRepository;
    
    public MembershipService(UserRepository userRepository) {
        this.userRepository = userRepository;
  
    }
    
    public void updateMembershipStatus(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setIsPaidMember(true);
            userRepository.save(user);
        }
    }
}