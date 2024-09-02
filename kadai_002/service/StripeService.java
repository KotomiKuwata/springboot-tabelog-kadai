package com.example.kadai_002.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class StripeService {
	private final MembershipService membershipService;
	public StripeService(MembershipService membershipService) {
	    this.membershipService = membershipService;
	}

	@Value("${stripe.api-key}")
	private String stripeApiKey;

	public String createStripeSession(String userName, HttpServletRequest httpServletRequest) throws StripeException {
		Stripe.apiKey = stripeApiKey;

		String requestUrl = new String(httpServletRequest.getRequestURL());

		// リクエストURLを元にベースURLを設定
		String baseUrl = requestUrl.replaceAll("/membership/subscribe", "");

		// 成功およびキャンセル時のURL設定
		String successUrl = baseUrl + "/membership/subscribe-success?session_id={CHECKOUT_SESSION_ID}";
		String cancelUrl = baseUrl + "/membership/subscribe?error=payment_cancelled";
		System.out.println(successUrl);
		System.out.println(cancelUrl);

		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
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
														SessionCreateParams.LineItem.PriceData.Recurring.builder().setInterval(
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
		try {
            Session session = Session.create(params);
            return session.getId();
        } catch (StripeException e) {
            e.printStackTrace();
            return "";
        }
    } 
	
	public void processSessionCompleted (Event event) {
		Session session = (Session) event.getDataObjectDeserializer().getObject().get();
        String userEmail = session.getCustomerEmail();
        membershipService.updateMembershipStatus(userEmail);
	}
}

/* @Value("${stripe.api-key}")
 private String stripeApiKey;

 @Autowired
 private UserRepository userRepository;

 public String createStripeSession(String userName, HttpServletRequest httpServletRequest) {
     Stripe.apiKey = stripeApiKey;
     String priceId = "price_1PkHm2FRY5VG4shk7itL1CeP";

     User user = userRepository.findByName(userName);
     if (user == null) {
         throw new RuntimeException("ユーザーが見つかりません");
     }
     
     String requestUrl = new String(httpServletRequest.getRequestURL());
     SessionCreateParams params = 
         SessionCreateParams.builder()
             .setMode(SessionCreateParams.Mode.PAYMENT)
             .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
             .addLineItem(
                 SessionCreateParams.LineItem.builder()
                     .setPrice(priceId)
                     .setQuantity(1L)
                     .build())
             .setSuccessUrl(requestUrl.replace("/payment/process", "/payment/success"))
             .setCancelUrl(requestUrl.replace("/payment/process", "/payment/cancel"))
             .setCustomerEmail(user.getEmail())
             .build();

     try {
         Session session = Session.create(params);
         return session.getId();
     } catch (StripeException e) {
         e.printStackTrace();
         throw new RuntimeException("決済セッションの作成に失敗しました", e);
     }
 }*/
