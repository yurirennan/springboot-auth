package com.yuri.projects.auth.service;

import com.yuri.projects.auth.dto.UserDTO;
import com.yuri.projects.auth.dto.out.UserDTOResponse;
import com.yuri.projects.auth.exceptions.UserNotAuthorizedException;
import com.yuri.projects.auth.exceptions.UserNotFoundException;
import com.yuri.projects.auth.models.User;
import com.yuri.projects.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(final UserRepository userRepository,
                       final PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void saveUser(final UserDTO userDTO) {
        final User user = userDTO.toUser();

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        this.userRepository.save(user);
    }

    public UserDTOResponse getUser(final Long id) {

        final String idUsuarioLogado = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!id.equals(Long.parseLong(idUsuarioLogado))) {
            throw new UserNotAuthorizedException("Usuario não autorizado!");
        }

        final Optional<User> user = this.userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("Usuario não encontrado!");
        }

        final UserDTOResponse userDTOResponse = UserDTOResponse.from(user.get());

        return userDTOResponse;
    }

}
