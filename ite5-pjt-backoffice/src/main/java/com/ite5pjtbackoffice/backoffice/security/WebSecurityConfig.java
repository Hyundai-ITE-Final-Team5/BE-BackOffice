package com.ite5pjtbackoffice.backoffice.security;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Resource
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("configure(HttpSecurity http) 실행");

		http.formLogin() // FormLoginConfiguer configuer = ~~ 이렇게 받아서 잘 쓰지는 않고 바로쓴다.
				.loginPage("/") // 요청 경로이므로 @requestmapping 메서드를 만들어야한다. //default: /login(GET)
				.usernameParameter("mid") // default: username //설정안하면
				.passwordParameter("mpassword") // default: password //설정안하면
				.loginProcessingUrl("/access") // default: /login(POST) post방식으로 로그인을 요청해야한다.
				.defaultSuccessUrl("/admin")
				.failureUrl("/"); // default: /login?error

		http.logout()
			.logoutUrl("/logout").logoutSuccessUrl("/");
			
		// 사이트간 요청 위조 방지 비활성화
		http.csrf().disable();

		// 요청 경로 권한 설정
		http.authorizeRequests()
			.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
//			.antMatchers("/**").authenticated()
			.antMatchers("/**").permitAll()
			;

		http.exceptionHandling().accessDeniedPage("/login");

		// CORS 설정 활성화
		http.cors();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("SELECT mid, mpassword, menabled FROM member WHERE mid=?")
				.authoritiesByUsernameQuery("SELECT mid, mrole FROM member WHERE mid=?")
				.passwordEncoder(passwordEncoder());		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		log.info("configure(WebSecurity web) 실행");
		DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
		defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchyImpl());
		web.expressionHandler(defaultWebSecurityExpressionHandler);
		web.ignoring().antMatchers("/images/**").antMatchers("/css/**").antMatchers("/js/**")
				.antMatchers("/bootstrap-4.6.0-dist/**").antMatchers("/jquery/**").antMatchers("/favicon.ico");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// return new BCryptPasswordEncoder();
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public RoleHierarchyImpl roleHierarchyImpl() {
		log.info("실행");
		RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
		roleHierarchyImpl.setHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER");
		return roleHierarchyImpl;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		// 모든 요청 사이트 허용
		config.addAllowedOrigin("*");
		// 모든 요청 방식 허용
		config.addAllowedMethod("*");
		// 모든 요청 헤드 허용
		config.addAllowedHeader("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return source;
	}
}
