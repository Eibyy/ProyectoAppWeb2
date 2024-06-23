package com.uleam.tejenaproyecto;

import com.uleam.tejenaproyecto.security.modelos.User.Role;
import com.uleam.tejenaproyecto.security.modelos.User.User;
import com.uleam.tejenaproyecto.security.modelos.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TejenaProyectoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TejenaProyectoApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        return (args) -> {
            String username = "admin";
            if (userRepository.findByUsername(username).isEmpty()) {
                User user = User.builder()
                        .username(username)
                        .lastname("owner")
                        .firstname("proyect")
                        .country("USA")
                        .password(passwordEncoder.encode("admin"))
                        .role(Role.USER)
                        .build();

                userRepository.save(user);
            } else {
                System.out.println("El usuario con username " + username + " ya existe.");
            }
        };
    }

}
