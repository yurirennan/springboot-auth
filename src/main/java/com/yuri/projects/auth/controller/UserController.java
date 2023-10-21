package com.yuri.projects.auth.controller;

import com.yuri.projects.auth.dto.UserDTO;
import com.yuri.projects.auth.dto.out.UserDTOResponse;
import com.yuri.projects.auth.models.User;
import com.yuri.projects.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody final UserDTO userDTO) {
        this.userService.saveUser(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTOResponse> getUser(@PathVariable("id") final Long id) {
        final UserDTOResponse user = this.userService.getUser(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
