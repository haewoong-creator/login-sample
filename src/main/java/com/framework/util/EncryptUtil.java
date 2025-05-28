package com.framework.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class EncryptUtil {
	
	/**
	 * 주어진 문자열을 SHA-256으로 해싱 (MariaDB SHA2('password', 256) 방식과 동일)
	 * 
	 * @param rawPassword 원본 비밀번호
	 * @return SHA-256 해시값 (16진수 문자열)
	 */
	public String encryptSha256(String rawPassword) {
		if (rawPassword == null || rawPassword.isEmpty()) {
			throw new IllegalArgumentException("비밀번호는 null이거나 빈 값일 수 없습니다.");
		}

		try {
			// SHA-256 해싱
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));

			// 바이트 배열을 16진수 문자열로 변환
			StringBuilder hexString = new StringBuilder();
			for (byte b : hashBytes) {
				hexString.append(String.format("%02x", b));
			}

			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-256 알고리즘을 찾을 수 없습니다.", e);
		}
	}

}
