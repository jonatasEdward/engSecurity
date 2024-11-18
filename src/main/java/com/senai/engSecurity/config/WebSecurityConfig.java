package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para facilitar testes via API REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll() // Permite acesso público a essa URL
                        .anyRequest().authenticated() // Exige autenticação para qualquer outra URL
                )
                .httpBasic(Customizer.withDefaults()); // Habilita o Basic Authentication

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("usuarioMemoria")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        var admin = User.withUsername("adminMemoria")
                .password(passwordEncoder().encode("adminpass"))
                .roles("ADMIN")
                .build();
        System.out.println("USER: " + user);
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
