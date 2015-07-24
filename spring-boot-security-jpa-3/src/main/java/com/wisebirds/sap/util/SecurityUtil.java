package com.wisebirds.sap.util;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class SecurityUtil {

	/**
	 * 토큰을 생성해 반환해준다.
	 * 
	 * @param accountId
	 * @param role
	 * @return access_token
	 */
	public static String createAccessToken(String accountId, String role) {
		return Jwts.builder().setId(accountId).claim("roles", role).signWith(SignatureAlgorithm.HS512, AppContext.SECRET_KEY).compact();
		// return Jwts.builder().setSubject(accountId).claim("roles", role).signWith(SignatureAlgorithm.HS512, AppContext.SECRET_KEY).compact();
	}

	/**
	 * 토큰을 생성해 반환해준다.
	 * 
	 * @param accountId
	 * @param role
	 * @return access_token
	 */
	public static String createAccessToken(long accountId, String role) {
		return createAccessToken(String.valueOf(accountId), role);
	}

	/**
	 * sha로 인코딩 후 반환.
	 * 
	 * @param password
	 * @return password
	 */
	public static String encodinPassword(String password) {
		return new ShaPasswordEncoder().encodePassword(password, null);
	}


}
