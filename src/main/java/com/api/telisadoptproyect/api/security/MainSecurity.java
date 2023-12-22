package com.api.telisadoptproyect.api.security;

import com.api.telisadoptproyect.api.configuration.CustomPermissionEvaluator;
import com.api.telisadoptproyect.api.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MainSecurity {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Autowired
    private CustomPermissionEvaluator customPermissionEvaluator;

    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf().disable();
        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                "/auth/generate-login-token",
                "/auth/reset-password-link",
                "/auth/resend-otp-code",
                "/auth/update-password");
    }

}