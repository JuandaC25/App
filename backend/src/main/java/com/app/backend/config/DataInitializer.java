package com.app.backend.config;

import com.app.backend.model.User;
import com.app.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ejecutando DataInitializer...");

        // Eliminar usuarios existentes si existen (asegura contraseñas correctas)
        if (userRepository.existsByUsername("admin")) {
            userRepository.findByUsername("admin").ifPresent(u -> {
                userRepository.delete(u);
                System.out.println("Usuario ADMIN existente eliminado");
            });
        }

        if (userRepository.existsByUsername("coordinador")) {
            userRepository.findByUsername("coordinador").ifPresent(u -> {
                userRepository.delete(u);
                System.out.println("Usuario COORDINADOR existente eliminado");
            });
        }

        // Crear usuario ADMIN
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setEmail("admin@app.com");
        admin.setRole(User.Role.ADMIN);
        admin.setActive(true);
        userRepository.save(admin);
        System.out.println("Usuario ADMIN creado - username:admin, password:admin123");

        // Crear usuario COORDINADOR
        User coordinador = new User();
        coordinador.setUsername("coordinador");
        coordinador.setPassword(passwordEncoder.encode("coordinador123"));
        coordinador.setEmail("coordinador@app.com");
        coordinador.setRole(User.Role.COORDINADOR);
        coordinador.setActive(true);
        userRepository.save(coordinador);
        System.out.println("Usuario COORDINADOR creado - username:coordinador, password:coordinador123");

        System.out.println("DataInitializer completado exitosamente");
    }

}