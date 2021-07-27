package com.pelosi.notes.service.serviceImpl;


import com.pelosi.notes.exception.UserNotFoundException;
import com.pelosi.notes.repository.UserRepository;
import com.pelosi.notes.repository.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final @NonNull UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> opUser = userRepository.findByUsername(s);
        if (opUser.isPresent()){
            return opUser.get();
        }else {
            throw new UserNotFoundException("Usuário não encontrado");
        }
    }
}
