package com.benchmark.ushers.common.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashProcessor {

	public String getHashPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}
}
