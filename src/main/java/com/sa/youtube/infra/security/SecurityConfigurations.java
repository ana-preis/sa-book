package com.sa.youtube.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private LoggerFilter loggerFilter;

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AccessDeniedHandler getAccessDeniedHandler() {
        return new ForbiddenHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers(HttpMethod.GET, "/v3/*","/v3/api-docs/*", "/swagger-ui.html","/swagger-ui/*").permitAll();
                auth.requestMatchers(HttpMethod.POST, "/login").permitAll();
                auth.requestMatchers(HttpMethod.POST, "/users").permitAll();
                auth.requestMatchers(HttpMethod.GET, "/categories").permitAll();
                auth.requestMatchers(HttpMethod.GET, "/search").permitAll();
                auth.anyRequest().authenticated();
            })
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterAfter(loggerFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling((handling) -> {
                handling.authenticationEntryPoint(authEntryPoint);
                handling.accessDeniedHandler(getAccessDeniedHandler());
            })
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .build();
    }

}
