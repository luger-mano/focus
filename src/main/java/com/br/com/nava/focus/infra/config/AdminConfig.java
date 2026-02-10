package com.br.com.nava.focus.infra.config;

import com.br.com.nava.focus.domain.model.Role;
import com.br.com.nava.focus.domain.model.User;
import com.br.com.nava.focus.domain.repository.RoleRepository;
import com.br.com.nava.focus.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminConfig implements CommandLineRunner {


    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminConfig(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Role roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
        var userAdmin = userRepository.findByFullName("admin");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("Admin já existe");
                },
                () ->{
                    User user = new User();
                    user.setFullName("admin");
                    user.setEmail("admin@admin.com");
                    user.setPassword(passwordEncoder.encode("3571"));
                    user.setRoles(Set.of(roleAdmin));

                    userRepository.save(user);
                }
        );

    }
}
