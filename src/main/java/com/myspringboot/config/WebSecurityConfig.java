//package com.myspringboot.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
////
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
////import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import com.myspringboot.security.JWTAuthenticationFilter;
//import com.myspringboot.security.JwtUtil;
//
//
//@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//	
//	@Autowired
//	private UserDetailsService uds;
//	@Autowired
//	private JwtUtil ju;
//	
//	public static final String[] PUBLIC_MATCHERS = {
//			"/produtos/**",
//			"/categorias/**",
//			"/clientes/**"
//	};
//	
////	public SecurityFilterChain springSecurityFilterChain(HttpSecurity http) throws Exception {
////		http.cors().and().csrf().disable();
////		http.authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_MATCHERS).permitAll()
////		.anyRequest().authenticated();	
////		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), ju));
////		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////		return http.build();
////	}
////	@Bean
////	CorsConfigurationSource corsConfigurationSource() {
////        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
////        return source;
////	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//		http.cors().and().csrf().disable();
//		http.authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_MATCHERS).permitAll()
//		.anyRequest().authenticated();	
//		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), ju));
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		//http.build();
//	}
//	
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//    	return new BCryptPasswordEncoder();
//    }
//    
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	auth.userDetailsService(uds).passwordEncoder(bCryptPasswordEncoder());
//    }
//	
//}
