package com.cesararaujostudio.api;

import com.cesararaujostudio.api.domain.Role;
import com.cesararaujostudio.api.model.User;
import com.cesararaujostudio.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
@AllArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        this.initDatabase();
    }

    private void initDatabase() {
        if (!this.userRepository.existsById(1L)) {
            HashSet<Role> roles = new HashSet<>(Arrays.asList(Role.ADMIN));
            this.userRepository.save(
                    User.builder().username("cleomarsilva@ienh.com.br").name("Administrador do Sistema").email("cleomarsilva@ienh.com.br")
                            .encryptedPassword(this.passwordEncoder.encode("1234")).roles(roles).build());

        }
    }


}
