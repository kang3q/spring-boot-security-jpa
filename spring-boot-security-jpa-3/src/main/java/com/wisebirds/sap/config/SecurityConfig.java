package com.wisebirds.sap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.wisebirds.sap.repository.account.AccountRepository;
import com.wisebirds.sap.service.authentication.AuthenticationService;
import com.wisebirds.sap.util.JwtFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter(accountRepository));
		registrationBean.addUrlPatterns("/v1.0/*");
		registrationBean.addUrlPatterns("/v1.1/*");
		return registrationBean;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //
				.antMatchers("/favicon.ico").permitAll() //
				.antMatchers("**/*.jsp").permitAll() //
				.antMatchers("**/*.js").permitAll() //
				.antMatchers("**/*.css").permitAll() //
				//
				.antMatchers("/").permitAll() //
				.antMatchers("/v1.*/**").permitAll() //
				//
				.antMatchers("/admin/**").hasAuthority("ADMIN") //
				.antMatchers("/user/**").hasAnyAuthority("ADMIN", "USER") //
				.antMatchers("/client/**").hasAnyAuthority("ADMIN", "CLIENT") //
				.antMatchers("/reviewer/**").hasAnyAuthority("ADMIN", "REVIEWER") //
				.antMatchers("/main/**").hasAnyAuthority("ADMIN", "USER", "CLIENT", "REVIEWER") //
				.anyRequest().fullyAuthenticated() //
				.and() //
				//
				.formLogin() //
				.loginPage("/signin") //
				.failureUrl("/signin?error") //
				.usernameParameter("email") //
				// .defaultSuccessUrl("/main", true) //successHandler가 있으면 안먹히는듯. -> 핸들러에서 처리함
				.successHandler(customAuthenticationSuccessHandler)//
				.permitAll() //
				.and() //
				//
				.logout() //
				.logoutUrl("/signout") //
				.deleteCookies("remember-me") //
				.logoutSuccessUrl("/") //
				.permitAll() //
				.and() //
				//
				.rememberMe() //
				.and() //
				//
				.csrf().disable(); // <form action="/signout" method="post"><input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"></form>
	}

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	// auth.inMemoryAuthentication().withUser("ksg").password("1").roles("USER");
	// }
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
		auth.userDetailsService(authenticationService).passwordEncoder(encoder);
	}

}


