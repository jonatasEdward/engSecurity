package com.senai.engSecurity.service;

import com.senai.engSecurity.model.User;
import com.senai.engSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsernameAndPassword(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // Comparar a senha fornecida com a senha criptografada
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new UsernameNotFoundException("Senha incorreta");
        }
        return user;
    }

    public User login(User user) {
        User foundUser = userRepository
                .findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        if (passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            return foundUser; // Login bem-sucedido
        } else {
            throw new UsernameNotFoundException("Credenciais inválidas");
        }
    }
}
