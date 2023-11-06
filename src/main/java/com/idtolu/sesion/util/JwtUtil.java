package com.idtolu.sesion.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class JwtUtil {

	private static final String KEYWORD = "35CU314";
	private static Algorithm ALGORITHM = Algorithm.HMAC256(KEYWORD);

	public String generarToken(String usuario) {

		String token = JWT.create()
				.withSubject(usuario)
				.withIssuer("id-tolu")
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)))
				.sign(ALGORITHM);

		return token;
	}

	public boolean isValid(String token) {
		try {
			JWT.require(ALGORITHM)
			.build()
			.verify(token);
			return true;
		} catch (JWTVerificationException e) {
			return false;
		}
	}

	public String getUsername(String token) {
		return JWT.require(ALGORITHM)
				.build()
				.verify(token)
				.getSubject();
	}

}
