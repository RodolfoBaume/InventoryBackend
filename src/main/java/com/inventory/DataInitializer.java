package com.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.inventory.entity.Rol;
import com.inventory.entity.Usuario;
import com.inventory.repository.IRolRepository;
import com.inventory.repository.IUsuarioRepository;

import jakarta.transaction.Transactional;

@Configuration
public class DataInitializer {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public ApplicationRunner initData() {
        return args -> initializeRolesAndUsers();
    }

    @Transactional
    public void initializeRolesAndUsers() {
        // Verifica y crea roles solo si faltan
        Rol adminRole = rolRepository.findByNombre("ADMIN")
                .orElseGet(() -> rolRepository.save(new Rol(null, "ADMIN")));
        Rol userRole = rolRepository.findByNombre("USER")
                .orElseGet(() -> rolRepository.save(new Rol(null, "USER")));

        // Verifica y crea el usuario administrador solo si falta
        if (usuarioRepository.findByUsername("admin@server.com").isEmpty()) {
            Usuario adminUser = new Usuario();
            adminUser.setUsername("admin@server.com");
            adminUser.setPassword(passwordEncoder.encode("1234"));
            adminUser.setRol(List.of(adminRole));
            usuarioRepository.save(adminUser);
        }

        // Verifica y crea el usuario regular solo si falta
        if (usuarioRepository.findByUsername("user@server.com").isEmpty()) {
            Usuario normalUser = new Usuario();
            normalUser.setUsername("user@server.com");
            normalUser.setPassword(passwordEncoder.encode("1234"));
            normalUser.setRol(List.of(userRole));
            usuarioRepository.save(normalUser);
        }
    }
}
