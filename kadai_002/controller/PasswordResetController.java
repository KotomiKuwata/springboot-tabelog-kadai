package com.example.kadai_002.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kadai_002.service.PasswordResetService;

@RestController
@RequestMapping("/auth")
public class PasswordResetController {

	@Autowired
	private PasswordResetService passwordResetService;

	@PostMapping("/request-password-reset")
	public void requestPasswordReset(@RequestParam String email) {
		passwordResetService.requestPasswordReset(email);
	}

	@PostMapping("/reset-password")
	public boolean resetPassword(@RequestParam String token, @RequestParam String newPassword) {
		return passwordResetService.resetPassword(token, newPassword);
	}
}
