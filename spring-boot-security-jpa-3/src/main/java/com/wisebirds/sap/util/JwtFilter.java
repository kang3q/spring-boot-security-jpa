package com.wisebirds.sap.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.wisebirds.sap.domain.account.Account;
import com.wisebirds.sap.repository.account.AccountRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {

	private AccountRepository accountRepository;

	public JwtFilter(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final String token = request.getParameter("access_token");

		if (StringUtils.isEmpty(token)) {
			throw new ServletException("Missing or invalid access_token.");
		}

		try {
			final Claims claims = Jwts.parser().setSigningKey(AppContext.SECRET_KEY).parseClaimsJws(token).getBody();
			request.setAttribute("claims", claims);
			Account account = accountRepository.findOneByEmail(claims.getSubject()).get();
			System.out.println(AppContext.GSON.toJson(account));
		} catch (final SignatureException e) {
			throw new ServletException("Invalid token.");
		}

		chain.doFilter(req, res);
	}

}
