package com.example.kadai_002.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.kadai_002.entity.User;
import com.example.kadai_002.repository.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Subscription;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class StripeService {
	private final UserRepository userRepository;

	@Value("${stripe.api-key}")
	private String stripeApiKey;

	public StripeService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public String createStripeSession(String userName, HttpServletRequest httpServletRequest) throws StripeException {
		Stripe.apiKey = stripeApiKey;

		String requestUrl = new String(httpServletRequest.getRequestURL());
		String baseUrl = requestUrl.replaceAll("/membership/subscribe", "");
		String successUrl = baseUrl + "/membership/subscribe-success?session_id={CHECKOUT_SESSION_ID}";
		String cancelUrl = baseUrl + "/membership/subscribe?error=payment_cancelled";

		SessionCreateParams params = SessionCreateParams.builder()
				.setMode(SessionCreateParams.Mode.SUBSCRIPTION)
				.setSuccessUrl(successUrl)
				.setCancelUrl(cancelUrl)
				.addLineItem(
						SessionCreateParams.LineItem.builder()
								.setPriceData(
										SessionCreateParams.LineItem.PriceData.builder()
												.setCurrency("jpy")
												.setUnitAmount(300L)
												.setRecurring(
														SessionCreateParams.LineItem.PriceData.Recurring.builder()
																.setInterval(
																		SessionCreateParams.LineItem.PriceData.Recurring.Interval.MONTH)
																.build())
												.setProductData(
														SessionCreateParams.LineItem.PriceData.ProductData.builder()
																.setName("NAGOYAMESHI")
																.build())
												.build())
								.setQuantity(1L)
								.build())
				.build();

		Session session = Session.create(params);
		return session.getId();
	}

	public void processSessionCompleted(String sessionId, User user) throws StripeException {
		Stripe.apiKey = stripeApiKey;

		Session session = Session.retrieve(sessionId);

		// サブスクリプションの情報を取得
		Subscription subscription = Subscription.retrieve(session.getSubscription());

		// 最新の請求情報を取得
		Invoice invoice = Invoice.retrieve(subscription.getLatestInvoice());

		// 支払い方法の情報を取得
		PaymentMethod paymentMethod = null;
		if (invoice.getPaymentIntentObject() != null) {
			paymentMethod = PaymentMethod.retrieve(invoice.getPaymentIntentObject().getPaymentMethod());
		} else if (invoice.getPaymentIntent() != null) {
			PaymentIntent paymentIntent = PaymentIntent.retrieve(invoice.getPaymentIntent());
			paymentMethod = PaymentMethod.retrieve(paymentIntent.getPaymentMethod());
		} else {
			throw new IllegalStateException("No payment information available for invoice: " + invoice.getId());
		}

		if (paymentMethod == null || paymentMethod.getCard() == null) {
			throw new IllegalStateException("No card information available for payment method");
		}

		PaymentMethod.Card card = paymentMethod.getCard();

		user.setCardLast4(card.getLast4());
		user.setCardBrand(card.getBrand());
		user.setCardExpMonth(card.getExpMonth().intValue());
		user.setCardExpYear(card.getExpYear().intValue());

		user.setStripeCustomerId(session.getCustomer());
		user.setStripeSubscriptionId(session.getSubscription());
	}
}

