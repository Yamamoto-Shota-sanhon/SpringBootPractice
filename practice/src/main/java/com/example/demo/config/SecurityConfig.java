package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.repository.AdminRepository;
import com.example.demo.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/contact","/contact/confirm","/contact/register","/contact/complete","/admin/signup", "/admin/signin", "/admin/contacts","/admin/contacts/{id}","/admin/contacts/{id}/edit","/admin/contacts/{id}/delete")
                .permitAll()
                .anyRequest().authenticated());
            /*.formLogin(form -> form.loginPage("/signin")
                .defaultSuccessUrl("/test", true)
                .failureUrl("/signin"));*/

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomUserDetailsService customUserDetailsService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        return new CustomUserDetailsService(adminRepository, passwordEncoder);
    }
}