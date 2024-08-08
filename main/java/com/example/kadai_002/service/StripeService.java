package com.example.kadai_002.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kadai_002.entity.Membership;
import com.example.kadai_002.entity.User;
import com.example.kadai_002.repository.MembershipRepository;
import com.stripe.Stripe;
import com.stripe.model.Customer;
import com.stripe.model.Subscription;

@Service
public class StripeService {

	static {
        Stripe.apiKey = "sk_test_51PjGIyFRY5VG4shkR9G0p79K7FNWb4wS9rcweTptB9tYD74k5vwmuvMJ5nok5ZscC8RhMmIWpdVyNZU3BvKOoJtD00RtPnDsBb"; // Stripeのシークレットキーを設定
    }

    @Autowired
    private MembershipRepository membershipRepository;

    public void createSubscription(String token, User user) throws Exception {
        // ユーザー情報に基づいてStripeのカスタマーを作成
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("email", user.getEmail());
        customerParams.put("source", token);
        Customer customer = Customer.create(customerParams);

        // サブスクリプションを作成
        Map<String, Object> subscriptionParams = new HashMap<>();
        subscriptionParams.put("customer", customer.getId());
        subscriptionParams.put("items", Arrays.asList(
            Map.of("plan", "prod_QbUlG3vYjKfSUw") // プランIDを設定
        ));
        Subscription subscription = Subscription.create(subscriptionParams);

        // プレミアム会員情報を保存
        Membership membership = new Membership();
        membership.setUserId(user.getId());
        membership.setSubscriptionStatus("active");
        membership.setStartDate(LocalDate.now());
        membership.setEndDate(LocalDate.now().plusMonths(1));
        membershipRepository.save(membership);
    }
}
