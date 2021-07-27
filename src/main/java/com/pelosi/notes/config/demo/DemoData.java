package com.pelosi.notes.config.demo;

import com.pelosi.notes.repository.UserRepository;
import com.pelosi.notes.repository.entity.Role;
import com.pelosi.notes.repository.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DemoData implements ApplicationRunner {

    private final @NonNull UserRepository repo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user2 = User.builder()
                .id(null)
                .username("Gabriel-ADMIN")
                .password("admin-senha@123")
                .roles(List.of(Role.ROLE_ADMIN))
                .build();
        repo.findByUsername("Gabriel-ADMIN").map(user -> {
            repo.delete(user);
            return user;
        });


        repo.save(user2);


    }
}