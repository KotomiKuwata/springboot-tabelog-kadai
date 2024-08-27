package com.example.kadai_002.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.kadai_002.service.MembershipService;
import com.example.kadai_002.service.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;


public class StripeWebhookController {
	private final StripeService stripeService;
	private final MembershipService membershipService;
	
	@Value("${stripe.api-key}")
	private String stripeApiKey;
	
	@Value("${stripe.webhook-secret}")
    private String webhookSecret;
	
	public StripeWebhookController(StripeService stripeService, MembershipService membershipService) {
	    this.stripeService = stripeService;
	    this.membershipService = membershipService;
	}

    @PostMapping("/stripe/webhook")
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        // Stripe署名の検証
        Event event;
        Stripe.apiKey = stripeApiKey;
        try {
            event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
        } catch (SignatureVerificationException e) {
            return ResponseEntity.badRequest().build();
        }

        // イベントタイプに応じた処理
        if ("checkout.session.completed".equals(event.getType())) {
        	stripeService.processSessionCompleted(event);
        }

        return ResponseEntity.ok().build();
    }

}
