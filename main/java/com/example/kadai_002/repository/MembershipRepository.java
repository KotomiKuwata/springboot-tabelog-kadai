package com.example.kadai_002.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kadai_002.entity.Membership;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
	Membership findByUserIdAndSubscriptionStatus(Integer userId, String subscriptionStatus);
}
