package com.example.kadai_002.service;

public class EmailService {
	
	public void sendPasswordResetEmail(String email, String token) {
        String resetLink = generateResetLink(token);
        // メール送信処理
        System.out.println("Send email to: " + email);
        System.out.println("Reset link: " + resetLink);
    }

    private String generateResetLink(String token) {
        // リセットリンクの生成
        return "http://example.com/reset-password?token=" + token;
    }
}
