package com.cilek.cilekbank.config;

import com.cilek.cilekbank.service.abstracts.IUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private IUserDetailsService userDetailsService;

    private JTWAuthFilter jtwAuthFilter;

    private CustomLogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.requestMatchers(
                                        HttpMethod.GET,
                                        "/api/user/**",
                                        "/api/accounts/**",
                                        "/api/transaction/**"
                                ).authenticated()
                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/api/user/**",
                                        "/api/accounts/**",
                                        "/api/transaction/**"
                                ).authenticated()
                                .requestMatchers(
                                        HttpMethod.PUT,
                                        "/api/user/**",
                                        "/api/accounts/**",
                                        "/api/transaction/**"
                                ).authenticated()
                                .requestMatchers(
                                        HttpMethod.DELETE,
                                        "/api/user/**",
                                        "/api/accounts/**",
                                        "/api/transaction/**"
                                ).authenticated()
                                .requestMatchers(
                                        "/api/users/register",
                                        "/api/users/login",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**"
                                ).permitAll()
                ).sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jtwAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutUrl("/api/users/logout")
                        .addLogoutHandler(logoutHandler)    // Custom logout handler
                        .logoutSuccessHandler((request, response, authentication) ->
                                SecurityContextHolder.clearContext() // Clear the security context
                        ));

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
