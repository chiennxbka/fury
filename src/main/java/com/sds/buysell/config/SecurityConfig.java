package com.sds.buysell.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${security.enabled:true}")
    private boolean securityEnabled;

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        if (securityEnabled) {
            security.csrf().disable();
            security.cors().and().authorizeRequests()
                    .antMatchers("/api/v1/**").authenticated()
                    .antMatchers("").hasAnyRole("", "")
                    .antMatchers("/api/v1/auth/login").permitAll();
            security.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthConverter);
            security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        } else {
            security.cors().and().csrf().disable().authorizeRequests().anyRequest().permitAll();
        }
        return security.build();
    }
}
