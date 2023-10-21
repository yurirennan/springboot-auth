package com.yuri.projects.auth.details.service;

import com.yuri.projects.auth.details.UserDetailsData;
import com.yuri.projects.auth.exceptions.UserNotFoundException;
import com.yuri.projects.auth.models.User;
import com.yuri.projects.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = this.userRepository.findByUsername(username);

        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("Usuario não encontrado!");
        }

        return new UserDetailsData(userOptional.get());
    }
}